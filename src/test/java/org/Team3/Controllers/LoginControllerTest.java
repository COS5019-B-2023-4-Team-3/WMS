package org.Team3.Controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginControllerTest {

    private WebDriver chromeDriver;
    private WebDriver fireFoxDriver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        System.out.println("Setting up test: ");
        // Set up Chrome browser
        System.out.println("initializing chrome driver: ");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);

        // Set up Firefox browser
        System.out.println("initializing firefox driver: ");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        fireFoxDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd.hub"), firefoxOptions);
    }

    @Test
    public void testChromeLoginWithValidCredentials() {
        System.out.println("Running test: testChromeLoginWithValidCredentials");

        chromeDriver.get("http://localhost:8081/login");
        // Perform login with valid credentials
         chromeDriver.findElement(By.id("username")).sendKeys("test_admin");
         chromeDriver.findElement(By.id("password")).sendKeys("420c23872305988507ab80a1213a23bb43ade60e9773de1d9a062c7f656132c3280a8b3d040ef380");
         chromeDriver.findElement(By.id("loginButton")).click();

        // Assert that the user is redirected to the homepage
        System.out.println("chrome: " + chromeDriver.getCurrentUrl());
        assertEquals("http://localhost:8081/homepage", chromeDriver.getCurrentUrl());
    }

    @Test
    public void testChromeLoginWithInvalidCredentials() {
        System.out.println("Running test: testChromeLoginWithInvalidCredentials");
        chromeDriver.get("http://localhost:8081/login");
        // Perform login with invalid credentials
         chromeDriver.findElement(By.id("username")).sendKeys("invalid_username");
         chromeDriver.findElement(By.id("password")).sendKeys("invalid_password");
         chromeDriver.findElement(By.id("loginButton")).click();

        // Assert that the user stays on the login page and sees the error message
        System.out.println("chrome: " + chromeDriver.getCurrentUrl());
        assertEquals("http://localhost:8081/login?error=invalid_username_or_password", chromeDriver.getCurrentUrl());
    }

    @Test
    public void testFirefoxLoginWithValidCredentials() {
        System.out.println("Running test: testFirefoxLoginWithValidCredentials");
        fireFoxDriver.get("http://localhost:8081/login");
        // Perform login with valid credentials
        fireFoxDriver.findElement(By.id("username")).sendKeys("test_admin");
        fireFoxDriver.findElement(By.id("password")).sendKeys("420c23872305988507ab80a1213a23bb43ade60e9773de1d9a062c7f656132c3280a8b3d040ef380");
        fireFoxDriver.findElement(By.id("loginButton")).click();

        // Assert that the user is redirected to the homepage
        System.out.println("firefox: " + fireFoxDriver.getCurrentUrl());
        assertEquals("http://localhost:8081/homepage", fireFoxDriver.getCurrentUrl());
    }

    @Test
    public void testFirefoxLoginWithInvalidCredentials() {
        System.out.println("Running test: testFirefoxLoginWithInvalidCredentials");
        chromeDriver.get("http://localhost:8081/login");
        // Perform login with invalid credentials
        chromeDriver.findElement(By.id("username")).sendKeys("invalid_username");
        chromeDriver.findElement(By.id("password")).sendKeys("invalid_password");
        chromeDriver.findElement(By.id("loginButton")).click();

        // Assert that the user stays on the login page and sees the error message
        System.out.println("firefox: " + fireFoxDriver.getCurrentUrl());
        assertEquals("http://localhost:8081/login?error=invalid_username_or_password", chromeDriver.getCurrentUrl());
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Tearing down tests: ");
        if(chromeDriver != null) {
            System.out.println("Quitting chrome driver: ");
            chromeDriver.quit();
        }
        if(fireFoxDriver != null) {
            System.out.println("Quitting chrome driver: ");
            fireFoxDriver.quit();
        }
    }
}
