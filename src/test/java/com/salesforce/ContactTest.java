package com.salesforce;

import com.github.javafaker.Faker;
import com.salesforce.dto.ContactForm;
import com.salesforce.page.AccountsPage;
import com.salesforce.page.ContactDetailsPage;
import com.salesforce.page.ContactsPage;
import com.salesforce.page.LoginPage;
import com.salesforce.util.LeadSource;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class ContactTest extends BaseTest {

    private ContactForm expectedContact;

    @BeforeClass
    public void generateContact() {
        expectedContact = new ContactForm();
        expectedContact.setFirstName(new Faker().name().firstName());
        expectedContact.setEmail(new Faker().internet().emailAddress());
        expectedContact.setMobile(new Faker().phoneNumber().cellPhone());
        expectedContact.setPhone(new Faker().phoneNumber().cellPhone());
        expectedContact.setTitle(new Faker().cat().name());
        expectedContact.setMailingStreet(new Faker().address().streetName());
        expectedContact.setFax(RandomStringUtils.random(5, false, true));
        expectedContact.setDepartment(RandomStringUtils.random(2, false, true));
        expectedContact.setOtherPhone(new Faker().phoneNumber().cellPhone());
        expectedContact.setLeadSource(LeadSource.SOCIAL.toString());
    }

    @Test
    public void checkContactDataAfterCreation() {
        new LoginPage(driver).open()
                .login()
                .waitForPageOpening();
        String accountName = new AccountsPage(driver).open()
                .waitForPageOpening()
                .getRandomAccountName();
        expectedContact.setAccountName(accountName);
        ContactDetailsPage contactDetailsPage = new ContactsPage(driver)
                .open()
                .waitForPageOpening()
                .createNewAccount()
                .fillInContactInformation(expectedContact)
                .saveContact();
        final String expectedContactCreatedMessage = "Contact " + "\"" + expectedContact.getFirstName() + "\""
                + " was created.";
        Assertions.assertThat(contactDetailsPage.getContactCreatedMessage())
                .as("Message should be " + expectedContactCreatedMessage)
                .isEqualTo(expectedContactCreatedMessage);
        ContactForm actualContact = contactDetailsPage.getContact();
        Assertions.assertThat(actualContact)
                .as("Contact invalid")
                .isEqualTo(expectedContact);
    }

    @Test(priority = 1)
    public void checkContactExistsInTheListOfAllContacts() {
        new LoginPage(driver).open()
                .login()
                .waitForPageOpening();
        List<String> contacts = new ContactsPage(driver)
                .open()
                .waitForPageOpening()
                .getContactsNames();
        Assertions.assertThat(contacts)
                .as("List should contain of: " + expectedContact.getFirstName())
                .contains(expectedContact.getFirstName());
    }

    @Test(priority = 2)
    public void checkContactDeletion() {
        new LoginPage(driver).open()
                .login()
                .waitForPageOpening();
        ContactsPage contactsPage = new ContactsPage(driver).open()
                .waitForPageOpening()
                .deleteContact(expectedContact.getFirstName());
        final String expectedContactDeletedMessage = "Contact " + "\"" + expectedContact.getFirstName() + "\""
                + " was deleted.";
        Assertions.assertThat(contactsPage.getContactDeletedMessage())
                .as("Message should contains of " + expectedContactDeletedMessage)
                .contains(expectedContactDeletedMessage);
        Assertions.assertThat(new ContactsPage(driver).getContactsNames())
                .as("List of contacts names should not contain: " + expectedContact.getFirstName())
                .doesNotContain(expectedContact.getFirstName());
    }
}
