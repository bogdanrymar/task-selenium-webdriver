package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {
    private static WebDriver instance;


    public static WebDriver get() {
        if (instance == null)
            instance = create();

        return instance;
    }

    public static WebDriver create() {
        String browser = PropertiesManager.getBrowser();

        return switch (browser) {
            case "edge" -> new EdgeDriver();
            case "firefox" -> new FirefoxDriver();
            default -> new ChromeDriver();
        };
    }

    public static void quit() {
        instance.quit();
        instance = null;
    }


    public static String getCurrentHandle() {
        return instance.getWindowHandle();
    }

    public static String newTab() {
        instance.switchTo().newWindow(WindowType.TAB);
        return instance.getWindowHandle();
    }

    public static void switchTab(String handle) {
        instance.switchTo().window(handle);
    }

    private static WebElement getBody() {
        return instance.findElement(By.cssSelector("body"));
    }


    public static void takeScreenShot() {
        // TODO: 04.04.2024 Fix copying screenshots
        /*try{
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            Random random = new Random();
            String screenshotTitle = "target/screenshots/screenshot_" +time+"_)"+random.nextInt(1000,9999)+".png";
            File targetFile = new File(screenshotTitle);

            FileUtils.copyFile(screenshotFile, targetFile);
        } catch (IOException e) {
            System.out.println("Exeption during screenshoting");
        }*/
    }
}