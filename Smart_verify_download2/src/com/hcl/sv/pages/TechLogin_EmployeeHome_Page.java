package com.hcl.sv.pages;

import java.util.ArrayList;
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
 * Description: This class implements the methods for accessing elements of Employee Home Page 
 * 
 * @author Abhishek
 * 
 */

public class TechLogin_EmployeeHome_Page {

	public WebDriver driver;
	public WebActionUtil actionUtil;
	public long ETO;

	public TechLogin_EmployeeHome_Page(WebDriver driver, long ETO, WebActionUtil actionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actionUtil = actionUtil;
		this.ETO = ETO;
	}

	/* Home page header text */
	@FindBy(className = "global-pagetitle")
	private WebElement txtHeaderHome;

	/* Unique verification id text field */
	@FindBy(xpath = "//input[contains(@id,'BGVID')]")
	private WebElement tbUniqueVerificationId;

	/* View details button */
	@FindBy(xpath = "//input[@value='View Details']")
	private WebElement btnViewDetails;

	/* Submit button */
	@FindBy(xpath = "//input[@class='global-btn' and @value='Submit']")
	private WebElement btnSubmit;

	/* Close button */
	@FindBy(xpath = "//input[@class='global-btn' and @value='Close']")
	private WebElement btnClose;

	/* Upload or view documents link */
	private WebElement lnkUpload(String documentType, String documentSubType) {
		String xpath = "//td[text()='" + documentType + "']/following-sibling::td/span[normalize-space(text())='"
				+ documentSubType + "']/parent::td/following-sibling::td/a[contains(@id,'lnkUpload')]";
		return driver.findElement(By.xpath(xpath));
	}

	/* My Document details text */
	@FindBy(xpath = "//td[normalize-space(text())='My Document Details'] | //td[contains(text(),'My Document Details')]")
	private WebElement txtMyDocumentDetails;
	
	/* Not applicable check box */
	@FindBy(xpath = "//td/child::input[@type='checkbox']")
	private List<WebElement> ckbxNotApplicable;

	/* Upload link */
	@FindBy(xpath = "//a[text()='Upload']")
	private List<WebElement> txtUpload;

	/* Upload multiple documents text */
	@FindBy(xpath = "//td[normalize-space(text())='Upload Multiple Documents']")
	private WebElement txtUploadMultipleDocuments;

	/* Choose file button */
	@FindBy(id = "fuUploadMultiple")
	private WebElement btnChooseFile;

	/* Document description text field */
	@FindBy(id = "txtDocumentDescMultiDoc")
	private WebElement tbDocumentDescription;

	/* Upload button */
	@FindBy(id = "btnAddDoc")
	private WebElement btnUpload;

	/* Document uploaded successfully message text */
	@FindBy(id = "lblMessage")
	private WebElement txtDocumentUploadedSuccessfully;

	/* Upload multiple documents Close button */
	@FindBy(id = "btnClose")
	private WebElement btnUploadMultipleDocumentsClose;

	/* Please select If not applicable check box */
	@FindBy(id = "chkNotApplicable")
	private WebElement cbPleaseSelectIfNotApplicable;

	/* Magnifying glass icon */
	private WebElement icnMagnifyingGlass(String fileFormat, String documentDescription) {
		String xpath = "//td[contains(text(),'" + fileFormat + "')]/following-sibling::td[contains(text(),'"
				+ documentDescription
				+ "')]/following-sibling::td/input[@type='image' and contains(@id,'imgBtnViewDocMulti')]";
		return driver.findElement(By.xpath(xpath));
	}

	/* Delete icon */
	private WebElement icnDelete(String fileFormat, String documentDescription) {
		String xpath = "//td[contains(text(),'" + fileFormat + "')]/following-sibling::td[contains(text(),'"
				+ documentDescription
				+ "')]/following-sibling::td/input[@type='image' and contains(@id,'imgBtnDeleteDocMulti')]";
		return driver.findElement(By.xpath(xpath));
	}

	/* Uploaded document details list */
	private List<WebElement> lstUploadedDocumentDetails(String documentSubType) {
		String xpath = "//span[normalize-space(text())='" + documentSubType + "']/parent::td/following-sibling::td";
		return driver.findElements(By.xpath(xpath));
	}

