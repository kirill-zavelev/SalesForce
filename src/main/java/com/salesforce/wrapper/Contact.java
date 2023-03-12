package com.salesforce.wrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Contact {

    private WebDriver driver;
    private String inputLabel;

    private static final String INPUT_LOCATOR = "//label[text()='%s']//ancestor::lightning-input//input";
    private static final String DROPDOWN_LOCATOR = "//label[text()='%s']//ancestor::lightning-combobox//button";
    private static final String ADDRESS_INPUT_LOCATOR = "//label[text()='%s']//ancestor::lightning-textarea//textarea";
    private static final By INPUT_LOCATOR_ACCOUNT_NAME = By.xpath("//label[text()='Account Name']" +
            "//ancestor::records-record-layout-lookup[@slot='inputField']");
    private static final By DROPDOWN_ITEMS = By.xpath("//lightning-base-combobox-item" +
            "//span[@class='slds-truncate']");

    public Contact(WebDriver driver, String inputLabel) {
        this.driver = driver;
        this.inputLabel = inputLabel;
    }

    public Contact(WebDriver driver) {
        this.driver = driver;
    }

    public void fillIn(String value, boolean isAddressInput) {
        if (!isAddressInput) {
            driver.findElement(By.xpath(String.format(INPUT_LOCATOR, inputLabel))).sendKeys(value);
        } else {
            driver.findElement(By.xpath(String.format(ADDRESS_INPUT_LOCATOR, inputLabel))).sendKeys(value);
        }
    }

    public void fillInAccountName(String value) {
        driver.findElement(INPUT_LOCATOR_ACCOUNT_NAME).sendKeys(value);
    }

    public void selectItemFromDropDown(String dropDownItem) {
        driver.findElement(By.xpath(String.format(DROPDOWN_LOCATOR, inputLabel))).click();
        List<WebElement> dropDownItems = driver.findElements(DROPDOWN_ITEMS);
        dropDownItems.stream()
                .filter(i -> i.getText().equals(dropDownItem))
                .findFirst()
                .ifPresent(WebElement::click);
    }
}
