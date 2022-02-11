package qaAutomation_TheXFuture;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObjects.onboardingPages.StartupProfilePage;
import reusableComponents.ExplicitWaits;
import testBase.TestBase;

public class AdminAddCompanyTest extends TestBase {

    String startupMail = "testuser1@adminaddcompanytest.mail7.io";

    @AfterMethod
    public void clearUsers() throws Exception {
        commonMethods.clearUserData(startupMail);
    }

    @Test
    public void adminAddStartupCompanyTest() throws Exception {
        commonMethods.clearUserData(startupMail);
        txfLoginPage.loginToAdminPanel();

        adminEnterprisePage.clickOnStartup();
        adminStartupPage.clickOnAddStartupCompanyButton();
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
        startupProfilePage.setCompanyEmailField("startupMail.com");
        startupProfilePage.clickOnStartUpNextButton();
        startupProfilePage.startUpVerifyInvalidEmailFieldError();
        startupProfilePage.startUpVerifyInvalidLinkedinFieldError();
        startupProfilePage.setStartUpLinkedinIdField("https://www.linkedin.com/"+faker.name().username());
        startupProfilePage.setCompanyEmailField(startupMail);
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
        startupDashboardPage.clickOnMyAccountSidebar();

    }

    @Test
    public void adminAddEnterpriseCompanyTest() throws Exception {
        commonMethods.clearUserData(startupMail);
        txfLoginPage.loginToAdminPanel();

        adminEnterprisePage.clickOnEnterprise();
        adminStartupPage.clickOnAddStartupCompanyButton();
        ExplicitWaits.waitTillAngularRequestIsFinish();
        entitySelectionPage.clickOnEnterpriseTile();


        String filePath =  System.getProperty("user.dir")+"/src/test/resources/testData/logo.jpg";
        enterpriseProfilePage.uploadCompanyLogo(filePath);
        ExplicitWaits.waitTillAngularRequestIsFinish();

        enterpriseProfilePage.setEmailField(startupMail);

        //Verify all mandatory fields
        enterpriseProfilePage.clickOnNextButton();
        enterpriseProfilePage.verifyEmptyNameFieldError();
        enterpriseProfilePage.verifyEmptyDesignationFieldError();
        //entitySelectionPage.verifyEmptyLinkedinFieldError();
        enterpriseProfilePage.verifyEmptyCompanyNameFieldError();
        enterpriseProfilePage.verifyEmptyCompanyLocationFieldError();
        enterpriseProfilePage.verifyEmptyCompanyWebsiteFieldError();


        //verify with all valid fields
        enterpriseProfilePage.setNameField(faker.name().firstName());
        enterpriseProfilePage.setDesignationField(faker.company().profession());
        enterpriseProfilePage.setLinkedinIdField("https://www.linkedin.com/in/thexfuture");
        String companyName = commonMethods.generateRandomString(6);
        enterpriseProfilePage.setCompanyNameField(companyName);
        enterpriseProfilePage.setCompanyWebsiteField(commonMethods.generateRandomUrls());
        enterpriseProfilePage.enterCompanyLocationField("Mumbai");

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


        adminEnterprisePage.approveUser(companyName);
        driver.navigate().refresh();

        adminEnterprisePage.clickOnLogoutBtn();

        txfLoginPage.enterEmail(startupMail);
        txfLoginPage.enterPassword("Password@123");
        txfLoginPage.clickLoginButton();

        ExplicitWaits.waitTillAngularRequestIsFinish();
        onboardPage.clickOnContinue();

        enterpriseProfilePage.clickOnNextButton();
        ExplicitWaits.waitTillAngularRequestIsFinish();
        enterpriseUsecasePage.clickOnPreviewButton();
        ExplicitWaits.waitTillAngularRequestIsFinish();
        enterpriseUsecasePage.clickOnSubmitButton();
        ExplicitWaits.waitTillAngularRequestIsFinish();
        userApprovalPage.clickOnTakeToMyAccount();

        enterpriseDashboardPage.clickOnMyAccountSidebar();
    }
}
