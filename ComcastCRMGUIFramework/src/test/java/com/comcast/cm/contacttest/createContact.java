package com.comcast.cm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.generic.fileutility.Excelutility;
import com.comcast.crm.generic.fileutility.PropertiesFileutility;
import com.comcast.crm.generic.wedriverutility.JavaUtility;
import com.comcast.crm.generic.wedriverutility.webDriverUtility;

public class createContact {

	@Test
	public void createcontactt()

	 throws IOException, InterruptedException {
		PropertiesFileutility putil= new PropertiesFileutility();
		Excelutility eutil= new Excelutility();
		JavaUtility jutil= new JavaUtility();
		webDriverUtility wutil= new webDriverUtility();
		
		//generating random number
		Random random= new Random();
		int randomInt = random.nextInt(500);
		
		//reading common data from properties file
		FileInputStream fis1= new FileInputStream("./configAppData/commondata.properties");
		Properties prop= new Properties();
		prop.load(fis1);
		String Browser = prop.getProperty("browser");
		String URL= prop.getProperty("url");
		String Username= prop.getProperty("username");
		String Password= prop.getProperty("password");
		
		//reading data from excel file
		FileInputStream fis2= new FileInputStream("./testData/testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis2);
		Sheet sh = wb.getSheet("Contact");
		Row row = sh.getRow(1);
		String contactName = row.getCell(2).toString() + randomInt;
		wb.close();
		
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
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		System.out.println(contactName);
	
		//Verifing  contact is created
		String actualcontact= driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		Boolean ststus =actualcontact.contains(contactName);
		Assert.assertEquals(ststus, true);
		System.out.println("==verified");
		
		
		//verify with org name in orgtextfield
		String actcontact = driver.findElement(By.id("mouseArea_Last Name")).getText();
		SoftAssert asser= new SoftAssert();
		asser.assertEquals(actcontact,contactName );
		System.out.println("==verified");
				
		
		
		Actions act= new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();


	}

}
