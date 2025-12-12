package com.paxera.ultima.tests;

import com.paxera.ultima.driver.DriverManager;
import com.paxera.ultima.utils.RetryAnalyzer;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;

@Epic("Study Management")
@Feature("Advanced Search")
public class AdvancedSearchTest extends BaseTest {
    Common common = new Common();
    String pat_ID = setPaxerahealthCorporationConfig().getProperty("patientId");
    String dcmCherryStudy = setPaxerahealthCorporationConfig().getProperty("dicomCherryStudy");
    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");
    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");

    public AdvancedSearchTest() throws IOException {
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify advanced search functionality using Not Viewed status")
    @Story("Advanced Search - Viewed Status")
    public void verifyAdvancedSearchViaViewedStatusNotViewed() throws IOException, InterruptedException {
        Allure.step("Start connection and import study");
        common.startConnection();
        common.importStudy(dcmCherryStudy);

        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);

        Allure.step("Perform advanced search with Not Viewed status");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnAdvancedSearchButton();
        browser.ultimaViewer.advancedSearchScreen.selectViewedStatusItems(3, "Not Viewed")
                .clickOnSearchButton();

        Allure.step("Verify search results");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(pat_ID));

        Allure.step("Clean up test data");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify advanced search functionality using Viewed status")
    @Story("Advanced Search - Viewed Status")
    public void verifyAdvancedSearchViaViewedStatusViewed() throws IOException, InterruptedException {
        Allure.step("Start connection and import study");
        common.startConnection();
        common.importStudy(dcmCherryStudy);

        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);

        Allure.step("Open study to mark as viewed");
        String parenthandle = DriverManager.getWebDriver().getWindowHandle();
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnSearchButton();
        browser.ultimaViewer.workListPage.clickOnStudyActionButton()
                .selectFromStudyActionsMenu(" View in Web Viewer (ZFP)");

        Allure.step("Switch back to main window and perform advanced search");
        DriverManager.getWebDriver().switchTo().window(parenthandle);
        browser.ultimaViewer.workListPage.clickOnAdvancedSearchButton();
        browser.ultimaViewer.advancedSearchScreen.selectViewedStatusItems(2, "Viewed")
                .clickOnSearchButton();

        Allure.step("Verify search results");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(pat_ID));

        Allure.step("Clean up test data");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify advanced search functionality using None viewed status")
    @Story("Advanced Search - Viewed Status")
    public void verifyAdvancedSearchViaViewedStatusNone() throws IOException, InterruptedException {
        Allure.step("Start connection and import study");
        common.startConnection();
        common.importStudy(dcmCherryStudy);

        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);

        Allure.step("Perform advanced search with None viewed status");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnAdvancedSearchButton();
        browser.ultimaViewer.advancedSearchScreen.selectViewedStatusItems(1, "None")
                .clickOnSearchButton();

        Allure.step("Verify search results");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(pat_ID));

        Allure.step("Clean up test data");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify advanced search functionality using None dictated status")
    @Story("Advanced Search - Dictated Status")
    public void verifyAdvancedSearchViaDictatedStatusNone() throws IOException, InterruptedException {
        Allure.step("Start connection and import study");
        common.startConnection();
        common.importStudy(dcmCherryStudy);

        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);

        Allure.step("Perform advanced search with None dictated status");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnAdvancedSearchButton();
        browser.ultimaViewer.advancedSearchScreen.selectDictatedStatusItems(1, "None")
                .clickOnSearchButton();

        Allure.step("Verify search results");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(pat_ID));

        Allure.step("Clean up test data");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify advanced search functionality using Not Dictated status")
    @Story("Advanced Search - Dictated Status")
    public void verifyAdvancedSearchViaDictatedStatusNotDictated() throws IOException, InterruptedException {
        Allure.step("Start connection and import study");
        common.startConnection();
        common.importStudy(dcmCherryStudy);

        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);

        Allure.step("Perform advanced search with Not Dictated status");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnAdvancedSearchButton();
        browser.ultimaViewer.advancedSearchScreen.selectDictatedStatusItems(3, "Not Dictated")
                .clickOnSearchButton();

        Allure.step("Verify search results");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(pat_ID));

        Allure.step("Clean up test data");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify advanced search functionality using Dictated status")
    @Story("Advanced Search - Dictated Status")
    public void verifyAdvancedSearchViaDictatedStatusDictated() throws IOException, InterruptedException {
        Allure.step("Start connection and import study");
        common.startConnection();
        common.importStudy(dcmCherryStudy);

        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);

        Allure.step("Mark study as dictated");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnSearchButton();
        browser.ultimaViewer.workListPage.selectStudy("1");
        browser.ultimaViewer.workListPage.clickOnWorkListActionsButtton().selectFromWorkListActionsMenu("Mark As", "Mark As Dictated");

        Allure.step("Perform advanced search with Dictated status");
        browser.ultimaViewer.workListPage.clickOnAdvancedSearchButton();
        browser.ultimaViewer.advancedSearchScreen.selectDictatedStatusItems(2, "Dictated")
                .clickOnSearchButton();
        Allure.step("Verify search results");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(pat_ID));
        common.startConnection();
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }
}
