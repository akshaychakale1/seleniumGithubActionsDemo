package qaAutomation_TheXFuture;

import java.awt.AWTException;
import java.time.Clock;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import pageObjects.onboardingPages.EnterpriseProfilePage;
import pageObjects.onboardingPages.StartupProfilePage;
import reusableComponents.ExplicitWaits;
import reusableComponents.PropertiesOperations;
import testBase.TestBase;

public class StartupSmokeTest extends TestBase {
	
	static String companyName;
    static String email = "txfmoduletest@vomoto.com"; //for individual module test
    String firstUser = "testuser1startuptest@vomoto.com";// for complete first user on-boarding
    String secondUser = "testuser2startuptest@vomoto.com";// for complete second user on-boarding
    String invitedUser = "testuser3startuptest@vomoto.com";
    String secondUserMail="testuser2startuptest@vomoto.com";



    @AfterTest
    public void clearUsers() throws Exception {
        commonMethods.clearUserData(email);
        commonMethods.clearUserData(firstUser);
        commonMethods.clearUserData(secondUser);
        commonMethods.clearUserData(invitedUser);

    }


    @Test(priority = 1)
    public void signUpTest() throws Exception {

        commonMethods.clearUserData(firstUser);

        txfLoginPage.clickSignUpButton();

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
    public void startUpFirstUserOnboard() throws Exception {
        //login with valid email and valid password
        txfLoginPage.enterEmail(firstUser);
        txfLoginPage.enterPassword("Password@123");
        txfLoginPage.clickLoginButton();
        Assert.assertTrue(txfLoginPage.homepageDisplayed());
        
        
        //select startup tile  
        ExplicitWaits.waitTillAngularRequestIsFinish();
        entitySelectionPage.clickOnStartUpTile();
        ExplicitWaits.waitTillAngularRequestIsFinish();
       
        
        // user profile page
        String filePath =  System.getProperty("user.dir")+"/src/test/resources/testData/logo.jpg";
        startupProfilePage.uploadStartUpProfileCompanyLogo(filePath);
        ExplicitWaits.waitTillAngularRequestIsFinish();

        //Verify all mandatory fields
        startupProfilePage.clickOnStartUpNextButton();
        startupProfilePage.startUpVerifyEmptyNameFieldError();
        startupProfilePage.startUpVerifyEmptyDesignationFieldError();


        //verify validation on linkedin  Field
        startupProfilePage.setStartupUserNameField(commonMethods.generateRandomString(10));
        startupProfilePage.setStartupDesignationField(faker.company().profession());
        startupProfilePage.setStartUpLinkedinIdField("https://www.facebook.com/"+faker.name().username());
        startupProfilePage.clickOnStartUpNextButton();
        startupProfilePage.startUpVerifyInvalidLinkedinFieldError();
        startupProfilePage.setStartUpLinkedinIdField("https://www.linkedin.com/"+faker.name().username());
        startupProfilePage.clickOnStartUpNextButton();


        // Company profile page
        String filePath1 =  System.getProperty("user.dir")+"/src/test/resources/testData/download.jpg";
        StartupProfilePage create=new StartupProfilePage();
        create.uploadStartUpCompanyLogo(filePath1);

        //Verify all mandatory fields
        startupProfilePage.clickOnStartUpNextButton();
        startupProfilePage.startUpVerifyEmptyCompanyLocationFieldError();
        startupProfilePage.startUpVerifyEmptyCompanySizeFieldError();
        startupProfilePage.startUpVerifyEmptyPaidCustomersFieldError();
        startupProfilePage.startUpVerifyEmptyFundingRaisedFieldError();
        startupProfilePage.startUpVerifyEmptyArRevenueFieldError();

        //verify validation on  Company website Fields
        startupProfilePage.setStartUpCompanyNameField("companyName");
        startupProfilePage.setStartUpCompanyWebsiteField("demosite");
        startupProfilePage.setStartUpArrRevenue("erxtctgvjhj");
        startupProfilePage.startUpVerifyInvalidCompanyWebsiteFieldError();

        //verify with all valid fields
         companyName = commonMethods.generateRandomString(6).toLowerCase();
        startupProfilePage.setStartUpCompanyNameField(companyName);
        startupProfilePage.setStartUpCompanyWebsiteField(commonMethods.generateRandomUrls());
        startupProfilePage.setStartUpCompanyLocationField("Pune");
        commonMethods.scrollPageDown(driver);
        startupProfilePage.setStartUpCompanySizeField(2);
        startupProfilePage.setStartUpPaidCustomersField("2: 1-20");
        startupProfilePage.setStartUpFundingRaised(2);
        startupProfilePage.setStartUpArrRevenue(faker.number().digits(5));
        startupProfilePage.setStartUpMaturityStage();
        startupProfilePage.setStartUpRecentFunding();
        startupProfilePage.clickOnStartUpNextButton();

        ExplicitWaits.waitTillAngularRequestIsFinish();
        startupSolutionsPage.setSolutionTitleField(faker.lorem().sentence(10));
        startupSolutionsPage.setSolutionIndustryField("Logistics & Supply Chain");
        startupSolutionsPage.setSolutionFunctionField("Purchasing");
        startupSolutionsPage.setSolutionTechnologiesField("Machine Vision");
        startupSolutionsPage.setSolutionTagsField("Robotic arms");

        startupSolutionsPage.setSolutionSummaryField(faker.lorem().sentence(30));
        startupSolutionsPage.setSolutionPainPointsAddressedField(faker.lorem().sentence(30));

        startupSolutionsPage.addSolutionProductExplainerVideoYoutubeLink("https://www.youtube.com/watch?v=L8tdPYqFV7E&ab_channel=Techquickie");
        ExplicitWaits.waitTillAngularRequestIsFinish();
        startupSolutionsPage.deleteAttachedVideo();

        String videoFile= System.getProperty("user.dir")+"/src/test/resources/testData/sample_video.mp4";
        startupSolutionsPage.uploadSolutionProductExplainerVideoMp4Field(videoFile);

        startupSolutionsPage.setSolutionTestimonialsNameField(commonMethods.generateRandomString(10));
        startupSolutionsPage.setSolutionTestimonialsRoleField(commonMethods.generateRandomString(5));
        startupSolutionsPage.setSolutionTestimonialsField(commonMethods.generateRandomString(30));

        //startupSolutionsPage.clickOnSolutionAddAnotherTestimonialField();

        String docFile= System.getProperty("user.dir")+"/src/test/resources/testData/test.docx";
        startupSolutionsPage.uploadSolutionCaseStudy(docFile);
        startupSolutionsPage.clickOnSolutionPreviewButton();
        startupSolutionsPage.clickOnSolutionPublishButton();


        //Assert.assertTrue(startupSolutionsPage.verifyOnboardCompletePage());
        startupSolutionsPage.clickOnLogout();

//        //second user flow
          txfLoginPage.clickSignUpButton();
        txfSignUpPage.enterEmail(secondUser);
        txfSignUpPage.enterPassword(PropertiesOperations.getPropertyValueByKey("enterprisePassword"));
        txfSignUpPage.clickSignup();

        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(secondUser);
        String otp = commonMethods.fetchEmailLink(secondUser);
        driver.get(otp);
        ExplicitWaits.waitTillAngularRequestIsFinish();

        onboardPage.verifyCompanyName(companyName);
        onboardPage.clickOnContinue();
        ExplicitWaits.waitTillAngularRequestIsFinish();

        String file =  System.getProperty("user.dir")+"/src/test/resources/testData/download.jpg";
        startupProfilePage.uploadStartUpProfileCompanyLogo(file);
        ExplicitWaits.waitTillAngularRequestIsFinish();

        startupProfilePage.setStartupUserNameField(commonMethods.generateRandomString(10));
        startupProfilePage.setStartupDesignationField(faker.company().profession());
        startupProfilePage.setStartUpLinkedinIdField("https://www.linkedin.com/"+faker.name().username());
        startupProfilePage.clickOnStartUpNextButton();

        //createStartupProfile.verifyCompanyProfileFieldsDisabled();
        startupProfilePage.clickOnStartUpNextButton();

        startupSolutionsPage.clickOnSkipAndSubmit();
        ExplicitWaits.waitTillAngularRequestIsFinish();
        startupSolutionsPage.clickOnLogout();
    }

    @Test(dependsOnMethods = "startUpFirstUserOnboard")
    public void approveUser() throws Exception {
        //approve user from admin panel
        txfLoginPage.loginToAdminPanel();
        adminStartupPage.clickOnAdminStartup();
        adminStartupPage.approveUser(companyName);
        driver.navigate().refresh();
        adminStartupPage.clickOnLogoutBtn();
    }


       @Test
    public void rejectStartupUserTest() throws Exception {
        //sign up
        commonMethods.clearUserData(firstUser);
        txfLoginPage.clickSignUpButton();
        txfSignUpPage.enterEmail(firstUser);
        txfSignUpPage.enterPassword(PropertiesOperations.getPropertyValueByKey("enterprisePassword"));
        txfSignUpPage.clickSignup();
        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(firstUser);
        String link = commonMethods.fetchEmailLink(firstUser);
        driver.get(link);
        ExplicitWaits.waitTillAngularRequestIsFinish();

        entitySelectionPage.clickOnStartUpTile();
        ExplicitWaits.waitTillAngularRequestIsFinish();
//FIXME
        // user profile page
//        String filePath =  System.getProperty("user.dir")+"/src/test/resources/testData/logo.jpg";
//        startupProfilePage.uploadStartUpProfileCompanyLogo(filePath);
//        ExplicitWaits.waitTillAngularRequestIsFinish();

        //Verify all mandatory fields
        startupProfilePage.clickOnStartUpNextButton();
        startupProfilePage.startUpVerifyEmptyNameFieldError();
        startupProfilePage.startUpVerifyEmptyDesignationFieldError();
        //verify validation on linkedin  Field
        startupProfilePage.setStartupUserNameField(commonMethods.generateRandomString(10));
        startupProfilePage.setStartupDesignationField(faker.company().profession());
        startupProfilePage.setStartUpLinkedinIdField("https://www.facebook.com/"+faker.name().username());
        startupProfilePage.clickOnStartUpNextButton();
        startupProfilePage.startUpVerifyInvalidLinkedinFieldError();
        startupProfilePage.setStartUpLinkedinIdField("https://www.linkedin.com/"+faker.name().username());
        startupProfilePage.clickOnStartUpNextButton();
        // Company profile page
        String filePath1 =  System.getProperty("user.dir")+"/src/test/resources/testData/download.jpg";
        StartupProfilePage create=new StartupProfilePage();
        create.uploadStartUpCompanyLogo(filePath1);
        //Verify all mandatory fields
        startupProfilePage.clickOnStartUpNextButton();
        startupProfilePage.startUpVerifyEmptyCompanyLocationFieldError();
        startupProfilePage.startUpVerifyEmptyCompanySizeFieldError();
        startupProfilePage.startUpVerifyEmptyPaidCustomersFieldError();
        startupProfilePage.startUpVerifyEmptyFundingRaisedFieldError();
        startupProfilePage.startUpVerifyEmptyArRevenueFieldError();
        //verify validation on  Company website Fields
        startupProfilePage.setStartUpCompanyNameField(faker.company().name());
        startupProfilePage.setStartUpCompanyWebsiteField("demosite");
        startupProfilePage.setStartUpArrRevenue("erxtctgvjhj");
        startupProfilePage.startUpVerifyInvalidCompanyWebsiteFieldError();
        //verify with all valid fields
        String startupName = commonMethods.generateRandomString(6).toLowerCase();
        startupProfilePage.setStartUpCompanyNameField(startupName);
        startupProfilePage.setStartUpCompanyWebsiteField(commonMethods.generateRandomUrls());
        startupProfilePage.setStartUpCompanyLocationField("Pune");
        commonMethods.scrollPageDown(driver);
        startupProfilePage.setStartUpCompanySizeField(2);
        startupProfilePage.setStartUpPaidCustomersField("2: 1-20");
        startupProfilePage.setStartUpFundingRaised(2);
        startupProfilePage.setStartUpArrRevenue(faker.number().digits(5));
        startupProfilePage.setStartUpMaturityStage();
        startupProfilePage.setStartUpRecentFunding();
        startupProfilePage.clickOnStartUpNextButton();
        //Solution page
        startupSolutionsPage.setSolutionTitleField(faker.lorem().sentence(10));
        startupSolutionsPage.setSolutionIndustryField("Logistics & Supply Chain");
        startupSolutionsPage.setSolutionFunctionField("Purchasing");
        startupSolutionsPage.setSolutionTechnologiesField("Machine Vision");
        startupSolutionsPage.setSolutionTagsField("Robotic arms");
        startupSolutionsPage.setSolutionSummaryField(faker.lorem().sentence(30));
        startupSolutionsPage.setSolutionPainPointsAddressedField(faker.lorem().sentence(30));
        startupSolutionsPage.addSolutionProductExplainerVideoYoutubeLink("https://www.youtube.com/watch?v=L8tdPYqFV7E&ab_channel=Techquickie");
        startupSolutionsPage.deleteAttachedVideo();
        String videoFile= System.getProperty("user.dir")+"/src/test/resources/testData/sample_video.mp4";
        startupSolutionsPage.uploadSolutionProductExplainerVideoMp4Field(videoFile);
        startupSolutionsPage.setSolutionTestimonialsNameField(commonMethods.generateRandomString(10));
        startupSolutionsPage.setSolutionTestimonialsRoleField(commonMethods.generateRandomString(5));
        startupSolutionsPage.setSolutionTestimonialsField(commonMethods.generateRandomString(30));
        //startupSolutionsPage.clickOnSolutionAddAnotherTestimonialField();
        String docFile= System.getProperty("user.dir")+"/src/test/resources/testData/test.docx";
        startupSolutionsPage.uploadSolutionCaseStudy(docFile);
        startupSolutionsPage.clickOnSolutionPreviewButton();
        commonMethods.scrollPageDown(driver);
        startupSolutionsPage.clickOnSolutionPublishButton();
        //Assert.assertTrue(startupSolutionsPage.verifyOnboardCompletePage());
        startupSolutionsPage.clickOnLogout();

        //Reject user from admin
        txfLoginPage.loginToAdminPanel();
        adminEnterprisePage.rejectUser(startupName);
        driver.navigate().refresh();
        adminEnterprisePage.clickOnLogoutBtn();

        txfLoginPage.enterEmail(firstUser);
        txfLoginPage.enterPassword("Password@123");
        txfLoginPage.clickLoginButton();
    }

        @Test
    public void secondUserSignUpForRejectedStartupDomainTest() throws Exception {
        //sign up
        commonMethods.clearUserData(firstUser);
        txfLoginPage.clickSignUpButton();
        txfSignUpPage.enterEmail(firstUser);
        txfSignUpPage.enterPassword(PropertiesOperations.getPropertyValueByKey("enterprisePassword"));
        txfSignUpPage.clickSignup();
        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(firstUser);
        String link = commonMethods.fetchEmailLink(firstUser);
        driver.get(link);
        ExplicitWaits.waitTillAngularRequestIsFinish();

        entitySelectionPage.clickOnStartUpTile();
        ExplicitWaits.waitTillAngularRequestIsFinish();
//FIXME
        // user profile page
//        String filePath =  System.getProperty("user.dir")+"/src/test/resources/testData/logo.jpg";
//        startupProfilePage.uploadStartUpProfileCompanyLogo(filePath);
//        ExplicitWaits.waitTillAngularRequestIsFinish();
        //Verify all mandatory fields
        startupProfilePage.clickOnStartUpNextButton();
        startupProfilePage.startUpVerifyEmptyNameFieldError();
        startupProfilePage.startUpVerifyEmptyDesignationFieldError();
        //verify validation on linkedin  Field
        startupProfilePage.setStartupUserNameField(commonMethods.generateRandomString(10));
        startupProfilePage.setStartupDesignationField(faker.company().profession());
        startupProfilePage.setStartUpLinkedinIdField("https://www.facebook.com/"+faker.name().username());
        startupProfilePage.clickOnStartUpNextButton();
        startupProfilePage.startUpVerifyInvalidLinkedinFieldError();
        startupProfilePage.setStartUpLinkedinIdField("https://www.linkedin.com/"+faker.name().username());
        startupProfilePage.clickOnStartUpNextButton();
        // Company profile page
        String filePath1 =  System.getProperty("user.dir")+"/src/test/resources/testData/download.jpg";
        StartupProfilePage create=new StartupProfilePage();
        create.uploadStartUpCompanyLogo(filePath1);
        //Verify all mandatory fields
        startupProfilePage.clickOnStartUpNextButton();
        startupProfilePage.startUpVerifyEmptyCompanyLocationFieldError();
        startupProfilePage.startUpVerifyEmptyCompanySizeFieldError();
        startupProfilePage.startUpVerifyEmptyPaidCustomersFieldError();
        startupProfilePage.startUpVerifyEmptyFundingRaisedFieldError();
        startupProfilePage.startUpVerifyEmptyArRevenueFieldError();
        //verify validation on  Company website Fields
        startupProfilePage.setStartUpCompanyNameField(faker.company().name());
        startupProfilePage.setStartUpCompanyWebsiteField("demosite");
        startupProfilePage.setStartUpArrRevenue("erxtctgvjhj");
        startupProfilePage.startUpVerifyInvalidCompanyWebsiteFieldError();
        //verify with all valid fields
        String startupName = commonMethods.generateRandomString(6).toLowerCase();
        startupProfilePage.setStartUpCompanyNameField(startupName);
        startupProfilePage.setStartUpCompanyWebsiteField(commonMethods.generateRandomUrls());
        startupProfilePage.setStartUpCompanyLocationField("Pune");
        commonMethods.scrollPageDown(driver);
        startupProfilePage.setStartUpCompanySizeField(2);
        startupProfilePage.setStartUpPaidCustomersField("2: 1-20");
        startupProfilePage.setStartUpFundingRaised(2);
        startupProfilePage.setStartUpArrRevenue(faker.number().digits(5));
        startupProfilePage.setStartUpMaturityStage();
        startupProfilePage.setStartUpRecentFunding();
        startupProfilePage.clickOnStartUpNextButton();
        //Solution page
        startupSolutionsPage.setSolutionTitleField(faker.lorem().sentence(10));
        startupSolutionsPage.setSolutionIndustryField("Logistics & Supply Chain");
        startupSolutionsPage.setSolutionFunctionField("Purchasing");
        startupSolutionsPage.setSolutionTechnologiesField("Machine Vision");
        startupSolutionsPage.setSolutionTagsField("Robotic arms");
        startupSolutionsPage.setSolutionSummaryField(faker.lorem().sentence(30));
        startupSolutionsPage.setSolutionPainPointsAddressedField(faker.lorem().sentence(30));
        startupSolutionsPage.addSolutionProductExplainerVideoYoutubeLink("https://www.youtube.com/watch?v=L8tdPYqFV7E&ab_channel=Techquickie");
        startupSolutionsPage.deleteAttachedVideo();
        String videoFile= System.getProperty("user.dir")+"/src/test/resources/testData/sample_video.mp4";
        startupSolutionsPage.uploadSolutionProductExplainerVideoMp4Field(videoFile);
        startupSolutionsPage.setSolutionTestimonialsNameField(commonMethods.generateRandomString(10));
        startupSolutionsPage.setSolutionTestimonialsRoleField(commonMethods.generateRandomString(5));
        startupSolutionsPage.setSolutionTestimonialsField(commonMethods.generateRandomString(30));
        //startupSolutionsPage.clickOnSolutionAddAnotherTestimonialField();
        String docFile= System.getProperty("user.dir")+"/src/test/resources/testData/test.docx";
        startupSolutionsPage.uploadSolutionCaseStudy(docFile);
        startupSolutionsPage.clickOnSolutionPreviewButton();
        commonMethods.scrollPageDown(driver);
        startupSolutionsPage.clickOnSolutionPublishButton();
        //Assert.assertTrue(startupSolutionsPage.verifyOnboardCompletePage());
        startupSolutionsPage.clickOnLogout();

        //Reject user from admin
        txfLoginPage.loginToAdminPanel();
        adminEnterprisePage.rejectUser(startupName);
        driver.navigate().refresh();
        adminEnterprisePage.clickOnLogoutBtn();

        //second user sign up
        txfLoginPage.clickSignUpButton();
        txfSignUpPage.enterEmail(secondUserMail);
        txfSignUpPage.enterPassword(PropertiesOperations.getPropertyValueByKey("enterprisePassword"));
        txfSignUpPage.clickSignup();

        //verify domain rejected message
    }



    @Test
    public void startUpGuestUserOnboardTest() throws Exception {
        //sign up
        commonMethods.clearUserData(firstUser);
        txfLoginPage.clickSignUpButton();
        txfSignUpPage.enterEmail(firstUser);
        txfSignUpPage.enterPassword("Password@123");
        txfSignUpPage.clickSignup();
        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(firstUser);
        String link = commonMethods.fetchEmailLink(firstUser);
        driver.get(link);
        entitySelectionPage.clickOnStartUpTile();
        ExplicitWaits.waitTillAngularRequestIsFinish();

        // user profile page
        String filePath =  System.getProperty("user.dir")+"/src/test/resources/testData/logo.jpg";
        startupProfilePage.uploadStartUpProfileCompanyLogo(filePath);
        ExplicitWaits.waitTillAngularRequestIsFinish();

        //verify validation on linkedin  Field
        startupProfilePage.setStartupUserNameField(commonMethods.generateRandomString(10));
        startupProfilePage.setStartupDesignationField(faker.company().profession());
        startupProfilePage.setStartUpLinkedinIdField("https://www.linkedin.com/"+faker.name().username());
        startupProfilePage.clickOnStartUpNextButton();

        //verify with all valid fields
        String startupName = commonMethods.generateRandomString(6).toLowerCase();
        startupProfilePage.setStartUpCompanyNameField(startupName);
        startupProfilePage.setStartUpCompanyWebsiteField(commonMethods.generateRandomUrls());
        startupProfilePage.setStartUpCompanyLocationField("Pune");
        commonMethods.scrollPageDown(driver);
        startupProfilePage.setStartUpCompanySizeField(2);
        startupProfilePage.setStartUpPaidCustomersField("2: 1-20");
        startupProfilePage.setStartUpFundingRaised(2);
        startupProfilePage.setStartUpArrRevenue(faker.number().digits(5));
        startupProfilePage.setStartUpMaturityStage();
        startupProfilePage.setStartUpRecentFunding();
        startupProfilePage.clickOnStartUpNextButton();


        //Solution page
        startupSolutionsPage.setSolutionTitleField(faker.lorem().sentence(10));
        startupSolutionsPage.setSolutionIndustryField("Logistics & Supply Chain");
        startupSolutionsPage.setSolutionFunctionField("Purchasing");
        startupSolutionsPage.setSolutionTechnologiesField("Machine Vision");
        startupSolutionsPage.setSolutionTagsField("Robotic arms");
        startupSolutionsPage.setSolutionSummaryField(faker.lorem().sentence(30));
        ExplicitWaits.waitTillAngularRequestIsFinish();
        startupSolutionsPage.setSolutionPainPointsAddressedField(faker.lorem().sentence(30));
        String docFile= System.getProperty("user.dir")+"/src/test/resources/testData/test.docx";
        startupSolutionsPage.uploadSolutionCaseStudy(docFile);
        driver.navigate().refresh();
        ExplicitWaits.waitTillAngularRequestIsFinish();
        startupSolutionsPage.clickOnSolutionPreviewButton();
        commonMethods.scrollPageDown(driver);
        ExplicitWaits.waitTillAngularRequestIsFinish();
        startupSolutionsPage.clickOnSolutionPublishButton();
        startupSolutionsPage.clickOnLogout();

        //approve user from admin
        txfLoginPage.loginToAdminPanel();
        adminEnterprisePage.approveUser(startupName);
        driver.navigate().refresh();
        adminEnterprisePage.clickOnLogoutBtn();

        txfLoginPage.enterEmail(firstUser);
        txfLoginPage.enterPassword("Password@123");
        txfLoginPage.clickLoginButton();

        userApprovalPage.clickOnTakeToMyAccount();
        startupDashboardPage.clickOnSolutionCollaborators();
        String guestMail= faker.name().username()+"@clrmail.com";
        startupDashboardPage.inviteCollaborators(guestMail);
        driver.navigate().refresh();
        startupDashboardPage.clickOnMyAccountSidebar();
        startupDashboardPage.clickOnLogoutOption();

        driver.get(commonMethods.fetchEmailLink(guestMail));

        txfSignUpPage.verifyEmailFieldDisabled();
        txfSignUpPage.enterPassword("Password@123");
        txfSignUpPage.clickSignup();
        ExplicitWaits.waitTillAngularRequestIsFinish();
        //guest user sign up
        onboardPage.clickOnContinueInvitedUser();
        ExplicitWaits.waitTillAngularRequestIsFinish();

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
