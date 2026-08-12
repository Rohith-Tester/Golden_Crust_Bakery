package tests;

import org.testng.annotations.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.ScreenshotOptions;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

import base.BaseTest;
import pages.LoginPage;
import pages.dashboard;

public class DashboardTest extends BaseTest {
	
	@Test
	public void verifydashboardpage() {
		
		LoginPage loginPage = new LoginPage(page);
		loginPage.login("admin", "admin123");
		
		page.waitForURL("http://localhost/bakery_php/index.php");
		
		dashboard db = new dashboard(page);
		
		//DASHBOARD -> CREATE ORDER BUTTON
		
		db.clickneworder();
		
		assertThat(page).hasURL("http://localhost/bakery_php/index.php#new-order");
		
		System.out.println("Pass : Create Order Page Is Opened Successfully");
		
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/createorder.png")).setFullPage(true));
		
		page.goBack();
		
		//DASHBOARD -> CHECK INVENTORY BUTTON
		
		db.clickcheckinventory();
		
		assertThat(page).hasURL("http://localhost/bakery_php/index.php#inventory");
		
		System.out.println("Pass : Check Inventory Page is Opened Successfully");
		
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/checkinventory.png")).setFullPage(true));
		
		page.goBack();
		
		//DASHBOARD -> 	VIEW INVOICE BUTTON
		
		db.clickviewinvoice();
		
		assertThat(page).hasURL("http://localhost/bakery_php/index.php#billing");
		
		System.out.println("Pass : View Invoice Page Is Opened Successfully");
		
		page.screenshot(new ScreenshotOptions().setPath(Paths.get("screenshots/viewinvoice.png")).setFullPage(true));
		
		page.goBack();
		
		//DASHBOARD -> MANAGE CUSTOMERS
		
		db.clickmanagecustomers();
		
		assertThat(page).hasURL("http://localhost/bakery_php/index.php#customers");
		
		System.out.println("Pass : Manage Customer Page Is Opened Successfully");
		
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("Screenshots/managecustomers.png")).setFullPage(true));
		
		page.goBack();
		
		
	}
	

}
