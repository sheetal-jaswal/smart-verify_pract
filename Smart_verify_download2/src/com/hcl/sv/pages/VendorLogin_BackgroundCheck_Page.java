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
 * Description: This class implements the method for Vendor Background Check
 * Page
 * 
 * @author Ramya R
 */
public class VendorLogin_BackgroundCheck_Page {
	public WebDriver driver;
	public WebActionUtil actionUtil;
	public long ETO;

	public VendorLogin_BackgroundCheck_Page(WebDriver driver, long ETO, WebActionUtil actionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actionUtil = actionUtil;
		this.ETO = ETO;

	}

	/* Edit/Update link */
	@FindBy(xpath = "//a[contains(@id,'lnkUpload')]")
	private WebElement lnkEditOrUpdate;

	/* View link */
	@FindBy(xpath = "//a[contains(@id,'lnkTPAView')]")
	private WebElement lnkView;

	/* Send Communication button */
	@FindBy(xpath = "//input[contains(@id,'btnSendCommunication')]")
	private WebElement btnSendCommunication;

	/* Submit to BGV button */
	@FindBy(xpath = "//input[contains(@id,'btnSubmit')]")
	private WebElement btnSubmitToBGV;

	/* Close button */
	@FindBy(xpath = "//input[contains(@id,'btnClose')]")
	private WebElement btnClose;

	/* Submit to Recruiter button */
	@FindBy(xpath = "//input[@id='btnReferBackNew']")
	private WebElement btnSubmitToRecruiter;

	/* Send to Candidate button */
	@FindBy(xpath = "//input[contains(@id,'btnSendTocandidate')]")
	private WebElement btnSendToCandidate;

	/* Background check text */
	@FindBy(xpath = "//table/tbody/tr/td[contains(text(),'Background Check Details')]")
	private WebElement txtBackgroundcheck;


	/* Background check check box */
	private List<WebElement> selectBackgroundCheckBox(String checkType) {
		return driver.findElements(By.xpath("//table[@class='datatbl']/tbody/tr/td[contains(text(),'" + checkType
				+ "')]/following-sibling::td[5]/input[@type='checkbox']"));
	}

	/* Image plus Communication details */
	@FindBy(xpath = "//img[@id='imgExpCol']")
	private WebElement imgPluseCommunicationDetails;

	/* Image plus Document specification */
	@FindBy(xpath = "//table/tbody/tr/td/img[@id='Image11']")
	private WebElement imgPluseDocumentSpecification;

	/* Action check box */
	private WebElement selectActionCheckBox(String category) {
		return driver.findElement(By.xpath(
				"//table[@class='datatbl']/tbody/tr[@class='global-rA' or @class='global-rB']/td[contains(text(),'"
						+ category + "')]/preceding-sibling::td/input[contains(@id,'ChkChec')]"));
	}

	/* Color status dropDown */
	private WebElement selectColorStatusDd(String category) {
		return driver.findElement(By.xpath(
				"//table[@class='datatbl']/tbody/tr[@class='global-rA' or @class='global-rB']/td[contains(text(),'"
						+ category + "')]/following-sibling::td/select[contains(@id,'ColorStatus')]"));
	}

	/* From Date textField */
	private WebElement selectFromDateTextField(String category) {
		return driver.findElement(By.xpath(
				"//table[@class='datatbl']/tbody/tr[@class='global-rA' or @class='global-rB']/td[contains(text(),'"
						+ category + "')]/following-sibling::td/input[contains(@id,'txtFromDate')]"));
	}

	/* To Date textField */
	private WebElement selectToDateTextField(String category) {
		return driver.findElement(By.xpath(
				"//table[@class='datatbl']/tbody/tr[@class='global-rA' or @class='global-rB']/td[contains(text(),'"
						+ category + "')]/following-sibling::td/input[contains(@id,'txtToDate')]"));
	}

	/* Choose File button */
	@FindBy(xpath = "//div[@id='uploadFile_div']/input[@id='fuUploadDoc']")
	private WebElement btnChooseFile;

	/* Comments text area */
	@FindBy(xpath = "//textarea[@id='txtComment']")
	private WebElement txtcommentsArea;

