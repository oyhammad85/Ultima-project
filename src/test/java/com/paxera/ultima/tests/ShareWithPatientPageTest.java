package com.paxera.ultima.tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;

public class ShareWithPatientPageTest extends BaseTest {
    Common common = new Common();
    String dcmCherryStudy = setPaxerahealthCorporationConfig().getProperty("dicomCherryStudy");
    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");
    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");
    public ShareWithPatientPageTest() throws IOException {
    }

    @Test(priority = 1, description = "verifying Share to  ShareWithPatientPage")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to  ShareWithPatientPage ")
    @Story("Story Name:To Share Study  ShareWithPatientt")
    public void verifyCopyLinkShareWithPatient() throws IOException, InterruptedException, UnsupportedFlavorException {
        common.loginUltima(userName, password);
        common.importStudy(dcmCherryStudy);
        String expectedResult = "tinyurl";
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnSearchButton()
                .selectStudy("1");
        Allure.step("Verify to Select Any Date ");
        Allure.step("Verify to Select All Modalities");
        Allure.step("Verify to Enter Patient Name");
        Allure.step("Verify to Search Study and Display On Worklist");
        browser.ultimaViewer.workListPage.clickOnStudyActionButton()
                .selectFromWorkListActionsMenu("Share", "Share With Patient");
        Allure.step("Verify to Click On Share With Patient ");
        browser.ultimaViewer.shareWithPatientPage.switchToShareWithPatientFrame()
                .clickOnACopyURLLink().switchANewTabToShareWithPatientFrame();
        Allure.step("Verify to Switch To New Tab With Patient Frame ");
        String actualResult = browser.ultimaViewer.shareWithPatientPage.getContainURL();
        Assert.assertTrue(actualResult.contains(expectedResult));
        Allure.step("Verify to Take Copy Link Share With Patient  ");
    }
}
