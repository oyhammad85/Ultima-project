package com.paxera.ultima.tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;
import static org.testng.Assert.assertEquals;

public class URLShareTest extends BaseTest {

    Common common = new Common();
    String pat_ID = setPaxerahealthCorporationConfig().getProperty("patientId");
    String dcmCherryStudy = setPaxerahealthCorporationConfig().getProperty("dicomCherryStudy");
    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");
    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");
    public URLShareTest() throws IOException {
    }

    @Test(priority = 1, description = "verifying Add New URL Share")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Add New URL Share ")
    @Story("Story Name:To Add New URL Share")
    public void verifyAddNewURLShare() throws IOException, InterruptedException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnSearchButton()
                .selectStudy("1");
        Allure.step("Verify to Select Any Date ");
        Allure.step("Verify to Select All Modalities");
        Allure.step("Verify to Enter Patient Name");
        Allure.step("Verify to Search Study and Display On Worklist");
        browser.ultimaViewer.workListPage.clickOnStudyActionButton()
                .selectFromStudyActionsMenu("Share", "URL Share");
        Allure.step("Verify to Click  URL Share");
        browser.ultimaViewer.uRLSharePage.switchToShareFrame()
                .clickOnAddNewURLShareButton();
        Allure.step("Verify to Click On Add New URL Share Button");
        browser.ultimaViewer.uRLSharePage.clickOnSaveAddNewURLShareButton();
        Allure.step("Verify to Click On Save Add New URL Share Button");
        assertEquals(browser.ultimaViewer.uRLSharePage.getURLcreatedSuccessfullMessage(), "URL share has been created successfully");
        Allure.step("Verify to URL share has been created successfully");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }
}
