package com.comcast.crm.objectrepositoryutility;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.PropertiesFileutility;
import com.comcast.crm.generic.wedriverutility.webDriverUtility;

public class Loginpage extends webDriverUtility{
	
	PropertiesFileutility plib =new PropertiesFileutility();
	WebDriver driver;
	public Loginpage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
		

		
		
		
	}
	
	@FindBy(name= "user_name")
	private WebElement usernameedit;
	
	
	@FindBy(name= "user_password")
	private WebElement passwordedit;
	
	@FindBy(id= "submitButton")
	private WebElement loginBtndedit;

	public WebElement getUsernameedit() {
		return usernameedit;
	}

	public WebElement getPasswordedit() {
		return passwordedit;
	}

	public WebElement getLoginBtndedit() {
		return loginBtndedit;
	}
	
	//provide action
	public void loginToApp(String url,String username, String password) throws IOException {
		
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		usernameedit.sendKeys(username);
		passwordedit.sendKeys(password);
		loginBtndedit.click();
		
	}

	

}
