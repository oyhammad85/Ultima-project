package com.paxera.ultima.screen;

import org.openqa.selenium.By;

public class CacheSettingsPage extends BaseScreen {

    private final By cacheSettingScreenTitle = By.id("ctl00_ContentPlaceHolder1_lbl_Caching_Studies");
    private final By cacheSettingSectionTitle = By.id("ctl00_ContentPlaceHolder1_lbl_CacheSettings");
    private final By cacheDirectoryTextField = By.id("ctl00_ContentPlaceHolder1_txt_Cache_Directory");
    private final By clearCachedStudyRadioButton = By.id("ctl00_ContentPlaceHolder1_chk_clearAfterClose");
    private final By cacheStudyRadioButton = By.id("ctl00_ContentPlaceHolder1_Chk_CacheOnCloseViewer");
    private final By noneRadioButton = By.id("ctl00_ContentPlaceHolder1_Chk_None");
    private final By autoClearCacheCheckBox = By.id("ctl00_ContentPlaceHolder1_chk_AutoClearCache");
    private final By autoClearCacheTextField = By.id("ctl00_ContentPlaceHolder1_txt_autoClearDays");
    private final By clearCacheManuallyDropdown = By.id("ctl00$ContentPlaceHolder1$ddl_Date");
    private final By clearNowButton = By.id("btn_ClearCash");

    public CacheSettingsPage getCacheSettingsScreenTitle() {
        waitUtils.waitUntilElementUntilIsClickable(cacheSettingScreenTitle).getText();
        return this;
    }

    public CacheSettingsPage getCacheSettingsSectionTitle() {
        waitUtils.waitUntilElementUntilIsClickable(cacheSettingSectionTitle).getText();
        return this;
    }

    public String getCacheDirectoryTextField() {
        String value = waitUtils.waitUntilElementUntilIsClickable(cacheDirectoryTextField).getAttribute("value");
        return value;
    }

    public String getAutoClearCacheTextField() {
        String value = waitUtils.waitUntilElementUntilIsClickable(autoClearCacheTextField).getAttribute("value");
        return value;
    }

    public CacheSettingsPage enterAutoClearCacheTextField(String duration) {
        waitUtils.waitUntilElementUntilIsClickable(autoClearCacheTextField).clear();
        waitUtils.waitUntilElementUntilIsClickable(autoClearCacheTextField).sendKeys(duration);
        return this;
    }


    public CacheSettingsPage clickOnClearCachedStudyRadioButton() {
        waitUtils.waitUntilElementUntilIsClickable(clearCachedStudyRadioButton).click();
        return this;
    }

    public CacheSettingsPage clickOnCacheStudyRadioButton() {
        waitUtils.waitUntilElementUntilIsClickable(cacheStudyRadioButton).click();
        return this;
    }

    public CacheSettingsPage clickOnNoneRadioButton() {
        waitUtils.waitUntilElementUntilIsClickable(noneRadioButton).click();
        return this;
    }

    public CacheSettingsPage clickOnAutoClearCacheCheckBox() {
        waitUtils.waitUntilElementUntilIsClickable(autoClearCacheCheckBox).click();
        return this;
    }

    public CacheSettingsPage clickOnClearNowButton() {
        waitUtils.waitUntilElementUntilIsClickable(clearNowButton).click();
        return this;
    }

    public CacheSettingsPage selectFromClearCacheManuallyDropDown(String option) {
        selectFromDropdownList(clearCacheManuallyDropdown, option);
        return this;
    }

    public CacheSettingsPage assertClearCachedStudyRadioButtonIsSelected(boolean status) {
        assertItemIsSelected(clearCachedStudyRadioButton, status);
        return this;
    }

    public CacheSettingsPage assertCachedStudyRadioButtonIsSelected(boolean status) {
        assertItemIsSelected(cacheStudyRadioButton, status);
        return this;
    }

    public CacheSettingsPage assertAutoClearCacheIsSelected(boolean status) {
        assertItemIsSelected(autoClearCacheCheckBox, status);
        return this;
    }


}

