package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import static Utilities.DataUtiles.getJsonData;

public class p07_sourcedcode {
    private final WebDriver driver;
    private final By SearchingOfRecordNumField = By.xpath("(//input[@name=\"recordNumber\"])[1]");
    private final By Loading_Circle = By.xpath("//mat-spinner[@mode=\"indeterminate\"]");
    private final By AddNewRequest_Button = By.xpath("//img[@src=\"../../../assets/images/circle-plus-white.svg\"]");
    private final By Personalidentificationnumber=By.xpath("//input-control[@formcontrolname='applicantNationalId']//input");
    private final By descraptincatogey=By.xpath("//input-control[@formcontrolname='category']//input");
    private final By importername=By.xpath("//input-control[@formcontrolname='importerName']//input");
    private final By importeraddress=By.xpath("//input-control[@formcontrolname='importerAddress']//input");
    private final By signedandno=By.xpath("//input-control[@formcontrolname='signsAndNo']//input");
    private final By typescount=By.xpath("//input-control[@formcontrolname='packagesTypesAndCounts']//input");
    private final By Netweight=By.xpath("//input-control[@formcontrolname='netWeight']//input");
    private final By totalWeight=By.xpath("//input-control[@formcontrolname='totalWeight']//input");
    private final By totalValue=By.xpath("//input-control[@formcontrolname='totalValue']//input");
    private final By importerycountry=By.xpath("//label[normalize-space(text())='دولة المستورد']/following-sibling::*[1]");
    private final By countryoforigin=By.xpath("//label[normalize-space(text())='دولة المنشأ']/following-sibling::*[1]");
    private final By Thesourceofthegoods=By.xpath("//label[normalize-space(text())='مصدر البضاعه']/following-sibling::*[1]");
    private final By Signatureauthentication=By.xpath("//label[normalize-space(text())='تصديق توقيع']/following-sibling::*[1]");
    private final By Certificatelanguage=By.xpath("//label[normalize-space(text())='لغة الشهادة']/following-sibling::*[1]");
    private final By Parcelquality=By.xpath("//label[normalize-space(text())='نوعية الطرود']/following-sibling::*[1]");
    private final By weightunit=By.xpath("//label[normalize-space(text())='وحدة الوزن']/following-sibling::*[1]");
    private final By Currency =By.xpath("//label[normalize-space(text())='العملة']/following-sibling::*[1]");
    private final By saveamedicalprovider=By.xpath("//button[@type='submit']");
    private final By Commercialdata=By.xpath("(//div[contains(@class, 'form-accordion-head')])[1]");
    private final By item=By.xpath("(//div[contains(@class, 'form-accordion-head')])[3]");
    private final By samecompany=By.xpath("(//input[@formcontrolname='sameCompany'])[1]");
    private final By sameaddress=By.xpath("(//input[@formcontrolname='sameAddress'])[1]");
    private final By FirstRowForRecordsResult = By.xpath("(//tr[@cdk-describedby-host=\"0\"]/td)[1]");
    private final By certificatesCount=By.xpath("//input-control[@formcontrolname='certificatesCount']//input");
    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");
    private final By AddAttachmentButton = By.xpath("//button[@title=\"إضافة ملف جديد\"]");
    private final By ConfirmRequestButton = By.xpath("//span[contains(text(),\"تاكيد الطلب\")]");
    private final By AcceptRequestButtonOfPopUP = By.xpath("//button[contains(text(),\" اقبل الطلب\")]");
    private final By additem=By.xpath("//button[@type='button']");
    public p07_sourcedcode(WebDriver driver) {
        this.driver=driver;
    }

    public p07_sourcedcode ClickOnAddNewRequest_Button () throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AddNewRequest_Button,Loading_Circle);
        return this;
    }
    public p07_sourcedcode searchonrecordnumber(String recordnumber) throws InterruptedException {
        Utility.WatingAndSENDKEYS(driver,SearchingOfRecordNumField,Loading_Circle,recordnumber);
        driver.findElement(SearchingOfRecordNumField).sendKeys(Keys.ENTER);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,FirstRowForRecordsResult,Loading_Circle);
        return this;
    }

    public p07_sourcedcode enterthePersonalidentificationnumber(String personalidentifier){
    Utility.SENDKEYS(driver,Personalidentificationnumber,personalidentifier);
    return this;
    }
    public p07_sourcedcode clickonsavedmedicalprovider() throws InterruptedException {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,saveamedicalprovider,Loading_Circle);
        return this;
    }
    public p07_sourcedcode clickoncommericaldata(String name,String address ) throws InterruptedException {
        Utility.CLICKONELEMENTS(driver,Commercialdata);
       Utility.CLICKONELEMENTS(driver,importerycountry);
        Utility.CLICKONELEMENTS(driver,Option1ForAnyList);
        Utility.CLICKONELEMENTS(driver,countryoforigin);
        Utility.CLICKONELEMENTS(driver,Option1ForAnyList);
        Utility.CLICKONELEMENTS(driver,Thesourceofthegoods);
        Utility.CLICKONELEMENTS(driver,Option1ForAnyList);
        Utility.SENDKEYS(driver,importername,name);
       Utility.SENDKEYS(driver,importeraddress,address);
       Utility.CLICKONELEMENTS(driver,samecompany);
       Utility.CLICKONELEMENTS(driver,sameaddress);
        return this;
    }
    public p07_sourcedcode Certificatecount(String numberofcertificates){
        Utility.SENDKEYS(driver,certificatesCount,numberofcertificates);
        return this;
    }
    public p07_sourcedcode Signatureauthentication() throws InterruptedException {
        Utility.CLICKONELEMENTS(driver,Signatureauthentication);
        Utility.CLICKONELEMENTS(driver,Option1ForAnyList);
        return this;
    }
    public p07_sourcedcode Certificatelanguage() throws InterruptedException {
        Utility.CLICKONELEMENTS(driver,Certificatelanguage);
        return this;
    }
    public p07_sourcedcode confirmrequest() throws InterruptedException, AWTException {
        Utility.CLICKONELEMENTS(driver, ConfirmRequestButton);
        Utility.CLICKONELEMENTS(driver,AcceptRequestButtonOfPopUP);
        return this;
    }
    public p07_sourcedcode clickonItems(String desccatogery,String Trademarks,String Numberofparcels,String netweight,String Totalweight,String Totalvalue) throws InterruptedException {
        Utility.CLICKONELEMENTS(driver,item);
        Utility.SENDKEYS(driver,descraptincatogey,desccatogery);
        Utility.SENDKEYS(driver,signedandno,Trademarks);
        Utility.SENDKEYS(driver,typescount,Numberofparcels);
        Utility.SENDKEYS(driver,Netweight,netweight);
        Utility.SENDKEYS(driver,totalWeight,Totalweight);
        Utility.SENDKEYS(driver,totalValue,Totalvalue);
        Utility.CLICKONELEMENTS(driver,Parcelquality);
        Utility.CLICKONELEMENTS(driver,Option1ForAnyList);
        Utility.CLICKONELEMENTS(driver,weightunit);
        Utility.CLICKONELEMENTS(driver,Option1ForAnyList);
        Utility.CLICKONELEMENTS(driver,Currency);
        Utility.CLICKONELEMENTS(driver,Option1ForAnyList);
        Utility.CLICKONELEMENTS(driver,additem);
        return this;
    }











}
