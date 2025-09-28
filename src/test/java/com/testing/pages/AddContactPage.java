package com.testing.pages;

import com.testing.models.Contact;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddContactPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(id = "birthdate")
    private WebElement birthdateField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "phone")
    private WebElement phoneField;

    @FindBy(id = "street1")
    private WebElement street1Field;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "stateProvince")
    private WebElement stateField;

    @FindBy(id = "postalCode")
    private WebElement postalCodeField;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public AddContactPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void addContact(Contact contact) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField));

        firstNameField.sendKeys(contact.getFirstName());
        lastNameField.sendKeys(contact.getLastName());
        birthdateField.sendKeys(contact.getBirthdate());
        emailField.sendKeys(contact.getEmail());
        phoneField.sendKeys(contact.getPhone());
        street1Field.sendKeys(contact.getStreet1());
        cityField.sendKeys(contact.getCity());
        stateField.sendKeys(contact.getState());
        postalCodeField.sendKeys(contact.getPostalCode());
        countryField.sendKeys(contact.getCountry());
        submitButton.click();
    }

    public void waitForRedirect() {
        wait.until(ExpectedConditions.urlContains("/contactList"));
    }

    public boolean isOnAddContactPage() {
        try {
            return firstNameField.isDisplayed() &&
                    driver.getCurrentUrl().contains("/addContact");
        } catch (Exception e) {
            return false;
        }
    }
}