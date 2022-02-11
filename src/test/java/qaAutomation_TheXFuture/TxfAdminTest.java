package qaAutomation_TheXFuture;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObjects.onboardingPages.EnterpriseProfilePage;
import reusableComponents.ExplicitWaits;
import reusableComponents.PropertiesOperations;
import testBase.TestBase;

public class TxfAdminTest extends TestBase {


    String firstUser = "testuser1admintest@tafmail.com";// for complete first user on-boarding
    String secondUser = "testuser2admintest@tafmail.com";// for complete second user on-boarding


    @AfterMethod
    public void clearUsers() throws Exception {
        commonMethods.clearUserData(firstUser);
    }

    @Test
    public void enterpriseApprovalTest() throws Exception {
        commonMethods.clearUserData(firstUser);

        txfLoginPage.clickSignUpButton();
        txfSignUpPage.enterEmail(firstUser);
        txfSignUpPage.enterPassword(PropertiesOperations.getPropertyValueByKey("enterprisePassword"));
        txfSignUpPage.clickSignup();
        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(firstUser);
        String link = commonMethods.fetchEmailLink(firstUser);
        driver.get(link);
         ExplicitWaits.waitTillAngularRequestIsFinish();
        Assert.assertTrue(otpVerificationPage.homepageDisplayed());
        //on-boarding process
        entitySelectionPage.clickOnEnterpriseTile();
        ExplicitWaits.waitTillAngularRequestIsFinish();
//        String filePath =  System.getProperty("user.dir")+"/src/test/resources/testData/logo.jpg";
//        enterpriseProfilePage.uploadCompanyLogo(filePath);
        //verify with all valid fields
        enterpriseProfilePage.setNameField(commonMethods.generateRandomString(8));
        enterpriseProfilePage.setDesignationField(commonMethods.generateRandomString(5));
        enterpriseProfilePage.setLinkedinIdField("https://www.linkedin.com/in/thexfuture");
        String companyName = commonMethods.generateRandomString(6);
        enterpriseProfilePage.setCompanyNameField(companyName);
        enterpriseProfilePage.setCompanyWebsiteField(commonMethods.generateRandomUrls());
        enterpriseProfilePage.setCompanyLocationField("Mumbai");
        enterpriseProfilePage.clickOnNextButton();

        enterpriseUsecasePage.clickOnChooseFromTemplates();
        enterpriseUsecasePage.previewTemplate();
        enterpriseUsecasePage.clickOnUseTemplate();
        commonMethods.scrollPageDown(driver);
        enterpriseUsecasePage.clickOnPreviewButton();
        commonMethods.scrollPageDown(driver);
        enterpriseUsecasePage.clickOnSubmitButton();
        enterpriseUsecasePage.verifyOnboardingCompleted();
        enterpriseUsecasePage.clickOnLogout();

        //Approve user from admin
        txfLoginPage.loginToAdminPanel();
        adminStartupPage.clickOnAdminEnterprise();
        adminEnterprisePage.approveUser(companyName);
        driver.navigate().refresh();
        adminEnterprisePage.clickOnLogoutBtn();

        txfLoginPage.enterEmail(firstUser);
        txfLoginPage.enterPassword("Password@123");
        txfLoginPage.clickLoginButton();

        userApprovalPage.clickOnTakeToMyAccount();

    }

