package tests;

import static org.testng.Assert.assertTrue;

import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.microsoft.playwright.ElementHandle.ScreenshotOptions;
import com.microsoft.playwright.Page;

import Base.BaseTest;
import pages.HomePage;

public class HomePageTest extends BaseTest {
    
    private HomePage homePage;  
    private String url;         

    @BeforeClass
    @Parameters({"url"})
    public void setUpUrl(@Optional("https://www.pos.com.my/send/ratecalculator") String url) {
        this.url = url;  
    }

    @BeforeMethod
    public void setUpHomePage() {
        homePage = new HomePage(page);  
    }

    @Test
    public void HomeTest() {
        // Navigate to Home Page using global URL
        homePage.navigateToLoginPage(url);

        // Validate title
        String actualTitle = homePage.getPageTitle();
        Assert.assertEquals(actualTitle, "Parcel Shipment Rate Calculator | Pos Malaysia");
    }

    @Test
    public void rateCalculatorTest() {
    	
    	assertTrue(homePage.getCountryField().isVisible());
    	homePage.calculateRate();
    	Assert.assertEquals(homePage.getQuoute().innerText(), "Your Quote");
    	homePage.validateMultipleQuotes();
    	page.mouse().wheel(0,600);
    	page.screenshot(new Page.ScreenshotOptions()
    			  .setPath(Paths.get("screenshot.png"))
    			  .setFullPage(true));
    }
}
