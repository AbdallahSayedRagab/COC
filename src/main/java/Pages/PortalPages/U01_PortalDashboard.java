package Pages.PortalPages;

import Utilities.JsonUtil;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class U01_PortalDashboard {
    public U01_PortalDashboard(WebDriver driver) { this.driver = driver;}
    private WebDriver driver;


    // تسجيل الدخول والخروج
    private final By AccountMenu = By.xpath("//span[contains(@class,'header-link') and text()='الحساب']");
    private final By LoginLink = By.xpath("//a[contains(@class,'asset-wrap')]//span[contains(text(),'تسجيل الدخول')]");
    private final By Portal_Email = By.xpath("//input[@formcontrolname=\"username\"]");
    private final By Portal_Password = By.xpath("//input[@formcontrolname=\"password\"]");
    private final By Portal_LoginButton = By.xpath("//button[contains(@class,'login-btn')]");
    private final By UserNameMenu = By.xpath("//ul[contains(@class,'user-dropdown')]/preceding-sibling::div//span[contains(@class,'header-link')]");
    private final By LogoutLink = By.xpath("//ul[contains(@class,'user-dropdown')]//span[text()='تسجيل الخروج']");

    // نافذة تغيير كلمة السر اللي بتظهر في اول دخول لمستخدم اتعمل من الباك اوفيس
    private final By ChangePasswordPopUp = By.xpath("//app-change-password-for-first-time");
    private final By NewPassword_Field = By.xpath("//input-control[@label=\"كلمة السر الجديدة\"]/input");
    private final By ConfirmNewPassword_Field = By.xpath("//input-control[@label=\"تأكيد كلمة السر الجديدة\"]/input");
    private final By ChangePassword_Button = By.xpath("//app-change-password-for-first-time//button[@type=\"submit\"]");

    // منيو الخدمات الالكترونية
    private final By EServices_Header = By.xpath("//a[@href=\"/ar/eservices\"]");
    private final By BusinessLicenseRequest_FSM = By.xpath("//a[contains(@class,'header-nested-links-item') and contains(text(),'شهادة ترخيص تجارة')]");
    private final By EditRecordRequest_FSM = By.xpath("//a[contains(@class,'header-nested-links-item') and contains(text(),'تعديل السجل التجاري')]");
    private final By RemoveRecordRequest_FSM = By.xpath("//a[contains(@class,'header-nested-links-item') and contains(text(),'محو سجل تجاري')]");
    private final By AdministrativeCertificateRequest_FSM = By.xpath("//a[contains(@class,'header-nested-links-item') and contains(text(),'اصدار شهادة ادارية')]");
    private final By SourceOfGoodsRequest_FSM = By.xpath("//a[contains(@class,'header-nested-links-item') and contains(text(),'شهادة منشأ مصدر بضاعة')]");
    private final By PaymentOfSubscriptionsRequest_FSM = By.xpath("//a[contains(@class,'header-nested-links-item') and contains(text(),'سداد اشتراكات')]");
    private final By SpecialServiceRequest_FSM = By.xpath("//a[contains(@class,'header-nested-links-item') and contains(text(),'طلب خدمة إختيارية')]");

    public U01_PortalDashboard LogoutFromPortalIfLoggedIn () {
        new WebDriverWait(driver, Duration.ofSeconds(180)).until(ExpectedConditions.visibilityOfElementLocated(EServices_Header));
        // نستنى الهيدر يحدد حالته: يا "الحساب" (مش عامل دخول) يا منيو اليوزر (عامل دخول)
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(d ->
                !d.findElements(AccountMenu).isEmpty() || !d.findElements(LogoutLink).isEmpty());
        if (!driver.findElements(LogoutLink).isEmpty()) {
            // المنيو بتفتح بالهوفر وساعات بتتأخر فبنعيد المحاولة لحد ما تفتح ونقدر ندوس تسجيل الخروج
            new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(500))
                    .ignoring(ElementNotInteractableException.class)
                    .ignoring(MoveTargetOutOfBoundsException.class)
                    .until(d -> {
                        new Actions(d).moveToElement(d.findElement(UserNameMenu)).pause(Duration.ofMillis(500))
                                .moveToElement(d.findElement(LogoutLink)).click().perform();
                        return true;
                    });
            Utility.WaitForPageLoad(driver);
            // نتأكد ان الخروج حصل فعلا قبل ما نكمل
            new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.presenceOfElementLocated(AccountMenu));
        }
        return this;
    }

    public U01_PortalDashboard LoginToPortal (String Email , String Password) {
        // بندوس على الحساب لحد ما لينك تسجيل الدخول يظهر (المنيو ساعات مش بتفتح من اول مرة)
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(ElementClickInterceptedException.class)
                .until(d -> {
                    if (d.findElements(Utility.Loader).stream().anyMatch(WebElement::isDisplayed)) return false;
                    if (!d.findElements(LoginLink).isEmpty() && d.findElement(LoginLink).isDisplayed()) return true;
                    d.findElement(AccountMenu).click();
                    return false;
                });
        Utility.WaitUntilReady_And_CLICK(driver,LoginLink);
        Utility.WaitUntilReady_And_SENDKEYS(driver,Portal_Email,Email);
        Utility.WaitUntilReady_And_SENDKEYS(driver,Portal_Password,Password);
        Utility.WaitUntilReady_And_CLICK(driver,Portal_LoginButton);
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.invisibilityOfElementLocated(Portal_LoginButton));
        return this;
    }

    // تسجيل الدخول بالمستخدم الجديد في البورتال
    // اول دخول بيطلب تغيير كلمة السر: بنغيرها للكلمة الثابتة ونسجلها في الجيسون، والموقع مش بيدخل بعدها فبندخل تاني
    // ولو المستخدم غير كلمته قبل كده النافذة مش بتظهر وبيدخل على طول
    public U01_PortalDashboard LoginWithNewPortalUser (String UserName , String Password , String NewPassword) {
        LoginToPortal(UserName,Password);
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(d ->
                !d.findElements(ChangePasswordPopUp).isEmpty() || !d.findElements(LogoutLink).isEmpty());
        if (!driver.findElements(ChangePasswordPopUp).isEmpty()) {
            Utility.WaitUntilReady_And_SENDKEYS(driver,NewPassword_Field,NewPassword);
            Utility.WaitUntilReady_And_SENDKEYS(driver,ConfirmNewPassword_Field,NewPassword);
            Utility.WaitUntilReady_And_CLICK(driver,ChangePassword_Button);
            new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.invisibilityOfElementLocated(ChangePasswordPopUp));
            JsonUtil.writeValue("NewPasswordPortal",NewPassword);
            LoginToPortal(UserName,NewPassword);
        }
        // لينك تسجيل الخروج موجود بس لما الدخول يتم فعلا - لو مظهرش الكيس بتفشل هنا
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.presenceOfElementLocated(LogoutLink));
        return this;
    }

    // بيفتح الخدمة من منيو الخدمات الالكترونية (هوفر) وبيعيد المحاولة لو المنيو اتأخرت
    private void OpenServiceFromEServicesMenu (By ServiceLink) {
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.visibilityOfElementLocated(EServices_Header));
        Utility.WaitForPageLoad(driver);
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(500))
                .ignoring(ElementNotInteractableException.class)
                .ignoring(MoveTargetOutOfBoundsException.class)
                .ignoring(StaleElementReferenceException.class)
                .until(d -> {
                    new Actions(d).moveToElement(d.findElement(EServices_Header)).pause(Duration.ofMillis(500))
                            .moveToElement(d.findElement(ServiceLink)).click().perform();
                    return true;
                });
    }

    public U02_BusinessLicenseRequestPage OpenBusinessLicenseRequest () {
        OpenServiceFromEServicesMenu(BusinessLicenseRequest_FSM);
        return new U02_BusinessLicenseRequestPage(driver);
    }

    public U03_EditRecordRequestPage OpenEditRecordRequest () {
        OpenServiceFromEServicesMenu(EditRecordRequest_FSM);
        return new U03_EditRecordRequestPage(driver);
    }

    public U04_RemoveRecordRequestPage OpenRemoveRecordRequest () {
        OpenServiceFromEServicesMenu(RemoveRecordRequest_FSM);
        return new U04_RemoveRecordRequestPage(driver);
    }

    public U05_AdministrativeCertificateRequestPage OpenAdministrativeCertificateRequest () {
        OpenServiceFromEServicesMenu(AdministrativeCertificateRequest_FSM);
        return new U05_AdministrativeCertificateRequestPage(driver);
    }

    public U06_SourceOfGoodsRequestPage OpenSourceOfGoodsRequest () {
        OpenServiceFromEServicesMenu(SourceOfGoodsRequest_FSM);
        return new U06_SourceOfGoodsRequestPage(driver);
    }

    public U07_PaymentOfSubscriptionsRequestPage OpenPaymentOfSubscriptionsRequest () {
        OpenServiceFromEServicesMenu(PaymentOfSubscriptionsRequest_FSM);
        return new U07_PaymentOfSubscriptionsRequestPage(driver);
    }

    public U08_SpecialServiceRequestPage OpenSpecialServiceRequest () {
        OpenServiceFromEServicesMenu(SpecialServiceRequest_FSM);
        return new U08_SpecialServiceRequestPage(driver);
    }

}
