package com.salesforce.wrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountFormInput {

    private WebDriver driver;
    private String inputLabel;

    private static final String INPUT_LOCATOR = "//span[text()='%s']//ancestor::div[@class='slds-form-element__control']//input";

    public AccountFormInput(WebDriver driver, String inputLabel) {
        this.driver = driver;
        this.inputLabel = inputLabel;
    }

    public void fillIn(String value) {
        driver.findElement(By.xpath(String.format(INPUT_LOCATOR, inputLabel))).sendKeys(value);
    }
}
