package com.hcl.sv.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.hcl.sv.util.WebActionUtil;

/**
 * Description: This class implements the method for Vendor TPA Home Page
 * 
 * @author Abhishek
 */
public class VendorTPAHome_Page {
	public WebDriver driver;
	public WebActionUtil actionUtil;
	public long ETO;

	public VendorTPAHome_Page(WebDriver driver, long ETO, WebActionUtil actionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actionUtil = actionUtil;
		this.ETO = ETO;
	}

	/* Login code text field */
	@FindBy(xpath = "//td[contains(text(),'Submitted by Initiator - Pending With Vendor')]/following-sibling::td/a")
	private WebElement tbLoginCode;

	/* Click on status */
	private WebElement statusName(String statusName) {
		return driver.findElement(By.xpath("//td[contains(text(),'"+statusName+"')]/following-sibling::td/a"));
	}

	/* Home link */
	@FindBy(xpath = "//a[contains(text(),'Home')]")
	private WebElement lnkHome;

	/* View link */
	@FindBy(xpath = "//a[contains(text(),'View')]")
	private WebElement lnkView;
	
	/* Report link */
	@FindBy(xpath = "//a[contains(text(),'Report')]")
	private WebElement lnkReport;

	/* Change lassword Link */
	@FindBy(xpath = "//a[contains(text(),'Change Password')]")
	private WebElement lnkChangePassword;

	/* Logout link */
	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	private WebElement lnkLogout;

	/**
	 * Description: This method is used to click on View detail link.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkViewDetail(String statusName) {
		try {
			WebActionUtil.clickOnElement(statusName(statusName), "View details link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on View details link");
			Assert.fail("Unable to perform action on View details link");
		}
	}

	/**
	 * Description: This method is used to click on Home link.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkHome() {
		try {
			WebActionUtil.clickOnElement(lnkHome, "Home link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Home link");
			Assert.fail("Unable to perform action on Home link");
		}
	}

	/**
	 * Description: This method is used to click on View link.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkView() {
		try {
			WebActionUtil.clickOnElement(lnkView, "ViewlLink");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on View link");
			Assert.fail("Unable to perform action on View link");
		}
	}

	/**
	 * Description: This method is used to click on Report link.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkReport() {
		try {
			WebActionUtil.clickOnElement(lnkReport, "Report link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Report link");
			Assert.fail("Unable to perform action on Report link");
		}
	}

	/**
	 * Description: This method is used to click on Change password link.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkChangePassword() {
		try {
			WebActionUtil.clickOnElement(lnkChangePassword, "Change password link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Change password link");
			Assert.fail("Unable to perform action on Change password link");
		}
	}

	/**
	 * Description: This method is used to click on Logout link.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkLogout() {
		try {
			WebActionUtil.clickOnElement(lnkLogout, "Logout link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Logout link");
			Assert.fail("Unable to perform action on Logout link");
		}
	}
	
	
	


}
