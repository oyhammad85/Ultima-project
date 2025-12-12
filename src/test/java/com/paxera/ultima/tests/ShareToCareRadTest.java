package com.paxera.ultima.tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;

public class ShareToCareRadTest extends BaseTest {
    Common common = new Common();
    String userName = setPaxerahealthCorporationConfig().getProperty("UserNameCareRad");
    String password = setPaxerahealthCorporationConfig().getProperty("PassWordCareRad");
    String dcmCherryStudy = setPaxerahealthCorporationConfig().getProperty("dicomCherryStudy");
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");
    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");
    public ShareToCareRadTest() throws IOException {
    }

    @Test(priority = 1, description = "verifying Share to CareRad")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Share Study to CareRad ")
    @Story("Story Name:To Share Study to CareRad")
    public void verfiyShareToCareRadTest() throws InterruptedException, IOException, UnsupportedFlavorException {
        common.loginUltima(userName, password);
        common.importStudy(dcmCherryStudy);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnSearchButton()
                .selectStudy("1");
        browser.ultimaViewer.workListPage.clickOnStudyActionButton()
                .selectFromStudyActionsMenu("Share", "Share to CareRad");
        Allure.step("Verify to Click On ShareCareRad ");
        browser.ultimaViewer.shareCareRad.switchTCareRadFrame();
        Allure.step("Verify to Open ShareCareRad Screen ");
        Thread.sleep(3000);
        browser.ultimaViewer.shareCareRad.enterUserName(userName).enterPassword(password).clickOnLoginButton();
        Allure.step("Enter The user Credential to Login sharecarerad ");
        browser.ultimaViewer.shareCareRad.clickOnNextButton().openListCountaryName().selectCountaryName();
        Allure.step("Verify to select Zip Code to Countary  ");
        Thread.sleep(3000);
        browser.ultimaViewer.shareCareRad.clickOnSearchButton().clickOnShareButton();
        Allure.step("Verify to Click On Share The study  to careRad ");
    }
}