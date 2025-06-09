package com.qa.swaglab.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

	private WebDriver driver;

	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
	}

	public String getTitleByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.title;").toString();
	}

	public void refreshBrowserByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0)");
	}

	public void generateAlert(String message) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('" + message + "')");
	}

	public String getInnerTextByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.documentElement.innerText;").toString();
	}

	// working only for webelement
	public void drawBorderByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	public void flash(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 15; i++) {
			changeColor("rgb(0,200,0)", element);// 1
			changeColor(bgcolor, element);// 2
		}
	}

	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}

	public void clickElementByJS(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", element);
	}

	public void scrollDownByJS() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public void scrollDownByJS(String hight) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, '" + hight + "')");
	}

	public void scrollUpByJS() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}

	public void scrollIntoViewByJS(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendKeysWithIdByJS(String Id, String value) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("document.getElementById('" + Id + "').value= '" + value + "'");
	}
}
