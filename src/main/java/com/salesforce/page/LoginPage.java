package com.salesforce.page;

import com.salesforce.page.BasePage;
import com.salesforce.util.PropertiesLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class LoginPage extends BasePage {

    private By username = By.id("username");
    private By password = By.id("password");
    private By loginButton = By.id("Login");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        Properties properties = PropertiesLoader.loadProperties();
        driver.get(properties.getProperty("base.url"));
        waitForPageLoaded();
        return this;
    }

    public HomePage login() {
        Properties properties = PropertiesLoader.loadProperties();
        driver.findElement(username).sendKeys(properties.getProperty("username"));
        driver.findElement(password).sendKeys(properties.getProperty("password"));
        driver.findElement(loginButton).click();
        return new HomePage(driver);
    }
}
