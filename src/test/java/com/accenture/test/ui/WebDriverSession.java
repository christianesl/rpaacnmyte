package com.accenture.test.ui;

import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.accenture.test.common.SystemProperties;

/**
 ****************************************************************************
 * HIGHLIGHTS:
 * > Reusable methods for all projects and focused on generate the driver.
 * > The "getDriverInstance" creates a new instance of the driver or gets the existing.
 ****************************************************************************
 */

public class WebDriverSession {
	static WebDriver webDriver = null;

	protected static HashMap<Long, WebDriver> map = new HashMap<Long, WebDriver>();
	
    /**
     * OBJECTIVE: Get the Webdriver instance so It can be reused during the script.
     */
	public static WebDriver getWebDriverSession() {
		WebDriver toReturn = map.get(Thread.currentThread().getId());
		if (toReturn == null) {
			loadNewWebDriverSession();
			System.out.println("NEW Instance Created");
			toReturn = map.get(Thread.currentThread().getId());
		} 
		return toReturn;
	}
	
    /**
     * OBJECTIVE: Load a NEW session of driver when there is not an existing to work on.
     */	
	public static void loadNewWebDriverSession() {
		WebDriver webDriver = null;
		String browserType = "CHROME";
		System.out.print("Browser to be utilized: " + browserType);
		
		try {
			DesiredCapabilities capabilities;
			switch (browserType) {
			case "IE":
				System.setProperty("webdriver.ie.driver", SystemProperties.IE_WEBDRIVER);								
				capabilities = DesiredCapabilities.internetExplorer();
				
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				capabilities.setCapability("dom.forms.number", false);
				capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
				capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "about:blank");
				capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setPlatform(Platform.ANY);
				if (SystemProperties.REMOTE) {
					webDriver = new RemoteWebDriver(new URL(SystemProperties.SELENIUM_GRID_URL), capabilities);
					System.out.println("Remote Grid: " + SystemProperties.SELENIUM_GRID_URL);  
				} else {
					System.out.println(" Use the IE explorer ");					
					webDriver = new InternetExplorerDriver(capabilities);
					}
				break;
			case "CHROME":
				System.getenv();
				System.setProperty("webdriver.chrome.driver", "drivers\\win_chromedriver.exe");
				capabilities = DesiredCapabilities.chrome();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type", "start-maximized", "--lang=en");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);				
				capabilities.setPlatform(Platform.ANY);				
			/*	if (SystemProperties.REMOTE) { //if (SystemProperties.REMOTE)
					System.out.println("Remote Grid to be used: " + SystemProperties.SELENIUM_GRID_URL);
					webDriver = new RemoteWebDriver(new URL(SystemProperties.SELENIUM_GRID_URL), capabilities);
				 	} else {*/
					System.out.println(" Use the CHROME ");					
					webDriver = new ChromeDriver(capabilities);
						/*}*/
				break;
			default:
				throw new RuntimeException("Browser type not supported");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Mximize window");
		webDriver.manage().window().maximize();
		System.out.println("Implicit waits");
		webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		System.out.println("Cookies deleted");
		webDriver.manage().deleteAllCookies();
		System.out.println("Session ID saved");
		map.put(Thread.currentThread().getId(), webDriver);
	}
}

