package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.SystemOutLogger;
import org.apache.xmlbeans.impl.soap.Text;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {

	@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		Actions builder= new Actions(driver);
		WebElement men = driver.findElement(By.xpath("//a[@data-group='men']"));
		builder.moveToElement(men).perform();
		driver.findElement(By.linkText("Jackets")).click();
		String totalcount = driver.findElement(By.xpath("//span[@class='title-count']")).getText();
		String fcount = totalcount.replaceAll("[^0-9]", "");
		int fint = Integer.parseInt(fcount);
		System.out.println("Total Number of Count is " + " "+fint);
		String jack = driver.findElement(By.xpath("//input[@value='Jackets']/following-sibling::span[1]")).getText();
		String jackk = jack.replaceAll("[^0-9]", "");
		int jacint = Integer.parseInt(jackk);
		String rainjac = driver.findElement(By.xpath("//input[@value='Rain Jacket']/following-sibling::span[1]")).getText();
		String rainjacc = rainjac.replaceAll("[^0-9]", "");
		int rainint = Integer.parseInt(rainjacc);
		int countval = jacint +rainint;
		System.out.println("Count of Jacket and RainJacket"+ " " +countval);
		if(fint==countval) {
			System.out.println("Counts are Equal");
		}
		else {
			System.out.println("Counts are not Equal");
		}
		driver.findElement(By.xpath("//label[text()='Jackets']/div")).click();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
	     js.executeScript("scroll(0, 200)");
		driver.findElement(By.xpath("//div[@class='brand-more']")).click();
		driver.findElement(By.xpath("//input[@class='FilterDirectory-searchInput']")).sendKeys("Duke");
		driver.findElement(By.xpath("(//input[@class='FilterDirectory-searchInput']/following::div)[2]")).click();
		driver.findElement(By.xpath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']")).click();
	 int brandcount = driver.findElements(By.xpath("//h3[@class='product-brand']")).size();
		
	 List<String> bb= new ArrayList<String>();
		
		for (int i= 1;i<=brandcount;i++) {
			String text = driver.findElement(By.xpath("//h3[@class='product-brand']")).getText();
			bb.add(text);
			
		}
		
		if(bb.contains("Duke")) {
		System.out.println("All Coat brands are Duke");
		}
		else {
			System.out.println("Some coats are not Duke");
		}
		driver.findElement(By.xpath("//span[text()='Recommended']/following-sibling::span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[text()='Better Discount']")).click();
		String productprice = driver.findElement(By.xpath("(//div[@class='product-price']//span)[2]")).getText();
		System.out.println("Product Price is" + " "+productprice);
		driver.findElement(By.xpath("(//div[@class='product-price']//span)[2]")).click();
		Set<String> windowhandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowhandles);
		String parentw = windows.get(0);
		String childw = windows.get(1);
		driver.switchTo().window(childw);
		//WebElement img = driver.findElement(By.xpath("div[@class='image-grid-image']"));
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File ("./images/shirt.png");
		FileUtils.copyFile(source, destination);
		driver.findElement(By.xpath("//span[text()='WISHLIST']")).click();
		driver.quit();
		
	}

}
