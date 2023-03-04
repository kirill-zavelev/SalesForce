package com.salesforce.page;

import com.salesforce.util.PropertiesLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class AccountsPage extends BasePage {

    private static final String ACCOUNTS_PAGE_PATH = "/lightning/o/Account/list?filterName=Recent";

    private By ACCOUNTS_TITLE_LOCATOR = By.xpath("//span[text()='Accounts' and @class='slds-var-p-right_x-small']");

    public AccountsPage(WebDriver driver) {
        super(driver);
    }

    public AccountsPage open() {
        Properties properties = PropertiesLoader.loadProperties();
        driver.get(properties.getProperty("base.url") + ACCOUNTS_PAGE_PATH);
        waitForPageLoaded();
        return this;
    }

    public AccountsPage waitForPageOpening() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ACCOUNTS_TITLE_LOCATOR));
        return this;
    }

    public NewAccountModalPage createNewAccount() {
        driver.findElement(NEW_BTN_LOCATOR).click();
        return new NewAccountModalPage(driver);
    }

    public String getAccountCreatedMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-aura-class='forceActionsText']"))).getText();
    }

    public String getAccountDeletedMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-aura-class='forceActionsText']"))).getText();
    }

    //a[text()='Johnathan Rath']//ancestor::tr//span[@class='disabledState']//span[@class='uiOutputPhone']
    public List<String> getAccountNames() {
        List<WebElement> accountNames = driver.findElements(By.xpath("//a[@data-refid='recordId']"));
        return accountNames.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getAccountPhone(String accountName) {
        return driver.findElement(By.xpath(String.format("//a[text()='%s']//ancestor::tr//span[@class='disabledState']//span[@class='uiOutputPhone']", accountName))).getText();
    }

    public AccountsPage deleteAccount(String accountName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[text()='%s']//ancestor::tr//a[@role='button']", accountName)))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='uiMenuItem']//a[@title='Delete']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Delete']"))).click();
        return this;
    }
}
