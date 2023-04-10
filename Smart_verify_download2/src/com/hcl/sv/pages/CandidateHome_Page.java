package com.hcl.sv.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.hcl.sv.baseutil.BaseTest;
import com.hcl.sv.util.WebActionUtil;

/**
 * Description: This class implements the method for Candidate home page
 * 
 * @author Manjappa Kammar
 */

public class CandidateHome_Page {

	public WebDriver driver;
	public WebActionUtil actionUtil;
	public long ETO;

	public CandidateHome_Page(WebDriver driver, long ETO, WebActionUtil actionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actionUtil = actionUtil;
		this.ETO = ETO;
	}

	/* Upload link */
	private WebElement uploadLink(String subDocumentType) {
		return driver.findElement(By.xpath("//span[normalize-space(text())='" + subDocumentType
				+ "']/parent::td/following-sibling::td/a[contains(text(),'View/Edit Documents') or contains(text(),'Upload')]"));
	}

	/* File choose button */
	@FindBy(id = "fuUploadMultiple")
	private WebElement btnFileChoose;

	/* Document description text */
	@FindBy(id = "txtDocumentDescMultiDoc")
	private WebElement txtDocumentDescription;

	/* Upload button */
	@FindBy(id = "btnAddDoc")
	private WebElement btnUpload;

	/* File uploaded message */
	@FindBy(id = "lblMessage")
	private WebElement uploadMessage;

	/* Close button */
	@FindBy(id = "btnClose")
	private WebElement btnClose;

	/* Logout link */
	@FindBy(xpath = "//a[text()='Logout']")
	private WebElement lnkLogout;

	/* Upload multiple document heading text */
	@FindBy(xpath = "//td[contains(text(),'Upload Multiple Documents')]")
	private WebElement txtUploadMultipleDocumentHeading;

	/* Submit button */
	@FindBy(xpath = "//input[@value='Submit']")
	private WebElement btnSubmit;

	/* Form submitted successfully message */
	@FindBy(xpath = "//span[text()='Form submitted successfully']")
	private WebElement txtActualFormSubmittedMessage;

	/* Choose file button */
	@FindBy(id = "fuUploadMultiple")
	private WebElement btnChooseFile;

	/* Upload multiple document heading text */
	@FindBy(xpath = "//td[contains(text(),'Upload Multiple Documents')]")
	private WebElement txtActualUploadMultipleDocumentHeading;

	/* Document uploaded successfully message */
	@FindBy(id = "lblMessage")
	private WebElement txtActualDocumentUploadedMessage;

	/* Magnifying glass icon under attachments link */
	private WebElement icnMagnifyingGlass(String fileName) {
		return driver.findElement(
				By.xpath("//td[contains(text(),'" + fileName + "')]/following-sibling::td/input[@type='image']"));
	}

	/* Form submitted successfully message */
	@FindBy(xpath = "//span[text()='Form submitted successfully']")
	private WebElement txtFormSubmittedSuccessfullyMessage;

	/* Uploaded Document Details List */
	private List<WebElement> lstUploadedDocumentDetails(String documentSubType) {
		String xpath = "//span[normalize-space(text())='" + documentSubType + "']/parent::td/following-sibling::td";
		return driver.findElements(By.xpath(xpath));
	}

	/**
	 * Description: This method is used to click on Upload link
	 * 
	 * @author Manjappa Kammar
	 * @param subDocumentType
	 */
	public void clkUploadLink(String subDocumentType) {
		try {
			try {
				if ((uploadLink(subDocumentType).isDisplayed())) {
				}
			} catch (Exception ex) {
				WebActionUtil.scrollDown();
			}
			WebActionUtil.clickOnElement(uploadLink(subDocumentType), subDocumentType + "Upload link");
			WebActionUtil.validationinfo("Clicked on upload link of " + subDocumentType, "blue");
			WebActionUtil.poll(2000);
			WebActionUtil.switchWindows();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on " + subDocumentType + " Upload link");
			Assert.fail("Unable to click on " + subDocumentType + " Upload link");
		}
	}

