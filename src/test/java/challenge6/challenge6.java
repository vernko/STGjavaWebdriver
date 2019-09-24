package challenge6;

import challenge2.challenge2;
import com.sun.xml.internal.ws.util.StringUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;

public class challenge6 {
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeSuite
    public void startSuite() throws Exception {
    }

    @AfterSuite
    public void stopSuite() throws Exception {
        System.out.println("All done!!!");
    }

    @BeforeClass
    public void startClass() throws Exception {
        System.setProperty("webdriver.chrome.driver", "./Binaries/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public void stopClass() {
        driver.quit();
    }

    @BeforeMethod()
    public void beforeMethod() throws Exception {
        goToCopart();
    }

    @AfterMethod()
    public void afterMethod() {
    }

    @Test()
    public void verifyMakeExists() throws Exception {
        searchCopart("nissan");
        openModelFilter();
        searchModelWithFilter("skyline");
    }

    public void goToCopart() throws Exception {
        driver.get("https://www.copart.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-search")));
    }

    public void searchCopart(String searchItem) throws Exception {
        driver.findElement(By.id("input-search")).sendKeys(searchItem);
        driver.findElement(By.cssSelector("[ng-click=\"search()\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("serverSideDataTable")));
    }

    public void openModelFilter() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-uname=\"ModelFilter\"]")));
        driver.findElement(By.cssSelector("[data-uname=\"ModelFilter\"]")).click();
    }

    public void searchModelWithFilter(String modelSearch) throws Exception {
        try
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("collapseinside4")));
            driver.findElement(By.cssSelector("#collapseinside4 input[type=\"text\"]")).sendKeys(modelSearch);


            WebElement abbrTitle = driver.findElement(By.cssSelector("abbr[title*=\"Skyline\"]"));
            String model = abbrTitle.getText();
            Assert.assertTrue(model.contains(StringUtils.capitalize(modelSearch)), model);
            System.out.println("A " + StringUtils.capitalize(modelSearch) + " exists on Copart");
        }
        catch (Exception e) {
            System.out.println("There were no " + StringUtils.capitalize(modelSearch) + "s. Check out the screenshot!");
            takeScreenshot(driver, "C:\\Users\\Vern Kofford\\Desktop\\STGcert\\src\\test\\java\\challenge6\\screenShots\\seleniumError"+System.currentTimeMillis()+".png");
        }
    }

    public void takeScreenshot(WebDriver webdriver, String fileWithPath) throws Exception {
        TakesScreenshot screenshot = ((TakesScreenshot)webdriver);
        File SrcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);
    }
}
