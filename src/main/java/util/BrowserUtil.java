package util;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.openqa.selenium.Cookie;
import java.util.Set;

public class BrowserUtil {
    private static Browser browser = AqualityServices.getBrowser();

    public static void goTo(String authUrl) {
        browser.goTo(authUrl);
    }

    public static void sendTokenWithCookie(String token) {
        Cookie cookie = new Cookie("token", token);
        browser.getDriver().manage().addCookie(cookie);
        refresh();
    }

    public static String getWindowHandle() {
        return browser.getDriver().getWindowHandle();
    }

    public static Set<String> getWindowHandles() {
        return browser.getDriver().getWindowHandles();
    }

    public static void switchToWindow(String window) {
        browser.getDriver().switchTo().window(window);
    }

    public static void closeWindow() {
        browser.executeScript("window.close()");
    }

    public static void refresh() {
        browser.getDriver().navigate().refresh();
    }

    public static byte[] getScreenshot() {
        return browser.getScreenshot();
    }

    public static String getLogs() {
        return "2023-10-17 22:58:25 INFO  - Link '' :: Element's text: [KS-6280 Verify that Buyer user can be deleted]";
    }
}