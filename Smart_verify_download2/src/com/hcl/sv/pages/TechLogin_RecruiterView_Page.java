package com.hcl.sv.pages;

import java.util.List;

import org.openqa.selenium.Alert;
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
 * Description: this class implements the methods for accessing elements of TechLogin Recruiter view page 
 * 
 * @author Abhishek
 * 
 */

public class TechLogin_RecruiterView_Page {

	public WebDriver driver;
	public WebActionUtil actionUtil;
	public long ETO;

	public TechLogin_RecruiterView_Page(WebDriver driver, long ETO, WebActionUtil actionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actionUtil = actionUtil;
		this.ETO = ETO;
	}

	/* Spinner */
	@FindBy(id = "processMessage")
	private WebElement spinner;

	/* Recruiter view text */
	@FindBy(xpath = "//td[@class='global-breadcrumb']/span[text()='Recruiter View']")
	private WebElement txtRecruiterView;

	/* Record Status drop down */
	@FindBy(xpath = "//td[contains(text(),'Record Status :')]/following-sibling::td/select")
	private WebElement ddRecordStatus;

	/* BGV ID text field */
	@FindBy(id = "ctl00_ContentPlaceHolder1_txtBGVID")
	private WebElement tbBGVID;

	/* Search button */
	@FindBy(xpath = "//input[@value='Search']")
	private WebElement btnSearch;

	/* Package drop down */
	@FindBy(id = "ctl00_ContentPlaceHolder1_ddlpackage")
	private WebElement ddPackage;

