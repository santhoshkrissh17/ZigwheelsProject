package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CarPage {
	
WebDriver driver;

	
	public CarPage(WebDriver driver) {
		
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//*[@id='Header']/div/div[1]/div[1]/a/img")
	WebElement logoLink;

	@FindBy(id = "headerSearch")
	WebElement searchBox;
	
	@FindBy(linkText = "Used Cars in Chennai")
	WebElement carLink;
	
	@FindBy(xpath = "//span[text()='Read More']")
	WebElement readMoreOption;
	
	@FindBy(xpath = "//*[@id='Header']/div/div[1]/div[1]/a/img")
	WebElement homeLogo;
	
	
	public void goToHome()
	{
		if(logoLink.isDisplayed())
		{
			logoLink.click();
		}
		
	}
	
	public void searchCarPrompt(String key)
	{
		if(searchBox.isDisplayed())
		{
			searchBox.sendKeys(key);
	        searchBox.sendKeys(Keys.ENTER);
		}
	}
	
	public boolean checkCarUrl()
	{
		return carLink.isDisplayed();
	}
	
	public void findCarLink()
	{
		try 
		{
			carLink.click();
			
		} catch (Exception e)
		{
			System.out.println("Visiting Car Page Directly !!\n");
		}
	}
	
	public void selectReadMore(JavascriptExecutor jse)
	{
		try
		{
			readMoreOption.click();
		}
		catch(Exception e)
		{
			jse.executeScript("arguments[0].click();", readMoreOption);
		}
	}
	
	public void clickLogo()
	{
		if(homeLogo.isDisplayed())
		{
			homeLogo.click();
		}
	}
}
