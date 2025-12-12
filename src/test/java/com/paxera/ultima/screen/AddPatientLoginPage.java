package com.paxera.ultima.screen;

import com.paxera.ultima.driver.DriverManager;
import org.openqa.selenium.By;

public class AddPatientLoginPage extends BaseScreen {
    private final By saveButton = By.id("UserControl_PHR_Generate_btn_Save");
    private final By successMessage = By.id("UserControl_PHR_Generate_lbl_err");

    public AddPatientLoginPage switchToEditPatientLoginFrame() throws InterruptedException {
        DriverManager.getWebDriver().switchTo().frame("frm");
        return this;
    }

    public AddPatientLoginPage clickOnSaveLoginInformation() {
        waitUtils.waitUntilElementIsPresence(saveButton).click();
        return this;
    }

    public String getUpdateSuccessfullMessage() throws InterruptedException {
        Thread.sleep(5000);
        return waitUtils.waitUntilElementIsPresence(successMessage).getText();
    }

}
