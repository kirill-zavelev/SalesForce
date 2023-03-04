package com.salesforce;

import com.github.javafaker.Faker;
import com.salesforce.model.AccountForm;
import com.salesforce.page.AccountsPage;
import com.salesforce.page.LoginPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class AccountCreationTest extends BaseTest {

    @Test
    public void checkAccountCreation() {
        AccountForm accountForm = new AccountForm();
        accountForm.setAccountName(new Faker().name().fullName());
        accountForm.setPhone(new Faker().phoneNumber().cellPhone());
        accountForm.setEmployees(RandomStringUtils.random(2, false, true));
        accountForm.setWebsite(new Faker().internet().domainName());
        final String expectedAccountCreatedMessage = "Account " + "\"" + accountForm.getAccountName() + "\""
                + " was created.";
        new LoginPage(driver).open()
                             .login()
                             .waitForPageOpening();
        AccountsPage accountsPage = new AccountsPage(driver).open()
                                .waitForPageOpening()
                                .createNewAccount()
                                .fillInAccountInformation(accountForm)
                                .saveAccount();
        Assertions.assertThat(accountsPage.getAccountCreatedMessage())
                .as("Message should be " + expectedAccountCreatedMessage)
                .isEqualTo(expectedAccountCreatedMessage);
        accountsPage.open().waitForPageOpening();
        Assertions.assertThat(accountsPage.getAccountNames())
                .as("List of account names should contain: " + accountForm.getAccountName())
                .contains(accountForm.getAccountName());
        Assertions.assertThat(accountsPage.getAccountPhone(accountForm.getAccountName()))
                .as("The phone should be " + accountForm.getPhone())
                .isEqualTo(accountForm.getPhone());
    }

    @Test
    public void checkAccountDeletion() {
        AccountForm accountForm = new AccountForm();
        accountForm.setAccountName(new Faker().name().fullName());
        accountForm.setPhone(new Faker().phoneNumber().cellPhone());
        accountForm.setEmployees(RandomStringUtils.random(2, false, true));
        accountForm.setWebsite(new Faker().internet().domainName());
        final String expectedAccountDeletedMessage = "Account " + "\"" + accountForm.getAccountName() + "\""
                + " was deleted.";
        new LoginPage(driver).open()
                .login()
                .waitForPageOpening();
        AccountsPage accountsPage = new AccountsPage(driver).open()
                .waitForPageOpening()
                .createNewAccount()
                .fillInAccountInformation(accountForm)
                .saveAccount()
                .open()
                .waitForPageOpening()
                .deleteAccount(accountForm.getAccountName());
        Assertions.assertThat(accountsPage.getAccountDeletedMessage())
                .as("Message should contains of " + expectedAccountDeletedMessage)
                .contains(expectedAccountDeletedMessage);
        Assertions.assertThat(accountsPage.getAccountNames())
                .as("List of account names should not contain: " + accountForm.getAccountName())
                .doesNotContain(accountForm.getAccountName());
    }
}
