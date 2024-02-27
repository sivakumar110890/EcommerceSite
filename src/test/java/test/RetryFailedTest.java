package test;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTest implements IRetryAnalyzer {

	int count = 0;
	int MaxCount = 2;

	public boolean retry(ITestResult result) {
		if (count < MaxCount) {
			count++;
			return true;
		}
		return false;
	}

}
