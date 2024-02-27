package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartButton;

	@FindBy(css = ".cartSection h3")
	private List<WebElement> AddedProductsList;

	@FindBy(css = ".actions a")
	WebElement PlaceOrderButton;

	@FindBy(css = ".hero-primary")
	WebElement ThanksMessageText;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean CartButtonVerifyProduct(String CartProductVerifyName) {
		cartButton.click();

		Boolean Output = AddedProductsList.stream()
				.anyMatch(cartProducts -> cartProducts.getText().equalsIgnoreCase(CartProductVerifyName));

		System.out.println("Product name is matching : " + Output);
		return Output;
	}

}
