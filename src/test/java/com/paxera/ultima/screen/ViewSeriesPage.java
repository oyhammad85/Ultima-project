package com.paxera.ultima.screen;

import com.paxera.ultima.driver.DriverManager;
import org.openqa.selenium.By;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ViewSeriesPage extends BaseScreen {
    private final By clickOnViewZFPLink = By.id("gv_Series_ctl02_ViewAsZfp_btn");

    public ViewSeriesPage switchToOpenSeriesFrame() throws InterruptedException {
        DriverManager.getWebDriver().switchTo().frame("frm");
        return this;
    }

    public ViewSeriesPage clickOnViewZFPLink() throws IOException, UnsupportedFlavorException {
        waitUtils.waitUntilElementUntilIsClickable(clickOnViewZFPLink).click();
        return this;
    }
}