	/* Document Specification checkBox */
	private WebElement selectDocumentSpecificationCheckbox(String education) {
		return driver.findElement(By.xpath("//label[contains(text(),'Education 1-10th')]/preceding-sibling::input"));
	}

	/* Document Specification color */
	private WebElement selectDocumentSpecificationColor(String education) {
		return driver.findElement(By.xpath(
				"//label[text()='Education 1-10th']/ancestor::td[@class='global-rB']/descendant::select[contains(@id,'ddlColorStatus')]"));
	}

	/* Add checks button */
	@FindBy(xpath = "//a[@id='btnAdditinal']")
	private WebElement btnAddChecks;
	
	/* Image plus Enter duration for criminal court ,address and employment */
	@FindBy(xpath = "//table[@class='global-filterTbl']/tbody/tr/td/img[@id='Image1']")
	private WebElement imgPluseEnterDurationForCriminalCourt;

	/* Background check text */
	@FindBy(xpath = "//table/tbody/tr/td[contains(text(),'Background Check Details')]")
	private WebElement txtBackgroundCheck;

	/* Send communication type radio button */
	private WebElement selectsendCommunicationTypeRdo(String type) {
		return driver.findElement(By.xpath("//table[@id='rdoReportType']/tbody/tr/td/label[contains(text(),'" + type
				+ "')]/preceding-sibling::input"));
	}

	/* Mark insufficiency check box */
	private WebElement markInsufficiencyCheckbox(String checkType) {
		String xpath = "//tr[@class='global-rA' or @class='global-rB']/child::td[text()='"+checkType+"']/following-sibling::td/input[@type='image']/parent::td/following-sibling::td/input[@type='checkbox']";
		return driver.findElement(By.xpath(xpath));
	}
	
	/* Reason drop down */
	@FindBy(xpath="//div[@class='multiselect-dropdown']")
	private WebElement ddReason;
		
	/* Mark insufficiency check box */
	private WebElement reasonDropDownOption(String option) {
		String xpath = "//label[text()='"+option+"']";
		return driver.findElement(By.xpath(xpath));
	}
	
	/* Cancel button */
	@FindBy(id="btnCancel")
	private WebElement btnCancel;
	
	/* Insufficient check box */
	@FindBy(xpath="//tbody/descendant::table[@id='grdDocDetails']/descendant::tr[@class='global-rB' or @class='global-rA']/descendant::input[@type='checkbox']")
	private List<WebElement> lstInsufficentCheckbox;
	
	/* Required checks text */
	@FindBy(xpath = "//table[@id='DlOtherChecks']/descendant::td/descendant::label")
	private List<WebElement> txtRequiredChecks;
	
	/**
	 * Description: This method is used to Validate Background check page
	 * 
	 * @author Ramya R
	 * 
	 */
	public synchronized void validateBackGroundCheckPage() {
		try {
			actionUtil.waitForVisibilityOfElement(txtBackgroundcheck, "Background Check text");
			if (txtBackgroundcheck.isDisplayed()) {
				WebActionUtil.info("Background Check page is Displayed");
				WebActionUtil.validationinfo("Background Chcek page is Displayed", "blue");
			} else {
				WebActionUtil.fail("Background Check page is not Displayed");
				Assert.fail("Background Check page is not Displayed");
			}

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Background Check page is not Displayed");
			Assert.fail("Background Check page is not Displayed");
		}
	}

