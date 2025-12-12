package com.paxera.ultima.screen;

import com.paxera.ultima.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class URLSharePage extends BaseScreen {

    private final By addNewButton = By.xpath("/html/body/div/div[3]/div[2]/a");
    private final By saveAddNewUrlShare = By.className("btn-orange");
    private final By successMessage = By.cssSelector(".custom-label-control.text-success.text-center.w-100.success.inter-font");

    public URLSharePage clickOnAddNewURLShareButton() {
        waitUtils.waitUntilElementUntilIsClickable(addNewButton).click();
        return this;
    }

    public URLSharePage switchToShareFrame() throws InterruptedException {
        DriverManager.getWebDriver().switchTo().frame("ShareFrame");
        return this;
    }

    public URLSharePage clickOnSaveAddNewURLShareButton() {
        WebElement SaveAddNewUrl = waitUtils.waitUntilElementIsPresence(saveAddNewUrlShare);
        ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", SaveAddNewUrl);
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(10));
        SaveAddNewUrl = wait.until(ExpectedConditions.elementToBeClickable(saveAddNewUrlShare));

        try {
            SaveAddNewUrl.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("arguments[0].click();", SaveAddNewUrl);
        }
        return this;
    }

    public String getURLcreatedSuccessfullMessage() {
        return waitUtils.waitUntilElementIsPresence(successMessage).getText();
    }
}

