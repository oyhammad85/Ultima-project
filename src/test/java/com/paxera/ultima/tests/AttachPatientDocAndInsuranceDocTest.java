package com.paxera.ultima.tests;

import com.paxera.ultima.utils.RetryAnalyzer;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;

public class AttachPatientDocAndInsuranceDocTest extends BaseTest {
    String pat_ID = setPaxerahealthCorporationConfig().getProperty("patientId");
    String dcmCherryStudy = setPaxerahealthCorporationConfig().getProperty("dicomCherryStudy");
    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    Common common = new Common();
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");
    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");
    String imageName= "unnamed";
    String projectPath = System.getProperty("user.dir");
    public AttachPatientDocAndInsuranceDocTest() throws IOException {
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void addPatientDocumentAndVerifyDocUploadedSuccessfully() throws InterruptedException, AWTException, IOException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.enterPatientId(pat_ID)
                .openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnSearchButton()
                .selectStudy("1")
                .clickOnStudyActionButton()
                .selectFromStudyActionsMenu("Attach Documents", "Attach Patient Document ");
        browser.ultimaViewer.attachPatientDocPage.switchToAttachPatientDocumentScreen();
        browser.ultimaViewer.attachPatientDocPage.clickOnUploadButton(projectPath+"\\src\\test\\java\\com\\paxera\\ultima\\Files\\unnamed.png");
        browser.ultimaViewer.attachDocumentPopup.clickOnSaveButton();
        browser.ultimaViewer.attachPatientDocPage.clickOnCloseButton();
        browser.ultimaViewer.attachPatientDocPage.switchToDefaultScreen();
        browser.ultimaViewer.workListPage.clickOnStudyActionButton()
                .selectFromStudyActionsMenu("Attach Documents", "Attach Patient Document ");
        browser.ultimaViewer.attachPatientDocPage.switchToAttachPatientDocumentScreen();
        Assert.assertTrue(browser.ultimaViewer.attachPatientDocPage.getImageName().contains(imageName));
        Allure.step("Clean up test data");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }
}