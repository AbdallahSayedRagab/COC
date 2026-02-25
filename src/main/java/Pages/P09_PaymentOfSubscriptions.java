package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class P09_PaymentOfSubscriptions {
    private final By AddNewRequest_Button = By.xpath("//img[@src=\"../../../assets/images/circle-plus-white.svg\"]");
    private final WebDriver driver;
    private final By Personalidentificationnumber=By.xpath("//input-control[@formcontrolname='applicantNationalId']//input");
    private final By SearchingOfRecordNumField = By.xpath("(//input[@name=\"recordNumber\"])[1]");
    private final By Loading_Circle = By.xpath("//mat-spinner[@mode=\"indeterminate\"]");
    private final By FirstRowForRecordsResult = By.xpath("(//tr[@cdk-describedby-host=\"0\"]/td)[1]");




    public P09_PaymentOfSubscriptions EnterThePersonalIdentificationNumber(String personalidentifier){
        Utility.SENDKEYS(driver,Personalidentificationnumber,personalidentifier);
        return this;
    }
    public P09_PaymentOfSubscriptions(WebDriver driver) {
        this.driver=driver;
    }
    public P09_PaymentOfSubscriptions SearchOnRecordNumber(String recordnumber) throws InterruptedException {
        Utility.WatingAndSENDKEYS(driver,SearchingOfRecordNumField,Loading_Circle,recordnumber);
        driver.findElement(SearchingOfRecordNumField).sendKeys(Keys.ENTER);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,FirstRowForRecordsResult,Loading_Circle);
        return this;
    }

}
