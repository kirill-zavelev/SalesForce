package com.salesforce.wrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactFormInput {

    private WebDriver driver;
    private String inputLabel;

    private static final String INPUT_LOCATOR = "//label[text()='%s']//ancestor::lightning-input//input";

    public ContactFormInput(WebDriver driver, String inputLabel) {
        this.driver = driver;
        this.inputLabel = inputLabel;
    }

    public void fillIn(String value) {
        driver.findElement(By.xpath(String.format(INPUT_LOCATOR, inputLabel))).sendKeys(value);
    }
}
