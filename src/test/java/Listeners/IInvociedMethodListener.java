package Listeners;

import DriverFactory.DriverFactoryClass;
import Utilities.LogsUtiles;
import Utilities.Utility;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.IOException;

import static DriverFactory.DriverFactoryClass.getdriver;

public class IInvociedMethodListener implements IInvokedMethodListener {


    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (testResult.getStatus()==ITestResult.FAILURE) {
            LogsUtiles.info("Test-Case:" + testResult.getTestName() + "Failed");

            try {
                Utility.SCREENSHOT(getdriver(),testResult.getName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
