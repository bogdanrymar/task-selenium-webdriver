package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmailPage extends AbstractPage {
    private static final String URL = "https://yopmail.com/uk/email-generator";

    public EmailPage(WebDriver driver) {
        super(driver);
    }


    public EmailPage open() {
        driver.get(URL);
        return this;
    }

    public String getTempEmail() {
        WebElement email = driver.findElement(By.xpath("//*[@id=\"geny\"]/span[1]"));
        return email.getText() + "@yopmail.com";
    }

    public String getTotalSumMailed() {
        delay(5);

        WebElement checkButton = driver.findElement(By.xpath("//main//div[2]//button[2]"));
        checkButton.click();

        delay(1);

        WebElement frame2 = driver.findElement(By.id("ifmail"));
        driver.switchTo().frame(frame2);

        delay(5);

        WebElement checkSum = driver.findElement(By.xpath("//*[@id=\"mail\"]//tr[2]/td[2]/h3"));
        return checkSum.getText();
    }
}
