package com.hcl.sv.testscripts;

import org.testng.annotations.Test;

import com.hcl.sv.baseutil.BaseTest;
import com.hcl.sv.dataprovider.DataProviderFactory;
import com.hcl.sv.dataprovider.DataProviderFileRowFilter;

/*
 *TestCaseId: TC_SV_PreJoining_003
 *TestScript Name: TYSS_SV_PREJ_003
 *Description: This test case is to verify the Candidate is able to submit the documents to Recruiter,
 *Recruiter is able to send back documents to Candidate, Candidate is able to upload the documents again and
 *submit to Recruiter.
 * 
 *@Author : SreeLatha
 * 
 */
public class TYSS_SV_PREJ_003 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/Data.xlsx", sql = "Select * from SV_Prejoining where SlNo ='TC_SV_PreJoining_003'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: verify that  Recruiter will Refer back flow from recruiter to candidate")

	public synchronized void TC_TYSS_SV_PREJ_003(String SINO, String candidateUserId, String candidatePassword,
			String techEmpCode, String techLoginCode, String techPassword, String vendorUserId, String vendorPassword,
			String bgvId, String fromDate, String toDate) {

		/**************************** Candidate Login*******************************/

		/* Login to application as Candidate */
		pages.candidateLoginPage.loginToApplication(candidateUserId, candidatePassword);

		/* Validate Home Page */
		pages.candidateHomePage.validateCandidateHomePage();

		/* Click on Graduation sub type Upload link */
		pages.candidateHomePage.uploadFileforGraduation(prop_constants.getProperty("document_Sub_Type_Graduation"),
				prop_constants.getProperty("header_uploadMultipleDocuments"),
				actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_jpg")),
				prop_constants.getProperty("descriptionTesting"),
				prop_constants.getProperty("uploadMessage_DocumentUploadedSuccessfully"),
				prop_constants.getProperty("header_areYouSureYouWantToCloseWindow"),
				prop_constants.getProperty("fileFormat_jpg"));

		/* Click on Intermediate sub type Upload link */
		pages.candidateHomePage.uploadFileForIntermediate(
				prop_constants.getProperty("document_Sub_Type_Intermediate12thStandard"),
				prop_constants.getProperty("header_uploadMultipleDocuments"),
				actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_jpeg")),
				prop_constants.getProperty("descriptionTesting"),
				prop_constants.getProperty("uploadMessage_DocumentUploadedSuccessfully"),
				prop_constants.getProperty("header_areYouSureYouWantToCloseWindow"),
				prop_constants.getProperty("fileFormat_jpeg"));

		/* Click on Second Last Employer sub type Upload link */
		pages.candidateHomePage.uploadFileforSecondLastEmpolyee(
				prop_constants.getProperty("document_Sub_Type_SecondLastEmployer"),
				prop_constants.getProperty("header_uploadMultipleDocuments"),
				actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_png")),
				prop_constants.getProperty("descriptionTesting"),
				prop_constants.getProperty("uploadMessage_DocumentUploadedSuccessfully"),
				prop_constants.getProperty("header_areYouSureYouWantToCloseWindow"),
				prop_constants.getProperty("fileFormat_png"));

		/* Click on Submit button */
		pages.candidateHomePage.clkSubmitButton();

		/* Validating submit alert pop up and click on OK button in pop up */
		pages.candidateHomePage
				.validateSubmitAlertPopup(prop_constants.getProperty("header_doYouReallyWantToSubmitTheDocument"));

		/* Validate Form Submitted Successfully message */
		pages.candidateHomePage.validateFormSubmittedSuccessfullyMessage();

		/* Validate Candidate Home page */
		pages.candidateHomePage.validateCandidateHomePage();

		/********************* Tech Login *************************/

		/* Login to Application as a Tech */
		pages.techLoginLoginPage.loginToApplicationAsTech(techEmpCode, techLoginCode, techPassword);

		/* Select Manager Role */
		pages.techLoginManageRolesPage.selectRoles(prop_constants.getProperty("role_Recruiter"));

		/* Validate Recruiter Home page */
		pages.techLoginRecruiterHomePage.validateRecruiterHomePage();

		/* Click on Recruiter View */
		pages.techLoginRecruiterHomePage.clkLinkInHeader(prop_constants.getProperty("recruiter_View"));

		/* Validate Recruiter View page */
		pages.techLoginRecruiterViewPage.validateRecruiterViewPage();

		/* Enter BGV Id in BGV Id Text field */
		pages.techLoginRecruiterViewPage.setBgvIdTextField(bgvId);

		/* Click on Record Status and select Document uploaded pending with Recruiter */
		pages.techLoginRecruiterViewPage
				.selectRecordStatus(prop_constants.getProperty("document_uploaded_Pending_With_Recruiter_Dd"));

		/* Click on Search button */
		pages.techLoginRecruiterViewPage.clkSearchButton();

		/* Verify search BGV Id displayed */
		pages.techLoginRecruiterViewPage.verifySearchedBgvIdIsDisplayed(bgvId);

		String parentWindow = driver.getWindowHandle();

		/* Click on View Link under BGV details */
		pages.techLoginRecruiterViewPage.clkViewLink();

		/* Validate View Page */
		pages.techLoginRecruiterRoleViewPage.validateManageBackgroundChecksOfRecruiterViewPage();

		String[] description = { prop_constants.getProperty("descriptionTesting"),
				prop_constants.getProperty("descriptionTesting"), prop_constants.getProperty("descriptionTesting") };
		String[] graduation_Doc = { prop_constants.getProperty("document_Sub_Type_Graduation"),
				prop_constants.getProperty("document_Sub_Type_Intermediate12thStandard"),
				prop_constants.getProperty("document_Sub_Type_SecondLastEmployer") };

		/* Verify All Documents Uploaded */
		pages.techLoginRecruiterRoleViewPage.verifyThatAllTheDocumentsAreUploaded(graduation_Doc, description);

		/* Click on Graduation Document Check Box */
		pages.techLoginRecruiterRoleViewPage.clkInsufficientCheckBox(
				prop_constants.getProperty("document_Sub_Type_Graduation"),
				prop_constants.getProperty("descriptionTesting"));

		/* Enter Remarks for Graduation Remarks text field */
		pages.techLoginRecruiterRoleViewPage.setRemarkField(prop_constants.getProperty("document_Sub_Type_Graduation"),
				prop_constants.getProperty("descriptionTesting"), prop_constants.getProperty("comment_Ok"));

		/* Click on mark insuffiency button */
		pages.techLoginRecruiterRoleViewPage.clkMarkInsuffiencyButton();

		/* Validate Alert Pop up and Click On OK button */
		pages.techLoginRecruiterRoleViewPage.validateAlertPopup(
				prop_constants.getProperty("alert_msg_Selected_record(s)_will_be_marked_for_Insufficiency"));

		/* Wait for Invisibility of Spinner */
		pages.techLoginRecruiterRoleViewPage.waitForInvisibilityOfSpinner();

		/* Validate Alert Pop up and Click On OK button */
		pages.techLoginRecruiterRoleViewPage.validateAlertPopup(
				prop_constants.getProperty("alert_msg_Document(s)_has_been_marked_for_insufficiency"));

		/* Close the Window Previously Opened */
		pages.techLoginRecruiterRoleViewPage.clkCloseButton(parentWindow);

		/* Validate Tech Login Recruiter View page */
		pages.techLoginRecruiterViewPage.validateRecruiterViewPage();

		/* Enter OK in Transaction Remark Box */
		pages.techLoginRecruiterViewPage.setTransactionRemarkTextField(prop_constants.getProperty("comment_Ok"));

		/* Click on Select Check Box Under Select Column */
		pages.techLoginRecruiterViewPage.clkCheckBoxUnderSelectColumn();

		/* Select I am Confirming Check Box */
		pages.techLoginRecruiterViewPage
				.clkIAmConfirmingThatIVerifiedTheDocumentsSubmittedByTheCandidateAgainstTheMRLListCheckBox();

		/* Click on Send To Candidate button */
		pages.techLoginRecruiterViewPage.clkSendToCandidateButton();

		/* Validate Alert pop up and click on Ok button */
		pages.techLoginRecruiterViewPage.validateAlertPopup(prop_constants.getProperty(
				"it_is_assumed_that_you_have_validated_all_the_documents_uploaded_by_the_candidate_against_the_MRL_Fake/Suspicious_list__Alert_Msg"));

		/* Wait For Invisibility Of Spinner */
		pages.techLoginRecruiterViewPage.waitForInvisibilityOfSpinner();

		/* Validate Alert Pop up and Click On OK button */
		pages.techLoginRecruiterViewPage.validateAlertPopup(
				prop_constants.getProperty("alert_msg_BGV_for_the_selected_employees_has_been_send_to_Candidate"));

		/* Validate Recruiter View page */
		pages.techLoginRecruiterViewPage.validateRecruiterViewPage();

		/************************ Candidate Login *************************/

		/* Login to Application as a Candidate */
		pages.candidateLoginPage.loginToApplication(candidateUserId, candidatePassword);

		/* Validating Home page */
		pages.candidateHomePage.validateCandidateHomePage();

		/* Upload File for Graduation */
		pages.candidateHomePage.clkUploadLinkToUploadDesiredDocumentSubTypeForSecondCadiadteLogin(
				prop_constants.getProperty("document_Sub_Type_Graduation"),
				prop_constants.getProperty("header_uploadMultipleDocuments"),
				actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_png")),
				prop_constants.getProperty("descriptionTesting"),
				prop_constants.getProperty("uploadMessage_DocumentUploadedSuccessfully"),
				prop_constants.getProperty("fileFormat_png"),
				prop_constants.getProperty("header_areYouSureYouWantToCloseWindow"),
				prop_constants.getProperty("header_doYouReallyWantToSubmitTheDocument"));

		/* Validate Candidate Home page */
		pages.candidateHomePage.validateCandidateHomePage();

		/************************* Tech Login **************************/

		/* Login to Application as a Tech */
		pages.techLoginLoginPage.loginToApplicationAsTech(techEmpCode, techLoginCode, techPassword);

		/* Select Manage Role */
		pages.techLoginManageRolesPage.selectRoles(prop_constants.getProperty("role_Initiator"));

		/* Validate Tech Login Initiator Home page */
		pages.techLoginInitiatorHomePage.validateInitiatorHomePage();

		/* Click on Initiator View page */
		pages.techLoginInitiatorHomePage.clkLinkInHeaderDD(prop_constants.getProperty("initiator"),
				prop_constants.getProperty("initiator_view"));

		/* Validate Tech Login Initiator View page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Enter the BGV Id in BGV Id text field */
		pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

		/* Select Record Status submitted by vendor pending with initiator */
		pages.techLoginInitiatorViewPage
				.selectRecordStatus(prop_constants.getProperty("document_Uploaded_Pending_with_Initiator_Dd"));

		/* Click on Search button */
		pages.techLoginInitiatorViewPage.clkSearchButton();

		/* Validate Searched BGV Id displayed */
		pages.techLoginInitiatorViewPage.verifySearchedBgvIdIsDisplayed();

		parentWindow = driver.getWindowHandle();

		/* Click on View/Edit link */
		pages.techLoginInitiatorViewPage.clkViewOrEditLink();

		/* Enter Remarks from the dropDown under QC team Remarks */
		pages.techLoginInitiatorViewPage.selectQcTeamRemark(prop_constants.getProperty("missing_Check_Dd"));

		/* Click on Submit reason */
		pages.techLoginInitiatorViewPage.clkQcTeamRemarkSubmitReasonButton();

		/* Validate Alert Pop Up and click on OK button */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("alert_msg_Submitted_the_reason"));

		/* Switch the window */
		pages.techLoginInitiatorViewPage.switchParentWindow(parentWindow);
			
		/* Click on Check Box Under Select button */
		pages.techLoginInitiatorViewPage.clkCheckBoxUnderSelectColumn();

		/* Click on Mark the Check box in case of TP/RE Band and GeoEntity */
		pages.techLoginInitiatorViewPage.clkMarkTheCheckBoxInCaseOfTPREBandAndGeoEntityWhileClosingTheCaseCheckBox();

		/* Click on Submit To Vendor button */
		pages.techLoginInitiatorViewPage.clkSubmitToVendorButton();

		/* Validate Alert Pop Up */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("selected_record(s)_will_be_submitted"));

		/* Wait For Invisibility of Spinner */
		pages.techLoginRecruiterViewPage.waitForInvisibilityOfSpinner();

		/* Validate Alert Pop up */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("record_send_to_Vendor_Successfully"));

		/* Validate Initiator View page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Verify Employee details is disappeared */
		pages.techLoginInitiatorViewPage.verifyEmployeeDetailIsDisappeared();

		/************************** Vendor Login *************************/

		/* Login to Application as a Vendor */
		pages.vendorLoginPage.loginToApplicationAsVendor(vendorUserId, vendorPassword);

		/* Click on View link */
		pages.vendorTPAHomePage.clkView();

		/* Validating view page */
		pages.vendorTPAViewPage.validateVendorTPAViewPage();

		/* Enter BGV Id of the Employee */
		pages.vendorTPAViewPage.setBgvId(bgvId);

		/* Click on Search Button */
		pages.vendorTPAViewPage.clkSearch();

		/* Validate Searched BGV Id is displayed */
		pages.vendorTPAViewPage.verifySearchedBgvIdIsDisplayed(bgvId);

		parentWindow = driver.getWindowHandle();

		/* Click on Edit/Update Link Under Action tab */
		pages.vendorTPAViewPage.clkEditOrUpdateLink();

		/* Validate BackGround Check page */
		pages.vendorLoginBackgroundCheckPage.validateBackGroundCheckPage();
		
		/* Click on Background Check box */
		pages.vendorLoginBackgroundCheckPage.clkBackgroundCheckbox("Graduation");
		
		/* Click '+' Symbol of Enter duration for Criminal Court */
		pages.vendorLoginBackgroundCheckPage.clkImagePluseEnterDurationForCriminalCourt(
				prop_constants.getProperty("CatagoryCriminalCourt"), prop_constants.getProperty("color_green"),
				fromDate, toDate);

		/* Click on '+' Symbol in Required Checks Document Specification */
		pages.vendorLoginBackgroundCheckPage.clkImagePluseDocumentSpecification(
				prop_constants.getProperty("educationType1-10"), prop_constants.getProperty("color_green"));

		/* Upload File under Send Communication : Upload verification report */
		pages.vendorLoginBackgroundCheckPage.uploadFileAndValidate(actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_pdf")));

		/* Click on Send Communication Final Report Radio button */
		pages.vendorLoginBackgroundCheckPage
				.clkSendCommunicationRadioButton(prop_constants.getProperty("rdo_Final_Report"));

		/* Enter Remarks Text Field */
		pages.vendorLoginBackgroundCheckPage.setComment(prop_constants.getProperty("comment_Ok"));

		/* Click on Submit to BGV team */
		pages.vendorLoginBackgroundCheckPage.clkSubmitToBGVTeamBtn();

		/* Validate Alert PopUp and click on Ok */
		pages.vendorLoginBackgroundCheckPage
				.clkAlertPopUpOkBtn(prop_constants.getProperty("alert_Msg_Unselect_checks_will_be_mark_as_NA"));

		/* Wait For Invisibility Of Spinner */
		pages.vendorLoginBackgroundCheckPage.waitForInvisibilityOfSpinner();

		/* Validate Alert Pop up and Click on OK button */
		pages.vendorLoginBackgroundCheckPage.clkAlertPopUpOkBtn(
				prop_constants.getProperty("header_verificationReporthasbeensubmittedsuccessfully"));

		/* Verify the Employee details disappeared */
		pages.vendorTPAViewPage.verifyEmployeeDetailIsDisappeared(bgvId, parentWindow);

		/********************** Tech Login ************************/

		/* Login to Application as a Tech */
		pages.techLoginLoginPage.loginToApplicationAsTech(techEmpCode, techLoginCode, techPassword);

		/* Select Manage Role */
		pages.techLoginManageRolesPage.selectRoles(prop_constants.getProperty("role_Initiator"));

		/* Validate Tech Login Initiator Home page */
		pages.techLoginInitiatorHomePage.validateInitiatorHomePage();

		/* Click on Initiator View page */
		pages.techLoginInitiatorHomePage.clkLinkInHeaderDD(prop_constants.getProperty("initiator"),
				prop_constants.getProperty("initiator_view"));

		/* Validate Tech Login Initiator View page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Enter the BGV Id in BGV Id text field */
		pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

		/* Select Record Status submitted by vendor pending with initiator */
		pages.techLoginInitiatorViewPage
				.selectRecordStatus(prop_constants.getProperty("submitted_By_Vendor_Pending_With_Initiator_Dd"));

		/* Click on Search button */
		pages.techLoginInitiatorViewPage.clkSearchButton();

		/* Validate Searched BGV Id displayed */
		pages.techLoginInitiatorViewPage.verifySearchedBgvIdIsDisplayed();

		parentWindow = driver.getWindowHandle();

		/* Click on View/Edit link */
		pages.techLoginInitiatorViewPage.clkViewOrEditLink();

		/* Click on Verify Checks button */
		pages.techLoginInitiatorViewPage.clkVerifyChecksButton();

		/* Validate Alert Pop Up and click on OK button */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("alert_msg_document_has_been_Verified"));

		/* Close the window Previously opened */
		pages.techLoginInitiatorViewPage.clkCloseButton(parentWindow);

		/* Validating Initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();
		
		/* Enter the BGV Id in BGV Id text field */
		pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

		/* Select Record Status submitted by vendor pending with initiator */
		pages.techLoginInitiatorViewPage
				.selectRecordStatus(prop_constants.getProperty("submitted_By_Vendor_Pending_With_Initiator_Dd"));

		/* Click on Search button */
		pages.techLoginInitiatorViewPage.clkSearchButton();

		/* Enter Transaction Remark */
		pages.techLoginInitiatorViewPage.setTransactionRemarkTextField(prop_constants.getProperty("comment_Ok"));

		/* Click on Check Box Under Select button */
		pages.techLoginInitiatorViewPage.clkCheckBoxUnderSelectColumn();

		/* Click on Mark the Check box in case of TP/RE Band and GeoEntity */
		pages.techLoginInitiatorViewPage.clkMarkTheCheckBoxInCaseOfTPREBandAndGeoEntityWhileClosingTheCaseCheckBox();

		/* Click on Close Case button */
		pages.techLoginInitiatorViewPage.clkCloseCaseButton();

		/* Click on Alert pop up ok button */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("selected_record(s)_will_be_submitted"));

		/* Wait For Invisibility Of Spinner */
		pages.techLoginInitiatorViewPage.waitForInvisibilityOfSpinner();

		/* Click on Alert pop up ok button */
		pages.techLoginInitiatorViewPage.validateAlertPopup(
				prop_constants.getProperty("alert_msg_bgv_for_the_selected_employees_has_been_closed."));
	
		/* Validating Initiator View page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Verify Employee Details is disappeared */
		pages.techLoginInitiatorViewPage.validateEmployeeDetailIsDisappeared();
	}
}
