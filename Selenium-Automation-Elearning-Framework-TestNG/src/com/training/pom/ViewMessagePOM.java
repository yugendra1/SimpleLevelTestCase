package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

//This POM captures WebElements and Methods related to Loan addition by Admin and view by Member 

public class ViewMessagePOM {
	private WebDriver driver; 
	
	public ViewMessagePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//Admin Login WebElements
	
		//Messages Menu
		@FindBy(xpath="//*[@id=\"menu8\"]/span[2]") 
		private WebElement messagesMenu; 
	
		//Messages Sub-menu option
		@FindBy(xpath="//*[@id=\"submenu8.0\"]/span[2]")
		private WebElement msgsSubmenuAdmin; 
	
		//Submit button
		@FindBy(xpath="//input[@value='Submit']")
		private WebElement submit; 
	
		//Member username textbox
		@FindBy(id="memberUsername")
		private WebElement memUserName; 
		
		//Category dropdown
		@FindBy(id="categorySelect")
		private WebElement category; 
		
		//Subject textbox
		@FindBy(id="subjectText")
		private WebElement subject; 
				
		//Body textbox
		@FindBy(xpath="//*[@id=\"cke_contents_bodyText\"]/iframe")
		private WebElement bodyText; 
		
		//Logout button
		@FindBy(xpath="//span[contains(text(),'Logout')]")
		private WebElement logout; 
	
	
	//Member Login WebElements
	
		//Personal menu  
		@FindBy(xpath="//*[@id=\"menu1\"]/span[2]")
		private WebElement personal; 
		
		//Messages Sub-menu option
		@FindBy(xpath="//*[@id=\"submenu1.1\"]/span[2]") 
		private WebElement msgsSubmenuMem; 
		
		//Message title link
		@FindBy(xpath="//*[@id=\"tdContents\"]/form[2]/table[1]/tbody/tr[2]/td/table/tbody/tr[2]/td[3]/table/tbody/tr/td[2]/a") 
		private WebElement messageLink; 
		
		//Message Body text in Message details page
		@FindBy(xpath="//*[@id=\"tdContents\"]/table[1]/tbody/tr[2]/td/table/tbody/tr[6]/td[2]") 
		private WebElement messageBodyText; 
		
	//Admin Login Methods	
	
		//Method to click Messages Menu
		public void clickMessagesMenu() {
			this.messagesMenu.click();
		}

		//Method to click Messages Sub-menu
		public void clickMsgsSubMenuAdmin() {
			this.msgsSubmenuAdmin.click();
		}
		
		//Method to click Submit button
		public void clickSubmit() {
			this.submit.click();
		}
		
		//Method to send Member username
		public void memberUserName (String name) {
			this.memUserName.sendKeys(name);
		}
				
		//Method to select value Category dropdown
		public void selectCategory(String ctgry) {
			this.category.click();
			Select cat = new Select(this.category);
			//List <WebElement> list = cat.getOptions();
			cat.selectByVisibleText(ctgry);
		}
		
		//Method to enter text in Subject
		public void enterSubjectText (String sub) {
			this.subject.sendKeys(sub);
		}
		
		//Method to enter text in Body
		public void enterBodyText (String body) {
			this.bodyText.sendKeys(body);
		}
		
		//Method to click OK in the confirmation popup after sending message
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
		
		//Method to click Messages Submenu
		public void clickMsgsSubMenuMember() {
			this.msgsSubmenuMem.click();
		}
		
		//Method to click Message title
		public void clickMessageTitle() {
			this.messageLink.click();
		}
		
		//Method to validate if the Message Body text is correct
		public String verifyBodyText() {
			return messageBodyText.getText();
		}
}
