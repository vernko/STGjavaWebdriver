package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class TakeScreenshots {
    private WebDriver driver;

    public TakeScreenshots(WebDriver driver){
        this.driver = driver;
    }
    public void takeScreenshot(String fileWithPath) throws Exception {
        TakesScreenshot camera = ((TakesScreenshot)driver);
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(screenshot, DestFile);
    }
}
