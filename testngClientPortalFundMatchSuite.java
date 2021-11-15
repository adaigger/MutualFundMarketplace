package ibmarketing.automatedqa.mutualFunds.validations;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ibmarketing.automatedqa.mutualFunds.validations.testngToolFunctionalilty;
 
 
 
public class testngClientPortalFundMatchSuite extends BaseTest {
    WebDriver driver;
 
 
 
 
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        //System.setProperty("webdriver.gecko.driver", "C:\\Users\\devuser6\\Desktop\\geckodriver-v0.24.0-win32\\geckodriver.exe");
 
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
 
    public WebElement GetCPTotalNum() {
        
        return driver.findElement(By.xpath("/html/body/div[1]/main/section/div[2]/section/div/div[3]/div[2]/div[1]/div/div"));
     
    }
 
 
    //Returns the total results displayed on the results table for the website MF scanner
    public int WebFundsTotal(String search, String country, String family, String fundType, String commissionType) throws InterruptedException {
 
        System.out.println("--------Opening Mutual Funds Tool--------");
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
        driver.get(homePage);
         
        //Enter the search Term
        driver.findElement(By.xpath("//*[@id=\"_f10\"]")).sendKeys(search);
 
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
 
        Thread.sleep(4000);
 
 
        //Get the total number of funds returned from the search
        String totalnum = driver.findElement(By.xpath("/html/body/div[3]/section[2]/div/div/div/div/div[1]/div/div[3]/div[2]/div[1]/div/div")).getText();
 
        String lastFourDigits = "";     //substring containing last 4 characters
 
        if (totalnum.length() == 22)
        {
            lastFourDigits = totalnum.substring(totalnum.length() - 3);
        }
        else if (totalnum.length() == 25)
        {
            lastFourDigits = totalnum.substring(totalnum.length() - 6);
        }
        else if (totalnum.length() == 14)
        {
            lastFourDigits = totalnum.substring(totalnum.length() - 1);
        }
        else if (totalnum.length() > 2)
        {
            lastFourDigits = totalnum.substring(totalnum.length() - 2);
        }
        else
        {
            lastFourDigits = totalnum;
        }
 
        System.out.println("Total Funds from Tool: " + lastFourDigits);
 
         String DeleteComma = lastFourDigits.replace(",", "");
         
        int Total = Integer.parseInt(DeleteComma);
 
 
        return Total;
    }
     
    //Returns the total results displayed on the results table for the website MF scanner
    //Assumes user is at the results table on the website
        public void CPFundsTotal(int webTotal) throws InterruptedException {
 
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
            driver.findElement(By.xpath("//*[@id=\"user_name\"]")).sendKeys("steves456");
            driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("knicks12");
            driver.findElement(By.xpath("//*[@id=\"submitForm\"]")).click();
 
            Thread.sleep(6000);
 
            //Get the total number of funds returned from the search
            String totalnum = GetCPTotalNum().getText();
 
            String lastFourDigits = "";     //substring containing last 4 characters
 
            if (totalnum.length() == 22)
            {
                lastFourDigits = totalnum.substring(totalnum.length() - 3);
            }
            else if (totalnum.length() == 25)
            {
                lastFourDigits = totalnum.substring(totalnum.length() - 6);
            }
            else if (totalnum.length() == 14)
            {
                lastFourDigits = totalnum.substring(totalnum.length() - 1);
            }
            else if (totalnum.length() > 2)
            {
                lastFourDigits = totalnum.substring(totalnum.length() - 2);
            }
            else
            {
                lastFourDigits = totalnum;
            }
 
            System.out.println("Total Funds from Tool: " + lastFourDigits);
 
            String DeleteComma = lastFourDigits.replace(",", "");
             
            int Total = Integer.parseInt(DeleteComma);
 
            if (Total == webTotal){
                System.out.println("Total from CP " + lastFourDigits + " matches the website " + webTotal);
                test.log(LogStatus.PASS, "Total from CP " + lastFourDigits + " matches the website " + webTotal);
                
            }
 
            else{
                System.out.println("Total from CP " + lastFourDigits + " does not match the website " + webTotal);
                test.log(LogStatus.FAIL, "Total from CP " + lastFourDigits + " does not match the website " + webTotal);
            }
 
             
        }
        
