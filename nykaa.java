package week4.assing;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class nykaa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		
		//Load the url
		driver.get("https://www.nykaa.com/"); 
		
		//Maximize the screen
		driver.manage().window().maximize();
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[text()='brands']"))).perform();
		
		action.moveToElement(driver.findElement(By.xpath("//a[text()='Popular']"))).perform();
		
		driver.findElement(By.xpath("//img[@src='https://adn-static2.nykaa.com/media/wysiwyg/2019/lorealparis.png']")).click();
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> secwindow=new ArrayList<String>(windowHandles);
		
		//Now Switch to Newly opened window
		driver.switchTo().window(secwindow.get(1));
		//Thread.sleep(1000);
		
		driver.findElement(By.xpath("//i[@class='fa fa-angle-down']")).click();
		//Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
		//Thread.sleep(1000);
		//Click Category and Click shampoo
		driver.findElement(By.xpath("//div[text()='Category']")).click();
		//Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		//Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		//Thread.sleep(1000);
		
		//To check Filter for shampoo
		String filterText = driver.findElement(By.xpath("//li[text()='Shampoo']")).getText();
		if(filterText.contains("Shampoo"))
		{
			System.out.println("Filter is applied with Shampoo");
		}
		else
		{
	System.out.println("Filter applied is incorrect");
		}
		WebElement searchbox = driver.findElement(By.xpath("//input[@type='search']"));
		
		searchbox.sendKeys(" L'Oreal Paris Colour Protect Shampoo");
		searchbox.sendKeys(Keys.ENTER);
		
		WebElement shampoo = driver.findElement(By.xpath("//img[@class='listing-img']"));
		shampoo.click();
		
		Set<String> allWindowhandle = driver.getWindowHandles();
		List<String> viewItemPageWindow=new ArrayList<String>(allWindowhandle);
		String viewpagewindow = viewItemPageWindow.get(2);
		driver.switchTo().window(viewpagewindow);
		WebElement shampooSize = driver.findElement(By.xpath("(//span[@class='size-pallets'])[2]"));
		shampooSize.click();
		String mrp = driver.findElement(By.xpath("//span[text()='135']")).getText();
		System.out.println("MRP " +mrp);
		//Add to Bag
		driver.findElement(By.xpath("//button[text()='ADD TO BAG']")).click();
		//Go to shopping bag
		driver.findElement(By.className("AddBagIcon")).click();
		
		String grandTotal = driver.findElement(By.xpath("//div[text()='205']")).getText();
		grandTotal=grandTotal.replaceAll("\\D", "").trim();
		
		
		int grandtotinshoppingpage = Integer.parseInt(grandTotal);
		System.out.println("Grandtotal on shopping page: " +grandtotinshoppingpage);
		
		
		
		//Click on Proceed
		driver.findElement(By.xpath("(//div[@class='second-col']//button)[1]")).click();
		
		//click on Continue as Guest
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		
		//Check the grandtotal
		 String newGrandTotal = driver.findElement(By.xpath("//div[text()='Grand Total']/following::span")).getText();
		 newGrandTotal=newGrandTotal.replaceAll("\\D", "").trim();
		 int totincheckout = Integer.parseInt(newGrandTotal);
		 System.out.println("Grandtotal on checkout page: " +totincheckout);
		if(grandtotinshoppingpage==totincheckout)
		{
			System.out.println("GrandTotal Matches on both shopping and checkout page");
		}
		
		else
		{
			System.out.println("GrandTotal is incorrect");
		}
		driver.quit();
	}

}
