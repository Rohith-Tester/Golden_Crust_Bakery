package pages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	// GET DELIVERY OTP

//	public String getdeliveryotp() {
//
//		String text = page.locator("body").innerText();
//
//		Pattern pattern = Pattern.compile(
//				"DELIVERY\\s*OTP[\\s\\S]{0,100}?([0-9]\\s*[0-9]\\s*[0-9]\\s*[0-9])",
//				Pattern.CASE_INSENSITIVE
//		);
//
//		Matcher matcher = pattern.matcher(text);
//
//		if (matcher.find()) {
//
//			return matcher.group(1)
//					.replaceAll("\\s+", "");
//
//		}
//
//		throw new RuntimeException(
//				"Delivery OTP not found on Track Order page"
//		);
//
//	}
	
//	public String getdeliveryotp() {
//
//	    page.waitForTimeout(1000);
//
//	    String bodytext = page.locator("body").innerText();
//
//	    System.out.println(
//	            "========== TRACK ORDER PAGE TEXT =========="
//	    );
//
//	    System.out.println(bodytext);
//
//	    System.out.println(
//	            "==========================================="
//	    );
//
//	    throw new RuntimeException(
//	            "DEBUG : Check console for Track Order page text"
//	    );
//	}
	
	public String getdeliveryotp() {

	    Locator otpSection = page.locator("text=DELIVERY OTP")
	            .locator("..");

	    String text = otpSection.innerText();

	    return text.replaceAll(
	            "(?s).*DELIVERY\\s*OTP\\s*([0-9]{4}).*",
	            "$1"
	    );
	}

	// GET RETURN OTP

	public String getreturnotp() {

		String text = page.locator("body").innerText();

		Pattern pattern = Pattern.compile(
				"DELIVERY\\s*OTP[\\s\\S]{0,100}?([0-9]\\s*[0-9]\\s*[0-9]\\s*[0-9])",
				Pattern.CASE_INSENSITIVE
		);

		Matcher matcher = pattern.matcher(text);

		if (matcher.find()) {

			return matcher.group(1)
					.replaceAll("\\s+", "");

		}

		throw new RuntimeException(
				"Return OTP not found on Track Order page"
		);

	}

}