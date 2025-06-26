package com.parabank.utils;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import java.lang.reflect.Method;

// Log4j2 imports
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestListener implements ITestListener {

    // Initialize Log4j2 Logger
    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        Object testInstance = result.getInstance();
        String testName = result.getName();

        logger.error("Test failed: {}", testName);  // Log the failure

        try {
            // Use static getter method to retrieve the WebDriver
            Method getDriverMethod = testInstance.getClass().getMethod("getDriver");
            WebDriver driver = (WebDriver) getDriverMethod.invoke(null); // null because it's static

            ScreenshotUtil.takeScreenshot(driver, testName);
            logger.info("Screenshot taken for failed test: {}", testName);

        } catch (Exception e) {
            logger.error("Could not capture screenshot for test '{}': {}", testName, e.getMessage());
        }
    }
}