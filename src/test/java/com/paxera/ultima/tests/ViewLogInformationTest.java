package com.paxera.ultima.tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;

public class ViewLogInformationTest extends BaseTest {

    Common common = new Common();
    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    String pat_ID = setPaxerahealthCorporationConfig().getProperty("patientId");
    String dcmCherryStudy = setPaxerahealthCorporationConfig().getProperty("dicomCherryStudy");
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");
    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");
    public ViewLogInformationTest() throws IOException {
    }

    @Test(priority = 1, description = "verifying View Log Information")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description To View Log Information ")
    @Story("Story Name:To View Log Information")
    public void verifyViewLogInformation() throws IOException, InterruptedException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        String expectedResult = "View Log Information";
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnSearchButton()
                .selectStudy("1")
                .clickOnStudyActionButton()
                .selectFromStudyActionsMenu("Edit Study Information");
        Allure.step("Verify to Select Any Date ");
        Allure.step("Verify to Select All Modalities");
        Allure.step("Verify to Enter Patient Name");
        Allure.step("Verify to Search Study and Display On Worklist");
        browser.ultimaViewer.editStudyInformationPage.switchToEditStudyInformationScreen();
        browser.ultimaViewer.editStudyInformationPage.enterFirstName(userName)
                .clickOnSaveButton();
        browser.ultimaViewer.confirmPopup.clickOnYesButton();
        browser.ultimaViewer.workListPage.refreshScreen()
                .selectStudy("1")
                .clickOnStudyActionButton()
                .selectFromStudyActionsMenu("Logs/Audit", "View Log Information");
        Allure.step("Verify to Open Study Action Button");
        browser.ultimaViewer.viewLogInformationPage.switchToViewLogInformationFrame();
        Allure.step("Verify to Click On View Log Information");
        String actualResult = browser.ultimaViewer.viewLogInformationPage.getTitleScreen();
        Assert.assertTrue(actualResult.contains(expectedResult));
        Allure.step("Verify to Navigate  to View Log  Screen");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }
}