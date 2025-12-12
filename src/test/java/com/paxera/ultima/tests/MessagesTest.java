package com.paxera.ultima.tests;

import com.paxera.ultima.driver.DriverManager;
import com.paxera.ultima.screen.LoginPage;
import com.paxera.ultima.screen.MessagesPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;

public class MessagesTest extends BaseTest {
    Common common = new Common();
    LoginPage loginPage = new LoginPage();

    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    String messageCountUserName = setPaxerahealthCorporationConfig().getProperty("refUsername");
    String pat_ID = setPaxerahealthCorporationConfig().getProperty("patientId");
    String dcmCherryStudy = setPaxerahealthCorporationConfig().getProperty("dicomCherryStudy");
    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");

    public MessagesTest() throws IOException {
    }

    @Test(description = "Verify messages button is clickable")
    public void verifyMessagesButtonIsClickable() throws IOException, InterruptedException {
        common.loginUltima(userName, password);

        browser.ultimaViewer.messagesPage.clickOnMessagesButton();
        browser.ultimaViewer.messagesPage.verifyEmptyMessageTable();
    }

    @Test(description = "Verify user has received messages by checking message count")
    public void verifyMessageCount() throws IOException, InterruptedException {
        common.setCriticalFindingAndLog(pat_ID, dcmCherryStudy, userName, password, study_ID2);
        DriverManager.getWebDriver().get(setPaxerahealthCorporationConfig().getProperty("URL"));
        browser.ultimaViewer.loginPage.enterUsername(messageCountUserName)
                .enterPassword(password)
                .clickOnSubmitButton();
        browser.ultimaViewer.messagesPage.clickOnMessagesButton();
        browser.ultimaViewer.messagesPage.getMessageCount();
        Assert.assertTrue(browser.ultimaViewer.messagesPage.getMessageCount() > 0, "Message count is not greater than zero.");
    }

    @Test(description = "Verify opening first message")
    public void verifyOpeningMessage() throws IOException, InterruptedException {
        common.setCriticalFindingAndLog(pat_ID, dcmCherryStudy, userName, password, study_ID2);
        DriverManager.getWebDriver().get(setPaxerahealthCorporationConfig().getProperty("URL"));
        browser.ultimaViewer.loginPage.enterUsername(messageCountUserName)
                .enterPassword(password)
                .clickOnSubmitButton();
        browser.ultimaViewer.messagesPage.clickOnMessagesButton();
        browser.ultimaViewer.messagesPage.selectFirstMessage();
        Assert.assertTrue(browser.ultimaViewer.messagesPage.getModalTitle().contains("Messages") );
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }
}
