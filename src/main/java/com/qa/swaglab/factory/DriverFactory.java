package com.qa.swaglab.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String highlight = null;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver init_driver(Properties prop) {

		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");
		String browserName = prop.getProperty("browser").trim();
		System.out.println("browser name: " + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver(optionsManager.getChromeOption());
//			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOption()));
//			driver = new ChromeDriver(optionsManager.getChromeOption());
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
//			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOption()));
//			driver = new FirefoxDriver(optionsManager.getFirefoxOption());
		} else if (browserName.equalsIgnoreCase("safari")) {
			tlDriver.set(new SafariDriver());
//			driver = new SafariDriver();
		} else {
			System.out.println("please pass the correct browser" + browserName);
		}

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url").trim());

		return getDriver();
	}

	// to synchronize between the copy of thread local driver
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties init_prop() {
		FileInputStream ip = null;
		prop = new Properties();
		String env = System.getProperty("env");
		System.out.println("running code on environment: --> " + env);

		if (env == null) {
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				switch (env) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				default:
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		try {
			prop.load(ip);
		} catch (FileNotFoundException e) {
			System.out.println("file not found exception");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO exception");
			e.printStackTrace();
		}

		return prop;

	}
	
	/**
	 * taking screenshot for report
	 */
	public String getScreenshot() {
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis();
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
