package com.testing.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class TestBase {
     public WebDriver driver;
    public final String BASE_URL = "https://thinking-tester-contact-list.herokuapp.com/";

    @BeforeClass
    public void setUp() {
        System.out.println("=== Testing after freeing up space ===");
        System.out.println("✅ Free space: 10GB (was 6GB)");

        try {
            WebDriverManager.chromedriver().setup(); // يجهز الكروم درايفر

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--start-maximized");

            driver = new ChromeDriver(options);
            System.out.println("🎉 Chrome started successfully!");

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(BASE_URL);
            System.out.println("✅ Page loaded: " + driver.getTitle());

        } catch (Exception e) {
            System.out.println("❌ Still failing: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("✅ Browser closed");
        }
    }
}