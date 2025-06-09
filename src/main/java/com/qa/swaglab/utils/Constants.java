package com.qa.swaglab.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {

	public static final String LOGIN_PAGE_TITLE = "Swag Labs";
	public static final String LOGIN_PAGE_URL = "saucedemo";
	
	public static final String INVENTORY_PAGE_TITLE = "Swag Labs";
	public static final String INVENTORY_PAGE_URL = "inventory.html";
	public static final String INVENTORY_PAGE_HEADER = "Products";
	public static final List<String> EXPECTED_INVENTORY_LIST = 
			Arrays.asList("Sauce Labs Backpack", 
					"Sauce Labs Bike Light", 
					"Sauce Labs Bolt T-Shirt", 
					"Sauce Labs Fleece Jacket", 
					"Sauce Labs Onesie", 
					"Test.allTheThings() T-Shirt (Red)");
}
