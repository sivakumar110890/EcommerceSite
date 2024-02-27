package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import abstractComponents.AbstractComponent;
import pageObject.CartPage;
import pageObject.CheckOutPage;
import pageObject.LandingPage;
import pageObject.OrdersPage;
import pageObject.ProductCatelogPage;

public class SubmitOrderTest3 extends BaseTest {
	public static String ExpectedThanksMessage = "THANKYOU FOR THE ORDER.";

	@Test(dataProvider = "DataProviderSubmitTest",retryAnalyzer = RetryFailedTest.class)
	public void SubmitOrderTest1(HashMap<String, String> inputFromDataProvider)
			throws IOException, InterruptedException {
		propertyFileLoading();
		String browser = prop.getProperty("browser");
		String url = prop.getProperty("url");
		String username = inputFromDataProvider.get("username");
		String password = inputFromDataProvider.get("password");
		String productName = inputFromDataProvider.get("productName");
		String CartProductVerifyName = prop.getProperty("CartProductVerifyName");

		LandingPage LandingPage = new LandingPage(driver);
		System.out.println("Landing page is opened");
		LandingPage.LoginAction(username, password);

		ProductCatelogPage ProductCatelogPage = new ProductCatelogPage(driver);
		ProductCatelogPage.getProductByName(productName);
		ProductCatelogPage.AddProductToCart(productName);

		CartPage CartPage = new CartPage(driver);
		Boolean Output1 = CartPage.CartButtonVerifyProduct(CartProductVerifyName);

		if (Output1 == true) {
			System.out.println("The Product name is matching ");
		} else {
			System.out.println("The product name does not match ");
		}
		// org.testng.Assert.assertTrue(Output1);

	}

	@Test(dependsOnMethods = "SubmitOrderTest1",retryAnalyzer=RetryFailedTest.class)
	public void CheckOutMethod() throws InterruptedException, IOException {
		CheckOutPage CheckOutPage = new CheckOutPage(driver);
		CheckOutPage.CheckOutPageActions();

		String ActualThanksMessage = CheckOutPage.OrderConfirmPage();
		Assert.assertEquals(ExpectedThanksMessage, ActualThanksMessage.toUpperCase());

		OrdersPage ordersPage = new OrdersPage(driver);
		org.testng.Assert.assertTrue(ordersPage.VerifytheProduct());

	}

	@DataProvider
	public static Object[][] DataProviderSubmitTest() {
		// return new Object[] []
		// {{"dummytest@dummy.com","Twentylakhs@098765432","IPHONE 13
		// PRO"},{"dummytest@dummy.com","Twentylakhs@098765432","IPHONE 13 PRO"}};
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("username", "dummytest@dummy.com");
		map.put("password", "Twentylakhs@098765432");
		map.put("productName ", "IPHONE 13 PRO");

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("username", "dummytest@dummy.com");
		map1.put("password", "Twentylakhs@098765432");
		map1.put("productName ", "ADIDAS ORIGINAL");

		return new Object[][] { { map }, { map1 } };
	}
	
	public String getScreenshot(String TestCaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		String Path = System.getProperty("user.dir")+"Screenshot"+TestCaseName+".png";
		File source = new File(Path);
		File destination=ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, destination);
		return Path;
		
	}
}
