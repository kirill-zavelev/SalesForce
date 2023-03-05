package com.salesforce.page;

import com.salesforce.model.AccountForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewAccountModalPage extends BasePage {

    private By SAVE_ACCOUNT = By.xpath("//button[@title='Save']");

    public NewAccountModalPage(WebDriver driver) {
        super(driver);
    }

    public NewAccountModalPage fillInAccountInformation(AccountForm accountForm) {
        new AccountForm.Builder(driver)
                .fillAccountName(accountForm.getAccountName())
                .fillWebsite(accountForm.getWebsite())
                .fillPhone(accountForm.getPhone())
                .fillEmployees(accountForm.getEmployees())
                .build();
        return this;
    }

    public AccountsPage saveAccount() {
        driver.findElement(SAVE_ACCOUNT).click();
        return new AccountsPage(driver);
    }
}
