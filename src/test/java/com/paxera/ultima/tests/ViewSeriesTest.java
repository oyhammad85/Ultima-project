package com.paxera.ultima.tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;

public class ViewSeriesTest extends BaseTest {
    Common common = new Common();
    String pat_ID = setPaxerahealthCorporationConfig().getProperty("patientId");
    String dcmCherryStudy = setPaxerahealthCorporationConfig().getProperty("dicomCherryStudy");
    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");
    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");
    public ViewSeriesTest() throws IOException {
    }

    @Test(priority = 1, description = "verifying View Series in ZFP ")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to View Series in ZFP ")
    @Story("Story Name:To View Series In ZFP")
    public void verifyToViewSeriesInZFP() throws IOException, InterruptedException, UnsupportedFlavorException {
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
                .selectFromStudyActionsMenu(" View Series");
        Allure.step("Verify to Click On View Series Button");
        browser.ultimaViewer.viewInSeriesScreen.switchToOpenSeriesFrame()
                .clickOnViewZFPLink();
        Allure.step("Verify to Click On Open Series Screen");
        Allure.step("Verify to Click On Open Series In ZFP");
        String actualResult = common.GetCurrentURL();
        Assert.assertTrue(actualResult.contains("ZFP"));
        Allure.step("Verify to Navigate  to  View Series In ZFP");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }
}
