package com.paxera.ultima.screen;

import org.openqa.selenium.By;

public class ProfileMenuPage extends BaseScreen {

    private final By settingsButton = By.id("ctl00_li_settings");
    private final By signOutButton = By.id("ctl00_lnk_SignOut");

    public ProfileMenuPage clickOnSettingsButton() {
        waitUtils.waitUntilElementUntilIsClickable(settingsButton).click();
        return this;
    }

    public ProfileMenuPage clickOnSignOutButton() {
        waitUtils.waitUntilElementUntilIsClickable(signOutButton).click();
        return this;
    }


}

