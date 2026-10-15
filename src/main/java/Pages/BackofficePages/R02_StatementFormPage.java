package Pages.BackofficePages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class R02_StatementFormPage {
    public R02_StatementFormPage(WebDriver driver) { this.driver = driver;}
    private WebDriver driver;

    private final By AddNewStatement_Button = By.xpath("//a[span[normalize-space(text())='أضافة طلب']]");

    // الاستعلام من خلال الاسم التجاري
    private final By CommercialNameSearch_Field = By.xpath("//input[@formcontrolname='statementRequestTerm']");
    private final By FirstRecordInSearchResults = By.xpath("(//table//input[@type='checkbox'])[1]");

    // بيانات الإفادة
    private final By Applicant_Field = By.xpath("//label[normalize-space(text())='مقدم الطلب']/following-sibling::*[1]");
    private final By RequestingEntity_Field = By.xpath("//label[normalize-space(text())='الجهة الطالبة للإفادة']/following-sibling::*[1]");
    private final By StatementNotes_Field = By.xpath("//label[normalize-space(text())='ملاحظات سيتم ادراجها في الإفادة']/following-sibling::*[1]");
    private final By ConfirmStatement_Button = By.xpath("//button[text()='تأكيد الإفادة']");
    private final By PrintStatement_Button = By.xpath("//button[span[text()='طباعة النموذج']]");

    public R02_StatementFormPage AddNewStatementForm () {
        Utility.WaitUntilReady_And_CLICK(driver,AddNewStatement_Button);
        return this;
    }

    public R02_StatementFormPage SearchByCommercialNameAndSelectFirstRecord (String CommercialName) {
        Utility.WaitUntilReady_And_SENDKEYS(driver,CommercialNameSearch_Field,CommercialName);
        // البحث مش بيشتغل غير بعد Enter
        driver.findElement(CommercialNameSearch_Field).sendKeys(Keys.ENTER);
        Utility.WaitUntilReady_And_CLICK(driver,FirstRecordInSearchResults);
        return this;
    }

    public R02_StatementFormPage FillStatementDataAndConfirm (String ApplicantName , String RequestingEntity , String Notes) {
        Utility.WaitUntilReady_And_SENDKEYS(driver,Applicant_Field,ApplicantName);
        Utility.WaitUntilReady_And_SENDKEYS(driver,RequestingEntity_Field,RequestingEntity);
        Utility.WaitUntilReady_And_SENDKEYS(driver,StatementNotes_Field,Notes);
        Utility.WaitUntilReady_And_CLICK(driver,ConfirmStatement_Button);
        return this;
    }

    public R02_StatementFormPage PrintStatementForm (String ScreenShotName) throws IOException {
        Utility.PrintAndScreenShot(driver,PrintStatement_Button,ScreenShotName);
        return this;
    }

}
