package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P02_Dashboard {
    public P02_Dashboard (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;
    private final By RequestsIcon = By.xpath("//img[@src=\"../../assets/images/certificates-icon.svg\"]");
    private final By BusinessLicenseRequest_FSM = By.xpath("//a[@href=\"/requests/issueCommerciallist?title=%D8%B7%D9%84%D8%A8%20%D8%B4%D9%87%D8%A7%D8%AF%D8%A9%20%D8%AA%D8%B1%D8%AE%D9%8A%D8%B5%20%D9%85%D8%B2%D8%A7%D9%88%D9%84%D8%A9%20%D8%AA%D8%AC%D8%A7%D8%B1%D8%A9\"]");
    private final By LoadingCircle = By.xpath("//mat-spinner[@mode=\"indeterminate\"]");
    private final By EditRequest_FSM = By.xpath("//a[contains(@href, '/requests/editCommercialList')]");
    public P03_BusinessLicensePage Select_BusinessLicense () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon,LoadingCircle);
        Utility.CLICKONELEMENTS(driver,BusinessLicenseRequest_FSM);
        return new P03_BusinessLicensePage(driver);
    }
    public P05_EditRequestPage Select_EditRequest () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon,LoadingCircle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,EditRequest_FSM,LoadingCircle);
        return new P05_EditRequestPage(driver);
    }
}
