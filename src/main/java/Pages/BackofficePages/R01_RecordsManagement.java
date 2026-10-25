package Pages.BackofficePages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class R01_RecordsManagement {

    private final By AddNewRequest_Button = By.xpath("//img[@src=\"../../../assets/images/circle-plus-white.svg\"]");
    private final By OutstandingSbscriptions = By.xpath("//label[normalize-space(text())='نوع البند']/following-sibling::*[1]");
    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");
    private final By DueAmount = By.xpath("//input-control[@formcontrolname='value']//input");
    private final By ItemName = By.xpath("//input-control[@formcontrolname='desc']//input");
    private final By AddSubscriptionItemButton = By.xpath("//button[contains(text(), ' اضافة بند اشتراك آخر')]");
    private final WebDriver driver;
    private final By YearsField = By.xpath("//label[normalize-space(text())='السنة']/following-sibling::*[1]");
    private final By FixedDateOfYear = By.xpath("//td[@role='gridcell']/span[contains(text(), \"٢٠٢١\")]");
    private final By RecordNumber = By.xpath("//input[@placeholder=\"ابحث برقم السجل  \"]");
    private final By RequestsIcon = By.xpath("//img[@src=\"../../assets/images/certificates-icon.svg\"]");
    private final By Payingsubscriptions = By.xpath("//a[contains(@href,'/requests/membership-collection-requests')]");

    public R01_RecordsManagement(WebDriver driver) {
        this.driver = driver;
    }

    public R01_RecordsManagement SearchOfRecordNumber(String record) throws InterruptedException {
        Utility.WatingAndSENDKEYS(driver, RecordNumber, record);
        driver.findElement(RecordNumber).sendKeys(Keys.ENTER);
        return this;
    }

    public R01_RecordsManagement ClickOnAddNewRequest_Button() throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, AddNewRequest_Button);
        return this;
    }

    // زرار العرض بتاع صف السجل اللي رقمه جاي من الجيسون
    public R01_RecordsManagement ClickOnEditRequest(String record) {
        By ReviewRequest = By.xpath("//tr[td[normalize-space()='" + record + "']]//a[contains(@href,'/records/view/')]");
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, ReviewRequest);
        return this;
    }

    public R01_RecordsManagement OutstandingSubscriptions(String item, String amount) throws InterruptedException {
        Utility.CLICKONELEMENTS(driver, YearsField);
        Utility.CLICKONELEMENTS(driver, FixedDateOfYear);
        Utility.CLICKONELEMENTS(driver, OutstandingSbscriptions);
        Utility.CLICKONELEMENTS(driver, Option1ForAnyList);
        Utility.SENDKEYS(driver, DueAmount, amount);
        Utility.SENDKEYS(driver, ItemName, item);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, AddSubscriptionItemButton);

        return this;
    }

    public P09_PaymentOfSubscriptions ClickOnPayingSub() throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon);
        Utility.CLICKONELEMENTS(driver,Payingsubscriptions);
        return new P09_PaymentOfSubscriptions(driver);
}



}
