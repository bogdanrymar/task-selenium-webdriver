import model.EmailPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import utils.WebDriverManager;

public abstract class EstimateBaseTest {
    @Test
    public void test() {
        try {
            flow();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void flow() {
        //Get temporary email
        EmailPage emailPage = new EmailPage();
        String mailTab = WebDriverManager.getCurrentHandle();
        String email = emailPage.open().getTempEmail();

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

    protected abstract String calculateEstimateAndSendTo(String email);

    @AfterClass
    public void finish() {
        WebDriverManager.quit();
    }
}
