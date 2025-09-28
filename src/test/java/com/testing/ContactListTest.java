package com.testing;

import com.testing.base.TestBase;
import com.testing.models.Contact;
import com.testing.pages.AddContactPage;
import com.testing.pages.ContactListPage;
import com.testing.pages.LoginPage;
import com.testing.pages.SignupPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Instant;
import java.util.UUID;

public class ContactListTest extends TestBase {

    @Test
    public void testCreateUserAddFiveContacts() throws InterruptedException {
        // تهيئة Pages
        LoginPage loginPage = new LoginPage(driver);
        SignupPage signupPage = new SignupPage(driver);
        ContactListPage contactListPage = new ContactListPage(driver);
        AddContactPage addContactPage = new AddContactPage(driver);

        // إنشاء بيانات مستخدم فريدة
        String uniqueId = UUID.randomUUID().toString().substring(0, 8);
        String email = "hanaa_" + uniqueId + "@example.com";
        String password = "Password123!";

        // 1. الانتقال إلى الصفحة الرئيسية
        loginPage.navigateToHomePage();
        Thread.sleep(2000);

        // 2. إنشاء مستخدم جديد
        loginPage.clickSignup();
        Thread.sleep(2000);

        signupPage.signUp("soso", "smai", email, password);
        Thread.sleep(3000);

        // 3. التحقق من التسجيل الناجح (يجب أن نكون في صفحة Contact List)
        Assert.assertTrue(contactListPage.isOnContactListPage(),
                "User registration failed - not redirected to contact list");

        // 4. إضافة 5 جهات اتصال
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
            // الانتقال إلى صفحة إضافة جهة اتصال
            contactListPage.clickAddContact();
            Thread.sleep(1000);

            // التحقق من أننا في صفحة الإضافة
            Assert.assertTrue(addContactPage.isOnAddContactPage(),
                    "Add contact page not loaded for contact " + (i+1));

            // إضافة الجهة
            addContactPage.addContact(contacts[i]);
            Thread.sleep(2000);

            // التحقق من ظهور الجهة في القائمة
            Assert.assertTrue(contactListPage.isContactDisplayed(
                            contacts[i].getFirstName(), contacts[i].getLastName()),
                    "Contact " + (i+1) + " not displayed: " + contacts[i].getFirstName());

            System.out.println("✅ Contact " + (i+1) + " added successfully: " +
                    contacts[i].getFirstName() + " " + contacts[i].getLastName());
        }

        // 5. التحقق من العدد الإجمالي
        int finalCount = contactListPage.getContactCount();
        Assert.assertEquals(finalCount, 5,
                "Expected 5 contacts, but found " + finalCount);

        System.out.println("✅ Total contacts verified: " + finalCount);

        // 6. تسجيل الخروج (اختياري)
        contactListPage.logout();
        Thread.sleep(2000);

        System.out.println("🎉 Test completed successfully!");
    }

    @Test
    public void testLoginWithExistingUser() throws InterruptedException {
        // اختبار إضافي لتسجيل الدخول بمستخدم موجود
        LoginPage loginPage = new LoginPage(driver);
        ContactListPage contactListPage = new ContactListPage(driver);

        loginPage.navigateToHomePage();
        Thread.sleep(2000);

        // استخدام بيانات اختبارية (عدلها حسب ما أنشأته)
        loginPage.login("test_existing@example.com", "Password123!");
        Thread.sleep(3000);

        Assert.assertTrue(contactListPage.isOnContactListPage(),
                "Login with existing user failed");

        System.out.println("✅ Existing user login test passed");

        // تسجيل الخروج
        contactListPage.logout();
    }
}