package Pages.BackofficePages;

import Utilities.JsonUtil;
import Utilities.Utility;
import Utilities.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;

public class P06_RemoveRecordPage {

    private static final Logger log = LoggerFactory.getLogger(P05_EditRequestPage.class);



    public  P06_RemoveRecordPage (WebDriver driver) {this.driver = driver;}

    private WebDriver driver;

    private final By RequestsIcon = By.xpath("//img[@src=\"../../assets/images/certificates-icon.svg\"]");
    private final By RemoveRequest_FSM = By.xpath("//a[contains(@href, '/requests/removeCommercialList')]");
    private final By AddNewRequest_Button = By.xpath("//img[@src=\"../../../assets/images/circle-plus-white.svg\"]");
    private final By PersonalIdentificationNumber_Field = By.xpath("//input-control[@label=\"رقم تحقيق الشخصية\"]/input");
    private final By SaveApplicantData_Button = By.xpath("//button[contains(text(),'حفظ جميع بيانات مقدم الطلب')]");
    private final By SearchForRecord_Field = By.xpath("//input[@placeholder=\"ابحث برقم السجل  \"]");
    private final By FirstRowForRecordsResult = By.xpath("(//tr[@cdk-describedby-host=\"0\"]/td)[1]");
    private final By SendCertificateToOfficeField = By.xpath("//label[normalize-space(text())='تقديم الشهادة الى مكتب']/following-sibling::*[1]");
    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");
    private final By AddAttachmentButton = By.xpath("//button[@title=\"إضافة ملف جديد\"]");
    private final By ConfirmRequestButton = By.xpath("//span[contains(text(),\"تاكيد الطلب\")]");
    private final By AcceptRequestButtonOfPopUP = By.xpath("//button[contains(text(),\" اقبل الطلب\")]");
    private final By EditButton = By.xpath("(//li[.//*[local-name()='svg' and contains(@class,'edit-icon')]])[1]");
    private final By PullTheRequest = By.xpath("//label[contains(normalize-space(), 'اسحب الطلب')]//input[@type='checkbox']");
    private final By AcceptRequestButton = By.xpath("//span[contains(text(),\"أقبل الطلب\")]");
    private final By AcceptButtonOfWarningPopUp = By.xpath("//button[@class=\"swal2-confirm swal2-styled\"]");
    private final By RequestNumber = By.xpath("(//td/a[@class=\"ng-star-inserted\"])[1]");
    private final By ViewButton = By.xpath("(//li[.//*[local-name()='svg' and contains(@class,'view-icon')]])[1]");
    private final By SendToCollectButton = By.xpath("//span[contains(normalize-space(), 'أرسال للتحصيل')]");
    private final By SendToCollectButtonPouUp = By.xpath("//button[contains(normalize-space(), 'ارسل الطلب للتحصيل')]");
    private final By RecieveCertificateButton = By.xpath("//button[contains(text(), \"تسليم الشهادة\")]");


    public P06_RemoveRecordPage CreateNewRemoveRequest_Button () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RemoveRequest_FSM);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddNewRequest_Button);
        return this;
    }
    public P06_RemoveRecordPage Validate_EditRequestURL () throws InterruptedException {
        Validations.ValidateURL("https://coc-backoffice-fix.apps.softprograms-eg.com/requests/issueCommerciallist");
        return this;
    }

    public P06_RemoveRecordPage FillAndSaveDataOfApplicant (String ID) throws InterruptedException {
        Utility.WatingAndSENDKEYS(driver,PersonalIdentificationNumber_Field,ID);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SaveApplicantData_Button);
        return this;
    }

    public P06_RemoveRecordPage ChoosingRecord (String RecordNumber){
        Utility.WatingAndSENDKEYS(driver,SearchForRecord_Field,RecordNumber);
        driver.findElement(SearchForRecord_Field).sendKeys(Keys.ENTER);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,FirstRowForRecordsResult);
        return this;
    }

//    public P06_RemoveRecordPage FillReasonToRemoveSection (){
//        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,);
//        return this;
//    }

    public P06_RemoveRecordPage FillCertificateData () throws InterruptedException {
        Utility.CLICKONELEMENTS(driver,SendCertificateToOfficeField);
        Utility.CLICKONELEMENTS(driver,Option1ForAnyList);
        return this;
    }

    public P06_RemoveRecordPage FillAttachmentSection() {
        Utility.UploadAttachments(driver, AddAttachmentButton);
        return this;
    }

    public P06_RemoveRecordPage ConfirmRequest() throws InterruptedException {
        Utility.CLICKONELEMENTS(driver, ConfirmRequestButton);
        Utility.CLICKONELEMENTS(driver,AcceptRequestButtonOfPopUP);
        return this;
    }

    public P06_RemoveRecordPage SaveRequestNumberInJsonFile() {

        String requestNumber = Utility.GETTEXT(driver,RequestNumber);
        JsonUtil.writeValue("RemoveRequestNumber", requestNumber);
        return this;
    }

    public P06_RemoveRecordPage ReviewRequest () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,EditButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AcceptRequestButton);
        try {
            if (AcceptButtonOfWarningPopUp.findElement(driver).isDisplayed())
                Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, AcceptButtonOfWarningPopUp);
        }
        catch (Exception e){}
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AcceptRequestButtonOfPopUP);
        return this;
    }

    public P04_InvoicesPage SendingRequestToCollect(){
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ViewButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SendToCollectButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SendToCollectButtonPouUp);
        return new P04_InvoicesPage(driver);
    }

    public P06_RemoveRecordPage ReceivingTheCertificate () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ViewButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RecieveCertificateButton);
        return this;
    }




}