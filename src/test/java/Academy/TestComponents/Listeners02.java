package Academy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Academy.Resources.ExtenrReporterNG;

public class Listeners02 extends BaseTest03 implements ITestListener {
	
	
	ExtentReports extent = ExtenrReporterNG.getReportObject(); //This is the method from the ExtentReporterNG class
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread safe for running TCs in parallel
	//Now we replace test. with extentTest.get(). in every @Override, except in the onTestStart
	

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		 test = extent.createTest(result.getMethod().getMethodName()); //result.getMethod().getMethodName() this gives us the name of a testcase
		 
		 extentTest.set(test); //Thread safe assigns one unique thread ID
		 
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test has passed.");
		
		
		//Ovo sam ja ubacio i dodao +System.currentTimeMillis()
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} //This for the driver to get a life, it comes from every TC since we have @BeforeMethod which initializes the driver
		
		
		
		//Take a Screenshot and attach it to the report
		String filePath =null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName()+System.currentTimeMillis(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// The method from the BaseTest02
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName()+System.currentTimeMillis());
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		extentTest.get().log(Status.FAIL, "Test has failed.");
		extentTest.get().fail(result.getThrowable()); //This is to get the error message when a test fails
		
		
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} //This for the driver to get a life, it comes from every TC since we have @BeforeMethod which initializes the driver
		
		
		
		//Take a Screenshot and attach it to the report
		String filePath =null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// The method from the BaseTest02
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.INFO, "Test has failed (or has been skipped) in the initial runtime, therefore it wil be re-tested one more time. Please check out"
				+ "the results of this TC in the following re-tested TC (the one that has #test-id increased by 1)");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
		extent.flush(); //With this flush() the Extent report is generated
	}
	
	
	
	

}
