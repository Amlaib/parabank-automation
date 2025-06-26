package com.parabank.tests;

import com.parabank.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.Assert;

import java.time.Duration;

import org.openqa.selenium.By;

@Listeners(com.parabank.utils.TestListener.class)

// This class is a test for the login functionality of the Parabank application.
public class LoginTest {
    private static WebDriver driver;
    // Getter method to access the WebDriver instance from other classes.
    public static WebDriver getDriver() {
    return driver;
    }
    private final String baseUrl = "https://parabank.parasoft.com/parabank/index.htm";

    // This method sets up the WebDriver and navigates to the Parabank login page before each test.
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get(baseUrl);
    }

    // This test method performs a valid login using demo credentials and asserts that the user is redirected to the Accounts Overview page.
    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("john", "demo"); // These are demo credentials
        
    // Assertion: Check if user is on Accounts Overview page
    String heading = driver.findElement(By.xpath("//h1")).getText();
    Assert.assertEquals(heading, "Wrong Text", "Login failed or unexpected landing page.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}