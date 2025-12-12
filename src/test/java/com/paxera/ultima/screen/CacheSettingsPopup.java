package com.paxera.ultima.screen;

import org.openqa.selenium.By;

public class CacheSettingsPopup extends BaseScreen {

    private final By okButton = By.xpath("//button[@class='btn btn-orange']");
    private final By popupTitle = By.xpath("//h5[@class='modal-title']");
    private final By popupMessage = By.xpath("//*[@class='bootbox-body']");

    public String getPopupTitle() {
        return waitUtils.waitUntilElementUntilIsVisible(popupTitle).getText();
    }

    public String getPopupMessage() throws InterruptedException {
        Thread.sleep(1000);
        return waitUtils.waitUntilElementIsPresence(popupMessage).getText();
    }

    public CacheSettingsPopup clickOnOkButton() {
        waitUtils.waitUntilElementUntilIsVisible(okButton).click();
        return this;
    }

}
