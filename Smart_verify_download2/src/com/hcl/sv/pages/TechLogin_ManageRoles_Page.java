package com.hcl.sv.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.hcl.sv.baseutil.BaseTest;
import com.hcl.sv.util.WebActionUtil;

/**
 * Description: This class implements the method for TechLogin Manage role Page
 * 
 * @author Ramya R
 */
public class TechLogin_ManageRoles_Page {
	public WebDriver driver;
	public WebActionUtil actionUtil;
	public long ETO;

	public TechLogin_ManageRoles_Page(WebDriver driver, long ETO, WebActionUtil actionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actionUtil = actionUtil;
		this.ETO = ETO;

	}

	/* Manage Roles link */
	private WebElement lnkRoles(String roles) {
		String xpath = "//a[contains(text(),'" + roles + "')]";
		return driver.findElement(By.xpath(xpath));
	}

	/**
	 * Description: Method to Select the Roles
	 * 
	 * @author Ramya R
	 * @param roles
	 */
	public synchronized void selectRoles(String roles) {
		try {
			WebActionUtil.clickOnElement(lnkRoles(roles), roles + " Roles");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to select " + lnkRoles(roles) + " link");
			Assert.fail("Unable to select " + lnkRoles(roles) + " link");
		}
	}

	/**
	 * Description: Method to validate manage roles page
	 * 
	 * @author Ramya R
	 * 
	 */
	public synchronized void validateManageRolePage() {
		try {
			WebActionUtil.validateUrl(BaseTest.prop_constants.getProperty("techLogin_ManageRoles_Page_url"),
					"Manage role ");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Manage Role Page is not Displayed");
			Assert.fail("Manage Role Page is not Displayed");
		}
	}

}
