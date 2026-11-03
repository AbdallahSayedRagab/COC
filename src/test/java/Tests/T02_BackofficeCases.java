package Tests;

import DriverFactory.DriverFactoryClass;
import Listeners.IInvociedMethodListener;
import Listeners.ITestResultListener;
import Pages.BackofficePages.*;
import Utilities.DataUtiles;
import Utilities.Utility;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.awt.*;
import java.io.IOException;

import static DriverFactory.DriverFactoryClass.SetupDriver;
import static DriverFactory.DriverFactoryClass.getdriver;

@Listeners({IInvociedMethodListener.class , ITestResultListener.class})
public class T02_BackofficeCases {
    private WebDriver driver ;
    @BeforeClass
    public void setupdriver () throws IOException {
        SetupDriver(DataUtiles.getPropertyValue("environment","BROWSER"));
        driver = DriverFactoryClass.getdriver();
        getdriver().get(DataUtiles.getPropertyValue("environment","BASE_URL"));
        Utility.WaitForPageLoad(driver);
//        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

    }

    @Test (priority = 8)
    public void BusinessLicenseRequest () throws InterruptedException, AWTException {
        new P02_Dashboard(driver).Select_BusinessLicense()
                .ClickOnAddNewRequest_Button().ChooseFacilityNature_And_CreateRequest()
                .FillAndSaveDataOfApplicant(DataUtiles.getJsonData("Data","NewNationalIdPortal"),DataUtiles.getJsonData("Data","Number"))
                .FillLegalDataSection(DataUtiles.getJsonData("Data","TradeName"),
                        DataUtiles.getJsonData("Data" , "CommercialFeature"))
                .FillMembersSection().FillActivitiesDataSection(DataUtiles.getJsonData("Data","TaxCardNum"),
                        DataUtiles.getJsonData("Data","Description"))
                .FillCapitalDataSection(DataUtiles.getJsonData("Data","Capital"))
                .FillBusinessLocationSection(DataUtiles.getJsonData("Data","NameOfSt"))
                .FillCertificateData().FillAttachmentSection().ConfirmRequest().SaveRequestNumberInJsonFile()
                .ReviewRequest().SendingRequestToCollect()
                .PayingFirstBill().Select_BusinessLicense().ReceivingTheCertificate();

    }

    @Test (priority = 9)
    public void CreateRecordEffect() throws InterruptedException {
//        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
//                ,DataUtiles.getJsonData("Data","Password"));
        new V01_CreateRecordEffectPage(driver).AddNewEffectToCreateNewRecord()
                .FillDataForRecordAndCreateEffect(DataUtiles.getJsonData("Data","BusinessLicenseRequestNumber"),
                        DataUtiles.getJsonData("Data","BusinessLicenseRequestNumber"),
                        DataUtiles.getJsonData("Data","BusinessLicenseRequestNumber")    )
                .ConfirmAndApproveTheRecord();
    }

    @Test (priority = 10)
    public void EditRequest () throws InterruptedException, AWTException {
//        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
//                ,DataUtiles.getJsonData("Data","Password"));
        new P05_EditRequestPage(driver).CreateNewEditRequest_Button().FillAndSaveDataOfApplicant(DataUtiles.getJsonData("Data","NewNationalIdPortal"),DataUtiles.getJsonData("Data","Number"))
                .ChoosingRecord(DataUtiles.getJsonData("Data","RecordNumber")).SelectSectionToEdit().FillCertificateData()
                .FillAttachmentSection().ConfirmRequest().SaveRequestNumberInJsonFile().ReviewRequest()
                .SendingRequestToCollect().PayingFirstBill().Select_EditRequest().ReceivingTheCertificate();
    }

    @Test (priority = 11)
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



    @Test (priority = 12)
    public void RemoveRequest() throws InterruptedException, AWTException {
//        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
//                ,DataUtiles.getJsonData("Data","Password"));
        new P06_RemoveRecordPage(driver).CreateNewRemoveRequest_Button().FillAndSaveDataOfApplicant(DataUtiles.getJsonData("Data","NewNationalIdPortal"),DataUtiles.getJsonData("Data","Number"))
                .ChoosingRecord(DataUtiles.getJsonData("Data","RecordNumber")).FillCertificateData()
                .FillAttachmentSection().ConfirmRequest().SaveRequestNumberInJsonFile()
                .ReviewRequest().SendingRequestToCollect().PayingFirstBill()
                .Select_RemoveRequest().ReceivingTheCertificate();

    }

