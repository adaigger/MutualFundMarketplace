package ibmarketing.automatedqa.mutualFunds.validations;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;




public class testngFundTypeMatchSuite extends BaseTest {
	WebDriver driver;
	
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\daigg\\Desktop\\selenium\\chromedriver_win32\\chromedriver.exe");
		
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
	
	
	public int DataNumFundsType(String family, String fundType) {
		 
        System.out.println("--------Opening Mutual trsrvr2--------");
        String Database = "https://proxy.prod.ibkr-int.com/~topsweb/cgi-bin/trsrvr2_client.pl";
 
 
 
        driver.get(Database);
        // Enter Query and click Submit
        Select drpdwn = new Select(driver.findElement(By.name("format")));
        drpdwn.selectByVisibleText("XML");
        driver.findElement(By.xpath("/html/body/form/div[4]/table/tbody/tr[1]/td[2]/input")).clear();
        driver.findElement(By.xpath("/html/body/form/div[4]/table/tbody/tr[1]/td[2]/input")).sendKeys("64 all mff=" + family + "\r\n" + " resolve=64&scope=all&qtype=mff&mff=" + family +"");
        System.out.println("64 all mff=" + family + "\r\n" + " resolve=64&scope=all&qtype=mff&mff=" + family +"");
        driver.findElement(By.xpath("/html/body/form/div[7]/input")).click();
 
        // get the text of the body element
        WebElement body = driver.findElement(By.tagName("body"));
        String bodyText = body.getText();
 
        // count occurrences of the string
        int count = 0;
 
        // search for the String within the text
        while (bodyText.contains(fundType)){
 
            // when match is found, increment the count
            count++;
 
            // continue searching from where you left off
            bodyText = bodyText.substring(bodyText.indexOf(fundType) + fundType.length());
        }
        System.out.println("Total Funds in Database: " + count);
 
        return count;
    }
 
 
    public void ToolNumFundsType(String family, String country, int database, String fundType, String commissionType) throws InterruptedException {
 
        System.out.println("--------Opening Mutual Funds Tool--------");
        String homePage = "https://www.interactivebrokers.com/en/index.php?f=46321#/";
 
        driver.get(homePage);
 
        // Click on the country dropdown
        WebElement CountryDrop = driver.findElement(By.xpath("//*[@id=\"_ddi13\"]"));
        CountryDrop.click();
        //Search for the country
        WebElement CountryDropSearch = driver.findElement(By.xpath("//*[@id=\"_ddi13\"]"));
        CountryDropSearch.sendKeys(country);
        CountryDropSearch.sendKeys(Keys.RETURN);
         
        driver.findElement(By.xpath("//span[.='"+fundType+"']")).click();
 
        // Click on the Fund family dropdown
        WebElement FundDrop = driver.findElement(By.xpath("//*[@id=\"_ddi16\"]"));
        FundDrop.click();
        //Search for the Fund family
        WebElement FundDropSearch = driver.findElement(By.xpath("//*[@id=\"_ddi16\"]"));
        FundDropSearch.sendKeys(family);
        FundDropSearch.sendKeys(Keys.RETURN);
 
        // WebElement countryDropResult = driver.findElement(By.xpath("//*[@id=\"contents\"]/section[2]/div/div/div/div/div/div/div/div[2]/form/div[4]/label[6]/span[1]"));
        // countryDropResult.click();
 
        driver.findElement(By.xpath("//span[.='"+fundType+"']")).click();
         
        driver.findElement(By.xpath("//span[.='"+commissionType+"']")).click();
         
 
 
        WebElement ViewResults = driver.findElement(By.xpath("/html/body/div[3]/section[2]/div/div/div/div/div/div/form/div[7]/div/button[1]"));
        ViewResults.click();
 
        Thread.sleep(3000);
 
 
 
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
 
        int Total = Integer.parseInt(lastFourDigits);
 
 
        if (Total == database){
            System.out.println("Total from Tool " + lastFourDigits + " matches the database " + database);
            test.log(LogStatus.PASS, "Total from Tool " + lastFourDigits + " matches the database " + database);
        }
 
        else{
            System.out.println("Total from Tool " + lastFourDigits + " does not match the database " + database);
            test.log(LogStatus.FAIL, "Total from Tool " + lastFourDigits + " does not match the database " + database);
        }
 
 
 
 
    }
 
