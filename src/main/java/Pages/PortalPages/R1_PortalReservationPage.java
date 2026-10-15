package Pages.PortalPages;

import Utilities.JsonUtil;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class R1_PortalReservationPage {
    public R1_PortalReservationPage(WebDriver driver) { this.driver = driver;}
    private WebDriver driver;

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
    // حجز الخدمة الاختيارية: نوع الطلب والشهادة ساعات بيجوا فاضيين
    private final By SpecialServiceRequestType = By.xpath("//div[@role='option']//span[normalize-space(text())='طلب خدمة اختيارية']");
    private final By SelectedCertificateType = By.xpath("//label[contains(normalize-space(text()),'الشهادة / الخدمة المطلوبة')]/following-sibling::*[1]//ng-select//span[contains(@class,'ng-value-label') and normalize-space(text())!='']");

    // بيانات مقدم الطلب (بتظهر لو مش عامل لوج ان)
    private final By Applicant_Name_Field = By.xpath("//label[normalize-space(text())='اسم مقدم الطلب']/following-sibling::*[1]");
    private final By NationalityOfApplicant_Field = By.xpath("//label[normalize-space(text())='جنسية مقدم الطلب']/following-sibling::*[1]");
    private final By ApplicantID_Field = By.xpath("//label[normalize-space(text())='رقم تحقيق شخصية مقدم الطلب']/following-sibling::*[1]");

    private void ClearAndTypePhoneNumber (String phoneNumber) {
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.visibilityOfElementLocated(NumberOfApplicant_Field));
        driver.findElement(NumberOfApplicant_Field).clear();
        Utility.WaitUntilReady_And_SENDKEYS(driver,NumberOfApplicant_Field,phoneNumber);
    }

    public R1_PortalReservationPage CreateNormalReservationWithoutLogin (String Name , String phoneNumber , String BeneficiaryName , String ID , String Branch) throws InterruptedException {
        Utility.WaitUntilReady_And_CLICK(driver,BookAppointmentIcon);
        Utility.WaitUntilReady_And_CLICK(driver,NextStepButton);
        Utility.WaitUntilReady_And_SENDKEYS(driver,Applicant_Name_Field,Name);
        ClearAndTypePhoneNumber(phoneNumber);
        Utility.WaitUntilReady_And_CLICK(driver,NationalityOfApplicant_Field);
        Utility.WaitUntilReady_And_CLICK(driver,EgyptianNation);
        Utility.WaitUntilReady_And_SENDKEYS(driver,ApplicantID_Field,ID);
        FillBeneficiaryAndNormalReservationData(BeneficiaryName,ID,Branch);
        return this;
    }

    public R1_PortalReservationPage CreateNormalReservation (String phoneNumber , String BeneficiaryName , String ID , String Branch) throws InterruptedException {
        Utility.WaitUntilReady_And_CLICK(driver,BookAppointmentIcon);
        Utility.WaitUntilReady_And_CLICK(driver,NextStepButton);
        ClearAndTypePhoneNumber(phoneNumber);
        FillBeneficiaryAndNormalReservationData(BeneficiaryName,ID,Branch);
        return this;
    }

    private void FillBeneficiaryAndNormalReservationData (String BeneficiaryName , String ID , String Branch) throws InterruptedException {
        By BranchChoice = By.xpath("//div[@role='option'][normalize-space()='" + Branch + "']");
        Utility.WaitUntilReady_And_CLICK(driver,ApplicantAttribute_Field);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        Utility.WaitUntilReady_And_SENDKEYS(driver,BeneficiaryName_Field,BeneficiaryName);
        Utility.WaitUntilReady_And_CLICK(driver,BeneficiaryNationality_Field);
        Utility.WaitUntilReady_And_CLICK(driver,EgyptianNation);
        Utility.WaitUntilReady_And_SENDKEYS(driver,BeneficiaryID_Field,ID);
        Utility.WaitUntilReady_And_CLICK(driver,RequestType_Field);
        Utility.WaitUntilReady_And_CLICK(driver,AdministrationRequest);
        Utility.WaitUntilReady_And_CLICK(driver,CertificateType_Field);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        Utility.WaitUntilReady_And_CLICK(driver,Branch_Field);
        Utility.WaitUntilReady_And_CLICK(driver,BranchChoice);
        Utility.WaitUntilReady_And_CLICK(driver,NextStepButton);
        Utility.WaitUntilReady_And_CLICK(driver,ReservationTypeList);
        Utility.WaitUntilReady_And_CLICK(driver,NormalReservationType);
        Utility.WaitUntilReady_And_CLICK(driver,ReservationCalendar);
        Utility.WaitUntilReady_And_CLICK(driver,FirstAvailableDay);
        Utility.WaitUntilReady_And_CLICK(driver,PeriodsList);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        Utility.WaitUntilReady_And_CLICK(driver,ConfirmReservationButton);
    }

    public R1_PortalReservationPage ConfirmReservation (String phoneNumber , String BeneficiaryName , String ID) throws InterruptedException {
        Utility.WaitUntilReady_And_CLICK(driver,NextStepButton);
        ClearAndTypePhoneNumber(phoneNumber);
        Utility.WaitUntilReady_And_CLICK(driver,ApplicantAttribute_Field);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        Utility.WaitUntilReady_And_SENDKEYS(driver,BeneficiaryName_Field,BeneficiaryName);
        Utility.WaitUntilReady_And_CLICK(driver,BeneficiaryNationality_Field);
        Utility.WaitUntilReady_And_CLICK(driver,EgyptianNation);
        Utility.WaitUntilReady_And_SENDKEYS(driver,BeneficiaryID_Field,ID);
        // نوع الطلب: طلب خدمة اختيارية
        Utility.WaitUntilReady_And_CLICK(driver,RequestType_Field);
        Utility.WaitUntilReady_And_CLICK(driver,SpecialServiceRequestType);
        // بعده الشهادة / الخدمة المطلوبة: الشهادات بتحمل بعد نوع الطلب (والسيرفر ساعات بيتأخر)
        // فبنفتح الليستة وناخد اول اختيار لو ظهر ولو لسه مظهرش بنعيد المحاولة
        // والاختيار لازم يفضل ثابت مرتين ورا بعض لان الليستة لما تحمل بتمسح الاختيار القديم
        int[] stableChecks = {0};
        new WebDriverWait(driver, Duration.ofSeconds(120), Duration.ofMillis(2000))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(ElementNotInteractableException.class)
                .until(d -> {
                    if (!d.findElements(SelectedCertificateType).isEmpty()) return ++stableChecks[0] >= 2;
                    stableChecks[0] = 0;
                    if (d.findElements(Utility.Loader).stream().anyMatch(WebElement::isDisplayed)) return false;
                    if (d.findElements(Option1ForAnyList).isEmpty()) {
                        d.findElement(CertificateType_Field).click();
                        return false;
                    }
                    d.findElement(Option1ForAnyList).click();
                    return false;
                });
        Utility.WaitUntilReady_And_CLICK(driver,NextStepButton);
        Utility.WaitUntilReady_And_CLICK(driver,ReservationTypeList);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        Utility.WaitUntilReady_And_CLICK(driver,ReservationCalendar);
        Utility.WaitUntilReady_And_CLICK(driver,FirstAvailableDay);
        Utility.WaitUntilReady_And_CLICK(driver,PeriodsList);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        Utility.WaitUntilReady_And_CLICK(driver,ConfirmReservationButton);
        return this;
    }

    public R1_PortalReservationPage SaveReservationNumberInJsonFile() {
        String NumberOfReservation = new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(d -> d.findElements(ReservationNumber)
                .stream().filter(WebElement::isDisplayed).findFirst().orElse(null)).getText().replaceAll("[^0-9-]", "");
        JsonUtil.writeValue("PortalReservationNumber", NumberOfReservation);
        return this;
    }

    public R1_PortalReservationPage PrintReservationData (String ScreenShotName) throws IOException {
        Utility.PrintAndScreenShot(driver,PrintReservationButton,ScreenShotName);
        return this;
    }

}
