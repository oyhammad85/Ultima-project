package com.paxera.ultima.screen;

import org.openqa.selenium.By;

public class ConfirmPopup extends BaseScreen {

    private final By yesButton = By.xpath("//button[@class='btn btn-orange']");
    private final By noButton = By.xpath("//button[@class='btn btn-white']");
    private final By popupTitle = By.xpath("//h5[@class='modal-title']");
    private final By popupMessage = By.xpath("//*[@class='bootbox-body']");

    public String getPopupTitle() {
        return waitUtils.waitUntilElementIsPresence(popupTitle).getText();
    }

    public String getPopupMessage() throws InterruptedException {
        Thread.sleep(1000);
        return waitUtils.waitUntilElementIsPresence(popupMessage).getText();
    }

    public void clickOnYesButton() throws InterruptedException {
        Thread.sleep(1000);
        waitUtils.waitUntilElementUntilIsVisible(yesButton).click();
    }

    public void clickOnNoButton() {
        waitUtils.waitUntilElementUntilIsVisible(noButton).click();
    }
}
