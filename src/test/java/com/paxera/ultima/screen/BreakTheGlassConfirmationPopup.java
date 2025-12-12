package com.paxera.ultima.screen;

import org.openqa.selenium.By;

public class BreakTheGlassConfirmationPopup extends BaseScreen {

    private final By confirmButton = By.id("ctl00_ContentPlaceHolder1_breakGlassConfirmBtn");
    private final By cancelButton = By.id("ctl00_ContentPlaceHolder1_breakGlassCancelBtn");
    private final By popupTitle = By.id("ctl00_ContentPlaceHolder1_H1");
    private final By popupMessage = By.id("ctl00_ContentPlaceHolder1_BreakTheGlasscConfirmLbl");

    public String getPopupTitle() {
        return waitUtils.waitUntilElementIsPresence(popupTitle).getText();
    }

    public String getPopupMessage() throws InterruptedException {
        Thread.sleep(1000);
        return waitUtils.waitUntilElementIsPresence(popupMessage).getText();
    }

    public void clickOnConfirmButton() throws InterruptedException {
        Thread.sleep(1000);
        waitUtils.waitUntilElementUntilIsVisible(confirmButton).click();
    }

    public void clickOnCancelButton() {
        waitUtils.waitUntilElementUntilIsVisible(cancelButton).click();
    }
}
