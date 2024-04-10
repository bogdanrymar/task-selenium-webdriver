package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchResultsPage extends AbstractPage {
    private static final String URL = "https://cloud.google.com/search?hl=uk&q=google%20cloud%20pricing%20calculator";

    public SearchResultsPage open() {
        driver.get(URL);
        return this;
    }

    public PricingCalculatorPage clickOnSearchItem(String linkText) {
        WebElement element = driver.findElement(By.xpath(linkText));
        element.click();

        return new PricingCalculatorPage();
    }
}