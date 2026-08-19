package pages;

import com.microsoft.playwright.Locator;

import com.microsoft.playwright.Page;

public class DeliveryPartnerPage {

    private Page page;

    // ORDER ACTIONS

    private Locator markdeliveredbutton;

    // OTP POPUP

    private Locator otpfield;

    private Locator confirmdeliverybutton;

    public DeliveryPartnerPage(Page page) {

        this.page = page;

        // MARK DELIVERED

        markdeliveredbutton = page.getByText(
                "✅ Mark Delivered"
        );

        // OTP FIELD

        otpfield = page.locator("#otp-input");

        // CONFIRM DELIVERY

        confirmdeliverybutton = page.getByText(
                "✅ Confirm Delivery"
        );

    }

    // GET LATEST ORDER NUMBER

    public String getLatestOrderNumber() {

        Locator latestOrderRow =
                page.locator("tbody tr").first();

        return latestOrderRow
                .locator("span.font-medium")
                .innerText()
                .trim();
    }

    // START DELIVERY

    public void clickStartDelivery(String orderNumber) {

        Locator orderRow =
                page.locator("tbody tr")
                        .filter(
                                new Locator.FilterOptions()
                                        .setHasText(orderNumber)
                        );

        orderRow.locator(
                "button[data-start-delivery]"
        ).click();

    }

    // MARK DELIVERED

    public void clickMarkDelivered(String orderNumber) {

        Locator orderRow =
                page.locator("tbody tr")
                        .filter(
                                new Locator.FilterOptions()
                                        .setHasText(orderNumber)
                        );

        orderRow.locator(
                "button[data-mark-delivered]"
        ).click();

    }

    // ENTER OTP

    public void enterOtp(String otp) {

        otpfield.fill(otp);

    }

    // CONFIRM DELIVERY

    public void confirmDelivery() {

        confirmdeliverybutton.click();

    }

}