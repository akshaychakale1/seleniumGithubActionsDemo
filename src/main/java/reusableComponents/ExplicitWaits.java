package reusableComponents;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import testBase.TestBase;

import java.time.Duration;
import java.util.List;

public class ExplicitWaits extends TestBase {


    public static void waitTillAngularRequestIsFinish(){
        try {
            ngDriver.waitForAngularRequestsToFinish();
        }catch (Exception e){
            System.out.println("Exception : " + e.getMessage());
        }
    }

    public static void explicitWaitElementToBeClickable(WebElement element, int time)
    {
        WebDriverWait clickableWait = new WebDriverWait(driver, Duration.ofSeconds(time));
        clickableWait.until(ExpectedConditions.elementToBeClickable(element));
    }


    /** To Wait Until Element to be Selectable */
    public static void explicitWaitElementToBeSelected(WebElement element, int time) {
        WebDriverWait selectableWait = new WebDriverWait(driver, Duration.ofSeconds(time));
        selectableWait.until(ExpectedConditions.elementToBeSelected(element));
    }


    /** To Wait Until Element has the text */
    public static void explicitWaitTextToBePresentInElement(WebElement element, int time, String text) {
        WebDriverWait textToBePresent = new WebDriverWait(driver, Duration.ofSeconds(time));
        textToBePresent.until(ExpectedConditions.textToBePresentInElement(element, text));
    }


    /** To Wait Until Title contains the text */
    public static void explicitWaitTitleContains(WebElement element, int time, String title) {
        WebDriverWait titleContains = new WebDriverWait(driver, Duration.ofSeconds(time));
        titleContains.until(ExpectedConditions.titleContains(title));
    }


    /** To Wait Until Title is */
    public static void explicitWaitTitleIs(WebElement element, int time, String title) {
        WebDriverWait titleIs = new WebDriverWait(driver, Duration.ofSeconds(time));
        titleIs.until(ExpectedConditions.titleIs(title));
    }


    /** To Wait Until Element to be Visible */
    public static void explicitWaitVisibilityOfElement(WebElement element, int time) {
        WebDriverWait elementToBeVisible = new WebDriverWait(driver, Duration.ofSeconds(time));
        elementToBeVisible.until(ExpectedConditions.visibilityOf(element));
    }


    /** To Wait Until Element is Selected */
    public static void explicitWaitSelectionStateToBe(WebElement element, int time, boolean selected) {
        WebDriverWait elementIsSelected = new WebDriverWait(driver, Duration.ofSeconds(time));
        elementIsSelected.until(ExpectedConditions.elementSelectionStateToBe(element, selected));
    }


    /** To Wait Until Elements to be Visible */
    public static void explicitWaitVisibilityOfElements(List<WebElement> element, int time) {
        WebDriverWait elementsToBeVisible = new WebDriverWait(driver, Duration.ofSeconds(time));
        elementsToBeVisible.until(ExpectedConditions.visibilityOfAllElements(element));
    }


    /*Select using By Method*/
    /** To Wait Until Element to be Clickable */
    public static void explicitWaitElementToBeClickable(By element, int time) {
        WebDriverWait clickableWait = new WebDriverWait(driver, Duration.ofSeconds(time));
        clickableWait.until(ExpectedConditions.elementToBeClickable(element));
    }


    /** To Wait Until Element to be Selectable */
    public static void explicitWaitElementToBeSelected(By element, int time) {
        WebDriverWait selectableWait = new WebDriverWait(driver, Duration.ofSeconds(time));
        selectableWait.until(ExpectedConditions.elementToBeSelected(element));
    }


    /** To Wait Until Title contains the text */
    public static void explicitWaitTitleContains(By element, int time, String title) {
        WebDriverWait titleContains = new WebDriverWait(driver, Duration.ofSeconds(time));
        titleContains.until(ExpectedConditions.titleContains(title));
    }


    /** To Wait Until Title is */
    public static void explicitWaitTitleIs(By element, int time, String title) {
        WebDriverWait titleIs = new WebDriverWait(driver, Duration.ofSeconds(time));
        titleIs.until(ExpectedConditions.titleIs(title));
    }


    /** To Wait Until Element to be Visible */
    public static void explicitWaitVisibilityOfElement(By element, int time) {
        WebDriverWait elementToBeVisible = new WebDriverWait(driver, Duration.ofSeconds(time));
        elementToBeVisible.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    /** To Wait Until Element to be inVisible */
    public static void explicitWaitInVisibilityOfElement(WebElement element, int time) {
        WebDriverWait elementToBeVisible = new WebDriverWait(driver, Duration.ofSeconds(time));
        elementToBeVisible.until(ExpectedConditions.invisibilityOf(element));
    }


    /** To Wait Until Element is Selected */
    public static void explicitWaitSelectionStateToBe(By element, int time, boolean selected) {
        WebDriverWait elementToBeVisible = new WebDriverWait(driver, Duration.ofSeconds(time));
        elementToBeVisible.until(ExpectedConditions.elementSelectionStateToBe(element, selected));
    }


    /** To Wait for the Alert */
    public static void explicitWaitForAlert(int time) {
        WebDriverWait waitForAlert = new WebDriverWait(driver, Duration.ofSeconds(time));
        waitForAlert.until(ExpectedConditions.alertIsPresent());
    }

    public static void waitForPageToLoad(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript(
                        "return document.readyState"
                ).equals("complete");
            }
        });
    }
}
