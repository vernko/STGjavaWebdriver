package challenge2;

import base.BaseTests;
import pages.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class challenge2 extends BaseTests {
    @AfterSuite
    public void stopSuite() throws Exception {
        System.out.println("Thanks for validating that Porsche exists in Exotics!!!");
    }

    @Test()
    public void verifySearchExistsOnResultsPage() {
        SearchResultsPage searchResultsPage = homePage.searchCopart("exotics");
        Assert.assertEquals(searchResultsPage.getModelInList().contains("PORSCHE"), true);
    }
}