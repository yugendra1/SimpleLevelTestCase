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
import com.training.pom.ChgPwdPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CYTC_010 {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private ChgPwdPOM chgPwdPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	
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
	
	@Test (priority=1)
	public void validLoginTest() {
		String expectedResult;
		String actualResult;
		
		expectedResult = "admin";
		actualResult = loginPOM.sendUserName("admin");
		loginPOM.sendPassword("123456");
		screenShot.captureScreenShot("Login_5");
		loginPOM.clickLoginBtn(); 
		
		assertEquals(expectedResult,actualResult);
	}
	
	@Test (priority=2)
	public void changePasswordTest() {
		String expectedResult;
		String actualResult;
		
		chgPwdPOM = new ChgPwdPOM(driver); 
		chgPwdPOM.sendCurrentPwdAdmin("123456");
		chgPwdPOM.sendNewPwd("12345");
		chgPwdPOM.sendNewPwdConfirm("12345");
		screenShot.captureScreenShot("CYTC_010_1");
		chgPwdPOM.clickSubmit(); 
		expectedResult = "The password was modified";
		actualResult = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		
		assertEquals(expectedResult,actualResult);
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}
