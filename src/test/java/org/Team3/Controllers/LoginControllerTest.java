package org.Team3.Controllers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginControllerTest {

    public WebDriver driver;
    public String hubURL = "http://0.0.0.0:4444";

    @Parameters({"browser"})
    @BeforeTest
    public void setDriver(String browser) throws MalformedURLException {
        switch(browser){
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                driver = new RemoteWebDriver(new URL(hubURL), chromeOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new RemoteWebDriver(new URL(hubURL), edgeOptions);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new RemoteWebDriver(new URL(hubURL), firefoxOptions);
                break;
        }
        driver.get("http://localhost:8081/");
    }

    @Test
    public void testGoToLoginPage() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("http://localhost:8081/login"));
        assertEquals("http://localhost:8081/login", driver.getCurrentUrl(), "User is on the login page");
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        System.out.println("Running test: testLoginWithInvalidCredentials");
        driver.get("http://localhost:8081/login");
        // Perform login with invalid credentials
        driver.findElement(By.id("username")).sendKeys("invalid_username");
        driver.findElement(By.id("password")).sendKeys("invalid_password");
        driver.findElement(By.id("loginButton")).click();

        // Assert that the user stays on the login page and sees the error message
        System.out.println("Current URL: " + driver.getCurrentUrl());
        assertEquals("http://localhost:8081/login?error=invalid_username_or_password", driver.getCurrentUrl());
    }

    @Test
    public void testLoginWithValidCredentials_Admin() {
        System.out.println("Running test: testLoginWithValidCredentials");
        driver.get("http://localhost:8081/login");
        // Perform login with invalid credentials
        driver.findElement(By.id("username")).sendKeys("test_admin");
        driver.findElement(By.id("password")).sendKeys("test");
        driver.findElement(By.id("loginButton")).click();

        // Assert that the user stays on the login page and sees the error message
        System.out.println("Current URL: " + driver.getCurrentUrl());
        assertEquals("http://localhost:8081/homepage", driver.getCurrentUrl());
    }

    @Test
    public void testLoginWithValidCredentials_Employee() {
        System.out.println("Running test: testLoginWithValidCredentials");
        driver.get("http://localhost:8081/login");
        // Perform login with invalid credentials
        driver.findElement(By.id("username")).sendKeys("test_employee");
        driver.findElement(By.id("password")).sendKeys("test");
        driver.findElement(By.id("loginButton")).click();

        // Assert that the user stays on the login page and sees the error message
        System.out.println("Current URL: " + driver.getCurrentUrl());
        assertEquals("http://localhost:8081/homepage", driver.getCurrentUrl());
    }

    @Test
    public void testLoginWithValidCredentials_Vendor() {
        System.out.println("Running test: testLoginWithValidCredentials");
        driver.get("http://localhost:8081/login");
        // Perform login with invalid credentials
        driver.findElement(By.id("username")).sendKeys("test_vendor");
        driver.findElement(By.id("password")).sendKeys("test");
        driver.findElement(By.id("loginButton")).click();

        // Assert that the user stays on the login page and sees the error message
        System.out.println("Current URL: " + driver.getCurrentUrl());
        assertEquals("http://localhost:8081/homepage", driver.getCurrentUrl());
    }

    @AfterTest
    public void tearDown() {
        System.out.println("Tearing down tests: ");
        if(driver != null) driver.quit();
    }
}
