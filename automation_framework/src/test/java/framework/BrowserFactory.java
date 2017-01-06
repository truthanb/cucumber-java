package framework;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {
    private static Map<String, WebDriver> drivers = new HashMap<>();

    static WebDriver getBrowser(String browserName) {
        WebDriver driver = null;

        switch (browserName){
            case "Firefox":
                driver = drivers.get("Firefox");
                if (driver == null) {
                    driver = new FirefoxDriver();
                    drivers.put("Firefox", driver);
                }
                break;
            case "IE":
                driver = drivers.get("IE");
                if (driver == null) {
                    System.setProperty("webdriver.ie.driver", "\\src\\test\\resources\\browser_drivers\\IEDriverServer.exe");
                    DesiredCapabilities cap = new DesiredCapabilities();
                    cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                    driver = new InternetExplorerDriver();
                    drivers.put("IE", driver);
                }
                break;
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", "\\src\\test\\resources\\browser_drivers\\chromedriver.exe");
                driver = drivers.get("Chrome");
                if (driver == null) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("chrome.switches", "--disable-extensions");
                    driver = new ChromeDriver(options);
                    drivers.put("Chrome", driver);
                }
                break;
            case "Edge":
                System.setProperty("webdriver.edge.driver", "\\src\\test\\resources\\browser_drivers\\MicrosoftWebDriver.exe");
                driver = drivers.get("Edge");
                if (driver == null) {
                    driver = new EdgeDriver();
                    driver.manage().window().maximize();
                    drivers.put("Edge", driver);
                }
                break;
            case "Headless":case "No browser":
                return null;
        }
        assert driver!=null;
        driver.manage().window().maximize();
        return driver;
    }

    public static void closeAllDrivers() {
        for (String key : drivers.keySet()) {
            drivers.get(key).close();
            drivers.get(key).quit();
        }
    }
}
