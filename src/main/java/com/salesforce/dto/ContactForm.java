package com.salesforce.dto;

import com.salesforce.wrapper.Contact;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class ContactForm {

    private String firstName;
    private String mobile;
    private String accountName;
    private String email;
    private String phone;
    private String title;
    private String mailingStreet;
    private String fax;
    private String department;
    private String otherPhone;
    private String leadSource;

    public String getFirstName() {
        return firstName;
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

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMailingStreet() {
        return mailingStreet;
    }

    public void setMailingStreet(String mailingStreet) {
        this.mailingStreet = mailingStreet;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOtherPhone() {
        return otherPhone;
    }

    public void setOtherPhone(String otherPhone) {
        this.otherPhone = otherPhone;
    }

    public String getLeadSource() {
        return leadSource;
    }

    public void setLeadSource(String leadSource) {
        this.leadSource = leadSource;
    }

    public static class Builder {

        private final ContactForm contactForm;
        private final WebDriver driver;

        public Builder(WebDriver driver) {
            contactForm = new ContactForm();
            this.driver = driver;
        }

        public Builder fillFirstName(String firstName) {
            contactForm.firstName = firstName;
            new Contact(driver, "Last Name").fillIn(firstName, false);
            return this;
        }

        public Builder fillMobile(String mobile) {
            contactForm.mobile = mobile;
            new Contact(driver, "Mobile").fillIn(mobile, false);
            return this;
        }

        public Builder fillAccountName(String accountName) {
            contactForm.accountName = accountName;
            new Contact(driver).fillInAccountName(accountName);
            return this;
        }

        public Builder fillEmail(String email) {
            contactForm.email = email;
            new Contact(driver, "Email").fillIn(email, false);
            return this;
        }

        public Builder fillPhone(String phone) {
            contactForm.phone = phone;
            new Contact(driver, "Phone").fillIn(phone, false);
            return this;
        }

        public Builder fillTitle(String title) {
            contactForm.title = title;
            new Contact(driver, "Title").fillIn(title, false);
            return this;
        }

        public Builder fillMailingStreet(String mailingStreet) {
            contactForm.mailingStreet = mailingStreet;
            new Contact(driver, "Mailing Street").fillIn(mailingStreet, true);
            return this;
        }

        public Builder fillOtherPhone(String otherPhone) {
            contactForm.otherPhone = otherPhone;
            new Contact(driver, "Other Phone").fillIn(otherPhone, false);
            return this;
        }

        public Builder fillFax(String fax) {
            contactForm.fax = fax;
            new Contact(driver, "Fax").fillIn(fax, false);
            return this;
        }

        public Builder fillDepartment(String department) {
            contactForm.department = department;
            new Contact(driver, "Department").fillIn(department, false);
            return this;
        }

        public Builder selectLeadSource(String leadSource) {
            contactForm.leadSource = leadSource;
            new Contact(driver, "Lead Source").selectItemFromDropDown(leadSource);
            return this;
        }

        public ContactForm build() {
            return contactForm;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactForm)) return false;
        ContactForm that = (ContactForm) o;
        return firstName.equals(that.firstName) && mobile.equals(that.mobile) && accountName.equals(that.accountName)
                && email.equals(that.email) && phone.equals(that.phone) && title.equals(that.title)
                && mailingStreet.equals(that.mailingStreet) && leadSource.equals(that.leadSource) && fax.equals(that.fax)
                && department.equals(that.department) && otherPhone.equals(that.otherPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, mobile, accountName, email, phone, title, mailingStreet, fax,
                department, otherPhone, leadSource);
    }

    @Override
    public String toString() {
        return "ContactForm{" +
                "firstName='" + firstName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", accountName='" + accountName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", title='" + title + '\'' +
                ", mailingStreet='" + mailingStreet + '\'' +
                ", leadSource='" + leadSource + '\'' +
                ", fax='" + fax + '\'' +
                ", department='" + department + '\'' +
                ", otherPhone='" + otherPhone + '\'' +
                '}';
    }
}
