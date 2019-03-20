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
import com.training.pom.ViewDeletedAdvPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

// This test is to verify if the advertisement removed by Admin is viewable by Member or not

public class CYTC_037 {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;			
	private ViewDeletedAdvPOM viewDeletedAdvPOM;
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
		screenShot.captureScreenShot("Login_7_1");
		//click Login
		loginPOM.clickLoginBtn(); 
		
		//assert validation for valid username
		assertEquals(expectedResult,actualResult);
	}
	
	//Delete Advertisement of a Member as an Admin user
	@Test (priority=2)
	public void deleteAdvertisementTest() throws Exception {
		String expectedResult;
		String actualResult;
				
		//Passing driver to ViewDeletedAdv POM from Login POM
		viewDeletedAdvPOM = new ViewDeletedAdvPOM(driver); 
		//Send user name to POM
		viewDeletedAdvPOM.memberUserName("yugendra");
		Thread.sleep(2000); 				//sleep to allow system to populate another field dynamically 
		screenShot.captureScreenShot("CYTC_037_1");
		//Click Submit button of Manage Advertisements
		viewDeletedAdvPOM.clickManageAdvSubmit(); 
		screenShot.captureScreenShot("CYTC_037_2");
		//Click Remove button of Advertisement
		viewDeletedAdvPOM.clickRemoveBtn(); 
		//Click OK in confirmation for delete popup
		viewDeletedAdvPOM.acceptAlert();
		//Click OK in confirmation popup after deletion
		actualResult = viewDeletedAdvPOM.acceptAlert();
		screenShot.captureScreenShot("CYTC_037_3");
		//Click logout  
		viewDeletedAdvPOM.logOut();
		viewDeletedAdvPOM.acceptAlert();
		
		//assert to verify if the popup message is same as expected
		expectedResult = "Advertisement removed";
		
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
		screenShot.captureScreenShot("Login_7_2");
		//click Login
		loginPOM.clickLoginBtn(); 
		
		//assert validation for valid username
		assertEquals(expectedResult,actualResult);
	}
	
	//Verify the removal of advertisement as a Member
	@Test (priority=4)
	public void viewdeletedAdvertisementTest() {
		boolean expectedResult;
		boolean actualResult;
				
		//Passing driver back to ViewDeletedAdv POM from Login POM
		try {
			viewDeletedAdvPOM = new ViewDeletedAdvPOM(driver);
			//Click Personal Menu
			viewDeletedAdvPOM.clickPersonal();
			//Click Advertisements Menu option
			viewDeletedAdvPOM.clickAdvertisements();
			screenShot.captureScreenShot("CYTC_037_4");
			//Returns a boolean confirming if remove button is present or not
			actualResult = viewDeletedAdvPOM.verifyDeletion();
			//Expected is that the remove button is not present (or) in other words there are no advertisements
			expectedResult = false;
			//assert validation to check both are false
			assertEquals(expectedResult, actualResult);
		}
		catch (Exception e) {
			System.out.println("Remove button is not present as expected");
		} 
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}
