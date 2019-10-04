package challenge6;

import base.BaseTests;
import org.testng.annotations.*;
import pages.SearchResultsPage;

public class challenge6 extends BaseTests {
    @AfterSuite
    public void stopSuite() throws Exception {
        System.out.println("All done!!!");
    }

    @Test()
    public void verifyMakeExists() throws Exception {
        SearchResultsPage searchResultsPage = homePage.searchCopart("nissan");
        searchResultsPage.openModelFilter();
        searchResultsPage.searchModelWithFilter("skyline");
    }
}