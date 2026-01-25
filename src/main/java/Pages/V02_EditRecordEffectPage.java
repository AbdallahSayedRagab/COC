package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class V02_EditRecordEffectPage {
    public V02_EditRecordEffectPage(WebDriver driver) { this.driver = driver;}
    private WebDriver driver;


    private final By RecordsListIcon = By.xpath("(//span[@class=\"ng-star-inserted\"])[3]");
    private final By EffectsPageButton = By.xpath("//a[contains(@href, '/records/actions-on-records/list')]");
    private final By AddEffectButton = By.xpath("//span[contains(text(), 'التأثير علي سجل')]");
    private final By EditRecordButton = By.id("edit");
    private final By ConfirmingEffectButton = By.xpath("//button[contains(text(), ' انشاء التأثير ')]");
    private final By Loading_Circle = By.xpath("//mat-spinner[@mode=\"indeterminate\"]");
//    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");
//    private final By RecordNumField = By.xpath("//label[normalize-space(text())='رقم السجل']/following-sibling::*[1]");
    private final By SignatureNumberInRecordField = By.xpath("//label[normalize-space(text())='رقم التأشير في السجل']/following-sibling::*[1]");
    private final By DateOfSigntureField = By.xpath("//label[normalize-space(text())='تاريخ التأشير في السجل']/following-sibling::*[1]");
    private final By FixedDateForAnyCalendar = By.xpath("//td[@role='gridcell']/span[contains(text(), \"١٥\")]");
    private final By SearchingOfRecordNumField = By.xpath("(//input[@name=\"recordNumber\"])[1]");
    private final By FirstRowForRecordsResult = By.xpath("(//tr[@cdk-describedby-host=\"0\"]/td)[1]");
    private final By SearchingOfCertificateNumField = By.xpath("(//input[@name=\"recordNumber\"])[2]");
    private final By SearchButton = By.xpath("//button/span[contains(text(), 'بحث')]");
    private final By FirstRowForCertificateResult = By.xpath("(//tr[@class=\"ng-star-inserted\"]/td)[1]");
    private final By SendingEffectButton = By.xpath("//button/span[contains(text(), ' ارسال')]");
    private final By OkButtonOfPopUp = By.xpath("//button[contains(text(), 'OK')]");
    private final By EditButton = By.xpath("(//a[.//*[local-name()='svg' and contains(@class,'icon-edit')]])[1]");


    private final By AcceptButton = By.xpath("//span[contains(text(), ' موافقة')]");
    private final By ApprovedButton = By.xpath("//span[contains(text(), ' اعتماد')]");



    public V02_EditRecordEffectPage AddNewEffectToEditNewRecord() {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, RecordsListIcon, Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, EffectsPageButton, Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, AddEffectButton, Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, EditRecordButton, Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, ConfirmingEffectButton, Loading_Circle);
        return this;
    }

    public V02_EditRecordEffectPage FillDataForRecordAndCreateEditEffect( String RecordNum ,String SignatureNumber, String CertificateNum ) {

        Utility.WatingAndSENDKEYS(driver, SignatureNumberInRecordField, Loading_Circle,SignatureNumber);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,DateOfSigntureField,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,FixedDateForAnyCalendar,Loading_Circle);
        Utility.WatingAndSENDKEYS(driver, SearchingOfCertificateNumField, Loading_Circle,CertificateNum);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, SearchButton , Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, FirstRowForCertificateResult, Loading_Circle);
        Utility.WatingAndSENDKEYS(driver,SearchingOfRecordNumField,Loading_Circle,RecordNum);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, SearchButton , Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,FirstRowForRecordsResult,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, SendingEffectButton, Loading_Circle);
        return this;
    }

    public V02_EditRecordEffectPage ConfirmAndApproveTheRecord() {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,OkButtonOfPopUp, Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,EditButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AcceptButton, Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,OkButtonOfPopUp, Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ApprovedButton, Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,OkButtonOfPopUp, Loading_Circle);
        return this;
    }
}
