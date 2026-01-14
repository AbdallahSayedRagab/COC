package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04_InvoicesPage {

    public  P04_InvoicesPage (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By CollectingIcon = By.xpath("//img[@src=\"../../assets/images/collections-icon.svg\"]");
    private final By InvoicesScreenIcon = By.xpath("//a[@href=\"/collections/invoices?title=::Invoices\"]");
    private final By Loading_Circle = By.xpath("//mat-spinner[@mode=\"indeterminate\"]");
    private final By ViewButton = By.xpath("(//li[.//*[local-name()='svg' and contains(@class,'view-icon')]])[1]");
    private final By PullTheRequest = By.id("WithdrawTheBill");
    private final By CashType = By.xpath("(//input[@type=\"radio\"])[1]");
    private final By PaidButton = By.xpath("//button[@type=\"button\"]");



    public P02_Dashboard PayingFirstBill(){
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,CollectingIcon,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,InvoicesScreenIcon,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ViewButton,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,CashType,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PaidButton,Loading_Circle);
        return new P02_Dashboard (driver);
    }
}
