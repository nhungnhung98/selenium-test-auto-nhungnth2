package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic15_Popup {
    WebDriver driver;
    Select select;
    JavascriptExecutor jsExecutor;
    Actions actions;

    @BeforeClass
    public void beforClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));// chờ cho việc tìm kiếm element
        driver.manage().window().maximize();

        actions = new Actions(driver);
    }

    @Test
    public void TC_01_IN_HTML() throws InterruptedException {
        driver.get("https://kmplayer.com/home");

        //Thread.sleep(8000);

        By popup = By.cssSelector("div[class='pop-conts']");
        // Neu nhu popup co hien thi thi Close di=> Click vao FAQ link
        // Neu khong hien thi thi click vaf FAQ link
        // Boi vi element du hien thij hay bien mat thi van luon con trong HTM
        // find elemnet se luon dc tim thay

        if (driver.findElement(popup).isDisplayed()) {
            System.out.println("----Popup hien thi----");
            driver.findElement(By.cssSelector("div.close")).click();
            Thread.sleep(2000);
        }

        System.out.println("----Popup khong hien thi----");

        //Deu mong doi se khong con hien thị truoc khi click vao FAQ link
        Assert.assertFalse(driver.findElement(popup).isDisplayed());

        driver.findElement(By.xpath("//a[text()='FAQ']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='KMPlayer FAQ']")).isDisplayed());

    }

    @Test
    public void TC_02_NOT_IN_HTML() throws InterruptedException {
        driver.get("https://ngoaingu24h.vn/");

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Thread.sleep(2000);

        By popup = By.cssSelector("div[class*='custom-dialog-paper']");
        Assert.assertTrue(driver.findElement(popup).isDisplayed());

        driver.findElement(By.cssSelector("input[placeholder='Tài khoản đăng nhập']")).sendKeys("automation");
        driver.findElement(By.cssSelector("input[placeholder='Mật khẩu']")).sendKeys("automation");
        driver.findElement(By.xpath("//form//button[text()='Đăng nhập']")).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#notistack-snackbar")).getText(), "Bạn đã nhập sai tài khoản hoặc mật khẩu!");
        driver.findElement(By.cssSelector("h2>button.close-btn")).click();

        //verify popup khong hien thi
        //isDisplayed() khong kiem tra cho element khong co trong HTML dc
        //Assert.assertFalse(driver.findElement(popup).isDisplayed());

        //Khi xu ly cac element khong ton tai trong HTML thi nen set lai timeout cua implicit ve 1 con so ngan hon
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        Assert.assertEquals(driver.findElements(popup).size(), 0);
        //NOTE: Neu popup dang fix cung/random khi khong hien thi khong ton tai trong HTML nua thi phai dung elements (dang list)
        // lay kich thuoc cua list element va kiem tra bang 0

    }

    @Test
    public void TC_03_Tiki() throws InterruptedException {
        driver.get("https://tiki.vn/");

        // la popup dang random
        By bundlePopup = By.cssSelector("div#VIP_BUNDLE");
        // Neu nhu popup co hien thi thi Close di=> click vao Dang nhap/ Dang ky
        // Neu khong hien thi click vao Dang nhap/ Dang ky

        if (driver.findElements(bundlePopup).size() > 0 && driver.findElements(bundlePopup).get(0).isDisplayed()) {

            //Close di
            driver.findElement(By.xpath("//img[@alt='close-icon']")).click();
            Thread.sleep(2000);

            //click vao dang nhap dang ky
            driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();

            // La 1 popup dang fix cung
            By loginPopup = By.cssSelector("div[class*='ReactModal__Content ']");

            //=> Check hien thi bang isDisplay duoc, khong check dc khi n k hien thi
            Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

            //click DN bang email
            driver.findElement(By.xpath("//p[@class='login-with-email']")).click();
            driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
            Thread.sleep(1000);

            Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Email không được để trống']")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Mật khẩu không được để trống']")).isDisplayed());
            Thread.sleep(1000);

            driver.findElement(By.cssSelector("button>img.close-img")).click();
            Thread.sleep(1000);

            //Verify popup k hien thi
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            Assert.assertEquals(driver.findElements(loginPopup).size(),0);

        }


    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
