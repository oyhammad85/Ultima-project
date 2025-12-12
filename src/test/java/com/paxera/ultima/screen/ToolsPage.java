package com.paxera.ultima.screen;

import org.openqa.selenium.By;

public class ToolsPage extends BaseScreen {

    private final By recycleBinButton = By.id("ctl00_Li_RecycleBin");
    private final By risButton = By.id("ctl00_lnk_OpenRIS");
    private final By administrationButton = By.id("ctl00_Li_Administration");
    private final By archiveManagerButton = By.id("ctl00_Li_ArchiveManager");
    private final By breakTheGlassButton = By.id("ctl00_Li_BreakTheGlass");

    public void clickOnRecycleBinButton() {
        waitUtils.waitUntilElementIsPresence(recycleBinButton).click();
    }

    public void clickOnRisButton() {
        waitUtils.waitUntilElementIsPresence(risButton).click();
    }

    public void clickOnAdministrationButton() {
        waitUtils.waitUntilElementIsPresence(administrationButton).click();
    }

    public void clickOnArchiveManagerButton() {
        waitUtils.waitUntilElementIsPresence(archiveManagerButton).click();
    }

    public void clickOnBreakTheGlassButton() {
        waitUtils.waitUntilElementIsPresence(breakTheGlassButton).click();
    }


}
