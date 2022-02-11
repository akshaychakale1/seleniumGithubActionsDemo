package qaAutomation_TheXFuture;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import testBase.TestBase;

import java.util.ArrayList;

public class StartupDashboardTest extends TestBase {

    String firstUser ="testuser1@startupdashboardtest.example.com";

    @AfterMethod
    public void clearUsers() throws Exception {
        commonMethods.clearUserData(firstUser);
    }

//    @Test
    public void startupDashboardMyAccountTest() throws Exception{
        //sign up
        commonMethods.clearUserData(firstUser);

        txfLoginPage.clickSignUpButton();
        txfSignUpPage.enterEmail(firstUser);
        txfSignUpPage.enterPassword("Password@123");
        txfSignUpPage.clickSignup();
        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(firstUser);

//        String link = commonMethods.fetchEmailBody(firstUser);
//        driver.get(link);

        otpVerificationPage.enterOtp("123456");
        ngDriver.waitForAngularRequestsToFinish();
        otpVerificationPage.clickOnVerifyButton();
        ngDriver.waitForAngularRequestsToFinish();
        entitySelectionPage.clickOnStartUpTile();
        ngDriver.waitForAngularRequestsToFinish();

        // user profile page
        String filePath =  System.getProperty("user.dir")+"/src/test/resources/testData/logo.jpg";
        startupProfilePage.uploadStartUpProfileCompanyLogo(filePath);
        ngDriver.waitForAngularRequestsToFinish();

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
        startupSolutionsPage.setSolutionPainPointsAddressedField(faker.lorem().sentence(30));
        String docFile= System.getProperty("user.dir")+"/src/test/resources/testData/test.docx";
        startupSolutionsPage.uploadSolutionCaseStudy(docFile);
        startupSolutionsPage.clickOnSolutionPreviewButton();
        commonMethods.scrollPageDown(driver);
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


        startupDashboardPage.clickOnSolutionOptions();
        startupDashboardPage.clickOnOpenInNewTabOption();
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        String mainWindow=newTab.get(0);
        String childWindow=newTab.get(1);
        driver.switchTo().window(childWindow);
        startupDashboardPage.verifyUseCaseExpandedView();
        startupDashboardPage.verifyCommentsFunctionality();
        startupDashboardPage.verifyFilesTabFunctionality();
        //startupDashboardPage.verifyExpandedViewCollaboratorsFunctionality();
    }


//    @Test
    public void startupDashboardAddSolution() throws Exception {
        //sign up
        commonMethods.clearUserData(firstUser);

        txfLoginPage.clickSignUpButton();
        txfSignUpPage.enterEmail(firstUser);
        txfSignUpPage.enterPassword("Password@123");
        txfSignUpPage.clickSignup();
        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(firstUser);

//        String link = commonMethods.fetchEmailBody(firstUser);
//        driver.get(link);

        otpVerificationPage.enterOtp("123456");
        ngDriver.waitForAngularRequestsToFinish();
        otpVerificationPage.clickOnVerifyButton();
        ngDriver.waitForAngularRequestsToFinish();

        entitySelectionPage.clickOnStartUpTile();
        ngDriver.waitForAngularRequestsToFinish();

        // user profile page
        String filePath =  System.getProperty("user.dir")+"/src/test/resources/testData/logo.jpg";
        startupProfilePage.uploadStartUpProfileCompanyLogo(filePath);
        ngDriver.waitForAngularRequestsToFinish();

        //verify validation on linkedin  Field
        startupProfilePage.setStartupUserNameField(commonMethods.generateRandomString(10));
        startupProfilePage.setStartupDesignationField(faker.company().profession());
        startupProfilePage.setStartUpLinkedinIdField("https://www.linkedin.com/" + faker.name().username());
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
        startupSolutionsPage.setSolutionPainPointsAddressedField(faker.lorem().sentence(30));
        String docFile = System.getProperty("user.dir") + "/src/test/resources/testData/test.docx";
        startupSolutionsPage.uploadSolutionCaseStudy(docFile);
        startupSolutionsPage.clickOnSolutionPreviewButton();
        commonMethods.scrollPageDown(driver);
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
//        ngDriver.waitForAngularRequestsToFinish();
        //dashboard
        enterpriseDashboardPage.setClickAddUsc();
        startupSolutionsPage.setSolutionTitleField(faker.lorem().sentence(10));


        startupDashboardPage.setDashboardSolutionIndustryField("Logistics & Supply Chain");
        startupDashboardPage.setDashboardSolutionFunctionField("Purchasing");
        startupDashboardPage.setDashboardSolutionTechnologiesField("Machine Vision");
        startupDashboardPage.setDashboardSolutionTagsField("Robotic arms");
        startupSolutionsPage.setSolutionSummaryField(faker.lorem().sentence(30));
        startupSolutionsPage.setSolutionPainPointsAddressedField(faker.lorem().sentence(30));

        startupSolutionsPage.uploadSolutionCaseStudy(docFile);
//        ngDriver.waitForAngularRequestsToFinish();
        startupDashboardPage.clickOnPublishSolution();
    }

    @Test
    public void dashboardOpportunitiesTest() throws Exception {
        //sign up
        commonMethods.clearUserData(firstUser);

        txfLoginPage.clickSignUpButton();
        txfSignUpPage.enterEmail(firstUser);
        txfSignUpPage.enterPassword("Password@123");
        txfSignUpPage.clickSignup();
        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(firstUser);

//        String link = commonMethods.fetchEmailBody(firstUser);
//        driver.get(link);

        otpVerificationPage.enterOtp("123456");
        ngDriver.waitForAngularRequestsToFinish();
        otpVerificationPage.clickOnVerifyButton();
        ngDriver.waitForAngularRequestsToFinish();

        entitySelectionPage.clickOnStartUpTile();
        ngDriver.waitForAngularRequestsToFinish();

        // user profile page
        String filePath =  System.getProperty("user.dir")+"/src/test/resources/testData/logo.jpg";
        startupProfilePage.uploadStartUpProfileCompanyLogo(filePath);
        ngDriver.waitForAngularRequestsToFinish();

        //verify validation on linkedin  Field
        startupProfilePage.setStartupUserNameField(commonMethods.generateRandomString(10));
        startupProfilePage.setStartupDesignationField(faker.company().profession());
        startupProfilePage.setStartUpLinkedinIdField("https://www.linkedin.com/" + faker.name().username());
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
        startupSolutionsPage.setSolutionPainPointsAddressedField(faker.lorem().sentence(30));
        String docFile = System.getProperty("user.dir") + "/src/test/resources/testData/test.docx";
        startupSolutionsPage.uploadSolutionCaseStudy(docFile);
        startupSolutionsPage.clickOnSolutionPreviewButton();
        commonMethods.scrollPageDown(driver);
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

        startupDashboardPage.clickOnSolutionTile();
        String url = driver.getCurrentUrl();
        System.out.println("Url >>>>>>>>>" + url);
        String solutionId=url.split("summary/")[1];

        commonMethods.forceMatch(solutionId,"solution");
        driver.navigate().refresh();
        startupDashboardPage.clickOnOpportunitiesButton();

        driver.navigate().refresh();
//        ngDriver.waitForAngularRequestsToFinish();
        startupDashboardPage.verifyStatusDropdownFunctionality();
//        startupDashboardPage.verifyKanbanViewFunctionality();

        startupDashboardPage.clickOnMatchedOpportunity();
        startupDashboardPage.verifyExpandedViewStatusDropdownFunctionality();
        startupDashboardPage.verifyCommentsFunctionality();
        startupDashboardPage.verifyFilesTabFunctionality();

    }


}
