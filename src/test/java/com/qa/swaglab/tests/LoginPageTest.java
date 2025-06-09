package com.qa.swaglab.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.swaglab.base.BaseTest;
import com.qa.swaglab.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageTest extends BaseTest {

	// description and severity is coming from allure
	@Description("Login page title test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void loginPageUrlTest() {
		String url = loginPage.getLoginPageUrl();
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_URL));
	}

	@Test(priority = 3)
	public void acceptedUsernameExistTest() {
		Assert.assertTrue(loginPage.isAcceptedUsernameExist());
	}

	@Test(priority = 4)
	public void doLoginTest() {
		loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@DataProvider
	public Object[][] negativeLoginData() {
		return new Object[][] {{"user", "user123"}, 
					{"test","test@45"}, 
						{" ", "asdf"}};
	}

	@Test(priority = 0, dataProvider = "negativeLoginData")
	public void loginNegativeTest(String un, String pwd) {
		loginPage.doLoginWithWrongCred(un, pwd);
	}

}
