package com.paxera.ultima.screen;

import org.openqa.selenium.By;

public class SendHL7Page extends BaseScreen {
    private final By frameId = By.id("Hl7-frm");
    private final By sendORMMessage = By.id("chkSendORMMessage");
    private final By sendButton = By.id("btnSendHl7");

    public SendHL7Page switchToSendHL7Screen() {
        switchToIframe(frameId);
        return this;
    }

    public SendHL7Page clickOnSendButton() {
        waitUtils.waitUntilElementIsPresence(sendButton).click();
        return this;
    }
}
