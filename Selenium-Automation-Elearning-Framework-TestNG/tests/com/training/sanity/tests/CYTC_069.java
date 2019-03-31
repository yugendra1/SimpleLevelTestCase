package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.CreateAdvCatPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

// This test is to verify if the loan granted by Admin is viewable by Member

public class CYTC_069 {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;			
	private CreateAdvCatPOM createAdvCatPOM;
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
		screenShot.captureScreenShot("Login_14_1");
		//click Login
		loginPOM.clickLoginBtn(); 
		
		//assert validation for valid username
		assertEquals(expectedResult,actualResult);
	}
	
	//Admin creates new category and adds an advertisement for newly created category
	@Test (priority=2)
	public void createCategoryAdvTest() throws Exception {
		String expectedResult;
		String actualResult;
				
		//Passing driver to CreateAdvCat POM from Login POM
		createAdvCatPOM = new CreateAdvCatPOM(driver); 
		//Click on Advertisements Menu
		createAdvCatPOM.clickAdvMenu();
		//Click on Manage Categories Sub-menu
		createAdvCatPOM.clickManageCat();
		//Click on Insert new category
		createAdvCatPOM.clickInsertNew();
		//Enter new Category name
		createAdvCatPOM.enterName("Special Offer Yugi");
		screenShot.captureScreenShot("CYTC_069_1");
		//Click Submit
		createAdvCatPOM.clickSave();
		//Click OK in confirmation Popup
		createAdvCatPOM.acceptAlert();
		//Click Home
		createAdvCatPOM.clickHome();
		
		//Enter username of Member
		createAdvCatPOM.memberUserName("yugendra");
		//Click Manage Advertisement button
		createAdvCatPOM.clickManageAdv();
		//Click New Advertisement button
		createAdvCatPOM.clickNewAdv();
		//Enter Title in textbox
		createAdvCatPOM.enterTitle("New Offer Yugi");
		//Select Category from dropdown
		createAdvCatPOM.selectCategory("Special Offer Yugi");
		//Enter Price in textbox
		createAdvCatPOM.enterPrice("7");
		//Click Not expirable checkbox
		createAdvCatPOM.clickCheckBox();
		//Enter Description in text area
		createAdvCatPOM.enterDescription("new offer for member");
		screenShot.captureScreenShot("CYTC_069_2");
		//Submit the details
		createAdvCatPOM.clickSave();
		//Alert popup expected value
		expectedResult = "Advertisement inserted";
		//Alert popup actual value
		actualResult = createAdvCatPOM.acceptAlert();
		//Click Back button
		createAdvCatPOM.clickBack();		
		screenShot.captureScreenShot("CYTC_069_3");
		//Click Logout
		createAdvCatPOM.clickLogout();
		createAdvCatPOM.acceptAlert();
		
		//assert validation to compare expected and actual alert popup text
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
		screenShot.captureScreenShot("Login_14_2");
		//click Login
		loginPOM.clickLoginBtn(); 
		
		//assert validation for valid username
		assertEquals(expectedResult,actualResult);
	}
	
	//Verify the message sent by Admin as a Member
	@Test (priority=4)
	public void viewAdvertisementTest() throws Exception {
		String expectedResult;
		String actualResult;
				
		//Passing driver back to ViewMessage POM from Login POM
			createAdvCatPOM = new CreateAdvCatPOM(driver);
			//Click Personal Menu
			createAdvCatPOM.clickPersonal();
			//Click Advertisements Sub-menu option
			createAdvCatPOM.clickAdv();
			screenShot.captureScreenShot("CYTC_069_4");
			//Returns Title of last added Advertisement 
			actualResult = createAdvCatPOM.verifyAdvTitle();
			expectedResult = "New Offer Yugi";
			
			//assert validation to check if the Body text matches
			assertEquals(expectedResult, actualResult);
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}
