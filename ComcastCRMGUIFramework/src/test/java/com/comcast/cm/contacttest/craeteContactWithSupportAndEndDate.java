package com.comcast.cm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

import com.comcast.crm.generic.fileutility.Excelutility;
import com.comcast.crm.generic.fileutility.PropertiesFileutility;
import com.comcast.crm.generic.wedriverutility.JavaUtility;
import com.comcast.crm.generic.wedriverutility.webDriverUtility;

public class craeteContactWithSupportAndEndDate {
	

	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {
		PropertiesFileutility putil= new PropertiesFileutility();
		Excelutility eutil= new Excelutility();
		JavaUtility jutil= new JavaUtility();
		webDriverUtility wutil= new webDriverUtility();
		
		//generating random number
		int ranno =jutil.getRandomNo();
		
		
		//Current date using date class in java
		String actDate = jutil.getSystemDateYYYYMMDD();
				//change the format of date as per req. use SimpleDateFormat class from java
		String reqdate = jutil.getRequiredDateYYYMMDD(30);
			
		//reading common data from properties file
		String Browser=putil.getDataFromPropertiesFile("browser");
		String URL=putil.getDataFromPropertiesFile("url");
		String Username=putil.getDataFromPropertiesFile("username");
		String Password= putil.getDataFromPropertiesFile("password");
		
		
		//reading data from excel file
		String contactName=eutil.getDataFromExcel("Contact", 1, 2) + ranno;
		
		
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
		
		//reading data from properties and excel file
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(Username);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(contactName);
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(actDate);
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(reqdate);
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		System.out.println(contactName);
	
		//Verifing  contact is created
		String actualcontact= driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(actualcontact.contains(contactName)) {
			System.out.println(contactName + "=====Created=====");
		}
		else {
			System.out.println(contactName + "=====Not Created=====");
		}
		
		//verify with contact name in contacttextfield
		String actcontact = driver.findElement(By.id("mouseArea_Last Name")).getText();
		if(actcontact.trim().equals(contactName)) {
			System.out.println(contactName + "=====Pass=====");
		}
		else {
			System.out.println(contactName + "=====fail=====");
		}
		
		//verify with Start and End Date
				String Currentdate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
				if(Currentdate.trim().equals(actDate)) {
					System.out.println(actDate + "=====Pass=====");
				}
				else {
					System.out.println(actDate + "=====fail=====");
				}
				
				String enddate = driver.findElement(By.id("dtlview_Support End Date")).getText();
				if(enddate.trim().equals(reqdate)) {
					System.out.println(reqdate + "=====Pass=====");
				}
				else {
					System.out.println(reqdate + "=====fail=====");
				}
				
		Actions act= new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();


	}

}
