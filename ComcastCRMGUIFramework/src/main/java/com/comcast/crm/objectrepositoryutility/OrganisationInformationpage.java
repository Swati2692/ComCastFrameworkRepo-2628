package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganisationInformationpage {
	
	WebDriver driver;
	public OrganisationInformationpage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement orgconfirmmsg;
	
	@FindBy(id="mouseArea_Organization Name")
	private WebElement orgconfirm;
	
	public WebElement getOrgconfirmmsg() {
		return orgconfirmmsg;
	}

	public WebElement getOrgconfirm() {
		return orgconfirm;
	}

}
