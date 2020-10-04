package testscripts;

import static base.PredefinedActions.driver;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import entities.Product;
import pages.MenuItemPage;
import pages.ProductCartPage;
import pages.ProductDetailPage;
import pages.ProductListPage;

//@Listeners(base.TestNGListenerImpl.class)
public class ProductPurchaseE2ETest extends TestBase {

	/*
	 * public void login() { DashboardPage dashboardPage = getDashBoardPage();
	 * ArrayList<String> credentialList = readCredentials();
	 * dashboardPage.doLogin(credentialList.get(0), credentialList.get(1)); boolean
	 * usernameFlag = dashboardPage.isUserNamePresent("Krishna Kanani"); }
	 */

	@Test
	public void productSelectTest() {
		SoftAssert softAssert = new SoftAssert();
		// login();
		// MenuItemPage menuItemPage = getMenuItemPage();
		Thread.currentThread().setName("Test 1");
		MenuItemPage menuItemPage = MenuItemPage.getMenuItemPageObject();
		System.out.println(Thread.currentThread().getName() + " : " + driver.get());
		menuItemPage.hoverOnCategory("Men's Fashion");
		System.out.println(Thread.currentThread().getName() + " : " + driver.get());
		ProductListPage productListPage = menuItemPage.clickOnSubItemLink("Sports Shoes");
		productListPage.filterByBrand("Reebok");
		System.out.println(Thread.currentThread().getName() + " : " + driver.get());

		boolean isbrandSelected = productListPage.isBrandSelected("Reebok");
		System.out.println(Thread.currentThread().getName() + " : " + driver.get());

		softAssert.assertTrue(isbrandSelected);

		
		ProductDetailPage productDetailPage = productListPage.selectFirstProduct();
		System.out.println(Thread.currentThread().getName() + " : " + driver.get());

		List<String> availableSizeList = productDetailPage.getAllAvailableSizeList();
		System.out.println(Thread.currentThread().getName() + " : " + driver.get());

		productDetailPage.selectProductSize(Integer.parseInt(availableSizeList.get(0)));
		System.out.println(Thread.currentThread().getName() + " : " + driver.get());

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

	@Test
	public void productSelectTest1() {
		SoftAssert softAssert = new SoftAssert();
		// login();
		// MenuItemPage menuItemPage = getMenuItemPage();
		Thread.currentThread().setName("Test 2");
		System.out.println(Thread.currentThread().getName() + " : " + driver.get());
		MenuItemPage menuItemPage = MenuItemPage.getMenuItemPageObject();
		menuItemPage.hoverOnCategory("Men's Fashion");
		System.out.println(Thread.currentThread().getName() + " : " + driver.get());

		ProductListPage productListPage = menuItemPage.clickOnSubItemLink("Sports Shoes");
		System.out.println(Thread.currentThread().getName() + " : " + driver.get());

		productListPage.filterByBrand("Reebok");
		System.out.println(Thread.currentThread().getName() + " : " + driver.get());

		boolean isbrandSelected = productListPage.isBrandSelected("Reebok");
		System.out.println(Thread.currentThread().getName() + " : " + driver.get());

		softAssert.assertTrue(isbrandSelected);
		System.out.println(Thread.currentThread().getName() + " : " + driver.get());


		ProductDetailPage productDetailPage = productListPage.selectFirstProduct();
		System.out.println(Thread.currentThread().getName() + " : " + driver.get());

		List<String> availableSizeList = productDetailPage.getAllAvailableSizeList();
		System.out.println(Thread.currentThread().getName() + " : " + driver.get());

		productDetailPage.selectProductSize(Integer.parseInt(availableSizeList.get(0)));
		System.out.println(Thread.currentThread().getName() + " : " + driver.get());

		// System.out.println(availableSizeList);
		boolean isSizeSelected = productDetailPage.isProductSizeSelected(Integer.parseInt(availableSizeList.get(0)));
		Assert.assertTrue(isSizeSelected);

		Product product = productDetailPage.getSeletedProductDetail();
		System.out.println(Thread.currentThread().getName() + " : " + driver.get());

		ProductCartPage productCartPage = productDetailPage.clickOnAddToCart();
		System.out.println(Thread.currentThread().getName() + " : " + driver.get());


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

}