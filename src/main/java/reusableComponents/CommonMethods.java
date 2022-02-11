package reusableComponents;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.core.Is;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class CommonMethods {

	public static Robot re;
	public static Select se;

	public void clearUserData(String email) throws Exception {
		RestAssured.baseURI= PropertiesOperations.getPropertyValueByKey("url");

		Map<String, String> loginBody=new HashMap<>();
		loginBody.put("email",PropertiesOperations.getPropertyValueByKey("adminUser"));
		loginBody.put("password",PropertiesOperations.getPropertyValueByKey("adminPass"));
		loginBody.put("invite_token","");
		loginBody.put("open_invite_token","");
		String loginResponse=given().contentType(ContentType.JSON).when().body(loginBody).post("/api/v2/login")
				.then().log().body().extract().asString();
		JsonPath js=new JsonPath(loginResponse);
		String authKey = js.getString("body.token");


		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("email",email);
		Map<String, String> authToken=new HashMap<>();
		authToken.put("Authorization",authKey);

		Response res=given().headers(authToken).contentType(ContentType.JSON).when()
				.body(requestBody).delete("/api/v2/clearEmail");
		res.then().log().body().extract().asString();
	}

	public void forceMatch(String ucs_id,String type) throws Exception {
		RestAssured.baseURI= PropertiesOperations.getPropertyValueByKey("url");

		Map<String, String> loginBody=new HashMap<>();
		loginBody.put("email",PropertiesOperations.getPropertyValueByKey("adminUser"));
		loginBody.put("password",PropertiesOperations.getPropertyValueByKey("adminPass"));
		loginBody.put("invite_token","");
		loginBody.put("open_invite_token","");
		String loginResponse=given().contentType(ContentType.JSON).when().body(loginBody).post("/api/v2/login")
				.then().extract().asString();
		JsonPath js=new JsonPath(loginResponse);
		String authKey = js.getString("body.token");

		Map<String, String> authToken=new HashMap<>();
		authToken.put("Authorization",authKey);

		given().log().body().headers(authToken).when().post("/api/v2/qa/force_match/"+ucs_id+"/"+type)
				.then().log().body().body("message", Is.is("operation success"));

	}

	public String fetchEmailLink(String email){
		try {
			Thread.sleep(8000);
		}catch (InterruptedException e){
			e.getMessage();
		}


		RestAssured.baseURI="https://getnada.com";
		RequestSpecification request = RestAssured.given();

		String resp = request.get("/api/v1/inboxes/" + email).then().extract().asString();

		JsonPath jsonPath = new JsonPath(resp);
		String messageId = jsonPath.getString("msgs[0].uid");

		String emailBody = request.get("/api/v1/messages/html/" + messageId).then().extract().asString();


		List<String> containedUrls = new ArrayList<String>();
		String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
		Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
		Matcher urlMatcher = pattern.matcher(emailBody);

		while (urlMatcher.find()) { containedUrls.add(emailBody.substring(urlMatcher.start(0),
				urlMatcher.end(0)));
		}

		List<String> txfurls=new ArrayList();

		for(String i:containedUrls){
			if (i.contains("thexfuture.com")){
				txfurls.add(i);
			}}


		String link = containedUrls.stream().max(Comparator.comparingInt(String::length)).get();
		System.out.println("Link >>> "+link);


		return link.replaceAll("amp;","");
	}

	public String fetchOtp(String link){
		String split = link.split("&")[0];
		String otp = split.split("=")[1];
		System.out.println("OTP >>> "+otp);
		return otp;
	}


	public String fetchEmailBody(String email) throws Exception {
		try {
			return getMail(email);
		}catch (Exception e){
			e.printStackTrace();
			return getMail(email);
		}
	}

	private String getMail(String email) throws Exception {
		Thread.sleep(5000);
		RestAssured.baseURI="https://api.mail7.io";
		RequestSpecification request = RestAssured.given();

		Map<String,String> body= new HashMap<>();
		body.put("apikey",PropertiesOperations.getPropertyValueByKey("apikey"));
		body.put("apisecret",PropertiesOperations.getPropertyValueByKey("apisecret"));
		body.put("to",email.split("@")[0]);
		body.put("domain",email.split("@")[1]);

		String res = request.queryParams(body).get("/inbox").then().log().body().extract().asString();

		JsonPath jsonResponse = new JsonPath(res);
		String text = jsonResponse.getString("data[0].mail_source.text");

		List<String> containedUrls = new ArrayList<String>();
		String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
		Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
		Matcher urlMatcher = pattern.matcher(text);

		while (urlMatcher.find()) { containedUrls.add(text.substring(urlMatcher.start(0),
				urlMatcher.end(0)));
		}
		System.out.println("link: "+ containedUrls.get(2));
		return containedUrls.get(2);
	}


	//common method to select dd value
	public void selectDropdownOption(WebElement element, String valueToBeSelected) throws Exception {
		Select os = new Select(element);
		try {
		os.selectByValue(valueToBeSelected);
		} catch(Exception e) {
			throw new Exception("Value is not present in dropdown for WebElement: "+element + "Value to be selected is: "+valueToBeSelected);
		}
		}

	
	public void selectRadioButtonValue(List<WebElement> element, String valueToBeSelected) {
		for (WebElement ref : element) {
			if(ref.getText().equalsIgnoreCase(valueToBeSelected)) {
				ref.click();
				break;
			}
			
		}
	}

	//To avoid StaleElementReferenceException
	public boolean retryingFindClick(WebElement element) {
			boolean result = false;
			int attempts = 0;
			while(attempts < 4) {
				try {
					element.click();
					result = true;
					break;
				} catch(StaleElementReferenceException e) {
				}
				attempts++;
			}
			return result;
	}


	public boolean isElementPresent(WebDriver driver, By locatorKey) {
		try {
			driver.findElement(locatorKey);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	//selecting check boxes
	public void selectCheckBoxes(List<WebElement> element, String checks) {
		String[] checksArray = checks.split(",");
		for (String str : checksArray) {   //speeding, Other
			for (WebElement ele : element) {
				if(ele.getText().equalsIgnoreCase(str)) {
					ele.click();
					break;
				}
			}
		}
		
	}
	
	//get dropdown options as list of string for compare
	public List<String> getDropDownOptionsAsList(WebElement element) {
		Select os = new Select(element);
		List<WebElement> list_webElement_model = os.getOptions();
		List<String> actualContents = new ArrayList<>();
		
		for (WebElement ref : list_webElement_model) {
			actualContents.add(ref.getText());
		}
		return actualContents;
	}

	public String GenerateRandomEMAILIDs()
	{
		String emailID = RandomStringUtils.randomAlphabetic(15).toString();
		String domain = RandomStringUtils.randomAlphabetic(7).toLowerCase().toString();

		return emailID + "@" + domain + ".com";
	}

	public String GenerateRandomExampleEmailIds()
	{
		String emailID = RandomStringUtils.randomAlphabetic(15).toString().toLowerCase();
		String domain = RandomStringUtils.randomAlphabetic(7).toLowerCase().toString();

		return emailID + "@" + domain + ".example.com";
	}

	/*	To Generate Random E-Mail IDs.*/
	public String GenerateRandomEMAILIDs(String DomainName)
	{
		String emailID = RandomStringUtils.randomAlphabetic(15).toString();

		return emailID + "@" + DomainName ;
	}

	public String generateRandomUrls()
	{
		String url = RandomStringUtils.randomAlphabetic(10).toString().toLowerCase();

		return  "www." + url +".com" ;
	}

	public String generateRandomString(int length){
		return RandomStringUtils.randomAlphabetic(length).toString();
	}


	public void moveToElement(WebDriver driver, WebElement element)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}

	public void dragAndDrop(WebDriver driver, WebElement elementOne, WebElement elementTwo)
	{
		Actions actions = new Actions(driver);
		actions.dragAndDrop(elementOne, elementTwo).release().build().perform();
	}

	public void rightClick(WebDriver driver, WebElement element)
	{
		Actions actions = new Actions(driver);
		actions.contextClick(element).build().perform();
	}

	public void doubleClick(WebDriver driver, WebElement element)
	{
		Actions actions = new Actions(driver);
		actions.doubleClick(element).build().perform();
	}

	public void clickElementByJS(WebElement element, WebDriver driver)
	{
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", element);
	}

	public void refreshBrowserByJS(WebDriver driver)
	{
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("history.go(0)");
	}

	public String getTitleByJS(WebDriver driver)
	{
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String title = js.executeScript("return document.title;").toString();
		return title;
	}


	public void scrollPageDown(WebDriver driver)
	{
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public void scrollToElement(WebDriver driver,WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}


	public  String getSystemDate()
	{
		DateFormat dateFormat = new SimpleDateFormat("_ddMMyyyy_HHmmss");
		Date date = new Date();
		return dateFormat.format(date);
	}


	/* To Press ENTER Key using Robot */
	public void hitEnter() throws Exception
	{
		re = new Robot();
		re.keyPress(KeyEvent.VK_ENTER);
		re.keyRelease(KeyEvent.VK_ENTER);
	}

	/* To Press Escape Key using Robot */
	public void hitEscape() throws Exception
	{
		re = new Robot();
		re.keyPress(KeyEvent.VK_ESCAPE);
		re.keyRelease(KeyEvent.VK_ESCAPE);
	}

	/* To Press BACKSPACE Key using Robot */
	public void hitBackspace() throws Exception
	{
		re = new Robot();
		re.keyPress(KeyEvent.VK_BACK_SPACE);
		re.keyRelease(KeyEvent.VK_BACK_SPACE);
	}


	/* To Press DELETE Key using Robot */
	public void hitDelete() throws Exception
	{
		re = new Robot();
		re.keyPress(KeyEvent.VK_DELETE);
		re.keyRelease(KeyEvent.VK_DELETE);
	}


	/* To Select all the Text/Web Elements using ROBOT */
	public void selectAll() throws Exception
	{
		re = new Robot();
		re.keyPress(KeyEvent.VK_CONTROL);
		re.keyPress(KeyEvent.VK_A);
		re.keyRelease(KeyEvent.VK_CONTROL);
		re.keyRelease(KeyEvent.VK_A);
	}


	/* To Copy all the Selected Text/Web Elements using ROBOT */
	public void copyAll() throws Exception
	{
		re = new Robot();
		re.keyPress(KeyEvent.VK_CONTROL);
		re.keyPress(KeyEvent.VK_C);
		re.keyRelease(KeyEvent.VK_CONTROL);
		re.keyRelease(KeyEvent.VK_C);
	}


	/* To Paste all the Selected Text/Web Elements using ROBOT */
	public void pasteAll() throws Exception
	{
		re = new Robot();
		re.keyPress(KeyEvent.VK_CONTROL);
		re.keyPress(KeyEvent.VK_V);
		re.keyRelease(KeyEvent.VK_CONTROL);
		re.keyRelease(KeyEvent.VK_V);
	}

	public void scrollPageDown() throws AWTException {
		re = new Robot();
		re.keyPress(KeyEvent.VK_PAGE_DOWN);
		re.keyRelease(KeyEvent.VK_PAGE_DOWN);
	}

	/* To Upload a File using JAVA AWT ROBOT */
	public void fileUpload(String FileToUpload) throws Exception
	{
		Thread.sleep(2000);
		StringSelection filetocopy = new StringSelection(FileToUpload);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filetocopy, null);
		Thread.sleep(1000);
		re = new Robot();
		re.keyPress(KeyEvent.VK_CONTROL);
		re.keyPress(KeyEvent.VK_V);
		re.keyRelease(KeyEvent.VK_V);
		re.keyRelease(KeyEvent.VK_CONTROL);
		re.keyPress(KeyEvent.VK_ENTER);
		re.keyRelease(KeyEvent.VK_ENTER);
	}


	/* To Perform Select Option by Visible Text */
	public void selectByVisibleText(WebElement element, String value)
	{
		se = new Select(element);
		se.selectByVisibleText(value);
	}


	/* To Perform Select Option by Index */
	public void selectByIndex(WebElement element, int value)
	{
		se = new Select(element);
		se.selectByIndex(value);
	}




}
