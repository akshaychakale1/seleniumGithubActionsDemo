package pageObjects.onboardingPages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import reusableComponents.ExplicitWaits;
import reusableComponents.Log;
import testBase.TestBase;

public class EnterpriseProfilePage extends TestBase {


    @FindBy(xpath = "//input[@id='corFullname']")
    WebElement nameField;

    @FindBy(xpath = "//input[@id='corFullname']//following-sibling::div")
    WebElement nameFieldError;

    @FindBy(xpath = "//input[@id='corDesignation']")
    WebElement designationField;

    @FindBy(xpath = "//input[@id='corDesignation']//following-sibling::div")
    WebElement designationFieldError;

    @FindBy(xpath = "//input[@id='corLinkedinId']")
    WebElement linkedinIdField;

    @FindBy(xpath = "//input[@id='corLinkedinId']//following-sibling::div")
    WebElement linkedinIdFieldError;

    @FindBy(xpath = "//input[@id='corName']")
    WebElement companyNameField;

    @FindBy(xpath = "//input[@id='corName']//following-sibling::div")
    WebElement companyNameFieldError;

    @FindBy(xpath = "//input[@id='corCompany']")
    WebElement companyWebsiteField;

    @FindBy(xpath = "//input[@id='corCompany']//following-sibling::div")
    WebElement companyWebsiteFieldError;

    @FindBy(xpath = "//ng-autocomplete[@id='cityInput']//input")
    WebElement companyLocationField;

    @FindBy(xpath = "//div[contains(text(),'Location seems missing, please try again')]")
    WebElement companyLocationFieldError;

    @FindBy(xpath = "//div[@class='suggestions-container is-visible']//li[position()=2]//a")
    WebElement locationDropdownOption;

    @FindBy(xpath = "//button[@id='backButton']")
    WebElement backButton;

    @FindBy(xpath = "//button[@id='corSubmit']")
    WebElement nextButton;

    @FindBy(xpath = "//div[@class='col-4 mx-auto text-center']//img")
    WebElement uploadLogoTile;

    @FindBy(xpath = "//input[@id='uploadStartupLogo']")
    WebElement chooseFile;

    @FindBy(xpath = "//button[@class='btn btn-primary upload-logo-btn']")
    WebElement uploadLogoButton;

    @FindBy(css = "button[class='btn btn-primary cancel-logo-btn']")
    WebElement uploadLogoCancelButton;

    @FindBy(xpath = "//div[@class='btn btn-secondary btn-lg ng-star-inserted']")
    WebElement logoutButton;

    @FindBy(xpath = "//input[@id='email']")
    WebElement emailField;

    public EnterpriseProfilePage() {
        PageFactory.initElements(driver, this);
    }

    public void setNameField(String name) {
        ExplicitWaits.explicitWaitVisibilityOfElement(nameField,50);
        nameField.clear();
        nameField.sendKeys(name);
        Log.info("User Entered name");
    }

    public void setEmailField(String email){
        emailField.sendKeys(email);
        Log.info("email entered in email field : "+email);
    }

    public void setDesignationField(String designation) {
        designationField.clear();
        designationField.sendKeys(designation);
        Log.info("User Entered designation");
    }

    public void setLinkedinIdField(String linkedinId) {
        linkedinIdField.clear();
        linkedinIdField.sendKeys(linkedinId);
        Log.info("User entered linkedIn Id");
    }

    public void setCompanyNameField(String companyName) {
        ExplicitWaits.explicitWaitVisibilityOfElement(companyNameField,50);
        companyNameField.clear();
        companyNameField.sendKeys(companyName);
        Log.info("User entered company name");
    }

    public void setCompanyWebsiteField(String website) {
        companyWebsiteField.clear();
        companyWebsiteField.sendKeys(website);
        Log.info("user entered company website");
    }

    public void setCompanyLocationField(String location) throws InterruptedException {
        companyLocationField.clear();
        companyLocationField.sendKeys(location);
        ExplicitWaits.explicitWaitElementToBeClickable(locationDropdownOption,60);
        companyLocationField.sendKeys(Keys.ARROW_DOWN);
        companyLocationField.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        companyLocationField.sendKeys(Keys.ENTER);
        Log.info("User entered company location");
    }

