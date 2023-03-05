package com.salesforce.model;

import com.salesforce.wrapper.ContactFormInput;
import org.openqa.selenium.WebDriver;

public class ContactForm {

    private String firstName;
    private String lastName;
    private String mobile;
    private String accountName;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static class Builder {

        private ContactForm contactForm;
        private WebDriver driver;

        public Builder(WebDriver driver) {
            contactForm = new ContactForm();
            this.driver = driver;
        }

        public Builder fillFirstName(String firstName) {
            contactForm.firstName = firstName;
            new ContactFormInput(driver, "Last Name").fillIn(firstName);
            return this;
        }

        public Builder fillLastName(String lastName) {
            contactForm.lastName = lastName;
            new ContactFormInput(driver, "Last Name").fillIn(lastName);
            return this;
        }

        public Builder fillMobile(String mobile) {
            contactForm.mobile = mobile;
            new ContactFormInput(driver, "Mobile").fillIn(mobile);
            return this;
        }

        public Builder fillAccountName(String accountName) {
            contactForm.accountName = accountName;
            new ContactFormInput(driver).fillInAccountName(accountName);
            return this;
        }

        public Builder fillEmail(String email) {
            contactForm.email = email;
            new ContactFormInput(driver, "Email").fillIn(email);
            return this;
        }

        public ContactForm build() {
            return contactForm;
        }
    }
}
