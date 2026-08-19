package pages;

import com.microsoft.playwright.Locator;

import com.microsoft.playwright.Page;

import com.microsoft.playwright.options.SelectOption;

public class OrderDetailsPage {

	private Page page;
	
	//FILTER FUNCTIONS
	
	private Locator pendingfilter;
	private Locator bakingfilter;
	private Locator readyfilter;
	private  Locator outfordelivery;
	private Locator delivered;
	
	//ACTIONS
	
	private Locator statusdropdown;
	private Locator assign;
	private Locator selectdeliverypartner;
	private Locator save;
	
	public OrderDetailsPage(Page page) {
		
		this.page = page;
		
		//FILTER FUNCTIONALITY LOCATORS
		
		pendingfilter = page.locator("button[data-tab='pending']");
		
		bakingfilter = page.locator("button[data-tab='baking']");
		
		readyfilter = page.locator("button[data-tab='ready']");
		
		outfordelivery = page.locator("button[data-tab='out_for_delivery']");
		
		delivered = page.locator("button[data-tab='delivered']");
		
		//DROPDOWN ACTIONS
		
		statusdropdown = page.locator("select[data-status-select]").first();
		
		assign = page.locator("button[title='Assign delivery partner']");
		
		selectdeliverypartner = page.locator("#dp-account");
		
		save = page.locator("#dp-save");
		
	}
		
		//FILTER ACTIONS
		
		public void clickpendingfilter() {
			
			pendingfilter.click();
			
		}
		
		public void clickbakinfilter() {
			
			bakingfilter.click();
			
		}
		
		public void clickreadyfilter() {
			
			readyfilter.click();
			
		}
		
		public void clickoutfordeliveryfilter() {
			
			outfordelivery.click();
			
		}
		
		
		public void clickdeliveryfilter() {
			
			delivered.click();
			
		}
		
		// GET FRESH ORDER NUMBER
		
		public String getLatestOrderNumber() {
			
			Locator latestOrderRow =
					page.locator("tbody tr").first();
			
			return latestOrderRow
					.locator("span.font-medium")
					.innerText()
					.trim();
		}
		
		//STATUS DROPDOWN
		
	    public void changeStatus(String orderNumber, String status) {
	    	
	    	Locator orderRow = page.locator("tbody tr")
	    			.filter(
	    					new Locator.FilterOptions()
	    							.setHasText(orderNumber)
	    			);
	    	
	        orderRow.locator("select[data-status-select]")
	        		.selectOption(
	        				new SelectOption().setLabel(status)
	        		);
	        
	    }
	    
	    //ASSIGN DELIVERY PARTNER
	    
	    public void clickassign() {
	    	
	    	assign.click();
	    	
	    }
	    
	    public void selectDeliveryPartner(String partnerName) {

	        selectdeliverypartner.selectOption(
	        		new SelectOption().setIndex(1)
	        );
	        
	    }
	    
	    public void clicksave() {
	    	
	    	save.click();
	    	
	    }
		
		
	}