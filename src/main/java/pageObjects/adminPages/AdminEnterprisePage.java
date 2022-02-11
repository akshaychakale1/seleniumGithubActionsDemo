package pageObjects.adminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reusableComponents.ExplicitWaits;
import reusableComponents.Log;
import testBase.TestBase;

public class AdminEnterprisePage extends TestBase {

    @FindBy(xpath = "//button[@id='logout']")
    WebElement logoutButton;

    @FindBy( xpath = "//a[@id='startupBtn']")
    WebElement adminStartup;

    @FindBy(xpath = "//a[@id='corporateBtn']")
    WebElement adminEnterprise;

    @FindBy(xpath = "//div[@class='list-group list-group-flush']//a[3]")
    WebElement adminSettings;

    @FindBy(xpath = "//button[@class='btn button-class mr-1 status-btn']")
    WebElement confirmApproveButton;

    @FindBy(xpath = "//button[@class='btn button-class mr-1 status-btn']")
    WebElement confirmRejectButton;

    public AdminEnterprisePage() {
        PageFactory.initElements(driver, this);
    }


    public void clickOnLogoutBtn(){
        ExplicitWaits.waitTillAngularRequestIsFinish();
        logoutButton.click();
        Log.info("clicked on logoutButton");
    }

    public void clickAdminSettings(){
        adminSettings.click();
        Log.info("clicked on adminSettings");
    }

    public void clickOnStartup(){
        adminStartup.click();
        Log.info("clicked on adminSettings");
    }

    public void clickOnEnterprise(){
        adminEnterprise.click();
        Log.info("clicked on adminSettings");
    }

    public void approveUser(String companyName){
        WebElement approve= driver.findElement(By.xpath("//mat-row[contains(.,'"+companyName+"')]//mat-cell[9]//button[1]"));
        //ExplicitWaits.explicitWaitVisibilityOfElement(approve,30);
        ExplicitWaits.waitTillAngularRequestIsFinish();
        try {
            approve.click();
        }catch (Exception e){
            commonMethods.clickElementByJS(approve,driver);
        }
        Log.info("clicked on approve Button");
        ExplicitWaits.waitTillAngularRequestIsFinish();
        ExplicitWaits.explicitWaitVisibilityOfElement(confirmApproveButton,50);
        confirmApproveButton.click();
        Log.info("clicked on approveBtn from popup");
    }

    public void rejectUser(String companyName){
        WebElement reject= driver.findElement(By.xpath("//mat-row[contains(.,'"+companyName+"')]//mat-cell[9]//button[2]"));
        reject.click();
        Log.info("clicked on reject Button");
        ExplicitWaits.waitTillAngularRequestIsFinish();
        ExplicitWaits.explicitWaitVisibilityOfElement(confirmRejectButton,30);
        confirmApproveButton.click();
        Log.info("clicked on reject button from popup");
    }

    public void verifyCompanyExpandedView(String companyName){
        WebElement company= driver.findElement(By.xpath("//mat-row[contains(.,'"+companyName+"')]//mat-cell[2]"));
        company.click();
        Log.info("clicked on company name");


    }

    public void verifyCompanyWebsiteOpensInNewTab(String companyName){
        WebElement companyWebsite= driver.findElement(By.xpath("//mat-row[contains(.,'"+companyName+"')]//mat-cell[3]//a"));

        String adminWindow = driver.getWindowHandle();
        companyWebsite.click();
        Log.info("clicked on company website");
        String companyWeb= driver.getWindowHandle();
        driver.switchTo().window(companyWeb);
        driver.close();
        Log.info("closed on company website tab");
        driver.switchTo().window(adminWindow);
        Log.info("switched to admin panel");
    }
}
