package com.qa.swaglab.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.swaglab.utils.Constants;
import com.qa.swaglab.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//1. private by locators
	private By username = By.id("user-name");
	private By password = By.id("password");
	private By loginButton = By.id("login-button");
	private By acceptedUsername = By.xpath("//div//h4[text()='Accepted usernames are:']");
	private By error = By.tagName("h3");
	
	//2. constructors
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	//3. public methods
	@Step("get login page title")
	public String getLoginPageTitle() {
		return elementUtil.waitForTitle(Duration.ofSeconds(5), Constants.LOGIN_PAGE_TITLE);
//		return elementUtil.waitForTitle(0, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Step("get login page url")
	public String getLoginPageUrl() {
		return elementUtil.getPageUrl();
	}
	
	public boolean isAcceptedUsernameExist() {
		return elementUtil.doIsDisplayed(acceptedUsername);
	}
	
	@Step("login with username: {0} and password: {1}")
	public InventoryPage doLogin(String name, String pwd) {
		elementUtil.doSendKeys(username, name);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		return new InventoryPage(driver);
	}
	
	@Step("login with username: {0} and password: {1}")
	public boolean doLoginWithWrongCred(String name, String pwd) {
		elementUtil.doSendKeys(username, name);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		return elementUtil.doIsDisplayed(error);
	}
}
