package qaAutomation_TheXFuture;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import reusableComponents.ExplicitWaits;
import reusableComponents.PropertiesOperations;
import testBase.TestBase;

import java.util.ArrayList;
import java.util.Set;

public class EnterpriseDashboardTest extends TestBase {

    String firstUser = "testuser@test1223.example.com";
    //"testuser1@enterprisedashboardtest.mail7.io";// for complete first user on-boarding

    @AfterMethod
    public void clearUsers() throws Exception {
        commonMethods.clearUserData(firstUser);
    }


    @Test(priority = 1)
    public void enterpriseDashboardMyAccountTest() throws Exception {
       commonMethods.clearUserData(firstUser);
        //sign up
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
        //login
        txfLoginPage.enterEmail(firstUser);
        txfLoginPage.enterPassword("Password@123");
        txfLoginPage.clickLoginButton();
        userApprovalPage.clickOnTakeToMyAccount();
        //My Account flow
        enterpriseDashboardPage.clickOnMyAccountSidebar();
        enterpriseDashboardPage.clickOnMyAccountOption();
        //my profile
        enterpriseDashboardPage.myProfileFieldTest();
        //company profile
        enterpriseDashboardPage.clickOnCompanyProfile();
//ToDO        enterpriseDashboardPage.verifyCompanyProfileFields();
        //my team
        enterpriseDashboardPage.clickOnMyTeam();

        enterpriseDashboardPage.verifyInviteMemberFunctionality();
        //change password
        enterpriseDashboardPage.clickOnChangePassword();
        enterpriseDashboardPage.changePasswordFunctionalityTest(firstUser);
    }

//    @Test(priority = 2)
    public void dashboardAddUseCaseTileTest() throws Exception {
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
        //select use case
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
        //login
        txfLoginPage.enterEmail(firstUser);
        txfLoginPage.enterPassword("Password@123");
        txfLoginPage.clickLoginButton();
        userApprovalPage.clickOnTakeToMyAccount();
        //dashboard
        enterpriseDashboardPage.clickOnUsecaseOptions();
        enterpriseDashboardPage.clickOnOpenInNewTabOption();
        ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
        String childWindow=newTab.get(1);
        driver.switchTo().window(childWindow);
        enterpriseDashboardPage.verifyUseCaseExpandedView();
        enterpriseDashboardPage.verifyCommentsFunctionality();
        enterpriseDashboardPage.verifyFilesTabFunctionality();
        enterpriseDashboardPage.verifyExpandedViewCollaboratorsFunctionality();
    }

//     @Test
    public void dashboardAddUseCaseTest() throws Exception {
        //sign up
        commonMethods.clearUserData(firstUser);

        txfLoginPage.clickSignUpButton();
        txfSignUpPage.enterEmail(firstUser);
        txfSignUpPage.enterPassword(PropertiesOperations.getPropertyValueByKey("enterprisePassword"));
        txfSignUpPage.clickSignup();
        otpVerificationPage.verifyOtpSentOnCorrectEmailAddress(firstUser);

        otpVerificationPage.enterOtp("123456");
//        ExplicitWaits.waitTillAngularRequestIsFinish();
        otpVerificationPage.clickOnVerifyButton();
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
        //select use case
        enterpriseUsecasePage.clickOnChooseFromTemplates();
        enterpriseUsecasePage.previewTemplate();
        enterpriseUsecasePage.clickOnUseTemplate();
        commonMethods.scrollPageDown(driver);
        enterpriseUsecasePage.clickOnPreviewButton();
        enterpriseUsecasePage.clickOnSubmitButton();
        enterpriseUsecasePage.verifyOnboardingCompleted();
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

        //dashboard
        enterpriseDashboardPage.setClickAddUsc();
        enterpriseDashboardPage.setClickCreateOwnUsc();
        enterpriseDashboardPage.setGetChooseFromTemplates();

        enterpriseUsecasePage.previewTemplate();
        enterpriseUsecasePage.clickOnUseTemplate();
        enterpriseDashboardPage.setPublishBtn();


        enterpriseDashboardPage.setClickAddUsc();
        enterpriseDashboardPage.setChooseFromTemplates();
        enterpriseUsecasePage.previewTemplate();
        enterpriseUsecasePage.clickOnUseTemplate();
        enterpriseDashboardPage.setSaveAsDraft();
    }


//    @Test
    public void DashboardSolutionTest() throws Exception {
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
        commonMethods.scrollPageDown();
        enterpriseUsecasePage.clickOnSubmitButton();
        enterpriseUsecasePage.verifyOnboardingCompleted();
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

        //force match solution
        enterpriseDashboardPage.clickOnUsecaseTile();
        String url = driver.getCurrentUrl();
        System.out.println("Url >>>>>>>>>" + url);
        String reg=url.split("summary/")[1];
        String usecaseId = reg.split("/")[0];

        commonMethods.forceMatch(usecaseId,"usecase");
        driver.navigate().refresh();
        enterpriseDashboardPage.clickOnExpandedViewSolutionsButton();

       // driver.navigate().refresh();

        enterpriseDashboardPage.verifyStatusDropdownFunctionality();

        enterpriseDashboardPage.verifyAddColumnFunctionality();

//        enterpriseDashboardPage.verifyKanbanViewFunctionality();

        enterpriseDashboardPage.clickOnMatchedSolution();

        enterpriseDashboardPage.verifyExpandedViewStatusDropdownFunctionality();

        enterpriseDashboardPage.verifyCommentsFunctionality();

//        enterpriseDashboardPage.verifyFilesTabFunctionality();
    }

}
