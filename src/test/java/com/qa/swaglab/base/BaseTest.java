package com.qa.swaglab.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.qa.swaglab.factory.DriverFactory;
import com.qa.swaglab.listeners.AllureReportListener;
import com.qa.swaglab.pages.InventoryPage;
import com.qa.swaglab.pages.LoginPage;
import com.qa.swaglab.pages.ProductInfoPage;
import com.qa.swaglab.pages.SortFilterInventoryPage;

@Listeners(AllureReportListener.class)
public class BaseTest {

	DriverFactory df;
	public LoginPage loginPage;
	public InventoryPage inventoryPage;
	public SortFilterInventoryPage sortFilterPage;
	public ProductInfoPage productInfopage;
	public WebDriver driver;
	public Properties prop;

	@BeforeTest
	@Parameters({ "browser" })
	public void setUp(String browserName) {
		df = new DriverFactory();
		prop = df.init_prop();
		prop.setProperty("browser", browserName);
		driver = df.init_driver(prop);
		loginPage = new LoginPage(driver);
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}
}
