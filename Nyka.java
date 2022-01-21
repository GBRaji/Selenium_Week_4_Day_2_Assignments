package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nyka {

	@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions builder = new Actions(driver);
		WebElement brand = driver.findElement(By.xpath("//div[@id='brand_arrowUp']"));
		builder.moveToElement(brand).perform();
		driver.findElement(By.xpath("//input[@id='brandSearchBox']")).sendKeys("L'Oreal Paris");
		driver.findElement(By.linkText("L'Oreal Paris")).click();
		System.out.println("Page Title is " + " " + driver.getTitle());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//span[@class='sort-name']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='control-value']/following-sibling::div")).click();
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		w.until(ExpectedConditions
				.elementToBeClickable(By.xpath("(//label[@for='radio_customer top rated_undefined']//div)[2]")));
		driver.findElement(By.xpath("(//label[@for='radio_customer top rated_undefined']//div)[2]")).click();
		driver.findElement(By.xpath("//a[@id='category']")).click();
		WebElement hair = driver.findElement(By.xpath("(//a[contains(text(),'hair')])[2]"));
		builder.moveToElement(hair).perform();
		driver.findElement(By.xpath("((//a[contains(text(),'hair')])[2]/following::a)[3]")).click();
		Thread.sleep(5000);
		Set<String> windowhandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowhandles);
		String parentw = windows.get(0);
		String childw = windows.get(1);
		driver.switchTo().window(childw);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,700)");
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		String text = driver.findElement(By.xpath("//span[text()='Color Protection']")).getText();
		if (text.contains("Color Protection")) {
			System.out.println("Filter on Shampoo is Applied");
		} else {
			System.out.println("Filter not Applied on Shampoo");
		}
		driver.findElement(By.xpath(
				"//img[@src='https://images-static.nykaa.com/media/catalog/product/tr:w-200,h-200,cm-pad_resize/1/5/153d065LPSAMP.jpg']"))
				.click();
		Set<String> windowhandles1 = driver.getWindowHandles();
		List<String> windowss = new ArrayList<String>(windowhandles1);
		String parentw1 = windowss.get(0);
		String childw1 = windowss.get(2);
		driver.switchTo().window(childw1);
		String rate = driver.findElement(By.xpath("//span[@class='css-12x6n3h']")).getText();
		System.out.println("Rate of the Product is" + " " + rate);
		driver.findElement(By.xpath("//span[@class='btn-text']")).click();
		driver.findElement(By.xpath("//span[@class='cart-count']")).click();
		driver.switchTo().frame(0);
		String total = driver.findElement(By.xpath("//span[text()='Grand Total']/following-sibling::div")).getText();
		System.out.println("Grand Total is" + " " + total);
		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();
		driver.findElement(By.xpath("(//button[contains(@class,'btn full')])[2]")).click();
		String gtotal = driver.findElement(By.xpath("(//div[@class='value']//span)[2]")).getText();
		if (total.equalsIgnoreCase("gtotal")) {
			System.out.println("Amounts are Matched");
		} else {
			System.out.println("Amounts are not Matching");
		}
		driver.quit();

	}

}
