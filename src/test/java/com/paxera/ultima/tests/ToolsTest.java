package com.paxera.ultima.tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;
import static org.testng.Assert.assertEquals;

public class ToolsTest extends BaseTest {


    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String refUserName = setPaxerahealthCorporationConfig().getProperty("refUsername");
    String techUserName = setPaxerahealthCorporationConfig().getProperty("technicianUsername");
    String radUserName = setPaxerahealthCorporationConfig().getProperty("radiologistUsername");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    String patName = setPaxerahealthCorporationConfig().getProperty("patientName");
    String dcmCherryStudy = setPaxerahealthCorporationConfig().getProperty("dicomCherryStudy");
    String breakTheGlassConfirmationMsg = setPaxerahealthCorporationConfig().getProperty("breakTheGlassConfirmationMessage");
    String unauthorizedMessage = setPaxerahealthCorporationConfig().getProperty("warningNotAuthorizedMessage");
    String unauthorizedReportMessage = setPaxerahealthCorporationConfig().getProperty("warningForNotAuthorizedToOpenReportType");
    String url = setPaxerahealthCorporationConfig().getProperty("URL");
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");
    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");
    Common common = new Common();

    public ToolsTest() throws IOException {
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Access Administration Site from Tools Menu")
    @Story("Story Name: Access Administration Site from Tools Menu")
    public void verifyUserCanAccessAdministrationSiteFromToolsMenu() throws InterruptedException, IOException {

        common.loginUltima(userName, password);
        Allure.step("Login to Ultima");
        browser.ultimaViewer.workListPage.clickOnToolsIconButton();
        Allure.step("Click on Tools icon");
        browser.ultimaViewer.toolsPage.clickOnAdministrationButton();
        Allure.step("Click on Administration button");
        String actualResult = common.GetCurrentURL();
        Allure.step("Get current URL");
        assertEquals(actualResult, url + "PXAdmin/Login.aspx");
        Allure.step("Verify Administration site URL");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Access RIS from Tools Menu")
    @Story("Story Name: Access RIS from Tools Menu")
    public void verifyUserCanAccessRisFromToolsMenu() throws InterruptedException, IOException {

        common.loginUltima(userName, password);
        Allure.step("Login to Ultima");
        browser.ultimaViewer.workListPage.clickOnToolsIconButton();
        Allure.step("Click on Tools icon");
        browser.ultimaViewer.toolsPage.clickOnRisButton();
        Allure.step("Click on RIS button");
        String actualResult = common.GetCurrentURL();
        Allure.step("Get current URL");
        assertEquals(actualResult, url + "Ris");
        Allure.step("Verify RIS site URL");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Access Archive Manager from Tools Menu")
    @Story("Story Name: Access Archive Manager from Tools Menu")
    public void verifyUserCanAccessArchiveManagerFromToolsMenu() throws InterruptedException, IOException {

        common.loginUltima(userName, password);
        Allure.step("Login to Ultima");
        browser.ultimaViewer.workListPage.clickOnToolsIconButton();
        Allure.step("Click on Tools icon");
        browser.ultimaViewer.toolsPage.clickOnArchiveManagerButton();
        Allure.step("Click on Archive Manager button");
        String actualResult = common.GetCurrentURL();
        Allure.step("Get current URL");
        assertEquals(actualResult, url + "ArcManager");
        Allure.step("Verify Archive Manager site URL");
    }

    @Test
    public void verifyUserCanAccessVerifiedReportFromBreakTheGlassPage() throws InterruptedException, IOException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.OtherUsersLoginUltima(refUserName, password);
        Common.sqlQuery("UPDATE Users SET U_AllStudies_Viewed = '1' WHERE u_name = '" + refUserName + "'");
        browser.ultimaViewer.workListPage.clickOnToolsIconButton();
        browser.ultimaViewer.toolsPage.clickOnBreakTheGlassButton();
        Common.sqlQuery("update Studies Set Status_Reported = '2' where Study_ID = '" + study_ID + "'");
        browser.ultimaViewer.breakTheGlassPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnReportStatusListButton()
                .clickOnAllReportStatusOption("multiselect-all")
                .enterPatientName(patName)
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.breakTheGlassPage.getWorkListTableText().contains("Verified"));
        browser.ultimaViewer.breakTheGlassPage.clickOnReportIconButton();
        assertEquals(browser.ultimaViewer.breakTheGlassConfirmationPopup.getPopupMessage(), breakTheGlassConfirmationMsg);
        browser.ultimaViewer.breakTheGlassConfirmationPopup.clickOnConfirmButton();
        String actualResult = common.GetCurrentURL();
        Assert.assertTrue(actualResult.contains(url + "BReport/ReportViewer.aspx"));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    public void verifyUserCannotAccessCompletedNotVerifiedReportFromBreakTheGlassPage() throws InterruptedException, IOException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.OtherUsersLoginUltima(refUserName, password);
        Common.sqlQuery("UPDATE Users SET U_AllStudies_Viewed = '1' WHERE u_name = '" + refUserName + "'");
        browser.ultimaViewer.workListPage.clickOnToolsIconButton();
        browser.ultimaViewer.toolsPage.clickOnBreakTheGlassButton();
        Common.sqlQuery("update Studies Set Status_Reported = '1' where Study_ID = '" + study_ID + "'");
        browser.ultimaViewer.breakTheGlassPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnReportStatusListButton()
                .clickOnAllReportStatusOption("multiselect-all")
                .enterPatientName(patName)
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.breakTheGlassPage.getWorkListTableText().contains("Completed Not Verified"));
        browser.ultimaViewer.breakTheGlassPage.clickOnReportIconButton();
        assertEquals(browser.ultimaViewer.warningPopup.getWarningPopupMessage(), unauthorizedReportMessage);
        browser.ultimaViewer.warningPopup.clickOnOkButton();
        Assert.assertTrue(browser.ultimaViewer.breakTheGlassPage.getWorkListTableText().contains("Completed Not Verified"));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    public void verifyUserCannotAccessPartialReportFromBreakTheGlassPage() throws InterruptedException, IOException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.OtherUsersLoginUltima(refUserName, password);
        Common.sqlQuery("UPDATE Users SET U_AllStudies_Viewed = '1' WHERE u_name = '" + refUserName + "'");
        browser.ultimaViewer.workListPage.clickOnToolsIconButton();
        browser.ultimaViewer.toolsPage.clickOnBreakTheGlassButton();
        Common.sqlQuery("update Studies Set Status_Reported = '0' where Study_ID = '" + study_ID + "'");
        browser.ultimaViewer.breakTheGlassPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnReportStatusListButton()
                .clickOnAllReportStatusOption("multiselect-all")
                .enterPatientName(patName)
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.breakTheGlassPage.getWorkListTableText().contains("Partial"));
        browser.ultimaViewer.breakTheGlassPage.clickOnReportIconButton();
        assertEquals(browser.ultimaViewer.warningPopup.getWarningPopupMessage(), unauthorizedReportMessage);
        browser.ultimaViewer.warningPopup.clickOnOkButton();
        Assert.assertTrue(browser.ultimaViewer.breakTheGlassPage.getWorkListTableText().contains("Partial"));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    public void verifyUserCannotAccessNoneReportStatusFromBreakTheGlassPage() throws InterruptedException, IOException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.OtherUsersLoginUltima(refUserName, password);
        Common.sqlQuery("UPDATE Users SET U_AllStudies_Viewed = '1' WHERE u_name = '" + refUserName + "'");
        browser.ultimaViewer.workListPage.clickOnToolsIconButton();
        browser.ultimaViewer.toolsPage.clickOnBreakTheGlassButton();
        Common.sqlQuery("update Studies Set Status_Reported = '3' where Study_ID = '" + study_ID + "'");
        browser.ultimaViewer.breakTheGlassPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnReportStatusListButton()
                .clickOnAllReportStatusOption("multiselect-all")
                .enterPatientName(patName)
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.breakTheGlassPage.getWorkListTableText().contains("None"));
        browser.ultimaViewer.breakTheGlassPage.clickOnReportIconButton();
        assertEquals(browser.ultimaViewer.warningPopup.getWarningPopupMessage(), unauthorizedMessage);
        browser.ultimaViewer.warningPopup.clickOnOkButton();
        Assert.assertTrue(browser.ultimaViewer.breakTheGlassPage.getWorkListTableText().contains("None"));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    public void verifyUserCanAccessStudyImagesFromBreakTheGlassPage() throws InterruptedException, IOException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.OtherUsersLoginUltima(refUserName, password);
        Common.sqlQuery("UPDATE Users SET U_AllStudies_Viewed = '1' WHERE u_name = '" + refUserName + "'");
        browser.ultimaViewer.workListPage.clickOnToolsIconButton();
        browser.ultimaViewer.toolsPage.clickOnBreakTheGlassButton();
        browser.ultimaViewer.breakTheGlassPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .clickOnReportStatusListButton()
                .clickOnAllReportStatusOption("multiselect-all")
                .enterPatientName(patName)
                .clickOnSearchButton();
        browser.ultimaViewer.breakTheGlassPage.clickOnEyeIconButton();
        assertEquals(browser.ultimaViewer.breakTheGlassConfirmationPopup.getPopupMessage(), breakTheGlassConfirmationMsg);
        browser.ultimaViewer.breakTheGlassConfirmationPopup.clickOnConfirmButton();
        String actualResult = common.GetCurrentURL();
        Assert.assertTrue(actualResult.contains("ZFP"));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    public void verifyRadiologistCanViewAllStudiesWithRightViewAllStudies() throws InterruptedException, IOException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(radUserName, password);
        Common.sqlQuery("UPDATE Users SET U_AllStudies_Viewed = '1' WHERE u_name = '" + radUserName + "'");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientName(patName)
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(patName));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }

    @Test
    public void verifyTechnicianCanViewAllStudiesWithRightViewAllStudies() throws InterruptedException, IOException {
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.loginUltima(techUserName, password);
        Common.sqlQuery("UPDATE Users SET U_AllStudies_Viewed = '1' WHERE u_name = '" + techUserName + "'");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientName(patName)
                .clickOnSearchButton();
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(patName));
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.closeConnection();
    }


}
