package com.paxera.ultima.screen;

import org.openqa.selenium.By;

public class ErrorPopup extends BaseScreen {

    private final By okButton = By.xpath("//button[@class='btn btn-orange']");
    private final By errorPopupTitle = By.xpath("//h5[@class='modal-title']");
    private final By errorPopupMessage = By.xpath("//*[@class='bootbox-body']");

    public String getErrorPopupTitle() {
        return waitUtils.waitUntilElementUntilIsVisible(errorPopupTitle).getText();
    }

    public String getErrorPopupMessage() throws InterruptedException {
        Thread.sleep(1000);
        return waitUtils.waitUntilElementIsPresence(errorPopupMessage).getText();
    }

    public void clickOnOkButton() {
        waitUtils.waitUntilElementUntilIsVisible(okButton).click();
    }
}
