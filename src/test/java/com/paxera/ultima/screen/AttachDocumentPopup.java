package com.paxera.ultima.screen;

import org.openqa.selenium.By;

public class AttachDocumentPopup extends BaseScreen {

    private final By attachNameTextField = By.id("txtImageName");
    private final By saveButton = By.id("saveAndClose");
    private final By closeButton = By.id("btn_CloseSaveDoc");

    public void getAttachName() {
        waitUtils.waitUntilElementUntilIsVisible(attachNameTextField).getText();
    }

    public void clickOnSaveButton() throws InterruptedException {
        waitUtils.waitUntilElementUntilIsVisible(saveButton).click();
        waitUtils.sleep(3000);
    }

    public void clickOnCloseButton() {
        waitUtils.waitUntilElementUntilIsVisible(closeButton).click();
    }
}
