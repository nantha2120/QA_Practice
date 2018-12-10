package login_page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.main.databroker.DataBroker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {
	public WebDriver driver;
	public String page_url;
	public String UserName;
	public String Password;
	public String in_UserName;
	WebDriverWait wait;
	@BeforeTest()
	public void setup(){
		DataBroker TestData = new DataBroker();
		 String [] csvCell;
		 csvCell = TestData.ReadData();
		 		   page_url = csvCell[0];
				   UserName = csvCell[1];
				   Password = csvCell[2];
				   in_UserName = csvCell[3];
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
//		ChromeOptions chromeOptions = new ChromeOptions();
//		chromeOptions.addArguments("--headless");
//		driver = new ChromeDriver(chromeOptions);
		driver.get(page_url);
		driver.manage().window().maximize();
		
		
	}
	@Test(description="Enter the invalid Username and password", priority=1, enabled = true)
	public void invalid_credentials() {
		wait = new WebDriverWait(driver, 100);
		driver.findElement(By.id("username")).sendKeys(in_UserName);
		driver.findElement(By.id("password")).sendKeys(Password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='flash-messages']")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='flash-messages']")).isDisplayed());
	}
	
	@Test(description = "Enter The valid username and password",priority=2, enabled = true)
	public void Valid_credentials() {
		driver.findElement(By.id("username")).sendKeys(UserName);
		driver.findElement(By.id("password")).sendKeys(Password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='flash']")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='flash']")).isDisplayed());
	}
	
	@Test(description = "validate the DashBoard content",priority=3, enabled = true)
	public void dashBoard() {
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='example']//h2")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//h4[@class='subheader']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='button secondary radius']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='large-4 large-centered columns']")).isDisplayed());
	}
	
	@Test(description = "validate logout function",priority=4, enabled = true)
	public void logout() {
		driver.findElement(By.xpath("//a[@class='button secondary radius']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
		String geturl = driver.getCurrentUrl();
		Assert.assertEquals(geturl,page_url);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='flash']")).isDisplayed());
	}
	@AfterTest
	public void close() {
		driver.close();
	}

}
