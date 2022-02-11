package pageObjects;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import reusableComponents.ExplicitWaits;
import reusableComponents.Log;
import reusableComponents.PropertiesOperations;
import testBase.TestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TxfSignUpPage extends TestBase {

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

    //private static Logger Log = LogManager.getLogger(Log.class.getName());

    public TxfSignUpPage() {
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

    public void verifyEmptyPasswordFieldError(){
        Assert.assertEquals( secondFieldError.getText(),"Use 8 or more characters, with letters, numbers and symbols");
        Log.info("verified empty passwordFieldError");
    }

    public void verifyInvalidEmailFieldError(){
        Assert.assertEquals( firstFieldError.getText(),"Please enter a valid email id");
        Log.info("verified invalid emailFieldError");
    }

    public void verifyInvalidPasswordFieldError(){
        Assert.assertEquals( secondFieldError.getText(),"Use 8 or more characters, with letters, numbers and symbols");
        Log.info("verified Invalid passwordFieldError");
    }

    public boolean verifyPersonalMailError(){
       ExplicitWaits.explicitWaitVisibilityOfElement(personalMailError,50);
       return personalMailError.isDisplayed();
    }


    public String fetchOtp(String email) throws Exception {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://mail7.io/");
        driver.findElement(By.xpath("(//div[@class='input-group form-group ']//input)[1]")).sendKeys(email);
        driver.findElement(By.cssSelector("input[class='button is-primary w-50']")).click();

        try {
            driver.findElement(By.xpath("//b[.='[The X Future] Confirm your email']//parent::div")).click();
        }catch (Exception e){
            throw new Exception("OTP verification mail not received");
        }

        WebElement iframe = driver.findElement(By.xpath("//div[@class='message']//iframe"));
        driver.switchTo().frame(iframe);
        WebElement emailBody = driver.findElement(By.xpath("(//div[@class='mj-column-per-100 mj-outlook-group-fix']//tr[6]//following::span)[1]"));
        String emailBodyText=emailBody.getText();
        String otp= emailBodyText.replaceAll("[^0-9]", "");
        System.out.println("otp is :"+otp);

        return otp;
    }

    public String fetchOtpYopmail(String email){

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("http://www.yopmail.com/en/");

        driver.findElement(By.xpath("//input[@id='login']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@class='sbut']")).click();

        WebElement iframe = driver.findElement(By.xpath("//iframe[@id='ifmail']"));
        driver.switchTo().frame(iframe);
        WebElement emailBody = driver.findElement(By.xpath("(//div[@class='mj-column-per-100 mj-outlook-group-fix']//tr[6]//following::span)[1]"));
        String emailBodyText=emailBody.getText();
        String otp= emailBodyText.replaceAll("[^0-9]", "");
        System.out.println("otp is :"+otp);

        return otp;
    }
}
