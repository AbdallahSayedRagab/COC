package Pages.BackofficePages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P14_RequestsAndCertificatesMailPage {
    public P14_RequestsAndCertificatesMailPage(WebDriver driver) {
        this.driver = driver;
    }
    private WebDriver driver;

    private final By AddRequestButton = By.xpath("//span[@class=\"asset-icon\" and contains(text(),'أضافة طلب ')]");
    private final By SelectAdministrationRequestFromPopUp = By.xpath("//input[@type=\"radio\" and @id=\"طلب اصدار شهادة إدارية\"]");
    private final By CreateRequestFromPopUp = By.xpath("//button[text() = ' انشاء طلب ']");



    public p08_Requestforissuanceofanadministrativecertificate AddAdministrationRequest (){
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddRequestButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SelectAdministrationRequestFromPopUp);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,CreateRequestFromPopUp);
        return new p08_Requestforissuanceofanadministrativecertificate (driver);
    }


}
