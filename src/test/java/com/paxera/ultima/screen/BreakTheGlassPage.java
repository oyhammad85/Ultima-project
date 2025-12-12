package com.paxera.ultima.screen;

import com.paxera.ultima.driver.DriverManager;
import org.openqa.selenium.By;

public class BreakTheGlassPage extends BaseScreen {
    private final By filterButton = By.id("tdFilters");
    private final By searchDateRangeOption = By.id("studyDateRange");
    private final By modalitiesDropDown = By.id("btn_Modalities");
    private final By allModalitiesOption = By.xpath("//*[@id='modalitiesDiv']/div[1]");
    private final By patientIdInput = By.id("ctl00_ContentPlaceHolder1_BasicSearch1_txt_PatientID");
    private final By searchButton = By.id("btnSearchWL");
    private final By studyIdInput = By.id("ctl00_ContentPlaceHolder1_txt_StudyID");
    private final By patientNameInput = By.id("ctl00_ContentPlaceHolder1_txt_PatientName");
    private final By AccNoInput = By.id("ctl00_ContentPlaceHolder1_txt_AccNumber");
    private final By studyActionsButton = By.xpath("//*[@class='action-icon more-actions']");
    private final By reportStatusDropDownList = By.xpath("//*[@id='searchCardBody']/div/div[1]/div[1]/div[2]/div/div[1]/span");
    private final By branchesDropDownList = By.xpath("//*[@id=\"searchCardBody\"]/div/div[1]/div[1]/div[2]/div/div[4]/span");
    private final By resetButton = By.id("btn_Reset");
    private final By customDateFromButton = By.xpath("//td[@data-title='r0c5'and text()='1']");
    private final By customDateToButton = By.xpath("//td[@data-title='r5c1'and text()='30']");
    private final By applyButton = By.xpath("//button[@type='button'and text()='Apply']");
    private final By workListActionsButton = By.xpath("//*[@id=\"main_nav\"]/ul/li/a");
    private final By reportIconButton = By.className("action-icon");
    private final By eyeIconButton = By.xpath("//*[@id=\"1\"]/td[3]/img[2]");

    public void clickOnFilterButton() {
        waitUtils.waitUntilElementIsPresence(filterButton).click();
    }

    public BreakTheGlassPage refreshScreen() {
        DriverManager.getWebDriver().navigate().refresh();
        return this;
    }

    public BreakTheGlassPage openSearchDateRange() {
        waitUtils.waitUntilElementIsPresence(searchDateRangeOption).click();
        return this;
    }

    public BreakTheGlassPage openModalitiesDropDown() {
        waitUtils.waitUntilElementIsPresence(modalitiesDropDown).click();
        return this;
    }

    public BreakTheGlassPage selectAllModalitiesOption() {
        waitUtils.waitUntilElementIsPresence(allModalitiesOption).click();
        return this;
    }

    public BreakTheGlassPage enterPatientId(String patientId) {
        waitUtils.waitUntilElementIsPresence(patientIdInput).sendKeys(patientId);
        return this;
    }

    public BreakTheGlassPage enterPatientName(String patientName) {
        waitUtils.waitUntilElementIsPresence(patientNameInput).sendKeys(patientName);
        return this;
    }

    public BreakTheGlassPage clickOnSearchButton() throws InterruptedException {
        waitUtils.waitUntilElementIsPresence(searchButton).click();
        Thread.sleep(2000);
        return this;
    }


    public BreakTheGlassPage enterStudyId(String studyId) {
        waitUtils.waitUntilElementIsPresence(studyIdInput).sendKeys(studyId);
        return this;
    }

    public BreakTheGlassPage enterAccNumber(String accNumber) {
        waitUtils.waitUntilElementIsPresence(AccNoInput).sendKeys(accNumber);
        return this;
    }

    public BreakTheGlassPage selectDateRange(String dateRange) {
        String dateRangeOption = "//li[text()='" + dateRange + "']";
        DriverManager.getWebDriver().findElement(By.xpath(dateRangeOption)).click();
        return this;
    }

    public String getWorkListTableText() throws InterruptedException {
        Thread.sleep(6000);
        return DriverManager.getWebDriver().findElement(By.xpath("//*[@id='list']/tbody/tr[2]")).getText();
    }

    public BreakTheGlassPage clickOnStudyActionButton() {
        waitUtils.waitUntilElementIsPresence(studyActionsButton).click();
        return this;
    }


    public void switchToEditStudyFrame() throws InterruptedException {
        DriverManager.getWebDriver().switchTo().frame("frm");
        Thread.sleep(2000);
    }

    public BreakTheGlassPage switchToDefaultFrame() {
        DriverManager.getWebDriver().switchTo().defaultContent();
        return this;
    }

    public BreakTheGlassPage clickOnReportStatusListButton() {
        waitUtils.waitUntilElementIsPresence(reportStatusDropDownList).click();
        return this;
    }

    public BreakTheGlassPage clickOnAllReportStatusOption(String reportStatus) {
        String dateRangeOption = "//ul[@x-placement='bottom-start']/li/a/label/input[@value='" + reportStatus + "']";
        DriverManager.getWebDriver().findElement(By.xpath(dateRangeOption)).click();
        return this;
    }

    public BreakTheGlassPage selectSpecificModality(String modality) {
        String ModalityOption = "//div[contains(text(),'" + modality + "')]";
        DriverManager.getWebDriver().findElement(By.xpath(ModalityOption)).click();
        return this;
    }

    public BreakTheGlassPage clickOnAllBranchesDropDownList() {
        waitUtils.waitUntilElementIsPresence(branchesDropDownList).click();
        return this;
    }

    public BreakTheGlassPage selectAllBranches(String branchValue) {
        String branch = "//*[@id='searchCardBody']/div/div[1]/div[1]/div[2]/div/div[4]/span/div/ul/li/a/label/input[@value='" + branchValue + "']";
        DriverManager.getWebDriver().findElement(By.xpath(branch)).click();
        return this;
    }

    public BreakTheGlassPage clickOnResetButton() {
        waitUtils.waitUntilElementIsPresence(resetButton).click();
        return this;
    }

    public String getSearchCriteriaContentMessageText() throws InterruptedException {
        Thread.sleep(3000);
        return DriverManager.getWebDriver().findElement(By.xpath("/html/body/div[7]/div/div/div[2]/div/div")).getText();
    }

    public BreakTheGlassPage selectFromDate() {
        waitUtils.waitUntilElementUntilIsVisible(customDateFromButton).click();
        return this;
    }

    public BreakTheGlassPage selectToDate() {
        waitUtils.waitUntilElementUntilIsVisible(customDateToButton).click();
        return this;
    }

    public BreakTheGlassPage clickOnApplyButton() {
        waitUtils.waitUntilElementUntilIsVisible(applyButton).click();
        return this;
    }

    public BreakTheGlassPage selectStudy(String rowNumber) {
        String rowNumberXpath = "//*[@id=\"jqg_list_" + rowNumber + "\"]";
        waitUtils.waitUntilElementUntilIsClickable(By.xpath(rowNumberXpath)).click();
        return this;
    }

    public BreakTheGlassPage clickOnWorkListActionsButton() {
        waitUtils.waitUntilElementIsPresence(workListActionsButton).click();
        return this;
    }

    public void clickOnReportIconButton() throws InterruptedException {
        waitUtils.waitUntilElementIsPresence(reportIconButton).click();
        Thread.sleep(2000);
    }

    public void clickOnEyeIconButton() throws InterruptedException {
        waitUtils.waitUntilElementIsPresence(eyeIconButton).click();
        Thread.sleep(2000);
    }
}
