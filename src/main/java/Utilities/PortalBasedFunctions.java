package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

// الخطوات المشتركة بين صفحات طلبات البورتال
// كل ميثود هنا كانت مكتوبة بنفس الكود ونفس اللوكيترز في كذا صفحة، فبقت مكتوبة مرة واحدة
// والصفحات بتناديها بنفس اسم الميثود اللي عندها، فالتستات مش بتتغير
// Step = رقم المرحلة في الصفحة (div id='test-l-رقم') لان نفس الزرار بيبقى في مرحلة مختلفة حسب الطلب
public class PortalBasedFunctions {

    private PortalBasedFunctions() {
    }

    private static final By AcceptConditionsCheckBox = By.xpath("//input[@formcontrolname=\"conditions\"]");
    private static final By StartServiceButton = By.xpath("//button[contains(text(),' إبدا الخدمة الآن ')]");
    private static final By ConfirmButtonOfPopUp = By.xpath("//button[contains(@class,'swal2-confirm')]");
    private static final By PopUpMessage = By.xpath("//div[@id=\"swal2-html-container\"]");

    // 1- بيانات السجل (الجدول بيعرض سجلات الحساب لوحده)
    private static final By FirstStep = By.xpath("//div[@id='test-l-1']");
    private static final By FirstRecordInSearchResults = By.xpath("(//div[@id='test-l-1']//table/tbody/tr)[1]");
    private static final By NextButtonOfRecordData = By.xpath("//div[@id='test-l-1']//button[contains(text(),'التالى')]");

    private static By AddAttachmentButton (int Step) {
        return By.xpath("//div[@id='test-l-" + Step + "']//button[@title=\"إضافة ملف جديد\"]");
    }

    private static By SendRequestButton (int Step) {
        return By.xpath("//div[@id='test-l-" + Step + "']//button[contains(text(),'ارسال الطلب')]");
    }

    // بيوافق على الشروط وبيدوس ابدأ الخدمة وبيستنى المرحلة الاولى تظهر (U03 - U07)
    public static void StartTheService (WebDriver driver) {
        StartTheService(driver, FirstStep);
    }

    // بيوافق على الشروط لو مش متعلم عليها وبيدوس ابدأ الخدمة، وبيستنى الحاجة اللي بتظهر بعدها (NextStep)
    // ساعات الضغطة على ابدأ الخدمة مش بتتسجل لو حصلت مع الموافقة على الشروط في نفس اللحظة والصفحة بتفضل مكانها
    // فلو بعد 5 ثواني الصفحة لسه مكانها (الزرار ظاهر والخطوة اللي بعده مش ظاهرة) بيدوس ابدأ الخدمة تاني
    public static void StartTheService (WebDriver driver , By NextStep) {
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.visibilityOfElementLocated(AcceptConditionsCheckBox));
        if (!driver.findElement(AcceptConditionsCheckBox).isSelected()) {
            Utility.WaitUntilReady_And_CLICK(driver,AcceptConditionsCheckBox);
        }
        Utility.WaitUntilReady_And_CLICK(driver,StartServiceButton);
        int[] Checks = {0};
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofSeconds(5))
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(ElementClickInterceptedException.class)
                    .ignoring(ElementNotInteractableException.class)
                    .withMessage("The service did not start after clicking start button")
                    .until(d -> {
                        if (d.findElements(NextStep).stream().anyMatch(WebElement::isDisplayed)) return true;
                        List<WebElement> Buttons = d.findElements(StartServiceButton);
                        // الزرار اختفى يعني الصفحة اتحركت والخطوة اللي بعده لسه بتحمل
                        if (Buttons.isEmpty() || !Buttons.get(0).isDisplayed()) return true;
                        // اول مرة بندي الضغطة الاولى فرصة، وبرضه مش بندوس واللودينج ظاهر
                        if (Checks[0]++ == 0 || d.findElements(Utility.Loader).stream().anyMatch(WebElement::isDisplayed)) return false;
                        LogsUtiles.info("Start service click did not open the service, clicking again");
                        Buttons.get(0).click();
                        return false;
                    });
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        }
    }

    public static void SelectFirstRecord (WebDriver driver) {
        Utility.WaitUntilReady_And_CLICK(driver,FirstRecordInSearchResults);
        Utility.WaitUntilReady_And_CLICK(driver,NextButtonOfRecordData);
    }

    // لو فيه نوع مرفق بيرفع عليه الملف ولو مفيش بيعدي
    public static void FillAttachmentSection (WebDriver driver , int Step) {
        Utility.UploadAttachments(driver,AddAttachmentButton(Step));
    }

    // بيبعت الطلب وبيحفظ رقمه من رسالة "تم ارسال" في المفتاح ده في الجيسون
    public static void SendTheRequestAndSaveRequestNumber (WebDriver driver , int Step , String JsonKey) {
        Utility.WaitUntilReady_And_CLICK(driver,SendRequestButton(Step));
        Utility.WaitUntilReady_And_CLICK(driver,ConfirmButtonOfPopUp);
        // نفس البوب اب بيتغير من رسالة التأكيد لرسالة "تم ارسال" فبنستنى الرسالة الجديدة
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.textToBePresentInElementLocated(PopUpMessage,"تم ارسال"));
        String Message = Utility.GETTEXT(driver,PopUpMessage);
        String requestNumber = Message.replaceAll("[^0-9]", "");
        Validations.ValidationTrue(!requestNumber.isEmpty(), "Request number not found in: " + Message);
        JsonUtil.writeValue(JsonKey, requestNumber);
    }
}
