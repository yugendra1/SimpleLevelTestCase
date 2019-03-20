package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//This POM captures WebElements and Methods related to Payment transfer between Members

public class PaymentPOM {
	private WebDriver driver; 
	
	public PaymentPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//Account menu
	@FindBy(xpath="//span[@class='menuText'][contains(text(),'Account')]")
	private WebElement account; 
	
	//Member Payment menu option
	@FindBy(xpath="//span[contains(text(),'Member Payment')]")
	private WebElement memPayment;
	
	//Member username textbox
	@FindBy(id="memberUsername")
	private WebElement memUserName; 
	
	//amount textbox
	@FindBy(id="amount")
	private WebElement amount; 
	
	//description textbox
	@FindBy(id="description")
	private WebElement description; 	
	
	//submit button
	@FindBy(id="submitButton")
	private WebElement submit; 
	
	//submit button from confirmation page 
	@FindBy(xpath="//input[@value='Submit']")
	private WebElement submitConf; 
	
	//Account Information menu
	@FindBy(xpath="//span[contains(text(),'Account Information')]")
	private WebElement acctInfo; 
	
	//Date field from Account Information page
	@FindBy(xpath="//*[@id=\"tdContents\"]/table[1]/tbody/tr[3]/td/table/tbody/tr[2]/td[1]")
	private WebElement dateVerify; 
	
	//From/To field from Account Information page
	@FindBy(xpath="//*[@id=\"tdContents\"]/table[1]/tbody/tr[3]/td/table/tbody/tr[2]/td[2]")
	private WebElement fromAccVerify; 
	
	//Description field from Account Information page
	@FindBy(xpath="//*[@id=\"tdContents\"]/table[1]/tbody/tr[3]/td/table/tbody/tr[2]/td[3]")
	private WebElement descVerify; 
	
	//Amount field from Account Information page
	@FindBy(xpath="//*[@id=\"tdContents\"]/table[1]/tbody/tr[3]/td/table/tbody/tr[2]/td[4]")
	private WebElement amtVerify; 
	
	//Click Member Payment option from Account menu
	public void clickMemberPayment () {
		this.account.click();
		this.memPayment.click();
	}
	
	//Method to send Recipient name 
	public void recipientName(String name) {
		this.memUserName.sendKeys(name);
	}
	
	//Method to send Amount value 
	public void amountVal(String amt) {
		this.amount.sendKeys(amt);
	}
	
	//Method to send Description
	public void descriptionText(String desc) {
		this.description.sendKeys(desc); 
	}
	
	//Method to click Submit button
	public void clickSubmit() {
		this.submit.click();
	}
	
	//Method to click Submit button from confirmation page
	public void submitConfirm() {
		this.submitConf.click();
	}
	
	//Method to click Account Information menu
	public void accountInformation() {
		this.acctInfo.click();
	}
	
	//Method to return date from Account Information page
	public String dateVal() {
		return dateVerify.getText();
	}
	
	//Method to return From/To from Account Information page
	public String fromAccVal() {
		return fromAccVerify.getText();
	}
	
	//Method to return Description from Account Information page
	public String descVal() {
		return descVerify.getText();
	}
	
	//Method to return amount from Account Information page
	public String amtVal() {
		return amtVerify.getText();
	}
}
