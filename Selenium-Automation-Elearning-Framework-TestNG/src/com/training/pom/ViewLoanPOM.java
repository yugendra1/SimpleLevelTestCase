package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//This POM captures WebElements and Methods related to Loan addition by Admin and view by Member 

public class ViewLoanPOM {
	private WebDriver driver; 
	
	public ViewLoanPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//Admin Login WebElements
	
		//Member username textbox
		@FindBy(id="memberUsername")
		private WebElement memUserName; 
		
		//Grant Loans button
		@FindBy(xpath="//tr[8]//td[1]//fieldset[1]//table[1]//tbody[1]//tr[1]//td[4]//input[1]")
		private WebElement grantLoans; 
		
		//Amount field
		@FindBy(name="loan(amount)")
		private WebElement amount; 	
		
		//Description text
		@FindBy(id="description")
		private WebElement description; 	
				
		//Submit button
		@FindBy(xpath="//input[@value='Submit']")
		private WebElement submit; 
		
		//View Loans button
		@FindBy(xpath="//tr[8]//td[1]//fieldset[1]//table[1]//tbody[1]//tr[1]//td[2]//input[1]")
		private WebElement viewLoans; 
		
		//Description of loan summary page
		@FindBy(xpath="//td[contains(text(),'Mortgage loan')]")
		private WebElement descVal; 
				
		//Logout button
		@FindBy(xpath="//span[contains(text(),'Logout')]")
		private WebElement logout; 
	
	
	//Member Login WebElements
	
		//Account menu  
		@FindBy(xpath="//span[@class='menuText'][contains(text(),'Account')]")
		private WebElement account; 
		
		//Loans menu option
		@FindBy(xpath="//span[contains(text(),'Loans')]")
		private WebElement loans; 
			
		
	//Admin Login Methods	
			
		//Method to send Member username
		public void memberUserName (String name) {
			this.memUserName.sendKeys(name);
		}
		
		//Method to click Submit button of Grant Loans
		public void clickGrantLoans() {
			this.grantLoans.click();
		}
		
		//Method to enter loan amount value
		public void enterAmt(String amt) {
			this.amount.sendKeys(amt);
		}
		
		//Method to enter description of loan
		public void enterDesc(String desc) {
		this.description.sendKeys(desc);
		}
		
		//Method to click Submit button 
		public void clickSubmit() {
			this.submit.click();
		}
		
		//Method to click OK in the confirmation popup after granting loan
		public String acceptAlert() {
			String message;
			message = this.driver.switchTo().alert().getText();
			this.driver.switchTo().alert().accept();
			return message;
		}
		
		//Method to click Submit button of View Loans
		public void clickViewLoans() {
			this.viewLoans.click();
		}
		
		//Method to click Logout
		public void logOut() {
			this.logout.click();
		}
		
	//Member Login Methods
		
		//Method to click Account Menu
		public void clickAccount() {
			this.account.click();
		}
		
		//Method to click Loans Menu option
		public void clickLoans() {
			this.loans.click();
		}
		
		//Method to validate if the granted loan is getting displayed in View Loans
		public boolean verifyGrantedLoan() {
			boolean grant;
			grant = this.descVal.isDisplayed();
			return grant;
		}
}
