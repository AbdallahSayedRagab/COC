package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P06_EffectsOnRecordsPage {
    public P06_EffectsOnRecordsPage (WebDriver driver) { this.driver = driver;}
    private WebDriver driver;


    private final By EffectsPageIcon = By.xpath("//a[contains(@href, '/records/actions-on-records/list')]");
    private final By AddEffectButton = By.xpath("//span[contains(text(), 'التأثير علي سجل')]");
    private final By CreateRecordButton = By.id("create");
    private final By ConfirmingEffectButton = By.xpath("//button[contains(text(), ' انشاء التأثير ')]");
    



}