	/* Logout link */
	@FindBy(xpath = "//a[contains(@id,'lnkLogout')]")
	private WebElement lnkLogout;

	/* Form submitted successfully toast message */
	@FindBy(xpath = "//span[text()='Form submitted successfully']")
	private WebElement txtFormSubmittedSuccessfully;


	/**
	 * Description: This method is used to click on submit button.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkSubmitButton() {
		try {
			WebActionUtil.clickOnElement(btnSubmit, "Submit Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on submit button");
			Assert.fail("Unable to click on submit button");
		}
	}


	/**
	 * Description: This method is used to validate employee home page.
	 * 
	 * @author Abhishek
	 */
	public synchronized void validateEmployeeHomePage() {
		try {
			WebActionUtil.waitForElement(txtHeaderHome, "Home Header Text");
			if ((driver.getCurrentUrl().contains(BaseTest.prop_constants.getProperty("employee_Home_Page_Url")))
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
			WebActionUtil.fail("Unable to validate Employee home page");
			Assert.fail("Unable to validate Employee home page");
		}
	}

	/**
	 * Description: This method used to set enter unique verification id text field.
	 * 
	 * @author Abhishek
	 * @param uniqueVerificationId
	 */
	public synchronized void setEnterUniqueVerificationIdTextField(String uniqueVerificationId) {
		try {
			WebActionUtil.typeText(tbUniqueVerificationId, uniqueVerificationId,
					"Enter Unique verification Id text field");
			WebActionUtil.validateAttribute(tbUniqueVerificationId, "value", uniqueVerificationId,
					"Enter Unique verification Id Text field",
					"Typed BGV ID: " + uniqueVerificationId + " into enter unique verification id text field",
					"Unable to type " + uniqueVerificationId + " into enter unique verification id text field", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil
					.fail("Unable to enter " + uniqueVerificationId + " into unique verification id text field");
			Assert.fail("Unable to enter " + uniqueVerificationId + " into unique verification id text field");
		}
	}

	/**
	 * Description: This method used to click on view details button.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkViewDetailsButton() {
		try {
			WebActionUtil.clickOnElement(btnViewDetails, "View details button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on view details button");
			Assert.fail("Unable to perform action on view details button");
		}
	}


	/**
	 * Description: This method used to click on upload or view documents link.
	 * 
	 * @author Abhishek
	 * @param documentType
	 * @param documentSubType
	 */
	public synchronized void clkUploadLink(String documentType, String documentSubType) {
		try {
			try {
				if (lnkUpload(documentType, documentSubType).isDisplayed()) {
					WebActionUtil.info(documentSubType + " upload link");
				}
			} catch (Exception ex) {
				WebActionUtil.scrollDown();
			}
			WebActionUtil.clickOnElement(lnkUpload(documentType, documentSubType),
					documentSubType + " Upload or view documents link");
			WebActionUtil.validationinfo("Clicked on upload link of " + documentSubType, "blue");
			try {
				WebActionUtil.switchWindows();
			} catch (Exception e) {
				WebActionUtil.switchWindows();
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on upload or view documents link");
			Assert.fail("Unable to perform action on upload or view documents link");
		}
	}

	/**
	 * Description: This method is used to validate my document details header text.
	 * 
	 * @author Abhishek
	 */
	public synchronized void validateMyDocumentDetailsHeaderText() {
		try {
			WebActionUtil.validateisElementDisplayed(txtMyDocumentDetails, "My Document Details Header Text",
					"My document details is displayed", "My document details is not displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate my document details ");
			Assert.fail("Unable to validate my document details ");
		}
	}

	/**
	 * Description: This method is used to validate upload multiple documents page.
	 * 
	 * @author Abhishek
	 */
	public synchronized void validateUploadMultipleDocumentsPage() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			try {
				wait.until(ExpectedConditions.visibilityOf(txtUploadMultipleDocuments));
			} catch (Exception e) {
				WebActionUtil.switchWindows();
			}
			WebActionUtil.waitForElement(txtUploadMultipleDocuments, "Upload Multiple Documents Header Text");
			if ((driver.getCurrentUrl()
					.contains(BaseTest.prop_constants.getProperty("upload_Docs_Of_Employee_Home_Page_Url")))
					&& txtUploadMultipleDocuments.isDisplayed()) {
				WebActionUtil.info("Upload multiple documents page is displayed");
				WebActionUtil.validationinfo("Upload multiple documents page is displayed", "blue");
			} else {
				WebActionUtil.error("Upload multiple documents page is not displayed");
				WebActionUtil.fail("Upload multiple documents page is not displayed");
				Assert.fail("Upload multiple documents page is not displayed");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate upload multiple documents page");
			Assert.fail("Unable to validate upload multiple documents page");
		}
	}

	/**
	 * Description: This method used to click on choose file button.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkChooseFileButton() {
		try {
			WebActionUtil.actionClick(btnChooseFile, "Choose File Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on choose file button");
			Assert.fail("Unable to perform action on choose file button");
		}
	}

	/**
	 * Description: This method used to upload to file.
	 * 
	 * @author Abhishek
	 * @param imagePath
	 */
	public synchronized void uploadFile(String imagePath) {
		try {
			WebActionUtil.upload(imagePath);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to upload the file");
			Assert.fail("Unable to upload the file");
		}
	}

	/**
	 * Description: This method used to validate the uploaded file.
	 * 
	 * @author Abhishek
	 * @param imagePath
	 */
	public synchronized void validateFileUploaded(String imagePath) {
		try {
			WebActionUtil.waitForElement(btnChooseFile, "Choose File Button");
			String actualFileName = btnChooseFile.getAttribute("value");
			String[] actualName = actualFileName.split("Sample");
			Assert.assertTrue((imagePath.toLowerCase()).contains(actualName[1].toLowerCase()));
			WebActionUtil.info(actualName[1] + " is selected and displayed");
			WebActionUtil.validationinfo(actualName[1] + " is selected and displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate file is uploaded");
			Assert.fail("Unable to validate file is uploaded");
		}
	}

	/**
	 * Description: This method used to set document description text field.
	 * 
	 * @author Abhishek
	 * @param documentDescription
	 */
	public synchronized void setDocumentDescriptionTextField(String documentDescription) {
		try {
			WebActionUtil.typeText(tbDocumentDescription, documentDescription, "Document Description Textfield");
			WebActionUtil.validateAttribute(tbDocumentDescription, "value", documentDescription,
					"Document Description Text field",
					"Typed: " + documentDescription + " into document description text field",
					"Unable to type " + documentDescription + " into document description text field", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to enter " + documentDescription + " into document description textfield");
			Assert.fail("Unable to enter " + documentDescription + " into document description textfield");
		}
	}

	/**
	 * Description: This method used to click on upload button.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkUploadButton() {
		try {
			WebActionUtil.clickOnElement(btnUpload, "Upload Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on upload button");
			Assert.fail("Unable to perform action on upload button");
		}
	}

	/**
	 * Description: This method used to validate document uploaded successfully.
	 * 
	 * @author Abhishek
	 */
	private synchronized void validateDocumentUploadedSuccessfullyText() {
		try {
			WebActionUtil.validateisElementDisplayed(txtDocumentUploadedSuccessfully,
					"Document Uploaded Successfully Message", "document uploaded successfully message is displayed",
					"document uploaded successfully message is not displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate document uploaded successfully");
			Assert.fail("Unable to validate document uploaded successfully");
		}
	}

	/**
	 * Description: This method used to validate uploaded document details under
	 * list of documents uploaded table.
	 * 
	 * @author Abhishek
	 * @param documentSubType
	 * @param fileName
	 * @param documentDescription
	 */
	private synchronized void validateUploadedDocumentDetails(String documentSubType, String fileName,
			String documentDescription) {
		try {
			WebActionUtil.waitForVisibilityOfAllElement(lstUploadedDocumentDetails(documentSubType),
					documentSubType + " Details");
			ArrayList<String> actualDetails = new ArrayList<String>();

			for (int i = 0; i < lstUploadedDocumentDetails(documentSubType).size(); i++) {
				String txtUploadedDocumentDetails = lstUploadedDocumentDetails(documentSubType).get(i).getText();
				if (((txtUploadedDocumentDetails.toLowerCase()).contains(fileName.toLowerCase()))
						| ((txtUploadedDocumentDetails.toLowerCase()).contains(documentDescription.toLowerCase()))) {
					actualDetails.add(txtUploadedDocumentDetails);
				}
			}
			WebActionUtil.info(actualDetails
					+ " are the uploaded document details displayed under list of documents uploaded table");
			WebActionUtil.extentinfo(actualDetails
					+ " are the uploaded document details displayed under list of documents uploaded table");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate uploaded document details under list of documents uploaded table");
			Assert.fail("Unable to validate uploaded document details under list of documents uploaded table");
		}
	}

	/**
	 * Description: This method used to click on magnifying glass icon under list of
	 * documents uploaded table for a particular uploaded document.
	 * 
	 * @author Abhishek
	 * @param fileFormat
	 * @param documentDescription
	 */
	private synchronized void clkMagnifyingGlassIcon(String fileFormat, String documentDescription) {
		try {
			WebActionUtil.clickOnElement(icnMagnifyingGlass(fileFormat, documentDescription), "Magnifying Glass Icon");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on magnifying glass icon");
			Assert.fail("Unable to perform action on magnifying glass icon");
		}
	}

	/**
	 * Description: This method used to click on delete icon under list of documents
	 * uploaded table for a particular uploaded document.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkDeleteIcon(String fileFormat, String documentDescription) {
		try {
			WebActionUtil.clickOnElement(icnDelete(fileFormat, documentDescription), "Delete Icon");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on delete icon");
			Assert.fail("Unable to perform action on delete icon");
		}
	}

	/**
	 * Description: This method used to validate document download successfully.
	 * 
	 * @author Abhishek
	 * @param fileExtension
	 *
	 */
	public synchronized void validateDocumentDownloaded(String fileExtension) {
		try {
			String userDir = System.getProperty("user.home");
			String downloadedDocumentName = WebActionUtil.getDownloadedDocumentName(userDir + "/Downloads/",
					fileExtension);
			WebActionUtil.validateDownload(downloadedDocumentName, "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate " + fileExtension + " document downloaded");
			Assert.fail("Unable to validate " + fileExtension + " document downloaded");
		}
	}

	/**
	 * Description: This method used to click on upload multiple documents close
	 * button.
	 * 
	 * @author Abhishek
	 */
	private synchronized void clkUploadMultipleDocumentsCloseButton() {
		try {
			WebActionUtil.clickOnElement(btnUploadMultipleDocumentsClose, "Upload multiple documents close bsutton");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on upload multiple documents close button");
			Assert.fail("Unable to perform action on upload multiple documents close button");
		}
	}

	/**
	 * Description : This method is used to validate are you sure you want to close
	 * window alert pop up message.
	 * 
	 * @author Abhishek
	 */
	public synchronized void validateAreYouSureYouWantToCloseWindowAlertPopup() {
		try {
			Alert alert = driver.switchTo().alert();
			String actualAlertConfirmationMessage = alert.getText();
			Assert.assertTrue((actualAlertConfirmationMessage.toLowerCase()).contains(
					BaseTest.prop_constants.getProperty("header_areYouSureYouWantToCloseWindow").toLowerCase()));
			WebActionUtil.info(actualAlertConfirmationMessage + " Alert message is displayed");
			WebActionUtil.validationinfo(actualAlertConfirmationMessage + " Alert message is displayed", "blue");
			clkOkButton();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate are you sure you want to close window alert popup");
			Assert.fail("Unable to validate are you sure you want to close window alert popup");
		}
	}

	/**
	 * Description: This method used to click on Ok button in alert pop up.
	 * 
	 * @author Abhishek
	 * 
	 */
	public synchronized void clkOkButton() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			WebActionUtil.info("Clicked on Ok button in alert popup");
			WebActionUtil.extentinfo("Clicked on Ok button in alert popup");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on Ok button in alert popup");
			Assert.fail("Unable to perform action on Ok button in alert popup");
		}
	}

	/**
	 * Description: This method is used to upload document for a particular document
	 * subType.
	 * 
	 * @author Abhishek
	 * @param documentType
	 * @param documentSubType
	 * @param imagePath
	 */
	public synchronized void uploadDocument(String documentType, String documentSubType, String imagePath) {
		try {
			validateUploadMultipleDocumentsPage();
			clkChooseFileButton();
			uploadFile(imagePath);
			validateFileUploaded(imagePath);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to upload document for " + documentSubType + " document Sub Type");
			Assert.fail("Unable to upload document for " + documentSubType + " document Sub Type");
		}
	}

	/**
	 * Description: This method is used to validates document is uploaded
	 * successfully text and the uploaded document details for a particular document
	 * subType.
	 * 
	 * @author Abhishek
	 * @param documentSubType
	 * @param documentDescription
	 * @param fileName
	 */
	public synchronized void validateUploadedDocument(String documentSubType, String documentDescription,
			String fileName) {
		try {
			validateDocumentUploadedSuccessfullyText();
			validateUploadedDocumentDetails(documentSubType, fileName, documentDescription);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate document is uploaded for " + documentSubType);
			Assert.fail("Unable to validate document is uploaded for " + documentSubType);
		}
	}

	/**
	 * Description: This method is used to click on document Close button present
	 * under Upload Multiple Documents Page and switches the control back to the
	 * Employee Home Page.
	 * 
	 * @author Abhishek
	 * 
	 */
	public synchronized void clkUploadMultipleDocumentCloseButton() {
		try {
			clkUploadMultipleDocumentsCloseButton();
			validateAreYouSureYouWantToCloseWindowAlertPopup();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on document close button");
			Assert.fail("Unable to click on document close button");
		}
	}

	/**
	 * Description: This method is used to click on magnifying glass icon and
	 * validate the particular file is downloaded.
	 * 
	 * @author Abhishek
	 * 
	 * @param documentSubType
	 * @param documentDescription
	 * @param fileName
	 */
	public synchronized void clkMagnifyingGlassIcon(String documentSubType, String documentDescription,
			String fileName) {
		try {
			clkMagnifyingGlassIcon(fileName, documentDescription);
			validateDocumentDownloaded(fileName);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on magnifying glass icon and validate " + fileName
					+ " is downloaded for" + documentSubType);
			Assert.fail("Unable to perform action on magnifying glass icon and validate " + fileName
					+ " is downloaded for" + documentSubType);
		}
	}

