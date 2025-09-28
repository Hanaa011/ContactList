package com.testing.tests;

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
        String email = "hmsa_" + uniqueId + "@example.com";
        String password = "Password123!";


        loginPage.navigateToHomePage();
        Thread.sleep(2000);


        loginPage.clickSignup();
        Thread.sleep(2000);

        signupPage.signUp("Hanaa", "Saleh", email, password); // إنشاء الحساب
        Thread.sleep(3000);


        Assert.assertTrue(contactListPage.isOnContactListPage(),
                "User registration failed");


        Contact[] contacts = {
                new Contact("Alice", "Smith", "1990-01-15", "alice@example.com",
                        "0551000111", "123 Main St", "Riyadh", "RJ", "12345", "KSA"),
                new Contact("Bob", "Johnson", "1985-05-20", "bob@example.com",
                        "0551000222", "456 Oak Ave", "Jeddah", "JD", "23456", "KSA")
        };

        for (int i = 0; i < contacts.length; i++) {
            contactListPage.clickAddContact();
            Thread.sleep(1000);

            addContactPage.addContact(contacts[i]);
            Thread.sleep(2000);

            Assert.assertTrue(contactListPage.isContactDisplayed(
                    contacts[i].getFirstName(), contacts[i].getLastName()));

            System.out.println("Contact " + (i+1) + " added");
        }


        int finalCount = contactListPage.getContactCount();
        Assert.assertEquals(finalCount, 2, "Expected 2 contacts");

        System.out.println("Test completed!");
    }
}