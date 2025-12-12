package com.paxera.ultima.screen;

import org.openqa.selenium.By;

public class WarningPopup extends BaseScreen {

    private final By okButton = By.xpath("//button[@class='btn btn-orange']");
    private final By warningPopupTitle = By.xpath("//h5[@class='modal-title']");
    private final By warningPopupMessage = By.xpath("//*[@class='bootbox-body']");

    public String getWarningPopupTitle() {
        return waitUtils.waitUntilElementUntilIsVisible(warningPopupTitle).getText();
    }

    public String getWarningPopupMessage() throws InterruptedException {
        Thread.sleep(1000);
        return waitUtils.waitUntilElementIsPresence(warningPopupMessage).getText();
    }

    public WarningPopup clickOnOkButton() {
        waitUtils.waitUntilElementUntilIsVisible(okButton).click();
        return this;
    }

}
