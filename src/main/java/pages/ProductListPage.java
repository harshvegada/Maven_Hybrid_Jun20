package pages;

import base.PredefinedActions;
import constant.ConstantPath;
import org.openqa.selenium.WebElement;
import util.PropertyFileOperation;

import java.util.ArrayList;
import java.util.Set;

public class ProductListPage extends PredefinedActions {

	private static ThreadLocal<ProductListPage> productListPage = new ThreadLocal<>();
	private PropertyFileOperation propertyFileOperation;

	private ProductListPage() {
		propertyFileOperation = new PropertyFileOperation(ConstantPath.LOCATORPATH + "ProductListPageProp.properties");
	}

	public static ProductListPage getProductListPageObject() {
		if (productListPage.get() == null) {
			productListPage.set(new ProductListPage());
		}
		return productListPage.get();
	}

	public void filterByBrand(String brandName) {
		clickOnElement(propertyFileOperation.getValue("searchByBrand"), true);
		enterText(propertyFileOperation.getValue("searchItem"), true, brandName);
		clickOnElement(String.format(propertyFileOperation.getValue("selectItem"), brandName), true);
		clickOnElement(propertyFileOperation.getValue("apply_btn"), false);
	}

	public boolean isBrandSelected(String brandName) {
		WebElement element = getElement(String.format(propertyFileOperation.getValue("selectItem"), brandName), true);
		String attrValue = getElementAttributeValue(element, "class");
		/*
		 * if(attrValue == null) return false; else
		 * if(attrValue.contains("active-filter")) return true; else return false;
		 */
		return attrValue == null ? false : attrValue.contains("active-filter") ? true : false;
	}

	public ProductDetailPage selectFirstProduct() {
		String mainWindow = getMainWindowHandleId();
		clickOnElement(propertyFileOperation.getValue("selectProduct"), false);
		Set<String> allWindows = getAllWindowHandleId(); // 2
		allWindows.remove(mainWindow); // 1
		ArrayList<String> temp = new ArrayList<String>(allWindows);
		switchToWindow(temp.get(0));
		return ProductDetailPage.getProductListPageObject();
	}

}
