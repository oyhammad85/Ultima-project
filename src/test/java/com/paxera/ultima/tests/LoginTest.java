package com.paxera.ultima.tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import com.paxera.ultima.utils.RetryAnalyzer;

import java.io.IOException;

import static com.paxera.ultima.utils.read_properties.ReadProperties.setPaxerahealthCorporationConfig;
import static org.testng.Assert.assertEquals;

@Epic("Authentication")
@Feature("Login")
public class LoginTest extends BaseTest {
    String userName = setPaxerahealthCorporationConfig().getProperty("userName");
    String password = setPaxerahealthCorporationConfig().getProperty("password");
    String invalidUsername = setPaxerahealthCorporationConfig().getProperty("inValidUsername");
    String invalidPassword = setPaxerahealthCorporationConfig().getProperty("inValidPassword");
    String url = setPaxerahealthCorporationConfig().getProperty("URL");

    public LoginTest() throws IOException {
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify login with valid username and valid password")
    @Story("Valid Login")
    public void loginTestCaseValidUserNameAndValidPassword() {
        Allure.step("Enter valid credentials and submit");
        browser.ultimaViewer.loginPage.enterUsername(userName)
                .enterPassword(password)
                .clickOnSubmitButton();
        assertEquals(browser.ultimaViewer.loginPage.getUrl(), url + "PluginConfig.aspx");

        Allure.step("Configure plugin and navigate to worklist");
        browser.ultimaViewer.ultimaPluginPage.checkRememberCheckBox()
                .clickOnGoToWorkListButton();
        assertEquals(browser.ultimaViewer.loginPage.getUrl(), url + "PXStudyList.aspx");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify login with invalid username and invalid password")
    @Story("Invalid Login")
    public void loginTestCaseInvalidUsernameAndInValidPassword() {
        Allure.step("Enter invalid credentials and submit");
        browser.ultimaViewer.loginPage.enterUsername(invalidUsername)
                .enterPassword(invalidPassword)
                .clickOnSubmitButton();

        Allure.step("Verify error message");
        assertEquals(browser.ultimaViewer.loginPage.getLoginErrorMessage(), "Invalid username or password.");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify login with valid password but invalid username")
    @Story("Invalid Login")
    public void loginTestCaseValidPasswordAndInvalidUsername() {
        Allure.step("Enter invalid username with valid password and submit");
        browser.ultimaViewer.loginPage.enterUsername(invalidUsername)
                .enterPassword(password)
                .clickOnSubmitButton();

        Allure.step("Verify error message");
        assertEquals(browser.ultimaViewer.loginPage.getLoginErrorMessage(), "Invalid username or password.");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify login with valid username but invalid password")
    @Story("Invalid Login")
    public void loginTestCaseValidUsernameAndInvalidPassword() {
        Allure.step("Enter valid username with invalid password and submit");
        browser.ultimaViewer.loginPage.enterUsername(userName)
                .enterPassword(invalidPassword)
                .clickOnSubmitButton();

        Allure.step("Verify error message");
        assertEquals(browser.ultimaViewer.loginPage.getLoginErrorMessage(), "Invalid username or password.");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("verify sign out after login with valid username and valid password")
    @Story("Verify Sign Out After Login")
    public void signOutAfterLogin() throws InterruptedException {
        Allure.step("Enter valid username with valid password and submit");
        browser.ultimaViewer.loginPage.enterUsername(userName)
                .enterPassword(password)
                .clickOnSubmitButton();
        browser.ultimaViewer.ultimaPluginPage.checkRememberCheckBox()
                .clickOnGoToWorkListButton();
        browser.ultimaViewer.workListPage.clickOnProfileIconButton();
        browser.ultimaViewer.profileMenuPage.clickOnSignOutButton();
        assertEquals(browser.ultimaViewer.loginPage.getUrl(), url + "Login.aspx");
    }
}