package com.hcl.sv.testscripts;

import org.testng.annotations.Test;

import com.hcl.sv.baseutil.BaseTest;
import com.hcl.sv.dataprovider.DataProviderFactory;
import com.hcl.sv.dataprovider.DataProviderFileRowFilter;

/*
 *TestCaseId: TYSS_SV_POSTJ_003
 *TestScript Name: TYSS_SV_POSTJ_003
 *Description: Verify the initiator is able to send back the documents to vendor to verify it again and the vendor is able to submit the documents to the bgv team and then the initiator is able to close the case after verifying all the documents
 * 
 *@Author : Shivananda
 * 
 */
public class TYSS_SV_POSTJ_003 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/Data.xlsx", sql = "Select * from SV_Postjoining where SlNo ='TC_SV_POSTJ_003'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Verify the initiator is able to send back the documents to vendor to verify it again and the vendor is able to submit the documents to the bgv team and then the initiator is able to close the case after verifying all the documents")
	
	public void TC_TYSS_SV_POSTJ_003(String SlNo,String sapCode,String techLoginCode,String techPassword,String techEmpCode,
	String bgvId,String fromDate,String toDate,String vendorUserId, String vendorPassword){
	
	/* Login to application as employee */
	pages.techLoginLoginPage.loginToApplicationAsEmployee(sapCode,techLoginCode,techPassword);

	/* Set Unique verification Id textField */
	pages.techLoginEmployeeHomePage.setEnterUniqueVerificationIdTextField(bgvId);

	/* Click on View details button */
	pages.techLoginEmployeeHomePage.clkViewDetailsButton();

	/* Validate my document details header text */
	pages.techLoginEmployeeHomePage.validateMyDocumentDetailsHeaderText();

	/* Click on Upload link of post graduation document sub type under attachment */
	pages.techLoginEmployeeHomePage.clkUploadLinkForPostGraduation(
			prop_constants.getProperty("document_Type_Education"),
			prop_constants.getProperty("document_Sub_Type_PostGraduation"),
			actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_pdf")),
			prop_constants.getProperty("descriptionTesting"),
			prop_constants.getProperty("fileFormat_pdf"));

	/* Click on Upload link of graduation document sub type under attachment */
	pages.techLoginEmployeeHomePage.clkUploadLinkToUploadDesiredDocumentSubType(
			prop_constants.getProperty("document_Type_Education"),
			prop_constants.getProperty("document_Sub_Type_Graduation"),
			actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_jpg")), 
			prop_constants.getProperty("descriptionTesting"),prop_constants.getProperty("fileFormat_jpg"));

	/* Click Upload link of intermediate-12 document sub type under attachment */
	pages.techLoginEmployeeHomePage.clkUploadLinkToUploadDesiredDocumentSubType(
			prop_constants.getProperty("document_Type_Education"),
			prop_constants.getProperty("document_Sub_Type_Intermediate12thStandard"),
			actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_jpg")), 
			prop_constants.getProperty("descriptionTesting"),prop_constants.getProperty("fileFormat_jpg"));

	/* Click on check box of Not Applicable under my document details */
	pages.techLoginEmployeeHomePage.clkNotApplicableCheckBox();

	/* Click on Submit button */
	pages.techLoginEmployeeHomePage.clkSubmitButton();

	/* Validate Do you really want to submit the document alert pop up */
	pages.techLoginEmployeeHomePage.validateDoYouReallyWantToSubmitTheDocumentAlertPopup();

	/* Validate form submitted successfully alert pop up */
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

	/* Select the Post joining radio button */
	pages.techLoginInitiatorViewPage.clkTypeRadioButton(prop_constants.getProperty("rdo_Post_Joining"));

	/* Click on Record Status and select pending for BGV initiation */
	pages.techLoginInitiatorViewPage
	.selectRecordStatus(prop_constants.getProperty("pending_for_BGV_Initiation_Dd"));

	/* Enter BGV id in BGV Id textField */
	pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

	/* Click on Search button */
	pages.techLoginInitiatorViewPage.clkSearchButton();

	/* Verify searched BGV Id is displayed */
	pages.techLoginInitiatorViewPage.verifySearchedBgvIdIsDisplayed();

	String parentWindow=driver.getWindowHandle();
	
	/* Click on Upload button under BGV details tab */
	pages.techLoginInitiatorViewPage.clkUploadLink();
	
	/* Validate manage background checks initiator view page*/
	pages.techLoginInitiatorViewPage.validateManageBackgroundChecksOfInitiatorViewPage();

	String[] documentType1= {prop_constants.getProperty("document_Sub_Type_PostGraduation"),prop_constants.getProperty("document_Sub_Type_Graduation")
			,prop_constants.getProperty("document_Sub_Type_Intermediate12thStandard")};

	String[] documentDescription1= {prop_constants.getProperty("descriptionTesting"),prop_constants.getProperty("descriptionTesting"),prop_constants.getProperty("descriptionTesting")};

	/* Verify all the documents uploaded */
	pages.techLoginInitiatorViewPage.verifyThatAllTheDocumentsAreUploaded(documentType1, documentDescription1);

	/* Click on Close button */
	pages.techLoginInitiatorViewPage.clkCloseButton(parentWindow);

	/* Validate initiator view page */
	pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();
	
	/* Click on Select check box under Select column */
	pages.techLoginInitiatorViewPage.clkCheckBoxUnderSelectColumn();

	/* Click on Mark check box in case Of TP/RE band and geo entity While closing the case */
	pages.techLoginInitiatorViewPage.clkMarkTheCheckBoxInCaseOfTPREBandAndGeoEntityWhileClosingTheCaseCheckBox();

	/* Click on Initiate BGV button */
	pages.techLoginInitiatorViewPage.clkInitiateBGVButton();
	
	/* Validate alert pop up and click on OK */
	pages.techLoginInitiatorViewPage.validateAlertPopup(prop_constants.getProperty("selected_record(s)_will_be_submitted"));

	/* wait for invisibility of spinner */
	pages.techLoginInitiatorViewPage.waitForInvisibilityOfSpinner();
	
	/* Validate alert pop up and click on OK */
	pages.techLoginInitiatorViewPage.validateAlertPopup(prop_constants.getProperty("record_send_to_Vendor_Successfully"));
	
	/* Validate initiator view page */
	pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

	/* Validate employee details should be disappeared */
	pages.techLoginInitiatorViewPage.verifyEmployeeDetailIsDisappeared();
	
	/**********************************Vendor Login********************************/

	/* Login to application as vendor */
	pages.vendorLoginPage.loginToApplicationAsVendor(vendorUserId, vendorPassword);

	/* Click on View tab */
	pages.vendorTPAHomePage.clkView();

	/* Validate TPA view page */
	pages.vendorTPAViewPage.validateVendorTPAViewPage();

	/* Set BGV Id textField */
	pages.vendorTPAViewPage.setBgvId(bgvId);

	/* Click on Search button */
	pages.vendorTPAViewPage.clkSearch();

	/* Verify searched BGV Id is displayed */
	pages.vendorTPAViewPage.verifySearchedBgvIdIsDisplayed(bgvId);

	parentWindow = driver.getWindowHandle();

	/* Click on View/Edit button */
	pages.vendorTPAViewPage.clkEditOrUpdateLink();

	/* Validate back ground check page */
	pages.vendorLoginBackgroundCheckPage.validateBackGroundCheckPage();
	
	 /* Click '+' symbol of Enter duration for criminal court,address and employment */
	pages.vendorLoginBackgroundCheckPage.clkImagePluseEnterDurationForCriminalCourt(
			prop_constants.getProperty("CatagoryCriminalCourt"), prop_constants.getProperty("color_green"),
			fromDate, toDate);

	/* Click on '+' symbol of Required Checks::Document Specification */
	pages.vendorLoginBackgroundCheckPage.clkImagePluseDocumentSpecification(
			prop_constants.getProperty("educationType1-10"), prop_constants.getProperty("color_green"));

	/* Upload file for Send Communication/Upload Verification report */
	pages.vendorLoginBackgroundCheckPage.uploadFileAndValidate(actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_pdf")));

	/* Click on send communication Final Report radio button */
	pages.vendorLoginBackgroundCheckPage
			.clkSendCommunicationRadioButton(prop_constants.getProperty("rdo_Final_Report"));

	/* Set comments/remark textField */
	pages.vendorLoginBackgroundCheckPage.setComment(prop_constants.getProperty("comment_Ok"));

	/* Click on Send BGV button */
	pages.vendorLoginBackgroundCheckPage.clkSubmitToBGVTeamBtn();

	/* Validate alert pop up and click on OK */
	pages.vendorLoginBackgroundCheckPage
			.clkAlertPopUpOkBtn(prop_constants.getProperty("alert_Msg_Unselect_checks_will_be_mark_as_NA"));

	/* Wait for invisibility of spinner */
	pages.vendorLoginBackgroundCheckPage.waitForInvisibilityOfSpinner();

	/* Validate alert pop up and click on OK */
	pages.vendorLoginBackgroundCheckPage.clkAlertPopUpOkBtn(
			prop_constants.getProperty("header_verificationReporthasbeensubmittedsuccessfully"));

	/* Verify employee details is disappeared */
	pages.vendorTPAViewPage.verifyEmployeeDetailIsDisappeared(bgvId, parentWindow);

	/**********************Tech Login**************************/

	/* Login to application as a Tech */
	pages.techLoginLoginPage.loginToApplicationAsTech(techEmpCode,techLoginCode,techPassword);

	/* Select role from role list */
	pages.techLoginManageRolesPage.selectRoles(prop_constants.getProperty("role_Initiator"));

	/* Validate tech login initiator home page */
	pages.techLoginInitiatorHomePage.validateInitiatorHomePage();

	/* Click on initiator view page */
	pages.techLoginInitiatorHomePage.clkLinkInHeaderDD(prop_constants.getProperty("initiator"), prop_constants.getProperty("initiator_view"));

	/* Validate tech login initiator view page */
	pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

	/* Select the Post joining radio button */
	pages.techLoginInitiatorViewPage.clkTypeRadioButton(prop_constants.getProperty("rdo_Post_Joining"));
	  
	/* Select Record Status */
	pages.techLoginInitiatorViewPage.selectRecordStatus(prop_constants.getProperty("submitted_By_Vendor_Pending_With_Initiator_Dd"));

	/* Enter the BGVId in BGVId textField */	
	pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

	/* Click on Search button */
	pages.techLoginInitiatorViewPage.clkSearchButton();

	/* Verify searched BGV ID is displayed */
	pages.techLoginInitiatorViewPage.verifySearchedBgvIdIsDisplayed();

	parentWindow=driver.getWindowHandle();
	
	/* Click on View/Edit link */
	pages.techLoginInitiatorViewPage.clkViewOrEditLink();

	/* Click on QC Team remarks */	
	pages.techLoginInitiatorViewPage.selectQcTeamRemark(prop_constants.getProperty("missing_Check_Dd"));

	/* Click On Submit reason button */
	pages.techLoginInitiatorViewPage.clkQcTeamRemarkSubmitReasonButton();

	/* Validate alert pop up and click on OK */
	pages.techLoginInitiatorViewPage.validateAlertPopup(prop_constants.getProperty("alert_msg_Submitted_the_reason"));

	/* Switch the window */
	pages.techLoginInitiatorViewPage.switchParentWindow(parentWindow);
	
	/* Validate tech login initiator view page */
	pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();
	
	/* Click on Select check box under Select column */
	pages.techLoginInitiatorViewPage.clkCheckBoxUnderSelectColumn();

	/* Click on Mark the check box in case of TP/RE band and geo entity */
	pages.techLoginInitiatorViewPage.clkMarkTheCheckBoxInCaseOfTPREBandAndGeoEntityWhileClosingTheCaseCheckBox();

	/* Click on Submit to vendor */
	pages.techLoginInitiatorViewPage.clkSubmitToVendorButton();

	/* Validate alert pop up click on OK */
	pages.techLoginInitiatorViewPage.validateAlertPopup(prop_constants.getProperty("selected_record(s)_will_be_submitted"));

	/* wait for invisibility of spinner */
	pages.techLoginInitiatorViewPage.waitForInvisibilityOfSpinner();
	
	/* Validate alert pop up and click on OK */
	pages.techLoginInitiatorViewPage.validateAlertPopup(prop_constants.getProperty("record_send_to_Vendor_Successfully"));
			
	/* Validate tech login initiator view page */
	pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

	/* Validate employee details should be disappeared */
	pages.techLoginInitiatorViewPage.verifyEmployeeDetailIsDisappeared();

	/******************************Vendor Login******************************/

	/* Login to application as vendor */
	pages.vendorLoginPage.loginToApplicationAsVendor(vendorUserId, vendorPassword);

	/* Click on View tab */
	pages.vendorTPAHomePage.clkView();

	/* Validate TPA view page */
	pages.vendorTPAViewPage.validateVendorTPAViewPage();

	/* Set BGV Id textField */
	pages.vendorTPAViewPage.setBgvId(bgvId);

	/* Click on Search button */
	pages.vendorTPAViewPage.clkSearch();

	/* Verify searched BGV Id is displayed */
	pages.vendorTPAViewPage.verifySearchedBgvIdIsDisplayed(bgvId);

	 parentWindow = driver.getWindowHandle();

	/* Click on View/Edit button */
	pages.vendorTPAViewPage.clkEditOrUpdateLink();

	/* Validate back ground check page */
	pages.vendorLoginBackgroundCheckPage.validateBackGroundCheckPage();
	
	/* Click '+' symbol of Enter duration for criminal court,address and employment */
	pages.vendorLoginBackgroundCheckPage.clkImagePluseEnterDurationForCriminalCourt(
			prop_constants.getProperty("CatagoryCriminalCourt"), prop_constants.getProperty("color_green"),
			fromDate, toDate);

	/* Click on '+' symbol of Required checks::Document specification */
	pages.vendorLoginBackgroundCheckPage.clkImagePluseDocumentSpecification(
			prop_constants.getProperty("educationType1-10"), prop_constants.getProperty("color_green"));

	/* Upload file for send communication/Upload verification report */
	pages.vendorLoginBackgroundCheckPage.uploadFileAndValidate(actionUtil.getSampleFilePath(prop_constants.getProperty("fileFormat_pdf")));

	/* Click on send communication Final report radio button */
	pages.vendorLoginBackgroundCheckPage
			.clkSendCommunicationRadioButton(prop_constants.getProperty("rdo_Final_Report"));

	/* Set comments/remark textField */
	pages.vendorLoginBackgroundCheckPage.setComment(prop_constants.getProperty("comment_Ok"));

	/* Click on Send BGV button */
	pages.vendorLoginBackgroundCheckPage.clkSubmitToBGVTeamBtn();

	/* Validate alert pop up and click on OK */
	pages.vendorLoginBackgroundCheckPage
			.clkAlertPopUpOkBtn(prop_constants.getProperty("alert_Msg_Unselect_checks_will_be_mark_as_NA"));

	/* Wait for invisibility of spinner */
	pages.vendorLoginBackgroundCheckPage.waitForInvisibilityOfSpinner();

	/* Validate alert pop up and click on OK */
	pages.vendorLoginBackgroundCheckPage.clkAlertPopUpOkBtn(
			prop_constants.getProperty("header_verificationReporthasbeensubmittedsuccessfully"));

	/* Verify employee details is disappeared */
	pages.vendorTPAViewPage.verifyEmployeeDetailIsDisappeared(bgvId, parentWindow);
	
	/**********************Tech Login*************************/

	/* Login to application as tech */
	pages.techLoginLoginPage.loginToApplicationAsTech(techEmpCode, techLoginCode, techPassword);

	/* Click on initiator link from role list */
	pages.techLoginManageRolesPage.selectRoles(prop_constants.getProperty("role_Initiator"));

	/* Validate tech login initiator home page */
	pages.techLoginInitiatorHomePage.validateInitiatorHomePage();

	/* Select initiator view from initiator dropDown */
	pages.techLoginInitiatorHomePage.clkLinkInHeaderDD(prop_constants.getProperty("initiator"),
			prop_constants.getProperty("initiator_view"));

	/* Validate initiator view page */
	pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();

	/* Select the Post joining radio button */
	pages.techLoginInitiatorViewPage.clkTypeRadioButton(prop_constants.getProperty("rdo_Post_Joining"));

	/* Click on record status and select 'submitted by vendor -pending with initiator */
	pages.techLoginInitiatorViewPage
			.selectRecordStatus(prop_constants.getProperty("submitted_By_Vendor_Pending_With_Initiator_Dd"));

	/* Set BGV Id textField */
	pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

	/* Click on Search button */
	pages.techLoginInitiatorViewPage.clkSearchButton();

	/* Verify searched BGV Id is displayed */
	pages.techLoginInitiatorViewPage.verifySearchedBgvIdIsDisplayed();

	parentWindow = driver.getWindowHandle();

	/* Click on View/Edit button under BGV details tab */
	pages.techLoginInitiatorViewPage.clkViewOrEditLink();

	/* Click on Verify checks button */
	pages.techLoginInitiatorViewPage.clkVerifyChecksButton();

	/* Validate alert pop up and click on OK */
	pages.techLoginInitiatorViewPage
			.validateAlertPopup(prop_constants.getProperty("alert_msg_document_has_been_Verified"));

	/* Validate manage background check page */
	pages.techLoginInitiatorViewPage.validateManageBackgroundChecksOfInitiatorViewPage();

	/* Click on Close button */
	pages.techLoginInitiatorViewPage.clkCloseButton(parentWindow);

	/* Validate initiator view page */
	pages.techLoginInitiatorViewPage.validateTechLoginInitiatorViewPage();
	
	/* Select the Post joining radio button */
	pages.techLoginInitiatorViewPage.clkTypeRadioButton(prop_constants.getProperty("rdo_Post_Joining"));

	/* Set BGV Id textField */
	pages.techLoginInitiatorViewPage.setBgvIdTextField(bgvId);

	/* Click on Record Status and select 'submitted by vendor -pending with initiator */
	pages.techLoginInitiatorViewPage
			.selectRecordStatus(prop_constants.getProperty("submitted_By_Vendor_Pending_With_Initiator_Dd"));

	/* Click on Search button */
	pages.techLoginInitiatorViewPage.clkSearchButton();

	/* Set Transaction remark textField */
	pages.techLoginInitiatorViewPage.setTransactionRemarkTextField(prop_constants.getProperty("comment_Ok"));

	/* Click check box under Select column */
	pages.techLoginInitiatorViewPage.clkCheckBoxUnderSelectColumn();

	/* Click on Mark the check box in case Of TP/RE check box */
	pages.techLoginInitiatorViewPage.clkMarkTheCheckBoxInCaseOfTPREBandAndGeoEntityWhileClosingTheCaseCheckBox();

	/* Click Close Case button */
	pages.techLoginInitiatorViewPage.clkCloseCaseButton();

	/* Validate alert pop up and click on OK */
	pages.techLoginInitiatorViewPage.validateAlertPopup(prop_constants.getProperty("alert_msg_please_confirm_if_you_have_verify_BGV_Compliance_status"));

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