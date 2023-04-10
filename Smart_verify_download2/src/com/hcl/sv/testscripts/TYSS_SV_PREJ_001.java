package com.hcl.sv.testscripts;

import org.testng.annotations.Test;

import com.hcl.sv.baseutil.BaseTest;
import com.hcl.sv.dataprovider.DataProviderFactory;
import com.hcl.sv.dataprovider.DataProviderFileRowFilter;

/**
 * TestCaseId: TC_SV_PreJoining_001 TestScript Name: TYSS_SV_PREJ_001
 * Description: Verify the Candidate is able to submit documents to Recruiter,
 * Recruiter is able to send the documents to Vendor, Vendor is able to verify
 * the documents and submit to BGV, Initiator is able to verify all the
 * documents and close the case.
 *
 * @Author: Abhishek
 */
public class TYSS_SV_PREJ_001 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/Data.xlsx", sql = "Select * from SV_Prejoining where SlNo ='TC_SV_PreJoining_001'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Verify the Candidate is able to submit documents to Recruiter, Recruiter is able to send the documents to Vendor, Vendor is able to verify the documents and submit to BGV, Initiator is able to verify all the documents and close the case.")

	public void TC_TYSS_SV_PREJ_001(String SINO, String candidateUserId, String candidatePassword, String techEmpCode,
			String techLoginCode, String techPassword, String vendorUserId, String vendorPassword, String bgvId,
			String fromDate, String toDate) {

		/************ Candidate Login ************************************/

		/* Login to the application as candidate */
		pages.candidateLoginPage.loginToApplication(candidateUserId, candidatePassword);

		/* Validate home page */
		pages.candidateHomePage.validateCandidateHomePage();

		/* Click on graduation sub type upload link */
		pages.candidateHomePage.uploadFileforGraduation(prop_constants.getProperty("document_Sub_Type_Graduation"),
				prop_constants.getProperty("header_uploadMultipleDocuments"),
				actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_jpg")),
				prop_constants.getProperty("descriptionTesting"),
				prop_constants.getProperty("uploadMessage_DocumentUploadedSuccessfully"),
				prop_constants.getProperty("header_areYouSureYouWantToCloseWindow"),
				prop_constants.getProperty("fileFormat_jpg"));

		/* Click on intermediate sub type upload link */
		pages.candidateHomePage.uploadFileForIntermediate(
				prop_constants.getProperty("document_Sub_Type_Intermediate12thStandard"),
				prop_constants.getProperty("header_uploadMultipleDocuments"),
				actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_jpeg")),
				prop_constants.getProperty("descriptionTesting"),
				prop_constants.getProperty("uploadMessage_DocumentUploadedSuccessfully"),
				prop_constants.getProperty("header_areYouSureYouWantToCloseWindow"),
				prop_constants.getProperty("fileFormat_jpeg"));

		/* Click on second last employer sub type upload link */
		pages.candidateHomePage.uploadFileforSecondLastEmpolyee(
				prop_constants.getProperty("document_Sub_Type_SecondLastEmployer"),
				prop_constants.getProperty("header_uploadMultipleDocuments"),
				actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_png")),
				prop_constants.getProperty("descriptionTesting"),
				prop_constants.getProperty("uploadMessage_DocumentUploadedSuccessfully"),
				prop_constants.getProperty("header_areYouSureYouWantToCloseWindow"),
				prop_constants.getProperty("fileFormat_png"));

		/* Click on submit button */
		pages.candidateHomePage.clkSubmitButton();

		/* Validate submit alert pop up and click on ok button in pop up */
		pages.candidateHomePage
				.validateSubmitAlertPopup(prop_constants.getProperty("header_doYouReallyWantToSubmitTheDocument"));

		/* Validate form submitted successfully message */
		pages.candidateHomePage.validateFormSubmittedSuccessfullyMessage();

		/* Validate candidate home page */
		pages.candidateHomePage.validateCandidateHomePage();

		/**************************** TechLogin Recruiter *****************************/

		/* Login to application as a tech */
		pages.techLoginLoginPage.loginToApplicationAsTech(techEmpCode, techLoginCode, techPassword);

		/* Select manage role */
		pages.techLoginManageRolesPage.selectRoles(prop_constants.getProperty("role_Recruiter"));

		/* Validate recruiter home page */
		pages.techLoginRecruiterHomePage.validateRecruiterHomePage();

		/* Click on recruiter view link */
		pages.techLoginRecruiterHomePage.clkLinkInHeader(prop_constants.getProperty("recruiter_View"));

		/* Validate Recruiter View page */
		pages.techLoginRecruiterViewPage.validateRecruiterViewPage();

		/* Enter BGV id in BGV Text field */
		pages.techLoginRecruiterViewPage.setBgvIdTextField(bgvId);

		/* Click on record status and select document upload pending with recruiter */
		pages.techLoginRecruiterViewPage
				.selectRecordStatus(prop_constants.getProperty("document_uploaded_Pending_With_Recruiter_Dd"));

		/* Click on search button */
		pages.techLoginRecruiterViewPage.clkSearchButton();

		/* Verify search BGV id displayed */
		pages.techLoginRecruiterViewPage.verifySearchedBgvIdIsDisplayed(bgvId);

		String parentWindow = driver.getWindowHandle();

		/* Click on view link under BGV details */
		pages.techLoginRecruiterViewPage.clkViewLink();

		/* Validate recruiter view page */
		pages.techLoginRecruiterRoleViewPage.validateManageBackgroundChecksOfRecruiterViewPage();

		String[] description = { prop_constants.getProperty("descriptionTesting"),
				prop_constants.getProperty("descriptionTesting"), prop_constants.getProperty("descriptionTesting") };
		String[] documents = { prop_constants.getProperty("document_Sub_Type_Graduation"),
				prop_constants.getProperty("document_Sub_Type_Intermediate12thStandard"),
				prop_constants.getProperty("document_Sub_Type_SecondLastEmployer") };

		/* Verify all documents uploaded */
		pages.techLoginRecruiterRoleViewPage.verifyThatAllTheDocumentsAreUploaded(documents, description);

		/* Close the window */
		pages.techLoginRecruiterRoleViewPage.clkCloseButton(parentWindow);

		/* Validating techLogin recruiter view Page */
		pages.techLoginRecruiterViewPage.validateRecruiterViewPage();

		/* Select HCL standard 2 option from package drop down */
		pages.techLoginRecruiterViewPage.selectPackage(prop_constants.getProperty("HCL_Standard2_Dd"));

		/* Click on select check box under select column */
		pages.techLoginRecruiterViewPage.clkCheckBoxUnderSelectColumn();

		/* Select I am confirming check box */
		pages.techLoginRecruiterViewPage
				.clkIAmConfirmingThatIVerifiedTheDocumentsSubmittedByTheCandidateAgainstTheMRLListCheckBox();

		/* Click on submit to BGV team button */
		pages.techLoginRecruiterViewPage.clkSubmitToBgvTeamButton();

		/* Validate alert pop up and click on Ok button */
		pages.techLoginRecruiterViewPage.validateAlertPopup(prop_constants.getProperty(
				"it_is_assumed_that_you_have_validated_all_the_documents_uploaded_by_the_candidate_against_the_MRL_Fake/Suspicious_list__Alert_Msg"));

		/* Wait for invisibility of spinner */
		pages.techLoginRecruiterViewPage.waitForInvisibilityOfSpinner();

		/* Click on Ok button */
		pages.techLoginRecruiterViewPage.validateAlertPopup(
				prop_constants.getProperty("bgv_for_the_selected_employees_has_been_send_to_BGV_Team_Alert_Msg"));

		/* Validate recruiter view page */
		pages.techLoginRecruiterViewPage.validateRecruiterViewPage();

		/* Validate employee details should be disappeared */
		pages.techLoginRecruiterViewPage.verifyEmployeeDetailIsDisappeared(bgvId);

		/**************** Tech Login Initiator***************************/
		
		/* Login to application as Tech */
		pages.techLoginLoginPage.loginToApplicationAsTech(techEmpCode, techLoginCode, techPassword);

		/* Click on initiator link from role list */
		pages.techLoginManageRolesPage.selectRoles(prop_constants.getProperty("role_Initiator"));

		/* Validate Tech login initiator home page */
		pages.techLoginInitiatorHomePage.validateInitiatorHomePage();

		/* Click on initiator view link from initiator drop down */
		pages.techLoginInitiatorHomePage.clkLinkInHeaderDD(prop_constants.getProperty("initiator"),
				prop_constants.getProperty("initiator_view"));

		/* Validate initiator view Page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Set BGV Id text field */
		pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

		/* Select record status from drop down */
		pages.techLoginInitiatorViewPage
				.selectRecordStatus(prop_constants.getProperty("submitted_by_Recruiter_Pending_with_Initiator_Dd"));

		/* Click on search button */
		pages.techLoginInitiatorViewPage.clkSearchButton();

		/* Verify search BGV Id is displayed */
		pages.techLoginInitiatorViewPage.verifySearchedBgvIdIsDisplayed();

		parentWindow = driver.getWindowHandle();

		/* Click viewOrEdit under BGV details tab */
		pages.techLoginInitiatorViewPage.clkViewOrEditLink();

		/* Select QC team remarks and select any reason for the drop down */
		pages.techLoginInitiatorViewPage.selectQcTeamRemark(prop_constants.getProperty("subjective_Hiring_Case_Dd"));

		/* Click on submit reason button */
		pages.techLoginInitiatorViewPage.clkQcTeamRemarkSubmitReasonButton();

		/* Validate alert pop up */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("alert_msg_Submitted_the_reason"));

		/* Switch to parent window */
		pages.techLoginInitiatorViewPage.switchParentWindow(parentWindow);

		/* Click on select check box under the select column */
		pages.techLoginInitiatorViewPage.clkCheckBoxUnderSelectColumn();

		/* Click on mark the check box in case of TP/RE */
		pages.techLoginInitiatorViewPage.clkMarkTheCheckBoxInCaseOfTPREBandAndGeoEntityWhileClosingTheCaseCheckBox();

		/* Click on submit to vendor */
		pages.techLoginInitiatorViewPage.clkSubmitToVendorButton();

		/* Click on alert pop up ok button */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("selected_record(s)_will_be_submitted"));

		/* wait For invisibility Of spinner */
		pages.techLoginInitiatorViewPage.waitForInvisibilityOfSpinner();

		/* Validate alert Popup and Click on Ok */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("record_send_to_Vendor_Successfully"));

		/* Validate initiator view Page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* validate employee details should be disappear */
		pages.techLoginInitiatorViewPage.verifyEmployeeDetailIsDisappeared();

		/************* Vendor Page ***************/

		/* Login as vendor */
		pages.vendorLoginPage.loginToApplicationAsVendor(vendorUserId, vendorPassword);

		/* Click on view tab */
		pages.vendorTPAHomePage.clkView();

		/* Validate TPA view page */
		pages.vendorTPAViewPage.validateVendorTPAViewPage();

		/* Set BGV Id text field */
		pages.vendorTPAViewPage.setBgvId(bgvId);

		/* Click on search button */
		pages.vendorTPAViewPage.clkSearch();

		/* Validate searched record displayed */
		pages.vendorTPAViewPage.verifySearchedBgvIdIsDisplayed(bgvId);

		parentWindow = driver.getWindowHandle();

		/* Click on View/Edit button */
		pages.vendorTPAViewPage.clkEditOrUpdateLink();

		/* Validate vendor login background check page */
		pages.vendorLoginBackgroundCheckPage.validateBackGroundCheckPage();

		/* Click '+' symbol of enter duration for criminal court,address and employment */
		pages.vendorLoginBackgroundCheckPage.clkImagePluseEnterDurationForCriminalCourt(
				prop_constants.getProperty("CatagoryCriminalCourt"), prop_constants.getProperty("color_green"),
				fromDate, toDate);

		/* Click on '+' symbol of required checks::document specification */
		pages.vendorLoginBackgroundCheckPage.clkImagePluseDocumentSpecification(
				prop_constants.getProperty("educationType1-10"), prop_constants.getProperty("color_green"));

		/* Upload file for send communication/upload verification report */
		pages.vendorLoginBackgroundCheckPage
				.uploadFileAndValidate(actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_pdf")));

		/* Click on send communication final report radio button */
		pages.vendorLoginBackgroundCheckPage.clkSendCommunicationRadioButton("Final Report");

		/* Set values in comments/remark text field */
		pages.vendorLoginBackgroundCheckPage.setComment(prop_constants.getProperty("comment_Ok"));

		/* Click on submit to BGV team button */
		pages.vendorLoginBackgroundCheckPage.clkSubmitToBGVTeamBtn();

		/* Validate alert pop up */
		pages.vendorLoginBackgroundCheckPage
				.clkAlertPopUpOkBtn(prop_constants.getProperty("alert_Msg_Unselect_checks_will_be_mark_as_NA"));

		/* Wait for invisibility Of spinner */
		pages.vendorLoginBackgroundCheckPage.waitForInvisibilityOfSpinner();

		/* Validate alert pop up */
		pages.vendorLoginBackgroundCheckPage.clkAlertPopUpOkBtn(
				prop_constants.getProperty("header_verificationReporthasbeensubmittedsuccessfully"));

		/* Verify the employee details disappeared */
		pages.vendorTPAViewPage.verifyEmployeeDetailIsDisappeared(bgvId, parentWindow);

		/****************** Tech Login *****************/

		/* Login to application as a Tech */
		pages.techLoginLoginPage.loginToApplicationAsTech(techEmpCode, techLoginCode, techPassword);

		/* Select manage role */
		pages.techLoginManageRolesPage.selectRoles(prop_constants.getProperty("role_Initiator"));

		/* Validate Tech login initiator home Page */
		pages.techLoginInitiatorHomePage.validateInitiatorHomePage();

		/* Click on initiator view page */
		pages.techLoginInitiatorHomePage.clkLinkInHeaderDD(prop_constants.getProperty("initiator"),
				prop_constants.getProperty("initiator_view"));

		/* Validate Tech login initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Enter the BGV id in BGV id text field */
		pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

		/* Select record status submitted by vendor pending with initiator */
		pages.techLoginInitiatorViewPage
				.selectRecordStatus(prop_constants.getProperty("submitted_By_Vendor_Pending_With_Initiator_Dd"));

		/* Click on search button */
		pages.techLoginInitiatorViewPage.clkSearchButton();

		/* Validate BGV id displayed in table */
		pages.techLoginInitiatorViewPage.verifySearchedBgvIdIsDisplayed();

		parentWindow = driver.getWindowHandle();

		/* Click on View/Edit link */
		pages.techLoginInitiatorViewPage.clkViewOrEditLink();

		/* Click on verify checks button */
		pages.techLoginInitiatorViewPage.clkVerifyChecksButton();

		/* Validate alert pop Up */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("alert_msg_document_has_been_Verified"));

		/* Close the previous window */
		pages.techLoginInitiatorViewPage.clkCloseButton(parentWindow);

		/* Validating initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Enter the BGV id in BGV id text field */
		pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

		/* Select record status submitted by vendor pending with initiator */
		pages.techLoginInitiatorViewPage
				.selectRecordStatus(prop_constants.getProperty("submitted_By_Vendor_Pending_With_Initiator_Dd"));

		/* Click on search button */
		pages.techLoginInitiatorViewPage.clkSearchButton();

		/* Enter transaction remark */
		pages.techLoginInitiatorViewPage.setTransactionRemarkTextField(prop_constants.getProperty("remark"));

		/* Click on select check box under select column */
		pages.techLoginInitiatorViewPage.clkCheckBoxUnderSelectColumn();

		/* Click on mark check box */
		pages.techLoginInitiatorViewPage.clkMarkTheCheckBoxInCaseOfTPREBandAndGeoEntityWhileClosingTheCaseCheckBox();

		/* Click on close case button */
		pages.techLoginInitiatorViewPage.clkCloseCaseButton();

		/* Click on alert pop up ok button */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("selected_record(s)_will_be_submitted"));

		/* Wait for invisibility Of spinner */
		pages.techLoginInitiatorViewPage.waitForInvisibilityOfSpinner();

		/* Click on alert pop up ok button */
		pages.techLoginInitiatorViewPage.validateAlertPopup(
				prop_constants.getProperty("alert_msg_bgv_for_the_selected_employees_has_been_closed."));

		/* Validate initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Validate employee details is disappeared */
		pages.techLoginInitiatorViewPage.validateEmployeeDetailIsDisappeared();

	}
}
