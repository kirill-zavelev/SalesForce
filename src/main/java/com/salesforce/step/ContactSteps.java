package com.salesforce.step;

import com.salesforce.dto.ContactForm;
import com.salesforce.page.ContactDetailsPage;
import com.salesforce.page.ContactsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactSteps {

    WebDriver driver;
    WebDriverWait wait;

    public ContactSteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public ContactDetailsPage createNewContact(ContactForm contactForm) {
        new ContactsPage(driver).open()
                .createNewContact()
                .fillInContactInformation(contactForm)
                .saveContact()
                .waitForPageOpening();
        return new ContactDetailsPage(driver);
    }
}
