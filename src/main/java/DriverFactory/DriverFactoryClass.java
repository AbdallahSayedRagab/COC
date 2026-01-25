package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

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

            case "edge":
                EdgeOptions Optionss = new EdgeOptions();
                Optionss.addArguments("--start-maximized");
                driverThreadLocal.set(new EdgeDriver(Optionss));
                break;

            default:
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--kiosk");;
                options.addPreference("layout.css.devPixelsPerPx", "0.80");
                driverThreadLocal.set(new FirefoxDriver(options));


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