        public void CPFundsTotal_Advisor(int webTotal) throws InterruptedException {
        	 
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
            driver.findElement(By.xpath("//*[@id=\"user_name\"]")).sendKeys("davefa123");
            driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("tester1");
            driver.findElement(By.xpath("//*[@id=\"submitForm\"]")).click();
 
            Thread.sleep(6000);
 
            //Get the total number of funds returned from the search
            String totalnum = GetCPTotalNum().getText();
 
            String lastFourDigits = "";     //substring containing last 4 characters
 
            if (totalnum.length() == 22)
            {
                lastFourDigits = totalnum.substring(totalnum.length() - 3);
            }
            else if (totalnum.length() == 25)
            {
                lastFourDigits = totalnum.substring(totalnum.length() - 6);
            }
            else if (totalnum.length() == 14)
            {
                lastFourDigits = totalnum.substring(totalnum.length() - 1);
            }
            else if (totalnum.length() > 2)
            {
                lastFourDigits = totalnum.substring(totalnum.length() - 2);
            }
            else
            {
                lastFourDigits = totalnum;
            }
 
            System.out.println("Total Funds from Tool: " + lastFourDigits);
 
            String DeleteComma = lastFourDigits.replace(",", "");
             
            int Total = Integer.parseInt(DeleteComma);
 
            if (Total == webTotal){
                System.out.println("Total from CP " + lastFourDigits + " matches the website " + webTotal);
                test.log(LogStatus.PASS, "Total from CP " + lastFourDigits + " matches the website " + webTotal);
                
            }
 
            else{
                System.out.println("Total from CP " + lastFourDigits + " does not match the website " + webTotal);
                test.log(LogStatus.FAIL, "Total from CP " + lastFourDigits + " does not match the website " + webTotal);
            }
 
             
        }
        
        public void CPFundsTotal_Broker(int webTotal) throws InterruptedException {
       	 
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
            driver.findElement(By.xpath("//*[@id=\"user_name\"]")).sendKeys("fully179");
            driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("tester22");
            driver.findElement(By.xpath("//*[@id=\"submitForm\"]")).click();
 
            Thread.sleep(6000);
 
            //Get the total number of funds returned from the search
            String totalnum = GetCPTotalNum().getText();
 
            String lastFourDigits = "";     //substring containing last 4 characters
 
            if (totalnum.length() == 22)
            {
                lastFourDigits = totalnum.substring(totalnum.length() - 3);
            }
            else if (totalnum.length() == 25)
            {
                lastFourDigits = totalnum.substring(totalnum.length() - 6);
            }
            else if (totalnum.length() == 14)
            {
                lastFourDigits = totalnum.substring(totalnum.length() - 1);
            }
            else if (totalnum.length() > 2)
            {
                lastFourDigits = totalnum.substring(totalnum.length() - 2);
            }
            else
            {
                lastFourDigits = totalnum;
            }
 
            System.out.println("Total Funds from Tool: " + lastFourDigits);
 
            String DeleteComma = lastFourDigits.replace(",", "");
             
            int Total = Integer.parseInt(DeleteComma);
 
            if (Total == webTotal){
                System.out.println("Total from CP " + lastFourDigits + " matches the website " + webTotal);
                test.log(LogStatus.PASS, "Total from CP " + lastFourDigits + " matches the website " + webTotal);
                
            }
 
            else{
                System.out.println("Total from CP " + lastFourDigits + " does not match the website " + webTotal);
                test.log(LogStatus.FAIL, "Total from CP " + lastFourDigits + " does not match the website " + webTotal);
            }
 
             
        }
 
