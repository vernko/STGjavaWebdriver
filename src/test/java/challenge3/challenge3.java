package challenge3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Iterator;
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
        goToCopart();
    }

    @AfterMethod()
    public void afterMethod() {
    }

    @Test()
    public void printPopularItemsA() throws Exception {
        printMakesModels(getListOfMakesModels());
    }

//    @Test()
//    public void printPopularItemsB() throws Exception {
//        printCategories(getListOfCategories());
//    }

    public void goToCopart() throws Exception {
        driver.get("https://www.copart.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-search")));
    }

    public List<WebElement> getListOfMakesModels() throws Exception {
        List<WebElement> popularSearch = driver.findElements(By.cssSelector("li[ng-repeat*=\"popularSearch\"] > a"));
        return popularSearch;
    }

    public void printMakesModels(List<WebElement> popularSearch) throws Exception {
        for (WebElement model : popularSearch) {
            System.out.println(model.getText() + " - " + model.getAttribute("href"));
        }
    }

    public List<WebElement> getListOfCategories() throws Exception {
        /*List<WebElement> categoriesList = driver.findElements(By.cssSelector("ul[class*='tabs-left']"));*/
        List<WebElement> categoriesList = driver.findElements(By.xpath("//*[@id=\"tabTrending\"]/div[3]/div[1]/ul/li > a"));
        return categoriesList;
    }

    public void printCategories(List<WebElement> categoriesList) throws Exception {
        for (WebElement e : categoriesList) {
            System.out.println(e.getText() + " - " + e.getAttribute("href"));
        }

/*        Iterator<WebElement> categoriesListIterator = categoriesList.iterator();
        while (categoriesListIterator.hasNext()) {
            System.out.println(categoriesListIterator.next());
        }*/
    }
}
