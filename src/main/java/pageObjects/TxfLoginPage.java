package pageObjects;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import reusableComponents.ExplicitWaits;
import reusableComponents.Log;
import reusableComponents.PropertiesOperations;
import testBase.TestBase;

public class TxfLoginPage extends TestBase {

    @FindBy(xpath = "//input[@id='email']")
    WebElement emailField;

    @FindBy(xpath = "(//div[@class='error-class ng-star-inserted'])[1]")
    WebElement emailFieldError;

    @FindBy(xpath = "//input[@id='signupPassword']")
    WebElement passwordField;

    @FindBy(xpath = "(//div[@class='error-class ng-star-inserted'])[2]")
    WebElement passwordFieldError;

    @FindBy(xpath = "//button[@id='loginSubmit']")
    WebElement loginButton;

    @FindBy(xpath = "//button[@id='signupButton']")
    WebElement signUpButton;

    @FindBy(css = "//em[@class='fa fa-eye']")
    WebElement passwordVisibilityToggleButton;

    @FindBy(xpath = "//a[@class='forgotpassword label-class']")
    WebElement forgotPasswordLink;

    @FindBy(xpath ="//div[@class='ng-star-inserted']")
    WebElement homepageHeading;

    //private static Logger Log = LogManager.getLogger(Log.class.getName());

    public TxfLoginPage() {
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        ExplicitWaits.explicitWaitVisibilityOfElement(emailField,30);
        emailField.clear();
        emailField.sendKeys(email);
        Log.info("enterprise_userName entered");
    }

    public void enterPassword(String passkey) {
        passwordField.clear();
        passwordField.sendKeys(passkey);
        Log.info("enterprise_Password entered");
    }

    public void clickLoginButton() {
        try {
            loginButton.click();
        }catch (ElementClickInterceptedException e){
            Log.info("ElementClickInterceptedException");
            commonMethods.clickElementByJS(loginButton,driver);
        }
        Log.info("clicked on loginButton");
    }

    public void clickSignUpButton() {
        ExplicitWaits.waitTillAngularRequestIsFinish();
        try {
            signUpButton.click();
        }catch (ElementClickInterceptedException e){
            Log.info("ElementClickInterceptedException");
            commonMethods.clickElementByJS(signUpButton,driver);
        }

        Log.info("clicked on SignUp Button");
    }

    public void clickForgotPasswordLink() {
        forgotPasswordLink.click();
        Log.info("clicked on forgotPasswordLink");
    }

    public void clickPasswordVisibilityToggleButton() {
        passwordVisibilityToggleButton.click();
    }

    public void verifyEmptyEmailFieldError(){
        Assert.assertEquals( emailFieldError.getText(),"Email is required");
    }

    public void verifyEmptyPasswordFieldError(){
        Assert.assertEquals( passwordFieldError.getText(),"Incorrect password. Please try again or reset your password");
    }

    public void verifyInvalidEmailFieldError(){
        Assert.assertEquals( emailFieldError.getText(),"Email must be a valid email address");
    }

    public void verifyInvalidPasswordFieldError(){
        Assert.assertEquals( passwordFieldError.getText(),"Password must be at least 8 characters");
    }

    public boolean homepageDisplayed(){
        ExplicitWaits.explicitWaitVisibilityOfElement(homepageHeading,20);
        return homepageHeading.isDisplayed();
    }

    public void loginToAdminPanel() throws Exception {
        txfLoginPage.enterEmail(PropertiesOperations.getPropertyValueByKey("adminUser"));//email
        txfLoginPage.enterPassword(PropertiesOperations.getPropertyValueByKey("adminPass"));//newPassword
        txfLoginPage.clickLoginButton();
    }

}
