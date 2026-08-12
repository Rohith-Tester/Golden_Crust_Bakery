package tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import base.BaseTest;
import pages.LoginPage;
import pages.dashboard;

import org.testng.annotations.Listeners;
import utils.ExtentListener;

@Listeners(ExtentListener.class)
public class LoginTest extends BaseTest {
		
	@Test
	public void validlogin( ) {
		
		LoginPage loginpage = new LoginPage(page);
		
		loginpage.login("admin", "admin123");
		
        dashboard db = new dashboard(page);
        
        page.waitForURL("http://localhost/bakery_php/index.php");

        assertThat(page).hasURL("http://localhost/bakery_php/index.php");
        
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/dashboard.png")).setFullPage(true));
        
	
	}

}
