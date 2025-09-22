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

import com.comcast.crm.generic.fileutility.Excelutility;
import com.comcast.crm.generic.fileutility.PropertiesFileutility;
import com.comcast.crm.generic.wedriverutility.JavaUtility;
import com.comcast.crm.generic.wedriverutility.webDriverUtility;

public class createOrgwithPhoneno {
	
	

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		PropertiesFileutility putil= new PropertiesFileutility();
		Excelutility eutil= new Excelutility();
		JavaUtility jutil= new JavaUtility();
		webDriverUtility wutil= new webDriverUtility();
		
		//generating random number
				Random random= new Random();
				int randomInt = random.nextInt(500);
				
				//reading common data from properties file
				FileInputStream fis1= new FileInputStream("./src/test/resources/TestData/commondata.properties");
				Properties prop= new Properties();
				prop.load(fis1);
				String Browser = prop.getProperty("browser");
				String URL= prop.getProperty("url");
				String Username= prop.getProperty("username");
				String Password= prop.getProperty("password");
				
				//reading data from excel file
				FileInputStream fis2= new FileInputStream("C:\\Users\\swati\\OneDrive\\Desktop\\TestScriptdata\\testdata.xlsx");
				Workbook wb = WorkbookFactory.create(fis2);
				Sheet sh = wb.getSheet("Organisation");
				Row row = sh.getRow(7);
				String orgName = row.getCell(2).toString() + randomInt;
				String phoneNo = row.getCell(3).toString();
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
				driver.findElement(By.linkText("Organizations")).click();
				driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
				driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
				driver.findElement(By.id("phone")).sendKeys(phoneNo);
				driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
				System.out.println(orgName);
			
				//Verifing  org is created
				String actualorg= driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(actualorg.contains(orgName)) {
					System.out.println(orgName + "=====Created=====");
				}
				else {
					System.out.println(orgName + "=====Not Created=====");
				}
				
				//verify with phoneNo.
				String actphone = driver.findElement(By.id("dtlview_Phone")).getText();
				if(actphone.trim().equals(phoneNo)) {
					System.out.println(phoneNo + "=====Pass=====");
				}
				else {
					System.out.println(phoneNo + "=====fail=====");
				}
				
				Actions act= new Actions(driver);
				act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
				Thread.sleep(2000);
				driver.findElement(By.linkText("Sign Out")).click();
				driver.quit();


	}

}
