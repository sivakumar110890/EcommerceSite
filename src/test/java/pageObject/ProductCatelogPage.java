package pageObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import abstractComponents.AbstractComponent;

public class ProductCatelogPage extends AbstractComponent {
	WebDriver driver;

	public ProductCatelogPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".col-lg-4")
	List<WebElement> products;

	By ToastAppear = By.cssSelector("#toast-container");
	By AnimationDisappear = By.cssSelector(".ng-animating");

	By AvailableProducts = By.cssSelector("b");
	By AddToCartButton = By.cssSelector(".card-body button:last-of-type");

	public WebElement getProductByName(String productName) {
		waitTillvisibilityOfAllElements(products);
		WebElement prod = products.stream()
				.filter(product -> product.findElement(AvailableProducts).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		return prod;
	}

	public void AddProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(AddToCartButton).click();
		WaitvisibilityOfElementLocated(ToastAppear);
		WaitinvisibilityOfElementLocated(AnimationDisappear);
	}

}
