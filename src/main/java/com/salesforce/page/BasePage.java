package com.salesforce.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class BasePage {

    private static final String ENTITY_ACTIONS_DROP_DOWN = "//a[text()='%s']//ancestor::tr//a[@role='button']";
    private static final By LIST_OF_NAMES = By.xpath("//th//a[@data-refid='recordId']");
    private static final By DELETE_BTN = By.xpath("//li[@class='uiMenuItem']//a[@title='Delete']");
    private static final By CONFIRM_DELETION_BTN = By.xpath("//span[text()='Delete']");
    private static final By ENTITY_MESSAGE = By.xpath("//span[@data-aura-class='forceActionsText']");
    protected By NEW_BTN_LOCATOR = By.xpath("//div[@title='New']");

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void waitForPageLoaded() {
        new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString()
                        .equals("complete");
            }
        };
    }

    protected List<String> getAllNames() {
        List<WebElement> contactsNames = driver.findElements(LIST_OF_NAMES);
        return contactsNames.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    protected void deleteEntity(String entityName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(ENTITY_ACTIONS_DROP_DOWN, entityName))))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_BTN)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(CONFIRM_DELETION_BTN)).click();
    }

    public String getMessageAfterActionsWithEntity() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(ENTITY_MESSAGE)).getText();
    }
}
