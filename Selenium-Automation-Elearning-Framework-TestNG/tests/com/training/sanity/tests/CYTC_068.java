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
import com.training.pom.AddAdvPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CYTC_068 {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private AddAdvPOM addAdvPOM;
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
		screenShot.captureScreenShot("Login_13_1");
		//click Login
		loginPOM.clickLoginBtn(); 
		
		//assert validation for valid username
		assertEquals(expectedResult,actualResult);
	}
		
	//Calling Data provider class to read access data from Excel
	@Test(priority=2, dataProvider = "adv-inputs", dataProviderClass = AccessDataProviders.class)
	public void advertisementTest(String userName, String title, String category, String price, String description) throws Exception {
		
		String expectedResult;
		String actualResult;
		
		//Passing driver to AddAdv POM from Login POM
		addAdvPOM = new AddAdvPOM(driver); 
		//Enter Member username
		addAdvPOM.memberUserName(userName);
		//Click Manage Advertisement button
		addAdvPOM.clickManageAdv();
		//Click New Advertisement button
		addAdvPOM.clickNewAdv();
		//Enter Title in textbox
		addAdvPOM.enterTitle(title);
		//Select Category from dropdown
		addAdvPOM.selectCategory(category);
		//Enter Price in textbox
		addAdvPOM.enterPrice(price);
		//Click Not expirable checkbox
		addAdvPOM.clickCheckBox();
		//Enter Description in text area
		addAdvPOM.enterDescription(description);
		screenShot.captureScreenShot("CYTC_068_1");
		//Submit the details
		addAdvPOM.clickSave();
		//Alert popup expected value
		expectedResult = "Advertisement inserted";
		//Alert popup actual value
		actualResult = addAdvPOM.acceptAlert();
		//Click Back button
		addAdvPOM.clickBack();		
		screenShot.captureScreenShot("CYTC_068_2");
		addAdvPOM.clickHome();
		
		//assert validation to compare expected and actual alert popup text
		assertEquals(expectedResult,actualResult);
	}

	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

}