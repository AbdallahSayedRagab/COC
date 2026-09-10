package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class K02_SelfServicePage {
    public K02_SelfServicePage(WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By TodayReservation_Button = By.xpath("//button[text()=' حجز اليوم ']");
    private final By NormalReservationType = By.xpath("//button[text()=' حجز عادي ']");


    private final By Applicant_Name_Field = By.xpath("//label[normalize-space(text())='اسم مقدم الطلب']/following-sibling::*[1]");
    private final By NumberOfApplicant_Field = By.xpath("//label[normalize-space(text())='رقم هاتف مقدم الطلب']/following-sibling::*[1]");
//    private final By NationalityOfApplicant_Field = By.xpath("//label[normalize-space(text())='جنسية مقدم الطلب']/following-sibling::*[1]");
//    private final By EgyptianNation = By.xpath("//div[@role='option']//span[normalize-space(text())='مصري']");
//    private final By ApplicantIDType_Field = By.xpath("//label[normalize-space(text())='نوع تحقيق شخصية مقدم الطلب']/following-sibling::*[1]");
    private final By ApplicantID_Field = By.xpath("//label[normalize-space(text())='رقم تحقيق شخصية مقدم الطلب']/following-sibling::*[1]");
    private final By ConfirmTheReservationButton = By.xpath("//button[text()=' تأكيد الحجز ']");
//    private final By ApplicantAttribute_Field = By.xpath("//label[normalize-space(text())='صفة مقدم الطلب']/following-sibling::*[1]");
//    private final By OwnerAttribute = By.xpath("//div[@role='option']//span[normalize-space(text())='صاحب الشأن']");

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




    public K02_SelfServicePage CreateTodayReservation (String Name , String phoneNumber , String ID) throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,TodayReservation_Button,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,NormalReservationType,Loading_Circle);
        Utility.WatingAndSENDKEYS(driver,Applicant_Name_Field,Loading_Circle,Name);
        Utility.WatingAndSENDKEYS(driver,NumberOfApplicant_Field,Loading_Circle,phoneNumber);
        Utility.WatingAndSENDKEYS(driver,ApplicantID_Field,Loading_Circle,ID);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ConfirmTheReservationButton,Loading_Circle);        Utility.SelectFirstEnableDayinCalender(driver,Loading_Circle);

        return this;
    }
}
