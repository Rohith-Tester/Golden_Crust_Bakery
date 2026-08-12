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
	public void invalidlogin() {
		
		LoginPage loginPage = new LoginPage(page);
		
		loginPage.enterusername("Rakesh");
		
		loginPage.enterpassword("Rakesh@123");
		
		loginPage.clicklogin();
		
		loginPage.takeloginerrorscreenshot("screenshots/Invalidcredentials.png");
		
	}
	
	@Test
	public void emptycredentails() {
		
		LoginPage loginPage = new LoginPage(page);
		
		loginPage.enterusername("");
		
		loginPage.enterpassword("");
		
		loginPage.clicklogin();
		
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/Emptycredentials.png")));
		
	}
	
	@Test
	public void username() {
		
		LoginPage loginPage = new LoginPage(page);
		
		loginPage.enterusername("Rohith");
		
		loginPage.enterpassword("Rakesh@123");
		
		loginPage.clicklogin();
		
		loginPage.takeloginerrorscreenshot("screenshots/Wrongpasswordcredentials.png");
		
	}
	
	
	@Test
	public void password() {
		
		LoginPage loginPage = new LoginPage(page);
		
		loginPage.enterusername("Rakesh");
		
		loginPage.enterpassword("Rohith123");
		
		loginPage.clicklogin();
		
		loginPage.takeloginerrorscreenshot("screenshots/Wrongusernamecredentials.png");
		
	}
	
	
	@Test
	public void validlogin() {
		
		LoginPage loginpage = new LoginPage(page);
		
		loginpage.login("admin", "admin123");
        
        page.waitForURL("http://localhost/bakery_php/index.php");

        assertThat(page).hasURL("http://localhost/bakery_php/index.php");
        
        System.out.println("Pass : Dashboard Page Is Viewed Successfully");
        
        //page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/dashboard.png")).setFullPage(true));
        
	
	}

}
