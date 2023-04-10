package com.hcl.sv.testscripts;

import org.testng.annotations.Test;

import com.hcl.sv.baseutil.BaseTest;
import com.hcl.sv.dataprovider.DataProviderFactory;
import com.hcl.sv.dataprovider.DataProviderFileRowFilter;

/*
 *TestCaseId: TC_SV_PreJoining_004
 *TestScript Name: TYSS_SV_PREJ_004
 *Description: Verify The Candidate is able to submit the documents to Recruiter,Recruiter is able to send back documents to Candidate,
 *Recruiter is able to send a documents to Vendor, 
 *Vendor is able to verify the documents and submit to the BGV,
 *Initiator is able to send back the documents to Vendor to verify,
 *Vendor is able to verify the documents and submit to the BGV,
 *Initiator is able to verify all the documents and close the case.
 * 
 *@Author : Sanjay Kumar
 *  
 */
public class TYSS_SV_PREJ_004 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/Data.xlsx", sql = "Select * from SV_Prejoining where SlNo ='TC_SV_PreJoining_004'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,
	description = "Verify The Candidate is able to submit the documents to Recruiter,Recruiter is able to send back documents to Candidate,Recruiter is able to send a documents to Vendor,  Vendor is able to verify the documents and submit to the BGV, Initiator is able to send back the documents to Vendor to verify, Vendor is able to verify the documents and submit to the BGV,Initiator is able to verify all the documents and close the case.")

	public void TC_TYSS_SV_PREJ_004(String SINO, String candidateUserId, String candidatePassword, String techEmpCode,
			String techLoginCode, String techPassword, String vendorUserId, String vendorPassword, String bgvId,
			String fromDate, String toDate) {

		/********** Candidate Login**********/

		/* Login as Candidate */
		pages.candidateLoginPage.loginToApplication(candidateUserId, candidatePassword);

		/* Validate Home page */
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

		/* Click on submit button */
		pages.candidateHomePage.clkSubmitButton();

		/* Validate submit Alert popup and click on ok button in popup */
		pages.candidateHomePage
				.validateSubmitAlertPopup(prop_constants.getProperty("header_doYouReallyWantToSubmitTheDocument"));

		/* Validate Form Submitted Successfully Message */
		pages.candidateHomePage.validateFormSubmittedSuccessfullyMessage();

		/* Validate Candidate Home Page */
		pages.candidateHomePage.validateCandidateHomePage();

		/******* Tech Login Recruiter ******/

		/* Login to Application as a Tech */
		pages.techLoginLoginPage.loginToApplicationAsTech(techEmpCode, techLoginCode, techPassword);

		/* Select Manage Role */
		pages.techLoginManageRolesPage.selectRoles(prop_constants.getProperty("role_Recruiter"));

		/* Validate Recruiter Home page */
		pages.techLoginRecruiterHomePage.validateRecruiterHomePage();

		/* Click on Recruiter View */
		pages.techLoginRecruiterHomePage.clkLinkInHeader(prop_constants.getProperty("recruiter_View"));

		/* Validate Recruiter View page */
		pages.techLoginRecruiterViewPage.validateRecruiterViewPage();

		/* Enter BGV Id in BGV Text field */
		pages.techLoginRecruiterViewPage.setBgvIdTextField(bgvId);

		/* Click on Record Status and select Document upload pending with Recruiter */
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
		String[] documents = { prop_constants.getProperty("document_Sub_Type_Graduation"),
				prop_constants.getProperty("document_Sub_Type_Intermediate12thStandard"),
				prop_constants.getProperty("document_Sub_Type_SecondLastEmployer") };

		/* Verify all Documents uploaded */
		pages.techLoginRecruiterRoleViewPage.verifyThatAllTheDocumentsAreUploaded(documents, description);

		/* Close the Window */
		pages.techLoginRecruiterRoleViewPage.clkCloseButton(parentWindow);

		/* Validate techLogin Recruiter View page */
		pages.techLoginRecruiterViewPage.validateRecruiterViewPage();

		/* Select HCL Standard 2 Option from package drop down */
		pages.techLoginRecruiterViewPage.selectPackage(prop_constants.getProperty("HCL_Standard2_Dd"));

		/* Set Transaction Remarks text field */
		pages.techLoginRecruiterViewPage.setTransactionRemarkTextField(prop_constants.getProperty("comment_Ok"));

		/* Click on Select Check Box Under Select Column */
		pages.techLoginRecruiterViewPage.clkCheckBoxUnderSelectColumn();

		/* Select I am Confirming Check Box */
		pages.techLoginRecruiterViewPage
				.clkIAmConfirmingThatIVerifiedTheDocumentsSubmittedByTheCandidateAgainstTheMRLListCheckBox();

		/* Click on Submit to BGV Team Button */
		pages.techLoginRecruiterViewPage.clkSubmitToBgvTeamButton();

		/* Validate Alert pop up and click on Ok button */
		pages.techLoginRecruiterViewPage.validateAlertPopup(prop_constants.getProperty(
				"it_is_assumed_that_you_have_validated_all_the_documents_uploaded_by_the_candidate_against_the_MRL_Fake/Suspicious_list__Alert_Msg"));

		/* Wait for Invisibility of Spinner */
		pages.techLoginRecruiterViewPage.waitForInvisibilityOfSpinner();

		/* Click On Ok button */
		pages.techLoginRecruiterViewPage.validateAlertPopup(
				prop_constants.getProperty("bgv_for_the_selected_employees_has_been_send_to_BGV_Team_Alert_Msg"));

		/* Validating recruiter view page */
		pages.techLoginRecruiterViewPage.validateRecruiterViewPage();

		/* Validate Employee details should be disappeared */
		pages.techLoginRecruiterViewPage.verifyEmployeeDetailIsDisappeared(bgvId);

		/*********************** Tech Login Initiator***************************/

		/* Login to Application as Tech */
		pages.techLoginLoginPage.loginToApplicationAsTech(techEmpCode, techLoginCode, techPassword);

		/* Click on Initiator link from role list */
		pages.techLoginManageRolesPage.selectRoles(prop_constants.getProperty("role_Initiator"));

		/* Validate Tech Login Initiator Home Page */
		pages.techLoginInitiatorHomePage.validateInitiatorHomePage();

		/* Click on Initiator view from Initiator drop down */
		pages.techLoginInitiatorHomePage.clkLinkInHeaderDD(prop_constants.getProperty("initiator"),
				prop_constants.getProperty("initiator_view"));

		/* Validate Initiator View Page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Set Bgv Id Text field */
		pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

		/* Select Record Status from Drop down */
		pages.techLoginInitiatorViewPage
				.selectRecordStatus(prop_constants.getProperty("submitted_by_Recruiter_Pending_with_Initiator_Dd"));

		/* Click on Search Button */
		pages.techLoginInitiatorViewPage.clkSearchButton();

		/* Verify Search Bgv Id is displayed */
		pages.techLoginInitiatorViewPage.verifySearchedBgvIdIsDisplayed();

		parentWindow = driver.getWindowHandle();

		/* Click ViewOrEdit Link Under BGV Details Tab */
		pages.techLoginInitiatorViewPage.clkViewOrEditLink();

		/* Select QC team remarks and select any reason for the Drop down */
		pages.techLoginInitiatorViewPage.selectQcTeamRemark(prop_constants.getProperty("subjective_Hiring_Case_Dd"));

		/* Click on Submit Reason */
		pages.techLoginInitiatorViewPage.clkQcTeamRemarkSubmitReasonButton();

		/* Validate Alert Popup "Reason Submitted Successfully" */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("alert_msg_Submitted_the_reason"));

		/* Switch to Parent Window */
		pages.techLoginInitiatorViewPage.switchParentWindow(parentWindow);

		/* Click on Select check box under the select column */
		pages.techLoginInitiatorViewPage.clkCheckBoxUnderSelectColumn();

		/* Click on Mark the checkbox in case of TP/RE Band */
		pages.techLoginInitiatorViewPage.clkMarkTheCheckBoxInCaseOfTPREBandAndGeoEntityWhileClosingTheCaseCheckBox();

		/* Click on Submit To Vendor button */
		pages.techLoginInitiatorViewPage.clkSubmitToVendorButton();

		/* Click on Alert pop up ok button */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("selected_record(s)_will_be_submitted"));

		/* Wait For Invisibility Of Spinner */
		pages.techLoginInitiatorViewPage.waitForInvisibilityOfSpinner();

		/* Validate AlertPopup and click on Ok */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("record_send_to_Vendor_Successfully"));

		/* Validate Tech Login Initiator View Page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Validate Employee details should be disappear */
		pages.techLoginInitiatorViewPage.verifyEmployeeDetailIsDisappeared();

		/********* Vendor Login *******/

		/* Login As Vendor */
		pages.vendorLoginPage.loginToApplicationAsVendor(vendorUserId, vendorPassword);

		/* Click on View Tab */
		pages.vendorTPAHomePage.clkView();

		/* Validate TPA View page */
		pages.vendorTPAViewPage.validateVendorTPAViewPage();

		/* Set BGV ID text field */
		pages.vendorTPAViewPage.setBgvId(bgvId);

		/* Click on Search button */
		pages.vendorTPAViewPage.clkSearch();

		/* Validate Searched record displayed */
		pages.vendorTPAViewPage.verifySearchedBgvIdIsDisplayed(bgvId);

		parentWindow = driver.getWindowHandle();

		/* Click on Edit/Update Link */
		pages.vendorTPAViewPage.clkEditOrUpdateLink();

		/* Validate Vendor Login BackgroundCheck page */
		pages.vendorLoginBackgroundCheckPage.validateBackGroundCheckPage();

		/* Click '+' Symbol of Enter duration for Criminal Court  */
		pages.vendorLoginBackgroundCheckPage.clkImagePluseEnterDurationForCriminalCourt(
				prop_constants.getProperty("CatagoryCriminalCourt"), prop_constants.getProperty("color_green"),
				fromDate, toDate);

		/* Click on '+' Symbol of Required Checks::Document Specification */
		pages.vendorLoginBackgroundCheckPage.clkImagePluseDocumentSpecification(
				prop_constants.getProperty("educationType1-10"), prop_constants.getProperty("color_green"));

		/* Upload file for Send Communication/Upload verification report */
		pages.vendorLoginBackgroundCheckPage
				.uploadFileAndValidate(actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_pdf")));

		/* Click on Send Communication Final report Radio button */
		pages.vendorLoginBackgroundCheckPage
				.clkSendCommunicationRadioButton(prop_constants.getProperty("rdo_Final_Report"));

		/* Set Comments/Remark text field */
		pages.vendorLoginBackgroundCheckPage.setComment(prop_constants.getProperty("comment_Ok"));

		/* Click on Submit to BGV team button */
		pages.vendorLoginBackgroundCheckPage.clkSubmitToBGVTeamBtn();

		/* Validate Alert Popup */
		pages.vendorLoginBackgroundCheckPage
				.clkAlertPopUpOkBtn(prop_constants.getProperty("alert_Msg_Unselect_checks_will_be_mark_as_NA"));

		/* Wait For Invisibility Of Spinner */
		pages.vendorLoginBackgroundCheckPage.waitForInvisibilityOfSpinner();

		/* Validate Alert popup */
		pages.vendorLoginBackgroundCheckPage.clkAlertPopUpOkBtn(
				prop_constants.getProperty("header_verificationReporthasbeensubmittedsuccessfully"));

		/* Verify the Employee details disappeared */
		pages.vendorTPAViewPage.verifyEmployeeDetailIsDisappeared(bgvId, parentWindow);

		/*********** Tech Login Initiator ***********/

		/* Login as Tech */
		pages.techLoginLoginPage.loginToApplicationAsTech(techEmpCode, techLoginCode, techPassword);

		/* Click on Initiator Link From Role list */
		pages.techLoginManageRolesPage.selectRoles(prop_constants.getProperty("role_Initiator"));

		/* Validate Initiator Home page */
		pages.techLoginInitiatorHomePage.validateInitiatorHomePage();

		/* Click on Initiator View page */
		pages.techLoginInitiatorHomePage.clkLinkInHeaderDD(prop_constants.getProperty("initiator"),
				prop_constants.getProperty("initiator_view"));

		/* Validate Initiator View page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Select Record Status from drop down */
		pages.techLoginInitiatorViewPage
				.selectRecordStatus(prop_constants.getProperty("submitted_By_Vendor_Pending_With_Initiator_Dd"));

		/* Set Bgv Id text field */
		pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

		/* Click on Search button */
		pages.techLoginInitiatorViewPage.clkSearchButton();

		/* Verify Search Bgv Id is displayed */
		pages.techLoginInitiatorViewPage.verifySearchedBgvIdIsDisplayed();

		parentWindow = driver.getWindowHandle();

		/* Click ViewOrEdit link */
		pages.techLoginInitiatorViewPage.clkViewOrEditLink();

		/* Click on QC team remarks and select any reason for the drop down */
		pages.techLoginInitiatorViewPage.selectQcTeamRemark(prop_constants.getProperty("subjective_Hiring_Case_Dd"));

		/* Click on Submit reason */
		pages.techLoginInitiatorViewPage.clkQcTeamRemarkSubmitReasonButton();

		/* Validate The Alert popup */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("alert_msg_Submitted_the_reason"));

		/* Switch Back to Parent window */
		pages.techLoginInitiatorViewPage.switchParentWindow(parentWindow);

		/* Validate Initiator Home page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Click on Select checkBox under Select column */
		pages.techLoginInitiatorViewPage.clkCheckBoxUnderSelectColumn();

		/* Click on Mark checkBox */
		pages.techLoginInitiatorViewPage.clkMarkTheCheckBoxInCaseOfTPREBandAndGeoEntityWhileClosingTheCaseCheckBox();

		/* Click on Submit To Vendor */
		pages.techLoginInitiatorViewPage.clkSubmitToVendorButton();

		/* Click on Alert pop up ok button */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("selected_record(s)_will_be_submitted"));

		/* Wait For Invisibility Of Spinner */
		pages.techLoginInitiatorViewPage.waitForInvisibilityOfSpinner();

		/* Validate AlertPopup And Click on Ok */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("record_send_to_Vendor_Successfully"));

		/* Validate Tech Login Initiator View page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Validate Employee Details Should Be disappear */
		pages.techLoginInitiatorViewPage.verifyEmployeeDetailIsDisappeared();

		/******* Vendor Login ********************/

		/* Login As Vendor */
		pages.vendorLoginPage.loginToApplicationAsVendor(vendorUserId, vendorPassword);

		/* Click on View tab */
		pages.vendorTPAHomePage.clkView();

		/* Validate TPA View page */
		pages.vendorTPAViewPage.validateVendorTPAViewPage();

		/* Set BGV ID Text field */
		pages.vendorTPAViewPage.setBgvId(bgvId);

		/* Click on Search button */
		pages.vendorTPAViewPage.clkSearch();

		/* Validate Searched record displayed */
		pages.vendorTPAViewPage.verifySearchedBgvIdIsDisplayed(bgvId);

		parentWindow = driver.getWindowHandle();

		/* Click on View/Edit button */
		pages.vendorTPAViewPage.clkEditOrUpdateLink();

		/* Validate Vendor Login BackgroundCheck page */
		pages.vendorLoginBackgroundCheckPage.validateBackGroundCheckPage();

		/* Click '+' Symbol of Enter duration for Criminal Court */
		pages.vendorLoginBackgroundCheckPage.clkImagePluseEnterDurationForCriminalCourt(
				prop_constants.getProperty("CatagoryCriminalCourt"), prop_constants.getProperty("color_green"),
				fromDate, toDate);

		/* Click on '+' Symbol of Required Checks::Document Specification */
		pages.vendorLoginBackgroundCheckPage.clkImagePluseDocumentSpecification(
				prop_constants.getProperty("educationType1-10"), prop_constants.getProperty("color_green"));

		/* Upload file for Send Communication/Upload verification report */
		pages.vendorLoginBackgroundCheckPage
				.uploadFileAndValidate(actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_pdf")));

		/* Click on Send Communication Final report Radio button */
		pages.vendorLoginBackgroundCheckPage
				.clkSendCommunicationRadioButton(prop_constants.getProperty("rdo_Final_Report"));

		/* Set Comments/Remark text field */
		pages.vendorLoginBackgroundCheckPage.setComment(prop_constants.getProperty("comment_Ok"));

		/* Click on Submit to BGV team button */
		pages.vendorLoginBackgroundCheckPage.clkSubmitToBGVTeamBtn();

		/* Validate Alert Popup */
		pages.vendorLoginBackgroundCheckPage
				.clkAlertPopUpOkBtn(prop_constants.getProperty("alert_Msg_Unselect_checks_will_be_mark_as_NA"));

		/* Wait For Invisibility Of Spinner */
		pages.vendorLoginBackgroundCheckPage.waitForInvisibilityOfSpinner();

		/* Validate Alert popup */
		pages.vendorLoginBackgroundCheckPage.clkAlertPopUpOkBtn(
				prop_constants.getProperty("header_verificationReporthasbeensubmittedsuccessfully"));

		/* Verify the Employee details disappeared */
		pages.vendorTPAViewPage.verifyEmployeeDetailIsDisappeared(bgvId, parentWindow);

		/*********************** Tech Login Initiator******************/

		/* Login to Application as a Tech */
		pages.techLoginLoginPage.loginToApplicationAsTech(techEmpCode, techLoginCode, techPassword);

		/* Select Manage Role */
		pages.techLoginManageRolesPage.selectRoles(prop_constants.getProperty("role_Initiator"));

		/* Validate Tech Login Initiator Home page */
		pages.techLoginInitiatorHomePage.validateInitiatorHomePage();

		/* Click on initiator view page */
		pages.techLoginInitiatorHomePage.clkLinkInHeaderDD(prop_constants.getProperty("initiator"),
				prop_constants.getProperty("initiator_view"));

		/* Validate Tech Login Initiator View page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Enter the BGVId in BGVId text field */
		pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

		/* Select record status submitted by vendor pending with initiator */
		pages.techLoginInitiatorViewPage
				.selectRecordStatus(prop_constants.getProperty("submitted_By_Vendor_Pending_With_Initiator_Dd"));

		/* Click on search button */
		pages.techLoginInitiatorViewPage.clkSearchButton();

		/* Validating bgvId displayed */
		pages.techLoginInitiatorViewPage.verifySearchedBgvIdIsDisplayed();

		parentWindow = driver.getWindowHandle();

		/* Click on View/Edit link */
		pages.techLoginInitiatorViewPage.clkViewOrEditLink();

		/* Validate Manager Back Ground Page Checks Page displayed */
		pages.techLoginInitiatorViewPage.validateManageBackgroundChecksOfInitiatorViewPage();

		/* Click on Verify Checks button */
		pages.techLoginInitiatorViewPage.clkVerifyChecksButton();

		/* Validate Alert Pop Up */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("alert_msg_document_has_been_Verified"));

		/* Close the previous window */
		pages.techLoginInitiatorViewPage.clkCloseButton(parentWindow);

		/* Validate Initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Enter the BGVId in BGVId text field */
		pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

		/* Select record status submitted by vendor pending with initiator */
		pages.techLoginInitiatorViewPage
				.selectRecordStatus(prop_constants.getProperty("submitted_By_Vendor_Pending_With_Initiator_Dd"));

		/* Click on search button */
		pages.techLoginInitiatorViewPage.clkSearchButton();

		/* Enter Transaction Remark */
		pages.techLoginInitiatorViewPage.setTransactionRemarkTextField(prop_constants.getProperty("remark"));

		/* Click on Select checkBox under Select column */
		pages.techLoginInitiatorViewPage.clkCheckBoxUnderSelectColumn();

		/* Click on Mark checkBox */
		pages.techLoginInitiatorViewPage.clkMarkTheCheckBoxInCaseOfTPREBandAndGeoEntityWhileClosingTheCaseCheckBox();

		/* Click on Close case button */
		pages.techLoginInitiatorViewPage.clkCloseCaseButton();

		/* Click on Alert pop up ok button */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("selected_record(s)_will_be_submitted"));

		/* Wait For Invisibility Of Spinner */
		pages.techLoginInitiatorViewPage.waitForInvisibilityOfSpinner();

		/* Click on Alert pop up ok button */
		pages.techLoginInitiatorViewPage.validateAlertPopup(
				prop_constants.getProperty("alert_msg_bgv_for_the_selected_employees_has_been_closed."));

		/* Validate Initiator View Page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Validate Employee details is disappeared */
		pages.techLoginInitiatorViewPage.validateEmployeeDetailIsDisappeared();

	}
}
