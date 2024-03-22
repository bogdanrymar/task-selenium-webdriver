import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import model.*;
import utils.Properties;
import utils.ScreenShot;

public class EstimateSmokeTest {
    @Test
    @Parameters("browser")
    public void test(String browser) {
            WebDriverManager mailDriver = Properties.getBrowserManager(browser);
            WebDriverManager formDriver = Properties.getBrowserManager(browser);

            try {
                flow(mailDriver, formDriver);
            }
            catch (Exception|Error e) {
                System.out.println("creating screenshots");
                //ScreenShot.takeScreenShot(formDriver.getWebDriver());
                //ScreenShot.takeScreenShot(mailDriver.getWebDriver());
                throw new RuntimeException(e);
            }
            formDriver.quit();
            mailDriver.quit();
    }


    public void flow(WebDriverManager mailDriver, WebDriverManager formDriver) {
        //Get temporary email
        EmailPage emailPage = new EmailPage(mailDriver.create());
        String email = emailPage
                .open()
                .getTempEmail();


        //Fill the calculator form and send an email
        String totalSum = calculateEstimateAndSendToEmail(formDriver.create(), email);

        //Ensure emailed calculated sum is the same
        String emailSum = emailPage.getTotalSumMailed();
        Assert.assertEquals(totalSum, emailSum);
    }

    public static String calculateEstimateAndSendToEmail(WebDriver driver, String email) {
        PricingCalculatorPage pricingCalculatorPage = new PricingCalculatorPage(driver);

        String totalSum = pricingCalculatorPage
                .open()
                .enterFormFrame()
                .switchToComputeEngineTab()
                .enterNumberOfInstances(4)
                .submitForm()
                .sendToEmail(email)
                .getTotalSum();

        return totalSum;
    }
}