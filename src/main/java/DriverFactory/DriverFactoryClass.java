package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                // Disable all Chrome native popups/bubbles (passwords, translate, notifications, infobars)
                Options.addArguments("--disable-features=PasswordLeakDetection,Translate,TranslateUI");
                Options.addArguments("--disable-notifications");
                Options.addArguments("--disable-popup-blocking");
                Options.addArguments("--no-default-browser-check");
                Options.addArguments("--no-first-run");
                Options.addArguments("--disable-search-engine-choice-screen");
                Options.setExperimentalOption("excludeSwitches", List.of("enable-automation"));
                Map<String, Object> Prefs = new HashMap<>();
                Prefs.put("credentials_enable_service", false);
                Prefs.put("profile.password_manager_enabled", false);
                Prefs.put("profile.password_manager_leak_detection", false);
                Prefs.put("profile.default_content_setting_values.notifications", 2);
                Prefs.put("translate.enabled", false);
                Prefs.put("autofill.profile_enabled", false);
                Prefs.put("autofill.credit_card_enabled", false);
                Prefs.put("download.prompt_for_download", false);
                Options.setExperimentalOption("prefs", Prefs);
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
