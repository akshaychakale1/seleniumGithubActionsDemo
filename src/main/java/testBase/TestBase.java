package testBase;

import java.net.URL;
import java.time.Duration;

import com.github.javafaker.Faker;
import com.paulhammant.ngwebdriver.NgWebDriver;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.asserts.SoftAssert;
import pageObjects.*;
import pageObjects.adminPages.AdminEnterprisePage;
import pageObjects.adminPages.AdminSettingsPage;
import pageObjects.adminPages.AdminStartupPage;
import pageObjects.onboardingPages.*;
import pageObjects.usecaseSharing.ApplyUseCaseLoginPage;
import pageObjects.usecaseSharing.ApplyUseCaseSignUpPage;
import reusableComponents.PropertiesOperations;

import static io.restassured.RestAssured.given;

public class TestBase extends ObjectsRepo {
	
	public void LaunchBrowserAndNavigate() throws Exception {
		//read prop file and get browser and url
//		String browser = PropertiesOperations.getPropertyValueByKey("browser");
		String browser = System.getProperty("browser", PropertiesOperations.getPropertyValueByKey("browser"));
		String url = System.getProperty("url",PropertiesOperations.getPropertyValueByKey("url"));
		String runMode= PropertiesOperations.getPropertyValueByKey("runMode");
		
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();

			if(runMode.equalsIgnoreCase("remote")) {
            DesiredCapabilities cap =new DesiredCapabilities();
            cap.setBrowserName(BrowserType.CHROME);
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
			}else {
				ChromeOptions chromeOptions =new ChromeOptions();
				chromeOptions.addArguments("--silent");
				chromeOptions.addArguments("--incognito");
				chromeOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
				driver = new ChromeDriver(chromeOptions);
			}

		} else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			if(runMode.equalsIgnoreCase("remote")) {
				DesiredCapabilities cap =new DesiredCapabilities();
				cap.setBrowserName(BrowserType.FIREFOX);
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
			}else {
				driver = new FirefoxDriver();
			}
		} else if(browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			 driver = new InternetExplorerDriver();
		}else if (browser.equalsIgnoreCase("edge")){
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}else if (browser.equalsIgnoreCase("headless")){
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions =new ChromeOptions();
			chromeOptions.addArguments("window-size=1920,1080");
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--incognito");
			chromeOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
			driver = new ChromeDriver(chromeOptions);
		}

		ngDriver = new NgWebDriver((JavascriptExecutor) driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(PropertiesOperations.getPropertyValueByKey("implicitWait"))));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(PropertiesOperations.getPropertyValueByKey("pageLoadTimeout"))));
		driver.manage().window().maximize();

		driver.get(url);
	}


	@BeforeMethod   /// it will get execute before each test method within current class
	public void setupMethod()  {

		try {
			LaunchBrowserAndNavigate();
		}catch (Exception e){
			e.printStackTrace();
		}

		verify = new SoftAssert();
		faker =new Faker();
		txfLoginPage = new TxfLoginPage();
		txfSignUpPage = new TxfSignUpPage();
		forgotPasswordPage = new ForgotPasswordPage();
		otpVerificationPage= new OtpVerificationPage();
		entitySelectionPage = new EntitySelectionPage();
		enterpriseUsecasePage= new EnterpriseUsecasePage();
		onboardPage = new userOnboardPage();
		enterpriseProfilePage = new EnterpriseProfilePage();
		startupProfilePage = new StartupProfilePage();
		startupSolutionsPage = new StartupSolutionsPage();
		adminStartupPage = new AdminStartupPage();
		adminEnterprisePage = new AdminEnterprisePage();
		adminSettingsPage = new AdminSettingsPage();
		userApprovalPage = new UserApprovalPage();
		enterpriseDashboardPage = new EnterpriseDashboardPage();
		startupDashboardPage = new StartupDashboardPage();
		guestProfilePage = new GuestProfilePage();

		applyUseCaseSignUpPage = new ApplyUseCaseSignUpPage();
		applyUseCaseLoginPage = new ApplyUseCaseLoginPage();
	}
	
	@AfterMethod
	public void cleanUp() {
		driver.quit();
	}


}
