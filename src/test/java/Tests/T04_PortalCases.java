package Tests;

import DriverFactory.DriverFactoryClass;
import Listeners.IInvociedMethodListener;
import Listeners.ITestResultListener;
import Pages.BackofficePages.*;
import Pages.PortalPages.*;
import Utilities.DataUtiles;
import Utilities.Utility;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.awt.*;
import java.io.IOException;

import static DriverFactory.DriverFactoryClass.SetupDriver;
import static DriverFactory.DriverFactoryClass.getdriver;

@Listeners({IInvociedMethodListener.class , ITestResultListener.class})
public class T04_PortalCases {
    private WebDriver driver ;
    // كل test بيفتح براوزر private جديد عشان يعمل login على طول
    @BeforeMethod
    public void setupdriver () throws IOException {
        SetupDriver(DataUtiles.getPropertyValue("environment","BROWSER"));
        driver = DriverFactoryClass.getdriver();
        getdriver().get(DataUtiles.getPropertyValue("environment","PORTAL_URL"));
        Utility.WaitForPageLoad(driver);
    }

    @Test (priority = 1)
    public void BusinessLicenseRequestFromPortal () throws InterruptedException, IOException {
        new U01_PortalDashboard(driver).LoginToPortal(DataUtiles.getJsonData("Data","PortalEmail"),DataUtiles.getJsonData("Data","PortalPassword"))
                .OpenBusinessLicenseRequest().StartTheService()
                .FillLegalDataSection(DataUtiles.getJsonData("Data","TradeName"))
                .AddPartnerFromAccountData()
                .FillActivitiesDataSection(DataUtiles.getJsonData("Data","TaxCardNum"),DataUtiles.getJsonData("Data","Description"))
                .FillCapitalDataSection(DataUtiles.getJsonData("Data","Capital"))
                .FillBusinessLocationSection(DataUtiles.getJsonData("Data","NameOfSt"))
                .FillCertificateDataAndSaveRequestNumber(DataUtiles.getJsonData("Data","ChamberBranch"),DataUtiles.getJsonData("Data","Governorate"))
                .FillAttachmentSection()
                .SendTheRequestAndValidateIt(DataUtiles.getJsonData("Data","PortalBusinessLicenseRequestNumber"));
    }

    @Test (priority = 2)
    public void EditRecordRequestFromPortal () throws InterruptedException, IOException {
        new U01_PortalDashboard(driver).LoginToPortal(DataUtiles.getJsonData("Data","PortalEmail"),DataUtiles.getJsonData("Data","PortalPassword"))
                .OpenEditRecordRequest().StartTheService()
                .SelectFirstRecord()
                .SelectLegalDataToEdit()
                .FillCertificateData(DataUtiles.getJsonData("Data","ChamberBranch"),DataUtiles.getJsonData("Data","Governorate"))
                .FillAttachmentSection()
                .SendTheRequestAndSaveRequestNumber();
    }

    @Test (priority = 3)
    public void RemoveRecordRequestFromPortal () throws InterruptedException, IOException {
        new U01_PortalDashboard(driver).LoginToPortal(DataUtiles.getJsonData("Data","PortalEmail"),DataUtiles.getJsonData("Data","PortalPassword"))
                .OpenRemoveRecordRequest().StartTheService()
                .SelectFirstRecord()
                .FillRemovalReason(DataUtiles.getJsonData("Data","TestData"))
                .FillCertificateData(DataUtiles.getJsonData("Data","ChamberBranch"),DataUtiles.getJsonData("Data","Governorate"))
                .FillAttachmentSection()
                .SendTheRequestAndSaveRequestNumber();
    }

    @Test (priority = 4)
    public void AdministrativeCertificateRequestFromPortal () throws InterruptedException, IOException {
        new U01_PortalDashboard(driver).LoginToPortal(DataUtiles.getJsonData("Data","PortalEmail"),DataUtiles.getJsonData("Data","PortalPassword"))
                .OpenAdministrativeCertificateRequest().StartTheService()
                .SelectFirstRecord()
                .FillCertificateDetails(DataUtiles.getJsonData("Data","TestData"))
                .FillAttachmentSection()
                .FillCertificateData(DataUtiles.getJsonData("Data","ChamberBranch"),DataUtiles.getJsonData("Data","TestData"))
                .SendTheRequestAndSaveRequestNumber();
    }

    @Test (priority = 5)
    public void SourceOfGoodsRequestFromPortal () throws InterruptedException, IOException {
        new U01_PortalDashboard(driver).LoginToPortal(DataUtiles.getJsonData("Data","PortalEmail"),DataUtiles.getJsonData("Data","PortalPassword"))
                .OpenSourceOfGoodsRequest().StartTheService()
                .SelectFirstRecord()
                .FillBasicData(DataUtiles.getJsonData("Data","First Name"),DataUtiles.getJsonData("Data","City"),
                        DataUtiles.getJsonData("Data","country"))
                .FillItemSection(DataUtiles.getJsonData("Data","desccatogery"),DataUtiles.getJsonData("Data","Trademarks"),
                        DataUtiles.getJsonData("Data","Numberofparcels"),DataUtiles.getJsonData("Data","netweight"),
                        DataUtiles.getJsonData("Data","Totalweight"),DataUtiles.getJsonData("Data","Totalvalue"))
                .FillCertificateData(DataUtiles.getJsonData("Data","ChamberBranch"),"1")
                .FillAttachmentSection()
                .SendTheRequestAndSaveRequestNumber();
    }

