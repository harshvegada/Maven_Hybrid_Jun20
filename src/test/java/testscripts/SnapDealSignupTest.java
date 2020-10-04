package testscripts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.security.auth.login.AccountException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.PredefinedActions;
import pages.DashboardPage;

public class SnapDealSignupTest extends TestBase{

//	@BeforeMethod
//	public void setUp() {
//		PredefinedActions.initializeBrowser("https://www.snapdeal.com/");
//	}
	
	
	@Test
	public void signUpUsingfbTest() throws InterruptedException {
		String expectedUsername = "Maulik Kanani";
		//System.out.println("Snapdeal website open successfully.");
		//DashboardPage dashboardPage = getDashBoardPage();
		ArrayList<String> credentialList = readCredentials();
		dashboardPage.singUpUsingFB(credentialList.get(0),credentialList.get(1));
		boolean usernamePresentFlag= dashboardPage.isUserNamePresent(expectedUsername);
		String actualSignUpUsername = dashboardPage.getUserNamePresent();
		Assert.assertTrue(usernamePresentFlag,"username is not present, expected " + expectedUsername + " but received " + actualSignUpUsername);
    }
	
	@Test(enabled=false)
	public void signUpHoverMenuTest() {
		List<String> expectedHoverOptionList = new ArrayList<>(Arrays.asList("Your Account","Your Orders","Shortlist","SD Cash"));
		//DashboardPage dashboardPage = getDashBoardPage();
		List<String> actualHoverOptionList = dashboardPage.getSignHoverOptions();
		System.out.println(actualHoverOptionList);
		Assert.assertEquals(actualHoverOptionList,expectedHoverOptionList);
	}
}
