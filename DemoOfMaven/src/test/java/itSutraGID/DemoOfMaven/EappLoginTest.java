package itSutraGID.DemoOfMaven;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertNotNull;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class EappLoginTest {
	
	WebDriver driver;
  @Test
  public void VerifyUserLoginAlertMsgWhenSubmitOnlyUsernameInfo() {
	  String Username = "admin";
	  String Password = "";
	  
	  driver.findElement(By.id("loginLink")).click();
	  Reporter.log("Login Button Clicked and login page is opened");
	  
	  driver.findElement(By.id("UserName")).sendKeys(Username);
	  Reporter.log("Username entered");
	  driver.findElement(By.id("Password")).sendKeys(Password);
	  Reporter.log("password is entered");
	  
	  driver.findElement(By.xpath("//*[@id=\'loginForm\']/form/div[4]/div/input")).click();
	  Reporter.log("Clicked to login button");
	  
	  WebElement ErrorMsg = driver.findElement(By.xpath("//span[.='The Password field is required.'][@for='Password']"));
	  assertNotNull(ErrorMsg, "Getting error msg for password");
	  Reporter.log("Test Case Success!!! , Getting error msg for password");
	  
  }
  
   

  @Parameters({"url", "browser"})
  @BeforeTest
  public void SetUp(String url, String browser) throws MalformedURLException{
	  
	  DesiredCapabilities dc = new DesiredCapabilities();
	   
	  
	  dc.setBrowserName("chrome");
	  driver = new ChromeDriver();
	  
	  
	  driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
	  
	  driver.get(url);
	  driver.manage().window().maximize();
	  Reporter.log("Browser Initiated and navigated to http://eaapp.somee.com/");	
	  
  }

  @AfterTest
  public void Clean() {
	  driver.quit();
  }

}
