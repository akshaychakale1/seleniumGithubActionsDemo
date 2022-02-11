package pageObjects.onboardingPages;

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

public class GuestProfilePage extends TestBase {

    @FindBy(xpath = "//input[@id='uploadStartupLogo']")
    WebElement chooseFile;

    @FindBy(css = "[class='btn btn-primary upload-logo-btn']")
    WebElement uploadLogoButton;


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

    @FindBy(xpath = "//button[@id='startupNext']")
    WebElement submitButton;

    public GuestProfilePage() {
        PageFactory.initElements(driver, this);
    }

    public void uploadGuestLogo(String filePath)  {
        chooseFile.sendKeys(filePath);
        ExplicitWaits.explicitWaitVisibilityOfElement(By.xpath("//div[@class='move']"),50);
        uploadLogoButton.click();
        Log.info("Company logo uploaded");
        ngDriver.waitForAngularRequestsToFinish();
    }

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

    public void verifyEmptyNameFieldError(){
        Assert.assertEquals(nameFieldError.getText(),"Please enter your name");
    }

    public void verifyEmptyDesignationFieldError(){
        Assert.assertEquals(designationFieldError.getText(),"Please enter your role");
    }

    public void clickOnSubmit(){
        commonMethods.scrollToElement(driver,submitButton);
        ExplicitWaits.explicitWaitVisibilityOfElement(submitButton,50);
        try {
            submitButton.click();
        }catch (ElementClickInterceptedException e){
            Log.info(e.getLocalizedMessage());
            commonMethods.clickElementByJS(submitButton,driver);
        }
        Log.info("clicked on submit button");
    }


}
