package com.salesforce;

import com.salesforce.dto.AccountForm;
import com.salesforce.page.AccountsPage;
import com.salesforce.page.LoginPage;
import com.salesforce.util.Industry;
import com.salesforce.util.Type;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class DeleteAccountTest extends BaseTest {

    @Test()
    public void checkAccountDeletion() {
        AccountForm expectedAccount = AccountForm.builder()
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
