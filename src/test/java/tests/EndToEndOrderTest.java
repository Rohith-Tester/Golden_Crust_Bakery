package tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

import javax.swing.Popup;

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

        // OPEN ORDERS

//        page.locator(
//                "button[data-group='orders']"
//        ).click();

        // OPEN NEW ORDER

        page.locator(
                "a[data-route='new-order']"
        ).click();

        NewOrderPage neworderpage = new NewOrderPage(page);

        neworderpage.addBrownie();

        neworderpage.clickCart();

        neworderpage.selectDelivery();

        neworderpage.enterDeliveryAddress(
                "343,Mettu Street,Chennai"
        );

        neworderpage.selectOnline();

        neworderpage.enterCardNumber(
                "6378642387646232"
        );

        neworderpage.enterExpiry("12/30");

        neworderpage.enterCVV("123");

        neworderpage.enterNameOnCard("Rohith");

        neworderpage.clickPay();

        neworderpage.clickPlaceOrder();

        System.out.println(
                "Pass : User Placed The Order"
        );
        
        page.getByText("Close").click();

        //LOGOUT AS USER

        page.getByText("⎋ Logout").click();
        
        page.getByText("Delete").click();

        //LOGIN AS ADMIN

        loginPage.login("admin", "admin123");

        page.waitForURL(
                "http://localhost/bakery_php/index.php"
        );

        System.out.println(
                "Pass : Admin Login Successfully"
        );

        page.getByText("Orders").nth(0).click();

        page.getByText("Order Details").click();

        OrderDetailsPage orderDetailsPage =
                new OrderDetailsPage(page);

        // GET FRESH ORDER NUMBER

        orderNumber =
                orderDetailsPage.getLatestOrderNumber();

        System.out.println(
                "Created Order Number : "
                + orderNumber
        );

        // CHANGE TO BAKING

        orderDetailsPage.changeStatus(
                orderNumber,
                "Baking"
        );

        System.out.println(
                "Pass : Change To Baking"
        );

        page.screenshot(
                new Page.ScreenshotOptions()
                        .setPath(
                                Paths.get(
                                        "screenshots/baking.png"
                                )
                        )
        );

        // CHANGE TO READY

        orderDetailsPage.changeStatus(
                orderNumber,
                "Ready"
        );

        System.out.println(
                "Pass : Change To Ready"
        );

        page.screenshot(
                new Page.ScreenshotOptions()
                        .setPath(
                                Paths.get(
                                        "screenshots/Ready.png"
                                )
                        )
        );

        // ASSIGN DELIVERY PARTNER

