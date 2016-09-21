package asv.tarificador.test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import static org.testng.Assert.*;


public class test_prueba {
//	private String baseUrl;
	private WebDriver driver;
	private ScreenshotHelper screenshotHelper;
	  
	@BeforeTest
	public void openBrowser() {
//		baseUrl = System.getProperty("https://www.google.es");
	    driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get("https://www.google.es");
	    screenshotHelper = new ScreenshotHelper();
	}
	  
	@AfterTest
	public void saveScreenshotAndCloseBrowser() throws IOException {
		screenshotHelper.saveScreenshot("screenshot.png");
		driver.quit();
	}
	  
	@Test
	public void pageTitleAfterSearchShouldBeginWithDrupal() throws IOException {
		assertEquals("Google", driver.getTitle(), "The page title should equal Google at the start of the test.");
	    WebElement searchField = driver.findElement(By.name("q"));
	    searchField.sendKeys("Drupal!");
	    searchField.submit();
	}
	  
	private class ScreenshotHelper {
		
		public void saveScreenshot(String screenshotFileName) throws IOException {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(screenshotFileName));
		}
	}  
}
