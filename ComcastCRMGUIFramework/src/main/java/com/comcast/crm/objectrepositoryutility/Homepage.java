package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
	
	WebDriver driver;
	public Homepage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	

	public WebElement getProjectedit() {
		return projectedit;
		
		//code1
		//code2
	}
	//code4
	//code6
	//code7
	public void setProjectedit(WebElement projectedit) {
		this.projectedit = projectedit;
	}

	public WebDriver getDriver() {
		return driver;
	}


	@FindBy(linkText="project")
	private WebElement projectedit;

	@FindBy(linkText="Organizations")
	private WebElement orgedit;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement imgedit;
	
	@FindBy (linkText="Sign Out")
	private WebElement signoutedit;
	public WebElement getOrgedit() {
		return orgedit;
	}

	public WebElement getImgedit() {
		return imgedit;
	}

	public WebElement getSignoutedit() {
		return signoutedit;
	}

}
