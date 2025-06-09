package com.qa.swaglab.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.swaglab.utils.Constants;
import com.qa.swaglab.utils.ElementUtil;

public class InventoryPage {

	private WebDriver driver;
	ElementUtil elementUtil;
	
	private By cartIcon = By.cssSelector("div#shopping_cart_container");
	private By productsHeader = By.xpath("(//div[@class='header_secondary_container']//span)[1]");
	private By sortContainer = By.xpath("//select[@class='product_sort_container']");
//	private By sortContainer = By.cssSelector("select.product_sort_container");
	private By proList = By.xpath("//div[@id='inventory_container']//div[@class='inventory_item_name']"); 
	
	public InventoryPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getInventoryPageTitle() {
		return elementUtil.waitForTitle(Duration.ofSeconds(5), Constants.INVENTORY_PAGE_TITLE);
	}
	
	public String getInventoryPageUrl() {
		return elementUtil.getPageUrl();
	}
	
	public String getInventoryPageHeader() {
		return elementUtil.doGetText(productsHeader);
	}
	
	public boolean isCartIconExist() {
		return elementUtil.doIsDisplayed(cartIcon);
	}
	
	public boolean isSortContainerExist() {
		return elementUtil.doIsDisplayed(sortContainer);
	}
	
	public List<String> getInventoryProdList() {
		List<String> inventoryListVal = new ArrayList<String>();
		List<WebElement> inventoryList = elementUtil.waitForVisibilityOfElements(proList, Duration.ofSeconds(5));
		for(WebElement e : inventoryList) {
			inventoryListVal.add(e.getText());
		}
		Collections.sort(inventoryListVal);
		return inventoryListVal;
	}
	
	public SortFilterInventoryPage selectSortFilterFromContainer(String sortFilter) {
		elementUtil.doSelectDropDownByVisibleText(sortContainer, sortFilter);
		return new SortFilterInventoryPage(driver);
	}
}
