package model;

import org.openqa.selenium.WebDriver;
import utils.Util;

public abstract class AbstractPage {
    final WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    static void delay(int secs) {
        Util.delay(secs);
    }
}