package pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;

import base.PredefinedActions;
import constant.ConstantPath;
import util.PropertyFileOperation;

public class DashboardPage extends PredefinedActions {
	private static ThreadLocal<DashboardPage> dashboardPage = new ThreadLocal<>();
	private PropertyFileOperation propOperation;
	private String mainWindow;

	private DashboardPage() {
		propOperation = new PropertyFileOperation(ConstantPath.LOCATORPATH + "DashboardPageProp.properties");
	}

	static public DashboardPage getDashboardPage() {
		if (dashboardPage.get() == null)
			dashboardPage.set(new DashboardPage());
		return dashboardPage.get();
	}

	public void searchProductName(String productName) {
		enterText(propOperation.getValue("searchBarOption"), true, productName);
		clickOnElement(propOperation.getValue("searchButton"), false);
	}

	public void selectFirstAvailableProduct() {
		List<WebElement> listOfPorduct = getAllElements(propOperation.getValue("availableProduct"), true);
		System.out.println(Thread.currentThread().getName() + " : " + listOfPorduct.size());
		clickOnElement(listOfPorduct.get(0));
	}

	private void signHover() {
		WebElement target = getElement(propOperation.getValue("singInMenu"), false);
		hoverToElement(target);
	}

	private void clickOnLogin() {
		signHover();
		clickOnElement(propOperation.getValue("signUpHoverloginBtn"), true);
		System.out.println("Clicked on login.");
	}

	private void switchToLoginFrame() {
		WebElement frameElement = getElement(propOperation.getValue("loginFrame"), true);
		switchToFrameByElement(frameElement);
		System.out.println("Switched to frame.");
	}

	private void loginUsingFb(String fbUsername, String fbPassword) {
		clickOnElement(propOperation.getValue("fbUserLoginBtn"), true);
		System.out.println("Clicked on fb login.");
		// String mainWindow = getMainWindowHandleId();
		System.out.println("---------------" + mainWindow);
		// To handle all new opened window.
		Set<String> s1 = getAllWindowHandleId();
		Iterator<String> i1 = s1.iterator();

		while (i1.hasNext()) {
			String ChildWindow = i1.next();
			if (!mainWindow.equalsIgnoreCase(ChildWindow)) {
				System.out.println("Switch to window.");
				// Switching to Child window
				switchToWindow(ChildWindow);
				enterText(propOperation.getValue("fbUserEmailTxt"), true, fbUsername);
				enterText(propOperation.getValue("fbUserEmailPwd"), false, fbPassword);
				clickOnElement(propOperation.getValue("fbLoginBtn"), false);
			}
		}
	}

	public void singUpUsingFB(String fbUsername, String fbPassword) throws InterruptedException {
		clickOnLogin();
		switchToLoginFrame();
		mainWindow = getMainWindowHandleId();
		loginUsingFb(fbUsername, fbPassword);
		switchToWindow(mainWindow);
	}

	/// boolean verifyUsernamePresent()

	public boolean isUserNamePresent(String username) {
		return waitForElementTextToBe(propOperation.getValue("accountUserName"), username, true);
	}

	public String getUserNamePresent() {
		return getElementText(propOperation.getValue("accountUserName"), false);
	}

	public List<String> getSignHoverOptions() {
		signHover();
		return getAllElementsText(propOperation.getValue("hoverOptions"), true);
	}

	public boolean isMobileEmailInputEnabled() {
		return isElementEnabled(propOperation.getValue("mobileNumberEmailTxt"), true);
	}

	public void doLogin(String userId, String password) {
		mainWindow = getMainWindowHandleId();
		clickOnLogin();
		switchToLoginFrame();
		enterText(propOperation.getValue("mobileNumberEmailTxt"), true, userId);
		clickOnElement(propOperation.getValue("loginContinueBtn"), false);
		if (isElementPresent(propOperation.getValue("passwordCode"), true)) {
			loginUsingPassCode(userId, password);
		} else {
			enterText(propOperation.getValue("loginPasswordTxt"), true, password);
			clickOnElement(propOperation.getValue("loginBtn"), false);
		}
	}

	private void loginUsingPassCode(String userId, String password) {
		EmailPage emailPage = EmailPage.getPageObject();
		String otp = emailPage.getOtp(userId, password);
		switchToLoginFrame();
		enterText(propOperation.getValue("passwordCode"), true, otp);
		clickOnElement(propOperation.getValue("passwordCode_continueBtn"), false);
	}

	public ProductDetailPage switchToProductPage() {
		Set<String> allWin = getAllWindowHandleId();
		String mainWin = getMainWindowHandleId();
		Iterator<String> i1 = allWin.iterator();

		while (i1.hasNext()) {
			String ChildWindow = i1.next();
			if (!mainWin.equalsIgnoreCase(ChildWindow)) {
				switchToWindow(ChildWindow);
			}
		}
		return ProductDetailPage.getProductListPageObject();
	}
}
