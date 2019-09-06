package challenge3;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.List;

public class challenge3 {
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
    }

    @AfterMethod()
    public void afterMethod() {
    }

    @Test()
    public void testChallengeTwo() throws Exception {
        driver.get("https://www.copart.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-search")));

        List<WebElement> popularSearch = driver.findElements(By.cssSelector("li[ng-repeat*='popularSearch'] > a"));

        for (WebElement model : popularSearch) {
            System.out.println(model.getText() + " - " + model.getAttribute("href"));
        }
    }
}
