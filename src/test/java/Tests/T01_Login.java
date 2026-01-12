package Tests;

import DriverFactory.DriverFactoryClass;
import Listeners.IInvociedMethodListener;
import Listeners.ITestResultListener;
import Pages.*;
import Utilities.DataUtiles;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactoryClass.SetupDriver;
import static DriverFactory.DriverFactoryClass.getdriver;

@Listeners({IInvociedMethodListener.class , ITestResultListener.class})
public class T01_Login {
    private WebDriver driver ;
    @BeforeMethod
    public void setupdriver () throws IOException {
        SetupDriver(DataUtiles.getPropertyValue("environment","BROWSER"));
        driver = DriverFactoryClass.getdriver();
        getdriver().get(DataUtiles.getPropertyValue("environment","BASE_URL"));
//       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }


    @Test
    public void ValidLogin () throws InterruptedException {
        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","ValidLoginEmail")
                ,DataUtiles.getJsonData("Data","Password"));

    }


    @Test
    public void InvalidLogin() throws InterruptedException {
        new P01_LoginPage(driver).Login(DataUtiles.getJsonData("Data","InValidLoginEmail")
                ,DataUtiles.getJsonData("Data","Password"));
//        P02_LoginPage visiable_messeges = new P02_LoginPage(driver);
    }
//    @Test
//    public void Logout(){
//        new P01_LoginPage(driver).ClickingOn_SignUpLoginButton()
//                .Login(DataUtiles.getJsonData("Data","ValidLoginEmail") ,DataUtiles.getJsonData("Data","Password"))
//                .ClickingOn_LogoutButton().VerifyHomePageURL();
////        P01_HomePage Logged_visability = new P01_HomePage(driver);
////        Assert.assertEquals(Logged_visability.Logged_as_Button(), "Logged in as AbdallahSayed");
////        new P01_HomePage(driver).Logout_Button();
////        Assert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/login");
//    }


    @AfterMethod
    public void quit () {
//        driver.quit();
    }
}




