package com.hcl.sv.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.hcl.sv.baseutil.BaseTest;
import com.hcl.sv.commonutils.ExcelUtil;
import com.hcl.sv.reports.ExtentManager;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;
import com.paulhammant.ngwebdriver.NgWebDriver;

/**
 * Description : All the action utilities added in this class e.g
 * clickonelement,WaitforElement etc
 * 
 * @author : Manikandan A
 */

public class WebActionUtil {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public long ETO;
	public static JavascriptExecutor jsExecutor;
	public static Actions action;
	public static TakesScreenshot screenshot;
	private static Select selectValue;
	public String mainWindowID;

	public WebActionUtil(WebDriver driver, long ETO) {
		this.driver = driver;
		this.ETO = ETO;
		wait = new WebDriverWait(driver,ETO);
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
		screenshot = (TakesScreenshot) driver;

	}


	/**
	 * Description Method for the info updation in extent Report
	 * 
	 * @author Manikandan A
	 * @param message
	 */
	public static void pass(String message) {
		ExtentManager.getTestReport().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
	}

	/**
	 * Description Method to provide info in the log,testNg reports
	 * 
	 * @author Manikandan A
	 * @param message
	 */
	public static void info(String message) {
		BaseTest.logger.info(message);
	}

	/**
	 * Description Method to provide info in the extent report
	 * 
	 * @author Manikandan A
	 * @param message
	 */
	public static void extentinfo(String message) {
		ExtentManager.getTestReport().info(message);
	}

	/**
	 * 
	 * Description Method for the error updation in extent Report
	 * 
	 * @author Manikandan A
	 * @param message
	 */
	public static void fail(String message) {
		ExtentManager.getTestReport().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
	}

	/**
	 * 
	 * Description Method for the info/error updation in extent Report
	 * 
	 * @author Manikandan A
	 * @param message
	 * @param color
	 */
	public static void validationinfo(String message, String color) {
		if (color.equalsIgnoreCase("blue"))
			ExtentManager.getTestReport().info(MarkupHelper.createLabel(message, ExtentColor.BLUE));
		else if (color.equalsIgnoreCase("green"))
			ExtentManager.getTestReport().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
		else if (color.equalsIgnoreCase("red"))
			ExtentManager.getTestReport().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
	}

	/**
	 * Description Method for the error updation in logs
	 * 
	 * @author Manikandan A
	 * @param message
	 */
	public static void error(String message) {
		BaseTest.logger.error(message);
	}

	/**
	 * 
	 * Description Method for fetching Current Date Time
	 * 
	 * @author Manikandan A
	 */
	public static String getCurrentDateTime() {
		DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate = new Date();
		return customFormat.format(currentDate);
	}

