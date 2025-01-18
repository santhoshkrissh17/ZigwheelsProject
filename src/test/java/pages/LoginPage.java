package pages;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//div[@id='forum_login_title_lg']")
	WebElement loginButton;
	
	@FindBy(xpath = "//*[@id='myModal3-modal-content']/div[1]/div/div[3]/div[6]/div")
	WebElement googleButton;
	
	@FindBy(xpath = "//input[@class='whsOnd zHQkBf']")
	WebElement emailInputBox;
	

	public boolean checkLoginButton() throws InterruptedException
	{
		Thread.sleep(3000);
		
		return loginButton.isDisplayed();
	}
	
	public void clickLoginButton(JavascriptExecutor jse)
	{
		try
		{
			loginButton.click();
		}
		catch(Exception e)
		{
			jse.executeScript("arguments[0].click();", loginButton);
		}
	}
	
	public boolean checkGoogleButton() throws InterruptedException
	{
		Thread.sleep(3000);
		
		return googleButton.isDisplayed();
	}
	
	public void clickGoogleButton(JavascriptExecutor jse) throws InterruptedException
	{
		jse.executeScript("arguments[0].click();", googleButton);
		
		Thread.sleep(3000);
	}
	
	public void enterCredentials(String invalidEmail) throws InterruptedException
	{
		emailInputBox.sendKeys(invalidEmail);
		
	    emailInputBox.sendKeys(Keys.ENTER);
	    
	    Thread.sleep(2000);
	}
	
	public void captureScreenshot(WebDriver driver, String browserType) throws InterruptedException
	{
		Thread.sleep(2000);
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		
		String path = "C:\\Users\\2373658\\eclipse-workspace\\ZigwheelsProject\\src\\test\\java\\files\\"
				+ browserType + "_Screenshot.png";
		
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		File target = new File(path);
		
		source.renameTo(target);
	}

}
