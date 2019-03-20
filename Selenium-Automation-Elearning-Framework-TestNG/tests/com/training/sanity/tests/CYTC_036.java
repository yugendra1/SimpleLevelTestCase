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
import com.training.pom.PaymentPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

// This test is to send Payment to a member and verify if it reflects in Account Information page

public class CYTC_036 {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;			
	private PaymentPOM paymentPOM;
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
		screenShot.captureScreenShot("Login_6");
		//click Login
		loginPOM.clickLoginBtn(); 
		
		//assert validation for valid username
		assertEquals(expectedResult,actualResult);
	}
	
	@Test (priority=2)
	public void memberPaymentTest() throws InterruptedException {
		String expectedResult1;
		String actualResult1;
		String expectedResult2;
		String actualResult2;
		String expectedResult3;
		String actualResult3;
		String expectedResult4;
		String actualResult4;
		
		//Passing driver to Payment POM from Login POM
		paymentPOM = new PaymentPOM(driver); 
		//Navigate to Member Payment page
		paymentPOM.clickMemberPayment();
		//Send recipient name to POM
		paymentPOM.recipientName("saurav");
		Thread.sleep(2000); 				//sleep to allow system to populate 2 others fields dynamically 
		//Send amount value to POM
		paymentPOM.amountVal("9");
		//Send description to POM
		paymentPOM.descriptionText("Small test transfer");
		screenShot.captureScreenShot("CYTC_036_1");
		//Submit the details
		paymentPOM.clickSubmit(); 
		screenShot.captureScreenShot("CYTC_036_2");
		//Submit again in the confirmation page
		paymentPOM.submitConfirm(); 
		screenShot.captureScreenShot("CYTC_036_3");		
		//Navigate to Account Information to verify if the transfer made is reflecting 
		paymentPOM.accountInformation();
		screenShot.captureScreenShot("CYTC_036_4");
		
		//assert to verify the date of the transfer is valid from the last transfer made
		expectedResult1 = "14/03/2019";
		actualResult1 = paymentPOM.dateVal();
		assertEquals(expectedResult1,actualResult1);
		
		//assert to verify the recipient of the transfer is valid from the last transfer made
		expectedResult2 = "saurav";
		actualResult2 = paymentPOM.fromAccVal();
		assertEquals(expectedResult2,actualResult2);
		
		//assert to verify the description of the transfer is valid from the last transfer made
		expectedResult3 = "Small test transfer";
		actualResult3 = paymentPOM.descVal();
		assertEquals(expectedResult3,actualResult3);
		
		//assert to verify the amount of the transfer is valid from the last transfer made
		expectedResult4 = "-0,09";
		actualResult4 = paymentPOM.amtVal();
		assertEquals(expectedResult4,actualResult4);
	
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}
