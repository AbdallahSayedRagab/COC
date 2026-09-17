package Tests;

import DriverFactory.DriverFactoryClass;
import Pages.P01_LoginPage;
import Pages.P02_Dashboard;
import Utilities.DataUtiles;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.awt.*;
import java.io.IOException;

import static DriverFactory.DriverFactoryClass.SetupDriver;
import static DriverFactory.DriverFactoryClass.getdriver;


public class T03_ReservationTests {
    private WebDriver driver ;
    @BeforeMethod
    public void setupdriver () throws IOException {
        SetupDriver(DataUtiles.getPropertyValue("environment","BROWSER"));
        driver = DriverFactoryClass.getdriver();
        getdriver().get(DataUtiles.getPropertyValue("environment","BASE_URL"));
//       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }


    @Test(priority = 1)
    public void  AddNormalReservation () throws InterruptedException, AWTException, IOException {
        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
                ,DataUtiles.getJsonData("Data","Password"));
        new P02_Dashboard(driver).SelectReservationsPage()
                .CreateNormalReservation(DataUtiles.getJsonData("Data","Full Name"),
                        DataUtiles.getJsonData("Data","Number"),
                        DataUtiles.getJsonData("Data","ID"))
                .SaveReservationNumberInJsonFile();

    }
    @Test (priority = 2)
    public void  AddVIPReservation () throws InterruptedException, AWTException, IOException {
        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
                ,DataUtiles.getJsonData("Data","Password"));
        new P02_Dashboard(driver).SelectReservationsPage()
                .CreateVIPReservation(DataUtiles.getJsonData("Data","Full Name"),
                        DataUtiles.getJsonData("Data","Number"),
                        DataUtiles.getJsonData("Data","ID"))
                .PayingFirstBill().SelectReservationsPage().ConfirmFirstReservationInTable()
        ;

    }
    @Test (priority = 3)
    public void  AddReservationAndPrintReservationData () throws InterruptedException, AWTException, IOException {
        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
                ,DataUtiles.getJsonData("Data","Password"));
        new P02_Dashboard(driver).SelectReservationsPage()
                .CreateNormalReservation(DataUtiles.getJsonData("Data","Full Name"),
                        DataUtiles.getJsonData("Data","Number"),
                        DataUtiles.getJsonData("Data","ID"))
                .PrintReservationData("ReservationData");

    }

    @Test (priority = 4)
    public void  ConfirmReservationAndPrintQueueData () throws InterruptedException, AWTException, IOException {
        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
                ,DataUtiles.getJsonData("Data","Password"));
        new P02_Dashboard(driver).SelectReservationsPage()
                .CreateNormalReservation(DataUtiles.getJsonData("Data","Full Name"),
                        DataUtiles.getJsonData("Data","Number"),
                        DataUtiles.getJsonData("Data","ID"))
                .ConfirmFirstReservationInTable().PrintQueueData("QueueData");

    }

    @Test (priority = 5)
    public void  AddReservationAndRescheduleIt () throws InterruptedException, AWTException, IOException {
        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
                ,DataUtiles.getJsonData("Data","Password"));
        new P02_Dashboard(driver).SelectReservationsPage()
                .CreateNormalReservation(DataUtiles.getJsonData("Data","Full Name"),
                        DataUtiles.getJsonData("Data","Number"),
                        DataUtiles.getJsonData("Data","ID"))
                .ReschedulingReservation();

    }

    @Test (priority = 6)
    public void  CreateReservationFromSelfServicePage () throws InterruptedException, AWTException, IOException {
        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
                ,DataUtiles.getJsonData("Data","Password"));
        new P02_Dashboard(driver).SelectSelfServicePage()
                .CreateTodayReservation(DataUtiles.getJsonData("Data","Full Name"),
                        DataUtiles.getJsonData("Data","Number"),
                        DataUtiles.getJsonData("Data","ID"));
    }
    @Test (priority = 7)
    public void  ConfirmAttendForReservationFromSelfServicePage () throws InterruptedException, AWTException, IOException {
        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data", "ValidLoginEmail")
                , DataUtiles.getJsonData("Data", "Password"));
        new P02_Dashboard(driver).SelectSelfServicePage()
                .ConfirmAttendReservation(DataUtiles.getJsonData("Data", "ID"));
    }
    @Test (priority = 8)
    public void  CompleteTheReservation () throws InterruptedException, AWTException, IOException {
        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
                ,DataUtiles.getJsonData("Data","Password"));
        new P02_Dashboard(driver).SelectReservationsPage()
                .CreateNormalReservation(DataUtiles.getJsonData("Data","Full Name"),
                        DataUtiles.getJsonData("Data","Number"),
                        DataUtiles.getJsonData("Data","ID"))
                .ConfirmFirstReservationInTable().PrintQueueData("QueueNumber")
                .SaveReservationNumberInJsonFile().SelectRequestsAndCertificatesPage().
                CompleteReservationForLastReservation(DataUtiles.getJsonData("Data","ReservationNumber"));

    }



    @AfterMethod
    public void quit () {
        driver.quit();
    }

}
