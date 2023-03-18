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
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CreateAccountTest extends BaseTest {

    private Faker faker;
    private List<AccountForm> expectedAccounts;
    private AccountForm expectedAccount;
    private AccountForm secondExpectedAccount;
    private AccountForm thirdExpectedAccount;

    @BeforeMethod
    public void generateAccounts() {
        expectedAccounts = new ArrayList<>();
        faker = new Faker();
        new LoginPage(driver).open()
                .login()
                .waitForPageOpening();
//        firstExpectedAccount = AccountForm.builder()
//                .accountName(faker.name().firstName())
//                .phone(faker.phoneNumber().cellPhone())
//                .employees(RandomStringUtils.random(1, false, true))
//                .website(faker.internet().domainName())
//                .billingStreet(faker.address().streetName())
//                .shippingStreet(faker.address().streetName())
//                .fax(RandomStringUtils.random(5, false, true))
//                .type(Type.ANALYST.toString())
//                .industry(Industry.CHEMISTRY.toString())
//                .build();
//        Collections.addAll(expectedAccounts, firstExpectedAccount);
    }

    @Test()
    public void checkAccountCreationWithMandatoryFields() {
        expectedAccount = AccountForm.builder()
                .accountName(faker.name().firstName())
//                .phone(faker.phoneNumber().cellPhone())
//                .employees(RandomStringUtils.random(1, false, true))
//                .website(faker.internet().domainName())
//                .billingStreet(faker.address().streetName())
//                .shippingStreet(faker.address().streetName())
//                .fax(RandomStringUtils.random(5, false, true))
//                .type(Type.ANALYST.toString())
//                .industry(Industry.CHEMISTRY.toString())
                .build();
        final String expectedAccountCreatedMessage = "Account " + "\"" + expectedAccount.getAccountName() + "\""
                + " was created.";
//        new LoginPage(driver).open()
//                .login()
//                .waitForPageOpening();
        AccountDetailsPage accountDetailsPage = new AccountsPage(driver).open()
                .waitForPageOpening()
                .createNewAccount()
                .fillInMandatoryAccountInformation(expectedAccount)
                .saveAccount();
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
//        new LoginPage(driver).open()
//                .login()
//                .waitForPageOpening();
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

//    @Test()
//    public void checkAccountExistsInTheListOfAllAccounts() {
//        thirdExpectedAccount = AccountForm.builder()
//                .accountName(faker.name().firstName())
//                .phone(faker.phoneNumber().cellPhone())
//                .employees(RandomStringUtils.random(1, false, true))
//                .website(faker.internet().domainName())
//                .billingStreet(faker.address().streetName())
//                .shippingStreet(faker.address().streetName())
//                .fax(RandomStringUtils.random(5, false, true))
//                .type(Type.ANALYST.toString())
//                .industry(Industry.CHEMISTRY.toString())
//                .build();
//        new LoginPage(driver).open()
//                .login()
//                .waitForPageOpening();
//        List<String> accounts = new AccountsPage(driver)
//                .open()
//                .waitForPageOpening()
//                .getAccountsNames();
//        Assertions.assertThat(accounts)
//                .as("List should contain of: " + thirdExpectedAccount.getAccountName())
//                .contains(secondExpectedAccount.getAccountName());
//        Assertions.assertThat(new AccountsPage(driver).getAccountPhone(secondExpectedAccount.getAccountName()))
//                .as("The phone should be " + secondExpectedAccount.getPhone())
//                .isEqualTo(secondExpectedAccount.getPhone());
//    }

    //
//    @Test(priority = 2)
//    public void checkAccountDeletion() {
//        new LoginPage(driver).open()
//                .login()
//                .waitForPageOpening();
//        final String expectedAccountDeletedMessage = "Account " + "\"" + expectedAccount.getAccountName() + "\""
//                + " was deleted.";
//        AccountsPage accountsPage = new AccountsPage(driver)
//                .open()
//                .waitForPageOpening()
//                .deleteAccount(expectedAccount.getAccountName());
//        Assertions.assertThat(accountsPage.getAccountDeletedMessage())
//                .as("Message should contains of " + expectedAccountDeletedMessage)
//                .contains(expectedAccountDeletedMessage);
//        Assertions.assertThat(new AccountsPage(driver).getAccountsNames())
//                .as("List of account names should not contain: " + expectedAccount.getAccountName())
//                .doesNotContain(expectedAccount.getAccountName());
//    }
    @AfterMethod
    public void cleanUp() {
        Collections.addAll(expectedAccounts, expectedAccount);
        System.out.println(expectedAccounts);
        AccountsPage accountsPage = new AccountsPage(driver)
                .open()
                .waitForPageOpening();
        List<String> accountsNames = expectedAccounts.stream()
                .map(AccountForm::getAccountName)
                .collect(Collectors.toList());
        for (String accountName : accountsNames) {
            accountsPage.deleteAccount(accountName);
        }
        expectedAccounts.clear();
        System.out.println(expectedAccounts);
    }
}
