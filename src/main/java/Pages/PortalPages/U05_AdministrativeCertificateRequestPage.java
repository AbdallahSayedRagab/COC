package Pages.PortalPages;

import Utilities.JsonUtil;
import Utilities.Utility;
import Utilities.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class U05_AdministrativeCertificateRequestPage {
    public U05_AdministrativeCertificateRequestPage(WebDriver driver) { this.driver = driver;}
    private WebDriver driver;

    private final By AcceptConditionsCheckBox = By.xpath("//input[@formcontrolname=\"conditions\"]");
    private final By StartServiceButton = By.xpath("//button[contains(text(),' إبدا الخدمة الآن ')]");
    private final By ConfirmButtonOfPopUp = By.xpath("//button[contains(@class,'swal2-confirm')]");
    private final By PopUpMessage = By.xpath("//div[@id=\"swal2-html-container\"]");

    // 1- بيانات السجل (الجدول بيعرض سجلات الحساب لوحده)
    private final By FirstRecordInSearchResults = By.xpath("(//div[@id='test-l-1']//table/tbody/tr)[1]");
    private final By NextButtonOfRecordData = By.xpath("//div[@id='test-l-1']//button[contains(text(),'التالى')]");

    // 2- تفاصيل شهادة ادارية
    private final By CertificateDetailsField = By.xpath("//label[normalize-space(text())='تفاصيل الشهادة الادارية']/following-sibling::*[1]");
    private final By NextButtonOfCertificateDetails = By.xpath("//div[@id='test-l-2']//button[contains(text(),'التالى')]");

    // 3- المرفقات
    private final By AddAttachmentButton = By.xpath("//div[@id='test-l-3']//button[@title=\"إضافة ملف جديد\"]");
    private final By NextButtonOfAttachments = By.xpath("//div[@id='test-l-3']//button[contains(text(),'التالى')]");

    // 4- بيانات الشهادة
    private final By SendCertificateToBranchField = By.xpath("//label[normalize-space(text())='تقديم الشهادة الى فرع']/following-sibling::*[1]");
    private final By SubmitToField = By.xpath("//label[normalize-space(text())='للتقديم الي']/following-sibling::*[1]");
    private final By SendRequestButton = By.xpath("//div[@id='test-l-4']//button[contains(text(),'ارسال الطلب')]");

    public U05_AdministrativeCertificateRequestPage StartTheService () {
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.visibilityOfElementLocated(AcceptConditionsCheckBox));
        if (!driver.findElement(AcceptConditionsCheckBox).isSelected()) {
            Utility.WaitUntilReady_And_CLICK(driver,AcceptConditionsCheckBox);
        }
        Utility.WaitUntilReady_And_CLICK(driver,StartServiceButton);
        return this;
    }

    public U05_AdministrativeCertificateRequestPage SelectFirstRecord () {
        Utility.WaitUntilReady_And_CLICK(driver,FirstRecordInSearchResults);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfRecordData);
        return this;
    }

    public U05_AdministrativeCertificateRequestPage FillCertificateDetails (String Details) {
        Utility.WaitUntilReady_And_SENDKEYS(driver,CertificateDetailsField,Details);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfCertificateDetails);
        return this;
    }

    // لو فيه نوع مرفق بيرفع عليه الملف ولو مفيش بيعدي
    public U05_AdministrativeCertificateRequestPage FillAttachmentSection () {
        Utility.UploadAttachments(driver,AddAttachmentButton);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfAttachments);
        return this;
    }

    public U05_AdministrativeCertificateRequestPage FillCertificateData (String Branch , String SubmitTo) {
        By BranchChoice = By.xpath("//div[@role='option'][normalize-space()='" + Branch + "']");
        Utility.WaitUntilReady_And_CLICK(driver,SendCertificateToBranchField);
        Utility.WaitUntilReady_And_CLICK(driver,BranchChoice);
        Utility.WaitUntilReady_And_SENDKEYS(driver,SubmitToField,SubmitTo);
        return this;
    }

    public U05_AdministrativeCertificateRequestPage SendTheRequestAndSaveRequestNumber () {
        Utility.WaitUntilReady_And_CLICK(driver,SendRequestButton);
        Utility.WaitUntilReady_And_CLICK(driver,ConfirmButtonOfPopUp);
        // نفس البوب اب بيتغير من رسالة التأكيد لرسالة "تم ارسال" فبنستنى الرسالة الجديدة
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.textToBePresentInElementLocated(PopUpMessage,"تم ارسال"));
        String Message = Utility.GETTEXT(driver,PopUpMessage);
        String requestNumber = Message.replaceAll("[^0-9]", "");
        Validations.ValidationTrue(!requestNumber.isEmpty(), "Request number not found in: " + Message);
        JsonUtil.writeValue("PortalAdministrativeCertificateRequestNumber", requestNumber);
        return this;
    }

}
