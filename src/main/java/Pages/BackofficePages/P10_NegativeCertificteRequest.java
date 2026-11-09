package Pages.BackofficePages;

import Utilities.BackofficeBasedFunctions;
import Utilities.Utility;
import Utilities.Validations;
import org.openqa.selenium.*;

import java.util.NoSuchElementException;

public class P10_NegativeCertificteRequest {
    public P10_NegativeCertificteRequest(WebDriver driver) { this.driver = driver;}
    private WebDriver driver;

    private final By RequestsIcon = By.xpath("//img[@src=\"../../assets/images/certificates-icon.svg\"]");
    private final By NegativeRequest_FSM = By.xpath("//a[contains(@href, '/requests/negative-request-list')]");
    private final By AddNewRequest_Button = By.xpath("//img[@src=\"../../../assets/images/circle-plus-white.svg\"]");
    private final By SendCertificateToNameField = By.xpath("//label[normalize-space(text())='تقديم الشهادة الي الاسم']/following-sibling::*[1]");
    private final By SendCertificateToEntityField = By.xpath("//label[normalize-space(text())='تقديم الشهادة الي الجهة']/following-sibling::*[1]");
    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");



    public P10_NegativeCertificteRequest CreateNewNegativeRequest_Button () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,NegativeRequest_FSM);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddNewRequest_Button);
        return this;
    }
    public P10_NegativeCertificteRequest Validate_EditRequestURL () throws InterruptedException {
        Validations.ValidateURL("https://coc-backoffice-fix.apps.softprograms-eg.com/requests/issueCommerciallist");
        return this;
    }

    public P10_NegativeCertificteRequest FillAndSaveDataOfApplicant (String NegativeID , String Phone) throws InterruptedException {
        BackofficeBasedFunctions.FillAndSaveDataOfApplicant(driver,NegativeID,Phone);
        return this;
    }

    public P10_NegativeCertificteRequest FillCertificateData (String TestData) throws InterruptedException {
        Utility.WatingAndSENDKEYS(driver,SendCertificateToNameField,TestData);
        Utility.WatingAndSENDKEYS(driver,SendCertificateToEntityField,TestData);
        return this;
    }

    public P10_NegativeCertificteRequest FillAttachmentSection() {
        BackofficeBasedFunctions.FillAttachmentSection(driver);
        return this;
    }

    public P10_NegativeCertificteRequest ConfirmRequest() throws InterruptedException {
        BackofficeBasedFunctions.ConfirmRequest(driver);
        return this;
    }


    public P10_NegativeCertificteRequest ReviewRequest () throws InterruptedException {
        BackofficeBasedFunctions.ReviewRequest(driver);
        return this;
    }

    public P04_InvoicesPage SendingRequestToCollect(){
        BackofficeBasedFunctions.SendingRequestToCollect(driver);
        return new P04_InvoicesPage(driver);
    }

    public P10_NegativeCertificteRequest ReceivingTheCertificate () throws InterruptedException {
        BackofficeBasedFunctions.ReceivingTheCertificate(driver);
        return this;
    }

}

