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

	public String getdeliveryotp() {

		String text = page.locator("body").innerText();

		Pattern pattern = Pattern.compile(
				"DELIVERY\\s*OTP\\s*([0-9]\\s*[0-9]\\s*[0-9]\\s*[0-9])",
				Pattern.CASE_INSENSITIVE
		);

		Matcher matcher = pattern.matcher(text);

		if (matcher.find()) {

			return matcher.group(1)
					.replaceAll("\\s+", "");

		}

		throw new RuntimeException(
				"Delivery OTP not found"
		);

	}

	public String getreturnotp() {

		String text = page.locator("body").innerText();

		Pattern pattern = Pattern.compile(
				"DELIVERY\\s*OTP\\s*([0-9]\\s*[0-9]\\s*[0-9]\\s*[0-9])",
				Pattern.CASE_INSENSITIVE
		);

		Matcher matcher = pattern.matcher(text);

		if (matcher.find()) {

			return matcher.group(1)
					.replaceAll("\\s+", "");

		}

		throw new RuntimeException(
				"Return OTP not found"
		);

	}

}