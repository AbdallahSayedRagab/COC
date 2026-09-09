package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

}
