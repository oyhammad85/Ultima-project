package com.paxera.ultima.tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;
import static org.testng.Assert.assertEquals;

@Epic("Study Management")
@Feature("Worklist Filters")
public class WorklistTest extends BaseTest {

    String filter1 = setPaxerahealthCorporationConfig().getProperty("filterName1");
    String filter2 = setPaxerahealthCorporationConfig().getProperty("filterName2");
    String successMsg = setPaxerahealthCorporationConfig().getProperty("successMessage");
    String ConfirmationMsg = setPaxerahealthCorporationConfig().getProperty("confirmationMessage");
    String errorMsg = setPaxerahealthCorporationConfig().getProperty("errorMessage");
    String pat_ID = setPaxerahealthCorporationConfig().getProperty("patientId");
    String dcmCherryStudy = setPaxerahealthCorporationConfig().getProperty("dicomCherryStudy");
    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");
    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");
    Common common = new Common();

    public WorklistTest() throws IOException {
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify adding and deleting filters in the worklist")
    @Story("Filter Management")
    public void addFilterAndDeleteFilter() throws InterruptedException, IOException {
        Allure.step("Start connection and import study");
        common.startConnection();
        common.importStudy(dcmCherryStudy);

        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);

        Allure.step("Add new filter");
        browser.ultimaViewer.workListPage.enterPatientId(pat_ID);
        browser.ultimaViewer.workListPage.clickOnFilterButton();
        browser.ultimaViewer.filtersPopup.enterFilterName(filter1)
                .clickOnAddFilterButton();
        assertEquals(browser.ultimaViewer.successPopup.getSuccessPopupMessage(), successMsg);
        browser.ultimaViewer.successPopup.clickOnOkButton();
        assertEquals(browser.ultimaViewer.filtersPopup.getFilterName(filter1), filter1);

        Allure.step("Delete the filter");
        browser.ultimaViewer.workListPage.refreshScreen()
                .clickOnFilterButton();
        browser.ultimaViewer.filtersPopup.clickOnDeleteFilterIcon(filter1);
        assertEquals(browser.ultimaViewer.confirmPopup.getPopupMessage(), ConfirmationMsg);
        browser.ultimaViewer.confirmPopup.clickOnYesButton();
        browser.ultimaViewer.successPopup.clickOnOkButton();

        Allure.step("Clean up test data");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();

    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify marking and deleting a filter in the worklist")
    @Story("Filter Management")
    public void markFilterAndDeleteFilter() throws InterruptedException, IOException {
        Allure.step("Start connection and setup");
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);

        Allure.step("Create new filter");
        browser.ultimaViewer.workListPage.enterPatientId(pat_ID);
        browser.ultimaViewer.workListPage.clickOnFilterButton();
        browser.ultimaViewer.filtersPopup.enterFilterName(filter1)
                .clickOnAddFilterButton();
        browser.ultimaViewer.successPopup.clickOnOkButton();

        Allure.step("Mark the filter");
        browser.ultimaViewer.workListPage.refreshScreen()
                .clickOnFilterButton();
        browser.ultimaViewer.filtersPopup.clickOnMarkFilterIcon(filter1);
        assertEquals(browser.ultimaViewer.filtersPopup.getFilterListItemsName(filter1), filter1);

        Allure.step("Delete the marked filter");
        browser.ultimaViewer.workListPage.refreshScreen();
        browser.ultimaViewer.workListPage.clickOnFilterButton();
        browser.ultimaViewer.filtersPopup.clickOnDeleteFilterIcon(filter1);
        assertEquals(browser.ultimaViewer.confirmPopup.getPopupMessage(), ConfirmationMsg);
        browser.ultimaViewer.confirmPopup.clickOnYesButton();
        browser.ultimaViewer.successPopup.clickOnOkButton();

        Allure.step("Clean up test data");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that duplicate filter names are not allowed")
    @Story("Filter Validation")
    public void canNotAddFilterTwiceWithSameName() throws InterruptedException, IOException {
        Allure.step("Start connection and setup");
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);

        Allure.step("Create first filter");
        browser.ultimaViewer.workListPage.clickOnFilterButton();
        browser.ultimaViewer.workListPage.enterPatientId(pat_ID);
        browser.ultimaViewer.filtersPopup.enterFilterName(filter1)
                .clickOnAddFilterButton();
        browser.ultimaViewer.successPopup.clickOnOkButton();

        Allure.step("Attempt to create duplicate filter");
        browser.ultimaViewer.workListPage.refreshScreen()
                .clickOnFilterButton();
        browser.ultimaViewer.workListPage.enterPatientId(pat_ID);
        browser.ultimaViewer.filtersPopup.enterFilterName(filter1)
                .clickOnAddFilterButton();
        assertEquals(browser.ultimaViewer.errorPopup.getErrorPopupMessage(), errorMsg);
        browser.ultimaViewer.errorPopup.clickOnOkButton();

        Allure.step("Create filter with different name");
        browser.ultimaViewer.workListPage.refreshScreen()
                .clickOnFilterButton();
        browser.ultimaViewer.workListPage.enterPatientId(pat_ID);
        browser.ultimaViewer.filtersPopup.enterFilterName(filter2)
                .clickOnAddFilterButton();
        browser.ultimaViewer.confirmPopup.clickOnYesButton();
        assertEquals(browser.ultimaViewer.filtersPopup.getFilterName(filter2), filter2);

        Allure.step("Clean up by deleting both filters");
        browser.ultimaViewer.workListPage.refreshScreen()
                .clickOnFilterButton();
        browser.ultimaViewer.filtersPopup.clickOnDeleteFilterIcon(filter1);
        assertEquals(browser.ultimaViewer.confirmPopup.getPopupMessage(), ConfirmationMsg);
        browser.ultimaViewer.confirmPopup.clickOnYesButton();
        browser.ultimaViewer.successPopup.clickOnOkButton();
        browser.ultimaViewer.workListPage.refreshScreen()
                .clickOnFilterButton();
        browser.ultimaViewer.filtersPopup.clickOnDeleteFilterIcon(filter2);
        assertEquals(browser.ultimaViewer.confirmPopup.getPopupMessage(), ConfirmationMsg);
        browser.ultimaViewer.confirmPopup.clickOnYesButton();
        browser.ultimaViewer.successPopup.clickOnOkButton();

        Allure.step("Clean up test data");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify applying a filter and checking filtered results")
    @Story("Filter Application")
    public void applyFilter() throws InterruptedException, IOException {
        Allure.step("Start connection and setup");
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);

        Allure.step("Search for study");
        browser.ultimaViewer.workListPage.enterPatientId(pat_ID)
                .openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnSearchButton();

        Allure.step("Create and apply filter");
        browser.ultimaViewer.workListPage.clickOnFilterButton();
        browser.ultimaViewer.filtersPopup.enterFilterName(filter1)
                .clickOnAddFilterButton();
        browser.ultimaViewer.successPopup.clickOnOkButton();
        browser.ultimaViewer.workListPage.refreshScreen()
                .clickOnFilterButton();
        browser.ultimaViewer.filtersPopup.clickOnAddedFilter(filter1);

        Allure.step("Verify filtered results");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(pat_ID));

        Allure.step("Clean up filter and test data");
        browser.ultimaViewer.workListPage.refreshScreen()
                .clickOnFilterButton();
        browser.ultimaViewer.filtersPopup.clickOnDeleteFilterIcon(filter1);
        browser.ultimaViewer.confirmPopup.clickOnYesButton();
        browser.ultimaViewer.successPopup.clickOnOkButton();
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

}