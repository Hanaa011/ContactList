package com.testing.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ContactListPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "add-contact")
    private WebElement addContactButton;

    @FindBy(css = "table tr")
    private List<WebElement> contactRows;

    @FindBy(id = "logout")
    private WebElement logoutButton;

    public ContactListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickAddContact() {
        addContactButton.click();
    }

    public int getContactCount() {
        return contactRows.size() - 1; // ناقص header row
    }

    public boolean isContactDisplayed(String contactName) {
        for (WebElement row : contactRows) {
            if (row.getText().contains(contactName)) {
                return true;
            }
        }
        return false;
    }

    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(addContactButton));
    }
}