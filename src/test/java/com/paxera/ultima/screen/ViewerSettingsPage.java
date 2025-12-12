package com.paxera.ultima.screen;

import org.openqa.selenium.By;

public class ViewerSettingsPage extends BaseScreen {

    private final By viewerSettingScreenTitle = By.id("ctl00_ContentPlaceHolder1_lbl_ViewerSettings");
    private final By currentStudyRadioButton = By.id("ctl00_ContentPlaceHolder1_studies_radiolst_0");
    private final By currentPriorStudiesRadioButton = By.id("ctl00_ContentPlaceHolder1_studies_radiolst_1");
    private final By currentPriorOldestStudiesRadioButton = By.id("ctl00_ContentPlaceHolder1_studies_radiolst_2");
    private final By openPriorsWithSimilarModalitiesForTheSelectedStudyCheckBox = By.id("ctl00_ContentPlaceHolder1_cb_priorwithsameMod");
    private final By openPriorsWithSimilarBodyPartsForTheSelectedStudiesCheckBox = By.id("ctl00_ContentPlaceHolder1_cb_priorwithBodyPart");
    private final By retrievePatientPriorsFromAllBranchesCheckBox = By.id("ctl00_ContentPlaceHolder1_cb_priorAllBranches");
    private final By enableStreamingCheckBox = By.id("ctl00_ContentPlaceHolder1_cb_Streaming");
    private final By enableLossyCompressionCheckBox = By.id("ctl00_ContentPlaceHolder1_cb_LossyCompression");
    private final By enableStudyNavigation = By.id("ctl00_ContentPlaceHolder1_cb_Navigation");
    private final By selectModalityDropDown = By.id("ctl00_ContentPlaceHolder1_lbl_Modality");
    private final By viewTypeDropDown = By.id("ctl00_ContentPlaceHolder1_ViewStudyList");
    private final By sameModalitiesDropdown = By.id("ctl00_ContentPlaceHolder1_SameModilityList");
    private final By sameBodyPartDropdown = By.id("ctl00_ContentPlaceHolder1_SameBodyPartList");


    public ViewerSettingsPage getViewerSettingsScreenTitle() {
        waitUtils.waitUntilElementUntilIsClickable(viewerSettingScreenTitle).getText();
        return this;
    }

    private String getModalityNameXpath(String modalityName) {
        return "//*[contains(text(),'" + modalityName + "')]";
    }

    public ViewerSettingsPage clickOnCurrentStudyRadioButton() {
        waitUtils.waitUntilElementUntilIsClickable(currentStudyRadioButton).click();
        return this;
    }

    public ViewerSettingsPage clickOnCurrentPriorStudiesRadioButton() {
        waitUtils.waitUntilElementUntilIsClickable(currentPriorStudiesRadioButton).click();
        return this;
    }

    public ViewerSettingsPage clickOnCurrentPriorOldestStudiesRadioButton() {
        waitUtils.waitUntilElementUntilIsClickable(currentPriorOldestStudiesRadioButton).click();
        return this;
    }

    public ViewerSettingsPage clickOnOpenPriorsWithSimilarModalitiesForTheSelectedStudyCheckBox() {
        waitUtils.waitUntilElementUntilIsClickable(openPriorsWithSimilarModalitiesForTheSelectedStudyCheckBox).click();
        return this;
    }

    public ViewerSettingsPage clickOnOpenPriorsWithSimilarBodyPartsForTheSelectedStudiesCheckBox() {
        waitUtils.waitUntilElementUntilIsClickable(openPriorsWithSimilarBodyPartsForTheSelectedStudiesCheckBox).click();
        return this;
    }

    public ViewerSettingsPage clickOnRetrievePatientPriorsFromAllBranchesCheckBox() {
        waitUtils.waitUntilElementUntilIsClickable(retrievePatientPriorsFromAllBranchesCheckBox).click();
        return this;
    }

    public ViewerSettingsPage clickOnEnableStreamingCheckBox() {
        waitUtils.waitUntilElementUntilIsClickable(enableStreamingCheckBox).click();
        return this;
    }

    public ViewerSettingsPage clickOnEnableLossyCompressionCheckBox() {
        waitUtils.waitUntilElementUntilIsClickable(enableLossyCompressionCheckBox).click();
        return this;
    }

    public ViewerSettingsPage clickOnEnableStudyNavigation() {
        waitUtils.waitUntilElementUntilIsClickable(enableStudyNavigation).click();
        return this;
    }

    public ViewerSettingsPage selectModality(String modalityName) {
        waitUtils.waitUntilElementUntilIsClickable(selectModalityDropDown).click();
        waitUtils.waitUntilElementIsPresence(By.xpath(getModalityNameXpath(modalityName))).click();
        return this;
    }

    public ViewerSettingsPage selectFromViewTypeDropDwn(String value) {
        selectFromDropdownList(viewTypeDropDown, value);
        return this;
    }

    public ViewerSettingsPage selectFromSameModalitiesDropDwn(String value) {
        selectFromDropdownList(sameModalitiesDropdown, value);
        return this;
    }

    public ViewerSettingsPage selectFromSameBodyPartDropDwn(String value) {
        selectFromDropdownList(sameBodyPartDropdown, value);
        return this;
    }


    public ViewerSettingsPage assertCurrentStudyRadioButtonIsSelected(boolean status) {
        assertItemIsSelected(currentStudyRadioButton, status);
        return this;
    }

    public ViewerSettingsPage assertCurrentPriorStudiesRadioButtonIsSelected(boolean status) {
        assertItemIsSelected(currentPriorStudiesRadioButton, status);
        return this;
    }

    public ViewerSettingsPage assertCurrentPriorOldestStudiesRadioButtonIsSelected(boolean status) {
        assertItemIsSelected(currentPriorOldestStudiesRadioButton, status);
        return this;
    }

    public ViewerSettingsPage assertOpenPriorsWithSimilarModalitiesForTheSelectedStudyCheckBoxIsSelected(boolean status) {
        assertItemIsSelected(openPriorsWithSimilarModalitiesForTheSelectedStudyCheckBox, status);
        return this;
    }

    public ViewerSettingsPage assertOpenPriorsWithSimilarBodyPartsForTheSelectedStudiesCheckBoxIsSelected(boolean status) {
        assertItemIsSelected(openPriorsWithSimilarBodyPartsForTheSelectedStudiesCheckBox, status);
        return this;
    }

    public ViewerSettingsPage assertRetrievePatientPriorsFromAllBranchesCheckBoxIsSelected(boolean status) {
        assertItemIsSelected(retrievePatientPriorsFromAllBranchesCheckBox, status);
        return this;
    }

    public ViewerSettingsPage assertEnableStreamingCheckBoxIsSelected(boolean status) {
        assertItemIsSelected(enableStreamingCheckBox, status);
        return this;
    }

    public ViewerSettingsPage assertEnableLossyCompressionCheckBoxIsSelected(boolean status) {
        assertItemIsSelected(enableLossyCompressionCheckBox, status);
        return this;
    }

    public ViewerSettingsPage assertEnableStudyNavigationIsSelected(boolean status) {
        assertItemIsSelected(enableStudyNavigation, status);
        return this;
    }

}

