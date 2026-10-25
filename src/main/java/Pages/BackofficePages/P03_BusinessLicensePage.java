package Pages.BackofficePages;

import Utilities.JsonUtil;
import Utilities.Utility;
import Utilities.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P03_BusinessLicensePage {
    public P03_BusinessLicensePage (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;
    private final By AddNewRequest_Button = By.xpath("//img[@src=\"../../../assets/images/circle-plus-white.svg\"]");
    private final By  FacilityNature_Choice  = By.xpath("//label[contains(text(), ' مركز رئيسى')]");
    private final By  CreateRequest_Button  = By.xpath("//button[contains(text(), ' انشاء الطلب ')]");
    private final By LegalDataSection = By.xpath("//span[contains(text(), \"البيانات القانونية\")]");
    private final By FacilityClassificationField = By.xpath("//label[contains(text(), \"تصنيف المنشاة\")]/following-sibling::*[1]");
    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");
    private final By FacilityTypeField = By.xpath("//label[contains(text(), \" نوع المنشأة \")]/following-sibling::*[1]");
    private final By TradeNameField = By.xpath("//label[normalize-space(text())='الاسم التجاري']/following-sibling::*[1]");
    private final By CommercialFeatureField = By.xpath("//label[normalize-space(text())='السمة التجارية']/following-sibling::*[1]");
    private final By MembersSection = By.xpath("//span[contains(text(), \"الاعضاء\")]");
    private final By LegalStatus = By.xpath("//label[normalize-space(text())='الصفة القانونية']/following-sibling::*[1]");
    private final By AddMemberButton = By.xpath("//span[contains(text(), \" إضافة عضو \")]");
    private final By CapitalData = By.xpath("//span[contains(text(), 'بيانات رأس المال')]");
    private final By CapitalDataField = By.xpath("//label[normalize-space(text())='رأس المال المسدد(ج.م)']/following-sibling::*[1]");
    private final By ActivitiesData = By.xpath("//span[contains(text(), 'بيانات النشاط')]");
    private final By TaxCardNumber = By.xpath("//label[normalize-space(text())='رقم التسجيل الضريبى']/following-sibling::*[1]");
    private final By DataOfStartingActivity = By.xpath("//label[normalize-space(text())='تاريخ بدء النشاط']/following-sibling::*[1]");
    private final By FixedDateForAnyCalendar = By.xpath("//td[@role='gridcell']/span[contains(text(), \"١٥\")]");
    private final By ActivityDescription = By.xpath("//label[normalize-space(text())='وصف النشاط طبقا للبطاقة الضريبية']/following-sibling::*[1]");
    private final By ActivityField = By.xpath("//label[normalize-space(text())='النشاط']/following-sibling::*[1]");
    private final By AddActivityButton = By.xpath("//button[contains(text(), ' اضافة نشاط ')]");
    private final By DepartmentName = By.xpath("//label[normalize-space(text())='اسم الشعبة']/following-sibling::*[1]");
    private final By AddDepartNameButton = By.xpath("//button[contains(text(), ' اضافة شعبة')]");
    private final By BusinessLocationSection = By.xpath("//span[contains(text(), 'محل النشاط')]");
    private final By FirstِِAdministrativeField = By.xpath("//label[normalize-space(text())='تقسيم اداري اول']/following-sibling::*[1]");
    private final By SecondAdministrativeField = By.xpath("//label[normalize-space(text())='تقسيم اداري ثان']/following-sibling::*[1]");
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
    private final By RecieveCertificateButton = By.xpath("//button[contains(text(), \"تسليم الشهادة\")]");
    private final By RequestNumber = By.xpath("(//td/a[@class=\"ng-star-inserted\"])[1]");
    private final By fff = By.xpath("");

    public P03_BusinessLicensePage ClickOnAddNewRequest_Button () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddNewRequest_Button);
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

    public P03_BusinessLicensePage FillAndSaveDataOfApplicant (String ID , String Phone) throws InterruptedException {
        Utility.FillAndSaveDataOfApplicant(driver,ID,Phone);
        return this;
    }

    public P03_BusinessLicensePage FillLegalDataSection (String TradeName , String CommercialFeature ) throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,LegalDataSection);
        Utility.CLICKONELEMENTS(driver,FacilityClassificationField);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, Option1ForAnyList);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,FacilityTypeField);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, Option1ForAnyList);
        Utility.WatingAndSENDKEYS(driver,TradeNameField,TradeName);
        Utility.WatingAndSENDKEYS(driver,CommercialFeatureField,CommercialFeature);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,LegalDataSection);
        return this;
    }

    public P03_BusinessLicensePage FillMembersSection () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,MembersSection);
        Utility.CLICKONELEMENTS(driver,LegalStatus);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, Option1ForAnyList);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddMemberButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,MembersSection);
        return this;
    }

    public P03_BusinessLicensePage FillCapitalDataSection (String Capital) throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,CapitalData);
        Utility.WatingAndSENDKEYS(driver,CapitalDataField,Capital);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,CapitalData);
        return this;
    }

    public P03_BusinessLicensePage FillActivitiesDataSection (String TaxCardNum, String Description) throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ActivitiesData);
        Utility.WatingAndSENDKEYS(driver,TaxCardNumber,TaxCardNum);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,DataOfStartingActivity);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,FixedDateForAnyCalendar);
        Utility.WatingAndSENDKEYS(driver,ActivityDescription,Description);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ActivityField);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, Option1ForAnyList);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddActivityButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,DepartmentName);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, Option1ForAnyList);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddDepartNameButton);

        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ActivitiesData);
        return this;
    }

    public  P03_BusinessLicensePage FillBusinessLocationSection(String NameOfSt){
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,BusinessLocationSection);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,FirstِِAdministrativeField);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, Option1ForAnyList);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SecondAdministrativeField);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, Option1ForAnyList);
        Utility.WatingAndSENDKEYS(driver,StreetName,NameOfSt);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,BusinessLocationSection);
        return this;


    }

    public P03_BusinessLicensePage FillCertificateData () throws InterruptedException {
        Utility.CLICKONELEMENTS(driver,SendCertificateToOfficeField);
        Utility.CLICKONELEMENTS(driver,Option1ForAnyList);
        return this;
    }

    public P03_BusinessLicensePage FillAttachmentSection() {
        Utility.UploadAttachments(driver, AddAttachmentButton);
        return this;
    }

    public P03_BusinessLicensePage ConfirmRequest() throws InterruptedException {
        Utility.CLICKONELEMENTS(driver, ConfirmRequestButton);
        Utility.CLICKONELEMENTS(driver,AcceptRequestButtonOfPopUP);
        return this;
    }

    public P03_BusinessLicensePage SaveRequestNumberInJsonFile() {

        String requestNumber = Utility.GETTEXT(driver,RequestNumber);
        JsonUtil.writeValue("BusinessLicenseRequestNumber", requestNumber);
        JsonUtil.writeValue("RecordNumber", requestNumber);

        return this;
    }


    public P03_BusinessLicensePage ReviewRequest () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,EditButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AcceptRequestButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AcceptRequestButtonOfPopUP);
        return this;
    }

    public P04_InvoicesPage SendingRequestToCollect(){
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ViewButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SendToCollectButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SendToCollectButtonPouUp);
        return new P04_InvoicesPage(driver);
    }

    public P03_BusinessLicensePage ReceivingTheCertificate () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ViewButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RecieveCertificateButton);
        return this;
    }

}
