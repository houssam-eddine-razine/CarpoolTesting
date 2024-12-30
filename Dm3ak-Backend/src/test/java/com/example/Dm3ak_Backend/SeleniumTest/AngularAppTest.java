package com.example.Dm3ak_Backend.SeleniumTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class AngularAppTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Set the path to the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize WebDriver
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to the Angular app
        driver.get("http://localhost:4200");
    }

    @Test(priority = 1)
    public void testSignup() {
        // Navigate to the Signup page
        driver.findElement(By.linkText("Sign up")).click();

        // Fill the signup form
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys("John");
        driver.findElement(By.id("firstname")).sendKeys("Doe");
        driver.findElement(By.id("email")).sendKeys("john.doe@example.com");
        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("passwordConfirmation")).sendKeys("Password123");
        driver.findElement(By.id("phone")).sendKeys("123456789");

        // Submit the form
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Verify the success message
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'Account created successfully')]")));
        assert successMessage.isDisplayed();
    }

    @Test(priority = 2)
    public void testLogin() {
        // Navigate to the Login page
        driver.findElement(By.linkText("Login")).click();

        // Fill the login form
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("john.doe@example.com");
        driver.findElement(By.id("password")).sendKeys("Password123");

        // Submit the form
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Verify redirection to the dashboard
        WebElement dashboardTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(), 'Welcome back')]")));
        assert dashboardTitle.isDisplayed();
    }

    @Test(priority = 3)
    public void testSearchCarpools() {
        // Navigate to the homepage
        driver.get("http://localhost:4200");

        // Fill the search form
        driver.findElement(By.name("departure")).sendKeys("New York");
        driver.findElement(By.name("arrival")).sendKeys("Boston");
        driver.findElement(By.name("date")).sendKeys("2024-12-31");
        driver.findElement(By.name("seats")).sendKeys("2");

        // Submit the search form
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Verify search results
        WebElement resultCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'New York - Boston')]")));
        assert resultCard.isDisplayed();
    }

    @Test(priority = 4)
    public void testPublishTrip() {
        // Navigate to the Publish a Trip page
        driver.findElement(By.linkText("Publish a trip")).click();

        // Fill the publish trip form
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("departure"))).sendKeys("Boston");
        driver.findElement(By.name("arrival")).sendKeys("Washington DC");
        driver.findElement(By.id("departureDate")).sendKeys("2024-12-31T10:00");
        driver.findElement(By.name("seats")).sendKeys("3");
        driver.findElement(By.name("price")).sendKeys("50");
        driver.findElement(By.name("description")).sendKeys("A comfortable trip with 3 seats available");

        // Submit the form
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Verify success message
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'Trip published successfully')]")));
        assert successMessage.isDisplayed();
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}