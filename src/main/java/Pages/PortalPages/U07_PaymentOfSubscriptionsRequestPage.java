package Pages.PortalPages;

import Utilities.JsonUtil;
import Utilities.LogsUtiles;
import Utilities.Utility;
import Utilities.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class U07_PaymentOfSubscriptionsRequestPage {
    public U07_PaymentOfSubscriptionsRequestPage(WebDriver driver) { this.driver = driver;}
    private WebDriver driver;

    private final By AcceptConditionsCheckBox = By.xpath("//input[@formcontrolname=\"conditions\"]");
    private final By StartServiceButton = By.xpath("//button[contains(text(),' إبدا الخدمة الآن ')]");
    private final By ConfirmButtonOfPopUp = By.xpath("//button[contains(@class,'swal2-confirm')]");
    private final By PopUpMessage = By.xpath("//div[@id=\"swal2-html-container\"]");

    // 1- بيانات السجل
    private final By RecordNumberField = By.xpath("//div[@id='test-l-1']//label[normalize-space(text())='رقم السجل']/following-sibling::*[1]");
    private final By FirstRecordInSearchResults = By.xpath("(//div[@id='test-l-1']//table/tbody/tr)[1]");
    private final By NextButtonOfRecordData = By.xpath("//div[@id='test-l-1']//button[contains(text(),'التالى')]");

    // 2- بنود الاشتراكات المستحقه
    private final By FirstSubscriptionItem = By.xpath("(//div[@id='test-l-2']//table[contains(@class,'subscription')]//tbody//input[@type='checkbox'])[1]");
    private final By NextButtonOfSubscriptionItems = By.xpath("//div[@id='test-l-2']//button[contains(text(),'التالى')]");

    // 3- المرفقات
    private final By AddAttachmentButton = By.xpath("//div[@id='test-l-3']//button[@title=\"إضافة ملف جديد\"]");
    private final By NextButtonOfAttachments = By.xpath("//div[@id='test-l-3']//button[contains(text(),'التالى')]");

    // 4- تقديم الطلب
    private final By SendCertificateToBranchField = By.xpath("//label[normalize-space(text())='تقديم الشهادة الى فرع']/following-sibling::*[1]");
    private final By SendRequestButton = By.xpath("//div[@id='test-l-4']//button[contains(text(),'ارسال الطلب')]");
    // بتظهر لو البند اتبعت قبل كده في طلب تاني
    private final By SameItemSelectedBeforeMessage = By.xpath("//div[contains(@class,'toast-message') and contains(text(),'نفس البند')]");

    public U07_PaymentOfSubscriptionsRequestPage StartTheService () {
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.visibilityOfElementLocated(AcceptConditionsCheckBox));
        if (!driver.findElement(AcceptConditionsCheckBox).isSelected()) {
            Utility.WaitUntilReady_And_CLICK(driver,AcceptConditionsCheckBox);
        }
        Utility.WaitUntilReady_And_CLICK(driver,StartServiceButton);
        return this;
    }

    // اول سجلات في الجدول مالهاش اشتراكات مستحقة فبنبحث بسجل عليه مستحقات وناخد اول نتيجة
    public U07_PaymentOfSubscriptionsRequestPage SearchOnRecordAndSelectFirstResult (String RecordNumber) {
        // نستنى جدول سجلات الحساب يحمل الاول عشان مايمسحش نتيجة البحث
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.visibilityOfElementLocated(FirstRecordInSearchResults));
        Utility.WaitUntilReady_And_SENDKEYS(driver,RecordNumberField,RecordNumber);
        // التالى بيعمل بحث برقم السجل
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfRecordData);
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.textToBePresentInElementLocated(FirstRecordInSearchResults,RecordNumber));
        Utility.WaitUntilReady_And_CLICK(driver,FirstRecordInSearchResults);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfRecordData);
        return this;
    }

    public U07_PaymentOfSubscriptionsRequestPage SelectFirstSubscriptionItem () {
        Utility.WaitUntilReady_And_CLICK(driver,FirstSubscriptionItem);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfSubscriptionItems);
        return this;
    }

    public U07_PaymentOfSubscriptionsRequestPage FillAttachmentSection () {
        Utility.UploadAttachments(driver,AddAttachmentButton);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfAttachments);
        return this;
    }

    public U07_PaymentOfSubscriptionsRequestPage SelectBranch (String Branch) {
        By BranchChoice = By.xpath("//div[@role='option'][normalize-space()='" + Branch + "']");
        Utility.WaitUntilReady_And_CLICK(driver,SendCertificateToBranchField);
        Utility.WaitUntilReady_And_CLICK(driver,BranchChoice);
        return this;
    }

    public U07_PaymentOfSubscriptionsRequestPage SendTheRequestAndSaveRequestNumber () {
        Utility.WaitUntilReady_And_CLICK(driver,SendRequestButton);
        // يا اما بوب اب التأكيد يظهر يا اما رسالة ان البند اتختار قبل كده في طلب تاني
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(ConfirmButtonOfPopUp),
                ExpectedConditions.visibilityOfElementLocated(SameItemSelectedBeforeMessage)));
        if (!driver.findElements(SameItemSelectedBeforeMessage).isEmpty()) {
            String Message = driver.findElement(SameItemSelectedBeforeMessage).getText();
            LogsUtiles.info("Subscription item was already sent before: " + Message);
            return this;
        }
        Utility.WaitUntilReady_And_CLICK(driver,ConfirmButtonOfPopUp);
        // نفس البوب اب بيتغير من رسالة التأكيد لرسالة "تم ارسال" فبنستنى الرسالة الجديدة
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.textToBePresentInElementLocated(PopUpMessage,"تم ارسال"));
        String Message = Utility.GETTEXT(driver,PopUpMessage);
        String requestNumber = Message.replaceAll("[^0-9]", "");
        Validations.ValidationTrue(!requestNumber.isEmpty(), "Request number not found in: " + Message);
        JsonUtil.writeValue("PortalPaymentOfSubscriptionsRequestNumber", requestNumber);
        return this;
    }

}
