package com.hcl.sv.testscripts;

import org.testng.annotations.Test;

import com.hcl.sv.baseutil.BaseTest;
import com.hcl.sv.dataprovider.DataProviderFactory;
import com.hcl.sv.dataprovider.DataProviderFileRowFilter;

/*
 *TestCaseId: TC_SV_Existing_003
 *TestScript Name: TYSS_SV_E_003
 *Description: Verify the Initiator is able to send back the documents to Vendor to verify
 *it again and the Vendor is able to submit the documents to the BGV Team and then the 
 *Initiator is able to close the case after verifying all the documents
 * 
 *@Author : SreeLatha
 * 
 */
public class TYSS_SV_Existing_003 extends BaseTest{
	
		@DataProviderFileRowFilter(file = "./data/Data.xlsx", sql = "Select * from SV_Existing where SlNo ='TC_SV_E_003'")
		@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class,
		description = "Verify the Initiator is able to send back the documents to Vendor to verify it again and the Vendor is able to submit the documents to the BGV Team and then the Initiator is able to close the case after verifying all the documents")
		
		public void TC_TYSS_SV_Existing_003(String SlNo,String sapCode,String techLoginCode,String techPassword,String techEmpCode,
		String bgvId,String fromDate,String toDate,String vendorUserId, String vendorPassword){
		
		/* Login to application as Employee */
		pages.techLoginLoginPage.loginToApplicationAsEmployee(sapCode,techLoginCode,techPassword);

		/* Set unique verification Id text field */
		pages.techLoginEmployeeHomePage.setEnterUniqueVerificationIdTextField(bgvId);

		/* Click on view details button */
		pages.techLoginEmployeeHomePage.clkViewDetailsButton();

		/* Validate my document details header text */
		pages.techLoginEmployeeHomePage.validateMyDocumentDetailsHeaderText();

		/* Click on upload link of post-graduation document sub type under attachment */
		pages.techLoginEmployeeHomePage.clkUploadLinkForPostGraduation(
				prop_constants.getProperty("document_Type_Education"),
				prop_constants.getProperty("document_Sub_Type_PostGraduation"),
				actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_pdf")),
				prop_constants.getProperty("descriptionTesting"),
				prop_constants.getProperty("fileFormat_pdf"));

		/* Click upload link of graduation document sub type under attachment */
		pages.techLoginEmployeeHomePage.clkUploadLinkToUploadDesiredDocumentSubType(
				prop_constants.getProperty("document_Type_Education"),
				prop_constants.getProperty("document_Sub_Type_Graduation"),
				actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_jpg")), 
				prop_constants.getProperty("descriptionTesting"),prop_constants.getProperty("fileFormat_jpg"));

		/* Click upload link of intermediate-12 document sub type under attachment */
		pages.techLoginEmployeeHomePage.clkUploadLinkToUploadDesiredDocumentSubType(
				prop_constants.getProperty("document_Type_Education"),
				prop_constants.getProperty("document_Sub_Type_Intermediate12thStandard"),
				actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_jpg")), 
				prop_constants.getProperty("descriptionTesting"),prop_constants.getProperty("fileFormat_jpg"));

		/* Click the check box of "Not Applicable" under my document details */
		pages.techLoginEmployeeHomePage.clkNotApplicableCheckBox();

		/* Click on submit button */
		pages.techLoginEmployeeHomePage.clkSubmitButton();

		/* Validate alert pop up */
		pages.techLoginEmployeeHomePage.validateDoYouReallyWantToSubmitTheDocumentAlertPopup();

		/* Validate alert pop up */
		pages.techLoginEmployeeHomePage.validateFormSubmittedSuccessfullyAlertPopup();

		/* Validate employee home page */
		pages.techLoginEmployeeHomePage.validateEmployeeHomePage();


		/********************** Tech Login **************************/

		/* Login to application as Tech */
		pages.techLoginLoginPage.loginToApplicationAsTech(techEmpCode,techLoginCode,techPassword);

		/* Click on initiator link from role list */
		pages.techLoginManageRolesPage.selectRoles(prop_constants.getProperty("role_Initiator"));

		/* Validate initiator home page */
		pages.techLoginInitiatorHomePage.validateInitiatorHomePage();
		
		/* Click on initiator view page from the right side of the home tab */
		pages.techLoginInitiatorHomePage.clkLinkInHeaderDD(prop_constants.getProperty("initiator"),
				prop_constants.getProperty("initiator_view"));

		/* Validate initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Select the existing radio active button */
		pages.techLoginInitiatorViewPage.clkTypeRadioButton(prop_constants.getProperty("rdo_Existing"));
 
		/* Set BGV Id text field */
		pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

		/* Click on record status */
		pages.techLoginInitiatorViewPage
		.selectRecordStatus(prop_constants.getProperty("pending_for_BGV_Initiation_Dd"));

		/* Click on search button */
		pages.techLoginInitiatorViewPage.clkSearchButton();

		/* Validate searched BGV Id is displayed */
		pages.techLoginInitiatorViewPage.verifySearchedBgvIdIsDisplayed();
 
		String parentWindow=driver.getWindowHandle();
		
		/* Click on view button under BGV details tab */
		pages.techLoginInitiatorViewPage.clkUploadLink();
		
		/* Validate alert pop up */
		pages.techLoginInitiatorViewPage.validateAlertPopup
		(prop_constants.getProperty("alert_msg_Post_Joining_for_this_Employee_has_been_Done_Do_you_want_to_proceed?"));
		
		/* Validate manage background checks of initiator view page*/
		pages.techLoginInitiatorViewPage.validateManageBackgroundChecksOfInitiatorViewPage();

		String[] documentType1= {prop_constants.getProperty("document_Sub_Type_PostGraduation"),prop_constants.getProperty("document_Sub_Type_Graduation")
				,prop_constants.getProperty("document_Sub_Type_Intermediate12thStandard")};

		String[] documentDescription1= {prop_constants.getProperty("descriptionTesting"),prop_constants.getProperty("descriptionTesting"),prop_constants.getProperty("descriptionTesting")};

		/* Verify all the documents  are uploaded */
		pages.techLoginInitiatorViewPage.verifyThatAllTheDocumentsAreUploaded(documentType1, documentDescription1);

		/* Click on close button */
		pages.techLoginInitiatorViewPage.clkCloseButton(parentWindow);

		/* Validate initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();
		
		/* Click on select check box under select column */
		pages.techLoginInitiatorViewPage.clkCheckBoxUnderSelectColumn();

		/* Click on mark check box in case of TP/RE  */
		pages.techLoginInitiatorViewPage.clkMarkTheCheckBoxInCaseOfTPREBandAndGeoEntityWhileClosingTheCaseCheckBox();

		/* Click on initiate BGV button */
		pages.techLoginInitiatorViewPage.clkInitiateBGVButton();
		
		/* Validate alert pop up */
		pages.techLoginInitiatorViewPage.validateAlertPopup(prop_constants.getProperty("selected_record(s)_will_be_submitted"));

		/* Wait for invisibility of spinner */
		pages.techLoginInitiatorViewPage.waitForInvisibilityOfSpinner();
		
		/* Validate alert pop up and click on OK button */
		pages.techLoginInitiatorViewPage.validateAlertPopup(prop_constants.getProperty("record_send_to_Vendor_Successfully"));
		
		/* Validate tech login initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Validate employee details should be disappeared */
		pages.techLoginInitiatorViewPage.verifyEmployeeDetailIsDisappeared();
		
		/**********************************Vendor Login********************************/

		/* Login to application as Vendor */
		pages.vendorLoginPage.loginToApplicationAsVendor(vendorUserId, vendorPassword);

		/* Click on view tab */
		pages.vendorTPAHomePage.clkView();

		/* Validate TPA view page */
		pages.vendorTPAViewPage.validateVendorTPAViewPage();

		/* Set BGV Id text field */
		pages.vendorTPAViewPage.setBgvId(bgvId);

		/* Click on search button */
		pages.vendorTPAViewPage.clkSearch();

		/* Validate searched BGV Id is displayed */
		pages.vendorTPAViewPage.verifySearchedBgvIdIsDisplayed(bgvId);

		parentWindow = driver.getWindowHandle();

		/* Click on view/edit link */
		pages.vendorTPAViewPage.clkEditOrUpdateLink();

		 /* Click '+' symbol of enter duration for criminal court, address and employment */
		pages.vendorLoginBackgroundCheckPage.clkImagePluseEnterDurationForCriminalCourt(
				prop_constants.getProperty("CatagoryCriminalCourt"), prop_constants.getProperty("color_green"),
				fromDate, toDate);

		/* Click on '+' symbol of required checks::document specification */
		pages.vendorLoginBackgroundCheckPage.clkImagePluseDocumentSpecification(
				prop_constants.getProperty("educationType1-10"), prop_constants.getProperty("color_green"));

		/* Upload file for send communication/upload verification report */
		pages.vendorLoginBackgroundCheckPage.uploadFileAndValidate(actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_pdf")));

		/* Click on send communication final report radio button */
		pages.vendorLoginBackgroundCheckPage
				.clkSendCommunicationRadioButton(prop_constants.getProperty("rdo_Final_Report"));

		/* Set comments/remark text field */
		pages.vendorLoginBackgroundCheckPage.setComment(prop_constants.getProperty("comment_Ok"));

		/* Click on submit to BGV button */
		pages.vendorLoginBackgroundCheckPage.clkSubmitToBGVTeamBtn();

		/* Validate alert pop up */
		pages.vendorLoginBackgroundCheckPage
				.clkAlertPopUpOkBtn(prop_constants.getProperty("alert_Msg_Unselect_checks_will_be_mark_as_NA"));

		/* Wait for invisibility of spinner */
		pages.vendorLoginBackgroundCheckPage.waitForInvisibilityOfSpinner();

		/* Validate alert pop up */
		pages.vendorLoginBackgroundCheckPage.clkAlertPopUpOkBtn(
				prop_constants.getProperty("header_verificationReporthasbeensubmittedsuccessfully"));

		/* Validate employee details should be disappeared */
		pages.vendorTPAViewPage.verifyEmployeeDetailIsDisappeared(bgvId, parentWindow);

		/**********************Tech Login**************************/

		/* Login to application as Tech */
		pages.techLoginLoginPage.loginToApplicationAsTech(techEmpCode,techLoginCode,techPassword);

		/* Select manage role */
		pages.techLoginManageRolesPage.selectRoles(prop_constants.getProperty("role_Initiator"));

		/* Validate tech login initiator home page */
		pages.techLoginInitiatorHomePage.validateInitiatorHomePage();

		/* Click on initiator view page */
		pages.techLoginInitiatorHomePage.clkLinkInHeaderDD(prop_constants.getProperty("initiator"), prop_constants.getProperty("initiator_view"));

		/* Validate tech login initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Select the existing radio active button */
		pages.techLoginInitiatorViewPage.clkTypeRadioButton(prop_constants.getProperty("rdo_Existing"));

		/* Set BGV Id in text field */	
		pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

		/* Select record status */
		pages.techLoginInitiatorViewPage.selectRecordStatus(prop_constants.getProperty("submitted_By_Vendor_Pending_With_Initiator_Dd"));

		/* Click on search button */
		pages.techLoginInitiatorViewPage.clkSearchButton();

		/* Validate searched BGV Id is displayed */
		pages.techLoginInitiatorViewPage.verifySearchedBgvIdIsDisplayed();

		parentWindow=driver.getWindowHandle();
		
		/* Click on view/edit link */
		pages.techLoginInitiatorViewPage.clkViewOrEditLink();

		/* Validating background checks page */
		pages.techLoginInitiatorViewPage.validateManageBackgroundChecksOfInitiatorViewPage();

		/* Click on QC team remarks */	
		pages.techLoginInitiatorViewPage.selectQcTeamRemark(prop_constants.getProperty("missing_Check_Dd"));

		/* Click On submit reason button */
		pages.techLoginInitiatorViewPage.clkQcTeamRemarkSubmitReasonButton();

		/* Validate alert pop up and click on OK */
		pages.techLoginInitiatorViewPage.validateAlertPopup(prop_constants.getProperty("alert_msg_Submitted_the_reason"));

		/* Switch the window */
		pages.techLoginInitiatorViewPage.switchParentWindow(parentWindow);
		
		/* Click on select check box under select column */
		pages.techLoginInitiatorViewPage.clkCheckBoxUnderSelectColumn();

		/* Click on mark the check box in case of TP/RE */
		pages.techLoginInitiatorViewPage.clkMarkTheCheckBoxInCaseOfTPREBandAndGeoEntityWhileClosingTheCaseCheckBox();

		/* Click on submit to vendor */
		pages.techLoginInitiatorViewPage.clkSubmitToVendorButton();

		/* Validate alert pop up and click on OK  */
		pages.techLoginInitiatorViewPage.validateAlertPopup(prop_constants.getProperty("selected_record(s)_will_be_submitted"));

		/* Wait for invisibility of spinner */
		pages.techLoginInitiatorViewPage.waitForInvisibilityOfSpinner();
		
		/* Validate alert pop up and click on OK  */
		pages.techLoginInitiatorViewPage.validateAlertPopup(prop_constants.getProperty("record_send_to_Vendor_Successfully"));
	
		/* Validate initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();
				
		/* Validate employee details should be disappeared */
		pages.techLoginInitiatorViewPage.verifyEmployeeDetailIsDisappeared();

		/******************************Vendor Login******************************/

		/* Login to application as Vendor */
		pages.vendorLoginPage.loginToApplicationAsVendor(vendorUserId, vendorPassword);

		/* Click on view tab */
		pages.vendorTPAHomePage.clkView();

		/* Validate TPA view page */
		pages.vendorTPAViewPage.validateVendorTPAViewPage();

		/* Set BGV Id text field */
		pages.vendorTPAViewPage.setBgvId(bgvId);

		/* Click on search button */
		pages.vendorTPAViewPage.clkSearch();

		/* Validate searched BGV Id is displayed */
		pages.vendorTPAViewPage.verifySearchedBgvIdIsDisplayed(bgvId);

		parentWindow = driver.getWindowHandle();

		/* Click on edit/update link */
		pages.vendorTPAViewPage.clkEditOrUpdateLink();

		/* Click '+' symbol of enter duration for criminal court, address and employment */
		pages.vendorLoginBackgroundCheckPage.clkImagePluseEnterDurationForCriminalCourt(
				prop_constants.getProperty("CatagoryCriminalCourt"), prop_constants.getProperty("color_green"),
				fromDate, toDate);

		/* Click on '+' symbol of required checks::document specification */
		pages.vendorLoginBackgroundCheckPage.clkImagePluseDocumentSpecification(
				prop_constants.getProperty("educationType1-10"), prop_constants.getProperty("color_green"));

		/* Upload file for send communication/upload verification report */
		pages.vendorLoginBackgroundCheckPage.uploadFileAndValidate(actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_pdf")));

		/* Click on send communication final report radio button */
		pages.vendorLoginBackgroundCheckPage
				.clkSendCommunicationRadioButton(prop_constants.getProperty("rdo_Final_Report"));

		/* Set comments/remark text field */
		pages.vendorLoginBackgroundCheckPage.setComment(prop_constants.getProperty("comment_Ok"));

		/* Click on submit to BGV  team button */
		pages.vendorLoginBackgroundCheckPage.clkSubmitToBGVTeamBtn();

		/* Validate alert pop up */
		pages.vendorLoginBackgroundCheckPage
				.clkAlertPopUpOkBtn(prop_constants.getProperty("alert_Msg_Unselect_checks_will_be_mark_as_NA"));

		/* Wait for invisibility of spinner */
		pages.vendorLoginBackgroundCheckPage.waitForInvisibilityOfSpinner();

		/* Validate alert pop up */
		pages.vendorLoginBackgroundCheckPage.clkAlertPopUpOkBtn(
				prop_constants.getProperty("header_verificationReporthasbeensubmittedsuccessfully"));
		
		/* Validate employee details should be disappeared */
		pages.vendorTPAViewPage.verifyEmployeeDetailIsDisappeared(bgvId, parentWindow);
		

		/**********************Tech Login*************************/

		/* Login to application as Tech */
		pages.techLoginLoginPage.loginToApplicationAsTech(techEmpCode, techLoginCode, techPassword);

		/* Click on initiator link from role list */
		pages.techLoginManageRolesPage.selectRoles(prop_constants.getProperty("role_Initiator"));

		/* Validate tech login initiator home page */
		pages.techLoginInitiatorHomePage.validateInitiatorHomePage();

		/* Select initiator view from initiator drop down */
		pages.techLoginInitiatorHomePage.clkLinkInHeaderDD(prop_constants.getProperty("initiator"),
				prop_constants.getProperty("initiator_view"));

		/* Validate initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Select the existing radio active button */
		pages.techLoginInitiatorViewPage.clkTypeRadioButton(prop_constants.getProperty("rdo_Existing"));

		/* Set BGV Id text field */
		pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

		/* Click on record status */
		pages.techLoginInitiatorViewPage
				.selectRecordStatus(prop_constants.getProperty("submitted_By_Vendor_Pending_With_Initiator_Dd"));

		/* Click on search button */
		pages.techLoginInitiatorViewPage.clkSearchButton();

		/* Validate searched BGV Id should be displayed */
		pages.techLoginInitiatorViewPage.verifySearchedBgvIdIsDisplayed();

		parentWindow = driver.getWindowHandle();

		/* Click on view/edit button under BGV details tab */
		pages.techLoginInitiatorViewPage.clkViewOrEditLink();

		/* Click on verify checks button */
		pages.techLoginInitiatorViewPage.clkVerifyChecksButton();

		/* Validate alert pop up */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("alert_msg_document_has_been_Verified"));

		/* Validate manage background check page */
		pages.techLoginInitiatorViewPage.validateManageBackgroundChecksOfInitiatorViewPage();

		/* Click on close button */
		pages.techLoginInitiatorViewPage.clkCloseButton(parentWindow);

		/* Select the existing Radio active button */
		pages.techLoginInitiatorViewPage.clkTypeRadioButton(prop_constants.getProperty("rdo_Existing"));

		/* Set BGV Id text field */
		pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

		/* Click on record status */
		pages.techLoginInitiatorViewPage
				.selectRecordStatus(prop_constants.getProperty("submitted_By_Vendor_Pending_With_Initiator_Dd"));

		/* Click on search button */
		pages.techLoginInitiatorViewPage.clkSearchButton();

		/* Validate initiator view page */
		pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

		/* Set transaction remark text field */
		pages.techLoginInitiatorViewPage.setTransactionRemarkTextField(prop_constants.getProperty("comment_Ok"));

		/* Click check box under select column */
		pages.techLoginInitiatorViewPage.clkCheckBoxUnderSelectColumn();

		/* Click TP/RE check box */
		pages.techLoginInitiatorViewPage.clkMarkTheCheckBoxInCaseOfTPREBandAndGeoEntityWhileClosingTheCaseCheckBox();

		/* Click close case button */
		pages.techLoginInitiatorViewPage.clkCloseCaseButton();

		/* Click on alert pop up and OK button */
		pages.techLoginInitiatorViewPage
				.validateAlertPopup(prop_constants.getProperty("selected_record(s)_will_be_submitted"));

		/* Wait for invisibility of spinner */
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
