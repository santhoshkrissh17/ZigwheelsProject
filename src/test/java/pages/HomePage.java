package pages;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.PropertyUtility;

public class HomePage {
	
	
	WebDriver driver;

	public HomePage(WebDriver driver) {
		
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//*[@id='Header']/div/div[1]/div[1]/a/img")
	WebElement logo;
	
	@FindBy(id = "headerSearch")
	WebElement searchBox;

	
	public void openURL() throws IOException {
		
		String url = PropertyUtility.findProperties("WebLink");
		
		driver.get(url);
		
		driver.manage().window().maximize();
	}
	
	
	public String getUrl() {
		
		return driver.getCurrentUrl();
	}
	
	
	public boolean checkLogo() {
		
		return logo.isDisplayed();
	}
	
	
	public boolean checkSearchBox() {

		return searchBox.isDisplayed();
	}
	
	
	public void searchVehicle(String key)
	{
		searchBox.sendKeys(key);
	    searchBox.sendKeys(Keys.ENTER);
	    
	}

}
