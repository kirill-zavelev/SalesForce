package com.salesforce;

import com.github.javafaker.Faker;
import com.salesforce.dto.AccountForm;
import com.salesforce.page.AccountDetailsPage;
import com.salesforce.page.AccountsPage;
import com.salesforce.page.LoginPage;
import com.salesforce.step.AccountSteps;
import com.salesforce.util.Industry;
import com.salesforce.util.Type;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CreateAccountTest extends BaseTest {

    private Faker faker;
    private List<AccountForm> expectedAccounts;
    private AccountForm expectedAccount;
    private AccountSteps accountSteps;

    @BeforeMethod
    public void setUp() {
        accountSteps = new AccountSteps(driver);
        expectedAccounts = new ArrayList<>();
        faker = new Faker();
        new LoginPage(driver).open()
                .login()
                .waitForPageOpening();
    }

    @Test()
    public void checkAccountCreationWithMandatoryFields() {
        expectedAccount = AccountForm.builder()
                .accountName(faker.name().firstName())
                .build();
        final String expectedAccountCreatedMessage = "Account " + "\"" + expectedAccount.getAccountName() + "\""
                + " was created.";
        AccountDetailsPage accountDetailsPage = accountSteps.createNewAccountWithMandatoryFields(expectedAccount);
        Assertions.assertThat(accountDetailsPage.getAccountCreatedMessage())
                .as("Message should be " + expectedAccountCreatedMessage)
                .isEqualTo(expectedAccountCreatedMessage);
        AccountForm actualAccount = accountDetailsPage.getAccountWithMandatoryFields();
        Assertions.assertThat(actualAccount).as("Account is invalid").isEqualTo(expectedAccount);
        List<String> accounts = new AccountsPage(driver)
                .open()
                .waitForPageOpening()
                .getAccountsNames();
        Assertions.assertThat(accounts)
                .as("List should contain of: " + expectedAccount.getAccountName())
                .contains(expectedAccount.getAccountName());
    }

    @Test()
    public void checkAccountCreationWithAllFields() {
        expectedAccount = AccountForm.builder()
                .accountName(faker.name().firstName())
                .phone(faker.phoneNumber().cellPhone())
                .employees(RandomStringUtils.random(1, false, true))
                .website(faker.internet().domainName())
                .billingStreet(faker.address().streetName())
                .shippingStreet(faker.address().streetName())
                .fax(RandomStringUtils.random(5, false, true))
                .type(Type.ANALYST.toString())
                .industry(Industry.CHEMISTRY.toString())
                .build();
        final String expectedAccountCreatedMessage = "Account " + "\"" + expectedAccount.getAccountName() + "\""
                + " was created.";
        AccountDetailsPage accountDetailsPage = accountSteps.createNewAccount(expectedAccount);
        Assertions.assertThat(accountDetailsPage.getAccountCreatedMessage())
                .as("Message should be " + expectedAccountCreatedMessage)
                .isEqualTo(expectedAccountCreatedMessage);
        AccountForm actualAccount = accountDetailsPage.getAccount();
        Assertions.assertThat(actualAccount).as("Account is invalid").isEqualTo(expectedAccount);
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

    @AfterMethod
    public void cleanUp() {
        Collections.addAll(expectedAccounts, expectedAccount);
        AccountsPage accountsPage = new AccountsPage(driver)
                .open()
                .waitForPageOpening();
        List<String> accountsNames = expectedAccounts.stream()
                .map(AccountForm::getAccountName)
                .collect(Collectors.toList());
        for (String accountName : accountsNames) {
            accountsPage.deleteAccount(accountName);
        }
        expectedAccounts = null;
    }
}
