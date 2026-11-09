package Pages.PortalPages;

import Utilities.JsonUtil;
import Utilities.PortalBasedFunctions;
import Utilities.Utility;
import Utilities.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class U02_BusinessLicenseRequestPage {
    public U02_BusinessLicenseRequestPage(WebDriver driver) { this.driver = driver;}
    private WebDriver driver;

    private final By FacilityNature_Choice = By.xpath("//label[contains(text(), ' مركز رئيسى')]");
    private final By CreateRequest_Button = By.xpath("//button[contains(text(), ' انشاء الطلب ')]");
    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");
    private final By FirstAvailableDay = By.xpath("(//table[@class='days weeks']//td[@role='gridcell']/span[not(contains(@class,'disabled')) and not(contains(@class,'is-other-month'))])[1]");
    private final By ConfirmButtonOfPopUp = By.xpath("//button[contains(@class,'swal2-confirm')]");
    private final By PopUpMessage = By.xpath("//div[@id=\"swal2-html-container\"]");

    // 1- البيانات القانونية
    private final By FacilityClassificationField = By.xpath("//label[normalize-space(text())='تصنيف المنشاة']/following-sibling::*[1]");
    private final By FacilityTypeField = By.xpath("//label[normalize-space(text())='نوع المنشأة']/following-sibling::*[1]");
    private final By TradeNameField = By.xpath("//label[normalize-space(text())='الاسم التجاري']/following-sibling::*[1]");
    private final By NextButtonOfLegalData = By.xpath("//div[@id='test-l-1']//button[contains(text(),'التالى')]");

    // 2- الإدارة (بيانات الشريك بتيجي جاهزة من الحساب)
    private final By AddPartnerButton = By.xpath("//span[normalize-space(text())='إضافة شريك']");
    private final By NextButtonOfManagement = By.xpath("//div[@id='test-l-2']//button[contains(text(),'التالى')]");

    // 3- بيانات النشاط
    private final By TaxCardNumberField = By.xpath("//label[normalize-space(text())='رقم التسجيل الضريبى']/following-sibling::*[1]");
    private final By CompanyEstablishmentDateField = By.xpath("//label[normalize-space(text())='تاريخ تاسيس الشركه']/following-sibling::*[1]");
    private final By ActivityDescriptionField = By.xpath("//label[normalize-space(text())='النشاط طبقا لوثيقة البطاقة الضريبية']/following-sibling::*[1]");
    private final By MainActivityField = By.xpath("//label[normalize-space(text())='النشاط الرئيسي']/following-sibling::*[1]");
    private final By SubActivityField = By.xpath("//label[normalize-space(text())='النشاط الفرعي']/following-sibling::*[1]");
    private final By ActivityField = By.xpath("//label[normalize-space(text())='النشاط']/following-sibling::*[1]");
    private final By ActivityStartDateField = By.xpath("//label[normalize-space(text())='تاريخ بدء المزاولة']/following-sibling::*[1]");
    private final By AddActivityButton = By.xpath("//button[contains(text(),'اضافة نشاط')]");
    private final By DivisionNameField = By.xpath("//label[normalize-space(text())='اسم الشعبة']/following-sibling::*[1]");
    private final By DivisionAddingDateField = By.xpath("//label[normalize-space(text())='تاريخ الاضافة للشعبة']/following-sibling::*[1]");
    private final By AddDivisionButton = By.xpath("//button[contains(text(),'اضافة شعبة')]");
    private final By NextButtonOfActivities = By.xpath("//div[@id='test-l-3']//button[contains(text(),'التالى')]");

    // 4- بيانات راس المال
    private final By CapitalField = By.xpath("//label[normalize-space(text())='رأس المال المسدد(ج.م)']/following-sibling::*[1]");
    private final By NextButtonOfCapital = By.xpath("//div[@id='test-l-4']//button[contains(text(),'التالى')]");

    // 5- بيانات محل النشاط
    private final By FirstAdministrativeField = By.xpath("//label[normalize-space(text())='التقسيم الاداري الاول']/following-sibling::*[1]");
    private final By SecondAdministrativeField = By.xpath("//label[normalize-space(text())='التقسيم الاداري الثانى']/following-sibling::*[1]");
    private final By StreetNameField = By.xpath("//label[normalize-space(text())='اسم الشارع / العنوان بالكامل']/following-sibling::*[1]");
    private final By NextButtonOfBusinessLocation = By.xpath("//div[@id='test-l-5']//button[contains(text(),'التالى')]");

    // 6- الوثائق (مش اجباري)
    private final By NextButtonOfDocuments = By.xpath("//div[@id='test-l-6']//button[contains(text(),'التالى')]");

    // 7- بيانات الشهادة
    private final By SendCertificateToBranchField = By.xpath("//label[normalize-space(text())='تقديم الشهادة الى فرع']/following-sibling::*[1]");
    private final By SendCertificateToGovernorateField = By.xpath("//label[normalize-space(text())='تقديم الشهادة الى محافظة']/following-sibling::*[1]");
    private final By CommercialRegistryOfficeField = By.xpath("//label[normalize-space(text())='مكتب سجل تجاري']/following-sibling::*[1]");
    private final By SaveAndContinueButton = By.xpath("//button[contains(text(),'حفظ ومتابعة')]");

    // 8- المرفقات
    private final By SendRequestButton = By.xpath("//div[@id='test-l-8']//button[contains(text(),'ارسال الطلب')]");

    public U02_BusinessLicenseRequestPage StartTheService () {
        // بعد ابدأ الخدمة بيظهر اختيار طبيعة المنشأة
        PortalBasedFunctions.StartTheService(driver,FacilityNature_Choice);
        Utility.WaitUntilReady_And_CLICK(driver,FacilityNature_Choice);
        Utility.WaitUntilReady_And_CLICK(driver,CreateRequest_Button);
        return this;
    }

    public U02_BusinessLicenseRequestPage FillLegalDataSection (String TradeName) {
        Utility.WaitUntilReady_And_CLICK(driver,FacilityClassificationField);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        Utility.WaitUntilReady_And_CLICK(driver,FacilityTypeField);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        // الفورم بيعيد تحميل نفسه بعد اختيار نوع المنشأة وبيمسح الاسم التجاري لو اتكتب بدري
        Utility.WaitUntilReady_And_SENDKEYS_AndCheckValue(driver,TradeNameField,TradeName);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfLegalData);
        return this;
    }

    public U02_BusinessLicenseRequestPage AddPartnerFromAccountData () {
        Utility.WaitUntilReady_And_CLICK(driver,AddPartnerButton);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfManagement);
        return this;
    }

    public U02_BusinessLicenseRequestPage FillActivitiesDataSection (String TaxCardNum , String Description) {
        Utility.WaitUntilReady_And_SENDKEYS(driver,TaxCardNumberField,TaxCardNum);
        Utility.WaitUntilReady_And_CLICK(driver,CompanyEstablishmentDateField);
        Utility.WaitUntilReady_And_CLICK(driver,FirstAvailableDay);
        Utility.WaitUntilReady_And_SENDKEYS(driver,ActivityDescriptionField,Description);
        Utility.WaitUntilReady_And_CLICK(driver,MainActivityField);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        Utility.WaitUntilReady_And_CLICK(driver,SubActivityField);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        Utility.WaitUntilReady_And_CLICK(driver,ActivityField);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        Utility.WaitUntilReady_And_CLICK(driver,ActivityStartDateField);
        Utility.WaitUntilReady_And_CLICK(driver,FirstAvailableDay);
        Utility.WaitUntilReady_And_CLICK(driver,AddActivityButton);
        Utility.WaitUntilReady_And_CLICK(driver,DivisionNameField);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        Utility.WaitUntilReady_And_CLICK(driver,DivisionAddingDateField);
        Utility.WaitUntilReady_And_CLICK(driver,FirstAvailableDay);
        Utility.WaitUntilReady_And_CLICK(driver,AddDivisionButton);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfActivities);
        return this;
    }

    public U02_BusinessLicenseRequestPage FillCapitalDataSection (String Capital) {
        // الحقل بيبقى فيه 0 جاهز فلازم يتمسح الاول
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.visibilityOfElementLocated(CapitalField));
        driver.findElement(CapitalField).clear();
        Utility.WaitUntilReady_And_SENDKEYS(driver,CapitalField,Capital);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfCapital);
        return this;
    }

    public U02_BusinessLicenseRequestPage FillBusinessLocationSection (String NameOfSt) {
        Utility.WaitUntilReady_And_CLICK(driver,FirstAdministrativeField);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        Utility.WaitUntilReady_And_CLICK(driver,SecondAdministrativeField);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        Utility.WaitUntilReady_And_SENDKEYS(driver,StreetNameField,NameOfSt);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfBusinessLocation);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfDocuments);
        return this;
    }

    public U02_BusinessLicenseRequestPage FillCertificateDataAndSaveRequestNumber (String Branch , String Governorate) {
        // اول محافظة في الليستة مفيهاش مكاتب سجل فبنختار المحافظة بالاسم
        By BranchChoice = By.xpath("//div[@role='option'][normalize-space()='" + Branch + "']");
        By GovernorateChoice = By.xpath("//div[@role='option'][normalize-space()='" + Governorate + "']");
        Utility.WaitUntilReady_And_CLICK(driver,SendCertificateToBranchField);
        Utility.WaitUntilReady_And_CLICK(driver,BranchChoice);
        Utility.WaitUntilReady_And_CLICK(driver,SendCertificateToGovernorateField);
        Utility.WaitUntilReady_And_CLICK(driver,GovernorateChoice);
        Utility.WaitUntilReady_And_CLICK(driver,CommercialRegistryOfficeField);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        Utility.WaitUntilReady_And_CLICK(driver,SaveAndContinueButton);
        String requestNumber = Utility.GETTEXT(driver,PopUpMessage).replaceAll("[^0-9]", "");
        JsonUtil.writeValue("PortalBusinessLicenseRequestNumber", requestNumber);
        Utility.WaitUntilReady_And_CLICK(driver,ConfirmButtonOfPopUp);
        return this;
    }

    // لو فيه نوع مرفق بيرفع عليه الملف ولو مفيش بيعدي
    public U02_BusinessLicenseRequestPage FillAttachmentSection () {
        PortalBasedFunctions.FillAttachmentSection(driver,8);
        return this;
    }

    public U02_BusinessLicenseRequestPage SendTheRequestAndValidateIt (String RequestNumber) {
        Utility.WaitUntilReady_And_CLICK(driver,SendRequestButton);
        Utility.WaitUntilReady_And_CLICK(driver,ConfirmButtonOfPopUp);
        // نفس البوب اب بيتغير من رسالة التأكيد لرسالة "تم ارسال" فبنستنى الرسالة الجديدة
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.textToBePresentInElementLocated(PopUpMessage,"تم ارسال"));
        String Message = Utility.GETTEXT(driver,PopUpMessage);
        Validations.ValidationTrue(Message.contains("تم ارسال") && Message.contains(RequestNumber), "Request was not sent: " + Message);
        return this;
    }

}
