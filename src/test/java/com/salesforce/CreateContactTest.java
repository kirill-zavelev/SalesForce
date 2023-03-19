package com.salesforce;

import com.github.javafaker.Faker;
import com.salesforce.dto.ContactForm;
import com.salesforce.page.AccountsPage;
import com.salesforce.page.ContactDetailsPage;
import com.salesforce.page.ContactsPage;
import com.salesforce.page.LoginPage;
import com.salesforce.step.ContactSteps;
import com.salesforce.util.LeadSource;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CreateContactTest extends BaseTest {

    private Faker faker;
    private List<ContactForm> expectedContacts;
    private ContactForm expectedContact;
    private ContactSteps contactSteps;

    @BeforeMethod
    public void generateContact() {
        contactSteps = new ContactSteps(driver);
        expectedContacts = new ArrayList<>();
        faker = new Faker();
        new LoginPage(driver).open()
                .login()
                .waitForPageOpening();
    }

    @Test
    public void checkContactCreation() {
        expectedContact = ContactForm.builder()
                .firstName(faker.name().firstName())
                .email(faker.internet().emailAddress())
                .mobile(faker.phoneNumber().cellPhone())
                .phone(faker.phoneNumber().cellPhone())
                .title(faker.cat().name())
                .mailingStreet(faker.address().streetName())
                .fax(RandomStringUtils.random(5, false, true))
                .department(RandomStringUtils.random(2, false, true))
                .otherPhone(faker.phoneNumber().cellPhone())
                .leadSource(LeadSource.SOCIAL.toString())
                .build();
        String accountName = new AccountsPage(driver).open()
                .waitForPageOpening()
                .getRandomAccountName();
        expectedContact.setAccountName(accountName);
        ContactDetailsPage contactDetailsPage = contactSteps.createNewContact(expectedContact);
        final String expectedContactCreatedMessage = "Contact " + "\"" + expectedContact.getFirstName() + "\""
                + " was created.";
        Assertions.assertThat(contactDetailsPage.getContactCreatedMessage())
                .as("Message should be " + expectedContactCreatedMessage)
                .isEqualTo(expectedContactCreatedMessage);
        ContactForm actualContact = contactDetailsPage.getContact();
        Assertions.assertThat(actualContact)
                .as("Contact invalid")
                .isEqualTo(expectedContact);
        List<String> contacts = new ContactsPage(driver)
                .open()
                .waitForPageOpening()
                .getContactsNames();
        Assertions.assertThat(contacts)
                .as("List should contain of: " + expectedContact.getFirstName())
                .contains(expectedContact.getFirstName());
    }

    @AfterMethod
    public void cleanUp() {
        Collections.addAll(expectedContacts, expectedContact);
        ContactsPage contactsPage = new ContactsPage(driver)
                .open()
                .waitForPageOpening();
        List<String> contactsNames = expectedContacts.stream()
                .map(ContactForm::getFirstName)
                .collect(Collectors.toList());
        for (String contactName : contactsNames) {
            contactsPage.deleteContact(contactName);
        }
        expectedContacts = null;
    }
}
