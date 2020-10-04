package testscripts;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.ProductCartPage;
import pages.ProductDetailPage;

//@Listeners(base.TestNGListenerImpl.class)
public class SearchByNameTest extends TestBase {

	@Test
	public void searchFunction() throws InterruptedException{
		DashboardPage dashboardPage = DashboardPage.getDashboardPage();
		dashboardPage.searchProductName("nicky shoe");
		Thread.sleep(3000);
		dashboardPage.selectFirstAvailableProduct();
		ProductDetailPage productDetailPage = dashboardPage.switchToProductPage();
		ProductCartPage productCartPage = productDetailPage.clickOnAddToCart();
		System.out.println("Product Final Price : " + productCartPage.getProductFinalPrice());
		System.out.println("Product Name : " + productCartPage.getProductTitle());
	}

}
