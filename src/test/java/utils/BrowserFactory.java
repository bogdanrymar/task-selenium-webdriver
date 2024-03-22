package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
public interface BrowserFactory {
    WebDriverManager createDriver(String browser);
}