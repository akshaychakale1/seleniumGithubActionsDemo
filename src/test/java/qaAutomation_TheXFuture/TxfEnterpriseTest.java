package qaAutomation_TheXFuture;

import com.github.javafaker.Faker;


import org.openqa.selenium.html5.WebStorage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObjects.onboardingPages.EnterpriseProfilePage;
import reusableComponents.ExplicitWaits;
import reusableComponents.PropertiesOperations;
import testBase.TestBase;

public class TxfEnterpriseTest extends TestBase {

    static String email = "txfmoduletest@robot-mail.com"; //for individual module test
    String firstUser = "testuser1enterprisetest@robot-mail.com";// for complete first user on-boarding
    String secondUser = "testuser2enterprisetest@robot-mail.com";// for complete second user on-boarding

//    static String email = "test@module.mail7.io"; //for individual module test
//    String firstUser = "testuser1@enterprisetest.mail7.io";// for complete first user on-boarding
//    String secondUser = "testuser2@enterprisetest.mail7.io";// for complete second user on-boarding

    Faker faker=new Faker();

    @AfterMethod
    public void clearUsers() throws Exception {
        commonMethods.clearUserData(email);
        commonMethods.clearUserData(firstUser);
    }


    @Test(priority = 1)
    public void signUpTest() throws Exception {
        String email = "test@testenterprisetest.example.com";
        commonMethods.clearUserData(email);

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
        txfSignUpPage.enterEmail(email);
        txfSignUpPage.enterPassword("pass123");
        txfSignUpPage.clickSignup();

        //try sign up with personal mail address Ex : gmail, yahoo etc
        txfSignUpPage.enterEmail(commonMethods.GenerateRandomEMAILIDs("gmail.com"));
        txfSignUpPage.enterPassword("Password@123");
        txfSignUpPage.clickSignup();
        Assert.assertTrue(txfSignUpPage.verifyPersonalMailError());

        // Try to sign up with valid email and valid password
        txfSignUpPage.enterEmail(email);
        txfSignUpPage.enterPassword(PropertiesOperations.getPropertyValueByKey("enterprisePassword"));

        txfSignUpPage.clickSignup();
        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(email);


        otpVerificationPage.enterOtp("123456");
        otpVerificationPage.clickOnVerifyButton();
        Assert.assertTrue(otpVerificationPage.homepageDisplayed());
    }



    @Test(priority = 2)
    public void loginTest() throws Exception {
        //String email=faker.name().username()+"@"+domain;
        commonMethods.clearUserData(email);
        //user sign up
        txfLoginPage.clickSignUpButton();
        txfSignUpPage.enterEmail(email);
        txfSignUpPage.enterPassword("Password@123");
        txfSignUpPage.clickSignup();
        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(email);

//        String link = commonMethods.fetchEmailBody(email);
        String link = commonMethods.fetchEmailLink(email);
        String sp = link.split("&")[0];
        String otp = sp.split("=")[1];
        System.out.println(otp);

        otpVerificationPage.enterOtp(otp);
        otpVerificationPage.clickOnVerifyButton();
        Assert.assertTrue(otpVerificationPage.homepageDisplayed());

        //clear session storage
        WebStorage webStorage = (WebStorage)driver;
        webStorage.getSessionStorage().clear();
        webStorage.getLocalStorage().clear();
        driver.navigate().refresh();


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
        txfLoginPage.enterEmail(email);
        txfLoginPage.enterPassword("Password@123");
        txfLoginPage.clickLoginButton();
        Assert.assertTrue(txfLoginPage.homepageDisplayed());
    }

    @Test(priority = 3)
    public void ForgotPasswordTest() throws Exception {
        commonMethods.clearUserData(email);
        //user sign up
        txfLoginPage.clickSignUpButton();
        txfSignUpPage.enterEmail(email);
        txfSignUpPage.enterPassword("Password@123");
        txfSignUpPage.clickSignup();
        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(email);

//        String link = commonMethods.fetchEmailBody(email);
        String link = commonMethods.fetchEmailLink(email);
        String sp = link.split("&")[0];
        String otp = sp.split("=")[1];
        System.out.println(otp);

        otpVerificationPage.enterOtp(otp);

//        ngDriver.waitForAngularRequestsToFinish();
        otpVerificationPage.clickOnVerifyButton();

        Assert.assertTrue(otpVerificationPage.homepageDisplayed());

        //clear session storage
        WebStorage webStorage = (WebStorage)driver;
        webStorage.getSessionStorage().clear();
        webStorage.getLocalStorage().clear();
        driver.navigate().refresh();

        //forgot password
        txfLoginPage.clickForgotPasswordLink();
        forgotPasswordPage.setEmailField(email);
        forgotPasswordPage.clickResetPasswordButton();

        forgotPasswordPage.verifyResetPasswordLinkSent();

//        String resetLink = commonMethods.fetchEmailBody(email);
        String resetLink = commonMethods.fetchEmailLink(email);
        driver.get(resetLink);
        String newPassword="Qwerty@123";
        forgotPasswordPage.enterNewPassword(newPassword);
        forgotPasswordPage.confirmNewPassword(newPassword);
        forgotPasswordPage.clickResetPasswordButton();

        //login with updated password
        txfLoginPage.enterEmail(email);
        txfLoginPage.enterPassword(newPassword);
        txfLoginPage.clickLoginButton();
        Assert.assertTrue(txfLoginPage.homepageDisplayed());
    }



