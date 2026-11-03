package Pages.BackofficePages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// شاشة خدمة الجمهور: الشباك الحالي والدور الحالي وزراير ادارة الدور (استدعاء التالي - انهاء - تجاهل ...)
public class K04_CitizenServicePage {
    public K04_CitizenServicePage(WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By CallNextButton = By.xpath("//button[normalize-space()='استدعاء التالي']");
    private final By FinishButton = By.xpath("//button[normalize-space()='انهاء']");
    private final By NoCurrentQueueText = By.xpath("//*[normalize-space(text())='لا يوجد دور حالي']");


    // بيستدعي الحجز التالي اللي مستني على الفرع (اقدم دور مستني، مش لازم يكون آخر حجز) - زرار انهاء بيتفتح بعدها
    public K04_CitizenServicePage CallNextReservation() {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, CallNextButton);
        return this;
    }

    // بيدوس انهاء على الدور الحالي وبيستنى لحد ما الشاشة تكتب "لا يوجد دور حالي" (الشباك فضي)
    public K04_CitizenServicePage FinishCurrentReservation() {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, FinishButton);
        Utility.GETTEXT(driver, NoCurrentQueueText);
        return this;
    }
}
