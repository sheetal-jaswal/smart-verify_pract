package com.hcl.sv.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.hcl.sv.baseutil.BaseTest;
import com.hcl.sv.util.WebActionUtil;

/**
 * Description: This class implements the method for Vendor TPA View Page
 * 
 * @author Ramya R
 */
public class VendorTPAView_Page {
	public WebDriver driver;
	public WebActionUtil actionUtil;
	public long ETO;

	public VendorTPAView_Page(WebDriver driver, long ETO, WebActionUtil actionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actionUtil = actionUtil;
		this.ETO = ETO;
	}

	/* Record Status drop down */
	@FindBy(xpath = "//td[contains(text(),'Record Status')]/following-sibling::td/select")
	private WebElement ddRecordStatus;
	
	/* BGV ID text area */
	@FindBy(xpath = "//td[contains(text(),'BGV ID')]/following-sibling::td/textarea")
	private WebElement taBGVId;
	
	/* Search button */
	@FindBy(xpath = "//input[@value='Search']")
	private WebElement btnSearch;
	
	/* Submit button */
	@FindBy(xpath = "//input[@value='Submit']")
	private WebElement btnSubmit;

	/* Select check box */
	private WebElement selectBGVIDCheckBox(String bGVId) {
		return driver.findElement(By.xpath("//td[text()='"+bGVId+"']/following-sibling::td/input[@type='checkbox']"));
	}
	
	/* Action link */
	private WebElement lnkAction(String bGVId) {
		return driver.findElement(By.xpath("//td[text()='"+bGVId+"']/following-sibling::td/a[contains(text(),'Edit/Update')]"));
	}
	
	/* Joining form link */
	private WebElement lnkJoiningForm(String bGVId) {
		return driver.findElement(By.xpath("//td[text()='"+bGVId+"']/following-sibling::td/a[contains(text(),'Joining form')]"));
	}

	/* Logout link */
	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	private WebElement lnkLogout;
	
	
	/* Select check box */
	private WebElement txtSearchedBGVID(String bgvId) {
		return driver.findElement(By.xpath("//td[text()='"+bgvId+"']"));
	}
	
	/*Edit/Update link */
	@FindBy(xpath = "//a[contains(@id,'lnkUpload')]")
	private WebElement lnkEditOrUpdate;
	
	/**
	 * Description: This method is used to select Record Status from drop down.
	 * 
	 * @author Ramya R
	 * @param recordStatusOption
	 */
	public synchronized void selectRecordStatus(String recordStatusOption) {
		try {
			actionUtil.selectByText(ddRecordStatus, recordStatusOption);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to select option from Record Status drop down");
			Assert.fail("Unable to select option from Record Status drop down");
		}
	}

	/**
	 * Description: This method is used to enter BGV Id into BGV ID text area.
	 * 
	 * @author Ramya R
	 * @param bGVId
	 */
	public synchronized void setBgvId(String bGVId) {
		try {
			WebActionUtil.typeText(taBGVId, bGVId, "BGV ID Text area");
			WebActionUtil.validateAttribute(taBGVId, "value", bGVId, "Login Code Textfield",
					bGVId + " is entered into Login Code Textfield",
					"Unable to enter " + bGVId + " in Login Code Textfield", "blue");
		} catch (AssertionError | Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to type " + bGVId + " into BGV ID Text area");
			Assert.fail("Unable to type " + bGVId + " into BGV ID Text area");
		}
	}
	
	/**
	 * Description: This method is used to click on Search button.
	 * 
	 * @author Ramya R
	 */
	public synchronized void clkSearch() {
		try {
			WebActionUtil.clickOnElement(btnSearch, "Search Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Search Button");
			Assert.fail("Unable to perform action on Search Button");
		}
	}
	
	/**
	 * Description: This method is used to click on Submit button.
	 * 
	 * @author Ramya R
	 */
	public synchronized void clkSubmit() {
		try {
			WebActionUtil.clickOnElement(btnSubmit, "Submit Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Submit Button");
			Assert.fail("Unable to perform action on Submit Button");
		}
	}
	
