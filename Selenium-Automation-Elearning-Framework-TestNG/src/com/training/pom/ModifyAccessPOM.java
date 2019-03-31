package com.training.pom;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

//This POM captures WebElements and Methods related to Delete Advertisement by Admin and confirm deletion by Member 

public class ModifyAccessPOM {
	private WebDriver driver; 
	
	public ModifyAccessPOM(WebDriver driver) throws Exception {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
		//Member username textbox
		@FindBy(id="memberUsername")
		private WebElement memUserName; 
		
		//Change Permission Group button
		@FindBy(xpath="//*[@id=\"tdContents\"]/table[1]/tbody/tr[2]/td/table/tbody/tr[2]/td/fieldset/table/tbody/tr[2]/td[2]/input")
		private WebElement permissionBtn; 
		
		//New Group dropdown 
		@FindBy(xpath="//select[@name='newGroupId']")
		private WebElement newGroup; 	
		
		//Comments textbox
		@FindBy(id="comments")
		private WebElement comments; 	
		
		//Submit button
		@FindBy(xpath="//input[@value='Submit']")
		private WebElement submit; 
		
		//Home menu
		@FindBy(xpath="//span[contains(text(),'Home')]")
		private WebElement home; 
		
			//Logout button
			//@FindBy(xpath="//span[contains(text(),'Logout')]")
			//private WebElement logout; 
	
		
		//Method to send Member username
		public void memberUserName (String name) throws InterruptedException {
			this.memUserName.sendKeys(name);
			Thread.sleep(2000);
			if (!"mariyabal".equals(name)) {
				this.memUserName.sendKeys(Keys.ENTER);
			}
		}
		
		//Method to click Submit button of Change Permission Group 
		public void clickChangePermissionSubmit() {
			this.permissionBtn.click();
		}
		
		//Method to select value from New Group dropdown
		public void selectNewGroup (String access) {
			this.newGroup.click();
			Select val = new Select(this.newGroup);
			val.selectByVisibleText(access);
		}
		
		//Method to enter Comments
		public void enterComment (String comment) {
			this.comments.sendKeys(comment);
		}
		
		//Method to click Submit Button
		public void clickSubmit() {
			this.submit.click();
		}
		
		//Method to click ok in confirmation popup
		public String acceptAlert() {
			String message;
			message = this.driver.switchTo().alert().getText();
			this.driver.switchTo().alert().accept();
			return message;
		}
		
		//Method to click Home
		public void clickHome() {
			this.home.click();
		}
		
}
