package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic21_Wait_PI_Status {
    WebDriver driver;
    WebDriverWait explicitWait;


    @BeforeClass
    public void beforClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));// chờ cho việc tìm kiếm element
        driver.manage().window().maximize();

        explicitWait =new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Visible() throws InterruptedException {

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button#send2")).click();
        //Dieu kien 1: Element hien thi o tren UI va co trong DOM/cay HTML
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
    }
    @Test
    public void TC_02_Invisible_in_HTML() throws InterruptedException {

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button#send2")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("nhung@gmail.com");
        driver.findElement(By.cssSelector("button#send2")).click();

        //Dieu kien 2: Element khong hien thi tren UI va co trong DOM/HTML
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));

    }
    @Test
    public void TC_03_Invisible_Not_in_HTML() throws InterruptedException {
        //Dieu kien 3: Element khong hien thị tren UI và khong co trong HTML

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("input#email")).sendKeys("nhung@gmail.com");
        driver.findElement(By.cssSelector("button#send2")).click();

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));

    }
    @Test
    public void TC_04_present() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.cssSelector("button#send2")).click();

        //ĐK 1: Element hiển thị trên UI &  có trong DOM/HTML
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#advice-required-entry-email")));

        driver.findElement(By.cssSelector("input#email")).sendKeys("nhung@gmail.com");
        driver.findElement(By.cssSelector("button#send2")).click();

        //Dieu kien 2- Element khong hien thị tren UI va co trong HTML
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#advice-required-entry-email")));

    }
    @Test
    public void TC_05_Staless_present() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.cssSelector("button#send2")).click();

        //Element đã xh tại thời điểm trước đó r
        WebElement emailErrorMessage = driver.findElement(By.cssSelector("div#advice-required-entry-email"));

        driver.navigate().refresh();

        //ĐK3: Element k có trên UI & k có trong DOM
        // Tại thời điểm này mong đợi k có xuất hiện nữa
        explicitWait.until(ExpectedConditions.stalenessOf(emailErrorMessage));

    }


    @AfterClass
    public void afterClass() {
        driver.quit();

    }
}

