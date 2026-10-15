package Pages.PortalPages;

import Pages.BackofficePages.P01_LoginPage;

import Utilities.DataUtiles;
import Utilities.JsonUtil;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;

public class P15_SpecialServicePortalPage {
    public P15_SpecialServicePortalPage(WebDriver driver) { this.driver = driver;}
    private WebDriver driver;

    private final By Loading_Circle = By.xpath("//div[contains(@class,'ngx-spinner-overlay')]");
    private final By AccountMenu = By.xpath("//span[contains(@class,'header-link') and text()='الحساب']");
    private final By LoginLink = By.xpath("//a[contains(@class,'asset-wrap')]//span[contains(text(),'تسجيل الدخول')]");
    private final By Portal_Email = By.xpath("//input[@formcontrolname=\"username\"]");
    private final By Portal_Password = By.xpath("//input[@formcontrolname=\"password\"]");
    private final By Portal_LoginButton = By.xpath("//button[contains(@class,'login-btn')]");
    private final By EServices_Header = By.xpath("//a[@href=\"/ar/eservices\"]");
    private final By SpecialServiceRequest_FSM = By.xpath("//a[contains(@class,'header-nested-links-item') and contains(text(),'طلب خدمة إختيارية')]");
    private final By AcceptConditionsCheckBox = By.xpath("//input[@formcontrolname=\"conditions\"]");
    private final By StartServiceButton = By.xpath("//button[contains(text(),' إبدا الخدمة الآن ')]");
    private final By ConfirmButtonOfPopUp = By.xpath("//button[contains(@class,'swal2-confirm')]");
    private final By RequestTypeField = By.xpath("//label[normalize-space(text())='نوع الطلب']/following-sibling::*[1]");
    private final By FacilityClassificationField = By.xpath("//label[normalize-space(text())='تصنيف المنشاة']/following-sibling::*[1]");
    private final By RecordOfficeField = By.xpath("//label[normalize-space(text())='مكتب السجل']/following-sibling::*[1]");
    private final By RecordNumberField = By.xpath("//label[normalize-space(text())='رقم السجل']/following-sibling::*[1]");
    private final By RequestDetailsField = By.xpath("//label[normalize-space(text())='تفاصيل الطلب']/following-sibling::*[1]");
    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");
    private final By NextButton = By.xpath("//button[contains(text(),' التالى ')]");
    private final By RequestNumberText = By.xpath("//div[@id=\"swal2-html-container\"]");
    private final By AddAttachmentCard = By.xpath("//span[text()='اضافة مرفق']");
    private final By AttachmentTypeField = By.xpath("//label[normalize-space(text())='نوع المرفق']/following-sibling::*[1]");
    private final By AddAttachmentTypeButton = By.xpath("//button[contains(text(),' أضافة ')]");
    private final By UploadFileButton = By.xpath("//div[contains(@class,'upload-new')]");
    private final By AttachmentNotesField = By.xpath("//textarea[@placeholder=\"أضف ملاحظات\"]");
    private final By NextButtonOfAttachments = By.xpath("//div[@id='test-l-2']//button[contains(text(),' التالى ')]");
    private final By SendCertificateToBranchField = By.xpath("//label[normalize-space(text())='تقديم الشهادة الى فرع']/following-sibling::*[1]");
    private final By ApplicantCapacityField = By.xpath("//label[normalize-space(text())='صفة مقدم الطلب']/following-sibling::*[1]");
    private final By SendRequestButton = By.xpath("//button[text()='إرسال']");
    private final By UserNameMenu = By.xpath("//ul[contains(@class,'user-dropdown')]/preceding-sibling::div//span[contains(@class,'header-link')]");
    private final By MyAccountLink = By.xpath("//ul[contains(@class,'user-dropdown')]//a[@href=\"/ar/profile\"]");
    private final By LogoutLink = By.xpath("//ul[contains(@class,'user-dropdown')]//span[text()='تسجيل الخروج']");
    private final By SpecialServicesTab = By.xpath("//div[@class='requested-services' and .//strong[text()='الخدمات المتميزة']]");
    private final By SearchByRequestNumberField = By.xpath("//input[@placeholder=\"رقم الطلب\"]");
    private final By PrintCertificateAndBookAppointmentButton = By.xpath("//button[contains(text(),' طباعة الشهادة وحجز موعد ')]");

    private By ViewButtonOfRequest (String RequestNumber) {
        return By.xpath("//app-special-service[.//span[text()='" + RequestNumber + "']]//img[contains(@src,'open-icon')]");
    }

