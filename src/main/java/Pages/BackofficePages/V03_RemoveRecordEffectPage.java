package Pages.BackofficePages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class V03_RemoveRecordEffectPage {
    public V03_RemoveRecordEffectPage(WebDriver driver) { this.driver = driver;}
    private WebDriver driver;


    private final By RecordsListIcon = By.xpath("(//span[@class=\"ng-star-inserted\"])[3]");
    private final By EffectsPageButton = By.xpath("//a[contains(@href, '/records/actions-on-records/list')]");
    private final By AddEffectButton = By.xpath("//span[contains(text(), 'التأثير علي سجل')]");
    private final By RemoveRecordButton = By.id("remove");
    private final By ConfirmingEffectButton = By.xpath("//button[contains(text(), ' انشاء التأثير ')]");
    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");
//    private final By RecordNumField = By.xpath("//label[normalize-space(text())='رقم السجل']/following-sibling::*[1]");
    private final By SignatureNumberInRecordField = By.xpath("//label[normalize-space(text())='رقم التأشير في السجل']/following-sibling::*[1]");
    private final By DateOfSigntureField = By.xpath("//label[normalize-space(text())='تاريخ التأشير في السجل']/following-sibling::*[1]");
    private final By FixedDateForAnyCalendar = By.xpath("//td[@role='gridcell']/span[contains(text(), \"١٥\")]");
    private final By SearchingOfRecordNumField = By.xpath("(//input[@name=\"recordNumber\"])[1]");
    private final By FirstRowForRecordsResult = By.xpath("(//tr[@cdk-describedby-host=\"0\"]/td)[1]");
    private final By SearchingOfCertificateNumField = By.xpath("(//input[@name=\"recordNumber\"])[2]");
    private final By SearchButton = By.xpath("//button/span[contains(text(), 'بحث')]");
    private final By FirstRowForCertificateResult = By.xpath("(//tr[@class=\"ng-star-inserted\"]/td)[1]");
    private final By OfficeOfRemove = By.xpath("//label[normalize-space(text())=\"مكتب ايداع المحو\"]/following-sibling::*[1]");
    private final By RemoveNumber = By.xpath("//label[normalize-space(text())=\"رقم المحو\"]/following-sibling::*[1]");


    private final By SendingEffectButton = By.xpath("//button/span[contains(text(), ' ارسال')]");
    private final By OkButtonOfPopUp = By.xpath("//button[contains(text(), 'OK')]");
    private final By EditButton = By.xpath("(//a[.//*[local-name()='svg' and contains(@class,'icon-edit')]])[1]");


    private final By AcceptButton = By.xpath("//span[contains(text(), ' موافقة')]");
    private final By ApprovedButton = By.xpath("//span[contains(text(), ' اعتماد')]");



    public V03_RemoveRecordEffectPage AddNewEffectToRemoveNewRecord() {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, RecordsListIcon);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, EffectsPageButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, AddEffectButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, RemoveRecordButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, ConfirmingEffectButton);
        return this;
    }

    public V03_RemoveRecordEffectPage FillDataForRecordAndCreateRemoveEffect( String RecordNum ,String SignatureNumber, String CertificateNum, String RemoveNum ) {

        Utility.WatingAndSENDKEYS(driver, SignatureNumberInRecordField,SignatureNumber);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,DateOfSigntureField);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,FixedDateForAnyCalendar);
        Utility.WatingAndSENDKEYS(driver, SearchingOfCertificateNumField,CertificateNum);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, SearchButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, FirstRowForCertificateResult);
        Utility.WatingAndSENDKEYS(driver,SearchingOfRecordNumField,RecordNum);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, SearchButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,FirstRowForRecordsResult);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, OfficeOfRemove);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,Option1ForAnyList);
        Utility.WatingAndSENDKEYS(driver,RemoveNumber,RemoveNum);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, SendingEffectButton);

        return this;
    }

    public V03_RemoveRecordEffectPage ConfirmAndApproveTheRecord() {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,OkButtonOfPopUp);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,EditButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AcceptButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,OkButtonOfPopUp);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ApprovedButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,OkButtonOfPopUp);
        return this;
    }
}
