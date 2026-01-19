package Tests;

import DriverFactory.DriverFactoryClass;
import Listeners.IInvociedMethodListener;
import Listeners.ITestResultListener;
import Pages.*;
import Utilities.DataUtiles;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactoryClass.SetupDriver;
import static DriverFactory.DriverFactoryClass.getdriver;

@Listeners({IInvociedMethodListener.class , ITestResultListener.class})
public class T02_HomePage {
    private WebDriver driver ;
    @BeforeMethod
    public void setupdriver () throws IOException {
        SetupDriver(DataUtiles.getPropertyValue("environment","BROWSER"));
        driver = DriverFactoryClass.getdriver();
        getdriver().get(DataUtiles.getPropertyValue("environment","BASE_URL"));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

    }


    @Test
    public void BusinessLicenseRequest () throws InterruptedException, AWTException {
        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
                ,DataUtiles.getJsonData("Data","Password")).Select_BusinessLicense()
                .ClickOnAddNewRequest_Button().ChooseFacilityNature_And_CreateRequest()
                .FillAndSaveDataOfApplicant(DataUtiles.getJsonData("Data","ID"))
                .FillLegalDataSection(DataUtiles.getJsonData("Data","TradeName"),
                        DataUtiles.getJsonData("Data" , "CommercialFeature"))
                .FillMembersSection().FillActivitiesDataSection(DataUtiles.getJsonData("Data","TaxCardNum"),
                        DataUtiles.getJsonData("Data","Description"))
                .FillCapitalDataSection(DataUtiles.getJsonData("Data","Capital"))
                .FillBusinessLocationSection(DataUtiles.getJsonData("Data","NameOfSt"))
                .FillCertificateData().FillAttachmentSectionAndConfirmRequest().ReviewRequest().SendingRequestToCollect()
                .PayingFirstBill().Select_BusinessLicense().ReceivingTheCertificate();

    }

    @Test
    public void EditRequest () throws InterruptedException, AWTException {
        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
                ,DataUtiles.getJsonData("Data","Password"));
        new P05_EditRequestPage(driver).CreateNewEditRequest_Button().FillAndSaveDataOfApplicant(DataUtiles.getJsonData("Data","ID"))
                .ChoosingRecord(DataUtiles.getJsonData("Data","Record")).SelectSectionToEdit().FillCertificateData()
                .FillAttachmentSectionAndConfirmRequest().ReviewRequest()
                .SendingRequestToCollect().PayingFirstBill().Select_EditRequest().ReceivingTheCertificate();
    }
//    @Test
//    public void EditReqwwwwwuest () throws InterruptedException {
//        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
//                ,DataUtiles.getJsonData("Data","Password")).Select_BusinessLicense();
//        new P05_EditRequestPage(driver).ReviewRequest()
//                .SendingRequestToCollect();
//    }




    @AfterMethod
    public void quit () {
//        driver.quit();
    }
}




