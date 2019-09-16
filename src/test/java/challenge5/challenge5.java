package challenge5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class challenge5 {
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
    public void countTypesOfPorscheModels() throws Exception {
        goToCopart();
        searchMakes();
        selectEntries();
        printCountResults(getModelList());
    }

    public void goToCopart() throws Exception {
        driver.get("https://www.copart.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-search")));
    }

    public void searchMakes() throws Exception {
        driver.findElement(By.id("input-search")).sendKeys("porsche");
        driver.findElement(By.cssSelector("[ng-click=\"search()\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("serverSideDataTable")));
    }

    public void selectEntries() throws Exception {
        Select entries = new Select(driver.findElement(By.name("serverSideDataTable_length")));
        entries.selectByValue("100");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("serverSideDataTable_processing")));
    }

    public List<String> getModelList() throws Exception {
        List<String> models = new ArrayList<String>();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[data-uname=\"lotsearchLotmodel\"]")));
        List<WebElement> modelList = driver.findElements(By.cssSelector("span[data-uname=\"lotsearchLotmodel\"]"));

        for (WebElement i : modelList) {
            models.add(i.getText());
        }

        return models;
    }

    public void printCountResults(List<String> models) throws Exception {
        Map<String, Integer> map = new HashMap<>();

        for (String i : models) {
            Integer count = map.get(i);
            map.put(i, (count == null) ? 1 : count + 1);
        }

        for (Map.Entry<String, Integer> model : map.entrySet()) {
            System.out.println("There are " + model.getValue() + " " + model.getKey() + " models");
        }
    }
}