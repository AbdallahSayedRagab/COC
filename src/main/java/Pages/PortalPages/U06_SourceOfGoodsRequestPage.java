package Pages.PortalPages;

import Utilities.PortalBasedFunctions;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class U06_SourceOfGoodsRequestPage {
    public U06_SourceOfGoodsRequestPage(WebDriver driver) { this.driver = driver;}
    private WebDriver driver;

    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");

    // 2- البيانات الاساسية
    private final By ImporterNameField = By.xpath("//label[normalize-space(text())='اسم المستورد']/following-sibling::*[1]");
    private final By ImporterCountryField = By.xpath("//label[normalize-space(text())='دولة المستورد']/following-sibling::*[1]");
    private final By ImporterAddressField = By.xpath("//label[normalize-space(text())='عنوان المستورد']/following-sibling::*[1]");
    private final By SameProducingCompanyChoice = By.xpath("(//input[@formcontrolname='sameCompany'])[1]");
    private final By SameCompanyAddressChoice = By.xpath("(//input[@formcontrolname='sameAddress'])[1]");
    private final By CountryOfOriginField = By.xpath("//label[normalize-space(text())='دولة المنشأ']/following-sibling::*[1]");
    private final By NextButtonOfBasicData = By.xpath("//div[@id='test-l-2']//button[contains(text(),'التالى')]");

    // 3- بيانات الشحن (مش اجباري)
    private final By NextButtonOfShippingData = By.xpath("//div[@id='test-l-3']//button[contains(text(),'التالى')]");

    // 4- الاصناف
    private final By ItemDescriptionField = By.xpath("//label[normalize-space(text())='وصف الصنف']/following-sibling::*[1]");
    private final By MarksAndNumbersField = By.xpath("//label[normalize-space(text())='العلامات والارقام']/following-sibling::*[1]");
    private final By ParcelTypeField = By.xpath("//label[normalize-space(text())='نوعية الطرود']/following-sibling::*[1]");
    private final By NumberOfParcelsField = By.xpath("//label[normalize-space(text())='عدد الطرود']/following-sibling::*[1]");
    private final By NetWeightField = By.xpath("//label[normalize-space(text())='الوزن الصافي']/following-sibling::*[1]");
    private final By TotalWeightField = By.xpath("//label[normalize-space(text())='الوزن الاجمالي']/following-sibling::*[1]");
    private final By WeightUnitField = By.xpath("//label[normalize-space(text())='وحدة الوزن']/following-sibling::*[1]");
    private final By TotalValueField = By.xpath("//label[normalize-space(text())='القيمة الاجمالية']/following-sibling::*[1]");
    private final By CurrencyField = By.xpath("//label[normalize-space(text())='العملة']/following-sibling::*[1]");
    private final By AddItemButton = By.xpath("//button[contains(text(),'اضافة صنف')]");
    private final By NextButtonOfItems = By.xpath("//div[@id='test-l-4']//button[contains(text(),'التالى')]");

    // 5- بيانات الشهادة
    private final By SendCertificateToBranchField = By.xpath("//label[normalize-space(text())='تقديم الشهادة الى فرع']/following-sibling::*[1]");
    private final By CertificatesCountField = By.xpath("//label[normalize-space(text())='عدد الشهادات']/following-sibling::*[1]");
    private final By SignatureAuthenticationField = By.xpath("//label[normalize-space(text())='تصديق توقيع']/following-sibling::*[1]");
    private final By CertificateLanguageField = By.xpath("//label[normalize-space(text())='لغة الشهادة']/following-sibling::*[1]");
    private final By NextButtonOfCertificateData = By.xpath("//div[@id='test-l-5']//button[contains(text(),'التالى')]");

    public U06_SourceOfGoodsRequestPage StartTheService () {
        PortalBasedFunctions.StartTheService(driver);
        return this;
    }

    // 1- بيانات السجل (الجدول بيعرض سجلات الحساب لوحده)
    public U06_SourceOfGoodsRequestPage SelectFirstRecord () {
        PortalBasedFunctions.SelectFirstRecord(driver);
        return this;
    }

    public U06_SourceOfGoodsRequestPage FillBasicData (String ImporterName , String ImporterAddress , String CountryOfOrigin) {
        // المرحلة دي بتحمل بيانات السجل بعد ما تظهر وبتمسح اسم المستورد لو اتكتب بدري
        Utility.WaitUntilReady_And_SENDKEYS_AndCheckValue(driver,ImporterNameField,ImporterName);
        Utility.WaitUntilReady_And_CLICK(driver,ImporterCountryField);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        Utility.WaitUntilReady_And_SENDKEYS(driver,ImporterAddressField,ImporterAddress);
        Utility.WaitUntilReady_And_CLICK(driver,SameProducingCompanyChoice);
        Utility.WaitUntilReady_And_CLICK(driver,SameCompanyAddressChoice);
        Utility.WaitUntilReady_And_SENDKEYS(driver,CountryOfOriginField,CountryOfOrigin);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfBasicData);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfShippingData);
        return this;
    }

    public U06_SourceOfGoodsRequestPage FillItemSection (String Description , String Marks , String NumberOfParcels ,
                                                         String NetWeight , String TotalWeight , String TotalValue) {
        Utility.WaitUntilReady_And_SENDKEYS(driver,ItemDescriptionField,Description);
        Utility.WaitUntilReady_And_SENDKEYS(driver,MarksAndNumbersField,Marks);
        Utility.WaitUntilReady_And_CLICK(driver,ParcelTypeField);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        Utility.WaitUntilReady_And_SENDKEYS(driver,NumberOfParcelsField,NumberOfParcels);
        Utility.WaitUntilReady_And_SENDKEYS(driver,NetWeightField,NetWeight);
        Utility.WaitUntilReady_And_SENDKEYS(driver,TotalWeightField,TotalWeight);
        Utility.WaitUntilReady_And_CLICK(driver,WeightUnitField);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        Utility.WaitUntilReady_And_SENDKEYS(driver,TotalValueField,TotalValue);
        Utility.WaitUntilReady_And_CLICK(driver,CurrencyField);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        Utility.WaitUntilReady_And_CLICK(driver,AddItemButton);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfItems);
        return this;
    }

    public U06_SourceOfGoodsRequestPage FillCertificateData (String Branch , String CertificatesCount) {
        By BranchChoice = By.xpath("//div[@role='option'][normalize-space()='" + Branch + "']");
        Utility.WaitUntilReady_And_CLICK(driver,SendCertificateToBranchField);
        Utility.WaitUntilReady_And_CLICK(driver,BranchChoice);
        // عدد الشهادات بيبقى فيه 0 جاهز فلازم يتمسح الاول
        driver.findElement(CertificatesCountField).clear();
        Utility.WaitUntilReady_And_SENDKEYS(driver,CertificatesCountField,CertificatesCount);
        Utility.WaitUntilReady_And_CLICK(driver,SignatureAuthenticationField);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        Utility.WaitUntilReady_And_CLICK(driver,CertificateLanguageField);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfCertificateData);
        return this;
    }

    // 6- المرفقات: لو فيه نوع مرفق بيرفع عليه الملف ولو مفيش بيعدي
    public U06_SourceOfGoodsRequestPage FillAttachmentSection () {
        PortalBasedFunctions.FillAttachmentSection(driver,6);
        return this;
    }

    public U06_SourceOfGoodsRequestPage SendTheRequestAndSaveRequestNumber () {
        PortalBasedFunctions.SendTheRequestAndSaveRequestNumber(driver,6,"PortalSourceOfGoodsRequestNumber");
        return this;
    }

}
