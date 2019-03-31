package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.training.bean.LoginBean;
import com.training.dao.ELearningDAO;
import com.training.dataproviders.AccessDataProviders;
import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM; 
import com.training.pom.ModifyAccessPOM;
import com.training.pom.ViewMessagePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CYTC_067 {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private ModifyAccessPOM modifyAccessPOM;
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
		screenShot.captureScreenShot("Login_12_1");
		//click Login
		loginPOM.clickLoginBtn(); 
		
		//assert validation for valid username
		assertEquals(expectedResult,actualResult);
	}
		
	//Calling Data provider class to read access data from Database
	@Test(priority=2, dataProvider = "access-inputs", dataProviderClass = AccessDataProviders.class)
	public void accessTest(String userName, String access, String comment) throws Exception {
		
		String expectedResult;
		String actualResult;
		
		//Passing driver to ModifyAccess POM from Login POM
		modifyAccessPOM = new ModifyAccessPOM(driver); 
		//Enter Member username
		modifyAccessPOM.memberUserName(userName);
		//Click Change Permission button
		modifyAccessPOM.clickChangePermissionSubmit();
		//Select New Group access value from dropdown
		modifyAccessPOM.selectNewGroup(access);
		//Enter comments in text area
		modifyAccessPOM.enterComment(comment);
		screenShot.captureScreenShot("CYTC_067_1");
		//Submit the details
		modifyAccessPOM.clickSubmit();
		//Alert popup expected value
		expectedResult = "The member's group was changed";
		//Alert popup actual value
		actualResult = modifyAccessPOM.acceptAlert();
		//Click Home menu
		modifyAccessPOM.clickHome();		
		screenShot.captureScreenShot("CYTC_067_2");
		
		//assert validation to compare expected and actual alert popup text
		assertEquals(expectedResult,actualResult);
	}

	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

}