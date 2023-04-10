package com.hcl.sv.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.hcl.sv.baseutil.BaseTest;
import com.hcl.sv.util.WebActionUtil;

/**
 * Description: This class implements the methods for tech login initiator dash board page.
 * 
 * @author Ramya R
 */

public class TechLogin_InitiatorDashboard_Page {
	public WebDriver driver;
	public WebActionUtil actionUtil;
	public long ETO;

	public TechLogin_InitiatorDashboard_Page(WebDriver driver, long ETO, WebActionUtil actionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actionUtil = actionUtil;
		this.ETO = ETO;

	}	

	/*Entity code drop down*/
	@FindBy(xpath = "//select[contains(@id,'ctl00_ContentPlaceHolder1_ddlEntityCode')]")
	private WebElement ddEntityCode;

	/*Verification type drop down*/
	@FindBy(xpath = "//select[contains(@id,'ctl00_ContentPlaceHolder1_ddlVerificationType')]")
	private WebElement ddVerificationType;

	/*L1 code drop down*/
	@FindBy(xpath = "//select[contains(@id,'ctl00_ContentPlaceHolder1_ddlL1')]")
	private WebElement ddL1;

	/*L2 code drop down*/
	@FindBy(xpath = "//select[contains(@id,'ctl00_ContentPlaceHolder1_ddlL2')]")
	private WebElement ddL2;

	/*L3 code drop down*/
	@FindBy(xpath = "//select[contains(@id,'ctl00_ContentPlaceHolder1_ddlL3')]")
	private WebElement ddL3;

	/*L4 code drop down*/
	@FindBy(xpath = "//select[contains(@id,'ctl00_ContentPlaceHolder1_ddlL4')]")
	private WebElement ddL4;

	/*Select dash board radio button*/
	private WebElement selectDashboard(String rdoDashBoard) {
		return driver.findElement(By.xpath("//label[contains(text(),'"+rdoDashBoard+"')]"));
	}

	/*From date clear link*/
	@FindBy(xpath = "//a[@id='ctl00_ContentPlaceHolder1_hlClearFromDate']")
	private WebElement lnkClearFromDtae;

	/*To date clear link*/
	@FindBy(xpath = "//a[@id='ctl00_ContentPlaceHolder1_hlClearToDate']")
	private WebElement lnkClearToDtae;

	/*From date calendar image*/
	@FindBy(xpath = "//img[@id='ctl00_ContentPlaceHolder1_imgCalFrom']")
	private WebElement imgFromDate;

	/*To date calendar image*/
	@FindBy(xpath = "//img[@id='ctl00_ContentPlaceHolder1_imgCalTo']")
	private WebElement imgToDate;

	/*Year drop down*/
	@FindBy(xpath = "//div[@class='selectBox']/span[@id='calendar_year_txt']")
	private WebElement ddYear;

	/*Select year from drop down*/
	private WebElement selectFromYear(String year) {
		return driver.findElement(By.xpath("//div[text()='"+year+"']"));
	}

	/*Month drop down*/
	@FindBy(xpath = "//div[@id='monthSelect']/span[@id='calendar_month_txt']")
	private WebElement ddMonth;

	/*Select month from drop down*/
	private WebElement selectFromMonth(String month) {
		return driver.findElement(By.xpath("//div[contains(text(),'"+month+"')]"));
	}

	/*Select date from calendar*/
	private WebElement selectFromDate(String date) {
		return driver.findElement(By.xpath("//table/tbody/tr/td[text()='"+date+"']"));
	}
	/*Date in from calendar text field*/
	@FindBy(xpath = "//input[contains(@id,'ctl00_ContentPlaceHolder1_txtFromDate')]")
	private WebElement txtDate;

	/**
	 * Description: Method to validate initiator dash board page
	 * 
	 * @author Ramya R
	 * 
	 */
	public synchronized void validateInitiatorDashboardPage() {
		try {
			WebActionUtil.validateUrl(BaseTest.prop_constants.getProperty("techLogin_InitiatorDashboard_Page_url"),
					"Initiator Dashboard Page");

		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Initiator Dashboard is not Displayed");
			Assert.fail("Initiator Dashboard is not Displayed");
		}
	}

