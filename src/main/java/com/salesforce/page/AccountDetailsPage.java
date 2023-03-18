package com.salesforce.page;

import com.salesforce.dto.AccountForm;
import com.salesforce.wrapper.FormattedText;
import com.salesforce.wrapper.FormattedTextWithLink;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDetailsPage extends BasePage {

    private static final By EMPLOYEES_LOCATOR = By.xpath("//span[text()='Employees']" +
            "//ancestor::records-record-layout-item//div//lightning-formatted-number");

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
        String employees = driver.findElement(EMPLOYEES_LOCATOR).getText();
        return AccountForm.builder()
                .accountName(getAccountInformation("Account Name"))
                .phone(getAccountInformationWithLink("Phone"))
                .fax(getAccountInformationWithLink("Fax"))
                .website(getAccountInformationWithLink("Website"))
                .billingStreet(getAccountInformationWithLink("Billing Address"))
                .shippingStreet(getAccountInformationWithLink("Shipping Address"))
                .employees(employees)
                .type(getAccountInformation("Type"))
                .industry(getAccountInformation("Industry"))
                .build();
    }

    public AccountForm getAccountWithMandatoryFields() {
        String employees = driver.findElement(EMPLOYEES_LOCATOR).getText();
        return AccountForm.builder()
                .accountName(getAccountInformation("Account Name"))
                .build();
    }
}
