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
        WebElement inputField = driver.findElement(By.xpath("/html/body/c-wiz[2]/header/div[2]/div[1]/div/div[2]/div[2]/div[1]/div/div/div[2]/form/div/div[1]/label/span[2]/input"));
        inputField.sendKeys(query);
        WebElement submitSearchButton = driver.findElement(By.xpath("/html/body/c-wiz[2]/header/div[2]/div[1]/div/div[2]/div[2]/div[1]/div/div/div[2]/form/div/div[1]/label/i[2]"));
        submitSearchButton.submit();
        delay(2);
        inputField.click();


        return new SearchResultsPage(driver);
    }
}