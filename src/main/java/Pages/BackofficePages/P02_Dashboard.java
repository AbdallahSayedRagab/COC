package Pages.BackofficePages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class P02_Dashboard {
    public P02_Dashboard (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;
    private final By RequestsIcon = By.xpath("//img[@src=\"../../assets/images/certificates-icon.svg\"]");
    private final By Records = By.xpath("//img[@src=\"../../assets/images/records-icon.svg\"]");
    private final By BusinessLicenseRequest_FSM = By.xpath("//a[contains(@href,'/requests/issueCommerciallist')]");
    private final By EditRequest_FSM = By.xpath("//a[contains(@href, '/requests/editCommercialList')]");
    private final By RemoveRequest_FSM = By.xpath("//a[contains(@href, '/requests/removeCommercialList')]");
    private final By  Sourceofgoods=By.xpath("//a[contains(@href,'/requests/goodsOriginrequestslist')]");
    private final By  Administrativecertificate =By.xpath("//a[contains(@href,'/requests/issueManagementList')]");
    private final By NegativeRequest_FSM = By.xpath("//a[contains(@href, '/requests/negative-request-list')]");
    private final By ServicesRequest_FSM = By.xpath("//a[contains(@href, '/requests/unregisteredRequestList')]");
    private final By DivisionEditRequest_FSM = By.xpath("//a[contains(@href, '/requests/recordDivisionRequestList')]");
    private final By RecordsManagement = By.xpath("//a[contains(@href,'/records/list')]");
    private final By StatementForm_FSM = By.xpath("//a[contains(@href,'/records/statement')]");
    private final By Payingsubscriptions = By.xpath("//a[contains(@href,'/requests/membership-collection-requests')]");
    private final By ReservationIcon = By.xpath("//img[@src=\"../../assets/images/booking.svg\"]");
    private final By ReservationsPage_FSM = By.xpath("//a[contains(@href,'/managementsys/electronicbookingmanagement')]");
    private final By SelfService_FSM = By.xpath("//a[contains(@href,'/managementsys/selfservicemanagement')]");
    private final By RequestsAndCertificates_FSM = By.xpath("//a[contains(@href,'/requests/management')]");
    private final By RequestsAndCertificatesMail_FSM = By.xpath("//a[contains(@href,'/requests/requests-certificates-mailbox')]");
    private final By QueuePage_FSM = By.xpath("//a[contains(@href,'/managementsys/electronicbookingqueuemanagement')]");
    private final By ProfileIcon = By.xpath("//div[contains(@class,'header-user')]//a[contains(@class,'dropdown-toggle')]");
    private final By LogoutButton = By.xpath("//a[contains(@class,'logout')]");
    private final By Login_Email = By.xpath("//input[@formcontrolname=\"username\"]");



    public P03_BusinessLicensePage Select_BusinessLicense () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon);
        Utility.CLICKONELEMENTS(driver,BusinessLicenseRequest_FSM);
        return new P03_BusinessLicensePage(driver);
    }
    public p07_sourcedcode selectthesourcecode() throws InterruptedException {

        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon);
        Utility.CLICKONELEMENTS(driver,Sourceofgoods);
        return new p07_sourcedcode(driver);


    }
    public P05_EditRequestPage Select_EditRequest () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,EditRequest_FSM);
        return new P05_EditRequestPage(driver);
    }
    public P06_RemoveRecordPage Select_RemoveRequest () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RemoveRequest_FSM);
        return new P06_RemoveRecordPage(driver);
    }
    public p08_Requestforissuanceofanadministrativecertificate selectTheAdministrativeCertificate() throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,Administrativecertificate);
        return new p08_Requestforissuanceofanadministrativecertificate(driver);

    }
    public P09_PaymentOfSubscriptions selectThePaymentOfSubscriptions() throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon);
        Utility.CLICKONELEMENTS(driver,Payingsubscriptions);
        return new P09_PaymentOfSubscriptions(driver);
    }
    public R01_RecordsManagement SelectTheRecorsMangements() throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,Records);
        Utility.CLICKONELEMENTS(driver,RecordsManagement);
        return new R01_RecordsManagement(driver);
    }

    public R02_StatementFormPage SelectStatementFormPage (){
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,Records);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,StatementForm_FSM);
        return new R02_StatementFormPage(driver);
    }


    public P10_NegativeCertificteRequest Select_NegativeRequest() throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon);
        Utility.CLICKONELEMENTS(driver,NegativeRequest_FSM);
        return new P10_NegativeCertificteRequest(driver);

    }
    public P10_NegativeCertificteRequest Select_ServicesRequest() throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon);
        Utility.CLICKONELEMENTS(driver,ServicesRequest_FSM);
        return new P10_NegativeCertificteRequest(driver);
    }
    public P12_DivisionEditRequestPage Select_DivisionRequest () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,DivisionEditRequest_FSM);
        return new P12_DivisionEditRequestPage(driver);
    }

    public K01_ResrvationsListPage SelectReservationsPage (){
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ReservationIcon);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ReservationsPage_FSM);
        return new K01_ResrvationsListPage(driver);
    }

    public K02_SelfServicePage SelectSelfServicePage (){
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ReservationIcon);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SelfService_FSM);
        return new K02_SelfServicePage(driver);
    }

    public P13_RequestsAndCertificatesPage SelectRequestsAndCertificatesPage (){
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsAndCertificates_FSM);
        return new P13_RequestsAndCertificatesPage(driver);
    }

    public P14_RequestsAndCertificatesMailPage SelectRequestsAndCertificatesMailPage (){
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsAndCertificatesMail_FSM);
        return new P14_RequestsAndCertificatesMailPage(driver);
    }

    public P01_LoginPage Logout (){
        // نستنى الصفحة تحدد حالتها: يا صفحة تسجيل الدخول (مش عامل دخول) يا ايقونة البروفايل (عامل دخول)
        new WebDriverWait(driver, Duration.ofSeconds(180), Duration.ofMillis(200)).until(d ->
                !d.findElements(Login_Email).isEmpty() || !d.findElements(ProfileIcon).isEmpty());
        if (!driver.findElements(ProfileIcon).isEmpty()) {
            Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ProfileIcon);
            Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,LogoutButton);
        }
        return new P01_LoginPage(driver);
    }

    public K03_QueuePage SelectQueuePage (){
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ReservationIcon);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,QueuePage_FSM);
        return new K03_QueuePage(driver);
    }

}
