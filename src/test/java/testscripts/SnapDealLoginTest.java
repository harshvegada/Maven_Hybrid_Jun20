package testscripts;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.PredefinedActions;
import constant.ConstantPath;
import pages.DashboardPage;
import util.ExcelOperation;

public class SnapDealLoginTest extends TestBase {

//	@BeforeMethod
//	public void setUp() {
//		PredefinedActions.initializeBrowser("https://www.snapdeal.com/");
//	}
	
	
	@Test(enabled=false)
	public void loginTest() {
		//DashboardPage dashboardPage = getDashBoardPage();
		ArrayList<String> credentialList = readCredentials();
		dashboardPage.doLogin(credentialList.get(0), credentialList.get(1));
		boolean usernameFlag = dashboardPage.isUserNamePresent("Maulik Kanani");
		Assert.assertTrue(usernameFlag);
	}
	
	@Test(dataProvider="loginDataProvider")
	public void loginTestUsingTestData(String userId, String password, String username) {
		//DashboardPage dashboardPage = getDashBoardPage();
		dashboardPage.doLogin(userId, password);
		boolean usernameFlag = dashboardPage.isUserNamePresent(username);
		Assert.assertTrue(usernameFlag);
	}
	
	@DataProvider(name="loginDataProvider")
	public Object[][] dataProvider(){
		return ExcelOperation.getAllRows(ConstantPath.loginTestData, ConstantPath.loginTestDataSheet);
	}
}
