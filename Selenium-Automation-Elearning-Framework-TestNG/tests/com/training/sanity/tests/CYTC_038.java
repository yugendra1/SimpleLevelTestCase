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
import com.training.pom.ViewLoanPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

// This test is to verify if the loan granted by Admin is viewable by Member

public class CYTC_038 {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;			
	private ViewLoanPOM viewLoanPOM;
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
	
	//Calling Login POM to log into the application as Admin
	@Test (priority=1)
	public void validLoginTestAdmin() {
		String expectedResult;
		String actualResult;
		
		//expected result to verify valid username 
		expectedResult = "admin";
		//actual result returned from Login POM method for username
		actualResult = loginPOM.sendUserName("admin");
		//send password
		loginPOM.sendPassword("123456");
		screenShot.captureScreenShot("Login_8_1");
		//click Login
		loginPOM.clickLoginBtn(); 
		
		//assert validation for valid username
		assertEquals(expectedResult,actualResult);
	}
	
	//Grant Loan for a Member as an Admin user
	@Test (priority=2)
	public void grantLoanTest() throws Exception {
		String expectedResult;
		String actualResult;
				
		//Passing driver to ViewLoan POM from Login POM
		viewLoanPOM = new ViewLoanPOM(driver); 
		//Send user name to POM
		viewLoanPOM.memberUserName("yugendra");
		Thread.sleep(2000); 				//sleep to allow system to populate another field dynamically 
		screenShot.captureScreenShot("CYTC_038_1");
		//Click Submit button of Grant Loans
		viewLoanPOM.clickGrantLoans(); 
		//Enter Amount and Description of loan and submit
		viewLoanPOM.enterAmt("500000"); 
		viewLoanPOM.enterDesc("Mortgage loan"); 
		screenShot.captureScreenShot("CYTC_038_2");
		viewLoanPOM.clickSubmit(); 
		//Click Submit in the confirmation page
		screenShot.captureScreenShot("CYTC_038_3");
		viewLoanPOM.clickSubmit(); 
		//Click OK in confirmation popup
		actualResult = viewLoanPOM.acceptAlert();
		//Click View Loans Submit
		viewLoanPOM.clickViewLoans();
		screenShot.captureScreenShot("CYTC_038_4");
		//Validate if granted loan is present in the page
		//Click logout  
		viewLoanPOM.logOut();
		viewLoanPOM.acceptAlert();
		
		//assert to verify if the popup message is same as expected
		expectedResult = "The loan was successfully granted";
		
		assertEquals(expectedResult,actualResult);
	}
	
	//Calling Login POM to log into the application as Member
	@Test (priority=3)
	public void validLoginTestMember() {
		String expectedResult;
		String actualResult;
		
		//expected result to verify valid username 
		expectedResult = "yugendra";
		//actual result returned from Login POM method for username
		actualResult = loginPOM.sendUserName("yugendra");
		//send password
		loginPOM.sendPassword("test1");
		screenShot.captureScreenShot("Login_8_2");
		//click Login
		loginPOM.clickLoginBtn(); 
		
		//assert validation for valid username
		assertEquals(expectedResult,actualResult);
	}
	
	//Verify the addition of loan as a Member
	@Test (priority=4)
	public void viewAddedLoanTest() {
		boolean expectedResult;
		boolean actualResult;
				
		//Passing driver back to ViewDeletedAdv POM from Login POM
			viewLoanPOM = new ViewLoanPOM(driver);
			//Click Account Menu
			viewLoanPOM.clickAccount();
			//Click Loans Menu option
			viewLoanPOM.clickLoans();
			screenShot.captureScreenShot("CYTC_038_5");
			//Returns a boolean confirming if remove button is present or not
			actualResult = viewLoanPOM.verifyGrantedLoan();
			//Expected is that the loan description is present in the page
			expectedResult = true;
			//assert validation to check both are true
			assertEquals(expectedResult, actualResult);
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}
