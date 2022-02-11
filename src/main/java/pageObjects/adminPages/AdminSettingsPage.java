package pageObjects.adminPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reusableComponents.ExplicitWaits;
import reusableComponents.Log;
import testBase.TestBase;

public class AdminSettingsPage extends TestBase {

    @FindBy(xpath = "//input[@id='max_soln_matches']")
    WebElement maxSolutionField;

    @FindBy(xpath = "//input[@id='max_days_matches']")
    WebElement maxDaysMatchesField;

    @FindBy(xpath = "//input[@id='match_interval_hours']")
    WebElement CronJobIntervalField;

    @FindBy(xpath = "//input[@id='thres_for_match']")
    WebElement thresholdForMatchField;

    @FindBy(xpath = "//input[@id='title_uc_weight']")
    WebElement titleWtg;

    @FindBy(xpath = "//input[@id='industries_uc_weight']")
    WebElement industriesWtg;

    @FindBy(xpath = "//input[@id='functions_uc_weight']")
    WebElement functionsWtg;

    @FindBy(xpath = "//input[@id='technologies_uc_weight']")
    WebElement techWtg;

    @FindBy(xpath = "//input[@id='tags_uc_weight']")
    WebElement tagsWtg;

    @FindBy(xpath = "//input[@id='short_description_uc_weight']")
    WebElement shortDescriptionWtg;

    @FindBy(xpath = "//input[@id='long_description_uc_weight']")
    WebElement longDescriptionWtg;

    @FindBy(xpath = "//input[@id='title_s_weight']")
    WebElement titleSWtg;

    @FindBy(xpath = "//input[@id='industries_s_weight']")
    WebElement industriesSWtg;

    @FindBy(xpath = "//input[@id='functions_s_weight']")
    WebElement functionsSWtg;

    @FindBy(xpath = "//input[@id='technologies_s_weight']")
    WebElement techSWtg;

    @FindBy(xpath = "//input[@id='tags_s_weight']")
    WebElement tagsSWtg;

    @FindBy(xpath = "//input[@id='solution_summary_s_weight']")
    WebElement solutionSummarySWtg;

    @FindBy(xpath = "//input[@id='key_customers_s_weight']")
    WebElement keyCXWtg;

    @FindBy(xpath = "//input[@id='annual_recurring_revenue_s_weight']")
    WebElement ARRWtg;

    @FindBy(xpath = "//input[@id='pain_points_addressed_s_weight']")
    WebElement painPtsWtg;

    @FindBy(xpath = "//input[@id='maturity_stage_s_weight']")
    WebElement maturityWtg;

    @FindBy(xpath = "//button[normalize-space()='SAVE']")
    WebElement saveChanges;

    @FindBy(xpath = "//button[@id='logout']")
    WebElement logoutButton;

    @FindBy(xpath = "(//a[@class='list-group-item list-group-item-action corporate mr-5 inactive'])[1]")
    WebElement adminSettings;

    @FindBy( xpath = "//a[normalize-space()='Startup']")
    WebElement adminStartup;

    @FindBy(xpath = "//a[normalize-space()='Corporate']")
    WebElement adminEnterprise;


    public AdminSettingsPage() {
        PageFactory.initElements(driver, this);
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

    public void maxSolutionField(String maxSolution) {
        maxSolutionField.clear();
        maxSolutionField.sendKeys(maxSolution);
        Log.info("User Entered maxSolutionField");
    }

    public void maxDaysMatchesField(String maxDaysMatches) {
        maxDaysMatchesField.clear();
        maxDaysMatchesField.sendKeys(maxDaysMatches);
        Log.info("User Entered maxDaysMatchesField");
    }

    public void CronJobIntervalField(String CronJobInterval) {
        CronJobIntervalField.clear();
        CronJobIntervalField.sendKeys(CronJobInterval);
        Log.info("User entered CronJobIntervalField");
    }

    public void thresholdForMatchField(String thresholdForMatch) {
        thresholdForMatchField.clear();
        thresholdForMatchField.sendKeys(thresholdForMatch);
        Log.info("User Entered thresholdForMatchField");
    }

    public void titleSWtg(String titleWtgVal) {
        titleSWtg.clear();
        titleSWtg.sendKeys(titleWtgVal);
        Log.info("User Entered startup title Wtg");
    }

    public void industriesSWtg(String industriesSWtgVal) {
        industriesSWtg.clear();
        industriesSWtg.sendKeys(industriesSWtgVal);
        Log.info("User entered industries Wtg");
    }

    public void functionsSWtg(String functionsSWtgVal) {
        functionsSWtg.clear();
        functionsSWtg.sendKeys(functionsSWtgVal);
        Log.info("User Entered functions Wtg");
    }

    public void techSWtg(String techSWtgVal) {
        techSWtg.clear();
        techSWtg.sendKeys(techSWtgVal);
        Log.info("User Entered tech Wtg");
    }

    public void tagsSWtg(String tagsSWtgVal) {
        tagsSWtg.clear();
        tagsSWtg.sendKeys(tagsSWtgVal);
        Log.info("User entered tags Wtg");
    }

    public void solutionSummarySWtg(String solutionSummarySWtgVal) {
        solutionSummarySWtg.clear();
        solutionSummarySWtg.sendKeys(solutionSummarySWtgVal);
        Log.info("User Entered solutionSummary Wtg");
    }

    public void keyCXWtg(String keyCXWtgVal) {
        keyCXWtg.clear();
        keyCXWtg.sendKeys(keyCXWtgVal);
        Log.info("User Entered keyCXWtg");
    }

    public void ARRWtg(String ARRWtg1) {
        ARRWtg.clear();
        ARRWtg.sendKeys(ARRWtg1);
        Log.info("User entered ARRWtg");
    }

    public void painPtsWtg(String painPtsWtg1) {
        painPtsWtg.clear();
        painPtsWtg.sendKeys(painPtsWtg1);
        Log.info("User entered painPtsWtg");
    }

    public void maturityWtg(String maturityWtg1) {
        maturityWtg.clear();
        maturityWtg.sendKeys(maturityWtg1);
        Log.info("User entered maturityWtg");
    }

    public void saveChanges() {
        saveChanges.click();

        Log.info("User clicked saveChanges");
    }


    public void clickOnLogoutBtn(){
        logoutButton.click();
        Log.info("clicked on logoutButton");
        ExplicitWaits.waitForPageToLoad();
    }
}
