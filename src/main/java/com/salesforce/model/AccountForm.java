package com.salesforce.model;

import com.salesforce.util.Industry;
import com.salesforce.util.Type;
import com.salesforce.wrapper.Account;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class AccountForm {

    private String accountName;
    private String phone;
    private String fax;
    private String website;
    private String employees;
    private String billingStreet;
    private String shippingStreet;
    private String type;
    private String industry;

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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

    public String getBillingStreet() {
        return billingStreet;
    }

    public void setBillingStreet(String billingStreet) {
        this.billingStreet = billingStreet;
    }

    public String getShippingStreet() {
        return shippingStreet;
    }

    public void setShippingStreet(String shippingStreet) {
        this.shippingStreet = shippingStreet;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
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
            new Account(driver, "Account Name").fillIn(accountName, false);
            return this;
        }

        public Builder fillPhone(String phone) {
            accountForm.phone = phone;
            new Account(driver, "Phone").fillIn(phone, false);
            return this;
        }

        public Builder fillWebsite(String website) {
            accountForm.website = website;
            new Account(driver, "Website").fillIn(website, false);
            return this;
        }

        public Builder fillEmployees(String employees) {
            accountForm.employees = employees;
            new Account(driver, "Employees").fillIn(employees, false);
            return this;
        }

        public Builder fillBillingStreet(String billingStreet) {
            accountForm.billingStreet = billingStreet;
            new Account(driver, "Billing Street").fillIn(billingStreet, true);
            return this;
        }

        public Builder fillShippingStreet(String shippingStreet) {
            accountForm.shippingStreet = shippingStreet;
            new Account(driver, "Shipping Street").fillIn(shippingStreet, true);
            return this;
        }

        public Builder fillFax(String fax) {
            accountForm.fax = fax;
            new Account(driver, "Fax").fillIn(fax, false);
            return this;
        }

        public Builder selectType(String type) {
            accountForm.type = type;
            new Account(driver, "Type").selectItemFromDropDown(type);
            return this;
        }

        public Builder selectIndustry(String industry) {
            accountForm.industry = industry;
            new Account(driver, "Industry").selectItemFromDropDown(industry);
            return this;
        }

        public AccountForm build() {
            return accountForm;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountForm)) return false;
        AccountForm that = (AccountForm) o;
        return accountName.equals(that.accountName) && phone.equals(that.phone) && fax.equals(that.fax)
                && website.equals(that.website) && employees.equals(that.employees)
                && billingStreet.equals(that.billingStreet) && shippingStreet.equals(that.shippingStreet)
                && Objects.equals(type, that.type) && Objects.equals(industry, that.industry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountName, phone, fax, website, employees, billingStreet, shippingStreet,
                type, industry);
    }

    @Override
    public String toString() {
        return "AccountForm{" +
                "accountName='" + accountName + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", website='" + website + '\'' +
                ", employees='" + employees + '\'' +
                ", billingStreet='" + billingStreet + '\'' +
                ", shippingStreet='" + shippingStreet + '\'' +
                ", type='" + type + '\'' +
                ", industry='" + industry + '\'' +
                '}';
    }
}
