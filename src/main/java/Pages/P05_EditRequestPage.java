package Pages;

import Utilities.Utility;
import Utilities.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P05_EditRequestPage {
    public P05_EditRequestPage(WebDriver driver) { this.driver = driver;
    }
    private WebDriver driver;

    private final By RequestsIcon = By.xpath("//img[@src=\"../../assets/images/certificates-icon.svg\"]");
    private final By EditRequest_FSM = By.xpath("//a[contains(@href, '/requests/editCommercialList')]");
    private final By AddNewRequest_Button = By.xpath("//img[@src=\"../../../assets/images/circle-plus-white.svg\"]");
    private final By Loading_Circle = By.xpath("//mat-spinner[@mode=\"indeterminate\"]");
    private final By PersonalIdentificationNumber_Field = By.xpath("//input-control[@label=\"رقم تحقيق الشخصية\"]/input");
    private final By SaveApplicantData_Button = By.xpath("//button[contains(text(),'حفظ جميع بيانات مقدم الطلب')]");
    private final By SearchForRecord_Field = By.xpath("//input[@placeholder=\"ابحث برقم السجل  \"]");
    private final By FirstRowForRecordsResult = By.xpath("(//tr[@cdk-describedby-host=\"0\"])[1]");
    private final By CheckBoxOFLegalData = By.xpath("(//input[@type=\"checkbox\"])[1]");
    private final By SendCertificateToOfficeField = By.xpath("//label[normalize-space(text())='تقديم الشهادة الى مكتب']/following-sibling::*[1]");
    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");
    private final By AddAttachmentButton = By.xpath("//button[@title=\"إضافة ملف جديد\"]");
    private final By ConfirmRequestButton = By.xpath("//span[contains(text(),\"تاكيد الطلب\")]");
    private final By AcceptRequestButtonOfPopUP = By.xpath("//button[contains(text(),\" اقبل الطلب\")]");
    private final By EditButton = By.xpath("(//img[@src=\"../../../../assets/images/pen-black.svg\"])[1]");
    private final By PullTheRequest = By.xpath("//label[contains(normalize-space(), 'اسحب الطلب')]//input[@type='checkbox']");
    private final By AcceptRequestButton = By.xpath("//span[contains(text(),\"أقبل الطلب\")]");
    private final By ViewButton = By.xpath("  (//img[@xmlns=\"http://www.w3.org/2000/svg\"])[1]");
    private final By SendToCollectButton = By.xpath("//span[contains(normalize-space(), 'أرسال للتحصيل')]");
    private final By SendToCollectButtonPouUp = By.xpath("//button[contains(normalize-space(), 'ارسل الطلب للتحصيل')]");


    public P05_EditRequestPage CreateNewEditRequest_Button () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,EditRequest_FSM,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddNewRequest_Button,Loading_Circle);
        return this;
    }
    public P05_EditRequestPage Validate_EditRequestURL () throws InterruptedException {
        Validations.ValidateURL("https://coc-backoffice-fix.apps.softprograms-eg.com/requests/issueCommerciallist");
        return this;
    }

    public P05_EditRequestPage FillAndSaveDataOfApplicant (String ID) throws InterruptedException {
        Utility.WatingAndSENDKEYS(driver,PersonalIdentificationNumber_Field,Loading_Circle,ID);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SaveApplicantData_Button,Loading_Circle);
        return this;
    }

    public P05_EditRequestPage ChoosingRecord (String RecordNumber){
        Utility.WatingAndSENDKEYS(driver,SearchForRecord_Field,Loading_Circle,RecordNumber);
         driver.findElement(SearchForRecord_Field).sendKeys(Keys.ENTER);
         Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,FirstRowForRecordsResult,Loading_Circle);
        return this;
    }


    public P05_EditRequestPage SelectSectionToEdit (){
    Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,CheckBoxOFLegalData,Loading_Circle);
        return this;
    }



    public P05_EditRequestPage FillCertificateData () throws InterruptedException {
        Utility.CLICKONELEMENTS(driver,SendCertificateToOfficeField);
        Utility.CLICKONELEMENTS(driver,Option1ForAnyList);
        return this;
    }

    public P05_EditRequestPage FillAttachmentSectionAndConfirmRequest() throws InterruptedException {

        List<WebElement> buttons = driver.findElements(AddAttachmentButton);
        if (buttons.size() > 0) {
            for (WebElement btn : buttons) {
                Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, (By) btn, Loading_Circle);
            }
        }
        Utility.CLICKONELEMENTS(driver, ConfirmRequestButton);
        Utility.CLICKONELEMENTS(driver,AcceptRequestButtonOfPopUP);
        return this;
    }


    public P05_EditRequestPage ReviewRequest () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,EditButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AcceptRequestButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AcceptRequestButtonOfPopUP,Loading_Circle);
        return this;
    }

    public P05_EditRequestPage SendingRequestToCollect(){
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ViewButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SendToCollectButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SendToCollectButtonPouUp,Loading_Circle);
        return this;
    }

}
