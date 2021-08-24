import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class TestScriptParameters {
private String module;
private String testScript;
private String browser;

//System.setProperty("webdriver.chrome.driver","C:\\work\\AF_Selenium\\ResourceFiles\\chromedriver.exe");
private WebDriver driver;
public TestScriptParameters(String module,String testScript,String browser) {
	this.module=module.trim();
	this.testScript=testScript.trim();
	this.browser=browser.trim();
}

public String getModule() {
	return module;
}
public String getTestScript() {
	return testScript;
}
public WebDriver createDriver()
{
	try {
		switch(browser.toLowerCase()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "ResourceFiles\\chromedriver.exe");
			driver=new ChromeDriver();
			break;
		//case "firefox":
		//System.setProperty("webdriver.gecko.driver", "ResourceFiles\\geckodriver.exe");
		//driver=new FirefoxDriver();
		//break;
		//more browsers can be added
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
	return driver;
}
public WebDriver getDriver() {
	return driver;
}

public void closeDriver() {
	driver.quit();
}

}

	

