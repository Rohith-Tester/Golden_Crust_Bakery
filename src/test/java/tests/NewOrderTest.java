package tests;

import org.testng.annotations.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import base.BaseTest;
import pages.LoginPage;
import pages.NewOrderPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

public class NewOrderTest extends BaseTest {
	
	@Test
	public void verifyeditbakerydetailscontact() {
		
		LoginPage loginPage = new LoginPage(page);
		
		loginPage.login("admin", "admin123");
		
		//WAIT FOR DASHBOARD
		
		page.waitForURL("http://localhost/bakery_php/index.php");
		
		//ORDERS -> NEW ORDERS
		
		page.getByText("Orders").nth(0).click();
		
		page.getByText("New Order").nth(0).click();
		
		NewOrderPage neworderpage = new NewOrderPage(page);
		
		//VERIFY THE NEWORDER PAGE IS DISPLAYED
		
		assertThat(page).hasURL("http://localhost/bakery_php/index.php#new-order");
		
		System.out.println("Pass : New Order Page Is Displayed");
		
		neworderpage.clickEdit();
		
		System.out.println("Pass : Edit Button Is Clicked Successfully");
		
		//UPDATE THE DETAILS
		
		neworderpage.enterphone("9994497232");
		
		neworderpage.enteremail("dummy12@gmail.com");
		
		neworderpage.enteraddress("342,Ms street,Vandavasi");
		
		neworderpage.clicksave();
		
		//VERIFY THE FIELDS ARE UPDATED
		
		assertThat(neworderpage.getphonetext()).hasText("9994497232");
		
		System.out.println("Pass : Mobile Number Updated");
		
		assertThat(neworderpage.getemailtext()).hasText("dummy12@gmail.com");
		
		System.out.println("Pass : Email Updated");
		
		assertThat(neworderpage.getaddresstext()).hasText("342,Ms street,Vandavasi");
		
		System.out.println("Pass : Address Updated");
		
		neworderpage.takeContactDetailsScreenshot("screenshots/contact-details-updated.png");
		
		System.out.println("Pass : Screenshot Taken Successfully");
		
		
	}
	
}
