package practice.test;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.genericbaseutility.BaseClass;


public class practiceExtentReport extends BaseClass {
	
	@Test
	public void createContact() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.get("https://www.flipkart.com/");
		String title = driver.findElement(By.xpath("//img[@title='Flipkart']")).getAttribute("title");
		TakesScreenshot edriver= (TakesScreenshot)driver;
		String filepath=edriver.getScreenshotAs(OutputType.BASE64);
		
		
		
		
		//step1: create an object of ExtentSparkreporter
		ExtentSparkReporter spark= new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM Application");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		//step2: create an object of Extentreports and specify env. info and create test
		
		ExtentReports report= new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows 10");
		report.setSystemInfo("Browser", "Chrome");
		
		ExtentTest test =report.createTest("createContact");
		test.log(Status.INFO, "Login");
		test.log(Status.INFO, "HomePage");
		Assert.assertEquals(title, "Flipkart");
		test.log(Status.PASS, "verified");
		test.log(Status.FAIL, "not verified");
		Reporter.log("==Verified==" +title, true);
		test.addScreenCaptureFromBase64String(filepath, "errorFile");
		
		report.flush();
		
		
	}

}
