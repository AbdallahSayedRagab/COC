package Pages.BackofficePages;

import Utilities.Utility;
import Utilities.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilities.Scrolling.ScrollToElement;

public class P01_LoginPage {
    public P01_LoginPage(WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;
    private final By Login_Email = By.xpath("//input[@formcontrolname=\"username\"]");
    private final By Login_Password = By.xpath("//input[@type=\"password\"]");
    private final By Login_button = By.xpath("//button[@type=\"submit\"]");
    private final By BranchList = By.xpath("(//ng-select)[1]");
    private final By ChamberBranchChoice = By.xpath("//span[text()=' فرع الغرفه للخدمات الاختياريه  ']");
    private final By WindowList = By.xpath("(//ng-select)[2]");
    private final By NormalWindowChoice = By.xpath("//span[text()=' 1 ']");
    private final By ConFirmChoiceButton = By.xpath("//span[text()='تأكيد الاختيار']");




    public P02_Dashboard Login (String EMAIL ,String password) throws InterruptedException {
//        String EMAIL =DataUtils.getJsonData("Data","ValidLoginEmail");
//        String password =DataUtils.getJsonData("Data","Password");
        Utility.SENDKEYS(driver,Login_Email,EMAIL);
        Utility.SENDKEYS(driver,Login_Password,password);
        Utility.CLICKONELEMENTS(driver,Login_button);
        return new P02_Dashboard( driver);
    }

    public P02_Dashboard LoginAndChooseWindow (String EMAIL ,String password) throws InterruptedException {
//        String EMAIL =DataUtils.getJsonData("Data","ValidLoginEmail");
//        String password =DataUtils.getJsonData("Data","Password");
        Utility.SENDKEYS(driver,Login_Email,EMAIL);
        Utility.SENDKEYS(driver,Login_Password,password);
        Utility.CLICKONELEMENTS(driver,Login_button);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,BranchList);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ChamberBranchChoice);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,WindowList);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,NormalWindowChoice);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ConFirmChoiceButton);

        return new P02_Dashboard( driver);
    }

}
