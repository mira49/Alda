package validation_tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class FavoriteMessageTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver", "C:/Users/guigu/Downloads/chromedriver_win32/chromedriver.exe");
	driver = new ChromeDriver();
    baseUrl = "http://localhost:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void test() throws Exception {
	    driver.get(baseUrl + "/Alda_Project/connexion");
	    driver.findElement(By.linkText("register ?")).click();
	    driver.findElement(By.name("email")).clear();
	    driver.findElement(By.name("email")).sendKeys("amira@gmail.com");
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("test1234");
	    driver.findElement(By.name("password_confirm")).clear();
	    driver.findElement(By.name("password_confirm")).sendKeys("test1234");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.findElement(By.name("email")).clear();
	    driver.findElement(By.name("email")).sendKeys("amira@gmail.com");
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("test1234");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.findElement(By.linkText("Profil")).click();
	    driver.findElement(By.name("Name")).clear();
	    driver.findElement(By.name("Name")).sendKeys("amira");
	    driver.findElement(By.name("FirstName")).clear();
	    driver.findElement(By.name("FirstName")).sendKeys("touati");
	    driver.findElement(By.name("Address")).clear();
	    driver.findElement(By.name("Address")).sendKeys("369 rue des fleurs");
	    driver.findElement(By.name("Phone")).clear();
	    driver.findElement(By.name("Phone")).sendKeys("06 45 47 54 47");
	    driver.findElement(By.cssSelector("button.btn.register")).click();
	    driver.findElement(By.xpath("(//img[@id='imghome'])[4]")).click();
	    driver.findElement(By.id("factor_lower_price")).clear();
	    driver.findElement(By.id("factor_lower_price")).sendKeys("20000");
	    driver.findElement(By.name("factor")).click();
	    driver.findElement(By.id("factor_lower_price")).clear();
	    driver.findElement(By.id("factor_lower_price")).sendKeys("0");
	    driver.findElement(By.id("factor_higher_price")).clear();
	    driver.findElement(By.id("factor_higher_price")).sendKeys("100000");
	    driver.findElement(By.name("factor")).click();
	    driver.findElement(By.id("factor_lower_price")).clear();
	    driver.findElement(By.id("factor_lower_price")).sendKeys("100000");
	    driver.findElement(By.id("factor_higher_price")).clear();
	    driver.findElement(By.id("factor_higher_price")).sendKeys("1000000");
	    driver.findElement(By.name("factor")).click();
	    driver.findElement(By.id("factor_location")).clear();
	    driver.findElement(By.id("factor_location")).sendKeys("33000");
	    driver.findElement(By.name("factor")).click();
	    driver.findElement(By.id("factor_location")).clear();
	    driver.findElement(By.id("factor_location")).sendKeys("");
	    driver.findElement(By.id("factor_lower_price")).clear();
	    driver.findElement(By.id("factor_lower_price")).sendKeys("");
	    driver.findElement(By.id("factor_higher_price")).clear();
	    driver.findElement(By.id("factor_higher_price")).sendKeys("");
	    new Select(driver.findElement(By.name("select_option"))).selectByVisibleText("higher_Price");
	    new Select(driver.findElement(By.name("select_option"))).selectByVisibleText("lower_Price");
	    new Select(driver.findElement(By.name("select_option"))).selectByVisibleText("Location");
	  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
