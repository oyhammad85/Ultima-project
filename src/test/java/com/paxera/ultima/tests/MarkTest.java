package com.paxera.ultima.tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;
import static org.testng.Assert.assertEquals;

@Epic("Study Management")
@Feature("Study Status Management")
public class MarkTest extends BaseTest {
    String pat_ID = setPaxerahealthCorporationConfig().getProperty("patientId");
    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    String dcmCherryStudy = setPaxerahealthCorporationConfig().getProperty("dicomCherryStudy");
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");
    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");
    Common common = new Common();

    public MarkTest() throws IOException {
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify marking a study as viewed and checking its status")
    @Story("Mark Study as Viewed")
    public void markStudyAsViewed() throws InterruptedException, IOException {
        Allure.step("Start connection and import study");
        common.startConnection();
        common.importStudy(dcmCherryStudy);

        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);

        Allure.step("Search for the study");
        browser.ultimaViewer.workListPage.enterPatientId(pat_ID)
                .openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnSearchButton();

        Allure.step("Mark study as viewed");
        browser.ultimaViewer.workListPage.selectStudy("1");
        browser.ultimaViewer.workListPage.clickOnWorkListActionsButtton().selectFromWorkListActionsMenu("Mark As", "Mark As Viewed");

        Allure.step("Verify study status is marked as viewed");
        String actualResult = browser.ultimaViewer.workListPage.getStudyViewedStatus();
        Assert.assertTrue(actualResult.contains("Viewed"));

        Allure.step("Clean up test data");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify marking a study as not viewed and checking its status")
    @Story("Mark Study as Not Viewed")
    public void markStudyAsNotViewed() throws InterruptedException, IOException {
        Allure.step("Start connection and import study");
        common.startConnection();
        common.importStudy(dcmCherryStudy);

        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);

        Allure.step("Search for the study");
        browser.ultimaViewer.workListPage.enterPatientId(pat_ID)
                .openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnSearchButton();

        Allure.step("Mark study as not viewed");
        browser.ultimaViewer.workListPage.selectStudy("1");
        browser.ultimaViewer.workListPage.clickOnWorkListActionsButtton().selectFromWorkListActionsMenu("Mark As", "Mark As Not Viewed");

        Allure.step("Verify study status is marked as not viewed");
        String actualResult = browser.ultimaViewer.workListPage.getStudyViewedStatus();
        Assert.assertTrue(actualResult.contains("NotViewed"));

        Allure.step("Clean up test data");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify marking a study as VIP and checking its status")
    @Story("Mark Study as VIP")
    public void markStudyAsVIP() throws InterruptedException, IOException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.enterPatientId(pat_ID)
                .openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnSearchButton();
        browser.ultimaViewer.workListPage.selectStudy("1");
        browser.ultimaViewer.workListPage.clickOnWorkListActionsButtton().selectFromWorkListActionsMenu("Mark As", "Mark As VIP");
        String actualResult = browser.ultimaViewer.workListPage.getStudyVIPStatus();
        Assert.assertTrue(actualResult.contains("VIP"));
        browser.ultimaViewer.workListPage.clickOnSearchButton()
                .selectStudy("1");
        browser.ultimaViewer.workListPage.clickOnWorkListActionsButtton().selectFromWorkListActionsMenu("Recycle Bin", "Permanent Delete");
        browser.ultimaViewer.workListPage.enterVIPComment("test");
        browser.ultimaViewer.confirmPopup.clickOnYesButton();
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify marking a study as not VIP and checking its status")
    @Story("Mark Study as Not VIP")
    public void markStudyAsNotVIP() throws InterruptedException, IOException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.enterPatientId(pat_ID)
                .openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnSearchButton();
        browser.ultimaViewer.workListPage.selectStudy("1");
        browser.ultimaViewer.workListPage.clickOnWorkListActionsButtton().selectFromWorkListActionsMenu("Mark As", "Mark As Not VIP");
        String actualResult = browser.ultimaViewer.workListPage.getStudyNotVIPStatus();
        Assert.assertTrue(actualResult.contains("gridcell"));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify marking a study as dictated and checking the dictator's name")
    @Story("Mark Study as Dictated")
    public void markStudyAsDictated() throws InterruptedException, IOException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.enterPatientId(pat_ID)
                .openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnSearchButton();
        browser.ultimaViewer.workListPage.selectStudy("1");
        browser.ultimaViewer.workListPage.clickOnWorkListActionsButtton().selectFromWorkListActionsMenu("Mark As", "Mark As Dictated");
        browser.ultimaViewer.workListPage.clickOnSearchButton()
                .selectStudy("1");
        assertEquals(browser.ultimaViewer.workListPage.getStudyDictated(), userName);

        Allure.step("Clean up test data");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify marking a study as not dictated and checking its status")
    @Story("Mark Study as Not Dictated")
    public void markStudyAsNotDictated() throws InterruptedException, IOException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.importStudy(dcmCherryStudy);
        browser.ultimaViewer.workListPage.enterPatientId(pat_ID)
                .openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnSearchButton();
        browser.ultimaViewer.workListPage.selectStudy("1");
        browser.ultimaViewer.workListPage.clickOnWorkListActionsButtton().selectFromWorkListActionsMenu("Mark As", "Mark As Not Dictated");
        String actualResult = browser.ultimaViewer.workListPage.getStudyDictated();
        Assert.assertTrue(actualResult.contains(""));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }
}
