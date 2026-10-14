package Pages;

import Utilities.JsonUtil;
import Utilities.Utility;
import Utilities.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class P12_DivisionEditRequestPage {
    public P12_DivisionEditRequestPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;


    private final By RequestsIcon = By.xpath("//img[@src=\"../../assets/images/certificates-icon.svg\"]");
    private final By DivisionEditRequest_FSM = By.xpath("//a[contains(@href, '/requests/recordDivisionRequestList')]");
    private final By AddNewRequest_Button = By.xpath("//img[@src=\"../../../assets/images/circle-plus-white.svg\"]");
    private final By Loading_Circle = By.xpath("//mat-spinner[@mode=\"indeterminate\"]");
    private final By PersonalIdentificationNumber_Field = By.xpath("//input-control[@label=\"رقم تحقيق الشخصية\"]/input");
    private final By SaveApplicantData_Button = By.xpath("//button[contains(text(),'حفظ جميع بيانات مقدم الطلب')]");
    private final By SearchForRecord_Field = By.xpath("//input[@placeholder=\"ابحث برقم السجل  \"]");
    private final By FirstRowForRecordsResult = By.xpath("(//tr[@cdk-describedby-host=\"0\"]/td)[1]");
//    private final By CheckBoxOFLegalData = By.xpath("(//input[@type=\"checkbox\"])[1]");
//    private final By SendCertificateToOfficeField = By.xpath("//label[normalize-space(text())='تقديم الشهادة الى مكتب']/following-sibling::*[1]");

    private final By DepartmentName = By.xpath("//label[normalize-space(text())='اسم الشعبة']/following-sibling::*[1]");
    private final By AddDepartNameButton = By.xpath("//button[contains(text(), ' اضافة شعبة')]");

    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");
    private final By AddAttachmentButton = By.xpath("//button[@title=\"إضافة ملف جديد\"]");
    private final By ConfirmRequestButton = By.xpath("//span[contains(text(),\"تاكيد الطلب\")]");
    private final By AcceptRequestButtonOfPopUP = By.xpath("//button[contains(text(),\" اقبل الطلب\")]");
    private final By EditButton = By.xpath("(//li[.//*[local-name()='svg' and contains(@class,'edit-icon')]])[1]");
    private final By PullTheRequest = By.xpath("//label[contains(normalize-space(), 'اسحب الطلب')]//input[@type='checkbox']");
    private final By AcceptRequestButton = By.xpath("//span[contains(text(),\"أقبل الطلب\")]");
    private final By ViewButton = By.xpath("(//li[.//*[local-name()='svg' and contains(@class,'view-icon')]])[1]");
    private final By SendToCollectButton = By.xpath("//span[contains(normalize-space(), 'أرسال للتحصيل')]");
    private final By SendToCollectButtonPouUp = By.xpath("//button[contains(normalize-space(), 'ارسل الطلب للتحصيل')]");
//    private final By RecieveCertificateButton = By.xpath("//button[contains(text(), \"تم التسليم\")]");


    public P12_DivisionEditRequestPage CreateNewDivisionEditRequest_Button() throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, RequestsIcon, Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, DivisionEditRequest_FSM, Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, AddNewRequest_Button, Loading_Circle);
        return this;
    }

    public P12_DivisionEditRequestPage FillAndSaveDataOfApplicant(String ID) throws InterruptedException {
        Utility.WatingAndSENDKEYS(driver, PersonalIdentificationNumber_Field, Loading_Circle, ID);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, SaveApplicantData_Button, Loading_Circle);
        return this;
    }

    public P12_DivisionEditRequestPage ChoosingRecord (String RecordNumber){
        Utility.WatingAndSENDKEYS(driver,SearchForRecord_Field,Loading_Circle,RecordNumber);
        driver.findElement(SearchForRecord_Field).sendKeys(Keys.ENTER);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,FirstRowForRecordsResult,Loading_Circle);
        return this;
    }

    public P12_DivisionEditRequestPage FillDivisionDataSection() throws InterruptedException {

        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, DepartmentName, Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, Option1ForAnyList, Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, AddDepartNameButton, Loading_Circle);
        return this;
    }

    public P12_DivisionEditRequestPage FillAttachmentSection() {
        Utility.UploadAttachments(driver, AddAttachmentButton, Loading_Circle);
        return this;
    }

    public P12_DivisionEditRequestPage ConfirmRequest () throws InterruptedException {
        Utility.CLICKONELEMENTS(driver, ConfirmRequestButton);
        Utility.CLICKONELEMENTS(driver,AcceptRequestButtonOfPopUP);
        return this;
    }

    public P12_DivisionEditRequestPage ReviewRequest() throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, EditButton, Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, PullTheRequest, Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, AcceptRequestButton, Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, AcceptRequestButtonOfPopUP, Loading_Circle);
        return this;
    }

    public P04_InvoicesPage SendingRequestToCollect() {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, ViewButton, Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, PullTheRequest, Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, SendToCollectButton, Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, SendToCollectButtonPouUp, Loading_Circle);
        return new P04_InvoicesPage(driver);
    }


}
