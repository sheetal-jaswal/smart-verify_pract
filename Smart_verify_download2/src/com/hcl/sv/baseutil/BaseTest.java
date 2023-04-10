package com.hcl.sv.baseutil;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.hcl.sv.commonutils.FileOperation;
import com.hcl.sv.reports.Extent;
import com.hcl.sv.reports.ExtentManager;
import com.hcl.sv.util.WebActionUtil;

/***********************************************************************
 * Description : Implements Application Precondition and Postcondition.
 * 
 * @author : Manikandan A
 * @BeforeSuite: Creates all the folder structure for Extent Reports
 * @BeforeClass : Launches the browser according to the browser name specified.
 * @BeforeMethod : Launches the application
 * @AfterClass : Closes the browser after completion of execution
 * @AfterSuite: Kills the driver (example chromedriver.exe) according to browser
 *              specified.
 */

public class BaseTest {

	public WebDriver driver;
	public static Properties prop;
	public static Properties prop_constants;
	public static final int ITO = 40;
	public static final int ETO = 90;
	public static String sDirPath = System.getProperty("user.dir");
	public static String sHomePath = System.getProperty("user.home");
	public static Logger logger = LoggerFactory.getLogger(BaseTest.class);
	public static WebActionUtil actionUtil;
	public String testCaseName;
	public static final String CONFIGPATH = sDirPath + "./config/config.properties";
	public static final String VALIDATIONS_CONSTANTS = sDirPath + "./data/Validation_Constants.properties";
	public static final String SAMPLEFILESPATH = sDirPath + "./data/SampleFiles";
	public static final String TESTDATAEXCELPATH = sDirPath + "./data/Data.xlsx";
	public static String className;
	public static InitializePages pages;
	static {
		try {
			prop = new Properties();
			prop_constants = new Properties();
			FileInputStream fis = new FileInputStream(CONFIGPATH);
			prop.load(fis);
			FileInputStream fis1 = new FileInputStream(VALIDATIONS_CONSTANTS);
			prop_constants.load(fis1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Description : Creates folder structure for Extent reports.
	 * 
	 * @author: Manikandan A
	 */
	@BeforeSuite(alwaysRun = true)
	public synchronized void createFiles() {
		try {
			logger.info("Folder creation for Extent");
			FileOperation fileOperation = new FileOperation();
			fileOperation.CreateFiles();
			// Runtime.getRuntime().exec("wscript " + System.getProperty("user.dir") +
			// "/PreventScreenLock.vbs");
		} catch (Exception e) {
			logger.error("Exception while report inititation");
		}

	}

	/**
	 * Description: Launches the browser as specified in the parameter
	 * 
	 * @author:Manikandan A
	 * @param :browserName
	 */

	@Parameters({ "browserName" })
	@BeforeClass
	public synchronized void launchApp(String browserName) {
		className = getClass().getSimpleName();
		logger = LoggerFactory.getLogger(getClass().getName());

		ExtentTest parentExtentTest = Extent.createTest(getClass().getSimpleName());
		ExtentManager.setParentReport(parentExtentTest);

		driver = CreateDriver.getInstance().launchBrowserCode(browserName);
		driver.manage().timeouts().implicitlyWait(ITO, TimeUnit.SECONDS);
		actionUtil = new WebActionUtil(driver, ETO);
		actionUtil.deleteXlFilesFromDownloads(System.getProperty("user.home") + "/Downloads");
		driver.manage().window().maximize();
	}

	/**
	 * Description: Closes the browser
	 * 
	 * @author:Manikandan A
	 */
	@AfterClass(alwaysRun = true)
	public synchronized void closeBrowser() {

		try {
			if (driver != null) {

				driver.quit();

			} else {
				logger.error("Unable to close the driver");
			}
		} catch (Exception e) {

		}

	}

	/**
	 * Description: Kills the driver in Task Manager as specified in the parameter.
	 * 
	 * @author:Manikandan A
	 * @param :browserName
	 */
	@AfterSuite(alwaysRun = true)
	@Parameters({ "browserName" })
	public synchronized void killTask(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			} else if (browserName.equalsIgnoreCase("firefox")) {

				Runtime.getRuntime().exec("taskkill /IM firefox.exe /F");
			} else if (browserName.equalsIgnoreCase("edge")) {

				Runtime.getRuntime().exec("taskkill /F /IM MicrosoftEdgeCP.exe");
			} else {
				logger.error("Browser name not specified properly to kill the task");
			}
		} catch (IOException e) {

		}
	}

	/**
	 * Description: Creates nodes for the test methods in Extent report.
	 * 
	 * @author Manikandan A
	 * @param methodName
	 * @param browserName
	 */
	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browserName" })
	public synchronized void setExtentReport(String browserName, Method methodName) {
		this.testCaseName = methodName.getName();
		String description = methodName.getAnnotation(Test.class).description();
		try {
			
			String appurl = prop.getProperty("tech_url");
			driver.get(appurl);
//			new WebDriverWait(driver, 80).until(ExpectedConditions.urlToBe(appurl));
			pages =new InitializePages(driver, ETO, actionUtil);
			ExtentTest testReport = ExtentManager.getParentReport().createNode(testCaseName, description);
			ExtentManager.setTestReport(testReport);
			if (browserName == null)
				browserName = "default";
			if (driver.getWindowHandles().size() > 0) {
				WebActionUtil.validationinfo(browserName + " Browser is launched", "blue");
				WebActionUtil.info(browserName + " Browser is launched");
			} else {
				WebActionUtil.fail("Failed to launch the Browser");
				WebActionUtil.error("Failed to launch the Browser");
			}
		} catch (Exception e) {
			e.printStackTrace();
			WebActionUtil.error(e.getMessage());
			throw new SkipException("Failed to launch the application");
			
		}
	}
	
}
