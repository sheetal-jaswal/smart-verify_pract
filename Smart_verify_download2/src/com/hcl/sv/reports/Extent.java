
package com.hcl.sv.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.hcl.sv.baseutil.BaseTest;

/**
 * Description: Implements the creation of the Extent Html report with specified
 * name after loading the Extent config file from specified location.
 * 
 * @author Manikanda A
 */
public class Extent {

	private static ExtentTest extentTtest;
	private static ExtentReports extentReports;

	/**
	 * Description : Creates the HTML report based on the name specified
	 * 
	 * @author Manikandan A
	 * @param name
	 */
	public static ExtentTest createTest(String name) {

		extentTtest = extentReports.createTest(name);
		return extentTtest;
	}

	public static ExtentReports getExtent() {
		return extentReports;
	}

	/**
	 * Description : Initializes report specified in the file path
	 * 
	 * @author Manikandan A
	 * @param filepath
	 */
	public void initReport(String filepath) {
		extentReports = new Extent().getExtent(filepath);
		BaseTest.logger.info("Report Initiated");
	}

	/**
	 * Description: Creates of HTML report in specified path
	 * 
	 * @author Manikandan A
	 */
	public ExtentReports getExtent(String filepath) {

		if (extentReports == null) {
			try {
				extentReports = new ExtentReports();
				extentReports.attachReporter(getHtmlReporter(filepath + "/AutomationReport.html"));
				return extentReports;
			} catch (Exception e) {
				BaseTest.logger.info("Exception while creating report html file.");
			}
		}
		return extentReports;
	}

	/**
	 * Description :Loads the Extent-config file specified from the location
	 * 
	 * @author Manikandan A
	 */
	private static ExtentHtmlReporter getHtmlReporter(String filePath) {

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "./config\\extent-config.xml");
		return htmlReporter;
	}
}
