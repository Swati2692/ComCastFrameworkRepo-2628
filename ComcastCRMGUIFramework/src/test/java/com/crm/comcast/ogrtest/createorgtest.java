package com.crm.comcast.ogrtest;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.Excelutility;
import com.comcast.crm.generic.fileutility.PropertiesFileutility;
import com.comcast.crm.generic.wedriverutility.JavaUtility;
import com.comcast.crm.generic.wedriverutility.webDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreateOrganisationpage;
import com.comcast.crm.objectrepositoryutility.Homepage;
import com.comcast.crm.objectrepositoryutility.Loginpage;
import com.comcast.crm.objectrepositoryutility.OrganisationInformationpage;
import com.crm.genericbaseutility.BaseClass;

import junit.framework.Assert;

public class createorgtest extends BaseClass {

	@Test(groups = "Smoke Testing")
	public void createOrganisation() throws InterruptedException, IOException {

		PropertiesFileutility putil = new PropertiesFileutility();
		Excelutility eutil = new Excelutility();
		JavaUtility jutil = new JavaUtility();
		webDriverUtility wutil = new webDriverUtility();

		// generating random number
		Random random = new Random();
		int randomInt = random.nextInt(500);

		// reading common data from properties file
		String Browser = putil.getDataFromPropertiesFile("browser");
		String URL = putil.getDataFromPropertiesFile("url");
		String Username = putil.getDataFromPropertiesFile("username");
		String Password = putil.getDataFromPropertiesFile("password");

		int ranno = jutil.getRandomNo();

		// reading data from excel file
		String orgName = eutil.getDataFromExcel("Organisation", 1, 2) + ranno;

		WebDriver driver;

		if (Browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equals("firefox")) {

			driver = new FirefoxDriver();
		} else if (Browser.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		Loginpage lp = new Loginpage(driver);
		Homepage hp = new Homepage(driver);
		CreateOrganisationpage co = new CreateOrganisationpage(driver);
		OrganisationInformationpage oi = new OrganisationInformationpage(driver);

		// reading data from properties and excel file
		driver.get(URL);
		lp.loginToApp("admin", "admin", orgName);
		hp.getOrgedit().click();
		co.getCreateorgbtn().click();
		co.getOrgnameedit().sendKeys(orgName);
		co.getSavebtnedit().click();
		System.out.println(orgName);

		// Verifing org is created
		String actualorg = oi.getOrgconfirmmsg().getText();
		if (actualorg.contains(orgName)) {
			System.out.println(orgName + "=====Created=====");
		} else {
			System.out.println(orgName + "=====Not Created=====");
		}

		// verify with org name in orgtextfield
		String actorg = oi.getOrgconfirm().getText();
		if (actorg.trim().equals(orgName)) {
			System.out.println(orgName + "=====Pass=====");
		} else {
			System.out.println(orgName + "=====fail=====");
		}

		Actions act = new Actions(driver);
		act.moveToElement(hp.getImgedit()).perform();
		Thread.sleep(2000);

		hp.getSignoutedit().click();

		driver.quit();

	}

	@Test(groups = "Regression Testing")
	public void createOrgWithIndustry() throws IOException, InterruptedException {
		PropertiesFileutility putil = new PropertiesFileutility();
		Excelutility eutil = new Excelutility();
		JavaUtility jutil = new JavaUtility();
		webDriverUtility wutil = new webDriverUtility();

		// generating random number
		Random random = new Random();
		int randomInt = random.nextInt(500);

		// reading common data from properties file
		FileInputStream fis1 = new FileInputStream("./configAppData/commondata.properties");
		Properties prop = new Properties();
		prop.load(fis1);
		String Browser = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String Username = prop.getProperty("username");
		String Password = prop.getProperty("password");

		// reading data from excel file
		FileInputStream fis2 = new FileInputStream("./testData/testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis2);
		Sheet sh = wb.getSheet("Organisation");
		Row row = sh.getRow(4);
		String orgName = row.getCell(2).toString() + randomInt;
		String industry = row.getCell(3).toString();
		String type = row.getCell(4).toString();
		wb.close();

		WebDriver driver;

		if (Browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equals("firefox")) {

			driver = new FirefoxDriver();
		} else if (Browser.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		// reading data from properties and excel file
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(Username);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		System.out.println(orgName);

		// selecting industry and type from dropdown
		WebElement industrydrp = driver.findElement(By.name("industry"));
		Select sel1 = new Select(industrydrp);
		sel1.selectByVisibleText(industry);

		WebElement typeydrp = driver.findElement(By.name("accounttype"));
		Select sel2 = new Select(typeydrp);
		sel2.selectByVisibleText(type);

		// Verifing industry is created
		String actuaIndustry = driver.findElement(By.id("mouseArea_Industry")).getText();
		if (actuaIndustry.contains(industry)) {
			System.out.println(industry + "=====Pass=====");
		} else {
			System.out.println(industry + "=====Not Created=====");
		}

		// verifing type is created
		String actType = driver.findElement(By.id("mouseArea_Type")).getText();
		if (actType.trim().equals(type)) {
			System.out.println(type + "=====Pass=====");
		} else {
			System.out.println(type + "=====fail=====");
		}

		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();

	}

	@Test(groups = "Regression Testing")
	public void createorgwithphoneno() throws IOException, InterruptedException {
		PropertiesFileutility putil = new PropertiesFileutility();
		Excelutility eutil = new Excelutility();
		JavaUtility jutil = new JavaUtility();
		webDriverUtility wutil = new webDriverUtility();

		// generating random number
		Random random = new Random();
		int randomInt = random.nextInt(500);

		// reading common data from properties file
		FileInputStream fis1 = new FileInputStream("./configAppData/commondata.properties");
		Properties prop = new Properties();
		prop.load(fis1);
		String Browser = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String Username = prop.getProperty("username");
		String Password = prop.getProperty("password");

		// reading data from excel file
		FileInputStream fis2 = new FileInputStream("./testData/testdata.xlsx");
				
		Workbook wb = WorkbookFactory.create(fis2);
		Sheet sh = wb.getSheet("Organisation");
		Row row = sh.getRow(7);
		String orgName = row.getCell(2).toString() + randomInt;
		String phoneNo = row.getCell(3).toString();
		wb.close();

		WebDriver driver;

		if (Browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equals("firefox")) {

			driver = new FirefoxDriver();
		} else if (Browser.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		// reading data from properties and excel file
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

		// Verifing org is created
		String actualorg = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		Assert.assertEquals(actualorg, "orgg");
		Reporter.log("==pass", true);

		// verify with phoneNo.
		String actphone = driver.findElement(By.id("dtlview_Phone")).getText();
		if (actphone.trim().equals(phoneNo)) {
			System.out.println(phoneNo + "=====Pass=====");
		} else {
			System.out.println(phoneNo + "=====fail=====");
		}

		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();

	}
}
