package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChgPwdPOM {
	private WebDriver driver; 
	
	public ChgPwdPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[contains(text(),'Personal')]")
	private WebElement personal; 
	
	@FindBy(xpath="//span[contains(text(),'Change password')]")
	private WebElement chgPwd;
	
	@FindBy(xpath="//span[contains(text(),'Change Password')]")
	private WebElement chgPwdAdmin;
	
	@FindBy(name="oldPassword")
	private WebElement oldPwd; 
	
	@FindBy(name="newPassword")
	private WebElement newPwd; 
	
	@FindBy(name="newPasswordConfirmation")
	private WebElement newPwdConfirm; 
	
	@FindBy(xpath="//input[@value='Submit']")
	private WebElement submit; 
	
	public void sendCurrentPwd(String cPwd) {
		this.personal.click();
		this.chgPwd.click();
		this.oldPwd.clear();
		this.oldPwd.sendKeys(cPwd);
	}
	
	public void sendCurrentPwdAdmin(String cPwd) {
		this.personal.click();
		this.chgPwdAdmin.click();
		this.oldPwd.clear();
		this.oldPwd.sendKeys(cPwd);
	}
	
	public void sendNewPwd(String nPwd) {
		this.newPwd.clear(); 
		this.newPwd.sendKeys(nPwd); 
	}
	
	public void sendNewPwdConfirm(String nPwdConfirm) {
		this.newPwdConfirm.clear(); 
		this.newPwdConfirm.sendKeys(nPwdConfirm); 
	}
	
	public void clickSubmit() {
		this.submit.click(); 
	}
}
