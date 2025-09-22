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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.Excelutility;
import com.comcast.crm.generic.fileutility.PropertiesFileutility;
import com.comcast.crm.generic.wedriverutility.JavaUtility;
import com.comcast.crm.generic.wedriverutility.webDriverUtility;

public class createorgWithIndustryAndType {
	

	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {
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
		Sheet sh = wb.getSheet("Organisation");
		Row row = sh.getRow(4);
		String orgName = row.getCell(2).toString() + randomInt;
		String industry = row.getCell(3).toString();
		String type = row.getCell(4).toString();
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
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		System.out.println(orgName);
	
		//selecting industry and type from dropdown
	WebElement industrydrp = driver.findElement(By.name("industry"));
	Select sel1 = new Select(industrydrp);
	sel1.selectByVisibleText(industry);
	
	WebElement typeydrp = driver.findElement(By.name("accounttype"));
	Select sel2 = new Select(typeydrp);
	sel2.selectByVisibleText(type);
	
		//Verifing  industry  is created
		String actuaIndustry= driver.findElement(By.id("mouseArea_Industry")).getText();
		if(actuaIndustry.contains(industry)) {
			System.out.println(industry + "=====Pass=====");
		}
		else {
			System.out.println(industry + "=====Not Created=====");
		}
		
		//verifing type is created
		String actType = driver.findElement(By.id("mouseArea_Type")).getText();
		if(actType.trim().equals(type)) {
			System.out.println(type + "=====Pass=====");
		}
		else {
			System.out.println(type + "=====fail=====");
		}
		
		Actions act= new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();


	}

}
