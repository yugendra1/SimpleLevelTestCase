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
import com.training.pom.ModProfPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CYTC_009 {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private ModProfPOM modProfPOM;
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
		
		expectedResult = "yugendra";
		actualResult = loginPOM.sendUserName("yugendra");
		loginPOM.sendPassword("test1");
		screenShot.captureScreenShot("Login_4");
		loginPOM.clickLoginBtn(); 
		
		assertEquals(expectedResult,actualResult);
	}
	
	@Test (priority=2)
	public void modifyProfileTest() {
		String expectedResult;
		String actualResult;
		String message;

		modProfPOM = new ModProfPOM(driver); 
		modProfPOM.clickProfile();
		modProfPOM.clearFullName();
		modProfPOM.changeFullName("Krishna Krish");
		screenShot.captureScreenShot("CYTC_009_1");
		modProfPOM.clickSubmit(); 
		expectedResult = "Profile modified";
		message = driver.switchTo().alert().getText();
		actualResult = message.replaceAll("\n","");
		modProfPOM.acceptPopup();
		
		assertEquals(expectedResult,actualResult);
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}