    @Test (priority = 13)
    public void SourceCodeRequest() throws InterruptedException, AWTException {
//        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
//                ,DataUtiles.getJsonData("Data","Password"));
                new P02_Dashboard(driver).selectthesourcecode()
                .ClickOnAddNewRequest_Button()
                .EnterThePersonalIdentificationNumber(DataUtiles.getJsonData("Data","NewNationalIdPortal"))
                .clickonsavedmedicalprovider()
                .SearchOnRecordNumber(DataUtiles.getJsonData("Data","RecordNumber"))
                .FillTheCommericalData(DataUtiles.getJsonData("Data","First Name"),DataUtiles.getJsonData("Data","City"),
                        DataUtiles.getJsonData("Data","country"))
                .FillTheItemSection(DataUtiles.getJsonData("Data","desccatogery"),DataUtiles.getJsonData("Data","Trademarks"),DataUtiles.getJsonData("Data","Numberofparcels")
                ,DataUtiles.getJsonData("Data","netweight"),DataUtiles.getJsonData("Data","Totalweight"),DataUtiles.getJsonData("Data","Totalvalue"))
                .Certificatecount(DataUtiles.getJsonData("Data","CertificatesCount"))
                .Signatureauthentication()
                .Certificatelanguage()
                .FillAttachmentSection()
                .confirmrequest().ReviewRequest().SendingRequestToCollect().PayingFirstBill()
                .selectthesourcecode().ReceivingTheCertificate();
    }

    @Test (priority = 14)
    public void AdministrativeCertificateRequest() throws InterruptedException, AWTException {
//        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
//                ,DataUtiles.getJsonData("Data","Password"));
                new P02_Dashboard(driver).selectTheAdministrativeCertificate()
                .ClickOnAddNewRequest_Button()
                .EnterThePersonalIdentificationNumber(DataUtiles.getJsonData("Data","NewNationalIdPortal"))
                .ClickOnSavedMedicalProvider()
                .SearchOnRecordNumber(DataUtiles.getJsonData("Data","RecordNumber"))
                .EnterTheAdministrativeCertificateDetails("Test")
                .SendCeriticateTo("Office")
                .FillAttachmentSection()
                .ConfirmRequest().ReviewRequest().SendingRequestToCollect().PayingFirstBill()
                        .selectTheAdministrativeCertificate().ReceivingTheCertificate();
    }

    @Test (priority = 15)
    public void ServicesRequest() throws InterruptedException, AWTException {
//    new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
//            ,DataUtiles.getJsonData("Data","Password"));
    new P11_ServicesRequest(driver).CreateNewServicesRequest().FillAndSaveDataOfApplicant(DataUtiles.getJsonData("Data","NewNationalIdPortal"),DataUtiles.getJsonData("Data","Number"))
            .SelectServicesData().FillAttachmentSection().ConfirmRequest().SendingRequestToCollect()
            .PayingFirstBill().Select_ServicesRequest();

}
//    @Test (priority = 9)
//    public void DivisionEditRequest() throws InterruptedException, AWTException {
//        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
//                ,DataUtiles.getJsonData("Data","Password"));
//        new P12_DivisionEditRequestPage(driver).CreateNewDivisionEditRequest_Button().FillAndSaveDataOfApplicant(DataUtiles.getJsonData("Data","NegativeID"),DataUtiles.getJsonData("Data","Number"))
//                .ChoosingRecord(DataUtiles.getJsonData("Data","Record")).FillDivisionDataSection()
//                .FillAttachmentSection().ConfirmRequest().ReviewRequest()
//                .SendingRequestToCollect().PayingFirstBill().Select_DivisionRequest();
//
//    }

