package com.paxera.ultima.screen;

import com.paxera.ultima.driver.DriverManager;
import org.openqa.selenium.By;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ShareToCareRadPage extends BaseScreen {
    private final By userNameTxt = By.id("Username");
    private final By passwordTxt = By.id("Password");
    private final By submitButton = By.xpath("//*[@id=\"LoginForm\"]/div[2]/div/button");
    private final By nextButton = By.cssSelector("button.btn.btn-sm.btn-success.float-end");
    private final By selectCountaryName = By.xpath("//*[@id=\"iti-item-eg\"]/span[1]");
    private final By openlistCountaryName = By.className("iti__selected-flag");
    private final By searchButton = By.xpath("/html/body/div/div[2]/div[6]/div/div/div/form/div[2]/div/button");
    private final By shareButton = By.id("ShareBtn");

    public ShareToCareRadPage enterUserName(String UserName) {
        waitUtils.waitUntilElementIsPresence(userNameTxt).sendKeys(UserName);
        return this;
    }

    public ShareToCareRadPage enterPassword(String Password) {
        waitUtils.waitUntilElementIsPresence(passwordTxt).sendKeys(Password);
        return this;
    }

    public void clickOnLoginButton() {
        waitUtils.waitUntilElementUntilIsClickable(submitButton).click();
    }

    public By getUserNameInput() {
        return userNameTxt;
    }

    public By getPasswordInput() {
        return passwordTxt;
    }

    public By getLoginButton() {
        return submitButton;
    }

    public ShareToCareRadPage switchTCareRadFrame() throws InterruptedException {
        DriverManager.getWebDriver().switchTo().frame("ShareFrame");
        return this;
    }

    public ShareToCareRadPage clickOnNextButton() throws IOException {

        waitUtils.waitUntilElementUntilIsClickable(nextButton).click();
        return this;
    }

    public ShareToCareRadPage openListCountaryName() throws IOException, UnsupportedFlavorException {
        waitUtils.waitUntilElementUntilIsClickable(openlistCountaryName).click();
        return this;
    }

    public ShareToCareRadPage selectCountaryName() throws IOException, UnsupportedFlavorException {

        waitUtils.waitUntilElementUntilIsClickable(selectCountaryName).click();
        return this;
    }

    public ShareToCareRadPage clickOnSearchButton() throws IOException, UnsupportedFlavorException {

        waitUtils.waitUntilElementUntilIsClickable(searchButton).click();
        return this;
    }

    public ShareToCareRadPage clickOnShareButton() throws IOException, UnsupportedFlavorException {

        waitUtils.waitUntilElementUntilIsClickable(shareButton).click();
        return this;
    }
}

