package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;

public class TestEnvRegistration {

	final public static String url = "https://alfa.apsello.com/TestEnvRegistration/index.html";
	private final WebDriver driver;

	@FindBy(name = "password")
	WebElement passwordField;

	@FindBy(name = "email")
	WebElement emailField;

	@FindBy(name = "language")
	WebElement languageField;

	@FindBy(xpath = "/html/body/div/form/input[8]")
	WebElement submitButton;

	public TestEnvRegistration(WebDriver driver) {
		this.driver = driver;
	}

	public DashboardPage register(String password, String email, String language) {
		passwordField.sendKeys(password);
		setEmail(email);
		setLanguage(language);
		submitButton.click();
		return  PageFactory.initElements(driver, DashboardPage.class);
	}

	public void setEmail(String email) {
		emailField.clear();
		emailField.sendKeys(email);
	}
	public void setLanguage(String language) {
		languageField.clear();
		languageField.sendKeys(language);
	}
	
	
}
