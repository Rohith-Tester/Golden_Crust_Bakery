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
	
//	private Locator baking;
//	private Locator ready;
	private Locator statusdropdown;
	private Locator assign;
//	private Locator deliverypartnername;
//	private Locator deliverypartnerphone;
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
		
//		baking = page.locator("option[value='baking']");
//		
//		ready = page.locator("option[value='ready']");
		
		statusdropdown = page.locator("select[data-status-select]").first();
		
		assign = page.locator("button[title='Assign delivery partner']");
		
//		deliverypartnername = page.locator("#dp-name");
//		
//		deliverypartnerphone = page.locator("#dp-phone");
		
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
		
		//STATUS DROPDOWN
		
	    public void changeStatus(String status) {
	    	
	        statusdropdown.selectOption(status);
	        
	    }
	    
	    //ASSIGN DELIVERY PARTNER
	    
	    public void clickassign() {
	    	
	    	assign.click();
	    	
	    }
	    
//	    public void enterdeliverypartnername(String name) {
//	    	
//	    	deliverypartnername.fill(name);
//	    	
//	    }
//	    
//	    public void enterdeliverypartnerphone(String phone) {
//	    	
//	    	deliverypartnerphone.fill(phone);
//	    	
//	    }
	    
	    public void selectDeliveryPartner(String partnerName) {

	        selectdeliverypartner.selectOption(new SelectOption().setLabel(partnerName));
	        
	    }
	    
	    public void clicksave() {
	    	
	    	save.click();
	    	
	    }
		
		
	}
