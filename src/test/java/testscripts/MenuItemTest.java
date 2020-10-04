package testscripts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import entities.Product;
import pages.ProductCartPage;
import pages.ProductDetailPage;
import pages.ProductListPage;

public class MenuItemTest extends TestBase {

	public void login() {
		// DashboardPage dashboardPage = getDashBoardPage();
		ArrayList<String> credentialList = readCredentials();
		dashboardPage.doLogin(credentialList.get(0), credentialList.get(1));
		boolean usernameFlag = dashboardPage.isUserNamePresent("Krishna Kanani");
	}

	@Test
	public void verifyTopCategoriesTest() {
		//login();
		List<String> expectedTopCategoriesList = new ArrayList<String>(
				Arrays.asList("All Offers", "Mobile & Tablets", "Electronics", "Computers & Gaming", "Home & Kitchen"));
		// MenuItemPage menuItemPage= getMenuItemPage();
		List<String> actualTopCategoriesList = menuItemPage.getListOfTopCategories();
		System.out.println(actualTopCategoriesList);
		Assert.assertTrue(actualTopCategoriesList.equals(expectedTopCategoriesList));
	}

	@Test(enabled = false)
	public void verifyMoreCategoriesTest() {
		login();
		List<String> expectedMoreCategoriesList = new ArrayList<String>(
				Arrays.asList("All Offers", "Mobile & Tablets", "Electronics", "Computers & Gaming", "Home & Kitchen"));
		// MenuItemPage menuItemPage= getMenuItemPage();
		List<String> actualMoreCategoriesList = menuItemPage.getListOfMoreCategories();
		Assert.assertTrue(actualMoreCategoriesList.equals(expectedMoreCategoriesList));
	}

	@Test
	public void productSelectTest() {
		SoftAssert softAssert = new SoftAssert();
		login();
		// MenuItemPage menuItemPage = getMenuItemPage();
		menuItemPage.hoverOnCategory("Men's Fashion");
		ProductListPage productListPage = menuItemPage.clickOnSubItemLink("Sports Shoes");
		productListPage.filterByBrand("Reebok");
		boolean isbrandSelected = productListPage.isBrandSelected("Reebok");
		softAssert.assertTrue(isbrandSelected);

		ProductDetailPage productDetailPage = productListPage.selectFirstProduct();
		List<String> availableSizeList = productDetailPage.getAllAvailableSizeList();
		productDetailPage.selectProductSize(Integer.parseInt(availableSizeList.get(0)));
		// System.out.println(availableSizeList);
		boolean isSizeSelected = productDetailPage.isProductSizeSelected(Integer.parseInt(availableSizeList.get(0)));
		Assert.assertTrue(isSizeSelected);

		Product product = productDetailPage.getSeletedProductDetail();
		ProductCartPage productCartPage = productDetailPage.clickOnAddToCart();

		String productCartPageProductTitle = productCartPage.getProductTitle();
		int productCartPageProductMRP = productCartPage.getProductMRP();
		int productCartPageProductFinalPrice = productCartPage.getProductFinalPrice();

		System.out.println(productCartPageProductTitle);
		System.out.println(productCartPageProductMRP);
		System.out.println(productCartPageProductFinalPrice);

		System.out.println(
				"ProductDetails Page Final price : " + (product.getProductMrp() + product.getDeliveryCharges()));
		softAssert.assertEquals(productCartPageProductTitle, product.getProductName(),
				"Product title is not as expected");
		softAssert.assertEquals(productCartPageProductMRP, product.getProductMrp());
		softAssert.assertEquals(productCartPageProductFinalPrice,
				(product.getProductMrp() + product.getDeliveryCharges()));

		softAssert.assertAll();

		System.out.println("Script End");
	}

	/*
	 * @Test public void verifyTrendingSearchTest() {
	 * 
	 * }
	 */
}
