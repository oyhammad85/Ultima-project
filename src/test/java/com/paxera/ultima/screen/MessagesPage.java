package com.paxera.ultima.screen;

import com.paxera.ultima.driver.DriverManager;
import org.openqa.selenium.By;

public class MessagesPage extends BaseScreen {
    private final By messagesButton = By.id("msgImg");
    private final By messagesIcon = By.cssSelector("#msgImg img");
    private final By messagesLabel = By.id("lblMsges");
    private final By messagesTitleMobile = By.id("ctl00_sp_Messages_Title");
    private final By messageTable = By.className("messagesBody");
    private final By noMessagesText = By.cssSelector(".messagesBody #msg-body span");
    private final By viewAllMessagesLink = By.id("lnk_viewAllMsgs");
    private final By messageCountBadge = By.id("message_NO");
    private final By firstMessageItem = By.cssSelector("#msgs-div li:first-child");
    private final By modalTitle = By.cssSelector("#px_Popup_Master > div > div > div:first-child > h5");

    public void clickOnMessagesButton() throws InterruptedException {
        waitUtils.waitUntilElementIsPresence(messagesButton).click();
        Thread.sleep(3000);
    }

    public boolean isMessageTableDisplayed() {
        return waitUtils.waitUntilElementIsPresence(messageTable).isDisplayed();
    }

    public boolean isViewAllMessagesLinkDisplayed() {
        return waitUtils.waitUntilElementIsPresence(viewAllMessagesLink).isDisplayed();
    }

    public String getViewAllMessagesLinkText() {
        return waitUtils.waitUntilElementIsPresence(viewAllMessagesLink).getText();
    }

    public boolean verifyEmptyMessageTable() {
        return isMessageTableDisplayed() &&
                isViewAllMessagesLinkDisplayed() &&
                getViewAllMessagesLinkText().equals("View all messages");
    }

    public int getMessageCount() {

        waitUtils.waitUntilElementIsPresence(messageCountBadge);
        String messageCount = DriverManager.getWebDriver().findElement(messageCountBadge).getText();
        System.out.println("Message count: " + messageCount);
        return Integer.parseInt(messageCount);
    }

    public void selectFirstMessage() {
        waitUtils.waitUntilElementIsPresence(firstMessageItem).click();
    }

    public String getModalTitle() {
        String title =DriverManager.getWebDriver().findElement(modalTitle).getText();
        System.out.println("Message count: " + title);
        return waitUtils.waitUntilElementIsPresence(modalTitle).getText();
    }
}
