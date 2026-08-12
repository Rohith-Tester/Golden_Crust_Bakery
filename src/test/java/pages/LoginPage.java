package pages;

import java.nio.file.Paths;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {
	
	private Page page;
	
	//LOCATORS
	private Locator usernamefield;
	private Locator passwordfield;
	private Locator loginbutton;
	private Locator loginerror;
	
	public LoginPage(Page page) {
		
		this.page = page;
		
		usernamefield = page.locator("#li-username");
		
		passwordfield = page.locator("#li-password");
		
		loginbutton = page.locator("#login-btn");
		
		loginerror = page.locator("#login-error");
		
	}
	
	//ACTIONS
	public void enterusername(String username) {
		
		usernamefield.fill(username);
		
	}
	
	public void enterpassword(String password) {
		
		passwordfield.fill(password);
		
	}
	
	public void clicklogin() {
		
		loginbutton.click();

	}
	
	public void login(String username, String password) {
	    enterusername(username);
	    enterpassword(password);
	    clicklogin();
	    
	}
	
	public void takeloginerrorscreenshot(String path) {
		
		loginerror.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(path)));
		
	}

}
