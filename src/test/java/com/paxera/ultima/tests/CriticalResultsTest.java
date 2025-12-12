package com.paxera.ultima.tests;

import com.paxera.ultima.utils.RetryAnalyzer;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;

public class CriticalResultsTest extends BaseTest {
    Common common = new Common();
    String pat_ID = setPaxerahealthCorporationConfig().getProperty("patientId");
    String dcmCherryStudy = setPaxerahealthCorporationConfig().getProperty("dicomCherryStudy");
    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");
    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");
    public CriticalResultsTest() throws IOException {
    }

    @Test(retryAnalyzer = RetryAnalyzer.class, priority = 1)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to verify Set Critical Findings for Critical Results")
    @Story("Story Name: Critical Results - Set Critical Findings")
    public void verifySetCriticalFinding() throws IOException, InterruptedException {
        Allure.step("Start DB connection");
        common.startConnection();
        Allure.step("Import study");
        common.importStudy(dcmCherryStudy);
        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnSearchButton();
        Allure.step("Search and select study");
        browser.ultimaViewer.workListPage.clickOnStudyActionButton()
                .selectFromStudyActionsMenu("Critical Results", "Set Critical Findings");
        browser.ultimaViewer.workListPage.switchToEditStudyFrame();
        browser.ultimaViewer.setCriticalFindingsPage.clickOnFindingsDropDownList()
                .selectFinding("Critical Findings")
                .clickOnSubmitButton();
        Allure.step("Set critical findings and submit");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
        Allure.step("Close DB connection");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class, priority = 2)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to verify Log Notification for Critical Results")
    @Story("Story Name: Critical Results - Log Notification")
    public void verifyLogNotification() throws IOException, InterruptedException {
        Allure.step("Start DB connection");
        common.startConnection();
        common.setCriticalFinding(pat_ID, dcmCherryStudy, userName, password, study_ID2);
        browser.ultimaViewer.workListPage.clickOnSearchButton();
        browser.ultimaViewer.workListPage.clickOnStudyActionButton()
                .selectFromStudyActionsMenu("Critical Results", "Log Notification");
        browser.ultimaViewer.workListPage.switchToEditStudyFrame();
        browser.ultimaViewer.setCriticalFindingsPage.clickOnRefPhysicianRadioButton()
                .clickOnRefPhysicianDropDownList()
                .selectRefPhysician("Ref1")
                .clickOnSubmitButton();
        Allure.step("Log notification for Ref Physician");
        browser.ultimaViewer.workListPage.switchToDefaultFrame()
                .clickOnSearchButton();
        browser.ultimaViewer.workListPage.clickOnStudyActionButton()
                .selectFromStudyActionsMenu("Critical Results", "Log Notification");
        browser.ultimaViewer.workListPage.switchToEditStudyFrame();
        String actualResult = browser.ultimaViewer.setCriticalFindingsPage.getNotificationHistoryText();
        Allure.step("Check notification history");
        Assert.assertTrue(actualResult.contains(userName));
        Allure.step("Verify notification history contains user name");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
        Allure.step("Close DB connection");
    }
}