	/**
	 * Description : This method is used to validate Do You Really Want To Submit
	 * The Document Alert Pop up message.
	 * 
	 * @author Abhishek
	 */
	public synchronized void validateDoYouReallyWantToSubmitTheDocumentAlertPopup() {
		try {
			Alert alert = driver.switchTo().alert();
			String actualAlertConfirmationMessage = alert.getText();
			Assert.assertTrue((actualAlertConfirmationMessage.toLowerCase()).contains(
					BaseTest.prop_constants.getProperty("header_doYouReallyWantToSubmitTheDocument").toLowerCase()));
			WebActionUtil.info(actualAlertConfirmationMessage + " Alert message is displayed");
			WebActionUtil.validationinfo(actualAlertConfirmationMessage + " Alert message is displayed", "blue");
			clkOkButton();
			WebActionUtil.poll(5000);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Do You Really Want To Submit The Document Alert Popup");
			Assert.fail("Unable to validate Do You Really Want To Submit The Document Alert Popup");
		}
	}

	/**
	 * Description: This method used to click on Logout link.
	 * 
	 * @author Abhishek
	 * 
	 */
	public synchronized void clkLogoutLink() {
		try {
			WebActionUtil.clickOnElement(lnkLogout, "Logout Link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on logout link");
			Assert.fail("Unable to perform action on logout link");
		}
	}