    public P15_SpecialServicePortalPage LoginToPortal (String Email , String Password) throws InterruptedException {
        Utility.WaitUntilReady_And_CLICK(driver,AccountMenu,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,LoginLink,Loading_Circle);
        Utility.WaitUntilReady_And_SENDKEYS(driver,Portal_Email,Loading_Circle,Email);
        Utility.WaitUntilReady_And_SENDKEYS(driver,Portal_Password,Loading_Circle,Password);
        Utility.WaitUntilReady_And_CLICK(driver,Portal_LoginButton,Loading_Circle);
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.invisibilityOfElementLocated(Portal_LoginButton));
        return this;
    }

    public P15_SpecialServicePortalPage OpenSpecialServiceRequest () throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.invisibilityOfElementLocated(Portal_LoginButton));
        Utility.WaitForPageLoad(driver);
        new Actions(driver).moveToElement(driver.findElement(EServices_Header)).pause(Duration.ofMillis(500))
                .moveToElement(driver.findElement(SpecialServiceRequest_FSM)).click().perform();
        return this;
    }

    public P15_SpecialServicePortalPage StartTheService () throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.visibilityOfElementLocated(AcceptConditionsCheckBox));
        if (!driver.findElement(AcceptConditionsCheckBox).isSelected()) {
            Utility.WaitUntilReady_And_CLICK(driver,AcceptConditionsCheckBox,Loading_Circle);
        }
        Utility.WaitUntilReady_And_CLICK(driver,StartServiceButton,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,ConfirmButtonOfPopUp,Loading_Circle);
        return this;
    }

    public P15_SpecialServicePortalPage FillRequestDataAndSaveRequestNumber (String TestData) throws InterruptedException {
        Utility.WaitUntilReady_And_CLICK(driver,RequestTypeField,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,FacilityClassificationField,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,RecordOfficeField,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList,Loading_Circle);
        Utility.WaitUntilReady_And_SENDKEYS(driver,RecordNumberField,Loading_Circle,TestData);
        Utility.WaitUntilReady_And_SENDKEYS(driver,RequestDetailsField,Loading_Circle,TestData);
        Utility.WaitUntilReady_And_CLICK(driver,NextButton,Loading_Circle);
        String requestNumber = Utility.GETTEXT(driver,RequestNumberText,Loading_Circle).replaceAll("[^0-9]", "");
        JsonUtil.writeValue("SpecialServiceRequestNumber", requestNumber);
        Utility.WaitUntilReady_And_CLICK(driver,ConfirmButtonOfPopUp,Loading_Circle);
        return this;
    }

    public P15_SpecialServicePortalPage FillAttachmentSection (String TestData) throws InterruptedException, AWTException {
        Utility.WaitUntilReady_And_CLICK(driver,AddAttachmentCard,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,AttachmentTypeField,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,AddAttachmentTypeButton,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,UploadFileButton,Loading_Circle);

        String path = "\"C:\\Users\\AcTiVE\\Desktop\\pexels-sulimansallehi-1704488 - Copy (4).jpg\"";
        StringSelection StringSelection = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(StringSelection,null);

        Robot robot = new Robot();
        robot.delay(1000);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(1000);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(5000);

        Utility.WaitUntilReady_And_SENDKEYS(driver,AttachmentNotesField,Loading_Circle,TestData);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfAttachments,Loading_Circle);
        return this;
    }

    public P15_SpecialServicePortalPage ConfirmSendingTheRequest (String Branch) throws InterruptedException {
        By BranchChoice = By.xpath("//div[@role='option'][normalize-space()='" + Branch + "']");
        Utility.WaitUntilReady_And_CLICK(driver,SendCertificateToBranchField,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,BranchChoice,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,ApplicantCapacityField,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,SendRequestButton,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,ConfirmButtonOfPopUp,Loading_Circle);
        return this;
    }

    public P15_SpecialServicePortalPage OpenMyAccount () {
        new Actions(driver).moveToElement(driver.findElement(UserNameMenu)).pause(Duration.ofMillis(500))
                .moveToElement(driver.findElement(MyAccountLink)).click().perform();
        return this;
    }

    public P15_SpecialServicePortalPage LogoutFromPortalIfLoggedIn () {
        new WebDriverWait(driver, Duration.ofSeconds(180)).until(ExpectedConditions.visibilityOfElementLocated(EServices_Header));
        if (!driver.findElements(LogoutLink).isEmpty()) {
            new Actions(driver).moveToElement(driver.findElement(UserNameMenu)).pause(Duration.ofMillis(500))
                    .moveToElement(driver.findElement(LogoutLink)).click().perform();
            Utility.WaitForPageLoad(driver);
        }
        return this;
    }

    public P15_SpecialServicePortalPage OpenSpecialServicesAndSearchOnRequest (String RequestNumber) throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(180)).until(ExpectedConditions.invisibilityOfElementLocated(Loading_Circle));
        Utility.WaitUntilReady_And_CLICK(driver,SpecialServicesTab,Loading_Circle);
        new WebDriverWait(driver, Duration.ofSeconds(180)).until(ExpectedConditions.invisibilityOfElementLocated(Loading_Circle));
        Utility.WaitUntilReady_And_SENDKEYS(driver,SearchByRequestNumberField,Loading_Circle,RequestNumber);
        return this;
    }

    public P17_PortalReservationPage ViewRequestAndBookAppointment (String RequestNumber) throws InterruptedException {
        Utility.WaitUntilReady_And_CLICK(driver,ViewButtonOfRequest(RequestNumber),Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,PrintCertificateAndBookAppointmentButton,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,ConfirmButtonOfPopUp,Loading_Circle);
        return new P17_PortalReservationPage(driver);
    }

    public P01_LoginPage OpenBackOfficeInNewWindow () throws IOException {
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get(DataUtiles.getPropertyValue("environment","BASE_URL"));
        Utility.WaitForPageLoad(driver);
        return new P01_LoginPage(driver);
    }

}
