package com.comcast.crm.Listnerutility;

import java.io.File;
import java.sql.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.crm.genericbaseutility.BaseClass;


public class ListImpClass implements ITestListener, ISuiteListener{


	
	public ExtentSparkReporter spark;
	public  ExtentReports report;
	ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report Config");
		ExtentSparkReporter spark= new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM Application");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		//step2: create an object of Extentreports and specify env. info and create test
		
		report= new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows 10");
		report.setSystemInfo("Browser", "Chrome");
			
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report Backup");
		report.flush();
			
	}
  
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("==="+result.getMethod().getMethodName()+"===Started====");
		test= report.createTest(result.getMethod().getMethodName());
		com.comcast.crm.generic.wedriverutility.staticUtility.setText(test);
		test.log(Status.INFO, result.getMethod().getMethodName()+"===Started====" );
		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		TakesScreenshot edriver= (TakesScreenshot)BaseClass.sdriver;
		String filepath=edriver.getScreenshotAs(OutputType.BASE64);
		String time= new Date(0).toString().replace(" ", "_").replace(":", "_");
		test.addScreenCaptureFromBase64String(filepath, testname+"_"+time);
		test.log(Status.FAIL, result.getMethod().getMethodName()+"===failed====" );
		
	}


	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.PASS, result.getMethod().getMethodName()+"===Passed====" );
	}	

}




