package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;

	By waitTillPlaceOrderButton = By.cssSelector(".actions a");

	@FindBy(css = ".actions a")
	WebElement PlaceOrderButton;

	@FindBy(css = ".hero-primary")
	WebElement ThanksMessageText;

	By WaitTill = By.cssSelector(".hero-primary");
	By WaitTillCheckout = By.cssSelector(".totalRow button");

	//
	@FindBy(css = ".totalRow button")
	WebElement CheckOutButton;

	@FindBy(css = "[placeholder*='Select Country']")
	WebElement CountryName;

	@FindBy(css = ".ta-item")
	List<WebElement> IndiaPossibleOptions;

	public CheckOutPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void CheckOutPageActions() throws InterruptedException {
		WaitvisibilityOfElementLocated(WaitTillCheckout);
		CheckOutButton.click();
		System.out.println("CheckOut Button is clicked");
		Actions act = new Actions(driver);
		act.sendKeys(CountryName, "India").build().perform();
		Thread.sleep(2000);
		for (WebElement button : IndiaPossibleOptions) {
			if (button.getText().equalsIgnoreCase("India")) {
				button.click();
			}
		}
		Thread.sleep(5000);
		WaitinvisibilityOfElementLocated(waitTillPlaceOrderButton);
		PlaceOrderButton.click();

	}

	public String OrderConfirmPage() {
		WaitvisibilityOfElementLocated(WaitTill);
		String ActualThanksMessage = ThanksMessageText.getText();
		return ActualThanksMessage;
	}
}