    public void enterCompanyLocationField(String location){
        ExplicitWaits.waitTillAngularRequestIsFinish();
        companyLocationField.clear();
        companyLocationField.sendKeys(location);
        ExplicitWaits.explicitWaitElementToBeClickable(locationDropdownOption,50);
        ExplicitWaits.waitTillAngularRequestIsFinish();
        try {
            locationDropdownOption.click();
        }catch (ElementClickInterceptedException e){
            Log.info("ElementClickInterceptedException : "+e.getMessage());
            commonMethods.clickElementByJS(locationDropdownOption,driver);
        }

        Log.info("User entered company location");
    }

    public void clickOnNextButton() {
        ExplicitWaits.explicitWaitVisibilityOfElement(nextButton,60);
        ExplicitWaits.waitTillAngularRequestIsFinish();
        try {
            nextButton.click();
        }catch (ElementClickInterceptedException e){
            Log.info("ElementClickInterceptedException : "+e.getMessage());
            commonMethods.clickElementByJS(nextButton,driver);
        }
        Log.info("Clicked on next button");
    }

    public void clickOnBackButton() {
        backButton.click();
        Log.info("clicked on back button");
    }

    public void clickOnUploadLogoTile(){
        try {
            uploadLogoTile.click();
        }catch (Exception e){
            commonMethods.clickElementByJS(uploadLogoTile,driver);
        }

        Log.info("Clicked on upload logo");
    }

    public void uploadCompanyLogo(String filePath)  {
        chooseFile.sendKeys(filePath);
        ExplicitWaits.explicitWaitVisibilityOfElement(By.xpath("//div[@class='move']"),50);
        ExplicitWaits.explicitWaitElementToBeClickable(uploadLogoButton,50);
        commonMethods.clickElementByJS(uploadLogoButton,driver);
        Log.info("Company logo uploaded");
    }

    public void clickOnUploadLogoCancelButton(){
        uploadLogoCancelButton.click();
        Log.info("Clicked on cancel button of upload logo");
    }



    public void verifyEmptyNameFieldError(){
        verify.assertEquals(nameFieldError.getText(),"Please enter your full name");
    }

    public void verifyEmptyDesignationFieldError(){
        Assert.assertEquals(designationFieldError.getText(),"Please enter your role");
    }

    public void verifyEmptyLinkedinFieldError(){
        Assert.assertEquals(linkedinIdFieldError.getText(),"Please enter your LinkedIn ID");
    }

    public void verifyEmptyCompanyNameFieldError(){
        verify.assertEquals(companyNameFieldError.getText(),"Please enter your company’s name");
    }

    public void verifyEmptyCompanyLocationFieldError(){
        verify.assertEquals(companyLocationFieldError.getText(),"Location seems missing, please try again");
    }

    public void verifyEmptyCompanyWebsiteFieldError(){
        verify.assertEquals(companyWebsiteFieldError.getText(),"The url seems invalid, please try again");
    }

    public void verifyInvalidLinkedinFieldError(){
        verify.assertEquals(linkedinIdFieldError.getText(),"The linked in url seems invalid, please try again");
    }

    public void verifyInvalidCompanyWebsiteFieldError(){
        verify.assertEquals(companyWebsiteFieldError.getText(),"The url seems invalid, please try again");
    }

    public void verifyCompanyNameFieldDisabled() {
        Assert.assertFalse(companyNameField.isEnabled(),"company name field is enabled");
    }

    public void verifyCompanyLocationFieldDisabled() {
        WebElement location = driver.findElement(By.xpath("//input[@id='location']"));
        Assert.assertFalse(location.isEnabled(),"company location field is enabled");
    }

    public void verifyCompanyWebsiteFieldDisabled() {
        Assert.assertFalse(companyWebsiteField.isEnabled(),"company website field is enabled");
    }

    public void clickOnLogoutButton(){
        logoutButton.click();
        Log.info("clicked on logout button");
    }
}
