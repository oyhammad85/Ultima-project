package com.paxera.ultima.screen;

import com.paxera.ultima.driver.DriverManager;
import org.openqa.selenium.By;

public class EditStudyInformationPage extends BaseScreen {
    private final By firstNameTextField = By.id("FirstName_txt");
    private final By middleNameTextField = By.id("MiddleName_txt");
    private final By lastnameTextField = By.id("LastName_txt");
    private final By saveButton = By.id("save_btn");
    private final By frameId = By.id("frm");
    private final By studyDatePickerTextField = By.xpath("//*[@data-toggle='datetimepicker']");
    private final By studyDateTodayOption = By.xpath("//td[@class='day today']");
    private final By otherStudyDateOption = By.xpath("//td[@class='day'and text()='1']");

    public EditStudyInformationPage switchToEditStudyInformationScreen() {
        switchToIframe(frameId);
        return this;
    }

    public void switchToWorkList() {
        switchToDefaultScreen();
    }

    public String getFirstName() {
        waitUtils.waitUntilElementUntilIsVisible(firstNameTextField).click();
        return waitUtils.waitUntilElementIsPresence(firstNameTextField).getAttribute("value");
    }

    public EditStudyInformationPage enterFirstName(String firstName) {
        waitUtils.waitUntilElementUntilIsVisible(firstNameTextField).clear();
        waitUtils.waitUntilElementUntilIsVisible(firstNameTextField).sendKeys(firstName);
        return this;
    }

    public EditStudyInformationPage enterMiddleName(String middleName) {
        waitUtils.waitUntilElementIsPresence(middleNameTextField).sendKeys(middleName);
        return this;
    }

    public EditStudyInformationPage enterLastName(String lastName) {
        waitUtils.waitUntilElementIsPresence(lastnameTextField).sendKeys(lastName);
        return this;
    }

    public EditStudyInformationPage clickOnSaveButton() {
        waitUtils.waitUntilElementIsPresence(saveButton).click();
        return this;
    }

    public EditStudyInformationPage clickOnStudyDatePicker() {
        waitUtils.waitUntilElementIsPresence(studyDatePickerTextField).click();
        return this;
    }

    public EditStudyInformationPage selectStudyDateToday() {
        waitUtils.waitUntilElementIsPresence(studyDateTodayOption).click();
        return this;
    }

    public EditStudyInformationPage selectOtherStudyDate() {
        waitUtils.waitUntilElementIsPresence(otherStudyDateOption).click();
        return this;
    }

    public EditStudyInformationPage selectStudyDate(String day) {
        DriverManager.getWebDriver().findElement(By.xpath("//td[@class='day'and text()='" + day + "']")).click();
        return this;
    }

}


