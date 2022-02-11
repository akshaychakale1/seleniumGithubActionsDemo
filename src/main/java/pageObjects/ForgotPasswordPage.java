package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import reusableComponents.Log;
import testBase.TestBase;

public class ForgotPasswordPage extends TestBase {

    @FindBy(xpath = "//input[@id='email']")
    WebElement emailField;

    @FindBy(xpath = "//button[@class='button-class button-bg']")
    WebElement resetPasswordButton;

    @FindBy(css = "button[class='mr-2 button-class mt-4']")
    WebElement verifyOtpButton;

    @FindBy(css = "button[class='mr-2 transparent-button mt-2']")
    WebElement goBackButton;

    @FindBy(xpath = "//div[@class='main-heading-class mt-3']")
    WebElement resetLinkSentBanner;

    @FindBy(xpath = "//input[@id='password']")
    WebElement newPasswordField;

    @FindBy(xpath = "//input[@id='confirmPassword']")
    WebElement confirmPasswordField;

    @FindBy(css = "button[class$='w-100 button-class button-bg']")
    WebElement signUpButton;

    //private static Logger Log = LogManager.getLogger(Log.class.getName());

    public ForgotPasswordPage() {
        PageFactory.initElements(driver, this);
    }

    public void setEmailField(String registeredEmail) {
        emailField.sendKeys(registeredEmail);
        Log.info("emailField entered");
    }

    public void clickResetPasswordButton() {
        resetPasswordButton.click();
        Log.info("clicked on resetPasswordButton");
    }

    public void clickVerifyButton() {
        verifyOtpButton.click();
        Log.info("clicked on verifyOtpButton");
    }

    public void clickGoBackButton() {
        goBackButton.click();
        Log.info("clicked on goBackButton");
    }

    public void verifyResetPasswordLinkSent(){
        Assert.assertTrue(resetLinkSentBanner.isDisplayed());
        Log.info("email address verified");
    }

    public String getResetPasswordLink(String email) throws Exception {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://mail7.io/");
        driver.findElement(By.xpath("(//div[@class='input-group form-group ']//input)[1]")).sendKeys(email);
        driver.findElement(By.cssSelector("input[class='button is-primary w-50']")).click();

        try {
            driver.findElement(By.xpath("//b[text()='[The X Future] Password reset request']//parent::div")).click();
        } catch (Exception e){
                throw  new Exception("Reset Link not received..");
            }

        WebElement iframe = driver.findElement(By.xpath("//div[@class='message']//iframe"));
        driver.switchTo().frame(iframe);
        WebElement emailBody = driver.findElement(By.xpath("//div[@class='mj-column-per-100 mj-outlook-group-fix']//tr[4]//a"));
        String resetLink = emailBody.getAttribute("href");

        return resetLink;
    }

    public void enterNewPassword(String password){
        newPasswordField.sendKeys(password);
        Log.info("password entered in newPasswordField");
    }

    public void confirmNewPassword(String password){
        confirmPasswordField.sendKeys(password);
        Log.info("password entered in confirmPasswordField");
    }

    public void clickOnSignUp(){
        signUpButton.click();
        Log.info("clicked on signUpButton");
    }


}
