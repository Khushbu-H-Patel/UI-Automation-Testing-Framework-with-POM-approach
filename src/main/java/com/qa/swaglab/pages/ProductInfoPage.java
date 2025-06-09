package com.qa.swaglab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.swaglab.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	ElementUtil elementUtil;

	By productHeader = By.cssSelector("div.inventory_details_name.large_size");
	By productDescription = By.cssSelector("div.inventory_details_desc.large_size");
	By productImage = By.className("inventory_details_img_container");
	By productPrice = By.className("inventory_details_price");
	By addToCartButton = By.cssSelector("div#inventory_item_container button");
	By cartBadge = By.cssSelector("span.shopping_cart_badge");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getProductHeader() {
		return elementUtil.doGetText(productHeader);
	}

	public String getProductDesc() {
		return elementUtil.doGetText(productDescription);
	}

	public int getProductImageCount() {
		return elementUtil.getElements(productImage).size();
	}

	public String getProductPrice() {
		return elementUtil.doGetText(productPrice);
	}

	public void clickOnAddToCartBtn() {
		elementUtil.doClick(addToCartButton);
	}
	
	public String successAddProdToCart() {
		return elementUtil.doGetText(cartBadge);
	}

}
