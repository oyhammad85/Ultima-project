package com.paxera.ultima.tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;

@Epic("Study Management")
@Feature("Study Merge Operations")
public class MergeTest extends BaseTest {
    Common common = new Common();
    String pat_ID = setPaxerahealthCorporationConfig().getProperty("patientId");
    String pat_ID2 = setPaxerahealthCorporationConfig().getProperty("patientId2");
    String dcmCherryStudy = setPaxerahealthCorporationConfig().getProperty("dicomCherryStudy");
    String dcm17300Study = setPaxerahealthCorporationConfig().getProperty("dicom17300Study");
    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");
    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");
    public MergeTest() throws IOException {
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the functionality to merge two studies and then split them")
    @Story("Study Merge and Split")
    public void verifyMergeAndSplitStudy() throws IOException, InterruptedException {
        Allure.step("Initialize connection and import test studies");
        common.startConnection();
        common.importStudy(dcmCherryStudy);
        common.importStudy(dcm17300Study);

        Allure.step("Login to Ultima");
        common.loginUltima(userName, password);

        Allure.step("Search and select the first study for merge");
        browser.ultimaViewer.workListPage.openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID)
                .clickOnSearchButton()
                .selectStudy("1");

        Allure.step("Navigate to Merge/Split functionality");
        browser.ultimaViewer.workListPage.clickOnStudyActionButton()
                .selectFromStudyActionsMenu("Merge/Split", "Merge/Split study ");

        Allure.step("Execute merge operation");
        browser.ultimaViewer.workListPage.switchToEditStudyFrame();
        browser.ultimaViewer.mergeSplitStudyPage.clickOnNextButton()
                .enterPatientId(pat_ID2)
                .clickOnSearchButton()
                .clickOnMergeStudyRadioButton()
                .clickOnNextButton()
                .clickOnStartMergeButton()
                .clickOnFinishButton();

        Allure.step("Refresh and search for merged study");
        browser.ultimaViewer.workListPage.switchToDefaultFrame()
                .refreshScreen()
                .openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID2)
                .clickOnSearchButton()
                .selectStudy("1");

        Allure.step("Initiate split operation");
        browser.ultimaViewer.workListPage.clickOnStudyActionButton()
                .selectFromStudyActionsMenu("Merge/Split", "Merge/Split study ");
        browser.ultimaViewer.workListPage.switchToEditStudyFrame();

        Allure.step("Execute split operation");
        browser.ultimaViewer.mergeSplitStudyPage.clickOnStudyCheckBox()
                .clickOnNextButton()
                .clickOnSplitStudyRadioButton()
                .clickOnNextButton()
                .clickOnStartSplitButton()
                .clickOnFinishButton();

        Allure.step("Verify split result");
        browser.ultimaViewer.workListPage.switchToDefaultFrame()
                .refreshScreen()
                .openSearchDateRange()
                .selectDateRange("Any Date")
                .openModalitiesDropDown()
                .selectAllModalitiesOption()
                .enterPatientId(pat_ID2)
                .clickOnSearchButton()
                .selectStudy("1")
                .selectStudy("2");
        Assert.assertTrue(browser.ultimaViewer.workListPage.getWorkListTableText().contains(pat_ID2));

        Allure.step("Clean up test data");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID + "'");
        Common.sqlQuery("Delete From Studies where Study_ID = '" + study_ID2 + "'");
        Common.closeConnection();

    }
}
