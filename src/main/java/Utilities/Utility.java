package Utilities;

import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import Utilities.Scrolling;


import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class Utility {
//    public static void CLICKONELEMENTS(WebDriver driver, By Locator ) throws InterruptedException {
//        Waits.WaitForElementClickable(driver,Locator,10);
//        driver.findElement(Locator).click();
//
//    }
        @Step("Clicking On :{Locator}")
    public static void CLICKONELEMENTS(WebDriver driver, By Locator ) throws InterruptedException {
            Scrolling.ScrollToElement(driver,Locator);
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(Locator));
        driver.findElement(Locator).click();
//        new WebDriverWait(driver, Duration.ofSeconds(3));
//        Thread.sleep(1500L);
        ((JavascriptExecutor) driver).executeAsyncScript(
                "var callback = arguments[arguments.length - 1]; setTimeout(callback, 1000);");
        LogsUtiles.info("Clicked on " + Locator.toString());

    }

public static void WatingLoadingCircle_And_CLICKONELEMENTS(WebDriver driver, By Locator, By LoadingCircle) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    wait.until(ExpectedConditions.elementToBeClickable(Locator));
    wait.until(ExpectedConditions.invisibilityOfElementLocated(LoadingCircle));
    Scrolling.ScrollToElement(driver,Locator);
    driver.findElement(Locator).click();
    wait.until(ExpectedConditions.invisibilityOfElementLocated(LoadingCircle));
    LogsUtiles.info("Clicked on " + Locator.toString());
}
    public static void WatingLoadingCircle_And_CLICKON_WebElement(
            WebDriver driver, WebElement element, By loadingCircle) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingCircle));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static void CLICKONVISIBLEELEMENTS(WebDriver driver, By Locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(Locator));
        driver.findElement(Locator).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(Locator));

    }
    //    @Step("Adding Data On: {Locator}")
    public static void SENDKEYS(WebDriver driver, By Locator, String text) {
        Scrolling.ScrollToElement(driver,Locator);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(elementToBeClickable(Locator));
        driver.findElement(Locator).sendKeys(text);
        LogsUtiles.info(" Data sent to  " + Locator.toString() + " is " + text);

    }
    public static void WatingAndSENDKEYS(WebDriver driver, By Locator, By LoadingCircle , String text) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(elementToBeClickable(Locator));
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfElementLocated(LoadingCircle));
        Scrolling.ScrollToElement(driver,Locator);
        driver.findElement(Locator).sendKeys(text);
        LogsUtiles.info(" Data sent to  " + Locator.toString() + " is " + text);

    }

    public static void SELECT(WebDriver driver, By locator, int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(elementToBeClickable(locator));
        Scrolling.ScrollToElement(driver,locator);
        WebElement dropdownElement = driver.findElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(index);
    }

    public static String GETTEXT(WebDriver driver, By Locator , By LoadingCircle ) {
//        new WebDriverWait(driver, Duration.ofSeconds(5)).until(elementToBeClickable(Locator));
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfElementLocated(LoadingCircle));
        String Text = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(Locator)).getText();
        LogsUtiles.info(" Data of " + Locator.toString() + " is " + Text);
        return Text ;
    }
    public static void HOVER(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.scrollToElement(element).moveToElement(element).perform();
    }

    public static void SCREENSHOT (WebDriver driver ,String ImageName) throws IOException {
        String Path = System.getProperty("user.dir") + "/src/main/java/Shots/";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File target = new File(Path + ImageName + ".png");
        FileUtils.copyFile(src,target);
    }
    public static int COUNT(WebDriver driver, By locator) {
        List<WebElement> products = driver.findElements(locator);
        return products.size();
    }

    public static void WaitForPageLoad (WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                                .executeScript("return document.readyState")
                                .equals("complete"));
                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));


    }



}
