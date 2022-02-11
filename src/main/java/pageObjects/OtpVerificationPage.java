package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import reusableComponents.ExplicitWaits;
import reusableComponents.Log;
import testBase.TestBase;

public class OtpVerificationPage extends TestBase {

    @FindBy(css = "[class=\"button-class\"]")
    WebElement verifyButton;

    @FindBy(xpath = "//button[@class='mr-2 transparent-button mt-2']")
    WebElement goBackButton;

    @FindBy(xpath = "//div[@class='wrapper ng-star-inserted']//input")
    WebElement otpField;

    @FindBy(xpath = "//div[@class='card-body text-center']//strong")
    WebElement registeredEmail;

    @FindBy(xpath ="//div[@class='ng-star-inserted']")
    WebElement homepageHeading;


    public OtpVerificationPage() {
        PageFactory.initElements(driver, this);
    }


    public void enterOtp(String otp){
        ExplicitWaits.explicitWaitVisibilityOfElement(otpField,50);
        otpField.sendKeys(otp);
        Log.info("otp entered");
    }

    public void clickOnVerifyButton(){
        verifyButton.click();
        Log.info("Clicked on verifyButton");
    }

    public void clickOnGoBackButton(){
        goBackButton.click();
        Log.info("Clicked on goBackButton");

    }

    public void verifyOtpSentOnCorrectEmailAddress(String email){
        ExplicitWaits.waitTillAngularRequestIsFinish();
        ExplicitWaits.explicitWaitVisibilityOfElement(registeredEmail,60);
        Assert.assertEquals(registeredEmail.getText(),email);
    }

    public boolean homepageDisplayed(){
        ExplicitWaits.explicitWaitVisibilityOfElement(homepageHeading,20);
        return homepageHeading.isDisplayed();
    }


}