	/**
	 * Description: This method is used to click on BackgroundCheck check box
	 * 
	 * @author Ramya R
	 * @param checkType
	 * 
	 */
	public synchronized void clkBackgroundCheckbox(String checkType) {
		try {
			for (WebElement checkBox : selectBackgroundCheckBox(checkType)) {
				if (checkBox.isSelected()) {
					checkBox.click();
				}
				WebActionUtil.validationinfo("Background Checkbox is not checked", "blue");
				WebActionUtil.info("Background Checkbox is not checked ");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Click and Validate Backgroundcheck checkbox");
			Assert.fail("Unable to Click and Validate Backgroundcheck checkbox");
		}
	}

	/**
	 * Description: This method is used to click on image plus Communication details
	 * 
	 * @author Ramya R
	 * 
	 */
	public synchronized void clkImagePluseCommunicationDetails() {
		try {
			WebActionUtil.clickOnElement(imgPluseCommunicationDetails, "Image Pluse Communication Details");

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Image Pluse Communication Details");
			Assert.fail("Unable to click on Image Pluse Communication Details");
		}
	}

	
	/**
	 * Description: This method is used to select color under Enter duration for
	 * criminal court, address & employment
	 * 
	 * @author Ramya R
	 * @param category
	 * @param color
	 * 
	 */
	private synchronized void selectColorDd(String category, String color) {
		try {
			actionUtil.selectByText(selectColorStatusDd(category), color);
			if (selectColorStatusDd(category).getText().contains(color)) {
				WebActionUtil.info(color + " Color option is selected from the dropdown");
				WebActionUtil.validationinfo(color + " Color option is selected from the dropdown", "blue");
			} else {
				WebActionUtil.fail(color + " Color option is selected from the dropdown");
				Assert.fail(color + " Color option is selected from the dropdown");
			}

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Select Color Drodown under Enter Duration For CriminalCourt");
			Assert.fail("Unable to Select Color Drodown under Enter Duration For CriminalCourt");
		}
	}

	/**
	 * Description: This method is used to select From date and To date under Enter
	 * duration for criminal court, address & employment
	 * 
	 * @author Ramya R
	 * @param fromDate
	 * @param ToDate
	 * @param category
	 * 
	 */
	public synchronized void selectDate(String category, String fromDate, String toDate) {
		try {
			WebActionUtil.typeText(selectFromDateTextField(category), fromDate, "From Date Text Field");
			if (selectFromDateTextField(category).getAttribute("value").contains(fromDate)) {
				WebActionUtil.info(fromDate + " is Dispalyed in From Date Textfield");
				WebActionUtil.validationinfo(fromDate + " is Dispalyed in From Date Textfield", "blue");
			} else {
				WebActionUtil.fail(fromDate + " is not Dispalyed in From Date Textfield");
				Assert.fail(fromDate + " is not Dispalyed in From Date Textfield");
			}
			WebActionUtil.waitForElement(selectToDateTextField(category), "To Date Text Field");
			WebActionUtil.typeText(selectToDateTextField(category), toDate, "To Date Text Field");
			if (selectToDateTextField(category).getAttribute("value").contains(toDate)) {
				WebActionUtil.info(fromDate + " is Dispalyed in To Date Textfield");
				WebActionUtil.validationinfo(fromDate + " is Dispalyed in To Date Textfield", "blue");
			} else {
				WebActionUtil.fail(fromDate + " is not Dispalyed in To Date Textfield");
				Assert.fail(fromDate + " is not Dispalyed in To Date Textfield");
			}

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Select Date from Calendar");
			Assert.fail("Unable to Select Date from Calendar");
		}
	}

	/**
	 * Description: This method is used to Upload Verification report
	 * 
	 * @author Ramya R
	 * @param filePath
	 * 
	 */
	public synchronized void uploadFileAndValidate(String filePath) {
		try {
			WebActionUtil.actionClick(btnChooseFile, "Choose file Button");
			WebActionUtil.upload(filePath);
			validateUploadedDocument(filePath, btnChooseFile);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Upload and Validate the File");
			Assert.fail("Unable to Upload and Validate the File");
		}
	}

	/**
	 * 
	 * Description: This method implements to validate uploaded document
	 * 
	 * @author Ramya R
	 * @param filePath
	 * @param element
	 */
	public synchronized void validateUploadedDocument(String filePath, WebElement element) {
		try {
			WebActionUtil.poll(4000);
			String[] fileLocation = filePath.split("\\\\");
			String fileName = " ";
			for (int i = fileLocation.length - 1; i < fileLocation.length; i++) {
				fileName = fileLocation[i];
			}
			String expected = btnChooseFile.getAttribute("value");
			Assert.assertTrue(expected.contains(fileName));
			WebActionUtil.validationinfo(fileName + " is selected and displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate the uploaded document");
			Assert.fail("Unable to validate the uploaded document");
		}

	}

	/**
	 * Description: This method is used to click on image plus Document
	 * Specification
	 * 
	 * @author Ramya R
	 * @param educationType
	 * @param color
	 * 
	 */
	public synchronized void clkImagePluseDocumentSpecification(String educationType, String color) {
		try {
			WebActionUtil.scrollToElement(imgPluseDocumentSpecification, "Image Pluse Document Specification");
			WebActionUtil.clickOnElement(imgPluseDocumentSpecification, "Image Pluse Document Specification");
			selectEducationType(educationType);
			selectColorFromDocumentSpecificationDd(educationType, color);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Image Pluse Document Specification");
			Assert.fail("Unable to click on Image Pluse Document Specification");
		}
	}

	/**
	 * Description: This method is used to click on EducationType check box from
	 * image plus Document specification
	 * 
	 * @author Ramya R
	 * @param educationType
	 * 
	 */
	private synchronized void selectEducationType(String educationType) {
		try {
			boolean enable = selectDocumentSpecificationCheckbox(educationType).isEnabled();
			System.out.println(enable);
			if (enable == true) {
				WebActionUtil.clickCheckBox(selectDocumentSpecificationCheckbox(educationType),
						"EducationType Checkbox");
				WebActionUtil.validateisElementSelected(selectDocumentSpecificationCheckbox(educationType),
						"EducationType Checkbox", "Education Type checkbox is checked",
						"Education Type checkbox is not checked", "blue");

			} else {
				WebActionUtil.extentinfo("The checkbox education type of " + educationType + " is already selected");
			}

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click EducationType checkbox from Image Pluse Document Specification");
			Assert.fail("Unable to click EducationType checkbox from Image Pluse Document Specification");
		}
	}

	/**
	 * Description: This method is used to select color dropDown from image plus
	 * Document specification
	 * 
	 * @author Ramya R
	 * @param educationType
	 * @param color
	 * 
	 */
	private synchronized void selectColorFromDocumentSpecificationDd(String educationType, String color) {
		try {
			actionUtil.selectByText(selectDocumentSpecificationColor(educationType), color);
			if (selectDocumentSpecificationColor(educationType).getText().contains(color)) {
				WebActionUtil.info(color + " Color option is selected from the dropdown");
				WebActionUtil.validationinfo(color + " Color option is selected from the dropdown", "blue");
			} else {
				WebActionUtil.fail(color + " Color option is selected from the dropdown");
				Assert.fail(color + " Color option is selected from the dropdown");
			}

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Select Color Drodown from Image Pluse Document Specification");
			Assert.fail("Unable to Select Color Drodown from Image Pluse Document Specification");
		}
	}

	/**
	 * Description: This method is used for Comment text area
	 * 
	 * @author Ramya R
	 * @param comment
	 * 
	 */
	public synchronized void setComment(String comment) {
		try {
			WebActionUtil.typeText(txtcommentsArea, comment, "Comment Text Field");
			WebActionUtil.validateAttribute(txtcommentsArea, "value", comment, "Comment Text Field",
					"Typed: "+comment+" in Comment Text Field ", "Unable to Type "+comment+" in Comment Text Field",
					"blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to set comment in Comment Text Field");
			Assert.fail("Unable to set comment in Comment Text Field");
		}
	}

	/**
	 * Description: This method is used to click on Submit to Recruiter
	 * 
	 * @author Ramya R
	 * 
	 */
	public synchronized void clkSubmitToRecruiterBtn() {
		try {
			WebActionUtil.clickOnElement(btnSubmitToRecruiter, "Submit To Recruiter");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Click on Submit To Recruiter Button");
			Assert.fail("Unable to Click on Submit To Recruiter Button");
		}
	}

	/**
	 * Description: This method is used to click on Send Communication button
	 * 
	 * @author Ramya R
	 *  
	 */
	public synchronized void clkSendCommunicationBtn() {
		try {
			WebActionUtil.clickOnElement(btnSendCommunication, "Send Communication Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Click on Send Communication Button");
			Assert.fail("Unable to Click on Send Communication Button");
		}
	}

	/**
	 * Description: This method is used to click on Submit to BGV team button
	 * 
	 * @author Ramya R
	 * 
	 * 
	 */
	public synchronized void clkSubmitToBGVTeamBtn() {
		try {
			WebActionUtil.clickOnElement(btnSubmitToBGV, "Submit To BGV Team Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Click on Submit To BGV Team Button");
			Assert.fail("Unable to Click on Submit To BGV Team Button");
		}
	}

	/**
	 * Description: ThiS method is used to click on Close button
	 * 
	 * @author Ramya R
	 * 
	 * 
	 */
	public synchronized void clkCloseBtn() {
		try {
			WebActionUtil.clickOnElement(btnClose, "Close Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Click on Close Button");
			Assert.fail("Unable to Click on Close Button");
		}
	}

	/**
	 * Description: This method is used to click on Send to candidate button
	 * 
	 * @author Ramya R
	 *  
	 */
	public synchronized void clkSendToCandidateBtn() {
		try {
			WebActionUtil.clickOnElement(btnSendToCandidate, "Send to Candidate Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail(" Unable to Click on Send to Candidate Button ");
			Assert.fail(" Unable to Click on Send to Candidate Button ");
		}
	}

	/**
	 * Description: This method is used to click on Add Checks button
	 * 
	 * @author Ramya R
	 * 
	 */
	public synchronized void clkAddChecks() {
		try {
			WebActionUtil.clickOnElement(btnAddChecks, "Add Checks Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Click on Add Checks Button");
			Assert.fail("Unable to Click on Add Checks Button");
		}
	}

	/**
	 * Description : This method is used to click on alert pop up OK button
	 * 
	 * @author Abhishek
	 * @param expectedAlertMessage
	 * 
	 */
	public synchronized void clkAlertPopUpOkBtn(String expectedAlertMessage) {
		try {
			validateAlertMessage(expectedAlertMessage);
			WebActionUtil.poll(2000);
			Alert alert = driver.switchTo().alert();
			String actualAlertMessage = alert.getText();
			alert.accept();
			WebActionUtil.info("Clicked on ok Button of " + actualAlertMessage + " Alert popup");
			WebActionUtil.extentinfo("Clicked on ok Button of " + actualAlertMessage + " Alert popup");
		} catch (Exception | AssertionError e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Alert pop up");
			Assert.fail("Unable to perform action on Alert pop up");
		}
	}

	/**
	 * Description : This method is used to validate alert pop up message.
	 * 
	 * @author Abhishek
	 * @param expectedAlertMessage
	 */
	private synchronized void validateAlertMessage(String expectedAlertMessage) {
		try {
			Alert alert = driver.switchTo().alert();
			String actualAlertMessage = alert.getText();
			Assert.assertTrue(actualAlertMessage.toLowerCase().equals(expectedAlertMessage.toLowerCase()));
			WebActionUtil.info("Alert popup with " + actualAlertMessage + " message is displayed");
			WebActionUtil.validationinfo("Alert popup with " + actualAlertMessage + " message is displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Alert pop up Message");
			Assert.fail("Unable to validate Alert pop up Message");
		}
	}

	/**
	 * Description: This method is used to click on image plus Enter duration for
	 * criminal court, address & employment
	 * 
	 * @author Ramya R
	 * 
	 */
	public synchronized void clkImagePluseEnterDurationForCriminalCourt(String category, String color, String fromDate,
			String toDate) {
		try {
			WebActionUtil.moveToElement(driver, imgPluseEnterDurationForCriminalCourt);
			WebActionUtil.actionClick(imgPluseEnterDurationForCriminalCourt,
					"Image Pluse EnterDurationForCriminalCourt");
			WebActionUtil.validateisElementDisplayed(selectActionCheckBox(category), "Category",
					"Duration for criminal court, Address & employment is Displayed",
					"Duration for criminal court, Address & employment is not Displayed", "blue");
			WebActionUtil.clickCheckBox(selectActionCheckBox(category), "Action Checkbox");
			WebActionUtil.validateisElementSelected(selectActionCheckBox(category), "Action Checkbox",
					category + " is selected", category + " is not selected", "blue");
			selectColorDd(category, color);
			selectDate(category, fromDate, toDate);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Image Pluse Enter Duration For CriminalCourt");
			Assert.fail("Unable to click on Image Pluse Enter Duration For CriminalCourt");
		}
	}

	/**
	 * Description: This method is used to click on Send communication radio button
	 * 
	 * @author Ramya R
	 * @param type
	 * 
	 */
	public synchronized void clkSendCommunicationRadioButton(String type) {
		try {
			WebActionUtil.scrollDown();
			if (selectsendCommunicationTypeRdo(type).isSelected()) {
				WebActionUtil.validationinfo(type + " Send Communication Radio Button", "blue");
			}
			actionUtil.clickOnElement(selectsendCommunicationTypeRdo(type), "Send Communication Radio Button",
					"Send Communication Radio Button is not Selected");
			WebActionUtil.validateisElementSelected(selectsendCommunicationTypeRdo(type),
					"Send Communication Radio Button", "Send Communication Radio Button is Selected",
					"Send Communication Radio Button is not Selected", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Send Communication Radio Button");
			Assert.fail("Unable to perform action on Send Communication Radio Button");
		}
	}

	/**
	 * Description: This method is used to wait for invisibility of spinner.
	 * 
	 * @author Abhishek
	 */
	public synchronized void waitForInvisibilityOfSpinner() {
		try {
			WebActionUtil.poll(3000);
			WebActionUtil.info("Spinner disappeared");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Spinner is visible");
		}
	}
	
	
	/**
	 * Description: This method is used to click on select mark insufficiency check box
	 * 
	 * @author Ramya R
	 * @param checkType
	 * 
	 */
	public synchronized void clkMarkInsufficiencyCheckbox(String checkType) {
		try {
			WebActionUtil.scrollToElement(markInsufficiencyCheckbox(checkType), "Mark insufficiency check box");
			WebActionUtil.poll(1000);
			WebActionUtil.clickOnElement(markInsufficiencyCheckbox(checkType), "Mark insufficiency check box");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Mark insufficiency check box");
			Assert.fail("Unable to perform action on Mark insufficiency check box");
		}
	}
	
	/**
	 * Description: This method is used to click on Reason drop down
	 * 
	 * @author Ramya R
	 * 
	 */
	public synchronized void clkReasonDropDown() {
		try {
			WebActionUtil.scrollToElement(ddReason, "Reason drop down");
			WebActionUtil.clickOnElement(ddReason, "Reason drop down");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Reason drop down");
			Assert.fail("Unable to perform action on Reason drop down");
		}
	}
	

	/**
	 * Description: This method is used to click on Reason drop down option
	 * 
	 * @author Ramya R
	 * @param option  
	 */
	public synchronized void clkReasonDropDownOption(String option) {
		try {
			WebActionUtil.clickOnElement(reasonDropDownOption(option), "Reason drop down option "+option);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Reason drop down option "+option);
			Assert.fail("Unable to perform action on Reason drop down option "+option);
		}
	}
	
	/**
	 * Description: This method is used to close the window
	 * 
	 * @author Ramya R
	 */
	public synchronized void closeTheWindow() {
		try {	
				WebActionUtil.clickOnElement(btnClose, "Close button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to close the window");
			Assert.fail("Unable to close the window");
		}
	}
	
	/**
	 * Description: This method is used to click on Cancel button
	 * 
	 * @author Ramya R
	 * @param window
	 */
	public synchronized void clkCancelButton(String window) {
		try {
			WebActionUtil.switchWindows();
			try {
				driver.close();
			} catch (Exception e) {
				WebActionUtil.switchWindows();
				WebActionUtil.clickOnElement(btnCancel, "Cancel button");
			}
			WebActionUtil.switchParentWindow(window);
			try {
				validateBackGroundCheckPage();
			} catch (Exception e) {
				WebActionUtil.switchParentWindow(window);
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Cancel button");
			Assert.fail("Unable to perform action on Cancel button");
		}
	}

	/**
	 * Description: This method is used to click on Mark insufficient check box
	 * 
	 * @author Ramya R
	 * 
	 */
	public synchronized void clkInsufficientCheckbox() {
		try {
			for (WebElement checkBox : lstInsufficentCheckbox) {
				if (checkBox.isSelected()) {
					checkBox.click();
				}
				WebActionUtil.validationinfo("Mark insufficient Checkbox is not checked", "blue");
				WebActionUtil.info("Mark insufficient Checkbox is not checked ");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Click and Validate Mark insufficient checkbox");
			Assert.fail("Unable to Click and Validate Mark insufficient checkbox");
		}
	}

}
