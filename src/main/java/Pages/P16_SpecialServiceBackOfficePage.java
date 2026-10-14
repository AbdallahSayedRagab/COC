package Pages;

import Utilities.Utility;
import Utilities.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P16_SpecialServiceBackOfficePage {
    public P16_SpecialServiceBackOfficePage(WebDriver driver) { this.driver = driver;}
    private WebDriver driver;

    private final By Loading_Circle = By.xpath("//mat-spinner[@mode=\"indeterminate\"]");
    private final By BranchList = By.xpath("(//ng-select)[1]");
    private final By SelectedBranch = By.xpath("(//ng-select)[1]//span[contains(@class,'ng-value-label')]");
    private final By RequestsIcon = By.xpath("//img[@src=\"../../assets/images/certificates-icon.svg\"]");
    private final By SpecialServiceRequest_FSM = By.xpath("//a[contains(@href, '/requests/electronic-reviews-requests')]");
    private final By PullTheRequest = By.xpath("//label[contains(normalize-space(), 'اسحب الطلب')]//input[@type='checkbox']");
    private final By AcceptRequestButton = By.xpath("//button[text()='قبول الطلب']");
    private final By ApproveRequestButton = By.xpath("//button[text()='الموافقة علي الطلب']");
    private final By NotesFieldOfPopUp = By.xpath("//textarea[@formcontrolname=\"notes\"]");
    private final By AcceptRequestButtonOfPopUP = By.xpath("//button[contains(text(),' اقبل الطلب')]");

    private By ViewButtonOfRequest (String RequestNumber) {
        return By.xpath("//tr[.//span[normalize-space()='" + RequestNumber + "']]//li[.//*[local-name()='svg' and contains(@class,'view-icon')]]");
    }

    private By StatusOfRequest (String RequestNumber) {
        return By.xpath("//tr[.//span[normalize-space()='" + RequestNumber + "']]//td[contains(@class,'table-actions')]//span");
    }

    public P16_SpecialServiceBackOfficePage SelectBranch (String Branch) throws InterruptedException {
        Utility.WaitForPageLoad(driver);
        if (!Utility.GETTEXT(driver,SelectedBranch,Loading_Circle).trim().equals(Branch)) {
            By BranchChoice = By.xpath("//div[@role='option'][normalize-space()='" + Branch + "']");
            Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,BranchList,Loading_Circle);
            Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,BranchChoice,Loading_Circle);
            Utility.WaitForPageLoad(driver);
        }
        return this;
    }

    public P16_SpecialServiceBackOfficePage OpenSpecialServiceRequests () {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,RequestsIcon,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,SpecialServiceRequest_FSM,Loading_Circle);
        return this;
    }

    public P16_SpecialServiceBackOfficePage AcceptTheRequest (String RequestNumber , String TestData) {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ViewButtonOfRequest(RequestNumber),Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AcceptRequestButton,Loading_Circle);
        Utility.WatingAndSENDKEYS(driver,NotesFieldOfPopUp,Loading_Circle,TestData);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AcceptRequestButtonOfPopUP,Loading_Circle);
        return this;
    }

    public P16_SpecialServiceBackOfficePage ApproveTheRequest (String RequestNumber , String TestData) {
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ViewButtonOfRequest(RequestNumber),Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,PullTheRequest,Loading_Circle);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,ApproveRequestButton,Loading_Circle);
        Utility.WatingAndSENDKEYS(driver,NotesFieldOfPopUp,Loading_Circle,TestData);
        Utility.WatingLoadingCircle_And_CLICKONELEMENTS(driver,AcceptRequestButtonOfPopUP,Loading_Circle);
        return this;
    }

    public P16_SpecialServiceBackOfficePage ValidateRequestIsApproved (String RequestNumber) {
        String Status = Utility.GETTEXT(driver,StatusOfRequest(RequestNumber),Loading_Circle).trim();
        Validations.ValidateEquals(Status,"تم الموافقة على الطلب");
        return this;
    }

    public P15_SpecialServicePortalPage BackToPortalWindow () {
        driver.close();
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
        return new P15_SpecialServicePortalPage(driver);
    }

}
