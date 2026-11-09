package Pages.BackofficePages;

import Utilities.BackofficeBasedFunctions;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class p07_sourcedcode {
    private final WebDriver driver;
    public p07_sourcedcode(WebDriver driver) {
        this.driver=driver;
    }
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
    private final By Commercialdata=By.xpath("(//div[contains(@class, 'form-accordion-head')])[1]");
    private final By item=By.xpath("(//div[contains(@class, 'form-accordion-head')])[3]");
    private final By samecompany=By.xpath("(//input[@formcontrolname='sameCompany'])[1]");
    private final By sameaddress=By.xpath("(//input[@formcontrolname='sameAddress'])[1]");
    private final By certificatesCount=By.xpath("//input-control[@formcontrolname='certificatesCount']//input");
    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");
    private final By additem=By.xpath("//button[@type='button']");

    public p07_sourcedcode ClickOnAddNewRequest_Button () throws InterruptedException {
        BackofficeBasedFunctions.ClickOnAddNewRequest_Button(driver);
        return this;
    }

    public p07_sourcedcode SearchOnRecordNumber(String recordnumber) throws InterruptedException {
        BackofficeBasedFunctions.SearchOnRecordNumber(driver,recordnumber);
        return this;
    }

    public p07_sourcedcode EnterThePersonalIdentificationNumber(String personalidentifier){
        BackofficeBasedFunctions.EnterThePersonalIdentificationNumber(driver,personalidentifier);
        return this;
    }

    public p07_sourcedcode clickonsavedmedicalprovider() throws InterruptedException {
        BackofficeBasedFunctions.ClickOnSavedMedicalProvider(driver);
        return this;
    }

    public p07_sourcedcode FillTheCommericalData(String name,String address,String country ) throws InterruptedException {
        // بالترتيب اللي ظاهر في الصفحة
        Utility.CLICKONELEMENTS(driver,Commercialdata);
        Utility.SENDKEYS(driver,importername,name);
        Utility.CLICKONELEMENTS(driver,importerycountry);
        Utility.CLICKONELEMENTS(driver,Option1ForAnyList);
        Utility.SENDKEYS(driver,importeraddress,address);
        Utility.CLICKONELEMENTS(driver,samecompany);
        Utility.CLICKONELEMENTS(driver,sameaddress);
        Utility.SENDKEYS(driver,countryoforigin,country);
        Utility.CLICKONELEMENTS(driver,Thesourceofthegoods);
        Utility.CLICKONELEMENTS(driver,Option1ForAnyList);
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
        Utility.CLICKONELEMENTS(driver,Option1ForAnyList);
        return this;
    }

    public p07_sourcedcode FillAttachmentSection() {
        BackofficeBasedFunctions.FillAttachmentSection(driver);
        return this;
    }

    public p07_sourcedcode confirmrequest() throws InterruptedException {
        BackofficeBasedFunctions.ConfirmRequest(driver);
        return this;
    }

    public p07_sourcedcode FillTheItemSection(String desccatogery,String Trademarks,String Numberofparcels,String netweight,String Totalweight,String Totalvalue) throws InterruptedException {
        // بالترتيب اللي ظاهر في الصفحة
        Utility.CLICKONELEMENTS(driver,item);
        Utility.SENDKEYS(driver,descraptincatogey,desccatogery);
        Utility.SENDKEYS(driver,signedandno,Trademarks);
        Utility.CLICKONELEMENTS(driver,Parcelquality);
        Utility.CLICKONELEMENTS(driver,Option1ForAnyList);
        Utility.SENDKEYS(driver,typescount,Numberofparcels);
        Utility.SENDKEYS(driver,Netweight,netweight);
        Utility.SENDKEYS(driver,totalWeight,Totalweight);
        Utility.CLICKONELEMENTS(driver,weightunit);
        Utility.CLICKONELEMENTS(driver,Option1ForAnyList);
        Utility.SENDKEYS(driver,totalValue,Totalvalue);
        Utility.CLICKONELEMENTS(driver,Currency);
        Utility.CLICKONELEMENTS(driver,Option1ForAnyList);
        Utility.CLICKONELEMENTS(driver,additem);
        return this;
    }
    public p07_sourcedcode ReviewRequest () throws InterruptedException {
        BackofficeBasedFunctions.ReviewRequest(driver);
        return this;
    }

    public P04_InvoicesPage SendingRequestToCollect(){
        BackofficeBasedFunctions.SendingRequestToCollect(driver);
        return new P04_InvoicesPage(driver);
    }

    public p07_sourcedcode ReceivingTheCertificate () throws InterruptedException {
        BackofficeBasedFunctions.ReceivingTheCertificate(driver);
        return this;
    }











}
