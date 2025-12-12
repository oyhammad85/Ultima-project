package com.paxera.ultima.screen;

import org.openqa.selenium.By;

public class MergeSplitStudyPage extends BaseScreen {
    private final By nextButton = By.xpath("//input[@value='Next']");
    private final By patientNameInput = By.id("wizHeader_txt_PatName");
    private final By searchButton = By.id("wizHeader_btn_Search1");
    private final By mergeStudyRadioButton = By.id("wizHeader_gv_Patients_ctl02_RadioButton1");
    private final By startMergeButton = By.xpath("//input[@value='Start Merge']");
    private final By finishButton = By.xpath("//input[@value='Finish']");
    private final By studyCheckBox = By.id("wizHeader_gv_Series_ctl02_chkRow");
    private final By splitStudyRadioButton = By.id("wizHeader_rdl_Choice_1");
    private final By startSplitButton = By.xpath("//input[@value='Start Split']");
    private final By patientIdInput = By.id("wizHeader_txt_PatID");

    public MergeSplitStudyPage clickOnNextButton() {
        waitUtils.waitUntilElementIsPresence(nextButton).click();
        return this;
    }

    public MergeSplitStudyPage enterPatientName(String patientName) throws InterruptedException {
        waitUtils.waitUntilElementIsPresence(patientNameInput).sendKeys(patientName);
        Thread.sleep(2000);
        return this;
    }

    public MergeSplitStudyPage enterPatientId(String patientID) throws InterruptedException {
        waitUtils.waitUntilElementIsPresence(patientIdInput).sendKeys(patientID);
        Thread.sleep(2000);
        return this;
    }

    public MergeSplitStudyPage clickOnSearchButton() {
        waitUtils.waitUntilElementIsPresence(searchButton).click();
        return this;
    }

    public MergeSplitStudyPage clickOnMergeStudyRadioButton() {
        waitUtils.waitUntilElementIsPresence(mergeStudyRadioButton).click();
        return this;
    }

    public MergeSplitStudyPage clickOnStartMergeButton() {
        waitUtils.waitUntilElementIsPresence(startMergeButton).click();
        return this;
    }

    public MergeSplitStudyPage clickOnFinishButton() {
        waitUtils.waitUntilElementIsPresence(finishButton).click();
        return this;
    }

    public MergeSplitStudyPage clickOnStudyCheckBox() {
        waitUtils.waitUntilElementIsPresence(studyCheckBox).click();
        return this;
    }

    public MergeSplitStudyPage clickOnSplitStudyRadioButton() {
        waitUtils.waitUntilElementIsPresence(splitStudyRadioButton).click();
        return this;
    }

    public MergeSplitStudyPage clickOnStartSplitButton() {
        waitUtils.waitUntilElementIsPresence(startSplitButton).click();
        return this;
    }
}
