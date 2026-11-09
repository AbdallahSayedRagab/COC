package Pages.BackofficePages;

import Utilities.BackofficeBasedFunctions;
import Utilities.Utility;
import Utilities.Validations;
import org.openqa.selenium.By;
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
    private final By AcceptRequestButtonOfPopUP = By.xpath("//button[contains(text(),\" اقبل الطلب\")]");
    private final By EditButton = By.xpath("(//li[.//*[local-name()='svg' and contains(@class,'edit-icon')]])[1]");
    private final By PullTheRequest = By.xpath("//label[contains(normalize-space(), 'اسحب الطلب')]//input[@type='checkbox']");
    private final By AcceptRequestButton = By.xpath("//span[contains(text(),\"أقبل الطلب\")]");
    private final By AcceptButtonOfWarningPopUp = By.xpath("//button[@class=\"swal2-confirm swal2-styled\"]");


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

    public P06_RemoveRecordPage FillAndSaveDataOfApplicant (String ID , String Phone) throws InterruptedException {
        BackofficeBasedFunctions.FillAndSaveDataOfApplicant(driver,ID,Phone);
        return this;
    }

    public P06_RemoveRecordPage ChoosingRecord (String RecordNumber){
        BackofficeBasedFunctions.ChoosingRecord(driver,RecordNumber);
        return this;
    }

//    public P06_RemoveRecordPage FillReasonToRemoveSection (){
//        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,);
//        return this;
//    }

    public P06_RemoveRecordPage FillCertificateData () throws InterruptedException {
        BackofficeBasedFunctions.FillCertificateData(driver);
        return this;
    }

    public P06_RemoveRecordPage FillAttachmentSection() {
        BackofficeBasedFunctions.FillAttachmentSection(driver);
        return this;
    }

    public P06_RemoveRecordPage ConfirmRequest() throws InterruptedException {
        BackofficeBasedFunctions.ConfirmRequest(driver);
        return this;
    }

    public P06_RemoveRecordPage SaveRequestNumberInJsonFile() {
        BackofficeBasedFunctions.SaveRequestNumberInJsonFile(driver,"RemoveRequestNumber");
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
        BackofficeBasedFunctions.SendingRequestToCollect(driver);
        return new P04_InvoicesPage(driver);
    }

    public P06_RemoveRecordPage ReceivingTheCertificate () throws InterruptedException {
        BackofficeBasedFunctions.ReceivingTheCertificate(driver);
        return this;
    }




}