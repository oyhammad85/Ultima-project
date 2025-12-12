package com.paxera.ultima.tests;

import com.paxera.ultima.driver.DriverManager;
import com.paxera.ultima.screen.LoginPage;
import com.paxera.ultima.screen.SetCriticalFindingsPage;
import com.paxera.ultima.screen.UltimaPluginPage;
import com.paxera.ultima.screen.WorkListPage;
import com.paxera.ultima.waits.WaitUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;

public class Common {
    private static Connection connection = null;
    WaitUtils waitUtils = new WaitUtils();
    UltimaPluginPage ultimaPluginPage = new UltimaPluginPage();
    LoginPage loginPage = new LoginPage();
    WorkListPage workListPage = new WorkListPage();
    SetCriticalFindingsPage setCriticalFindingsPage = new SetCriticalFindingsPage();
    String dateBaseConnectionURL = setPaxerahealthCorporationConfig().getProperty("dateBaseURl");
    String dateBaseConnectionUsername = setPaxerahealthCorporationConfig().getProperty("dateBaseUsername");
    String dateBaseConnectionPassword = setPaxerahealthCorporationConfig().getProperty("dateBasePassword");
    String branchIdImport = setPaxerahealthCorporationConfig().getProperty("branchId");

    public Common() throws IOException {
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close(); // Close the connection
                System.out.println("Connection closed successfully.");
            } catch (SQLException e) {
                System.out.println("Error closing the connection: " + e.getMessage());
            } finally {
                connection = null; // Set the connection to null after closing
            }
        } else {
            System.out.println("Connection is already null or not initialized.");
        }
    }

    public static void sqlQuery(String sql) {
        Statement statement = null;

        try {
            statement = connection.createStatement();

            // Execute the update query
            int rowsAffected = statement.executeUpdate(sql);

            // Print result
            if (rowsAffected > 0) {
                System.out.println("Record updated successfully.");
            } else {
                System.out.println("No records were updated.");
            }

        } catch (SQLException e) {
            System.err.println("Error executing update: " + e.getMessage());
        }
    }

    public void loginUltima(String username, String password) throws IOException {
        waitUtils.waitUntilElementIsPresence(loginPage.getUserNameInput()).sendKeys(username);
        waitUtils.waitUntilElementIsPresence(loginPage.getPasswordInput()).sendKeys(password);
        waitUtils.waitUntilElementIsPresence(loginPage.getLoginButton()).click();
        ultimaPluginPage.clickOnGoToWorkListButton();
    }

    public void OtherUsersLoginUltima(String username, String password) throws IOException {
        waitUtils.waitUntilElementIsPresence(loginPage.getUserNameInput()).sendKeys(username);
        waitUtils.waitUntilElementIsPresence(loginPage.getPasswordInput()).sendKeys(password);
        waitUtils.waitUntilElementIsPresence(loginPage.getLoginButton()).click();
    }

    public void importStudy(String studyName) throws InterruptedException, IOException {
        File fileToUpload = new File(studyName);
        Response restAssuredResponse = (Response) RestAssured.given()
                .multiPart("file", fileToUpload)
                .multiPart("branchid", branchIdImport)
                .post(setPaxerahealthCorporationConfig().getProperty("URL") + "ImportLabeling/home/UploadFile");
        Assert.assertEquals(restAssuredResponse.getStatusCode(), 200);
        String responseBody = restAssuredResponse.getBody().asString();
        System.out.println("Response Body: " + responseBody);
        Thread.sleep(3000);
    }

    public String GetCurrentURL() throws InterruptedException {
        ArrayList<String> tabs = new ArrayList<>(DriverManager.getWebDriver().getWindowHandles());
        Thread.sleep(2000);
        DriverManager.getWebDriver().switchTo().window(tabs.get(1));
        String newUrl = DriverManager.getWebDriver().getCurrentUrl();
        System.out.println("New URL:" + newUrl);
        return newUrl;
    }

    public Connection startConnection() {
        try {
            connection = DriverManager.getConnection(dateBaseConnectionURL, dateBaseConnectionUsername, dateBaseConnectionPassword);
            System.out.println("Connection established successfully.");
        } catch (SQLException e) {
            System.out.println("Error establishing connection: " + e.getMessage());
        }

        return connection;
    }

    public void setCriticalFinding(String pat_ID, String dcmCherryStudy, String userName, String password, String study_ID) throws IOException, InterruptedException {
        startConnection();
        importStudy(dcmCherryStudy);
        loginUltima(userName, password);
        workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnSearchButton();
        workListPage.clickOnStudyActionButton()
                .selectFromStudyActionsMenu("Critical Results", "Set Critical Findings");
        workListPage.switchToEditStudyFrame();
        setCriticalFindingsPage.clickOnFindingsDropDownList()
                .selectFinding("Critical Findings")
                .clickOnSubmitButton();
        workListPage.switchToDefaultFrame();
        Thread.sleep(4000);
    }

    public void setCriticalFindingAndLog(String pat_ID, String dcmCherryStudy, String userName, String password, String study_ID) throws IOException, InterruptedException {
        startConnection();
        importStudy(dcmCherryStudy);
        loginUltima(userName, password);
        workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnSearchButton();
        workListPage.clickOnStudyActionButton()
                .selectFromStudyActionsMenu("Critical Results", "Set Critical Findings");
        workListPage.switchToEditStudyFrame();
        setCriticalFindingsPage.clickOnFindingsDropDownList()
                .selectFinding("Critical Findings")
                .clickOnSubmitButton();
        workListPage.switchToDefaultFrame();
        Thread.sleep(4000);
        workListPage.clickOnSearchButton()
                .clickOnStudyActionButton()
                .selectFromStudyActionsMenu("Critical Results", "Log Notification");
        workListPage.switchToEditStudyFrame();
        setCriticalFindingsPage.clickOnRefPhysicianRadioButton()
                .clickOnRefPhysicianDropDownList()
                .selectRefPhysician("ref1")
                .clickOnSubmitButton();
        Thread.sleep(4000);
    }
}
