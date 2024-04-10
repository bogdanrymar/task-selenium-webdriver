package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class PricingCalculatorPage extends AbstractPage {
    private static final String URL = "https://cloud.google.com/products/calculator-legacy";
    private static final String REGEX_TOTAL_SUM = ".*: (.+) p.*";


    public PricingCalculatorPage open() {
        driver.get(URL);
        return this;
    }


    public PricingCalculatorPage enterFormFrame() {
        WebElement mainframe = driver.findElement(By.xpath("//*[@allow='clipboard-write https://cloud-dot-devsite-v2-prod.appspot.com']"));
        driver.switchTo().frame(mainframe);

        WebElement myFrame = driver.findElement(By.id("myFrame"));
        driver.switchTo().frame(myFrame);

        return this;
    }

    public PricingCalculatorPage switchToComputeEngineTab() {
        WebElement computeEngine = driver.findElement(By.id("tab-item-1"));
        computeEngine.click();

        return this;
    }

    public PricingCalculatorPage enterNumberOfInstances(int instances) {
        WebElement numInstances = driver.findElement(By.id("input_100"));
        numInstances.sendKeys(String.valueOf(instances));

        return this;
    }

    public PricingCalculatorPage enterPurpose(String description) {
        WebElement forWhatInstances = driver.findElement(By.id("input_101"));
        forWhatInstances.sendKeys(description);

        return this;
    }

    public PricingCalculatorPage chooseFreeOS() {
        WebElement operatingSystem = driver.findElement(By.id("select_value_label_92"));
        operatingSystem.click();


        WebElement freeOS = driver.findElement(By.id("select_option_102"));
        freeOS.click();

        return this;
    }

    public PricingCalculatorPage chooseRegularProvisioningModel() {
        WebElement provisioningModel = driver.findElement(By.id("select_value_label_93"));
        provisioningModel.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement regular = wait.until(ExpectedConditions.elementToBeClickable(By.id("select_value_label_93")));
        regular.click();

        return this;
    }

    public PricingCalculatorPage chooseGeneralMachineFamily() {
        WebElement machineFamily = driver.findElement(By.id("select_value_label_94"));
        machineFamily.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement general = wait.until(ExpectedConditions.elementToBeClickable(By.id("select_option_119")));
        general.click();

        return this;
    }

    public PricingCalculatorPage chooseN1Series() {
        WebElement series = driver.findElement(By.id("select_value_label_95"));
        series.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement n1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("select_option_224")));
        n1.click();

        return this;
    }

    public PricingCalculatorPage chooseCpu8Ram32MachineType() {
        WebElement machineType = driver.findElement(By.id("select_value_label_96"));
        machineType.click();

        WebElement cpu8Ram32 = driver.findElement(By.id("select_option_471"));
        cpu8Ram32.click();

        return this;
    }

    public PricingCalculatorPage chooseNVIDIATeslaV100GpuType() {
        WebElement checkGPU = driver.findElement(By.cssSelector("[aria-label='Add GPUs']"));
        checkGPU.click();
        WebElement gpuType = driver.findElement(By.id("select_506"));
        gpuType.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement NVIDIATeslaV100 = wait.until(ExpectedConditions.elementToBeClickable(By.id("select_option_511")));
        NVIDIATeslaV100.click();

        return this;
    }

    public PricingCalculatorPage choose1Gpu() {
        WebElement numOfGPUs = driver.findElement(By.id("select_508"));
        numOfGPUs.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement oneGPU = wait.until(ExpectedConditions.elementToBeClickable(By.id("select_option_516")));
        oneGPU.click();

        return this;
    }

    public PricingCalculatorPage chooseSSD2x375() {
        WebElement localSSD = driver.findElement(By.id("select_value_label_468"));
        localSSD.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement SSD2x375 = wait.until(ExpectedConditions.elementToBeClickable(By.id("select_option_495")));
        SSD2x375.click();

        return this;
    }

    public PricingCalculatorPage chooseEurope3Location() {
        WebElement location = driver.findElement(By.id("select_value_label_98"));
        location.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement europe3 = wait.until(ExpectedConditions.elementToBeClickable(By.id("select_option_268")));
        europe3.click();

        return this;
    }

    public PricingCalculatorPage choose1YearUsage() {
        WebElement usage = driver.findElement(By.id("select_value_label_99"));
        usage.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement oneYear = wait.until(ExpectedConditions.elementToBeClickable(By.id("select_option_138")));
        oneYear.click();

        return this;
    }

    public PricingCalculatorPage submitForm() {
        WebElement submitButton = driver.findElement(By.cssSelector("[ng-click=\"listingCtrl.addComputeServer(ComputeEngineForm);\"]"));
        submitButton.click();

        return this;
    }

    public PricingCalculatorPage sendToEmail(String email) {
        WebElement emailButton = driver.findElement(By.id("Email Estimate"));
        emailButton.click();

        WebElement emailString = driver.findElement(By.xpath("//input[@type='email']"));
        emailString.sendKeys(email);
        WebElement sendLetterButton = driver.findElement(By.xpath("/html/body//md-dialog/form/md-dialog-actions/button[2]"));
        sendLetterButton.click();

        return this;
    }

    public String getTotalSum() {
        WebElement totalSum = driver.findElement(By.xpath("//*[@id=\"resultBlock\"]/md-card/md-card-content/div/div/div/div[1]/h2/b"));
        String sum = totalSum.getText();

        Pattern pattern = Pattern.compile(REGEX_TOTAL_SUM);
        Matcher matcher = pattern.matcher(sum);
        return matcher.replaceAll("$1");
    }
}