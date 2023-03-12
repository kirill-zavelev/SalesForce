package com.salesforce.wrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormattedTextWithLink {

    private static final String TEXT_LOCATOR = "//span[text()='%s']//ancestor::records-record-layout-item//div//a";

    private WebDriver driver;
    private String label;

    public FormattedTextWithLink(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public String getText() {
        return driver.findElement(By.xpath(String.format(TEXT_LOCATOR, label))).getText();
    }
}
