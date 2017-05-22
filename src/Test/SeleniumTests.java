package Test;

import static org.junit.Assert.*;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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
	public void setProperty(){
		
			System.setProperty("webdriver.gecko.driver", "C:/Users/JanJa/workspace/Selenium/geckodriver.exe");
			System.setProperty("webdriver.chrome.driver", "C:/Users/JanJa/workspace/Selenium/chromedriver.exe");
		}
	
	@Test
	public void chrome_Registration_Logout_Login() {
		
		String password  = "password";
		String email = generateEmail();
		
		WebDriver driver = new ChromeDriver();
		driver.get(TestEnvRegistration.url);
		
		TestEnvRegistration registration = PageFactory.initElements(driver, TestEnvRegistration.class);
		DashboardPage dashboard = registration.register(password, email, Language.randomValue());
		
		
		dashboard.waitUntilLoaded();
		Assert.assertEquals(DashboardPage.url, driver.getCurrentUrl());
		
		LoginPage loginPage = dashboard.logout();
		
		Assert.assertEquals(LoginPage.LogoutUrl, driver.getCurrentUrl());
		loginPage.login(email, password);
		
		Assert.assertEquals(DashboardPage.url+"#/", driver.getCurrentUrl());
		driver.close();
	}
	
	@Test
	public void firefox_Registration_Logout_Login() {
		
		String password  = "password";
		String email = generateEmail();
		
		WebDriver driver = new FirefoxDriver();
		driver.get(TestEnvRegistration.url);
		
		
		TestEnvRegistration registration = PageFactory.initElements(driver, TestEnvRegistration.class);
		DashboardPage dashboard = registration.register(password, email, Language.randomValue());
		
		
		Assert.assertEquals(DashboardPage.url, driver.getCurrentUrl());
		
		LoginPage loginPage = dashboard.logout();
		
		Assert.assertEquals(LoginPage.LogoutUrl, driver.getCurrentUrl());
		loginPage.login(email, password);
		
		Assert.assertEquals(DashboardPage.url+"#/", driver.getCurrentUrl());
		driver.close();
		
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
