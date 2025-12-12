package com.paxera.ultima.screen;

import com.paxera.ultima.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class FiltersPopup extends BaseScreen {

    private final By filterTextField = By.id("txt_FilterName");
    private final By addFilterButton = By.id("DivAddFilter");

    private String getFilterNameXpath(String filterName) {
        return "//ul[@id='FiltersList']//*[contains(text(),'" + filterName + "')]";
    }

    private String getSaveFilterXpath(String filterName) {
        return "//*[contains(text(),'" + filterName + "')]/following-sibling::*[@class='filters-actions']/i[1]";
    }

    private String getMarkFilterXpath(String filterName) {
        return "//*[contains(text(),'" + filterName + "')]/following-sibling::*[@class='filters-actions']/i[2]";
    }

    private String getDeleteFilterXpath(String filterName) {
        return "//*[contains(text(),'" + filterName + "')]/following-sibling::*[@class='filters-actions']/i[3]";
    }

    private String getFilterListItemsXpath(String filterName) {
        return "//*[@class='filter-list']//*[contains(text(),'" + filterName + "')]";
    }

    public String getFilterName(String filterName) {
        return DriverManager.getWebDriver().findElement(By.xpath(getFilterNameXpath(filterName))).getAttribute("title");

    }

    public String getFilterListItemsName(String filterName) {
        WebElement filterListItemName = DriverManager.getWebDriver().findElement(By.xpath(getFilterListItemsXpath(filterName)));
        return getElementTextUsingJS(filterListItemName);
    }

    public FiltersPopup enterFilterName(String filterName) {
        waitUtils.waitUntilElementUntilIsClickable(filterTextField).sendKeys(filterName);
        return this;
    }

    public FiltersPopup clickOnAddFilterButton() {
        waitUtils.waitUntilElementUntilIsClickable(addFilterButton).click();
        return this;
    }

    public FiltersPopup clickOnAddedFilter(String filterName) throws InterruptedException {
        Thread.sleep(2000);
        waitUtils.waitUntilElementIsPresence(By.xpath(getFilterNameXpath(filterName))).click();
        return this;
    }

    public void clickOnSaveFilterIcon(String filterName) {
        moveToElement(By.xpath(getFilterNameXpath(filterName)));
        waitUtils.waitUntilElementUntilIsClickable(By.xpath(getSaveFilterXpath(filterName))).click();
    }

    public void clickOnMarkFilterIcon(String filterName) {
        moveToElement(By.xpath(getFilterNameXpath(filterName)));
        waitUtils.waitUntilElementUntilIsClickable(By.xpath(getMarkFilterXpath(filterName))).click();
    }

    public void clickOnDeleteFilterIcon(String filterName) {
        moveToElement(By.xpath(getFilterNameXpath(filterName)));
        waitUtils.waitUntilElementUntilIsVisible(By.xpath(getDeleteFilterXpath(filterName))).click();
    }
}