    @Test
    public void secondUserSignUpForApprovedEnterpriseDomainTest() throws Exception {
        commonMethods.clearUserData(firstUser);

        txfLoginPage.clickSignUpButton();
        txfSignUpPage.enterEmail(firstUser);
        txfSignUpPage.enterPassword(PropertiesOperations.getPropertyValueByKey("enterprisePassword"));
        txfSignUpPage.clickSignup();
        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(firstUser);
        String link = commonMethods.fetchEmailLink(firstUser);
        driver.get(link);
         ExplicitWaits.waitTillAngularRequestIsFinish();
        Assert.assertTrue(otpVerificationPage.homepageDisplayed());
        //on-boarding process
        entitySelectionPage.clickOnEnterpriseTile();
//        String filePath =  System.getProperty("user.dir")+"/src/test/resources/testData/logo.jpg";
//        enterpriseProfilePage.uploadCompanyLogo(filePath);
         ExplicitWaits.waitTillAngularRequestIsFinish();
        //verify with all valid fields
        enterpriseProfilePage.setNameField(faker.name().firstName());
        enterpriseProfilePage.setDesignationField(faker.company().profession());
        enterpriseProfilePage.setLinkedinIdField("https://www.linkedin.com/in/thexfuture");
        String companyName = commonMethods.generateRandomString(6);
        enterpriseProfilePage.setCompanyNameField(companyName);
        enterpriseProfilePage.setCompanyWebsiteField(commonMethods.generateRandomUrls());
        enterpriseProfilePage.setCompanyLocationField("Mumbai");
        enterpriseProfilePage.clickOnNextButton();

        enterpriseUsecasePage.clickOnChooseFromTemplates();
        enterpriseUsecasePage.previewTemplate();
        enterpriseUsecasePage.clickOnUseTemplate();
        commonMethods.scrollPageDown(driver);
        enterpriseUsecasePage.clickOnPreviewButton();
        commonMethods.scrollPageDown(driver);
        enterpriseUsecasePage.clickOnSubmitButton();
        enterpriseUsecasePage.verifyOnboardingCompleted();
        enterpriseUsecasePage.clickOnLogout();
        //approve user from admin panel
        txfLoginPage.loginToAdminPanel();
        adminStartupPage.clickOnAdminEnterprise();
        adminEnterprisePage.approveUser(companyName);
        driver.navigate().refresh();
        adminEnterprisePage.clickOnLogoutBtn();
         ExplicitWaits.waitTillAngularRequestIsFinish();

        //second user sign up
        txfLoginPage.clickSignUpButton();
        txfSignUpPage.enterEmail(secondUser);
        txfSignUpPage.enterPassword(PropertiesOperations.getPropertyValueByKey("enterprisePassword"));
        txfSignUpPage.clickSignup();
        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(secondUser);
        String otpLink = commonMethods.fetchEmailLink(secondUser);
        driver.get(otpLink);
         ExplicitWaits.waitTillAngularRequestIsFinish();

        //onboardPage.verifyCompanyName(companyName);
        onboardPage.clickOnContinue();
         ExplicitWaits.waitTillAngularRequestIsFinish();
        EnterpriseProfilePage profilePage=new EnterpriseProfilePage();
        profilePage.setNameField(commonMethods.generateRandomString(8));
        profilePage.setDesignationField(commonMethods.generateRandomString(5));
        profilePage.setLinkedinIdField("https://www.linkedin.com/in/thexfuture");
        enterpriseProfilePage.verifyCompanyNameFieldDisabled();
        enterpriseProfilePage.verifyCompanyLocationFieldDisabled();
        enterpriseProfilePage.verifyCompanyWebsiteFieldDisabled();
        enterpriseProfilePage.clickOnNextButton();
        driver.navigate().refresh();
         ExplicitWaits.waitTillAngularRequestIsFinish();
        enterpriseUsecasePage.clickOnChooseFromTemplates();
        enterpriseUsecasePage.previewTemplate();
        enterpriseUsecasePage.clickOnUseTemplate();
        commonMethods.scrollPageDown(driver);
        enterpriseUsecasePage.clickOnPreviewButton();
        commonMethods.scrollPageDown(driver);
        enterpriseUsecasePage.clickOnSubmitButton();

        //verify dashboard is displayed

    }

