package pageObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {
	WebDriver driver;
	Properties prop;

	@FindBy(css = "[routerlink*=\"myorders\"]")
	public WebElement OrdersButton;

	@FindBy(css = "tr td:nth-child(3)")
	public List<WebElement> ProductNames;

	public OrdersPage(WebDriver driver) throws IOException {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		PropertyFileLoader();
		// TODO Auto-generated constructor stub
	}

	public Boolean VerifytheProduct() {
		OrdersButton.click();
		waitTillvisibilityOfAllElements(ProductNames);
		String productName=prop.getProperty("productName");
		Boolean OrderPageMatch=ProductNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return OrderPageMatch;
		
	}

	public Properties PropertyFileLoader() throws IOException {
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//config//config.properties");
		prop = new Properties();
		prop.load(file);
		return prop;
		
		

	}

}
