package Pages.PortalPages;

import Utilities.JsonUtil;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class P17_PortalReservationPage {
    public P17_PortalReservationPage(WebDriver driver) { this.driver = driver;}
    private WebDriver driver;

    private final By Loading_Circle = By.xpath("//div[contains(@class,'ngx-spinner-overlay')]");
    private final By NextStepButton = By.xpath("//button[text()='الخطوة التالية']");
    private final By NumberOfApplicant_Field = By.xpath("//label[normalize-space(text())='رقم هاتف مقدم الطلب']/following-sibling::*[1]");
    private final By ApplicantAttribute_Field = By.xpath("//label[normalize-space(text())='صفة مقدم الطلب']/following-sibling::*[1]");
    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");

    // locators المستفيد
    private final By BeneficiaryName_Field = By.xpath("//label[normalize-space(text())='اسم المُستفيد']/following-sibling::*[1]");
    private final By BeneficiaryNationality_Field = By.xpath("//label[normalize-space(text())='جنسية المُستفيد']/following-sibling::*[1]");
    private final By EgyptianNation = By.xpath("//div[@role='option']//span[normalize-space(text())='مصري']");
    private final By BeneficiaryID_Field = By.xpath("//label[normalize-space(text())='رقم تحقيق شخصية المستفيد']/following-sibling::*[1]");

    // بيانات الحجز
    private final By ReservationTypeList = By.id("elctReservationTypeId");
    private final By ReservationCalendar = By.xpath("//label[normalize-space(text())='تاريخ الحجز']/following-sibling::*[1]");
    private final By FirstAvailableDay = By.xpath("(//table[@class='days weeks']//td[@role='gridcell']/span[not(contains(@class,'disabled')) and not(contains(@class,'is-other-month'))])[1]");
    private final By PeriodsList = By.id("elctAgendaDatePeriodId");
    private final By ConfirmReservationButton = By.xpath("//button[text()='تأكيد الحجز']");
    private final By ReservationNumber = By.xpath("//li[span[contains(text(),'رقم الحجز')]] | //span[contains(@class,'col-md-7')][span[contains(text(),'رقم الحجز')]]");
    private final By PrintReservationButton = By.xpath("//button[text()='طباعة']");

    // حجز عادي من ايقونة أحجز ميعاد
    private final By BookAppointmentIcon = By.xpath("//li[@ngbtooltip='أحجز ميعاد']/a");
    private final By RequestType_Field = By.id("requestTypeId");
    private final By CertificateType_Field = By.xpath("//label[contains(normalize-space(text()),'الشهادة / الخدمة المطلوبة')]/following-sibling::*[1]//ng-select");
    private final By Branch_Field = By.id("branchId");
    private final By NormalReservationType = By.xpath("//div[@role='option']//span[normalize-space(text())='حجز عادي']");
    private final By AdministrationRequest = By.xpath("//div[@role='option']//span[normalize-space(text())='طلب اصدار شهادة إدارية']");

    // بيانات مقدم الطلب (بتظهر لو مش عامل لوج ان)
    private final By Applicant_Name_Field = By.xpath("//label[normalize-space(text())='اسم مقدم الطلب']/following-sibling::*[1]");
    private final By NationalityOfApplicant_Field = By.xpath("//label[normalize-space(text())='جنسية مقدم الطلب']/following-sibling::*[1]");
    private final By ApplicantID_Field = By.xpath("//label[normalize-space(text())='رقم تحقيق شخصية مقدم الطلب']/following-sibling::*[1]");

    private void ClearAndTypePhoneNumber (String phoneNumber) {
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.visibilityOfElementLocated(NumberOfApplicant_Field));
        driver.findElement(NumberOfApplicant_Field).clear();
        Utility.WaitUntilReady_And_SENDKEYS(driver,NumberOfApplicant_Field,Loading_Circle,phoneNumber);
    }

    public P17_PortalReservationPage CreateNormalReservationWithoutLogin (String Name , String phoneNumber , String BeneficiaryName , String ID , String Branch) throws InterruptedException {
        Utility.WaitUntilReady_And_CLICK(driver,BookAppointmentIcon,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,NextStepButton,Loading_Circle);
        Utility.WaitUntilReady_And_SENDKEYS(driver,Applicant_Name_Field,Loading_Circle,Name);
        ClearAndTypePhoneNumber(phoneNumber);
        Utility.WaitUntilReady_And_CLICK(driver,NationalityOfApplicant_Field,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,EgyptianNation,Loading_Circle);
        Utility.WaitUntilReady_And_SENDKEYS(driver,ApplicantID_Field,Loading_Circle,ID);
        FillBeneficiaryAndNormalReservationData(BeneficiaryName,ID,Branch);
        return this;
    }

    public P17_PortalReservationPage CreateNormalReservation (String phoneNumber , String BeneficiaryName , String ID , String Branch) throws InterruptedException {
        Utility.WaitUntilReady_And_CLICK(driver,BookAppointmentIcon,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,NextStepButton,Loading_Circle);
        ClearAndTypePhoneNumber(phoneNumber);
        FillBeneficiaryAndNormalReservationData(BeneficiaryName,ID,Branch);
        return this;
    }

    private void FillBeneficiaryAndNormalReservationData (String BeneficiaryName , String ID , String Branch) throws InterruptedException {
        By BranchChoice = By.xpath("//div[@role='option'][normalize-space()='" + Branch + "']");
        Utility.WaitUntilReady_And_CLICK(driver,ApplicantAttribute_Field,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList,Loading_Circle);
        Utility.WaitUntilReady_And_SENDKEYS(driver,BeneficiaryName_Field,Loading_Circle,BeneficiaryName);
        Utility.WaitUntilReady_And_CLICK(driver,BeneficiaryNationality_Field,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,EgyptianNation,Loading_Circle);
        Utility.WaitUntilReady_And_SENDKEYS(driver,BeneficiaryID_Field,Loading_Circle,ID);
        Utility.WaitUntilReady_And_CLICK(driver,RequestType_Field,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,AdministrationRequest,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,CertificateType_Field,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,Branch_Field,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,BranchChoice,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,NextStepButton,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,ReservationTypeList,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,NormalReservationType,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,ReservationCalendar,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,FirstAvailableDay,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,PeriodsList,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,ConfirmReservationButton,Loading_Circle);
    }

    public P17_PortalReservationPage ConfirmReservation (String phoneNumber , String BeneficiaryName , String ID) throws InterruptedException {
        Utility.WaitUntilReady_And_CLICK(driver,NextStepButton,Loading_Circle);
        ClearAndTypePhoneNumber(phoneNumber);
        Utility.WaitUntilReady_And_CLICK(driver,ApplicantAttribute_Field,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList,Loading_Circle);
        Utility.WaitUntilReady_And_SENDKEYS(driver,BeneficiaryName_Field,Loading_Circle,BeneficiaryName);
        Utility.WaitUntilReady_And_CLICK(driver,BeneficiaryNationality_Field,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,EgyptianNation,Loading_Circle);
        Utility.WaitUntilReady_And_SENDKEYS(driver,BeneficiaryID_Field,Loading_Circle,ID);
        Utility.WaitUntilReady_And_CLICK(driver,NextStepButton,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,ReservationTypeList,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,ReservationCalendar,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,FirstAvailableDay,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,PeriodsList,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,ConfirmReservationButton,Loading_Circle);
        return this;
    }

    public P17_PortalReservationPage SaveReservationNumberInJsonFile() {
        String NumberOfReservation = new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(d -> d.findElements(ReservationNumber)
                .stream().filter(WebElement::isDisplayed).findFirst().orElse(null)).getText().replaceAll("[^0-9-]", "");
        JsonUtil.writeValue("PortalReservationNumber", NumberOfReservation);
        return this;
    }

    public P17_PortalReservationPage PrintReservationData (String ScreenShotName) throws IOException {
        Utility.PrintAndScreenShot(driver,PrintReservationButton,Loading_Circle,ScreenShotName);
        return this;
    }

}
