package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P13_RequestsAndCertificatesPage {

    public P13_RequestsAndCertificatesPage (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By FilterListButton = By.xpath("//span[text()='تصفية']");
    private final By ReservationNumberField = By.xpath("//label[normalize-space(text())='رقم الحجز']/following-sibling::*[1]");
    private final By AddNewRequest_Button = By.xpath("//img[@src=\"../../../assets/images/circle-plus-white.svg\"]");
    private final By FilterButton = By.xpath("//Button[@type=\"submit\"]");
    private final By CompleteReservationButton = By.xpath("//a[text()=' حجز موعد استكمال ']");
    private final By Loading_Circle = By.xpath("//mat-spinner[@mode=\"indeterminate\"]");
    private final By PersonalIdentificationNumber_Field = By.xpath("//input-control[@label=\"رقم تحقيق الشخصية\"]/input");
    private final By SaveApplicantData_Button = By.xpath("//button[contains(text(),'حفظ جميع بيانات مقدم الطلب')]");
    private final By ServicesList = By.xpath("//ng-select[@id=\"elctCertificateTypeId\"]");
    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");
    private final By AddServiceButton = By.xpath("//button[@class=\"add-btn\"]");

    private final By AddAttachmentButton = By.xpath("//button[@title=\"إضافة ملف جديد\"]");
    private final By ConfirmRequestButton = By.xpath("//span[contains(text(),\"تاكيد الطلب\")]");
    private final By AcceptRequestButtonOfPopUP = By.xpath("//button[contains(text(),\" اقبل الطلب\")]");
    private final By EditButton = By.xpath("(//li[.//*[local-name()='svg' and contains(@matTooltip, 'تعديل الطلب')]])[1]");
    private final By PullTheRequest = By.xpath("//label[contains(normalize-space(), 'اسحب الطلب')]//input[@type='checkbox']");
    private final By AcceptRequestButton = By.xpath("//span[contains(text(),\"أقبل الطلب\")]");
    private final By ViewButton = By.xpath("(//li[.//*[local-name()='svg' and contains(@class,'view-icon')]])[1]");
    private final By SendToCollectButton = By.xpath("//span[contains(normalize-space(), 'أرسال للتحصيل')]");
    private final By SendToCollectButtonPouUp = By.xpath("//button[contains(normalize-space(), 'ارسل الطلب للتحصيل')]");
//    private final By RecieveCertificateButton = By.xpath("//button[contains(text(), \"تم التسليم\")]");
//    private final By RequestNumber = By.xpath("(//td/a[@class=\"ng-star-inserted\"])[1]");
private final By ReservationTypeList = By.xpath("//ng-select[@formcontrolname=\"elctReservationTypeId\"]");
    private final By NormalReservationType = By.xpath("//div[@role='option']//span[normalize-space(text())='حجز عادي']");
    private final By VIPReservationType = By.xpath("//div[@role='option']//span[normalize-space(text())='حجز عاجل']");
    private final By ReservationCalendar = By.xpath("//label[normalize-space(text())='تاريخ الحجز']/following-sibling::*[1]");
    private final By PeriodsList = By.xpath("//label[normalize-space(text())='أختر الفترة المناسبة']/following-sibling::*[1]");
    private final By SubmitButton = By.xpath("//a[text()=\"تأكيد\"]");




    public P13_RequestsAndCertificatesPage CompleteReservationForLastReservation(String NumOfReservation) throws InterruptedException {

        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,FilterListButton,Loading_Circle);
        Utility.WatingAndSENDKEYS(driver,ReservationNumberField,Loading_Circle,NumOfReservation);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,FilterButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,EditButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,CompleteReservationButton,Loading_Circle);

        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ReservationTypeList,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,NormalReservationType,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ReservationCalendar,Loading_Circle);
        Utility.SelectFirstEnableDayinCalender(driver,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PeriodsList,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,Option1ForAnyList,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SubmitButton,Loading_Circle);


        return this;
    }





}
