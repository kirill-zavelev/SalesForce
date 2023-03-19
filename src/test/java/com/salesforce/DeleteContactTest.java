package com.salesforce;

import com.salesforce.dto.ContactForm;
import com.salesforce.page.AccountsPage;
import com.salesforce.page.ContactsPage;
import com.salesforce.page.LoginPage;
import com.salesforce.step.ContactSteps;
import com.salesforce.util.LeadSource;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class DeleteContactTest extends BaseTest {

    @Test()
    public void checkContactDeletion() {
        ContactForm expectedContact = ContactForm.builder()
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
        new LoginPage(driver).open()
                .login()
                .waitForPageOpening();
        String accountName = new AccountsPage(driver).open()
                .waitForPageOpening()
                .getRandomAccountName();
        expectedContact.setAccountName(accountName);
        new ContactSteps(driver).createNewContact(expectedContact);
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