    @Test
    public void userRejectionTest() throws Exception {
        commonMethods.clearUserData(firstUser);

        txfLoginPage.clickSignUpButton();
        txfSignUpPage.enterEmail(firstUser);
        txfSignUpPage.enterPassword(PropertiesOperations.getPropertyValueByKey("enterprisePassword"));
        txfSignUpPage.clickSignup();
        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(firstUser);
        String link = commonMethods.fetchEmailLink(firstUser);
        driver.get(link);
         ExplicitWaits.waitTillAngularRequestIsFinish();
        Assert.assertTrue(otpVerificationPage.homepageDisplayed());

        //on-boarding process
        entitySelectionPage.clickOnEnterpriseTile();

//        String filePath =  System.getProperty("user.dir")+"/src/test/resources/testData/logo.jpg";
//        enterpriseProfilePage.uploadCompanyLogo(filePath);
         ExplicitWaits.waitTillAngularRequestIsFinish();

        //Verify all mandatory fields
        enterpriseProfilePage.clickOnNextButton();
        enterpriseProfilePage.verifyEmptyNameFieldError();
        enterpriseProfilePage.verifyEmptyDesignationFieldError();
        //entitySelectionPage.verifyEmptyLinkedinFieldError();
        enterpriseProfilePage.verifyEmptyCompanyNameFieldError();
        enterpriseProfilePage.verifyEmptyCompanyLocationFieldError();
        enterpriseProfilePage.verifyEmptyCompanyWebsiteFieldError();

        //verify validation on linkedin & Company website Fields
        enterpriseProfilePage.setNameField(faker.name().firstName());
        enterpriseProfilePage.setDesignationField(faker.company().profession());
        enterpriseProfilePage.setLinkedinIdField("https://www.facebook.com/"+faker.name().username());
        enterpriseProfilePage.setCompanyNameField(faker.company().name());
        enterpriseProfilePage.setCompanyLocationField("Pune");
        enterpriseProfilePage.setCompanyWebsiteField("demosite.");
        enterpriseProfilePage.clickOnNextButton();
        enterpriseProfilePage.verifyInvalidLinkedinFieldError();
        enterpriseProfilePage.verifyInvalidCompanyWebsiteFieldError();

        //verify with all valid fields
        enterpriseProfilePage.setNameField(faker.name().firstName());
        enterpriseProfilePage.setDesignationField(faker.company().profession());
        enterpriseProfilePage.setLinkedinIdField("https://www.linkedin.com/in/thexfuture");
        String companyName = commonMethods.generateRandomString(6);
        enterpriseProfilePage.setCompanyNameField(companyName);
        enterpriseProfilePage.setCompanyWebsiteField(commonMethods.generateRandomUrls());
        enterpriseProfilePage.setCompanyLocationField("Mumbai");
        enterpriseProfilePage.clickOnNextButton();

        commonMethods.scrollPageDown(driver);

        enterpriseUsecasePage.setUseCaseTitleField("Machine anomaly detection and predictive maintenance");

        enterpriseUsecasePage.setIndustryField("any Industry");
        enterpriseUsecasePage.setFunctionField("IT ");
        enterpriseUsecasePage.setTechnologiesField("3D Printing");
        enterpriseUsecasePage.setTagsField("Storage Automation");

        enterpriseUsecasePage.setProblemStatementField(faker.lorem().sentence(100));
        enterpriseUsecasePage.setDescriptionField(faker.lorem().sentence(100));

        enterpriseUsecasePage.clickOnChooseFromTemplates();
        enterpriseUsecasePage.previewTemplate();
        enterpriseUsecasePage.clickOnUseTemplate();
        commonMethods.scrollPageDown(driver);
        enterpriseUsecasePage.clickOnPreviewButton();
        commonMethods.scrollPageDown(driver);
        enterpriseUsecasePage.clickOnSubmitButton();
        enterpriseUsecasePage.verifyOnboardingCompleted();
        userApprovalPage.verifyApprovalIsPending();
        enterpriseUsecasePage.clickOnLogout();

        //Reject user from admin panel
        txfLoginPage.loginToAdminPanel();
        adminStartupPage.clickOnAdminEnterprise();
        adminEnterprisePage.rejectUser(companyName);
        driver.navigate().refresh();
        adminEnterprisePage.clickOnLogoutBtn();

        txfLoginPage.enterEmail(firstUser);
        txfLoginPage.enterPassword("Password@123");
        txfLoginPage.clickLoginButton();
    }

