package Pages.BackofficePages;

import Utilities.JsonUtil;
import Utilities.Utility;
import Utilities.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class S02_PortalUsersPage {
    public S02_PortalUsersPage(WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    // شاشة مستخدمين الموقع
    private final By AddNewUser_Button = By.xpath("//a[normalize-space()='أضافة مستخدم']");
    private final By SearchField = By.xpath("//input[@name=\"srchhkey\"]");
    private final By TableRows = By.xpath("//table//tbody/tr");
    private final By OpenFilterPanel_Button = By.xpath("//button[.//span[normalize-space()='تصفية']]");
    private final By NationalIdFilter_Field = By.xpath("//label[normalize-space(text())='رقم تحقيق الشخصية']/following-sibling::*[1]");
    private final By ApplyFilter_Button = By.xpath("//div[contains(@class,'site-footer-actions')]//button[normalize-space()='تصفية']");
    private final By ResetFilter_Button = By.xpath("//button[normalize-space()='اعادة ضبط']");

    // فورم اضافة مستخدم - الحقول الفاضية اللي لازم نكملها
    private final By FullName_Field = By.xpath("//input-control[@label=\"الاسم كامل\"]/input");
    private final By IdTypeList = By.xpath("//select-control[@formcontrolname=\"idTypeId\"]//ng-select");
    private final By NationalIdTypeChoice = By.xpath("//div[@role='option'][normalize-space()='بطاقة رقم قومي']");
    private final By NationalId_Field = By.xpath("//input-control[@label=\"رقم تحقيق الشخصية\"]/input");
    private final By Mobile_Field = By.xpath("//input-control[@label=\"رقم الهاتف\"]/input");
    private final By Save_Button = By.xpath("//button[@type=\"submit\" and normalize-space()='حفظ']");
    private final By EditUserTitle = By.xpath("//*[normalize-space(text())='تعديل مستخدم موقع']");

    // تأكيد البريد من منيو "المزيد" - من غيره البورتال بيرفض دخول المستخدم
    private final By ConfirmEmail_Button = By.xpath("//button[normalize-space()='تأكيد البريد الالكتروني']");
    private final By ConfirmEmailDone_Message = By.xpath("//*[contains(text(),'تم تأكيد البريد')]");
    private final By ConfirmEmailDone_OK_Button = By.xpath("//button[contains(@class,'swal2-confirm')]");

    // تأكيد المستخدم من منيو "المزيد" - بيفتح نافذة فيها مرفق وزرار تأكيد
    private final By ConfirmUser_Button = By.xpath("//button[normalize-space()='تأكيد المستخدم']");
    private final By ConfirmUserPopUp = By.xpath("//app-verify-user");
    private final By ConfirmUserUpload_Button = By.xpath("//app-verify-user//button[contains(@class,'file-upload-btn')]");
    private final By ConfirmUserUploadedFile = By.xpath("//app-verify-user//span[contains(@class,'floating-text')]");
    private final By ConfirmUserSubmit_Button = By.xpath("//app-verify-user//button[@type=\"submit\"]");

    // حقول ليها قيمة افتراضية في الفورم - مش بنغير قيمتها، واسم المستخدم وكلمة المرور بنقراهم بس عشان نسجلهم في الجيسون
    // كلمة المرور الافتراضية بتستخدم مرة واحدة: بتتغير للكلمة الثابتة في اول دخول على البورتال (ولازم تختلف عنها)
    private final By UserName_Field = By.xpath("//input-control[@label=\"اسم المستخدم\"]/input");
    private final By Password_Field = By.xpath("//input-control[@label=\"كلمة المرور\"]/input");
    private final By ConfirmPassword_Field = By.xpath("//input-control[@label=\"تاكيد كلمة المرور\"]/input");
    private final By Email_Field = By.xpath("//input-control[@label=\"البريد الالكتروني\"]/input");
    private final By SpecialServicesBalance_Field = By.xpath("//input-control[@label=\"رصيد الخدمات المتميزة\"]/input");
    private final By Verified_Switch = By.xpath("//input[@formcontrolname=\"verified\"]");

    private String NewFullName;
    private String NewNationalId;
    private String NewUserName;

    private By UserNameCell (String FullName , String NationalId) {
        return By.xpath("//table//tbody/tr[td[1][normalize-space()='" + FullName + "'] and td[5][normalize-space()='" + NationalId + "']]/td[3]");
    }

    private By MoreButtonOfUser (String FullName) {
        return By.xpath("//table//tbody/tr[td[1][normalize-space()='" + FullName + "']]//button[normalize-space()='المزيد']");
    }

    // الرقم القومي: بيبدأ من اللي بعد آخر رقم في الجيسون ويزود لحد ما يلاقي رقم مش موجود في الشاشة
    // الموقع بيقبل زيادة 1 لان آخر رقم مش رقم تحقق، وتاريخ الميلاد وكود المحافظة مش بيتغيروا مع الزيادة
    // الاسم بياخد نفس الرقم من آخر 5 خانات عشان ميتكررش، بس بالحروف لان فورم الطلبات بيرفض الارقام في اسم مقدم الطلب
    public S02_PortalUsersPage PrepareUniqueUserData (String FullName) {
        WaitForListToLoad();
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,OpenFilterPanel_Button);
        long NationalId = Long.parseLong(JsonUtil.readValue("NewNationalIdPortal")) + 1;
        while (IsNationalIdFoundInList(String.valueOf(NationalId))) {
            NationalId++;
        }
        NewNationalId = String.valueOf(NationalId);
        NewFullName = FullName + " " + NumberAsLetters(NationalId % 100000);
        ResetFilter();
        return this;
    }

    // كل رقم بيتحول لحرف: 0=A و 1=B ... فمثلا 12 تبقى BC
    private String NumberAsLetters (long Number) {
        StringBuilder Letters = new StringBuilder();
        for (char Digit : String.valueOf(Number).toCharArray()) {
            Letters.append((char) ('A' + (Digit - '0')));
        }
        return Letters.toString();
    }

    // لازم اول تحميل للجدول يخلص قبل السيرش - لو رد التحميل رجع بعد السيرش بيغطي على نتيجته
    private void WaitForListToLoad () {
        Utility.GETTEXT(driver,TableRows);
    }

    // السيرش العادي مش بيدور بالرقم القومي، فلازم من فلتر "رقم تحقيق الشخصية" في لوحة التصفية
    private boolean IsNationalIdFoundInList (String NationalId) {
        Utility.ClearAndSENDKEYS(driver,NationalIdFilter_Field,NationalId);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ApplyFilter_Button);
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200))
                .ignoring(StaleElementReferenceException.class)
                .until(d -> d.findElements(Utility.Loader).stream().noneMatch(WebElement::isDisplayed)
                        && Utility.IsTableFilteredBy(d,TableRows,NationalId));
        return !driver.findElements(TableRows).isEmpty();
    }

    // زرار الاضافة بيبني اسم المستخدم الافتراضي من عدد صفوف القائمة، فلازم الفلتر يتشال عشان العدد يرجع صح
    private void ResetFilter () {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ResetFilter_Button);
        WaitForListToLoad();
    }

    public S02_PortalUsersPage ClickOnAddNewUser () {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddNewUser_Button);
        return this;
    }

    public S02_PortalUsersPage FillDataOfNewUser (String Mobile) {
        Utility.WatingAndSENDKEYS(driver,FullName_Field,NewFullName);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,IdTypeList);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,NationalIdTypeChoice);
        Utility.WatingAndSENDKEYS(driver,NationalId_Field,NewNationalId);
        Utility.WatingAndSENDKEYS(driver,Mobile_Field,Mobile);
        return this;
    }

    public S02_PortalUsersPage SaveUserAndDataInJsonFile () {
        // القيم الافتراضية بنقراها قبل الحفظ لان حقل كلمة المرور مش بيظهر بعده
        NewUserName = driver.findElement(UserName_Field).getDomProperty("value");
        String NewPassword = driver.findElement(Password_Field).getDomProperty("value");
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,Save_Button);
        // بعد الحفظ بيفتح صفحة تعديل المستخدم - لو الحفظ فشل الصفحة مش هتظهر والكيس هتفشل هنا ومش هنحفظ حاجة
        Utility.GETTEXT(driver,EditUserTitle);
        JsonUtil.writeValue("NewUsernamePortal",NewUserName);
        JsonUtil.writeValue("NewPasswordPortal",NewPassword);
        JsonUtil.writeValue("NewNationalIdPortal",NewNationalId);
        return this;
    }

    public S02_PortalUsersPage SearchByNameAndValidateUser () {
        new P02_Dashboard(driver).SelectPortalUsersPage();
        WaitForListToLoad();
        Utility.SearchInTable(driver,SearchField,TableRows,NewFullName);
        Validations.ValidateEquals(Utility.GETTEXT(driver,UserNameCell(NewFullName,NewNationalId)).trim(),NewUserName);
        return this;
    }

    // تأكيد المستخدم: من "المزيد" بيأكد البريد الالكتروني وبعدين بيأكد المستخدم
    public S02_PortalUsersPage ConfirmUser () {
        ConfirmEmail();
        ConfirmUserWithAttachment();
        return this;
    }

    private void ConfirmEmail () {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,MoreButtonOfUser(NewFullName));
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ConfirmEmail_Button);
        Utility.GETTEXT(driver,ConfirmEmailDone_Message);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ConfirmEmailDone_OK_Button);
    }

    private void ConfirmUserWithAttachment () {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,MoreButtonOfUser(NewFullName));
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ConfirmUser_Button);
        Utility.GETTEXT(driver,ConfirmUserUpload_Button);
        Utility.UploadFile(driver,driver.findElement(ConfirmUserUpload_Button),Utility.AttachmentFilePath,ConfirmUserUploadedFile);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ConfirmUserSubmit_Button);
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.invisibilityOfElementLocated(ConfirmUserPopUp));
        // بعد ما يتأكد الاختيار بيختفي من المنيو - لو لسه موجود الكيس بتفشل هنا
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,MoreButtonOfUser(NewFullName));
        new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofMillis(200)).until(ExpectedConditions.invisibilityOfElementLocated(ConfirmUser_Button));
    }

}
