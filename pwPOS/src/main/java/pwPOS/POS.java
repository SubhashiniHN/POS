package pwPOS;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class POS {
	   public static void main(String[] args) {
	        try (Playwright playwright = Playwright.create()) {
	            Browser browser = playwright.chromium().launch();
	            Page page = browser.newPage();
	            page.navigate("https://www.pos.com.my/send/ratecalculator");
	            System.out.println(page.title());
	        }
	    }
}
