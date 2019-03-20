package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//This POM captures WebElements and Methods related to viewing Loan repayment by Member 

public class ViewLoanRepayPOM {
	private WebDriver driver; 
	
	public ViewLoanRepayPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//WebElements
	
		//Account menu  
		@FindBy(xpath="//span[@class='menuText'][contains(text(),'Account')]")
		private WebElement account; 
		
		//Loans menu option
		@FindBy(xpath="//span[contains(text(),'Loans')]")
		private WebElement loans; 
			
		//Loans view icon
		@FindBy(xpath="//body[@class='main']//tr//tr[2]//td[4]//img[1]")
		private WebElement viewIcon; 
		
		//Amount text box
		@FindBy(id="amountText")
		private WebElement amount; 	
	
		//Repay button
		@FindBy(xpath="//input[@value='Repay']")
		private WebElement repayBtn; 
		
		//Account Information menu  
		@FindBy(xpath="//span[contains(text(),'Account Information')]")
		private WebElement acctInfo; 
		
		//Repayment amount in Account Information page
		@FindBy(xpath="//*[@id=\"tdContents\"]/table[1]/tbody/tr[3]/td/table/tbody/tr[2]/td[4]")
		private WebElement repayAmt; 
		
	//Methods
		
		//Method to click Account Menu
		public void clickAccount() {
			this.account.click();
		}
		
		//Method to click Loans Menu option
		public void clickLoans() {
			this.loans.click();
		}
		
		//Method to click Loans view icon
		public void clickIcon() {
			this.viewIcon.click();
		}
		
		//Method to enter Amount in the textbox
		public void enterAmount(String amt) {
			this.amount.clear();
			this.amount.sendKeys(amt);
		}
		
		//Method to click Repay button
		public void clickRepay() {
			this.repayBtn.click();
		}
		
		//Method to click OK in alert popup
		public String acceptAlert() {
			String message;
			message = this.driver.switchTo().alert().getText();
			this.driver.switchTo().alert().accept();
			return message;
		}
		
		//Method to click Account Information Menu
		public void clickAcctInfo() {
			this.acctInfo.click();
		}
		
		//Method to validate if the Loan repayment amount is displayed in View Loans
		public String verifyRepayAmount() {
			String repaid;
			repaid = this.repayAmt.getText();
			return repaid;
		}
}
