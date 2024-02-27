package test;

import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resource.ExtentReportTestNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentReports extent = ExtentReportTestNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);

		// not implemented
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test is PASSED");
		// not implemented
	}

	public void onTestFailure(ITestResult result) {
		extentTest.get().log(Status.FAIL, "Test is FAILED");
		extentTest.get().fail(result.getThrowable());
		
		// driver =
		// (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance);

		String Filepath = null;
		try {
			Filepath = getScreenshot(result.getMethod().getMethodName()); // Taking a screenshot

		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(Filepath, result.getMethod().getMethodName());
	}

	public void onTestSkipped(ITestResult result) {
		// not implemented
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not implemented
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

	public void onStart(ITestContext context) {
		// not implemented
	}

	public void onFinish(ITestContext context) {
		// not implemented
		extent.flush();
	}

}
