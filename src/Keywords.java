import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Keywords {
	private String key;
	private String object;
	private String value;
	private WebDriver driver;
	
	public Keywords(WebDriver driver,String key,String object,String value) {
		this.driver=driver;
		this.key=key;
		this.object=object;
		this.value=value;
	}
	public void executeKey() {
		try {
			switch(key.toLowerCase()) {
			case "openapplication":
				driver.get(value);
				System.out.println("Application "+value+" Opened successfully\t[Passed]");
				break;
			case "entertext":
				driver.findElement(getObjectRepository(object)).clear();
				driver.findElement(getObjectRepository(object)).sendKeys(value);
				System.out.println("Value "+value+" entered successfully\t[Passed]");
				break;
			case "selectdropdown":
				//WebElement elem=driver.findElement(getObjectRepository(object));
				//new Select(elem).selectByVisibleText(value);
				System.out.println("Value "+value+" selected successfully\t[Passed]");
				break;
			case "clickbutton":
				driver.findElement(getObjectRepository(object)).click();
				System.out.println("Clicked on object "+object+" successfully\t[Passed]");
				break;
			case "verifytext":
				String actualVal=driver.findElement(getObjectRepository(object)).getAttribute("value");
				if(actualVal.contentEquals(value)) {
					System.out.println(value + " found in the object\t[Passed]");
					
				}else {
					System.out.println(value+" not found in the object, actual value is "+actualVal+"\t[Failed]");
				}
			}
		}catch(Exception e) {
					System.out.println("Step failed\t[Failed]\nEXCEPTION:"+e.getMessage());
				}
			
			}
		

			public By getObjectRepository(String objName) {
				String locatorType=objName.substring(objName.lastIndexOf("_")+1).toLowerCase();
				Properties or=new Properties();
				try {
					or.load(new FileInputStream("ObjectMap\\ObjectCollection.properties"));
				}catch(Exception e) {
					e.printStackTrace();
				}
				String objValue=or.getProperty(objName).trim();
				switch(locatorType.toLowerCase()) {
				case "id":
					return By.id(objValue);
				case "name":
					return By.name(objValue);
				case "xpath":
					return By.xpath(objValue);
					
				}
				return null;
			
		
	}
	

}
