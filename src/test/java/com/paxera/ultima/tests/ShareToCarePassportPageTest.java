package com.paxera.ultima.tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;
import static org.testng.Assert.assertEquals;

public class ShareToCarePassportPageTest extends BaseTest {

    Common common = new Common();
    String pat_ID = setPaxerahealthCorporationConfig().getProperty("patientId");
    String dcmCherryStudy = setPaxerahealthCorporationConfig().getProperty("dicomCherryStudy");
    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");
    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");
    public ShareToCarePassportPageTest() throws IOException {
    }

    @Test(priority = 1, description = "verifying Share to CarePassport")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Share Study to CarePassport ")
    @Story("Story Name:To Share Study to CarePassport")
    public void verifyShareToCarePassportTest() throws IOException, InterruptedException, UnsupportedFlavorException {
        common.loginUltima(userName, password);
        common.importStudy(dcmCherryStudy);
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
                .selectFromStudyActionsMenu("Share", "Share to CarePassport");
        Allure.step("Verify to Click On care passport ");
        browser.ultimaViewer.shareCarePassport.switchTCarePassportFrame().OpenlistCountaryName().selectCountaryName();
        Thread.sleep(3000);
        Allure.step("Verify to select Zip Code to Countary ");
        browser.ultimaViewer.shareCarePassport.ClickOnSearchButton().ClickOnShareButton();
        Allure.step("Verify to Click On Share The study ");
        assertEquals(browser.ultimaViewer.shareCarePassport.SharedSuccessfullyMesssage(), "The study has been shared successfully");
        Allure.step("Verify to The study has been shared successfully ");
    }
}
