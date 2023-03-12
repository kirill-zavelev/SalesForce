package com.salesforce;

import com.github.javafaker.Faker;
import com.salesforce.dto.AccountForm;
import com.salesforce.page.AccountDetailsPage;
import com.salesforce.page.AccountsPage;
import com.salesforce.page.LoginPage;
import com.salesforce.util.Industry;
import com.salesforce.util.Type;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class AccountTest extends BaseTest {

    private AccountForm expectedAccount;

    @BeforeClass
    public void generateAccount() {
        expectedAccount = new AccountForm();
        expectedAccount.setAccountName(new Faker().name().firstName());
        expectedAccount.setPhone(new Faker().phoneNumber().cellPhone());
        expectedAccount.setEmployees(RandomStringUtils.random(1, false, true));
        expectedAccount.setWebsite(new Faker().internet().domainName());
        expectedAccount.setBillingStreet(new Faker().address().streetName());
        expectedAccount.setShippingStreet(new Faker().address().streetName());
        expectedAccount.setFax(RandomStringUtils.random(5, false, true));
        expectedAccount.setType(Type.ANALYST.toString());
        expectedAccount.setIndustry(Industry.CHEMISTRY.toString());
    }

    @Test()
    public void checkAccountDataAfterCreation() {
        final String expectedAccountCreatedMessage = "Account " + "\"" + expectedAccount.getAccountName() + "\""
                + " was created.";
        new LoginPage(driver).open()
                .login()
                .waitForPageOpening();
        AccountDetailsPage accountDetailsPage = new AccountsPage(driver).open()
                .waitForPageOpening()
                .createNewAccount()
                .fillInAccountInformation(expectedAccount)
                .saveAccount();
        Assertions.assertThat(accountDetailsPage.getAccountCreatedMessage())
                .as("Message should be " + expectedAccountCreatedMessage)
                .isEqualTo(expectedAccountCreatedMessage);
        AccountForm actualAccount = accountDetailsPage.getAccount();
        Assertions.assertThat(actualAccount).as("Account is invalid").isEqualTo(expectedAccount);
    }

    @Test(priority = 1)
    public void checkAccountExistsInTheListOfAllAccounts() {
        new LoginPage(driver).open()
                .login()
                .waitForPageOpening();
        List<String> accounts = new AccountsPage(driver)
                .open()
                .waitForPageOpening()
                .getAccountsNames();
        Assertions.assertThat(accounts)
                .as("List should contain of: " + expectedAccount.getAccountName())
                .contains(expectedAccount.getAccountName());
        Assertions.assertThat(new AccountsPage(driver).getAccountPhone(expectedAccount.getAccountName()))
                .as("The phone should be " + expectedAccount.getPhone())
                .isEqualTo(expectedAccount.getPhone());
    }

    @Test(priority = 2)
    public void checkAccountDeletion() {
        new LoginPage(driver).open()
                .login()
                .waitForPageOpening();
        final String expectedAccountDeletedMessage = "Account " + "\"" + expectedAccount.getAccountName() + "\""
                + " was deleted.";
        AccountsPage accountsPage = new AccountsPage(driver)
                .open()
                .waitForPageOpening()
                .deleteAccount(expectedAccount.getAccountName());
        Assertions.assertThat(accountsPage.getAccountDeletedMessage())
                .as("Message should contains of " + expectedAccountDeletedMessage)
                .contains(expectedAccountDeletedMessage);
        Assertions.assertThat(new AccountsPage(driver).getAccountsNames())
                .as("List of account names should not contain: " + expectedAccount.getAccountName())
                .doesNotContain(expectedAccount.getAccountName());
    }
}
