package reusablecomponents;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testbase.ObjectRepositories;

public class ExtentSetup extends ObjectRepositories {
	
	public static ExtentReports setupExtentReport() {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);
		
		String reportPath = System.getProperty("user.dir")+"/src/test/resources/testreports/Reports"+actualDate+".html";
		
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportPath);
		
		 extent = new ExtentReports();
		extent.attachReporter(sparkReport);
		
		sparkReport.config().setDocumentTitle("TestReport");
		sparkReport.config().setTheme(Theme.DARK);
		sparkReport.config().setReportName("ReportName");
		
		return extent;
	}

}
