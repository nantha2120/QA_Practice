package common_classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login_credentials {
	public static WebDriver driver;
	public WebDriver invlaid_login(WebDriver driver, String name, String pass) {
		driver.findElement(By.id("username")).sendKeys(name);
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		return driver;
		
	}

}
