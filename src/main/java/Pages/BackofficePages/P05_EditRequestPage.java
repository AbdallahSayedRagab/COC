package Pages.BackofficePages;

import Utilities.BackofficeBasedFunctions;
import Utilities.Utility;
import Utilities.Validations;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;


public class P05_EditRequestPage {
    private static final Logger log = LoggerFactory.getLogger(P05_EditRequestPage.class);

    public P05_EditRequestPage(WebDriver driver) { this.driver = driver;
    }
    private WebDriver driver;

    private final By RequestsIcon = By.xpath("//img[@src=\"../../assets/images/certificates-icon.svg\"]");
    private final By EditRequest_FSM = By.xpath("//a[contains(@href, '/requests/editCommercialList')]");
    private final By AddNewRequest_Button = By.xpath("//img[@src=\"../../../assets/images/circle-plus-white.svg\"]");
    private final By CheckBoxOFLegalData = By.xpath("(//input[@type=\"checkbox\"])[1]");
    private final By AcceptRequestButtonOfPopUP = By.xpath("//button[contains(text(),\" اقبل الطلب\")]");
    private final By EditButton = By.xpath("(//li[.//*[local-name()='svg' and contains(@class,'edit-icon')]])[1]");
    private final By PullTheRequest = By.xpath("//label[contains(normalize-space(), 'اسحب الطلب')]//input[@type='checkbox']");
    private final By AcceptRequestButton = By.xpath("//span[contains(text(),\"أقبل الطلب\")]");
    private final By AcceptButtonOfWarningPopUp = By.xpath("//button[@class=\"swal2-confirm swal2-styled\"]");


    public P05_EditRequestPage CreateNewEditRequest_Button () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,EditRequest_FSM);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddNewRequest_Button);
        return this;
    }
    public P05_EditRequestPage Validate_EditRequestURL () throws InterruptedException {
        Validations.ValidateURL("https://coc-backoffice-fix.apps.softprograms-eg.com/requests/issueCommerciallist");
        return this;
    }

    public P05_EditRequestPage FillAndSaveDataOfApplicant (String ID , String Phone) throws InterruptedException {
        BackofficeBasedFunctions.FillAndSaveDataOfApplicant(driver,ID,Phone);
        return this;
    }

    public P05_EditRequestPage ChoosingRecord (String RecordNumber){
        BackofficeBasedFunctions.ChoosingRecord(driver,RecordNumber);
        return this;
    }

    public P05_EditRequestPage SelectSectionToEdit (){
    Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,CheckBoxOFLegalData);
        return this;
    }

    public P05_EditRequestPage FillCertificateData () throws InterruptedException {
        BackofficeBasedFunctions.FillCertificateData(driver);
        return this;
    }

    public P05_EditRequestPage FillAttachmentSection() {
        BackofficeBasedFunctions.FillAttachmentSection(driver);
        return this;
    }

    public P05_EditRequestPage ConfirmRequest() throws InterruptedException {
        BackofficeBasedFunctions.ConfirmRequest(driver);
        return this;
    }

    public P05_EditRequestPage SaveRequestNumberInJsonFile() {
        BackofficeBasedFunctions.SaveRequestNumberInJsonFile(driver,"EditRequestNumber");
        return this;
    }

    public P05_EditRequestPage ReviewRequest () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,EditButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AcceptRequestButton);
        // بوب اب التحذير بيظهر في حالات بس: بنستنى اللودر يخلص وبعدين ندور عليه ثانيتين بس
        // لو ظهر بندوس عليه ولو مظهرش بنكمل على طول - وفي الحالتين بندوس اقبل الطلب
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.invisibilityOfElementLocated(Utility.Loader));
        try {
            new WebDriverWait(driver, Duration.ofSeconds(2), Duration.ofMillis(200))
                    .until(ExpectedConditions.elementToBeClickable(AcceptButtonOfWarningPopUp)).click();
        } catch (TimeoutException e) {
            // مفيش بوب اب تحذير
        }
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, AcceptRequestButtonOfPopUP);

        return this;
    }

    public P04_InvoicesPage SendingRequestToCollect(){
        BackofficeBasedFunctions.SendingRequestToCollect(driver);
        return new P04_InvoicesPage(driver);
    }

    public P05_EditRequestPage ReceivingTheCertificate () throws InterruptedException {
        BackofficeBasedFunctions.ReceivingTheCertificate(driver);
        return this;
    }

}