   @Test (priority = 17)
   public void NegativeCertificteRequest() throws InterruptedException, AWTException {
//        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
//                ,DataUtiles.getJsonData("Data","Password"));
    new P10_NegativeCertificteRequest(driver).CreateNewNegativeRequest_Button().FillAndSaveDataOfApplicant(DataUtiles.getJsonData("Data","NewNationalIdPortal"),DataUtiles.getJsonData("Data","Number"))
            .FillCertificateData(DataUtiles.getJsonData("Data","TestData"))
            .FillAttachmentSection().ConfirmRequest()
            .ReviewRequest().SendingRequestToCollect().PayingFirstBill()
            .Select_NegativeRequest().ReceivingTheCertificate();

}
    @Test (priority = 20)
    public void RemoveRecordEffect() throws InterruptedException {
        // الكيس اللي قبلها بتدخل بمستخدم البريد، فلازم نرجع للمستخدم الجديد
        new P02_Dashboard(driver).Logout().Login(DataUtiles.getJsonData("Data","NewUsernameBackOffice")
                ,DataUtiles.getJsonData("Data","NewPasswordBackOffice"));
        new V03_RemoveRecordEffectPage(driver).AddNewEffectToRemoveNewRecord()
                .FillDataForRecordAndCreateRemoveEffect(
                        DataUtiles.getJsonData("Data","RecordNumber"),
                        DataUtiles.getJsonData("Data","RemoveRequestNumber"),
                        DataUtiles.getJsonData("Data","RemoveRequestNumber"),
                        DataUtiles.getJsonData("Data","RemoveRequestNumber"))
                .ConfirmAndApproveTheRecord();
    }
    @Test (priority = 16)
    public void payingsub() throws InterruptedException, AWTException {
//        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
//            ,DataUtiles.getJsonData("Data","Password"));
        new P02_Dashboard(driver).SelectTheRecorsMangements()
               .SearchOfRecordNumber(DataUtiles.getJsonData("Data","RecordNumber"))
               .ClickOnEditRequest(DataUtiles.getJsonData("Data","RecordNumber"))
               .OutstandingSubscriptions(" ","229","2021")
               .OutstandingSubscriptions(" ","230","2022")
               .OutstandingSubscriptions(" ","231","2023")
               .ClickOnPayingSub()
               .ClickOnAddNewRequest_Button()
              .EnterThePersonalIdentificationNumber(DataUtiles.getJsonData("Data","NewNationalIdPortal"))
              .clickonsavedmedicalprovider()
                .SearchOnRecordNumber(DataUtiles.getJsonData("Data","RecordNumber"))
              .ClickOnFirstSubscriptionItem()
              .ConfirmRequest()
              .SendingRequestToCollect().PayingFirstBill()
              .selectThePaymentOfSubscriptions().ViewFirstRequest();
    }

    @Test (priority = 19)
    public void AddAdministrativeCertificateRequestByRequestMailPage() throws InterruptedException, AWTException {
        new P02_Dashboard(driver).Logout().Login(DataUtiles.getJsonData("Data","MailUserName")
                ,DataUtiles.getJsonData("Data","MailUserPassword"));
        new P02_Dashboard(driver).SelectRequestsAndCertificatesMailPage()
                .AddAdministrationRequest()
                .EnterThePersonalIdentificationNumber(DataUtiles.getJsonData("Data","NewNationalIdPortal"))
                .ClickOnSavedMedicalProvider()
                .SearchOnRecordNumber(DataUtiles.getJsonData("Data","RecordNumber"))
                .EnterTheAdministrativeCertificateDetails("Test")
                .SendCeriticateTo("Office")
                .FillAttachmentSection()
                .ConfirmRequest();
    }

    @Test (priority = 18)
    public void StatementFormRequest() throws IOException, InterruptedException {
//        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
//                ,DataUtiles.getJsonData("Data","Password"));
        new P02_Dashboard(driver).SelectStatementFormPage()
                .AddNewStatementForm()
                .SearchByCommercialNameAndSelectFirstRecord(DataUtiles.getJsonData("Data","TradeName"))
                .FillStatementDataAndConfirm(DataUtiles.getJsonData("Data","Full Name"),
                        DataUtiles.getJsonData("Data","TestData"),
                        DataUtiles.getJsonData("Data","TestData"))
                .PrintStatementForm("StatementFormPrint");
    }

