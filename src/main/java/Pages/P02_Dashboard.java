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
    private final By RemoveRequest_FSM = By.xpath("//a[contains(@href, '/requests/removeCommercialList')]");
    private final By  Sourceofgoods=By.xpath("//a[@href=\"/requests/goodsOriginrequestslist?title=%D8%B7%D9%84%D8%A8%20%D8%B4%D9%87%D8%A7%D8%AF%D8%A9%20%D9%85%D8%B5%D8%AF%D8%B1%D8%B1%20%D8%A8%D8%B6%D8%A7%D8%B9%D8%A9\"]");
    private final By  Administrativecertificate =By.xpath("//a[@href=\"/requests/issueManagementList?title=%D8%B7%D9%84%D8%A8%20%D8%A7%D8%B5%D8%AF%D8%A7%D8%B1%20%D8%B4%D9%87%D8%A7%D8%AF%D8%A9%20%D8%A5%D8%AF%D8%A7%D8%B1%D9%8A%D8%A9\"]");
    private final By NegativeRequest_FSM = By.xpath("//a[contains(@href, '/requests/negative-request-list')]");


    public P03_BusinessLicensePage Select_BusinessLicense () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon,LoadingCircle);
        Utility.CLICKONELEMENTS(driver,BusinessLicenseRequest_FSM);
        return new P03_BusinessLicensePage(driver);
    }
    public p07_sourcedcode selectthesourcecode() throws InterruptedException {

        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon,LoadingCircle);
        Utility.CLICKONELEMENTS(driver,Sourceofgoods);
        return new p07_sourcedcode(driver);


    }
    public P05_EditRequestPage Select_EditRequest () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon,LoadingCircle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,EditRequest_FSM,LoadingCircle);
        return new P05_EditRequestPage(driver);
    }
    public P06_RemoveRecordPage Select_RemoveRequest () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon,LoadingCircle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RemoveRequest_FSM,LoadingCircle);
        return new P06_RemoveRecordPage(driver);
    }
    public p08_Requestforissuanceofanadministrativecertificate selecttheAdministrativecertificate() throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon,LoadingCircle);
        Utility.CLICKONELEMENTS(driver,Administrativecertificate);
        return new p08_Requestforissuanceofanadministrativecertificate(driver);

    }
    public P10_NegativeCertificteRequest Select_NegativeRequest() throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon,LoadingCircle);
        Utility.CLICKONELEMENTS(driver,NegativeRequest_FSM);
        return new P10_NegativeCertificteRequest(driver);

    }

}
