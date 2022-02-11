package pageObjects.onboardingPages;

import com.paulhammant.ngwebdriver.ByAngularButtonText;
import org.apache.commons.math3.analysis.function.Exp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import reusableComponents.ExplicitWaits;
import reusableComponents.Log;
import testBase.TestBase;

import java.awt.*;
import java.util.List;

public class EnterpriseUsecasePage extends TestBase {

    @FindBy( xpath = "//button[@class='btn Choose-from-templates ng-star-inserted']")
    WebElement chooseFromTemplatesButton;

    @FindBy(xpath = "//input[@id='ucsTitle']")
    WebElement useCaseTitleField;

    @FindBy(xpath = "//ng-select[@id='addUcsIndustry']//input")
    WebElement industryField;

    @FindBy(xpath = "//div[@class='ng-dropdown-panel-items scroll-host']")
    WebElement dropdownOption;

    @FindBy(xpath ="//ng-select[@id='addUcsFunction']//input")
    WebElement functionField;

    @FindBy(xpath = "//ng-select[@id='addUcsTechnology']//input")
    WebElement technologiesField;

    @FindBy(xpath = "//ng-select[@id='addUcsTags']//input")
    WebElement tagsField;

    @FindBy(xpath = "//textarea[@id='validationServer02']")
    WebElement problemStatementField;

    @FindBy(xpath = "//div[@role='textbox']//p")
    WebElement descriptionField;

    @FindBy(xpath = "//button[@id='ucsBackButton']")
    WebElement backButton;

    @FindBy(xpath = "(//button[text()='Preview'])[1]")
    WebElement templatePreviewButton;

    @FindBy(xpath = "//button[@class='close']")
    WebElement closeTemplatesTile;


    @FindBy(xpath = "(//div[@class='use-case-card-wrapper m-4'])[1]")
    WebElement template;

    @FindBy(xpath = "(//button[@class='btn btn-lg btn-light button-class card-preview-btn'])[1]")
    WebElement previewButton;

    @FindBy(xpath = "//button[@class='btn btn-lg btn-primary use-template-btn']")
    WebElement useTemplateButton;

    @FindBy(xpath = "//button[@class='btn btn-light btn-lg button-class ng-star-inserted']")
    WebElement skipAndSubmitButton;

    @FindBy(xpath = "//button[@id='ucsSubmitButton']")
    WebElement profilePreviewButton;

    @FindBy(xpath = "//button[@id='ucsSubmitButton']")
    WebElement submitButton;

    @FindBy(xpath = "//a[@class='link-class']")
    WebElement onboardCompletedScreen;

    @FindBy(css = "div[type='button']")
    WebElement logoutButton;


    public EnterpriseUsecasePage() {
        PageFactory.initElements(driver, this);
    }


    public void setUseCaseTitleField(String useCaseTitle) {
        ExplicitWaits.explicitWaitVisibilityOfElement(useCaseTitleField,50);
        useCaseTitleField.clear();
        useCaseTitleField.sendKeys(useCaseTitle);
        Log.info("user added UseCase title");
    }

    public void setIndustryField(String industry) {
        industryField.sendKeys(industry);
        ExplicitWaits.explicitWaitVisibilityOfElement(dropdownOption,10);
        dropdownOption.click();
        Log.info("user added industry");
    }

    public void setFunctionField(String functions){
        ExplicitWaits.explicitWaitVisibilityOfElement(functionField,50);
        functionField.sendKeys(functions);
        ExplicitWaits.explicitWaitVisibilityOfElement(dropdownOption,50);
        try {
            dropdownOption.click();
        } catch (ElementClickInterceptedException e) {
            Log.info("ElementClickInterceptedException");
            commonMethods.clickElementByJS(dropdownOption, driver);
        }
        Log.info("user added functions");
    }

    public void setTechnologiesField(String technologies) {
        technologiesField.sendKeys(technologies);
        ExplicitWaits.explicitWaitVisibilityOfElement(dropdownOption,10);
        try {
            dropdownOption.click();
        } catch (ElementClickInterceptedException e) {
            Log.info("ElementClickInterceptedException");
            commonMethods.clickElementByJS(dropdownOption, driver);
        }
        Log.info("user added technologies");
    }

    public void setTagsField(String tags) {
        tagsField.sendKeys(tags);
        ExplicitWaits.explicitWaitVisibilityOfElement(dropdownOption,10);
        try {
            dropdownOption.click();
        } catch (ElementClickInterceptedException e) {
            Log.info("ElementClickInterceptedException");
            commonMethods.clickElementByJS(dropdownOption, driver);
        }
        Log.info("user added tags");
    }

    public void setProblemStatementField(String problemStatement){
        problemStatementField.sendKeys(problemStatement);
        Log.info("user entered problem statement");
    }

    public void setDescriptionField(String description) {
        descriptionField.sendKeys(description);
        Log.info("User entered description");
    }

    public void clickOnChooseFromTemplates(){
        commonMethods.scrollToElement(driver,chooseFromTemplatesButton);
        ExplicitWaits.explicitWaitVisibilityOfElement(chooseFromTemplatesButton,50);
        try {
        chooseFromTemplatesButton.click();
        }catch (ElementClickInterceptedException e){
            Log.info("ElementClickInterceptedException");
            commonMethods.clickElementByJS(chooseFromTemplatesButton,driver);
        }
        Log.info("clicked on chooseFromTemplatesButton");
    }

    public void previewTemplate() {
        ExplicitWaits.explicitWaitVisibilityOfElement(template,50);
        commonMethods.moveToElement(driver,template);
        Log.info("moved to template");
        ExplicitWaits.explicitWaitVisibilityOfElement(templatePreviewButton,50);
        templatePreviewButton.click();
        Log.info("clicked on previewButton");
    }
    public void clickOnUseTemplate(){
        ExplicitWaits.explicitWaitVisibilityOfElement(useTemplateButton,60);
        useTemplateButton.click();
        Log.info("clicked on useTemplateButton");
    }

    public void clickOnPreviewButton() {
        try {
            profilePreviewButton.click();
        }catch (Exception e){
            commonMethods.clickElementByJS(profilePreviewButton,driver);
        }
        Log.info("clicked on profile preview button");
    }


    public void clickOnSubmitButton() throws AWTException {
        commonMethods.scrollPageDown();
        commonMethods.scrollToElement(driver,submitButton);
        ExplicitWaits.explicitWaitVisibilityOfElement(submitButton,60);
        try {
        commonMethods.retryingFindClick(submitButton);
        }catch (ElementClickInterceptedException e){
            Log.info("ElementClickInterceptedException");
            commonMethods.clickElementByJS(submitButton,driver);
        }
        Log.info("clicked on submit button");
    }

    public void clickOnBackButton() {
        backButton.click();
        Log.info("clicked on back button");
    }

    public void verifyOnboardingCompleted(){
        ExplicitWaits.explicitWaitVisibilityOfElement(onboardCompletedScreen,50);
        Assert.assertTrue(onboardCompletedScreen.isDisplayed());
    }

    public void clickOnLogout(){
        ExplicitWaits.waitTillAngularRequestIsFinish();
        logoutButton.click();
        Log.info("clicked on logoutButton");
    }

    public void clickOnSkipAndSubmit(){
        try {
            skipAndSubmitButton.click();
        }catch (ElementClickInterceptedException e){
            e.printStackTrace();
            commonMethods.clickElementByJS(skipAndSubmitButton,driver);
        }
        Log.info("click on skip & submit button");
    }



}
