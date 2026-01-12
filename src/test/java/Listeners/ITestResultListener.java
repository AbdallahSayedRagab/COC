package Listeners;

import Utilities.LogsUtiles;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

public class ITestResultListener implements ITestListener {
    public void onTestStart (ITestResult Result){
        LogsUtiles.info("Test-Case:" + Result.getMethod().getMethodName() + " is Started");

    }
    public void onTestSuccess (ITestResult Result){
        LogsUtiles.info("Test-Case:" + Result.getMethod().getMethodName() + " is Successed");

    }
    public void onTestSkipped (ITestResult Result){
        LogsUtiles.info("Test-Case:" + Result.getMethod().getMethodName() + " is Skipped");

    }
}
