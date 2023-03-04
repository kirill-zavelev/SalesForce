package com.salesforce.page;

import com.salesforce.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private By graph = By.xpath("//div[@class='ps-container']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage waitForPageOpening() {
        waitForPageLoaded();
        wait.until(ExpectedConditions.visibilityOfElementLocated(graph));
        return this;
    }
}
