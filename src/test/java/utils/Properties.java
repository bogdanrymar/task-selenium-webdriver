package utils;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.Arrays;
import java.util.List;

public class Properties {
    public static List<String> getBrowsers(String browsers) {
        return Arrays.stream(browsers.split(",")).toList();
    }

    public static WebDriverManager getBrowserManager(String browser) {
        return switch (browser) {
            case "edge" -> WebDriverManager.edgedriver();
            case "firefox" -> WebDriverManager.chromiumdriver();

            default -> WebDriverManager.firefoxdriver();
        };
    }
}