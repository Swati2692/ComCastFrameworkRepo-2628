package com.comcast.crm.generic.wedriverutility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;



public class staticUtility {
	
	public static ThreadLocal<WebDriver> driver= new ThreadLocal<WebDriver>();
	public static ThreadLocal<ExtentTest> test= new ThreadLocal<ExtentTest>();
	
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	public static void  setDriver(WebDriver actDriver) {
		 driver.set(actDriver);
	}

	public static ExtentTest getText() {
		return test.get();
	}
	public static void  setText(ExtentTest actTest) {
		 test.set(actTest);
	}

}
