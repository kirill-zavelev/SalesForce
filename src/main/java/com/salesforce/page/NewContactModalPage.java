package com.salesforce.page;

import com.salesforce.dto.ContactForm;
import com.salesforce.wrapper.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewContactModalPage extends BasePage {

    private static final By SAVE_CONTACT = By.xpath("//button[@name='SaveEdit']");

    public NewContactModalPage(WebDriver driver) {
        super(driver);
    }

    public NewContactModalPage fillInContactInformation(ContactForm contactForm) {
        new Contact(driver, "Last Name").fillIn(contactForm.getFirstName(), false);
        new Contact(driver, "Mobile").fillIn(contactForm.getMobile(), false);
        new Contact(driver, "Email").fillIn(contactForm.getEmail(), false);
        new Contact(driver, "Phone").fillIn(contactForm.getPhone(), false);
        new Contact(driver, "Title").fillIn(contactForm.getTitle(), false);
        new Contact(driver, "Mailing Street").fillIn(contactForm.getMailingStreet(), true);
        new Contact(driver, "Fax").fillIn(contactForm.getFax(), false);
        new Contact(driver, "Department").fillIn(contactForm.getDepartment(), false);
        new Contact(driver, "Other Phone").fillIn(contactForm.getOtherPhone(), false);
        new Contact(driver, "Lead Source").selectItemFromDropDown(contactForm.getLeadSource());
        new Contact(driver, "Account Name").fillInAccountName(contactForm.getAccountName());
        driver.findElement(By.xpath(String.format("//strong[text()='%s']//ancestor::span[@class='slds-media__body']",
                contactForm.getAccountName()))).click();
        return this;
    }

    public ContactDetailsPage saveContact() {
        driver.findElement(SAVE_CONTACT).click();
        return new ContactDetailsPage(driver);
    }
}
