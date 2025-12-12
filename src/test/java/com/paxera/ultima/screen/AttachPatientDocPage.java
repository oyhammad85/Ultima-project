package com.paxera.ultima.screen;

import com.paxera.ultima.driver.DriverManager;
import org.openqa.selenium.By;

import java.awt.*;

public class AttachPatientDocPage extends BaseScreen {

    private final By patientNameLabel = By.id("lbl_PatientNameValue");
    private final By studyIdLabel = By.id("lbl_StudyId_Value");
    private final By procedureNameLabel = By.id("lbl_ProcNameValue");
    private final By studyDescriptionLabel = By.id("lbl_StudyDescription_Value");
    private final By frameId = By.id("frm");
    private final By uploadedDoc = By.id("Repeater_DocList_ctl01_lbl_Img_Desc");
    private final By uploadButton = By.xpath("//*[@class='upload-click']");
    private final By closeButton = By.id("btn_Close");
    private final By imageName = By.xpath("//*[@id=\"Repeater_DocList_ctl01_Img_id\"]");

    public AttachPatientDocPage switchToAttachPatientDocumentScreen() {
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

    public String getUploadedFileName() throws InterruptedException {
        waitUtils.sleep(3000);
        return waitUtils.waitUntilElementUntilIsVisible(uploadedDoc).getText();
    }

    public void clickOnCloseButton() throws InterruptedException{

        waitUtils.waitUntilElementIsPresence(closeButton).click();
        waitUtils.sleep(3000);
    }

    public void clickOnUploadButton(String filePath) throws InterruptedException, AWTException {
        uploadFile(uploadButton, filePath);
        waitUtils.sleep(3000);
    }

    public String getImageName() {
        String imageNameText = DriverManager.getWebDriver().findElement(imageName).getAttribute("data-image-des");
        System.out.println("Image Name: " + imageNameText);
        return  waitUtils.waitUntilElementUntilIsVisible(imageName).getAttribute("data-image-des");

    }
}