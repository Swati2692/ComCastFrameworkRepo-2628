package com.comcast.crm.generic.wedriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class webDriverUtility {
	
	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
	}
	
	public void waitForelementpresent(WebDriver driver, WebElement element) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));

}
	
	public void switchtoWindowByURL(WebDriver driver, String partialUrl) {
		
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it= set.iterator();
		
		while(it.hasNext()) {
			String windowId= it.next();
			driver.switchTo().window(windowId);
			
			String actualurl= driver.getCurrentUrl();
			if(actualurl.contains(partialUrl)) {
				break;
	}
		}
		
		
		
	}
	
	public void switchtoWindowByTitle(WebDriver driver, String title) {
		
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it= set.iterator();
		
		while(it.hasNext()) {
			String windowId= it.next();
			driver.switchTo().window(windowId);
			
			String actualurl= driver.getTitle();
			if(actualurl.contains(title)) {
				
				break;
			}
	}
}
	
	public void switchToframe(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	} 
	
	public void switchToframe(WebDriver driver, String nameID) {
		driver.switchTo().frame(nameID);
	}
	
	public void switchToframe(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}
	
	public void switchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
		
	}
	
	public void switchToAlertAndDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
		
	}
	
	public void selectDropdown(WebElement element, int index) {
		Select sel= new Select(element);
		sel.selectByIndex(index);
	}
	
	public void selectDropdown(WebElement element, String text) {
		Select sel= new Select(element);
		sel.selectByVisibleText(text);
	}
	
	public void selectDropdown( String value, WebElement element) {
		Select sel= new Select(element);
		sel.selectByValue(value);
}
	public void mouseoverAction(WebDriver driver, WebElement element) {
		Actions act= new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	public void doubleclick(WebDriver driver, WebElement element) {
		Actions act= new Actions(driver);
		act.doubleClick(element).perform();
	}
}

