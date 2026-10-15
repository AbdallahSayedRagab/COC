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
public class T04_SpecialServiceRequest {
    private WebDriver driver ;
    @BeforeClass
    public void setupdriver () throws IOException {
        SetupDriver(DataUtiles.getPropertyValue("environment","BROWSER"));
        driver = DriverFactoryClass.getdriver();
        getdriver().get(DataUtiles.getPropertyValue("environment","PORTAL_URL"));
        Utility.WaitForPageLoad(driver);
    }

    @Test (priority = 1)
    public void SpecialServiceRequestFromPortalAndApproveItFromBackOffice () throws InterruptedException, AWTException, IOException {
        new P15_SpecialServicePortalPage(driver).LoginToPortal(DataUtiles.getJsonData("Data","PortalEmail")
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
                .ApproveTheRequest(DataUtiles.getJsonData("Data","SpecialServiceRequestNumber"),DataUtiles.getJsonData("Data","TestData"))
                .ValidateRequestIsApproved(DataUtiles.getJsonData("Data","SpecialServiceRequestNumber"))
                .BackToPortalWindow().OpenMyAccount()
                .OpenSpecialServicesAndSearchOnRequest(DataUtiles.getJsonData("Data","SpecialServiceRequestNumber"))
                .ViewRequestAndBookAppointment(DataUtiles.getJsonData("Data","SpecialServiceRequestNumber"))
                .ConfirmReservation(DataUtiles.getJsonData("Data","Number"),DataUtiles.getJsonData("Data","First Name"),
                        DataUtiles.getJsonData("Data","ID"))
                .SaveReservationNumberInJsonFile()
                .PrintReservationData("SpecialServiceReservation");
    }

    @Test (priority = 2)
    public void NormalReservationFromPortalWithoutLogin () throws InterruptedException, IOException {
        getdriver().get(DataUtiles.getPropertyValue("environment","PORTAL_URL"));
        Utility.WaitForPageLoad(driver);
        new P15_SpecialServicePortalPage(driver).LogoutFromPortalIfLoggedIn();
        new P17_PortalReservationPage(driver).CreateNormalReservationWithoutLogin(DataUtiles.getJsonData("Data","Full Name"),
                        DataUtiles.getJsonData("Data","Number"),DataUtiles.getJsonData("Data","First Name"),
                        DataUtiles.getJsonData("Data","ID"),DataUtiles.getJsonData("Data","ChamberBranch"))
                .SaveReservationNumberInJsonFile()
                .PrintReservationData("NormalReservationFromPortalWithoutLogin");
    }

    @Test (priority = 3)
    public void NormalReservationFromPortalWithLogin () throws InterruptedException, IOException {
        getdriver().get(DataUtiles.getPropertyValue("environment","PORTAL_URL"));
        Utility.WaitForPageLoad(driver);
        new P15_SpecialServicePortalPage(driver).LogoutFromPortalIfLoggedIn()
                .LoginToPortal(DataUtiles.getJsonData("Data","PortalEmail"),DataUtiles.getJsonData("Data","PortalPassword"));
        new P17_PortalReservationPage(driver).CreateNormalReservation(DataUtiles.getJsonData("Data","Number"),
                        DataUtiles.getJsonData("Data","First Name"),DataUtiles.getJsonData("Data","ID"),
                        DataUtiles.getJsonData("Data","ChamberBranch"))
                .SaveReservationNumberInJsonFile()
                .PrintReservationData("NormalReservationFromPortalWithLogin");
    }

    @AfterClass
    public void quit () {
//        driver.quit();
    }
}
