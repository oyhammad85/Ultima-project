package com.paxera.ultima.screen;

import org.openqa.selenium.By;

public class SettingsPage extends BaseScreen {

    private final By columnsSettings = By.id("v-pills-column-tab");
    private final By worklistSettings = By.id("v-pills-worklist-tab");
    private final By otherSettings = By.id("v-pills-other-tab");
    private final By viewerSettings = By.id("v-pills-ViewerSettings-tab");
    private final By cacheSettings = By.id("v-pills-caching-tab");
    private final By locationSettings = By.id("v-pills-location-tab");
    private final By customerSettings = By.id("v-pills-customer-tab");
    private final By saveButton = By.id("ctl00_ContentPlaceHolder1_btn_Save");
    private final By cancelButton = By.id("ctl00_ContentPlaceHolder1_btn_cancel");

    public SettingsPage clickOnColumnsSettingsButton() {
        waitUtils.waitUntilElementUntilIsClickable(columnsSettings).click();
        return this;
    }

    public SettingsPage clickOnWorklistSettingsButton() {
        waitUtils.waitUntilElementUntilIsClickable(worklistSettings).click();
        return this;
    }

    public SettingsPage clickOnOtherSettingsButton() {
        waitUtils.waitUntilElementUntilIsClickable(otherSettings).click();
        return this;
    }

    public SettingsPage clickOnViewerSettingsButton() {
        waitUtils.waitUntilElementUntilIsClickable(viewerSettings).click();
        return this;
    }

    public SettingsPage clickOnCacheSettingsButton() {
        waitUtils.waitUntilElementUntilIsClickable(cacheSettings).click();
        return this;
    }

    public SettingsPage clickOnCustomerSettingsButton() {
        waitUtils.waitUntilElementUntilIsClickable(customerSettings).click();
        return this;
    }

    public SettingsPage clickOnLocationSettingsButton() {
        waitUtils.waitUntilElementUntilIsClickable(locationSettings).click();
        return this;
    }

    public SettingsPage clickOnSaveButton() {
        waitUtils.waitUntilElementUntilIsClickable(saveButton).click();
        return this;
    }

    public SettingsPage clickOnCancelButton() {
        waitUtils.waitUntilElementUntilIsClickable(saveButton).click();
        return this;
    }


}

