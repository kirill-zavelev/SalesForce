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
                .fillBillingStreet(accountForm.getBillingStreet())
                .fillShippingStreet(accountForm.getShippingStreet())
                .fillFax(accountForm.getFax())
                .selectIndustry(accountForm.getIndustry())
                .selectType(accountForm.getType())
                .build();
        return this;
    }

    public AccountDetailsPage saveAccount() {
        driver.findElement(SAVE_ACCOUNT).click();
        return new AccountDetailsPage(driver);
    }
}
