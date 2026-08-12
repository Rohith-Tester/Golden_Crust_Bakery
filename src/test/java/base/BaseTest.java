package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	
	protected Playwright playwright;
	protected Browser browser;
	protected BrowserContext context;
	protected Page page;
	
	@BeforeMethod
	public void setup() {
		
		//LAUNCH THE PLAYWRIGHT
		playwright = Playwright.create();
		
		//LAUNCH THE CHROMIUM BROWSER
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setArgs(List.of("--start-maximized")));
		
		//CREATE BROWSER CONTEXT
		context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
		
		//CREATE PAGE
		page = context.newPage();
		
		//NAVIAGE TO THE WEB
		page.navigate("http://localhost/bakery_php/login.php");
		
		page.waitForTimeout(2000);
		
		}
	
	@AfterMethod
	public void end() {
		
		//CLOSE BROWSER
		browser.close();
		
		//CLOSE PLAYWRIGHT
		playwright.close();
	}

}
