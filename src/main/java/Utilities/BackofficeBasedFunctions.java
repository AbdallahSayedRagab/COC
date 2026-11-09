package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

// الخطوات المشتركة بين صفحات طلبات الباك أوفيس
// كل ميثود هنا كانت مكتوبة بنفس الكود ونفس اللوكيترز بالظبط في كذا صفحة، فبقت مكتوبة مرة واحدة
// والصفحات بتناديها بنفس اسم الميثود اللي عندها، فالتستات مش بتتغير
public class BackofficeBasedFunctions {

    private BackofficeBasedFunctions() {
    }

    // بيانات مقدم الطلب (P03 - P05 - P06 - P10 - P11 - P12)
    private static final By PersonalIdentificationNumber_Field = By.xpath("//input-control[@label=\"رقم تحقيق الشخصية\"]/input");
    private static final By ApplicantPhone_Field = By.xpath("//input-control[@label=\"رقم الهاتف\"]/input");
    private static final By SaveApplicantData_Button = By.xpath("//button[contains(text(),'حفظ جميع بيانات مقدم الطلب')]");

    // زرار اضافة طلب جديد (P03 - p07 - p08 - P09)
    private static final By AddNewRequest_Button = By.xpath("//img[@src=\"../../../assets/images/circle-plus-white.svg\"]");

    // اختيار السجل من البحث برقم السجل (P05 - P06 - P12)
    private static final By SearchForRecord_Field = By.xpath("//input[@placeholder=\"ابحث برقم السجل  \"]");
    private static final By FirstRowForRecordsResult = By.xpath("(//tr[@cdk-describedby-host=\"0\"]/td)[1]");

    // الرقم القومي والبحث برقم السجل (p07 - p08 - P09)
    private static final By Personalidentificationnumber = By.xpath("//input-control[@formcontrolname='applicantNationalId']//input");
    private static final By saveamedicalprovider = By.xpath("//button[@type='submit']");
    private static final By SearchingOfRecordNumField = By.xpath("(//input[@name=\"recordNumber\"])[1]");

    // بيانات الشهادة: تقديم الشهادة الى مكتب (P03 - P05 - P06)
    private static final By SendCertificateToOfficeField = By.xpath("//label[normalize-space(text())='تقديم الشهادة الى مكتب']/following-sibling::*[1]");
    private static final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");

    // المرفقات
    private static final By AddAttachmentButton = By.xpath("//button[@title=\"إضافة ملف جديد\"]");

    // تأكيد الطلب ومراجعته
    private static final By ConfirmRequestButton = By.xpath("//span[contains(text(),\"تاكيد الطلب\")]");
    private static final By AcceptRequestButtonOfPopUP = By.xpath("//button[contains(text(),\" اقبل الطلب\")]");
    private static final By EditButton = By.xpath("(//li[.//*[local-name()='svg' and contains(@class,'edit-icon')]])[1]");
    private static final By PullTheRequest = By.xpath("//label[contains(normalize-space(), 'اسحب الطلب')]//input[@type='checkbox']");
    private static final By AcceptRequestButton = By.xpath("//span[contains(text(),\"أقبل الطلب\")]");

    // الارسال للتحصيل وتسليم الشهادة
    private static final By ViewButton = By.xpath("(//li[.//*[local-name()='svg' and contains(@class,'view-icon')]])[1]");
    private static final By SendToCollectButton = By.xpath("//span[contains(normalize-space(), 'أرسال للتحصيل')]");
    private static final By SendToCollectButtonPouUp = By.xpath("//button[contains(normalize-space(), 'ارسل الطلب للتحصيل')]");
    private static final By RecieveCertificateButton = By.xpath("//button[contains(text(), \"تسليم الشهادة\")]");

    // رقم اول طلب في القائمة
    private static final By RequestNumber = By.xpath("(//td/a[@class=\"ng-star-inserted\"])[1]");


    public static void FillAndSaveDataOfApplicant(WebDriver driver, String ID, String Phone) {
        // الـ Tab بيخرج من الحقل فالموقع يدور على الرقم القومي ويظهر حقل رقم الهاتف
        Utility.WatingAndSENDKEYS(driver,PersonalIdentificationNumber_Field,ID + Keys.TAB);
        Utility.ClearAndSENDKEYS(driver,ApplicantPhone_Field,Phone);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SaveApplicantData_Button);
    }

    public static void ClickOnAddNewRequest_Button(WebDriver driver) {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddNewRequest_Button);
    }

    public static void ChoosingRecord(WebDriver driver, String RecordNumber) {
        Utility.WatingAndSENDKEYS(driver,SearchForRecord_Field,RecordNumber);
        driver.findElement(SearchForRecord_Field).sendKeys(Keys.ENTER);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,FirstRowForRecordsResult);
    }

    public static void EnterThePersonalIdentificationNumber(WebDriver driver, String personalidentifier) {
        Utility.SENDKEYS(driver,Personalidentificationnumber,personalidentifier);
    }

    public static void ClickOnSavedMedicalProvider(WebDriver driver) {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,saveamedicalprovider);
    }

    public static void SearchOnRecordNumber(WebDriver driver, String recordnumber) {
        Utility.WatingAndSENDKEYS(driver,SearchingOfRecordNumField,recordnumber);
        driver.findElement(SearchingOfRecordNumField).sendKeys(Keys.ENTER);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,FirstRowForRecordsResult);
    }

    public static void FillCertificateData(WebDriver driver) throws InterruptedException {
        Utility.CLICKONELEMENTS(driver,SendCertificateToOfficeField);
        Utility.CLICKONELEMENTS(driver,Option1ForAnyList);
    }

    public static void FillAttachmentSection(WebDriver driver) {
        Utility.UploadAttachments(driver, AddAttachmentButton);
    }

    public static void ConfirmRequest(WebDriver driver) throws InterruptedException {
        Utility.CLICKONELEMENTS(driver, ConfirmRequestButton);
        Utility.CLICKONELEMENTS(driver,AcceptRequestButtonOfPopUP);
    }

    // بيقرا رقم اول طلب في القائمة ويحفظه في الجيسون في المفتاح ده، وبيرجعه لو الصفحة محتاجة تحفظه في مفتاح تاني
    public static String SaveRequestNumberInJsonFile(WebDriver driver, String JsonKey) {
        String requestNumber = Utility.GETTEXT(driver,RequestNumber);
        JsonUtil.writeValue(JsonKey, requestNumber);
        return requestNumber;
    }

    public static void ReviewRequest(WebDriver driver) {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,EditButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AcceptRequestButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AcceptRequestButtonOfPopUP);
    }

    public static void SendingRequestToCollect(WebDriver driver) {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ViewButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SendToCollectButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SendToCollectButtonPouUp);
    }

    public static void ReceivingTheCertificate(WebDriver driver) {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ViewButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RecieveCertificateButton);
    }
}
