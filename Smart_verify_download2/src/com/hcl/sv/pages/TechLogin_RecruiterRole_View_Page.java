package com.hcl.sv.pages;

import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.hcl.sv.util.WebActionUtil;

/**
 * Description: This class implements the methods for tech login Recruiter role view page(Manage background check window).
 * 
 * @author SreeLatha
 */

public class TechLogin_RecruiterRole_View_Page {
	public WebDriver driver;
	public WebActionUtil actionUtil;
	public long ETO;

	public TechLogin_RecruiterRole_View_Page(WebDriver driver, long ETO, WebActionUtil actionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actionUtil = actionUtil;
		this.ETO = ETO;
	}

	/* Send button */
	@FindBy(id = "btnSubmit")
	private WebElement btnSend;

	/* Close button */
	@FindBy(id = "btnClose")
	private WebElement btnClose;
	
	/* Background check details */
	@FindBy(xpath="//td[contains(text(),'Background Check Details')]")
	private WebElement txtBackgroundCheckDetails;
	
	/* Refer back joining form to candidate remark text field */
	@FindBy(xpath = "//textarea[@name='txtJoiningformRemarks']")
	private WebElement tbReferBackJoiningFormToCandidateRemarks;

	/* Refer back joining form button */
	@FindBy(id="btnFormReferback")
	private WebElement btnReferBackjoiningForm;
	
	/* Mark insufficiency button */
	@FindBy(id="btnMarkInsifficiency")
	private WebElement btnMarkInsuffiency;

