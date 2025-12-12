package com.paxera.ultima.screen;

import com.paxera.ultima.driver.DriverManager;
import com.paxera.ultima.waits.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

public class BaseScreen {
    Actions actions;
    WaitUtils waitUtils = new WaitUtils();
    JavascriptExecutor executor;

    protected String constructXpath(String xpath, String replacingStr) {
        return String.format(xpath, replacingStr);
    }

    protected WebElement getWebElement(By by) {
        return waitUtils.waitUntilElementUntilIsVisible(by);
    }

    public List<WebElement> getElements(By selector) {
        waitUtils.waitUntilElementUntilIsVisible(selector);
        return DriverManager.getWebDriver().findElements(selector);
    }

    public void scrollToElement(By selector) {
        WebElement element = getWebElement(selector);
        actions = new Actions(DriverManager.getWebDriver());
        ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void moveToElement(By selector) {
        WebElement element = getWebElement(selector);
        actions = new Actions(DriverManager.getWebDriver());
        actions.moveToElement(element).perform();
    }

    public boolean elementIsExisted(By by) {
        return !DriverManager.getWebDriver().findElements(by).isEmpty();
    }

    public String getElementText(By by) {
        return waitUtils.waitUntilElementIsPresence(by).getText();
    }

    public String getCurrentUrl() {
        return DriverManager.getWebDriver().getCurrentUrl();
    }

    public void clickOnElementUsingJS(WebElement link) {
        executor = (JavascriptExecutor) DriverManager.getWebDriver();
        executor.executeScript("arguments[0].click();", link);
    }

    public String getElementTextUsingJS(WebElement element) {
        executor = (JavascriptExecutor) DriverManager.getWebDriver();
        return (String) executor.executeScript("return arguments[0].innerText", element);
    }

    public void switchToIframe(By by) {
        WebElement iframe = waitUtils.waitUntilElementIsPresence(by);
        DriverManager.getWebDriver().switchTo().frame(iframe);
    }

    public void switchToDefaultScreen() {
        DriverManager.getWebDriver().switchTo().defaultContent();
    }

    public void selectFromWorkListActionsMenu(String menuName) {
        selectFromWorkListActionsMenu(menuName, null);
    }


    public void selectFromWorkListActionsMenu(String menuName, String subMenuName) {
        Actions actions = new Actions(DriverManager.getWebDriver());
        WebElement actionMenu = DriverManager.getWebDriver().findElement(By.id("ActionMenu"));
        WebElement menuElement = actionMenu.findElement(By.xpath(".//*[text()='" + menuName + "']"));
        actions.moveToElement(menuElement).perform(); // Hover over the main menu item
        if (subMenuName != null && !subMenuName.isEmpty()) {
            List<WebElement> subMenuElements = menuElement.findElements(By.xpath("//ul//*[text()='" + subMenuName + "']"));
            if (!subMenuElements.isEmpty()) {
                WebElement subMenuElement = subMenuElements.get(0);
                actions.moveToElement(subMenuElement).click().perform();
            } else {
                System.out.println("Sub-menu item '" + subMenuName + "' not found. Clicking on main menu instead.");
                menuElement.click();
            }
        } else {
            menuElement.click();
        }
    }

    public void selectFromStudyActionsMenu(String menuName) throws InterruptedException{
        selectFromStudyActionsMenu(menuName, null);
    }


    public void selectFromStudyActionsMenu(String menuName, String subMenuName)  throws InterruptedException{
        Actions actions = new Actions(DriverManager.getWebDriver());

        WebElement menuElement = waitUtils.waitUntilElementUntilIsClickable(By.xpath(".//*[text()='" + menuName + "']"));
        actions.moveToElement(menuElement).perform();
        if (subMenuName != null && !subMenuName.isEmpty()) {
            List<WebElement> subMenuElements = DriverManager.getWebDriver().findElements(By.xpath(".//*[text()='" + subMenuName + "']"));

            if (!subMenuElements.isEmpty()) {
                WebElement subMenuElement = subMenuElements.get(0);
                subMenuElement.click();
                Thread.sleep(3000);
            } else {
                System.out.println("Sub-menu item '" + subMenuName + "' not found.");
                menuElement.click();
            }
        } else {
            menuElement.click();
        }
    }

    public void uploadFile(By by, String filePath) throws AWTException {
        WebElement fileInput = waitUtils.waitUntilElementIsPresence(by);
        fileInput.click();
        Robot robot = new Robot();
        robot.setAutoDelay(2000);
        StringSelection filePathSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePathSelection, null);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public void selectFromDropdownList(By by, String value) {
        WebElement dropdownElement = waitUtils.waitUntilElementIsPresence(by);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(value);
    }

    public void assertOnSelectedItem(By by, String value) {
        WebElement dropdownElement = waitUtils.waitUntilElementIsPresence(by);
        Select dropdown = new Select(dropdownElement);
        WebElement selectedOption = dropdown.getFirstSelectedOption();
        String selectedValue = selectedOption.getText();
        Assert.assertEquals(selectedValue, value);
    }

    public void assertItemIsSelected(By by, boolean shouldBeChecked) {
        WebElement item = waitUtils.waitUntilElementIsPresence(by);
        boolean isChecked = item.isSelected();
        Assert.assertEquals(isChecked, shouldBeChecked);
    }

    public BaseScreen handlePluginPopup(String action) {
        try {
            Robot robot = new Robot();
            robot.delay(3000); // wait for the popup to appear

            if (action.equalsIgnoreCase("Open UltimaPlugin")) {
                robot.keyPress(KeyEvent.VK_TAB);   // Move to Cancel button
                robot.keyRelease(KeyEvent.VK_TAB);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
            } else if (action.equalsIgnoreCase("Cancel")) {
                robot.keyPress(KeyEvent.VK_TAB);   // Move to Cancel button
                robot.keyRelease(KeyEvent.VK_TAB);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
            } else {
                throw new IllegalArgumentException("Unsupported action: " + action);
            }
        } catch (Exception e) {
            e.printStackTrace(); // or better: throw a custom exception
        }

        return this;
    }
}


