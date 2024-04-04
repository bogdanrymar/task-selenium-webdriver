package model;

import org.openqa.selenium.WebDriver;
import utils.Util;
import utils.WebDriverManager;

public abstract class AbstractPage {
    final WebDriver driver;

    public AbstractPage() {
        this.driver = WebDriverManager.get();
    }

    // TODO: 04.04.2024 Remove delay
    static void delay(int secs) {
        Util.delay(secs);
    }
}