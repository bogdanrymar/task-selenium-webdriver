import model.PricingCalculatorPage;

public class EstimateSmokeTest extends EstimateBaseTest {
    @Override
    protected String calculateEstimateAndSendTo(String email) {
        PricingCalculatorPage pricingCalculatorPage = new PricingCalculatorPage();

        return pricingCalculatorPage
                .open()
                .enterFormFrame()
                .switchToComputeEngineTab()
                .enterNumberOfInstances(4)
                .submitForm()
                .sendToEmail(email)
                .getTotalSum();
    }
}