package com.qa.swaglab.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.swaglab.factory.DriverFactory;

public class ElementUtil {
	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}

	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(element);
		}
		return element;
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	public void doSendKeys(By locator, String value) {
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(value);
	}

	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}

	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public String getPageUrl() {
		return driver.getCurrentUrl();
	}

	public List<String> getElementsTextList(By locator) {
		List<String> eleTextList = new ArrayList<String>();

		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			if (!e.getText().isEmpty()) {
				eleTextList.add(e.getText());
			}
		}

		return eleTextList;
	}

	public void doActionsMoveToElement(By locator) {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(locator)).perform();
	}

	public void doActionsMoveToElementAndClick(By locator) {
		doActionsMoveToElement(locator);
		getElement(locator).click();
	}

	/********************************** drop down utils ********************/

	public void doSelectDropDownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectDropDownByVisibleText(By locator, String visibletext) {
		Select select = new Select(getElement(locator));
//		select.deselectByValue(visibletext);
		select.selectByVisibleText(visibletext);
	}

	public void doSelectDropDownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectDropDownValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		System.out.println(optionsList.size());

		for (WebElement e : optionsList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	public void doSelectDropDownValueWithoutSelect(By locator, String value) {
		List<WebElement> list = getElements(locator);
		for (WebElement e : list) {
			if (e.getText().equals(value)) {
				e.click();
				break;
			}
		}
	}

	public String switchToWindowAndGetTitle(String windowId) {
		driver.switchTo().window(windowId);
		String title = driver.getTitle();
		return title;
	}

	// ***************************** Wait Utils **************************

	public Alert waitForAlertPresent(Duration timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertText(Duration timeOut) {
		return waitForAlertPresent(timeOut).getText();
	}

	public void acceptAlert(Duration timeOut) {
		waitForAlertPresent(timeOut).accept();
	}

	public void dismissAlert(Duration timeOut) {
		waitForAlertPresent(timeOut).dismiss();
	}

	public String waitForTitle(Duration timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}

	public String waitForTitleContains(Duration timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}

	public String waitForTitle(Duration timeOut, String title, Duration intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut, intervalTime);
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}

	public boolean waitForUrl(Duration timeOut, String url) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.urlContains(url));
	}

	public void waitForFrameAndSwitchToIt(String idOrName, Duration timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
	}

	public void waitForFrameAndSwitchToIt(By locator, Duration timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public void waitForFrameAndSwitchToIt(int index, Duration timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

	public void waitForFrameAndSwitchToIt(WebElement frameElement, Duration timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 */
	public WebElement waitForElementPresent(By locator, Duration timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementVisible(By locator, Duration timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locator are visible. Visibility means that the elements are not
	 * only displayed but also have a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForVisibilityOfElements(By locator, Duration timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}

	public void printElementsText(By locator, Duration timeOut) {
		waitForVisibilityOfElements(locator, timeOut).stream().forEach(ele -> System.out.println(ele.getText()));
	}

	public void printListElements(List<String> eleList) {
		eleList.forEach(ele -> System.out.println(ele));
	}

	public List<String> getElementsListWithText(By locator, Duration timeOut, String filterVal) {
		return waitForVisibilityOfElements(locator, timeOut).stream().filter(ele -> ele.getText().contains(filterVal))
				.map(ele -> ele.getText()).collect(Collectors.toList());
	}

	public List<WebElement> getElementsList(By locator, Duration timeOut, String filterVal) {
		return waitForVisibilityOfElements(locator, timeOut).stream().filter(ele -> ele.getText().contains(filterVal))
				.collect(Collectors.toList());
	}

	public WebElement waitForElementVisibleWithElement(By locator, Duration timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}

	/**
	 * This method is specifically for tagName
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForPresentOfElements(By locator, Duration timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public List<String> getElementsTextList(By locator, Duration timeOut) {
		List<String> eleTextList = new ArrayList<String>();
		List<WebElement> eleList = waitForVisibilityOfElements(locator, timeOut);
		for (WebElement e : eleList) {
			if (!e.getText().isEmpty()) {
				eleTextList.add(e.getText());
			}
		}
		return eleTextList;
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementToBeClickable(By locator, Duration timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void clickWhenReady(By locator, Duration timeOut) {
		waitForElementToBeClickable(locator, timeOut).click();
	}

	public WebElement waitForElementWithFluentWait(By locator, int timeout, long pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(pollingTime)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public Alert waitForAlertWithFluentWait(int timeout, long pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(pollingTime)).ignoring(NoAlertPresentException.class);

		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public WebDriver waitForFrameWithFluentWait(String frameLocator, int timeout, long pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(pollingTime)).ignoring(NoSuchFrameException.class);

		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
}
