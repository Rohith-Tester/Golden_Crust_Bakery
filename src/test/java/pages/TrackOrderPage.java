package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class TrackOrderPage {
	
	private Page page;
	
	private Locator trackordertf;
	private Locator trackorderbutton;
	
	public TrackOrderPage(Page page) {
		
		this.page = page;
		
		trackordertf = page.locator("#track-input");
		
		trackorderbutton = page.locator("#track-btn");
		
	}
		
		public void entertrackingnumber(String orderid) {
			
			trackordertf.fill(orderid);
			
		}
		
		public void clicktrackorderbutton() {
			
			trackorderbutton.click();
			
		}
		
	}
