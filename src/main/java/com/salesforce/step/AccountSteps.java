package com.salesforce.step;

import com.salesforce.dto.AccountForm;
import com.salesforce.page.AccountDetailsPage;
import com.salesforce.page.AccountsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountSteps {

    WebDriver driver;
    WebDriverWait wait;

    public AccountSteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public AccountDetailsPage createNewAccount(AccountForm accountForm) {
        new AccountsPage(driver).open()
                .createNewAccount()
                .fillInAccountInformation(accountForm)
                .saveAccount();
        return new AccountDetailsPage(driver);
    }

    public AccountDetailsPage createNewAccountWithMandatoryFields(AccountForm accountForm) {
        new AccountsPage(driver).open()
                .createNewAccount()
                .fillInMandatoryAccountInformation(accountForm)
                .saveAccount();
        return new AccountDetailsPage(driver);
    }
}
