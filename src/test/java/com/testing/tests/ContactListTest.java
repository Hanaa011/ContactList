package com.testing.tests;

import com.testing.base.TestBase;
import com.testing.pages.AddContactPage;
import com.testing.pages.ContactListPage;
import com.testing.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactListTest extends TestBase {

    @Test
    public void testCreateUserAddFiveContacts() {
        LoginPage loginPage = new LoginPage(driver);
        ContactListPage contactListPage = new ContactListPage(driver);
        AddContactPage addContactPage = new AddContactPage(driver);


        // 1. Create new user account
        loginPage.clickSignup();

        String email = "user" + System.currentTimeMillis() + "@test.com";
        loginPage.registerUser("John", "Doe", email, "password123");

        // 2. Verify successful registration and login
        contactListPage.waitForPageLoad();
        Assert.assertTrue(driver.getCurrentUrl().contains("contactList"), "Login failed");

        // 3. Add 5 contacts
        String[] firstNames = {"Alice", "Bob", "Charlie", "Diana", "Eve"};
        String[] lastNames = {"Smith", "Johnson", "Brown", "Davis", "Wilson"};

        for (int i = 0; i < 5; i++) {
            contactListPage.clickAddContact();
            addContactPage.addContact(
                    firstNames[i],
                    lastNames[i],
                    firstNames[i].toLowerCase() + i + "@test.com",
                    "123-456-789" + i
            );

            // Wait for contact list to reload
            contactListPage.waitForPageLoad();
        }

        // 4. Verify all contacts are added
        Assert.assertEquals(contactListPage.getContactCount(), 5, "Contact count mismatch");

        // 5. Verify each contact is displayed
        for (String firstName : firstNames) {
            Assert.assertTrue(contactListPage.isContactDisplayed(firstName),
                    "Contact " + firstName + " not found");
        }
    }
}