package Pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

	final public static String url = "https://alfa.apsello.com/Dashboard/index.html";
	
	private final WebDriver driver;
	
	
	
	WebElement greetingLabel;
	WebElement button;
	
	public DashboardPage(WebDriver driver) {
	
		this.driver = driver;
	}
	
	public LoginPage logout()
	{
		
		while(!button.isEnabled() || !button.isDisplayed())
		{
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		button.click();
		while(driver.getCurrentUrl().equals("https://alfa.apsello.com/Dashboard/index.html#/"))
		{	
			button.click();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		return PageFactory.initElements(driver, LoginPage.class);
	}
	
	
	
	public void waitUntilLoaded()
	{
		(new WebDriverWait(driver, 80)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				try {
					 button = driver.findElement(By.className("mousePointer"));
					 greetingLabel = driver.findElement(By.className("greetingsText"));

				} catch (NoSuchElementException e) {
					
					System.out.println(button);
					System.out.println(greetingLabel);
					return false;
				}
				return true;
			}
		});
		
		
		System.out.println("PAGE LOADED");
	}
}
