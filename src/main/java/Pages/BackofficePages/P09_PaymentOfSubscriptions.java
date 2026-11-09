package Pages.BackofficePages;

import Utilities.BackofficeBasedFunctions;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.awt.*;

public class P09_PaymentOfSubscriptions {
    private final WebDriver driver;
    private final By ViewButton = By.xpath("(//li[.//*[local-name()='svg' and contains(@class,'view-icon')]])[1]");
    private final By FirstSubscriptionItemCheckBox = By.xpath("(//table[contains(@class, 'subscription')]//tbody//tr//input[@type='checkbox'])[1]");
    public P09_PaymentOfSubscriptions EnterThePersonalIdentificationNumber(String personalidentifier) {
        BackofficeBasedFunctions.EnterThePersonalIdentificationNumber(driver, personalidentifier);
        return this;
    }

    public P09_PaymentOfSubscriptions(WebDriver driver) {
        this.driver = driver;
    }

    public P09_PaymentOfSubscriptions SearchOnRecordNumber(String recordnumber) throws InterruptedException {
        BackofficeBasedFunctions.SearchOnRecordNumber(driver, recordnumber);
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

    public P09_PaymentOfSubscriptions ClickOnFirstSubscriptionItem() {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, FirstSubscriptionItemCheckBox);
        return this;
    }

    public P09_PaymentOfSubscriptions ViewFirstRequest() {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, ViewButton);
        return this;
    }

    public P09_PaymentOfSubscriptions ConfirmRequest() throws InterruptedException, AWTException {
        BackofficeBasedFunctions.ConfirmRequest(driver);
        return this;
    }
    public P09_PaymentOfSubscriptions ClickOnAddNewRequest_Button () throws InterruptedException {
        BackofficeBasedFunctions.ClickOnAddNewRequest_Button(driver);
        return this;
    }
    public P04_InvoicesPage SendingRequestToCollect(){
        BackofficeBasedFunctions.SendingRequestToCollect(driver);
        return new P04_InvoicesPage(driver);
    }
    public P09_PaymentOfSubscriptions clickonsavedmedicalprovider() throws InterruptedException {
        BackofficeBasedFunctions.ClickOnSavedMedicalProvider(driver);
        return this;
    }
}
