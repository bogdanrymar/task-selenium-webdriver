package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BrowserFactoryImpl implements BrowserFactory {

    private Supplier<WebDriverManager> chromeDriver = () -> {
        WebDriverManager driver = WebDriverManager.chromiumdriver();
        return driver;
    };

    private Supplier<WebDriverManager> firefoxDriver = () -> {
        WebDriverManager driver = WebDriverManager.firefoxdriver();
        return driver;
    };

    private Supplier<WebDriverManager> edgeDriver = () -> {
        WebDriverManager driver = WebDriverManager.edgedriver();
        return driver;
    };

    private Map<String, Supplier<WebDriverManager>> driverMap = new HashMap<>();

    public BrowserFactoryImpl() {
        driverMap.put("firefox", firefoxDriver);
        driverMap.put("chrome", chromeDriver);
        driverMap.put("edge", edgeDriver);
    }

    @Override
    public WebDriverManager createDriver(String browser) {
        return driverMap.getOrDefault(browser.toLowerCase(), chromeDriver).get();
    }

}
