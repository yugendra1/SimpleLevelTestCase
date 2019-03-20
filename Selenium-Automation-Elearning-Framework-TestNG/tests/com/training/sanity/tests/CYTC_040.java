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
import com.training.pom.ViewMessagePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

// This test is to verify if the loan granted by Admin is viewable by Member

public class CYTC_040 {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;			
	private ViewMessagePOM viewMessagePOM;
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
		screenShot.captureScreenShot("Login_10_1");
		//click Login
		loginPOM.clickLoginBtn(); 
		
		//assert validation for valid username
		assertEquals(expectedResult,actualResult);
	}
	
	//Send Message to a Member as an Admin user
	@Test (priority=2)
	public void sendMessageTest() throws Exception {
		String expectedResult;
		String actualResult;
				
		//Passing driver to ViewLoan POM from Login POM
		viewMessagePOM = new ViewMessagePOM(driver); 
		//Click on Messages
		viewMessagePOM.clickMessagesMenu();
		viewMessagePOM.clickMsgsSubMenuAdmin();
		//Click on Submit to send new Message
		viewMessagePOM.clickSubmit();
		//Enter username of Member
		viewMessagePOM.memberUserName("yugendra");
		Thread.sleep(2000); 				//sleep to allow system to populate another field dynamically 
		//Select Category, enter text in Subject and Body
		viewMessagePOM.selectCategory("Loan request");
		viewMessagePOM.enterSubjectText("CYTC_040_Testrun");
		viewMessagePOM.enterBodyText("welcome");
		screenShot.captureScreenShot("CYTC_040_1");
		//Click Submit button 
		viewMessagePOM.clickSubmit(); 
		//Click OK in confirmation popup
		actualResult = viewMessagePOM.acceptAlert();
		screenShot.captureScreenShot("CYTC_040_2");
		//Logout
		viewMessagePOM.logOut();
		viewMessagePOM.acceptAlert();
		
		//assert to verify if the popup message is same as expected
		expectedResult = "The message was successfully sent";
		
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
		screenShot.captureScreenShot("Login_10_2");
		//click Login
		loginPOM.clickLoginBtn(); 
		
		//assert validation for valid username
		assertEquals(expectedResult,actualResult);
	}
	
	//Verify the message sent by Admin as a Member
	@Test (priority=4)
	public void viewMessageTest() {
		String expectedResult;
		String actualResult;
				
		//Passing driver back to ViewMessage POM from Login POM
			viewMessagePOM = new ViewMessagePOM(driver);
			//Click Personal Menu
			viewMessagePOM.clickPersonal();
			//Click Messages Submenu option
			viewMessagePOM.clickMsgsSubMenuMember();
			//Click on Message Title
			viewMessagePOM.clickMessageTitle();
			screenShot.captureScreenShot("CYTC_040_3");
			//Returns Body text of Message 
			actualResult = viewMessagePOM.verifyBodyText();
			expectedResult = "welcome";
			
			//assert validation to check if the Body text matches
			assertEquals(expectedResult, actualResult);
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}
