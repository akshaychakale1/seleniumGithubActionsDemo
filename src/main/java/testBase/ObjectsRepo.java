package testBase;

import com.github.javafaker.Faker;
import com.paulhammant.ngwebdriver.NgWebDriver;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import org.testng.asserts.SoftAssert;
import pageObjects.*;
import pageObjects.adminPages.AdminEnterprisePage;
import pageObjects.adminPages.AdminSettingsPage;
import pageObjects.adminPages.AdminStartupPage;
import pageObjects.onboardingPages.*;
import pageObjects.usecaseSharing.ApplyUseCaseLoginPage;
import pageObjects.usecaseSharing.ApplyUseCaseSignUpPage;
import reusableComponents.CommonMethods;

public class ObjectsRepo {
	
	public static WebDriver driver;
	public static NgWebDriver ngDriver;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static Faker faker;
	public static SoftAssert verify;

	public static TxfLoginPage txfLoginPage;
	public static TxfSignUpPage txfSignUpPage;
	public static ForgotPasswordPage forgotPasswordPage;
	public static OtpVerificationPage otpVerificationPage;
	public static EntitySelectionPage entitySelectionPage;
	public static EnterpriseUsecasePage enterpriseUsecasePage;
	public static StartupProfilePage startupProfilePage;
	public static StartupSolutionsPage startupSolutionsPage;
	public static AdminStartupPage adminStartupPage;
	public static AdminEnterprisePage adminEnterprisePage;
	public static AdminSettingsPage adminSettingsPage;
	public static userOnboardPage onboardPage;
	public static EnterpriseProfilePage enterpriseProfilePage;
	public static UserApprovalPage userApprovalPage;
	public static EnterpriseDashboardPage enterpriseDashboardPage;
	public static StartupDashboardPage startupDashboardPage;
	public static GuestProfilePage guestProfilePage;

	public static ApplyUseCaseSignUpPage applyUseCaseSignUpPage;
	public static ApplyUseCaseLoginPage applyUseCaseLoginPage;
	
	public CommonMethods commonMethods = new CommonMethods();
}
