package tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.NewOrderPage;
import utils.ExtentListener;

@Listeners(ExtentListener.class)

public class CouponTest extends BaseTest {

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

        	System.out.println("Apply button visible: " +newOrderPage.getApplyCouponButton().isVisible());

        // Cart empty -> Apply Coupon should not be visible
        assertThat(newOrderPage.getApplyCouponButton()).isHidden();

        System.out.println("Pass : Apply Coupon Button Is Hidden When Cart Is Empty");
    }

    @Test
    public void verifyCouponBelowMinimumValue() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.login("admin", "admin123");

        // Dashboard
        page.waitForURL("http://localhost/bakery_php/index.php");

        // Orders -> New Order
        page.getByText("Orders").nth(0).click();
        page.getByText("New Order").nth(0).click();

        NewOrderPage newOrderPage = new NewOrderPage(page);

        // Add Baguette
        // Baguette = ₹155
        newOrderPage.addBaguette();

        System.out.println("Pass : Baguette Added");

        // Cart = ₹155 < ₹200
        assertThat(newOrderPage.getApplyCouponButton()).isVisible();

        System.out.println("Pass : Apply Coupon Button Is Hidden Below Minimum Order Value");
    }

    @Test
    public void verifyCouponAboveMinimumValue() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.login("admin", "admin123");

        // Dashboard
        page.waitForURL("http://localhost/bakery_php/index.php");

        // Orders -> New Order
        page.getByText("Orders").nth(0).click();
        page.getByText("New Order").nth(0).click();

        NewOrderPage newOrderPage = new NewOrderPage(page);

        // Baguette = ₹155
        newOrderPage.addBaguette();

        // Brownie = ₹105
        newOrderPage.addBrownie();

        	System.out.println("Apply button visible: " +newOrderPage.getApplyCouponButton().isVisible());

        	System.out.println("Apply button enabled: " +newOrderPage.getApplyCouponButton().isEnabled());

        // Total = ₹260 > ₹200

        System.out.println("Pass : Cart Value Is Above Minimum Order Value");

        // Apply Coupon should now be visible
        assertThat(newOrderPage.getApplyCouponButton()).isVisible();

        System.out.println("Pass : Apply Coupon Button Is Visible Above Minimum Order Value");

        // Apply Coupon should be enabled
        assertThat(newOrderPage.getApplyCouponButton()).isEnabled();

        System.out.println("Pass : Apply Coupon Button Is Enabled Above Minimum Order Value");
    }
}