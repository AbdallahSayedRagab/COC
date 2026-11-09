package Pages.BackofficePages;

import Utilities.BackofficeBasedFunctions;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.awt.*;

public class p08_Requestforissuanceofanadministrativecertificate {
    private final WebDriver driver;
    private final By TextArea=By.xpath("//input-control[@formcontrolname='certificateAbout']//textarea");
    private final By CertificateToMe=By.xpath("//input-control[@formcontrolname='certificateFor']//input");

    public p08_Requestforissuanceofanadministrativecertificate(WebDriver driver) {
        this.driver=driver;
    }
    public p08_Requestforissuanceofanadministrativecertificate ClickOnAddNewRequest_Button () throws InterruptedException {
        BackofficeBasedFunctions.ClickOnAddNewRequest_Button(driver);
        return this;
    }
    public p08_Requestforissuanceofanadministrativecertificate SearchOnRecordNumber(String recordnumber) throws InterruptedException {
        BackofficeBasedFunctions.SearchOnRecordNumber(driver,recordnumber);
        return this;
    }

    public p08_Requestforissuanceofanadministrativecertificate EnterThePersonalIdentificationNumber(String personalidentifier){
        BackofficeBasedFunctions.EnterThePersonalIdentificationNumber(driver,personalidentifier);
        return this;
    }
    public p08_Requestforissuanceofanadministrativecertificate ClickOnSavedMedicalProvider() throws InterruptedException {
        BackofficeBasedFunctions.ClickOnSavedMedicalProvider(driver);
        return this;
    }

    public p08_Requestforissuanceofanadministrativecertificate FillAttachmentSection() {
        BackofficeBasedFunctions.FillAttachmentSection(driver);
        return this;
    }

    public p08_Requestforissuanceofanadministrativecertificate ConfirmRequest() throws InterruptedException, AWTException {
        BackofficeBasedFunctions.ConfirmRequest(driver);
        return this;
    }
    public p08_Requestforissuanceofanadministrativecertificate SendCeriticateTo(String text){
        Utility.SENDKEYS(driver,CertificateToMe,text);

        return this;
    }
    public p08_Requestforissuanceofanadministrativecertificate EnterTheAdministrativeCertificateDetails(String text){
        Utility.SENDKEYS(driver,TextArea,text);
        return this;

    }
    public p08_Requestforissuanceofanadministrativecertificate ReviewRequest () throws InterruptedException {
        BackofficeBasedFunctions.ReviewRequest(driver);
        return this;
    }

    public P04_InvoicesPage SendingRequestToCollect(){
        BackofficeBasedFunctions.SendingRequestToCollect(driver);
        return new P04_InvoicesPage(driver);
    }

    public p08_Requestforissuanceofanadministrativecertificate ReceivingTheCertificate () throws InterruptedException {
        BackofficeBasedFunctions.ReceivingTheCertificate(driver);
        return this;
    }


}
