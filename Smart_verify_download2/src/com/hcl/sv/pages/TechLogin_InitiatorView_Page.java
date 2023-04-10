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
 * Description: This class implements the methods for accessing elements of TechLogin Initiator view page  
 * 
 * @author Abhishek
 * 
 */
public class TechLogin_InitiatorView_Page {

	public WebDriver driver;
	public WebActionUtil actionUtil;
	public long ETO;

	public TechLogin_InitiatorView_Page(WebDriver driver, long ETO, WebActionUtil actionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actionUtil = actionUtil;
		this.ETO = ETO;
	}

	/* Initiator view text */
	@FindBy(xpath = "//span[text()='Initiator View']")
	private WebElement txtInitiatorView;

	/* Type options */
	public WebElement optType(String type) {
		String xpath = "//label[text()='" + type + "']/preceding-sibling::input";
		return driver.findElement(By.xpath(xpath));
	}

	/* Record status drop down */
	@FindBy(xpath = "//td[contains(text(),'Record Status :')]/following-sibling::td/select")
	private WebElement ddRecordStatus;

	/* BGV ID text field */
	@FindBy(id = "ctl00_ContentPlaceHolder1_txtBGVID")
	private WebElement tbBGVID;

	/* Search button */
	@FindBy(xpath = "//input[@value='Search']")
	private WebElement btnSearch;

	/* Clear button */
	@FindBy(xpath = "//input[@value='Clear']")
	private WebElement btnClear;

	/* Mark the check box in case of TP/RE band and geo entity while closing the case check box */
	@FindBy(xpath = "//label[text()='Mark the check box in case of TP/RE Band And Geo Entity While closing the case']/preceding-sibling::input[@type='checkbox']")
	private WebElement ckbxMarkTheCheckBoxInCaseOfTPREBandAndGeoEntityWhileClosingTheCase;

	/* Submit to vendor button */
	@FindBy(id = "ctl00_ContentPlaceHolder1_btnInitiateRequest")
	private WebElement btnSubmitToVendor;

	/* Send to recruiter/HR button */
	@FindBy(id = "ctl00_ContentPlaceHolder1_btnSubmitApprover")
	private WebElement btnSendToRecruiterOrHR;

	/* Close case button */
	@FindBy(xpath = "//input[@value='Close Case']")
	private WebElement btnCloseCase;

	/* View/edit link */
	@FindBy(id = "ctl00_ContentPlaceHolder1_grdEHSView_ctl02_lnkUpload")
	private WebElement lnkViewOrEdit;

	/* Transaction remark text field */
	@FindBy(id = "ctl00_ContentPlaceHolder1_grdEHSView_ctl02_txtTransactionRemark")
	private WebElement tbTransactionRemark;

	/* Under select column check box */
	@FindBy(id = "ctl00_ContentPlaceHolder1_grdEHSView_ctl02_chkAction")
	private WebElement ckbxUnderSelectColumn;

	/* Verify checks button */
	@FindBy(id = "btnSubmitChecks")
	private WebElement btnVerifyChecks;

	/* QC team remarks drop down */
	@FindBy(id = "ddlReasonInitiator")
	private WebElement ddQcTeamRemarks;

	/* QC team remark submit reason button */
	@FindBy(id = "btnReasoToVendor")
	private WebElement btnQcTeamRemarkSubmitReason;

	/* Close button */
	@FindBy(id = "btnClose")
	private WebElement btnClose;
	
	/* Upload link */
	@FindBy(xpath="//a[text()='Upload']")
	private WebElement lnkUpload;

	/* Records text */
	@FindBy(xpath="//span[contains(text(),'Total Records: 1')]")
	private WebElement txtRecords;

	/* Employee background check text */
	@FindBy(xpath="//td[contains(text(),'Employee Backgroud Check') or contains(text(),'Background Check Details')]")
	private WebElement txtEmployeeBackgroundCheck;
	
	/* Select all check box */
	@FindBy(xpath="//label[text()='Select All']/preceding-sibling::input")
	private WebElement cbxSelectAll;
	
	/* Select required checks */
	@FindBy(xpath="//td[normalize-space(text())='Select Required Checks']/parent::tr/following-sibling::tr/descendant::input[@type='checkbox']")
	private List<WebElement > cbSelectRequiredChecks;
	
	/* Other reason text field */
	@FindBy(id="txtOthersReason")
	private WebElement tbOtherReason;
	
