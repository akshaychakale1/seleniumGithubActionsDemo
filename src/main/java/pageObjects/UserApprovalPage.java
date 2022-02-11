package pageObjects;

import org.apache.commons.math3.analysis.function.Exp;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import reusableComponents.ExplicitWaits;
import reusableComponents.Log;
import testBase.TestBase;

public class UserApprovalPage extends TestBase {

    @FindBy(xpath = "//button[@id='logout']")
    WebElement logoutButton;

    @FindBy(xpath = "//button[@id='takeToMyAccount']")
    WebElement takeToMyAccount;

    @FindBy(xpath = "//div[@class='approval_pending ng-star-inserted']")
    WebElement approvalPendingText;

    @FindBy(xpath = "//button[@id='takeToMyAccount']")
    WebElement gotoMyAccount;

    public UserApprovalPage() {
        PageFactory.initElements(driver, this);
    }

    public void clickOnLogoutBtn(){
        logoutButton.click();
        Log.info("clicked on logoutButton");
        ngDriver.waitForAngularRequestsToFinish();
    }

    public void verifyApprovalIsPending(){
        ExplicitWaits.explicitWaitVisibilityOfElement(approvalPendingText,30);
        Assert.assertTrue(approvalPendingText.isDisplayed());
        Log.info("verified approval is pending");
    }

    public void clickOnTakeToMyAccount(){
        commonMethods.scrollPageDown(driver);
        ExplicitWaits.explicitWaitVisibilityOfElement(takeToMyAccount,50);
        try {
            takeToMyAccount.click();
        }catch (ElementClickInterceptedException e){
            Log.info("ElementClickInterceptedException");
        commonMethods.clickElementByJS(takeToMyAccount,driver);
        }
        Log.info("clicked on takeToMyAccount");
        ExplicitWaits.waitTillAngularRequestIsFinish();
    }

    public void verifyTakeMeToMyAccountButtonIsDisplayed()  {
        Assert.assertTrue(takeToMyAccount.isDisplayed());
    }

    public void clickOnGotoMyAccount(){
        ExplicitWaits.waitTillAngularRequestIsFinish();
        try {
            gotoMyAccount.click();
        }catch (ElementClickInterceptedException e){
            Log.info("ElementClickInterceptedException");
            commonMethods.clickElementByJS(gotoMyAccount,driver);
        }
        Log.info("clicked on gotoMyAccount");
    }
}
