package org.testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; // log4j
import org.apache.logging.log4j.Logger; // log4j
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter.Red;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;

	public Logger logger;

	@BeforeClass(groups = { "Sanity", "Regression", "Master" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String browserName) throws IOException {

		logger = LogManager.getLogger(this.getClass());

		if (readDataFromPropertiesFile("execution_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

			if (os.equalsIgnoreCase("windows")) {

				desiredCapabilities.setPlatform(Platform.WIN10);
			}

			else if (os.equalsIgnoreCase("linux")) {

				desiredCapabilities.setPlatform(Platform.LINUX);
			}
			
			else if (os.equalsIgnoreCase("mac")) {

				desiredCapabilities.setPlatform(Platform.MAC);
			}

			else {

				System.out.println("No matching os");
			}

			switch (browserName.toLowerCase()) {

			case "chrome":

				desiredCapabilities.setBrowserName("chrome");

				break;

			case "edge":

				desiredCapabilities.setBrowserName("MicrosoftEdge");

				break;

			case "firefox":

				desiredCapabilities.setBrowserName("firefox");

				break;

			default:

				System.out.println("No matching browser");

				break;
			}

			driver = new RemoteWebDriver(new URL("http://192.168.1.3:4444/wd/hub"), desiredCapabilities);

		}

		if (readDataFromPropertiesFile("execution_env").equalsIgnoreCase("local")) {

			switch (browserName.toLowerCase()) {

			case "chrome":

				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();

				break;

			case "edge":

				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();

				break;

			case "firefox":

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();

				break;

			default:

				System.out.println("Provide valid browser Name.....");

				break;
			}
		}

		driver.manage().deleteAllCookies();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.manage().window().maximize();

		driver.get(readDataFromPropertiesFile("appUrl"));

	}

	@AfterClass(groups = { "Sanity", "Regression", "Master" })
	public void tearDown() {

		driver.quit();

	}

	// Reusable Methods

	public String randomString() {

		String randomAlphabetic = RandomStringUtils.randomAlphabetic(6);

		return randomAlphabetic;

	}

	public String randomNumber() {

		String randomNumeric = RandomStringUtils.randomNumeric(10);

		return randomNumeric;

	}

	public String randomAlphaNumeric() {

		String randomAlphanumeric = RandomStringUtils.randomAlphanumeric(6);

		return randomAlphanumeric;

	}

	public String readDataFromPropertiesFile(String propertyKey) throws IOException {

		File file = new File("src\\test\\resources\\config.properties");

		FileReader fileReader = new FileReader(file);

		Properties properties = new Properties();

		properties.load(fileReader);

		String propertyValue = properties.getProperty(propertyKey);

		return propertyValue;

	}

	public String captureScreen(String tname) {

		/*
		 * SimpleDateFormat simpleDateFormat = new
		 * SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); Date date = new Date(); String
		 * currentDateTimeStampformat = simpleDateFormat.format(date);
		 */

		// Or

		String currentDateTimeStampformat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\Screenshots\\" + tname + "_"
				+ currentDateTimeStampformat + ".jpeg";

		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;

		// or

		// Type Casting - Takescreenshot - webdriver
		/*
		 * TakesScreenshot takesScreenshot = (TakesScreenshot) driver; File sourceFile =
		 * takesScreenshot.getScreenshotAs(OutputType.FILE); File targetFile = new
		 * File("ErrorImages\\" + System.currentTimeMillis() + ".jpeg");
		 * 
		 * FileUtils.copyFile(sourceFile, targetFile);
		 */

	}
}
