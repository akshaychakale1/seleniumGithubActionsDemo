package pageObjects.onboardingPages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import reusableComponents.ExplicitWaits;
import reusableComponents.Log;
import testBase.TestBase;

public class EntitySelectionPage extends TestBase {

    @FindBy(css = "[id='enterprise']")
    WebElement startUpTile;

    @FindBy(css = "[id='startup']")
    WebElement enterpriseTile;


    public EntitySelectionPage() {
        PageFactory.initElements(driver, this);
    }

    public void clickOnStartUpTile(){
        ExplicitWaits.waitForPageToLoad();
        startUpTile.click();
        Log.info("clicked on StartUp tile");
    }

    public void clickOnEnterpriseTile() {
        ExplicitWaits.explicitWaitVisibilityOfElement(startUpTile,50);
        try {
            enterpriseTile.click();
        }catch (Exception e){
            commonMethods.clickElementByJS(enterpriseTile, driver);
        }
        Log.info("clicked on Enterprise tile");

    }

    /*
    public void setNameField(String name) {
        nameField.clear();
        nameField.sendKeys(name);
        Log.info("User Entered name");
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
        ExplicitWaits.explicitWaitVisibilityOfElement(By.xpath("//div[@class='suggestions-container is-visible']"),10);
        ExplicitWaits.explicitWaitElementToBeClickable(locationDropdownOption,10);
        companyLocationField.sendKeys(Keys.ARROW_DOWN);
        companyLocationField.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        companyLocationField.sendKeys(Keys.ENTER);
        Log.info("User entered company location");
    }

    public void clickOnNextButton() {
        nextButton.click();
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
        ExplicitWaits.explicitWaitVisibilityOfElement(By.xpath("//div[@class='move']"),10);
        uploadLogoButton.click();
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
    */

}
