package challenge7;

import base.BaseTests;
import org.testng.annotations.*;

public class challenge7 extends BaseTests {
    @AfterSuite
    public void stopSuite() throws Exception {
        System.out.println("All done!!!");
    }

    @Test()
    public void verifyMakeModelLinksWork() throws Exception {
        homePage.goToListOfMakesModels(homePage.getListOfMakesModels());
    }
}