package pages;

import com.sun.xml.internal.ws.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By searchField = By.id("input-search");
    private By searchButton = By.cssSelector("[ng-click=\"search()\"]");
    private By tableContainer = By.id("serverSideDataTable");
    private By lotSearchModel = By.cssSelector("span[data-uname=\"lotsearchLotmodel\"]");
    private By popularModelList = By.cssSelector("li[ng-repeat*=\"popularSearch\"] > a");

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

    public void goToListOfMakesModels (List<WebElement> popularMakesModels) throws Exception {
        try
        {
            for (WebElement model : popularMakesModels) {
                model.getAttribute("href");
                driver.get(String.valueOf(model));

                wait.until(ExpectedConditions.visibilityOfElementLocated(tableContainer));
                WebElement modelText = driver.findElement(lotSearchModel);
                wait.until(ExpectedConditions.textToBePresentInElement(modelText, model.getText()));
            }
        }
        catch (Exception e) {
            System.out.println("Did not make it to " + StringUtils.capitalize(popularMakesModels) + "s. Check out the screenshot!");
            takeScreenshot(driver, "C:\\Users\\Vern Kofford\\Desktop\\STGcert\\src\\test\\java\\challenge\\challenge7.screenShots\\seleniumError"+System.currentTimeMillis()+".png");
        }
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