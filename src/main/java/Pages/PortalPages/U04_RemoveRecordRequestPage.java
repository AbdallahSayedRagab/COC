package Pages.PortalPages;

import Utilities.PortalBasedFunctions;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class U04_RemoveRecordRequestPage {
    public U04_RemoveRecordRequestPage(WebDriver driver) { this.driver = driver;}
    private WebDriver driver;

    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");

    // 2- تفاصيل سبب المحو
    private final By RemovalReasonField = By.xpath("//label[normalize-space(text())='سبب المحو']/following-sibling::*[1]");
    private final By NextButtonOfRemovalReason = By.xpath("//div[@id='test-l-2']//button[contains(text(),'التالى')]");

    // 3- بيانات الشهادة
    private final By SendCertificateToBranchField = By.xpath("//label[normalize-space(text())='تقديم الشهادة الى فرع']/following-sibling::*[1]");
    private final By SendCertificateToGovernorateField = By.xpath("//label[normalize-space(text())='تقديم الشهادة الى محافظة']/following-sibling::*[1]");
    private final By CommercialRegistryOfficeField = By.xpath("//div[@id='test-l-3']//label[normalize-space(text())='مكتب سجل تجاري']/following-sibling::*[1]");
    private final By NextButtonOfCertificateData = By.xpath("//div[@id='test-l-3']//button[contains(text(),'التالى')]");

    public U04_RemoveRecordRequestPage StartTheService () {
        PortalBasedFunctions.StartTheService(driver);
        return this;
    }

    // 1- بيانات السجل (الجدول بيعرض سجلات الحساب لوحده)
    public U04_RemoveRecordRequestPage SelectFirstRecord () {
        PortalBasedFunctions.SelectFirstRecord(driver);
        return this;
    }

    public U04_RemoveRecordRequestPage FillRemovalReason (String Reason) {
        Utility.WaitUntilReady_And_SENDKEYS(driver,RemovalReasonField,Reason);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfRemovalReason);
        return this;
    }

    public U04_RemoveRecordRequestPage FillCertificateData (String Branch , String Governorate) {
        // اول محافظة في الليستة مفيهاش مكاتب سجل فبنختار المحافظة بالاسم
        By BranchChoice = By.xpath("//div[@role='option'][normalize-space()='" + Branch + "']");
        By GovernorateChoice = By.xpath("//div[@role='option'][normalize-space()='" + Governorate + "']");
        Utility.WaitUntilReady_And_CLICK(driver,SendCertificateToBranchField);
        Utility.WaitUntilReady_And_CLICK(driver,BranchChoice);
        Utility.WaitUntilReady_And_CLICK(driver,SendCertificateToGovernorateField);
        Utility.WaitUntilReady_And_CLICK(driver,GovernorateChoice);
        Utility.WaitUntilReady_And_CLICK(driver,CommercialRegistryOfficeField);
        Utility.WaitUntilReady_And_CLICK(driver,Option1ForAnyList);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfCertificateData);
        return this;
    }

    // 4- المرفقات (البطاقة الشخصية اجباري)
    public U04_RemoveRecordRequestPage FillAttachmentSection () {
        PortalBasedFunctions.FillAttachmentSection(driver,4);
        return this;
    }

    public U04_RemoveRecordRequestPage SendTheRequestAndSaveRequestNumber () {
        PortalBasedFunctions.SendTheRequestAndSaveRequestNumber(driver,4,"PortalRemoveRecordRequestNumber");
        return this;
    }

}
