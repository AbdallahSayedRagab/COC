package Pages.BackofficePages;

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
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, RequestsIcon);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, DivisionEditRequest_FSM);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, AddNewRequest_Button);
        return this;
    }

    public P12_DivisionEditRequestPage FillAndSaveDataOfApplicant(String ID , String Phone) throws InterruptedException {
        Utility.FillAndSaveDataOfApplicant(driver, ID, Phone);
        return this;
    }

    public P12_DivisionEditRequestPage ChoosingRecord (String RecordNumber){
        Utility.WatingAndSENDKEYS(driver,SearchForRecord_Field,RecordNumber);
        driver.findElement(SearchForRecord_Field).sendKeys(Keys.ENTER);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,FirstRowForRecordsResult);
        return this;
    }

    public P12_DivisionEditRequestPage FillDivisionDataSection() throws InterruptedException {

        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, DepartmentName);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, Option1ForAnyList);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, AddDepartNameButton);
        return this;
    }

    public P12_DivisionEditRequestPage FillAttachmentSection() {
        Utility.UploadAttachments(driver, AddAttachmentButton);
        return this;
    }

    public P12_DivisionEditRequestPage ConfirmRequest () throws InterruptedException {
        Utility.CLICKONELEMENTS(driver, ConfirmRequestButton);
        Utility.CLICKONELEMENTS(driver,AcceptRequestButtonOfPopUP);
        return this;
    }

    public P12_DivisionEditRequestPage ReviewRequest() throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, EditButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, PullTheRequest);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, AcceptRequestButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, AcceptRequestButtonOfPopUP);
        return this;
    }

    public P04_InvoicesPage SendingRequestToCollect() {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, ViewButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, PullTheRequest);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, SendToCollectButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, SendToCollectButtonPouUp);
        return new P04_InvoicesPage(driver);
    }


}
