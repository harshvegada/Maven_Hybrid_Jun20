package testscripts;

import java.util.ArrayList;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import base.PredefinedActions;
import constant.ConstantPath;
import pages.DashboardPage;
import pages.MenuItemPage;
import pages.ProductListPage;
import util.PropertyFileOperation;

public class TestBase {

	static DashboardPage dashboardPage = DashboardPage.getDashboardPage();
	static MenuItemPage menuItemPage = MenuItemPage.getMenuItemPageObject();

	@Parameters(value = { "browser", "env" })
	@BeforeMethod
	public void setup(String browser, String env) {
		PredefinedActions.initializeBrowser(browser, env);
	}

	DashboardPage getDashBoardPage() {
		if (dashboardPage == null)
			dashboardPage = DashboardPage.getDashboardPage();
		System.out.println("Test Base : " + Thread.currentThread().getName() + " : " + dashboardPage);
		return dashboardPage;
	}

	MenuItemPage getMenuItemPage() {
		if (menuItemPage == null)
			menuItemPage = MenuItemPage.getMenuItemPageObject();
		System.out.println("Test Base : " + Thread.currentThread().getName() + " : " + menuItemPage);
		return menuItemPage;
	}

	ProductListPage getProductListPage() {
		return ProductListPage.getProductListPageObject();
	}

	ArrayList<String> readCredentials() {
		PropertyFileOperation propOperation;
		ArrayList<String> credentialList = new ArrayList<String>();
		propOperation = new PropertyFileOperation(ConstantPath.LOGINCREDENTIALS);
		credentialList.add(propOperation.getValue("fbUsername"));
		credentialList.add(propOperation.getValue("fbPassword"));
		return credentialList;
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			PredefinedActions.captureScreenshot(result.getName());
		}
		PredefinedActions.closeBrowser();
	}

}
