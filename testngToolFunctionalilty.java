package ibmarketing.automatedqa.mutualFunds.validations;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
 
public class testngToolFunctionalilty extends BaseTest {
    WebDriver driver;
 
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\devuser6\\Desktop\\geckodriver-v0.24.0-win32\\geckodriver.exe");
 
    }
 
 
    @BeforeMethod
	public void setUpMethod() throws Exception {
        System.out.println("--------Starting Test--------");
        driver = Utils.getDriver();
 
    }
 
    @AfterMethod
	public void tearDownMethod() throws Exception {
        System.out.println("--------Test Finished--------");
        driver.quit();
    }
 
     
     
     
     
     
    public WebElement GetViewResultsButton() {
         
        return driver.findElement(By.xpath("/html/body/div[3]/section[2]/div/div/div/div/div/div/form/div[7]/div/button[1]"));
     
    }
     
public WebElement GetEditButton() {
         
        return driver.findElement(By.xpath("/html/body/div[3]/section[2]/div/div/div/div/div[1]/div/div[1]/div/div/button[3]"));
     
    }
 
 
public WebElement GetStartOverButton() {
     
    return driver.findElement(By.xpath("/html/body/div[3]/section[2]/div/div/div/div/div[1]/div/div[1]/div/div/button[2]"));
 
}
 
public WebElement GetCPCountry() {
     
    return driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div/div/div[2]/div/span[4]"));
 
}
 
public WebElement GetCPFamily() {
     
    return driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div/div/div[2]/div/span[6]"));
 
}
 
public WebElement GetCPCommission() {
     
    return driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div/div/div[2]/div/span[8]"));
 
}
 
public WebElement GetCPFundType() {
     
    return driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div/div/div[2]/div/span[10]"));
 
}
 
public WebElement GetCPEditButton() {
     
    return driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div/div/div[1]/div/div/button[3]"));
 
}
 
public WebElement GetCPViewResultsButton() {
     
    return driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div/div/div/form/div[7]/div/button[1]"));
 
}

public WebElement GetCountryResults() {
    
    return driver.findElement(By.xpath("/html/body/div[3]/section[2]/div/div/div/div/div[1]/div/div[2]/div/span[2]"));
 
}

public WebElement GetFundFamilyResults() {
    
    return driver.findElement(By.xpath("/html/body/div[3]/section[2]/div/div/div/div/div[1]/div/div[2]/div/span[4]"));
 
}
 
