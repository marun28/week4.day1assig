package week4.assing;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Merge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		
		ChromeDriver driver = new ChromeDriver();
		
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");				
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElementByClassName("decorativeSubmit").click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[1]")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> lwindow = new ArrayList<String>(windowHandles);
		driver.switchTo().window(lwindow.get(1));
		
		System.out.println("Second window url:" +driver.getCurrentUrl());
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]")).click();
		driver.switchTo().window(lwindow.get(0));
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
		Set<String> windowHandles2 = driver.getWindowHandles();
		
		List<String> secWindow = new ArrayList<String>(windowHandles2);
		driver.switchTo().window(secWindow.get(1));
		
		//Click on second contact
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[2]")).click();
		
		//Switch to first window
		driver.switchTo().window(secWindow.get(0));
		
		//Click on merge contact
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		
		//Switch to alert window
		Alert alert = driver.switchTo().alert();
		
		//accept the alert
		alert.accept();
	
		System.out.println("Page title is " +driver.getTitle());
		
		
	}

}
