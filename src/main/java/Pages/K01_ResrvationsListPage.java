package Pages;

import Utilities.Utility;
import Utilities.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilities.Scrolling.ScrollToElement;
public class K01_ResrvationsListPage {
    public K01_ResrvationsListPage(WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By Add_Reservation_Button = By.xpath("//button[contains (text(), 'أضافة حجز ')]");
    private final By Applicant_Name_Field = By.xpath("//label[normalize-space(text())='اسم مقدم الطلب']/following-sibling::*[1]");
    private final By NumberOfApplicant_Field = By.xpath("//label[normalize-space(text())='رقم هاتف مقدم الطلب']/following-sibling::*[1]");
    private final By NationalityOfApplicant_Field = By.xpath("//label[normalize-space(text())='جنسية مقدم الطلب']/following-sibling::*[1]");
    private final By ApplicantIDType_Field = By.xpath("//label[normalize-space(text())='نوع تحقيق شخصية مقدم الطلب']/following-sibling::*[1]");
    private final By ApplicantID_Field = By.xpath("//label[normalize-space(text())='رقم تحقيق شخصية مقدم الطلب']/following-sibling::*[1]");
    private final By ApplicantAttribute_Field = By.xpath("//label[normalize-space(text())='صفة مقدم الطلب']/following-sibling::*[1]");
    private final By RequestType_Field = By.xpath("//label[normalize-space(text())='نوع الطلب']/following-sibling::*[1]");
    private final By CertificateType_Field = By.xpath("//label[normalize-space(text())='نوع الشهادة']/following-sibling::*[1]");


    private final By PersonalIdentificationNumber_Field = By.xpath("//label[normalize-space(text())='رقم تحقيق شخصية مقدم الطلب']/following-sibling::*[1]");

}
