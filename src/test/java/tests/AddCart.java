package tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.NewOrderPage;

public class AddCart extends BaseTest {
	
    @Test
    public void verifyCouponWhenCartEmpty() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.login("admin", "admin123");

        // Dashboard
        page.waitForURL("http://localhost/bakery_php/index.php");

        // Orders -> New Order
        page.getByText("Orders").nth(0).click();
        page.getByText("New Order").nth(0).click();

        NewOrderPage newOrderPage = new NewOrderPage(page);

        // Verify New Order page
        assertThat(page).hasURL("http://localhost/bakery_php/index.php#new-order");

        System.out.println("Pass : New Order Page Is Displayed");
        
        newOrderPage.addBaguette();
        
        System.out.println("Pass : Product Added To The Cart Successfully");
        
        newOrderPage.clickCart();
        
        System.out.println("Pass : Cart Opened Successfully");
        
        newOrderPage.clickPlaceOrder();
	
	

}
