package com.paxera.ultima.screen;

import org.openqa.selenium.By;

public class AttachInsuranceDocPage extends BaseScreen {
    private final By patientNameLabel = By.id("lbl_PatientNameValue");
    private final By studyIdLabel = By.id("lbl_StudyId_Value");
    private final By procedureNameLabel = By.id("lbl_ProcNameValue");
    private final By studyDescriptionLabel = By.id("lbl_StudyDescription_Value");
    private final By frameId = By.id("frm");
    private final By uploadButton = By.xpath("//*[@class='upload-click']");

    public AttachInsuranceDocPage switchToAttachPatientDocumentScreen() {
        switchToIframe(frameId);
        return this;
    }

    public void switchToWorkList() {
        switchToDefaultScreen();
    }

    public String getPatientName() {
        return waitUtils.waitUntilElementIsPresence(patientNameLabel).getText();
    }

    public String getStudyId() {
        return waitUtils.waitUntilElementIsPresence(studyIdLabel).getText();
    }

    public String getProcedureName() {
        return waitUtils.waitUntilElementIsPresence(procedureNameLabel).getText();
    }

    public String getStudyDescription() {
        return waitUtils.waitUntilElementIsPresence(studyDescriptionLabel).getText();
    }

    public void clickOnUploadButton() {
        waitUtils.waitUntilElementUntilIsVisible(uploadButton).click();
    }


}
