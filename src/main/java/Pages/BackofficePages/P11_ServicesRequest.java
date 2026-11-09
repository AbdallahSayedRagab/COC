package Pages.BackofficePages;

import Utilities.BackofficeBasedFunctions;
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
    private final By ServicesList = By.xpath("//ng-select[@id=\"elctCertificateTypeId\"]");
    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");
    private final By AddServiceButton = By.xpath("//button[@class=\"add-btn\"]");

//    private final By RecieveCertificateButton = By.xpath("//button[contains(text(), \"تم التسليم\")]");
//    private final By RequestNumber = By.xpath("(//td/a[@class=\"ng-star-inserted\"])[1]");


    public P11_ServicesRequest CreateNewServicesRequest () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ServicesRequest_FSM);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddNewRequest_Button);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,WithoutRecordRequest_Button);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,CreateRequestButton);
        return this;
    }
    public P11_ServicesRequest Validate_EditRequestURL () throws InterruptedException {
    Validations.ValidateURL("https://coc-backoffice-fix.apps.softprograms-eg.com/requests/issueCommerciallist");
    return this;
}

    public P11_ServicesRequest FillAndSaveDataOfApplicant (String ID , String Phone) throws InterruptedException {
    BackofficeBasedFunctions.FillAndSaveDataOfApplicant(driver,ID,Phone);
    return this;
}

    public P11_ServicesRequest SelectServicesData () throws InterruptedException {
    Utility.CLICKONELEMENTS(driver,ServicesList);
    Utility.CLICKONELEMENTS(driver,Option1ForAnyList);
    Utility.CLICKONELEMENTS(driver,AddServiceButton);
    return this;
}

    public P11_ServicesRequest FillAttachmentSection() {
        BackofficeBasedFunctions.FillAttachmentSection(driver);
        return this;
    }
    public P11_ServicesRequest ConfirmRequest () throws InterruptedException {
        BackofficeBasedFunctions.ConfirmRequest(driver);
        return this;
    }

//    public P11_ServicesRequest ReviewRequest () throws InterruptedException {
//        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,EditButton);
////        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest);
//        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AcceptRequestButton);
//        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, AcceptRequestButtonOfPopUP);
//
//    return this;
//}

    public P04_InvoicesPage SendingRequestToCollect(){
        BackofficeBasedFunctions.SendingRequestToCollect(driver);
        return new P04_InvoicesPage(driver);
    }


//    public P11_ServicesRequest ReceivingTheCertificate () throws InterruptedException {
//        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ViewButton);
//        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest);
//        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RecieveCertificateButton);
//        return this;
//    }


    }
