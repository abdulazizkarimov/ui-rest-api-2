package base;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    @BeforeMethod
    public void init() {
        AqualityServices.getBrowser().maximize();
    }

    @AfterMethod
    public void destroy() {
        AqualityServices.getBrowser().quit();
    }
}