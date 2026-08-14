package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class NewOrderPage {
	
	private Page page;
	
	//LOACTOR
	private Locator editbutton;
	
	//EDIT FORM FIELDS
	private Locator phonefield;
	private Locator emailfield;
	private Locator addressfield;
	private Locator savebutton;
	private Locator applycouponbutton;
	private Locator cartbutton;
	private Locator customerdropdown;
//	private Locator customernamefield;
//	private Locator phonefieldcart;
    private Locator pickupradio;
    private Locator deliveryradio;
    private Locator cashradio;
    private Locator onlineradio;
    private Locator deliveryaddress;
    private Locator placeorderbutton;
    private Locator cardnumber;
    private Locator expiry;
    private Locator cvv;
    private Locator nameoncard;
    private Locator pay;
    
	
	//UPDATED TEXT DISPLAYED ON THE SCREEN
	private Locator phonetext;
	private Locator emailtext;
	private Locator addresstext;
	
	//PRODUCTS
	private Locator baguetteaddbutton;
	private Locator brownieaddbutton;
	
	public NewOrderPage(Page page) {
		
		this.page = page;
		
		//NEW ORDER -> EDIT
		
		editbutton = page.locator("#edit-bakery-contact");
		
		//EDIT FORM LOCATORS
		phonefield = page.locator("#bc-phone");
		
		emailfield = page.locator("#bc-email");
		
		addressfield = page.locator("#bc-address");
		
		savebutton = page.locator("#bc-save");
		
		applycouponbutton = page.getByText("Apply");
		
		baguetteaddbutton = page.getByText(
		        "Baguette",
		        new Page.GetByTextOptions().setExact(true)
		).locator(
		        "xpath=ancestor::div[.//button[normalize-space()='ADD']][1]"
		).getByRole(
		        com.microsoft.playwright.options.AriaRole.BUTTON
		);

		brownieaddbutton = page.getByText(
		        "Brownie",
		        new Page.GetByTextOptions().setExact(true)
		).locator(
		        "xpath=ancestor::div[.//button[normalize-space()='ADD']][1]"
		).getByRole(
		        com.microsoft.playwright.options.AriaRole.BUTTON
		);
		
		
		cartbutton = page.locator("#pos-cart-toggle");
		
		customerdropdown = page.locator("#pos-customer-select");
		
		deliveryradio = page.locator("input[name='pos-delivery'][value='delivery']");
		
		deliveryaddress = page.locator("#pos-address");
		
		onlineradio = page.locator("input[name='pos-payment'][value='online']");
		
		cardnumber = page.locator("#pos-pay-card-number");
		
		expiry = page.locator("#pos-pay-card-expiry");
		
		cvv = page.locator("#pos-pay-card-cvv");
		
		nameoncard = page.locator("#pos-pay-card-name");
		
		pay = page.locator("#pos-pay-pay-submit");
				
		placeorderbutton = page.locator("#pos-place-order");
				
		//UPDATED DETAILS ON PAGE LOCATORS
		
	    phonetext = page.locator("div.text-muted strong").nth(0);
		emailtext = page.locator("div.text-muted strong").nth(1);
		addresstext = page.locator("div.text-muted strong").nth(2);
		
	}
		
		//ACTIONS
		
		public void clickEdit() {
			
			editbutton.click();
			
		}
		
		public void enterphone(String phone) {
			
			phonefield.fill(phone);
			
		}
		
		public void enteremail(String email) {
			
			emailfield.fill(email);
			
		}
		
		public void enteraddress(String address) {
			
			addressfield.fill(address);
			
		}
		
		public void clicksave() {
			
			savebutton.click();
			
		}
		
		public void clickApplyCoupon() {
			
		    applycouponbutton.click();
		    
		}
		
		public void clickCart1() {
			
		    cartbutton.click();
		    
		}

		public void clickPlaceOrder() {
			
		    placeorderbutton.click();
		    
		}
		
		//GET THE INFO
		
		public Locator getphonetext() {
			
			return phonetext;
			
		}
		
		public Locator getemailtext() {
			
			return emailtext;
			
		}
		
		public Locator getaddresstext() {
			
			return addresstext;
			
		}
		
		public Locator getApplyCouponButton() {
			
		    return applycouponbutton;
		    
		}
		
	    // PRODUCTS

	    public void addBaguette() {
	    	
	        baguetteaddbutton.click();
	        
	    }

	    public void addBrownie() {
	    	
	        brownieaddbutton.click();
	    }
	    
	    public void clickCart() {
	    	
	        cartbutton.click();
	        
	    }
	    
	    public void selectCustomerByName(String customerName) {

	        customerdropdown.selectOption(new SelectOption().setLabel(customerName));
	        
	    }
	    
	    public void selectDelivery() {
	    	
	        deliveryradio.check();
	        
	    }
	    
	    public void enterDeliveryAddress(String address) {
	        
	        deliveryaddress.fill(address);
	        
	    }
	    
	    public void selectOnline() {
	    	
	        onlineradio.check();
	        
	    }

	    public void enterCardNumber(String cardNumber) {
	    	
	        cardnumber.fill(cardNumber);
	        
	    }
	    
	    public void enterExpiry(String expiryValue) {
	    	
	        expiry.fill(expiryValue);
	        
	    }

	    public void enterCVV(String cvvValue) {
	    	
	        cvv.fill(cvvValue);
	        
	    }
	    
	    public void enterNameOnCard(String name) {
	    	
	        nameoncard.fill(name);
	        
	    }

	    public void clickPay() {
	    	
	        pay.click();
	        
	    }
	    
	    public void clickPlaceOrder1() {
	    	
	        placeorderbutton.click();
	        
	    }

	    
		public void takeContactDetailsScreenshot(String path) {

		    Locator contactDetailsCard =
		            page.locator("div.card:has(#edit-bakery-contact)");

		    contactDetailsCard.scrollIntoViewIfNeeded();

		    assertThat(contactDetailsCard).isVisible();

		    contactDetailsCard.screenshot(
		            new Locator.ScreenshotOptions()
		                    .setPath(Paths.get(path))
		    );
		}
		
}
	
