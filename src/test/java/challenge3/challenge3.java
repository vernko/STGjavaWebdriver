package challenge3;

import base.BaseTests;
import org.testng.annotations.*;

public class challenge3 extends BaseTests {
    @AfterSuite
    public void stopSuite() throws Exception {
        System.out.println("All done!!!");
    }

    @Test()
    public void printPopularItemsA() throws Exception {
        homePage.printMakesModels(homePage.getListOfMakesModels());
    }

//    @Test()
//    public void printPopularItemsB() throws Exception {
//        homePage.printCategories(homePage.getListOfCategories());
//    }
}
