package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement usernameInput;

	@FindBy(id = "userPassword")
	WebElement passwordInput;

	@FindBy(id = "login")
	WebElement LoginButton;

	@FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
	WebElement WE_IncorrectEmailOrPasswordMessage;

	public static By BY_IncorrectEmailOrPasswordMessage = By.xpath("//div[@aria-label='Incorrect email or password.']");

	public void LoginAction(String username, String password) {
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		LoginButton.click();
	}

	public String GetInvalidUserMessageString() {
		AbstractComponent abstractComponent = new AbstractComponent(driver);
		abstractComponent.WaitvisibilityOfElementLocated(BY_IncorrectEmailOrPasswordMessage);
		String InvalidCredentialMessage = WE_IncorrectEmailOrPasswordMessage.getText();
		return InvalidCredentialMessage;
	}
}
