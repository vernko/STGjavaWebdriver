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
        printModelCount(getModelList());
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

    public void printModelCount(List<String> models) throws Exception {
        Map<String, Integer> map = new HashMap<>();

        for (String i : models) {
            Integer count = map.get(i);
            map.put(i, (count == null) ? 1 : count + 1);
        }

        for (Map.Entry<String, Integer> model : map.entrySet()) {
            System.out.println("There are " + model.getValue() + " " + model.getKey() + " models");
        }
    }

    @Test()
    public void countTypesOfDamages() throws Exception {
        goToCopart();
        searchMakes();
        selectEntries();
        printDamageCount(getDamageList());
    }

    public List<String> getDamageList() throws Exception {
        List<String> damages = new ArrayList<String>();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("damage_desc")));
        List<WebElement> damageList = driver.findElements(By.cssSelector("span[data-uname=\"lotsearchLotdamagedescription\"]"));

        for (WebElement i : damageList) {
            damages.add(i.getText());
        }

        return damages;
    }

    public void printDamageCount(List<String> damages) {
        Map<String, Integer> results = new HashMap<>();
        results.put("REAR END", 0);
        results.put("FRONT END", 0);
        results.put("MINOR DENT/SCRATCHES", 0);
        results.put("UNDERCARRIAGE", 0);
        results.put("MISC", 0);

        for (String damage : damages) {
            Integer count = results.get(damage);

            switch (damage) {
                case "REAR END":
                    if (count == null) {
                        results.put("REAR END", 1);
                    }
                    else {
                        results.put("REAR END", count + 1);
                    }
                    break;
                case "FRONT END":
                    if (count == null) {
                        results.put("FRONT END", 1);
                    }
                    else {
                        results.put("FRONT END", count + 1);
                    }
                    break;
                case "MINOR DENT/SCRATCHES":
                    if (count == null) {
                        results.put("MINOR DENT/SCRATCHES", 1);
                    }
                    else {
                        results.put("MINOR DENT/SCRATCHES", count + 1);
                    }
                    break;
                case "UNDERCARRIAGE":
                    if (count == null) {
                        results.put("UNDERCARRIAGE", 1);
                    }
                    else {
                        results.put("UNDERCARRIAGE", count + 1);
                    }
                    break;
                default:
                    if (results.get("MISC") == null) {
                        results.put("MISC", 1);
                    }
                    else {
                        results.put("MISC", results.get("MISC") + 1);
                    }
                    break;
            }
        }

        System.out.println(results);
    }
}