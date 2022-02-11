package pageObjects.onboardingPages;

import com.paulhammant.ngwebdriver.ByAngularButtonText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import reusableComponents.ExplicitWaits;
import reusableComponents.Log;
import testBase.TestBase;

import java.util.List;

public class StartupSolutionsPage extends TestBase {

    @FindBy(xpath = "//input[@id='title']")
    WebElement enterSolutionTitle;

    @FindBy(xpath = "//ng-select[@id='ucsIndustries']//input")
    WebElement industryField;

    @FindBy(xpath = "//div[@class='ng-dropdown-panel-items scroll-host']//div[@role='option']")
    WebElement dropdownOption;

    @FindBy(xpath ="//ng-select[@id='ucsFunction']//input")
    WebElement functionField;

    @FindBy(xpath = "//ng-select[@id='ucsTechnologies']//input")
    WebElement technologiesField;

    @FindBy(xpath = "//ng-select[@id='ucsTags']//input")
    WebElement tagsField;

    //todo
    @FindBy(xpath = "(//div[@role='textbox'])[1]")
    WebElement solutionSummaryField;

    @FindBy(xpath = "(//div[@role='textbox'])[2]")
    WebElement painPointsAddressedField;
//    //bold,italicised,etc

    @FindBy(xpath = "//input[@id='file']")
    WebElement productExplainerVideoMp4Field;

    @FindBy(xpath = "//div[@class='mat-menu-trigger img-with-text']")
    WebElement productExplainerVideoYoutubeField;

    @FindBy(css = "input[class='form-control ng-untouched ng-pristine ng-valid']")
    WebElement videoLinkField;

    @FindBy(xpath = "//span[@class='mr-2 ml-3']")
    WebElement selectVideoLinkButton;

    @FindBy(xpath = "//div[@class='ng-star-inserted']")
    WebElement videoPreview;

    @FindBy(xpath = "//div[starts-with(text(),'Delete Video')]")
    WebElement deleteVideoLink;

    @FindBy(xpath = "//div[@class='add-another-testimonial ng-star-inserted']")
    WebElement addAnotherTestimonialField;

    @FindBy(xpath = "//input[@id='name']")
    WebElement testimonialsNameField;

    @FindBy(xpath = "//input[@id='role']")
    WebElement testimonialsRoleField;

    @FindBy(xpath = "//textarea[@id='testimonial']")
    WebElement testimonialsField;

    @FindBy(xpath = "//input[@id='casestudyFile']")
    WebElement uploadCaseStudy;

    @FindBy(xpath = "//button[@id='solutionBack']")
    WebElement backButton;

    @FindBy(xpath = "//button[@id='solutionSubmit']")
    WebElement previewButton;

    @FindBy(xpath = "//button[@id='ucsSubmitButton']")
    WebElement solutionPublishButton;

    @FindBy(xpath = "//button[@id='skipAndSubmit']")
    WebElement skipAndSubmitButton;

    @FindBy(xpath = "//div[@id='cdk-step-content-0-3']")
    WebElement onboardCompletePage;

    @FindBy(css = "[class='btn btn-secondary button-text btn-lg']")
    WebElement logoutButton;

    @FindBy(css = "button[class=\"choose-existing-solution-btn\"]")
    WebElement chooseExistingSolutionButton;

    @FindBy(css = "[class=\"solution-card\"]")
    WebElement solutionCard;

    @FindBy(xpath = "//div[@class=\"solution-card\"]//following-sibling::button")
    WebElement instantApplyButton;

    @FindBy(xpath="//button[.='Apply without Editing']")
    WebElement applyWithoutEditingButton;


    public StartupSolutionsPage() {
        PageFactory.initElements(driver, this);
    }



    public void setSolutionTitleField(String SolutionTitle) {
        ExplicitWaits.explicitWaitVisibilityOfElement(enterSolutionTitle,30);
        enterSolutionTitle.sendKeys(SolutionTitle);
        Log.info("user added Solution title");
    }

