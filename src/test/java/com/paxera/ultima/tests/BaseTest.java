package com.paxera.ultima.tests;

import com.paxera.ultima.browser.Browser;
import com.paxera.ultima.driver.Driver;
import com.paxera.ultima.utils.read_properties.ReadProperties;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;


public class BaseTest {
    protected static Browser browser;

    static {
        try {
            browser = new Browser();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedFlavorException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getBrowserName() throws IOException {
        return ReadProperties.setPaxerahealthCorporationConfig().getProperty("browserName");
    }

    @BeforeMethod(alwaysRun = true)
    protected void setUp() throws IOException {
        Driver.initDriver(getBrowserName());
        Driver.setBrowserResolution();
    }

    @AfterMethod(alwaysRun = true)
    protected void tearDown() {
        Driver.closeDriver();
    }

}