    @Test
    public void secondUserSignUpForRejectedDomainTest() throws Exception {
        commonMethods.clearUserData(firstUser);

        txfLoginPage.clickSignUpButton();
        txfSignUpPage.enterEmail(firstUser);
        txfSignUpPage.enterPassword(PropertiesOperations.getPropertyValueByKey("enterprisePassword"));
        txfSignUpPage.clickSignup();
        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(firstUser);
        String link = commonMethods.fetchEmailLink(firstUser);
        driver.get(link);
         ExplicitWaits.waitTillAngularRequestIsFinish();
        Assert.assertTrue(otpVerificationPage.homepageDisplayed());

        //on-boarding process
        entitySelectionPage.clickOnEnterpriseTile();

//        String filePath =  System.getProperty("user.dir")+"/src/test/resources/testData/logo.jpg";
//        enterpriseProfilePage.uploadCompanyLogo(filePath);
         ExplicitWaits.waitTillAngularRequestIsFinish();

        //Verify all mandatory fields
        enterpriseProfilePage.clickOnNextButton();
        enterpriseProfilePage.verifyEmptyNameFieldError();
        enterpriseProfilePage.verifyEmptyDesignationFieldError();
        //entitySelectionPage.verifyEmptyLinkedinFieldError();
        enterpriseProfilePage.verifyEmptyCompanyNameFieldError();
        enterpriseProfilePage.verifyEmptyCompanyLocationFieldError();
        enterpriseProfilePage.verifyEmptyCompanyWebsiteFieldError();

        //verify validation on linkedin & Company website Fields
        enterpriseProfilePage.setNameField(faker.name().firstName());
        enterpriseProfilePage.setDesignationField(faker.company().profession());
        enterpriseProfilePage.setLinkedinIdField("https://www.facebook.com/"+faker.name().username());
        enterpriseProfilePage.setCompanyNameField(faker.company().name());
        enterpriseProfilePage.setCompanyLocationField("Pune");
        enterpriseProfilePage.setCompanyWebsiteField("demosite.");
        enterpriseProfilePage.clickOnNextButton();
        enterpriseProfilePage.verifyInvalidLinkedinFieldError();
        enterpriseProfilePage.verifyInvalidCompanyWebsiteFieldError();

        //verify with all valid fields
        enterpriseProfilePage.setNameField(faker.name().firstName());
        enterpriseProfilePage.setDesignationField(faker.company().profession());
        enterpriseProfilePage.setLinkedinIdField("https://www.linkedin.com/in/thexfuture");
        String companyName = commonMethods.generateRandomString(6);
        enterpriseProfilePage.setCompanyNameField(companyName);
        enterpriseProfilePage.setCompanyWebsiteField(commonMethods.generateRandomUrls());
        enterpriseProfilePage.setCompanyLocationField("Mumbai");
        enterpriseProfilePage.clickOnNextButton();

        commonMethods.scrollPageDown(driver);

        enterpriseUsecasePage.setUseCaseTitleField("Machine anomaly detection and predictive maintenance");

        enterpriseUsecasePage.setIndustryField("any Industry");
        enterpriseUsecasePage.setFunctionField("IT ");
        enterpriseUsecasePage.setTechnologiesField("3D Printing");
        enterpriseUsecasePage.setTagsField("Storage Automation");

        enterpriseUsecasePage.setProblemStatementField(faker.lorem().sentence(100));
        enterpriseUsecasePage.setDescriptionField(faker.lorem().sentence(100));

        enterpriseUsecasePage.clickOnChooseFromTemplates();
        enterpriseUsecasePage.previewTemplate();
        enterpriseUsecasePage.clickOnUseTemplate();
        commonMethods.scrollPageDown(driver);
        enterpriseUsecasePage.clickOnPreviewButton();
        commonMethods.scrollPageDown(driver);
        enterpriseUsecasePage.clickOnSubmitButton();
        enterpriseUsecasePage.verifyOnboardingCompleted();
        userApprovalPage.verifyApprovalIsPending();
        enterpriseUsecasePage.clickOnLogout();

        //Reject user from admin panel
        txfLoginPage.loginToAdminPanel();
        adminStartupPage.clickOnAdminEnterprise();
        adminEnterprisePage.rejectUser(companyName);
        driver.navigate().refresh();
        adminEnterprisePage.clickOnLogoutBtn();

        //second user sign up after first user is rejected
        txfLoginPage.clickSignUpButton();
        txfSignUpPage.enterEmail(secondUser);
        txfSignUpPage.enterPassword(PropertiesOperations.getPropertyValueByKey("enterprisePassword"));
        txfSignUpPage.clickSignup();

        //verify message
    }



