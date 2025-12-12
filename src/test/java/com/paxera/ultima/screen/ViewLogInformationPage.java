package com.paxera.ultima.screen;

import com.paxera.ultima.driver.DriverManager;
import org.openqa.selenium.By;

public class ViewLogInformationPage extends BaseScreen {
    private final By viewLogInformationComment = By.xpath("/html/body/div[1]/form/div[10]/div[6]/div/div/div[1]/h5");

    public ViewLogInformationPage switchToViewLogInformationFrame() throws InterruptedException {
        DriverManager.getWebDriver().switchTo().frame("frm");
        return this;
    }

    public String getTitleScreen() {
        return waitUtils.waitUntilElementIsPresence(viewLogInformationComment).getText();
    }
}
