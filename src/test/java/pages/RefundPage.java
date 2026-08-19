package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class RefundPage {

    private Page page;

    // RETURNED PRODUCT
    private Locator markReceivedButton;

    // REFUND
    private Locator refundButton;

    // REFUND MODAL
    private Locator bankTransferReference;
    private Locator markRefundedButton;

    public RefundPage(Page page) {

        this.page = page;

        // Product received
        markReceivedButton = page.getByText(
                "Mark Received",
                new Page.GetByTextOptions().setExact(true)
        );

        // Refund button
        refundButton = page.getByText(
                "Refund",
                new Page.GetByTextOptions().setExact(true)
        );

        // Bank transfer reference
        bankTransferReference = page.locator(
                "input[placeholder*='NEFT']"
        );

        // Mark refunded
        markRefundedButton = page.getByText(
                "Mark Refunded",
                new Page.GetByTextOptions().setExact(true)
        );
    }

    // =========================
    // MARK PRODUCT RECEIVED
    // =========================

    public void markProductReceived() {

        markReceivedButton.click();
    }

    // =========================
    // OPEN REFUND FORM
    // =========================

    public void clickRefund() {

        refundButton.click();
    }

    // =========================
    // ENTER BANK TRANSFER REFERENCE
    // =========================

    public void enterBankTransferReference(String reference) {

        bankTransferReference.fill(reference);
    }

    // =========================
    // MARK REFUNDED
    // =========================

    public void clickMarkRefunded() {

        markRefundedButton.click();
    }
}