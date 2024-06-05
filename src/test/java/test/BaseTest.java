package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import pageObject.LandingPage;

public class BaseTest {

	public static Properties prop;
	public static WebDriver driver;

	public static String browser = null;
	public static String url = null;
	public static String username = null;
	public static String password = null;
	public static String productName = null;
	public static String CartProductVerifyName = null;

	@BeforeTest(alwaysRun = true)
	public WebDriver LaunchBrowser() throws IOException {
		propertyFileLoading();
		String BrowserFromProperty = prop.getProperty("browser");
		String BrowserFromMavenComdLine = System.getProperty("browser");
		String browser = BrowserFromMavenComdLine != null ? BrowserFromMavenComdLine : BrowserFromMavenComdLine;

		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		String productName = prop.getProperty("productName");
		String CartProductVerifyName = prop.getProperty("CartProductVerifyName");

		if (BrowserFromProperty.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"D:\\Softwares\\Chrome_V_122\\chromedriver-win64\\chromedriver.exe");
			ChromeOptions opt = new ChromeOptions();
			File BinaryPath = new File("D:\\Softwares\\Chrome_V_122\\chrome-win64\\chrome.exe");
			opt.setBinary(BinaryPath);
			driver = new ChromeDriver(opt);

		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.get(url);
		System.out.println("The URL is : " + url + " opened ");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		System.out.println("Before Test Method to Open the browser is executed");
		return driver;
	}

	@AfterTest(alwaysRun = true)
	public void CloseBrowser() {
		driver.quit();
		System.out.println("After Test Method to close the browser is executed");
	}

	public Properties propertyFileLoading() throws IOException {
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "//config//config.properties");
		prop = new Properties();
		prop.load(file);
		return prop;
	}

	public String getScreenshot(String TestName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File Source = ts.getScreenshotAs(OutputType.FILE);
		String DestinatonPath = System.getProperty("user.dir") + "//screenshot//" + TestName + ".png";
		File Destination = new File(DestinatonPath);
		FileUtils.copyFile(Source, Destination);
		return DestinatonPath;

	}
}
