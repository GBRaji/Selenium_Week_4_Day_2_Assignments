package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro");
		driver.findElement(By.id("nav-search-submit-button")).click();
		List<WebElement> pricee = driver.findElements(By.className("a-price-whole"));
		List<String> prices = new ArrayList<String>();
		for (WebElement e : pricee) {
			prices.add(e.getText());
		}
		List<String> sortedprice = new ArrayList<String>(prices);
		String fprice = sortedprice.get(0);
		String repfrice = fprice.replaceAll("[^0-9]", "");
		System.out.println(" Mobile Price is"+ " "+repfrice);
		//driver.findElement(By.xpath("//input[@id='nav-search-submit-button']/following::span[text()='49,999']")).getText();
		//System.out.println("Price of the Mobile is" + " " + price);
		String review = driver.findElement(By.xpath("//span[contains(@class,'a-size-medium a-color-base')]/following::span[contains(@class,'a-size-base a-color-base')][1]")).getText();
		System.out.println("No of Person Reviewed" + " " + review);
		Thread.sleep(3000);
		/*
		WebElement star = driver.findElement(By.xpath("(//a[@role='button']//i)[2]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(star).perform();
		Thread.sleep(3000);
		String reviewstar = driver.findElement(By.xpath("(//span[@class='a-size-base']//a)[2]")).getText();
		System.out.println("Percentage of Five Star" + " " + reviewstar);
		*/
		//driver.findElement(By.xpath("(//a[contains(@class,'a-link-normal s-underline-text')]//span)[2]")).click();
		driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']")).click();
		Set<String> windowhandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowhandles);
		String childw = windows.get(1);
		driver.switchTo().window(childw);
		WebElement img = driver.findElement(By.xpath("//div[@id='imgTagWrapperId']"));
		File source = img.getScreenshotAs(OutputType.FILE);
		File destination = new File("./images/Mobile.png");
		FileUtils.copyFile(source, destination);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,200)");
		driver.findElement(By.id("add-to-cart-button")).click();
		Thread.sleep(3000);
		String finalp = driver.findElement(By.xpath("(//b[text()='Cart subtotal']/following::span)[2]")).getText();
		String finalpp = finalp.replaceAll("[^0-9]", "");
		String finall = finalpp.replaceAll("[0]", "");
		System.out.println("Mobile Final Price"+ " "+finall);
		if (repfrice.equals(finall)) {
			System.out.println("Initial and Final Mobile Price are same");
		}
		else {
			System.out.println("Varaiation in price is found");
		}
		driver.quit();
	}

}
