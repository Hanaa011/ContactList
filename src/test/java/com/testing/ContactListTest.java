package com.testing;

import com.testing.base.TestBase;
import com.testing.models.Contact;
import com.testing.pages.AddContactPage;
import com.testing.pages.ContactListPage;
import com.testing.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Instant;

public class ContactListTest extends TestBase {

    @Test
    public void testCreateUserAddFiveContacts() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        ContactListPage contactListPage = new ContactListPage(driver);
        AddContactPage addContactPage = new AddContactPage(driver);

        String username = "testuser_" + Instant.now().toEpochMilli();
        String password = "Pass1234!";

        boolean regOk = loginPage.registerUser(username, password);
        Assert.assertTrue(regOk, "Registration failed!");

        loginPage.login(username, password);

        Assert.assertTrue(contactListPage.isAt(), "Login failed, contact list not visible!");

        for (int i = 1; i <= 5; i++) {
            contactListPage.clickAddContact();
            Thread.sleep(500);
            Contact c = new Contact("First" + i, "Last" + i, "0551000" + i, "user" + i + "@example.com");
            addContactPage.addContact(c);
            addContactPage.wait();
            Thread.sleep(500);
            Assert.assertTrue(contactListPage.toString(c), "Contact not added: " + c);
        }

        int count = contactListPage.getContactCount();
        Assert.assertEquals(count, 5, "Total contacts should be 5");
    }
}
