package com.salesforce.page;

import com.salesforce.dto.ContactForm;
import com.salesforce.wrapper.FormattedText;
import com.salesforce.wrapper.FormattedTextWithLink;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ContactDetailsPage extends BasePage {

    private static final By ACCOUNT_NAME = By.xpath("//span[text()='Account Name']" +
            "//ancestor::records-record-layout-item//div//a//span");
    private static final By CONTACT_NAME = By.xpath("//span[text()='Name']" +
            "//ancestor::records-record-layout-item//div//lightning-formatted-name");
    private static final By CONTACT_TITLE_LOCATOR = By.xpath("//div[@class='slds-media__body']" +
            "//div[text()='Contact']");

    public ContactDetailsPage(WebDriver driver) {
        super(driver);
    }

    public ContactDetailsPage waitForPageOpening() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACT_TITLE_LOCATOR));
        return this;
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
        String name = driver.findElement(CONTACT_NAME).getText();
        return ContactForm.builder()
                .firstName(name)
                .mobile(getContactInformationWithLink("Mobile"))
                .email(getContactInformationWithLink("Email"))
                .phone(getContactInformationWithLink("Phone"))
                .title(getContactInformation("Title"))
                .mailingStreet(getContactInformationWithLink("Mailing Address"))
                .fax(getContactInformationWithLink("Fax"))
                .department(getContactInformation("Department"))
                .otherPhone(getContactInformationWithLink("Other Phone"))
                .leadSource(getContactInformation("Lead Source"))
                .accountName(driver.findElement(ACCOUNT_NAME).getText())
                .build();
    }
}
