package qaAutomation_TheXFuture;

import org.openqa.selenium.html5.WebStorage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pageObjects.onboardingPages.EnterpriseProfilePage;
import pageObjects.onboardingPages.StartupProfilePage;
import reusableComponents.ExplicitWaits;
import reusableComponents.PropertiesOperations;
import testBase.TestBase;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class EnterpriseSmokeTest extends TestBase {

    static String companyName = "Enterprise Test Company";
    static String email = "txfmoduletest@robot-mail.com"; //for individual module test
    String firstUser = "testuser1enterprisetest@robot-mail.com";// for complete first user on-boarding
    String secondUser = "testuser2enterprisetest@robot-mail.com";// for complete second user on-boarding
    String invitedUser = "testuser3enterprisetest@robot-mail.com";

    static String startupMail = "testuser1startuptest@vomoto.com";


    @AfterTest
    public void clearUsers() throws Exception {
        commonMethods.clearUserData(email);
        commonMethods.clearUserData(firstUser);
        commonMethods.clearUserData(secondUser);
        commonMethods.clearUserData(invitedUser);
    }


    @Test(priority = 1,groups = {"Smoke"})
    public void signUpTest() throws Exception {

        commonMethods.clearUserData(firstUser);

        txfLoginPage.clickSignUpButton();
        // Try to sign up without email and password
        txfSignUpPage.enterEmail("");
        txfSignUpPage.enterPassword("");
        txfSignUpPage.clickSignup();
        txfSignUpPage.verifyEmptyEmailFieldError();
        txfSignUpPage.verifyEmptyPasswordFieldError();

        // Try to sign up without invalid email and invalid password
        txfSignUpPage.enterEmail(faker.name().username()+".com");
        txfSignUpPage.enterPassword("pass123");
        txfSignUpPage.clickSignup();
        txfSignUpPage.verifyInvalidEmailFieldError();
        txfSignUpPage.verifyInvalidPasswordFieldError();

        // Try to sign up without invalid email and valid password
        txfSignUpPage.enterEmail(faker.name().username()+".com");
        txfSignUpPage.enterPassword("Password@123");
        txfSignUpPage.clickSignup();
        txfSignUpPage.verifyInvalidEmailFieldError();

        // Try to sign up without valid email and invalid password
        txfSignUpPage.enterEmail(firstUser);
        txfSignUpPage.enterPassword("pass123");
        txfSignUpPage.clickSignup();

        //try sign up with personal mail address Ex : gmail, yahoo etc
        txfSignUpPage.enterEmail(commonMethods.GenerateRandomEMAILIDs("gmail.com"));
        txfSignUpPage.enterPassword("Password@123");
        txfSignUpPage.clickSignup();
        Assert.assertTrue(txfSignUpPage.verifyPersonalMailError());

        // Try to sign up with valid email and valid password
        txfSignUpPage.enterEmail(firstUser);
        txfSignUpPage.enterPassword(PropertiesOperations.getPropertyValueByKey("enterprisePassword"));

        txfSignUpPage.clickSignup();
        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(firstUser);

        String link = commonMethods.fetchEmailLink(firstUser);
        String sp = link.split("&")[0];
        String otp = sp.split("=")[1];

        otpVerificationPage.enterOtp(otp);
        otpVerificationPage.clickOnVerifyButton();
        Assert.assertTrue(otpVerificationPage.homepageDisplayed());
    }

    @Test(dependsOnMethods = "signUpTest")
    public void loginTest() {

        // Try to sign in without email and password
        txfLoginPage.enterEmail("");
        txfLoginPage.enterPassword("");
        txfLoginPage.clickLoginButton();
        // txfLoginPage.verifyEmptyEmailFieldError();
        txfLoginPage.verifyEmptyPasswordFieldError();

        // Try to sign in without invalid email and password
        txfLoginPage.enterEmail("demoemail.com");
        txfLoginPage.enterPassword("Password@23");
        txfLoginPage.clickLoginButton();
        //txfLoginPage.verifyInvalidEmailFieldError();

        // Try to sign in without valid email and invalid password (less than 8 characters)
        txfLoginPage.enterEmail("demoemail@mail7.com");
        txfLoginPage.enterPassword("pass123");
        txfLoginPage.clickLoginButton();
        //txfLoginPage.verifyInvalidPasswordFieldError();

        //login with valid email and valid password
        txfLoginPage.enterEmail(firstUser);
        txfLoginPage.enterPassword("Password@123");
        txfLoginPage.clickLoginButton();
        Assert.assertTrue(txfLoginPage.homepageDisplayed());
    }

    @Test(dependsOnMethods = "loginTest")
    public void enterpriseFirstUserOnboard() throws InterruptedException, AWTException {
        //login with valid email and valid password
        txfLoginPage.enterEmail(firstUser);
        txfLoginPage.enterPassword("Password@123");
        txfLoginPage.clickLoginButton();
        Assert.assertTrue(txfLoginPage.homepageDisplayed());

        //on-boarding process
        entitySelectionPage.clickOnEnterpriseTile();

        String filePath =  System.getProperty("user.dir")+"/src/test/resources/testData/logo.jpg";
        enterpriseProfilePage.uploadCompanyLogo(filePath);

        //verify with all valid fields
        ExplicitWaits.waitTillAngularRequestIsFinish();
        enterpriseProfilePage.setNameField(faker.name().firstName());
        enterpriseProfilePage.setDesignationField(faker.company().profession());
        enterpriseProfilePage.setLinkedinIdField("https://www.linkedin.com/in/thexfuture");
        companyName = commonMethods.generateRandomString(6);
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
        enterpriseUsecasePage.clickOnLogout();
    }

    @Test(dependsOnMethods = "enterpriseFirstUserOnboard")
    public void approveUser() throws Exception {
        //approve user from admin panel
        txfLoginPage.loginToAdminPanel();
        adminStartupPage.clickOnAdminEnterprise();
        adminEnterprisePage.approveUser(companyName);
        driver.navigate().refresh();
        adminEnterprisePage.clickOnLogoutBtn();
    }

    @Test(dependsOnMethods = "approveUser")
    public void enterpriseSecondUserOnboard() throws Exception {

        txfLoginPage.clickSignUpButton();
        // Try to sign up with valid email and valid password
        txfSignUpPage.enterEmail(secondUser);
        txfSignUpPage.enterPassword(PropertiesOperations.getPropertyValueByKey("enterprisePassword"));

        txfSignUpPage.clickSignup();
        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(secondUser);

        String link = commonMethods.fetchEmailLink(secondUser);
        String sp = link.split("&")[0];
        String otp = sp.split("=")[1];

        otpVerificationPage.enterOtp(otp);
        otpVerificationPage.clickOnVerifyButton();
//        Assert.assertTrue(otpVerificationPage.homepageDisplayed());

        //on-boarding process
//        onboardPage.verifyCompanyName(companyName);
        onboardPage.clickOnContinue();
        ngDriver.waitForAngularRequestsToFinish();
        EnterpriseProfilePage profilePage=new EnterpriseProfilePage();
        profilePage.setNameField(commonMethods.generateRandomString(8));
        profilePage.setDesignationField(commonMethods.generateRandomString(5));
        profilePage.setLinkedinIdField("https://www.linkedin.com/in/thexfuture");
        enterpriseProfilePage.verifyCompanyNameFieldDisabled();
        enterpriseProfilePage.verifyCompanyLocationFieldDisabled();
        enterpriseProfilePage.verifyCompanyWebsiteFieldDisabled();
        enterpriseProfilePage.clickOnNextButton();
        driver.navigate().refresh();    //ToDo : remove it after test is stable
        ngDriver.waitForAngularRequestsToFinish();
        enterpriseUsecasePage.clickOnChooseFromTemplates();
        enterpriseUsecasePage.previewTemplate();
        enterpriseUsecasePage.clickOnUseTemplate();
        commonMethods.scrollPageDown(driver);

        enterpriseUsecasePage.clickOnPreviewButton();
        commonMethods.scrollPageDown(driver);
        enterpriseUsecasePage.clickOnSubmitButton();

        enterpriseUsecasePage.clickOnLogout();
    }

    @Test(dependsOnMethods = "enterpriseSecondUserOnboard")
    public void invitedUserTest(){
        txfLoginPage.enterEmail(firstUser);
        txfLoginPage.enterPassword("Password@123");
        txfLoginPage.clickLoginButton();

        userApprovalPage.clickOnTakeToMyAccount();
        enterpriseDashboardPage.clickOnUsecaseCollaborators();
        enterpriseDashboardPage.inviteCollaborators(invitedUser);
        driver.navigate().refresh();
        enterpriseDashboardPage.clickOnMyAccountSidebar();
        enterpriseDashboardPage.clickOnLogoutOption();

        String inviteLink = commonMethods.fetchEmailLink(invitedUser);
        driver.get(inviteLink);
        txfSignUpPage.verifyEmailFieldDisabled();
        txfSignUpPage.enterPassword("Password@123");
        txfSignUpPage.clickSignup();
        ExplicitWaits.waitTillAngularRequestIsFinish();
        //invited user sign up
        onboardPage.clickOnContinueInvitedUser();
        ExplicitWaits.waitTillAngularRequestIsFinish();

        enterpriseProfilePage.setNameField(commonMethods.generateRandomString(8));
        enterpriseProfilePage.setDesignationField(commonMethods.generateRandomString(5));
        enterpriseProfilePage.setLinkedinIdField("https://www.linkedin.com/in/thexfuture");
        enterpriseProfilePage.verifyCompanyNameFieldDisabled();
        enterpriseProfilePage.verifyCompanyLocationFieldDisabled();
        enterpriseProfilePage.verifyCompanyWebsiteFieldDisabled();
        enterpriseProfilePage.clickOnNextButton();

        commonMethods.scrollPageDown(driver);

        enterpriseUsecasePage.clickOnSkipAndSubmit();
        ExplicitWaits.waitTillAngularRequestIsFinish();
        userApprovalPage.verifyTakeMeToMyAccountButtonIsDisplayed();
    }


    @Test(dependsOnMethods = "invitedUserTest")
    public void adminAddStartupCompanyTest() throws Exception {
        commonMethods.clearUserData(startupMail);
        txfLoginPage.loginToAdminPanel();

        adminEnterprisePage.clickOnStartup();
        adminStartupPage.clickOnAddStartupCompanyButton();
        ExplicitWaits.waitTillAngularRequestIsFinish();
        entitySelectionPage.clickOnStartUpTile();


        ExplicitWaits.waitTillAngularRequestIsFinish();
        // user profile page
        String filePath = System.getProperty("user.dir") + "/src/test/resources/testData/logo.jpg";
        startupProfilePage.uploadStartUpProfileCompanyLogo(filePath);
        ExplicitWaits.waitTillAngularRequestIsFinish();

        //Verify all mandatory fields
        startupProfilePage.clickOnStartUpNextButton();
        startupProfilePage.startUpVerifyEmptyNameFieldError();
        startupProfilePage.startUpVerifyEmptyDesignationFieldError();

        //verify validation on linkedin  Field
        startupProfilePage.setStartupUserNameField(commonMethods.generateRandomString(10));
        startupProfilePage.setStartupDesignationField(faker.company().profession());
        startupProfilePage.setStartUpLinkedinIdField("https://www.linkedin.com/" + faker.name().username());
        startupProfilePage.setCompanyEmailField(startupMail);
        startupProfilePage.clickOnStartUpNextButton();
        // Company profile page
        String filePath1 = System.getProperty("user.dir") + "/src/test/resources/testData/download.jpg";
        StartupProfilePage create = new StartupProfilePage();
        create.uploadStartUpCompanyLogo(filePath1);

        //verify validation on  Company website Fields
        startupProfilePage.setStartUpCompanyNameField(faker.company().name());
        startupProfilePage.setStartUpCompanyWebsiteField("demosite");
        startupProfilePage.setStartUpArrRevenue("erxtctgvjhj");
        startupProfilePage.startUpVerifyInvalidCompanyWebsiteFieldError();

        //verify with all valid fields
        String startupName = commonMethods.generateRandomString(6).toLowerCase();
        startupProfilePage.setStartUpCompanyNameField(startupName);
        startupProfilePage.setStartUpCompanyWebsiteField(commonMethods.generateRandomUrls());
        startupProfilePage.setStartUpCompanyLocationField("Pune");                               //<<<<<<<<<<<<<<<<<<<
        commonMethods.scrollPageDown(driver);
        startupProfilePage.setStartUpCompanySizeField(2);
        startupProfilePage.setStartUpPaidCustomersField("2: 1-20");
        startupProfilePage.setStartUpKeyCustomer(faker.company().name());
        startupProfilePage.setStartUpFundingRaised(2);
        startupProfilePage.setStartUpArrRevenue(faker.number().digits(5));
        startupProfilePage.setStartUpMaturityStage();
        startupProfilePage.setStartUpRecentFunding();
        startupProfilePage.clickOnStartUpNextButton();

        startupSolutionsPage.setSolutionTitleField(faker.lorem().sentence(10));
        startupSolutionsPage.setSolutionIndustryField("Logistics & Supply Chain");
        startupSolutionsPage.setSolutionFunctionField("Purchasing");
        startupSolutionsPage.setSolutionTechnologiesField("Machine Vision");
        startupSolutionsPage.setSolutionTagsField("Robotic arms");
        startupSolutionsPage.setSolutionSummaryField(faker.lorem().sentence(30));
        startupSolutionsPage.setSolutionPainPointsAddressedField(faker.lorem().sentence(30));

        startupSolutionsPage.addSolutionProductExplainerVideoYoutubeLink("https://www.youtube.com/watch?v=L8tdPYqFV7E&ab_channel=Techquickie");
        startupSolutionsPage.deleteAttachedVideo();
        String videoFile = System.getProperty("user.dir") + "/src/test/resources/testData/sample_video.mp4";
        startupSolutionsPage.uploadSolutionProductExplainerVideoMp4Field(videoFile);
        startupSolutionsPage.setSolutionTestimonialsNameField(commonMethods.generateRandomString(10));
        startupSolutionsPage.setSolutionTestimonialsRoleField(commonMethods.generateRandomString(5));
        startupSolutionsPage.setSolutionTestimonialsField(commonMethods.generateRandomString(30));
        //startupSolutionsPage.clickOnSolutionAddAnotherTestimonialField();
        String docFile = System.getProperty("user.dir") + "/src/test/resources/testData/test.docx";
        startupSolutionsPage.uploadSolutionCaseStudy(docFile);
        startupSolutionsPage.clickOnSolutionPreviewButton();
        commonMethods.scrollPageDown(driver);
        ExplicitWaits.waitTillAngularRequestIsFinish();
        startupSolutionsPage.clickOnSolutionPublishButton();

        adminEnterprisePage.approveUser(startupName);
        driver.navigate().refresh();

        adminEnterprisePage.clickOnLogoutBtn();

        txfLoginPage.enterEmail(startupMail);
        txfLoginPage.enterPassword("Password@123");
        txfLoginPage.clickLoginButton();

        ExplicitWaits.waitTillAngularRequestIsFinish();
        onboardPage.clickOnContinue();
        startupProfilePage.clickOnStartUpNextButton();
        ExplicitWaits.waitTillAngularRequestIsFinish();
        startupProfilePage.clickOnStartUpNextButton();
        commonMethods.scrollPageDown(driver);
        ExplicitWaits.waitTillAngularRequestIsFinish();
        startupSolutionsPage.clickOnSolutionPreviewButton();
        commonMethods.scrollPageDown(driver);
        startupSolutionsPage.clickOnSolutionPublishButton();
        ExplicitWaits.waitTillAngularRequestIsFinish();
        userApprovalPage.clickOnTakeToMyAccount();
    }

    @Test(dependsOnMethods = "adminAddStartupCompanyTest")
    public void publicSharingUsecase(){
        txfLoginPage.enterEmail(firstUser);
        txfLoginPage.enterPassword("Password@123");
        txfLoginPage.clickLoginButton();

        enterpriseDashboardPage.clickOnShareUseCaseIcon();
        enterpriseDashboardPage.verifyUseCaseSharingTabIsVisible();
        enterpriseDashboardPage.clickOnPublicSharingToggleSwitch();
        String publicLink = enterpriseDashboardPage.getPublicLink();

        enterpriseDashboardPage.clickOnMyAccountSidebar();
        enterpriseDashboardPage.clickOnLogoutOption();
        driver.get(publicLink);
        enterpriseDashboardPage.clickOnApplyNowButton();

        txfLoginPage.enterEmail(startupMail);
        txfLoginPage.enterPassword("Password@123");
        txfLoginPage.clickLoginButton();

        startupSolutionsPage.clickOnExistingSolutionButton();
        startupSolutionsPage.applyExistingSolution();
    }
}
