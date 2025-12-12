package com.paxera.ultima.tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;

@Epic("Study Information Management")
@Feature("Edit Study Information")
public class EditStudyInformationTest extends BaseTest {

    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    String pat_ID = setPaxerahealthCorporationConfig().getProperty("patientId");
    String dcmCherryStudy = setPaxerahealthCorporationConfig().getProperty("dicomCherryStudy");
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");
    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");
    Common common = new Common();

    public EditStudyInformationTest() throws IOException {
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Edit the first name of the patient and verify the change is reflected in the worklist")
    @Story("Edit Patient First Name")
    public void editFirstName() throws InterruptedException, IOException {
        Allure.step("Start connection and import study");
        common.startConnection();
        common.importStudy(dcmCherryStudy);

        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);

        Allure.step("Search for patient and navigate to Edit Study Information");
        browser.ultimaViewer.workListPage.enterPatientId(pat_ID)
                .openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnSearchButton()
                .selectStudy("1")
                .clickOnStudyActionButton()
                .selectFromStudyActionsMenu("Edit Study Information");

        Allure.step("Edit first name and save changes");
        browser.ultimaViewer.editStudyInformationPage.switchToEditStudyInformationScreen();
        browser.ultimaViewer.editStudyInformationPage.enterFirstName(userName)
                .clickOnSaveButton();
        browser.ultimaViewer.confirmPopup.clickOnYesButton();

        Allure.step("Verify changes in worklist");
        browser.ultimaViewer.editStudyInformationPage.switchToWorkList();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(userName));

        Allure.step("Clean up test data");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }
}