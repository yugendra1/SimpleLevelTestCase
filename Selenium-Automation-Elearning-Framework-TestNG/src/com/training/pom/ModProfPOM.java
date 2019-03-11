package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ModProfPOM {
	private WebDriver driver; 
	
	public ModProfPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[contains(text(),'Personal')]")
	private WebElement personal; 
	
	@FindBy(xpath="//span[contains(text(),'Profile')]")
	private WebElement profile;
	
	@FindBy(xpath="//input[@value='Change']")
	private WebElement changeBtn; 
	
	@FindBy(xpath="//input[@value='Kannan Manmohanan']")
	private WebElement fullName; 
	
	@FindBy(id="saveButton")
	private WebElement submit; 
		
	public void clickProfile () {
		this.personal.click();
		this.profile.click();
	}
	
	public void clearFullName() {
		this.changeBtn.click();
		this.fullName.clear();
	}
	
	public void changeFullName(String name) {
		this.fullName.sendKeys(name);
	}
	
	public void clickSubmit() {
		this.submit.click(); 
	}
	
	public void acceptPopup() {
		this.driver.switchTo().alert().accept();
	}
}
