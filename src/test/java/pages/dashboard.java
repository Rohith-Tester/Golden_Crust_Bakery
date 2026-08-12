package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class dashboard {
	
	private Page page;
	
	private Locator createneworder1;
	private Locator checkinventory;
	private Locator viewinvoice;
	private Locator managecustomers;
	
	public dashboard(Page page) {
		
		this.page = page;
		
		createneworder1 = page.locator("#qa-new-order");
		checkinventory = page.getByText("📦 Check Inventory");
		viewinvoice = page.getByText("🧾 View Invoices");
		managecustomers = page.getByText("👥 Manage Customers");
	}
	
	public void clickneworder() {
		
		createneworder1.click();
		
	}
	
	public void clickcheckinventory() {
		
		checkinventory.click();
		
	}
	
	public void clickviewinvoice() {
		
		viewinvoice.click();
		
	}
	
	public void clickmanagecustomers() {
		
		managecustomers.click();
		
	}
	
}
