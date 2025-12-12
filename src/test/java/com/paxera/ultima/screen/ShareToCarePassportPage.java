package com.paxera.ultima.screen;

import com.paxera.ultima.driver.DriverManager;
import org.openqa.selenium.By;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ShareToCarePassportPage extends BaseScreen {

    private final By openListCountaryName = By.className("iti__selected-flag");
    private final By selectCountaryName = By.xpath("//*[@id=\"iti-item-eg\"]/span[1]");
    private final By searchButton = By.xpath("/html/body/div/div[2]/div[4]/div/div/div/form/div[2]/div/button");
    private final By shareButton = By.id("ShareBtn");
    private final By sharedSuccessfullyMesssage = By.xpath("//*[@id=\"container-result-card\"]/div/h5\n");

    public ShareToCarePassportPage OpenlistCountaryName() throws IOException, UnsupportedFlavorException {
        waitUtils.waitUntilElementUntilIsClickable(openListCountaryName).click();
        return this;
    }

    public ShareToCarePassportPage switchTCarePassportFrame() throws InterruptedException {
        DriverManager.getWebDriver().switchTo().frame("ShareFrame");
        return this;
    }

    public ShareToCarePassportPage selectCountaryName() throws IOException, UnsupportedFlavorException {
        waitUtils.waitUntilElementUntilIsClickable(selectCountaryName).click();
        return this;
    }

    public ShareToCarePassportPage ClickOnSearchButton() throws IOException, UnsupportedFlavorException {
        waitUtils.waitUntilElementUntilIsClickable(searchButton).click();
        return this;
    }

    public ShareToCarePassportPage ClickOnShareButton() throws IOException, UnsupportedFlavorException {
        waitUtils.waitUntilElementUntilIsClickable(shareButton).click();
        return this;
    }

    public String SharedSuccessfullyMesssage() {
        return waitUtils.waitUntilElementIsPresence(sharedSuccessfullyMesssage).getText();
    }
}