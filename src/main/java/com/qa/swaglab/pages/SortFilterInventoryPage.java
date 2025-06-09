package com.qa.swaglab.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.swaglab.utils.ElementUtil;

public class SortFilterInventoryPage {

	private WebDriver driver;
	ElementUtil elementUtil;
	
	By filtetItem = By.cssSelector("div.inventory_item_description");
	By filterItemsName = By.cssSelector("div.inventory_item_name");
	
	public SortFilterInventoryPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public int filterItemCount() {
		return elementUtil.getElements(filtetItem).size();
	}
	
	public ProductInfoPage selectItemFromFilter(String itemName) {
		List<WebElement> filterItemList = elementUtil.getElements(filterItemsName);
//		System.out.println("total number of items available: " + filterItemList.size());
		for(WebElement e : filterItemList) {
			if(e.getText().equals(itemName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
	
	
}