	/* Uploaded documents with search button */
	private WebElement btnUploadedDocument(String documentType, String documentDescription) {
		String xpath = "//td[text()='" + documentType + "']/following-sibling::td[text()='" + documentDescription
				+ "']/following::input[@type='image']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Insufficient check box */
	private WebElement ckbxInsufficient(String documentType, String documentDescription) {
		String xpath = "//td[text()='"+documentType+"']/following-sibling::td[text()='"+documentDescription+"']/following-sibling::td/input[@type='image']/parent::td/following::input[@type='checkbox']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Submitted documents list */
	@FindBy(xpath = "//input[@type='image']/parent::td/preceding-sibling::td[3]")
	private List<WebElement> lstSubmittedDcocuments;

	/* Mark insufficiency button */
	@FindBy(id = "btnMarkInsifficiency")
	private WebElement btnMarkInsufficiency;

	/* Joining exception category drop down */
	@FindBy(id = "ddlExceptionDocCategory")
	private WebElement ddExceptionCategory;

	/* Joining exception reason drop down */
	@FindBy(id = "ddlReason")
	private WebElement ddExceptionReason;

	/* Joining exception choose file button */
	@FindBy(xpath="//input[@id='fuExc_FileUpload']")
	private WebElement btnJoiningExceptionChooseFile;

	/* Joining exception remark text field */
	@FindBy(id = "txtExc_Remarks")
	private WebElement tbJoiningExceptionRemark;

	/* Upload exception button */
	@FindBy(id = "btnUploadException")
	private WebElement btnUploadException;

	/* Desired document remark text field */
	private WebElement tbRemarks(String documentType, String documentDescription) {
		String xpath = "//td[text()='"+documentType+"']/following-sibling::td[text()='"+documentDescription+"']/following-sibling::td/input[@type='image']/parent::td/following-sibling::td/textarea";
		return driver.findElement(By.xpath(xpath));
	}


	/**
	 * Description: This method used to select option from exception category drop down
	 * 
	 * @author SreeLatha
	 * @param exceptionCategory
	 */
	public synchronized void selectExceptionCategory(String exceptionCategory) {
		try {
			actionUtil.selectByText(ddExceptionCategory, exceptionCategory);
			WebActionUtil.validateSelectedListboxOption(ddExceptionCategory, exceptionCategory,
					"Exception Category Drop Down");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Select " + exceptionCategory + " Option From Exception Category Drop Down");
			Assert.fail("Unable to Select " + exceptionCategory + " Option From Exception Category Drop Down");
		}
	}

	/**
	 * Description: This method used to select option from reason drop down.
	 * 
	 * @author SreeLatha
	 * @param exceptionReason
	 */
	public synchronized void selectExceptionReason(String exceptionReason) {
		try {
			actionUtil.selectByText(ddExceptionReason, exceptionReason);
			WebActionUtil.validateSelectedListboxOption(ddExceptionReason, exceptionReason, "Reason Drop Down");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Select " + exceptionReason + " Option From Reason Drop Down");
			Assert.fail("Unable to Select " + exceptionReason + " Option From Reason Drop Down");
		}
	}

	/**
	 * Description: This method used to click on joining exception choose file button.
	 * 
	 * @author SreeLatha
	 */
	public synchronized void clkJoiningExceptionChooseFileButton() {
		try {
			WebActionUtil.scrollToElement(btnJoiningExceptionChooseFile, "Joining Exception Choose File Button");
			WebActionUtil.actionClick(btnJoiningExceptionChooseFile, "Joining Exception Choose File Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Joining Exception Choose File Button");
			Assert.fail("Unable to perform action on Joining Exception Choose File Button");
		}
	}

	/**
	 * Description: This method used to click on choose file button.
	 * 
	 * @author SreeLatha
	 * @param imagePath
	 */
	public synchronized void uploadFile(String imagePath) {
		try {
			WebActionUtil.upload(imagePath);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to upload the File");
			Assert.fail("Unable to upload the File");
		}
	}

	/**
	 * Description: This method used to validate uploaded file.
	 * 
	 * @author SreeLatha
	 * @param imagePath
	 */
	public synchronized void validateFileUploaded(String imagePath) {
		try {
			WebActionUtil.waitForElement(btnJoiningExceptionChooseFile, "Joining Exception Choose File Button");
			String actualFileName = btnJoiningExceptionChooseFile.getText();
			Assert.assertTrue((imagePath.toLowerCase()).contains(actualFileName.toLowerCase()));
			WebActionUtil.info(actualFileName+" is Selected and displayed");
			WebActionUtil.validationinfo(actualFileName+" is Selected and displayed","blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate File is Uploaded");
			Assert.fail("Unable to validate File is Uploaded");
		}
	}

	/**
	 * Description: This method used to set joining exception remark text field.
	 * 
	 * @author SreeLatha
	 * @param exceptionRemark
	 */
	public synchronized void setJoiningExceptionRemarkTextField(String exceptionRemark) {
		try {
			WebActionUtil.typeText(tbJoiningExceptionRemark, exceptionRemark, "Joining Exception Remark Text Field");
			WebActionUtil.validateAttribute(tbJoiningExceptionRemark, "value", exceptionRemark,
					"Joining Exception Remark Text Field",
					"Typed: " + exceptionRemark + " into Joining Exception Remark Text Field",
					"Unable to type " + exceptionRemark + " into Joining Exception Remark Text Field", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to enter " + exceptionRemark + " into Joining Exception Remark Text Field");
			Assert.fail("Unable to enter " + exceptionRemark + " into Joining Exception Remark Text Field");
		}
	}

	/**
	 * Description: This method used to click on upload exception button.
	 * 
	 * @author SreeLatha
	 */
	public synchronized void clkUploadExceptionButton() {
		try {
			WebActionUtil.clickOnElement(btnUploadException, "Upload Exception button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Upload Exception button");
			Assert.fail("Unable to perform action on Upload Exception button");
		}
	}

	/**
	 * Description: This method used to enter remark in remark text field.
	 * 
	 * @author SreeLatha
	 * @param documentType
	 * @param documentDescription
	 * @param remarks
	 */
	public synchronized void setRemarkField(String documentType, String documentDescription, String remarks) {
		try {
			WebActionUtil.typeText(tbRemarks(documentType, documentDescription), remarks, "Remark sText Field");
			WebActionUtil.validateAttribute(tbRemarks(documentType, documentDescription), "value", remarks, "Remarks Text Field",
					"Typed: " + remarks + " into Remarks Text Field",
					"Unable to type " + remarks + " into Remarks Text Field", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to enter " + remarks + " into Remarks Text Field");
			Assert.fail("Unable to enter " + remarks + " into Remarks Text Field");
		}
	}

	/**
	 * Description: This method used to click on insufficient check box .
	 * 
	 * @author SreeLatha
	 * @param documentType
	 * @param documentDescription
	 */
	public synchronized void clkInsufficientCheckBox(String documentType, String documentDescription) {
		try {
			WebActionUtil.clickCheckBox(ckbxInsufficient(documentType, documentDescription), "Insufficient Check Box");
			WebActionUtil.validateisElementSelected(ckbxInsufficient(documentType, documentDescription),
					"Insufficient Check Box", "Insufficient Check Box is Selected",
					"Insufficient Check Box is not selected", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on " + ckbxInsufficient(documentType, documentDescription)
			+ " Insufficient Check Box");
			Assert.fail("Unable to perform action on " + ckbxInsufficient(documentType, documentDescription)
			+ " Insufficient Check Box");
		}
	}

	/**
	 * Description: This method is used to click on Ok button to handle alert for confirmation .
	 * 
	 * @author SreeLatha
	 * @param expectedAlertMessage
	 */
	public synchronized void validateAlertPopup(String expectedAlertMessage) {
		try {
			Alert alert = driver.switchTo().alert();
			String actualAlertMessage = alert.getText();
			Assert.assertTrue(actualAlertMessage.toLowerCase().equals(expectedAlertMessage.toLowerCase()));
			WebActionUtil.info("Alert popup is displayed");
			WebActionUtil.validationinfo("Alert popup is displayed", "green");
			alert.accept();
			WebActionUtil.info("Clicked on Ok button in alert popup");
			WebActionUtil.extentinfo("Clicked on Ok button in alert popup");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Validate Alert popup");
			Assert.fail("Unable to Validate Alert popup");
		}
	}


	/**
	 * Description: This method used to verify that all the documents are uploaded.
	 * 
	 * @author SreeLatha
	 * @param documentType
	 * @param documentDescription
	 */
	public synchronized void verifyThatAllTheDocumentsAreUploaded(String[] documentType, String[] documentDescription) {
		boolean flag = false;
		try {
			WebActionUtil.poll(3000);
			for (int i = 0; i < documentType.length; i++) {
				try {
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
				WebActionUtil.info("All the documents are uploaded Showing with search button");
				WebActionUtil.validationinfo("All the documents are uploaded Showing with search button", "blue");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to verify all the documents are uploaded");
			Assert.fail("Unable to verify all the documents are uploaded");
		}
	}

	/**
	 * Description: This method used to click on close button.
	 * 
	 * @author SreeLatha
	 * @param parentWindow
	 */
	public synchronized void clkCloseButton(String parentWindow) {
		try {
			WebActionUtil.clickOnElement(btnClose, "Close button");
			WebActionUtil.switchParentWindow(parentWindow);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Close button");
			Assert.fail("Unable to perform action on Close button");
		}
	}

	/**
	 * Description: This method is used to validate manage background checks of recruiter view page.
	 * 
	 * @author SreeLatha
	 */
	public synchronized void validateManageBackgroundChecksOfRecruiterViewPage() {
		try {
			WebActionUtil.validateisElementDisplayed(txtBackgroundCheckDetails, "Background check detais text", "Manage Background Check page is displayed",
					"Manage Background Check page is not displayed","blue");

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Manage Background Checks of Recruiter View Page");
			Assert.fail("Unable to validate Manage Background Checks of Recruiter View Page");

		}
	}

	/**
	 * Description: This method used to set refer back joining form to candidate remark text field.
	 * 
	 * @author SreeLatha
	 * @param referBackJoiningFormRemark 
	 */
	public synchronized void setReferBackJoiningFormToCandidateRemarks(String referBackJoiningFormRemark) {
		try {
			WebActionUtil.typeText(tbReferBackJoiningFormToCandidateRemarks, referBackJoiningFormRemark, "Refer Back Joining Form To Candidate Remark Text Field");
			WebActionUtil.validateAttribute(tbReferBackJoiningFormToCandidateRemarks, "value",referBackJoiningFormRemark,
					"Refer Back Joining Form To Candidate Remark Text Field",
					"Typed: " + referBackJoiningFormRemark + " into Refer Back Joining Form To Candidate Remark Text Field",
					"Unable to type " + referBackJoiningFormRemark + " into Refer Back Joining Form To Candidate Remark Text Field", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to enter " + referBackJoiningFormRemark + " into Refer Back Joining Form To Candidate Remark Text Field");
			Assert.fail("Unable to enter " + referBackJoiningFormRemark + " into Refer Back Joining Form To Candidate Remark Text Field");
		}
	}

	/**
	 * Description: This method used to click on refer back joining form Button.
	 * 
	 * @author SreeLatha
	 */
	public synchronized void clkReferBackjoiningFormButton() {
		try {
			WebActionUtil.clickOnElement(btnReferBackjoiningForm, "Refer Back Joining Form Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Refer Back Joining Form Button");
			Assert.fail("Unable to perform action on Refer Back Joining Form Button");
		}
	}

	/**
	 * Description: This method used to wait for invisibility of spinner.
	 * 
	 * @author SreeLatha
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
	 * Description: This method used to click on mark insufficiency button.
	 * 
	 * @author SreeLatha
	 */
	public synchronized void clkMarkInsuffiencyButton() {
		try {
			WebActionUtil.clickOnElement(btnMarkInsuffiency, "Mark Insuffiency Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Mark Insuffiency Button");
			Assert.fail("Unable to perform action on Mark Insuffiency Button");
		}
	}

}
