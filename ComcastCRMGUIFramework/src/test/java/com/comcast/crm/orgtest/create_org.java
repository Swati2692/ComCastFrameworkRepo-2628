package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.Excelutility;
import com.comcast.crm.generic.fileutility.PropertiesFileutility;
import com.comcast.crm.generic.wedriverutility.JavaUtility;
import com.comcast.crm.generic.wedriverutility.webDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreateOrganisationpage;
import com.comcast.crm.objectrepositoryutility.Homepage;
import com.comcast.crm.objectrepositoryutility.Loginpage;
import com.comcast.crm.objectrepositoryutility.OrganisationInformationpage;

public class create_org {
	
	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {
		
		
		
		PropertiesFileutility putil= new PropertiesFileutility();
		Excelutility eutil= new Excelutility();
		JavaUtility jutil= new JavaUtility();
		webDriverUtility wutil= new webDriverUtility();
		
		//generating random number
		Random random= new Random();
		int randomInt = random.nextInt(500);
		
		//reading common data from properties file
		String Browser =putil.getDataFromPropertiesFile("browser");
		String URL =putil.getDataFromPropertiesFile("url");
		String Username=putil.getDataFromPropertiesFile("username");
		String Password=putil.getDataFromPropertiesFile("password");
		
	
		int ranno =jutil.getRandomNo();
		
		//reading data from excel file
		String orgName = eutil.getDataFromExcel("Organisation", 1, 2) + ranno;
		
		
		WebDriver driver;
		
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
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		Loginpage lp = new Loginpage(driver);
		Homepage hp= new Homepage(driver);
		CreateOrganisationpage co= new CreateOrganisationpage(driver);
		OrganisationInformationpage oi= new OrganisationInformationpage(driver);
		
		//reading data from properties and excel file
		driver.get(URL);
		lp.loginToApp("admin", "admin", URL);
		hp.getOrgedit().click();
		co.getCreateorgbtn().click();
		co.getOrgnameedit().sendKeys(orgName);
		co.getSavebtnedit().click();
		System.out.println(orgName);
	
		//Verifing  org is created
		String actualorg= oi.getOrgconfirmmsg().getText();
		if(actualorg.contains(orgName)) {
			System.out.println(orgName + "=====Created=====");
		}
		else {
			System.out.println(orgName + "=====Not Created=====");
		}
		
		//verify with org name in orgtextfield
		String actorg = oi.getOrgconfirm().getText();
		if(actorg.trim().equals(orgName)) {
			System.out.println(orgName + "=====Pass=====");
		}
		else {
			System.out.println(orgName + "=====fail=====");
		}
		
		Actions act= new Actions(driver);
		act.moveToElement(hp.getImgedit()).perform();
		Thread.sleep(2000);
		
		hp.getSignoutedit().click();
		
		driver.quit();

	}

}
