package ibmarketing.automatedqa.mutualFunds.validations;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
 
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseTest {
	 	
	    public static ExtentReports report;
	    public static ExtentTest test;
	    
	    @BeforeSuite
		public static void setUp() throws Exception {
			report = new ExtentReports(System.getProperty("user.dir")+"\\MutualFundsResults.html");
			// String nameofCurrMethod = name.getMethodName();
			//test = report.startTest(name.getMethodName());
		}
	    
	  
	    
	    @AfterSuite
	    public void tearDown()
	    {
	    	report.endTest(test);
	        report.flush();
	    }
	    
	    
	    
}
