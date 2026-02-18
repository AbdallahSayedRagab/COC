package Tests;

import DriverFactory.DriverFactoryClass;
import Listeners.IInvociedMethodListener;
import Listeners.ITestResultListener;
import Pages.*;
import Utilities.DataUtiles;
import Utilities.Utility;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.awt.*;
import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactoryClass.SetupDriver;
import static DriverFactory.DriverFactoryClass.getdriver;

@Listeners({IInvociedMethodListener.class , ITestResultListener.class})
public class T02_HomePage {
    private WebDriver driver ;
    @BeforeClass
    public void setupdriver () throws IOException {
        SetupDriver(DataUtiles.getPropertyValue("environment","BROWSER"));
        driver = DriverFactoryClass.getdriver();
        getdriver().get(DataUtiles.getPropertyValue("environment","BASE_URL"));
        Utility.WaitForPageLoad(driver);
//        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

    }

    @Test (priority = 1)
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
                .FillCertificateData().FillAttachmentSectionAndConfirmRequest().SaveRequestNumberInJsonFile()
                .ReviewRequest().SendingRequestToCollect()
                .PayingFirstBill().Select_BusinessLicense().ReceivingTheCertificate();

    }

    @Test (priority = 2)
    public void CreateRecordEffect() throws InterruptedException {
//        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
//                ,DataUtiles.getJsonData("Data","Password"));
        new V01_CreateRecordEffectPage(driver).AddNewEffectToCreateNewRecord()
                .FillDataForRecordAndCreateEffect(DataUtiles.getJsonData("Data","BusinessLicenseRequestNumber"),
                        DataUtiles.getJsonData("Data","BusinessLicenseRequestNumber"),
                        DataUtiles.getJsonData("Data","BusinessLicenseRequestNumber")    )
                .ConfirmAndApproveTheRecord();
    }

    @Test (priority = 3)
    public void EditRequest () throws InterruptedException, AWTException {
//        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
//                ,DataUtiles.getJsonData("Data","Password"));
        new P05_EditRequestPage(driver).CreateNewEditRequest_Button().FillAndSaveDataOfApplicant(DataUtiles.getJsonData("Data","ID"))
                .ChoosingRecord(DataUtiles.getJsonData("Data","RecordNumber")).SelectSectionToEdit().FillCertificateData()
                .FillAttachmentSectionAndConfirmRequest().SaveRequestNumberInJsonFile().ReviewRequest()
                .SendingRequestToCollect().PayingFirstBill().Select_EditRequest().ReceivingTheCertificate();
    }

    @Test (priority = 4)
    public void EditRecordEffect() throws InterruptedException {
//        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
//                ,DataUtiles.getJsonData("Data","Password"));
        new V02_EditRecordEffectPage(driver).AddNewEffectToEditNewRecord()
                .FillDataForRecordAndCreateEditEffect(
                        DataUtiles.getJsonData("Data","RecordNumber"),
                        DataUtiles.getJsonData("Data","EditRequestNumber"),
                        DataUtiles.getJsonData("Data","EditRequestNumber"))
                .ConfirmAndApproveTheRecord();
    }

    @Test (priority = 5)
    public void RemoveRequest() throws InterruptedException, AWTException {
//        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
//                ,DataUtiles.getJsonData("Data","Password"));
        new P06_RemoveRecordPage(driver).CreateNewRemoveRequest_Button().FillAndSaveDataOfApplicant(DataUtiles.getJsonData("Data","ID"))
                .ChoosingRecord(DataUtiles.getJsonData("Data","RecordNumber")).FillCertificateData()
                .FillAttachmentSectionAndConfirmRequest().ReviewRequest()
                .SendingRequestToCollect().PayingFirstBill().Select_RemoveRequest().ReceivingTheCertificate();

    }
    @Test
    public void sourcecode () throws InterruptedException, AWTException {
        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
                ,DataUtiles.getJsonData("Data","Password")).selectthesourcecode()
                .ClickOnAddNewRequest_Button()
                .enterthePersonalidentificationnumber(DataUtiles.getJsonData("Data","ID"))
                .clickonsavedmedicalprovider()
                .searchonrecordnumber(DataUtiles.getJsonData("Data","requestNumber"))
                .clickoncommericaldata(DataUtiles.getJsonData("Data","First Name"),DataUtiles.getJsonData("Data","City"))
                .clickonItems(DataUtiles.getJsonData("Data","desccatogery"),DataUtiles.getJsonData("Data","Trademarks"),DataUtiles.getJsonData("Data","Numberofparcels")
                ,DataUtiles.getJsonData("Data","netweight"),DataUtiles.getJsonData("Data","Totalweight"),DataUtiles.getJsonData("Data","Totalvalue"))
                .Certificatecount("2")
                .Signatureauthentication()
                .Certificatelanguage()
                .confirmrequest();




    }
    @Test
    public void AdministrativeCertificate () throws InterruptedException, AWTException {
        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
                        ,DataUtiles.getJsonData("Data","Password"))
                .selecttheAdministrativecertificate()
                .ClickOnAddNewRequest_Button()
                .EnterThePersonalIdentificationNumber(DataUtiles.getJsonData("Data","ID"))
                .ClickOnSavedMedicalProvider()
                .SearchOnRecordNumber(DataUtiles.getJsonData("Data","RecordNumber"))
                .EnterTheAdministrativeCertificateDetails("alahly")
                .SendCeriticateTo("bahaa")
                .ConfirmRequest();





    }


        @AfterClass
    public void quit () {
        driver.quit();
    }
}




