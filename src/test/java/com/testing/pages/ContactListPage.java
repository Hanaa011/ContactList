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

    @FindBy(xpath = "//h1[contains(text(), 'Contact List')]")
    private WebElement pageTitle;

    @FindBy(className = "contactTable")
    private WebElement contactTable;

    @FindBy(xpath = "//tr[@class='contactTableBodyRow']")
    private List<WebElement> contactRows;

    @FindBy(id = "logout")
    private WebElement logoutButton;

    public ContactListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }


    public AddContactPage clickAddContact() {
        wait.until(ExpectedConditions.elementToBeClickable(addContactButton)).click();
        return new AddContactPage(driver);
    }


    public int getContactCount() {
        wait.until(ExpectedConditions.visibilityOf(contactTable));
        return contactRows.size();
    }


    public boolean isContactDisplayed(String firstName, String lastName) {
        wait.until(ExpectedConditions.visibilityOf(contactTable));
        String fullName = firstName + " " + lastName;

        for (WebElement row : contactRows) {
            if (row.getText().contains(fullName)) {
                return true;
            }
        }
        return false;
    }


    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }


    public boolean isOnContactListPage() {
        try {
            return pageTitle.isDisplayed() &&
                    driver.getCurrentUrl().contains("/contactList");
        } catch (Exception e) {
            return false;
        }
    }
}