package com.testing;

import com.testing.base.TestBase;
import com.testing.models.Contact;
import com.testing.pages.AddContactPage;
import com.testing.pages.ContactListPage;
import com.testing.pages.LoginPage;
import com.testing.pages.SignupPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.UUID;

public class ContactListTest extends TestBase {

    @Test
    public void testCreateUserAddFiveContacts() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        SignupPage signupPage = new SignupPage(driver);
        ContactListPage contactListPage = new ContactListPage(driver);
        AddContactPage addContactPage = new AddContactPage(driver);


        String uniqueId = UUID.randomUUID().toString().substring(0, 8);
        String email = "hanaa_" + uniqueId + "@example.com";
        String password = "Password123!";


        loginPage.navigateToHomePage();
        Thread.sleep(2000);


        loginPage.clickSignup();
        Thread.sleep(2000);

        signupPage.signUp("soso", "smai", email, password);
        Thread.sleep(3000);


        Assert.assertTrue(contactListPage.isOnContactListPage(),
                "User registration failed - not redirected to contact list");


        Contact[] contacts = {
                new Contact("Alice", "Smith", "1990-01-15", "alice.smith@example.com",
                        "0551000111", "123 Main St", "Riyadh", "RJ", "12345", "Saudi Arabia"),

                new Contact("Bob", "Johnson", "1985-05-20", "bob.johnson@example.com",
                        "0551000222", "456 Oak Ave", "Jeddah", "JD", "23456", "Saudi Arabia"),

                new Contact("Carol", "Williams", "1992-12-10", "carol.williams@example.com",
                        "0551000333", "789 Pine Rd", "Dammam", "DM", "34567", "Saudi Arabia"),

                new Contact("David", "Brown", "1988-07-03", "david.brown@example.com",
                        "0551000444", "321 Elm St", "Mecca", "MC", "45678", "Saudi Arabia"),

                new Contact("Eva", "Davis", "1995-03-25", "eva.davis@example.com",
                        "0551000555", "654 Maple Dr", "Medina", "MD", "56789", "Saudi Arabia")
        };

        for (int i = 0; i < contacts.length; i++) {
            contactListPage.clickAddContact();
            Thread.sleep(1000);


            Assert.assertTrue(addContactPage.isOnAddContactPage(),
                    "Add contact page not loaded for contact " + (i+1));

            addContactPage.addContact(contacts[i]);
            Thread.sleep(2000);


            Assert.assertTrue(contactListPage.isContactDisplayed(
                            contacts[i].getFirstName(), contacts[i].getLastName()),
                    "Contact " + (i+1) + " not displayed: " + contacts[i].getFirstName());

            System.out.println("Contact " + (i+1) + " added successfully: " +
                    contacts[i].getFirstName() + " " + contacts[i].getLastName());
        }


        int finalCount = contactListPage.getContactCount();
        Assert.assertEquals(finalCount, 5,
                "Expected 5 contacts, but found " + finalCount);

        System.out.println("Total contacts verified: " + finalCount);


        contactListPage.logout();
        Thread.sleep(2000);

        System.out.println("Test completed successfully!");
    }

    @Test
    public void testLoginWithExistingUser() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        ContactListPage contactListPage = new ContactListPage(driver);

        loginPage.navigateToHomePage();
        Thread.sleep(2000);


        loginPage.login("test_existing@example.com", "Password123!");
        Thread.sleep(3000);

        Assert.assertTrue(contactListPage.isOnContactListPage(),
                "Login with existing user failed");

        System.out.println("Existing user login test passed");


        contactListPage.logout();
    }
}