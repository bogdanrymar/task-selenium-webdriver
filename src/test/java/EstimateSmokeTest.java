import model.EmailPage;
import model.PricingCalculatorPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import utils.WebDriverManager;

public class EstimateSmokeTest {
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
        PricingCalculatorPage pricingCalculatorPage = new PricingCalculatorPage();

        pricingCalculatorPage
                .open()
                .enterFormFrame()
                .switchToComputeEngineTab()
                .enterNumberOfInstances(4)
                .submitForm()
                .sendToEmail(email);

        return pricingCalculatorPage.getTotalSum();
    }


    @AfterTest
    public void finish() {
        WebDriverManager.quit();
    }
}