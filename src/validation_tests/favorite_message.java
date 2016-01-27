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

public class favorite_message {
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
    driver.get(baseUrl + "/ALDA_Project/home_user");
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("guillaume.ver@gmail.com");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("istball21");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.linkText("Profil")).click();
    driver.findElement(By.name("Name")).clear();
    driver.findElement(By.name("Name")).sendKeys("verdugo");
    driver.findElement(By.name("FirstName")).clear();
    driver.findElement(By.name("FirstName")).sendKeys("guillaume");
    driver.findElement(By.name("Address")).clear();
    driver.findElement(By.name("Address")).sendKeys("388 rue pasteur");
    driver.findElement(By.name("Phone")).clear();
    driver.findElement(By.name("Phone")).sendKeys("07 85 57 47 44");
    driver.findElement(By.cssSelector("button.btn.register")).click();
    driver.findElement(By.xpath("(//img[@id='imghome'])[4]")).click();
    driver.findElement(By.name("favorite")).click();
    driver.findElement(By.xpath("(//input[@name='favorite'])[2]")).click();
    driver.findElement(By.linkText("My messages")).click();
    driver.findElement(By.linkText("Actuality")).click();
    driver.findElement(By.name("contact")).click();
    driver.findElement(By.name("Message")).clear();
    driver.findElement(By.name("Message")).sendKeys("hello");
    driver.findElement(By.cssSelector("button.btn.register")).click();
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
