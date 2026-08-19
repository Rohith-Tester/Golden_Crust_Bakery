package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

public class ReturnPage {

    private Page page;

    // USER RETURN

    private Locator returnButton;
    private Locator returnReason;
    private Locator submitReturnButton;

    // ADMIN RETURN

    private Locator acceptReturnButton;
    private Locator deliveryPartnerDropdown;
    private Locator saveButton;

    // DELIVERY PARTNER RETURN

    private Locator confirmPickupButton;
    private Locator returnOtpField;
    private Locator confirmReturnButton;

    public ReturnPage(Page page) {

        this.page = page;

        // USER RETURN

        returnButton = page.getByText(
                "Request Return",
                new Page.GetByTextOptions().setExact(true)
        );

        returnReason = page.locator(
                "#return-reason"
        );

        submitReturnButton = page.locator(
                "#submit-return"
        );

        // ADMIN RETURN

        acceptReturnButton = page.getByText(
                "Accept",
                new Page.GetByTextOptions().setExact(true)
        );

        deliveryPartnerDropdown = page.locator(
                "#active-modal select#dp-account"
        );

        saveButton = page.locator(
                "#active-modal #dp-save"
        );

        // DELIVERY PARTNER RETURN

        confirmPickupButton = page.getByText(
                "✅ Confirm Pickup",
                new Page.GetByTextOptions().setExact(true)
        );

        returnOtpField = page.locator(
                "#otp-input"
        );

        confirmReturnButton = page.getByText(
                "✅ Confirm Delivery",
                new Page.GetByTextOptions().setExact(true)
        );

    }

    // USER ACTIONS

    public void clickReturn() {

        returnButton.click();

    }

    public void selectProductToReturn(String productName) {

        page.getByText(
                productName,
                new Page.GetByTextOptions().setExact(true)
        ).click();

    }

    public void enterReturnReason(String reason) {

        returnReason.fill(reason);

    }

    public void submitReturn() {

        submitReturnButton.click();

    }

    // ADMIN ACTIONS

    public void acceptReturn() {

        acceptReturnButton.click();

    }

    public void selectDeliveryPartner(String partnerName) {

        deliveryPartnerDropdown.selectOption(
                new SelectOption().setIndex(1)
        );

    }

    public void clickSave() {

        saveButton.click();

    }

    // DELIVERY PARTNER RETURN ACTIONS

    public void confirmReturnPickup() {

        confirmPickupButton.click();

    }

    public void enterReturnOtp(String otp) {

        returnOtpField.fill(otp);

    }

    public void confirmReturnDelivery() {

        confirmReturnButton.click();

    }

}