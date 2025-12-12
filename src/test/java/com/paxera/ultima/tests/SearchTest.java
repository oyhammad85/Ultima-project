package com.paxera.ultima.tests;

import com.paxera.ultima.driver.DriverManager;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;

public class SearchTest extends BaseTest {

    Common common = new Common();
    String pat_ID = setPaxerahealthCorporationConfig().getProperty("patientId");
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");
    String accNum = setPaxerahealthCorporationConfig().getProperty("accNumber");
    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");
    String accNum2 = setPaxerahealthCorporationConfig().getProperty("accNumber2");
    String patName = setPaxerahealthCorporationConfig().getProperty("patientName");
    String dcmCherryStudy = setPaxerahealthCorporationConfig().getProperty("dicomCherryStudy");
    String dcm17300Study = setPaxerahealthCorporationConfig().getProperty("dicom17300Study");
    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    String startWithPatName = setPaxerahealthCorporationConfig().getProperty("startWithPatientName");
    String containPatName = setPaxerahealthCorporationConfig().getProperty("containPatientName");
    String endWithPatName = setPaxerahealthCorporationConfig().getProperty("endWithPatientName");
    String startWithPatId = setPaxerahealthCorporationConfig().getProperty("startWithPatientId");
    String containPatId = setPaxerahealthCorporationConfig().getProperty("containPatientId");
    String endWithPatId = setPaxerahealthCorporationConfig().getProperty("endWithPatientId");
    String startWithStudy_Id2 = setPaxerahealthCorporationConfig().getProperty("startWithStudyId2");
    String containStudy_Id2 = setPaxerahealthCorporationConfig().getProperty("containStudyId2");
    String endWithStudy_Id2 = setPaxerahealthCorporationConfig().getProperty("endWithStudyId2");
    String startWithAccNum2 = setPaxerahealthCorporationConfig().getProperty("startWithAccNumber2");
    String containAccNum2 = setPaxerahealthCorporationConfig().getProperty("containAccNumber2");
    String endWithAccNum2 = setPaxerahealthCorporationConfig().getProperty("endWithAccNumber2");
    String CustomerBranch = setPaxerahealthCorporationConfig().getProperty("branchName");

