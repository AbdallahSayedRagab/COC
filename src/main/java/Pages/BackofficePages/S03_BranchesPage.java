package Pages.BackofficePages;

import Utilities.JsonUtil;
import Utilities.Utility;
import Utilities.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

public class S03_BranchesPage {
    public S03_BranchesPage(WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    // شاشة الفروع
    private final By AddNewBranch_Button = By.xpath("//button[normalize-space()='إضافة جديد']");
    private final By SearchField = By.xpath("//input[@name=\"srchhkey\"]");
    private final By TableRows = By.xpath("//table//tbody/tr");
    private final By CodeCells = By.xpath("//table//tbody/tr/td[1]");
    private final By NameCells = By.xpath("//table//tbody/tr/td[2]");
    private final By NextPage_Button = By.xpath("//button[contains(@class,'p-paginator-next') and not(contains(@class,'p-disabled'))]");

    // فورم اضافة فرع
    private final By Code_Field = By.xpath("//input-control[@label=\"كود الفرع\"]/input");
    private final By NameAr_Field = By.xpath("//input-control[@label=\"الاسم بالعربي\"]/input");
    private final By NameEn_Field = By.xpath("//input-control[@label=\"الاسم بالانجليزي\"]/input");
    private final By DescriptionAr_Field = By.xpath("//input-control[@label=\"الوصف بالعربي\"]/textarea");
    private final By DescriptionEn_Field = By.xpath("//input-control[@label=\"الوصف بالانجليزي\"]/textarea");
    private final By StartTime_Field = By.xpath("//input-control[@label=\"توقيت بداية العمل\"]/input");
    private final By EndTime_Field = By.xpath("//input-control[@label=\"توقيت نهاية العمل\"]/input");
    private final By PaperList = By.xpath("//select-control[@formcontrolname=\"paperForPrintCertificate\"]//ng-select");
    private final By MaxRequests_Field = By.xpath("//input-control[@formcontrolname=\"maxNumberOfRequestsPerUser\"]/input");
    private final By AlertMinutes_Field = By.xpath("//input-control[@formcontrolname=\"alertInactivityDurationMinutes\"]/input");
    private final By ReleaseMinutes_Field = By.xpath("//input-control[@formcontrolname=\"releaseInactivityDurationMinutes\"]/input");
    private final By IsActive_Switch = By.xpath("//input[@formcontrolname=\"isActive\"]/following-sibling::span[@class=\"slider\"]");
    private final By ElcBooking_Switch = By.xpath("//input[@formcontrolname=\"supportElcBooking\"]/following-sibling::span[@class=\"slider\"]");
    private final By AddBranch_Button = By.xpath("//button[@type=\"submit\" and normalize-space()='اضافة']");

    // حقول بنسيبها مقفولة: النماذج المؤمنة وصندوق البريد الموحد مقفولين في الفرع المصدر
    // و"قيمة تلقائية" مفتوحة في الفرع المصدر بس مش بنفتحها هنا عشان ممكن تغير الفرع الافتراضي للسيستم كله
    private final By IsDefaultBranch_Switch = By.xpath("//input[@formcontrolname=\"isDefaultBranch\"]/following-sibling::span[@class=\"slider\"]");
    private final By SecuredForms_Switch = By.xpath("//input[@formcontrolname=\"enableSecuredForms\"]/following-sibling::span[@class=\"slider\"]");
    private final By UnifiedMailbox_Switch = By.xpath("//input[@formcontrolname=\"enableUnifiedMailbox\"]/following-sibling::span[@class=\"slider\"]");

    private String NewCode;
    private String NewName;

    private By CheckBoxByLabel (String Label) {
        return By.xpath("//label[normalize-space()='" + Label + "']/preceding-sibling::input");
    }

    private By BranchCodeCell (String Name) {
        return By.xpath("//table//tbody/tr[td[2][normalize-space()='" + Name + "']]/td[1]");
    }

    // الكود والاسم: بياخدوا اللي بعد آخر قيمة في الجيسون، ولو القيمة موجودة في الشاشة بينقلوا للي بعدها
    public S03_BranchesPage PrepareUniqueBranchData (String BaseName) {
        Set<String> UsedCodes = new HashSet<>();
        Set<String> UsedNames = new HashSet<>();
        ReadAllCodesAndNames(UsedCodes,UsedNames);

        String LastCode = JsonUtil.readValue("NewBranchCode");
        String Code = LastCode == null ? "K0" : NextCode(LastCode);
        while (UsedCodes.contains(Code)) {
            Code = NextCode(Code);
        }

        String LastName = JsonUtil.readValue("NewBranchName");
        String LastNumber = LastName == null ? "" : LastName.replaceAll("\\D","");
        int Number = LastNumber.isEmpty() ? 1 : Integer.parseInt(LastNumber) + 1;
        while (UsedNames.contains(BaseName + " " + Number)) {
            Number++;
        }

        NewCode = Code;
        NewName = BaseName + " " + Number;
        return this;
    }

    // حقل الكود بياخد حرفين بس، فبعد K9 بينتقل للحرف اللي بعده: L0
    private String NextCode (String Code) {
        char Letter = Code.charAt(0);
        int Digit = Code.charAt(1) - '0';
        return Digit == 9 ? (char) (Letter + 1) + "0" : Letter + String.valueOf(Digit + 1);
    }

    // لوحة التصفية مفيهاش الكود، فبنقرا كل صفحات الجدول (عددهم قليل)
    private void ReadAllCodesAndNames (Set<String> Codes , Set<String> Names) {
        WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200));
        Wait.ignoring(StaleElementReferenceException.class);
        Utility.GETTEXT(driver,TableRows);
        while (true) {
            Wait.until(d -> {
                for (WebElement Cell : d.findElements(CodeCells)) Codes.add(Cell.getText().trim());
                for (WebElement Cell : d.findElements(NameCells)) Names.add(Cell.getText().trim());
                return true;
            });
            if (driver.findElements(NextPage_Button).isEmpty()) {
                return;
            }
            String FirstRow = Wait.until(d -> d.findElement(TableRows).getText());
            Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,NextPage_Button);
            Wait.until(d -> !d.findElement(TableRows).getText().equals(FirstRow));
        }
    }

    public S03_BranchesPage ClickOnAddNewBranch () {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddNewBranch_Button);
        return this;
    }

    public S03_BranchesPage FillCodeAndNames () {
        Utility.WatingAndSENDKEYS(driver,Code_Field,NewCode);
        Utility.WatingAndSENDKEYS(driver,NameAr_Field,NewName);
        Utility.WatingAndSENDKEYS(driver,NameEn_Field,NewName);
        Utility.WatingAndSENDKEYS(driver,DescriptionAr_Field,NewName);
        Utility.WatingAndSENDKEYS(driver,DescriptionEn_Field,NewName);
        return this;
    }

    public S03_BranchesPage FillWorkData (String StartTime , String EndTime , String Paper , String MaxRequests , String AlertMinutes , String ReleaseMinutes) {
        By PaperChoice = By.xpath("//div[@role='option'][normalize-space()='" + Paper + "']");
        Utility.SetValueByJavaScript(driver,StartTime_Field,StartTime);
        Utility.SetValueByJavaScript(driver,EndTime_Field,EndTime);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PaperList);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PaperChoice);
        Utility.WatingAndSENDKEYS(driver,MaxRequests_Field,MaxRequests);
        // مدة التنبيه والتحرير ليهم قيمة افتراضية مختلفة عن الفرع المصدر فلازم نمسحها الاول
        Utility.ClearAndSENDKEYS(driver,AlertMinutes_Field,AlertMinutes);
        Utility.ClearAndSENDKEYS(driver,ReleaseMinutes_Field,ReleaseMinutes);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,IsActive_Switch);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ElcBooking_Switch);
        return this;
    }

    // القيم جاية من الجيسون مفصولة بفاصلة
    public S03_BranchesPage SelectRequestTypes (String RequestTypes) {
        CheckAll(RequestTypes);
        return this;
    }

    public S03_BranchesPage SelectWorkDays (String WorkDays) {
        CheckAll(WorkDays);
        return this;
    }

    private void CheckAll (String Labels) {
        for (String Label : Labels.split(",")) {
            Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,CheckBoxByLabel(Label.trim()));
        }
    }

    public S03_BranchesPage SaveBranchAndDataInJsonFile () {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddBranch_Button);
        // بعد الحفظ بيرجع لشاشة الفروع - لو الحفظ فشل الجدول مش هيظهر والكيس هتفشل هنا ومش هنحفظ حاجة
        Utility.GETTEXT(driver,TableRows);
        JsonUtil.writeValue("NewBranchCode",NewCode);
        JsonUtil.writeValue("NewBranchName",NewName);
        return this;
    }

    // السيرش في الشاشة دي بيشتغل بالـ Enter بس وبيدور في الاسم العربي
    public S03_BranchesPage SearchByNameAndValidateBranch () {
        Utility.ClearAndSENDKEYS(driver,SearchField,NewName + Keys.ENTER);
        Validations.ValidateEquals(Utility.GETTEXT(driver,BranchCodeCell(NewName)).trim(),NewCode);
        return this;
    }

}