//        orderDetailsPage.clickassign();
        
        orderDetailsPage.clickassign(orderNumber);

        orderDetailsPage.selectDeliveryPartner(
                "Naveen (75475454328)"
        );

        orderDetailsPage.clicksave();

        System.out.println(
                "Pass : Delivery Partner Assigned"
        );

        //LOGOUT AS ADMIN

        page.getByText("⎋ Logout").click();
        
        page.getByText("Delete").click();

        //LOGIN AS DELIVERY PARTNER

        loginPage.login(
                "Naveen",
                "Naveen@123"
        );

        System.out.println(
                "Pass : Delivery Partner Login Successfully"
        );
        
        page.getByText("Orders").nth(0).click();

        DeliveryPartnerPage deliveryPartnerPage =
                new DeliveryPartnerPage(page);

        // START DELIVERY

        deliveryPartnerPage.clickStartDelivery(
                orderNumber
        );

        System.out.println(
                "Pass : Delivery Started"
        );

        // MARK DELIVERED

        deliveryPartnerPage.clickMarkDelivered(
                orderNumber
        );

        System.out.println(
                "Pass : Mark Delivered Clicked"
        );
        
        page.getByText("Cancel").click();

        //LOGOUT AS DELIVERY PARTNER

        page.getByText("⎋ Logout").click();
        
        page.getByText("Delete").click();

        //LOGIN AS USER

        loginPage.login(
                "Rohith",
                "Rohith@123"
        );

        page.waitForURL(
                "http://localhost/bakery_php/index.php"
        );

        page.getByText("Maybe later").click();

        System.out.println(
                "Pass : User Login Successful"
        );

        page.getByText("Orders").nth(0).click();

        page.getByText("Track Order").click();

        TrackOrderPage trackOrderPage =
                new TrackOrderPage(page);

        trackOrderPage.entertrackingnumber(
                orderNumber
        );

        trackOrderPage.clicktrackorderbutton();

        deliveryOtp =
                trackOrderPage.getdeliveryotp();

        System.out.println(
                "DELIVERY OTP : "
                + deliveryOtp
        );

        //LOGOUT AS USER

        page.getByText("⎋ Logout").click();
        
        page.getByText("Delete").click();

        //LOGIN AS DELIVERY PARTNER

        loginPage.login(
                "Naveen",
                "Naveen@123"
        );

        DeliveryPartnerPage deliveryPartnerPage1 =
                new DeliveryPartnerPage(page);

        deliveryPartnerPage1.enterOtp(
                deliveryOtp
        );

        deliveryPartnerPage1.confirmDelivery();

        System.out.println(
                "Pass : Order Delivered"
        );

        //LOGOUT AS DELIVERY PARTNER

        page.getByText("⎋ Logout").click();
        
        page.getByText("Delete").click();

        //REQUEST RETURN BY USER

        loginPage.login(
                "Rohith",
                "Rohith@123"
        );

        page.waitForURL(
                "http://localhost/bakery_php/index.php"
        );

        page.getByText("Maybe later").click();

        System.out.println(
                "Pass : User Login Successful"
        );

        page.getByText("Orders").nth(0).click();

        page.getByText("Track Order").click();

        TrackOrderPage trackOrderPage1 =
                new TrackOrderPage(page);

        trackOrderPage1.entertrackingnumber(
                orderNumber
        );

        trackOrderPage1.clicktrackorderbutton();

        ReturnPage returnPage =
                new ReturnPage(page);

        returnPage.clickReturn();

        returnPage.selectProductToReturn(
                "Baguette"
        );

        returnPage.enterReturnReason(
                "Product Damaged"
        );

        returnPage.submitReturn();

        System.out.println(
                "Pass : Return Request Submitted"
        );

        //LOGOUT AS USER

        page.getByText("⎋ Logout").click();
        
        page.getByText("Delete").click();

        //LOGIN AS ADMIN

        loginPage.login(
                "admin",
                "admin123"
        );

        page.waitForURL(
                "http://localhost/bakery_php/index.php"
        );

        System.out.println(
                "Pass : Admin Login Successfully"
        );

        page.getByText(
                "Returns & Refunds"
        ).click();

        ReturnPage adminReturnPage =
                new ReturnPage(page);

        // ACCEPT RETURN

        adminReturnPage.acceptReturn();

        System.out.println(
                "Pass : Return Accepted"
        );

        // ASSIGN DELIVERY PARTNER

        adminReturnPage.selectDeliveryPartner(
                "Naveen (75475454328)"
        );

        adminReturnPage.clickSave();

        System.out.println(
                "Pass : Return Partner Assigned"
        );

        //LOGOUT AS ADMIN

        page.getByText("⎋ Logout").click();
        
        page.getByText("Delete").click();

        //LOGIN AS DELIVERY PARTNER

        loginPage.login(
                "Naveen",
                "Naveen@123"
        );

        page.getByText(
                "Returns & Refunds"
        ).click();

        ReturnPage returnPage1 =
                new ReturnPage(page);

        // CONFIRM PICKUP

        returnPage1.confirmReturnPickup();

        System.out.println(
                "Pass : Return Pickup Started"
        );

        //LOGOUT AS DELIVERY PARTNER

        page.getByText("⎋ Logout").click();
        
        page.getByText("Delete").click();

        //LOGIN AS USER

        loginPage.login(
                "Rohith",
                "Rohith@123"
        );

        page.waitForURL(
                "http://localhost/bakery_php/index.php"
        );

        page.getByText("Maybe later").click();

        System.out.println(
                "Pass : User Login Successful"
        );

        page.getByText("Orders").nth(0).click();

        page.getByText("Track Order").click();

        TrackOrderPage returnTrackPage =
                new TrackOrderPage(page);

        returnTrackPage.entertrackingnumber(
                orderNumber
        );

        returnTrackPage.clicktrackorderbutton();

        returnOtp =
                returnTrackPage.getreturnotp();

        System.out.println(
                "RETURN OTP : "
                + returnOtp
        );

        //LOGOUT AS USER

        page.getByText("⎋ Logout").click();
        
        page.getByText("Delete").click();

        //LOGIN AS DELIVERY PARTNER

        loginPage.login(
                "Naveen",
                "Naveen@123"
        );

        page.getByText(
                "Returns & Refunds"
        ).click();

        ReturnPage returnPage2 =
                new ReturnPage(page);

        // ENTER RETURN OTP

        returnPage2.enterReturnOtp(
                returnOtp
        );

        System.out.println(
                "Pass : Return OTP Entered"
        );

        returnPage2.confirmReturnDelivery();

        System.out.println(
                "Pass : Return OTP Confirmed"
        );

        //LOGOUT AS DELIVERY PARTNER

        page.getByText("⎋ Logout").click();
        
        page.getByText("Delete").click();

        //LOGIN AS ADMIN

        loginPage.login(
                "admin",
                "admin123"
        );

        page.waitForURL(
                "http://localhost/bakery_php/index.php"
        );

        page.getByText(
                "Returns & Refunds"
        ).click();

        RefundPage refundPage =
                new RefundPage(page);

        // MARK RECEIVED

        refundPage.markProductReceived();

        System.out.println(
                "Pass : Product Marked As Received"
        );

        // REFUND

        refundPage.clickRefund();

        System.out.println(
                "Pass : Refund Form Opened"
        );

        // BANK TRANSFER REFERENCE

        refundPage.enterBankTransferReference(
                "NEFT20260819001"
        );

        System.out.println(
                "Pass : Bank Transfer Reference Entered"
        );

        // MARK REFUNDED

        refundPage.clickMarkRefunded();

        System.out.println(
                "Pass : Refund Marked Successfully"
        );

        //LOGOUT AS ADMIN

        page.getByText("⎋ Logout").click();
        
        page.getByText("Delete").click();

        //LOGIN AS USER

        loginPage.login(
                "Rohith",
                "Rohith@123"
        );

        page.waitForURL(
                "http://localhost/bakery_php/index.php"
        );

        page.getByText("Maybe later").click();

        System.out.println(
                "Pass : User Login Successful"
        );

        // VERIFY REFUND

        page.getByText("Orders").nth(0).click();

        page.getByText("Track Order").click();

        TrackOrderPage refundTrackPage =
                new TrackOrderPage(page);

        refundTrackPage.entertrackingnumber(
                orderNumber
        );

        refundTrackPage.clicktrackorderbutton();

        assertThat(
                page.getByText(
                        "Refunded",
                        new Page.GetByTextOptions()
                                .setExact(true)
                )
        ).isVisible();

        System.out.println(
                "Pass : Refund Verified By User"
        );

        page.screenshot(
                new Page.ScreenshotOptions()
                        .setPath(
                                Paths.get(
                                        "screenshots/e2e-final.png"
                                )
                        )
        );

        System.out.println(
                "PASS : COMPLETE E2E FLOW FINISHED"
        );
    }
}