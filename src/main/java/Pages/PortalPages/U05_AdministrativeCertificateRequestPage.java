package Pages.PortalPages;

import Utilities.PortalBasedFunctions;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class U05_AdministrativeCertificateRequestPage {
    public U05_AdministrativeCertificateRequestPage(WebDriver driver) { this.driver = driver;}
    private WebDriver driver;

    // 2- تفاصيل شهادة ادارية
    private final By CertificateDetailsField = By.xpath("//label[normalize-space(text())='تفاصيل الشهادة الادارية']/following-sibling::*[1]");
    private final By NextButtonOfCertificateDetails = By.xpath("//div[@id='test-l-2']//button[contains(text(),'التالى')]");

    // 3- المرفقات
    private final By NextButtonOfAttachments = By.xpath("//div[@id='test-l-3']//button[contains(text(),'التالى')]");

    // 4- بيانات الشهادة
    private final By SendCertificateToBranchField = By.xpath("//label[normalize-space(text())='تقديم الشهادة الى فرع']/following-sibling::*[1]");
    private final By SubmitToField = By.xpath("//label[normalize-space(text())='للتقديم الي']/following-sibling::*[1]");

    public U05_AdministrativeCertificateRequestPage StartTheService () {
        PortalBasedFunctions.StartTheService(driver);
        return this;
    }

    // 1- بيانات السجل (الجدول بيعرض سجلات الحساب لوحده)
    public U05_AdministrativeCertificateRequestPage SelectFirstRecord () {
        PortalBasedFunctions.SelectFirstRecord(driver);
        return this;
    }

    public U05_AdministrativeCertificateRequestPage FillCertificateDetails (String Details) {
        Utility.WaitUntilReady_And_SENDKEYS(driver,CertificateDetailsField,Details);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfCertificateDetails);
        return this;
    }

    // لو فيه نوع مرفق بيرفع عليه الملف ولو مفيش بيعدي
    public U05_AdministrativeCertificateRequestPage FillAttachmentSection () {
        PortalBasedFunctions.FillAttachmentSection(driver,3);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfAttachments);
        return this;
    }

    public U05_AdministrativeCertificateRequestPage FillCertificateData (String Branch , String SubmitTo) {
        By BranchChoice = By.xpath("//div[@role='option'][normalize-space()='" + Branch + "']");
        Utility.WaitUntilReady_And_CLICK(driver,SendCertificateToBranchField);
        Utility.WaitUntilReady_And_CLICK(driver,BranchChoice);
        Utility.WaitUntilReady_And_SENDKEYS(driver,SubmitToField,SubmitTo);
        return this;
    }

    public U05_AdministrativeCertificateRequestPage SendTheRequestAndSaveRequestNumber () {
        PortalBasedFunctions.SendTheRequestAndSaveRequestNumber(driver,4,"PortalAdministrativeCertificateRequestNumber");
        return this;
    }

}
