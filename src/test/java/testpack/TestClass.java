package testpack;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.BikePage;
import pages.CarPage;
import pages.HomePage;
import pages.LoginPage;
import utility.ExcelUtility;
import utility.PropertyUtility;

public class TestClass {
	
	
	WebDriver driver;
	JavascriptExecutor jse;
	
	ChromeOptions chromeObj;
	EdgeOptions edgeObj;
	
	String browserType;
	HomePage home;
	BikePage bike;
	CarPage car;
	LoginPage login;
	
	List<List<String>> bikeDetails;
	List<List<String>> carDetails;
	
	
	@BeforeClass
	@Parameters({"browser"})
	public void setupDriver(String br)
	{
		browserType = br;
		
		switch(browserType)
		{
		case "Chrome" : System.out.println("\nRunning Test Cases in Chrome Browser !!!\n\n");
						chromeObj = new ChromeOptions();
						chromeObj.addArguments("--disable-notifications");
						driver = new ChromeDriver(chromeObj);
						break;
						
		case "Edge" :   System.out.println("\nRunning Test Cases in Edge Browser !!!\n\n");
						edgeObj = new EdgeOptions();
						edgeObj.addArguments("--disable-notifications");
						driver = new EdgeDriver(edgeObj);
						break;
						
		default : System.out.println("Invalid Browser !!!");
		return;
		}
		
		driver.get("https://www.zigwheels.com/");
		
	    driver.manage().window().maximize();
	    
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    
	    jse = (JavascriptExecutor)driver;
	}
	
	
	@Test(priority = 1)
	public void openWebsite() throws IOException {
		
		home = new HomePage(driver);

		home.openURL();
	}
	
	
	@Test(priority = 2)
	public void validateUrl() {
		
		String actualLink = home.getUrl();
		
		String expectedLink = "https://www.zigwheels.com/";
		
		Assert.assertEquals(expectedLink, actualLink);
	}
	
	
	@Test(priority = 3, dependsOnMethods = {"openWebsite"})
	public void validateLogo() throws InterruptedException {
		
		boolean logoVisible = home.checkLogo();
		
		Assert.assertTrue(logoVisible);
	}
	
	
	@Test(priority = 4)
	public void validateSearchBox()
	{
		boolean searchBoxVisible = home.checkSearchBox();
		
		Assert.assertTrue(searchBoxVisible);
	}
	

	@Test(priority = 5, dependsOnMethods = {"validateSearchBox"})
	public void searchBikes() throws IOException {
		
		String key = PropertyUtility.findProperties("BikePrompt");
		
		home.searchVehicle(key);
	}
	
	
	@Test(priority = 6, dependsOnMethods = {"searchBikes"})
	public void visitBikeLink() {
		
		bike = new BikePage(driver);
		
		boolean bikeUrl = bike.checkBikeUrl();
		
		if(bikeUrl)
		{
			bike.findBikeLink();
		}
	}
	
	
	@Test(priority = 7)
	public void clickReadMoreOption()
	{
		if(bike.checkReadMore())
		{
			bike.clickReadMore(jse);
		}
	}
	
	
	@Test(priority = 8, dependsOnMethods = {"clickReadMoreOption"})
	public void validateViewMoreOption()
	{
		jse.executeScript("window.scrollBy(0,1800)");
		
		bike.clickViewMore(jse);
	}
	
	
	@Test(priority = 9, dependsOnMethods = {"validateViewMoreOption"})
	public void getBikeDetails()
	{	
		bikeDetails = TestExecution.getElementList(driver);
		
		System.out.println("Bike Details retrieved Successfully !!");
	}
	
	
	@Test(priority = 10, dependsOnMethods = {"getBikeDetails"})
	public void writeBikeData() throws IOException
	{
		ExcelUtility.write(bikeDetails, "Bike", browserType);
		
	    System.out.println("Bike Details Written to Excel Successfully !!\n");
	}
	
	
	@Test(priority = 11)
	public void returnToHome()
	{
		car = new CarPage(driver);
		
		car.goToHome();
		
		System.out.println("Driver Returned to Home Page !!\n");
	}
	
	
	@Test(priority = 12)
	public void searchCar() throws IOException
	{
		String key = PropertyUtility.findProperties("CarPrompt");
		
		car.searchCarPrompt(key);
	}
	
	
	@Test(priority = 13 , dependsOnMethods = {"searchCar"})
	public void visitCarLink()
	{
		boolean carUrl = car.checkCarUrl();
		
		if(carUrl)
		{
			car.findCarLink();
		}
	}
	
	
	@Test(priority = 14)
	public void getCarDetails()
	{
		car.selectReadMore(jse);
		
		carDetails = new ArrayList<>();
		
		carDetails.add(List.of("Name of Popular Used Cars", "Price"));
		
		carDetails = TestExecution.carList(driver, carDetails);
		
		System.out.println("Car Details retrieved Successfully !!");
	}
	
	
	@Test(priority = 15, dependsOnMethods = {"getCarDetails"})
	public void writeCarData() throws IOException
	{
		ExcelUtility.write(carDetails, "Car", browserType);
		
		System.out.println("Car Details Written to Excel Successfully !!\n");
	}
	
	
	@Test(priority = 16)
	public void goToMain()
	{
		car.clickLogo();
		
		System.out.println("Driver Returned to Home Page !!\n");
	}
	
	
	@Test(priority = 17)
	public void s() throws InterruptedException
	{
		login = new LoginPage(driver);
		
		boolean loginVisible = login.checkLoginButton();
		
		if(loginVisible)
		{
			login.clickLoginButton(jse);	
		}
		
		Assert.assertTrue(loginVisible);
	}
	
	
	@Test(priority = 18)
	public void selectGoogleButton() throws InterruptedException
	{
		boolean googleVisible = login.checkGoogleButton();
		
		if(googleVisible)
		{
			login.clickGoogleButton(jse);	
		}
		
		Assert.assertTrue(googleVisible);
	}
	
	
	@Test(priority = 19, dependsOnMethods = {"selectGoogleButton"})
	public void tryInvalidLogin() throws IOException, InterruptedException
	{
		Set<String> windowIDs = driver.getWindowHandles();
		List<String> windowIDList = new ArrayList(windowIDs);
		
		driver.switchTo().window(windowIDList.get(1));
		
		String invalidEmail = PropertyUtility.findProperties("InvalidEmail");
		
		login.enterCredentials(invalidEmail);
	}
	
	
	@Test(priority = 20, dependsOnMethods = {"tryInvalidLogin"})
	public void captureErrorMessage() throws InterruptedException
	{
		login.captureScreenshot(driver, browserType);
		
		System.out.println("\nError Message Captured Successfully !!\n");
	}
	
	
	@AfterTest
	public void closeBrowser() throws InterruptedException
	{
		Thread.sleep(2000);
		
		driver.quit();
	}
}
