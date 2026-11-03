package Tests;

import DriverFactory.DriverFactoryClass;
import Pages.BackofficePages.P01_LoginPage;
import Pages.BackofficePages.P02_Dashboard;
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
        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","NewUsernameBackOffice")
                ,DataUtiles.getJsonData("Data","NewPasswordBackOffice"));
        new P02_Dashboard(driver).SelectReservationsPage()
                .CreateNormalReservation(DataUtiles.getJsonData("Data","Full Name"),
                        DataUtiles.getJsonData("Data","Number"),
                        DataUtiles.getJsonData("Data","NewNationalIdPortal"))
                .SaveReservationNumberInJsonFile();

    }
    @Test (priority = 2)
    public void  AddVIPReservation () throws InterruptedException, AWTException, IOException {
        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","NewUsernameBackOffice")
                ,DataUtiles.getJsonData("Data","NewPasswordBackOffice"));
        new P02_Dashboard(driver).SelectReservationsPage()
                .CreateVIPReservation(DataUtiles.getJsonData("Data","Full Name"),
                        DataUtiles.getJsonData("Data","Number"),
                        DataUtiles.getJsonData("Data","NewNationalIdPortal"))
                .PayingFirstBill().SelectReservationsPage().ConfirmFirstReservationInTable()
        ;

    }
    @Test (priority = 3)
    public void  AddReservationAndPrintReservationData () throws InterruptedException, AWTException, IOException {
        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","NewUsernameBackOffice")
                ,DataUtiles.getJsonData("Data","NewPasswordBackOffice"));
        new P02_Dashboard(driver).SelectReservationsPage()
                .CreateNormalReservation(DataUtiles.getJsonData("Data","Full Name"),
                        DataUtiles.getJsonData("Data","Number"),
                        DataUtiles.getJsonData("Data","NewNationalIdPortal"))
                .PrintReservationData("ReservationData");

    }

    @Test (priority = 4)
    public void  ConfirmReservationAndPrintQueueData () throws InterruptedException, AWTException, IOException {
        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","NewUsernameBackOffice")
                ,DataUtiles.getJsonData("Data","NewPasswordBackOffice"));
        new P02_Dashboard(driver).SelectReservationsPage()
                .CreateNormalReservation(DataUtiles.getJsonData("Data","Full Name"),
                        DataUtiles.getJsonData("Data","Number"),
                        DataUtiles.getJsonData("Data","NewNationalIdPortal")).SaveReservationNumberInJsonFile()
                .ConfirmFirstReservationInTable().PrintQueueData("QueueData");

    }

    @Test (priority = 5)
    public void  AddReservationAndRescheduleIt () throws InterruptedException, AWTException, IOException {
        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","NewUsernameBackOffice")
                ,DataUtiles.getJsonData("Data","NewPasswordBackOffice"));
        new P02_Dashboard(driver).SelectReservationsPage()
                .CreateNormalReservation(DataUtiles.getJsonData("Data","Full Name"),
                        DataUtiles.getJsonData("Data","Number"),
                        DataUtiles.getJsonData("Data","NewNationalIdPortal"))
                .ReschedulingReservation();

    }

    @Test (priority = 6)
    public void  CreateReservationFromSelfServicePage () throws InterruptedException, AWTException, IOException {
        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","NewUsernameBackOffice")
                ,DataUtiles.getJsonData("Data","NewPasswordBackOffice"));
        new P02_Dashboard(driver).SelectSelfServicePage()
                .CreateTodayReservation(DataUtiles.getJsonData("Data","Full Name"),
                        DataUtiles.getJsonData("Data","Number"),
                        DataUtiles.getJsonData("Data","NewNationalIdPortal"));
    }
    @Test (priority = 7)
    public void  ConfirmAttendForReservationFromSelfServicePage () throws InterruptedException, AWTException, IOException {
        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","NewUsernameBackOffice")
                , DataUtiles.getJsonData("Data","NewPasswordBackOffice"));
        new P02_Dashboard(driver).SelectSelfServicePage()
                .ConfirmAttendReservation(DataUtiles.getJsonData("Data","NewNationalIdPortal"));
    }

//    @Test (priority = 8)
//    public void  CompleteTheReservation () throws InterruptedException, AWTException, IOException {
//        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
//                ,DataUtiles.getJsonData("Data","Password"));
//        new P02_Dashboard(driver).SelectReservationsPage()
//                .CreateNormalReservation(DataUtiles.getJsonData("Data","Full Name"),
//                        DataUtiles.getJsonData("Data","Number"),
//                        DataUtiles.getJsonData("Data","ID"))
//                .ConfirmFirstReservationInTable().PrintQueueData("QueueNumber")
//                .SaveReservationNumberInJsonFile().SelectRequestsAndCertificatesPage().
//                CompleteReservationForLastReservation(DataUtiles.getJsonData("Data","ReservationNumber"));
//
//    }

    @Test(priority = 9)
    public void AssignWindowToReservationFromQueuePage () throws InterruptedException {
        // مستخدم الشباك بيعمل حجز عادي النهارده واول فترة على فرعه، ويأكد الحضور، وبعدين يعينه على شباك من شاشة الدور
        new P01_LoginPage(driver).LoginAndChooseWindow(DataUtiles.getJsonData("Data","WindowUserName")
                ,DataUtiles.getJsonData("Data","WindowUserPassword"),DataUtiles.getJsonData("Data","WindowUserBranch"))
                .SelectReservationsPage()
                .CreateTodayNormalReservation(DataUtiles.getJsonData("Data","Full Name"),
                        DataUtiles.getJsonData("Data","Number"),
                        DataUtiles.getJsonData("Data","NewNationalIdPortal"))
                .SaveReservationNumberInJsonFile()
                .ConfirmFirstReservationInTable()
                .CloseQueueNumberPopUp();
        new P02_Dashboard(driver).SelectQueuePage().AssigningReservationToAvailableWindow();
        // انهاء الحجز من خدمة الجمهور في نفس الجلسة (تسجيل دخول جديد بيلغي التعيين) عشان الشباك يفضى للرن الجاي
        new P02_Dashboard(driver).SelectCitizenServicePage().FinishCurrentReservation();

    }

    @Test(priority = 10)
    public void CallNextReservationFromCitizenService () throws InterruptedException {
        // مستخدم الشباك بيعمل حجز عادي النهارده واول فترة، ويأكد الحضور من ادارة الحجوزات، وبعدين من خدمة الجمهور يستدعي الحجز التالي ويدوس انهاء
        new P01_LoginPage(driver).LoginAndChooseWindow(DataUtiles.getJsonData("Data","WindowUserName")
                ,DataUtiles.getJsonData("Data","WindowUserPassword"),DataUtiles.getJsonData("Data","WindowUserBranch"))
                .SelectReservationsPage()
                .CreateTodayNormalReservation(DataUtiles.getJsonData("Data","Full Name"),
                        DataUtiles.getJsonData("Data","Number"),
                        DataUtiles.getJsonData("Data","NewNationalIdPortal"))
                .SaveReservationNumberInJsonFile()
                .ConfirmFirstReservationInTable()
                .CloseQueueNumberPopUp();
        new P02_Dashboard(driver).SelectCitizenServicePage().CallNextReservation().FinishCurrentReservation();
    }



    @AfterMethod
    public void quit () {
        driver.quit();
    }

}
