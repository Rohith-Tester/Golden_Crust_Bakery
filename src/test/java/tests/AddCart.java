package tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import base.BaseTest;
import pages.LoginPage;
import pages.NewOrderPage;
import utils.ExtentListener;

@Listeners(ExtentListener.class)

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
        
        newOrderPage.selectCustomerByName("Nandha");
        
        System.out.println("Pass : Customer Seleced Successfully");
        
        newOrderPage.selectDelivery();
        
        System.out.println("Pass : Delivery Radio Button Selected Successfully");
        
        newOrderPage.enterDeliveryAddress("342,mettu Street,Kilkodungalur");
        
        newOrderPage.selectOnline();
        
        newOrderPage.enterCardNumber("4111111111111111");
        
        newOrderPage.enterExpiry("12/30");

        newOrderPage.enterCVV("123");

        newOrderPage.enterNameOnCard("Rohith");

        System.out.println("Pass : Card Details Entered Successfully");
        
        newOrderPage.clickPay();
        
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/payment.png")));
        
        newOrderPage.clickPlaceOrder();
	
    }

}
