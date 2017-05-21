package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	final public static String url = "https://alfa.apsello.com/login";
	public static final Object LogoutUrl = "https://alfa.apsello.com/login#logout";
	
	private final WebDriver driver;
	@FindBy(name = "username")
	WebElement emailField;

	@FindBy(name = "password")
	WebElement passwordField;

	@FindBy(className = "logInBtn")
	WebElement loginButton;

	public LoginPage(WebDriver driver) {
		this.driver = driver;

		(new WebDriverWait(driver, 80)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				try {

					emailField = driver.findElement(By.name("username"));
					passwordField = driver.findElement(By.name("password"));
					loginButton = driver.findElement(By.className("logInBtn"));

				} catch (NoSuchElementException e) {
					System.out.println(driver.getCurrentUrl());
					System.out.println("passwordField " + passwordField);
					System.out.println("emailField " + emailField);
					return false;
				}
				return true;
			}
		});

	}

	public DashboardPage login(String email, String password) {

		ifLoginAgain();

		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		loginButton.click();
		while(driver.getCurrentUrl().equals("https://alfa.apsello.com/login#"))
		{	
			loginButton.click();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		
		
		return PageFactory.initElements(driver, DashboardPage.class);

	}

	private void ifLoginAgain() {
		try {
			driver.findElement(By.className("overlay ")).click();
		} catch (NoSuchElementException e) {
		}
	}

}
