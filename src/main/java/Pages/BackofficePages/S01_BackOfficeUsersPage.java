package Pages.BackofficePages;

import Utilities.JsonUtil;
import Utilities.Utility;
import Utilities.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class S01_BackOfficeUsersPage {
    public S01_BackOfficeUsersPage(WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    // شاشة مستخدمين النظام
    private final By AddNewUser_Button = By.xpath("//a[normalize-space()='أضافة مستخدم']");
    private final By SearchField = By.xpath("//input[@name=\"srchhkey\"]");
    private final By TableRows = By.xpath("//table//tbody/tr");

    // فورم اضافة مستخدم
    private final By FullName_Field = By.xpath("//input-control[@label=\"الاسم كامل\"]/input");
    private final By Email_Field = By.xpath("//input-control[@label=\"البريد الالكتروني\"]/input");
    private final By UserName_Field = By.xpath("//input-control[@label=\"اسم المستخدم\"]/input");
    private final By Password_Field = By.xpath("//input-control[@label=\"كلمة المرور\"]/input");
    private final By RolesList = By.xpath("//select-control[@label=\"الادوار\"]//ng-select");
    private final By BranchesList = By.xpath("//select-control[@label=\"الفروع\"]//ng-select");
    private final By AllowedRequestSourcesList = By.xpath("//select-control[@label=\"مصادر الطلبات المسموح بها\"]//ng-select");
    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");
    private final By UserDataTitle = By.xpath("//strong[normalize-space()='بيانات المستخدم']");
    private final By MailBox_Radio = By.xpath("//label[normalize-space()='صندوق بريد']/preceding-sibling::input[@formcontrolname=\"operation\"]");
    private final By AddUser_Button = By.xpath("//button[@type=\"submit\" and normalize-space()='اضافة']");

    // حقول ليها قيمة افتراضية في الفورم - اللوكيتر موجود بس مش بنغير قيمتها
    private final By IsActive_Switch = By.xpath("//input[@formcontrolname=\"isActive\"]");
    private final By ElectronicDistribution_Radio = By.xpath("//label[normalize-space()='توزيع إلكتروني']/preceding-sibling::input[@formcontrolname=\"operation\"]");
    private final By CustomerService_Radio = By.xpath("//label[normalize-space()='خدمة جمهور']/preceding-sibling::input[@formcontrolname=\"operation\"]");

    private String NewFullName;
    private String NewUserName;
    private String NewPassword;

    private By UserRowByUserName (String UserName) {
        return By.xpath("//table//tbody/tr[td[3][normalize-space()='" + UserName + "']]");
    }

    private By UserNameCellByFullName (String FullName) {
        return By.xpath("//table//tbody/tr[td[1][normalize-space()='" + FullName + "']]/td[3]");
    }

    // بيبدأ من الرقم اللي بعد آخر مستخدم متسجل في الجيسون ويزود لحد ما يلاقي اسم مش موجود في الشاشة
    public S01_BackOfficeUsersPage PrepareUniqueUserData (String FullName , String BaseUserName , String Password) {
        WaitForListToLoad();
        String LastUserName = JsonUtil.readValue("NewUsernameBackOffice");
        String LastNumber = LastUserName == null ? "" : LastUserName.replaceAll("\\D","");
        int Number = LastNumber.isEmpty() ? 1 : Integer.parseInt(LastNumber) + 1;
        while (IsUserNameFoundInList(BaseUserName + Number)) {
            Number++;
        }
        NewFullName = FullName + " " + Number;
        NewUserName = BaseUserName + Number;
        NewPassword = Password;
        return this;
    }

    // لازم اول تحميل للجدول يخلص قبل السيرش - لو رد التحميل رجع بعد السيرش بيغطي على نتيجته
    private void WaitForListToLoad () {
        Utility.GETTEXT(driver,TableRows);
    }

    private boolean IsUserNameFoundInList (String UserName) {
        Utility.SearchInTable(driver,SearchField,TableRows,UserName);
        return !driver.findElements(UserRowByUserName(UserName)).isEmpty();
    }

    public S01_BackOfficeUsersPage ClickOnAddNewUser () {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddNewUser_Button);
        return this;
    }

    public S01_BackOfficeUsersPage FillDataOfNewUser (String Role , String Branch) {
        By RoleChoice = By.xpath("//div[@role='option'][normalize-space()='" + Role + "']");
        By BranchChoice = By.xpath("//div[@role='option'][normalize-space()='" + Branch + "']");
        Utility.WatingAndSENDKEYS(driver,FullName_Field,NewFullName);
        Utility.WatingAndSENDKEYS(driver,Email_Field,NewUserName + "@coc.net");
        Utility.WatingAndSENDKEYS(driver,UserName_Field,NewUserName);
        Utility.WatingAndSENDKEYS(driver,Password_Field,NewPassword);
        // الراديو قبل القوايم عشان القايمة المفتوحة بتغطي عليه
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,MailBox_Radio);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RolesList);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RoleChoice);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,BranchesList);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,BranchChoice);
        SelectAllRequestSources();
        return this;
    }

    // العنصر اللي بيتختار بيختفي من القايمة والقايمة بتفضل مفتوحة، فبنختار الاول لحد ما تفضى وبعدين نقفلها
    private void SelectAllRequestSources () {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AllowedRequestSourcesList);
        Utility.GETTEXT(driver,Option1ForAnyList);
        while (Utility.COUNT(driver,Option1ForAnyList) > 0) {
            Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,Option1ForAnyList);
        }
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,UserDataTitle);
    }

    public S01_BackOfficeUsersPage SaveUserAndDataInJsonFile () {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddUser_Button);
        // بعد الحفظ بيرجع لشاشة مستخدمين النظام - لو الحفظ فشل الجدول مش هيظهر والكيس هتفشل هنا ومش هنحفظ حاجة
        WaitForListToLoad();
        JsonUtil.writeValue("NewUsernameBackOffice",NewUserName);
        JsonUtil.writeValue("NewPasswordBackOffice",NewPassword);
        return this;
    }

    public S01_BackOfficeUsersPage SearchByNameAndValidateUser () {
        Utility.SearchInTable(driver,SearchField,TableRows,NewFullName);
        Validations.ValidateEquals(Utility.GETTEXT(driver,UserNameCellByFullName(NewFullName)).trim(),NewUserName);
        return this;
    }

}
