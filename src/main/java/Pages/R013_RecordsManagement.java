package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class R013_RecordsManagement {

    private final By AddNewRequest_Button = By.xpath("//img[@src=\"../../../assets/images/circle-plus-white.svg\"]");
    private final By Loading_Circle = By.xpath("//mat-spinner[@mode=\"indeterminate\"]");
    private final By ReviewRequest = By.xpath("//a[@href=\"/records/view/8ec81e3a-3618-ea53-2ef2-e5a182ef3334\"]");
    private final By OutstandingSbscriptions = By.xpath("//label[normalize-space(text())='نوع البند']/following-sibling::*[1]");
    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");
    private final By DueAmount = By.xpath("//input-control[@formcontrolname='value']//input");
    private final By ItemName = By.xpath("//input-control[@formcontrolname='desc']//input");
    private final WebDriver driver;
    private final By YearsField = By.xpath("//label[normalize-space(text())='السنة']/following-sibling::*[1]");
    private final By FixedDateOfYear = By.xpath("//td[@role='gridcell']/span[contains(text(), \"٢٠٢١\")]");
    private final By RecordNumber = By.xpath("//input[@placeholder=\"ابحث برقم السجل  \"]");
    private final By RequestsIcon = By.xpath("//img[@src=\"../../assets/images/certificates-icon.svg\"]");
    private final By Payingsubscriptions = By.xpath("//a[@href=\"/requests/membership-collection-requests?title=%D8%B7%D9%84%D8%A8%20%D8%B3%D8%AF%D8%A7%D8%AF%20%D8%A7%D9%84%D8%A7%D8%B4%D8%AA%D8%B1%D8%A7%D9%83%D8%A7%D8%AA\"]");

    public R013_RecordsManagement(WebDriver driver) {
        this.driver = driver;
    }

    public R013_RecordsManagement SearchOfRecordNumber(String record) throws InterruptedException {
        Utility.WatingAndSENDKEYS(driver, RecordNumber, Loading_Circle, record);
        driver.findElement(RecordNumber).sendKeys(Keys.ENTER);
        return this;
    }

    public R013_RecordsManagement ClickOnAddNewRequest_Button() throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, AddNewRequest_Button, Loading_Circle);
        return this;
    }

    public R013_RecordsManagement ClickOnEditRequest() {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, ReviewRequest, Loading_Circle);
        return this;
    }

    public R013_RecordsManagement OutstandingSubscriptions(String item, String amount) throws InterruptedException {
        Utility.CLICKONELEMENTS(driver, YearsField);
        Utility.CLICKONELEMENTS(driver, FixedDateOfYear);
        Utility.CLICKONELEMENTS(driver, OutstandingSbscriptions);
        Utility.CLICKONELEMENTS(driver, Option1ForAnyList);
        Utility.SENDKEYS(driver, DueAmount, amount);
        Utility.SENDKEYS(driver, ItemName, item);

        return this;
    }

    public P09_PaymentOfSubscriptions ClickOnPayingSub() throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon,Loading_Circle);
        Utility.CLICKONELEMENTS(driver,Payingsubscriptions);
        return new P09_PaymentOfSubscriptions(driver);
}



}
