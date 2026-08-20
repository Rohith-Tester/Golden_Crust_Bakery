package tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

import org.testng.annotations.Listeners;

import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import base.BaseTest;

import pages.LoginPage;

import pages.NewOrderPage;

import pages.OrderDetailsPage;

import utils.ExtentListener;

@Listeners(ExtentListener.class)

public class OrderDetailsTest extends BaseTest {
	
    @Test
    public void updateOrderStatusAndAssignPartner() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.login("admin", "admin123");

        page.waitForURL("http://localhost/bakery_php/index.php");
        
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
        
        page.waitForTimeout(2000);
        
//      page.getByText("Orders").nth(0).click();
        
        page.getByText("Order Details").click();
        
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(page);
        
        // ONLY CHANGE : GET FRESH ORDER NUMBER
        
        String orderNumber =
        		orderDetailsPage.getLatestOrderNumber();
        
        System.out.println(
        		"Created Order Number : " + orderNumber
        );
        
        orderDetailsPage.changeStatus(
        		orderNumber,
        		"Baking"
        );
        
        System.out.println("Pass : Status Changed Into Baking");
        
        orderDetailsPage.changeStatus(
        		orderNumber,
        		"Ready"
        );
        
        System.out.println("Pass : Status Changed Into Ready");
        
//        orderDetailsPage.clickassign();
        
        orderDetailsPage.clickassign(orderNumber);
        
        orderDetailsPage.selectDeliveryPartner("Naveen (75475454328)");
        
//      orderDetailsPage.enterdeliverypartnername("Rohith");
//      
//      orderDetailsPage.enterdeliverypartnerphone("8347236423");
        
        orderDetailsPage.clicksave();
        
        System.out.println("Pass : Delivery Partner Assigned Successfully");

   }
    
}