	/**
	 * Description: This method is used to upload file by clicking on upload or
	 * View/Edit document link of post graduation.
	 * 
	 * @author Abhishek
	 * @param documentType
	 * @param documentSubType
	 * @param imagePath
	 * @param documentDescription
	 * @param fileName
	 */
	public synchronized void clkUploadLinkForPostGraduation(String documentType, String documentSubType,
			String imagePath, String documentDescription, String fileName) {
		try {
			String parentWindow = driver.getWindowHandle();
			clkUploadLink(documentType, documentSubType);
			validateUploadMultipleDocumentsPage();
			clkChooseFileButton();
			uploadFile(imagePath);
			validateFileUploaded(imagePath);
			setDocumentDescriptionTextField(documentDescription);
			clkUploadButton();
			validateUploadedDocument(documentSubType, documentDescription, fileName);
			clkMagnifyingGlassIcon(documentSubType, documentDescription, fileName);
			clkUploadMultipleDocumentsCloseButton();
			validateAreYouSureYouWantToCloseWindowAlertPopup();
			driver.switchTo().window(parentWindow);
			validateEmployeeHomePage();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Upload document for post graduation Link");
			Assert.fail("Unable to Upload document for post graduation Link");
		}
	}

	/**
	 * Description: This method is used to upload file by clicking on upload or
	 * View/Edit document link of desired document.
	 * 
	 * @author Abhishek
	 * @param documentType
	 * @param documentSubType
	 * @param imagePath
	 * @param documentDescription
	 * @param fileName
	 */
	public synchronized void clkUploadLinkToUploadDesiredDocumentSubType(String documentType, String documentSubType,
			String imagePath, String documentDescription, String fileName) {
		try {
			String parentWindow = driver.getWindowHandle();
			clkUploadLink(documentType, documentSubType);
			validateUploadMultipleDocumentsPage();
			clkChooseFileButton();
			uploadFile(imagePath);
			validateFileUploaded(imagePath);
			setDocumentDescriptionTextField(documentDescription);
			clkUploadButton();
			validateUploadedDocument(documentSubType, documentDescription, fileName);
			clkUploadMultipleDocumentsCloseButton();
			validateAreYouSureYouWantToCloseWindowAlertPopup();
			driver.switchTo().window(parentWindow);
			validateEmployeeHomePage();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to upload document for " + documentSubType + " link");
			Assert.fail("Unable to upload document for " + documentSubType + " link");
		}
	}

	
	
