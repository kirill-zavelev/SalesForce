package com.salesforce;

import com.github.javafaker.Faker;
import com.salesforce.model.ContactForm;
import com.salesforce.page.AccountsPage;
import com.salesforce.page.ContactsPage;
import com.salesforce.page.LoginPage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class ContactTest extends BaseTest {

    @Test
    public void checkContactCreation() {
        Random random = new Random();
        new LoginPage(driver).open()
                .login()
                .waitForPageOpening();
        List<String> accounts = new AccountsPage(driver).open()
                .waitForPageOpening()
                .getAccountNames();
        String a = accounts.get(random.nextInt(accounts.size()));

        ContactForm contactForm = new ContactForm();
        contactForm.setFirstName(new Faker().name().firstName());
        contactForm.setLastName(new Faker().name().lastName());
        contactForm.setEmail(new Faker().internet().emailAddress());
        contactForm.setMobile(new Faker().phoneNumber().cellPhone());
        contactForm.setAccountName(a);
        ContactsPage contactsPage = new ContactsPage(driver)
                .open()
                .waitForPageOpening()
                .createNewAccount()
                .fillInContactInformation(contactForm)
                .saveContact();
        final String expectedContactCreatedMessage = "Contact " + "\"" + contactForm.getLastName() + "\""
                + " was created.";
        Assertions.assertThat(contactsPage.getContactCreatedMessage())
                .as("Message should be " + expectedContactCreatedMessage)
                .isEqualTo(expectedContactCreatedMessage);
    }
}
