package Pages.BackofficePages;

import Utilities.Utility;
import Utilities.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P11_ServicesRequest {
    public P11_ServicesRequest (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By RequestsIcon = By.xpath("//img[@src=\"../../assets/images/certificates-icon.svg\"]");
    private final By ServicesRequest_FSM = By.xpath("//a[contains(@href, '/requests/unregisteredRequestList')]");
    private final By AddNewRequest_Button = By.xpath("//img[@src=\"../../../assets/images/circle-plus-white.svg\"]");
    private final By WithoutRecordRequest_Button = By.id("MainBranch");
    private final By CreateRequestButton = By.xpath("//button[contains(text(),\" انشاء الطلب \")]");
    private final By Loading_Circle = By.xpath("//mat-spinner[@mode=\"indeterminate\"]");
    private final By PersonalIdentificationNumber_Field = By.xpath("//input-control[@label=\"رقم تحقيق الشخصية\"]/input");
    private final By SaveApplicantData_Button = By.xpath("//button[contains(text(),'حفظ جميع بيانات مقدم الطلب')]");
    private final By ServicesList = By.xpath("//ng-select[@id=\"elctCertificateTypeId\"]");
    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");
    private final By AddServiceButton = By.xpath("//button[@class=\"add-btn\"]");

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
//    private final By RequestNumber = By.xpath("(//td/a[@class=\"ng-star-inserted\"])[1]");


    public P11_ServicesRequest CreateNewServicesRequest () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ServicesRequest_FSM,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddNewRequest_Button,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,WithoutRecordRequest_Button,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,CreateRequestButton,Loading_Circle);
        return this;
    }
    public P11_ServicesRequest Validate_EditRequestURL () throws InterruptedException {
    Validations.ValidateURL("https://coc-backoffice-fix.apps.softprograms-eg.com/requests/issueCommerciallist");
    return this;
}

    public P11_ServicesRequest FillAndSaveDataOfApplicant (String ID) throws InterruptedException {
    Utility.WatingAndSENDKEYS(driver,PersonalIdentificationNumber_Field,Loading_Circle,ID);
    Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SaveApplicantData_Button,Loading_Circle);
    return this;
}

    public P11_ServicesRequest SelectServicesData () throws InterruptedException {
    Utility.CLICKONELEMENTS(driver,ServicesList);
    Utility.CLICKONELEMENTS(driver,Option1ForAnyList);
    Utility.CLICKONELEMENTS(driver,AddServiceButton);
    return this;
}

    public P11_ServicesRequest FillAttachmentSection() {
        Utility.UploadAttachments(driver, AddAttachmentButton, Loading_Circle);
        return this;
    }
    public P11_ServicesRequest ConfirmRequest () throws InterruptedException {
        Utility.CLICKONELEMENTS(driver, ConfirmRequestButton);
        Utility.CLICKONELEMENTS(driver,AcceptRequestButtonOfPopUP);
        return this;
    }

//    public P11_ServicesRequest ReviewRequest () throws InterruptedException {
//        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,EditButton,Loading_Circle);
////        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest,Loading_Circle);
//        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AcceptRequestButton,Loading_Circle);
//        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, AcceptRequestButtonOfPopUP, Loading_Circle);
//
//    return this;
//}

    public P04_InvoicesPage SendingRequestToCollect(){
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ViewButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SendToCollectButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SendToCollectButtonPouUp,Loading_Circle);
        return new P04_InvoicesPage(driver);
    }


//    public P11_ServicesRequest ReceivingTheCertificate () throws InterruptedException {
//        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ViewButton,Loading_Circle);
//        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest,Loading_Circle);
//        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RecieveCertificateButton,Loading_Circle);
//        return this;
//    }


    }
