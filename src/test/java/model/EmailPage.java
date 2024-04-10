package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmailPage extends AbstractPage {
    private static final String URL = "https://yopmail.com/uk/email-generator";


    public EmailPage open() {
        driver.get(URL);
        return this;
    }

    public String getTempEmail() {
        WebElement email = driver.findElement(By.xpath("//*[@id=\"geny\"]/span[1]"));
        return email.getText() + "@yopmail.com";
    }

    public String getTotalSumMailed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("//main//div[2]//button[2]")));
        checkButton.click();

        WebElement frame2 = driver.findElement(By.id("ifmail"));
        driver.switchTo().frame(frame2);


        WebElement checkSum = driver.findElement(By.xpath("//*[@id=\"mail\"]//tr[2]/td[2]/h3"));
        return checkSum.getText();
    }
}
