package pageObjects.usecaseSharing;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import reusableComponents.ExplicitWaits;
import reusableComponents.Log;
import testBase.TestBase;

public class ApplyUseCaseSignUpPage extends TestBase {

    @FindBy(xpath = "//input[@id='signEmail']")
    public WebElement emailField;

    @FindBy(xpath = "(//div[@class='error-class ng-star-inserted'])[1]")
    WebElement firstFieldError;

    @FindBy(xpath = "//input[@id='signupPassword']")
    WebElement passwordField;

    @FindBy(xpath = "(//div[@class='error-class ng-star-inserted'])[2]")
    WebElement secondFieldError;

    @FindBy(xpath = "//button[@id='submitButtom']")
    WebElement signUpButton;

    @FindBy(css = "span[class='form-text text-muted'] span[class='link-class']")
    WebElement loginLink;

    @FindBy(xpath = "//div[@class='error-class mt-2 ng-star-inserted']")
    WebElement personalMailError;

    public ApplyUseCaseSignUpPage() {
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        ExplicitWaits.explicitWaitVisibilityOfElement(emailField,50);
        emailField.clear();
        emailField.sendKeys(email);
        Log.info("email entered for sign up");
    }

    public void verifyEmailFieldDisabled(){
        Assert.assertFalse(emailField.isEnabled());
        Log.info("verified email field is disabled");
    }

    public void enterPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
        Log.info("password entered for sign up");
    }
    public void clickSignup(){
        signUpButton.click();
        Log.info("Clicked on signup");
    }

    public void verifyEmptyEmailFieldError(){
        Assert.assertEquals( firstFieldError.getText(),"Please enter a valid email id");
        Log.info("verified empty EmptyEmailFieldError");
    }


}