	/**
	 * Description: This method is used to validate new window is displayed with
	 * upload multiple document header.
	 * 
	 * @author Manjappa Kammar
	 * @param expectedHeader
	 */
	public synchronized void validationMultipleDocumentPage(String expectedHeader) {
		try {
			try {
				WebActionUtil.validatetext(expectedHeader, txtUploadMultipleDocumentHeading,
						"Upload multiple document header", "Upload multiple documents page is displayed",
						"Upload multiple documents page is not displayed", "blue");
			} catch (Exception e) {
				WebActionUtil.switchWindows();
				WebActionUtil.validatetext(expectedHeader, txtUploadMultipleDocumentHeading,
						"Upload multiple document header", "Upload multiple documents page is displayed",
						"Upload multiple documents page is not displayed", "blue");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Upload multiple documents page");
			Assert.fail("Unable to validate Upload multiple documents page");
		}
	}

	/**
	 * Description : This method is used to validate Document uploaded successfully message displayed.
	 * 
	 * @author Manjappa Kammar
	 * @param txtExpectedUploadedMessage
	 */
	public synchronized void validateDocumentUploadedSuccesfullyMsg(String txtExpectedUploadedMessage) {
		try {
			String txtActualUplodedMessage = WebActionUtil.getText(uploadMessage, "Document uploaded successfully");
			Assert.assertTrue(txtActualUplodedMessage.toLowerCase().equals(txtExpectedUploadedMessage.toLowerCase()));
			WebActionUtil.info("Document uploaded successfully message is displayed");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Document uploaded successfully message is not displayed");
			Assert.fail("Document uploaded successfully message is not displayed");
		}
	}

	/**
	 * Description : This method is used to click on File choose button
	 * 
	 * @author Manjappa Kammar
	 * @param imagePath
	 * @param fileName
	 */
	public synchronized void clkFileChooseButton(String imagePath, String fileName) {
		try {
			WebActionUtil.actionClick(btnChooseFile, "Choose file Button");
			WebActionUtil.upload(imagePath);
			validateUploadedDocument(fileName, btnChooseFile);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Choose file Button");
			Assert.fail("Unable to click on Choose file Button");
		}
	}

	/**
	 * Description : This method is used to enter description into Document
	 * description text field
	 * 
	 * @author Manjappa Kammar
	 * @param description
	 * 
	 */
	public synchronized void setDescription(String description) {
		try {
			WebActionUtil.typeText(txtDocumentDescription, description, "Document description text field");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to type description into Document description text field");
			Assert.fail("Unable to type description into Document description text field");
		}
	}

	/**
	 * Description : This method is used to click on Upload button
	 * 
	 * @author Manjappa Kammar
	 */
	public synchronized void clkUploadButton() {
		try {
			WebActionUtil.clickOnElement(btnUpload, "Upload button");

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Upload button");
			Assert.fail("Unable to click on Upload button");
		}
	}

	/**
	 * Description : This method is used to click on Close button
	 * 
	 * @author Manjappa Kammar
	 */
	public synchronized void clkClosebutton() {
		try {
			WebActionUtil.clickOnElement(btnClose, "Close button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Close button");
			Assert.fail("Unable to click on Close button");
		}
	}

	/**
	 * Description : This method is used to validate Alert pop up confirmation
	 * message
	 * 
	 * @author Manjappa Kammar
	 * @param expectedAlertMessage
	 */
	public synchronized void validateAlertPopup(String expectedAlertMessage) {
		try {
			Alert alert = driver.switchTo().alert();
			String actualAlertConfirmationMessage = alert.getText();
			System.out.println(actualAlertConfirmationMessage);
			Assert.assertTrue(actualAlertConfirmationMessage.toLowerCase().equals(expectedAlertMessage.toLowerCase()));
			WebActionUtil.info("Alert pop up is displayed");
			WebActionUtil.validationinfo("Alert pop up is displayed", "green");
			alert.accept();
			WebActionUtil.info("Clicked on ok button in alert pop up");
			WebActionUtil.extentinfo("Clicked on ok button in alert pop up");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Ok button in alert pop up");
			Assert.fail("Unable to click on Ok button in alert pop up");
		}
	}

	/**
	 * Description : This method is used to validate Alert pop up confirmation
	 * message
	 * 
	 * @author Manjappa Kammar
	 * @param expectedSubmitAlertMessage
	 */
	public synchronized void validateSubmitAlertPopup(String expectedSubmitAlertMessage) {
		try {
			Alert alert = driver.switchTo().alert();
			String actualAlertConfirmationMessage = alert.getText();
			Assert.assertTrue(
					actualAlertConfirmationMessage.toLowerCase().equals(expectedSubmitAlertMessage.toLowerCase()));
			WebActionUtil.info("Alert pop up is displayed");
			WebActionUtil.validationinfo("Alert pop up is displayed", "green");
			alert.accept();
			WebActionUtil.info("Clicked on ok button in Alert pop up");
			WebActionUtil.extentinfo("Clicked on ok button in Alert pop up");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Ok button in Alert pop up");
			Assert.fail("Unable to click on Ok button in Alert pop up");
		}
	}

	/**
	 * Description : This method is used to validate Form submitted successfully
	 * message
	 * 
	 * @author Manjappa Kammar 
	 * 
	 */
	public synchronized void validateFormSubmittedSuccessfullyMessage() {
		try {
			WebActionUtil.scrollDown();
			WebActionUtil.validateisElementDisplayed(txtFormSubmittedSuccessfullyMessage, "Form submitted successfully",
					"Form submitted successfully message is displayed",
					"Form submitted successfully message is not displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Form submitted successfully message is not displayed");
			Assert.fail("Form submitted successfully message is not displayed");
		}
	}

	/**
	 * Description : This method is used to upload file by clicking on Upload link
	 * of Graduation under attachment.
	 * 
	 * @author Manjappa Kammar
	 * 
	 * @param subDocumentType
	 * @param txtExpectedHeader
	 * @param imagePath
	 * @param description
	 * @param txtExpectedUploadedMessage
	 * @param expectedAlertMessage
	 * @param fileName
	 */
	public synchronized void uploadFileforGraduation(String subDocumentType, String txtExpectedHeader, String imagePath,
			String description, String txtExpectedUploadedMessage, String expectedAlertMessage, String fileName) {
		String parentWindow = driver.getWindowHandle();
		clkUploadLink(subDocumentType);
		validationMultipleDocumentPage(txtExpectedHeader);
		clkFileChooseButton(imagePath, fileName);
		setDescription(description);
		clkUploadButton();
		validateDocumentUploadedSuccesfullyMsg(txtExpectedUploadedMessage);
		validateUploadedDocumentDetails(subDocumentType, fileName, description);
		clkClosebutton();
		validateAlertPopup(expectedAlertMessage);
		WebActionUtil.poll(2000);
		driver.switchTo().window(parentWindow);
		validateCandidateHomePage();
	}

	/**
	 * Description : This method is used to upload file by clicking on Upload link
	 * of Second last employee under attachment
	 * 
	 * @author Manjappa Kammar
	 * 
	 * @param subDocumentType
	 * @param txtExpectedHeader
	 * @param imagePath
	 * @param description
	 * @param txtExpectedUploadedMessage
	 * @param expectedAlertMessage
	 * @param fileName
	 */
	public synchronized void uploadFileforSecondLastEmpolyee(String subDocumentType, String txtExpectedHeader,
			String imagePath, String description, String txtExpectedUploadedMessage, String expectedAlertMessage,
			String fileName) {
		String parentWindow = driver.getWindowHandle();
		clkUploadLink(subDocumentType);
		validationMultipleDocumentPage(txtExpectedHeader);
		clkFileChooseButton(imagePath, fileName);
		setDescription(description);
		clkUploadButton();
		validateDocumentUploadedSuccesfullyMsg(txtExpectedUploadedMessage);
		validateUploadedDocumentDetails(subDocumentType, fileName, description);
		clkClosebutton();
		validateAlertPopup(expectedAlertMessage);
		WebActionUtil.poll(2000);
		driver.switchTo().window(parentWindow);
		validateCandidateHomePage();
	}

	/**
	 * Description: This method is used to click Submit button.
	 * 
	 * @author SreeLatha
	 */
	public synchronized void clkSubmitButton() {
		try {
			System.out.println("click submit");
			WebActionUtil.clickOnElement(btnSubmit, "Submit button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Submit button");
			Assert.fail("Unable to click on Submit button");
		}
	}

	/**
	 * Description: This method is used to click on Magnifying glass icon under
	 * attachment.
	 * 
	 * @author SreeLatha
	 * @param fileName
	 */
	private synchronized void clkMagnifyGlassIcon(String fileName) {
		try {
			WebActionUtil.clickOnElement(icnMagnifyingGlass(fileName), "Magnifying glass Icon");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to perform click action on Magnifying glass Icon");
			Assert.fail("Unable to perform click action on Magnifying glass Icon");
		}
	}

	/**
	 * Description: This method is used to upload file by clicking on view edit
	 * document link of Post graduation.
	 * 
	 * @author SreeLatha
	 * @param subDocumentType
	 * @param txtExpectedHeader
	 * @param imagePath
	 * @param description
	 * @param txtExpectedUploadedMessage
	 * @param fileName
	 * @param expectedAlertMessage
	 * @param txtExpectedHeaderUrl
	 * @param expectedCandidateHomePageURL
	 * @param fileName2
	 */
	public synchronized void uploadFileForPostGraduation1(String subDocumentType, String txtExpectedHeader,
			String imagePath, String description, String txtExpectedUploadedMessage, String fileName,
			String expectedAlertMessage, String txtExpectedHeaderUrl, String expectedCandidateHomePageURL,
			String fileName2) {
		String parentWindow = driver.getWindowHandle();
		clkUploadLink(subDocumentType);
		validationMultipleDocumentPage(txtExpectedHeader);
		clkFileChooseButton(imagePath, fileName2);
		setDescription(description);
		clkUploadButton();
		validateDocumentUploadedSuccesfullyMsg(txtExpectedUploadedMessage);
		clkMagnifyGlassIcon(fileName);
		clkClosebutton();
		validateAlertPopup(expectedAlertMessage);
		WebActionUtil.poll(2000);
		driver.switchTo().window(parentWindow);
		validateCandidateHomePage();
	}

	/**
	 * Description: This method is used to upload file by clicking on view edit
	 * document link of Intermediate-12th standard.
	 * 
	 * @author SreeLatha
	 * @param subDocumentType
	 * @param txtExpectedHeader
	 * @param imagePath
	 * @param description
	 * @param txtExpectedUploadedMessage
	 * @param expectedAlertMessage
	 * @param fileName
	 */
	public synchronized void uploadFileForIntermediate(String subDocumentType, String txtExpectedHeader,
			String imagePath, String description, String txtExpectedUploadedMessage, String expectedAlertMessage,
			String fileName) {
		String parentWindow = driver.getWindowHandle();
		clkUploadLink(subDocumentType);
		validationMultipleDocumentPage(txtExpectedHeader);
		clkFileChooseButton(imagePath, fileName);
		setDescription(description);
		clkUploadButton();
		validateDocumentUploadedSuccesfullyMsg(txtExpectedUploadedMessage);
		validateUploadedDocumentDetails(subDocumentType, fileName, description);
		clkClosebutton();
		validateAlertPopup(expectedAlertMessage);
		WebActionUtil.poll(2000);
		driver.switchTo().window(parentWindow);
		validateCandidateHomePage();
	}

	/**
	 * Description: This method used to validate Uploaded document details under
	 * List of Documents uploaded table.
	 * 
	 * @author Manjappa Kammar
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
					+ " are the Uploaded document details displayed under list of Documents uploaded table");
			WebActionUtil.validationinfo(
					actualDetails
							+ " are the Uploaded document details displayed under list of Documents uploaded table",
					"blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Uploaded document details under list of Documents uploaded table");
			Assert.fail("Unable to validate Uploaded document details under list of Documents uploaded table");
		}
	}

	/**
	 * Description: This method used to click on Choose file button.
	 * 
	 * @author Manjappa Kammar
	 * @param imagePath
	 */
	public synchronized void validateFileUploaded(String imagePath) {
		try {
			WebActionUtil.waitForElement(btnChooseFile, "Choose file button");
			String actualFileName = btnChooseFile.getAttribute("value");
			String[] actualName = actualFileName.split("Sample");
			Assert.assertTrue((imagePath.toLowerCase()).contains(actualName[1].toLowerCase()));
			WebActionUtil.info(actualName[1] + " is Selected and displayed");
			WebActionUtil.validationinfo(actualName[1] + " is Selected and displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate File is uploaded");
			Assert.fail("Unable to validate File is uploaded");
		}
	}

	/**
	 * Description: This method is used to upload file by clicking on Upload or
	 * View/Edit document link of Desired Document.
	 * 
	 * @author Manjappa Kammar
	 * @param subDocumentType
	 * @param txtExpectedHeader
	 * @param imagePath
	 * @param description
	 * @param documentSubType
	 * @param fileName
	 * @param expectedAlertMessage
	 * @param txtExpectedHeaderUrl
	 * @param expectedCandidateHomePageURL
	 */
	public synchronized void clkUploadLinkToUploadDesiredDocumentSubType(String subDocumentType,
			String txtExpectedHeader, String imagePath, String description, String documentSubType, String fileName,
			String expectedAlertMessage, String txtExpectedHeaderUrl, String expectedCandidateHomePageURL) {
		try {
			String parentWindow = driver.getWindowHandle();
			clkUploadLink(subDocumentType);
			validationMultipleDocumentPage(txtExpectedHeader);
			clkFileChooseButton(imagePath, fileName);
			validateFileUploaded(imagePath);
			setDescription(description);
			clkUploadButton();
			validateUploadedDocumentDetails(documentSubType, description, fileName);
			clkClosebutton();
			validateAlertPopup(expectedAlertMessage);
			driver.switchTo().window(parentWindow);
			validateCandidateHomePage();

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Upload document for " + documentSubType + " link");
			Assert.fail("Unable to Upload document for " + documentSubType + " link");
		}
	}

	/**
	 * Description: This method is used to upload file by clicking on view edit
	 * document link of Second candidate login.
	 * 
	 * @author SreeLatha
	 * @param subDocumentType
	 * @param expectedHeader
	 * @param txtExpectedHeader
	 * @param imagePath
	 * @param description
	 * @param txtExpectedUploadedMessage1
	 * @param fileName
	 * @param expectedAlertMessage
	 * @param expectedSubmitAlertMessage
	 */
	public synchronized void clkUploadLinkToUploadDesiredDocumentSubTypeForSecondCadiadteLogin(String subDocumentType,
			String txtExpectedHeader, String imagePath, String description, String txtExpectedUploadedMessage1,
			String fileName, String expectedAlertMessage, String expectedSubmitAlertMessage) {
		String parentWindow = driver.getWindowHandle();
		clkUploadLink(subDocumentType);
		validationMultipleDocumentPage(txtExpectedHeader);
		clkFileChooseButton(imagePath, fileName);
		setDescription(description);
		clkUploadButton();
		validateDocumentUploadedSuccesfullyMsg(txtExpectedUploadedMessage1);
		validateUploadedDocumentDetails(subDocumentType, description, fileName);
		clkMagnifyGlassIcon(fileName);
		validateDocumentDownloaded(fileName);
		clkClosebutton();
		validateAlertPopup(expectedAlertMessage);
		WebActionUtil.poll(2000);
		driver.switchTo().window(parentWindow);
		validateCandidateHomePage();
		clkSubmitButton();
		validateSubmitAlertPopup(expectedSubmitAlertMessage);
		validateFormSubmittedSuccessfullyMessage();
	}

	/**
	 * Description: This method used to validate homePage.
	 * 
	 * @author Manjappa Kammar
	 * 
	 */
	public synchronized void validateCandidateHomePage() {
		try {
			WebActionUtil.validateUrl(BaseTest.prop_constants.getProperty("candidate_Home_Page_url"), "Candiadte home");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Candidate home page");
			Assert.fail("Unable to validate Candidate homepPage");
		}
	}

	/**
	 * 
	 * Description: Method implements to validate Uploaded document
	 * 
	 * @author Manjappa Kammar
	 * @param filePath,element
	 */
	public synchronized void validateUploadedDocument(String filePath, WebElement element) {
		try {
			WebActionUtil.poll(4000);
			String[] fileLocation = filePath.split("\\\\");
			String fileName = " ";
			for (int i = fileLocation.length - 1; i < fileLocation.length; i++) {
				fileName = fileLocation[i];
				System.out.println(fileName);
			}
			String expected = btnChooseFile.getAttribute("value");
			Assert.assertTrue(expected.contains(fileName));
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate the uploaded document");
			Assert.fail("Unable to validate the uploaded document");
		}

	}

	/**
	 * Description: This method used to validate Document downloaded successfully.
	 * 
	 * @author Manjappa Kammar
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

}
