package resource;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportTestNG {

	public static ExtentSparkReporter reporter;
	public static ExtentReports extent;

	public static ExtentReports getReportObject() {

		File path = new File(System.getProperty("user.dir") + "//reports//index.html");
		reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Ecommerce site");
		reporter.config().setReportName("Ecommerce site Click Order");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Author", "Sivakumar A");
		return extent;

	}
}
