package Pages;

import Utilities.Utility;
import Utilities.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

public class P03_BusinessLicensePage {
    public P03_BusinessLicensePage (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;
    private final By AddNewRequest_Button = By.xpath("//img[@src=\"../../../assets/images/circle-plus-white.svg\"]");
    private final By Loading_Circle = By.xpath("//mat-spinner[@mode=\"indeterminate\"]");
    private final By  FacilityNature_Choice  = By.xpath("//label[contains(text(), ' مركز رئيسى')]");
    private final By  CreateRequest_Button  = By.xpath("//button[contains(text(), ' انشاء الطلب ')]");
    private final By PersonalIdentificationNumber_Field = By.xpath("//input-control[@label=\"رقم تحقيق الشخصية\"]/input");
    private final By SaveApplicantData_Button = By.xpath("//button[contains(text(),'حفظ جميع بيانات مقدم الطلب')]");
    private final By LegalDataSection = By.xpath("//span[contains(text(), \"البيانات القانونية\")]");
    private final By FacilityClassificationField = By.xpath("//label[contains(text(), \"تصنيف المنشاة\")]/following-sibling::*[1]");
    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");
    private final By FacilityTypeField = By.xpath("//label[contains(text(), \" نوع المنشأة \")]/following-sibling::*[1]");
    private final By TradeNameField = By.xpath("//label[normalize-space(text())='الاسم التجاري']/following-sibling::*[1]");
    private final By CommercialFeatureField = By.xpath("//label[normalize-space(text())='السمة التجارية']/following-sibling::*[1]");
    private final By MembersSection = By.xpath("//span[contains(text(), \"الاعضاء\")]");
    private final By LegalStatus = By.xpath("//label[normalize-space(text())='الصفات الإدارية']/following-sibling::*[1]");
    private final By AddMemberButton = By.xpath("//span[contains(text(), \" إضافة  شريك \")]");
    private final By CapitalData = By.xpath("//span[contains(text(), 'بيانات رأس المال')]");
    private final By CapitalDataField = By.xpath("//label[normalize-space(text())='رأس المال المسدد (ج.م)']/following-sibling::*[1]");
    private final By ActivitiesData = By.xpath("//span[contains(text(), 'بيانات النشاط')]");
    private final By TaxCardNumber = By.xpath("//label[normalize-space(text())='رقم التسجيل الضريبى']/following-sibling::*[1]");
    private final By DataOfStartingActivity = By.xpath("//label[normalize-space(text())='تاريخ بدء النشاط']/following-sibling::*[1]");
    private final By FixedDateForAnyCalendar = By.xpath("//td[@role='gridcell']/span[contains(text(), \"١٥\")]");
    private final By ActivityDescription = By.xpath("//label[normalize-space(text())='النشاط طبقا لوثيقة البطاقة الضريبية']/following-sibling::*[1]");
    private final By ActivityField = By.xpath("//label[normalize-space(text())='النشاط']/following-sibling::*[1]");
    private final By AddActivityButton = By.xpath("//button[contains(text(), ' اضافة نشاط ')]");
    private final By DepartmentName = By.xpath("//label[normalize-space(text())='اسم الشعبة']/following-sibling::*[1]");
    private final By AddDepartNameButton = By.xpath("//button[contains(text(), ' اضافة شعبة')]");
    private final By BusinessLocationSection = By.xpath("//span[contains(text(), 'محل النشاط')]");
    private final By FirstِِAdministrativeField = By.xpath("//label[normalize-space(text())='التقسيم الاداري الاول']/following-sibling::*[1]");
    private final By SecondAdministrativeField = By.xpath("//label[normalize-space(text())='التقسيم الاداري الثانى']/following-sibling::*[1]");
    private final By StreetName = By.xpath("//label[normalize-space(text())='اسم الشارع']/following-sibling::*[1]");
    private final By SendCertificateToOfficeField = By.xpath("//label[normalize-space(text())='تقديم الشهادة الى مكتب']/following-sibling::*[1]");


    private final By AddAttachmentButton = By.xpath("//button[@title=\"إضافة ملف جديد\"]");
    private final By ConfirmRequestButton = By.xpath("//span[contains(text(),\"تاكيد الطلب\")]");
    private final By AcceptRequestButtonOfPopUP = By.xpath("//button[contains(text(),\" اقبل الطلب\")]");

    private final By EditButton = By.xpath("(//li[.//*[local-name()='svg' and contains(@class,'edit-icon')]])[1]");
    private final By PullTheRequest = By.xpath("//label[contains(normalize-space(), 'اسحب الطلب')]//input[@type='checkbox']");
    private final By AcceptRequestButton = By.xpath("//span[contains(text(),\"أقبل الطلب\")]");
    private final By ViewButton = By.xpath("(//li[.//*[local-name()='svg' and contains(@class,'view-icon')]])[1]");
    private final By SendToCollectButton = By.xpath("//span[contains(normalize-space(), 'أرسال للتحصيل')]");
    private final By SendToCollectButtonPouUp = By.xpath("//button[contains(normalize-space(), 'ارسل الطلب للتحصيل')]");
    private final By RecieveCertificateButton = By.xpath("//button[contains(text(), \"تم التسليم\")]");
    private final By RequestNumber = By.xpath("(//td/a[@class=\"ng-star-inserted\"])[1]");


    public P03_BusinessLicensePage ClickOnAddNewRequest_Button () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddNewRequest_Button,Loading_Circle);
        return this;
    }
    public P03_BusinessLicensePage Validate_BusinessLicenseURL () throws InterruptedException {
        Validations.ValidateURL("https://coc-backoffice-fix.apps.softprograms-eg.com/requests/issueCommerciallist");
        return this;
    }
    public P03_BusinessLicensePage ChooseFacilityNature_And_CreateRequest() throws InterruptedException {
        Utility.CLICKONELEMENTS(driver,FacilityNature_Choice);
        Utility.CLICKONELEMENTS(driver,CreateRequest_Button);
        return this;
    }
    public P03_BusinessLicensePage FillAndSaveDataOfApplicant (String ID) throws InterruptedException {
        Utility.WatingAndSENDKEYS(driver,PersonalIdentificationNumber_Field,Loading_Circle,ID);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SaveApplicantData_Button,Loading_Circle);
        return this;
    }
    public P03_BusinessLicensePage FillLegalDataSection (String TradeName , String CommercialFeature ) throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,LegalDataSection,Loading_Circle);
        Utility.CLICKONELEMENTS(driver,FacilityClassificationField);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, Option1ForAnyList,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,FacilityTypeField,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, Option1ForAnyList,Loading_Circle);
        Utility.WatingAndSENDKEYS(driver,TradeNameField,Loading_Circle,TradeName);
        Utility.WatingAndSENDKEYS(driver,CommercialFeatureField,Loading_Circle,CommercialFeature);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,LegalDataSection,Loading_Circle);
        return this;
    }
    public P03_BusinessLicensePage FillMembersSection () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,MembersSection,Loading_Circle);
        Utility.CLICKONELEMENTS(driver,LegalStatus);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, Option1ForAnyList,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddMemberButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,MembersSection,Loading_Circle);
        return this;
    }

    public P03_BusinessLicensePage FillCapitalDataSection (String Capital) throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,CapitalData,Loading_Circle);
        Utility.WatingAndSENDKEYS(driver,CapitalDataField,Loading_Circle,Capital);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,CapitalData,Loading_Circle);
        return this;
    }



    public P03_BusinessLicensePage FillActivitiesDataSection (String TaxCardNum, String Description) throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ActivitiesData,Loading_Circle);
        Utility.WatingAndSENDKEYS(driver,TaxCardNumber,Loading_Circle,TaxCardNum);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,DataOfStartingActivity,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,FixedDateForAnyCalendar,Loading_Circle);
        Utility.WatingAndSENDKEYS(driver,ActivityDescription,Loading_Circle,Description);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ActivityField,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, Option1ForAnyList,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddActivityButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,DepartmentName,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, Option1ForAnyList,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddDepartNameButton,Loading_Circle);

        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ActivitiesData,Loading_Circle);
        return this;
    }

    public  P03_BusinessLicensePage FillBusinessLocationSection(String NameOfSt){
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,BusinessLocationSection,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,FirstِِAdministrativeField,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, Option1ForAnyList,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SecondAdministrativeField,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, Option1ForAnyList,Loading_Circle);
        Utility.WatingAndSENDKEYS(driver,StreetName,Loading_Circle,NameOfSt);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,BusinessLocationSection,Loading_Circle);
        return this;


    }
    public P03_BusinessLicensePage FillCertificateData () throws InterruptedException {
        Utility.CLICKONELEMENTS(driver,SendCertificateToOfficeField);
        Utility.CLICKONELEMENTS(driver,Option1ForAnyList);
        return this;
    }

    public P03_BusinessLicensePage FillAttachmentSectionAndConfirmRequest() throws InterruptedException, AWTException {

        List<WebElement> buttons = driver.findElements(AddAttachmentButton);
        if (!buttons.isEmpty()) {
//            int size = driver.findElements(AddAttachmentButton).size();
            for(int i = 0; i < buttons.size();i++) {
                WebElement btn = buttons.get(i);
                String path = "\"C:\\Users\\AcTiVE\\Desktop\\pexels-sulimansallehi-1704488 - Copy (4).jpg\"";
                Utility.WatingLoadingCircle_And_CLICKON_WebElement(driver, btn, Loading_Circle);
                StringSelection StringSelection = new StringSelection(path);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(StringSelection,null);

                Robot robot = new Robot();
                robot.delay(500);

                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                robot.delay(1000);

//                robot.keyPress(KeyEvent.VK_CONTROL);
//                robot.keyPress(KeyEvent.VK_C);
//                robot.keyRelease(KeyEvent.VK_C);
//                robot.keyRelease(KeyEvent.VK_CONTROL);
//                robot.delay(1000);

                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.delay(1000);

                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                robot.delay(7000);
            }
        }
        Utility.CLICKONELEMENTS(driver, ConfirmRequestButton);
        Utility.CLICKONELEMENTS(driver,AcceptRequestButtonOfPopUP);
        return this;
    }
    public P03_BusinessLicensePage ReviewRequest () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,EditButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AcceptRequestButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AcceptRequestButtonOfPopUP,Loading_Circle);
        return this;
    }

    public P04_InvoicesPage SendingRequestToCollect(){
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ViewButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SendToCollectButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SendToCollectButtonPouUp,Loading_Circle);
        return new P04_InvoicesPage(driver);
    }

    public P03_BusinessLicensePage ReceivingTheCertificate () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ViewButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RecieveCertificateButton,Loading_Circle);
        return this;
    }

}
