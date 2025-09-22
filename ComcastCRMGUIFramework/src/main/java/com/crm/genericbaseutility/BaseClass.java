package com.crm.genericbaseutility;



import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.Excelutility;
import com.comcast.crm.generic.fileutility.PropertiesFileutility;
import com.comcast.crm.generic.wedriverutility.JavaUtility;
import com.comcast.crm.generic.wedriverutility.staticUtility;
import com.comcast.crm.generic.wedriverutility.webDriverUtility;
import com.comcast.crm.objectrepositoryutility.Homepage;
import com.comcast.crm.objectrepositoryutility.Loginpage;

public class BaseClass {
	
	
	public DatabaseUtility db= new DatabaseUtility();
	public PropertiesFileutility plib= new PropertiesFileutility();
	public Excelutility elib= new Excelutility();
	public JavaUtility jlib= new JavaUtility();
	public webDriverUtility wlib = new webDriverUtility();
	public  WebDriver driver=null;
	public static  WebDriver sdriver=null;
	
	
	
	@BeforeSuite
	public void configBeforeSuite() throws SQLException {
		db.getDBconnected();
		System.out.println("===Connected To DB, Report Config===");
	}
	
	
	@BeforeClass
	 public void launchBrowser() throws IOException {
		String Browser= plib.getDataFromPropertiesFile("browser");
	
		
		if(Browser.equals("chrome")) {
			driver= new ChromeDriver();
		}else if(Browser.equals("firefox")) {
		
			driver= new FirefoxDriver();
		}else if(Browser.equals("edge")) {
			driver= new EdgeDriver();
		}
		else{
			driver= new ChromeDriver();
		
	}
		sdriver=driver;
		staticUtility.setDriver(driver);
		System.out.println("===launch the Browser===");
	}
	
	@BeforeMethod
	public void configBeforeMethod() throws IOException {
		Loginpage lp= new Loginpage(driver);
		String url= plib.getDataFromPropertiesFile("url");
		String username= plib.getDataFromPropertiesFile("username");
		String password= plib.getDataFromPropertiesFile("password");
		lp.loginToApp(url, username, password);
		System.out.println("===Loged to the Application===");
	}
	
	
	@AfterMethod
	public void configAfterMethod() {
		Homepage hp= new Homepage(driver);
		
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		hp.getSignoutedit().click();
		System.out.println("===Logout to the Application===");
	}

	@AfterClass
	public void configAfterClass() throws SQLException {
		driver.quit();
	
		System.out.println("===Close the Browser===");
	}
	
	
	@AfterSuite
	public void configAfterSuite() throws SQLException {
		db.closeConnection();
		System.out.println("===Closed DB, Report Backup===");
	}
	

}
