package com.hcl.sv.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.hcl.sv.baseutil.BaseTest;
import com.hcl.sv.util.WebActionUtil;
/*
 * Description: This class implements the methods for accessing elements of TechLogin Login Page 
 * 
 * @author Abhishek
 * 
 */
public class TechLoginLogin_Page {

	public WebDriver driver;
	public WebActionUtil actionUtil;
	public long ETO;

	public TechLoginLogin_Page(WebDriver driver, long ETO, WebActionUtil actionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actionUtil = actionUtil;
		this.ETO = ETO;
	}
	
	/*Enter login details text*/
	@FindBy(xpath="//td[normalize-space(text())='Enter Login Details']")
	private WebElement txtEnterLoginDetails;

	/*Employee code text field*/
	@FindBy(xpath="//td[normalize-space(text())='Employee Code']/following-sibling::td/input")
	private WebElement tbEmployeeCode;
	
	/* Login code text field */
	@FindBy(xpath = "//td[normalize-space(text())='Login Code']/following-sibling::td/input")
	private WebElement tbLoginCode;

	/* Password text field */
	@FindBy(xpath = "//input[@type='password']")
	private WebElement tbPassword;

	/* Go button */
	@FindBy(xpath = "//input[@type='image']")
	private WebElement btnGo;
	
	/*Employee header home text*/
	@FindBy(className="global-pagetitle")
	private WebElement txtHeaderHome;
	
	
	/**
	 * Description: This method is used to validate techLogin login page.
	 * 
	 * @author Abhishek
	 */
	private synchronized void validateTechLoginLoginPage() {
		try {
			WebActionUtil.waitForElement(txtEnterLoginDetails, "Enter login details text");		
			if ((driver.getCurrentUrl()
					.contains(BaseTest.prop_constants.getProperty("tech_Login_Login_Page_url")))
					&& txtEnterLoginDetails.isDisplayed()) {
				WebActionUtil.info("TechLogin login page is displayed");
				WebActionUtil.validationinfo("TechLogin login page is displayed", "green");
			} else {
				WebActionUtil.error("TechLogin login page is not displayed");
				WebActionUtil.fail("TechLogin login page is not displayed");
				Assert.fail("TechLogin login page is not displayed");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate techLogin login page");
			Assert.fail("Unable to validate techLogin login page");
		}
	}

	/**
	 * Description: This method used to enter employee code in employee code text field.
	 * 
	 * @author Abhishek
	 * @param employeeCode
	 */
	private synchronized void setEmployeeCodeTextField(String employeeCode) {
		try {
			actionUtil.clearText(tbEmployeeCode, "Employee code text field");
			WebActionUtil.typeText(tbEmployeeCode, employeeCode, "Employee code text field");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to enter "+employeeCode+" into employee code text field");
			Assert.fail("Unable to enter "+employeeCode+" into employee code text field");
		}
	}
	
	/**
	 * Description: This method used to enter login code in login code text field.
	 * 
	 * @author Abhishek
	 * @param loginCode
	 */
	private synchronized void setLoginCodeTextField(String loginCode) {
		try {
			actionUtil.clearText(tbLoginCode, "Login code text field");
			WebActionUtil.typeText(tbLoginCode, loginCode, "Login code text field");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to enter "+loginCode+" into login code text field");
			Assert.fail("Unable to enter "+loginCode+" into login code text field");
		}
	}

	/**
	 * Description: This method used to enter password in password text field.
	 * 
	 * @author Abhishek
	 * @param password
	 */
	private synchronized void setPasswordTextField(String password) {
		try {
			WebActionUtil.typeText(tbPassword, password, "Password text field");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to enter "+password+" into password text field");
			Assert.fail("Unable to enter "+password+" into password text field");
		}
	}

	/**
	 * Description: This method used to click on go button.
	 * 
	 * @author Abhishek
	 */
	private synchronized void clkGoButton() {
		try {
			WebActionUtil.clickOnElement(btnGo, "Go Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on go button");
		    Assert.fail("Unable to click on go button");
		}
	}
	
	/**
	 * Description: This method is used to validate manage roles page.
	 * 
	 * @author Abhishek
	 */
	private synchronized void validateManageRolesPage() {
		try {
			WebActionUtil.validateUrl(BaseTest.prop_constants.getProperty("manage_Roles_Page_Url"), "Manage Roles Page");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate manage roles page");
			Assert.fail("Unable to validate manage roles page");
		}
	}

	/**
	 * Description: This method is used to login to the application as tech
	 * 
	 * @author Abhishek
	 * @param employeeCode
	 * @param loginCode
	 * @param password
	 */
	public synchronized void loginToApplicationAsTech(String employeeCode, String loginCode, String password) {
		try {
			driver.navigate().to(BaseTest.prop_constants.getProperty("tech_Login_Login_Page_url"));
			validateTechLoginLoginPage();
			setEmployeeCodeTextField(employeeCode);
			setLoginCodeTextField(loginCode);
			setPasswordTextField(password);
			clkGoButton();
			validateManageRolesPage();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to login to the application as Tech");
			Assert.fail("Unable to login to the application as Tech");
		}
	}
	
	/**
	 * Description: This method is used to validate employee home page.
	 * 
	 * @author Abhishek
	 */
	private synchronized void validateEmployeeHomePage(){
		try {
			WebActionUtil.waitForElement(txtHeaderHome, "Header Home Text");
			if ((driver.getCurrentUrl()
					.contains(BaseTest.prop_constants.getProperty("employee_Home_Page_Url")))
					&& txtHeaderHome.isDisplayed()) {
				WebActionUtil.info("Employee home page is displayed");
				WebActionUtil.validationinfo("Employee home page is displayed", "green");
			} else {
				WebActionUtil.error("Employee home page is not displayed");
				WebActionUtil.fail("Employee home page is not displayed");
				Assert.fail("Employee home page is not displayed");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate employee home page");
			Assert.fail("Unable to validate employee home page");
		}
	}
	
	/**
	 * Description: This method is used to login to the application as employee
	 * 
	 * @author Abhishek
	 * @param employeeCode
	 * @param loginCode
	 * @param password
	 */
	public synchronized void loginToApplicationAsEmployee(String employeeCode, String loginCode, String password){
		try {
			driver.get(BaseTest.prop.getProperty("tech_url"));
			validateTechLoginLoginPage();
			setEmployeeCodeTextField(employeeCode);
			setLoginCodeTextField(loginCode);
			setPasswordTextField(password);
			clkGoButton();
			validateEmployeeHomePage();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to login to the application as employee");
			Assert.fail("Unable to login to the application as employee");
		}
	}
}
