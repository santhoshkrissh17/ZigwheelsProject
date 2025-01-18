package testpack;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestExecution {

	static List<List<String>> getElementList(WebDriver driver)
	{
		// getting price elements into a list
		List<WebElement> bikesGetPrice = driver.findElements(By.xpath("//li[contains(@class,'col-lg-4 ')]"));
		// getting name elements into a list
		List<WebElement> bikesGetName = driver.findElements(By.xpath("//a[@data-track-label=\"model-name\"]"));
		// getting date elements into a list
		List<WebElement> bikesGetDate = driver.findElements(By.xpath("//div[@class=\"clr-try fnt-14\"]"));
		
		// creating 2D list and adding first row for headings
		List<List<String>>bikeDetails = new ArrayList<>();
		bikeDetails.add(List.of("Model Name", "Price", "Expected Launch Date"));
		
		// populate 2D list
				for (int i = 0; i < bikesGetPrice.size(); i++)
				{
					WebElement elePrice = bikesGetPrice.get(i);
					String price = elePrice.getAttribute("data-price");
					
					if (Integer.parseInt(price) < 400001) {
						String name = bikesGetName.get(i).getAttribute("title");
						String date = bikesGetDate.get(i).getText();
						bikeDetails.add(List.of(name, price, date));
					}
				}
		return bikeDetails;
	}
	
	static List<List<String>> carList(WebDriver driver, List<List<String>> carDetails)
	{
		for(int i=1;i<=5;i++)
        {
			
			String carName = driver.findElement(By.xpath("//table[@class=\"tbl bdr\"]/tbody/tr[" + i + "]/td[1]")).getText();
			String carPrice =driver.findElement(By.xpath("//table[@class=\"tbl bdr\"]/tbody/tr[" + i + "]/td[2]")).getText();
			
			carDetails.add(List.of(carName, carPrice));
		}
		
		return carDetails;
	}
}
