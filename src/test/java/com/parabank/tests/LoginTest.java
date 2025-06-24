package com.parabank.tests;

import com.parabank.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.Assert;
import org.openqa.selenium.By;

public class LoginTest {
    private WebDriver driver;
    private final String baseUrl = "https://parabank.parasoft.com/parabank/index.htm";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("john", "demo"); // These are demo credentials
        // Later i'll assert if login was successful
        
    // Assertion: Check if user is on Accounts Overview page
    String heading = driver.findElement(By.xpath("//h1")).getText();
    Assert.assertEquals(heading, "Accounts Overview", "Login failed or unexpected landing page.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}