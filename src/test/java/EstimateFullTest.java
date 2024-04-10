import model.SearchResultsPage;
import model.PricingCalculatorPage;

public class EstimateFullTest extends EstimateBaseTest {
    @Override
    protected String calculateEstimateAndSendTo(String email) {
        SearchResultsPage homePage = new SearchResultsPage();

        SearchResultsPage searchResultsPage = homePage
                .open();

        PricingCalculatorPage pricingCalculatorPage = searchResultsPage
                .clickOnSearchItem("//*[@track-metadata-eventdetail='cloud.google.com/products/calculator-legacy']");

        return pricingCalculatorPage
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
    }
}


