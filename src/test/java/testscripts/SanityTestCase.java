package testscripts;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SanityTestCase {

	@Parameters(value = { "browser", "env" })
	@Test
	public void smokeTestCase(String browser, String env) {
		System.out.println("-------------------Sanity---------------------------");
		System.out.println("Browser Name : " + browser);
		System.out.println("Env Name :" + env);
	}

}
