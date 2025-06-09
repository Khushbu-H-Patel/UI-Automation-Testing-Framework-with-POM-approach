package com.qa.swaglab.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOption() {
		co = new ChromeOptions();
		co.setBrowserVersion("116");
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			co.addArguments("--incognito");
		}
		return co;
	}
	
	public FirefoxOptions getFirefoxOption() {
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			fo.addArguments("--incognito");
		}
		return fo;
	}
}