    @Test
    public void FundFamilySearch() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("", "United States", "Aberdeen Funds", "All", "All");
        CPFundsTotal(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
    
    @Test
    public void FundFamilySearch_Advisor() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("", "United States", "Aberdeen Funds", "All", "All");
        CPFundsTotal_Advisor(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
    
    @Test
    public void FundFamilySearch_Broker() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("", "United States", "Aberdeen Funds", "All", "All");
        CPFundsTotal_Advisor(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
     
    @Test
    public void DefaultSearch() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("", "United States", "All", "All", "All");
        CPFundsTotal(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
    
    @Test
    public void DefaultSearch_Advisor() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("", "United States", "All", "All", "All");
        CPFundsTotal_Advisor(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
    
    @Test
    public void DefaultSearch_Broker() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("", "United States", "All", "All", "All");
        CPFundsTotal_Broker(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
     
    @Test
    public void CountrySearch() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("", "Germany", "All", "All", "All");
        CPFundsTotal(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
    
    @Test
    public void CountrySearch_Advisor() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("", "Germany", "All", "All", "All");
        CPFundsTotal_Advisor(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
    
    @Test
    public void CountrySearch_Broker() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("", "Germany", "All", "All", "All");
        CPFundsTotal_Broker(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
     
    @Test
    public void CountryFamilySearch() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("", "Germany", "Allianz Global Funds", "All", "All");
        CPFundsTotal(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
    
    @Test
    public void CountryFamilySearch_Advisor() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("", "Germany", "Allianz Global Funds", "All", "All");
        CPFundsTotal_Advisor(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
    
    @Test
    public void CountryFamilySearch_Broker() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("", "Germany", "Allianz Global Funds", "All", "All");
        CPFundsTotal_Broker(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
     
    @Test
    public void CommissionsSearch() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("", "United States", "All", "Yes", "All");
        CPFundsTotal(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
    
    @Test
    public void CommissionsSearch_Advisor() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("", "United States", "All", "Yes", "All");
        CPFundsTotal_Advisor(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
    
    @Test
    public void CommissionsSearch_Broker() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("", "United States", "All", "Yes", "All");
        CPFundsTotal_Broker(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
     
    @Test
    public void FundTypeSearch() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("", "United States", "All", "All", "Stock");
        CPFundsTotal(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
    
    @Test
    public void FundTypeSearch_Advisor() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("", "United States", "All", "All", "Stock");
        CPFundsTotal_Advisor(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
    
    @Test
    public void FundTypeSearch_Broker() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("", "United States", "All", "All", "Stock");
        CPFundsTotal_Broker(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
     
    @Test
    public void SymbolSearch() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("AGAZX", "United States", "All", "All", "All");
        CPFundsTotal(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
    
    @Test
    public void SymbolSearch_Advisor() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("AGAZX", "United States", "All", "All", "All");
        CPFundsTotal_Advisor(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
    
    @Test
    public void SymbolSearch_Broker() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("AGAZX", "United States", "All", "All", "All");
        CPFundsTotal_Broker(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
     
    @Test
    public void CUSIPSearch() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("003020351", "United States", "All", "All", "All");
        CPFundsTotal(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
    
    @Test
    public void CUSIPSearch_Advisor() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("003020351", "United States", "All", "All", "All");
        CPFundsTotal_Advisor(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
    
    @Test
    public void CUSIPSearch_Broker() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("003020351", "United States", "All", "All", "All");
        CPFundsTotal_Broker(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
     
    @Test
    public void ISINSearch() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("US0030203287", "United States", "All", "All", "All");
        CPFundsTotal(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
    
    @Test
    public void ISINSearch_Advisor() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("US0030203287", "United States", "All", "All", "All");
        CPFundsTotal_Advisor(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
    
    @Test
    public void ISINSearch_Broker() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("US0030203287", "United States", "All", "All", "All");
        CPFundsTotal_Broker(webTotal);
        testngToolFunctionalilty.LogoutCP(driver);
    }
     
    @Test
    public void NoResultsSearch() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int webTotal = WebFundsTotal("US0030203287", "United States", "Allianz Global Funds", "All", "All");
        Thread.sleep(2000);
        if (webTotal == 0){
            System.out.println("Total from search was 0, message successfully popped up");
            test.log(LogStatus.PASS, "Total from search was 0, message successfully popped up");
            
        }

        else{
            System.out.println("Total from search was not 0");
            test.log(LogStatus.FAIL, "Total from search was not 0");
        }
    }
    
    
 
}
