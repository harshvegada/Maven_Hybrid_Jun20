package base;

import com.google.common.io.Files;
import constant.ConstantPath;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.PropertyFileOperation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class PredefinedActions {
	private static WebDriverWait wait;
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public static void initializeBrowser(String browserName, String env) {
		String os = "";
		String path = "";

		switch (browserName.toUpperCase()) {
		case "CHROME":
			os = System.getProperty("os.name").toLowerCase();
			System.out.println("os : " + os);
			path = os.contains("windows") ? ConstantPath.CHROMEDRIVER_WINDOWS
					: os.contains("mac") ? ConstantPath.CHROMEDRIVER_MAC : null;
			System.setProperty("webdriver.chrome.driver", path);
			driver.set(new ChromeDriver());
			break;

		case "FF":
			os = System.getProperty("os.name").toLowerCase();
			System.out.println("os : " + os);
			path = os.contains("windows") ? ConstantPath.FIREFOXDRIVER_WINDOWS
					: os.contains("mac") ? ConstantPath.CHROMEDRIVER_MAC : null;
			System.setProperty("webdriver.gecko.driver", path);
			driver.set(new FirefoxDriver());
			break;

		default:
			break;
		}

		driver.get().manage().window().maximize();
		driver.get().get(getApplicationURL(env)); // "https://www.snapdeal.com/"
		wait = new WebDriverWait(driver.get(), 20);
	}

	private static String getApplicationURL(String env) {
		PropertyFileOperation propOpe = new PropertyFileOperation(ConstantPath.LOCATORPATH + "Credentials.properties");
		switch (env.toUpperCase()) {
		case "QA":
			return propOpe.getValue("qaApplicationURL");

		case "PROD":
			return propOpe.getValue("prodApplicationURL");
		}
		return null;
	}

	protected void navigateToURL(String url) {
		driver.get().navigate().to(url);
	}

	protected void openNewTab() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open()");
	}

	protected void hoverToElement(WebElement target) {
		Actions action = new Actions(driver.get());
		action.moveToElement(target).perform();
	}

	private String getLocatorType(String locator) { // [Xpath]:-//span[text()='Sign In']
		return locator.split("]:-")[0].substring(1); // Xpath
	}

	private String getLocatorValue(String locator) {
		return locator.split("]:-")[1]; // //span[text()='Sign In']
	}

	protected List<WebElement> getAllElements(String locator, boolean isWaitRequired) {
		By byLocator = getByReference(getLocatorType(locator), getLocatorValue(locator));
		return driver.get().findElements(byLocator);
	}

	protected List<String> getAllElementsText(String locator, boolean isWaitRequired) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> hoverElements = getAllElements(locator, isWaitRequired);
		List<String> listOfHoverElementsText = new ArrayList<>();
		for (WebElement element : hoverElements) {
			listOfHoverElementsText.add(element.getText());
		}
		return listOfHoverElementsText;
	}

	protected List<Integer> getAllElementsTextInNumberFormat(String locator, boolean isWaitRequired) {
		List<WebElement> hoverElements = getAllElements(locator, isWaitRequired);
		List<Integer> listOfHoverElementsText = new ArrayList<>();
		for (WebElement element : hoverElements) {
			listOfHoverElementsText.add(Integer.parseInt(element.getText()));
		}
		return listOfHoverElementsText;
	}

	protected WebElement getElement(String locator, boolean isWaitRequired) {
		String locatorType = getLocatorType(locator);
		String locatorValue = getLocatorValue(locator);

		WebElement element = null;
		if (isWaitRequired)
			element = wait
					.until(ExpectedConditions.visibilityOfElementLocated(getByReference(locatorType, locatorValue)));
		else
			element = driver.get().findElement(getByReference(locatorType, locatorValue));

		/*
		 * switch (locatorType.toUpperCase()) { case "XPATH": if (isWaitRequired)
		 * element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * locatorValue))); else element = driver.findElement(By.xpath(locatorValue));
		 * break;
		 *
		 * case "ID": if (isWaitRequired) element =
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue))
		 * ); else element = driver.findElement(By.id(locatorValue)); break;
		 *
		 * default: System.out.println("Invalid locator"); }
		 */
		return element;
	}

	protected void switchToFrameByElement(WebElement frameElement) {
		driver.get().switchTo().frame(frameElement);
	}

	protected void switchToFrameByElement(String locator, boolean isWaitRequired) {
		WebElement element = getElement(locator, isWaitRequired);
		switchToFrameByElement(element);
	}

	protected String getMainWindowHandleId() {
		return driver.get().getWindowHandle();
	}

	protected Set<String> getAllWindowHandleId() {
		Set<String> allWindowId = driver.get().getWindowHandles();
		return allWindowId;
	}

	protected void switchToWindow(String windowId) {
		driver.get().switchTo().window(windowId);
	}

	protected void clickOnElement(WebElement element) {
		element.click();
	}

	protected void clickOnElement(String locator, boolean isWaitRequired) {
		WebElement element = getElement(locator, isWaitRequired);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	protected void enterText(WebElement element, String textToBeEnter) {
		if (element.isEnabled())
			element.sendKeys(textToBeEnter);
		else
			System.out.println("Element is not enabled");
	}

	protected void enterText(String locator, boolean isWaitRequired, String textToBeEnter) {
		WebElement element = getElement(locator, isWaitRequired);
		enterText(element, textToBeEnter);
	}

	private By getByReference(String locatorType, String locatorValue) {

		switch (locatorType.toUpperCase()) {
		case "XPATH":
			return By.xpath(locatorValue);

		case "ID":
			return By.id(locatorValue);

		case "NAME":
			return By.name(locatorValue);

		case "CSS":
			return By.cssSelector(locatorValue);

		case "CLASSNAME":
			return By.className(locatorValue);

		case "LINKTEXT":
			return By.linkText(locatorValue);

		default:
			System.out.println("Invalid locator");
		}
		return null;
	}

	protected boolean waitForElementTextToBe(String locator, String expectedText, boolean isWaitRequired) {
		// WebElement signupUserElement = getElement(locator,isWaitRequired);
		boolean flag = wait.until(ExpectedConditions
				.textToBe(getByReference(getLocatorType(locator), getLocatorValue(locator)), expectedText));
		System.out.println(flag);
		return flag;
	}

	protected String getElementText(String locator, boolean isWaitRequired) {
		return getElement(locator, isWaitRequired).getText();
	}

	protected WebElement waitForElementToBePresent(String locator) {
		return wait.until(ExpectedConditions
				.presenceOfElementLocated(getByReference(getLocatorType(locator), getLocatorValue(locator))));
	}

	protected boolean isElementEnabled(String locator, boolean isWaitRequired) {
		return getElement(locator, isWaitRequired).isEnabled();
	}

	protected boolean isElementPresent(String locator, boolean isWaitRequired) {
		try {
			String locatorType = getLocatorType(locator);
			String locatorValue = getLocatorValue(locator);
			WebDriverWait wait = new WebDriverWait(driver.get(), 3);
			WebElement element = null;
			if (isWaitRequired)
				element = wait.until(
						ExpectedConditions.visibilityOfElementLocated(getByReference(locatorType, locatorValue)));
			else
				element = driver.get().findElement(getByReference(locatorType, locatorValue));

			getElement(locator, isWaitRequired);
			return true;
		} catch (Exception ne) {
			return false;
		}
	}

	protected String getElementAttributeValue(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}

	public static void captureScreenshot(String fileName) {
		TakesScreenshot ts = (TakesScreenshot) driver.get();
		File src = ts.getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(src, new File(ConstantPath.SCREENSHOTPATH + fileName + ConstantPath.SCREENSHOTEXTENTION));
		} catch (IOException e) {
			System.out.println("Failed to copy file from memory to location");
			e.printStackTrace();
		}
	}

	public static void closeBrowser() {
		driver.get().close();
	}
}
