package Pages.BackofficePages;

import Utilities.BackofficeBasedFunctions;
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
    private final By fff = By.xpath("");

    public P03_BusinessLicensePage ClickOnAddNewRequest_Button () throws InterruptedException {
        BackofficeBasedFunctions.ClickOnAddNewRequest_Button(driver);
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
        BackofficeBasedFunctions.FillAndSaveDataOfApplicant(driver,ID,Phone);
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

    // نفس اللي فوق بس بيختار تصنيف المنشأة بالاسم (فردية / الاموال / الاشخاص) بدل اول اختيار
    public P03_BusinessLicensePage FillLegalDataSection (String TradeName , String CommercialFeature , String Classification) throws InterruptedException {
        By ClassificationChoice = By.xpath("//div[@role='option'][contains(normalize-space(),'" + Classification + "')]");
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,LegalDataSection);
        Utility.CLICKONELEMENTS(driver,FacilityClassificationField);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, ClassificationChoice);
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

    // سكشن الاعضاء اسمه بيتغير حسب تصنيف المنشأة (المالك / الشركاء / الإدارة) وكذلك زرار الاضافة
    public P03_BusinessLicensePage FillMembersSection (String SectionName , String AddButtonText) throws InterruptedException {
        By Section = By.xpath("//span[normalize-space()='" + SectionName + "']");
        By AddButton = By.xpath("//span[normalize-space()='" + AddButtonText + "']");
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,Section);
        Utility.CLICKONELEMENTS(driver,LegalStatus);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, Option1ForAnyList);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,Section);
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

    // في الشركات خانة التاريخ اسمها "تاريخ تأسيس الشركة" بدل "تاريخ بدء النشاط" فبنبعت اسمها
    public P03_BusinessLicensePage FillActivitiesDataSection (String TaxCardNum, String Description, String StartDateLabel) throws InterruptedException {
        By StartDateField = By.xpath("//label[normalize-space(text())='" + StartDateLabel + "']/following-sibling::*[1]");
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ActivitiesData);
        Utility.WatingAndSENDKEYS(driver,TaxCardNumber,TaxCardNum);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,StartDateField);
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
        BackofficeBasedFunctions.FillCertificateData(driver);
        return this;
    }

    public P03_BusinessLicensePage FillAttachmentSection() {
        BackofficeBasedFunctions.FillAttachmentSection(driver);
        return this;
    }

    public P03_BusinessLicensePage ConfirmRequest() throws InterruptedException {
        BackofficeBasedFunctions.ConfirmRequest(driver);
        return this;
    }

    public P03_BusinessLicensePage SaveRequestNumberInJsonFile() {
        // رقم طلب المزاولة هو نفسه رقم السجل
        String requestNumber = BackofficeBasedFunctions.SaveRequestNumberInJsonFile(driver,"BusinessLicenseRequestNumber");
        JsonUtil.writeValue("RecordNumber", requestNumber);
        return this;
    }


    public P03_BusinessLicensePage ReviewRequest () throws InterruptedException {
        BackofficeBasedFunctions.ReviewRequest(driver);
        return this;
    }

    public P04_InvoicesPage SendingRequestToCollect(){
        BackofficeBasedFunctions.SendingRequestToCollect(driver);
        return new P04_InvoicesPage(driver);
    }

    public P03_BusinessLicensePage ReceivingTheCertificate () throws InterruptedException {
        BackofficeBasedFunctions.ReceivingTheCertificate(driver);
        return this;
    }

}
