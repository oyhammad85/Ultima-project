package com.paxera.ultima.tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;

public class ViewMedicalRecordsTest extends BaseTest {
    Common common = new Common();
    String pat_ID = setPaxerahealthCorporationConfig().getProperty("patientId");
    String dcmCherryStudy = setPaxerahealthCorporationConfig().getProperty("dicomCherryStudy");
    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");
    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");
    public ViewMedicalRecordsTest() throws IOException {
    }

    @Test(priority = 1, description = "verifying View Medical Records")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to View Medical Records")
    @Story("Story Name:To View Medical Records")
    public void verifyToViewMedicalRecords() throws IOException, InterruptedException, UnsupportedFlavorException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        common.sqlQuery("Update Patients Set Pat_sex ='M' where Pat_Name = 'SAMMY CHERY'");
        common.sqlQuery("Update Patients Set Pat_phone_cell = '01220000000' Where Pat_Name = 'SAMMY CHERY'");
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
                .selectFromStudyActionsMenu("Share", "View Medical Records");
        Allure.step("Verify to Click On ViewMRecord");
        String actualResult = common.GetCurrentURL();
        Assert.assertTrue(actualResult.contains("MRecords"));
        Allure.step("Verify to Navigate  to  ViewMRecord page");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }
}

