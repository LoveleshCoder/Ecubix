package com.watch.utilities;

import java.io.File;
import java.time.LocalDate;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

	private static ExtentReports report;
	private static ExtentSparkReporter reporter;

	public static ExtentReports getReportObject() {
		if (report == null) {
			String reportPath = System.getProperty("user.dir") + "//reports//index"+getCurrentDate()+".html";
			reporter = new ExtentSparkReporter(new File(reportPath));
			reporter.config().setDocumentTitle("Test Results");
			reporter.config().setReportName("Watch: Automation Report");
			report = new ExtentReports();
			report.attachReporter(reporter);
			report.setSystemInfo("OS", System.getProperty("os.name"));
		}
		return report;
	}
	
	
	public static String getCurrentDate() {
		return LocalDate.now().toString();
	}

}
