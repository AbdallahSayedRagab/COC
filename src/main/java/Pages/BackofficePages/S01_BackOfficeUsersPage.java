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
    private final By AddUser_Button = By.xpath("//button[@type=\"submit\" and normalize-space()='اضافة']");

    // حقول ليها قيمة افتراضية في الفورم - اللوكيتر موجود بس مش بنغير قيمتها
    private final By IsActive_Switch = By.xpath("//input[@formcontrolname=\"isActive\"]");

    private String NewFullName;
    private String NewUserName;
    private String NewPassword;
    private String NewBranch;
    // مفاتيح الجيسون اللي بيتحفظ فيها اليوزر (العادي: NewUsernameBackOffice - بريد الطلبات: MailUserName - الشباك: WindowUserName)
    private String UserNameKey;
    private String PasswordKey;

    // آلية العمل: صندوق بريد - توزيع إلكتروني - خدمة جمهور
    private By OperationRadio (String Operation) {
        return By.xpath("//label[normalize-space()='" + Operation + "']/preceding-sibling::input[@formcontrolname=\"operation\"]");
    }

    private By UserRowByUserName (String UserName) {
        return By.xpath("//table//tbody/tr[td[3][normalize-space()='" + UserName + "']]");
    }

    private By UserNameCellByFullName (String FullName) {
        return By.xpath("//table//tbody/tr[td[1][normalize-space()='" + FullName + "']]/td[3]");
    }

    // اليوزر العادي (AutoUser)
    public S01_BackOfficeUsersPage PrepareUniqueUserData (String FullName , String BaseUserName , String Password) {
        return PrepareUniqueUserData(FullName, BaseUserName, Password, "NewUsernameBackOffice", "NewPasswordBackOffice");
    }

    // بيبدأ من الرقم اللي بعد آخر مستخدم متسجل في الجيسون ويزود لحد ما يلاقي اسم مش موجود في الشاشة
    // (لو آخر يوزر في الجيسون مش من نفس النوع - زي يوزر قديم متسجل بايدينا - بيبدأ من 1)
    public S01_BackOfficeUsersPage PrepareUniqueUserData (String FullName , String BaseUserName , String Password ,
                                                          String UserNameKey , String PasswordKey) {
        this.UserNameKey = UserNameKey;
        this.PasswordKey = PasswordKey;
        WaitForListToLoad();
        String LastUserName = JsonUtil.readValue(UserNameKey);
        String LastNumber = LastUserName == null || !LastUserName.startsWith(BaseUserName) ? "" : LastUserName.substring(BaseUserName.length()).replaceAll("\\D","");
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

    // اليوزر العادي: آلية العمل صندوق بريد
    public S01_BackOfficeUsersPage FillDataOfNewUser (String Role , String Branch) {
        return FillDataOfNewUser(Role, Branch, "صندوق بريد");
    }

    public S01_BackOfficeUsersPage FillDataOfNewUser (String Role , String Branch , String Operation) {
        NewBranch = Branch;
        By RoleChoice = By.xpath("//div[@role='option'][normalize-space()='" + Role + "']");
        By BranchChoice = By.xpath("//div[@role='option'][normalize-space()='" + Branch + "']");
        Utility.WatingAndSENDKEYS(driver,FullName_Field,NewFullName);
        Utility.WatingAndSENDKEYS(driver,Email_Field,NewUserName + "@coc.net");
        Utility.WatingAndSENDKEYS(driver,UserName_Field,NewUserName);
        Utility.WatingAndSENDKEYS(driver,Password_Field,NewPassword);
        // الراديو قبل القوايم عشان القايمة المفتوحة بتغطي عليه
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,OperationRadio(Operation));
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
        JsonUtil.writeValue(UserNameKey,NewUserName);
        JsonUtil.writeValue(PasswordKey,NewPassword);
        return this;
    }

    // فرع اليوزر (يوزر الشباك بيختاره في الدخول بالشباك)
    public S01_BackOfficeUsersPage SaveUserBranchInJsonFile (String BranchKey) {
        JsonUtil.writeValue(BranchKey,NewBranch);
        return this;
    }

    public S01_BackOfficeUsersPage SearchByNameAndValidateUser () {
        Utility.SearchInTable(driver,SearchField,TableRows,NewFullName);
        Validations.ValidateEquals(Utility.GETTEXT(driver,UserNameCellByFullName(NewFullName)).trim(),NewUserName);
        return this;
    }

}
