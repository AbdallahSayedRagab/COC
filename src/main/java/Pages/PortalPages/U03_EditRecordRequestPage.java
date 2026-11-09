package Pages.PortalPages;

import Utilities.PortalBasedFunctions;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class U03_EditRecordRequestPage {
    public U03_EditRecordRequestPage(WebDriver driver) { this.driver = driver;}
    private WebDriver driver;

    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");

    // 2- التعديلات المطلوبة
    private final By LegalDataChangeCheckBox = By.xpath("//input[@formcontrolname='isLegalInfoChange']");
    private final By NextButtonOfRequiredChanges = By.xpath("//div[@id='test-l-2']//button[contains(text(),'التالى')]");

    // 3- بيانات الشهادة
    private final By SendCertificateToBranchField = By.xpath("//label[normalize-space(text())='تقديم الشهادة الى فرع']/following-sibling::*[1]");
    private final By SendCertificateToGovernorateField = By.xpath("//label[normalize-space(text())='تقديم الشهادة الى محافظة']/following-sibling::*[1]");
    private final By CommercialRegistryOfficeField = By.xpath("//div[@id='test-l-3']//label[normalize-space(text())='مكتب سجل تجاري']/following-sibling::*[1]");
    private final By SaveButton = By.xpath("//div[@id='test-l-3']//button[contains(text(),'حفظ')]");

    public U03_EditRecordRequestPage StartTheService () {
        PortalBasedFunctions.StartTheService(driver);
        return this;
    }

    // 1- بيانات السجل (الجدول بيعرض سجلات الحساب لوحده)
    public U03_EditRecordRequestPage SelectFirstRecord () {
        PortalBasedFunctions.SelectFirstRecord(driver);
        return this;
    }

    public U03_EditRecordRequestPage SelectLegalDataToEdit () {
        Utility.WaitUntilReady_And_CLICK(driver,LegalDataChangeCheckBox);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfRequiredChanges);
        return this;
    }

    public U03_EditRecordRequestPage FillCertificateData (String Branch , String Governorate) {
        // اول محافظة في الليستة مفيهاش مكاتب سجل فبنختار المحافظة بالاسم
        By BranchChoice = By.xpath("//div[@role='option'][normalize-space()='" + Branch + "']");
        By GovernorateChoice = By.xpath("//div[@role='option'][normalize-space()='" + Governorate + "']");
        Utility.WaitUntilReady_And_CLICK(driver,SendCertificateToBranchField);
        Utility.WaitUntilReady_And_CLICK(driver,BranchChoice);
        Utility.WaitUntilReady_And_CLICK(driver,SendCertificateToGovernorateField);
        Utility.WaitUntilReady_And_CLICK(driver,GovernorateChoice);
        Utility.WaitUntilReady_And_CLICK(driver,CommercialRegistryOfficeField);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        Utility.WaitUntilReady_And_CLICK(driver,SaveButton);
        return this;
    }

    // 4- المرفقات (البطاقة الشخصية اجباري)
    public U03_EditRecordRequestPage FillAttachmentSection () {
        PortalBasedFunctions.FillAttachmentSection(driver,4);
        return this;
    }

    public U03_EditRecordRequestPage SendTheRequestAndSaveRequestNumber () {
        PortalBasedFunctions.SendTheRequestAndSaveRequestNumber(driver,4,"PortalEditRecordRequestNumber");
        return this;
    }

}
