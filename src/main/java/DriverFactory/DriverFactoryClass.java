package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactoryClass {
    public static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    //    @Step ("Create driver instance on: {BROWSER}")
    public static void SetupDriver (String BROWSER) {
        switch (BROWSER.toLowerCase())
        {
            case "chrome":
                ChromeOptions Options = new ChromeOptions();
                Options.addArguments("--start-maximized");
                Options.addArguments("--force-device-scale-factor=0.75");
                driverThreadLocal.set(new ChromeDriver(Options));
                break;

            case "firefox":
                driverThreadLocal.set(new FirefoxDriver());
                break;
            default:
                EdgeOptions Optionss = new EdgeOptions();
                Optionss.addArguments("--start-maximized");
                driverThreadLocal.set(new EdgeDriver(Optionss));

        }
    }
    public static WebDriver getdriver (){
        return driverThreadLocal.get();
    }
    public static WebDriver Quit (){
        getdriver().quit();
        driverThreadLocal.remove();
        return null;
    }
}
