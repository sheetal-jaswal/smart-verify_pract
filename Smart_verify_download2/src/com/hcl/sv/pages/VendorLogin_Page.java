package com.hcl.sv.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.hcl.sv.baseutil.BaseTest;
import com.hcl.sv.util.WebActionUtil;

/*
 * Description: This class implements the methods for accessing elements of Vendor Login page 
 * 
 * @author Abhishek
 * 
 */
public class VendorLogin_Page {

	public WebDriver driver;
	public WebActionUtil actionUtil;
	public long ETO;

	public VendorLogin_Page(WebDriver driver, long ETO, WebActionUtil actionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actionUtil = actionUtil;
		this.ETO = ETO;
	}

	/* User ID text field */
	@FindBy(xpath = "//input[@type='text']")
	private WebElement tbUserId;

	/* Password text field */
	@FindBy(xpath = "//input[@type='password']")
	private WebElement tbPassword;

	/* Login button */
	@FindBy(xpath = "//input[@value='Login']")
	private WebElement btnLogin;

	/* Forgot Password link */
	@FindBy(linkText = "Forgot Password")
	private WebElement lnkForgotPassword;
	
	/* Is MFA not required radio button */
	@FindBy(xpath="//input[@value='RdMFARequiredORNot']")
	private WebElement rdoIsMFANotRequired;

	/**
	 * Description: this method is used to validate Vendor login page.
	 * 
	 * @author Abhishek
	 */
	public synchronized void validateVendorLoginPage() {

		try {
			WebActionUtil.validateUrl(BaseTest.prop_constants.getProperty("vendor_Login_Page_Url"),
					"Vendor login page");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("unable to validate Vendor login page");
			Assert.fail("unable to validate Vendor login page");

		}
	}

	/**
	 * Description: this method used to enter User Id in User ID text field.
	 * 
	 * @author Abhishek
	 * @param userID
	 */

	private synchronized void setUserIDTextField(String userID) {
		try {
			WebActionUtil.typeText(tbUserId, userID, "User ID text field");
			WebActionUtil.validateAttribute(tbUserId, "value", userID, "User ID text field",
					"able to type " + userID + " into User ID text field",
					"unable to type " + userID + " into User ID text field", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("unable to enter " + userID + " into User ID text field");
			Assert.fail("unable to enter " + userID + " into User ID text field");
		}
	}

	/**
	 * Description: this method used to enter password in Password text field.
	 * 
	 * @author Abhishek
	 * @param password
	 */
	private synchronized void setPasswordTextField(String password) {
		try {
			WebActionUtil.typeText(tbPassword, password, "Password text field");
			WebActionUtil.validateAttribute(tbPassword, "value", password, "Password text field",
					"able to type " + password + " into Password text field",
					"unable to type " + password + " into Password text field", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("unable to enter " + password + " in password text field");
			Assert.fail("unable to enter " + password + " in password text field");
		}
	}

	/**
	 * Description: this method used to click on Login button.
	 * 
	 * @author Abhishek
	 * 
	 */

	private synchronized void clkLoginButton() {
		try {
			WebActionUtil.clickOnElement(btnLogin, "Login button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("unable to perform action on Login button");
			Assert.fail("unable to perform action on Login button");
		}
	}

	/**
	 * Description: this method is used to validate Vendor home page.
	 * 
	 * @author Abhishek
	 */
	public synchronized void validateVendorHomePage() {

		try {
			WebActionUtil.validateUrl(BaseTest.prop_constants.getProperty("vendor_Home_Page_Url"), "Vendor home page");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("unable to validate Vendor home page");
			Assert.fail("unable to validate Vendor home page");

		}
	}

	/**
	 * Description: this method is used to login to the application as Vendor
	 * 
	 * @author Abhishek
	 * @param userID
	 * @param password
	 */

	public synchronized void loginToApplicationAsVendor(String userID, String password) {
		try {
			driver.get(BaseTest.prop_constants.getProperty("vendor_Login_Page_Url"));
			validateVendorLoginPage();
			setUserIDTextField(userID);
			setPasswordTextField(password);
//			clkIsMFANotRequiredRadioButton();
			clkLoginButton();
			validateVendorHomePage();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("unable to login to the application as Vendor");
			Assert.fail("unable to login to the application as Vendor");
		}
	}

	/**
	 * Description: this method used to click on Forgot password link.
	 * 
	 * @author Abhishek
	 */

	public synchronized void clkForgotPasswordLink() {
		try {
			WebActionUtil.clickOnElement(lnkForgotPassword, "Forgot password link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("unable to perform action on Forgot password link");
			Assert.fail("unable to perform action on Forgot password link");
		}
	}
	
	/**
	 * Description: this method used to click on Is MFA not required radio button.
	 * 
	 * @author Abhishek
	 */
	private synchronized void clkIsMFANotRequiredRadioButton() {
		try {
			if(rdoIsMFANotRequired.isSelected()) {
				WebActionUtil.info("Is MFA not required radio button is selected");
			}
			else {
				WebActionUtil.clickOnElement(rdoIsMFANotRequired, "Is MFA not required radio button");
			}
			
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("unable to perform action on Is MFA not required radio button");
			Assert.fail("unable to perform action on Is MFA not required radio button");
		}
	}

}
