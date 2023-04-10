package com.hcl.sv.testscripts;

import org.testng.annotations.Test;

import com.hcl.sv.baseutil.BaseTest;
import com.hcl.sv.dataprovider.DataProviderFactory;
import com.hcl.sv.dataprovider.DataProviderFileRowFilter;

/*
 *TestCaseId: TC_SV_Existing_001
 *TestScript Name: TYSS_SV_E_001
 *Description: Verify the employee is able to submit the documents to vendor and then the initiator is able to close 
 *the case after verifying all the documents
 * 
 *@Author : Ramya R
 * 
 */

public class TYSS_SV_Existing_001 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/Data.xlsx", sql = "Select * from SV_Existing where SlNo ='TC_SV_E_001'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Verify the employee is able to submit the documents to vendor and then the initiator is able to close  the case after verifying all the documents")
	
	public void TC_TYSS_SV_Existing_001(String SlNo, String sapCode, String techLoginCode, String techPassword,
			String techEmpCode, String bgvId, String fromDate, String toDate, String vendorUserId,
			String vendorPassword) {
	
		/* Login to application as Employee*/
		pages.techLoginLoginPage.loginToApplicationAsEmployee(sapCode, techLoginCode, techPassword);

		/* Set unique verification id text field */
		pages.techLoginEmployeeHomePage.setEnterUniqueVerificationIdTextField(bgvId);

		/* Click on View Details button */
		pages.techLoginEmployeeHomePage.clkViewDetailsButton();

		/* Validate my document details header text */
		pages.techLoginEmployeeHomePage.validateMyDocumentDetailsHeaderText();

		/* Click on Upload link of post graduation document sub type under attachment */
		pages.techLoginEmployeeHomePage.clkUploadLinkForPostGraduation(
				prop_constants.getProperty("document_Type_Education"),
				prop_constants.getProperty("document_Sub_Type_PostGraduation"),
				actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_pdf")),
				prop_constants.getProperty("descriptionTesting"), prop_constants.getProperty("fileFormat_pdf"));

		/* Click Upload link of graduation document sub type under attachment */
		pages.techLoginEmployeeHomePage.clkUploadLinkToUploadDesiredDocumentSubType(
				prop_constants.getProperty("document_Type_Education"),
				prop_constants.getProperty("document_Sub_Type_Graduation"),
				actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_jpg")),
				prop_constants.getProperty("descriptionTesting"), prop_constants.getProperty("fileFormat_jpg"));

		/* Click Upload link of intermediate-12 document sub type under attachment */
		pages.techLoginEmployeeHomePage.clkUploadLinkToUploadDesiredDocumentSubType(
				prop_constants.getProperty("document_Type_Education"),
				prop_constants.getProperty("document_Sub_Type_Intermediate12thStandard"),
				actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_jpg")),
				prop_constants.getProperty("descriptionTesting"), prop_constants.getProperty("fileFormat_jpg"));

		/* Click on Not Applicable check box */
		pages.techLoginEmployeeHomePage.clkNotApplicableCheckBox();

		/* Click on Submit button */
		pages.techLoginEmployeeHomePage.clkSubmitButton();

		/* Validate Do you really want to submit the document alert pop up */
		pages.techLoginEmployeeHomePage.validateDoYouReallyWantToSubmitTheDocumentAlertPopup();

		/* Validate form submitted successfully alert pop up */
		pages.techLoginEmployeeHomePage.validateFormSubmittedSuccessfullyAlertPopup();

		/* Validate employee home page */
		pages.techLoginEmployeeHomePage.validateEmployeeHomePage();

		/***************** TechLogin ****************/

		/* Login to application as Tech */
		pages.techLoginLoginPage.loginToApplicationAsTech(techEmpCode, techLoginCode, techPassword);

		/* Click on initiator link from role list */
		pages.techLoginManageRolesPage.selectRoles(prop_constants.getProperty("role_Initiator"));

		/* Validate initiator home page */
		pages.techLoginInitiatorHomePage.validateInitiatorHomePage();

		/* Click on header dropDown */
		pages.techLoginInitiatorHomePage.clkLinkInHeaderDD(prop_constants.getProperty("initiator"),
				prop_constants.getProperty("initiator_view"));

		/* Validate initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Select the Existing radio button */
		pages.techLoginInitiatorViewPage.clkTypeRadioButton(prop_constants.getProperty("rdo_Existing"));

		/* Set BGV Id in textField */
		pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

		/* Click on  Record Status and select as pending with BGV initiation */
		pages.techLoginInitiatorViewPage
				.selectRecordStatus(prop_constants.getProperty("pending_for_BGV_Initiation_Dd"));

		/* Click on Search button */
		pages.techLoginInitiatorViewPage.clkSearchButton();

		/* Verify searched BGV Id is displayed */
		pages.techLoginInitiatorViewPage.verifySearchedBgvIdIsDisplayed();

		String parentWindow = driver.getWindowHandle();

		/* Click Upload link */
		pages.techLoginInitiatorViewPage.clkUploadLink();

		/* Validate alert pop up and click on OK */
		pages.techLoginInitiatorViewPage.validateAlertPopup(prop_constants
				.getProperty("alert_msg_Post_Joining_for_this_Employee_has_been_Done_Do_you_want_to_proceed?"));

		/* Validate manage background checks initiator view page */
		pages.techLoginInitiatorViewPage.validateManageBackgroundChecksOfInitiatorViewPage();

		String[] documentType1 = { prop_constants.getProperty("document_Sub_Type_PostGraduation"),
				prop_constants.getProperty("document_Sub_Type_Graduation"),
				prop_constants.getProperty("document_Sub_Type_Intermediate12thStandard") };

		String[] documentDescription1 = { prop_constants.getProperty("descriptionTesting"),
				prop_constants.getProperty("descriptionTesting"), prop_constants.getProperty("descriptionTesting") };

		/* Verify all documents should be uploaded */
		pages.techLoginInitiatorViewPage.verifyThatAllTheDocumentsAreUploaded(documentType1, documentDescription1);

		/* Click on Close button */
		pages.techLoginInitiatorViewPage.clkCloseButton(parentWindow);

		/* Validate tech login initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Click on Select checkBox under Select column */
		pages.techLoginInitiatorViewPage.clkCheckBoxUnderSelectColumn();

		/* Click on Mark checkBox */
		pages.techLoginInitiatorViewPage.clkMarkTheCheckBoxInCaseOfTPREBandAndGeoEntityWhileClosingTheCaseCheckBox();

		/* Click on Initiate BGV button */
		pages.techLoginInitiatorViewPage.clkInitiateBGVButton();

		/* Validate alert pop up and click on OK */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("selected_record(s)_will_be_submitted"));

		/* Wait for invisibility of spinner */
		pages.techLoginInitiatorViewPage.waitForInvisibilityOfSpinner();

		/* Validate alert pop up and click on OK */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("record_send_to_Vendor_Successfully"));

		/* Validate initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Verify employee details should be disappeared */
		pages.techLoginInitiatorViewPage.verifyEmployeeDetailIsDisappeared();

		/****************** Vendor ******************/

		/* Login to application as vendor */
		pages.vendorLoginPage.loginToApplicationAsVendor(vendorUserId, vendorPassword);

		/* Click on View tab */
		pages.vendorTPAHomePage.clkView();

		/* Validate vendor TPA view page */
		pages.vendorTPAViewPage.validateVendorTPAViewPage();

		/* Enter BGV Id in BGV id textField */
		pages.vendorTPAViewPage.setBgvId(bgvId);

		/* Click on Search button */
		pages.vendorTPAViewPage.clkSearch();

		/* Verify searched BGV Id is displayed */
		pages.vendorTPAViewPage.verifySearchedBgvIdIsDisplayed(bgvId);

		parentWindow = driver.getWindowHandle();

		/* Click Edit or Update link */
		pages.vendorTPAViewPage.clkEditOrUpdateLink();

		/* Validate background check page */
		pages.vendorLoginBackgroundCheckPage.validateBackGroundCheckPage();

		/* Click on '+' symbol in Enter duration for criminal court ,address and employment */
		pages.vendorLoginBackgroundCheckPage.clkImagePluseEnterDurationForCriminalCourt(
				prop_constants.getProperty("CatagoryCriminalCourt"), prop_constants.getProperty("color_green"),
				fromDate, toDate);

		/* Click on '+' symbol in Required Checks ::Document Specification */
		pages.vendorLoginBackgroundCheckPage.clkImagePluseDocumentSpecification(
				prop_constants.getProperty("educationType1-10"), prop_constants.getProperty("color_green"));

		/* Click on choose file under Send Communication/Upload Verification report */
		pages.vendorLoginBackgroundCheckPage
				.uploadFileAndValidate(actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_pdf")));

		/* Select Final report radio button */
		pages.vendorLoginBackgroundCheckPage
				.clkSendCommunicationRadioButton(prop_constants.getProperty("rdo_Final_Report"));

		/* Set comment in textField */
		pages.vendorLoginBackgroundCheckPage.setComment(prop_constants.getProperty("comment_Ok"));

		/* Click on Submit to BGV team button */
		pages.vendorLoginBackgroundCheckPage.clkSubmitToBGVTeamBtn();

		/* Validate alert pop up and click on Ok */
		pages.vendorLoginBackgroundCheckPage
				.clkAlertPopUpOkBtn(prop_constants.getProperty("alert_Msg_Unselect_checks_will_be_mark_as_NA"));

		/* Wait for invisibility of spinner */
		pages.vendorLoginBackgroundCheckPage.waitForInvisibilityOfSpinner();

		/* Validate alert pop up and click on OK */
		pages.vendorLoginBackgroundCheckPage.clkAlertPopUpOkBtn(
				prop_constants.getProperty("header_verificationReporthasbeensubmittedsuccessfully"));

		/* Verify employee details is disappeared */
		pages.vendorTPAViewPage.verifyEmployeeDetailIsDisappeared(bgvId, parentWindow);

		/********************* TechLogin *******************/

		/* Login to application as Tech */
		pages.techLoginLoginPage.loginToApplicationAsTech(techEmpCode, techLoginCode, techPassword);

		/* Click on initiator link from role list */
		pages.techLoginManageRolesPage.selectRoles(prop_constants.getProperty("role_Initiator"));

		/* Validate initiator home page */
		pages.techLoginInitiatorHomePage.validateInitiatorHomePage();

		/* Click on initiator on header dropDown and select initiator view */
		pages.techLoginInitiatorHomePage.clkLinkInHeaderDD(prop_constants.getProperty("initiator"),
				prop_constants.getProperty("initiator_view"));

		/* Validate tech login initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Click on Existing radio button */
		pages.techLoginInitiatorViewPage.clkTypeRadioButton(prop_constants.getProperty("rdo_Existing"));

		/* Set BGV Id in textField */
		pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

		/* Select Record Status from dropDown */
		pages.techLoginInitiatorViewPage
				.selectRecordStatus(prop_constants.getProperty("submitted_By_Vendor_Pending_With_Initiator_Dd"));

		/* Click on Search button */
		pages.techLoginInitiatorViewPage.clkSearchButton();

		/* Verify searched BGV Id is displayed */
		pages.techLoginInitiatorViewPage.verifySearchedBgvIdIsDisplayed();

		parentWindow = driver.getWindowHandle();

		/* Click View or Edit link */
		pages.techLoginInitiatorViewPage.clkViewOrEditLink();

		/* Click on Verify checks button */
		pages.techLoginInitiatorViewPage.clkVerifyChecksButton();

		/* Validate alert pop up and click on OK  */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("alert_msg_document_has_been_Verified"));

		/* Click on Close button */
		pages.techLoginInitiatorViewPage.clkCloseButton(parentWindow);

		/* Validate initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Click on Existing radio button */
		pages.techLoginInitiatorViewPage.clkTypeRadioButton(prop_constants.getProperty("rdo_Existing"));

		/* Set BGV Id in textField */
		pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

		/* Select Record Status from dropDown */
		pages.techLoginInitiatorViewPage
				.selectRecordStatus(prop_constants.getProperty("submitted_By_Vendor_Pending_With_Initiator_Dd"));

		/* Click on Search button */
		pages.techLoginInitiatorViewPage.clkSearchButton();

		/* Set Transaction remark textField */
		pages.techLoginInitiatorViewPage.setTransactionRemarkTextField(prop_constants.getProperty("remark"));

		/* Click on Select check box under Select column */
		pages.techLoginInitiatorViewPage.clkCheckBoxUnderSelectColumn();

		/* Click on Mark check box */
		pages.techLoginInitiatorViewPage.clkMarkTheCheckBoxInCaseOfTPREBandAndGeoEntityWhileClosingTheCaseCheckBox();

		/* Click on Close Case button */
		pages.techLoginInitiatorViewPage.clkCloseCaseButton();

		/* Validate alert pop up and click on OK */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("selected_record(s)_will_be_submitted"));

		/* Wait for invisibility of spinner */
		pages.techLoginInitiatorViewPage.waitForInvisibilityOfSpinner();

		/* Validate alert pop up and click on OK */
		pages.techLoginInitiatorViewPage.validateAlertPopup(
				prop_constants.getProperty("alert_msg_bgv_for_the_selected_employees_has_been_closed."));

		/* Validate initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Verify employee details is disappeared */
		pages.techLoginInitiatorViewPage.validateEmployeeDetailIsDisappeared();

	}
}