    @Test (priority = 2)
    public void AddNewBackOfficeUser() throws InterruptedException {
        new P02_Dashboard(driver).Logout().Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
                ,DataUtiles.getJsonData("Data","Password")).SelectBackOfficeUsersPage()
                .PrepareUniqueUserData(DataUtiles.getJsonData("Data","NewBackOfficeUserFullName"),
                        DataUtiles.getJsonData("Data","NewBackOfficeUserBaseName"),
                        DataUtiles.getJsonData("Data","NewUsersPassword"))
                .ClickOnAddNewUser()
                .FillDataOfNewUser(DataUtiles.getJsonData("Data","NewBackOfficeUserRole"),
                        DataUtiles.getJsonData("Data","NewBranchName"))
                .SaveUserAndDataInJsonFile()
                .SearchByNameAndValidateUser();
    }

    // يوزر بريد الطلبات والشهادات (آلية العمل توزيع إلكتروني) - بيستخدم في اي كيس فيها بريد الطلبات والشهادات
    @Test (priority = 3)
    public void AddNewMailUser() throws InterruptedException {
        new P02_Dashboard(driver).Logout().Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
                ,DataUtiles.getJsonData("Data","Password")).SelectBackOfficeUsersPage()
                .PrepareUniqueUserData(DataUtiles.getJsonData("Data","MailUserFullName"),
                        DataUtiles.getJsonData("Data","MailUserBaseName"),
                        DataUtiles.getJsonData("Data","NewUsersPassword"),
                        "MailUserName","MailUserPassword")
                .ClickOnAddNewUser()
                .FillDataOfNewUser(DataUtiles.getJsonData("Data","NewBackOfficeUserRole"),
                        DataUtiles.getJsonData("Data","NewBranchName"),
                        DataUtiles.getJsonData("Data","MailUserOperation"))
                .SaveUserAndDataInJsonFile()
                .SearchByNameAndValidateUser();
    }

    // يوزر الشباك (آلية العمل خدمة جمهور) - بيستخدم في اي كيس فيها دخول بالشباك
    @Test (priority = 4)
    public void AddNewWindowUser() throws InterruptedException {
        new P02_Dashboard(driver).Logout().Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
                ,DataUtiles.getJsonData("Data","Password")).SelectBackOfficeUsersPage()
                .PrepareUniqueUserData(DataUtiles.getJsonData("Data","WindowUserFullName"),
                        DataUtiles.getJsonData("Data","WindowUserBaseName"),
                        DataUtiles.getJsonData("Data","NewUsersPassword"),
                        "WindowUserName","WindowUserPassword")
                .ClickOnAddNewUser()
                .FillDataOfNewUser(DataUtiles.getJsonData("Data","NewBackOfficeUserRole"),
                        DataUtiles.getJsonData("Data","NewBranchName"),
                        DataUtiles.getJsonData("Data","WindowUserOperation"))
                .SaveUserAndDataInJsonFile()
                .SaveUserBranchInJsonFile("WindowUserBranch")
                .SearchByNameAndValidateUser();
    }

    @Test (priority = 5)
    public void AddNewPortalUser() throws InterruptedException {
        new P02_Dashboard(driver).Logout().Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
                ,DataUtiles.getJsonData("Data","Password")).SelectPortalUsersPage()
                .PrepareUniqueUserData(DataUtiles.getJsonData("Data","NewPortalUserFullName"))
                .ClickOnAddNewUser()
                .FillDataOfNewUser(DataUtiles.getJsonData("Data","Number"))
                .SaveUserAndDataInJsonFile()
                .SearchByNameAndValidateUser()
                .ConfirmUser();
    }

    @Test (priority = 1)
    public void AddNewBranch() throws InterruptedException {
        new P02_Dashboard(driver).Logout().Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
                ,DataUtiles.getJsonData("Data","Password")).SelectBranchesPage()
                .PrepareUniqueBranchData(DataUtiles.getJsonData("Data","NewBranchBaseName"))
                .ClickOnAddNewBranch()
                .FillCodeAndNames()
                .FillWorkData(DataUtiles.getJsonData("Data","NewBranchStartTime"),
                        DataUtiles.getJsonData("Data","NewBranchEndTime"),
                        DataUtiles.getJsonData("Data","NewBranchPaper"),
                        DataUtiles.getJsonData("Data","NewBranchMaxRequests"),
                        DataUtiles.getJsonData("Data","NewBranchAlertMinutes"),
                        DataUtiles.getJsonData("Data","NewBranchReleaseMinutes"))
                .SelectRequestTypes(DataUtiles.getJsonData("Data","NewBranchRequestTypes"))
                .SelectWorkDays(DataUtiles.getJsonData("Data","NewBranchWorkDays"))
                .SaveBranchAndDataInJsonFile()
                .SearchByNameAndValidateBranch();
    }

    @Test (priority = 7)
    public void SetupElcBranch() throws InterruptedException {
        new P02_Dashboard(driver).SelectElcBranchesPage()
                .SearchOnBranchAndOpenEdit(DataUtiles.getJsonData("Data","NewBranchName"))
                .FillBranchSettings()
                .AddWindowsWithPeriods(DataUtiles.getJsonData("Data","ElcWindows"))
                .SetupAgendaFromFirstToLastDay()
                .ValidateBranchWindowsAndAgenda(DataUtiles.getJsonData("Data","NewBranchName"),
                        DataUtiles.getJsonData("Data","ElcWindowsCount"));
    }

    @Test (priority = 6, description = "تسجيل الدخول بالمستخدم الجديد في الباك اوفيس")
    public void LoginWithNewBackOfficeUser() throws InterruptedException {
        new P02_Dashboard(driver).Logout().LoginWithNewBackOfficeUser(DataUtiles.getJsonData("Data","NewUsernameBackOffice"),
                DataUtiles.getJsonData("Data","NewPasswordBackOffice"));
    }


        @AfterClass
    public void quit () {
        driver.quit();
    }
}




