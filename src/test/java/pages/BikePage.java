package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BikePage {
	
	
	WebDriver driver;

	
	public BikePage(WebDriver driver) {
		
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(linkText = "Upcoming Honda Bikes")
	WebElement bikeLink;
	
	@FindBy(xpath = "//*[contains (text(), 'Read More')]")
	WebElement readMore;
	
	@FindBy(xpath = "//span[@class='zw-cmn-loadMore']")
	WebElement viewMoreBike;
	
	
	public boolean checkBikeUrl()
	{
		try
		{
			bikeLink.isDisplayed();
			
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	
	public void findBikeLink()
	{
		try 
		{
			bikeLink.click();
			
		} catch (Exception e)
		{
			System.out.println("Visiting Bike Page Directly !!\n");
		}
	}
	
	public boolean checkReadMore()
	{
		if(readMore.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void clickReadMore(JavascriptExecutor jse)
	{
		jse.executeScript("arguments[0].click();", readMore);
	}
	
	public void clickViewMore(JavascriptExecutor jse)
	{
		jse.executeScript("arguments[0].click();", viewMoreBike);

	}

}
