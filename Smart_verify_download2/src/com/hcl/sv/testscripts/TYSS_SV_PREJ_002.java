package com.hcl.sv.testscripts;

import org.testng.annotations.Test;

import com.hcl.sv.baseutil.BaseTest;
import com.hcl.sv.dataprovider.DataProviderFactory;
import com.hcl.sv.dataprovider.DataProviderFileRowFilter;

/*
 *TestCaseId: TC_SV_PreJoining_002
 *TestScript Name: TYSS_SV_PREJ_002
 *Description: Verify the Candidate is able to submit document to Recruiter,
 *Recruiter is able to Upload exception to BGV team, Vendor is able to verify
 *the documents and submit to BGV, Initiator is able to verify all the
 *documents and close the case.
 * 
 *@Author : Abhishek
 * 
 */
public class TYSS_SV_PREJ_002 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/Data.xlsx", sql = "Select * from SV_Prejoining where SlNo ='TC_SV_PreJoining_002'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = " Verify the Candidate is able to submit document to Recruiter,Recruiter is able to Upload exception to BGV team,Vendor is able to verify the documents and submit to BGV,Initiator is able to verify all the documents and close the case.")

	public void TC_TYSS_SV_PREJ_002(String SINO, String candidateUserId, String candidatePassword, String techEmpCode,
			String techLoginCode, String techPassword, String vendorUserId, String vendorPassword, String bgvId,
			String fromDate, String toDate) {

		/****** Candidate Login*****/

		/* Login to application as candidate */
		pages.candidateLoginPage.loginToApplication(candidateUserId, candidatePassword);

		/* Validate candidate home page */
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

		/* Click on Second last employer sub type upload link */
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

		/* Validate alert pop up and click on Ok */
		pages.candidateHomePage
		.validateAlertPopup(prop_constants.getProperty("header_doYouReallyWantToSubmitTheDocument"));

		/* Validate form submitted successfully message */
		pages.candidateHomePage.validateFormSubmittedSuccessfullyMessage();

		/* Validate candidate home page */
		pages.candidateHomePage.validateCandidateHomePage();

		/******* Tech Login Recruiter********/

		/* Login to application as Tech */
		pages.techLoginLoginPage.loginToApplicationAsTech(techEmpCode, techLoginCode, techPassword);

		/* Click on recruiter link from role list */
		pages.techLoginManageRolesPage.selectRoles(prop_constants.getProperty("role_Recruiter"));

		/* Validate recruiter home page */
		pages.techLoginRecruiterHomePage.validateRecruiterHomePage();

		/* Click on recruiter view header */
		pages.techLoginRecruiterHomePage.clkLinkInHeader(prop_constants.getProperty("recruiter_View"));

		/* Validate recruiter view page */
		pages.techLoginRecruiterViewPage.validateRecruiterViewPage();

		/* Select record status from drop down */
		pages.techLoginRecruiterViewPage
		.selectRecordStatus(prop_constants.getProperty("document_uploaded_Pending_With_Recruiter_Dd"));

		/* Set BGV id text field */
		pages.techLoginRecruiterViewPage.setBgvIdTextField(bgvId);

		/* Click on search button */
		pages.techLoginRecruiterViewPage.clkSearchButton();

		/* Validate searched BGV id is displayed */
		pages.techLoginRecruiterViewPage.verifySearchedBgvIdIsDisplayed(bgvId);

		String parentWindow = driver.getWindowHandle();

		/* Click on view link under BGV details tab */
		pages.techLoginRecruiterViewPage.clkViewLink();

		/* Validate manage background check page */
		pages.techLoginRecruiterRoleViewPage.validateManageBackgroundChecksOfRecruiterViewPage();

		/* Select exception category from drop down */
		pages.techLoginRecruiterRoleViewPage
		.selectExceptionCategory(prop_constants.getProperty("initiate_Exception_Joining_Dd"));

		/* Select exception reason from drop down */
		pages.techLoginRecruiterRoleViewPage.selectExceptionReason(prop_constants.getProperty("BGV_Waiver_Dd"));

		/* Click on choose file button */
		pages.techLoginRecruiterRoleViewPage.clkJoiningExceptionChooseFileButton();

		/* Upload file */
		pages.techLoginRecruiterRoleViewPage
		.uploadFile(actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_jpg")));

		/* Validate uploaded file */
		pages.techLoginRecruiterRoleViewPage.validateFileUploaded(prop_constants.getProperty("fileFormat_jpg"));

		/* Set joining exception remark text field */
		pages.techLoginRecruiterRoleViewPage
		.setJoiningExceptionRemarkTextField(prop_constants.getProperty("comment_Ok"));

		/* Click on upload exception button */
		pages.techLoginRecruiterRoleViewPage.clkUploadExceptionButton();

		/* Validate alert pop up message and click on Ok */
		pages.techLoginRecruiterRoleViewPage
		.validateAlertPopup(prop_constants.getProperty("exception_Uploaded_Successfully_Alert_Msg"));

		/* Validate manage bgv page */
		pages.techLoginRecruiterRoleViewPage.validateManageBackgroundChecksOfRecruiterViewPage();

		/* Click close button */
		pages.techLoginRecruiterRoleViewPage.clkCloseButton(parentWindow);

		/* Validate recruiter view page */
		pages.techLoginRecruiterViewPage.validateRecruiterViewPage();

		/* Select package drop down */
		pages.techLoginRecruiterViewPage.selectPackage(prop_constants.getProperty("HCL_Standard2_Dd"));

		/* Set remark text field */
		pages.techLoginRecruiterViewPage.setTransactionRemarkTextField(prop_constants.getProperty("comment_Ok"));

		/* Click On select check box under select column */
		pages.techLoginRecruiterViewPage.clkCheckBoxUnderSelectColumn();

		/* Click on I am confirming... check box */
		pages.techLoginRecruiterViewPage
		.clkIAmConfirmingThatIVerifiedTheDocumentsSubmittedByTheCandidateAgainstTheMRLListCheckBox();

		/* Click on submit to BGV team button */
		pages.techLoginRecruiterViewPage.clkSubmitToBgvTeamButton();

		/* Validate alert pop up and click on Ok button */
		pages.techLoginRecruiterViewPage.validateAlertPopup(prop_constants.getProperty(
				"it_is_assumed_that_you_have_validated_all_the_documents_uploaded_by_the_candidate_against_the_MRL_Fake/Suspicious_list__Alert_Msg"));

		/* wait for invisibility of spinner */
		pages.techLoginRecruiterViewPage.waitForInvisibilityOfSpinner();

		/* Validate alert pop up and click on Ok button */
		pages.techLoginRecruiterViewPage.validateAlertPopup(
				prop_constants.getProperty("bgv_for_the_selected_employees_has_been_send_to_BGV_Team_Alert_Msg"));

		/* Validate recruiter view page */
		pages.techLoginRecruiterViewPage.validateRecruiterViewPage();

		/* Validate employee details disappeared */
		pages.techLoginRecruiterViewPage.verifyEmployeeDetailIsDisappeared(bgvId);

		/***** Tech Login Initiator*********/

		/* Login to application as Tech */
		pages.techLoginLoginPage.loginToApplicationAsTech(techEmpCode, techLoginCode, techPassword);

		/* Click on initiator link from role list */
		pages.techLoginManageRolesPage.selectRoles(prop_constants.getProperty("role_Initiator"));

		/* Validate Tech login initiator home page */
		pages.techLoginInitiatorHomePage.validateInitiatorHomePage();

		/* Click on initiator view from initiator drop down */
		pages.techLoginInitiatorHomePage.clkLinkInHeaderDD(prop_constants.getProperty("initiator"),
				prop_constants.getProperty("initiator_view"));

		/* Validate initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Select record status drop down */
		pages.techLoginInitiatorViewPage
		.selectRecordStatus(prop_constants.getProperty("submitted_by_Recruiter_Pending_with_Initiator_Dd"));

		/* Set BGV id text field */
		pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

		/* Click on search button */
		pages.techLoginInitiatorViewPage.clkSearchButton();

		/* Validate searched BGV id is displayed */
		pages.techLoginInitiatorViewPage.verifySearchedBgvIdIsDisplayed();

		parentWindow = driver.getWindowHandle();

		/* Click on View/Edit button under BGV details tab */
		pages.techLoginInitiatorViewPage.clkViewOrEditLink();

		/* Select QC team remark from drop down */
		pages.techLoginInitiatorViewPage.selectQcTeamRemark(prop_constants.getProperty("missing_Check_Dd"));

		/* Click on submit reason button */
		pages.techLoginInitiatorViewPage.clkQcTeamRemarkSubmitReasonButton();

		/* Validate alert pop up and click on Ok button */
		pages.techLoginInitiatorViewPage
		.validateAlertPopup(prop_constants.getProperty("alert_msg_Submitted_the_reason"));

		/* Switch the window */
		pages.techLoginInitiatorViewPage.switchParentWindow(parentWindow);

		/* Validate Tech login initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Set transaction remark text field */
		pages.techLoginInitiatorViewPage.setTransactionRemarkTextField(prop_constants.getProperty("comment_Ok"));

		/* Click select check box under select clumn */
		pages.techLoginInitiatorViewPage.clkCheckBoxUnderSelectColumn();

		/*Click mark the checkBox in case of TP/RE band */
		pages.techLoginInitiatorViewPage.clkMarkTheCheckBoxInCaseOfTPREBandAndGeoEntityWhileClosingTheCaseCheckBox();

		/* Click on close case button */
		pages.techLoginInitiatorViewPage.clkSubmitToVendorButton();

		/* Click on alert pop up ok button */
		pages.techLoginInitiatorViewPage
		.validateAlertPopup(prop_constants.getProperty("selected_record(s)_will_be_submitted"));

		/* Wait for invisibility of spinner */
		pages.techLoginRecruiterViewPage.waitForInvisibilityOfSpinner();

		/* Click on alert pop up ok button */
		pages.techLoginInitiatorViewPage
		.validateAlertPopup(prop_constants.getProperty("record_send_to_Vendor_Successfully"));

		/* Validate initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Validate employee details should be disappeared */
		pages.techLoginInitiatorViewPage.verifyEmployeeDetailIsDisappeared();

		/****** Vendor Page *************/

		/* Login As vendor */
		pages.vendorLoginPage.loginToApplicationAsVendor(vendorUserId, vendorPassword);

		/* Click on view Tab */
		pages.vendorTPAHomePage.clkView();

		/* Validate TPA view page */
		pages.vendorTPAViewPage.validateVendorTPAViewPage();

		/* Set BGV id text field */
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

		/* Click '+' Symbol of enter duration for Criminal court,address and employment*/
		pages.vendorLoginBackgroundCheckPage.clkImagePluseEnterDurationForCriminalCourt(
				prop_constants.getProperty("CatagoryCriminalCourt"), prop_constants.getProperty("color_green"),
				fromDate, toDate);

		/* Click on '+' symbol of required checks document specification */
		pages.vendorLoginBackgroundCheckPage.clkImagePluseDocumentSpecification(
				prop_constants.getProperty("educationType1-10"), prop_constants.getProperty("color_green"));

		/* Upload file for send communication/upload verification report */
		pages.vendorLoginBackgroundCheckPage
		.uploadFileAndValidate(actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_pdf")));

		/* Click on send communication final report radio button */
		pages.vendorLoginBackgroundCheckPage
		.clkSendCommunicationRadioButton(prop_constants.getProperty("rdo_Final_Report"));

		/* Set comments/remark text field */
		pages.vendorLoginBackgroundCheckPage.setComment(prop_constants.getProperty("comment_Ok"));

		/* Click on submit to BGV team button */
		pages.vendorLoginBackgroundCheckPage.clkSubmitToBGVTeamBtn();

		/* Validate alert pop up */
		pages.vendorLoginBackgroundCheckPage
		.clkAlertPopUpOkBtn(prop_constants.getProperty("alert_Msg_Unselect_checks_will_be_mark_as_NA"));

		/* Wait for invisibility Of spinner */
		pages.vendorLoginBackgroundCheckPage.waitForInvisibilityOfSpinner();

		/* Validate alert pop up and click on Ok */
		pages.vendorLoginBackgroundCheckPage.clkAlertPopUpOkBtn(
				prop_constants.getProperty("header_verificationReporthasbeensubmittedsuccessfully"));

		/* Validate employee details should be disappeared */
		pages.vendorTPAViewPage.verifyEmployeeDetailIsDisappeared(bgvId, parentWindow);

		/*********** Tech Login Initiator******************/

		/* Login to application as Tech */
		pages.techLoginLoginPage.loginToApplicationAsTech(techEmpCode, techLoginCode, techPassword);

		/* Click on initiator link from role list */
		pages.techLoginManageRolesPage.selectRoles(prop_constants.getProperty("role_Initiator"));

		/* Validate Tech login initiator home page */
		pages.techLoginInitiatorHomePage.validateInitiatorHomePage();

		/* Click on initiator view from initiator drop down */
		pages.techLoginInitiatorHomePage.clkLinkInHeaderDD(prop_constants.getProperty("initiator"),
				prop_constants.getProperty("initiator_view"));

		/* Validate initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Select record status drop down */
		pages.techLoginInitiatorViewPage
		.selectRecordStatus(prop_constants.getProperty("submitted_By_Vendor_Pending_With_Initiator_Dd"));

		/* Set BGV id text field */
		pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

		/* Click on search button */
		pages.techLoginInitiatorViewPage.clkSearchButton();

		/* Validate searched BGV id is displayed */
		pages.techLoginInitiatorViewPage.verifySearchedBgvIdIsDisplayed();

		parentWindow = driver.getWindowHandle();

		/* Click on View/Edit button under BGV details tab */
		pages.techLoginInitiatorViewPage.clkViewOrEditLink();

		/* Click on verify checks button */
		pages.techLoginInitiatorViewPage.clkVerifyChecksButton();

		/* Validate alert pop up */
		pages.techLoginInitiatorViewPage
		.validateAlertPopup(prop_constants.getProperty("alert_msg_document_has_been_Verified"));

		/* Validate manage background Check page */
		pages.techLoginInitiatorViewPage.validateManageBackgroundChecksOfInitiatorViewPage();

		/* Click on close button */
		pages.techLoginInitiatorViewPage.clkCloseButton(parentWindow);

		/* Validate Tech login initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Select record status drop down */
		pages.techLoginInitiatorViewPage
		.selectRecordStatus(prop_constants.getProperty("submitted_By_Vendor_Pending_With_Initiator_Dd"));

		/* Set BGV id text field */
		pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

		/* Click on search button */
		pages.techLoginInitiatorViewPage.clkSearchButton();

		/* Set transaction remark text field */
		pages.techLoginInitiatorViewPage.setTransactionRemarkTextField(prop_constants.getProperty("comment_Ok"));

		/* Click select check box under select column */
		pages.techLoginInitiatorViewPage.clkCheckBoxUnderSelectColumn();

		/* Click mark the check box in case of TP/RE band */
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

		/* Validate employee details should be disappeared */
		pages.techLoginInitiatorViewPage.validateEmployeeDetailIsDisappeared();

	}
}
