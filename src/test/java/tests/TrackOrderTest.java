package tests;

import java.nio.file.Paths;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import base.BaseTest;
import pages.LoginPage;
import pages.NewOrderPage;
import pages.TrackOrderPage;
import utils.ExtentListener;

@Listeners(ExtentListener.class)

public class TrackOrderTest extends BaseTest {

    @Test
    public void trackorder() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.login("admin", "admin123");

        page.waitForURL("http://localhost/bakery_php/index.php");
        
        page.getByText("Orders").nth(0).click();
        
        page.getByText("Track Order").click();
        
        TrackOrderPage trackOrderPage = new TrackOrderPage(page);
        
        page.waitForTimeout(2000);
        
        trackOrderPage.entertrackingnumber("ORD-007");
        
        page.waitForTimeout(2000);
        
        trackOrderPage.clicktrackorderbutton();        
        		
        page.waitForTimeout(5000);
        
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/trackorder.png")));
	
    }
    
}
