package com.ankita.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class CartJourneyTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void verifyCartPageButtons() {

        driver.get("https://www.saucedemo.com/");

        // Login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Add first product
        driver.findElement(By.linkText("Sauce Labs Backpack")).click();
        driver.findElement(By.id("add-to-cart")).click();

        // Back to products
        driver.findElement(By.id("back-to-products")).click();

        // Add second product
        driver.findElement(By.linkText("Sauce Labs Bike Light")).click();
        driver.findElement(By.id("add-to-cart")).click();

        // Open cart
        driver.findElement(By.className("shopping_cart_link")).click();

        // Validate buttons
        Assert.assertTrue(driver.findElement(By.id("continue-shopping")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("checkout")).isDisplayed());

        List<WebElement> removeButtons =
                driver.findElements(By.xpath("//button[contains(text(),'Remove')]"));

        Assert.assertTrue(removeButtons.size() >= 2);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
