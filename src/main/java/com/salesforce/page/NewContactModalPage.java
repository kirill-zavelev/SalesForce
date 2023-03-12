package com.salesforce.page;

import com.salesforce.dto.ContactForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewContactModalPage extends BasePage {

    private static final By SAVE_CONTACT = By.xpath("//button[@name='SaveEdit']");

    public NewContactModalPage(WebDriver driver) {
        super(driver);
    }

    public NewContactModalPage fillInContactInformation(ContactForm contactForm) {
        new ContactForm.Builder(driver)
                .fillFirstName(contactForm.getFirstName())
                .fillMobile(contactForm.getMobile())
                .fillEmail(contactForm.getEmail())
                .fillPhone(contactForm.getPhone())
                .fillTitle(contactForm.getTitle())
                .fillMailingStreet(contactForm.getMailingStreet())
                .fillFax(contactForm.getFax())
                .fillDepartment(contactForm.getDepartment())
                .fillOtherPhone(contactForm.getOtherPhone())
                .selectLeadSource(contactForm.getLeadSource())
                .fillAccountName(contactForm.getAccountName())
                .build();
        driver.findElement(By.xpath(String.format("//strong[text()='%s']//ancestor::span[@class='slds-media__body']",
                contactForm.getAccountName()))).click();
        return this;
    }

    public ContactDetailsPage saveContact() {
        driver.findElement(SAVE_CONTACT).click();
        return new ContactDetailsPage(driver);
    }
}
