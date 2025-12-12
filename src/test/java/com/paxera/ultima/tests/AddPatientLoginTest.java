package com.paxera.ultima.tests;

import com.paxera.ultima.utils.RetryAnalyzer;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;
import static org.testng.Assert.assertEquals;

public class AddPatientLoginTest extends BaseTest {

    Common common = new Common();
    String pat_ID = setPaxerahealthCorporationConfig().getProperty("patientId");
    String dcmCherryStudy = setPaxerahealthCorporationConfig().getProperty("dicomCherryStudy");
    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");

    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");
    String user="CHERY^SAMMY";
    public AddPatientLoginTest() throws IOException {
    }

    @Test(priority = 1, description = "verifying  Add Patient Login  ",retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Add Patient Login  ")
    @Story("Story Name:To  Add Patient Login ")
    public void verifyToAddPatientLogin() throws IOException, InterruptedException, UnsupportedFlavorException {
        //common.startConnection();
       // common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnSearchButton()
                .selectStudy("1");
        Allure.step("Verify to SeAny Date ");
        Allure.step("Verify to Select All Modalities");
        Allure.step("Verify to Enter Patient Name");
        Allure.step("Verify to Search Study and Display On Worklist");
        browser.ultimaViewer.workListPage.clickOnStudyActionButton()
                .selectFromStudyActionsMenu(" Add Patient Login ");
        Allure.step("Verify to Click On Add Patient Login  Button");
        browser.ultimaViewer.addPatientloginScreen.switchToEditPatientLoginFrame().clickOnSaveLoginInformation();
        Allure.step("Verify to switch To Edit Patient Login Screen");
        assertEquals(browser.ultimaViewer.addPatientloginScreen.getUpdateSuccessfullMessage(), "Update was done successfully.");
        Allure.step("Verify to Update was done successfully");
        common.sqlQuery("Delete From users where u_name = '" + user + "'");
        common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        common.closeConnection();

    }
}
