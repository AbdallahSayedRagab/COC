package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.awt.*;

public class p08_Requestforissuanceofanadministrativecertificate {
    private final WebDriver driver;
    private final By AddNewRequest_Button = By.xpath("//img[@src=\"../../../assets/images/circle-plus-white.svg\"]");
    private final By Loading_Circle = By.xpath("//mat-spinner[@mode=\"indeterminate\"]");
    private final By SearchingOfRecordNumField = By.xpath("(//input[@name=\"recordNumber\"])[1]");
    private final By FirstRowForRecordsResult = By.xpath("(//tr[@cdk-describedby-host=\"0\"]/td)[1]");
    private final By Personalidentificationnumber=By.xpath("//input-control[@formcontrolname='applicantNationalId']//input");
    private final By saveamedicalprovider=By.xpath("//button[@type='submit']");
    private final By TextArea=By.xpath("//input-control[@formcontrolname='certificateAbout']//textarea");
    private final By CertificateToMe=By.xpath("//input-control[@formcontrolname='certificateFor']//input");
    private final By ConfirmRequestButton = By.xpath("//span[contains(text(),\"تاكيد الطلب\")]");
    private final By AcceptRequestButtonOfPopUP = By.xpath("//button[contains(text(),\" اقبل الطلب\")]");
    private final By EditButton = By.xpath("(//li[.//*[local-name()='svg' and contains(@class,'edit-icon')]])[1]");
    private final By PullTheRequest = By.xpath("//label[contains(normalize-space(), 'اسحب الطلب')]//input[@type='checkbox']");
    private final By AcceptRequestButton = By.xpath("//span[contains(text(),\"أقبل الطلب\")]");
    private final By ViewButton = By.xpath("(//li[.//*[local-name()='svg' and contains(@class,'view-icon')]])[1]");
    private final By SendToCollectButton = By.xpath("//span[contains(normalize-space(), 'أرسال للتحصيل')]");
    private final By SendToCollectButtonPouUp = By.xpath("//button[contains(normalize-space(), 'ارسل الطلب للتحصيل')]");
    private final By RecieveCertificateButton = By.xpath("//button[contains(text(), \"تم التسليم\")]");

    public p08_Requestforissuanceofanadministrativecertificate(WebDriver driver) {
        this.driver=driver;
    }
    public p08_Requestforissuanceofanadministrativecertificate ClickOnAddNewRequest_Button () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddNewRequest_Button,Loading_Circle);
        return this;
    }
    public p08_Requestforissuanceofanadministrativecertificate SearchOnRecordNumber(String recordnumber) throws InterruptedException {
        Utility.WatingAndSENDKEYS(driver,SearchingOfRecordNumField,Loading_Circle,recordnumber);
        driver.findElement(SearchingOfRecordNumField).sendKeys(Keys.ENTER);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,FirstRowForRecordsResult,Loading_Circle);
        return this;
    }

    public p08_Requestforissuanceofanadministrativecertificate EnterThePersonalIdentificationNumber(String personalidentifier){
        Utility.SENDKEYS(driver,Personalidentificationnumber,personalidentifier);
        return this;
    }
    public p08_Requestforissuanceofanadministrativecertificate ClickOnSavedMedicalProvider() throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,saveamedicalprovider,Loading_Circle);
        return this;
    }

    public p08_Requestforissuanceofanadministrativecertificate ConfirmRequest() throws InterruptedException, AWTException {
        Utility.CLICKONELEMENTS(driver, ConfirmRequestButton);
        Utility.CLICKONELEMENTS(driver,AcceptRequestButtonOfPopUP);
        return this;
    }
    public p08_Requestforissuanceofanadministrativecertificate SendCeriticateTo(String text){
        Utility.SENDKEYS(driver,CertificateToMe,text);

        return this;
    }
    public p08_Requestforissuanceofanadministrativecertificate EnterTheAdministrativeCertificateDetails(String text){
        Utility.SENDKEYS(driver,TextArea,text);
        return this;

    }
    public p08_Requestforissuanceofanadministrativecertificate ReviewRequest () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,EditButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AcceptRequestButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, AcceptRequestButtonOfPopUP, Loading_Circle);

        return this;
    }

    public P04_InvoicesPage SendingRequestToCollect(){
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ViewButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SendToCollectButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SendToCollectButtonPouUp,Loading_Circle);
        return new P04_InvoicesPage(driver);
    }

    public p08_Requestforissuanceofanadministrativecertificate ReceivingTheCertificate () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ViewButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RecieveCertificateButton,Loading_Circle);
        return this;
    }


}
