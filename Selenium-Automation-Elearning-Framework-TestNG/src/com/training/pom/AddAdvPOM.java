package com.training.pom;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

//This POM captures WebElements and Methods related to Delete Advertisement by Admin and confirm deletion by Member 

public class AddAdvPOM {
	private WebDriver driver; 
	
	public AddAdvPOM(WebDriver driver) throws Exception {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
		//Member username textbox
		@FindBy(id="memberUsername")
		private WebElement memUserName; 
		
		//Manage Advertisements button
		@FindBy(xpath="//*[@id=\"tdContents\"]/table[1]/tbody/tr[2]/td/table/tbody/tr[4]/td/fieldset/table/tbody/tr/td[2]/input")
		private WebElement advertisementsBtn; 
		
		//Submit button
		@FindBy(xpath="//input[@value='Submit']")
		private WebElement submit; 	
		
		//Title textbox
		@FindBy(xpath="//input[@name='ad(title)']")
		private WebElement title; 	
		
		//Category dropdown
		@FindBy(name="ad(category)")
		private WebElement category; 	
				
		//Price textbox
		@FindBy(name="ad(price)")
		private WebElement price; 	

		//Not expirable checkbox
		@FindBy(id="notExpirableCheck")
		private WebElement checkBox; 	
		
		//Description textarea
		@FindBy(xpath="//*[@id=\"cke_contents_descriptionText\"]/iframe")
		private WebElement description; 
		
		//Save button
		@FindBy(id="saveButton")
		private WebElement saveButton; 
		
		//Back button
		@FindBy(id="backButton")
		private WebElement backButton; 
		
		//Home menu
		@FindBy(xpath="//span[contains(text(),'Home')]")
		private WebElement home; 
		
		//Method to send Member username
		public void memberUserName (String name) throws InterruptedException {
			this.memUserName.sendKeys(name);
			Thread.sleep(2000);
			if (!"mariyabal".equals(name)) {
				this.memUserName.sendKeys(Keys.ENTER);
			}
		}
		
		//Method to click Submit button of Manage Advertisements 
		public void clickManageAdv() {
			this.advertisementsBtn.click();
		}
	
		//Method to click New Advertisements Submit
		public void clickNewAdv() {
			this.submit.click();
		}
		
		//Method to enter Title
		public void enterTitle(String title) {
			this.title.sendKeys(title);
		}
		
		//Method to select value from Category dropdown
		public void selectCategory (String category) {
			this.category.click();
			Select val = new Select(this.category);
			val.selectByVisibleText(category);
		}

		//Method to enter Price
		public void enterPrice (String price) {
			this.price.sendKeys(price);
		}
		
		//Method to select the checkbox
		public void clickCheckBox () {
			this.checkBox.click();
		}
		
		//Method to enter Comments
		public void enterDescription (String desc) {
			this.description.sendKeys(desc);
		}
		
		//Method to click Save Button
		public void clickSave() {
			this.saveButton.click();
		}
		
		//Method to click ok in confirmation popup
		public String acceptAlert() {
			String message;
			message = this.driver.switchTo().alert().getText();
			this.driver.switchTo().alert().accept();
			return message;
		}
		
		//Method to click Back Button
		public void clickBack() {
			this.backButton.click();
		}
		
		//Method to click Home
		public void clickHome() {
			this.home.click();
		}
}
