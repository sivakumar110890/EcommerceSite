package other;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StandAloneTest {
	public static Properties prop;
	public static WebDriver driver;
	public static String ExpectedThanksMessage = "THANKYOU FOR THE ORDER.";

	public static void main(String[] args) throws IOException {
		propertyFileLoader();
		String browser = prop.getProperty("browser");
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("EDGE")) {
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("FIREFOX")) {
			driver = new FirefoxDriver();
		}
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		loginPage(driver, username, password);
		addToCart(driver);

	}

	public static void loginPage(WebDriver driver, String username, String password) {
		WebElement usernameInput = driver.findElement(By.id("userEmail"));
		usernameInput.sendKeys(username);

		WebElement passwordInput = driver.findElement(By.id("userPassword"));
		passwordInput.sendKeys(password);

		WebElement LoginButton = driver.findElement(By.id("login"));
		LoginButton.click();
	}

	public static void addToCart(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
		wait.until(ExpectedConditions.visibilityOfAllElements(products));

		WebElement prod = products.stream().filter(
				product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("adidas original"))
				.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

		WebElement cartButton = driver.findElement(By.cssSelector("[routerlink*='cart']"));
		cartButton.click();

		List<WebElement> addedProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean outputProduct = addedProducts.stream()
				.anyMatch(cartProducts -> cartProducts.getText().equalsIgnoreCase("adidas original"));
		Assert.assertTrue(outputProduct);

		WebElement CheckOutButton = driver.findElement(By.cssSelector(".totalRow button"));
		CheckOutButton.click();

		WebElement CountryName = driver.findElement(By.cssSelector("[placeholder*='Select Country']"));

		Actions act = new Actions(driver);
		act.sendKeys(CountryName, "India").build().perform();

		List<WebElement> IndiaOptions = driver.findElements(By.cssSelector(".ta-item"));

		for (WebElement button : IndiaOptions) {
			if (button.getText().equalsIgnoreCase("India")) {
				button.click();
			}
		}

		WebElement PlaceOrderButton = driver.findElement(By.cssSelector(".actions a"));
		PlaceOrderButton.click();

		String ActualThanksMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(ExpectedThanksMessage, ActualThanksMessage.toUpperCase());

	}

	public static Properties propertyFileLoader() throws IOException {
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "//config//config.properties");
		prop = new Properties();
		prop.load(file);
		return prop;
	}
}
