package Pages;

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
    private final By Login_Password = By.xpath("//input[@formcontrolname=\"password\"]");
    private final By Login_button = By.xpath("//button[@type=\"submit\"]");
    private final By LoadingCircle = By.xpath("//mat-spinner[@mode=\"indeterminate\"]");


    public P02_Dashboard Login (String EMAIL ,String password) throws InterruptedException {
//        String EMAIL =DataUtils.getJsonData("Data","ValidLoginEmail");
//        String password =DataUtils.getJsonData("Data","Password");
        Utility.WatingAndSENDKEYS(driver,Login_Email,LoadingCircle,EMAIL);
        Utility.SENDKEYS(driver,Login_Password,password);
        Utility.CLICKONELEMENTS(driver,Login_button);
        return new P02_Dashboard( driver);
    }
    //    public P01_HomePage Continue_Button_AfterDeleteAccount(){
//        Utilitiy.CLICKONELEMENTS(driver,Continue_Button_AfterDeleteAccount);
//        return this;
//    }
//    public P07_ViewCartPage ClickingOn_CartButton(){
//        Utility.CLICKONELEMENTS(driver,Cart_Button);
//        return new P07_ViewCartPage(driver);
//    }
//    public P05_ProductsPage ClickingOn_ProductsButton(){
//        Utility.CLICKONELEMENTS(driver,Products_Button);
//        return new P05_ProductsPage(driver);
//    }
//    public P05_ProductsPage VerifyLoggedName() {
//        String Logged_visability = Utility.GETTEXT(driver,Logged_as_Button);
//        Validations.ValidateEquals(Logged_visability,"Logged in as AbdallahSayed");
//        return new P05_ProductsPage(driver);
//    }
//    public P01_LoginPage VerifyLoggedNameP1() {
//        String Logged_visability = Utility.GETTEXT(driver,Logged_as_Button);
//        Validations.ValidateEquals(Logged_visability,"Logged in as Abdallah Sayed");
//        return this;

//
//    public P01_LoginPage VerifyHomePageURL() {
//        Validations.ValidateURL("https://automationexercise.com/login");
//        return this;
//    }
//    public P01_LoginPage ClickingOn_LogoutButton(){
//        Utility.CLICKONELEMENTS(driver,Logout_Button);
////        Validations.ValidateURL("https://automationexercise.com/login");
//        return this;
//    }

//    public P01_LoginPage VerifyDeletedMessege() {
//        String Messege = Utility.GETTEXT(driver,Deleted_Account_messege);
//        Validations.ValidateEquals(Messege,"ACCOUNT DELETED!");
//        Utility.CLICKONELEMENTS(driver,Continue_Button_AfterDeleteAccount);
//        return this;
//    }
//    public P02_LoginPage ClickingOn_SignUpLoginButton(){
//        Utility.CLICKONELEMENTS(driver,Signup_login_button);
//        return new P02_LoginPage(driver);
//    }
}
