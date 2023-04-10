package com.hcl.sv.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.hcl.sv.baseutil.BaseTest;
import com.hcl.sv.util.WebActionUtil;

/*
 * Description: This class implements the methods for accessing elements of Home Page of Initiator role
 * 
 * @author Manikandan
 */
public class TechLogin_InitiatorHome_Page {
	public WebDriver driver;
	public WebActionUtil actionUtil;
	public long ETO;

	public TechLogin_InitiatorHome_Page(WebDriver driver, long ETO, WebActionUtil actionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actionUtil = actionUtil;
		this.ETO = ETO;
	}
	
	/* Welcome to the Smart Verify system text */
	@FindBy(xpath = "//td[contains(text(),'Welcome to the Smart Verify System')]")
	private WebElement txtHeaderHome;

	/* Logout link */
	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	private WebElement lnkLogout;


	/**
	 * Description:  Link present in header i.e Home, Consolidated report, Initiator etc
	 * 
	 * @author Abhishek
	 * @param value 
	 * @param elementName
	 */
	private WebElement lnkInHeader(String value, String elementName) {
		String xpath = "//span[contains(text(),'" + value + "')]/ancestor::a";
		try {
			new WebDriverWait(driver, BaseTest.ETO)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {
			WebActionUtil.error(elementName + " is not visible");
			WebActionUtil.fail(elementName + " is not visible");
			Assert.fail(elementName + " is not visible");
		}
		return driver.findElement(By.xpath(xpath));
	}
	
	/**
	 * Description: This method used for Drop down value xpath present in header link i.e Initiator view, report upload etc 
	 * 
	 * @author Manikandan
	 * @param dropdownValue
	 * @param elementName
	 */
	private WebElement lnkInHeaderDropdown(String dropdownValue, String elementName) {
		String xpath = "//a[text()='"+dropdownValue+"']";
		try {
			new WebDriverWait(driver, BaseTest.ETO)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {
			WebActionUtil.error(elementName + " is not visible");
			WebActionUtil.fail(elementName + " is not visible");
			Assert.fail(elementName + " is not visible");
		}
		return driver.findElement(By.xpath(xpath));
	}
	
	/**
	 * Description: This method used to View details link for mentioned status in Pre joining.
	 * 
	 * @author Manikandan
	 * @param status
	 * @param elementName
	 */
	private WebElement lnkViewDetailsForMentionedStatusInPreJoining(String status, String elementName) {
		String xpath = "//td[contains(text(),'Summary Report for PreJoining')]/parent::tr/following-sibling::tr/descendant::td[contains(text(),'"
				+ status + "')]/following-sibling::td/child::a[contains(text(),'View Details')]";
		try {
			new WebDriverWait(driver, BaseTest.ETO)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {
			WebActionUtil.error(elementName + " is not visible");
			WebActionUtil.fail(elementName + " is not visible");
			Assert.fail(elementName + " is not visible");
		}
		return driver.findElement(By.xpath(xpath));
	}

	/**
	 * Description: This method used to View details link for mentioned status in Post joining
	 * 
	 * @author Manikandan
	 * @param status
	 * @param elementName
	 */
	private WebElement lnkViewDetailsForMentionedStatusInPostJoining(String status, String elementName) {
		String xpath = "//td[contains(text(),'Summary Report for PostJoining')]/parent::tr/following-sibling::tr/descendant::td[contains(text(),'"
				+ status + "')]/following-sibling::td/child::a[contains(text(),'View Details')]";
		try {
			new WebDriverWait(driver, BaseTest.ETO)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {
			WebActionUtil.error(elementName + " is not visible");
			WebActionUtil.fail(elementName + " is not visible");
			Assert.fail(elementName + " is not visible");
		}
		return driver.findElement(By.xpath(xpath));
	}

	/**
	 * Description: This method used to View details link for mentioned status in Existing
	 * 
	 * @author Manikandan
	 * @param status
	 * @param elementName
	 */
	private WebElement lnkViewDetailsForMentionedStatusInExisting(String status, String elementName) {
		String xpath = "//td[contains(text(),'Summary Report for Existing')]/parent::tr/following-sibling::tr/descendant::td[contains(text(),'"
				+ status + "')]/following-sibling::td/child::a[contains(text(),'View Details')]";
		try {
			new WebDriverWait(driver, BaseTest.ETO)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {
			WebActionUtil.error(elementName + " is not visible");
			WebActionUtil.fail(elementName + " is not visible");
			Assert.fail(elementName + " is not visible");
		}
		return driver.findElement(By.xpath(xpath));
	}

	

	/**
	 * Description: This method is used to click on Logout link
	 * 
	 * @author Manikandan
	 */
	public synchronized void clkLogoutLnk() {
		try {
			WebActionUtil.clickOnElement(lnkLogout, "Logout Link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Logout Link");
			Assert.fail("Unable to perform action on Logout Link");
		}
	}

	/**
	 * Description: This method is used to click on link present in header in recruiter home page(i.e Home, Consolidated report, Initiator etc)
	 * 
	 * @author Manikandan
	 * @param headerValue
	 */
	public synchronized void clkLinkInHeader(String headerValue) {
		try {
			WebActionUtil.clickOnElement(lnkInHeader(headerValue, headerValue + " Link"), headerValue + " Link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on " + headerValue + " Link");
			Assert.fail("Unable to perform action on " + headerValue + " Link");
		}
	}
	
	/**
	 * Description: This method is used to mouse hover on link present in header then click on option in drop down
	 * 
	 * @author Manikandan
	 * @param headerValue
	 * @param dropdownValue
	 */
	public synchronized void clkLinkInHeaderDD(String headerValue,String dropdownValue) {
		try {
			WebActionUtil.actionMouseOver(lnkInHeader(headerValue, headerValue + " Link"), headerValue + " Link");
			WebActionUtil.clickOnElement(lnkInHeaderDropdown(dropdownValue, dropdownValue + " Link"), dropdownValue + " Link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on " + headerValue + " Link");
			Assert.fail("Unable to perform action on " + headerValue + " Link");
		}
	}

	/**
	 * Description: This method is used to click on View Details link for mentioned status in Pre joining
	 * 
	 * @author Manikandan
	 * @param status
	 */
	public synchronized void clkViewDetailsLinkInPrejoining(String status) {
		try {
			WebActionUtil.clickOnElement(
					lnkViewDetailsForMentionedStatusInPreJoining(status, status + " View Details Link"),
					status + " View Details Link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on " + status + " View Details Link");
			Assert.fail("Unable to perform action on " + status + " View Details Link");
		}
	}
	
	/**
	 * Description: This method is used to click on View Details link for mentioned status in Post joining
	 * 
	 * @author Manikandan
	 * @param status
	 */
	public synchronized void clkViewDetailsLinkInPostjoining(String status) {
		try {
			WebActionUtil.clickOnElement(
					lnkViewDetailsForMentionedStatusInPostJoining(status, status + " View Details Link"),
					status + " View Details Link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on " + status + " View Details Link");
			Assert.fail("Unable to perform action on " + status + " View Details Link");
		}
	}
	
	/**
	 * Description: This method is used to click on View Details link for mentioned status in Existing
	 * 
	 * @author Manikandan
	 * @param status
	 */
	public synchronized void clkViewDetailsLinkInExisting(String status) {
		try {
			WebActionUtil.clickOnElement(
					lnkViewDetailsForMentionedStatusInExisting(status, status + " View Details Link"),
					status + " View Details Link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on " + status + " View Details Link");
			Assert.fail("Unable to perform action on " + status + " View Details Link");
		}
	}

	/**
	 * Description: This method is used to validate initiator home page
	 * 
	 * @author Manikandan
	 */
	public synchronized void validateInitiatorHomePage() {
		try {
			WebActionUtil.waitForElement(txtHeaderHome, "Header Home Text");
			if ((txtHeaderHome.isDisplayed() && txtHeaderHome.getText().toLowerCase()
					.contains(BaseTest.prop_constants.getProperty("role_Initiator").toLowerCase()))) {
				WebActionUtil.info("Initiator Home page is displayed");
				WebActionUtil.validationinfo("Initiator Home page is displayed", "blue");
			} else {
				WebActionUtil.error("Initiator Home page is not displayed");
				WebActionUtil.fail("Initiator Home page is not displayed");
				Assert.fail("Initiator Home page is not displayed");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Initiator Home page");
			Assert.fail("Unable to validate Initiator Home page");

		}
	}
}
