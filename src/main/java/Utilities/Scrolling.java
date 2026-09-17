package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Scrolling {
    private Scrolling() {
    }

//    public static void ScrollToElement(WebDriver driver, By Locator) {
//        // Wait for the element to be present in the DOM before trying to find it and
//        // scroll
//        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(Locator));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});",
//                driver.findElement(Locator));
//
//    }
    public static void ScrollToElement(WebDriver driver, By Locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(Locator));

        ((JavascriptExecutor) driver).executeScript(
                "const el = arguments[0];" +
                        "let node = el;" +
                        "let p = el.parentElement;" +
                        // نطلع لفوق طبقة طبقة ونعمل scroll لكل container قابل للسكرول
                        "while (p) {" +
                        "  const st = getComputedStyle(p);" +
                        "  const scrollX = /(auto|scroll|overlay)/.test(st.overflowX) && p.scrollWidth  > p.clientWidth;" +
                        "  const scrollY = /(auto|scroll|overlay)/.test(st.overflowY) && p.scrollHeight > p.clientHeight;" +
                        "  if (scrollX || scrollY) {" +
                        "    const er = node.getBoundingClientRect();" +
                        "    const pr = p.getBoundingClientRect();" +
                        "    if (scrollX) p.scrollLeft += (er.left - pr.left) - (pr.width  / 2) + (er.width  / 2);" +
                        "    if (scrollY) p.scrollTop  += (er.top  - pr.top)  - (pr.height / 2) + (er.height / 2);" +
                        "  }" +
                        "  p = p.parentElement;" +
                        "}" +
                        // وأخيرا نظبط الصفحة نفسها لو العنصر لسه بره الشاشة
                        "const r = el.getBoundingClientRect();" +
                        "const outside = r.top < 0 || r.left < 0 ||" +
                        "  r.bottom > (window.innerHeight || document.documentElement.clientHeight) ||" +
                        "  r.right  > (window.innerWidth  || document.documentElement.clientWidth);" +
                        "if (outside) el.scrollIntoView({block:'center', inline:'center'});",
                driver.findElement(Locator));
    }
}
