package com.salesforce.page;

import com.salesforce.model.AccountForm;
import com.salesforce.wrapper.FormattedText;
import com.salesforce.wrapper.FormattedTextWithLink;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDetailsPage extends BasePage {

    public AccountDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getAccountCreatedMessage() {
        return getMessageAfterActionsWithEntity();
    }

    public String getAccountInformation(String label) {
        return new FormattedText(driver, label).getText();
    }

    public String getAccountInformationWithLink(String label) {
        return new FormattedTextWithLink(driver, label).getText();
    }

    public AccountForm getAccount() {
        String employees = driver.findElement(By.xpath("//span[text()='Employees']//ancestor::records-record-layout-item//div//lightning-formatted-number")).getText();
        AccountForm accountForm = new AccountForm();
        accountForm.setAccountName(getAccountInformation("Account Name"));
        accountForm.setPhone(getAccountInformationWithLink("Phone"));
        accountForm.setFax(getAccountInformationWithLink("Fax"));
        accountForm.setWebsite(getAccountInformationWithLink("Website"));
        accountForm.setBillingStreet(getAccountInformationWithLink("Billing Address"));
        accountForm.setShippingStreet(getAccountInformationWithLink("Shipping Address"));
        accountForm.setEmployees(employees);
        accountForm.setType(getAccountInformation("Type"));
        accountForm.setIndustry(getAccountInformation("Industry"));
        return accountForm;
    }
}
