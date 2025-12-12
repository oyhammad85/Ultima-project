package com.paxera.ultima.screen;

import org.openqa.selenium.By;

public class LocationSettingsPage extends BaseScreen {

    private final By locationSettingScreenTitle = By.id("ctl00_ContentPlaceHolder1_lbl_Loc_Setting");
    private final By hl7SectionTitle = By.id("ctl00_ContentPlaceHolder1_HL7_lbl");
    private final By hl7Checkbox = By.id("ctl00_ContentPlaceHolder1_Cbl_EnableHL7");
    private final By hl7WhenDeleteStudyCheckbox = By.id("ctl00_ContentPlaceHolder1_Cbl_DeleteStudy");
    private final By hl7WhenUpdateStudyCheckbox = By.id("ctl00_ContentPlaceHolder1_Cbl_UpdateStudy");
    private final By hl7WhenSplitStudyCheckbox = By.id("ctl00_ContentPlaceHolder1_Cbl_SplitStudy");
    private final By completedNotVerifiedReportCheckbox = By.id("ctl00_ContentPlaceHolder1_Cbl_CompletedNotVerified");
    private final By verifiedReportCheckbox = By.id("ctl00_ContentPlaceHolder1_Cbl_Verified");
    private final By hl7DirectoryTextField = By.id("ctl00_ContentPlaceHolder1_HL7_Dir_txt");
    private final By reportSettingsSectionTitle = By.id("ctl00_ContentPlaceHolder1_lblBasicReportSettings");
    private final By addReportHeaderWhileReceivingORU = By.id("ctl00_ContentPlaceHolder1_BReportAdding");
    private final By addReportFooterWhileReceivingORU = By.id("ctl00_ContentPlaceHolder1_BReportAdding_Footer");
    private final By userSettingsDropDown = By.id("ctl00_ContentPlaceHolder1_Users_ddl");
    private final By dateFormatDropDown = By.id("ctl00_ContentPlaceHolder1_drpDateFormatOptions");
    private final By timeFormatDropDown = By.id("ctl00_ContentPlaceHolder1_drpTimeFormatOptions");
    private final By automaticallyMarkStudyAsViewedOnceOpenedInViewersCheckbox = By.id("ctl00_ContentPlaceHolder1_ChkMarkStudyAsViwed");
    private final By automaticallyMarkStudyAsCompletedOnceVerifyReportCheckbox = By.id("ctl00_ContentPlaceHolder1_ChkMarkCompletedWhenVerify");
    private final By openReportWhenStatusIsCompleted = By.id("ctl00_ContentPlaceHolder1_ChkOpenReportWhenCompleted");

    public LocationSettingsPage enterHl7DirectoryTextFiled(String hl7Directory) {
        waitUtils.waitUntilElementUntilIsClickable(hl7DirectoryTextField).sendKeys(hl7Directory);
        return this;
    }

    public LocationSettingsPage clearHl7DirectoryTextFiled() {
        waitUtils.waitUntilElementUntilIsClickable(hl7DirectoryTextField).clear();
        return this;
    }

    public LocationSettingsPage getHl7DirectoryText() {
        waitUtils.waitUntilElementUntilIsClickable(hl7DirectoryTextField).getText();
        return this;
    }

    public LocationSettingsPage getLocationSettingsScreenTitle() {
        waitUtils.waitUntilElementUntilIsClickable(locationSettingScreenTitle).getText();
        return this;
    }

    public LocationSettingsPage getHl7SectionTitle() {
        waitUtils.waitUntilElementUntilIsClickable(hl7SectionTitle).getText();
        return this;
    }

    public LocationSettingsPage getReportSettingsSectionTitle() {
        waitUtils.waitUntilElementUntilIsClickable(reportSettingsSectionTitle).getText();
        return this;
    }

    public LocationSettingsPage clickEnableHl7Checkbox() {
        waitUtils.waitUntilElementUntilIsClickable(hl7Checkbox).click();
        return this;
    }

    public LocationSettingsPage clickEnableHl7WhenDeleteStudyCheckbox() {
        waitUtils.waitUntilElementUntilIsClickable(hl7WhenDeleteStudyCheckbox).click();
        return this;
    }

    public LocationSettingsPage clickEnableHl7WhenUpdateStudyCheckbox() {
        waitUtils.waitUntilElementUntilIsClickable(hl7WhenUpdateStudyCheckbox).click();
        return this;
    }

    public LocationSettingsPage clickEnableHl7WhenSplitStudyCheckbox() {
        waitUtils.waitUntilElementUntilIsClickable(hl7WhenSplitStudyCheckbox).click();
        return this;
    }

    public LocationSettingsPage clickOnCompletedNotVerifiedCheckbox() {
        waitUtils.waitUntilElementUntilIsClickable(completedNotVerifiedReportCheckbox).click();
        return this;
    }

