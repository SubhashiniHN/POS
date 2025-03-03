package pages;

import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

public class HomePage {
    private Page page;

    // Locators
    private String countryField = "#country";
    private String countryFillVal = "input[role='combobox']";
    private String weightField = "//input[@placeholder='eg. 0.1kg']";
    private String fromPostCodeField = "input[type='number'][formcontrolname='postcodeFrom']";
    private String toPostCodeField = "input[formcontrolname='postcodeTo']";
    private String calculateButton = "//a[normalize-space()='Calculate']";
    private String yourQuoteHeading = "//h1[normalize-space()='Your Quote']";


    // Constructor
    public HomePage(Page page) {
        this.page = page;
    }

    // Actions
    public void navigateToLoginPage(String url) {
        page.navigate(url);
    }

    public void getCountryDropdown(String countryVal) {
    	 // Locate and click the dropdown
        Locator dropdown = page.locator(countryField);
        dropdown.click();

        // Fill the input field with the country value
        Locator inputField = page.locator(countryField);
        inputField.fill(countryVal);

        // Build the dynamic selector for the option
        String optionSelector = String.format("small[title='%s']", countryVal);

        // Wait for the option to appear
        page.waitForSelector(optionSelector, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));

        // Click the option if found
        Locator option = page.locator(optionSelector);
        if (option.isVisible()) {
            option.click();
            System.out.println("Selected: " + countryVal);
        } else {
            System.out.println("Option not found: " + countryVal);
        }
    }
    
    public Locator getCountryField() {
    	return page.locator(countryField);
    }

    public void enterWeight(String weightVal) {
        page.fill(weightField, weightVal);
    }

    public void enterFromPostCode(String fromPostCodeVal) {
        page.fill(fromPostCodeField, fromPostCodeVal);

    }

    public void enterToPostCode(String toPostCodeVal) {
        page.fill(toPostCodeField, toPostCodeVal);
    }

    public void getCalculateButton() {
        page.click(calculateButton);
    }
    public String getPageTitle() {
        return page.title();
    }
    
    public void selectCountry(String countryTitle) {
    	
    	page.click(countryField);
        
    	// Fill the input field with the country value
        Locator inputField = page.locator(countryFillVal);
        inputField.clear();
        inputField.fill(countryTitle);
        // Open dropdown
        
        // Wait for options to appear
        page.waitForSelector("[role='option']");
        
        // Click the specific country option
        page.click("[role='option'] [title='India - IN']");
    }
    
    public void calculateRate() {
    	//getCountryField().click();
    	selectCountry("India");
    	enterFromPostCode("35600");
    	page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    	page.locator(weightField).click();   	
    	enterWeight("1");
    	getCalculateButton();
    }
    
    public Locator getQuoute() {
    	return page.locator(yourQuoteHeading);
    }
    
    public void validateMultipleQuotes() {
    	 // Locate all <dt+dd> elements inside .border-b
        Locator dtElements = page.locator(".border-b dt + dd");

        // Get text contents of all <dt> elements
        List<String> dtTexts = dtElements.allTextContents();
        
        // Verify if each <dt+dd> is displayed
        for (int i = 0; i < dtElements.count(); i++) {
            Locator dt = dtElements.nth(i);
            boolean isVisible = dt.isVisible();
            System.out.println("DT Text: " + dtTexts.get(i) + " | Displayed: " + isVisible);
        }
    }
}
