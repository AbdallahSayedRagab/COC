package Pages.BackofficePages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class V01_CreateRecordEffectPage {
    public V01_CreateRecordEffectPage(WebDriver driver) { this.driver = driver;}
    private WebDriver driver;


    private final By RecordsListIcon = By.xpath("(//span[@class=\"ng-star-inserted\"])[3]");
    private final By EffectsPageButton = By.xpath("//a[contains(@href, '/records/actions-on-records/list')]");
    private final By AddEffectButton = By.xpath("//span[contains(text(), 'التأثير علي سجل')]");
    private final By CreateRecordButton = By.id("create");
    private final By ConfirmingEffectButton = By.xpath("//button[contains(text(), ' انشاء التأثير ')]");
    private final By RecordOffice = By.xpath("//label[normalize-space(text())='مكتب السجل']/following-sibling::*[1]");
    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");
    private final By RecordNumField = By.xpath("//label[normalize-space(text())='رقم السجل']/following-sibling::*[1]");
    private final By SignatureNumberInRecordField = By.xpath("//label[normalize-space(text())='رقم التأشير في السجل']/following-sibling::*[1]");
    private final By SearchingOfCertificateNumField = By.xpath("//input[@name=\"recordNumber\"]");
    private final By SearchButton = By.xpath("//button/span[contains(text(), 'بحث')]");
    private final By FirstRowForCertificateResult = By.xpath("(//tr[@class=\"ng-star-inserted\"]/td)[1]");
    private final By SendingEffectButton = By.xpath("//button/span[contains(text(), ' ارسال')]");
    private final By OkButtonOfPopUp = By.xpath("//button[contains(text(), 'OK')]");
    private final By AcceptButton = By.xpath("//span[contains(text(), ' موافقة')]");
    private final By ApprovedButton = By.xpath("//span[contains(text(), ' اعتماد')]");



    public V01_CreateRecordEffectPage AddNewEffectToCreateNewRecord() {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, RecordsListIcon);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, EffectsPageButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, AddEffectButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, CreateRecordButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, ConfirmingEffectButton);
    return this;
    }

    public V01_CreateRecordEffectPage FillDataForRecordAndCreateEffect(String RecordNum, String SignatureNumber, String CertificateNum) {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, RecordOffice);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, Option1ForAnyList);
        Utility.WatingAndSENDKEYS(driver, RecordNumField,RecordNum);
        Utility.WatingAndSENDKEYS(driver, SignatureNumberInRecordField,SignatureNumber);
        Utility.WatingAndSENDKEYS(driver, SearchingOfCertificateNumField,CertificateNum);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, SearchButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, FirstRowForCertificateResult);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, SendingEffectButton);
        return this;
    }

    public V01_CreateRecordEffectPage ConfirmAndApproveTheRecord() {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,OkButtonOfPopUp);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AcceptButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,OkButtonOfPopUp);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ApprovedButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,OkButtonOfPopUp);
        return this;
    }
}
