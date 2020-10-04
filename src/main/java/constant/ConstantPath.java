package constant;

import java.io.File;

public class ConstantPath {
	public final static String CHROMEDRIVER_WINDOWS = ".//src//main//resources//windows//chromedriver.exe";
	public final static String FIREFOXDRIVER_WINDOWS = ".//src//main//resources//windows//geckodriver.exe";
	public final static String CHROMEDRIVER_MAC = "./src/main/resources/mac/chromedriver";
	public final static String SCREENSHOTPATH = ".//src//test//resources//screenshot//";
	public final static String SCREENSHOTEXTENTION = ".png";
	public final static String LOCATORPATH = "."+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"config"+File.separator;
	public final static String loginTestData = ".//src//test//resources//testdata//Demo.xlsx";
	public final static String loginTestDataSheet = "LoginData";
	public final static String LOGINCREDENTIALS  = ".//src//main//resources//config//Credentials.properties";
}
