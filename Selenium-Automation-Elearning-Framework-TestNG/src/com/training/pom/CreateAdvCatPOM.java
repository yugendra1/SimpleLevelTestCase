package com.training.pom;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

//This POM captures WebElements and Methods related to Delete Advertisement by Admin and confirm deletion by Member 

public class CreateAdvCatPOM {
	private WebDriver driver; 
	
	public CreateAdvCatPOM(WebDriver driver) throws Exception {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//Admin WebElements
	
		//Advertisements Menu
		@FindBy(xpath="//span[contains(text(),'Advertisements')]")
		private WebElement advMenu; 

		//Manage Categories Sub-menu
		@FindBy(xpath="//span[contains(text(),'Manage Categories')]")
		private WebElement manageCat; 

		//Submit button of New category
		@FindBy(id="newButton")
		private WebElement submitNew; 	

		//Name text area
		@FindBy(name="category(name)")
		private WebElement nameText; 	
		
		//Save button
		@FindBy(id="saveButton")
		private WebElement saveButton; 
		
		//Home menu
		@FindBy(xpath="//span[contains(text(),'Home')]")
		private WebElement home; 
		
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
		
		//Back button
		@FindBy(id="backButton")
		private WebElement backButton; 
		
		//Logout menu
		@FindBy(xpath="//span[contains(text(),'Logout')]")
		private WebElement logout; 
		
	//Admin Methods
		
		//Method to click Advertisements Menu
		public void clickAdvMenu() {
			this.advMenu.click();
		}	
		
		//Method to click Manage Categories Sub-menu
		public void clickManageCat() {
			this.manageCat.click();
		}			
		
		//Method to click Insert New Category button
		public void clickInsertNew() {
			this.submitNew.click();
		}		
		
		//Method to enter text in Name textarea
		public void enterName(String name) {
			this.nameText.sendKeys(name);
		}		

		//Method to click Save button
		public void clickSave() {
			this.saveButton.click();
		}		
		
		//Method to click Home Menu
		public void clickHome() {
			this.home.click();
		}
		
		//Method to send Member username
		public void memberUserName (String name) throws InterruptedException {
			this.memUserName.sendKeys(name);
			Thread.sleep(2000);
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
		
		//Method to click Logout
		public void clickLogout() {
			this.logout.click();
		}
		
	//Member WebElements
		
		//Personal Menu
		@FindBy(xpath="//span[contains(text(),'Personal')]")
		private WebElement personal; 	

		//Advertisements Sub-menu
		@FindBy(xpath="//span[contains(text(),'Advertisements')]")
		private WebElement advertisements; 	

		//Advertisement title
		@FindBy(xpath="//*[@id=\"tdContents\"]/table[1]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/div[1]/a")
		private WebElement advTitle; 	
		
	//Member Methods
		
		//Method to click Personal Menu
		public void clickPersonal() {
			this.personal.click();
		}

		//Method to click Advertisements Sub-menu
		public void clickAdv() {
			this.advertisements.click();
		}
		
		//Method to verify Advertisement Title
		public String verifyAdvTitle() {
			return this.advTitle.getText();
		}		
}
