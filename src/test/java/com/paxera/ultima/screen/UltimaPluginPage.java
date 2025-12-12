package com.paxera.ultima.screen;

import com.paxera.ultima.driver.DriverManager;
import org.openqa.selenium.By;

public class UltimaPluginPage extends BaseScreen {

    private final By rememberCheckBox = By.id("chk_remember");
    private final By gotoWorkListBtn = By.id("btnGotoWl");

    public UltimaPluginPage checkRememberCheckBox() {
        waitUtils.waitUntilElementUntilIsClickable(rememberCheckBox).click();
        return this;
    }

    public void clickOnGoToWorkListButton() {
        waitUtils.waitUntilElementUntilIsClickable(gotoWorkListBtn).click();
    }

    public String getUltimaPluginText() {
        String ultimaPluginText = DriverManager.getWebDriver().findElement(By.id("PluginHeader")).getText();
        return ultimaPluginText;
    }
}