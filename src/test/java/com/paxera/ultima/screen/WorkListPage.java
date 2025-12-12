package com.paxera.ultima.screen;

import com.paxera.ultima.driver.DriverManager;
import org.openqa.selenium.By;

public class WorkListPage extends BaseScreen {
    private final By filterButton = By.id("btn_Filters");
    private final By searchDateRangeOption = By.id("studyDateRange");
    private final By modalitiesDropDown = By.id("btn_Modalities");
    private final By allModalitiesOption = By.xpath("//*[@id='modalitiesDiv']/div[1]");
    private final By patientIdInput = By.id("ctl00_ContentPlaceHolder1_BasicSearch1_txt_PatientID");
    private final By searchButton = By.id("btnSearchWL");
    private final By studyIdInput = By.id("ctl00_ContentPlaceHolder1_BasicSearch1_txt_StudyID");
    private final By patientNameInput = By.id("ctl00_ContentPlaceHolder1_BasicSearch1_txt_PatientName");
    private final By AccNoInput = By.id("ctl00_ContentPlaceHolder1_BasicSearch1_txt_AccNumber");
    private final By studyActionsButton = By.xpath("//*[@class='action-icon more-actions']");
    private final By reportStatusDropDownList = By.xpath("//*[@id=\"searchCardBody\"]/div/div[1]/div/div[6]/span/div/button/span");
    private final By branchesDropDownList = By.xpath("//*[@id=\"searchCardBody\"]/div/div[1]/div/div[9]/span/div/button");
    private final By resetButton = By.id("btn_Reset");
    private final By customDateFromButton = By.xpath("//td[@data-title='r0c5'and text()='1']");
    private final By customDateToButton = By.xpath("//td[@data-title='r5c1'and text()='30']");
    private final By applyButton = By.xpath("//button[@type='button'and text()='Apply']");
    private final By workListActionsButton = By.xpath("//*[@id=\"main_nav\"]/ul/li/a");
    private final By getStudyViewedStatusButton = By.cssSelector("img[alt='Open study']");
    private final By getStudyVIPStatusButton = By.cssSelector("img[alt='VIP']");
    private final By enterVIPActionReasonTextField = By.xpath("//*[@id=\"comment\"]");
    private final By saveVIPActionReasonButton = By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_VIPSaveButton\"]");
    private final By getStudyNotVIPStatus = By.xpath("//*[@aria-describedby=\"list_Status_VIP\"]");
    private final By getStudyDictatorStatus = By.xpath("//*[@aria-describedby=\"list_report_dictator\"]");
    private final By profileIcon = By.id("ctl00_span_FullName");
    private final By advancedSearchButton = By.id("btn_AdvancedSearch");
    private final By toolsIconButton = By.id("navbarTools");
    private final By ofPageText = By.id("sp_1_pager1");
    private final By leftCalendar = By.cssSelector(".drp-calendar.left");
    private final By rightCalendar = By.cssSelector(".drp-calendar.right");
    private final By leftMonthSelect = By.cssSelector(".drp-calendar.left .monthselect");
    private final By leftYearSelect = By.cssSelector(".drp-calendar.left .yearselect");
    private final By rightMonthSelect = By.cssSelector(".drp-calendar.right .monthselect");
    private final By rightYearSelect = By.cssSelector(".drp-calendar.right .yearselect");
    private final By tagsDropdown = By.xpath("//*[@id=\"searchCardBody\"]/div/div[1]/div/div[8]/span/div/button");
    private final By firstTagOption = By.xpath("//label[contains(text(),'Tag')]/input");

    public void clickOnFilterButton() {
        waitUtils.waitUntilElementIsPresence(filterButton).click();
    }

    public void clickOnToolsIconButton() {
        waitUtils.waitUntilElementIsPresence(toolsIconButton).click();
    }

    public WorkListPage refreshScreen() {
        DriverManager.getWebDriver().navigate().refresh();
        return this;
    }

    public WorkListPage openSearchDateRange() throws InterruptedException{
        waitUtils.waitUntilElementIsPresence(searchDateRangeOption).click();
        Thread.sleep(2000);
        return this;
    }

    public String getOfPageTextValue() throws InterruptedException {
        return waitUtils.waitUntilElementIsPresence(ofPageText).getText();
    }
    public WorkListPage openModalitiesDropDown() {
        waitUtils.waitUntilElementIsPresence(modalitiesDropDown).click();
        return this;
    }

    public WorkListPage selectAllModalitiesOption() {
        waitUtils.waitUntilElementIsPresence(allModalitiesOption).click();
        return this;
    }

    public WorkListPage enterPatientId(String patientId) {
        waitUtils.waitUntilElementIsPresence(patientIdInput).sendKeys(patientId);
        return this;
    }

    public WorkListPage enterPatientName(String patientName) {
        waitUtils.waitUntilElementIsPresence(patientNameInput).sendKeys(patientName);
        return this;
    }

    public WorkListPage clickOnSearchButton() throws InterruptedException {
        waitUtils.waitUntilElementIsPresence(searchButton).click();
        Thread.sleep(2000);
        return this;
    }

    public WorkListPage enterStudyId(String studyId) {
        waitUtils.waitUntilElementIsPresence(studyIdInput).sendKeys(studyId);
        return this;
    }

    public WorkListPage enterAccNumber(String accNumber) {
        waitUtils.waitUntilElementIsPresence(AccNoInput).sendKeys(accNumber);
        return this;
    }

    public WorkListPage selectDateRange(String dateRange) throws InterruptedException {
        Thread.sleep(2000);
        String dateRangeOption = "//li[text()='" + dateRange + "']";
        //*[@data-range-key="Any Date"]
        DriverManager.getWebDriver().findElement(By.xpath(dateRangeOption)).click();
        return this;
    }

    public String getWorkListTableText() throws InterruptedException {
        Thread.sleep(6000);
        return DriverManager.getWebDriver().findElement(By.xpath("//*[@id='list']/tbody/tr[2]")).getText();
    }

    public WorkListPage clickOnStudyActionButton() {
        waitUtils.waitUntilElementIsPresence(studyActionsButton).click();
        return this;
    }


    public void switchToEditStudyFrame() throws InterruptedException {
        DriverManager.getWebDriver().switchTo().frame("frm");
        Thread.sleep(2000);
    }

    public WorkListPage switchToDefaultFrame() {
        DriverManager.getWebDriver().switchTo().defaultContent();
        return this;
    }

    public WorkListPage clickOnReportStatusListButton() {
        waitUtils.waitUntilElementIsPresence(reportStatusDropDownList).click();
        return this;
    }

    public WorkListPage clickOnAllReportStatusOption(String reportStatus) {
        String dateRangeOption = "//ul[@x-placement='bottom-start']/li/a/label/input[@value='" + reportStatus + "']";
        DriverManager.getWebDriver().findElement(By.xpath(dateRangeOption)).click();
        return this;
    }

    public WorkListPage selectSpecificModality(String modality) {
        String ModalityOption = "//div[contains(text(),'" + modality + "')]";
        DriverManager.getWebDriver().findElement(By.xpath(ModalityOption)).click();
        return this;
    }

    public WorkListPage clickOnAllBranchesDropDownList() {
        waitUtils.waitUntilElementUntilIsClickable(branchesDropDownList).click();
        return this;
    }

    public WorkListPage selectAllBranches(String branchValue) {
        String branch = "//*[@id='searchCardBody']/div/div[1]/div/div[9]/span/div/ul/li[1]/a/label/input[@value='" + branchValue + "']";
        DriverManager.getWebDriver().findElement(By.xpath(branch)).click();
        return this;
    }

    public WorkListPage clickOnResetButton() {
        waitUtils.waitUntilElementIsPresence(resetButton).click();
        return this;
    }

    public String getSearchCriteriaContentMessageText() throws InterruptedException {
        Thread.sleep(3000);
        return DriverManager.getWebDriver().findElement(By.xpath("/html/body/div[7]/div/div/div[2]/div/div")).getText();
    }

    public WorkListPage selectFromDate() {
        waitUtils.waitUntilElementUntilIsVisible(customDateFromButton).click();
        return this;
    }

    public WorkListPage selectToDate() {
        waitUtils.waitUntilElementUntilIsVisible(customDateToButton).click();
        return this;
    }

    public WorkListPage clickOnApplyButton() {
        waitUtils.waitUntilElementUntilIsVisible(applyButton).click();
        return this;
    }

    public WorkListPage selectStudy(String rowNumber) {
        String rowNumberXpath = "//*[@id=\"jqg_list_" + rowNumber + "\"]";
        waitUtils.waitUntilElementUntilIsClickable(By.xpath(rowNumberXpath)).click();
        return this;
    }

    public WorkListPage clickOnWorkListActionsButtton() {
        waitUtils.waitUntilElementIsPresence(workListActionsButton).click();
        return this;
    }

    public void enterVIPComment(String message) {
        DriverManager.getWebDriver().findElement(enterVIPActionReasonTextField).sendKeys(message);
        DriverManager.getWebDriver().findElement(saveVIPActionReasonButton).click();
    }

    public String getStudyViewedStatus() {
        moveToElement(getStudyViewedStatusButton);
        waitUtils.waitUntilElementUntilIsVisible(getStudyViewedStatusButton);
        return DriverManager.getWebDriver().findElement(getStudyViewedStatusButton).getAttribute("id");
    }

    public String getStudyVIPStatus() {
        waitUtils.waitUntilElementUntilIsVisible(getStudyVIPStatusButton);
        return DriverManager.getWebDriver().findElement(getStudyVIPStatusButton).getAttribute("id");
    }

    public String getStudyNotVIPStatus() {
        waitUtils.waitUntilElementUntilIsVisible(getStudyNotVIPStatus);
        return DriverManager.getWebDriver().findElement(getStudyNotVIPStatus).getAttribute("role");
    }

    public void clickOnAdvancedSearchButton() throws InterruptedException {
        waitUtils.waitUntilElementIsPresence(advancedSearchButton).click();
        Thread.sleep(4000);
    }

    public String getStudyDictated() {
        waitUtils.waitUntilElementUntilIsVisible(getStudyDictatorStatus);
        return DriverManager.getWebDriver().findElement(getStudyDictatorStatus).getText();
    }

    public void clickOnProfileIconButton() throws InterruptedException {
        waitUtils.waitUntilElementIsPresence(profileIcon).click();
    }

    public WorkListPage selectFromDate(String month, String year, String day) {
        waitUtils.waitUntilElementIsPresence(leftCalendar);
        DriverManager.getWebDriver().findElement(leftMonthSelect).click();
        DriverManager.getWebDriver().findElement(By.xpath("//select[@class='monthselect']/option[text()='" + month + "']")).click();
        DriverManager.getWebDriver().findElement(leftYearSelect).click();
        DriverManager.getWebDriver().findElement(By.xpath("//select[@class='yearselect']/option[text()='" + year + "']")).click();
        String dayXPath = String.format("//div[contains(@class,'left')]//td[not(contains(@class,'off')) and text()='%s']", day);
        DriverManager.getWebDriver().findElement(By.xpath(dayXPath)).click();
        return this;
    }

    public WorkListPage selectToDate(String month, String year, String day) {
        waitUtils.waitUntilElementIsPresence(rightCalendar);
        DriverManager.getWebDriver().findElement(rightMonthSelect).click();
        DriverManager.getWebDriver().findElement(By.xpath("//select[@class='monthselect']/option[text()='" + month + "']")).click();
        DriverManager.getWebDriver().findElement(rightYearSelect).click();
        DriverManager.getWebDriver().findElement(By.xpath("//select[@class='yearselect']/option[text()='" + year + "']")).click();
        String dayXPath = String.format("//div[contains(@class,'right')]//td[not(contains(@class,'off')) and text()='%s']", day);
        DriverManager.getWebDriver().findElement(By.xpath(dayXPath)).click();
        return this;
    }

    public WorkListPage selectTagsDropdown() throws InterruptedException{
        DriverManager.getWebDriver().navigate().refresh();
        Thread.sleep(4000);
        waitUtils.waitUntilElementIsPresence(tagsDropdown).click();
        return this;
    }

    public WorkListPage selectFirstTagOption() {
        waitUtils.waitUntilElementIsPresence(firstTagOption).click();
        return this;
    }
}
