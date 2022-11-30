package StepdefsJupiterToys;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.yaml.snakeyaml.Yaml;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Utilities {
	
	public Properties properties;

	public WebDriver driver;
     
	static final String Contact_Page_objectspath = "src/test/resources/JupiterToysDemo/xpath.properties";
   
	
	public String getPageElement(String ele_name) throws IOException {
		FileInputStream is = new FileInputStream(Contact_Page_objectspath);
		properties = new Properties();
		properties.load(is);
		
	    return properties.getProperty(ele_name);
	}
	
	
	public void launchChromeBrowser() {
	System.setProperty("webdriver.chrome.driver","/Users/jai/Downloads/chromedriver");
    driver= new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    driver.manage().window().maximize();
    
	}
	
	public byte[] captureScreen() throws IOException{

	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File source = ts.getScreenshotAs(OutputType.FILE);
	    byte[] scn = FileUtils.readFileToByteArray(source);
     	return scn;


	}
    public void launchURL(String url) {
    	driver.navigate().to(url);
    	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    }
	
    public void CLICK(String ele_key) throws IOException {
    	driver.findElement(By.xpath(getPageElement(ele_key))).click();    	
    }
    
    public void FILL_TEXTBOX(String ele_key, String data) throws IOException {
    	driver.findElement(By.xpath(getPageElement(ele_key))).sendKeys(data);
    }
    
    public String GET_TEXT(String ele_key) throws IOException {
    	return driver.findElement(By.xpath(getPageElement(ele_key))).getText();
    }
    public float getPriceForEachProduct(String ele_key) throws IOException, InterruptedException {
    	
    	Thread.sleep(1000);
    	String rate = GET_TEXT(ele_key).substring(1, 5);
    	System.out.println(Float.parseFloat(rate));
	 return Float.parseFloat(rate);
	  //  int Fluffy_Bunny_price= Integer.parseInt(GET_TEXT(getPageElement("Fluffy_Bunny_price")));
	   // int Valentine_Bear_price= Integer.parseInt(GET_TEXT(getPageElement("Valentine_Bear_price")));
	    
	    
    }
    
}