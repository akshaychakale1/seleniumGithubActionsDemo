package pageObjects.adminPages;

import org.apache.commons.math3.analysis.function.Exp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import reusableComponents.ExplicitWaits;
import reusableComponents.Log;
import reusableComponents.PropertiesOperations;
import testBase.TestBase;

public class AdminStartupPage extends TestBase {

    @FindBy( xpath = "//div[@class='list-group list-group-flush']//a[1]")
    WebElement adminStartup;

    @FindBy(xpath = "//div[@class='list-group list-group-flush']//a[2]")
    WebElement adminEnterprise;

    @FindBy(xpath = "//div[@class='list-group list-group-flush']//a[3]")
    WebElement adminSettings;

    @FindBy(xpath = "(//button[.='Approve'])[last()]")
    WebElement approveBtn;

    @FindBy(xpath = "//button[@class='btn button-class mr-1 status-btn']")
    WebElement confirmApproveButton;

    @FindBy(xpath = "//button[@class='btn button-class ml-1 status-btn']")
    WebElement cancelBtn;

    @FindBy(xpath = "(//button[.='Reject'])[last()]")
    WebElement rejectBtn;

    @FindBy(xpath = "//button[@class='btn button-class mr-1 status-btn']")
    WebElement confirmRejectButton;

    @FindBy(xpath ="(//button[.='Resend Approve Mail'])[last()]")
    WebElement resendApproveBtn;

    @FindBy(xpath = "//button[@id='logout']")
    WebElement logoutButton;

    @FindBy(xpath = "(//tr[@class='mat-row cdk-row ng-star-inserted']//td[@style='cursor: pointer;'])[last()]")
    WebElement latestUserEntry;

    @FindBy(xpath = "//div[@class='modal fade txf-template show']")
    WebElement expandedView;

    @FindBy(xpath = "//button[@class='startup-title btn addusecase-btn']")
    WebElement addStartupCompanyButton;


    public AdminStartupPage() {
        PageFactory.initElements(driver, this);
    }


    public void clickAdminSettings(){
        adminSettings.click();
        Log.info("clicked on adminSettings");
    }

    public void verifyExpandedView() throws Exception {
        ngDriver.waitForAngularRequestsToFinish();
        latestUserEntry.click();
        Log.info("clicked on latestUserEntry");
        ngDriver.waitForAngularRequestsToFinish();
        commonMethods.hitEscape();
        Log.info("clicked on escape button");
        ngDriver.waitForAngularRequestsToFinish();
    }

    public void clickOnApproveBtn(){
        ngDriver.waitForAngularRequestsToFinish();
        ExplicitWaits.explicitWaitVisibilityOfElement(approveBtn,30);
        approveBtn.click();
        Log.info("clicked on approveBtn");
    }

    public void confirmApproval(){
        ngDriver.waitForAngularRequestsToFinish();
        ExplicitWaits.explicitWaitVisibilityOfElement(confirmApproveButton,30);
        confirmApproveButton.click();
        Log.info("clicked on confirm approveBtn");
        ExplicitWaits.explicitWaitVisibilityOfElement(resendApproveBtn,30);
        verify.assertTrue(resendApproveBtn.isDisplayed());
    }

    public void clickOnRejectBtn(){
        ngDriver.waitForAngularRequestsToFinish();
        ExplicitWaits.explicitWaitVisibilityOfElement(rejectBtn,30);
        rejectBtn.click();
        Log.info("clicked on rejectBtn");
    }

    public void confirmRejectBtn(){
        ngDriver.waitForAngularRequestsToFinish();
        ExplicitWaits.explicitWaitVisibilityOfElement(confirmRejectButton,30);
        confirmRejectButton.click();
        Log.info("clicked on confirm reject Button");
    }

    public void clickOnCancelBtn(){
        ExplicitWaits.explicitWaitVisibilityOfElement(cancelBtn,30);
        cancelBtn.click();
        Log.info("clicked on cancelBtn");
    }

    public void clickOnResendApproveBtn(){
        resendApproveBtn.click();
        Log.info("clicked on resendApproveBtn");
    }




    public void clickOnLogoutBtn(){
        logoutButton.click();
        Log.info("clicked on logoutButton");
        ngDriver.waitForAngularRequestsToFinish();
    }



    public void clickOnAdminStartup(){
        adminStartup.click();
        Log.info("clicked on adminStartup");
    }

    public void clickOnAdminEnterprise(){
        adminEnterprise.click();
        Log.info("clicked on adminEnterprise");
    }

    public void approveUser(String companyName){
        WebElement approve= driver.findElement(By.xpath("//mat-row[contains(.,'"+companyName+"')]//mat-cell[last()]//button[1]"));
        ExplicitWaits.explicitWaitVisibilityOfElement(approve,30);
        approve.click();
        Log.info("clicked on approve Button");
        ExplicitWaits.waitTillAngularRequestIsFinish();
        ExplicitWaits.explicitWaitVisibilityOfElement(confirmApproveButton,30);
        confirmApproveButton.click();
        Log.info("clicked on approveBtn from popup");
    }

    public void rejectUser(String companyName){
        WebElement reject= driver.findElement(By.xpath("//mat-row[contains(.,'"+companyName+"')]//mat-cell[7]//button[1]"));
        reject.click();
        Log.info("clicked on reject Button");
        ngDriver.waitForAngularRequestsToFinish();
        ExplicitWaits.explicitWaitVisibilityOfElement(confirmRejectButton,30);
        confirmApproveButton.click();
        Log.info("clicked on reject button from popup");
    }

    public void verifyCompanyExpandedView(String companyName){
        WebElement company= driver.findElement(By.xpath("//mat-row[contains(.,'"+companyName+"')]//mat-cell[2]"));
        company.click();
        Log.info("clicked on company name");
        Assert.assertTrue(expandedView.isDisplayed());
    }

    public void verifyCompanyWebsiteOpensInNewTab(String companyName){
        WebElement companyWebsite= driver.findElement(By.xpath("//mat-row[contains(.,'"+companyName+"')]//mat-cell[3]//a"));

        String adminWindow = driver.getWindowHandle();
        companyWebsite.click();
        Log.info("clicked on company website");
        String companyWeb= driver.getWindowHandle();
        driver.switchTo().window(adminWindow);
        Log.info("switched to admin panel");
    }

    public void clickOnAddStartupCompanyButton(){
        addStartupCompanyButton.click();
        Log.info("clicked on admin startup page add company button");
    }



}
