package Utilities;

import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import Utilities.Scrolling;


import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Utility {

    public static final By Loader = By.xpath("//mat-spinner[@mode=\"indeterminate\"] | //div[contains(@class,'ngx-spinner-overlay')] | //app-spinner//div[contains(@class,'cssload-container')]");

        @Step("Clicking On :{Locator}")
    public static void CLICKONELEMENTS(WebDriver driver, By Locator ) throws InterruptedException {
        WaitUntilReady_And_CLICK(driver,Locator);
    }

public static void WatingLoadingCircle_And_CLICKONELEMENTS(WebDriver driver, By Locator) {
    WaitUntilReady_And_CLICK(driver,Locator);
}
    public static void WatingLoadingCircle_And_CLICKON_WebElement(
            WebDriver driver, WebElement element) {

        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200))
                    .ignoring(ElementClickInterceptedException.class)
                    .ignoring(ElementNotInteractableException.class)
                    .until(d -> {
                        if (d.findElements(Loader).stream().anyMatch(WebElement::isDisplayed)) return false;
                        if (!element.isDisplayed() || !element.isEnabled()) return false;
                        element.click();
                        return true;
                    });
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        }
    }

    public static void CLICKONVISIBLEELEMENTS(WebDriver driver, By Locator) {
        WaitUntilReady_And_CLICK(driver,Locator);
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(ExpectedConditions.invisibilityOfElementLocated(Locator));

    }
    //    @Step("Adding Data On: {Locator}")
    public static void SENDKEYS(WebDriver driver, By Locator, String text) {
        WaitUntilReady_And_SENDKEYS(driver,Locator,text);
    }
    public static void WatingAndSENDKEYS(WebDriver driver, By Locator, String text) {
        WaitUntilReady_And_SENDKEYS(driver,Locator,text);
    }
    // بيمسح الحقل (Ctrl+A ثم Delete عشان Angular يحس بالتغيير) وبعدين يكتب
    public static void ClearAndSENDKEYS(WebDriver driver, By Locator, String text) {
        WaitUntilReady_And_SENDKEYS(driver,Locator,Keys.chord(Keys.CONTROL,"a") + Keys.DELETE + text);
    }

    // بيانات مقدم الطلب (نفس الحقول في كل صفحات الطلبات في الباك أوفيس)
    private static final By PersonalIdentificationNumber_Field = By.xpath("//input-control[@label=\"رقم تحقيق الشخصية\"]/input");
    private static final By ApplicantPhone_Field = By.xpath("//input-control[@label=\"رقم الهاتف\"]/input");
    private static final By SaveApplicantData_Button = By.xpath("//button[contains(text(),'حفظ جميع بيانات مقدم الطلب')]");

    public static void FillAndSaveDataOfApplicant(WebDriver driver, String ID, String Phone) {
        // الـ Tab بيخرج من الحقل فالموقع يدور على الرقم القومي ويظهر حقل رقم الهاتف
        WatingAndSENDKEYS(driver,PersonalIdentificationNumber_Field,ID + Keys.TAB);
        ClearAndSENDKEYS(driver,ApplicantPhone_Field,Phone);
        WatingLoadingCircle_And_CLICKONELEMENTS(driver,SaveApplicantData_Button);
    }

    // بيستنى لحد 60 ثانية بس بيدوس اول ما العنصر يبقى جاهز واللودينج يختفي (من غير sleep ثابت)
    public static void WaitUntilReady_And_CLICK(WebDriver driver, By Locator) {
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200))
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(ElementClickInterceptedException.class)
                    .ignoring(ElementNotInteractableException.class)
                    .until(d -> {
                        if (d.findElements(Loader).stream().anyMatch(WebElement::isDisplayed)) return false;
                        WebElement element = d.findElement(Locator);
                        if (!element.isDisplayed() || !element.isEnabled()) return false;
                        Scrolling.ScrollToElement(d,Locator);
                        element.click();
                        return true;
                    });
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        }
        LogsUtiles.info("Clicked on " + Locator.toString());
    }

    // بيستنى لحد 60 ثانية بس بيكتب اول ما الحقل يبقى جاهز واللودينج يختفي (من غير sleep ثابت)
    public static void WaitUntilReady_And_SENDKEYS(WebDriver driver, By Locator, String text) {
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200))
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(ElementNotInteractableException.class)
                    .until(d -> {
                        if (d.findElements(Loader).stream().anyMatch(WebElement::isDisplayed)) return false;
                        WebElement element = d.findElement(Locator);
                        if (!element.isDisplayed() || !element.isEnabled()) return false;
                        Scrolling.ScrollToElement(d,Locator);
                        element.sendKeys(text);
                        return true;
                    });
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        }
        LogsUtiles.info(" Data sent to  " + Locator.toString() + " is " + text);
    }

    // بيكتب في السيرش ويستنى الجدول يتفلتر فعلا: كل الصفوف فيها الكلمة (او مفيش صفوف) ومفيش لودينج
    // الشاشات دي بتبعت طلب مع كل حركة كيبورد والردود مش بترجع بالترتيب، فرد قديم ممكن يرجع الجدول كامل
    // عشان كده لو الجدول لسه مش متفلتر بندوس End عشان يتبعت طلب جديد بالقيمة الكاملة
    public static void SearchInTable(WebDriver driver, By SearchField, By TableRows, String Text) {
        ClearAndSENDKEYS(driver,SearchField,Text);
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofSeconds(2))
                .ignoring(StaleElementReferenceException.class)
                .until(d -> {
                    if (d.findElements(Loader).stream().anyMatch(WebElement::isDisplayed)) return false;
                    boolean IsFiltered = IsTableFilteredBy(d,TableRows,Text);
                    if (!IsFiltered) {
                        d.findElement(SearchField).sendKeys(Keys.END);
                    }
                    return IsFiltered;
                });
    }

    public static boolean IsTableFilteredBy(WebDriver driver, By TableRows, String Text) {
        return driver.findElements(TableRows).stream()
                .allMatch(Row -> Row.getText().toLowerCase().contains(Text.toLowerCase()));
    }

    // حقل الوقت شكل كتابته بيتغير حسب لغة الجهاز (12 او 24 ساعة) فبنحط القيمة مباشرة ونبلغ Angular بالتغيير
    public static void SetValueByJavaScript(WebDriver driver, By Locator, String Value) {
        WebElement Element = new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200)).until(d -> d.findElement(Locator));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1];" +
                "arguments[0].dispatchEvent(new Event('input', {bubbles: true}));" +
                "arguments[0].dispatchEvent(new Event('change', {bubbles: true}));", Element, Value);
    }

    public static void SELECT(WebDriver driver, By locator, int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200));
        wait.until(elementToBeClickable(locator));
        Scrolling.ScrollToElement(driver,locator);
        WebElement dropdownElement = driver.findElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(index);
    }

    public static String GETTEXT(WebDriver driver, By Locator ) {
        // بيستنى لحد 60 ثانية بس بيقرا النص اول ما اللودينج يختفي والعنصر يظهر
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        String Text;
        try {
            Text = new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200))
                    .ignoring(StaleElementReferenceException.class)
                    .until(d -> {
                        if (d.findElements(Loader).stream().anyMatch(WebElement::isDisplayed)) return null;
                        WebElement element = d.findElement(Locator);
                        return element.isDisplayed() ? element.getText() : null;
                    });
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        }
        LogsUtiles.info(" Data of " + Locator.toString() + " is " + Text);
        return Text ;
    }
    public static void HOVER(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.scrollToElement(element).moveToElement(element).perform();
    }

    public static void SCREENSHOT (WebDriver driver ,String ImageName) throws IOException {
        String Path = System.getProperty("user.dir") + "/src/main/java/Shots/";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File target = new File(Path + ImageName + ".png");
        FileUtils.copyFile(src,target);
    }
    public static int COUNT(WebDriver driver, By locator) {
        List<WebElement> products = driver.findElements(locator);
        return products.size();
    }

    public static void WaitForPageLoad (WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                                .executeScript("return document.readyState")
                                .equals("complete"));
                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));


    }
    public static void SelectFirstEnableDayinCalender (WebDriver driver) throws InterruptedException {
       By locator = By.xpath("//table[@class='days weeks']//span[@class='ng-star-inserted'][1]");
        WaitUntilReady_And_CLICK(driver,locator);
    }

    // بيختار اليوم الحالي بالظبط في التقويم (الأرقام في التقويم بالعربي ٠-٩) - لو النهارده مقفول (اجازة) الكيس بيفشل
    public static void SelectTodayInCalender (WebDriver driver) {
        String Today = String.valueOf(java.time.LocalDate.now().getDayOfMonth());
        StringBuilder ArabicToday = new StringBuilder();
        for (char c : Today.toCharArray()) ArabicToday.append((char) ('٠' + (c - '0')));
        By locator = By.xpath("//table[@class='days weeks']//span[@class='ng-star-inserted' and (normalize-space()='" + ArabicToday + "' or normalize-space()='" + Today + "')]");
        WaitUntilReady_And_CLICK(driver,locator);
    }

    public static By InputFieldXpath(String label){
        return By.xpath("By.xpath(\"//label[contains(normalize-space(.), '\"+label.trim()+\"')]/following::input[1]\");");
    }

    // ===================== المرفقات =====================
    // الملف اللي بيترفع في كل المرفقات - جوه المشروع عشان يتلاقي من عندك ومن الـ Pipeline
    public static final String AttachmentFilePath =
            new File(System.getProperty("user.dir"), "src/test/TestData/COC.jpg").getAbsolutePath();
    // اقصى وقت لرفع مرفق واحد (السيرفر ساعات بيبقى بطيء)
    private static final int AttachmentUploadTimeoutSeconds = 120;
    // اي progress bar بيظهر وقت الرفع
    private static final By UploadProgress = By.xpath("//mat-progress-bar | //*[@role='progressbar']");
    // المرفق اللي اترفع فعليا: بيظهر كارت preview جواه صورة الـ alt بتاعها "id من السيرفر/اسم الملف"
    private static final By UploadedAttachment = By.xpath("//div[contains(@class,'attachment-preview')]/img[contains(@alt,'/')]");

    public static void UploadAttachments(WebDriver driver, By AddAttachmentButton) {
        UploadAttachments(driver, AddAttachmentButton, AttachmentFilePath);
    }

    // بيرفع الملف على كل زرار مرفق موجود في الصفحة (لو مفيش مرفقات بيعدي)
    // وبيستنى كل مرفق يترفع فعليا قبل ما يروح للي بعده - فلو مرفق مترفعش الميثود بتفشل وماحدش بيدوس تأكيد
    public static void UploadAttachments(WebDriver driver, By AddAttachmentButton, String FilePath) {
        if (!new File(FilePath).exists()) {
            throw new IllegalArgumentException("Attachment file not found: " + FilePath);
        }
        WaitUntilNotBusy(driver, 60);

        int total = driver.findElements(AddAttachmentButton).size();
        LogsUtiles.info("Attachments to upload: " + total);

        for (int i = 0; i < total; i++) {
            // بندور على الزراير من جديد كل مرة عشان الصفحة بتعمل re-render بعد كل رفع
            // ولو الصفحة بنت القسم من جديد وقت الرفع (Stale) بنمسك الزرار الجديد ونعيد لحد 3 مرات
            for (int attempt = 1; ; attempt++) {
                WaitUntilNotBusy(driver, 60);
                List<WebElement> buttons = driver.findElements(AddAttachmentButton);
                int removed = total - buttons.size(); // لو الزرار بيختفي بعد ما المرفق يترفع
                WebElement button = buttons.get(i - removed);
                try {
                    UploadFile(driver, button, FilePath);
                    break;
                } catch (StaleElementReferenceException e) {
                    if (attempt == 3) throw e;
                    LogsUtiles.info("Attachment button re-rendered, retrying: attempt " + attempt);
                }
            }
            LogsUtiles.info("Attachment " + (i + 1) + "/" + total + " uploaded");
        }
    }

    // بيرفع ملف واحد من الزرار ده وبيستنى لحد ما يترفع فعليا
    public static void UploadFile(WebDriver driver, WebElement UploadButton, String FilePath) {
        UploadFile(driver, UploadButton, FilePath, UploadedAttachment);
    }

    // نفس الرفع للشاشات اللي المرفق المرفوع بيظهر فيها بشكل مختلف عن كارت الـ preview
    public static void UploadFile(WebDriver driver, WebElement UploadButton, String FilePath, By UploadedFileLocator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WaitUntilNotBusy(driver, 60);
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", UploadButton);
        int before = UploadedFileLocator == null ? 0 : driver.findElements(UploadedFileLocator).size();

        // الزرار (openfilesbrowser) بيعمل input[type=file] مؤقت ويدوس عليه فتفتح نافذة الويندوز
        // فبنمسك الـ input ده قبل ما يفتح النافذة ونحطه في الصفحة ونبعتله المسار مباشرة - اسرع وادق من Robot
        js.executeScript(
                "window.__autoFileInput = null;" +
                "var orig = HTMLInputElement.prototype.click;" +
                "HTMLInputElement.prototype.click = function () {" +
                "  if (this.type === 'file') {" +
                "    HTMLInputElement.prototype.click = orig;" +
                "    this.style.display = 'block'; document.body.appendChild(this); window.__autoFileInput = this; return;" +
                "  }" +
                "  return orig.call(this);" +
                "};");
        WatingLoadingCircle_And_CLICKON_WebElement(driver, UploadButton);
        WebElement fileInput = (WebElement) js.executeScript("return window.__autoFileInput;");

        if (fileInput != null) {
            fileInput.sendKeys(FilePath);
            js.executeScript("arguments[0].remove(); window.__autoFileInput = null;", fileInput);
        } else if (DriverFactory.DriverFactoryClass.IsHeadless()) {
            // في الـ headless مفيش نافذة ويندوز ولا Robot - بنبعت المسار لآخر input file في الصفحة
            List<WebElement> inputs = driver.findElements(By.xpath("//input[@type='file']"));
            if (inputs.isEmpty()) {
                throw new RuntimeException("No file input found for upload in headless mode");
            }
            inputs.get(inputs.size() - 1).sendKeys(FilePath);
        } else {
            // احتياطي: لو الزرار فتح نافذة الويندوز بطريقة تانية بنستخدم Robot زي الاول
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection("\"" + FilePath + "\""), null);
            try {
                Robot robot = new Robot();
                robot.delay(1500); // لحد ما نافذة اختيار الملف تفتح
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.delay(500);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
            } catch (AWTException e) {
                throw new RuntimeException("Robot could not be created for file upload", e);
            }
        }

        if (UploadedFileLocator == null) {
            // شاشات مالهاش شكل ثابت للمرفق المرفوع: بنستنى اللودينج والـ progress يخلصوا بس
            WaitUntilNotBusy(driver, AttachmentUploadTimeoutSeconds);
        } else {
            WaitUntilAttachmentUploaded(driver, UploadedFileLocator, before);
        }
    }

    // بيستنى لحد ما كارت المرفق الجديد يظهر (يعني السيرفر رجع id للملف) + مفيش لودينج ولا progress bar
    // ولازم الحالة دي تفضل ثابتة مرتين ورا بعض عشان مايدوسش وهو لسه بيرفع
    private static void WaitUntilAttachmentUploaded(WebDriver driver, By UploadedFileLocator, int before) {
        int[] stableChecks = {0};
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(AttachmentUploadTimeoutSeconds), Duration.ofMillis(500))
                    .ignoring(StaleElementReferenceException.class)
                    .withMessage("Attachment was not uploaded within " + AttachmentUploadTimeoutSeconds + " seconds")
                    .until(d -> {
                        if (d.findElements(UploadedFileLocator).size() > before && !IsBusy(d)) {
                            return ++stableChecks[0] >= 2;
                        }
                        stableChecks[0] = 0;
                        return false;
                    });
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        }
    }

    private static boolean IsBusy(WebDriver driver) {
        return driver.findElements(Loader).stream().anyMatch(WebElement::isDisplayed)
                || driver.findElements(UploadProgress).stream().anyMatch(WebElement::isDisplayed);
    }

    private static void WaitUntilNotBusy(WebDriver driver, int Seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(Seconds), Duration.ofMillis(200))
                    .ignoring(StaleElementReferenceException.class)
                    .until(d -> !IsBusy(d));
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        }
    }

    public static void PrintAndScreenShot (WebDriver driver, By Locator, String ImageName) throws IOException {

        String originalWindow = driver.getWindowHandle();
        Set<String> oldHandles = driver.getWindowHandles();

        WatingLoadingCircle_And_CLICKONELEMENTS(driver, Locator);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofMillis(200));

        // 1) ننتظر تاب جديد يتفتح
        wait.until(d -> d.getWindowHandles().size() > oldHandles.size());

        Set<String> newHandles = driver.getWindowHandles();
        newHandles.removeAll(oldHandles);
        String newWindow = newHandles.iterator().next();

        driver.switchTo().window(newWindow);

        // 2) ننتظر الـ URL يحتوي على blob
        wait.until(ExpectedConditions.urlContains("blob:"));

        // 3) ننتظر الصفحة تخلص تحميل بالكامل
        wait.until(d -> ((JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));

        // 4) ننتظر السكرين شوت نفسه يستقر (يبقى نفس الصورة مرتين على التوالي)
        byte[] previousShot = null;
        long startTime = System.currentTimeMillis();
        long timeoutMillis = 15000; // 15 ثانية أقصى انتظار للاستقرار
        boolean stable = false;

        while (System.currentTimeMillis() - startTime < timeoutMillis) {
            byte[] currentShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            if (previousShot != null && Arrays.equals(previousShot, currentShot)) {
                stable = true;
                break;
            }

            previousShot = currentShot;
            try {
                Thread.sleep(500); // فاصل بسيط بين المقارنات
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (!stable) {
            LogsUtiles.info("Warning: PDF screenshot did not stabilize within timeout, taking screenshot anyway");
        }

        SCREENSHOT(driver, ImageName);

        driver.close();
        driver.switchTo().window(originalWindow);

        LogsUtiles.info("Screenshot taken for PDF: " + ImageName);
    }
}
