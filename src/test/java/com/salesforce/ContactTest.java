package com.salesforce;

import com.github.javafaker.Faker;
import com.salesforce.model.ContactForm;
import com.salesforce.page.AccountsPage;
import com.salesforce.page.ContactsPage;
import com.salesforce.page.LoginPage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class ContactTest extends BaseTest {

    @Test
    public void checkContactCreation() {
        new LoginPage(driver).open()
                .login()
                .waitForPageOpening();
        String accountName = new AccountsPage(driver).open()
                .waitForPageOpening()
                .getRandomAccountName();
        ContactForm contactForm = new ContactForm();
        contactForm.setFirstName(new Faker().name().firstName());
        contactForm.setLastName(new Faker().name().lastName());
        contactForm.setEmail(new Faker().internet().emailAddress());
        contactForm.setMobile(new Faker().phoneNumber().cellPhone());
        contactForm.setAccountName(accountName);
        ContactsPage contactsPage = new ContactsPage(driver)
                .open()
                .waitForPageOpening()
                .createNewAccount()
                .fillInContactInformation(contactForm)
                .saveContact();
        final String expectedContactName = contactForm.getFirstName() + contactForm.getLastName();
        final String expectedContactCreatedMessage = "Contact " + "\"" + expectedContactName + "\""
                + " was created.";
        Assertions.assertThat(contactsPage.getContactCreatedMessage())
                .as("Message should be " + expectedContactCreatedMessage)
                .isEqualTo(expectedContactCreatedMessage);
        contactsPage.open().waitForPageOpening();
        Assertions.assertThat(new ContactsPage(driver).getContactsNames())
                .as("List should contain of: " + expectedContactName)
                .contains(expectedContactName);
    }

    @Test
    public void checkContactDeletion() {
        new LoginPage(driver).open()
                .login()
                .waitForPageOpening();
        String accountName = new AccountsPage(driver).open()
                .waitForPageOpening()
                .getRandomAccountName();
        ContactForm contactForm = new ContactForm();
        contactForm.setFirstName(new Faker().name().firstName());
        contactForm.setLastName(new Faker().name().lastName());
        contactForm.setEmail(new Faker().internet().emailAddress());
        contactForm.setMobile(new Faker().phoneNumber().cellPhone());
        contactForm.setAccountName(accountName);
        final String expectedContact = contactForm.getFirstName() + contactForm.getLastName();
        final String expectedContactCreatedMessage = "Contact " + "\"" + expectedContact + "\""
                + " was created.";
        final String expectedContactDeletedMessage = "Contact " + "\"" + expectedContact + "\""
                + " was deleted.";
        ContactsPage contactsPage = new ContactsPage(driver)
                .open()
                .waitForPageOpening()
                .createNewAccount()
                .fillInContactInformation(contactForm)
                .saveContact();
        Assertions.assertThat(contactsPage.getContactCreatedMessage())
                .as("Message should be " + expectedContactCreatedMessage)
                .isEqualTo(expectedContactCreatedMessage);
        contactsPage.open()
                .waitForPageOpening()
                .deleteContact(expectedContact);
        Assertions.assertThat(contactsPage.getContactDeletedMessage())
                .as("Message should contains of " + expectedContactDeletedMessage)
                .contains(expectedContactDeletedMessage);
        Assertions.assertThat(new ContactsPage(driver).getContactsNames())
                .as("List of contacts names should not contain: " + expectedContact)
                .doesNotContain(expectedContact);
    }
}
