package Pages.PortalPages;

import Pages.BackofficePages.P01_LoginPage;

import Utilities.DataUtiles;
import Utilities.JsonUtil;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;

public class U08_SpecialServiceRequestPage {
    public U08_SpecialServiceRequestPage(WebDriver driver) { this.driver = driver;}
    private WebDriver driver;

    private final By Loading_Circle = By.xpath("//div[contains(@class,'ngx-spinner-overlay')]");
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
    private final By SpecialServicesTab = By.xpath("//div[contains(@class,'requested-services') and .//strong[text()='الخدمات المتميزة']]");
    private final By SearchByRequestNumberField = By.xpath("//input[@placeholder=\"رقم الطلب\"]");
    private final By PrintCertificateAndBookAppointmentButton = By.xpath("//button[contains(text(),' طباعة الشهادة وحجز موعد ')]");

    private By ViewButtonOfRequest (String RequestNumber) {
        return By.xpath("//app-special-service[.//span[text()='" + RequestNumber + "']]//img[contains(@src,'open-icon')]");
    }

    public U08_SpecialServiceRequestPage StartTheService () throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.visibilityOfElementLocated(AcceptConditionsCheckBox));
        if (!driver.findElement(AcceptConditionsCheckBox).isSelected()) {
            Utility.WaitUntilReady_And_CLICK(driver,AcceptConditionsCheckBox,Loading_Circle);
        }
        Utility.WaitUntilReady_And_CLICK(driver,StartServiceButton,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,ConfirmButtonOfPopUp,Loading_Circle);
        return this;
    }

    public U08_SpecialServiceRequestPage FillRequestDataAndSaveRequestNumber (String TestData) throws InterruptedException {
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

    public U08_SpecialServiceRequestPage FillAttachmentSection (String TestData) throws InterruptedException, AWTException {
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

    public U08_SpecialServiceRequestPage ConfirmSendingTheRequest (String Branch) throws InterruptedException {
        By BranchChoice = By.xpath("//div[@role='option'][normalize-space()='" + Branch + "']");
        Utility.WaitUntilReady_And_CLICK(driver,SendCertificateToBranchField,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,BranchChoice,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,ApplicantCapacityField,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,SendRequestButton,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,ConfirmButtonOfPopUp,Loading_Circle);
        return this;
    }

    // بيفتح الخدمات المتميزة في حسابي على طول من غير ما يعدي على الخدمات المطلوبة (بطيئة جدا على السيرفر)
    public U08_SpecialServiceRequestPage OpenSpecialServicesInMyAccount () throws IOException {
        driver.get(DataUtiles.getPropertyValue("environment","PORTAL_URL") + "/profile?page=SpecialRequest");
        Utility.WaitForPageLoad(driver);
        new WebDriverWait(driver, Duration.ofSeconds(180)).until(ExpectedConditions.visibilityOfElementLocated(SpecialServicesTab));
        return this;
    }

    public U08_SpecialServiceRequestPage SearchOnRequestInSpecialServices (String RequestNumber) throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(180)).until(ExpectedConditions.invisibilityOfElementLocated(Loading_Circle));
        Utility.WaitUntilReady_And_SENDKEYS(driver,SearchByRequestNumberField,Loading_Circle,RequestNumber);
        return this;
    }

    public R1_PortalReservationPage ViewRequestAndBookAppointment (String RequestNumber) throws InterruptedException {
        // قايمة الخدمات المتميزة ساعات بتتأخر في التحميل
        new WebDriverWait(driver, Duration.ofSeconds(180), Duration.ofMillis(500)).until(ExpectedConditions.visibilityOfElementLocated(ViewButtonOfRequest(RequestNumber)));
        Utility.WaitUntilReady_And_CLICK(driver,ViewButtonOfRequest(RequestNumber),Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,PrintCertificateAndBookAppointmentButton,Loading_Circle);
        Utility.WaitUntilReady_And_CLICK(driver,ConfirmButtonOfPopUp,Loading_Circle);
        return new R1_PortalReservationPage(driver);
    }

    public P01_LoginPage OpenBackOfficeInNewWindow () throws IOException {
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get(DataUtiles.getPropertyValue("environment","BASE_URL"));
        Utility.WaitForPageLoad(driver);
        return new P01_LoginPage(driver);
    }

}