    @Test
    public void adminPanelTest() throws Exception {

        commonMethods.clearUserData(firstUser);

        txfLoginPage.clickSignUpButton();
        txfSignUpPage.enterEmail(firstUser);
        txfSignUpPage.enterPassword(PropertiesOperations.getPropertyValueByKey("enterprisePassword"));
        txfSignUpPage.clickSignup();
        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(firstUser);
        String link = commonMethods.fetchEmailLink(firstUser);
        driver.get(link);
        ExplicitWaits.waitTillAngularRequestIsFinish();
        Assert.assertTrue(otpVerificationPage.homepageDisplayed());
        //on-boarding process
        entitySelectionPage.clickOnEnterpriseTile();
//        String filePath =  System.getProperty("user.dir")+"/src/test/resources/testData/logo.jpg";
//        enterpriseProfilePage.uploadCompanyLogo(filePath);
         ExplicitWaits.waitTillAngularRequestIsFinish();
        //verify with all valid fields
        enterpriseProfilePage.setNameField(faker.name().firstName());
        enterpriseProfilePage.setDesignationField(faker.company().profession());
        enterpriseProfilePage.setLinkedinIdField("https://www.linkedin.com/in/thexfuture");
        String companyName = commonMethods.generateRandomString(6);
        enterpriseProfilePage.setCompanyNameField(companyName);
        enterpriseProfilePage.setCompanyWebsiteField(commonMethods.generateRandomUrls());
        enterpriseProfilePage.setCompanyLocationField("Mumbai");
        enterpriseProfilePage.clickOnNextButton();

        enterpriseUsecasePage.clickOnChooseFromTemplates();
        enterpriseUsecasePage.previewTemplate();
        enterpriseUsecasePage.clickOnUseTemplate();
        commonMethods.scrollPageDown(driver);
        enterpriseUsecasePage.clickOnPreviewButton();
        commonMethods.scrollPageDown(driver);
        enterpriseUsecasePage.clickOnSubmitButton();
        enterpriseUsecasePage.verifyOnboardingCompleted();
        enterpriseUsecasePage.clickOnLogout();
        
        txfLoginPage.loginToAdminPanel();
        adminStartupPage.clickOnAdminEnterprise();
        adminStartupPage.verifyCompanyWebsiteOpensInNewTab(companyName); ////////<<<<<
        adminStartupPage.verifyCompanyExpandedView(companyName);
    }


//    @Test
    public void adminSettingsPageTest() throws Exception{

        txfLoginPage.enterEmail(PropertiesOperations.getPropertyValueByKey("adminUser"));//email
        txfLoginPage.enterPassword(PropertiesOperations.getPropertyValueByKey("adminPass"));//newPassword
        txfLoginPage.clickLoginButton();

        adminStartupPage.clickAdminSettings();

        adminSettingsPage.maxSolutionField("900");
        adminSettingsPage.maxDaysMatchesField("15");
        adminSettingsPage.CronJobIntervalField("6");
        adminSettingsPage.thresholdForMatchField("70");
        adminSettingsPage.titleSWtg("4");
        adminSettingsPage.industriesSWtg("4");
        adminSettingsPage.functionsSWtg("4");
        adminSettingsPage.techSWtg("4");
        adminSettingsPage.tagsSWtg("4");
        adminSettingsPage.solutionSummarySWtg("3");
        adminSettingsPage.keyCXWtg("2");
        adminSettingsPage.ARRWtg("2");
        adminSettingsPage.painPtsWtg("2");
        adminSettingsPage.maturityWtg("2");
        adminSettingsPage.saveChanges();
    }


}
