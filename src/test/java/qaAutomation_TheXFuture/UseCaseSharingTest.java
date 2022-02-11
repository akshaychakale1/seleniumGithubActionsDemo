package qaAutomation_TheXFuture;

import org.openqa.selenium.html5.WebStorage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObjects.onboardingPages.StartupProfilePage;
import reusableComponents.ExplicitWaits;
import reusableComponents.PropertiesOperations;
import testBase.TestBase;

public class UseCaseSharingTest extends TestBase {

    String firstUser = "testuser1@usecasesharingtest.example.com";

    @AfterMethod
    public void clearUsers() throws Exception {
        commonMethods.clearUserData(firstUser);
    }

    @Test
    public void shareUseCaseTest() throws Exception {
        //sign up
        commonMethods.clearUserData(firstUser);

        txfLoginPage.clickSignUpButton();
        txfSignUpPage.enterEmail(firstUser);
        txfSignUpPage.enterPassword(PropertiesOperations.getPropertyValueByKey("enterprisePassword"));
        txfSignUpPage.clickSignup();
        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(firstUser);

        otpVerificationPage.enterOtp("123456");
        ExplicitWaits.waitTillAngularRequestIsFinish();
        otpVerificationPage.clickOnVerifyButton();
        ExplicitWaits.waitTillAngularRequestIsFinish();

        Assert.assertTrue(otpVerificationPage.homepageDisplayed());
        //on-boarding process
        entitySelectionPage.clickOnEnterpriseTile();
        ExplicitWaits.waitTillAngularRequestIsFinish();

        String filePath =  System.getProperty("user.dir")+"/src/test/resources/testData/logo.jpg";
        enterpriseProfilePage.uploadCompanyLogo(filePath);
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
        //select use case
        enterpriseUsecasePage.clickOnChooseFromTemplates();
        enterpriseUsecasePage.previewTemplate();
        enterpriseUsecasePage.clickOnUseTemplate();
        commonMethods.scrollPageDown(driver);
        enterpriseUsecasePage.clickOnPreviewButton();
        commonMethods.scrollPageDown(driver);
        enterpriseUsecasePage.clickOnSubmitButton();
        enterpriseUsecasePage.clickOnLogout();
        //approve user from admin panel
        txfLoginPage.loginToAdminPanel();
        adminStartupPage.clickOnAdminEnterprise();
        adminEnterprisePage.approveUser(companyName);
        driver.navigate().refresh();
        adminEnterprisePage.clickOnLogoutBtn();
        //login
        txfLoginPage.enterEmail(firstUser);
        txfLoginPage.enterPassword("Password@123");
        txfLoginPage.clickLoginButton();
        userApprovalPage.clickOnTakeToMyAccount();

        enterpriseDashboardPage.clickOnShareUseCaseIcon();
        enterpriseDashboardPage.verifyUseCaseSharingTabIsVisible();
        enterpriseDashboardPage.clickOnPublicSharingToggleSwitch();
        String publicLink = enterpriseDashboardPage.getPublicLink();

        enterpriseDashboardPage.clickOnMyAccountSidebar();
        enterpriseDashboardPage.clickOnLogoutOption();
        driver.get(publicLink);
        enterpriseDashboardPage.clickOnApplyNowButton();


        //use case sharing startup on-boarding flow
        String startUpEmail= "testuser1startuptest@getnada.com";
        commonMethods.clearUserData(startUpEmail);
        applyUseCaseLoginPage.clickSignUpButton();
        applyUseCaseSignUpPage.enterEmail(startUpEmail);
        applyUseCaseSignUpPage.enterPassword("Password@123");
        applyUseCaseSignUpPage.clickSignup();
        //otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(startUpEmail);
        String otpLink = commonMethods.fetchEmailLink(startUpEmail);
        driver.get(otpLink);
        ExplicitWaits.waitTillAngularRequestIsFinish();
       // Assert.assertTrue(otpVerificationPage.homepageDisplayed());


        // user profile page
        String logoFilePath =  System.getProperty("user.dir")+"/src/test/resources/testData/logo.jpg";
        startupProfilePage.uploadStartUpProfileCompanyLogo(logoFilePath);
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
        //Start up solution
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


        String docFile= System.getProperty("user.dir")+"/src/test/resources/testData/test.docx";
        startupSolutionsPage.uploadSolutionCaseStudy(docFile);
        startupSolutionsPage.clickOnSolutionPreviewButton();
        driver.navigate().refresh();
        commonMethods.scrollPageDown(driver);
        startupSolutionsPage.clickOnSolutionPublishButton();

        userApprovalPage.clickOnGotoMyAccount();

        startupDashboardPage.verifyAccountUnderReviewWarning();
        startupDashboardPage.verifyAddSolutionIsDisabled();

    }
}