public static void LogoutCP(WebDriver driver) throws InterruptedException {
     
    driver.findElement(By.xpath("//*[@id=\"ib-bar-user-icon\"]")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/button")).click();
    Thread.sleep(3000);
    
}
 
     
    // ***** Assumes user is already on the MF search tool *****
    public void SubmitSearch(String searchTerm, String country, String family, String commissionType, String fundType) throws InterruptedException {
 
        //Enter the search Term
        driver.findElement(By.xpath("//*[@id=\"_f10\"]")).sendKeys(searchTerm);
 
        // Click on the country dropdown
        WebElement CountryDrop = driver.findElement(By.xpath("//*[@id=\"_ddi13\"]"));
        CountryDrop.click();
        //Search for the country
        WebElement CountryDropSearch = driver.findElement(By.xpath("//*[@id=\"_ddi13\"]"));
        CountryDropSearch.sendKeys(country);
        CountryDropSearch.sendKeys(Keys.RETURN);
 
        // Find and click on the fund type
        driver.findElement(By.xpath("//span[.='"+fundType+"']")).click();
 
        //Find and click on the Commission Type
        driver.findElement(By.xpath("//span[.='"+commissionType+"']")).click();
 
        // Click on the Fund family dropdown
        WebElement FundDrop = driver.findElement(By.xpath("//*[@id=\"_ddi16\"]"));
        FundDrop.click();
        //Search for the Fund family
        WebElement FundDropSearch = driver.findElement(By.xpath("//*[@id=\"_ddi16\"]"));
        FundDropSearch.sendKeys(family);
        FundDropSearch.sendKeys(Keys.RETURN);
 
        //Click on View Results
        WebElement ViewResults = driver.findElement(By.xpath("/html/body/div[3]/section[2]/div/div/div/div/div/div/form/div[7]/div/button[1]"));
        ViewResults.click();
 
    }
 
    //***** Assumes user is already on the results table page *****
    public void ClientPortalLogin(String user, String pass) throws InterruptedException {
 
        // Click on new column
        driver.findElement(By.xpath("/html/body/div[3]/section[2]/div/div/div/div/div[1]/div/div[3]/div[2]/div[2]/table/tbody/tr[1]/td[1]")).click();
        Thread.sleep(1000);
        // Click on Login button
        driver.findElement(By.xpath("/html/body/div[3]/section[2]/div/div/div/div/div[2]/div/div/div/p[1]/button")).click();
        //Verify Login Page has loaded
        Thread.sleep(3000);
        //  String LoginURL = driver.getCurrentUrl();
        //   System.out.println(LoginURL);
 
        //Login into Client Portal
        driver.findElement(By.xpath("//*[@id=\"user_name\"]")).sendKeys(user);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(pass);
        driver.findElement(By.xpath("//*[@id=\"submitForm\"]")).click();
 
        Thread.sleep(3000);
 
    }
 
 
 
    @Test
    public void TestDropDownContentsCountry() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        System.out.println("--------Opening Mutual Fund Marketplace--------");
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
 
        driver.get(homePage);
 
        // Click on the country dropdown
        WebElement CountryDrop = driver.findElement(By.xpath("//*[@id=\"_ddi13\"]"));
        CountryDrop.click();
        //Search for the country
        WebElement CountryDropSearch = driver.findElement(By.xpath("//*[@id=\"_ddi13\"]"));
        CountryDropSearch.sendKeys("Germany");
        CountryDropSearch.sendKeys(Keys.RETURN);
 
        //click the View Results Button
        WebElement ViewResults = GetViewResultsButton();
        ViewResults.click();
        Thread.sleep(3000);
 
        String value = GetCountryResults().getText();
 
        if (value.contains("Germany")){
            System.out.println("Country Dropdown matches " + value);
            test.log(LogStatus.PASS, "Country Dropdown matches " + value);
 
        }
 
        else{
            System.out.println("Country Dropdown doesn't match Germany: " + value);
            test.log(LogStatus.FAIL, "Country Dropdown doesn't match Germany: " + value);
 
        }
 
    }
 
    @Test
    public void TestDropDownContentsFundFamily() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        System.out.println("--------Opening Mutual Fund Marketplace--------");
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
 
        driver.get(homePage);
 
        // Click on the Fund family dropdown
        WebElement FundDrop = driver.findElement(By.xpath("//*[@id=\"_ddi16\"]"));
        FundDrop.click();
        //Search for the Fund family
        WebElement FundDropSearch = driver.findElement(By.xpath("//*[@id=\"_ddi16\"]"));
        FundDropSearch.sendKeys("Aberdeen Funds");
        FundDropSearch.sendKeys(Keys.RETURN);
 
        //click the View Results Button
                WebElement ViewResults = GetViewResultsButton();
                ViewResults.click();
                 
 
        Thread.sleep(3000);
 
        String value = GetFundFamilyResults().getText();
 
        if (value.contains("Aberdeen Funds")){
            System.out.println("Fund Family matches " + value);
            test.log(LogStatus.PASS, "Fund Family matches " + value);
 
        }
 
        else{
            System.out.println("Fund Family Dropdown doesn't matches " + value);
            test.log(LogStatus.FAIL, "Fund Family Dropdown doesn't matches " + value);
 
        }
 
    }
 
 
    @Test
    public void TestResetFilters() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        //Get MF Scanner
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
        driver.get(homePage);
 
        // Change the country selected
        // Click on the country dropdown
        WebElement CountryDrop = driver.findElement(By.xpath("//*[@id=\"_ddi13\"]"));
        CountryDrop.click();
        //Search for the country
        WebElement CountryDropSearch = driver.findElement(By.xpath("//*[@id=\"_ddi13\"]"));
        CountryDropSearch.sendKeys("Germany");
        CountryDropSearch.sendKeys(Keys.RETURN);
 
 
 
        // Click the Reset Filter Button
        driver.findElement(By.xpath("/html/body/div[3]/section[2]/div/div/div/div/div/div/form/div[7]/div/button[2]")).click();
        Thread.sleep(1000);
        // go to results page
        driver.findElement(By.xpath("/html/body/div[3]/section[2]/div/div/div/div/div/div/form/div[7]/div/button[1]")).click();
 
        Thread.sleep(3000);
 
        String value = GetCountryResults().getText();
 
 
        System.out.println(value);
 
        if(value.equals("United States")) {
            System.out.println("Reset Button Worked Properly");
            test.log(LogStatus.PASS, "Reset Button Worked Properly");
 
        }
 
        else{
            System.out.println("Reset Button Did not function");
            test.log(LogStatus.FAIL, "Reset Button Did not function");
        }
 
 
    }
 
    @Test
    public void TestResultLogin() throws InterruptedException  {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        //Get MF Scanner
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
        driver.get(homePage);
 
        SubmitSearch("gopax", "United States", "Aberdeen Funds", "No", "Stock" );
 
        Thread.sleep(3000);
 
        //Login to CP
        ClientPortalLogin("fancy0011", "tester12");
        Thread.sleep(6000);
 
         
 
        //verify the user is brought to the MF scanner
        String LoginURL = driver.getCurrentUrl();
        System.out.println(LoginURL);
 
        if(LoginURL.contains("interactivebrokers.com/portal/#/wlms/mf-scanner")) {
            System.out.println("Login Button Works correctly, user was brought to CP results");
            test.log(LogStatus.PASS, "Login Button Works correctly, user was brought to CP results");
            LogoutCP(driver);
        }
        else{
            System.out.println("Login Button Did not function");
            test.log(LogStatus.FAIL, "Login Button Did not function");
            LogoutCP(driver);
        }
    }
 
    @Test
    public void TestResultCreateAccount() throws InterruptedException  {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        //Get MF Scanner
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
        driver.get(homePage);
 
        //Click View Results
                WebElement ViewResults = GetViewResultsButton();
                ViewResults.click();
                Thread.sleep(1000);
 
        Thread.sleep(3000);
        // Click on new column
        driver.findElement(By.xpath("/html/body/div[3]/section[2]/div/div/div/div/div[1]/div/div[3]/div[2]/div[2]/table/tbody/tr[1]/td[1]")).click();
        Thread.sleep(1000);
        // Click on Create Account button
        driver.findElement(By.xpath("/html/body/div[3]/section[2]/div/div/div/div/div[2]/div/div/div/p[2]/a")).click();
        //Verify Create Account Page has loaded
        Thread.sleep(3000);
        String LoginURL = driver.getCurrentUrl();
        System.out.println(LoginURL);
 
        if(LoginURL.contains("https://www.interactivebrokers.com/en/index.php?f=4695")) {
            System.out.println("Open Account Button Works correctly");
            test.log(LogStatus.PASS, "Open Account Button Works correctly");
        }
        else{
            System.out.println("Open Account Button Did not function");
            test.log(LogStatus.FAIL, "Open Account Button Did not function");
        }
    }
 
    @Test
    public void TestStartOverButton() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        //Get MF Scanner
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
        driver.get(homePage);
 
        //Submit Search
        SubmitSearch("", "Germany", "All", "All", "All" );
        Thread.sleep(3000);
 
        //Click the Start Over Button
        WebElement StartOver = GetStartOverButton();
        StartOver.click();
        Thread.sleep(1000);
 
        //Click View Results
        WebElement ViewResults = GetViewResultsButton();
        ViewResults.click();
        Thread.sleep(1000);
 
        //Verify that default search filters were used
        String DropdownValue = GetCountryResults().getText();
        System.out.println(DropdownValue);
 
        if(DropdownValue.equals("United States")) {
            System.out.println("Start Over Button Worked Properly");
            test.log(LogStatus.PASS, "Start Over Button Worked Properly");
 
        }
 
        else{
            System.out.println("Start Over Button Did not function");
            test.log(LogStatus.FAIL, "Start Over Button Did not function");
        }
 
    }
 
    @Test
    public void TestEditButton() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        //Get MF Scanner
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
        driver.get(homePage);
 
        //Submit Search
        SubmitSearch("", "Germany", "All", "All", "All" );
        Thread.sleep(3000);
 
        //Click the Edit Button
        //driver.findElement(By.xpath("/html/body/div[3]/section[2]/div/div/div/div/div[1]/div/div[1]/div/div/button[3]")).click();
        WebElement EditButton = GetEditButton();
        EditButton.click();
        Thread.sleep(1000);
 
 
        //Click View Results again
        WebElement ViewResults = GetViewResultsButton();
        ViewResults.click();
        Thread.sleep(1000);
 
        //Verify that Germany is still searched
        String DropdownValue = GetCountryResults().getText();
        System.out.println(DropdownValue);
 
        if(DropdownValue.equals("Germany")) {
            System.out.println("Edit Button Worked Properly");
            test.log(LogStatus.PASS, "Edit Button Worked Properly");
        }
 
        else{
            System.out.println("Edit Button Did not function");
            test.log(LogStatus.FAIL, "Edit Button Did not function");
        }
 
    }
 
    @Test
    public void TestSymbolSearch() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        //Get MF Scanner
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
        driver.get(homePage);
 
        //Submit Search
        SubmitSearch("AGAZX", "United States", "All", "All", "All" );
        Thread.sleep(3000);
 
        // Verify that the search worked
        boolean SymbolValue = driver.getPageSource().contains("AGAZX");
        System.out.println(SymbolValue);
 
        if(SymbolValue == true) {
            System.out.println("Symbol Search Worked Properly");
            test.log(LogStatus.PASS, "Symbol Search Worked Properly");
        }
 
        else{
            System.out.println("Symbol Search Did not function");
            test.log(LogStatus.FAIL, "Symbol Search Did not function");
        }
 
    }
 
    @Test
    public void TestCUSIPSearch() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        //Get MF Scanner
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
        driver.get(homePage);
 
        //Submit Search
        SubmitSearch("003020351", "United States", "All", "All", "All" );
        Thread.sleep(3000);
 
        // Verify that the search worked
        boolean SymbolValue = driver.getPageSource().contains("MLSCX");
        System.out.println(SymbolValue);
 
        if(SymbolValue == true) {
            System.out.println("CUSIP Search Worked Properly");
            test.log(LogStatus.PASS, "CUSIP Search Worked Properly");
        }
 
        else{
            System.out.println("CUSIP Search Did not function");
            test.log(LogStatus.FAIL, "CUSIP Search Did not function");
        }
 
    }
 
    @Test
    public void TestISINSearch() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        //Get MF Scanner
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
        driver.get(homePage);
 
        //Submit Search
        SubmitSearch("US0030203287", "United States", "All", "All", "All" );
        Thread.sleep(3000);
 
        // Verify that the search worked
        boolean SymbolValue = driver.getPageSource().contains("AELSX");
        System.out.println(SymbolValue);
 
        if(SymbolValue == true) {
            System.out.println("ISIN Search Worked Properly");
            test.log(LogStatus.PASS, "ISIN Search Worked Properly");
        }
 
        else{
            System.out.println("ISIN Search Did not function");
            test.log(LogStatus.FAIL, "ISIN Search Did not function");
        }
 
    }
 
    @Test
    public void TestSymbolFilterSearch() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        //Get MF Scanner
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
        driver.get(homePage);
 
        //Submit Search
        SubmitSearch("AGAZX", "United States", "361 Capital Funds", "All", "All" );
        Thread.sleep(3000);
 
        // Verify that the search worked
        boolean SymbolValue = driver.getPageSource().contains("AGAZX");
        System.out.println(SymbolValue);
 
        if(SymbolValue == true) {
            System.out.println("Symbol Search Worked Properly");
            test.log(LogStatus.PASS, "Symbol Search Worked Properly");
        }
 
        else{
            System.out.println("Symbol Search Did not function");
            test.log(LogStatus.FAIL, "Symbol Search Did not function");
        }
 
    }
 
    @Test
    public void TestCUSIPFilterSearch() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        //Get MF Scanner
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
        driver.get(homePage);
 
        //Submit Search
        SubmitSearch("003020351", "United States", "aberdeen Funds", "All", "All" );
        Thread.sleep(3000);
 
 
        // Verify that the search worked
        boolean SymbolValue = driver.getPageSource().contains("MLSCX");
        System.out.println(SymbolValue);
 
        if(SymbolValue == true) {
            System.out.println("CUSIP Search Worked Properly");
            test.log(LogStatus.PASS, "CUSIP Search Worked Properly");
        }
 
        else{
            System.out.println("CUSIP Search Did not function");
            test.log(LogStatus.FAIL, "CUSIP Search Did not function");
        }
 
    }
 
    @Test
    public void TestISINFilterSearch() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        //Get MF Scanner
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
        driver.get(homePage);
 
        //Submit Search
        SubmitSearch("US0030203287", "United States", "aberdeen Funds", "All", "All" );
        Thread.sleep(3000);
 
        // Verify that the search worked
        boolean SymbolValue = driver.getPageSource().contains("AELSX");
        System.out.println(SymbolValue);
 
        if(SymbolValue == true) {
            System.out.println("ISIN Search Worked Properly");
            test.log(LogStatus.PASS, "ISIN Search Worked Properly");
        }
 
        else{
            System.out.println("ISIN Search Did not function");
            test.log(LogStatus.FAIL, "ISIN Search Did not function");
        }
 
    }
 
    @Test
    public void TestSymbolFilterNegativeSearch() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        //Get MF Scanner
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
        driver.get(homePage);
        //Submit Search
        SubmitSearch("AGAZX", "United States", "aaamco Funds", "All", "All" );
        Thread.sleep(3000);
 
        // Verify that the search returned no results
        String SymbolValue = driver.findElement(By.xpath("//*[@id=\"_alert-msg\"]/p")).getText();
        System.out.println(SymbolValue);
 
        if(SymbolValue.equals("There were 0 results")) {
            System.out.println("Symbol Search Worked Properly");
            test.log(LogStatus.PASS, "Symbol Search Worked Properly");
        }
 
        else{
            System.out.println("Symbol Search Did not function");
            test.log(LogStatus.FAIL, "Symbol Search Did not function");
        }
 
    }
 
    @Test
    public void TestCUSIPFilterNegativeSearch() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        //Get MF Scanner
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
        driver.get(homePage);
        //Submit Search
        SubmitSearch("003020351", "United States", "aaamco Funds", "All", "All" );
        Thread.sleep(3000);
 
        // Verify that the search returned no results
        String SymbolValue = driver.findElement(By.xpath("//*[@id=\"_alert-msg\"]/p")).getText();
        System.out.println(SymbolValue);
 
        if(SymbolValue.equals("There were 0 results")) {
            System.out.println("CUSIP Search Worked Properly");
            test.log(LogStatus.PASS, "CUSIP Search Worked Properly");
        }
 
        else{
            System.out.println("CUSIP Search Did not function");
            test.log(LogStatus.FAIL, "CUSIP Search Did not function");
        }
 
    }
 
    @Test
    public void TestISINFilterNegativeSearch() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
 
        //Fetch the MF Scanner page
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
        driver.get(homePage);
 
        SubmitSearch("US0030203287", "United States", "aaamco Funds", "All", "All" );
 
        Thread.sleep(3000);
 
        // Verify that the search returned no results
        String SymbolValue = driver.findElement(By.xpath("//*[@id=\"_alert-msg\"]/p")).getText();
        System.out.println(SymbolValue);
 
        if(SymbolValue.equals("There were 0 results")) {
            System.out.println("ISIN Search Worked Properly");
            test.log(LogStatus.PASS, "ISIN Search Worked Properly");
        }
 
        else{
            System.out.println("ISIN Search Did not function");
            test.log(LogStatus.FAIL, "ISIN Search Did not function");
        }
 
    }
 
    @Test
    public void TestAllFilters() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        //Fetch the MF Scanner page
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
        driver.get(homePage);
 
        SubmitSearch("gopax", "United States", "Aberdeen Funds", "No", "Stock" );
 
        Thread.sleep(3000);
        // Verify that the search worked
        boolean SymbolValue = driver.getPageSource().contains("GOPAX");
        System.out.println(SymbolValue);
 
 
        if(SymbolValue == true) {
            System.out.println("All Filters Search Worked Properly");
            test.log(LogStatus.PASS, "All Filters Search Worked Properly");
        }
 
        else{
            System.out.println("All Filters Search Did not function");
            test.log(LogStatus.FAIL, "All Filters Search Did not function");
        }
 
    }
 
    @Test
    public void CPPersistResultsIndiv() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        //Fetch the MF Scanner page
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
        driver.get(homePage);
 
        SubmitSearch("gopax", "United States", "Aberdeen Funds", "No", "Stock" );
 
        Thread.sleep(3000);
 
        //Login to CP
        ClientPortalLogin("steves456", "knicks12");
        Thread.sleep(6000);
         
        String CPCountry = GetCPCountry().getText();
        String CPFamily = GetCPFamily().getText();
        String CPCommission = GetCPCommission().getText();
        String CPFundType = GetCPFundType().getText();
        boolean SymbolValue = driver.getPageSource().contains("GOPAX");
        System.out.println(SymbolValue);
        System.out.println(CPCountry);
        System.out.println(CPFamily);
        System.out.println(CPCommission);
        System.out.println(CPFundType);
 
        if(!CPCountry.contains("United States")) {
            System.out.println("Country Filter Did not carry over");
            test.log(LogStatus.FAIL, "Country Filter Did not carry over");
            LogoutCP(driver);
 
        }
 
        else if(!CPFamily.contains("Aberdeen Funds")){
            System.out.println("Family Filter Did not carry over");
            test.log(LogStatus.FAIL, "Family Filter Did not carry over");
            LogoutCP(driver);
        }
         
        else if(!CPCommission.contains("No")){
            System.out.println("Commission Filter Did not carry over");
            test.log(LogStatus.FAIL, "Commission Filter Did not carry over");
            LogoutCP(driver);
        }
         
        else if(!CPFundType.contains("Stock") ){
            System.out.println("Fund Type Filter Did not carry over");
            test.log(LogStatus.FAIL, "Fund Type Filter Did not carry over");
            LogoutCP(driver);
        }
         
        else if(SymbolValue == false) {
            System.out.println("Search Filter Did not carry over");
            test.log(LogStatus.FAIL, "Search Filter Did not carry over");
            LogoutCP(driver);
 
        }
         
        else {
            System.out.println("All Filter were Successfully carried over");
            test.log(LogStatus.PASS, "All Filter were Successfully carried over");
            LogoutCP(driver);
             
        }
 
    }
     
    @Test
    public void CPPersistResultsJoint() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        //Fetch the MF Scanner page
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
        driver.get(homePage);
 
        SubmitSearch("gopax", "United States", "Aberdeen Funds", "No", "Stock" );
 
        Thread.sleep(3000);
 
        //Login to CP
        ClientPortalLogin("fancy0012", "tester12");
        Thread.sleep(6000);
         
        String CPCountry = GetCPCountry().getText();
        String CPFamily = GetCPFamily().getText();
        String CPCommission = GetCPCommission().getText();
        String CPFundType = GetCPFundType().getText();
        boolean SymbolValue = driver.getPageSource().contains("GOPAX");
        System.out.println(SymbolValue);
        System.out.println(CPCountry);
        System.out.println(CPFamily);
        System.out.println(CPCommission);
        System.out.println(CPFundType);
 
        if(!CPCountry.contains("United States")) {
            System.out.println("Country Filter Did not carry over");
            test.log(LogStatus.FAIL, "Country Filter Did not carry over");
            LogoutCP(driver);
 
        }
 
        else if(!CPFamily.contains("Aberdeen Funds")){
            System.out.println("Family Filter Did not carry over");
            test.log(LogStatus.FAIL, "Family Filter Did not carry over");
            LogoutCP(driver);
        }
         
        else if(!CPCommission.contains("No")){
            System.out.println("Commission Filter Did not carry over");
            test.log(LogStatus.FAIL, "Commission Filter Did not carry over");
            LogoutCP(driver);
        }
         
        else if(!CPFundType.contains("Stock") ){
            System.out.println("Fund Type Filter Did not carry over");
            test.log(LogStatus.FAIL, "Fund Type Filter Did not carry over");
            LogoutCP(driver);
        }
         
        else if(SymbolValue == false) {
            System.out.println("Search Filter Did not carry over");
            test.log(LogStatus.FAIL, "Search Filter Did not carry over");
            LogoutCP(driver);
 
        }
         
        else {
            System.out.println("All Filter were Successfully carried over");
            test.log(LogStatus.PASS, "All Filter were Successfully carried over");
            LogoutCP(driver);
             
        }
 
    }
     
    @Test
    public void CPPersistResultsIRA() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        //Fetch the MF Scanner page
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
        driver.get(homePage);
 
        SubmitSearch("gopax", "United States", "Aberdeen Funds", "No", "Stock" );
 
        Thread.sleep(3000);
 
        //Login to CP
        ClientPortalLogin("fancy0011", "tester12");
        Thread.sleep(6000);
         
        String CPCountry = GetCPCountry().getText();
        String CPFamily = GetCPFamily().getText();
        String CPCommission = GetCPCommission().getText();
        String CPFundType = GetCPFundType().getText();
        boolean SymbolValue = driver.getPageSource().contains("GOPAX");
        System.out.println(SymbolValue);
        System.out.println(CPCountry);
        System.out.println(CPFamily);
        System.out.println(CPCommission);
        System.out.println(CPFundType);
 
        if(!CPCountry.contains("United States")) {
            System.out.println("Country Filter Did not carry over");
            test.log(LogStatus.FAIL, "Country Filter Did not carry over");
            LogoutCP(driver);
 
        }
 
        else if(!CPFamily.contains("Aberdeen Funds")){
            System.out.println("Family Filter Did not carry over");
            test.log(LogStatus.FAIL, "Family Filter Did not carry over");
            LogoutCP(driver);
        }
         
        else if(!CPCommission.contains("No")){
            System.out.println("Commission Filter Did not carry over");
            test.log(LogStatus.FAIL, "Commission Filter Did not carry over");
            LogoutCP(driver);
        }
         
        else if(!CPFundType.contains("Stock") ){
            System.out.println("Fund Type Filter Did not carry over");
            test.log(LogStatus.FAIL, "Fund Type Filter Did not carry over");
            LogoutCP(driver);
        }
         
        else if(SymbolValue == false) {
            System.out.println("Search Filter Did not carry over");
            test.log(LogStatus.FAIL, "Search Filter Did not carry over");
            LogoutCP(driver);
 
        }
         
        else {
            System.out.println("All Filter were Successfully carried over");
            test.log(LogStatus.PASS, "All Filter were Successfully carried over");
            LogoutCP(driver);
             
        }
 
    }
     
    @Test
    public void CPPersistResultsTrust() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        //Fetch the MF Scanner page
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
        driver.get(homePage);
 
        SubmitSearch("gopax", "United States", "Aberdeen Funds", "No", "Stock" );
 
        Thread.sleep(3000);
 
        //Login to CP
        ClientPortalLogin("fancy0015", "tester12");
        Thread.sleep(6000);
         
        String CPCountry = GetCPCountry().getText();
        String CPFamily = GetCPFamily().getText();
        String CPCommission = GetCPCommission().getText();
        String CPFundType = GetCPFundType().getText();
        boolean SymbolValue = driver.getPageSource().contains("GOPAX");
        System.out.println(SymbolValue);
        System.out.println(CPCountry);
        System.out.println(CPFamily);
        System.out.println(CPCommission);
        System.out.println(CPFundType);
 
        if(!CPCountry.contains("United States")) {
            System.out.println("Country Filter Did not carry over");
            test.log(LogStatus.FAIL, "Country Filter Did not carry over");
            LogoutCP(driver);
 
        }
 
        else if(!CPFamily.contains("Aberdeen Funds")){
            System.out.println("Family Filter Did not carry over");
            test.log(LogStatus.FAIL, "Family Filter Did not carry over");
            LogoutCP(driver);
        }
         
        else if(!CPCommission.contains("No")){
            System.out.println("Commission Filter Did not carry over");
            test.log(LogStatus.FAIL, "Commission Filter Did not carry over");
            LogoutCP(driver);
        }
         
        else if(!CPFundType.contains("Stock") ){
            System.out.println("Fund Type Filter Did not carry over");
            test.log(LogStatus.FAIL, "Fund Type Filter Did not carry over");
            LogoutCP(driver);
        }
         
        else if(SymbolValue == false) {
            System.out.println("Search Filter Did not carry over");
            test.log(LogStatus.FAIL, "Search Filter Did not carry over");
            LogoutCP(driver);
 
        }
         
        else {
            System.out.println("All Filter were Successfully carried over");
            test.log(LogStatus.PASS, "All Filter were Successfully carried over");
            LogoutCP(driver);
             
        }
 
    }
     
    //After Implemented, add cases for Institutional accounts as well
     
     
    @Test
    public void CPPersistResultsEditButton() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        //Fetch the MF Scanner page
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
        driver.get(homePage);
 
        SubmitSearch("gopax", "United States", "Aberdeen Funds", "No", "Stock" );
 
        Thread.sleep(3000);
 
        //Login to CP
        ClientPortalLogin("steves456", "knicks12");
        Thread.sleep(10000);
         
        //Click the edit button
        WebElement EditButton = GetCPEditButton();
        EditButton.click();
        Thread.sleep(3000);
         
        //Click the View Results button
        WebElement ViewResults = GetCPViewResultsButton();
        ViewResults.click();
        Thread.sleep(3000);
         
        //Collect the values for the currently applied filters
        String CPCountry = GetCPCountry().getText();
        String CPFamily = GetCPFamily().getText();
        String CPCommission = GetCPCommission().getText();
        String CPFundType = GetCPFundType().getText();
        boolean SymbolValue = driver.getPageSource().contains("GOPAX");
        System.out.println(SymbolValue);
        System.out.println(CPCountry);
        System.out.println(CPFamily);
        System.out.println(CPCommission);
        System.out.println(CPFundType);
 
        //Verify that after clicking the edit button and resubmitting the search, the same filters are still being applied
        if(!CPCountry.contains("United States")) {
            System.out.println("Country Filter Did not carry over");
            test.log(LogStatus.FAIL, "Country Filter Did not carry over");
            LogoutCP(driver);
        }
 
        else if(!CPFamily.contains("Aberdeen Funds")){
            System.out.println("Family Filter Did not carry over");
            test.log(LogStatus.FAIL, "Family Filter Did not carry over");
            LogoutCP(driver);
        }
         
        else if(!CPCommission.contains("No")){
            System.out.println("Commission Filter Did not carry over");
            test.log(LogStatus.FAIL, "Commission Filter Did not carry over");
            LogoutCP(driver);
        }
         
        else if(!CPFundType.contains("Stock") ){
            System.out.println("Fund Type Filter Did not carry over");
            test.log(LogStatus.FAIL, "Fund Type Filter Did not carry over");
            LogoutCP(driver);
        }
         
        else if(SymbolValue == false) {
            System.out.println("Search Filter Did not carry over");
            test.log(LogStatus.FAIL, "Search Filter Did not carry over");
            LogoutCP(driver);
 
        }
         
        else {
            System.out.println("All Filter were Successfully carried over");
            test.log(LogStatus.PASS, "All Filter were Successfully carried over");
            LogoutCP(driver);
        }
 
    }
 
}
