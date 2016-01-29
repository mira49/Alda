package validation_tests;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ConnexionaddTest {
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
  public void testAldaConnexionRegisterAdd() throws Exception {
    driver.get(baseUrl + "/Alda_Project/connexion");
    driver.findElement(By.linkText("register ?")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("guillaume.ver@gmail.com");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("test1234");
    driver.findElement(By.name("password_confirm")).clear();
    driver.findElement(By.name("password_confirm")).sendKeys("test1234");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("guillaume.ver@gmail.com");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("test1234");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("(//img[@id='imghome'])[2]")).click();
    driver.findElement(By.linkText("Favorite")).click();
    driver.findElement(By.linkText("Actuality")).click();
    driver.findElement(By.linkText("My announcements")).click();
    driver.findElement(By.cssSelector("button.btn.btn-link")).click();
    driver.findElement(By.name("Name")).clear();
    driver.findElement(By.name("Name")).sendKeys("My first announcement");
    driver.findElement(By.name("postal_code")).clear();
    driver.findElement(By.name("postal_code")).sendKeys("33200");
    driver.findElement(By.name("Town")).clear();
    driver.findElement(By.name("Town")).sendKeys("Bordeaux");
    driver.findElement(By.name("Price")).clear();
    driver.findElement(By.name("Price")).sendKeys("95000");
    driver.findElement(By.name("Surface")).clear();
    driver.findElement(By.name("Surface")).sendKeys("45");
    driver.findElement(By.name("Description")).clear();
    driver.findElement(By.name("Description")).sendKeys("Test selenium");
    driver.findElement(By.name("picture1")).clear();
    driver.findElement(By.name("picture1")).sendKeys("C:\\Users\\guigu\\Desktop\\audrey\\chips_2.png");
    driver.findElement(By.name("picture2")).clear();
    driver.findElement(By.name("picture2")).sendKeys("C:\\Users\\guigu\\Desktop\\audrey\\chips_3.png");
    driver.findElement(By.cssSelector("button.btn.register")).click();
    driver.findElement(By.id("imghome")).click();
    driver.findElement(By.name("Name")).clear();
    driver.findElement(By.name("Name")).sendKeys("My Second Image");
    driver.findElement(By.name("postal_code")).clear();
    driver.findElement(By.name("postal_code")).sendKeys("65000");
    driver.findElement(By.name("Town")).clear();
    driver.findElement(By.name("Town")).sendKeys("Bordeaux");
    driver.findElement(By.name("Price")).clear();
    driver.findElement(By.name("Price")).sendKeys("950000");
    driver.findElement(By.name("Surface")).clear();
    driver.findElement(By.name("Surface")).sendKeys("400");
    driver.findElement(By.name("Description")).clear();
    driver.findElement(By.name("Description")).sendKeys("Big flat");
    driver.findElement(By.name("picture1")).clear();
    driver.findElement(By.name("picture1")).sendKeys("C:\\Users\\guigu\\Desktop\\audrey\\chips_3.png");
    driver.findElement(By.name("picture2")).clear();
    driver.findElement(By.name("picture2")).sendKeys("C:\\Users\\guigu\\Desktop\\audrey\\chips_2.png");
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