	/* Records text */
	private WebElement txtRecordTextFromTable(String bgvID) {
		String xpath = "//td[text()='" + bgvID + "']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Transaction remark text field */
	@FindBy(id = "ctl00_ContentPlaceHolder1_grdRecruiterView_ctl02_txtTransactionRemark")
	private WebElement tbTransactionRemark;

	/* Resume link */
	@FindBy(id = "ctl00_ContentPlaceHolder1_grdRecruiterView_ctl02_lnkResume")
	private WebElement lnkResume;

	/* View link */
	@FindBy(id = "ctl00_ContentPlaceHolder1_grdRecruiterView_ctl02_lnkUpload")
	private WebElement lnkView;

	/* Under Select column check box */
	@FindBy(id = "ctl00_ContentPlaceHolder1_grdRecruiterView_ctl02_chkAction")
	private WebElement ckbxUnderSelectColumn;

	/* Submit to BGV team button */
	@FindBy(id = "ctl00_ContentPlaceHolder1_btnSubmitInitiator")
	private WebElement btnSubmitToBgvTeam;

	/* Send to Candidate button */
	@FindBy(id = "ctl00_ContentPlaceHolder1_btnSubmitCandidate")
	private WebElement btnSendToCandidate;

	/* I am confirming that I verified the documents submitted by the candidateagainst the MRL list...check box*/
	@FindBy(id = "ctl00_ContentPlaceHolder1_chk")
	private WebElement ckbxIAmConfirmingThatIVerifiedTheDocumentsSubmitedByTheCandidateAgainstTheMRLList;

	/* Submitted documents list */
	@FindBy(xpath = "//input[@type='image']/parent::td/preceding-sibling::td[3]")
	private List<WebElement> lstSubmittedDcocuments;

	/* Desired document Remark text field */
	private WebElement tbRemarks(String documentType, String documentDescription) {
		String xpath = "//td[text()='" + documentType + "']/following-sibling::td[text()='" + documentDescription
				+ "']/following::textarea";
		return driver.findElement(By.xpath(xpath));
	}

	/* Send button */
	@FindBy(id = "btnSubmit")
	private WebElement btnSend;

	/* Close button */
	@FindBy(id = "btnClose")
	private WebElement btnClose;

	/* Logout link */
	@FindBy(id = "ctl00_Header1_lnkLogout")
	private WebElement lnkLogout;

	/**
	 * Description: This method is used to validate Recruiter view page.
	 * 
	 * @author Abhishek
	 */
	public synchronized void validateRecruiterViewPage() {
		try {
			WebActionUtil.waitForElement(txtRecruiterView, "Recruiter view text");
			if ((driver.getCurrentUrl().contains(BaseTest.prop_constants.getProperty("recruiter_View_Page_Url")))
					&& txtRecruiterView.isDisplayed()) {
				WebActionUtil.info("Recruiter view page is displayed");
				WebActionUtil.validationinfo("Recruiter view page is displayed", "blue");
			} else {
				WebActionUtil.error("Recruiter view page is not displayed");
				WebActionUtil.fail("Recruiter view page is not displayed");
				Assert.fail("Recruiter view page is not displayed");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Recruiter view page");
			Assert.fail("Unable to validate Recruiter view page");

		}
	}

	/**
	 * Description: This method used to select option from Record Status drop down.
	 * 
	 * @author Abhishek
	 * @param recordStatus
	 */
	public synchronized void selectRecordStatus(String recordStatus) {
		try {
			actionUtil.selectByText(ddRecordStatus, recordStatus);
			WebActionUtil.validateSelectedListboxOption(ddRecordStatus, recordStatus, "Record Status drop down");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to select " + recordStatus + " option from Record Status drop down");
			Assert.fail("Unable to select " + recordStatus + " option from Record Status drop down");
		}
	}

	/**
	 * Description: This method used to enter BGV ID in BGV ID text field.
	 * 
	 * @author Abhishek
	 * @param bgvID
	 */
	public synchronized void setBgvIdTextField(String bgvID) {
		try {
			WebActionUtil.typeText(tbBGVID, bgvID, "BGV ID text field");
			WebActionUtil.validateAttribute(tbBGVID, "value", bgvID, "BGV ID text field",
					"Typed: " + bgvID + " into BGV ID text field",
					"Unable to type " + bgvID + " into BGV ID text field", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to enter " + bgvID + " into BGV ID text field");
			Assert.fail("Unable to enter " + bgvID + " into BGV ID text field");
		}
	}

	
	/**
	 * Description: This method used to click on Search button.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkSearchButton() {
		try {
			WebActionUtil.clickOnElement(btnSearch, "Search button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Search button");
			Assert.fail("Unable to perform action on Search button");
		}
	}

	/**
	 * Description: This method used to select option from Package drop down.
	 * 
	 * @author Abhishek
	 * @param optPackage
	 */
	public synchronized void selectPackage(String optPackage) {
		try {
			actionUtil.selectByText(ddPackage, optPackage);
			WebActionUtil.validateSelectedListboxOption(ddPackage, optPackage, "Package drop down");
			WebActionUtil.waitForInvisibilityOfElement(spinner, "Spinner");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to select " + optPackage + " Option from Package drop down");
			Assert.fail("Unable to select " + optPackage + " Option from Package drop down");
		}
	}

	/**
	 * Description: This method used to verify that searched BGV ID is displayed.
	 * 
	 * @author Abhishek
	 * @param bgvID
	 */
	public synchronized void verifySearchedBgvIdIsDisplayed(String bgvID) {
		try {
			WebActionUtil.validateisElementDisplayed(txtRecordTextFromTable(bgvID), "BGV ID in table",
					"Searched BGV ID is displayed", "Searched BGV ID is not displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Searched BGV ID is not displayed");
			Assert.fail("Searched BGV ID is not displayed");
		}
	}

	/**
	 * Description: This method used to click on view link.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkViewLink() {
		try {
			WebActionUtil.clickOnElement(lnkView, "View link");
			WebActionUtil.switchWindows();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on view link");
			Assert.fail("Unable to perform action on view link");
		}
	}

	/**
	 * Description: This method used to click on Resume link.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkResumeLink() {
		try {
			WebActionUtil.clickOnElement(lnkResume, "Resume link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Resume link");
			Assert.fail("Unable to perform action on Resume link");
		}
	}
	/**
	 * Description: This method used to enter Transaction remark in Transaction remark text field.
	 * 
	 * @author Abhishek
	 * @param remark
	 */
	public synchronized void setTransactionRemarkTextField(String remark) {
		try {
			WebActionUtil.typeText(tbTransactionRemark, remark, "Transaction remark text field");
			WebActionUtil.validateAttribute(tbTransactionRemark, "value", remark, "Transaction remark text field",
					"Typed: " + remark + " into Transaction remark text field",
					"Unable to type " + remark + " into Transaction remark text field", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to enter " + remark + " into Transaction remark text field");
			Assert.fail("Unable to enter " + remark + " into Transaction remark text field");
		}
	}

	/**
	 * Description: This method used to click on I am confirming that I verified the
	 * documents submitted by the candidate against the MRL list Check box .
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkIAmConfirmingThatIVerifiedTheDocumentsSubmittedByTheCandidateAgainstTheMRLListCheckBox() {
		try {
			WebActionUtil.scrollDown();
			WebActionUtil.clickCheckBox(
					ckbxIAmConfirmingThatIVerifiedTheDocumentsSubmitedByTheCandidateAgainstTheMRLList,
					"I am confirming that I verified the documents submited by the candidate against the MRL list Check box ");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail(
					"Unable to perform action on I am confirming that I verified the documents submited by the candidate against the MRL list Check ox ");
			Assert.fail(
					"Unable to perform action on I am confirming that I verified the documents submited by the candidate against the MRL list Check Box ");
		}
	}

	/**
	 * Description: This method used to click on Check box under select column.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkCheckBoxUnderSelectColumn() {
		try {
			try {
				WebActionUtil.poll(1000);
				WebActionUtil.clickOnElement(ckbxUnderSelectColumn, "Check box under select column");
			} catch (Exception e) {
				if (ckbxUnderSelectColumn.isSelected()) {
					WebActionUtil.validationinfo("Check box under select column", "blue");
				} else {
					WebActionUtil.clickCheckBox(ckbxUnderSelectColumn, "Check box under select column");
				}
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("unable to perform action on Check box under select column");
			Assert.fail("unable to perform action on Check box under select column");
		}
	}

	/**
	 * Description: This method used to click on Submit to BGV team button.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkSubmitToBgvTeamButton() {
		try {
			WebActionUtil.clickOnElement(btnSubmitToBgvTeam, "Submit to BGV team button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("unable to perform action on Submit to BGV team button");
			Assert.fail("unable to perform action on Submit to BGV team button");
		}
	}

	/**
	 * Description: This method used to click on Send to Candidate button.
	 * 
	 * @author Abhishek
	 * 
	 */
	public synchronized void clkSendToCandidateButton() {
		try {
			WebActionUtil.clickOnElement(btnSendToCandidate, "Send to Candidate button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("unable to perform action on Send to Candidate button");
			Assert.fail("unable to perform action on Send to Candidate button");
		}
	}

	/**
	 * Description: This method used to enter Remark in Remark text field.
	 * 
	 * @author Abhishek
	 * @param documentType
	 * @param documentDescription
	 * @param remarks
	 */
	public synchronized void setRemarkField(String documentType, String documentDescription, String remarks) {
		try {
			WebActionUtil.typeText(tbRemarks(documentType, documentDescription), remarks, "Remark text field");
			WebActionUtil.validateAttribute(tbBGVID, "value", remarks, "Remark text field",
					"Typed: " + remarks + " into Remark text field",
					"unable to type " + remarks + " into Remark text field", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("unable to enter " + remarks + " into Remark text field");
			Assert.fail("unable to enter " + remarks + " into Remark text field");
		}
	}



	/**
	 * Description: This method used to click on Logout link.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkLogoutLink() {
		try {
			WebActionUtil.clickOnElement(lnkLogout, "Logout link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("unable to perform action on Logout link");
			Assert.fail("unable to perform action on Logout Link");
		}
	}

	/**
	 * Description : This method is used to validate Alert pop up confirmation
	 * message.
	 * 
	 * @author Abhishek
	 * @param expectedAlertMessage
	 */
	public synchronized void validateAlertPopup(String expectedAlertMessage) {
		try {
			WebDriverWait wait=new WebDriverWait(driver, 5);
			if(wait.until(ExpectedConditions.alertIsPresent())==null) {
			}else {
			WebActionUtil.poll(2000);
			Alert alert = driver.switchTo().alert();
			String actualAlertMessage = alert.getText();
			Assert.assertTrue(actualAlertMessage.toLowerCase().contains(expectedAlertMessage.toLowerCase()));
			WebActionUtil.info("Alert popup with " + actualAlertMessage + " message is displayed");
			WebActionUtil.validationinfo("Alert popup with " + actualAlertMessage + " message is displayed", "green");
			alert.accept();
			WebActionUtil.info("clicked on ok button of " + actualAlertMessage + " Alert popup");
			WebActionUtil.extentinfo("clicked on ok button of " + actualAlertMessage + " Alert popup");
		} 
		}catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("unable to validate Alert pop up");
			Assert.fail("unable to validate Alert pop up");
		}
	}

	/**
	 * Description: This method used to verify that Employee details is disappeared.
	 * 
	 * @author Abhishek
	 * @param bgvID
	 */
	public synchronized void verifyEmployeeDetailIsDisappeared(String bgvID) {
		try {
			try {
				WebActionUtil.poll(1000);
				if (txtRecordTextFromTable(bgvID).isDisplayed()) {
					WebActionUtil.error("Employee details is not disappeared");
					WebActionUtil.validationinfo("Employee details is not disappeared", "Red");
					Assert.fail("Employee details is not disappeared");
				}
			} catch (Exception e1) {
				WebActionUtil.info("Employee details is disappeared");
				WebActionUtil.validationinfo("Employee details is disappeared", "Green");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Employee details is not disappeared");
			Assert.fail("Employee details is not disappeared");
		}
	}

	/**
	 * Description: This method used to wait for invisibility of spinner.
	 * 
	 * @author Abhishek
	 */
	public synchronized void waitForInvisibilityOfSpinner() {
		try {
			WebActionUtil.poll(45000);
			WebActionUtil.info("Spinner disappeared");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Spinner is visible");
		}
	}

}
