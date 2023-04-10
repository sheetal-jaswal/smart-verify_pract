package com.hcl.sv.baseutil;

import org.openqa.selenium.WebDriver;

import com.hcl.sv.pages.CandidateHome_Page;
import com.hcl.sv.pages.CandidateLogin_Page;
import com.hcl.sv.pages.TechLoginLogin_Page;
import com.hcl.sv.pages.TechLogin_EmployeeHome_Page;
import com.hcl.sv.pages.TechLogin_InitiatorDashboard_Page;
import com.hcl.sv.pages.TechLogin_InitiatorHome_Page;
import com.hcl.sv.pages.TechLogin_InitiatorView_Page;
import com.hcl.sv.pages.TechLogin_ManageRoles_Page;
import com.hcl.sv.pages.TechLogin_RecruiterHome_Page;
import com.hcl.sv.pages.TechLogin_RecruiterRole_View_Page;
import com.hcl.sv.pages.TechLogin_RecruiterView_Page;
import com.hcl.sv.pages.VendorLogin_BackgroundCheck_Page;
import com.hcl.sv.pages.VendorLogin_Page;
import com.hcl.sv.pages.VendorTPAHome_Page;
import com.hcl.sv.pages.VendorTPAView_Page;
import com.hcl.sv.util.WebActionUtil;

/**
 * Description: Initializes all pages with driver instance ,Explicit Time out,
 * WebAactionUtility using variables driver,ETO,WebactionUtil
 * 
 * @author : Manikandan A
 */
public class InitializePages {
	public CandidateHome_Page candidateHomePage;
	public CandidateLogin_Page candidateLoginPage;
	public TechLogin_EmployeeHome_Page techLoginEmployeeHomePage;
	public TechLogin_InitiatorView_Page techLoginInitiatorViewPage;
	public TechLogin_RecruiterView_Page techLoginRecruiterViewPage;
	public TechLogin_InitiatorDashboard_Page techLoginInitiatorDashboardPage;
	public TechLogin_ManageRoles_Page techLoginManageRolesPage;
	public TechLogin_RecruiterRole_View_Page techLoginRecruiterRoleViewPage;
	public TechLoginLogin_Page techLoginLoginPage;
	public VendorLogin_BackgroundCheck_Page vendorLoginBackgroundCheckPage;
	public VendorLogin_Page vendorLoginPage;
	public VendorTPAHome_Page vendorTPAHomePage;
	public VendorTPAView_Page vendorTPAViewPage;
	public TechLogin_InitiatorHome_Page techLoginInitiatorHomePage;
	public TechLogin_RecruiterHome_Page techLoginRecruiterHomePage;

	public InitializePages(WebDriver driver, long ETO, WebActionUtil actionUtil) {
		candidateHomePage = new CandidateHome_Page(driver, ETO, actionUtil);
		candidateLoginPage = new CandidateLogin_Page(driver, ETO, actionUtil);
		techLoginEmployeeHomePage = new TechLogin_EmployeeHome_Page(driver, ETO, actionUtil);
		techLoginInitiatorViewPage = new TechLogin_InitiatorView_Page(driver, ETO, actionUtil);
		techLoginRecruiterViewPage = new TechLogin_RecruiterView_Page(driver, ETO, actionUtil);
		techLoginInitiatorDashboardPage = new TechLogin_InitiatorDashboard_Page(driver, ETO, actionUtil);
		techLoginManageRolesPage = new TechLogin_ManageRoles_Page(driver, ETO, actionUtil);
		techLoginRecruiterRoleViewPage = new TechLogin_RecruiterRole_View_Page(driver, ETO, actionUtil);
		techLoginLoginPage = new TechLoginLogin_Page(driver, ETO, actionUtil);
		vendorLoginBackgroundCheckPage = new VendorLogin_BackgroundCheck_Page(driver, ETO, actionUtil);
		vendorLoginPage = new VendorLogin_Page(driver, ETO, actionUtil);
		vendorTPAHomePage = new VendorTPAHome_Page(driver, ETO, actionUtil);
		vendorTPAViewPage = new VendorTPAView_Page(driver, ETO, actionUtil);
		techLoginInitiatorHomePage = new TechLogin_InitiatorHome_Page(driver, ETO, actionUtil);
		techLoginRecruiterHomePage = new TechLogin_RecruiterHome_Page(driver, ETO, actionUtil);
	}

}