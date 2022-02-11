package pageObjects.usecaseSharing;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reusableComponents.ExplicitWaits;
import reusableComponents.Log;
import testBase.TestBase;

public class ApplyUseCaseLoginPage extends TestBase {

    @FindBy(xpath = "//p[@class='company-content']")
    WebElement companyTitle;

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


    public ApplyUseCaseLoginPage() {
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
        loginButton.click();
        Log.info("clicked on loginButton");
    }

    public void clickSignUpButton() {
        signUpButton.click();
        Log.info("clicked on SignUp Button");
    }

    public void clickForgotPasswordLink() {
        forgotPasswordLink.click();
        Log.info("clicked on forgotPasswordLink");
    }


}
