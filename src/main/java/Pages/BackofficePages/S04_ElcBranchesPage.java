package Pages.BackofficePages;

import Utilities.DataUtiles;
import Utilities.Utility;
import Utilities.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class S04_ElcBranchesPage {
    public S04_ElcBranchesPage(WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    // شاشة الحجز الالكتروني للافرع
    private final By SearchField = By.xpath("//input[contains(@placeholder,'ابحث')]");

    // اعدادات الفرع
    private final String[] SettingsFields = {
            "timeSlotDuration",
            "numberOfTransactionsDuringTheSingleWindowFlowTime",
            "allowanceDuration",
            "lateCustomerGracePeriod",
            "numberOfActiveReservationsAvailableToApplicantOnSameCategory",
            "numberOfActiveReservationsAvailableToApplicantOnAllCategories",
            "numberOfAvailableBeneficiaryReservations",
            "numberOfReservationsAvailableToBeneficiariesInAllCategories",
            "maximumLimitForReschedulingUnpaidReservations",
            "maximumLimitForReschedulingpaidReservations",
            "maxRequestsOnReservation",
            "maximumUnPaidReservationCompletionLimit",
            "maximumPaidReservationCompletionLimit",
            "maxNumberOfUnexecutedReservationsIn30Days"
    };
    private final String[] SettingsSwitches = {
            "allowApplicantToMakeMoreThanOneReservationOnTheSameDay",
            "allowAutomaticAgendaCreation",
            "allowBeneficiaryToMakeMoreThanOneReservationOnTheSameDay"
    };
    private final By SaveSettings_Button = By.xpath("//form//button[normalize-space()='حفظ']");
    private final By Saved_Message = By.xpath("//*[contains(text(),'تمت عملية')]");
    private final By OK_Button = By.xpath("//button[contains(@class,'swal2-confirm')]");

    // نافذة الشباك
    private final By AddWindow_Button = By.xpath("//button[contains(normalize-space(),'اضافة شباك')]");
    private final By WindowCode_Field = By.xpath("//app-create-update-elct-service-windows//input-control[@label=\"كود الشباك\"]/input");
    private final By PeriodFrom_Field = By.xpath("//app-create-update-elct-service-windows//input[@formcontrolname=\"from\"]");
    private final By PeriodTo_Field = By.xpath("//app-create-update-elct-service-windows//input[@formcontrolname=\"to\"]");
    private final By ReservationTypeList = By.xpath("//ng-select[@id=\"elctReservationTypeId\"]");
    private final By AddPeriod_Button = By.xpath("//app-create-update-elct-service-windows//button[contains(@class,'add-btn')]");
    private final By SaveWindow_Button = By.xpath("//app-create-update-elct-service-windows//button[normalize-space()='حفظ']");
    private final By CloseWindow_Button = By.xpath("//app-create-update-elct-service-windows//button[contains(@class,'btn-close')]");

    // نافذة الاجندة
    private final By SetupAgenda_Button = By.xpath("//button[normalize-space()='إعداد الاجندة']");
    private final By AgendaStartDate_Field = By.xpath("//input-date-control[@formcontrolname=\"startDate\"]//input");
    private final By AgendaEndDate_Field = By.xpath("//input-date-control[@formcontrolname=\"endDate\"]//input");
    private final By FirstEnabledDay = By.xpath("(//bs-datepicker-container//td/span[not(contains(@class,'is-other-month')) and not(contains(@class,'disabled'))])[1]");
    private final By LastEnabledDay = By.xpath("(//bs-datepicker-container//td/span[not(contains(@class,'is-other-month')) and not(contains(@class,'disabled'))])[last()]");
    private final By ConfirmAgenda_Button = By.xpath("//app-create-update-agenda//button[normalize-space()='تأكيد']");

    private By OptionByName (String Name) {
        return By.xpath("(//div[@role='option'][normalize-space()='" + Name + "'])[1]");
    }

    private By EditButtonOfBranch (String BranchName) {
        return By.xpath("//table//tbody/tr[td[1][normalize-space()='" + BranchName + "']]//button[.//*[local-name()='svg' and contains(@class,'edit-icon')]]");
    }

    private By BranchCell (String BranchName , int Column) {
        return By.xpath("//table//tbody/tr[td[1][normalize-space()='" + BranchName + "']]/td[" + Column + "]");
    }

    private By EditButtonOfWindow (String Code) {
        return By.xpath("//table//tbody/tr[td[1][normalize-space()='" + Code + "']]//a[.//*[local-name()='svg' and contains(@class,'edit-icon')]]");
    }

    private By PeriodRow (String ReservationType) {
        return By.xpath("//app-create-update-elct-service-windows//table//tbody/tr[td[3][normalize-space()='" + ReservationType + "']]");
    }

    private By SettingField (String FormControlName) {
        return By.xpath("//input-control[@formcontrolname=\"" + FormControlName + "\"]/input");
    }

    private By SwitchInput (String FormControlName) {
        return By.xpath("//input[@formcontrolname=\"" + FormControlName + "\"]");
    }

    private By SwitchSlider (String FormControlName) {
        return By.xpath("//input[@formcontrolname=\"" + FormControlName + "\"]/following-sibling::span[@class=\"slider\"]");
    }

    // السيرش بيشتغل بالـ Enter بس. قايمة "اسم الفرع" في لوحة التصفية بتعرض اول 5 فروع بس فمش بنستخدمها
    private void SearchByBranchName (String BranchName) {
        Utility.ClearAndSENDKEYS(driver,SearchField,BranchName + Keys.ENTER);
    }

    public S04_ElcBranchesPage SearchOnBranchAndOpenEdit (String BranchName) {
        SearchByBranchName(BranchName);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,EditButtonOfBranch(BranchName));
        return this;
    }

    // القيم من الجيسون بنفس اسم الحقل في الموقع: Elc_<اسم الحقل>
    public S04_ElcBranchesPage FillBranchSettings () {
        for (String Field : SettingsFields) {
            Utility.ClearAndSENDKEYS(driver,SettingField(Field),DataUtiles.getJsonData("Data","Elc_" + Field));
        }
        for (String Switch : SettingsSwitches) {
            if (!driver.findElement(SwitchInput(Switch)).isSelected()) {
                Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SwitchSlider(Switch));
            }
        }
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SaveSettings_Button);
        ConfirmSavedMessage();
        return this;
    }

    // كل شباك في الجيسون بالشكل: الكود|من|الي|نوع الحجز - والشبابيك مفصولة بفاصلة
    // الشباك بيتحفظ الاول، والفترات بتتضاف من تعديله بعد الحفظ
    public S04_ElcBranchesPage AddWindowsWithPeriods (String Windows) {
        for (String Window : Windows.split(",")) {
            String[] Data = Window.split("\\|",-1);
            AddWindow(Data[0].trim());
            AddPeriodToWindow(Data[0].trim(),Data[1].trim(),Data[2].trim(),Data[3].trim());
        }
        return this;
    }

    private void AddWindow (String Code) {
        // لو الشباك موجود من تشغيل قبل كده بنعدي عليه عشان الكود ميتكررش
        if (!driver.findElements(EditButtonOfWindow(Code)).isEmpty()) {
            return;
        }
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddWindow_Button);
        Utility.WatingAndSENDKEYS(driver,WindowCode_Field,Code);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SaveWindow_Button);
        ConfirmSavedMessage();
    }

    private void AddPeriodToWindow (String Code , String From , String To , String ReservationType) {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,EditButtonOfWindow(Code));
        // النافذة بتحمل بيانات الشباك بعد ما تفتح وبتمسح اللي مكتوب، فلازم نستنى الكود يظهر الاول
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200))
                .ignoring(StaleElementReferenceException.class)
                .until(d -> Code.equals(d.findElement(WindowCode_Field).getDomProperty("value")));
        Utility.WaitForPageLoad(driver);
        // لو الفترة موجودة من تشغيل قبل كده بنقفل النافذة من غير اضافة
        if (!driver.findElements(PeriodRow(ReservationType)).isEmpty()) {
            Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,CloseWindow_Button);
            return;
        }
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ReservationTypeList);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,OptionByName(ReservationType));
        Utility.SetValueByJavaScript(driver,PeriodFrom_Field,From);
        Utility.SetValueByJavaScript(driver,PeriodTo_Field,To);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddPeriod_Button);
        Utility.GETTEXT(driver,PeriodRow(ReservationType));
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SaveWindow_Button);
        ConfirmSavedMessage();
    }

    // الاجندة من اول يوم قابل للنقر في الكالندر لاخر يوم قابل للنقر
    public S04_ElcBranchesPage SetupAgendaFromFirstToLastDay () {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SetupAgenda_Button);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AgendaStartDate_Field);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,FirstEnabledDay);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AgendaEndDate_Field);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,LastEnabledDay);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ConfirmAgenda_Button);
        ConfirmSavedMessage();
        return this;
    }

    private void ConfirmSavedMessage () {
        Utility.GETTEXT(driver,Saved_Message);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,OK_Button);
    }

    // بيتأكد من القائمة: عدد الشبابيك (عمود 6) وحالة الاجندة (عمود 8)
    public S04_ElcBranchesPage ValidateBranchWindowsAndAgenda (String BranchName , String WindowsCount) {
        new P02_Dashboard(driver).SelectElcBranchesPage();
        SearchByBranchName(BranchName);
        Validations.ValidateEquals(Utility.GETTEXT(driver,BranchCell(BranchName,6)).trim(),WindowsCount);
        Validations.ValidateEquals(Utility.GETTEXT(driver,BranchCell(BranchName,8)).trim(),"الاجندة متاحة");
        return this;
    }

}