    public SearchTest() throws IOException {
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Search by Patient ID")
    @Story("Story Name: Search by Patient ID")
    public void verifySearchByPatientId() throws IOException, InterruptedException {
        common.startConnection();
        Allure.step("Start DB connection");
        common.importStudy(dcmCherryStudy);
        Allure.step("Import study");
        common.loginUltima(userName, password);
        Allure.step("Login to Ultima");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnSearchButton();
        Allure.step("Search by Patient ID");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(pat_ID));
        Allure.step("Verify patient ID is present in worklist");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Search by Study ID")
    @Story("Story Name: Search by Study ID")
    public void verifySearchByStudyId() throws IOException, InterruptedException {
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
                .enterStudyId(study_ID)
                .clickOnSearchButton();
        Allure.step("Search by Study ID");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(study_ID));
        Allure.step("Verify study ID is present in worklist");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Search by Accession Number")
    @Story("Story Name: Search by Accession Number")
    public void verifySearchByAccNumber() throws IOException, InterruptedException {
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
                .enterAccNumber(accNum)
                .clickOnSearchButton();
        Allure.step("Search by Accession Number");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(accNum));
        Allure.step("Verify accession number is present in worklist");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Search by Patient Name")
    @Story("Story Name: Search by Patient Name")
    public void verifySearchByPatientName() throws IOException, InterruptedException {
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
                .enterPatientName(patName)
                .clickOnSearchButton();
        Allure.step("Search by Patient Name");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(patName));
        Allure.step("Verify patient name is present in worklist");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Search by Any Data Option")
    @Story("Story Name: Search by Any Data Option")
    public void verifySearchByAnyDataOption() throws IOException, InterruptedException {
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
                .enterPatientName(patName)
                .clickOnSearchButton();
        Allure.step("Search by Any Data Option");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(patName));
        Allure.step("Verify patient name is present in worklist");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Search by Today Option")
    @Story("Story Name: Search by Today Option")
    public void verifySearchByTodayOption() throws IOException, InterruptedException {
        Allure.step("Start DB connection");
        common.startConnection();
        Allure.step("Import study");
        common.importStudy(dcmCherryStudy);
        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);
        Allure.step("Update study date to today");
        Common.sqlQuery("update Studies Set StudyDate = GETDATE() where Study_ID = '" + study_ID + "'");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Today")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterAccNumber(accNum)
                .clickOnSearchButton();
        Allure.step("Search by Today Option");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(accNum));
        Allure.step("Verify accession number is present in worklist");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Search by Today And Yesterday Option")
    @Story("Story Name: Search by Today And Yesterday Option")
    public void verifySearchByTodayAndYesterdayOption() throws IOException, InterruptedException {
        Allure.step("Start DB connection");
        common.startConnection();
        Allure.step("Import study");
        common.importStudy(dcmCherryStudy);
        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);
        Allure.step("Update study date to today");
        Common.sqlQuery("update Studies Set StudyDate = GETDATE() where Study_ID = '" + study_ID + "'");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Today")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterAccNumber(accNum)
                .clickOnSearchButton();
        Allure.step("Search by Today Option");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(accNum));
        Allure.step("Update study date to yesterday");
        Common.sqlQuery("update Studies Set StudyDate = DATEADD(DAY, -1, GETDATE()) where Study_ID = '" + study_ID + "'");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Today And Yesterday")
                .clickOnSearchButton();
        Allure.step("Search by Today And Yesterday Option");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(accNum));
        Allure.step("Verify accession number is present in worklist");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Search by Yesterday Option")
    @Story("Story Name: Search by Yesterday Option")
    public void verifySearchByYesterdayOption() throws IOException, InterruptedException {
        Allure.step("Start DB connection");
        common.startConnection();
        Allure.step("Import study");
        common.importStudy(dcmCherryStudy);
        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);
        Allure.step("Update study date to yesterday");
        Common.sqlQuery("update Studies Set StudyDate = DATEADD(DAY, -1, GETDATE()) where Study_ID = '" + study_ID + "'");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Yesterday")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterAccNumber(accNum)
                .clickOnSearchButton();
        Allure.step("Search by Yesterday Option");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(accNum));
        Allure.step("Verify accession number is present in worklist");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Search by Last Week Option")
    @Story("Story Name: Search by Last Week Option")
    public void verifySearchByLastWeekOption() throws IOException, InterruptedException {
        Allure.step("Start DB connection");
        common.startConnection();
        Allure.step("Import study");
        common.importStudy(dcmCherryStudy);
        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);
        Allure.step("Update study date to last week");
        Common.sqlQuery("update Studies Set StudyDate = DATEADD(DAY, -7, GETDATE()) where Study_ID = '" + study_ID + "'");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Last Week")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientName(patName)
                .clickOnSearchButton();
        Allure.step("Search by Last Week Option");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(patName));
        Allure.step("Verify patient name is present in worklist");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Search by Last 3 Months Option")
    @Story("Story Name: Search by Last 3 Months Option")
    public void verifySearchByLast3MonthsOption() throws IOException, InterruptedException {
        Allure.step("Start DB connection");
        common.startConnection();
        Allure.step("Import study");
        common.importStudy(dcmCherryStudy);
        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);
        Allure.step("Update study date to last 3 months");
        Common.sqlQuery("update Studies Set StudyDate = DATEADD(DAY, -45, GETDATE()) where Study_ID = '" + study_ID + "'");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Last 3 Months")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientName(patName)
                .clickOnSearchButton();
        Allure.step("Search by Last 3 Months Option");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(patName));
        Allure.step("Verify patient name is present in worklist");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Search by Last 6 Months Option")
    @Story("Story Name: Search by Last 6 Months Option")
    public void verifySearchByLast6MonthsOption() throws IOException, InterruptedException {
        Allure.step("Start DB connection");
        common.startConnection();
        Allure.step("Import study");
        common.importStudy(dcmCherryStudy);
        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);
        Allure.step("Update study date to last 6 months");
        Common.sqlQuery("update Studies Set StudyDate = DATEADD(DAY, -120, GETDATE()) where Study_ID = '" + study_ID + "'");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Last 6 Months")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientName(patName)
                .clickOnSearchButton();
        Allure.step("Search by Last 6 Months Option");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(patName));
        Allure.step("Verify patient name is present in worklist");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Search by Last Month Option")
    @Story("Story Name: Search by Last Month Option")
    public void verifySearchByLastMonthOption() throws IOException, InterruptedException {
        Allure.step("Start DB connection");
        common.startConnection();
        Allure.step("Import study");
        common.importStudy(dcmCherryStudy);
        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);
        Allure.step("Update study date to last month");
        Common.sqlQuery("update Studies Set StudyDate = DATEADD(DAY, -20, GETDATE()) where Study_ID = '" + study_ID + "'");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Last Month")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientName(patName)
                .clickOnSearchButton();
        Allure.step("Search by Last Month Option");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(patName));
        Allure.step("Verify patient name is present in worklist");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Search by All Report Status Option")
    @Story("Story Name: Search by All Report Status Option")
    public void verifySearchByAllReportStatusOption() throws IOException, InterruptedException {
        Allure.step("Start DB connection");
        common.startConnection();
        Allure.step("Import study");
        common.importStudy(dcmCherryStudy);
        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);
        Allure.step("Update study status to Partial");
        Common.sqlQuery("update Studies Set Status_Reported = '0' where Study_ID = '" + study_ID + "'");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnReportStatusListButton()
                .clickOnAllReportStatusOption("multiselect-all")
                .enterPatientName(patName)
                .clickOnSearchButton();
        Allure.step("Search by All Report Status Option - Partial");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains("Partial"));
        Allure.step("Update study status to Completed Not Verified");
        Common.sqlQuery("update Studies Set Status_Reported = '1' where Study_ID = '" + study_ID + "'");
        browser.ultimaViewer.workListPage.clickOnSearchButton();
        Allure.step("Search by All Report Status Option - Completed Not Verified");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains("Completed Not Verified"));
        Allure.step("Update study status to Verified");
        Common.sqlQuery("update Studies Set Status_Reported = '2' where Study_ID = '" + study_ID + "'");
        browser.ultimaViewer.workListPage.clickOnSearchButton();
        Allure.step("Search by All Report Status Option - Verified");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains("Verified"));
        Allure.step("Update study status to None");
        Common.sqlQuery("update Studies Set Status_Reported = '3' where Study_ID = '" + study_ID + "'");
        browser.ultimaViewer.workListPage.clickOnSearchButton();
        Allure.step("Search by All Report Status Option - None");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains("None"));
        Allure.step("Verify all report statuses are present in worklist");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    public void verifySearchByPartialReportStatusOption() throws IOException, InterruptedException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        Common.sqlQuery("update Studies Set Status_Reported = '0' where Study_ID = '" + study_ID + "'");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnReportStatusListButton()
                .clickOnAllReportStatusOption("0")
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains("Partial"));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    public void verifySearchByCompletedButNotVerifiedReportStatusOption() throws IOException, InterruptedException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        Common.sqlQuery("update Studies Set Status_Reported = '1' where Study_ID = '" + study_ID + "'");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnReportStatusListButton()
                .clickOnAllReportStatusOption("1")
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains("Completed Not Verified"));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    public void verifySearchByVerifiedReportStatusOption() throws IOException, InterruptedException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        Common.sqlQuery("update Studies Set Status_Reported = '2' where Study_ID = '" + study_ID + "'");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnReportStatusListButton()
                .clickOnAllReportStatusOption("2")
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains("Verified"));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    public void verifySearchByNoneReportStatusOption() throws IOException, InterruptedException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        Common.sqlQuery("update Studies Set Status_Reported = '3' where Study_ID = '" + study_ID + "'");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnReportStatusListButton()
                .clickOnAllReportStatusOption("3")
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains("None"));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    public void verifySearchByAllModalities() throws IOException, InterruptedException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains("CT"));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    public void verifySearchBySpecificModality() throws IOException, InterruptedException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectSpecificModality("CT")
                .enterPatientId(pat_ID)
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains("CT"));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    public void verifySearchBySpecificBranch() throws IOException, InterruptedException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectSpecificModality("CT")
                .enterPatientId(pat_ID)
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(CustomerBranch));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    public void verifySearchByAllBranches() throws IOException, InterruptedException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectSpecificModality("CT")
                .enterPatientId(pat_ID)
                .clickOnAllBranchesDropDownList()
                .selectAllBranches("multiselect-all")
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(CustomerBranch));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    public void verifyResetSearch() throws IOException, InterruptedException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        String expectedResult = "Please provide one or more search criteria";
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectSpecificModality("CT")
                .enterPatientId(pat_ID)
                .clickOnAllBranchesDropDownList()
                .selectAllBranches("multiselect-all")
                .clickOnSearchButton()
                .clickOnResetButton()
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getSearchCriteriaContentMessageText().contains(expectedResult));
        browser.ultimaViewer.confirmPopup.clickOnYesButton();
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    public void verifyWildCardSearchByStartWithPatientName() throws IOException, InterruptedException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientName(startWithPatName)
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(patName));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    public void verifyWildCardSearchByContainPatientName() throws IOException, InterruptedException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientName(containPatName)
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(patName));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    public void verifyWildCardSearchByEndWithPatientName() throws IOException, InterruptedException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientName(endWithPatName)
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(patName));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    public void verifyWildCardSearchByStartWithPatientId() throws IOException, InterruptedException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(startWithPatId)
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(pat_ID));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    public void verifyWildCardSearchByContainPatientId() throws IOException, InterruptedException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(containPatId)
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(pat_ID));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    public void verifyWildCardSearchByEndWithPatientId() throws IOException, InterruptedException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(endWithPatId)
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(pat_ID));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    public void verifyWildCardSearchByStartWithStudyID() throws IOException, InterruptedException {
        common.startConnection();
        common.importStudy(dcm17300Study);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterStudyId(startWithStudy_Id2)
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(study_ID2));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID2 + "'");
        Common.closeConnection();
    }

    @Test
    public void verifyWildCardSearchByContainStudyID() throws IOException, InterruptedException {
        common.startConnection();
        common.importStudy(dcm17300Study);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterStudyId(containStudy_Id2)
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(study_ID2));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID2 + "'");
        Common.closeConnection();
    }

    @Test
    public void verifyWildCardSearchByEndWithStudyID() throws IOException, InterruptedException {
        common.startConnection();
        common.importStudy(dcm17300Study);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterStudyId(endWithStudy_Id2)
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(study_ID2));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID2 + "'");
        Common.closeConnection();
    }

    @Test
    public void verifyWildCardSearchByStartWithAccNumber() throws IOException, InterruptedException {
        common.startConnection();
        common.importStudy(dcm17300Study);
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterAccNumber(startWithAccNum2)
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(accNum2));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID2 + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Search by Contain Accession Number")
    @Story("Story Name: Search by Contain Accession Number")
    public void verifyWildCardSearchByContainAccNumber() throws IOException, InterruptedException {
        Allure.step("Start DB connection");
        common.startConnection();
        Allure.step("Import study");
        common.importStudy(dcm17300Study);
        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterAccNumber(containAccNum2)
                .clickOnSearchButton();
        Allure.step("Search by contain accession number");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(accNum2));
        Allure.step("Verify accession number is present in worklist");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID2 + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Search by End With Accession Number")
    @Story("Story Name: Search by End With Accession Number")
    public void verifyWildCardSearchByEndWithAccNumber() throws IOException, InterruptedException {
        Allure.step("Start DB connection");
        common.startConnection();
        Allure.step("Import study");
        common.importStudy(dcm17300Study);
        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterAccNumber(endWithAccNum2)
                .clickOnSearchButton();
        Allure.step("Search by end with accession number");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(accNum2));
        Allure.step("Verify accession number is present in worklist");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID2 + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Search by Custom Date")
    @Story("Story Name: Search by Custom Date")
    public void verifySearchViaCustomDate() throws IOException, InterruptedException {
        Allure.step("Start DB connection");
        common.startConnection();
        Allure.step("Import study");
        common.importStudy(dcmCherryStudy);
        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);
        Allure.step("Update study date to specific date");
        Common.sqlQuery("update Studies Set StudyDate = '2025-04-01' where Study_ID = '" + study_ID + "'");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Custom")
                .selectFromDate("April", "2025", "1")
                .selectToDate("June", "2025", "24")
                .clickOnApplyButton()
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnSearchButton();
        Allure.step("Search by Custom Date Range");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(pat_ID));
        Allure.step("Verify patient ID is present in worklist");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Search by Tag Name")
    @Story("Story Name: Search by Tag Name")
    public void verifySearchByTagName() throws IOException, InterruptedException {
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
                .enterStudyId(study_ID)
                .clickOnSearchButton();
        browser.ultimaViewer.workListPage.selectStudy("1");
        browser.ultimaViewer.workListPage.clickOnWorkListActionsButtton().selectFromWorkListActionsMenu("Tags", " Tag ");
        browser.ultimaViewer.errorPopup.clickOnOkButton();
        browser.ultimaViewer.workListPage.selectTagsDropdown()
                .selectFirstTagOption();
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnSearchButton();
        Allure.step("Search by Tag Name");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(study_ID));
        Allure.step("Verify tagged study is present in the worklist");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }
}
