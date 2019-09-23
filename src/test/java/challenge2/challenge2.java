package challenge2;

import com.sun.glass.ui.Window;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class challenge2 {
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeSuite
    public void startSuite() throws Exception {
    }

    @AfterSuite
    public void stopSuite() throws Exception {
        System.out.println("Thanks for validating that Porsche exists in Exotics!!!");
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
    }

    @AfterMethod()
    public void afterMethod() {
    }

    @Test()
    public void goToCopart() throws Exception {
        driver.get("https://www.copart.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-search")));
    }

    @Test()
    public void searchExotics() throws Exception {
        driver.findElement(By.id("input-search")).sendKeys("exotics" + Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("zip-code")));
    }

    @Test()
    public void verifyPorscheInList() throws Exception {
        WebElement porsche = (driver.findElement(By.id("serverSideDataTable")));
        Assert.assertEquals(porsche.getText().contains("PORSCHE"), true);
    }
}