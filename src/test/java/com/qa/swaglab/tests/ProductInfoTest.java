package com.qa.swaglab.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.swaglab.base.BaseTest;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void prodInfoSetUp() {
		inventoryPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		sortFilterPage = inventoryPage.selectSortFilterFromContainer("Price (high to low)");
	}

	@Test(priority = 1)
	public void prodCountTest() {
//		sortFilterPage = inventoryPage.selectSortFilterFromContainer("Price (low to high)");
		int prodCount = sortFilterPage.filterItemCount();
		System.out.println("product count after sort filter from prodInfotest: " + prodCount);
		Assert.assertEquals(prodCount, 6);
	}
	
	@Test(priority = 2)
	public void prodHeaderTest() {
//		sortFilterPage = inventoryPage.selectSortFilterFromContainer("Price (low to high)");
		productInfopage = sortFilterPage.selectItemFromFilter("Sauce Labs Bike Light".trim());
		String prodHeader = productInfopage.getProductHeader();
		System.out.println("product header from prodinfotest: " + prodHeader);
		Assert.assertEquals(prodHeader, "Sauce Labs Bike Light".trim());
	}
	
	@Test(priority = 3)
	public void prodPriceTest() {
//		sortFilterPage = inventoryPage.selectSortFilterFromContainer("Price (low to high)");
		productInfopage = sortFilterPage.selectItemFromFilter("Sauce Labs Bike Light".trim());
		String prodprice = productInfopage.getProductPrice();
		System.out.println("price of selected product: " + prodprice);
		Assert.assertEquals(prodprice, "$9.99".trim());
	}
	
	@Test(priority = 4)
	public void prodCartBadgeCheckTest() {
//		sortFilterPage = inventoryPage.selectSortFilterFromContainer("Price (low to high)");
		productInfopage = sortFilterPage.selectItemFromFilter("Sauce Labs Bike Light".trim());
		productInfopage.clickOnAddToCartBtn();
		String badge = productInfopage.successAddProdToCart();
		System.out.println("item added in cart: " + badge);
		Assert.assertTrue(!badge.equals(null));
	}

	
}
