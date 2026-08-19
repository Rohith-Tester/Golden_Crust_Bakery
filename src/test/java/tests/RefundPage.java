package tests;

import com.microsoft.playwright.Locator;

import com.microsoft.playwright.Page;

public class RefundPage {

    private Page page;

    private Locator markReceivedButton;

    private Locator refundButton;

    private Locator bankTransferReference;

    private Locator markRefundedButton;

    public RefundPage(Page page) {

        this.page = page;

        // MARK RECEIVED

        markReceivedButton = page.getByText(
                "Mark Received",
                new Page.GetByTextOptions().setExact(true)
        );

        // REFUND

        refundButton = page.getByText(
                "Refund",
                new Page.GetByTextOptions().setExact(true)
        );

        // BANK TRANSFER REFERENCE

        bankTransferReference = page.locator(
                "input[placeholder*='NEFT']"
        );

        // MARK REFUNDED

        markRefundedButton = page.getByText(
                "Mark Refunded",
                new Page.GetByTextOptions().setExact(true)
        );

    }

    public void markProductReceived() {

        markReceivedButton.click();

    }

    public void clickRefund() {

        refundButton.click();

    }

    public void enterBankTransferReference(
            String reference) {

        bankTransferReference.fill(reference);

    }

    public void clickMarkRefunded() {

        markRefundedButton.click();

    }

}