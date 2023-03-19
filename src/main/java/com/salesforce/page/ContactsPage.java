package com.salesforce.page;

import com.salesforce.util.PropertiesLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Properties;

public class ContactsPage extends BasePage {

    private static final String CONTACTS_PAGE_PATH = "/lightning/o/Contact/list?filterName=Recent";
    private static final By CONTACTS_TITLE_LOCATOR = By.xpath("//span[text()='Contacts' "
            + "and @class='slds-var-p-right_x-small']");

    public ContactsPage(WebDriver driver) {
        super(driver);
    }

    public ContactsPage open() {
        Properties properties = PropertiesLoader.loadProperties();
        driver.get(properties.getProperty("base.url") + CONTACTS_PAGE_PATH);
        waitForPageLoaded();
        return this;
    }

    public ContactsPage waitForPageOpening() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACTS_TITLE_LOCATOR));
        return this;
    }

    public NewContactModalPage createNewContact() {
        driver.findElement(NEW_BTN_LOCATOR).click();
        return new NewContactModalPage(driver);
    }

    public List<String> getContactsNames() {
        return getAllNames();
    }

    public ContactsPage deleteContact(String contactName) {
        deleteEntity(contactName);
        return this;
    }

    public String getContactDeletedMessage() {
        return getMessageAfterActionsWithEntity();
    }
}
