package com.paxera.ultima.tests;

import com.paxera.ultima.utils.RetryAnalyzer;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;

public class CompareStudiesTest extends BaseTest {
    Common common = new Common();
    String pat_ID = setPaxerahealthCorporationConfig().getProperty("patientId");
    String dcmCherryStudy = setPaxerahealthCorporationConfig().getProperty("dicomCherryStudy");
    String dcm17300Study = setPaxerahealthCorporationConfig().getProperty("dicom17300Study");
    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");
    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");
    public CompareStudiesTest() throws IOException {
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to verify Compare Studies")
    @Story("Story Name: Compare Studies")
    public void verifyCompareStudies() throws IOException, InterruptedException {
        Allure.step("Start DB connection");
        common.startConnection();
        Allure.step("Import first study");
        common.importStudy(dcmCherryStudy);
        Allure.step("Import second study");
        common.importStudy(dcm17300Study);
        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);
        Allure.step("Update study dates to today");
        Common.sqlQuery("update Studies Set StudyDate = GETDATE() where Study_ID = '" + study_ID + "'");
        Common.sqlQuery("update Studies Set StudyDate = GETDATE() where Study_ID = '" + study_ID2 + "'");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Today")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnSearchButton()
                .selectStudy("1")
                .selectStudy("2");
        Allure.step("Select both studies for comparison");
        browser.ultimaViewer.workListPage.clickOnWorkListActionsButtton().selectFromWorkListActionsMenu("Compare Studies");
        Allure.step("Compare the selected studies");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID2 + "'");
        Allure.step("Clean up studies from DB");
        Common.closeConnection();
        Allure.step("Close DB connection");
    }
}