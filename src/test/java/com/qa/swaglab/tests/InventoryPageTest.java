package com.qa.swaglab.tests;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.swaglab.base.BaseTest;
import com.qa.swaglab.utils.Constants;
import com.qa.swaglab.utils.ErrorClass;

public class InventoryPageTest extends BaseTest{

	@BeforeClass
	public void doInventorySetup() {
		inventoryPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Test(enabled = false)
	public void inventoryPageTitleTest() {
		String title = inventoryPage.getInventoryPageTitle();
		Assert.assertEquals(title, Constants.INVENTORY_PAGE_TITLE, ErrorClass.INVENTORY_HEADER_TITLE_ERROR);
	}
	
	@Test
	public void inventoryPageUrlTest() {
		String url = inventoryPage.getInventoryPageUrl();
		Assert.assertTrue(url.contains(Constants.INVENTORY_PAGE_URL));
	}
	
	@Test
	public void inventoryPageHeaderTest() {
		String header = inventoryPage.getInventoryPageHeader();
		Assert.assertEquals(header, Constants.INVENTORY_PAGE_HEADER, ErrorClass.INVENTORY_HEADER_TITLE_ERROR);
	}
	
	@Test
	public void cartIconTest() {
		Assert.assertTrue(inventoryPage.isCartIconExist(), ErrorClass.INVENTORY_CART_ICON_NOT_PRESENT);
	}
	
	@Test
	public void sortContainerTest() {
		Assert.assertTrue(inventoryPage.isSortContainerExist());
	}
	
	@Test
	public void inventoryProdListTest() {
		List<String> prodList = inventoryPage.getInventoryProdList();
		prodList.stream().forEach(e -> System.out.println(e));
		Collections.sort(Constants.EXPECTED_INVENTORY_LIST);
		Assert.assertEquals(prodList, Constants.EXPECTED_INVENTORY_LIST);
	}
}