	/**
	 * Description : This method is used to validate form submitted successfully
	 * alert pop up message.
	 * 
	 * @author Abhishek
	 */
	public synchronized void validateFormSubmittedSuccessfullyAlertPopup() {
		try {
			Alert alert = driver.switchTo().alert();
			String actualAlertConfirmationMessage = alert.getText();
			Assert.assertTrue((actualAlertConfirmationMessage.toLowerCase())
					.contains(BaseTest.prop_constants.getProperty("header_FormSubmittedSuccessfully").toLowerCase()));
			WebActionUtil.info(actualAlertConfirmationMessage + " alert message is displayed");
			WebActionUtil.validationinfo(actualAlertConfirmationMessage + " alert message is displayed", "blue");
			clkOkButton();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate form submitted successfully alert popup");
			Assert.fail("Unable to validate form submitted successfully alert popup");
		}
	}

	/**
	 * Description: This method used to click on not applicable check box.
	 * 
	 * @author Abhishek
	 */
	public synchronized void clkNotApplicableCheckBox() {
		try {
			boolean flag = false;
			for (int i = 0; i < ckbxNotApplicable.size(); i++) {
				if (ckbxNotApplicable.get(i).isDisplayed()) {
					WebActionUtil.scrollToElement(ckbxNotApplicable.get(i), "Not applicable check box");
					ckbxNotApplicable.get(i).click();
					if (!(ckbxNotApplicable.get(i).isSelected())) {
						WebActionUtil.error("Check box is not selected");
						flag = true;
					} else {
						WebActionUtil.info("Check box is selected");
					}
				}
			}
			WebActionUtil.info("Clicked on all remaning not applicable check box");
			WebActionUtil.extentinfo("Clicked on all remaning not applicable check box");
			if (flag == false) {
				WebActionUtil.validationinfo("Not applicable check box is selected", "blue");
			} else {
				WebActionUtil.validationinfo("Not applicable check box is selected", "red");
				Assert.fail("Check box is not selected");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform action on not applicable check box");
			Assert.fail("Unable to perform action on not applicable check box");
		}
	}

	/**
	 * Description : This method is used to validate form submitted successfully
	 * Message.
	 * 
	 * @author Abhishek
	 */
	public synchronized void validateFormSubmittedSuccessfullyMessage() {
		try {
			WebActionUtil.scrollDown();
			WebActionUtil.poll(2000);
			WebActionUtil.validateisElementDisplayed(txtFormSubmittedSuccessfully, "Form submitted successfully text",
					"Form submitted successfully message is displayed",
					"Form submitted successfully message is not displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate form submitted successfully message");
			Assert.fail("Unable to validate form submitted successfully message");
		}
	}

}
