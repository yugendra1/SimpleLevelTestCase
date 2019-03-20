package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//This POM captures WebElements and Methods related to Delete Advertisement by Admin and confirm deletion by Member 

public class ViewDeletedAdvPOM {
	private WebDriver driver; 
	
	public ViewDeletedAdvPOM(WebDriver driver) throws Exception {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//Admin Login WebElements
	
		//Member username textbox
		@FindBy(id="memberUsername")
		private WebElement memUserName; 
		
		//Manage Advertisements Submit button
		@FindBy(xpath="//tr[4]//td[1]//fieldset[1]//table[1]//tbody[1]//tr[1]//td[2]//input[1]")
		private WebElement submit1; 
		
		//Remove button 
		@FindBy(xpath="//*[@id=\"tdContents\"]/table[1]/tbody/tr[2]/td/table/tbody/tr[2]/td[6]/img[2]")
		private WebElement removeButton; 	
		
		//Logout button
		@FindBy(xpath="//span[contains(text(),'Logout')]")
		private WebElement logout; 
	
	
	//Member Login WebElements
	
		//Personal menu  
		@FindBy(xpath="//span[contains(text(),'Personal')]")
		private WebElement personal; 
		
		//Advertisements menu option
		@FindBy(xpath="//span[contains(text(),'Advertisements')]")
		private WebElement advertisements; 
			
	//Admin Login Methods	
			
		//Method to send Member username
		public void memberUserName (String name) {
			this.memUserName.sendKeys(name);
		}
		
		//Method to click Submit button of Manage Advertisements 
		public void clickManageAdvSubmit() {
			this.submit1.click();
		}
		
		//Method to click Remove button of Advertisement
		public void clickRemoveBtn() {
			this.removeButton.click();
		}
		
		//Method to click Remove button of Advertisement
		public String acceptAlert() {
			String message;
			message = this.driver.switchTo().alert().getText();
			this.driver.switchTo().alert().accept();
			return message;
		}
		
		//Method to click Logout
		public void logOut() {
			this.logout.click();
		}
		
	//Member Login Methods
		
		//Method to click Personal Menu
		public void clickPersonal() {
			this.personal.click();
		}
		
		//Method to click Advertisements Menu option
		public void clickAdvertisements() {
			this.advertisements.click();
		}
		
		//Method to verify the removal of Advertisement
		public boolean verifyDeletion() {
			boolean removed;
			removed = this.removeButton.isDisplayed();
			return removed;
		}
		
}
