package com.salesforce.page;

import com.salesforce.dto.ContactForm;
import com.salesforce.wrapper.FormattedText;
import com.salesforce.wrapper.FormattedTextWithLink;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactDetailsPage extends BasePage {

    private static final By ACCOUNT_NAME = By.xpath("//span[text()='Account Name']" +
            "//ancestor::records-record-layout-item//div//a//span");
    private static final By CONTACT_NAME = By.xpath("//span[text()='Name']" +
            "//ancestor::records-record-layout-item//div//lightning-formatted-name");

    public ContactDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getContactCreatedMessage() {
        return getMessageAfterActionsWithEntity();
    }

    public String getContactInformation(String label) {
        return new FormattedText(driver, label).getText();
    }

    public String getContactInformationWithLink(String label) {
        return new FormattedTextWithLink(driver, label).getText();
    }

    public ContactForm getContact() {
        ContactForm contactForm = new ContactForm();
        String name = driver.findElement(CONTACT_NAME).getText();
        contactForm.setFirstName(name);
        contactForm.setMobile(getContactInformationWithLink("Mobile"));
        contactForm.setEmail(getContactInformationWithLink("Email"));
        contactForm.setPhone(getContactInformationWithLink("Phone"));
        contactForm.setTitle(getContactInformation("Title"));
        contactForm.setMailingStreet(getContactInformationWithLink("Mailing Address"));
        contactForm.setFax(getContactInformationWithLink("Fax"));
        contactForm.setDepartment(getContactInformation("Department"));
        contactForm.setOtherPhone(getContactInformationWithLink("Other Phone"));
        contactForm.setLeadSource(getContactInformation("Lead Source"));
        contactForm.setAccountName(driver.findElement(ACCOUNT_NAME).getText());
        return contactForm;
    }
}
