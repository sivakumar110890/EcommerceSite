package abstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

	WebDriver driver;
	WebDriverWait wait;
	WebElement Clickable;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	public void waitTillvisibilityOfAllElements(List<WebElement> WebElementss) {

		wait.until(ExpectedConditions.visibilityOfAllElements(WebElementss));
	}

	public void WaitvisibilityOfElementLocated(By Locator) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));

	}

	public void WaitinvisibilityOfElementLocated(By Locator) {

		wait.until(ExpectedConditions.invisibilityOfElementLocated(Locator));
	}
	
	public void WaitTillclickable(WebElement Clickable) {
		wait.until(ExpectedConditions.elementToBeClickable(Clickable));
	}

	// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
}
