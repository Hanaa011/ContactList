package com.testing.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class taskAddRecords {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://claruswaysda.github.io/addRecordWebTable.html");

        wait.until(ExpectedConditions.titleContains("Web Table"));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @DataProvider(name = "usersData")
    public Object[][] getData() {
        return new Object[][]{
                {"Hanaa", "25", "USA"},
                {"Ahmed", "30", "UK"},
                {"Sara", "22", "Canada"},
        };
    }

    @Test(dataProvider = "usersData")
    public void addUserRecord(String firstName, String age, String department) throws InterruptedException {


        driver.findElement(By.id("nameInput"));
        driver.findElement(By.id("nameInput")).sendKeys(firstName);

        driver.findElement(By.id("ageInput"));
        driver.findElement(By.id("ageInput")).sendKeys(age);


        WebElement countrySelect = driver.findElement(By.id("countrySelect"));
        countrySelect.sendKeys(department);


        WebElement addRecordButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add Record']")));
        addRecordButton.click();

        Thread.sleep(1000);
    }
}