package com.salesforce.wrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Account {

    private WebDriver driver;
    private String inputLabel;

    private static final String INPUT_LOCATOR = "//span[text()='%s']" +
            "//ancestor::div[@class='slds-form-element__control']//input";
    private static final String ADDRESS_INPUT_LOCATOR = "//span[text()='%s']" +
            "//ancestor::div[@class='form-element__row']//textarea";
    private static final String DROPDOWN_LOCATOR = "//span[text()='%s']" +
            "//ancestor::div[@class='slds-form-element__control']//a";

    private static final By DROPDOWN_ITEMS = By.xpath("//li[@role='presentation']//a");

    public Account(WebDriver driver, String inputLabel) {
        this.driver = driver;
        this.inputLabel = inputLabel;
    }

    public void fillIn(String value, boolean isAddressInput) {
        if (inputLabel.equals("Account Name") && !isAddressInput) {
            driver.findElement(By.xpath(String.format(INPUT_LOCATOR, inputLabel))).sendKeys(value);
            driver.findElement(By.xpath(String.format(INPUT_LOCATOR, inputLabel))).sendKeys(Keys.TAB);
        } else if (!isAddressInput) {
            driver.findElement(By.xpath(String.format(INPUT_LOCATOR, inputLabel))).sendKeys(value);
        } else {
            driver.findElement(By.xpath(String.format(ADDRESS_INPUT_LOCATOR, inputLabel))).sendKeys(value);
        }
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
