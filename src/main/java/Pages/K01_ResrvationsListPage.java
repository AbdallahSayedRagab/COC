package Pages;

import Utilities.JsonUtil;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class K01_ResrvationsListPage {
    public K01_ResrvationsListPage(WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By Add_Reservation_Button = By.xpath("//button[contains (text(), 'أضافة حجز ')]");
    private final By Applicant_Name_Field = By.xpath("//label[normalize-space(text())='اسم مقدم الطلب']/following-sibling::*[1]");
    private final By NumberOfApplicant_Field = By.xpath("//label[normalize-space(text())='رقم هاتف مقدم الطلب']/following-sibling::*[1]");
    private final By NationalityOfApplicant_Field = By.xpath("//label[normalize-space(text())='جنسية مقدم الطلب']/following-sibling::*[1]");
    private final By EgyptianNation = By.xpath("//div[@role='option']//span[normalize-space(text())='مصري']");
    private final By ApplicantIDType_Field = By.xpath("//label[normalize-space(text())='نوع تحقيق شخصية مقدم الطلب']/following-sibling::*[1]");
    private final By ApplicantID_Field = By.xpath("//label[normalize-space(text())='رقم تحقيق شخصية مقدم الطلب']/following-sibling::*[1]");
    private final By ApplicantAttribute_Field = By.xpath("//label[normalize-space(text())='صفة مقدم الطلب']/following-sibling::*[1]");
    private final By OwnerAttribute = By.xpath("//div[@role='option']//span[normalize-space(text())='صاحب الشأن']");
    private final By RequestType_Field = By.xpath("//label[normalize-space(text())='نوع الطلب']/following-sibling::*[1]");
    private final By AdministrationRequest = By.xpath("//div[@role='option']//span[normalize-space(text())='طلب اصدار شهادة إدارية']");
    private final By CertificateType_Field = By.xpath("//label[normalize-space(text())='نوع الشهادة']/following-sibling::*[1]");
    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");
    private final By Loading_Circle = By.xpath("//mat-spinner[@mode=\"indeterminate\"]");
    private final By OptionsButton = By.xpath("(//button[@id=\"optionBtn\"])[1]");
    private final By ConfirmAttendanceButtonForFirstReserv = By.xpath("(//button[text()=' تأكيد الحضور '])[last()]");
    private final By ConfirmAttendanceButtonOnPopUp = By.xpath("//button[text()='تأكيد الحضور']");
    private final By PrintButtonForFirstReserv = By.xpath("(//button[text()=' طباعة '])[last()]");
    private final By PrintQueueDaraAfterAttending = By.xpath("//button[text()='طباعة']");
    private final By ReschedulingButtonForFirstReserv = By.xpath("(//button[text()=' اعادة جدولة الحجز '])[last()]");


    // locators المستفيد
    private final By BeneficiaryName_Field = By.xpath("//label[normalize-space(text())='اسم المُستفيد']/following-sibling::*[1]");
    private final By BeneficiaryNationality_Field = By.xpath("//label[normalize-space(text())='جنسية المُستفيد']/following-sibling::*[1]");
    private final By BeneficiaryIDType_Field = By.xpath("//label[normalize-space(text())='نوع تحقيق شخصية المُستفيد']/following-sibling::*[1]");
    private final By BeneficiaryID_Field = By.xpath("//label[normalize-space(text())='رقم تحقيق شخصية المستفيد']/following-sibling::*[1]");

    // بيانات الحجز
    private final By ReservationTypeList = By.id("elctReservationTypeId");
    private final By NormalReservationType = By.xpath("//div[@role='option']//span[normalize-space(text())='حجز عادي']");
    private final By VIPReservationType = By.xpath("//div[@role='option']//span[normalize-space(text())='حجز عاجل']");
    private final By ReservationCalendar = By.xpath("//label[normalize-space(text())='تاريخ الحجز']/following-sibling::*[1]");
    private final By PeriodsList = By.xpath("//label[normalize-space(text())='أختر الفترة المناسبة']/following-sibling::*[1]");

    private final By SubmitButton = By.xpath("//button[@type=\"submit\"]");
    private final By SubmitReschedulingButton = By.xpath("//button[text()='تاكيد إعادة الجدولة ']");
    private final By ReservationNumber  = By.xpath("(//td)[1]");




    public K01_ResrvationsListPage CreateNormalReservation(String Name , String phoneNumber , String ID) throws InterruptedException {
       Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,Add_Reservation_Button,Loading_Circle);
        Utility.WatingAndSENDKEYS(driver,Applicant_Name_Field,Loading_Circle,Name);
        Utility.WatingAndSENDKEYS(driver,NumberOfApplicant_Field,Loading_Circle,phoneNumber);
        Utility.CLICKONELEMENTS(driver,NationalityOfApplicant_Field);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,EgyptianNation,Loading_Circle);
        Utility.WatingAndSENDKEYS(driver,ApplicantID_Field,Loading_Circle,ID);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ApplicantAttribute_Field,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,OwnerAttribute,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestType_Field,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AdministrationRequest,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,CertificateType_Field,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,Option1ForAnyList,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ReservationTypeList,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,NormalReservationType,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ReservationCalendar,Loading_Circle);
        Utility.SelectFirstEnableDayinCalender(driver,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PeriodsList,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,Option1ForAnyList,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SubmitButton,Loading_Circle);

        return this;
    }

    public P04_InvoicesPage CreateVIPReservation(String Name , String phoneNumber , String ID) throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,Add_Reservation_Button,Loading_Circle);
        Utility.WatingAndSENDKEYS(driver,Applicant_Name_Field,Loading_Circle,Name);
        Utility.WatingAndSENDKEYS(driver,NumberOfApplicant_Field,Loading_Circle,phoneNumber);
        Utility.CLICKONELEMENTS(driver,NationalityOfApplicant_Field);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,EgyptianNation,Loading_Circle);
        Utility.WatingAndSENDKEYS(driver,ApplicantID_Field,Loading_Circle,ID);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ApplicantAttribute_Field,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,OwnerAttribute,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestType_Field,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AdministrationRequest,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,CertificateType_Field,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,Option1ForAnyList,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ReservationTypeList,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,VIPReservationType,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ReservationCalendar,Loading_Circle);
        Utility.SelectFirstEnableDayinCalender(driver,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PeriodsList,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,Option1ForAnyList,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SubmitButton,Loading_Circle);

        return new P04_InvoicesPage(driver);
    }

    public K01_ResrvationsListPage ConfirmFirstReservationInTable() throws InterruptedException {

        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,OptionsButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ConfirmAttendanceButtonForFirstReserv,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ConfirmAttendanceButtonOnPopUp,Loading_Circle);
        return this;
    }
    public K01_ResrvationsListPage PrintReservationData (String ScreenShotName) throws InterruptedException, IOException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,OptionsButton,Loading_Circle);
        Utility.PrintAndScreenShot(driver,PrintButtonForFirstReserv,Loading_Circle,ScreenShotName);
        return this;
    }

    public K01_ResrvationsListPage PrintQueueData (String ScreenShotName) throws InterruptedException, IOException {

//        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,OptionsButton,Loading_Circle);
//        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ConfirmAttendanceButtonForFirstReserv,Loading_Circle);
//        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ConfirmAttendanceButtonOnPopUp,Loading_Circle);
//        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PrintQueueDaraAfterAttending,Loading_Circle);
        Utility.PrintAndScreenShot(driver,PrintQueueDaraAfterAttending,Loading_Circle,ScreenShotName);
        return this;
    }

    public K01_ResrvationsListPage ReschedulingReservation () throws InterruptedException, IOException {

        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,OptionsButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ReschedulingButtonForFirstReserv,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ReservationTypeList,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,VIPReservationType,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ReservationCalendar,Loading_Circle);
        Utility.SelectFirstEnableDayinCalender(driver,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PeriodsList,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,Option1ForAnyList,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SubmitReschedulingButton,Loading_Circle);
        return this;
    }
    public P02_Dashboard SaveReservationNumberInJsonFile() {
        String NumberOfReservation = Utility.GETTEXT(driver,ReservationNumber,Loading_Circle);
        JsonUtil.writeValue("ReservationNumber", NumberOfReservation);
        JsonUtil.writeValue("ReservationNumber", NumberOfReservation);
        return new P02_Dashboard(driver);
    }


}