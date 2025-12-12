package com.paxera.ultima.screen;

import com.paxera.ultima.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public class ShareWithPatientPage extends BaseScreen {

    private final By shareWithPatientFrame = By.id("ShareFrame");
    private final By copyURLLink = By.id("CopyLink");

    private static String getClipboardData() throws IOException, UnsupportedFlavorException {
        return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
    }

    public ShareWithPatientPage clickOnACopyURLLink() throws IOException, UnsupportedFlavorException {
        waitUtils.waitUntilElementUntilIsClickable(copyURLLink).click();
        return this;
    }

    public ShareWithPatientPage switchToShareWithPatientFrame() throws InterruptedException {
        DriverManager.getWebDriver().switchTo().frame("ShareFrame");
        return this;
    }

    public ShareWithPatientPage switchANewTabToShareWithPatientFrame() throws InterruptedException, IOException, UnsupportedFlavorException {

        ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(DriverManager.getWebDriver().getWindowHandles());
        DriverManager.getWebDriver().switchTo().window(tabs.get(1));
        DriverManager.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return this;
    }

    public String getContainURL() throws IOException, UnsupportedFlavorException {
        Actions actions = new Actions(DriverManager.getWebDriver());
        actions.sendKeys(Keys.chord(Keys.CONTROL, "v")).build().perform();
        String clipboardText = getClipboardData();
        System.out.println("Text after paste: " + clipboardText);
        return clipboardText;
    }
}