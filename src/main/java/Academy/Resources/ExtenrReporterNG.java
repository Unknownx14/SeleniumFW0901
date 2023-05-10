package Academy.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtenrReporterNG {
	
	
	public static ExtentReports getReportObject()
	{
		String path =  System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
		sparkReporter.config().setReportName("Web Automation Results JK");
		sparkReporter.config().setDocumentTitle("Test Results JK");
		
		ExtentReports extent =  new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Tester", "Jovan Kovacevic");
		return extent;
	}
	

}
