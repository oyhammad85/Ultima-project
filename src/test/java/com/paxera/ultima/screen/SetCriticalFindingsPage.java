package com.paxera.ultima.screen;

import com.paxera.ultima.driver.DriverManager;
import org.openqa.selenium.By;

public class SetCriticalFindingsPage extends BaseScreen {
    private final By findingsDropDownList = By.id("ddl_Findings");
    private final By submitButton = By.id("btn_Submit");
    private final By refPhysicianRadioButton = By.id("rd_RefUser");
    private final By refPhysicianDropDownList = By.id("ddl_Refs");

    public SetCriticalFindingsPage clickOnFindingsDropDownList() {
        waitUtils.waitUntilElementIsPresence(findingsDropDownList).click();
        return this;
    }

    public SetCriticalFindingsPage selectFinding(String finding) {
        String findingOption = "//option[text()='" + finding + "']";
        DriverManager.getWebDriver().findElement(By.xpath(findingOption)).click();
        return this;
    }

    public SetCriticalFindingsPage clickOnSubmitButton()   throws InterruptedException{
        waitUtils.waitUntilElementIsPresence(submitButton).click();
        Thread.sleep(30000);
        return this;
    }

    public SetCriticalFindingsPage clickOnRefPhysicianDropDownList() {
        waitUtils.waitUntilElementIsPresence(refPhysicianDropDownList).click();
        return this;
    }

    public SetCriticalFindingsPage clickOnRefPhysicianRadioButton() {
        waitUtils.waitUntilElementIsPresence(refPhysicianRadioButton).click();
        return this;
    }

    public SetCriticalFindingsPage selectRefPhysician(String ref) {
        String refOption = "//option[text()='" + ref + "']";
        DriverManager.getWebDriver().findElement(By.xpath(refOption)).click();
        return this;
    }

    public String getNotificationHistoryText() {
        return DriverManager.getWebDriver().findElement(By.id("notification-history-accordion")).getText();
    }
}
