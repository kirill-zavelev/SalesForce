package com.salesforce.page;

import com.salesforce.util.PropertiesLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Properties;
import java.util.Random;

public class AccountsPage extends BasePage {

    private static final String ACCOUNTS_PAGE_PATH = "/lightning/o/Account/list?filterName=Recent";
    private static final String ACCOUNT_PHONE = "//a[text()='%s']"
            + "//ancestor::tr//span[@class='disabledState']//span[@class='uiOutputPhone']";

    private final By ACCOUNTS_TITLE_LOCATOR = By.xpath("//span[text()='Accounts' " +
            "and @class='slds-var-p-right_x-small']");

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
        return getMessageAfterActionsWithEntity();
    }

    public String getAccountDeletedMessage() {
        return getMessageAfterActionsWithEntity();
    }

    public List<String> getAccountsNames() {
        return getAllNames();
    }

    public String getRandomAccountName() {
        Random random = new Random();
        List<String> accountNames = getAccountsNames();
        return accountNames.get(random.nextInt(accountNames.size()));
    }

    public String getAccountPhone(String accountName) {
        return driver.findElement(By.xpath(String.format(ACCOUNT_PHONE, accountName))).getText();
    }

    public AccountsPage deleteAccount(String accountName) {
        deleteEntity(accountName);
        return this;
    }
}
