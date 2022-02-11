package pageObjects.onboardingPages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import reusableComponents.ExplicitWaits;
import reusableComponents.Log;
import testBase.TestBase;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class StartupProfilePage extends TestBase {



    @FindBy(xpath = "//input[@id='name']")
    WebElement nameField;

    @FindBy(xpath = "//input[@id='name']//following-sibling::div")
    WebElement nameFieldError;

    @FindBy(xpath = "//input[@id='srole']")
    WebElement designationField;

    @FindBy(xpath = "//input[@id='srole']//following-sibling::div")
    WebElement designationFieldError;

    @FindBy(xpath = "//input[@id='l-id']")
    WebElement linkedinIdField;

    @FindBy(xpath = "//input[@id='l-id']//following-sibling::div")
    WebElement linkedinIdFieldError;

    @FindBy(xpath = "//input[@id='c-name']")
    WebElement companyNameField;

    @FindBy(xpath = "//input[@id='c-name']//following-sibling::div")
    WebElement companyNameFieldError;

    @FindBy(xpath = "//input[@id='c-website']")
    WebElement companyWebsiteField;

    @FindBy(xpath = "//input[@id='c-website']//following-sibling::div")
    WebElement companyWebsiteFieldError;

    @FindBy(xpath = "//ng-autocomplete[@id='cityInput']//input")
    WebElement companyLocationField;

    @FindBy(xpath = "//div[contains(text(),'Location seems missing, please try again')]")
    WebElement companyLocationFieldError;

    @FindBy(xpath = "//select[@id='company_size']")
    WebElement companySizeField;

    @FindBy(xpath = "//select[@id='company_size']//following-sibling::div")
    WebElement companySizeFieldError;

    @FindBy(xpath = "//select[@id='paidCustomers']")
    WebElement paidCustomersField;

    @FindBy(xpath = "//select[@id='paidCustomers']//following-sibling::div")
    WebElement paidCustomersError;

    @FindBy(xpath = "//input[@id='kCustomer']")
    WebElement keyCustomers;

    @FindBy(xpath = "//select[@id='funding']")
    WebElement fundingRaisedField;

    @FindBy(xpath = "//select[@id='funding']//following-sibling::div")
    WebElement fundingRaisedFieldError;

    @FindBy(xpath = "//input[@id='rRevenue']")
    WebElement arRevenueField;

    @FindBy(xpath = "//input[@id='rRevenue']//following-sibling::div")
    WebElement arRevenueFieldError;

    @FindBy(xpath = "//div[@class='Startup_Maturity_Stage m-2 p-2 non-active ng-star-inserted']//input")
    List<WebElement> maturityStage;

    @FindBy(xpath = "//div[@class='Startup_Maturity_Stage m-2 p-2 ng-star-inserted non-active']//input")
    List<WebElement> recentFunding;

    @FindBy(xpath = "//button[@id='startupBack']")
    WebElement backButton;

    @FindBy(xpath = "//button[@id='startupNext']")
    WebElement nextButton;

    @FindBy(xpath = "//img[@class='camera-icon']")
    WebElement uploadLogoTile;

    @FindBy(xpath = "//input[@id='uploadStartupLogo']")
    WebElement chooseFile;

    @FindBy(xpath = "//button[@class='btn btn-primary upload-logo-btn']")
    WebElement uploadLogoButton;

    @FindBy(xpath = "//button[@class='btn btn-primary cancel-logo-btn remove-logo-btn']")
    WebElement uploadLogoCancelButton;

    @FindBy(xpath = "(//a[@class=\"ng-star-inserted\"])[1]")
    WebElement locationDropdownOption;

    // admin on-boarding
    @FindBy(xpath = "//input[@id='signEmail']")
    WebElement emailField;

    @FindBy(xpath = "//input[@id='signEmail']//following-sibling::div")
    WebElement emailFieldError;



    public StartupProfilePage() {
        PageFactory.initElements(driver, this);
    }


    public void setStartupUserNameField(String name) {
        nameField.clear();
        nameField.sendKeys(name);
        Log.info("startup User Entered name");
    }

    public void setStartupNameField(String name) {
        companyNameField.clear();
        companyNameField.sendKeys(name);
        Log.info("startup User Entered company name");
    }

    public void setStartupDesignationField(String designation) {
        designationField.clear();
        designationField.sendKeys(designation);
        Log.info("startup User Entered designation");
    }

    public void setStartUpLinkedinIdField(String linkedinId) {
        ExplicitWaits.explicitWaitVisibilityOfElement(linkedinIdField,50);
        linkedinIdField.clear();
        linkedinIdField.sendKeys(linkedinId);
        Log.info("startup User entered linkedIn Id");
    }

    public void setCompanyEmailField(String email){
        emailField.clear();
        emailField.sendKeys(email);
        Log.info("email field entered");
    }

    public void setStartUpCompanyNameField(String companyName) {
        companyNameField.clear();
        companyNameField.sendKeys(companyName);
        Log.info("User entered startup name");
    }

    public void setStartUpCompanyWebsiteField(String website) {
        companyWebsiteField.clear();
        companyWebsiteField.sendKeys(website);
        Log.info("user entered startup website");
    }

    public void setStartUpCompanyLocationField(String location) {
        companyLocationField.clear();
        companyLocationField.sendKeys(location);
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.getMessage();
        }
        ExplicitWaits.waitTillAngularRequestIsFinish();
        ExplicitWaits.explicitWaitElementToBeClickable(locationDropdownOption,30);
        try {
            locationDropdownOption.click();
        }catch (ElementClickInterceptedException e){
            Log.info("ElementClickInterceptedException");
            commonMethods.clickElementByJS(locationDropdownOption,driver);
        }
        Log.info("User entered company location");
    }

    public void setStartUpCompanySizeField(int index) {
       Select select=new Select(companySizeField);
       select.selectByIndex(index);
        Log.info("User selected startup_Size_Field");
    }

    public void setStartUpPaidCustomersField(String paidCustomers) throws Exception {
        commonMethods.selectDropdownOption(paidCustomersField,paidCustomers);
        Log.info("User entered startup paid_Customers");
    }

    public void setStartUpKeyCustomer(String keyCustomer) {
        keyCustomers.clear();
        keyCustomers.sendKeys(keyCustomer);
        Log.info("User entered startup key_Customer");
    }

    public void setStartUpFundingRaised(int fundingIndex)  {
        commonMethods.selectByIndex(fundingRaisedField,fundingIndex);
        Log.info("User entered startup funding");
    }

    public void setStartUpArrRevenue(String revenue) {
        arRevenueField.clear();
        arRevenueField.sendKeys(revenue);
        Log.info("User entered startup ARR");
    }

    public void setStartUpMaturityStage() {
        for (WebElement rf : maturityStage){
            ExplicitWaits.waitTillAngularRequestIsFinish();
            try {
                rf.click();
            }catch (ElementClickInterceptedException e){
                Log.info("ElementClickInterceptedException");
                commonMethods.clickElementByJS(rf,driver);
            }

        }
        Log.info("User selected startup maturity_stage");
    }

    public void setStartUpRecentFunding() {
        recentFunding.get(2).click();
       Log.info("User selected startup most_recent_funding_round");
    }

    public void clickOnStartUpNextButton() {
        ExplicitWaits.explicitWaitElementToBeClickable(nextButton,50);
        try {
            nextButton.click();
        }catch (ElementClickInterceptedException e){
            Log.debug("exception handled");
            commonMethods.clickElementByJS(nextButton,driver);
        }
        ExplicitWaits.waitTillAngularRequestIsFinish();
        Log.info("Clicked on startup next button");
    }

    public void clickOnStartUpBackButton() {
        backButton.click();
        Log.info("clicked on startup back button");
    }

    public void clickOnStartUpUploadLogoTile(){
        ExplicitWaits.explicitWaitVisibilityOfElement(uploadLogoTile,50);
        try {
            uploadLogoTile.click();
        }catch (Exception e){
            commonMethods.clickElementByJS(uploadLogoTile,driver);
        }
        Log.info("Clicked on startup_upload logo");
    }

    public void uploadStartUpCompanyLogo(String filePath)  {
        chooseFile.sendKeys(filePath);
        ExplicitWaits.explicitWaitVisibilityOfElement(By.xpath("//div[@class='move']"),10);
        uploadLogoButton.click();
        Log.info("Company logo uploaded");
        ExplicitWaits.waitTillAngularRequestIsFinish();
    }

    public void clickOnStartUpProfileUploadLogoTile(){
        ExplicitWaits.explicitWaitVisibilityOfElement(uploadLogoTile,50);
        try {
            uploadLogoTile.click();
        }catch (Exception e){
            commonMethods.clickElementByJS(uploadLogoTile,driver);
        }
        Log.info("Clicked on startup_upload logo");
    }



    public void uploadStartUpProfileCompanyLogo(String filePath)  {
        chooseFile.sendKeys(filePath);
        ExplicitWaits.explicitWaitVisibilityOfElement(By.xpath("//div[@class='move']"),10);
        uploadLogoButton.click();
        Log.info("Company logo uploaded");
    }



    public void clickOnStartUpUploadLogoCancelButton(){
        uploadLogoCancelButton.click();
        Log.info("Clicked on cancel button of upload logo");
    }



    public void startUpVerifyEmptyNameFieldError(){
        Assert.assertEquals(nameFieldError.getText(),"Please enter your name");
    }

    public void startUpVerifyEmptyDesignationFieldError(){
        Assert.assertEquals(designationFieldError.getText(),"Please enter your role");
    }

    public void startUpVerifyEmptyCompanyNameFieldError(){
        Assert.assertEquals(companyNameFieldError.getText(),"Please enter your company’s name");
    }

    public void startUpVerifyEmptyCompanyLocationFieldError(){
        Assert.assertEquals(companyLocationFieldError.getText(),"Location seems missing, please try again");
    }

    public void startUpVerifyEmptyCompanyWebsiteFieldError(){
        Assert.assertEquals(companyWebsiteFieldError.getText(),"Please enter your company's website");
    }

    public void startUpVerifyEmptyCompanySizeFieldError(){
        Assert.assertEquals(companySizeFieldError.getText(),"Please select your company size");
    }

    public void startUpVerifyEmptyPaidCustomersFieldError(){
        Assert.assertEquals(paidCustomersError.getText(),"Please select one");
    }

    public void startUpVerifyEmptyFundingRaisedFieldError(){
        Assert.assertEquals(fundingRaisedFieldError.getText(),"Please select one");
    }

    public void startUpVerifyEmptyArRevenueFieldError(){
        Assert.assertEquals(arRevenueFieldError.getText(),"Please enter your company’s ARR");
    }

    public void startUpVerifyInvalidArRevenueFieldError(){
        Assert.assertEquals(arRevenueFieldError.getText(),"The company’s ARR field should only be integer");
    }

    public void startUpVerifyInvalidLinkedinFieldError(){
        verify.assertEquals(linkedinIdFieldError.getText(),"The linked in url seems invalid, please try again");
    }

    public void startUpVerifyInvalidCompanyWebsiteFieldError(){
        Assert.assertEquals(companyWebsiteFieldError.getText(),"The url seems invalid, please try again");
    }

    public void startUpVerifyInvalidEmailFieldError(){
        Assert.assertEquals(emailFieldError.getText(),"Please enter a valid email id");
    }

    public void verifyCompanyProfileFieldsDisabled(){
        Assert.assertFalse(companyNameField.isEnabled(),"company profile fields should be disabled for second user");
        Assert.assertFalse(companyWebsiteField.isEnabled(),"company profile fields should be disabled for second user");
        Assert.assertFalse(companyLocationField.isEnabled(),"company profile fields should be disabled for second user");
        Assert.assertFalse(companySizeField.isEnabled(),"company profile fields should be disabled for second user");
        Assert.assertFalse(paidCustomersField.isEnabled(),"company profile fields should be disabled for second user");
        Assert.assertFalse(fundingRaisedField.isEnabled(),"company profile fields should be disabled for second user");
    }


}
