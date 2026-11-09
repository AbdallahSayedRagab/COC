package Pages.BackofficePages;

import Utilities.BackofficeBasedFunctions;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P12_DivisionEditRequestPage {
    public P12_DivisionEditRequestPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;


    private final By RequestsIcon = By.xpath("//img[@src=\"../../assets/images/certificates-icon.svg\"]");
    private final By DivisionEditRequest_FSM = By.xpath("//a[contains(@href, '/requests/recordDivisionRequestList')]");
    private final By AddNewRequest_Button = By.xpath("//img[@src=\"../../../assets/images/circle-plus-white.svg\"]");
//    private final By CheckBoxOFLegalData = By.xpath("(//input[@type=\"checkbox\"])[1]");
//    private final By SendCertificateToOfficeField = By.xpath("//label[normalize-space(text())='تقديم الشهادة الى مكتب']/following-sibling::*[1]");

    private final By DepartmentName = By.xpath("//label[normalize-space(text())='اسم الشعبة']/following-sibling::*[1]");
    private final By AddDepartNameButton = By.xpath("//button[contains(text(), ' اضافة شعبة')]");

    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");
//    private final By RecieveCertificateButton = By.xpath("//button[contains(text(), \"تم التسليم\")]");


    public P12_DivisionEditRequestPage CreateNewDivisionEditRequest_Button() throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, RequestsIcon);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, DivisionEditRequest_FSM);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, AddNewRequest_Button);
        return this;
    }

    public P12_DivisionEditRequestPage FillAndSaveDataOfApplicant(String ID , String Phone) throws InterruptedException {
        BackofficeBasedFunctions.FillAndSaveDataOfApplicant(driver, ID, Phone);
        return this;
    }

    public P12_DivisionEditRequestPage ChoosingRecord (String RecordNumber){
        BackofficeBasedFunctions.ChoosingRecord(driver,RecordNumber);
        return this;
    }

    public P12_DivisionEditRequestPage FillDivisionDataSection() throws InterruptedException {

        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, DepartmentName);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, Option1ForAnyList);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, AddDepartNameButton);
        return this;
    }

    public P12_DivisionEditRequestPage FillAttachmentSection() {
        BackofficeBasedFunctions.FillAttachmentSection(driver);
        return this;
    }

    public P12_DivisionEditRequestPage ConfirmRequest () throws InterruptedException {
        BackofficeBasedFunctions.ConfirmRequest(driver);
        return this;
    }

    public P12_DivisionEditRequestPage ReviewRequest() throws InterruptedException {
        BackofficeBasedFunctions.ReviewRequest(driver);
        return this;
    }

    public P04_InvoicesPage SendingRequestToCollect() {
        BackofficeBasedFunctions.SendingRequestToCollect(driver);
        return new P04_InvoicesPage(driver);
    }


}
