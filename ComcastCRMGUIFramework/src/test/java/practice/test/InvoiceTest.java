package practice.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import com.crm.genericbaseutility.BaseClass;

@Listeners(com.comcast.crm.Listnerutility.ListImpClass.class)

public class InvoiceTest extends BaseClass {
	
	WebDriver driver= new ChromeDriver();
	
	
	@Test
	
	public void createInvoiceTest() {
		
		Reporter.log("Execute createInvoiceTest ", true);
		String actTitle=driver.getTitle();
		Assert.assertEquals(actTitle, actTitle);
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}
	
	@Test
	public void createInvoiceithContactTest() {
		Reporter.log("Execute createInvoiceithContactTest ",true);
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
		
	}

}
