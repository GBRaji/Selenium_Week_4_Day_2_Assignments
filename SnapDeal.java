package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		Actions builder = new Actions(driver);
		WebElement men = driver.findElement(By.xpath("//span[@class='catText']"));
		builder.moveToElement(men).perform();
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
		Thread.sleep(2000);
		String count = driver.findElement(By.xpath("//div[@class='child-cat-name selected']/following-sibling::div[1]"))
				.getText();
		int shoecount = Integer.parseInt(count);
		System.out.println("Count of Sports Shoe is" + " " + shoecount);
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		driver.findElement((By.xpath("//span[text()='Sort by:']/following-sibling::i"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li[@data-index='0']/following-sibling::li[1]")).click();
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		w.ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='lfloat product-price']")));

		List<WebElement> price = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		List<String> prices = new ArrayList<String>();
		for (WebElement e : price) {
			prices.add(e.getText());
		}
		List<String> sortedprice = new ArrayList<String>(prices);
		
		Collections.sort(sortedprice);
		boolean val = sortedprice.equals(prices);
		if (val == true) {
			System.out.println("Sorting Validation Successful");
		}

		else {
			System.out.println("Sorting Validation is not Successful");
		}
		Thread.sleep(3000);
		WebElement fval = driver.findElement(By.xpath("//input[@name='fromVal']"));
		fval.clear();
		fval.sendKeys("900");
		WebElement tval = driver.findElement(By.xpath("//input[@name='toVal']"));
		tval.clear();
		tval.sendKeys("1200");
		driver.findElement(By.xpath("//div[text()[normalize-space()='GO']]")).click();
		Thread.sleep(3000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, 800)");
		driver.findElement(By.xpath("//label[@for='Color_s-Navy']")).click();
		Thread.sleep(3000);
		JavascriptExecutor jsee = (JavascriptExecutor) driver;
		jsee.executeScript("scroll(0, 300)");
		Thread.sleep(3000);
		WebElement img = driver.findElement(By.xpath("//source[@class='product-image']/following-sibling::img"));
		WebDriverWait waittt = new WebDriverWait(driver, Duration.ofSeconds(30));
		waittt.until(ExpectedConditions.visibilityOf(img));
		waittt.until(ExpectedConditions.elementToBeClickable(img));
		Actions builderr = new Actions(driver);
		builderr.moveToElement(img).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='clearfix row-disc']//div[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='quickViewHead']")).click();
		String shoepri = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		System.out.println("Shoe Price is" + " " + shoepri);
		String per = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		System.out.println("Offer Percentage is" + " " + per);
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./images/Shoe.png");
		FileUtils.copyFile(source, destination);

		driver.quit();

	}

}
