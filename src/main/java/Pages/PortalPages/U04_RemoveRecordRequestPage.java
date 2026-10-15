package Pages.PortalPages;

import Utilities.JsonUtil;
import Utilities.Utility;
import Utilities.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class U04_RemoveRecordRequestPage {
    public U04_RemoveRecordRequestPage(WebDriver driver) { this.driver = driver;}
    private WebDriver driver;

    private final By AcceptConditionsCheckBox = By.xpath("//input[@formcontrolname=\"conditions\"]");
    private final By StartServiceButton = By.xpath("//button[contains(text(),' إبدا الخدمة الآن ')]");
    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");
    private final By ConfirmButtonOfPopUp = By.xpath("//button[contains(@class,'swal2-confirm')]");
    private final By PopUpMessage = By.xpath("//div[@id=\"swal2-html-container\"]");

    // 1- بيانات السجل (الجدول بيعرض سجلات الحساب لوحده)
    private final By FirstRecordInSearchResults = By.xpath("(//div[@id='test-l-1']//table/tbody/tr)[1]");
    private final By NextButtonOfRecordData = By.xpath("//div[@id='test-l-1']//button[contains(text(),'التالى')]");

    // 2- تفاصيل سبب المحو
    private final By RemovalReasonField = By.xpath("//label[normalize-space(text())='سبب المحو']/following-sibling::*[1]");
    private final By NextButtonOfRemovalReason = By.xpath("//div[@id='test-l-2']//button[contains(text(),'التالى')]");

    // 3- بيانات الشهادة
    private final By SendCertificateToBranchField = By.xpath("//label[normalize-space(text())='تقديم الشهادة الى فرع']/following-sibling::*[1]");
    private final By SendCertificateToGovernorateField = By.xpath("//label[normalize-space(text())='تقديم الشهادة الى محافظة']/following-sibling::*[1]");
    private final By CommercialRegistryOfficeField = By.xpath("//div[@id='test-l-3']//label[normalize-space(text())='مكتب سجل تجاري']/following-sibling::*[1]");
    private final By NextButtonOfCertificateData = By.xpath("//div[@id='test-l-3']//button[contains(text(),'التالى')]");

    // 4- المرفقات (البطاقة الشخصية اجباري)
    private final By AddAttachmentButton = By.xpath("//div[@id='test-l-4']//button[@title=\"إضافة ملف جديد\"]");
    private final By SendRequestButton = By.xpath("//div[@id='test-l-4']//button[contains(text(),'ارسال الطلب')]");

    public U04_RemoveRecordRequestPage StartTheService () {
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.visibilityOfElementLocated(AcceptConditionsCheckBox));
        if (!driver.findElement(AcceptConditionsCheckBox).isSelected()) {
            Utility.WaitUntilReady_And_CLICK(driver,AcceptConditionsCheckBox);
        }
        Utility.WaitUntilReady_And_CLICK(driver,StartServiceButton);
        return this;
    }

    public U04_RemoveRecordRequestPage SelectFirstRecord () {
        Utility.WaitUntilReady_And_CLICK(driver,FirstRecordInSearchResults);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfRecordData);
        return this;
    }

    public U04_RemoveRecordRequestPage FillRemovalReason (String Reason) {
        Utility.WaitUntilReady_And_SENDKEYS(driver,RemovalReasonField,Reason);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfRemovalReason);
        return this;
    }

    public U04_RemoveRecordRequestPage FillCertificateData (String Branch , String Governorate) {
        // اول محافظة في الليستة مفيهاش مكاتب سجل فبنختار المحافظة بالاسم
        By BranchChoice = By.xpath("//div[@role='option'][normalize-space()='" + Branch + "']");
        By GovernorateChoice = By.xpath("//div[@role='option'][normalize-space()='" + Governorate + "']");
        Utility.WaitUntilReady_And_CLICK(driver,SendCertificateToBranchField);
        Utility.WaitUntilReady_And_CLICK(driver,BranchChoice);
        Utility.WaitUntilReady_And_CLICK(driver,SendCertificateToGovernorateField);
        Utility.WaitUntilReady_And_CLICK(driver,GovernorateChoice);
        Utility.WaitUntilReady_And_CLICK(driver,CommercialRegistryOfficeField);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfCertificateData);
        return this;
    }

    public U04_RemoveRecordRequestPage FillAttachmentSection () {
        Utility.UploadAttachments(driver,AddAttachmentButton);
        return this;
    }

    public U04_RemoveRecordRequestPage SendTheRequestAndSaveRequestNumber () {
        Utility.WaitUntilReady_And_CLICK(driver,SendRequestButton);
        Utility.WaitUntilReady_And_CLICK(driver,ConfirmButtonOfPopUp);
        // نفس البوب اب بيتغير من رسالة التأكيد لرسالة "تم ارسال" فبنستنى الرسالة الجديدة
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.textToBePresentInElementLocated(PopUpMessage,"تم ارسال"));
        String Message = Utility.GETTEXT(driver,PopUpMessage);
        String requestNumber = Message.replaceAll("[^0-9]", "");
        Validations.ValidationTrue(!requestNumber.isEmpty(), "Request number not found in: " + Message);
        JsonUtil.writeValue("PortalRemoveRecordRequestNumber", requestNumber);
        return this;
    }

}