	/**
	 * Description Validate the Text partially
	 * 
	 * @author Manikandan A
	 * @param expected
	 * @param element
	 * @param elementname
	 * @param validationPassMessage
	 * @param validationFailMessage
	 * @param color
	 */
	public synchronized void validatePartialText(String expected, WebElement element, String elementname,
			String validationPassMessage, String validationFailMessage, String color) {
		String actual = null;
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			actual = element.getText();
			Assert.assertTrue(actual.contains(expected));
			info("Expected text : " + expected + " is present in " + actual + " text in the application");
			validationinfo(validationPassMessage, color);
			info(validationPassMessage);
		} catch (Exception e) {
			error("Expected text : " + expected + " is not present in " + actual + " text in the application ");
			error(validationFailMessage);
			fail(validationFailMessage);
		}
	}

	/**
	 * 
	 * Description Method clicks on web element
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param elementName
	 */
	public static synchronized  void clickOnElement(WebElement element, String elementName) {
		try {
			if (isElementClickable(element, elementName)) {
				element.click();
				info("Clicked on : " + elementName);
       			extentinfo("Clicked on : " + elementName);
			} else {
				error("Unable to click on : " + elementName);
				fail("Unable to click on : " + elementName);
				Assert.fail("Unable to click on : " + elementName);
			}
		} catch (ElementClickInterceptedException e) {
			System.out.println("Click on element catch block");
			try {
				jsExecutor.executeScript("arguments[0].click();", element);
				info("Clicked on : " + elementName);
				extentinfo("Clicked on : " + elementName);
			} catch (Exception r) {
				error(r.getMessage());
				fail("Unable to click on : " + elementName);
				Assert.fail("Unable to click on : " + elementName);
			}}
	}

	/**
	 * 
	 * Description Check whether the Element is displayed with expected conditions
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param elementName
	 */
	public static synchronized boolean isElementClickable(WebElement element, String elementName) {

		info("Validate " + elementName + " is clickable");
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {
			error(elementName + " is not clickable");
			return false;
		}
	}

	/**
	 * 
	 * Description Wait for the Element is displayed with expected conditions
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param elementName
	 */
	public static void waitForElement(WebElement element, String elementName) {
		try {
			info("Wait for " + elementName);
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));

		} catch (Exception e) {
			error(elementName + " is not visible");
			Assert.fail(elementName + " is not visible");

		}
	}

	/**
	 * 
	 * Description Method to delete the directory
	 * 
	 * @author Manikandan A
	 * @param pathToDeleteFolder
	 */
	public static void deleteDir(String pathToDeleteFolder) {
		File extefolder = new File(pathToDeleteFolder);
		if ((extefolder.exists())) {
			deleteFolderDir(extefolder);
		}
	}

	/**
	 * Description Method to delete folder directory for time period above 30 days
	 * 
	 * @author Manikandan A
	 * @param pathToDeleteFolder
	 */
	public static void deleteDirectory(String pathToDeleteFolder) {
		try {
			File dir = new File(pathToDeleteFolder);
			File[] files = dir.listFiles();
			if (files.length > 1) {
				for (File file : files) {
					String name = file.getName();
					if (name.toLowerCase().contains("automation")) {
						String[] dateTime = name.split("- ");
						if (calculateDateDifference(dateTime[1]) > 30) {
							String tempFile = new StringBuffer(pathToDeleteFolder).append(File.separator).append(name)
									.toString();
							deleteDir(tempFile);
						}
					}
				}
			}
		} catch (Exception e) {
			error("Unable to delete the directory");
		}
	}

	/**
	 * Description: Method to find difference between current date and mentioned
	 * date
	 * 
	 * @author Manikandan A
	 * @param actualDateTime
	 * @return daysDifference
	 */
	public static synchronized long calculateDateDifference(String actualDateTime) {
		long daysDifference = 0;
		try {
			// create an instance of the SimpleDateFormat class
			DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");

			String currentDateTime = getCurrentDateTime();
			// use parse method to get date object of both dates
			Date date1 = customFormat.parse(actualDateTime);
			Date date2 = customFormat.parse(currentDateTime);
			// calculate time difference in milliseconds
			long timeDifference = date2.getTime() - date1.getTime();
			// calculate time difference in days using TimeUnit class
			daysDifference = TimeUnit.MILLISECONDS.toDays(timeDifference) % 365;

		} catch (Exception e) {
			error("Unable to calculate Date difference");
		}
		return daysDifference;
	}

	/**
	 * 
	 * Description Method to delete folder directory
	 * 
	 * @author Manikandan A
	 * @param folderToDelete
	 */
	public static void deleteFolderDir(File folderToDelete) {
		File[] folderContents = folderToDelete.listFiles();
		if (folderContents != null) {
			for (File folderfile : folderContents) {
				if (!Files.isSymbolicLink(folderfile.toPath())) {
					deleteFolderDir(folderfile);
				}
			}

		}
		folderToDelete.delete();
	}

	/**
	 * Description Capture the screenshot of the complete screen
	 * 
	 * @author Manikandan A
	 * @param path
	 * @return destinationPath
	 */
	public static String getScreenShot(String path) {
		File src = (screenshot.getScreenshotAs(OutputType.FILE));
		String currentDateTime = getCurrentDateTime();
		String destinationPath = path + BaseTest.className + "-" + currentDateTime + ".png";
		File destination = new File(destinationPath);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "./Screenshots/" + BaseTest.className + "-" + currentDateTime + ".png";
	}


	/**
	 * Description To Enter the Text to the Text filed
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param value
	 * @param elementName
	 */
	public static synchronized void typeText(WebElement element, String value, String elementName) {
		try {
			info("Enter the value into " + elementName);
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.sendKeys(value);
			info(value + " typed into " + elementName);
			extentinfo(value + " typed into " + elementName);
		} catch (AssertionError error) {
			error(" Unable to type " + value + " into " + elementName);
			Assert.fail("Unable to type in " + elementName);
		} catch (Exception e) {
			error(" Unable to type " + value + "into " + elementName);
			Assert.fail("Unable to type in " + elementName);
		}
	}
	/**
	 * Description To Select the value by the Visible Text
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param value
	 */
	public synchronized void selectByText(WebElement element, String value) {
		try {
			info("Select the value " + value);
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			selectValue = new Select(element);
			selectValue.selectByVisibleText(value);
			info("Selected the value : " + value +" from the drop down");
			extentinfo("Selected the value : " + value +" from the drop down");
		} catch (Exception e) {
			error("Unable to select the value : " + value);
			fail("Unable to select the value : " + value);
			Assert.fail("Unable to select the value : " + value);
		}

	}

	/**
	 * Description To Select the value by the Visible Text
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param value
	 */
	public synchronized void deSelectByText(WebElement element, String value) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			Select selecvalue = new Select(element);
			selecvalue.deselectByVisibleText(value);
			info("Able to De-select value");
			extentinfo("Able to De-select value");
		} catch (Exception e) {
			error(" Unable to De-select all the value");
			fail(" Unable to De-select all the value");
			Assert.fail("Unable to De-select all the value");
		}

	}

	/**
	 * Description To Select the value by the Visible Text
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param value
	 */
	public synchronized String getFirstSelectedValue(WebElement element) {
		String eleValue = null;
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			Select selecvalue = new Select(element);
			WebElement ele = selecvalue.getFirstSelectedOption();
			eleValue = ele.getText();
			info("Able to De-select value");
			extentinfo("Able to De-select value");
		} catch (Exception e) {
			error(" Unable to De-select all the value");
			fail(" Unable to De-select all the value");
			Assert.fail("Unable to De-select all the value");
		}
		return eleValue;

	}

	/**
	 * Description Scroll to the Element
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param elementName
	 */
	public static synchronized void scrollToElement(WebElement element, String elementName) {
		info("Scroll till the " + elementName);
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
			info("Scroll till the " + elementName + " completed");
		} catch (Exception e) {
			error(elementName + " is not visible because scrolling till that element is failed");
			fail(elementName + " is not visible because scrolling till that element is failed");
			Assert.fail(elementName + " is not visible because scrolling till that element is failed");
		}
	}

	/**
	 * Description Scroll up
	 * 
	 * @author Manikandan A
	 */
	public synchronized void scrollUp() {
		try {
			jsExecutor.executeScript("window.scrollTo(document.body.scrollHeight , 0)");
			info("Scroll up");
		} catch (Exception e) {
			error("Scroll up failed");
		}
	}

	/**
	 * Description: Method to click on Element using JavaScript
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param elementName
	 */
	public synchronized void clickOnElementUsingJS(WebElement element, String elementName) {
		if (isElementClickable(element, elementName)) {
			jsExecutor.executeScript("arguments[0].click();", element);
			info("Clicked on : " + elementName);
			extentinfo("Clicked on : " + elementName);
		} else {
			error("Unable to click on : " + elementName);
			fail("Unable to click on : " + elementName);
			Assert.fail("Unable to click on : " + elementName);
		}
	}

	/**
	 * Description Clear the Text
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param elementName
	 */
	public void clearText(WebElement element, String elementName) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.clear();
			info("Cleared the text present in" + elementName);
		} catch (Exception e) {
			error("Unable to clear the text in " + elementName);
		}
	}

	/**
	 * Description Wait for the angular page to load
	 * 
	 * @author Manikandan A
	 */
	public static synchronized void waitForAngularPageToLoad() {
		try {
			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return Boolean.valueOf(((JavascriptExecutor) driver)
							.executeScript("return (window.angular !== undefined) "
									+ "&& (angular.element(document).injector() !== undefined) "
									+ "&& (angular.element(document).injector().get('$http').pendingRequests.length === 0)")
							.toString());
				}
			};
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(pageLoadCondition);
		} catch (Exception e) {
			error("Unable to load the page correctly");
		}
	}

	/**
	 * Description Validate the Text
	 * 
	 * @author Manikandan A
	 * @param expected
	 * @param element
	 * @param elementname
	 * @param validationPassMessage
	 * @param validationFailMessage
	 * @param color
	 */
	public static synchronized void validatetext(String expected, WebElement element, String elementname,
			String validationPassMessage, String validationFailMessage, String color) {
		wait.until(ExpectedConditions.visibilityOf(element));
		String actual = element.getText();
		try {
			Assert.assertEquals(actual, expected);
			info("Expected text : " + expected + " is matching with the : " + actual + " text in the application ");
			validationinfo(validationPassMessage, color);
			info(validationPassMessage);
		} catch (Exception e) {
			error("Expected text : " + expected + " is not  matching with the : " + actual
					+ " text in the application ");
			error(validationFailMessage);
			fail(validationFailMessage);
		}

	}

	/**
	 * Description :Checks whether an element is visible
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param elementName
	 * 
	 */
	public synchronized boolean isElementVisible(WebElement element, String elemantName) {

		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			info(elemantName + " is visible");
			return true;
		} catch (Exception e) {
			return false;
		}
	}



	/**
	 * Description :File upload by using the image path
	 * 
	 * @author Manikandan A
	 * @param imagePath
	 */
	public static synchronized void upload(String imagePath) {
		StringSelection stringSelection = new StringSelection(imagePath);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		info("Uploaded file Successfully "+imagePath );
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			fail("Unable to upload File "+imagePath);
		}
	}

	/**
	 * Description Click on element using action class
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param elementName
	 */
	public static synchronized void actionClick(WebElement element, String elementName) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			action.click(element).build().perform();
			info("Clicked on " + elementName);
			extentinfo("Clicked on " + elementName);
		} catch (Exception e) {
			error("Unable to click on " + elementName);
			fail("Unable to click on " + elementName);
			Assert.fail("Unable to click on " + elementName);
		}

	}

	/**
	 * Description Mouse over on an element
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param elementName
	 */
	public static synchronized void actionMouseOver(WebElement element, String elementName) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			action.moveToElement(element).build().perform();
			info("Able to mouse over on " + elementName);
			extentinfo("Able to mouse over on " + elementName);
		} catch (Exception e) {
			error("Unable to mouse over on " + elementName);
			fail("Unable to mouse over on " + elementName);
			Assert.fail("Unable to mouse over on " + elementName);
		}

	}

	/**
	 * Description Selects range date from the given from & to date
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param elementName
	 * 
	 */

	public synchronized void selectCalendarRangeDate(WebElement calendarOpen, String fromYear, String fromMonth,
			String fromDate, String toYear, String toMonth, String toDate) {

		List<WebElement> lstMonthSelect = calendarOpen.findElements(By.xpath("//select[@class='monthselect']"));

		List<WebElement> lstYearSelect = calendarOpen.findElements(By.xpath("//select[@class='yearselect']"));

		Select fromMonthSelect = new Select(lstMonthSelect.get(0));
		Select toMonthSelect = new Select(lstMonthSelect.get(1));

		Select fromYearSelect = new Select(lstYearSelect.get(0));
		Select toYearSelect = new Select(lstYearSelect.get(1));

		fromYearSelect.selectByVisibleText(fromYear);
		fromMonthSelect.selectByVisibleText(fromMonth);

		String fromDatePath = "//div[@class='drp-calendar left']/descendant::td[text()='" + fromDate + "']";
		calendarOpen.findElement(By.xpath(fromDatePath)).click();

		toYearSelect.selectByVisibleText(toYear);
		toMonthSelect.selectByVisibleText(toMonth);
		String toDatePath = "//div[@class='drp-calendar right']/descendant::td[text()='" + toDate + "']";
		calendarOpen.findElement(By.xpath(toDatePath)).click();
	}

	/**
	 * 
	 * Description : Wait for given time
	 * 
	 * @author Manikandan A
	 * @param millis
	 */
	public synchronized static void poll(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Description Check whether the Element is displayed with expected conditions
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param elementName
	 * @param validationPassMessage
	 * @param validationFailMessage
	 * @param color
	 */
	public static synchronized void validateisElementDisplayed(WebElement element, String elementName,
			String validationPassMessage, String validationFailMessage, String color) {

		try {
			info("Wait for visiblity of " + elementName);
			wait.until(ExpectedConditions.visibilityOf(element));
			Assert.assertTrue(element.isDisplayed());
			validationinfo(validationPassMessage, color);
			info(validationPassMessage);
		} catch (Exception | AssertionError e) {
			fail(validationFailMessage);
			error(validationFailMessage);
			Assert.fail(validationFailMessage);
		}
	}

	/**
	 * 
	 * Description : Verify the attribute value of an element
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param attribute
	 * @param valuetobecompared
	 * @param elementName
	 * @param validationPassMessage
	 * @param validationFailMessage
	 * @param color
	 */
	public static synchronized void validateAttribute(WebElement element, String attribute, String valuetobecompared,
			String elementName, String validationPassMessage, String validationFailMessage, String color) {
		String actualvalue = element.getAttribute(attribute);
		try {
			wait.until(ExpectedConditions.attributeContains(element, attribute, valuetobecompared));
			Assert.assertEquals(element.getAttribute(attribute), valuetobecompared);
			info("Expected attribute value : " + valuetobecompared + " is matching with the actual attribute value : "
					+ actualvalue + " of " + elementName);
			info(validationPassMessage);
			validationinfo(validationPassMessage, color);
		} catch (Exception e) {
			error("Expected attribute value : " + valuetobecompared
					+ " is not matching with the actual attribute value : " + actualvalue + " of " + elementName);
			error(validationFailMessage);
			fail(validationFailMessage);
			Assert.fail(validationFailMessage);
		}

	}

	/**
	 * 
	 * Description : Wait for the angular page load
	 * 
	 * @author Manikandan A
	 */
	public static synchronized void waitForAngularPageload() {
		NgWebDriver ngDriver = new NgWebDriver(jsExecutor);
		ngDriver.waitForAngularRequestsToFinish();
	}

	/**
	 * 
	 * Description : Get file path
	 * 
	 * @author Manikandan A
	 * @param fileFormat
	 * @return filePath + fileName
	 */

	public synchronized String getSampleFilePath(String fileFormat) {
		String filePath = System.getProperty("user.dir") + "\\data\\SampleFiles\\";
		String fileName = "Sample" + "." + fileFormat;
		return filePath + fileName;

	}

	/**
	 * 
	 * Description : Retrives the value entered in an element
	 * 
	 * @author Manikandan A
	 * @param elementId
	 * @return entereddValue
	 */
	public synchronized String getTextUsingJS(String elementId) {
		String entereddValue = null;
		try {
			poll(1000);
			String script = "return document.getElementById('" + elementId + "').value";
			entereddValue = (String) (jsExecutor.executeScript(script));
		} catch (Exception e) {
			error("Unable to get entered value from element with id " + elementId);
		}
		return entereddValue;
	}

	/**
	 * Description :Retrieves text of the webelement
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param elementName
	 * 
	 */
	public static synchronized String getText(WebElement element, String elementName) {

		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			info(elementName + " is visible");
			String text = element.getText();
			info("Retrieved the text of the element " + elementName);
			return text;
		} catch (Exception e) {
			error("Failed to retrieve the text of the element " + elementName);
			return null;
		}
	}

	/**
	 * Description :Compares text of the webelement
	 * 
	 * @author Manikandan A
	 * @param actual
	 * @param expected
	 * @param color
	 */
	public synchronized void comparetText(String actual, String expected, String color) {
		try {
			Assert.assertEquals(actual, expected);
			info(actual + " is matching with " + expected);
			validationinfo(actual + " is matching with " + expected, color);

		} catch (Exception e) {
			error(actual + " is not  matching with " + expected);
			fail(actual + " is not  matching with " + expected);
		}
	}

	/**
	 * Description :Retrieves text of the web element
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param attribute
	 * @param elementName
	 * @param validationPassMessage
	 * @param validationFailMessage
	 * @param color
	 */
	public synchronized void validateAttributeIsEmpty(WebElement element, String attribute, String elementName,
			String validationPassMessage, String validationFailMessage, String color) {
		String actualvalue = element.getAttribute(attribute);
		try {
			if (actualvalue.isEmpty()) {
				validationinfo(validationPassMessage, color);
				info(validationPassMessage);
			} else {
				error(validationFailMessage);
				fail(validationFailMessage);
			}
		} catch (Exception e) {
			error(validationFailMessage);
			fail(validationFailMessage);
		}
	}

	/**
	 * 
	 * Description : get file Name
	 * 
	 * @author Sushmita
	 * @param fileFormat
	 * @return fileName
	 */

	public synchronized String getSampleFileName(String fileFormat) {
		String fileName = "Sample" + fileFormat.toUpperCase() + "." + fileFormat;
		return fileName;
	}

	/**
	 * Description Verifies the Page Title
	 * 
	 * @author Manikandan A
	 * @param expected
	 * @param elementname
	 * 
	 */
	public synchronized void validatePageTitle(String expectedTitle) {
		try {
			String actualTitle = driver.getTitle();
			Assert.assertEquals(expectedTitle, actualTitle);
		} catch (Exception e) {
			fail("Failed to verify the Page Title ");
		}
	}

	/**
	 * Description Wait for the load by checking readystate using JavascriptExecutor
	 * 
	 * @author Manikandan A
	 */
	public synchronized void waitForPageLoadJS(WebDriver driver, int timeInSecs) {
		new WebDriverWait(driver, timeInSecs).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
				.executeScript("return document.readyState").equals("complete"));
	}

	/**
	 * 
	 * Description : Validate the value entered in an element
	 * 
	 * @author Manikandan A
	 * @param expectedValue
	 * @param actualvalue
	 * @param elementName
	 * @param validationPassMessage
	 * @param validationFailMessage
	 * @param color
	 */
	public synchronized void validateEnteredValue(String expectedValue, String actualvalue, String elementName,
			String validationPassMessage, String validationFailMessage, String color) {
		try {
			Assert.assertEquals(expectedValue, actualvalue);
			info("Valid value is entered in " + elementName);
			info(validationPassMessage);
			validationinfo(validationPassMessage, color);
		} catch (Exception e) {
			error("Valid value is not entered in " + elementName);
			error(validationFailMessage);
			fail(validationFailMessage);
		}

	}

	/**
	 * 
	 * Description Check whether the Element is Enabled
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param elementName
	 * @param validationPassMessage
	 * @param validationFailMessage
	 * @param color
	 */
	public synchronized void validateisElementEnabled(WebElement element, String elementName,
			String validationPassMessage, String validationFailMessage, String color) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			Assert.assertTrue(element.isEnabled());
			validationinfo(validationPassMessage, color);
			info(validationPassMessage);
		} catch (AssertionError e) {
			fail(validationFailMessage);
			error(validationFailMessage);
		}
	}

	/**
	 * 
	 * Description Method to return data for excel upload
	 * 
	 * @author Manikandan A
	 * @param data
	 * @param format
	 */
	public synchronized Map<String, String> getData_FormatMap(String[] data, String[] format) {
		Map<String, String> mapDataAndFormat = new LinkedHashMap<String, String>();
		for (int i = 0; i < data.length; i++) {
			mapDataAndFormat.put(data[i], format[i]);
		}
		return mapDataAndFormat;
	}

	/**
	 * 
	 * Description Check whether the Element is selected with expected conditions
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param elementName
	 * @param validationPassMessage
	 * @param validationFailMessage
	 * @param color
	 */
	public static synchronized void validateisElementSelected(WebElement element, String elementName,
			String validationPassMessage, String validationFailMessage, String color) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			Assert.assertTrue(element.isSelected());
			validationinfo(validationPassMessage, color);
			info(validationPassMessage);
		} catch (AssertionError e) {
			fail(validationFailMessage);
			error(validationFailMessage);
		}
	}

	/**
	 * 
	 * Description Method to switch driver focus to New Tab/window
	 * 
	 * @author Manikandan A
	 */
	public synchronized void switchToNewTab() {
		try {
			mainWindowID = driver.getWindowHandle();
			Set<String> allWindowID = driver.getWindowHandles();
			for (String id : allWindowID) {
				if (!id.equals(mainWindowID)) {
					driver.switchTo().window(id);
				}
			}
			info("Switched to New Tab");
			extentinfo("Switched to New Tab");
		} catch (Exception e) {
			error("Unable to switch to New Tab");
			fail("Unable to switch to New Tab");
			Assert.fail("Unable to switch to New Tab");

		}
	}

	/**
	 * 
	 * Description Method to switch driver focus to Main Tab/window
	 * 
	 * @author Manikandan A
	 */
	public synchronized void switchToMainTab() {
		try {
			driver.switchTo().window(mainWindowID);
			info("Switched to Main Tab");
			extentinfo("Switched to Main Tab");
		} catch (Exception e) {
			error("Unable to switch to Main Tab");
			fail("Unable to switch to Main Tab");
			Assert.fail("Unable to switch to Main Tab");
		}
	}

	/**
	 * Description: Wait for the Invisibility of the Element with expected
	 * conditions.
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param elementName
	 */
	public static synchronized void waitForInvisibilityOfElement(WebElement element, String elementName) {
		try {
			info("Wait for the invisibility of " + elementName);
			wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception e) {
			error(elementName + " is visible");
//			Assert.fail(elementName + " is visible");
		}
	}


	/**
	 * Description Click on the check box using JavaScript
	 * 
	 * @author Manikandan A jain
	 * @param element
	 * @param elementName
	 */
	public void clickCheckBoxUsingJS(WebElement element, String elementName) {
		try {
			if (element.isSelected()) {
				info(elementName + " selected by default");
				extentinfo(elementName + " selected by default");
			} else {
				jsExecutor.executeScript("arguments[0].click();", element);
				info(elementName + " is selected");
				extentinfo(elementName + " is selected");
			}
		} catch (NoSuchElementException e) {
			error("Unable to click on " + elementName);
			fail("Unable to click on " + elementName);
			Assert.fail("Unable to click on " + elementName);
		}
	}

	/**
	 * Description Double Click On Element
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param elementName
	 */
	public void doubleClickOnElement(WebElement element, String elementName) {
		try {
			action.doubleClick(element).perform();
			info("Double clicked on : " + elementName);
			extentinfo("Double clicked on : " + elementName);
		} catch (Exception e) {
			error("Unable to double click on " + elementName);
			fail("Unable to double click on " + elementName);
			Assert.fail("Unable to double click on " + elementName);
		}
	}

	/**
	 * Description Method to press Enter key
	 * 
	 * @author Manikandan A
	 */
	public synchronized void actionEnter() {
		try {
			action.sendKeys(Keys.ENTER).build().perform();
			info("Enter Key pressed");
			extentinfo("Enter Key  is pressed");
		} catch (Exception e) {
			error("Unable to press Enter Key");
			fail("Unable to press Enter Key");
			Assert.fail("Unable to press Enter Key");
		}
	}

	/**
	 * Description Click on the check box
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param elementName
	 */
	public static void clickCheckBox(WebElement element, String elementname) {

		wait.until(ExpectedConditions.visibilityOf(element));
		if (element.isSelected()) {
			info(elementname + " selected by default");
			extentinfo(elementname + " selected by default");
		} else {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			info(elementname + " is selected");
			extentinfo(elementname + " is selected");
		}
	}

	/**
	 * Description Wait for Visibility of element
	 * 
	 * @author Manikandan A jain
	 * @param element
	 * @param elementName
	 */
	public synchronized void waitForVisibilityOfElement(WebElement element, String elementName) {
		info("Wait for Visiblity of " + elementName);
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			error(elementName + " is not visible");
		}
	}

	/**
	 * 
	 * Description : This method validates the current url of the application
	 * 
	 * @author Manikandan A.
	 * @param expectedUrl
	 * @param elementName
	 */
	public static synchronized void validateUrl(String expectedUrl, String elementName) {
		try {
			String actualUrl = driver.getCurrentUrl();
			Assert.assertEquals(expectedUrl, actualUrl);
			info(elementName + " Page is displayed");
			validationinfo(elementName + " Page is displayed", "green");
		} catch (Exception e) {
			error(elementName + " Page is not displayed");
			fail(elementName + " Page is not displayed");
			Assert.fail(elementName + " Page is not displayed");
		}
	}

	/**
	 * Description To create the duration of the Test Run
	 * 
	 * @author Manikandan A
	 * @param millis
	 */
	public static String formatDuration(long millis) {
		long seconds = (millis / 1000) % 60;
		long minutes = (millis / (1000 * 60)) % 60;
		long hours = millis / (1000 * 60 * 60);

		StringBuilder b = new StringBuilder();
		b.append(hours == 0 ? "00" : hours < 10 ? String.valueOf("0" + hours) : String.valueOf(hours));
		b.append(":");
		b.append(minutes == 0 ? "00" : minutes < 10 ? String.valueOf("0" + minutes) : String.valueOf(minutes));
		b.append(":");
		b.append(seconds == 0 ? "00" : seconds < 10 ? String.valueOf("0" + seconds) : String.valueOf(seconds));
		return b.toString();
	}

	/**
	 * 
	 * Description : This method selects the options by value using Select Class.
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param value
	 * @param elementName
	 */
	public synchronized void selectByValue(WebElement element, String value, String elementName) {
		try {
			waitForElement(element, elementName);
			Select sc = new Select(element);
			sc.selectByValue(value);
			info(value + " is selected from " + elementName);
			extentinfo(value + " is selected from " + elementName);
		} catch (Exception e) {
			error("Unable to select " + value + " from " + elementName);
			fail("Unable to select " + value + " from " + elementName);
			Assert.fail("Unable to select " + value + " from " + elementName);
		}
	}

	/**
	 * Description fetches all the login details from AAM Test Data
	 * 
	 * @author Manikandan A
	 * @param map
	 * @param esaEmpCode
	 */
	public synchronized static Map<String, String[]> getLoginDetailsForRoles(Map<String, String> map) {
		String xlpath = System.getProperty("user.dir") + "/data/Data.xlsx";
		String[] loginData = new String[1];
		Set<String> setRoles = map.keySet();
		String strQuery = null;
		String empCode = null;
		String empRole = null;
		Map<String, String[]> mapLoginDetails = new LinkedHashMap<>();
		for (String role : setRoles) {
			empCode = map.get(role);
			if (role.contains("Initiator")) {
				role = "Initiator";
				empRole = role;
			} else if (role.contains("Approver1")) {
				role = "Approver1";
				empRole = role;
			} else if (role.contains("Approver2")) {
				role = "Approver2";
				empRole = role;

			}
			strQuery = "SELECT * from LoginCredentials WHERE EmpCode='" + empCode + "' and Role = '" + empRole + "'";
			String employeedata[][] = null;
			employeedata = ExcelUtil.getRowDataFromExcelUsingFillo(xlpath, strQuery);

			for (String[] singleEmpData : employeedata) {

				mapLoginDetails.put(singleEmpData[0], new String[] { singleEmpData[1], singleEmpData[2] });
			}
		}
		return mapLoginDetails;
	}

	/**
	 * Description Wait for the Element is displayed with expected conditions
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param elementName
	 */
	public void waitForElements(List<WebElement> elements, String elementName) {
		try {
			info("Wait for " + elementName);
			wait.until(ExpectedConditions.visibilityOfAllElements(elements));
			Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfAllElements(elements)) != null);
		} catch (Exception e) {
			error(elementName + " is not visible");
		}
	}

	/**
	 * Description : Method is used to scroll to the element
	 * 
	 * @author Sushmita
	 * @param element
	 */
	public synchronized void scrollToElementUsingJs(WebElement element) {
		try {
			poll(1000);
			jsExecutor.executeScript("arguments[0].scrollIntoView(true)", element);
			info("Scrolled to the element");
		} catch (Exception e) {
			error("Unable to Scroll to the element using js");
		}

	}

	/**
	 * Description Method is used to Scroll down
	 * 
	 * @author Manikandan A
	 */
	public synchronized static void scrollDown() {
		try {
			jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			info("Scroll down");
		} catch (Exception e) {
			error("Scroll down failed");
		}
	}

	/**
	 * Description Scroll using x, y coordinates
	 * 
	 * @author Manikandan A
	 * @param x
	 * @param y
	 */
	public void scrollUsingCoordinates(int x, int y) {
		try {
			jsExecutor.executeScript("window.scrollBy(" + x + ", " + y + ");");
			info("Scroll completed");
			System.out.println("Scroll completed");
		} catch (Exception e) {
			error("Scroll failed");
		}
	}

	/**
	 * Description Method to split the data
	 * 
	 * @author Manikandan A
	 * @param data
	 * @return splitData
	 */
	public static String[] splitData(String data) {
		String[] splitData = null;
		try {
			splitData = data.split(",");
		} catch (Exception e) {
			error("Unable to perform split action");
		}
		return splitData;
	}

	/**
	 * Description This Method to validate entered data in text area
	 * 
	 * @author Manikandan A
	 * @param element
	 * @param value
	 * @param elementName
	 */
	public synchronized void validateEnteredValueUsingJS(WebElement element, String value, String elementName) {
		try {
			waitForElement(element, elementName);
			String actualvalue = getTextusingJSWithXpath(element);
			Assert.assertEquals(actualvalue.toLowerCase(), value.toLowerCase());
			validationinfo(value + " entered in " + elementName, "blue");
			info(value + " entered in " + elementName);
		} catch (AssertionError e) {
			error("Unable to validate " + value + " is entered in " + elementName);
			fail("Unable to validate " + value + " is entered in " + elementName);
			Assert.fail("Unable to validate " + value + " is entered in " + elementName);
		}
	}

	/**
	 * Description :Retrieves text of the Web element
	 * 
	 * @author Manikandan A
	 * @param element
	 */
	public synchronized String getTextusingJSWithXpath(WebElement element) {
		String enteredValue = null;
		try {
			enteredValue = (String) (jsExecutor.executeScript("return arguments[0].value", element));
			info("The value entered in element is " + enteredValue);
		} catch (Exception e) {
			error("Unable to entered value using JS with xpath");
		}
		return enteredValue;
	}

	/**
	 * Description : This method is used to Type the Text into an element using JS.
	 * 
	 * @author Manikandan A
	 * @param elementId
	 * @param value
	 * 
	 */
	public synchronized void typeTextUsingJS(String elementId, String value) {

		try {
			poll(1000);
			jsExecutor.executeScript("document.getElementById('" + elementId + "').value='" + value + "'");
			info("The value entered into  the element is " + value);
		} catch (Exception e) {
			error("Unable to type " + value + " into element");
		}
	}

	/**
	 * Description : This method is used to Type the Text into an element using JS.
	 * 
	 * @author Manikandan A
	 * @param elementId
	 * @param value
	 * 
	 */
	public synchronized void typeTextUsingJS(WebElement element, String elementId,String elementName, String value) {
		try {
			info("Enter the value into " + elementName);
			poll(1000);
			jsExecutor.executeScript("document.getElementById('" + elementId + "').value='" + value + "'");
			info(value + " typed into " + elementName);
		} catch (Exception e) {
			System.out.println("Exception");
			error("Unable to type " + value + "into " + elementName);
			Assert.fail("Unable to type " + value + " into " + elementName);
		}
	}

	/**
	 * Description Method to select calendar date
	 * 
	 * @param elementId
	 * @param date
	 * @param elementName
	 * @author Sushmita
	 */
	public synchronized void select_CalendarDate(String elementId, String date, String elementName) {
		try {
			WebActionUtil.poll(1000);
			waitForElement(driver.findElement(By.id(elementId)), elementName);
			String script = "document.getElementById('" + elementId + "').value=" + "\"" + date + "\" ";
			jsExecutor.executeScript(script);
			info(date + " is selected");
		} catch (Exception e) {
			error("Unable to select " + date);
			Assert.fail("Unable to select " + date);
		}
	}

	/**
	 * Description : This method is used to fetch a date value from calender within
	 * a defined Range.
	 * 
	 * @author Manikandan A
	 * @return dateDuration
	 * 
	 */
	public synchronized String fetchCalenderWithinRange(String duration) {

		String dateDuration = "";
		try {
			int numberofDays = Integer.parseInt(duration);
			DateFormat customFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date currentDate = new Date();
			System.out.println(customFormat.format(currentDate));

			Calendar calender = Calendar.getInstance();
			calender.setTime(new Date());
			calender.add(Calendar.DATE, 0);
			String fromDate = customFormat.format(calender.getTime());

			calender.add(Calendar.DATE, numberofDays);
			String toDate = customFormat.format(calender.getTime());

			dateDuration = fromDate + " - " + toDate;
			info("Able to fetch the duration as " + dateDuration);

		} catch (Exception e) {
			error("Unable to the duration Date for Calender");
		}
		return dateDuration;
	}

	/**
	 * Description : This method is used to Increase the current date for a
	 * specified number of Days and returns the New Date.
	 * 
	 * @author Manikandan A
	 * @param numberOfDays
	 * @return Date
	 */
	public synchronized String fetchIncreaseCurrentDate(String numberOfDays) {
		String Date = "";
		try {
			DateFormat customFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date currentDate = new Date();
			System.out.println(customFormat.format(currentDate));

			int numberofDays = Integer.parseInt(numberOfDays);
			Calendar calender = Calendar.getInstance();
			calender.setTime(new Date());
			calender.add(Calendar.DATE, numberofDays);
			Date = customFormat.format(calender.getTime());

			info("Able to increase  the duration of  Calender  by " + numberofDays + " as  " + Date);

		} catch (Exception e) {
			error("Unable to the increase the duration Date for Calender");
		}
		return Date;
	}


	/**
	 * Description Method to delete excel file from downloads
	 * 
	 * @author Sushmita
	 * @param downloadspath
	 */
	public synchronized void deleteXlFilesFromDownloads(String downloadspath) {
		// Delete all files from this directory
		String targetDirectory = downloadspath;
		File dir = new File(targetDirectory);

		// Filter out all log files
		String[] xlFiles = dir.list((d, s) -> {
			boolean fileName = s.startsWith("Claim") && s.endsWith(".xlsx") ? true : false;
			return fileName;
		});
		if (xlFiles.length >= 50) {
			for (String xlFile : xlFiles) {
				String tempXLFile = new StringBuffer(targetDirectory).append(File.separator).append(xlFile).toString();
				File fileDelete = new File(tempXLFile);
				boolean isdeleted = fileDelete.delete();
			}
		}
	}

	/**
	 * Description : Method for fetching Current Date Time
	 *
	 * @author Ramya R,Manikandan
	 * 
	 */
	public static String getFutureMonth() {
		Date currentDate = null;
		String dateString = "";
		try {
			Calendar c = new GregorianCalendar();
			c.add(Calendar.MONTH, 1);// Next month
			currentDate = c.getTime();
			SimpleDateFormat sdfr = new SimpleDateFormat("MMMM");
			dateString = sdfr.format(currentDate);
		} catch (Exception e) {
			e.printStackTrace();
			error("Unable to get CurrentMonth");
			fail("Unable to get CurrentMonth");

		}
		return dateString;
	}

	/**
	 * 
	 * Description Method clicks on web element
	 * 
	 * @author Manikandan A
	 * 
	 */

	public synchronized void clickOnElement(WebElement element, String elementName, String message) {

		if (isElementClickable(element, elementName)) {
			element.click();
			info("Clicked on : " + elementName);
			extentinfo("Clicked on : " + elementName);
		} else {
			error(message);
			fail(message);
			Assert.fail(message);
		}

	}

	/**
	 * Description : Method for fetching Current Date Time
	 *
	 * @author Ramya R,Manikandan
	 * 
	 */
	public static String getCurrentDate() {
		String actualCurrentDate = "";
		try {
			DateFormat customFormat = new SimpleDateFormat("d/MM/yyyy");
			Date date = new Date();
			String actualDate = customFormat.format(date);
			String[] currentDate = actualDate.split("/");
			actualCurrentDate = currentDate[0];
			validationinfo("Able to get the Current Date as " + actualCurrentDate + "", "blue");
		} catch (Exception e) {
			error("Unable to get CurrentDate");
			fail("Unable to get CurrentDate");

		}
		return actualCurrentDate;
	}

	/**
	 * 
	 * Description Wait for the Visibility of all Element with expected conditions.
	 * 
	 * @author Manikandan A
	 * @param elements
	 * @param elementName
	 */
	public static void waitForVisibilityOfAllElement(List<WebElement> elements, String elementName) {
		try {
			info("Wait for Visibility of all " + elementName);
			wait.until(ExpectedConditions.visibilityOfAllElements(elements));

		} catch (Exception e) {
			error(elementName + " is not visible");
			fail(elementName + " is not visible");
			Assert.fail(elementName + " is not visible");

		}
	}

	/**
	 * * Description Method to perform Drag and Drop operation using Action Class
	 * 
	 * @author Sushmita
	 * @param driver
	 * @param elementFrom
	 * @param elementTo
	 */
	public static void dragandDropActionClass(WebDriver driver, WebElement elementFrom, WebElement elementTo) {
		try {
			info("performing drag and Drop using Action Class");
			Actions action = new Actions(driver);
			action.dragAndDrop(elementFrom, elementTo).perform();
			info("Drag and Drop operation is performed");
		} catch (Exception e) {
			error("unable to perform drag and drop Operation using Action Class");
			fail("unable to perform drag and drop Operation using Action Class");
		}

	}
	/**
	 * 
	 * Description Wait for the Element is displayed with expected conditions
	 * 
	 * @author Ramya R
	 * @param element
	 * @param elementName
	 */
	public void waitForInvisiblityElement(WebElement element, String elementName) {
		try {
			info("Wait for " + elementName);
			wait.until(ExpectedConditions.invisibilityOf(element));

		} catch (Exception e) {
			error(elementName + " is visible");
		}
	}
	/**
	 * Description : Method for fetching Current Date Time
	 *
	 * @author Ramya R,Manikandan
	 * 
	 */
	public static String getCurrentDateMonthYear() {
		String actualDate = "";
		try {
			DateFormat customFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			actualDate = customFormat.format(date);

			validationinfo("Able to get the Current Date as " + actualDate + "", "blue");
		} catch (Exception e) {
			error("Unable to get CurrentDate");
			fail("Unable to get CurrentDate");

		}

		return actualDate;
	}

	/**
	 * 
	 * Description . This method converts the given String to a Character and enters
	 * the character into the element.
	 * 
	 * @author Sushmita
	 * @param element
	 * @param textValue
	 * @param elementName
	 */
	public  synchronized void typeCharacterText(WebElement element, String textValue, String elementName) {
		try {
			info("Converting String " +textValue+ " to Character");

			for (int i = 0; i < textValue.length(); i++) {
				char c = textValue.charAt(i);
				String s = new StringBuilder().append(c).toString();
				poll(1000);
				element.sendKeys(s);			
			}

		} catch (Exception e) {
			e.printStackTrace();
			error("Unable to type " + textValue + " into " + elementName + "");
			fail("Unable to type " + textValue + " into " + elementName + "");

		}
	}

	/**
	 * Description Wait for the load by checking readystate using JavascriptExecutor
	 * 
	 * @author Manikandan A
	 */
	public synchronized void waiForPageLoadJS(WebDriver driver, int timeInSecs) {
		new WebDriverWait(driver, timeInSecs).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
				.executeScript("return document.readyState").equals("complete"));
	}

	/**
	 * 
	 * Description: Method to generate Pdf file
	 * 
	 * @author Manikandan A
	 * @param fileName
	 */
	public synchronized void createPdf(String fileName) {

		String filePath = System.getProperty("user.dir") + "/data/SampleFiles/";
		info("Creating a Pdf file");
		try {
			Document doc = new Document();
			String msg1 = "0123456789";	
			StringBuilder b = new StringBuilder();
			Random gen = new Random();
			while (b.length() < 4) {
				int index = (int) (gen.nextFloat() * msg1.length());
				b.append(msg1.charAt(index));
			}
			PdfWriter.getInstance(doc, new FileOutputStream(filePath + fileName +".pdf"));
			doc.open();
			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk chunk = new Chunk("Test Hello world " + b.toString(), font);
			doc.add(chunk);
			doc.close();
			poll(20000);
		} catch (Exception e) {
			error("Unable to create new Pdf file");
		}

	}

	/**
	 * 
	 * Description Method clicks on web element
	 * 
	 * @author Manikandan A
	 * 
	 */
	public static void moveToElement(WebDriver driver, WebElement element) {
		try {
			info("performing move to element using Action Class");
			Actions action = new Actions(driver);
			action.moveToElement(element).perform();
			info("performing move to element");
		} catch (Exception e) {
			error("unable to perform  move to element using Action Class");
			fail("unable to perform  move to element using Action Class");
		}

	}	

	/**
	 * 
	 * Description : Method for fetching downloaded file name
	 * 
	 * @author Manikandan A
	 * @param downloadDir
	 * @param fileExtension
	 * @return downloadedFileName
	 */
	public static synchronized String getDownloadedDocumentName(String downloadDir, String fileExtension) {
		String downloadedFileName = null;
		boolean valid = true;
		boolean found = false;

		// default timeout in seconds
		long timeOut = 30;
		try {
			Path downloadFolderPath = Paths.get(downloadDir);
			WatchService watchService = FileSystems.getDefault().newWatchService();
			downloadFolderPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
			long startTime = System.currentTimeMillis();
			do {
				WatchKey watchKey;
				watchKey = watchService.poll(timeOut, TimeUnit.SECONDS);
				long currentTime = (System.currentTimeMillis() - startTime) / 1000;
				if (currentTime > timeOut) {
					info("Download operation timed out.. Expected file was not downloaded");
					return downloadedFileName;
				}

				for (WatchEvent event : watchKey.pollEvents()) {
					WatchEvent.Kind kind = event.kind();
					if (kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
						String fileName = event.context().toString();
						info("New File Created:" + fileName);
						if (fileName.endsWith(fileExtension)) {
							downloadedFileName = fileName;
							info("Downloaded file found with extension " + fileExtension + ". File name is " +

										fileName);
							Thread.sleep(500);
							found = true;
							break;
						}
					}
				}
				if (found) {
					return downloadedFileName;
				} else {
					currentTime = (System.currentTimeMillis() - startTime) / 1000;
					if (currentTime > timeOut) {
						info("Failed to download expected file");
						return downloadedFileName;
					}
					valid = watchKey.reset();
				}
			} while (valid);
		}

		catch (InterruptedException e) {
			error("Interrupted error - " + e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			error("Download operation timed out.. Expected file was not downloaded");
		} catch (Exception e) {
			error("Error occured - " + e.getMessage());
			e.printStackTrace();
		}
		return downloadedFileName;
	}
	
	/**
	 * Description:Method is used to switch the control of window
	 * 
	 * @author Abhishek,Manjappa Kammar
	 */
	public static void switchWindows() {
		try {
			String currentWindow = driver.getWindowHandle();
			Set<String> windows = driver.getWindowHandles();
			Iterator<String> it=windows.iterator();
			while (it.hasNext()) {
				String child_window=it.next();
				if(!currentWindow.equals(child_window)) 
				{
					try {
						driver.switchTo().window(child_window);
						info("Able to switch the control of window");
					} catch (Exception e) {
						error(e.getMessage());
						Assert.fail("Unable to switch the control of window");
					}
				}
			}
		} catch (Exception e) {
			error(e.getMessage());
			fail("Unable to switch the control of window");
			Assert.fail("Unable to switch the control of window");
		}
	}
	/**
	 * Description: Method to validate downloaded document
	 * 
	 * @author Manikandan A
	 * @param downloadedDocumentName
	 * @param color
	 */
	public static synchronized void validateDownload(String downloadedDocumentName, String color) {
		String userDir = System.getProperty("user.home");
		File file = new File(userDir + "/Downloads/" + downloadedDocumentName);
		if (file.exists()) {
			info("Downloaded document name is " + downloadedDocumentName);
			info(downloadedDocumentName + " document downloaded successfully");
			validationinfo(downloadedDocumentName + " document downloaded successfully", color);
		} else {
			error("Unable to validate downloaded document");
			fail("Unable to validate downloaded document");
			Assert.fail("Unable to validate downloaded document");
		}
	}
	/**
	 * Description : Method to write and upload excel Data.
	 * 
	 * @author Manikandan A
	 * @param data
	 * @param downloadedDocumentName
	 * @param element
	 * @param elementName
	 */
	public static synchronized void writeAndUploadExcelData(String[] data, String downloadedDocumentName, int count) {
		try {
			ExcelUtil.writeDataIntoExcelFile(data, downloadedDocumentName, count);
		} catch (Exception e) {
			error(e.getMessage());
			error("Unable to write data into Excel File");
			fail("Unable to write data into Excel File");
		}
	}

	/**
	 * Description:Method implements to validate Selected value from the listbox
	 * 
	 * @author Sushmita P H
	 * @param element
	 * @param expectedValue
	 * @param elementName
	 */
	public synchronized static void validateSelectedListboxOption(WebElement element, String expectedValue,
			String elementName) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			WebElement selectedOption = selectValue.getFirstSelectedOption();
			String actualValue = selectedOption.getText();
			Assert.assertEquals(actualValue, expectedValue);
			info("Actual value: "+actualValue + " is matching with the expected value: "+expectedValue);
			extentinfo("Actual value: "+actualValue + " is matching with the expected value: "+expectedValue);
		} catch (Exception e) {
			error("Unable to validate selected listbox option");
			fail("Unable to validate selected listbox option");
			Assert.fail(" Unable to validate selected listbox option");
		}

	}
	
	/**
	 * Description:Method is used to switch the control of window
	 * 
	 * @author Abhishek,Manjappa Kammar
	 */
	public static void switchToParentWindow() {
		try {
			String currentWindow = driver.getWindowHandle();
			Set<String> windows = driver.getWindowHandles();
			Iterator<String> it=windows.iterator();
			while (it.hasNext()) {
				String child_window=it.next();

				if(!(currentWindow.equals(child_window))) 
				{
					try {
						driver.switchTo().window(child_window);
						info("Able to switch the control of window");
				
					} catch (Exception e) {
						error(e.getMessage());
						Assert.fail("Unable to switch the control of window");
						
					}
				}
			}
		} catch (Exception e) {
			error(e.getMessage());
			Assert.fail("Unable to switch the control of window");
		}
	}
	
	/**
	 * Description: This method used to switch to parent window
	 * 
	 * @author Abhishek
	 * @param parentWindow
	 * 
	 */

	public synchronized static void switchParentWindow(String parentWindow) {
		try {
			driver.switchTo().window(parentWindow);
			WebActionUtil.info("Able to switch to parent window ");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to switch to parent window");
			Assert.fail("Unable to switch to parent window");
		}
	}
	
	/**
	 * Description Method is used to Scroll down
	 * 
	 * @author Manikandan A
	 */
	public synchronized static void scrollDownByCoordinates() {
		try {
			jsExecutor.executeScript("window.scrollBy(0,750)");
			info("Scroll down");
		} catch (Exception e) {
			error("Scroll down failed");
		}
	}
}
