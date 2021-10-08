package ibmarketing.automatedqa.mutualFunds.validations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Utils {

   public static WebDriver getDriver () {
       System.setProperty("webdriver.gecko.driver", "C:/Users/daigg/Desktop/selenium/geckodriver-v0.29.0-win32/geckodriver.exe");
       WebDriver driver = new FirefoxDriver();
       return driver;
   }
    
   public static boolean killDriver () {
       System.out.println("Not yet implemeted...");
       return true;
   }
    
}