	/**
	 * Description: Method to select entity code 
	 * 
	 * @author Ramya R
	 * @param entityCode
	 * 
	 */
	public synchronized void selectEntityCode(String entityCode) {
		try {
			actionUtil.waitForAngularPageload();
			actionUtil.selectByText(ddEntityCode, entityCode);

			if(ddEntityCode.getText().contains(entityCode)) {

				actionUtil.info("selected "+entityCode+" is Displayed in Text Field");
				actionUtil.validationinfo("selected "+entityCode+" is Displayed in Text Field","blue");
			} else {
				actionUtil.fail("selected "+entityCode+" is not Displayed in Text Field");
				Assert.fail("selected "+entityCode+" is not Displayed in Text Field");
			}

		} catch (Exception e) {

			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to Select"+entityCode+" Entity code");
			Assert.fail("Unable to Select"+entityCode+" Entity code");
		}
	}

	/**
	 * Description: Method to select verification type
	 * 
	 * @author Ramya R
	 * @param type
	 * 
	 */
	public synchronized void selectVerificationType(String type) {
		try {
			actionUtil.waitForAngularPageload();
			actionUtil.selectByText(ddVerificationType,type);

			if(ddVerificationType.getText().contains(type)) {

				actionUtil.info("selected "+type+" is Displayed in Text Field");
				actionUtil.validationinfo("selected "+type+" is Displayed in Text Field","blue");
			} else {
				actionUtil.fail("selected "+type+" is not Displayed in Text Field");
				Assert.fail("selected "+type+" is not Displayed in Text Field");
			}

		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to Select"+type+" Verification Type");
			Assert.fail("Unable to Select"+type+" Verification Type");
		}
	}

	/**
	 * Description: Method to select L1 code
	 * 
	 * @author Ramya R
	 * @param l1Code
	 * 
	 */
	public synchronized void selectL1Code(String l1Code) {
		try {
			actionUtil.waitForAngularPageload();
			actionUtil.selectByText(ddL1,l1Code);

			if(ddL1.getText().contains(l1Code)) {

				actionUtil.info("selected "+l1Code+" is Displayed in Text Field");
				actionUtil.validationinfo("selected "+l1Code+" is Displayed in Text Field","blue");
			} else {
				actionUtil.fail("selected "+l1Code+" is not Displayed in Text Field");
				Assert.fail("selected "+l1Code+" is not Displayed in Text Field");
			}

		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to Select"+l1Code+" L1 Code");
			Assert.fail("Unable to Select"+l1Code+" L1 Code");
		}
	}

	/**
	 * Description: Method to select L2 code
	 * 
	 * @author Ramya R
	 * @param l2Code
	 * 
	 */
	public synchronized void selectL2Code(String l2Code) {
		try {
			actionUtil.waitForAngularPageload();
			actionUtil.selectByText(ddL2,l2Code);

			if(ddL2.getText().contains(l2Code)) {

				actionUtil.info("selected "+l2Code+" is Displayed in Text Field");
				actionUtil.validationinfo("selected "+l2Code+" is Displayed in Text Field","blue");
			} else {
				actionUtil.fail("selected "+l2Code+" is not Displayed in Text Field");
				Assert.fail("selected "+l2Code+" is not Displayed in Text Field");
			}

		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to Select"+l2Code+" L2 Code");
			Assert.fail("Unable to Select"+l2Code+" L2 Code");
		}
	}

	/**
	 * Description: Method to select L3 code
	 * 
	 * @author Ramya R
	 * @param l3Code
	 * 
	 */
	public synchronized void selectL3Code(String l3Code) {
		try {
			actionUtil.waitForAngularPageload();
			actionUtil.selectByText(ddL3,l3Code);

			if(ddL3.getText().contains(l3Code)) {

				actionUtil.info("selected "+l3Code+" is Displayed in Text Field");
				actionUtil.validationinfo("selected "+l3Code+" is Displayed in Text Field","blue");
			} else {
				actionUtil.fail("selected "+l3Code+" is not Displayed in Text Field");
				Assert.fail("selected "+l3Code+" is not Displayed in Text Field");
			}

		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to Select"+l3Code+" L3 Code");
			Assert.fail("Unable to Select"+l3Code+" L3 Code");
		}
	}

