package com.salesforce.page;

import com.salesforce.model.AccountForm;
import com.salesforce.model.ContactForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewContactModalPage extends BasePage {

    public NewContactModalPage(WebDriver driver) {
        super(driver);
    }

    public NewContactModalPage fillInContactInformation(ContactForm contactForm) {
        new ContactForm.Builder(driver)
                .fillFirstName(contactForm.getFirstName())
                .fillLastName(contactForm.getLastName())
                .fillMobile(contactForm.getMobile())
                .fillEmail(contactForm.getEmail())
                .fillAccountName(contactForm.getAccountName())
                .build();
        driver.findElement(By.xpath(String.format("//span[text()='%s']//ancestor::span[@class='slds-media__body']", contactForm.getAccountName()))).click();
        return this;
    }

    public ContactsPage saveContact() {
        driver.findElement(By.xpath(" //button[@name='SaveEdit']")).click();
        return new ContactsPage(driver);
    }
}
