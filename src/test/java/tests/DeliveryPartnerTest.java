package tests;

import org.testng.annotations.Test;

import base.BaseTest;

import pages.DeliveryPartnerPage;

import pages.LoginPage;

public class DeliveryPartnerTest extends BaseTest {

    @Test
    public void verifyDeliveryPartner() {

        LoginPage loginPage = new LoginPage(page);

        // Delivery Partner login

        loginPage.login("Naveen","Naveen@123");

        DeliveryPartnerPage deliveryPage =
                new DeliveryPartnerPage(page);

        // ONLY CHANGE : GET CURRENT ORDER

        String orderNumber =
                deliveryPage.getLatestOrderNumber();

        System.out.println(
                "Order Number : " + orderNumber
        );

        deliveryPage.clickStartDelivery(
                orderNumber
        );

        System.out.println(
                "Pass : Delivery Started"
        );

        deliveryPage.clickMarkDelivered(
                orderNumber
        );

        System.out.println(
                "Pass : Mark Delivered Clicked"
        );
    }
}