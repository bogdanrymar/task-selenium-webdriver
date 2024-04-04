import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import model.*;
import utils.Properties;
import utils.ScreenShot;

public class EstimateFullTest {
    @Test
    @Parameters("browser")
    public void test(String browsers) {
        for (String browser : Properties.getBrowsers(browsers)) {
            WebDriverManager mailDriver = Properties.getBrowserManager(browser);
            WebDriverManager formDriver = Properties.getBrowserManager(browser);

            try {
                flow(mailDriver, formDriver);
            } catch (Exception | Error e) {
                System.out.println("creating screenshots");
                //ScreenShot.takeScreenShot(formDriver.getWebDriver());
                //ScreenShot.takeScreenShot(mailDriver.getWebDriver());
                throw new RuntimeException(e);
            }
            formDriver.quit();
            mailDriver.quit();
        }

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
        HomePage homePage = new HomePage(driver);

        SearchResultsPage searchResultsPage = homePage
                .open()
                .search("Google Cloud Platform Pricing Calculator");

        PricingCalculatorPage pricingCalculatorPage = searchResultsPage
                .clickOnSearchItem("//*[@track-metadata-eventdetail='cloud.google.com/products/calculator-legacy']");
        String totalSum = pricingCalculatorPage
                .enterFormFrame()
                .switchToComputeEngineTab()
                .enterNumberOfInstances(4)
                .enterPurpose("")
                .chooseFreeOS()
                .chooseRegularProvisioningModel()
                .chooseGeneralMachineFamily()
                .chooseN1Series()
                .chooseCpu8Ram32MachineType()
                .chooseNVIDIATeslaV100GpuType()
                .choose1Gpu()
                .chooseSSD2x375()
                .chooseEurope3Location()
                .choose1YearUsage()
                .submitForm()
                .sendToEmail(email)
                .getTotalSum();

        return totalSum;
    }
}