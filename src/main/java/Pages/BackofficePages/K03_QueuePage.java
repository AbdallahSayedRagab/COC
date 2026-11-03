package Pages.BackofficePages;

import Utilities.DataUtiles;
import Utilities.JsonUtil;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class K03_QueuePage {
    public K03_QueuePage(WebDriver driver){
        this.driver = driver ;
    }
    private WebDriver driver;

    private final By AssignWindowButton = By.xpath("//div[contains(concat(' ', normalize-space(@class), ' '), ' dropdown-menu ') and contains(concat(' ', normalize-space(@class), ' '), ' show ')]//button[normalize-space()='تعيين شباك']");
    private final By ListOfAvailableWindows = By.xpath("//ng-select[@id=\"windowSelect\"]");
    private final By Option1ForAnyList = By.xpath("//div[@role='option'][1]");
    private final By ConfirmButtonAtPopUp = By.xpath("//a[text()='تأكيد']");




    // رسالة النجاح "تم تعيين الشباك كود N إلى الحجز رقم ..." بعد التأكيد
    private By AssignSuccessMessage(String ReservationNumber) {
        return By.xpath("//*[contains(text(),'تم تعيين الشباك') and contains(text(),'" + ReservationNumber + "')]");
    }

    private By ThreeDotsIcon(String ReservationNumber) {
        return By.xpath("//tr[td[normalize-space()='" + ReservationNumber + "']]//img[contains(@src,'more.svg')]");
    }

    private void ClickOn3DotsIconOfLastReservation() {
        String bookingNumber = DataUtiles.getJsonData("Data", "ReservationNumber"); // غيّر المفتاح لو مختلف عندك
        By moreIcon = ThreeDotsIcon(bookingNumber);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, moreIcon);
    }



    public K03_QueuePage AssigningReservationToAvailableWindow () {
        ClickOn3DotsIconOfLastReservation();
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, AssignWindowButton);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, ListOfAvailableWindows);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, Option1ForAnyList);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver, ConfirmButtonAtPopUp);
        // بيستنى رسالة النجاح عشان الكيس ما يقفلش المتصفح قبل ما التعيين يتحفظ
        Utility.GETTEXT(driver, AssignSuccessMessage(DataUtiles.getJsonData("Data", "ReservationNumber")));
        return this;
    }
}
