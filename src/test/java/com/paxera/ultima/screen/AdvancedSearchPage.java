package com.paxera.ultima.screen;

import com.paxera.ultima.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AdvancedSearchPage extends BaseScreen {
    private final By searchButton = By.id("ctl00_ContentPlaceHolder1_AdvancedSearch1_btn_Search");
    private final By viewedLabel = By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_AdvancedSearch1_lbl_Viewed\"]");
    private final By viewedStatusDropdown = By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_AdvancedSearch1_ddl_Viewed\"]");
    private final By dictatedLabel = By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_AdvancedSearch1_lbl_Dictated\"]");
    private final By dictatedStatusDropdown = By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_AdvancedSearch1_ddl_Dictated\"]");

    public AdvancedSearchPage clickOnSearchButton() throws InterruptedException {
        waitUtils.waitUntilElementIsPresence(searchButton).click();
        return this;
    }

    public AdvancedSearchPage selectViewedStatusItems(int value, String displayValue) {
        DriverManager.getWebDriver().findElement(viewedLabel);
        WebElement viewedStatusList = DriverManager.getWebDriver().findElement(viewedStatusDropdown);
        Select select = new Select(viewedStatusList);
        select.selectByIndex(value);
        return this;
    }

    public AdvancedSearchPage selectDictatedStatusItems(int index, String displayValue) {
        DriverManager.getWebDriver().findElement(dictatedLabel);
        WebElement dictatedStatusList = DriverManager.getWebDriver().findElement(dictatedStatusDropdown);
        Select select = new Select(dictatedStatusList);
        select.selectByIndex(index);
        return this;
    }
}
