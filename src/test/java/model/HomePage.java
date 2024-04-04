package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends AbstractPage {
    private static final String URL = "https://cloud.google.com/?hl=uk";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get(URL);
        return this;
    }

    public SearchResultsPage search(String query) {
        WebElement searchButton = driver.findElement(By.className("YSM5S"));

        searchButton.click();
        WebElement inputField = driver.findElement(By.id("i5"));
        inputField.sendKeys(query);
        WebElement submitSearchButton = driver.findElement(By.xpath("//*[@class='google-material-icons PETVs PETVs-OWXEXe-UbuQg']"));
        submitSearchButton.submit();
        delay(2);
        inputField.click();


        return new SearchResultsPage(driver);
    }
}