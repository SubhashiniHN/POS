package Base;

import com.microsoft.playwright.Page;

import factory.PlaywrightFactory;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected Page page;

    @BeforeClass
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        page = PlaywrightFactory.initBrowser(browser);
    }

    @AfterClass
    public void tearDown() {
        PlaywrightFactory.closeBrowser();
    }
}
