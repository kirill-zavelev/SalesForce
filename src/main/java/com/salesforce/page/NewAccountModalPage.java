package com.salesforce.page;

import com.beust.ah.A;
import com.salesforce.dto.AccountForm;
import com.salesforce.wrapper.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewAccountModalPage extends BasePage {

    private static final By SAVE_ACCOUNT = By.xpath("//button[@title='Save']");

    public NewAccountModalPage(WebDriver driver) {
        super(driver);
    }

    public NewAccountModalPage fillInAccountInformation(AccountForm accountForm) {
       new Account(driver, "Account Name").fillIn(accountForm.getAccountName(), false);
       new Account(driver, "Website").fillIn(accountForm.getWebsite(), false);
       new Account(driver, "Phone").fillIn(accountForm.getPhone(), false);
       new Account(driver, "Employees").fillIn(accountForm.getEmployees(), false);
       new Account(driver, "Billing Street").fillIn(accountForm.getBillingStreet(), true);
       new Account(driver, "Shipping Street").fillIn(accountForm.getShippingStreet(), true);
       new Account(driver, "Fax").fillIn(accountForm.getFax(), false);
       new Account(driver, "Industry").selectItemFromDropDown(accountForm.getIndustry());
       new Account(driver, "Type").selectItemFromDropDown(accountForm.getType());
       return this;
    }

    public AccountDetailsPage saveAccount() {
        driver.findElement(SAVE_ACCOUNT).click();
        return new AccountDetailsPage(driver);
    }
}
