package pages;

import base.PredefinedActions;
import constant.ConstantPath;
import util.PropertyFileOperation;

import java.util.ArrayList;

class EmailPage extends PredefinedActions {
    static private EmailPage emailPage;
    private PropertyFileOperation propOperation;

    private EmailPage() {
        propOperation = new PropertyFileOperation(ConstantPath.LOCATORPATH + "EmailPageProp.properties");
    }

    static EmailPage getPageObject() {
        if (emailPage == null) {
            emailPage = new EmailPage();
        }
        return emailPage;
    }

    private void switchDriverToNewTab(String mainWindow) {
		/*Set<String> allWindow= getAllWindowHandleId();
		for(String currentWindow : allWindow) {
			if(!currentWindow.equals(mainWindow))
				switchToWindow(currentWindow);
		}*/
        ArrayList<String> allWindow = new ArrayList<String>(getAllWindowHandleId());
        allWindow.remove(mainWindow);
        switchToWindow(allWindow.get(0));
    }

    private void loginYahoo(String emailId, String password) {
        clickOnElement(propOperation.getValue("yahoo_signInButton"), true);
        enterText(propOperation.getValue("yahoo_username"), true, emailId);
        clickOnElement(propOperation.getValue("yahoo_username_next"), false);
        enterText(propOperation.getValue("yahoo_password"), true, password);
        clickOnElement(propOperation.getValue("yahoo_password_next"), false);
    }

    private void handleSkipNotification() {
        if (isElementPresent(propOperation.getValue("yahoo_notification_skip"), true))
            clickOnElement(propOperation.getValue("yahoo_notification_skip"), false);
    }

    private String hoverEmailAndGetOtp() {
        hoverToElement(getElement(propOperation.getValue("yahoo_mail"), true));
        String emailText = getElementText(propOperation.getValue("yahoo_snapdeal_mail"), true);
        return emailText.split(" ")[0];
    }

    String getOtp(String emailId, String password) {
        String mainWindow = getMainWindowHandleId();
        openNewTab();
        switchDriverToNewTab(mainWindow);
        navigateToURL("http://yahoo.com");
        loginYahoo(emailId, password);
        handleSkipNotification();
        String otp = hoverEmailAndGetOtp();
        closeBrowser();
        switchToWindow(mainWindow);
        return otp;
    }

}
