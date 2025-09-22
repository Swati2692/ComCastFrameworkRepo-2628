package com.crm.genericbaseutility;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;
import org.testng.annotations.Test;


public class takeSSwithtestNg {
	
	@Test
	public void takeSSofwebpage() throws IOException {
		
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.get("https://www.flipkart.com/");
		
		TakesScreenshot tks= (TakesScreenshot) driver;
		File temp = tks.getScreenshotAs(OutputType.FILE);
		File perm= new File("./errorScreenshot/home.jpeg");
		FileHandler.copy(temp, perm);
		Reporter.log("====SS Taken Successfully", true);
		driver.quit();
	}
	@Test
	public void takeSSofwebeklement() throws IOException {
		
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.get("https://www.flipkart.com/");
		
		
		WebElement logo = driver.findElement(By.xpath("//img[@title='Flipkart']"));
		File temp = logo.getScreenshotAs(OutputType.FILE);
		
		File perm= new File("./errorScreenshot/logo.jpeg");
		FileHandler.copy(temp, perm);
		Reporter.log("====SS Taken Successfully====", true);
		driver.quit();
	
		
	}

}
