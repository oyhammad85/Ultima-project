package com.paxera.ultima.screen;

import org.openqa.selenium.By;

public class SuccessPopup extends BaseScreen {

    private final By okButton = By.xpath("//button[@class='btn btn-orange']");
    private final By successPopupTitle = By.xpath("//h5[@class='modal-title']");
    private final By successPopupMessage = By.xpath("//*[@class='bootbox-body']");

    public String getSuccessPopupTitle() {
        return waitUtils.waitUntilElementUntilIsVisible(successPopupTitle).getText();
    }

    public String getSuccessPopupMessage() throws InterruptedException {
        Thread.sleep(1000);
        return waitUtils.waitUntilElementIsPresence(successPopupMessage).getText();
    }

    public SuccessPopup clickOnOkButton() {
        waitUtils.waitUntilElementUntilIsVisible(okButton).click();
        return this;
    }

}
