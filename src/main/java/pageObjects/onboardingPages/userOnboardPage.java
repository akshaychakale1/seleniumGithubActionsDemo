package pageObjects.onboardingPages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import reusableComponents.ExplicitWaits;
import reusableComponents.Log;
import testBase.TestBase;

public class userOnboardPage extends TestBase {

    @FindBy(xpath = "//span[@class='main-heading-class']")
    WebElement companyHeading;

    @FindBy(xpath = "//button[@id='backButton']")
    WebElement continueButton;

    public userOnboardPage (){
        PageFactory.initElements(driver, this);
    }

    public void verifyCompanyName(String companyName){
        ExplicitWaits.explicitWaitVisibilityOfElement(companyHeading,30);
        ExplicitWaits.waitTillAngularRequestIsFinish();
        Assert.assertEquals(companyName.toLowerCase(),companyHeading.getText().toLowerCase());
    }

    public void clickOnContinue(){
        ExplicitWaits.waitTillAngularRequestIsFinish();
        try {
            continueButton.click();
        }catch (ElementClickInterceptedException e){
            System.out.println("exception handled");
            commonMethods.clickElementByJS(continueButton,driver);
        }
        Log.info("clicked on continue button");
    }

    public void clickOnContinueInvitedUser(){
        ExplicitWaits.explicitWaitVisibilityOfElement(continueButton,60);
        try {
            commonMethods.retryingFindClick(continueButton);
        }catch (ElementClickInterceptedException e){
            System.out.println("exception handled");
            commonMethods.clickElementByJS(continueButton,driver);
        }
        Log.info("clicked on continue button");
    }

}
