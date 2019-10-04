package challenge5;

import base.BaseTests;
import org.testng.annotations.*;
import pages.SearchResultsPage;

public class challenge5 extends BaseTests {
    @AfterSuite
    public void stopSuite() throws Exception {
        System.out.println("All done!!!");
    }


    @Test()
    public void countTypesOfPorscheModels() throws Exception {
        SearchResultsPage searchResultsPage = homePage.searchCopart("porsche");
        searchResultsPage.clearSearch();
        searchResultsPage.selectEntries();
        searchResultsPage.printModelCount(searchResultsPage.getListOfModels());
    }

    @Test()
    public void countTypesOfDamages() throws Exception {
        SearchResultsPage searchResultsPage = homePage.searchCopart("porsche");
        searchResultsPage.clearSearch();
        searchResultsPage.selectEntries();
        searchResultsPage.printDamageCount(searchResultsPage.getDamageList());
    }
}