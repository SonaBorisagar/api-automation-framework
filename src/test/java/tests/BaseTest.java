package tests;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentTest;

import Utils.ExtentReportManager;

public class BaseTest {

    protected ExtentTest test;

    @BeforeMethod
    public void beforeTest(Method method) {
        test = ExtentReportManager.getInstance().createTest(method.getName());
        ExtentReportManager.setTest(test);
    }

    @AfterMethod
    public void afterTest(ITestResult result) {

        ExtentTest test = ExtentReportManager.getTest();

        switch (result.getStatus()) {

            case ITestResult.SUCCESS:
                test.pass("Test Passed");
                break;

            case ITestResult.FAILURE:
                test.fail(result.getThrowable());
                break;

            case ITestResult.SKIP:
                test.skip(result.getThrowable());
                break;
        }

        ExtentReportManager.getInstance().flush();
    }
}