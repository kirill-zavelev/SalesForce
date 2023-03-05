package com.salesforce.wrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactFormInput {

    private WebDriver driver;
    private String inputLabel;

    private static final String INPUT_LOCATOR = "//label[text()='%s']//ancestor::lightning-input//input";
    private static final By INPUT_LOCATOR_ACCOUNT_NAME = By.xpath("//label[text()='Account Name']" +
            "//ancestor::records-record-layout-lookup[@slot='inputField']");

    public ContactFormInput(WebDriver driver, String inputLabel) {
        this.driver = driver;
        this.inputLabel = inputLabel;
    }

    public ContactFormInput(WebDriver driver) {
        this.driver = driver;
    }

    public void fillIn(String value) {
        driver.findElement(By.xpath(String.format(INPUT_LOCATOR, inputLabel))).sendKeys(value);
    }

    public void fillInAccountName(String value) {
        driver.findElement(INPUT_LOCATOR_ACCOUNT_NAME).sendKeys(value);
    }
}