    @Test(priority=4)
    public void enterpriseOnboardTest() throws Exception {
        //sign up
        commonMethods.clearUserData(firstUser);

        txfLoginPage.clickSignUpButton();
        txfSignUpPage.enterEmail(firstUser);
        txfSignUpPage.enterPassword(PropertiesOperations.getPropertyValueByKey("enterprisePassword"));
        txfSignUpPage.clickSignup();
        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(firstUser);

//        String link = commonMethods.fetchEmailBody(firstUser);
        String link = commonMethods.fetchEmailLink(firstUser);
        String sp = link.split("&")[0];
        String otp = sp.split("=")[1];
        System.out.println("OTP >>> "+otp);
        otpVerificationPage.enterOtp(otp);
//        ngDriver.waitForAngularRequestsToFinish();
        otpVerificationPage.clickOnVerifyButton();
//        ngDriver.waitForAngularRequestsToFinish();

        Assert.assertTrue(otpVerificationPage.homepageDisplayed());

        //on-boarding process
        entitySelectionPage.clickOnEnterpriseTile();

        String filePath =  System.getProperty("user.dir")+"/src/test/resources/testData/logo.jpg";
        enterpriseProfilePage.uploadCompanyLogo(filePath);
//        ngDriver.waitForAngularRequestsToFinish();

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
//        enterpriseProfilePage.verifyInvalidLinkedinFieldError();
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
        enterpriseUsecasePage.clickOnLogout();


        //second user sign up
        System.out.println("first user :"+firstUser);
        System.out.println("second user :"+secondUser);

        txfLoginPage.clickSignUpButton();
        txfSignUpPage.enterEmail(secondUser);
        txfSignUpPage.enterPassword(PropertiesOperations.getPropertyValueByKey("enterprisePassword"));
        txfSignUpPage.clickSignup();
        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(secondUser);

//        String otplink = commonMethods.fetchEmailBody(secondUser);
        String otplink = commonMethods.fetchEmailLink(secondUser);
        String split = otplink.split("&")[0];
        String otp2 = split.split("=")[1];
        System.out.println("OTP >>> "+otp2);
        otpVerificationPage.enterOtp(otp2);
        ExplicitWaits.waitTillAngularRequestIsFinish();
        otpVerificationPage.clickOnVerifyButton();
        ExplicitWaits.waitTillAngularRequestIsFinish();

        onboardPage.verifyCompanyName(companyName);
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
//TODO            enterpriseUsecasePage.verifyOnboardingCompleted();

        enterpriseUsecasePage.clickOnLogout();

    }


    @Test
    public void inviteSameDomainUserTest() throws Exception {
        //sign up
        commonMethods.clearUserData(firstUser);

        txfLoginPage.clickSignUpButton();
        txfSignUpPage.enterEmail(firstUser);
        txfSignUpPage.enterPassword(PropertiesOperations.getPropertyValueByKey("enterprisePassword"));
        txfSignUpPage.clickSignup();
        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(firstUser);

//        String link = commonMethods.fetchEmailBody(firstUser);
        String link = commonMethods.fetchEmailLink(firstUser);
        otpVerificationPage.enterOtp(commonMethods.fetchOtp(link));
        ngDriver.waitForAngularRequestsToFinish();
        otpVerificationPage.clickOnVerifyButton();
        ngDriver.waitForAngularRequestsToFinish();

        Assert.assertTrue(otpVerificationPage.homepageDisplayed());

        //on-boarding process
        entitySelectionPage.clickOnEnterpriseTile();
        ExplicitWaits.waitTillAngularRequestIsFinish();

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
        enterpriseUsecasePage.clickOnLogout();

        //approve user from admin panel
        txfLoginPage.loginToAdminPanel();
        adminStartupPage.clickOnAdminEnterprise();
        adminEnterprisePage.approveUser(companyName);
        driver.navigate().refresh();
        adminEnterprisePage.clickOnLogoutBtn();

        txfLoginPage.enterEmail(firstUser);
        txfLoginPage.enterPassword("Password@123");
        txfLoginPage.clickLoginButton();

        userApprovalPage.clickOnTakeToMyAccount();
        enterpriseDashboardPage.clickOnUsecaseCollaborators();
        enterpriseDashboardPage.inviteCollaborators(secondUser);
        driver.navigate().refresh();
        enterpriseDashboardPage.clickOnMyAccountSidebar();
        enterpriseDashboardPage.clickOnLogoutOption();

//        String inviteLink = commonMethods.fetchEmailBody(secondUser);
        String inviteLink = commonMethods.fetchEmailLink(secondUser);
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
        driver.navigate().refresh();           //ToDo : remove it after test is stable
        commonMethods.scrollPageDown(driver);

        enterpriseUsecasePage.clickOnSkipAndSubmit();
        ExplicitWaits.waitTillAngularRequestIsFinish();
        userApprovalPage.verifyTakeMeToMyAccountButtonIsDisplayed();
    }

