package com.salesforce;

import com.github.javafaker.Faker;
import com.salesforce.model.AccountForm;
import com.salesforce.page.AccountDetailsPage;
import com.salesforce.page.AccountsPage;
import com.salesforce.page.LoginPage;
import com.salesforce.util.Industry;
import com.salesforce.util.Type;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AccountCreationTest extends BaseTest {

    private AccountForm expectedAccount;
    private AccountsPage accountsPage;

    @BeforeMethod
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
    @Test
    public void checkAccountCreation() {
//        AccountForm accountForm = new AccountForm();
//        accountForm.setAccountName(new Faker().name().firstName());
//        accountForm.setPhone(new Faker().phoneNumber().cellPhone());
//        accountForm.setEmployees(RandomStringUtils.random(1, false, true));
//        accountForm.setWebsite(new Faker().internet().domainName());
//        accountForm.setBillingStreet(new Faker().address().streetName());
//        accountForm.setShippingStreet(new Faker().address().streetName());
//        accountForm.setFax("1111");
//        accountForm.setType(Type.ANALYST.toString());
//        accountForm.setIndustry(Industry.CHEMISTRY.toString());
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
        System.out.println(actualAccount);
        System.out.println(expectedAccount);
        Assertions.assertThat(actualAccount).as("Account is invalid").isEqualTo(expectedAccount);
//        accountDetailsPage.open().waitForPageOpening();
//        Assertions.assertThat(new AccountsPage(driver).getAccountsNames())
//                .as("List of account names should contain: " + accountForm.getAccountName())
//                .contains(accountForm.getAccountName());
//        Assertions.assertThat(accountDetailsPage.getAccountPhone(accountForm.getAccountName()))
//                .as("The phone should be " + accountForm.getPhone())
//                .isEqualTo(accountForm.getPhone());
    }

//    @Test
//    public void checkAccountDeletion() {
//        AccountForm accountForm = new AccountForm();
//        accountForm.setAccountName(new Faker().name().fullName());
//        accountForm.setPhone(new Faker().phoneNumber().cellPhone());
//        accountForm.setEmployees(RandomStringUtils.random(2, false, true));
//        accountForm.setWebsite(new Faker().internet().domainName());
//        final String expectedAccountDeletedMessage = "Account " + "\"" + accountForm.getAccountName() + "\""
//                + " was deleted.";
//        new LoginPage(driver).open()
//                .login()
//                .waitForPageOpening();
//        AccountsPage accountsPage = new AccountsPage(driver).open()
//                .waitForPageOpening()
//                .createNewAccount()
//                .fillInAccountInformation(accountForm)
//                .saveAccount()
//                .open()
//                .waitForPageOpening()
//                .deleteAccount(accountForm.getAccountName());
//        Assertions.assertThat(accountsPage.getAccountDeletedMessage())
//                .as("Message should contains of " + expectedAccountDeletedMessage)
//                .contains(expectedAccountDeletedMessage);
//        Assertions.assertThat(new AccountsPage(driver).getAccountsNames())
//                .as("List of account names should not contain: " + accountForm.getAccountName())
//                .doesNotContain(accountForm.getAccountName());
//    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        final String expectedAccountDeletedMessage = "Account " + "\"" + expectedAccount.getAccountName() + "\""
                + " was deleted.";
        accountsPage = new AccountsPage(driver);
        accountsPage.open().waitForPageOpening().deleteAccount(expectedAccount.getAccountName());
        Assertions.assertThat(accountsPage.getAccountDeletedMessage())
                .as("Message should contains of " + expectedAccountDeletedMessage)
                .contains(expectedAccountDeletedMessage);
        Assertions.assertThat(new AccountsPage(driver).getAccountsNames())
                .as("List of account names should not contain: " + expectedAccount.getAccountName())
                .doesNotContain(expectedAccount.getAccountName());
    }
}