    public LocationSettingsPage clickOnVerifiedCheckbox() {
        waitUtils.waitUntilElementUntilIsClickable(verifiedReportCheckbox).click();
        return this;
    }

    public LocationSettingsPage clickOnAddReportHeaderWhileReceivingORUCheckbox() {
        waitUtils.waitUntilElementUntilIsClickable(addReportHeaderWhileReceivingORU).click();
        return this;
    }

    public LocationSettingsPage clickOnAddReportFooterWhileReceivingORUCheckbox() {
        waitUtils.waitUntilElementUntilIsClickable(addReportFooterWhileReceivingORU).click();
        return this;
    }

    public LocationSettingsPage clickOnAutomaticallyMarkStudyAsViewedOnceOpenedInViewersCheckbox() {
        waitUtils.waitUntilElementUntilIsClickable(automaticallyMarkStudyAsViewedOnceOpenedInViewersCheckbox).click();
        return this;
    }

    public LocationSettingsPage clickOnAutomaticallyMarkStudyAsCompletedOnceVerifyReportCheckbox() {
        waitUtils.waitUntilElementUntilIsClickable(automaticallyMarkStudyAsCompletedOnceVerifyReportCheckbox).click();
        return this;
    }

    public LocationSettingsPage clickOnOpenReportWhenStatusIsCompletedCheckbox() {
        waitUtils.waitUntilElementUntilIsClickable(openReportWhenStatusIsCompleted).click();
        return this;
    }


    public LocationSettingsPage selectFromUserSettingsDropDown(String option) {
        selectFromDropdownList(userSettingsDropDown, option);
        return this;
    }

    public LocationSettingsPage assertSelectedUserFromUserSettingsDropDown(String option) {
        assertOnSelectedItem(userSettingsDropDown, option);
        return this;
    }


    public LocationSettingsPage selectFromDateFormatDropDown(String option) {
        selectFromDropdownList(dateFormatDropDown, option);
        return this;
    }

    public LocationSettingsPage assertOnSelectedOptionFromDateFormat(String option) {
        assertOnSelectedItem(dateFormatDropDown, option);
        return this;
    }

    public LocationSettingsPage selectFromTimeFormatDropDown(String option) {
        selectFromDropdownList(timeFormatDropDown, option);
        return this;
    }

    public LocationSettingsPage assertOnSelectedOptionFromTimeFormat(String option) {
        assertOnSelectedItem(timeFormatDropDown, option);
        return this;
    }

    public LocationSettingsPage assertEnableHl7CheckboxIsChecked(boolean status) {
        assertItemIsSelected(hl7Checkbox, status);
        return this;
    }

    public LocationSettingsPage assertDeleteStudyCheckboxIsChecked(boolean status) {
        assertItemIsSelected(hl7WhenDeleteStudyCheckbox, status);
        return this;
    }

    public LocationSettingsPage assertUpdateStudyCheckboxIsChecked(boolean status) {
        assertItemIsSelected(hl7WhenUpdateStudyCheckbox, status);
        return this;
    }

    public LocationSettingsPage assertSplitStudyCheckboxIsChecked(boolean status) {
        assertItemIsSelected(hl7WhenSplitStudyCheckbox, status);
        return this;
    }

    public LocationSettingsPage assertCompletedNotVerifiedCheckboxIsChecked(boolean status) {
        assertItemIsSelected(completedNotVerifiedReportCheckbox, status);
        return this;
    }

    public LocationSettingsPage assertVerifiedReportCheckboxIsChecked(boolean status) {
        assertItemIsSelected(verifiedReportCheckbox, status);
        return this;
    }

    public LocationSettingsPage assertAddReportHeaderWhileReceivingORUCheckboxIsChecked(boolean status) {
        assertItemIsSelected(addReportHeaderWhileReceivingORU, status);
        return this;
    }

    public LocationSettingsPage assertAddReportFooterWhileReceivingORUCheckboxIsChecked(boolean status) {
        assertItemIsSelected(addReportFooterWhileReceivingORU, status);
        return this;
    }

    public LocationSettingsPage assertAutomaticallyMarkStudyAsViewedOnceOpenedInViewersCheckboxIsChecked(boolean status) {
        assertItemIsSelected(automaticallyMarkStudyAsViewedOnceOpenedInViewersCheckbox, status);
        return this;
    }

    public LocationSettingsPage assertAutomaticallyMarkStudyAsCompletedOnceVerifyReportCheckboxIsChecked(boolean status) {
        assertItemIsSelected(automaticallyMarkStudyAsCompletedOnceVerifyReportCheckbox, status);
        return this;
    }

    public LocationSettingsPage assertOpenReportWhenStatusIsCompletedCheckboxIsChecked(boolean status) {
        assertItemIsSelected(openReportWhenStatusIsCompleted, status);
        return this;
    }


}