	/*Candidate Details text*/
	@FindBy(xpath="//td[contains(text(),'Candidate Details')]")
	private WebElement txtCandidateDetails;
	
	/*Ok button*/
	@FindBy(xpath="//input[@value='Ok']")
	private WebElement btnOk;
	
	/* Send to employee button */
	@FindBy(xpath = "//input[@value='Send to Employee']")
	private WebElement btnSendToEmployee;
	
	/* Spinner */
	@FindBy(xpath = "//div[@id='processMessage']/img")
	private WebElement spinner1;

	/* Logout link */
	@FindBy(id = "ctl00_Header1_lnkLogout")
	private WebElement lnkLogout;

	/* Uploaded documents with search button */
	private WebElement btnUploadedDocument(String documentType, String documentDescription) {
		String xpath = "//td[text()='" + documentType + "']/following-sibling::td[text()='" + documentDescription
				+ "']/following::input[@type='image']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Spinner */
	@FindBy(xpath = "//div[@id='processMessage']/img")
	private WebElement spinner;

	/* View BGV details link */
	private WebElement lnkViewBGVDetails(String bgvId) {
		String xpath = "//td[text()='" + bgvId + "']/following-sibling::td/a[contains(@id,'lnkUpload')]";
		return driver.findElement(By.xpath(xpath));
	}
	
	/* Click on initiative BGV button */
	@FindBy(xpath = "//td[@align='center']/child::input[@value='Initiate BGV']")
	private WebElement btnInitiativeBGV;
	
	/**
	 * Description: This method is used to validate tech login Initiator view page.
	 * 
	 * @author Abhishek
	 */
	public synchronized void validateTechLoginInitiatorViewPage() {
		try {
			WebActionUtil.waitForElement(txtInitiatorView, "Initiator View Text");
			if ((driver.getCurrentUrl().contains(BaseTest.prop_constants.getProperty("initiator_View_Page_Url")))
					&& txtInitiatorView.isDisplayed()) {
				WebActionUtil.info("TechLogin Initiator View Page is displayed");
				WebActionUtil.validationinfo("TechLogin Initiator View Page is displayed", "blue");
			} else {
				WebActionUtil.error("TechLogin Initiator View Page is not displayed");
				WebActionUtil.fail("TechLogin Initiator View Page is not displayed");
				Assert.fail("TechLogin Initiator View Page is not displayed");
			}
		} catch (Exception e) {
			WebActionUtil.error("Unable to validate TechLogin Initiator View Page");
			WebActionUtil.fail("Unable to validate TechLogin Initiator View Page");
			Assert.fail("Unable to validate TechLogin Initiator View Page");

		}
	}

	/**
	 * Description: This method used to click on type of employee radio button.
	 * 
	 * @author Abhishek
	 * @param type
	 */
	public synchronized void clkTypeRadioButton(String type) {
		try {
			try {
				WebActionUtil.clickCheckBox(optType(type), "Type of employee Radio Button");
				WebActionUtil.validateisElementSelected(optType(type), "Type of employee Radio Button",
						type + " Type of employee Radio Button is Selected",
						type + " Type of employee Radio Button is not selected", "blue");
			} catch (Exception e1) {
				WebActionUtil.clickCheckBox(optType(type), "Type of employee Radio Button");
				WebActionUtil.validateisElementSelected(optType(type), "Type of employee Radio Button",
						type + " Type of employee Radio Button is Selected",
						type + " Type of employee Radio Button is not selected", "blue");
			}
			WebActionUtil.waitForInvisibilityOfElement(spinner, "spinner");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Perform action on " + type + " Type Radio Button");
			Assert.fail("Unable to Perform action on " + type + " Type Radio Button");
		}
	}

	/**
	 * Description: This method used to select option from record status drop down.
	 * 
	 * @author Abhishek
	 * @param recordStatus
	 */
	public synchronized void selectRecordStatus(String recordStatus) {
		try {
			WebActionUtil.poll(1000);
			actionUtil.selectByText(ddRecordStatus, recordStatus);
			try {
				WebActionUtil.validateSelectedListboxOption(ddRecordStatus, recordStatus, "Record Status Drop Down");
			} catch (Exception e) {
				actionUtil.selectByText(ddRecordStatus, recordStatus);
				WebActionUtil.validateSelectedListboxOption(ddRecordStatus, recordStatus, "Record Status Drop Down");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Select " + recordStatus + " Option From Record Status Drop Down");
			Assert.fail("Unable to Select " + recordStatus + " Option From Record Status Drop Down");
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
			actionUtil.typeTextUsingJS(tbBGVID, "ctl00_ContentPlaceHolder1_txtBGVID", "BGV ID Text Field", bgvID);
			WebActionUtil.validateAttribute(tbBGVID, "value", bgvID, "BGV ID Text Field",
					"Typed: " + bgvID + " into BGV ID Text Field",
					"Unable to type " + bgvID + " into BGV ID Text Field", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			e.printStackTrace();
			WebActionUtil.fail("Unable to enter " + bgvID + " into BGV ID Text Field");
			Assert.fail("Unable to enter " + bgvID + " into BGV ID Text Field");
		}
	}


	/**
	 * Description: This method used to click on search button.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkSearchButton() {
		try {
			try {
				WebActionUtil.clickOnElement(btnSearch, "Search Button");
			} catch (Exception e) {
				WebActionUtil.actionClick(btnSearch, "Search Button");
			}
			WebActionUtil.waitForInvisibilityOfElement(spinner, "Spinner");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Search button");
			Assert.fail("Unable to click on Search button");
		}
	}

	/**
	 * Description: This method used to click on clear button.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkClearButton() {
		try {
			WebActionUtil.clickOnElement(btnClear, "Clear Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Clear button");
			Assert.fail("Unable to click on Clear button");
		}
	}

	/**
	 * Description: This method used to verify that searched BGV ID is displayed.
	 * 
	 * @author Abhishek
	 */
	public synchronized void verifySearchedBgvIdIsDisplayed() {
		try {
			WebActionUtil.validateisElementDisplayed(txtRecords, "BGV ID in table",
					"Searched BGV ID is displayed in Total records", "Searched BGV ID is not displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Searched BGV ID is not displayed");
			Assert.fail("Searched BGV ID is not displayed");
		}
	}

	/**
	 * Description: This method used to click on view/edit link.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkViewOrEditLink() {
		try {
			WebActionUtil.clickOnElement(lnkViewOrEdit, "View/Edit Link");
			WebActionUtil.switchWindows();
			validateManageBackgroundChecksOfInitiatorViewPage();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on View/Edit Link");
			Assert.fail("Unable to perform action on View/Edit Link");
		}
	}

	/**
	 * Description: This method used to select option from Qc team remark drop down.
	 * 
	 * @author Abhishek
	 * @param remarks
	 */
	public synchronized void selectQcTeamRemark(String remarks) {
		try {
			WebActionUtil.scrollToElement(ddQcTeamRemarks,"Qc Team Remark Drop Down");
			actionUtil.selectByText(ddQcTeamRemarks, remarks);
			WebActionUtil.validateSelectedListboxOption(ddQcTeamRemarks, remarks, "Qc Team Remark Drop Down");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Select " + remarks + " Option From Qc Team Remark Drop Down");
			Assert.fail("Unable to Select " + remarks + " Option From Qc Team Remark Drop Down");
		}
	}

	/**
	 * Description: This method used to enter transaction remark in transaction
	 * remark text field.
	 * 
	 * @author Abhishek
	 * @param remark
	 */
	public synchronized void setTransactionRemarkTextField(String remark) {
		try {
			WebActionUtil.typeText(tbTransactionRemark, remark, "Transaction remark text field");
			WebActionUtil.validateAttribute(tbTransactionRemark, "value", remark, "Transaction remark Text field",
					"Typed: " + remark + " into Transaction remark Text field",
					"Unable to type " + remark + " into Transaction remark Text field", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to enter " + remark + " into Transaction remark text field");
			Assert.fail("Unable to enter " + remark + " into Transaction remark text field");
		}
	}

	/**
	 * Description: This method used to click on check box under select column.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkCheckBoxUnderSelectColumn() {
		try {
			try {
				WebActionUtil.clickCheckBox(ckbxUnderSelectColumn, "Check Box under Select column");
			} catch (Exception e2) {
				WebActionUtil.validateisElementSelected(ckbxUnderSelectColumn, "Check Box under Select column",
						"Check Box under Select column is Selected", " Check Box under Select column is not selected",
						"blue");
			}
			try {
				WebActionUtil.waitForInvisibilityOfElement(spinner1, "Spinner");
				WebActionUtil.info("Spinner is disappeared");
			} catch (Exception e) {
				WebActionUtil.fail("Spinner is visible");
			}
			try {
				if(cbxSelectAll.isDisplayed()) {
					try {
						clkSelectRequiredChecksCheckBox();
					} catch (Exception e) {
						WebActionUtil.fail("Unable to click Requred check box");
					}
				}
			}
			catch(Exception e1) {
				WebActionUtil.info("Select all CheckBox is not displayed");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Perform action on Check Box under Select column");
			Assert.fail("Unable to Perform action on Check Box under Select column");
		}
	}

	/**
	 * Description: This method used to click on mark the check box in case of TP/RE
	 * band and geo entity while closing the case check box.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkMarkTheCheckBoxInCaseOfTPREBandAndGeoEntityWhileClosingTheCaseCheckBox() {
		try {
			WebActionUtil.scrollDownByCoordinates();
			WebActionUtil.poll(2000);
			WebActionUtil.clickCheckBox(ckbxMarkTheCheckBoxInCaseOfTPREBandAndGeoEntityWhileClosingTheCase,
					"Mark the check box in case of TP/RE Band And Geo Entity While closing the case BoxCheck Box");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail(
					"Unable to perform action on Mark the check box in case of TP/RE Band And Geo Entity While closing the case Check Box");
			Assert.fail(
					"Unable to perform action on Mark the check box in case of TP/RE Band And Geo Entity While closing the case Check Box");
		}
	}

	/**
	 * Description: This method used to click on submit to vendor button.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkSubmitToVendorButton() {
		try {
			WebActionUtil.clickOnElement(btnSubmitToVendor, "Submit to Vendor Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Submit to Vendor button");
			Assert.fail("Unable to perform action on Submit to Vendor button");
		}
	}

	/**
	 * Description: This method used to click on send to recruiter/HR button.
	 * 
	 * @author Abhishek
	 * @param expectedAlertMessage
	 */
	public synchronized void clkSendToRecruiterOrHRButton(String expectedAlertMessage) {
		try {
			WebActionUtil.clickOnElement(btnSendToRecruiterOrHR, "Send to Recruiter/HR Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Send to Recruiter/HR button");
			Assert.fail("Unable to perform action on Send to Recruiter/HR button");
		}
	}

	/**
	 * Description: This method used to click on close case button.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkCloseCaseButton() {
		try {
			WebActionUtil.clickOnElement(btnCloseCase, "Close Case Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Close Case button");
			Assert.fail("Unable to perform action on Close Case button");
		}
	}

	/**
	 * Description: This method used to verify that all the documents are uploaded.
	 * 
	 * @author Abhishek
	 */
	public synchronized void verifyThatAllTheDocumentsAreUploaded(String[] documentType, String[] documentDescription) {
		boolean flag = false;
		try {
			for (int i = 0; i < documentType.length; i++) {
				try {
					WebActionUtil.scrollToElement(btnUploadedDocument(documentType[i], documentDescription[i]), documentType[i]+" document");
					if (btnUploadedDocument(documentType[i], documentDescription[i]).isDisplayed()) {
						WebActionUtil.info(documentType[i] + " is uploaded Showing with search button");
					}
				} catch (Exception e1) {
					WebActionUtil.error(documentType[i] + " is not uploaded");
					flag = true;
				}
			}
			if (flag == true) {
				WebActionUtil.error("All the documents are not uploaded");
				WebActionUtil.fail("All the documents are not uploaded");
				Assert.fail("All the documents are not uploaded ");
			} else {
				WebActionUtil.pass("All the documents are uploaded");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to verify all the documents are uploaded");
			Assert.fail("Unable to verify all the documents are uploaded");
		}
	}

	/**
	 * Description: This method used to click on logout link.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkLogoutLink() {
		try {
			WebActionUtil.clickOnElement(lnkLogout, "Logout Link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Logout Link");
			Assert.fail("Unable to perform action on Logout Link");
		}
	}

	/**
	 * Description : This method is used to validate alert pop up confirmation
	 * message.
	 * 
	 * @author Abhishek
	 * @param expectedAlertMessage
	 */
	public synchronized void validateAlertPopup(String expectedAlertMessage) {
		try {
			try {
				Alert alert = driver.switchTo().alert();
				String actualAlertMessage = alert.getText();
				WebActionUtil.info(actualAlertMessage);
				Assert.assertTrue(actualAlertMessage.toLowerCase().contains(expectedAlertMessage.toLowerCase()));
				WebActionUtil.info("Alert popup with " + actualAlertMessage + " message is displayed");
				WebActionUtil.validationinfo("Alert popup with " + actualAlertMessage + " message is displayed", "blue");
				alert.accept();
				WebActionUtil.info("Clicked on ok Button of " + actualAlertMessage + " Alert popup");
				WebActionUtil.extentinfo("Clicked on ok Button of " + actualAlertMessage + " Alert popup");
			} catch (Exception e) {
				try {
					WebDriverWait wait=new WebDriverWait(driver, 5);
					if(wait.until(ExpectedConditions.alertIsPresent())==null) {				
					}
				} catch (Exception e1) {
					WebActionUtil.info("Alert pop is not present");	
				}
			}		
		}catch (Exception | AssertionError e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Alert pop up");
			Assert.fail("Unable to perform action on Alert pop up");
		}
	}

	/**
	 * Description: This method used to wait for spinner to be invisible.
	 * 
	 * @author Abhishek
	 */
	public synchronized void waitForSpinner() {
		try {
			if (spinner.isDisplayed()) {
				WebActionUtil.waitForInvisibilityOfElement(spinner, "Spinner");
				WebActionUtil.info("Spinner is handled Successfully");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to handle Spinner");
			Assert.fail("Unable to handle Spinner");
		}
	}


	/**
	 * Description: This method used to click on view BGV details link.
	 * 
	 * @author Abhishek
	 * @param bgvId
	 */
	public synchronized void clkViewBGVDetailsLink(String bgvId) {
		try {
			WebActionUtil.clickOnElement(lnkViewBGVDetails(bgvId), bgvId + " View BGV Details Link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on " + bgvId + " View BGV Details Link");
			Assert.fail("Unable to perform action on " + bgvId + " View BGV Details Link");
		}
	}

	/**
	 * Description: This method used to click on initiate BGV button
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkInitiateBGVButton() {
		try {
			WebActionUtil.clickOnElement(btnInitiativeBGV, "Initiate BGV Button");
			WebActionUtil.poll(3000);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Initiate BGV Button");
			Assert.fail("Unable to perform action on Initiate BGV Button");
		}
	}

	/**
	 * Description: This method used to verify that employee details is disappeared.
	 * 
	 * @author Abhishek
	 */
	public synchronized void verifyEmployeeDetailIsDisappeared() {
		try {
			try {
				if (txtRecords.isDisplayed()) {
					WebActionUtil.error("Employee Details is not disappeared");
					WebActionUtil.extentinfo("Employee Details is not disappeared");
					Assert.fail("Employee Details is not disappeared");
				} 
			}
			catch(Exception e1) {
				WebActionUtil.info("Employee Details is disappeared");
				WebActionUtil.validationinfo("Employee Details disappeared", "blue");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Employee Details is not disappeared");
			Assert.fail("Employee Details is not disappeared");
		}
	}


	/**
	 * Description: This method used to click on send to employee button.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkSendToEmployeeButtton() {
		try {
			WebActionUtil.clickOnElement(btnSendToEmployee, "Send to employee Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Send to employee Button");
			Assert.fail("Unable to perform action on Send to employee Button");
		}
	}


	/**
	 * Description: This method used to click on verify checks button.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkVerifyChecksButton() {
		try {
			WebActionUtil.scrollToElement(btnVerifyChecks, "Verify Checks Button");
			WebActionUtil.poll(1000);
			WebActionUtil.clickOnElement(btnVerifyChecks, "Verify Checks Button");

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Verify Checks Button");
			Assert.fail("Unable to perform action on Verify Checks Button");
		}
	}

	/**
	 * Description: This method used to switch to parent window
	 * 
	 * @author Abhishek
	 * @param parentWindow
	 * 
	 */
	public synchronized void switchParentWindow(String parentWindow) {
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
	 * Description: This method used to click on close button.
	 * 
	 * @author Abhishek
	 * @param parentWindow 
	 */
	public synchronized void clkCloseButton(String parentWindow) {
		try {
			WebActionUtil.clickOnElement(btnClose, "Close button");
			WebActionUtil.switchParentWindow(parentWindow);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Perform action on Close button");
			Assert.fail("Unable to Perform action on Close button");
		}
	}


	/**
	 * Description: This method used to click on Qc team remark submit reason
	 * button.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkQcTeamRemarkSubmitReasonButton() {
		try {
			WebActionUtil.clickOnElement(btnQcTeamRemarkSubmitReason, "Qc Team Remark Submit Reason Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Qc Team Remark Submit Reason button");
			Assert.fail("Unable to perform action on Qc Team Remark Submit Reason button");
		}
	}

	/**
	 * Description: This method used to click on upload link
	 * button.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkUploadLink() {
		try {
			WebActionUtil.clickOnElement(lnkUpload, "Upload link");
			WebActionUtil.switchWindows();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Upload link");
			Assert.fail("Unable to perform action on Upload link");
		}
	}


	/**
	 * Description: This method is used to validate employee background check
	 * initiator view page.
	 * 
	 * @author Abhishek
	 */
	public synchronized void validateManageBackgroundChecksOfInitiatorViewPage() {
		try {
			WebActionUtil.validateisElementDisplayed(txtEmployeeBackgroundCheck, "Manage Backgroud Check", "Manage Backgroud Check page is displayed", "Backgroud Check page is not displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Manage Background Checks of Initiator View Page");
			Assert.fail("Unable to validate Manage Background Checks of Initiator View Page");

		}
	}

	/**
	 * Description: This method used to click on select all check box
	 * button.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkSelectAllCheckBox() {
		try {
			WebActionUtil.clickOnElement(cbxSelectAll, "Select All Check box");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Select All Check box");
			Assert.fail("Unable to perform action on Select All Check box");
		}
	}

	/**
	 * Description: This method used to click on select all check box
	 * button.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkSelectRequiredChecksCheckBox() {
		try {
			for(int i=0;i<cbSelectRequiredChecks.size();i++) {
				if(cbSelectRequiredChecks.get(i).isDisplayed()) {

					try {
						cbSelectRequiredChecks.get(i).click();
					} catch (Exception e) {
						WebActionUtil.scrollToElement(cbSelectRequiredChecks.get(i), "Select Required Checks");
						cbSelectRequiredChecks.get(i).click();
						e.printStackTrace();
					}
				}
			}
			WebActionUtil.info("Clicked on all Required checks Check box");
			WebActionUtil.extentinfo("Clicked on all Required checks Check box");

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Select Required Checks");
			Assert.fail("Unable to perform action on Select Required Checks");
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


	/**
	 * Description: This method used to enter other reason in other reason text field.
	 * 
	 * @author Abhishek
	 * @param reason
	 */
	public synchronized void setOtherReasonTextField(String reason) {
		try {
			WebActionUtil.poll(2000);
			WebActionUtil.typeText(tbOtherReason, reason, "Other reason Text Field");
			WebActionUtil.validateAttribute(tbOtherReason, "value", reason, "Other reason Text Field",
					"Able to type " + reason + " into Other reason Text Field",
					"Unable to type " + reason + " into Other reason Text Field", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to enter " + reason + " into Other reason Text Field");
			Assert.fail("Unable to enter " + reason + " into Other reason Text Field");
		}
	}
	
	/**
	 * Description: This method is used to validate Candidate details page.
	 * 
	 * @author Abhishek
	 */
	public synchronized void validateCandidateDetailsPage(){

		try {
			WebActionUtil.switchWindows();
			try {
				WebActionUtil.waitForElement(txtCandidateDetails, "Candidate Details text");
			} catch (Exception e) {
				WebActionUtil.switchWindows();
			}
			 WebActionUtil.validateisElementDisplayed(txtCandidateDetails, "Candidate Details text", "Candidate details page is displayed", "Candidate details page is not displayed", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate candidate details Page");
			Assert.fail("Unable to validate candidate details Page");

		}
	}

	/**
	 * Description: This method used to click on Ok button.
	 * 
	 * @author Abhishek
	 * @param parentWindow 
	 */
	public synchronized void clkOkButton(String parentWindow) {
		try {
			WebActionUtil.clickOnElement(btnOk, "Ok button");
			WebActionUtil.switchParentWindow(parentWindow);
			validateTechLoginInitiatorViewPage();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Ok button");
			Assert.fail("Unable to perform action on Ok button");
		}
	}
	
	/**
	 * Description: This method used to verify that employee details is disappeared.
	 * 
	 * @author Abhishek
	 */
	public synchronized void validateEmployeeDetailIsDisappeared() {
		try {
			try {
				if (txtRecords.isDisplayed()) {
					WebActionUtil.info("Employee Details is not disappeared");
					WebActionUtil.extentinfo("Employee Details is not disappeared");
				} 
			}
			catch(Exception e1) {
				WebActionUtil.info("Employee Details is disappeared");
				WebActionUtil.validationinfo("Employee Details disappeared", "blue");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Employee Details is not disappeared");
			Assert.fail("Employee Details is not disappeared");
		}
	}
	
}
