package com.comcast.cm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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

public class createContactWithCreatedOrg {

	public static void main(String[] args) throws IOException, InterruptedException {
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
		Sheet sh = wb.getSheet("Contact");
		Row row = sh.getRow(7);
		
		String lastName = row.getCell(2).toString() + randomInt;
		String orgname = row.getCell(3).toString() + randomInt;
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
		
		//1st create orgname
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgname);
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		
		//create contact
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		//switch to child window
	
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it= set.iterator();
		
		while(it.hasNext()) {
			String windowId= it.next();
			driver.switchTo().window(windowId);
			
			String actualurl= driver.getCurrentUrl();
			if(actualurl.contains("module=Accounts&action")) {
				break;
			}
		}
			driver.findElement(By.id("search_txt")).sendKeys(orgname);
			driver.findElement(By.name("search")).click();
			driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
		
			//switch to parent window
	Set<String> set1 = driver.getWindowHandles();
	Iterator<String> it1= set1.iterator();
	while(it1.hasNext()) {
		String windowId= it1.next();
		driver.switchTo().window(windowId);
		String actualurl= driver.getCurrentUrl();
		if(actualurl.contains("module=Contacts&action")) {
			break;
		}
		
	driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		
	
		//Verifing  contact is created with last name
		String actualcontact= driver.findElement(By.id("mouseArea_Last Name")).getText();
		if(actualcontact.contains(lastName)) {
			System.out.println(lastName + "=====Created=====");
		}
		else {
			System.out.println(lastName + "=====Not Created=====");
		}
		
		//verify with org name in orgtextfield
		String actorgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if(actorgname.trim().equals(orgname)) {
			System.out.println(orgname + "=====Pass=====");
		}
		else {
			System.out.println(orgname + "=====fail=====");
		}
		
		Actions act= new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();

	}

}
}