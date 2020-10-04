package pages;

import base.PredefinedActions;
import constant.ConstantPath;
import util.PropertyFileOperation;

import java.util.List;

public class MenuItemPage extends PredefinedActions {
	
	static private ThreadLocal<MenuItemPage> menuItemPage = new ThreadLocal<>();
	PropertyFileOperation propertyFileOperation;

	private MenuItemPage() {
		propertyFileOperation = new PropertyFileOperation(ConstantPath.LOCATORPATH + "MenuItemPageProp.properties");
	}

	public static MenuItemPage getMenuItemPageObject() {
		if (menuItemPage.get() == null) {
			menuItemPage.set(new MenuItemPage());
		}
		return menuItemPage.get();
	}

	public List<String> getListOfTopCategories() {
		return getAllElementsText(propertyFileOperation.getValue("top_category_list"), true);
	}

	public List<String> getListOfMoreCategories() {
		return getAllElementsText(propertyFileOperation.getValue("more_category_list"), true);
	}

	public int getListOfTrendingSearch() {
		return getAllElements(propertyFileOperation.getValue("trending_search_list"), true).size();
	}

	public void hoverOnCategory(String category) {
		String locatorCategory = propertyFileOperation.getValue("menu_item_category");
		locatorCategory = String.format(locatorCategory, category);
		System.out.println("LocatorCategory : " + locatorCategory);
		hoverToElement(getElement(locatorCategory, true));
	}

	public ProductListPage clickOnSubItemLink(String subItem) {
		String locatorSubItem = String.format(propertyFileOperation.getValue("subItemLink"), subItem);
		System.out.println("LocatorCategory : " + locatorSubItem);
		clickOnElement(locatorSubItem, true);
		return ProductListPage.getProductListPageObject();
	}

}