    @Test
    public void Capital361_USA_Equity() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("361\\ Capital\\ Funds", " <investType>Equity");
        ToolNumFundsType("361 Capital Funds", "United States", data, "Equity", "All");
 
    }
 
    @Test
    public void Capital361_USA_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("361\\ Capital\\ Funds", "p77036");
        ToolNumFundsType("361 Capital Funds", "United States", data, "All", "All");
 
    }
 
    @Test
    public void Capital361_Netherland_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("361\\ Capital\\ Funds", "NLD");
        ToolNumFundsType("361 Capital Funds", "Netherlands Antilles", data, "All", "All");
 
    }
 
    @Test
    public void Vanguard_USA_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("Vanguard", "p77036");
        ToolNumFundsType("Vanguard", "United States", data, "All", "All");
 
    }
 
    @Test
    public void Vanguard_USA_Bond() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("Vanguard", "<investType>Bond") - 8;
        ToolNumFundsType("Vanguard", "United States", data, "Bond", "All");
 
    }
 
    @Test
    public void Vanguard_Netherlands_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data1 = DataNumFundsType("VANGUARD\\ GROUP\\ (IRELAND)\\ LTD\\ (IE)", "p76395");
        int data2 = DataNumFundsType("VANGUARD\\ INV\\ UK\\ LTD\\ (GB)", "p76395");
        int data = data1 + data2;
        ToolNumFundsType("Vanguard", "Netherlands Antilles", data, "All", "All");
 
    }
 
    @Test
    public void Aberdeen_USA_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("Aberdeen\\ Funds", "p77036");
        ToolNumFundsType("Aberdeen", "United States", data, "All", "All");
 
    }
 
    @Test
    public void Aberdeen_Alternatives() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("Aberdeen\\ Funds", "<investType>Alternatives");
        ToolNumFundsType("Aberdeen", "United States", data, "Alternatives", "All");
 
    }
 
    @Test
    public void AmericanFunds_USA_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("AMG\\ Funds", "p77036");
        ToolNumFundsType("AMG Funds", "United States", data, "All", "All");
 
    }
 
    @Test
    public void AmericanFunds_Netherlands_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("AMG\\ Funds", "NLD");
        ToolNumFundsType("AMG Funds", "Netherlands Antilles", data, "All", "All");
 
    }
 
    @Test
    public void AmericanFunds_Balanced() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("AMG\\ Funds", "<investType>Balanced") - 1;
        ToolNumFundsType("AMG Funds", "United States", data, "Balanced", "All");
 
    }
 
    @Test
    public void AQRFunds_Commodity() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("AQR\\ Funds", "<investType>Commodity");
        ToolNumFundsType("AQR Funds", "United States", data, "Commodity", "All");
 
    }
 
    @Test
    public void Calvert_MixedInvestments() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("Calvert", "<investType>Actifs Mixtes");
        ToolNumFundsType("Calvert", "United States", data, "Mixed Investments", "All");
 
    }
 
    @Test
    public void Calvert_USA_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("Calvert", "p77036");
        ToolNumFundsType("Calvert", "United States", data, "All", "All");
 
    }
 
    @Test
    public void AAAMCO_USA_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("AAAMCO\\ Funds", "p77036");
        ToolNumFundsType("AAAMCO Funds", "United States", data, "All", "All");
 
    }
 
    @Test
    public void DavisFunds_USA_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("Davis\\ Funds", "p77036");
        ToolNumFundsType("Davis Funds", "United States", data, "All", "All");
 
    }
 
    @Test
    public void Carmignac_AUT_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("CARMIGNAC\\ GESTION\\ (LU)", "AUT");
        ToolNumFundsType("CARMIGNAC GESTION", "Austria", data, "All", "All");
 
    }
 
    @Test
    public void DavisFunds_MoneyMarket() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("Davis\\ Funds", "<investType>Money Market") - 1;
        ToolNumFundsType("Davis Funds", "United States", data, "Money Market", "All");
 
    }
 
    @Test
    public void AkreFunds_USA_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("Akre\\ Funds", "p77036");
        ToolNumFundsType("Akre Funds", "United States", data, "All", "All");
 
    }
 
    @Test
    public void AkreFunds_NLD_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("Akre\\ Funds", "NLD");
        ToolNumFundsType("Akre Funds", "Netherlands Antilles", data, "All", "All");
 
    }
 
    @Test
    public void AkreFunds_Stock() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("Akre\\ Funds", "<investType>Stock");
        ToolNumFundsType("Akre Funds", "United States", data, "Stock", "All");
 
    }
 
    @Test
    public void DFAOffshoreFunds_USA_Total() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("DFA\\ Offshore\\ Funds", "USA");
        ToolNumFundsType("DFA Offshore Funds", "United States", data, "All", "All");
 
    }
 
    @Test
    public void DFAOffshoreFunds_Netherlands_Total() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("DFA\\ Offshore\\ Funds", "<resultAsset idRef=");
        ToolNumFundsType("DFA Offshore Funds", "Netherlands Antilles", data, "All", "All");
 
    }
 
    @Test
    public void DFAOffshoreFunds_HK_Total() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("DFA\\ Offshore\\ Funds", "HKG");
        ToolNumFundsType("DFA Offshore Funds", "Hong Kong Special Administrative Region of China", data, "All", "All");
 
    }
 
    @Test
    public void Aberdeen_EU_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data1 = DataNumFundsType("ABERDEEN\\ UT\\ MGRS\\ LTD\\ (GB)", "<resultAsset idRef=");
        int data2 = DataNumFundsType("ABERDEEN\\ STANDARD\\ INV\\ (LU)", "<resultAsset idRef=") - 1;
        int data = data1 + data2;
        ToolNumFundsType("Aberdeen Funds", "Netherlands Antilles", data, "All", "All");
 
    }
 
    @Test
    public void Aberdeen_HK_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("ABERDEEN\\ STANDARD\\ INV\\ (LU)", "HKG");
        ToolNumFundsType("Aberdeen Funds", "Hong Kong Special Administrative Region of China", data, "All", "All");
 
    }
 
    @Test
    public void ABRDynamic_USA_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("ABR\\ Dynamic\\ Mutual\\ Funds", "p77036");
        ToolNumFundsType("ABR Dynamic Mutual Funds", "United States", data, "All", "All");
 
    }
 
    @Test
    public void BaronFunds_USA_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("Baron\\ Funds", "p77036");
        ToolNumFundsType("Baron Funds", "United States", data, "All", "All");
 
    }
 
    @Test
    public void BaronFunds_DEU_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("Baron\\ Funds", "DEU");
        ToolNumFundsType("Baron Funds", "Germany", data, "All", "All");
 
    }
 
    @Test
    public void ChironFunds_USA_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("Chiron\\ Funds", "p77036");
        ToolNumFundsType("Chiron Funds", "United States", data, "All", "All");
 
    }
 
    @Test
    public void ChironFunds_DEU_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("Chiron\\ Funds", "DEU");
        ToolNumFundsType("Chiron Funds", "Germany", data, "All", "All");
 
    }
 
    @Test
    public void DavenportFunds_USA_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("Davenport\\ Funds", "p77036");
        ToolNumFundsType("Davenport Funds", "United States", data, "All", "All");
 
    }
 
    @Test
    public void DavenportFunds_DEU_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("Davenport\\ Funds", "DEU");
        ToolNumFundsType("Davenport Funds", "Germany", data, "All", "All");
 
    }
 
    @Test
    public void ZacksFunds_USA_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("Zacks\\ Funds", "p77036");
        ToolNumFundsType("Zacks Funds", "United States", data, "All", "All");
 
    }
     
    @Test
    public void AllianzGI_IND_All() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("AZIMUT\\ FUNDS", "p77038");
        ToolNumFundsType("AZIMUT FUNDS", "India", data, "All", "All");
 
    }
     
    @Test
    public void AmericanBeacon_NoCommission() throws InterruptedException {
    	String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
        int data = DataNumFundsType("American\\ Beacon", "<fee12b1>T") - 43;
        ToolNumFundsType("American Beacon", "United States", data, "All", "No");
 
    }
	
	
	
	
	
}
