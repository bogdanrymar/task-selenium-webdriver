import model.EmailPage;
import model.HomePage;
import model.PricingCalculatorPage;
import model.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import utils.WebDriverManager;

public class EstimateFullTest {
    @Test
    public void test() {
        try {
            flow();
        } catch (Throwable e) {
            System.out.println("creating screenshots");
            //ScreenShot.takeScreenShot(formDriver.getWebDriver());
            //ScreenShot.takeScreenShot(mailDriver.getWebDriver());
            throw new RuntimeException(e);
        }
    }


    public void flow() {
        //Get temporary email
        EmailPage emailPage = new EmailPage();
        String mailTab = WebDriverManager.getCurrentHandle();

        String email = emailPage
                .open()
                .getTempEmail();

        //Switch to a new tab
        WebDriverManager.newTab();

        //Fill the calculator form and send an email
        String totalSum = calculateEstimateAndSendTo(email);

        //Switch back to the mail tab
        WebDriverManager.switchTab(mailTab);

        //Ensure emailed calculated sum is the same
        String emailSum = emailPage.getTotalSumMailed();
        Assert.assertEquals(totalSum, emailSum);
    }

    public static String calculateEstimateAndSendTo(String email) {
        HomePage homePage = new HomePage();

        SearchResultsPage searchResultsPage = homePage
                .open()
                .search("Google Cloud Platform Pricing Calculator");

        PricingCalculatorPage pricingCalculatorPage = searchResultsPage
                .clickOnSearchItem("//*[@track-metadata-eventdetail='cloud.google.com/products/calculator-legacy']");

        pricingCalculatorPage
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
                .sendToEmail(email);

        return pricingCalculatorPage.getTotalSum();
    }


    @AfterTest
    public void finish() {
        WebDriverManager.quit();
    }
}