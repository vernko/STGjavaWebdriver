package pages;

import com.sun.xml.internal.ws.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TakeScreenshots;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By searchField = By.id("input-search");
    private By searchButton = By.cssSelector("[ng-click=\"search()\"]");
    private By tableContainer = By.id("serverSideDataTable");
    private By lotSearchModel = By.cssSelector("span[data-uname=\"lotsearchLotmodel\"]");
    private By popularModelList = By.cssSelector("li[ng-repeat*=\"popularSearch\"] > a");
    private By popularCategoryList = By.xpath("//div[@id=\"tabTrending\"]/div/div/ul/li/a");

    public HomePage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;

    }

    public SearchResultsPage searchCopart(String searchItem) {
        driver.findElement(searchField).sendKeys(searchItem);
        driver.findElement(searchButton).click();

        return new SearchResultsPage(driver, wait);
    }

    public List<WebElement> getListOfMakesModels() {
        List<WebElement> popularSearch = driver.findElements(popularModelList);
        return popularSearch;
    }

    public Map<String, String> getListOfMakeModelsUrls(List<WebElement> popularSearch) {
        Map<String, String> modelUrls = new HashMap<>();
        for (WebElement modelUrl : popularSearch) {
            modelUrls.put(modelUrl.getText(), modelUrl.getAttribute("href"));

            for (String url : modelUrls.values()) {
                driver.get(url);
            }
        }

        return modelUrls;
    }


//    public void goToListOfMakesModels (Map<String, String> modelUrls) throws Exception {
//        try
//        {
//            for (String modelUrl : modelUrls.values()) {
//                driver.get(modelUrl);
//            }
//        }
//        catch (Exception e) {
//            System.out.println("Did not make it to "+ modelUrls.values() + "s. Check out the screenshot!");
//            TakeScreenshots takeScreenshots = new TakeScreenshots(driver);
//            takeScreenshots.takeScreenshot("C:\\Users\\Vern Kofford\\Desktop\\STGcert\\src\\test\\java\\challenge7\\challenge7.screenShots\\seleniumError"+System.currentTimeMillis()+".png");
//        }
//    }

    public void printMakesModels(List<WebElement> popularSearch) throws Exception {
        for (WebElement model : popularSearch) {
            System.out.println(model.getText() + " - " + model.getAttribute("href"));
        }
    }

    public List<WebElement> getListOfCategories() {
        List<WebElement> categoriesList = driver.findElements(popularCategoryList);
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