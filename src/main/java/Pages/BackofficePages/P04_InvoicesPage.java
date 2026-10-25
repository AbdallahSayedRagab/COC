package Pages.BackofficePages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04_InvoicesPage {

    public  P04_InvoicesPage (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By CollectingIcon = By.xpath("//img[@src=\"../../assets/images/collections-icon.svg\"]");
    private final By InvoicesScreenIcon = By.xpath("//a[contains(@href,'/collections/invoices')]");
    private final By ViewButton = By.xpath("(//li[.//*[local-name()='svg' and contains(@class,'view-icon')]])[1]");
    private final By PullTheRequest = By.id("WithdrawTheBill");
    private final By CashType = By.xpath("(//input[@type=\"radio\"])[1]");
    private final By PaidButton = By.xpath("//button[@type=\"button\"]");



    public P02_Dashboard PayingFirstBill(){
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,CollectingIcon);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,InvoicesScreenIcon);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ViewButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,CashType);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PaidButton);
        return new P02_Dashboard (driver);
    }
}
