package pages;

import base.PredefinedActions;
import constant.ConstantPath;
import util.PropertyFileOperation;

public class ProductCartPage extends PredefinedActions {
	private static ThreadLocal<ProductCartPage> productCartPage = new ThreadLocal<>();
	private PropertyFileOperation propertyFileOperation;

	private ProductCartPage() {
		propertyFileOperation = new PropertyFileOperation(ConstantPath.LOCATORPATH + "ProductCartPageProp.properties");
	}

	public static ProductCartPage getProductCartPageObject() {
		if (productCartPage.get() == null) {
			productCartPage.set(new ProductCartPage());
		}
		return productCartPage.get();
	}

	public String getProductTitle() {
		return getElementText(propertyFileOperation.getValue("productTitle"), true);
	}

	private int getPrice(String price) {
		return Integer.parseInt(price.split(" ")[1].replace(",", ""));
	}

	public int getProductMRP() {
		return getPrice(getElementText(propertyFileOperation.getValue("productMRP"), false));
	}

	public int getProductFinalPrice() {
		System.out.println(Thread.currentThread().getName());
		return getPrice(getElementText(propertyFileOperation.getValue("productFinalPrice"), true));
	}

}
