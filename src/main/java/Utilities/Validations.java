package Utilities;

import DriverFactory.DriverFactoryClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Validations {

    private Validations() {
    }

    public static void ValidationTrue (Boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    public static void ValidationFalse (Boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }

    public static void ValidateEquals (Object actual, Object expected) {
        Assert.assertEquals(actual, expected);
    }
    public static void ValidateURL( Object expected) throws InterruptedException {
        String CurrentURL = DriverFactoryClass.getdriver().getCurrentUrl();
        Assert.assertEquals(CurrentURL, expected);
    }

    public static void ValidateNotEquals (Object actual, Object expected, String message) {
        Assert.assertNotEquals(actual, expected, message);
    }

}