    public void setSolutionIndustryField(String industry) {
        industryField.sendKeys(industry);
        ExplicitWaits.explicitWaitVisibilityOfElement(dropdownOption,50);
        dropdownOption.click();
        Log.info("user added Solution industry");
    }

    public void setSolutionFunctionField(String functions){
        functionField.sendKeys(functions);
        ExplicitWaits.explicitWaitVisibilityOfElement(dropdownOption,50);
        try {
            dropdownOption.click();
        } catch (ElementClickInterceptedException e) {
            Log.info("ElementClickInterceptedException");
            commonMethods.clickElementByJS(dropdownOption, driver);
        }
        Log.info("user added Solution functions");
    }

    public void setSolutionTechnologiesField(String technologies) {
        technologiesField.sendKeys(technologies);
        ExplicitWaits.explicitWaitVisibilityOfElement(dropdownOption,50);
        try {
            dropdownOption.click();
        } catch (ElementClickInterceptedException e) {
            Log.info("ElementClickInterceptedException");
            commonMethods.clickElementByJS(dropdownOption, driver);
        }
        Log.info("user added Solution technologies");
    }

    public void setSolutionTagsField(String tags) {
        tagsField.sendKeys(tags);
        ExplicitWaits.explicitWaitVisibilityOfElement(dropdownOption,5);
        try {
            dropdownOption.click();
        } catch (ElementClickInterceptedException e) {
            Log.info("ElementClickInterceptedException");
            commonMethods.clickElementByJS(dropdownOption, driver);
        }
        Log.info("user added Solution tags");
    }

    public void setSolutionSummaryField(String solutionSummaryField1){
        solutionSummaryField.sendKeys(solutionSummaryField1);
        Log.info("user entered Solution Summary");
    }

    public void setSolutionPainPointsAddressedField(String painPointsAddressedField1) {

        painPointsAddressedField.sendKeys(painPointsAddressedField1);
        Log.info("User entered Solution painPointsAddressed");
    }

    public void uploadSolutionProductExplainerVideoMp4Field(String filepath){
        productExplainerVideoMp4Field.sendKeys(filepath);
        ExplicitWaits.explicitWaitVisibilityOfElement(deleteVideoLink,50);
        Log.info("uploaded Solution Product Explainer Video");
    }

//    public void clickOnSolutionOpenCancelButton(){
//        openCancelButton.click();
//        Log.info("Clicked on cancel button of Solution productExplainerVideoMp4Field");
//    }

    public void addSolutionProductExplainerVideoYoutubeLink(String youtube_ViemeoLink){
        ExplicitWaits.explicitWaitVisibilityOfElement(productExplainerVideoYoutubeField,50);
        try {
            productExplainerVideoYoutubeField.click();
        }catch (ElementClickInterceptedException e){
            Log.info("ElementClickInterceptedException");
            commonMethods.clickElementByJS(productExplainerVideoYoutubeField,driver);
        }

        ExplicitWaits.explicitWaitVisibilityOfElement(videoLinkField,10);
        videoLinkField.sendKeys(youtube_ViemeoLink);
        Log.info("entered link in productExplainerVideoYoutubeField");
        selectVideoLinkButton.click();
        Log.info("added productExplainerVideo Youtube/vimeo link");
//ToDo
//        ExplicitWaits.explicitWaitVisibilityOfElement(videoPreview,50);
//        Assert.assertTrue(videoPreview.isDisplayed(),"attached video preview not displayed");
    }

    public void deleteAttachedVideo(){
        ExplicitWaits.explicitWaitVisibilityOfElement(deleteVideoLink,60);
        try {
            deleteVideoLink.click();
        }catch (ElementClickInterceptedException e){
            Log.info("ElementClickInterceptedException");
            commonMethods.clickElementByJS(deleteVideoLink,driver);
        }
        Log.info("deleted attached video");
    }


//    public void clickOnOpenCancelButton(){
//        openCancelButton.click();
//        Log.info("Clicked on cancel button of open ");
//    }

