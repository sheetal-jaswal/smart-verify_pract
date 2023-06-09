package com.hcl.sv.baseutil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CreateDriver {

	private static CreateDriver instance = null;

	private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

	/* constructor */
	private CreateDriver() {
	}

	/**
	 * getInstance method to retrieve active driver instance
	 *
	 * @return CreateDriver
	 */
	public static CreateDriver getInstance() {
		if (instance == null) {
			instance = new CreateDriver();
		}
		return instance;
	}

	public DesiredCapabilities setWebDriverCapabilities(String browser, DesiredCapabilities capabilities) {

		switch (browser) {
		case "ie":
			capabilities = new DesiredCapabilities().internetExplorer();
			break;
		case "firefox":
			capabilities = new DesiredCapabilities().firefox();
			break;
		case "chrome":

			capabilities = new DesiredCapabilities().chrome();

			break;
		case "edge":
			capabilities = new DesiredCapabilities().edge();
			break;
		case "safari":
			capabilities = new DesiredCapabilities().safari();
			break;
		default:
			capabilities = null;
		}

		return capabilities;
	}

	/**
	 * Description :Sets the web driver according to the
	 * 
	 * @author Manikanda A
	 * @param browser
	 * @param capabilities
	 * @param LOCAL_HUB_URL
	 * @param chromeOptions
	 * @param firefoxOptions
	 * 
	 */
	public WebDriver setWebDriver(String browser, DesiredCapabilities capabilities, String LOCAL_HUB_URL)
			throws IOException {

		switch (browser) {
		case "Ie":
			capabilities = new DesiredCapabilities().internetExplorer();
			break;
		case "Firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setHeadless(true);
			ProfilesIni profile = new ProfilesIni();
			FirefoxProfile testprofile = profile.getProfile("ShreyaU");
			testprofile.setPreference("dom.webnotifications.enabled", false);
			testprofile.setPreference("dom.push.enabled", false);
			capabilities = new DesiredCapabilities();
			capabilities.setBrowserName(BrowserType.FIREFOX);
			capabilities.setCapability(FirefoxDriver.PROFILE, testprofile);
			capabilities.setCapability(firefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
			firefoxOptions.merge(capabilities);
			break;
		case "Chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			// chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--disable-popup-blocking");
			chromeOptions.addArguments("--disable-infobars");
			capabilities = new DesiredCapabilities();
			capabilities.setBrowserName(BrowserType.CHROME);
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			chromeOptions.merge(capabilities);
			break;
		case "Edge":
			EdgeOptions edgeoptions = new EdgeOptions();
			capabilities = new DesiredCapabilities();
			capabilities.setBrowserName(BrowserType.EDGE);
			capabilities.setPlatform(Platform.WINDOWS);
			break;
		case "Safari":
			capabilities = new DesiredCapabilities().safari();
			break;
		default:
			capabilities = null;
		}

		try {
			return new RemoteWebDriver(new URL(LOCAL_HUB_URL), capabilities);

		} catch (MalformedURLException e) {

			BaseTest.logger.info("The given HUB URL is Malformed");

		}
		return null;
	}


	/**
	 * Description: launch the browser according to the mentioned browser name.
	 * 
	 * @author Manikanda A
	 * @param browserName
	 */
	public static synchronized WebDriver launchBrowserCode(String browserName) {
		if (browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			String userPath = System.getProperty("user.home") + "\\AppData\\Local\\Google\\Chrome\\UserData";
			ChromeOptions chromeOpt = new ChromeOptions();
//			chromeOpt.addArguments("incognito");
//			chromeOpt.addArguments("user-data-dir=" + userPath);
			return new ChromeDriver(chromeOpt);
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			return new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("Edge")) {
//				System.setProperty("webdriver.edge.driver", "./drivers/msedgedriver.exe");
//				String userPath = System.getProperty("user.home") + "\\AppData\\Local\\Microsoft\\Edge\\UserData";
//				EdgeOptions edgeopt = new EdgeOptions();
//				edgeopt.addArguments("user-data-dir=" + userPath);
//				return new EdgeDriver(edgeopt);
		} else {
			BaseTest.logger.error("Mentioned browser code need to implement");
		}
		return null;
	}

	
}
