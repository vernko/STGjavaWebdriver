package pages;

import com.sun.xml.internal.ws.util.StringUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchResultsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public By searchField = By.id("input-search");
    public By serverSideDataTable = By.id("serverSideDataTable");
    public By damageOrder = By.id("damage_desc");
    public By damageDescription = By.cssSelector("span[data-uname=\"lotsearchLotdamagedescription\"]");
    public By lotSearchModel = By.cssSelector("span[data-uname=\"lotsearchLotmodel\"]");
    public By entrySelector = By.name("serverSideDataTable_length");
    public By processedEntrySelector = By.id("serverSideDataTable_processing");
    public By modelFilter = By.cssSelector("[data-uname=\"ModelFilter\"]");
    public By modelFilterSearchContainer = By.id("collapseinside4");
    public By modelFilterSearchField = By.cssSelector("#collapseinside4 input[type=\"text\"]");
    public By modelFilterResultsTitle = By.cssSelector("abbr[title*=\"Skyline\"]");


    public SearchResultsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public List<String> getDamageList() throws Exception {
        List<String> damages = new ArrayList<String>();

        wait.until(ExpectedConditions.visibilityOfElementLocated(damageOrder));
        List<WebElement> damageList = driver.findElements(damageDescription);

        for (WebElement i : damageList) {
            damages.add(i.getText());
        }

        return damages;
    }

    public String getModelInList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(serverSideDataTable));
        return driver.findElement(serverSideDataTable).getText();
    }

    public List<String> getListOfModels() throws Exception {
        List<String> models = new ArrayList<String>();

        wait.until(ExpectedConditions.visibilityOfElementLocated(lotSearchModel));
        List<WebElement> modelList = driver.findElements(lotSearchModel);

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

    public void selectEntries() throws Exception {
        wait.until(ExpectedConditions.visibilityOfElementLocated(entrySelector));
        Select entries = new Select(driver.findElement(entrySelector));
        entries.selectByValue("100");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(processedEntrySelector));
    }

    public void openModelFilter() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(modelFilter));
        driver.findElement(modelFilter).click();
    }

    public void searchModelWithFilter(String modelSearch) throws Exception {
        try
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(modelFilterSearchContainer));
            driver.findElement(modelFilterSearchField).sendKeys(modelSearch);

            WebElement abbrTitle = driver.findElement(modelFilterResultsTitle);
            String model = abbrTitle.getText();

            if (model.contains(StringUtils.capitalize(modelSearch))) {
                System.out.println("A " + StringUtils.capitalize(modelSearch) + " exists on Copart");
            }
        }
        catch (Exception e) {
            System.out.println("There were no " + StringUtils.capitalize(modelSearch) + "s. Check out the screenshot!");
            takeScreenshot(driver, "C:\\Users\\Vern Kofford\\Desktop\\STGcert\\src\\test\\java\\challenge6\\challenge6.screenShots\\+seleniumError"+ System.currentTimeMillis() +".png");
        }
    }

    public void takeScreenshot(WebDriver webdriver, String fileWithPath) throws Exception {
        TakesScreenshot camera = ((TakesScreenshot)webdriver);
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(screenshot, DestFile);
    }

    public void clearSearch() {
        driver.findElement(searchField).clear();
        wait.until(ExpectedConditions.attributeContains(searchField, "placeholder", "Search"));
    }
}