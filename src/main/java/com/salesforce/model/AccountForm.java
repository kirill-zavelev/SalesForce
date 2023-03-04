package com.salesforce.model;

import com.salesforce.wrapper.AccountFormInput;
import org.openqa.selenium.WebDriver;

public class AccountForm {

    private String accountName;
    private String phone;
    private String website;
    private String employees;

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setEmployees(String employees) {
        this.employees = employees;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public String getEmployees() {
        return employees;
    }

    public static class Builder {

        private AccountForm accountForm;
        private WebDriver driver;

        public Builder(WebDriver driver) {
            accountForm = new AccountForm();
            this.driver = driver;
        }

        public Builder fillAccountName(String accountName) {
            accountForm.accountName = accountName;
            new AccountFormInput(driver, "Account Name").fillIn(accountName);
            return this;
        }

        public Builder fillPhone(String phone) {
            accountForm.phone = phone;
            new AccountFormInput(driver, "Phone").fillIn(phone);
            return this;
        }

        public Builder fillWebsite(String website) {
            accountForm.website = website;
            new AccountFormInput(driver, "Website").fillIn(website);
            return this;
        }

        public Builder fillEmployees(String employees) {
            accountForm.employees = employees;
            new AccountFormInput(driver, "Employees").fillIn(employees);
            return this;
        }

        public AccountForm build() {
            return accountForm;
        }
    }
}
