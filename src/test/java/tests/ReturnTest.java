package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ReturnPage;

public class ReturnTest extends BaseTest {

    @Test
    public void verifyReturn() {

        LoginPage loginPage = new LoginPage(page);

        // User login
        loginPage.login("Rohith","Rohith@123");

        ReturnPage returnPage = new ReturnPage(page);

        returnPage.clickReturn();

        returnPage.enterReturnReason("Product damaged");

        returnPage.submitReturn();

        System.out.println("Pass : Return Request Submitted");
        
    }
}