	/**
	 * Description: Method to select L4 code
	 * 
	 * @author Ramya R
	 * @param l4Code
	 * 
	 */
	public synchronized void selectL4Code(String l4Code) {
		try {
			actionUtil.waitForAngularPageload();
			actionUtil.selectByText(ddL4,l4Code);

			if(ddL4.getText().contains(l4Code)) {

				actionUtil.info("selected "+l4Code+" is Displayed in Text Field");
				actionUtil.validationinfo("selected "+l4Code+" is Displayed in Text Field","blue");
			} else {
				actionUtil.fail("selected "+l4Code+" is not Displayed in Text Field");
				Assert.fail("selected "+l4Code+" is not Displayed in Text Field");
			}

		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to Select"+l4Code+" L4 Code");
			Assert.fail("Unable to Select"+l4Code+" L4 Code");
		}
	}

	/**
	 * Description: Method to select dash board radio button
	 * 
	 * @author Ramya R
	 * @param rdo
	 * 
	 */
	public synchronized void selectDashboardRdo(String rdo) {
		try {			
			WebActionUtil.clickOnElement(selectDashboard(rdo), "Dashboard RadioButton");
			if(selectDashboard(rdo).getAttribute("checked").equalsIgnoreCase("checked"))
			{
				actionUtil.info(selectDashboard(rdo) + " is selected");
				actionUtil.validationinfo(selectDashboard(rdo) + " is selected", "blue");
			} else {
				actionUtil.fail(selectDashboard(rdo) + " is not selected");
				Assert.fail(selectDashboard(rdo) + " is not selected");
			}
		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to Select"+rdo+" Dashboard");
			Assert.fail("Unable to Select"+rdo+" Dashboard");
		}
	}

	/**
	 * Description: Method to select calendar from date
	 * 
	 * @author Ramya R
	 * @param fromDate
	 * 
	 */
	private synchronized void selectCalendarFromDate(String fromDate) {
		try {
			String[] dateArr= fromDate.split("/");  
			String date=dateArr[0];
			String month=dateArr[1];
			String year=dateArr[2];

			actionUtil.clickOnElement(imgFromDate, "Image From Date");
			actionUtil.clickOnElement(ddMonth, "Month dropdown");
			actionUtil.clickOnElement(selectFromMonth(month), "From Month");
			actionUtil.clickOnElement(ddYear, "Year dropdown");
			actionUtil.clickOnElement(selectFromYear(year), "From Year");
			actionUtil.clickOnElement(selectFromDate(date), "From Date");

			if(txtDate.getText().contains(fromDate)) {
				actionUtil.info("selected "+fromDate+" is Displayed in Text Field");
				actionUtil.validationinfo("selected "+fromDate+" is Displayed in Text Field","blue");
			} else {
				actionUtil.fail("selected "+fromDate+" is not Displayed in Text Field");
				Assert.fail("selected "+fromDate+" is not Displayed in Text Field");
			}

		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to Select Calendar From Date");
			Assert.fail("Unable to Select Calendar From Date");
		}
	}

	/**
	 * Description: Method to select calendar to date
	 * 
	 * @author Ramya R
	 * @param toDate
	 * 
	 */
	private synchronized void selectCalendarToDate(String toDate) {
		try {
			String[] dateArr= toDate.split("/");  
			String date=dateArr[0];
			String month=dateArr[1];
			String year=dateArr[2];

			actionUtil.clickOnElement(imgToDate, "Image To Date");
			actionUtil.clickOnElement(ddMonth, "Month dropdown");
			actionUtil.clickOnElement(selectFromMonth(month), "To Month");
			actionUtil.clickOnElement(ddYear, "Year dropdown");
			actionUtil.clickOnElement(selectFromYear(year), "To Year");
			actionUtil.clickOnElement(selectFromDate(date), "To Date");

			if(txtDate.getText().contains(toDate)) {
				actionUtil.info("selected "+toDate+" is Displayed in Text Field");
				actionUtil.validationinfo("selected "+toDate+" is Displayed in Text Field","blue");
			} else {
				actionUtil.fail("selected "+toDate+" is not Displayed in Text Field");
				Assert.fail("selected "+toDate+" is not Displayed in Text Field");
			}

		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to Select Calendar To Date");
			Assert.fail("Unable to Select Calendar To Date");
		}
	}

	/**
	 * Description: Method to select calendar date
	 * 
	 * @author Ramya R
	 * @param fromDate,toDate
	 * 
	 */
	public synchronized void selectCalendarDate(String fromDate,String toDate) {
		try {
			selectCalendarFromDate(fromDate);
			selectCalendarToDate(toDate);

		} catch (Exception e) {
			actionUtil.error(e.getMessage());
			actionUtil.fail("Unable to Select Calendar Date");
			Assert.fail("Unable to Select Calendar Date");
		}
	}

}
