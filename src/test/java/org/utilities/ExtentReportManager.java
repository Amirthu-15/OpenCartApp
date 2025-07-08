package org.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testBase.BaseClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter extentSparkReporter;

	public ExtentReports extentReports;

	public ExtentTest extentTest;

	public String reportName;

	public void onStart(ITestContext context) {

		/*
		 * SimpleDateFormat simpleDateFormat = new
		 * SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); Date date = new Date(); String
		 * currentDateTimeStampformat = simpleDateFormat.format(date);
		 */

		// Or

		String currentDateTimeStampformat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportName = "Test-Reort-" + currentDateTimeStampformat + ".html";

		// extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")
		// + "\\reports\\myreports.html");
		extentSparkReporter = new ExtentSparkReporter(".\\Reports\\" + reportName);

		extentSparkReporter.config().setDocumentTitle("Automation Report"); // Title of report
		extentSparkReporter.config().setReportName("Functional Testing"); // Name of the report
		extentSparkReporter.config().setTheme(Theme.DARK);

		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);

		extentReports.setSystemInfo("Application", "Opencart");

		extentReports.setSystemInfo("Module", "Admin");

		extentReports.setSystemInfo("Sub Module", "Customers");

		extentReports.setSystemInfo("User Name", System.getProperty("user.name"));

		extentReports.setSystemInfo("Environment", "QA");

		String os = context.getCurrentXmlTest().getParameter("os");
		extentReports.setSystemInfo("Operating System", os);

		String browser = context.getCurrentXmlTest().getParameter("browser");
		extentReports.setSystemInfo("Browser Name", browser);

		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extentReports.setSystemInfo("Groups", includedGroups.toString());
		}

	}

	/*
	 * public void onTestStart(ITestResult result) {
	 * 
	 * System.out.println("The test is started");
	 * 
	 * }
	 */

	public void onTestSuccess(ITestResult result) {

		extentTest = extentReports.createTest(result.getTestClass().getName()); // Create a new entry in the report
		extentTest.assignCategory(result.getMethod().getGroups()); // To display groups in report
		extentTest.log(Status.PASS, "Test Case is PASSED:" + result.getName()); // Update Status

	}

	public void onTestFailure(ITestResult result) {

		extentTest = extentReports.createTest(result.getTestClass().getName()); // Create a new entry in the report
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.FAIL, "Test Case is FAILED:" + result.getName()); // Update Status
		extentTest.log(Status.FAIL, "Test Case is Failed:" + result.getThrowable().getMessage());

		try {
			String captureScreenPath = new BaseClass().captureScreen(result.getName());
			extentTest.addScreenCaptureFromPath(captureScreenPath);
		}

		catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {

		extentTest = extentReports.createTest(result.getTestClass().getName()); // Create a new entry in the report
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.SKIP, "Test Case is SKIPPD:" + result.getName()); // Update Status
		extentTest.log(Status.INFO, "Test Case is SKIPPD:" + result.getThrowable().getMessage()); // Update Status
	}

	public void onFinish(ITestContext context) {

		extentReports.flush();

		// To open the report automatically
		String pathOfExtentReport = System.getProperty("user.dir") + "\\Reports\\" + reportName;

		File extentReport = new File(pathOfExtentReport);

		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {

			
			e.printStackTrace();
		}

		/*try {
			URL url = new URL("file://" + System.getProperty("user.dir") + "\\Reports\\" + reportName);

			// create email message
			ImageHtmlEmail email = new ImageHtmlEmail();

			email.setDataSourceResolver(new DataSourceUrlResolver(url));

			email.setHostName("smtp.googlemail.com");

			email.setSmtpPort(465);

			email.setAuthenticator(new DefaultAuthenticator("amirtharajk05@gmail.com", "Akilaadhu@1101"));

			email.setSSLOnConnect(true);

			email.setFrom("amirtharajk05@gmail.com");

			email.setSubject("Test Reports");

			email.setMsg("Please find the attached report...");

			email.addTo("amirtharaj15790@gmail.com");

			email.attach(url, "extent report", "Please check the report");

			email.send();

		} catch (Exception e) {

			e.printStackTrace();
		}*/

	}

}
