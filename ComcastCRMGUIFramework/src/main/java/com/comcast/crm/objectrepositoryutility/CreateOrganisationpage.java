package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrganisationpage {
	WebDriver driver;
	public CreateOrganisationpage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement createorgbtn;
	
	@FindBy(name="accountname")
	private WebElement orgnameedit;
	
	@FindBy(xpath="//input[@class='crmbutton small save']")
	private WebElement savebtnedit;
	public WebElement getCreateorgbtn() {
		return createorgbtn;
	}

	public WebElement getOrgnameedit() {
		return orgnameedit;
	}

	public WebElement getSavebtnedit() {
		return savebtnedit;
	}
	
	

}
