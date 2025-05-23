package org.cucumbertaf.corelib;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import org.cucumbertaf.utils.PropertyUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverClass {
	private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return driverThreadLocal.get();
	}

	public static void setDriverNull() {
		if (getDriver() != null)
		{
			getDriver().close();//bug fix?
			getDriver().quit();
		}
		driverThreadLocal.set(null);
	}

	public static WebDriver getDriverInstance(String browserName) throws Exception {
		if (driverThreadLocal.get() != null) {
			return getDriver();
		}
		switch (browserName) {
		case "chrome":
		case "gc":
			System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
			//Modified by Kuldeep
			Map<String, Object> preferences = new HashMap<>();
			preferences.put("profile.default_content_settings.popups", 0);
			String rdPath = PropertyUtil.getProperty("selenium.chrome.download.default_directory").replaceAll("/",
					Matcher.quoteReplacement(File.separator));
			String dPath = System.getProperty("user.dir") + File.separator + rdPath;
			preferences.put("download.default_directory", dPath);
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("prefs", preferences);
			driverThreadLocal.set(new ChromeDriver(chromeOptions));
			break;
		case "edge":
		case "msedge":
			System.setProperty("webdriver.edge.driver", "src/test/resources/drivers/msedgedriver.exe");
			driverThreadLocal.set(new EdgeDriver());
			break;
		case "ff":
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
			driverThreadLocal.set(new FirefoxDriver());
			break;
		default:
			throw new RuntimeException("Invalid browser name provided " + browserName);
		}
		driverThreadLocal.get().manage().window().maximize();
		return driverThreadLocal.get();
	}

}