	/**
	 * Description: This method is used to click on BGV Id Check box.
	 * 
	 * @author Ramya R
	 * @param bGVId
	 */
	public synchronized void clkBGVIDCheckBox(String bGVId) {
		try {
			WebActionUtil.clickOnElement(selectBGVIDCheckBox(bGVId), "BGVID Check Box");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on BGVID Check Box");
			Assert.fail("Unable to perform action on BGVID Check Box");
		}
	}
	
	/**
	 * Description: This method is used to click on Action link.
	 * 
	 * @author Ramya R
	 * @param bGVId
	 */
	public synchronized void clkAction(String bGVId) {
		try {
			WebActionUtil.clickOnElement(lnkAction(bGVId), "Action Link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Action Link");
			Assert.fail("Unable to perform action on Action Link");
		}
	}
	
	/**
	 * Description: This method is used to click on Joining form link.
	 * 
	 * @author Ramya R
	 * @param bGVId
	 */
	public synchronized void clkJoiningForm(String bGVId) {
		try {
			WebActionUtil.clickOnElement(lnkJoiningForm(bGVId), "Joining Form Link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Joining Form Link");
			Assert.fail("Unable to perform action on Joining Form Link");
		}
	}
	
	/**
	 * Description: This method is used to validate vendor TPA view page.
	 * 
	 * @author Manjappa
	 */
	public synchronized void validateVendorTPAViewPage() {

		try {
			WebActionUtil.validateUrl(BaseTest.prop_constants.getProperty("vendor_TPA_View_Page_Url"), "Vendor TPA View ");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Vendor TPA View Page");
			Assert.fail("Unable to validate Vendor TPA View Page");

		}
	}
	
	/**
	 * Description: This method is used to click on Logout link.
	 * 
	 * @author Ramya R
	 */
	public synchronized void clkLogout() {
		try {
			WebActionUtil.clickOnElement(lnkLogout, "Logout Link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Logout Link");
			Assert.fail("Unable to perform action on Logout Link");
		}
	}
	

	/**
	 * Description: This method used to verify that searched BGV Id is displayed.
	 * 
	 * @author Ramya R
	 * @param bgvID
	 */
	public synchronized void verifySearchedBgvIdIsDisplayed(String bgvID) {
		try {
			WebActionUtil.validateisElementDisplayed(txtSearchedBGVID(bgvID), "BGV ID in table",
					"Searched BGV ID is displayed in the table", "Searched BGV ID is not displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Searched BGV ID is not displayed");
			Assert.fail("Searched BGV ID is not displayed");
		}
	}
	
	
	/**
	 * Description: This method used to verify that employee details  is disappeared.
	 * 
	 * @author Ramya R
	 * @param bgvID
	 * @param parentWindow
	 */
	public synchronized void verifyEmployeeDetailIsDisappeared(String bgvID,String parentWindow) {
		try {
			WebActionUtil.switchParentWindow(parentWindow);
			validateVendorTPAViewPage();
		try {
			WebActionUtil.poll(3000);
			if(txtSearchedBGVID(bgvID).isDisplayed()) {
				WebActionUtil.error("Employee Details is not disappeared");
				WebActionUtil.validationinfo("Employee Details is not disappeared", "Red");
				Assert.fail("Employee Details is not disappeared");
			}
			}catch (Exception e1)  {
				WebActionUtil.info("Employee Details is disappeared");
				WebActionUtil.validationinfo("Employee Details is disappeared", "blue");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Employee Details is not disappeared");
			Assert.fail("Employee Details is not disappeared");
		}
	}
	
	
	/**
	 * Description: This method is used to click on Edit/Update link
	 * 
	 * @author Ramya R
	 * 
	 */
	public synchronized void clkEditOrUpdateLink() {
		try {
			WebActionUtil.poll(3000);
			actionUtil.waitForVisibilityOfElement(lnkEditOrUpdate, "Edit/Update Link");
			WebActionUtil.actionMouseOver(lnkEditOrUpdate, "Edit/Update Link");
			WebActionUtil.clickOnElement(lnkEditOrUpdate, "Edit/Update Link");
			WebActionUtil.poll(2000);
			WebActionUtil.switchWindows();
		} catch (Exception | AssertionError e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Click on Edit/Update Link");
			Assert.fail("Unable to Click on Edit/Update Link");
		}
	}
	
}
