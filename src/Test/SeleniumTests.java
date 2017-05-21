package Test;

import static org.junit.Assert.*;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import Pages.DashboardPage;
import Pages.Language;
import Pages.LoginPage;
import Pages.TestEnvRegistration;

public class SeleniumTests {

	
	@Before
	public void main(){
		System.setProperty("webdriver.gecko.driver", "C:/Users/JanJa/workspace/Selenium/geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "C:/Users/JanJa/workspace/Selenium/chromedriver.exe");
	}
	
	
	
	@Test
	public void chromeRegistration() {
		
		String password  = "password";
		String email = generateEmail();
		WebDriver driver = new ChromeDriver();
		shouldRegister(password, email, driver);
	}
	
	@Test
	public void firefoxRegistration() {
		
		String password  = "password";
		String email = generateEmail();
		WebDriver driver = new FirefoxDriver();
		shouldRegister(password, email, driver);
	}
	

	private void shouldRegister(String password, String email, WebDriver driver) {
		driver.get(TestEnvRegistration.url);
		TestEnvRegistration registration = PageFactory.initElements(driver, TestEnvRegistration.class);
		DashboardPage dashboard = registration.register(password, email, Language.randomValue());
		dashboard.waitUntilLoaded();
		LoginPage loginPage = dashboard.logout();
		loginPage.login(email, password);
		driver.quit();
	}
	
	
	
	private static String generateEmail() {
		return randomString(6, 8) + "@no-spam.ws";
	}
	
	private static String randomString(int minLength, int maxLength) {
		int length = ThreadLocalRandom.current().nextInt(minLength, maxLength + 1);
		// String bigChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String smallChars = "abcdefghijklmnopqrstuvwxyz";
		// String numbers = "1234567890";

		String characters = smallChars;// + bigChars + numbers;
		Random random = new SecureRandom();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(characters.charAt(random.nextInt(characters.length())));
		}

		return sb.toString();
	}
}
