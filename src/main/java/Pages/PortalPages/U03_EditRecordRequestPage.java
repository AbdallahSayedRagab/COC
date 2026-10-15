package Pages.PortalPages;

import Utilities.JsonUtil;
import Utilities.Utility;
import Utilities.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class U03_EditRecordRequestPage {
    public U03_EditRecordRequestPage(WebDriver driver) { this.driver = driver;}
    private WebDriver driver;

    private final By Loading_Circle = By.xpath("//div[contains(@class,'ngx-spinner-overlay')]");
    private final By AcceptConditionsCheckBox = By.xpath("//input[@formcontrolname=\"conditions\"]");
    private final By StartServiceButton = By.xpath("//button[contains(text(),' إبدا الخدمة الآن ')]");
    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");
    private final By ConfirmButtonOfPopUp = By.xpath("//button[contains(@class,'swal2-confirm')]");
    private final By PopUpMessage = By.xpath("//div[@id=\"swal2-html-container\"]");

    // 1- بيانات السجل (الجدول بيعرض سجلات الحساب لوحده)
    private final By FirstRecordInSearchResults = By.xpath("(//div[@id='test-l-1']//table/tbody/tr)[1]");
    private final By NextButtonOfRecordData = By.xpath("//div[@id='test-l-1']//button[contains(text(),'التالى')]");

    // 2- التعديلات المطلوبة
    private final By LegalDataChangeCheckBox = By.xpath("//input[@formcontrolname='isLegalInfoChange']");
    private final By NextButtonOfRequiredChanges = By.xpath("//div[@id='test-l-2']//button[contains(text(),'التالى')]");

    // 3- بيانات الشهادة
    private final By SendCertificateToBranchField = By.xpath("//label[normalize-space(text())='تقديم الشهادة الى فرع']/following-sibling::*[1]");
    private final By SendCertificateToGovernorateField = By.xpath("//label[normalize-space(text())='تقديم الشهادة الى محافظة']/following-sibling::*[1]");
    private final By CommercialRegistryOfficeField = By.xpath("//div[@id='test-l-3']//label[normalize-space(text())='مكتب سجل تجاري']/following-sibling::*[1]");
    private final By SaveButton = By.xpath("//div[@id='test-l-3']//button[contains(text(),'حفظ')]");

    // 4- المرفقات (البطاقة الشخصية اجباري)
    private final By AddAttachmentButton = By.xpath("//div[@id='test-l-4']//button[@title=\"إضافة ملف جديد\"]");
    private final By SendRequestButton = By.xpath("//div[@id='test-l-4']//button[contains(text(),'ارسال الطلب')]");

    public U03_EditRecordRequestPage StartTheService () {
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.visibilityOfElementLocated(AcceptConditionsCheckBox));
        if (!driver.findElement(AcceptConditionsCheckBox).isSelected()) {
            Utility.WaitUntilReady_And_CLICK(driver,AcceptConditionsCheckBox,Loading_Circle);
        }
        Utility.WaitUntilReady_And_CLICK(driver,StartServiceButton,Loading_Circle);
        return this;
    }

    public U03_EditRecordRequestPage SelectFirstRecord () {
        Utility.WaitUntilReady_And_CLICK(driver,FirstRecordInSearchResults,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfRecordData,Loading_Circle);
        return this;
    }

    public U03_EditRecordRequestPage SelectLegalDataToEdit () {
        Utility.WaitUntilReady_And_CLICK(driver,LegalDataChangeCheckBox,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfRequiredChanges,Loading_Circle);
        return this;
    }

    public U03_EditRecordRequestPage FillCertificateData (String Branch , String Governorate) {
        // اول محافظة في الليستة مفيهاش مكاتب سجل فبنختار المحافظة بالاسم
        By BranchChoice = By.xpath("//div[@role='option'][normalize-space()='" + Branch + "']");
        By GovernorateChoice = By.xpath("//div[@role='option'][normalize-space()='" + Governorate + "']");
        Utility.WaitUntilReady_And_CLICK(driver,SendCertificateToBranchField,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,BranchChoice,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,SendCertificateToGovernorateField,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,GovernorateChoice,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,CommercialRegistryOfficeField,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,SaveButton,Loading_Circle);
        return this;
    }

    public U03_EditRecordRequestPage FillAttachmentSection () {
        Utility.UploadAttachments(driver,AddAttachmentButton,Loading_Circle);
        return this;
    }

    public U03_EditRecordRequestPage SendTheRequestAndSaveRequestNumber () {
        Utility.WaitUntilReady_And_CLICK(driver,SendRequestButton,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,ConfirmButtonOfPopUp,Loading_Circle);
        // نفس البوب اب بيتغير من رسالة التأكيد لرسالة "تم ارسال" فبنستنى الرسالة الجديدة
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.textToBePresentInElementLocated(PopUpMessage,"تم ارسال"));
        String Message = Utility.GETTEXT(driver,PopUpMessage,Loading_Circle);
        String requestNumber = Message.replaceAll("[^0-9]", "");
        Validations.ValidationTrue(!requestNumber.isEmpty(), "Request number not found in: " + Message);
        JsonUtil.writeValue("PortalEditRecordRequestNumber", requestNumber);
        return this;
    }

}
