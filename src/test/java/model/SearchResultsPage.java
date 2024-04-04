package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchResultsPage extends AbstractPage {
    public PricingCalculatorPage clickOnSearchItem(String linkText) {
        WebElement element = driver.findElement(By.xpath(linkText));
        element.click();

        return new PricingCalculatorPage();
    }
}
