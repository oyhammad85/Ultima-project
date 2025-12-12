package com.paxera.ultima.screen;

import org.openqa.selenium.By;

public class CustomerSettingsPage extends BaseScreen {

    private final By columnsSettingScreenTitle = By.id("ctl00_ContentPlaceHolder1_Cust_Settings_lbl");
    private final By columnsSettingSectionTitle = By.id("ctl00_ContentPlaceHolder1_Label1");
    private final By chatSettingsDropDownName = By.id("ctl00_ContentPlaceHolder1_Label2");
    private final By chatSettingsDropDown = By.id("ctl00_ContentPlaceHolder1_drpChatSettings");

    public CustomerSettingsPage getCustomerSettingsScreenTitle() {
        waitUtils.waitUntilElementUntilIsClickable(columnsSettingScreenTitle).getText();
        return this;
    }

    public CustomerSettingsPage getCustomerSettingsSectionTitle() {
        waitUtils.waitUntilElementUntilIsClickable(columnsSettingSectionTitle).getText();
        return this;
    }

    public CustomerSettingsPage getChatSettingsDropDownName() {
        waitUtils.waitUntilElementUntilIsClickable(chatSettingsDropDownName).getText();
        return this;
    }

    public CustomerSettingsPage assertOnSelectedOption(String option) {
        assertOnSelectedItem(chatSettingsDropDown, option);
        return this;
    }

    public CustomerSettingsPage selectFromChatSettingsScreen(String option) {
        selectFromDropdownList(chatSettingsDropDown, option);
        return this;
    }


}

