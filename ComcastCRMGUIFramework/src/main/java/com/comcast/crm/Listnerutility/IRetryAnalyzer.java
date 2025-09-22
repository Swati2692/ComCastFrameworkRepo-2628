package com.comcast.crm.Listnerutility;

import org.testng.ITestResult;

public class IRetryAnalyzer implements org.testng.IRetryAnalyzer{
	int count=0;
	int limitcount= 5;

	@Override
	public boolean retry(ITestResult result) {
		if(count<limitcount) {
			count++;
			return true;
		}
		return false;
	}

}
