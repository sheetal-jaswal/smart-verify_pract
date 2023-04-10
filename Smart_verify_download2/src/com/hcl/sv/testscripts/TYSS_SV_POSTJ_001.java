package com.hcl.sv.testscripts;

import org.testng.annotations.Test;

import com.hcl.sv.baseutil.BaseTest;
import com.hcl.sv.dataprovider.DataProviderFactory;
import com.hcl.sv.dataprovider.DataProviderFileRowFilter;

/*
 *TestCaseId: TYSS_SV_POSTJ_001
 *TestScript Name: TC_SV_POSTJOINING_001
 *Description: Verify the Employee is able to submit the documents to Vendor and then the initiator is able to close the case after verifying all the documents.
 * 
 *@Author : Manikandan
 * 
 */

public class TYSS_SV_POSTJ_001 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/Data.xlsx", sql = "Select * from SV_Postjoining where SlNo ='TC_SV_POSTJ_001'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Verify the Employee is able to submit the documents to Vendor and then the initiator is able to close the case after verifying all the documents")
	public void TC_SV_POSTJOINING_001(String SlNo, String sapCode, String techLoginCode, String techPassword,
			String techEmpCode, String bgvId, String fromDate, String toDate, String vendorUserId,
			String vendorPassword) {

		/* Login to application as Employee */
		pages.techLoginLoginPage.loginToApplicationAsEmployee(sapCode, techLoginCode, techPassword);

		/* Set unique verification id text field */
		pages.techLoginEmployeeHomePage.setEnterUniqueVerificationIdTextField(bgvId);

		/* Click on view details button */
		pages.techLoginEmployeeHomePage.clkViewDetailsButton();

		/* Validate my documents details header text */
		pages.techLoginEmployeeHomePage.validateMyDocumentDetailsHeaderText();

		/* Click on upload link of Post-graduation document sub-type under attachment */
		pages.techLoginEmployeeHomePage.clkUploadLinkForPostGraduation(
				prop_constants.getProperty("document_Type_Education"),
				prop_constants.getProperty("document_Sub_Type_PostGraduation"),
				actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_pdf")),
				prop_constants.getProperty("descriptionTesting"), prop_constants.getProperty("fileFormat_pdf"));

		/* Click on upload link of Graduation document sub-type under attachment */
		pages.techLoginEmployeeHomePage.clkUploadLinkToUploadDesiredDocumentSubType(
				prop_constants.getProperty("document_Type_Education"),
				prop_constants.getProperty("document_Sub_Type_Graduation"),
				actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_jpg")),
				prop_constants.getProperty("descriptionTesting"), prop_constants.getProperty("fileFormat_jpg"));

		/* Click on upload link of Intermediate 12th standard document sub-type under attachment */
		pages.techLoginEmployeeHomePage.clkUploadLinkToUploadDesiredDocumentSubType(
				prop_constants.getProperty("document_Type_Education"),
				prop_constants.getProperty("document_Sub_Type_Intermediate12thStandard"),
				actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_jpg")),
				prop_constants.getProperty("descriptionTesting"), prop_constants.getProperty("fileFormat_jpg"));

		/* Click on the not applicable check box under my documents details */
		pages.techLoginEmployeeHomePage.clkNotApplicableCheckBox();

		/* Click on Submit button */
		pages.techLoginEmployeeHomePage.clkSubmitButton();

		/* Validate 'Do you really want to submit the document' alert pop up */
		pages.techLoginEmployeeHomePage.validateDoYouReallyWantToSubmitTheDocumentAlertPopup();

		/* Validate 'Form submitted successfully' alert pop up */
		pages.techLoginEmployeeHomePage.validateFormSubmittedSuccessfullyAlertPopup();

		/* Validate employee home page */
		pages.techLoginEmployeeHomePage.validateEmployeeHomePage();

		/***************** Tech login Initiator *********************************/

		/* Login to application as Tech */
		pages.techLoginLoginPage.loginToApplicationAsTech(techEmpCode, techLoginCode, techPassword);

		/* Select initiator role from role list */
		pages.techLoginManageRolesPage.selectRoles(prop_constants.getProperty("role_Initiator"));

		/* Validate initiator home page */
		pages.techLoginInitiatorHomePage.validateInitiatorHomePage();

		/* Select initiator view from initiator drop down */
		pages.techLoginInitiatorHomePage.clkLinkInHeaderDD(prop_constants.getProperty("initiator"),
				prop_constants.getProperty("initiator_view"));

		/* Validate tech login initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Click on Post-joining radio button */
		pages.techLoginInitiatorViewPage.clkTypeRadioButton(prop_constants.getProperty("rdo_Post_Joining"));

		/* Select record status from drop down */
		pages.techLoginInitiatorViewPage
				.selectRecordStatus(prop_constants.getProperty("pending_for_BGV_Initiation_Dd"));

		/* Set BGV id text field */
		pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

		/* Click Search button */
		pages.techLoginInitiatorViewPage.clkSearchButton();

		/* Verify searched BGV id is displayed */
		pages.techLoginInitiatorViewPage.verifySearchedBgvIdIsDisplayed();

		String parentWindow = driver.getWindowHandle();

		/* Click on upload link under BGV details tab */
		pages.techLoginInitiatorViewPage.clkUploadLink();

		/* Validate manage background checks of initiator view page */
		pages.techLoginInitiatorViewPage.validateManageBackgroundChecksOfInitiatorViewPage();

		String[] documentDescriptions = { prop_constants.getProperty("descriptionTesting"),
				prop_constants.getProperty("descriptionTesting"), prop_constants.getProperty("descriptionTesting") };
		String[] documentType = { prop_constants.getProperty("document_Sub_Type_PostGraduation"),
				prop_constants.getProperty("document_Sub_Type_Graduation"),
				prop_constants.getProperty("document_Sub_Type_Intermediate12thStandard") };

		/* Verify all the documents are uploaded */
		pages.techLoginInitiatorViewPage.verifyThatAllTheDocumentsAreUploaded(documentType, documentDescriptions);

		/* Click on close button */
		pages.techLoginInitiatorViewPage.clkCloseButton(parentWindow);

		/* Validate initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Click on check box under select column */
		pages.techLoginInitiatorViewPage.clkCheckBoxUnderSelectColumn();

		/* Click on mark the check box in case of TP/RE band and geo entity.. */
		pages.techLoginInitiatorViewPage.clkMarkTheCheckBoxInCaseOfTPREBandAndGeoEntityWhileClosingTheCaseCheckBox();

		/* Click on Initiate BGV button */
		pages.techLoginInitiatorViewPage.clkInitiateBGVButton();

		/* Validate alert pop up and click on ok button */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("selected_record(s)_will_be_submitted"));

		/* Wait for invisibility of spinner */
		pages.techLoginInitiatorViewPage.waitForInvisibilityOfSpinner();

		/* Validate alert pop up and click on ok button */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("record_send_to_Vendor_Successfully"));

		/* Validate tech login initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Verify employee details disappeared */
		pages.techLoginInitiatorViewPage.verifyEmployeeDetailIsDisappeared();

		/************************ Vendor Login ***************************/

		/* Login to application as a Vendor */
		pages.vendorLoginPage.loginToApplicationAsVendor(vendorUserId, vendorPassword);

		/* Click on View link */
		pages.vendorTPAHomePage.clkView();

		/* Validate TPA view page */
		pages.vendorTPAViewPage.validateVendorTPAViewPage();

		/* Set BGV id text field */
		pages.vendorTPAViewPage.setBgvId(bgvId);

		/* Click on Search button */
		pages.vendorTPAViewPage.clkSearch();

		/* Verify searched BGV id is displayed */
		pages.vendorTPAViewPage.verifySearchedBgvIdIsDisplayed(bgvId);

		parentWindow = driver.getWindowHandle();

		/* Click on Edit/Update link */
		pages.vendorTPAViewPage.clkEditOrUpdateLink();

		/* Validate manage background checks page */
		pages.vendorLoginBackgroundCheckPage.validateBackGroundCheckPage();

		/* Click '+' symbol of enter duration for criminal court,address and employment */
		pages.vendorLoginBackgroundCheckPage.clkImagePluseEnterDurationForCriminalCourt(
				prop_constants.getProperty("CatagoryCriminalCourt"), prop_constants.getProperty("color_green"),
				fromDate, toDate);

		/* Click on '+' symbol of required checks document specification */
		pages.vendorLoginBackgroundCheckPage.clkImagePluseDocumentSpecification(
				prop_constants.getProperty("educationType1-10"), prop_constants.getProperty("color_green"));

		/* Upload file for send communication or upload verification report */
		pages.vendorLoginBackgroundCheckPage
				.uploadFileAndValidate(actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_pdf")));

		/* Click on send communication final report radio button */
		pages.vendorLoginBackgroundCheckPage
				.clkSendCommunicationRadioButton(prop_constants.getProperty("rdo_Final_Report"));

		/* Set comments text field */
		pages.vendorLoginBackgroundCheckPage.setComment(prop_constants.getProperty("comment_Ok"));

		/* Click on submit to BGV button */
		pages.vendorLoginBackgroundCheckPage.clkSubmitToBGVTeamBtn();

		/* Validate alert pop up and click on ok button */
		pages.vendorLoginBackgroundCheckPage
				.clkAlertPopUpOkBtn(prop_constants.getProperty("alert_Msg_Unselect_checks_will_be_mark_as_NA"));

		/* Wait for invisibility of spinner */
		pages.vendorLoginBackgroundCheckPage.waitForInvisibilityOfSpinner();

		/* Validate alert pop up and click on ok button */
		pages.vendorLoginBackgroundCheckPage.clkAlertPopUpOkBtn(
				prop_constants.getProperty("header_verificationReporthasbeensubmittedsuccessfully"));

		/* Verify employee details disappeared */
		pages.vendorTPAViewPage.verifyEmployeeDetailIsDisappeared(bgvId, parentWindow);

		/*********** Tech Login Initiator ******************/

		/* Login to application as a Tech */
		pages.techLoginLoginPage.loginToApplicationAsTech(techEmpCode, techLoginCode, techPassword);

		/* Select initiator role from role list */
		pages.techLoginManageRolesPage.selectRoles(prop_constants.getProperty("role_Initiator"));

		/* Validate initiator home page */
		pages.techLoginInitiatorHomePage.validateInitiatorHomePage();

		/* Click on initiator view from initiator drop down */
		pages.techLoginInitiatorHomePage.clkLinkInHeaderDD(prop_constants.getProperty("initiator"),
				prop_constants.getProperty("initiator_view"));

		/* Validate tech login initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Click on Post-joining radio button */
		pages.techLoginInitiatorViewPage.clkTypeRadioButton(prop_constants.getProperty("rdo_Post_Joining"));

		/* Select record status from drop down */
		pages.techLoginInitiatorViewPage
				.selectRecordStatus(prop_constants.getProperty("submitted_By_Vendor_Pending_With_Initiator_Dd"));

		/* Enter BGV id in BGV id text field */
		pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

		/* Click on Search button */
		pages.techLoginInitiatorViewPage.clkSearchButton();

		/* Verify searched BGV id is displayed */
		pages.techLoginInitiatorViewPage.verifySearchedBgvIdIsDisplayed();

		parentWindow = driver.getWindowHandle();

		/* Click on View/Edit link under action tab */
		pages.techLoginInitiatorViewPage.clkViewOrEditLink();

		/* Click on verify checks button */
		pages.techLoginInitiatorViewPage.clkVerifyChecksButton();

		/* Validate alert pop up and click on ok button */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("alert_msg_document_has_been_Verified"));

		/* Validate manage background checks page */
		pages.techLoginInitiatorViewPage.validateManageBackgroundChecksOfInitiatorViewPage();

		/* Click on Close button */
		pages.techLoginInitiatorViewPage.clkCloseButton(parentWindow);

		/* Validate tech login initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Click on Post joining radio button */
		pages.techLoginInitiatorViewPage.clkTypeRadioButton(prop_constants.getProperty("rdo_Post_Joining"));

		/* Select record status from drop down */
		pages.techLoginInitiatorViewPage
				.selectRecordStatus(prop_constants.getProperty("submitted_By_Vendor_Pending_With_Initiator_Dd"));

		/* Set BGV id text field */
		pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

		/* Click on Search button */
		pages.techLoginInitiatorViewPage.clkSearchButton();

		/* Set transaction remark text field */
		pages.techLoginInitiatorViewPage.setTransactionRemarkTextField(prop_constants.getProperty("comment_Ok"));

		/* Click on check box under select column */
		pages.techLoginInitiatorViewPage.clkCheckBoxUnderSelectColumn();

		/* Click on mark the check box in case of TP/RE band and geo entity.. */
		pages.techLoginInitiatorViewPage.clkMarkTheCheckBoxInCaseOfTPREBandAndGeoEntityWhileClosingTheCaseCheckBox();

		/* Click Close ase button */
		pages.techLoginInitiatorViewPage.clkCloseCaseButton();

		/* Validate alert pop up and click on ok button */
		pages.techLoginInitiatorViewPage.validateAlertPopup(
				prop_constants.getProperty("alert_msg_please_confirm_if_you_have_verify_BGV_Compliance_status"));

		/* Wait for invisibility of spinner */
		pages.techLoginInitiatorViewPage.waitForInvisibilityOfSpinner();

		/* Validate alert pop up and click on ok button */
		pages.techLoginInitiatorViewPage.validateAlertPopup(
				prop_constants.getProperty("alert_msg_bgv_for_the_selected_employees_has_been_closed."));

		/* Validate initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Validate employee details disappeared */
		pages.techLoginInitiatorViewPage.validateEmployeeDetailIsDisappeared();

	}
}
