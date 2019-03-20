package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.ViewLoanRepayPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

// This test is to verify if the loan repayment is done and viewable by Member

public class CYTC_039 {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;			
	private ViewLoanRepayPOM viewLoanRepayPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	//Driver Factory setup
	@BeforeClass
	public void setUp() throws Exception {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	//Calling Login POM to log into the application 
	@Test (priority=1)
	public void validLoginTest() {
		String expectedResult;
		String actualResult;
		
		//expected result to verify valid username 
		expectedResult = "yugendra";
		//actual result returned from Login POM method for username
		actualResult = loginPOM.sendUserName("yugendra");
		//send password
		loginPOM.sendPassword("test1");
		screenShot.captureScreenShot("Login_9_1");
		//click Login
		loginPOM.clickLoginBtn(); 
		
		//assert validation for valid username
		assertEquals(expectedResult,actualResult);
	}
	
	//Loan repayment by Member
	@Test (priority=2)
	public void repayLoanTest() {
		String expectedResult;
		String actualResult;
				
		//Passing driver to ViewLoanRepay POM from Login POM
		viewLoanRepayPOM = new ViewLoanRepayPOM(driver); 
		//Click Account Menu
		viewLoanRepayPOM.clickAccount(); 
		//Click Loans Menu option
		viewLoanRepayPOM.clickLoans(); 
		screenShot.captureScreenShot("CYTC_039_1");
		//Click View icon of Loan
		viewLoanRepayPOM.clickIcon(); 
		//Enter repayment amount in Amount textbox
		viewLoanRepayPOM.enterAmount("200,00"); 
		screenShot.captureScreenShot("CYTC_039_2");
		//Click Repay button
		viewLoanRepayPOM.clickRepay(); 
		//Click OK in 2 confirmation popups back to back
		viewLoanRepayPOM.acceptAlert();
		actualResult = viewLoanRepayPOM.acceptAlert();
		screenShot.captureScreenShot("CYTC_039_3");
			
		//assert to verify if the popup message is same as expected
		expectedResult = "The repayment was succesfully processed";
		
		assertEquals(expectedResult,actualResult);
	}
	
	//Verify the addition of loan as a Member
	@Test (priority=3)
	public void viewRepaidLoanTest() {
		String expectedResult;
		String actualResult;
				
			//Click Account Information option
			viewLoanRepayPOM.clickAcctInfo(); 
			screenShot.captureScreenShot("CYTC_039_4");
			//Returns repayment Amount in Account Information page
			actualResult = viewLoanRepayPOM.verifyRepayAmount();
			//Expected is that the loan description is present in the page
			expectedResult = "-200,00";
			
			//assert validation to check both are true
			assertEquals(expectedResult, actualResult);
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}
