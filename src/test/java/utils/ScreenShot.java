package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ScreenShot {
    public static void takeScreenShot(WebDriver driver){
        try{
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            Random random = new Random();
            String screenshotTitle = "target/screenshots/screenshot_" +time+"_)"+random.nextInt(1000,9999)+".png";
            File targetFile = new File(screenshotTitle);
            FileUtils.copyFile(screenshotFile, targetFile);
        } catch (IOException e) {
            System.out.println("Exeption during screenshoting");
        }
    }
}