    @Test (priority = 6)
    public void PaymentOfSubscriptionsRequestFromPortal () throws InterruptedException, IOException {
        new U01_PortalDashboard(driver).LoginToPortal(DataUtiles.getJsonData("Data","PortalEmail"),DataUtiles.getJsonData("Data","PortalPassword"))
                .OpenPaymentOfSubscriptionsRequest().StartTheService()
                .SearchOnRecordAndSelectFirstResult(DataUtiles.getJsonData("Data","PortalSubscriptionRecordNumber"))
                .SelectFirstSubscriptionItem()
                .FillAttachmentSection()
                .SelectBranch(DataUtiles.getJsonData("Data","ChamberBranch"))
                .SendTheRequestAndSaveRequestNumber();
    }

    @Test (priority = 7)
    public void SpecialServiceRequestFromPortalAndApproveItFromBackOffice () throws InterruptedException, AWTException, IOException {
        new U01_PortalDashboard(driver).LoginToPortal(DataUtiles.getJsonData("Data","PortalEmail")
                        ,DataUtiles.getJsonData("Data","PortalPassword"))
                .OpenSpecialServiceRequest().StartTheService()
                .FillRequestDataAndSaveRequestNumber(DataUtiles.getJsonData("Data","TestData"))
                .FillAttachmentSection(DataUtiles.getJsonData("Data","TestData"))
                .ConfirmSendingTheRequest(DataUtiles.getJsonData("Data","ChamberBranch"))
                .OpenBackOfficeInNewWindow()
                .Login(DataUtiles.getJsonData("Data","ValidLoginEmail"),DataUtiles.getJsonData("Data","Password"));
        new P16_SpecialServiceBackOfficePage(driver).SelectBranch(DataUtiles.getJsonData("Data","ChamberBranch"))
                .OpenSpecialServiceRequests()
                .AcceptTheRequest(DataUtiles.getJsonData("Data","SpecialServiceRequestNumber"),DataUtiles.getJsonData("Data","TestData"))
                .EstimateTheFees(DataUtiles.getJsonData("Data","SpecialServiceRequestNumber"),DataUtiles.getJsonData("Data","TestData"))
                .ApproveTheRequest(DataUtiles.getJsonData("Data","SpecialServiceRequestNumber"),DataUtiles.getJsonData("Data","TestData"))
                .ValidateRequestIsApproved(DataUtiles.getJsonData("Data","SpecialServiceRequestNumber"))
                .BackToPortalWindow().OpenSpecialServicesInMyAccount()
                .SearchOnRequestInSpecialServices(DataUtiles.getJsonData("Data","SpecialServiceRequestNumber"))
                .ViewRequestAndBookAppointment(DataUtiles.getJsonData("Data","SpecialServiceRequestNumber"))
                .ConfirmReservation(DataUtiles.getJsonData("Data","Number"),DataUtiles.getJsonData("Data","First Name"),
                        DataUtiles.getJsonData("Data","ID"))
                .SaveReservationNumberInJsonFile()
                .PrintReservationData("SpecialServiceReservation");
    }

    @Test (priority = 8)
    public void NormalReservationFromPortalWithoutLogin () throws InterruptedException, IOException {
        new R1_PortalReservationPage(driver).CreateNormalReservationWithoutLogin(DataUtiles.getJsonData("Data","Full Name"),
                        DataUtiles.getJsonData("Data","Number"),DataUtiles.getJsonData("Data","First Name"),
                        DataUtiles.getJsonData("Data","ID"),DataUtiles.getJsonData("Data","ChamberBranch"))
                .SaveReservationNumberInJsonFile()
                .PrintReservationData("NormalReservationFromPortalWithoutLogin");
    }

    @Test (priority = 9)
    public void NormalReservationFromPortalWithLogin () throws InterruptedException, IOException {
        new U01_PortalDashboard(driver).LoginToPortal(DataUtiles.getJsonData("Data","PortalEmail"),DataUtiles.getJsonData("Data","PortalPassword"));
        new R1_PortalReservationPage(driver).CreateNormalReservation(DataUtiles.getJsonData("Data","Number"),
                        DataUtiles.getJsonData("Data","First Name"),DataUtiles.getJsonData("Data","ID"),
                        DataUtiles.getJsonData("Data","ChamberBranch"))
                .SaveReservationNumberInJsonFile()
                .PrintReservationData("NormalReservationFromPortalWithLogin");
    }

    @Test (priority = 10, description = "تسجيل الدخول بالمستخدم الجديد في البورتال")
    public void LoginWithNewPortalUser () {
        new U01_PortalDashboard(driver).LoginWithNewPortalUser(DataUtiles.getJsonData("Data","NewUsernamePortal"),
                DataUtiles.getJsonData("Data","NewPasswordPortal"),
                DataUtiles.getJsonData("Data","NewUsersPassword"));
    }

    @AfterMethod
    public void quit () {
        DriverFactoryClass.Quit();
    }
}
