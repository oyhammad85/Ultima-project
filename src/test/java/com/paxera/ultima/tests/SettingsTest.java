package com.paxera.ultima.tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;
import static org.testng.Assert.assertEquals;

public class SettingsTest extends BaseTest {

    String cacheDir = setPaxerahealthCorporationConfig().getProperty("cacheDirectory");
    Common common = new Common();
    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    String study_ID = setPaxerahealthCorporationConfig().getProperty("studyId");
    String study_ID2 = setPaxerahealthCorporationConfig().getProperty("studyId2");
    public SettingsTest() throws IOException {
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Change Customer Settings")
    @Story("Story Name: Change Customer Settings")
    public void verifyUserAbleToChangeCustomerSettings() throws InterruptedException, IOException {

        common.loginUltima(userName, password);
        Allure.step("Login to Ultima");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        Allure.step("Click on Profile icon");
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        Allure.step("Click on Settings button");
        browser.ultimaViewer.settingsScreen.clickOnCustomerSettingsButton();
        Allure.step("Click on Customer Settings button");
        browser.ultimaViewer.customerSettingsPage.assertOnSelectedOption("Branch Base")
                .selectFromChatSettingsScreen("Customer Base")
                .assertOnSelectedOption("Customer Base");
        Allure.step("Select and assert Customer Base option");
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
        Allure.step("Click on Save button");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Change HL7 Settings")
    @Story("Story Name: Change HL7 Settings")
    public void verifyUserAbleToChangeHl7Settings() throws InterruptedException, IOException {

        common.loginUltima(userName, password);
        Allure.step("Login to Ultima");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        Allure.step("Click on Profile icon");
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        Allure.step("Click on Settings button");
        browser.ultimaViewer.settingsScreen.clickOnLocationSettingsButton();
        Allure.step("Click on Location Settings button");
        browser.ultimaViewer.locationSettingsPage.assertEnableHl7CheckboxIsChecked(false)
                .clickEnableHl7Checkbox()
                .assertDeleteStudyCheckboxIsChecked(false)
                .clickEnableHl7WhenDeleteStudyCheckbox()
                .assertUpdateStudyCheckboxIsChecked(false)
                .clickEnableHl7WhenUpdateStudyCheckbox()
                .assertSplitStudyCheckboxIsChecked(false)
                .clickEnableHl7WhenSplitStudyCheckbox()
                .enterHl7DirectoryTextFiled("C:\\Hl7");
        Allure.step("Configure HL7 settings and directory");
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
        Allure.step("Click on Save button");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        browser.ultimaViewer.settingsScreen.clickOnLocationSettingsButton();
        browser.ultimaViewer.locationSettingsPage.assertEnableHl7CheckboxIsChecked(true)
                .assertDeleteStudyCheckboxIsChecked(true)
                .assertUpdateStudyCheckboxIsChecked(true)
                .assertSplitStudyCheckboxIsChecked(true)
                .clearHl7DirectoryTextFiled();
        Thread.sleep(1000);
        browser.ultimaViewer.cacheSettingsPopup.clickOnOkButton();
        browser.ultimaViewer.locationSettingsPage.clickEnableHl7WhenSplitStudyCheckbox();
        browser.ultimaViewer.locationSettingsPage.clickEnableHl7WhenUpdateStudyCheckbox()
                .clickEnableHl7WhenDeleteStudyCheckbox()
                .clickEnableHl7Checkbox();
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Change HL7 Settings When Save Report")
    @Story("Story Name: Change HL7 Settings When Save Report")
    public void verifyUserAbleToChangeHl7SettingsWhenSaveReport() throws InterruptedException, IOException {

        common.loginUltima(userName, password);
        Allure.step("Login to Ultima");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        Allure.step("Click on Profile icon");
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        Allure.step("Click on Settings button");
        browser.ultimaViewer.settingsScreen.clickOnLocationSettingsButton();
        Allure.step("Click on Location Settings button");
        browser.ultimaViewer.locationSettingsPage.assertEnableHl7CheckboxIsChecked(false)
                .clickEnableHl7Checkbox()
                .assertCompletedNotVerifiedCheckboxIsChecked(false)
                .clickOnCompletedNotVerifiedCheckbox()
                .assertVerifiedReportCheckboxIsChecked(false)
                .clickOnVerifiedCheckbox()
                .enterHl7DirectoryTextFiled("C:\\Hl7");
        Allure.step("Configure HL7 settings for report saving");
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
        Allure.step("Click on Save button");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        browser.ultimaViewer.settingsScreen.clickOnLocationSettingsButton();
        browser.ultimaViewer.locationSettingsPage.assertEnableHl7CheckboxIsChecked(true)
                .assertCompletedNotVerifiedCheckboxIsChecked(true)
                .assertVerifiedReportCheckboxIsChecked(true)
                .clearHl7DirectoryTextFiled();
        browser.ultimaViewer.cacheSettingsPopup.clickOnOkButton();
        browser.ultimaViewer.locationSettingsPage.clickOnCompletedNotVerifiedCheckbox()
                .clickOnVerifiedCheckbox()
                .clickEnableHl7Checkbox();
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Change Report Settings")
    @Story("Story Name: Change Report Settings")
    public void verifyUserAbleToChangeReportSettings() throws InterruptedException, IOException {

        common.loginUltima(userName, password);
        Allure.step("Login to Ultima");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        Allure.step("Click on Profile icon");
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        Allure.step("Click on Settings button");
        browser.ultimaViewer.settingsScreen.clickOnLocationSettingsButton();
        Allure.step("Click on Location Settings button");
        browser.ultimaViewer.locationSettingsPage.assertAddReportHeaderWhileReceivingORUCheckboxIsChecked(false)
                .clickOnAddReportHeaderWhileReceivingORUCheckbox()
                .assertAddReportFooterWhileReceivingORUCheckboxIsChecked(false)
                .clickOnAddReportFooterWhileReceivingORUCheckbox()
                .selectFromUserSettingsDropDown(userName);
        Allure.step("Configure report header, footer and user settings");
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
        Allure.step("Click on Save button");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        browser.ultimaViewer.settingsScreen.clickOnLocationSettingsButton();
        browser.ultimaViewer.locationSettingsPage.assertAddReportHeaderWhileReceivingORUCheckboxIsChecked(true)
                .clickOnAddReportHeaderWhileReceivingORUCheckbox()
                .assertAddReportFooterWhileReceivingORUCheckboxIsChecked(true)
                .clickOnAddReportFooterWhileReceivingORUCheckbox()
                .assertSelectedUserFromUserSettingsDropDown("Select user");
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Change Date Time Format Settings")
    @Story("Story Name: Change Date Time Format Settings")
    public void verifyUserAbleToChangeDateTimeFormatSettings() throws InterruptedException, IOException {

        common.loginUltima(userName, password);
        Allure.step("Login to Ultima");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        Allure.step("Click on Profile icon");
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        Allure.step("Click on Settings button");
        browser.ultimaViewer.settingsScreen.clickOnLocationSettingsButton();
        Allure.step("Click on Location Settings button");
        browser.ultimaViewer.locationSettingsPage.selectFromDateFormatDropDown("MM/dd/yyyy")
                .assertOnSelectedOptionFromDateFormat("MM/dd/yyyy")
                .selectFromTimeFormatDropDown("24 h")
                .assertOnSelectedOptionFromTimeFormat("24 h");
        Allure.step("Select and assert date/time format");
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Change Other Settings From Location Settings")
    @Story("Story Name: Change Other Settings From Location Settings")
    public void verifyUserAbleToChangeOtherSettingsFromLocationSettings() throws InterruptedException, IOException {

        common.loginUltima(userName, password);
        Allure.step("Login to Ultima");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        Allure.step("Click on Profile icon");
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        Allure.step("Click on Settings button");
        browser.ultimaViewer.settingsScreen.clickOnLocationSettingsButton();
        Allure.step("Click on Location Settings button");
        browser.ultimaViewer.locationSettingsPage.assertAutomaticallyMarkStudyAsCompletedOnceVerifyReportCheckboxIsChecked(false)
                .clickOnAutomaticallyMarkStudyAsCompletedOnceVerifyReportCheckbox()
                .assertAutomaticallyMarkStudyAsViewedOnceOpenedInViewersCheckboxIsChecked(false)
                .clickOnAutomaticallyMarkStudyAsViewedOnceOpenedInViewersCheckbox()
                .assertOpenReportWhenStatusIsCompletedCheckboxIsChecked(false)
                .clickOnOpenReportWhenStatusIsCompletedCheckbox();
        Allure.step("Configure other location settings");
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
        Allure.step("Click on Save button");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        browser.ultimaViewer.settingsScreen.clickOnLocationSettingsButton();
        browser.ultimaViewer.locationSettingsPage.assertAutomaticallyMarkStudyAsCompletedOnceVerifyReportCheckboxIsChecked(true)
                .assertAutomaticallyMarkStudyAsViewedOnceOpenedInViewersCheckboxIsChecked(true)
                .assertOpenReportWhenStatusIsCompletedCheckboxIsChecked(true)
                .clickOnAutomaticallyMarkStudyAsCompletedOnceVerifyReportCheckbox()
                .clickOnAutomaticallyMarkStudyAsViewedOnceOpenedInViewersCheckbox()
                .clickOnOpenReportWhenStatusIsCompletedCheckbox();
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Validate Cache Directory Displayed With Default Value At PXCacheFile")
    @Story("Story Name: Validate Cache Directory At PXCacheFile")
    public void validateCacheDirectoryDisplayedWithDefaultValueAtPXCacheFile() throws InterruptedException, IOException {

        common.loginUltima(userName, password);
        Allure.step("Login to Ultima");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        Allure.step("Click on Profile icon");
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        Allure.step("Click on Settings button");
        browser.ultimaViewer.settingsScreen.clickOnCacheSettingsButton();
        Allure.step("Click on Cache Settings button");
        assertEquals(browser.ultimaViewer.cacheSettingsPage.getCacheDirectoryTextField(), cacheDir);
        Allure.step("Assert cache directory value");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Change Option For On Close Viewer")
    @Story("Story Name: Change Option For On Close Viewer")
    public void verifyUserCanChangeOptionForOnCloseViewer() throws InterruptedException, IOException {

        common.loginUltima(userName, password);
        Allure.step("Login to Ultima");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        Allure.step("Click on Profile icon");
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        Allure.step("Click on Settings button");
        browser.ultimaViewer.settingsScreen.clickOnCacheSettingsButton();
        Allure.step("Click on Cache Settings button");
        browser.ultimaViewer.cacheSettingsPage.clickOnClearCachedStudyRadioButton();
        Allure.step("Select Clear Cached Study option");
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
        Allure.step("Click on Save button");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        browser.ultimaViewer.settingsScreen.clickOnCacheSettingsButton();
        browser.ultimaViewer.cacheSettingsPage.assertClearCachedStudyRadioButtonIsSelected(true)
                .assertCachedStudyRadioButtonIsSelected(false)
                .clickOnCacheStudyRadioButton();
        Allure.step("Select Cache Study option");
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
        Allure.step("Click on Save button");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        browser.ultimaViewer.settingsScreen.clickOnCacheSettingsButton();
        browser.ultimaViewer.cacheSettingsPage.assertCachedStudyRadioButtonIsSelected(true);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Verify Auto Clear Cache Checked By Default And User Can Change Duration")
    @Story("Story Name: Auto Clear Cache Settings")
    public void verifyAutoClearCacheCheckedByDefaultAndUserCanChangeDuration() throws InterruptedException, IOException {

        common.loginUltima(userName, password);
        Allure.step("Login to Ultima");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        Allure.step("Click on Profile icon");
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        Allure.step("Click on Settings button");
        browser.ultimaViewer.settingsScreen.clickOnCacheSettingsButton();
        Allure.step("Click on Cache Settings button");
        browser.ultimaViewer.cacheSettingsPage.assertAutoClearCacheIsSelected(true)
                .enterAutoClearCacheTextField("60");
        Allure.step("Set auto clear cache duration to 60 minutes");
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
        Allure.step("Click on Save button");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        browser.ultimaViewer.settingsScreen.clickOnCacheSettingsButton();
        assertEquals(browser.ultimaViewer.cacheSettingsPage.getAutoClearCacheTextField(), "60");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Change Viewer Settings From Current Study To Current Prior Study")
    @Story("Story Name: Change Viewer Settings Study Type")
    public void verifyUserAbleToChangeViewerSettingsFromCurrentStudyToCurrentPriorStudy() throws InterruptedException, IOException {

        common.loginUltima(userName, password);
        Allure.step("Login to Ultima");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        Allure.step("Click on Profile icon");
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        Allure.step("Click on Settings button");
        browser.ultimaViewer.settingsScreen.clickOnViewerSettingsButton();
        Allure.step("Click on Viewer Settings button");
        browser.ultimaViewer.viewerSettingsPage.assertCurrentStudyRadioButtonIsSelected(true)
                .assertCurrentPriorStudiesRadioButtonIsSelected(false)
                .assertCurrentPriorOldestStudiesRadioButtonIsSelected(false)
                .assertOpenPriorsWithSimilarModalitiesForTheSelectedStudyCheckBoxIsSelected(false)
                .assertOpenPriorsWithSimilarBodyPartsForTheSelectedStudiesCheckBoxIsSelected(false)
                .clickOnCurrentPriorStudiesRadioButton()
                .clickOnOpenPriorsWithSimilarModalitiesForTheSelectedStudyCheckBox()
                .clickOnOpenPriorsWithSimilarBodyPartsForTheSelectedStudiesCheckBox();
        Allure.step("Change viewer settings to Current Prior Studies with similar modalities and body parts");
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
        Allure.step("Click on Save button");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        browser.ultimaViewer.settingsScreen.clickOnViewerSettingsButton();
        browser.ultimaViewer.viewerSettingsPage.assertCurrentStudyRadioButtonIsSelected(false)
                .assertCurrentPriorStudiesRadioButtonIsSelected(true)
                .assertOpenPriorsWithSimilarModalitiesForTheSelectedStudyCheckBoxIsSelected(true)
                .assertOpenPriorsWithSimilarBodyPartsForTheSelectedStudiesCheckBoxIsSelected(true)
                .clickOnCurrentStudyRadioButton();
        Allure.step("Revert viewer settings to Current Study");
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Change Viewer Settings From Current Study To Current Prior Oldest Studies")
    @Story("Story Name: Change Viewer Settings Study Type to Oldest")
    public void verifyUserAbleToChangeViewerSettingsFromCurrentStudyToCurrentPriorOldestStudies() throws InterruptedException, IOException {

        common.loginUltima(userName, password);
        Allure.step("Login to Ultima");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        Allure.step("Click on Profile icon");
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        Allure.step("Click on Settings button");
        browser.ultimaViewer.settingsScreen.clickOnViewerSettingsButton();
        Allure.step("Click on Viewer Settings button");
        browser.ultimaViewer.viewerSettingsPage.assertCurrentStudyRadioButtonIsSelected(true)
                .assertCurrentPriorStudiesRadioButtonIsSelected(false)
                .assertCurrentPriorOldestStudiesRadioButtonIsSelected(false)
                .assertOpenPriorsWithSimilarModalitiesForTheSelectedStudyCheckBoxIsSelected(false)
                .assertOpenPriorsWithSimilarBodyPartsForTheSelectedStudiesCheckBoxIsSelected(false)
                .clickOnCurrentPriorOldestStudiesRadioButton()
                .clickOnOpenPriorsWithSimilarModalitiesForTheSelectedStudyCheckBox()
                .clickOnOpenPriorsWithSimilarBodyPartsForTheSelectedStudiesCheckBox();
        Allure.step("Change viewer settings to Current Prior Oldest Studies with similar modalities and body parts");
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
        Allure.step("Click on Save button");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        browser.ultimaViewer.settingsScreen.clickOnViewerSettingsButton();
        browser.ultimaViewer.viewerSettingsPage.assertCurrentStudyRadioButtonIsSelected(false)
                .assertCurrentPriorStudiesRadioButtonIsSelected(false)
                .assertCurrentPriorOldestStudiesRadioButtonIsSelected(true)
                .assertOpenPriorsWithSimilarModalitiesForTheSelectedStudyCheckBoxIsSelected(true)
                .assertOpenPriorsWithSimilarBodyPartsForTheSelectedStudiesCheckBoxIsSelected(true)
                .clickOnCurrentStudyRadioButton();
        Allure.step("Revert viewer settings to Current Study");
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Change Viewer Settings By Enable Retrieve Patient Priors From All Branches")
    @Story("Story Name: Change Viewer Settings Retrieve Patient Priors")
    public void verifyUserAbleToChangeViewerSettingsByEnableRetrievePatientPriorsFromAllBranches() throws InterruptedException, IOException {

        common.loginUltima(userName, password);
        Allure.step("Login to Ultima");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        Allure.step("Click on Profile icon");
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        Allure.step("Click on Settings button");
        browser.ultimaViewer.settingsScreen.clickOnViewerSettingsButton();
        Allure.step("Click on Viewer Settings button");
        browser.ultimaViewer.viewerSettingsPage.assertRetrievePatientPriorsFromAllBranchesCheckBoxIsSelected(false)
                .clickOnRetrievePatientPriorsFromAllBranchesCheckBox();
        Allure.step("Enable Retrieve Patient Priors From All Branches");
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
        Allure.step("Click on Save button");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        browser.ultimaViewer.settingsScreen.clickOnViewerSettingsButton();
        browser.ultimaViewer.viewerSettingsPage.assertRetrievePatientPriorsFromAllBranchesCheckBoxIsSelected(true)
                .clickOnRetrievePatientPriorsFromAllBranchesCheckBox();
        Allure.step("Disable Retrieve Patient Priors From All Branches");
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Change Viewer Settings By Enable Lossy Compression")
    @Story("Story Name: Change Viewer Settings Lossy Compression")
    public void verifyUserAbleToChangeViewerSettingsByEnableLossyCompression() throws InterruptedException, IOException {

        common.loginUltima(userName, password);
        Allure.step("Login to Ultima");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        Allure.step("Click on Profile icon");
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        Allure.step("Click on Settings button");
        browser.ultimaViewer.settingsScreen.clickOnViewerSettingsButton();
        Allure.step("Click on Viewer Settings button");
        browser.ultimaViewer.viewerSettingsPage.assertEnableLossyCompressionCheckBoxIsSelected(false)
                .clickOnEnableLossyCompressionCheckBox();
        Allure.step("Enable Lossy Compression");
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
        Allure.step("Click on Save button");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        browser.ultimaViewer.settingsScreen.clickOnViewerSettingsButton();
        browser.ultimaViewer.viewerSettingsPage.assertEnableLossyCompressionCheckBoxIsSelected(true)
                .clickOnEnableLossyCompressionCheckBox();
        Allure.step("Disable Lossy Compression");
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Change Viewer Settings By Enable Study Navigation")
    @Story("Story Name: Change Viewer Settings Study Navigation")
    public void verifyUserAbleToChangeViewerSettingsByEnableStudyNavigation() throws InterruptedException, IOException {

        common.loginUltima(userName, password);
        Allure.step("Login to Ultima");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        Allure.step("Click on Profile icon");
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        Allure.step("Click on Settings button");
        browser.ultimaViewer.settingsScreen.clickOnViewerSettingsButton();
        Allure.step("Click on Viewer Settings button");
        browser.ultimaViewer.viewerSettingsPage.assertEnableStudyNavigationIsSelected(false)
                .clickOnEnableStudyNavigation();
        Allure.step("Enable Study Navigation");
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
        Allure.step("Click on Save button");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        browser.ultimaViewer.settingsScreen.clickOnViewerSettingsButton();
        browser.ultimaViewer.viewerSettingsPage.assertEnableStudyNavigationIsSelected(true)
                .clickOnEnableStudyNavigation();
        Allure.step("Disable Study Navigation");
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description to Verify Enable Streaming Checkbox Selected By Default")
    @Story("Story Name: Verify Enable Streaming Checkbox")
    public void verifyEnableStreamingCheckboxSelectedByDefault() throws InterruptedException, IOException {

        common.loginUltima(userName, password);
        Allure.step("Login to Ultima");
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        Allure.step("Click on Profile icon");
        browser.ultimaViewer.profileMenuPage.clickOnSettingsButton();
        Allure.step("Click on Settings button");
        browser.ultimaViewer.settingsScreen.clickOnViewerSettingsButton();
        Allure.step("Click on Viewer Settings button");
        browser.ultimaViewer.viewerSettingsPage.assertEnableStreamingCheckBoxIsSelected(true);
        Allure.step("Verify Enable Streaming checkbox is selected by default");
        browser.ultimaViewer.settingsScreen.clickOnSaveButton();
    }
}