    @Test
    public void guestUserSignUpTest() throws Exception {
        String guestUser = "txfguestuser1test@tafmail.com";
        //sign up
        commonMethods.clearUserData(firstUser);

        txfLoginPage.clickSignUpButton();
        txfSignUpPage.enterEmail(firstUser);
        txfSignUpPage.enterPassword(PropertiesOperations.getPropertyValueByKey("enterprisePassword"));
        txfSignUpPage.clickSignup();
        ExplicitWaits.waitTillAngularRequestIsFinish();
        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(firstUser);
//        String link = commonMethods.fetchEmailBody(firstUser);
        String link = commonMethods.fetchEmailLink(firstUser);
        driver.get(link);
//        ngDriver.waitForAngularRequestsToFinish();
        Assert.assertTrue(otpVerificationPage.homepageDisplayed());

        //on-boarding process
        entitySelectionPage.clickOnEnterpriseTile();

        //verify validation on linkedin & Company website Fields
        ExplicitWaits.waitTillAngularRequestIsFinish();

        enterpriseProfilePage.setNameField(faker.name().firstName());
        enterpriseProfilePage.setDesignationField(faker.company().profession());
        enterpriseProfilePage.setLinkedinIdField("https://www.facebook.com/");
        enterpriseProfilePage.setCompanyNameField(commonMethods.generateRandomString(8));
        enterpriseProfilePage.setCompanyLocationField("Pune");
        enterpriseProfilePage.setCompanyWebsiteField("demosite.");
        enterpriseProfilePage.clickOnNextButton();
//        enterpriseProfilePage.verifyInvalidLinkedinFieldError();
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

        txfLoginPage.enterEmail(firstUser);
        txfLoginPage.enterPassword("Password@123");
        txfLoginPage.clickLoginButton();

        userApprovalPage.clickOnTakeToMyAccount();
        enterpriseDashboardPage.clickOnUsecaseCollaborators();
        enterpriseDashboardPage.inviteCollaborators(guestUser);
        driver.navigate().refresh();
        enterpriseDashboardPage.clickOnMyAccountSidebar();
        enterpriseDashboardPage.clickOnLogoutOption();

//        String inviteLink = commonMethods.fetchEmailBody(guestUser);
        String inviteLink = commonMethods.fetchEmailLink(guestUser);
        driver.get(inviteLink);

        txfSignUpPage.verifyEmailFieldDisabled();
        txfSignUpPage.enterPassword("Password@123");
        txfSignUpPage.clickSignup();
//        ngDriver.waitForAngularRequestsToFinish();
        //guest user sign up
        onboardPage.clickOnContinueInvitedUser();
//        ngDriver.waitForAngularRequestsToFinish();

        String logo =  System.getProperty("user.dir")+"/src/test/resources/testData/logotest.jpg";
        guestProfilePage.uploadGuestLogo(logo);

        guestProfilePage.clickOnSubmit();
        guestProfilePage.verifyEmptyDesignationFieldError();
        guestProfilePage.verifyEmptyDesignationFieldError();

        guestProfilePage.setNameField(commonMethods.generateRandomString(8));
        guestProfilePage.setDesignationField(commonMethods.generateRandomString(5));
        guestProfilePage.setLinkedinIdField("https://www.linkedin.com/in/thexfuture");
        guestProfilePage.clickOnSubmit();

        userApprovalPage.verifyTakeMeToMyAccountButtonIsDisplayed();
    }


}
