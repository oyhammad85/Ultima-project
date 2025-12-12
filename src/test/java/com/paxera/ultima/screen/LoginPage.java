package com.paxera.ultima.screen;

import org.openqa.selenium.By;

public class LoginPage extends BaseScreen {
    private final By userNameText = By.id("txt_UserName");
    private final By passwordText = By.id("txt_Password");
    private final By submitButton = By.id("btn_Login");
    private final By LoginErrorMessage = By.id("lbl_Error");

    public LoginPage enterUsername(String username) {
        waitUtils.waitUntilElementIsPresence(userNameText).clear();
        waitUtils.waitUntilElementIsPresence(userNameText).sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        waitUtils.waitUntilElementIsPresence(passwordText).clear();
        waitUtils.waitUntilElementIsPresence(passwordText).sendKeys(password);
        return this;
    }

    public String getUrl() {
        return getCurrentUrl();
    }

    public String getLoginErrorMessage() {
        return waitUtils.waitUntilElementIsPresence(LoginErrorMessage).getText();
    }

    public void clickOnSubmitButton() {
        waitUtils.waitUntilElementUntilIsClickable(submitButton).click();
    }

    public By getUserNameInput() {
        return userNameText;
    }

    public By getPasswordInput() {
        return passwordText;
    }

    public By getLoginButton() {
        return submitButton;
    }
}
