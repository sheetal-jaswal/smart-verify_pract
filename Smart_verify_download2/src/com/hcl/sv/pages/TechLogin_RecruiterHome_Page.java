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
 * Description: This class implements the methods for accessing elements of Home Page of Recruiter role
 * 
 * @author Manikandan
 */
public class TechLogin_RecruiterHome_Page {

	public WebDriver driver;
	public WebActionUtil actionUtil;
	public long ETO;

	public TechLogin_RecruiterHome_Page(WebDriver driver, long ETO, WebActionUtil actionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actionUtil = actionUtil;
		this.ETO = ETO;
	}

	/* Welcome to the Smart Verify System Text */
	@FindBy(xpath = "//td[contains(text(),'Welcome to the Smart Verify System')]")
	private WebElement txtHeaderHome;

	/* Logout link */
	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	private WebElement lnkLogout;

	/* Link present in header i.e Home, Recruiter View, TechBeesFilter */
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

	/* View Details Link for mentioned status */
	private WebElement lnkViewDetailsForMentionedStatus(String status, String elementName) {
		String xpath = "//td[contains(text(),'" + status
				+ "')]/following-sibling::td/child::a[contains(text(),'View Details')]";
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
	 * Description: This method used to click on Logout Link
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
	 * Description: This method used to click on link present in header in recruiter
	 * home page(i.e Home, Recruiter View, TechBeesFilter )
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
	 * Description: This method used to click on view details link for mentioned
	 * status
	 * 
	 * @author Manikandan
	 * @param status
	 */
	public synchronized void clkViewDetailsLnk(String status) {
		try {
			WebActionUtil.clickOnElement(lnkViewDetailsForMentionedStatus(status, status + " View Details Link"),
					status + " View Details Link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on " + status + " View Details Link");
			Assert.fail("Unable to perform action on " + status + " View Details Link");
		}
	}

	/**
	 * Description: This method is used to validate Recruiter Home page
	 * 
	 * @author Manikandan
	 */
	public synchronized void validateRecruiterHomePage() {
		try {
			WebActionUtil.waitForElement(txtHeaderHome, "Header Home Text");
			if ((txtHeaderHome.isDisplayed() && txtHeaderHome.getText().toLowerCase()
					.contains(BaseTest.prop_constants.getProperty("role_Recruiter").toLowerCase()))) {
				WebActionUtil.info("Recruiter Home page is displayed");
				WebActionUtil.validationinfo("Recruiter Home page is displayed", "blue");
			} else {
				WebActionUtil.error("Recruiter Home page is not displayed");
				WebActionUtil.fail("Recruiter Home page is not displayed");
				Assert.fail("Recruiter Home page is not displayed");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Recruiter Home page");
			Assert.fail("Unable to validate Recruiter Home page");

		}
	}

}
