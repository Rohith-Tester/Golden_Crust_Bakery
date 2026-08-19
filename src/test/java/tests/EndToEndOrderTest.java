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
import pages.TrackOrderPage;
import pages.DeliveryPartnerPage;
import pages.ReturnPage;
import pages.RefundPage;
import utils.ExtentListener;

@Listeners(ExtentListener.class)
public class EndToEndOrderTest extends BaseTest {

    @Test
    public void completeOrderToRefundFlow() {

        String orderNumber;
        String deliveryOtp;
        String returnOtp;
        
        LoginPage loginPage = new LoginPage(page);
        
        //LOGIN AS USER

        loginPage.login("Rohith", "Rohith@123");

        page.waitForURL("http://localhost/bakery_php/index.php");
        
        page.getByText("Maybe later").click();

        System.out.println("Pass : User Login Successful");
        
//		page.getByText("Orders").nth(0).click();
//		
//		page.getByText("New Order").click();
        
     // OPEN ORDERS MENU
        page.locator(
                "button[data-group='orders']"
        ).click();

        System.out.println(
                "Pass : Orders Menu Opened"
        );

        // OPEN NEW ORDER
        page.locator(
                "a[data-route='new-order']"
        ).click();
		
		NewOrderPage neworderpage = new NewOrderPage(page);
		
		neworderpage.addBaguette();
		
		neworderpage.clickCart();
		
		neworderpage.selectDelivery();
		
		neworderpage.enterDeliveryAddress("343,Mettu Street,Chennai");
		
		neworderpage.selectOnline();
		
		neworderpage.enterCardNumber("6378642387646232");
		
		neworderpage.enterExpiry("12/30");
		
		neworderpage.enterCVV("123");
		
		neworderpage.enterNameOnCard("Rohith");
		
		neworderpage.clickPay();
		
		neworderpage.clickPlaceOrder();
		
		System.out.println("Pass : User Placed The Order");
		
		orderNumber = page.locator("ACTUAL_ORDER_NUMBER_LOCATOR").innerText().trim();

		System.out.println("Created Order Number : " + orderNumber);
		
		//LOGOUT AS USER
		
		page.getByText("⎋ Logout").click();
		
		page.getByText("Delete").click();
		
		//LOGIN AS ADMIN
		
		loginPage.login("admin", "admin123");
		
		page.waitForURL("http://localhost/bakery_php/index.php");
		
		System.out.println("Pass : Admin Login Successfully");
		
		page.getByText("Orders").nth(0).click();
		
		page.getByText("Order Details").click();
		
		OrderDetailsPage orderDetailsPage = new OrderDetailsPage(page);
		
		orderDetailsPage.changeStatus("Baking");
		
		System.out.println("Pass : Change To Baking");
		
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/baking.png")));
		
		orderDetailsPage.changeStatus("Ready");
		
		System.out.println("Pass : Change To Ready");
		
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/Ready.png")));
		
		orderDetailsPage.clickassign();
		
		orderDetailsPage.selectDeliveryPartner("Naveen (75475454328)");
		
		orderDetailsPage.clicksave();
		
		System.out.println("Pass : Delivery Partner Assigned");
		
		//LOGOUT AS ADMIN
		
		page.getByText("⎋ Logout").click();
		
		page.getByText("Delete");
		
		//LOGIN AS DELIVERY PARTNER
		
		loginPage.login("Naveen", "Naveen@123");
		
		System.out.println("Pass : Delivery Partner Login Successfully");
		
		DeliveryPartnerPage deliveryPartnerPage = new DeliveryPartnerPage(page);
		
		deliveryPartnerPage.clickStartDelivery();
		
		System.out.println("Pass : Delivery Started");
		
		deliveryPartnerPage.clickMarkDelivered();
		
		//LOGOUT AS DELIVERY PARTNER
		
		page.getByText("⎋ Logout").click();
		
		page.getByText("Delete");
		
		//LOGIN AS USER
		
		loginPage.login("Rohith", "Rohith@123");

        page.waitForURL("http://localhost/bakery_php/index.php");
        
        page.getByText("Maybe later").click();

        System.out.println("Pass : User Login Successful");
        
        page.getByText("Orders").nth(0).click();
        
        page.getByText("Track Order").click();
        
        TrackOrderPage trackOrderPage = new TrackOrderPage(page);
        
        trackOrderPage.entertrackingnumber(orderNumber);
        
        trackOrderPage.clicktrackorderbutton();
        
        deliveryOtp = trackOrderPage.getdeliveryotp();
        
        System.out.println("DELIVERY OTP :" + deliveryOtp);
        
        //LOGOUT AS USER
        
		page.getByText("⎋ Logout").click();
		
		page.getByText("Delete");
		
		//LOGIN AS DELIVERY PARTNER
		
        loginPage.login("Naveen", "Naveen@123");
		
		System.out.println("Pass : Delivery Partner Login Successfully");
		
		DeliveryPartnerPage deliveryPartnerPage1 = new DeliveryPartnerPage(page);
		
		deliveryPartnerPage1.enterOtp(deliveryOtp);
		
		deliveryPartnerPage1.confirmDelivery();
		
		System.out.println("Pass : Order Delivered");
		
        //LOGOUT AS DELIVERY PARTNER
        
		page.getByText("⎋ Logout").click();
		
		page.getByText("Delete");
		
		//REQUEST RETURN BY USER
		
		loginPage.login("Rohith", "Rohith@123");

        page.waitForURL("http://localhost/bakery_php/index.php");
        
        page.getByText("Maybe later").click();

        System.out.println("Pass : User Login Successful");
        
        page.getByText("Orders").nth(0).click();
        
        page.getByText("Track Order").click();
        
        TrackOrderPage trackOrderPage1 = new TrackOrderPage(page);
        
        trackOrderPage1.entertrackingnumber(orderNumber);
        
        trackOrderPage1.clicktrackorderbutton();
        
        ReturnPage returnPage = new ReturnPage(page);
        
        returnPage.clickReturn();
        
        returnPage.selectProductToReturn("Baguette");
        
        returnPage.enterReturnReason("Product Damaged");
        
        returnPage.submitReturn();
        
        
        
        
		
		
        
    }
    
    
}    