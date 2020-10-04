package pages;

import base.PredefinedActions;
import constant.ConstantPath;
import entities.Product;
import util.PropertyFileOperation;

import java.util.List;

public class ProductDetailPage extends PredefinedActions {

	private static ThreadLocal<ProductDetailPage> productDetailPage = new ThreadLocal<>();
	private PropertyFileOperation propertyFileOperation;

	private ProductDetailPage() {
		propertyFileOperation = new PropertyFileOperation(
				ConstantPath.LOCATORPATH + "ProductDetailPageProp.properties");
	}

	public static ProductDetailPage getProductListPageObject() {
		if (productDetailPage.get() == null) {
			productDetailPage.set(new ProductDetailPage());
		}
		return productDetailPage.get();
	}

	public List<String> getAllAvailableSizeList() {
		return getAllElementsText(propertyFileOperation.getValue("availableAllSize"), true);
	}

	public void selectProductSize(int sizeNumber) {
		String locator = String.format(propertyFileOperation.getValue("productSize"), String.valueOf(sizeNumber));
		clickOnElement(locator, false);
	}

	public boolean isProductSizeSelected(int sizeNumber) {
		String locator = String.format(propertyFileOperation.getValue("productSize"), String.valueOf(sizeNumber));
		String classAttributeValue = getElementAttributeValue(getElement(locator, false), "class");
		return classAttributeValue.contains("attr-selected") ? true : false;
	}

	private int getPrice(String charges) {
		String[] chargesArr = charges.split(" ");
		for (String word : chargesArr) {
			try {
				int price = Integer.parseInt(word.replace(",", ""));
				return price;
			} catch (NumberFormatException ne) {
				// System.out.println("Not a price");
			}
		}
		return 0;
	}

	public Product getSeletedProductDetail() {
		Product product = new Product();
		product.setProductName(getElementText(propertyFileOperation.getValue("productTitle"), true));
		product.setProductMrpDetails(getElementText(propertyFileOperation.getValue("productMrp"), false));
		product.setProductMrp(getPrice(getElementText(propertyFileOperation.getValue("productMrp"), false)));
		product.setSize(Integer.parseInt(getElementText(propertyFileOperation.getValue("selectedProductSize"), false)));
		String charges = getElementText(propertyFileOperation.getValue("deliveryCharges"), false);
		product.setDeliveryCharges(getPrice(charges));
		return product;
	}

	public ProductCartPage clickOnAddToCart() {
		clickOnElement(propertyFileOperation.getValue("addToCartBtn"), true);
		return ProductCartPage.getProductCartPageObject();
	}
}
