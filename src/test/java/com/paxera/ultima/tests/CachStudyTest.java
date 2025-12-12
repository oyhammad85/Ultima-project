package com.paxera.ultima.tests;

import com.paxera.ultima.utils.RetryAnalyzer;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;

public class CachStudyTest extends BaseTest {
    Common common = new Common();
    String pat_ID = setPaxerahealthCorporationConfig().getProperty("patientId");
    String dcmCherryStudy = setPaxerahealthCorporationConfig().getProperty("dicomCherryStudy");
    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");
    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");
    public CachStudyTest() throws IOException {
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to verify Cache Study")
    @Story("Story Name: Cache Study")
    public void verifyCacheStudy() throws IOException, InterruptedException {
        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);
        Allure.step("Import study");
        common.importStudy(dcmCherryStudy);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnSearchButton()
                .selectStudy("1");
        Allure.step("Select study and open Cache Studies action");
        browser.ultimaViewer.workListPage.clickOnWorkListActionsButtton().selectFromWorkListActionsMenu("Cache Studies", "Cache Studies");
        Allure.step("Cache the study");
        common.startConnection();
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to verify View Cached Studies")
    @Story("Story Name: View Cached Studies")
    public void verifyViewCachedStudies() throws IOException, InterruptedException {
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
                .clickOnSearchButton()
                .selectStudy("1");
        Allure.step("Select study and open View Cached Studies action");
        browser.ultimaViewer.workListPage.clickOnWorkListActionsButtton().selectFromWorkListActionsMenu("Cache Studies", "View Cached Studies");
        Allure.step("View cached studies");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();

    }
}
