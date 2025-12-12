package com.paxera.ultima.tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;
import static org.testng.Assert.assertEquals;

public class DeleteStudyTest extends BaseTest {

    String confirmationMsg = setPaxerahealthCorporationConfig().getProperty("deleteMessage");
    String confirmationMsgForRecycleBin = setPaxerahealthCorporationConfig().getProperty("recycleBinMessage");
    String confirmationMsgForRestore = setPaxerahealthCorporationConfig().getProperty("restoreFromRecycleBinMessage");
    String unauthorizedMessage = setPaxerahealthCorporationConfig().getProperty("warningNotAuthorizedMessage");
    String pat_ID = setPaxerahealthCorporationConfig().getProperty("patientId");
    String dcmCherryStudy = setPaxerahealthCorporationConfig().getProperty("dicomCherryStudy");
    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    String UnauthorizedUserName = setPaxerahealthCorporationConfig().getProperty("adminWithoutRightUsername");
    String UnauthorizedPassword = setPaxerahealthCorporationConfig().getProperty("adminWithoutRightPassword");
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");
    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");
    Common common = new Common();

    public DeleteStudyTest() throws IOException {
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify user can delete study permanently")
    @Story("Delete Study - Permanent Delete")
    public void VerifyUserCanDeleteStudyPermanently() throws InterruptedException, IOException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnSearchButton();
        browser.ultimaViewer.workListPage.selectStudy("1");
        browser.ultimaViewer.workListPage.clickOnWorkListActionsButtton().
                selectFromWorkListActionsMenu("Recycle Bin", "Permanent Delete");
        assertEquals(browser.ultimaViewer.confirmPopup.getPopupMessage(), confirmationMsg);
        browser.ultimaViewer.confirmPopup.clickOnYesButton();
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify user can move study to recycle bin")
    @Story("Delete Study - Move to Recycle Bin")
    public void VerifyUserCanMoveStudyToRecycleBin() throws InterruptedException, IOException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnSearchButton();
        browser.ultimaViewer.workListPage.selectStudy("1");
        browser.ultimaViewer.workListPage.clickOnWorkListActionsButtton().
                selectFromWorkListActionsMenu("Recycle Bin", "Move to recycle bin");
        assertEquals(browser.ultimaViewer.confirmPopup.getPopupMessage(), confirmationMsgForRecycleBin);
        browser.ultimaViewer.confirmPopup.clickOnYesButton();
        browser.ultimaViewer.successPopup.clickOnOkButton();
        browser.ultimaViewer.workListPage.clickOnToolsIconButton();
        browser.ultimaViewer.toolsPage.clickOnRecycleBinButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(pat_ID));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify user can move study to recycle bin then restore it again")
    @Story("Delete Study - Restore from Recycle Bin")
    public void VerifyUserCanMoveStudyToRecycleBinThenRestoreItAgain() throws InterruptedException, IOException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnSearchButton();
        browser.ultimaViewer.workListPage.selectStudy("1");
        browser.ultimaViewer.workListPage.clickOnWorkListActionsButtton().
                selectFromWorkListActionsMenu("Recycle Bin", "Move to recycle bin");
        assertEquals(browser.ultimaViewer.confirmPopup.getPopupMessage(), confirmationMsgForRecycleBin);
        browser.ultimaViewer.confirmPopup.clickOnYesButton();
        browser.ultimaViewer.successPopup.clickOnOkButton();
        browser.ultimaViewer.workListPage.clickOnToolsIconButton();
        browser.ultimaViewer.toolsPage.clickOnRecycleBinButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(pat_ID));
        browser.ultimaViewer.recycleBinPage.selectStudy("1");
        browser.ultimaViewer.recycleBinPage.clickOnWorkListActionsButtton().
                selectFromWorkListActionsMenu("Recycle Bin", "Restore");
        assertEquals(browser.ultimaViewer.confirmPopup.getPopupMessage(), confirmationMsgForRestore);
        browser.ultimaViewer.confirmPopup.clickOnYesButton();
        browser.ultimaViewer.successPopup.clickOnOkButton();
        assertEquals(browser.ultimaViewer.recycleBinPage.getOfPageTextValue(), "0");
        browser.ultimaViewer.recycleBinPage.clickOnBackToWorkListButton();
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(pat_ID));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to verify permanent delete from recycle bin")
    @Story("Story Name: Delete Study - Permanent Delete from Recycle Bin")
    public void VerifyUserCanDeleteStudyPermanentlyFromRecycleBin() throws InterruptedException, IOException {
        Allure.step("Start DB connection");
        common.startConnection();
        Allure.step("Import study");
        common.importStudy(dcmCherryStudy);
        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnSearchButton();
        Allure.step("Select study and move to recycle bin");
        browser.ultimaViewer.workListPage.selectStudy("1");
        browser.ultimaViewer.workListPage.clickOnWorkListActionsButtton().
                selectFromWorkListActionsMenu("Recycle Bin", "Move to recycle bin");
        assertEquals(browser.ultimaViewer.confirmPopup.getPopupMessage(), confirmationMsgForRecycleBin);
        browser.ultimaViewer.confirmPopup.clickOnYesButton();
        browser.ultimaViewer.successPopup.clickOnOkButton();
        Allure.step("Open recycle bin and select study");
        browser.ultimaViewer.workListPage.clickOnToolsIconButton();
        browser.ultimaViewer.toolsPage.clickOnRecycleBinButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(pat_ID));
        browser.ultimaViewer.recycleBinPage.selectStudy("1");
        browser.ultimaViewer.recycleBinPage.clickOnWorkListActionsButtton().
                selectFromWorkListActionsMenu("Recycle Bin", "Permanent Delete");
        assertEquals(browser.ultimaViewer.confirmPopup.getPopupMessage(), confirmationMsg);
        browser.ultimaViewer.confirmPopup.clickOnYesButton();
        browser.ultimaViewer.successPopup.clickOnOkButton();
        //  browser.ultimaViewer.recycleBinPage.refreshScreen();
        Allure.step("Refresh worklist and search for deleted study");
        browser.ultimaViewer.workListPage.switchToDefaultFrame().openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnSearchButton();
        assertEquals(browser.ultimaViewer.workListPage.getOfPageTextValue(), "0");
        browser.ultimaViewer.workListPage.clickOnToolsIconButton();
        Allure.step("Verify study is permanently deleted");
        browser.ultimaViewer.toolsPage.clickOnRecycleBinButton();
        assertEquals(browser.ultimaViewer.recycleBinPage.getOfPageTextValue(), "0");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify unauthorized user cannot restore study from recycle bin")
    @Story("Delete Study - Unauthorized Restore Attempt")
    public void VerifyUnauthorizedUserCanNotRestoreStudyFromRecycleBin() throws InterruptedException, IOException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(UnauthorizedUserName, UnauthorizedPassword);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnSearchButton();
        browser.ultimaViewer.workListPage.selectStudy("1");
        browser.ultimaViewer.workListPage.clickOnWorkListActionsButtton().
                selectFromWorkListActionsMenu("Recycle Bin", "Move to recycle bin");
        assertEquals(browser.ultimaViewer.confirmPopup.getPopupMessage(), confirmationMsgForRecycleBin);
        browser.ultimaViewer.confirmPopup.clickOnYesButton();
        browser.ultimaViewer.successPopup.clickOnOkButton();
        browser.ultimaViewer.workListPage.clickOnToolsIconButton();
        browser.ultimaViewer.toolsPage.clickOnRecycleBinButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(pat_ID));
        browser.ultimaViewer.recycleBinPage.selectStudy("1");
        browser.ultimaViewer.recycleBinPage.clickOnWorkListActionsButtton().
                selectFromWorkListActionsMenu("Recycle Bin", "Restore");
        assertEquals(browser.ultimaViewer.warningPopup.getWarningPopupMessage(), unauthorizedMessage);
        browser.ultimaViewer.warningPopup.clickOnOkButton();
        Assert.assertTrue(browser.ultimaViewer.recycleBinPage.getWorkListTableText().contains(pat_ID));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }
}
