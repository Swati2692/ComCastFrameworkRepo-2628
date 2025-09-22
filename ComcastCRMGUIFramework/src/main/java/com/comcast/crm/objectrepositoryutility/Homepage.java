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
	
<<<<<<< HEAD
	@FindBy(linkText="Project")
	private WebElement Project;
	
=======
	//code
	public WebElement getProjectedit() {
		return projectedit;
	}

	public void setProjectedit(WebElement projectedit) {
		this.projectedit = projectedit;
	}

	public WebDriver getDriver() {
		return driver;
	}


	@FindBy(linkText="project")
	private WebElement projectedit;

>>>>>>> branch 'main' of https://github.com/Swati2692/ComCastFrameworkRepo-2628.git
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
