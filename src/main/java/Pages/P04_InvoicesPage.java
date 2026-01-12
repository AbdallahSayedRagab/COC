package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04_InvoicesPage {

    public void P04_InvoicesPage (WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By CollectingIcon = By.xpath("src=\"../../assets/images/collections-icon.svg\"");
    private final By InvoicesScreenIcon = By.xpath("//a[@href=\"/collections/invoices?title=::Invoices\"]");
    private final By Loading_Circle = By.xpath("//mat-spinner[@mode=\"indeterminate\"]");



    public P04_InvoicesPage PayingFirstBill(){
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,CollectingIcon,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,InvoicesScreenIcon,Loading_Circle);


    }
}
