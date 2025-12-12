package com.paxera.ultima.tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;

public class SendHL7Test extends BaseTest {
    Common common = new Common();
    String pat_ID = setPaxerahealthCorporationConfig().getProperty("patientId");
    String dcmCherryStudy = setPaxerahealthCorporationConfig().getProperty("dicomCherryStudy");
    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");
    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");
    public SendHL7Test() throws IOException {
    }

    @Test(priority = 1, description = "verifying  Send HL7 Test")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Send HL7  ")
    @Story("Story Name:To Send HL7 ")
    public void verifySendHL7() throws IOException, InterruptedException {
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
        browser.ultimaViewer.workListPage.clickOnWorkListActionsButtton()
                .selectFromWorkListActionsMenu("Send Hl7");
        Allure.step("Verify to click On Study Action Button");
        Allure.step("Verify to click On Send HL7 Option");
        browser.ultimaViewer.sendHL7Page.switchToSendHL7Screen();
        browser.ultimaViewer.sendHL7Page.clickOnSendButton();
        Allure.step("Verify To Send ORM Message ");
        Allure.step("Verify To Confirmation Message Send AS ORM Message ");
        Common.closeConnection();
    }
}
