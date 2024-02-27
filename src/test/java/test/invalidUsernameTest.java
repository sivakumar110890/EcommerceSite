package test;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import abstractComponents.AbstractComponent;
import pageObject.LandingPage;

public class invalidUsernameTest extends BaseTest {

	@Test(retryAnalyzer = RetryFailedTest.class)
	public void InvalidUsernameLoginTest() {
		LandingPage landingPage = new LandingPage(driver);
		landingPage.LoginAction("InvalidUsernam@dummy.com", "Twentylakhs@098765432");
		String InvalidCredMessage = landingPage.GetInvalidUserMessageString();
		Assert.assertEquals("Incorrect email or password.", InvalidCredMessage);
	}

}
