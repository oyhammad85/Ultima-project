package com.paxera.ultima.tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;
import static org.testng.Assert.assertEquals;

@Epic("Study Management")
@Feature("Export Studies")
public class ExportStudiesTest extends BaseTest {

    String exportSingleSuccessMsg = setPaxerahealthCorporationConfig().getProperty("exportSingleSuccessMessage");
    String exportMultipleSuccessMsg = setPaxerahealthCorporationConfig().getProperty("exportMultipleSuccessMessage");
    String exportDisplayedSuccessMsg = setPaxerahealthCorporationConfig().getProperty("exportDisplayedSuccessMessage");
    String pat_ID = setPaxerahealthCorporationConfig().getProperty("patientId");
    String dcmCherryStudy = setPaxerahealthCorporationConfig().getProperty("dicomCherryStudy");
    String dcm17300Study = setPaxerahealthCorporationConfig().getProperty("dicom17300Study");
    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");
    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");
    Common common = new Common();

    public ExportStudiesTest() throws IOException {
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify export functionality for a single selected study to Excel")
    @Story("Export Selected Studies to Excel")
    public void exportSelectedStudies() throws InterruptedException, IOException {
        Allure.step("Login and import study");
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        Allure.step("Search for study");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnSearchButton();
        Allure.step("Select and export study");
        browser.ultimaViewer.workListPage.selectStudy("1");
        browser.ultimaViewer.workListPage.clickOnWorkListActionsButtton().selectFromWorkListActionsMenu("Export", "Export Selected To Excel");
        Allure.step("Verify export success message");
        assertEquals(browser.ultimaViewer.successPopup.getSuccessPopupMessage(), exportSingleSuccessMsg);
        browser.ultimaViewer.successPopup.clickOnOkButton();
        Allure.step("Clean up test data");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify export functionality for multiple studies to Excel")
    @Story("Export Multiple Studies to Excel")
    public void exportMultipleStudies() throws InterruptedException, IOException {
        Allure.step("Login to Ultima");
        common.importStudy(dcmCherryStudy);
        common.importStudy(dcm17300Study);
        common.startConnection();
        common.loginUltima(userName, password);
        Common.sqlQuery("update Studies Set StudyDate = GETDATE() where Study_ID = '" + study_ID + "'");
        Common.sqlQuery("update Studies Set StudyDate = GETDATE() where Study_ID = '" + study_ID2 + "'");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Today")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnSearchButton();
        browser.ultimaViewer.workListPage.selectStudy("1")
                .selectStudy("2");
        browser.ultimaViewer.workListPage.clickOnWorkListActionsButtton().selectFromWorkListActionsMenu("Export", "Export Selected To Excel");
        assertEquals(browser.ultimaViewer.successPopup.getSuccessPopupMessage(), exportMultipleSuccessMsg);
        browser.ultimaViewer.successPopup.clickOnOkButton();
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID2 + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify export functionality for displayed studies to Excel")
    @Story("Export Displayed Studies to Excel")
    public void exportDisplayedStudies() throws InterruptedException, IOException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnSearchButton();
        browser.ultimaViewer.workListPage.clickOnWorkListActionsButtton().selectFromWorkListActionsMenu("Export", "Export Displayed To Excel");
        assertEquals(browser.ultimaViewer.successPopup.getSuccessPopupMessage(), exportDisplayedSuccessMsg);
        browser.ultimaViewer.successPopup.clickOnOkButton();
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify export functionality for all studies to Excel")
    @Story("Export All Studies to Excel")
    public void exportAllStudies() throws InterruptedException, IOException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnSearchButton();
        browser.ultimaViewer.workListPage.clickOnWorkListActionsButtton().selectFromWorkListActionsMenu("Export", "Export All Studies To Excel");
        assertEquals(browser.ultimaViewer.successPopup.getSuccessPopupMessage(), exportDisplayedSuccessMsg);
        browser.ultimaViewer.successPopup.clickOnOkButton();
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

}
