package com.parabank.utils;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Method;

// This class implements TestNG's ITestListener to capture screenshots on test failure.
public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testInstance = result.getInstance();

        try {
            // Use the public static getDriver() method from the test class
            Method getDriverMethod = testInstance.getClass().getMethod("getDriver");
            WebDriver driver = (WebDriver) getDriverMethod.invoke(null); // static method, so pass null

            ScreenshotUtil.takeScreenshot(driver, result.getName());

        } catch (Exception e) {
            System.err.println("❌ Could not capture screenshot: " + e.getMessage());
        }
    }
}