package com.hcl.sv.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.hcl.sv.baseutil.BaseTest;
import com.hcl.sv.util.WebActionUtil;

/**
 * Description: This class implements the method for Candidate Login page
 * 
 * @author SreeLatha
 */
public class CandidateLogin_Page {
	
	public WebDriver driver;
	public WebActionUtil actionUtil;
	public long ETO;

	public CandidateLogin_Page(WebDriver driver, long ETO, WebActionUtil actionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actionUtil = actionUtil;
		this.ETO = ETO;
	}

	/* User id text box */
	@FindBy(xpath = "//input[@type='text']")
	private WebElement tbuserId;

	/* Password text box */
	@FindBy(xpath = "//input[@type='password']")
	private WebElement tbPassword;

	/* Login button */
	@FindBy(xpath = "//input[@value='Login']")
	private WebElement btnLogin;
	
	/* Smart verify link */
//	@FindBy(xpath="//a[normalize-space(text())='Smart Verify']")
	@FindBy(xpath="//*[@id='global-logo']/h1")
	private WebElement lnkSmartVerify;
	
	/**
	 * Description: This method used to validate candidate homePage.
	 * 
	 * @author SreeLatha
	 * 
	 */
	public synchronized void validateCandidateHomePage(){
		try {
			WebActionUtil.validateUrl(BaseTest.prop_constants.getProperty("candidate_Home_Page_url") , "Candiadte Home Page URL"); 	
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate candidate home page");
			Assert.fail("Unable to validate candidate home page");
		}
	}
	
	/**
	 * Description: This method used to enter user id in user id text field.
	 * 
	 * @author SreeLatha
	 * @param userId
	 */
	private synchronized void setUserIdTextFeild(String userId) {
		try {
			WebActionUtil.typeText(tbuserId, userId, " Userid textbox");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to enter user id in user Id text field");
			Assert.fail("Unable to enter user Id in user Id text field");
		}
	}

	/**
	 * Description: This method used to enter password in password textFIeld.
	 * 
	 * @author SreeLatha
	 * @param password
	 */
	private synchronized void setPassword(String password) {
		try {
			WebActionUtil.typeText(tbPassword, password, "Password textbox");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to set password in password text field");
			Assert.fail("Unable to set password in password text field");
		}
	}

	/**
	 * Description: This method used to click on login button.
	 * 
	 * @author SreeLatha
	 */
	private synchronized void clkLoginButton() {
		try {
			WebActionUtil.clickOnElement(btnLogin, "Login Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Click  on login button");
			Assert.fail("Unable to Click  on login button");
		}
	}

	/**
	 * Description: This method is used to login to the application
	 * 
	 * @author sreeLatha
	 * @param userId
	 * @param password
	 */
	public synchronized void loginToApplication(String userId, String password){
		try {
			driver.navigate().to(BaseTest.prop_constants.getProperty("candidate_url"));
			validateCandidateLoginPage();
			setUserIdTextFeild(userId);
			setPassword(password);
			clkLoginButton();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to login to the application");
			Assert.fail("Unable to login to the application");
		}
	}
	
	/**
	 * Description: This method used to validate candidate Login Page.
	 * 
	 * @author SreeLatha
	 * 
	 */
	private synchronized void validateCandidateLoginPage(){
		try {
			System.out.println("link");
			WebActionUtil.validateisElementDisplayed(lnkSmartVerify, "Smart verify link",
					"Login page is displayed", "Login page is not displayed", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Candidate login page");
			Assert.fail("Unable to validate Candidate login page");
		}
	}
}