    public void setSolutionTestimonialsNameField(String testimonialsNameField1) {

        testimonialsNameField.sendKeys(testimonialsNameField1);
        Log.info("User entered Solution TestimonialsNameField");
    }

    public void setSolutionTestimonialsRoleField(String testimonialsRoleField1) {

        testimonialsRoleField.sendKeys(testimonialsRoleField1);
        Log.info("User entered Solution TestimonialsRoleField");
    }

    public void setSolutionTestimonialsField(String testimonialsField1) {

        testimonialsField.sendKeys(testimonialsField1);
        Log.info("User entered Solution TestimonialsField");
    }

    public void clickOnSolutionAddAnotherTestimonialField(){
        addAnotherTestimonialField.click();
        Log.info("Clicked on Solution addAnotherTestimonialField");
    }

    public void uploadSolutionCaseStudy(String filePath){
        uploadCaseStudy.sendKeys(filePath);
        Log.info("uploaded docs on Solution caseStudyField");
        ExplicitWaits.explicitWaitVisibilityOfElement(By.xpath("//div[@class='form-group mt-4']//div[@class='file-upload-card ng-star-inserted']"),40);
        ////div[@class='document-class ng-star-inserted']
    }

    public void clickOnSolutionPreviewButton() {
        ExplicitWaits.waitTillAngularRequestIsFinish();
        ExplicitWaits.explicitWaitVisibilityOfElement(previewButton,50);
        try {
            previewButton.click();
        }catch (ElementClickInterceptedException e){
            Log.debug("exception handled");
            commonMethods.clickElementByJS(previewButton,driver);
        }
        Log.info("clicked on Solution preview button");
    }

    public void clickOnSolutionPublishButton() {
        commonMethods.scrollToElement(driver,solutionPublishButton);
        ExplicitWaits.explicitWaitElementToBeClickable(solutionPublishButton,50);
        ExplicitWaits.waitTillAngularRequestIsFinish();
        try {
            solutionPublishButton.click();
        }catch (ElementClickInterceptedException e){
            Log.debug("exception handled");
            commonMethods.clickElementByJS(solutionPublishButton,driver);
        }
        Log.info("clicked on Solution publish button");
        ExplicitWaits.waitTillAngularRequestIsFinish();
    }

    public void clickOnSolutionBackButton() {

        backButton.click();
        Log.info("clicked on Solution back button");
    }

    public boolean verifyOnboardCompletePage(){
        ExplicitWaits.explicitWaitVisibilityOfElement(onboardCompletePage,30);
        return onboardCompletePage.isDisplayed();
    }

    public void clickOnLogout(){
//        ExplicitWaits.explicitWaitVisibilityOfElement(logoutButton,300);
        ExplicitWaits.waitTillAngularRequestIsFinish();
        logoutButton.click();
        Log.info("clicked on logoutButton");
    }

    public void clickOnSkipAndSubmit(){
        try {
            skipAndSubmitButton.click();
        }catch (ElementClickInterceptedException e){
            Log.debug("exception handled");
            commonMethods.clickElementByJS(skipAndSubmitButton,driver);
        }
        Log.info("clicked on skipAndSubmitButton");
    }

    public void clickOnExistingSolutionButton(){
        chooseExistingSolutionButton.click();
        Log.info("Clicked on Choose Existing Solution Button");
    }

    public void applyExistingSolution(){
        ExplicitWaits.explicitWaitVisibilityOfElement(solutionCard,30);
        commonMethods.moveToElement(driver,solutionCard);
        ExplicitWaits.explicitWaitVisibilityOfElement(solutionCard,30);
        instantApplyButton.click();
        Log.info("Clicked on instantApplyButton");
        applyWithoutEditingButton.click();
        Log.info("Clicked on applyWithoutEditingButton");
    }


}
