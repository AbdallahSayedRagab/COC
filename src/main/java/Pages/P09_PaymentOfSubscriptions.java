package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.awt.*;

public class P09_PaymentOfSubscriptions {
    private final By AddNewRequest_Button = By.xpath("//img[@src=\"../../../assets/images/circle-plus-white.svg\"]");
    private final WebDriver driver;
    private final By Personalidentificationnumber = By.xpath("//input-control[@formcontrolname='applicantNationalId']//input");
    private final By SearchingOfRecordNumField = By.xpath("(//input[@name=\"recordNumber\"])[1]");
    private final By Loading_Circle = By.xpath("//mat-spinner[@mode=\"indeterminate\"]");
    private final By FirstRowForRecordsResult = By.xpath("(//tr[@cdk-describedby-host=\"0\"]/td)[1]");
    private final By ConfirmRequestButton = By.xpath("//span[contains(text(),\"تاكيد الطلب\")]");
    private final By AcceptRequestButtonOfPopUP = By.xpath("//button[contains(text(),\" اقبل الطلب\")]");
    private final By ViewButton = By.xpath("(//li[.//*[local-name()='svg' and contains(@class,'view-icon')]])[1]");
    private final By PullTheRequest = By.xpath("//label[contains(normalize-space(), 'اسحب الطلب')]//input[@type='checkbox']");
    private final By SendToCollectButton = By.xpath("//span[contains(normalize-space(), 'أرسال للتحصيل')]");
    private final By SendToCollectButtonPouUp = By.xpath("//button[contains(normalize-space(), 'ارسل الطلب للتحصيل')]");
    private final By saveamedicalprovider=By.xpath("//button[@type='submit']");
    public P09_PaymentOfSubscriptions EnterThePersonalIdentificationNumber(String personalidentifier) {
        Utility.SENDKEYS(driver, Personalidentificationnumber, personalidentifier);
        return this;
    }

    public P09_PaymentOfSubscriptions(WebDriver driver) {
        this.driver = driver;
    }

    public P09_PaymentOfSubscriptions SearchOnRecordNumber(String recordnumber) throws InterruptedException {
        Utility.WatingAndSENDKEYS(driver, SearchingOfRecordNumField, Loading_Circle, recordnumber);
        driver.findElement(SearchingOfRecordNumField).sendKeys(Keys.ENTER);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, FirstRowForRecordsResult, Loading_Circle);
        return this;
    }

    public P09_PaymentOfSubscriptions ClickOnLastYear() {
        By allYearsCheckboxes = By.xpath("//table[contains(@class, 'subscription')]//tbody//tr//input[@type='checkbox']");
        int size = driver.findElements(allYearsCheckboxes).size();
        System.out.println("The number of subscription years found: " + size);

        if (size > 0) {
            By lastYearCheckbox = By
                    .xpath("(//table[contains(@class, 'subscription')]//tbody//tr//input[@type='checkbox'])[last()]");
            try {
                Utility.CLICKONELEMENTS(driver, lastYearCheckbox);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No subscription years found to click.");
        }
        return this;
    }

    public P09_PaymentOfSubscriptions ConfirmRequest() throws InterruptedException, AWTException {
        Utility.CLICKONELEMENTS(driver, ConfirmRequestButton);
        Utility.CLICKONELEMENTS(driver, AcceptRequestButtonOfPopUP);
        return this;
    }
    public P09_PaymentOfSubscriptions ClickOnAddNewRequest_Button () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddNewRequest_Button,Loading_Circle);
        return this;
    }
    public P04_InvoicesPage SendingRequestToCollect(){
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ViewButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SendToCollectButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SendToCollectButtonPouUp,Loading_Circle);
        return new P04_InvoicesPage(driver);
    }
    public P09_PaymentOfSubscriptions clickonsavedmedicalprovider() throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,saveamedicalprovider,Loading_Circle);
        return this;
    }
}
