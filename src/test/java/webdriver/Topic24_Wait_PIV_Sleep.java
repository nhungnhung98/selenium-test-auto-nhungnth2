package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic24_Wait_PIV_Sleep {
    WebDriver driver;
    WebDriverWait explicitWait;


    @BeforeClass
    public void beforClass() {
        driver = new FirefoxDriver();
    }

   @Test
    public void TC_01_No_Set() throws InterruptedException {

        driver.get("https://automationfc.github.io/dynamic-loading");


        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div#loading")).isDisplayed());

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");

    }
    @Test
    public void TC_02_Less() throws InterruptedException {

        driver.get("https://automationfc.github.io/dynamic-loading");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div#loading")).isDisplayed());

        Thread.sleep(3000);// fix cứng chờ hết 3s
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");

    }
    @Test
    public void TC_03_Equal() throws InterruptedException {

        driver.get("https://automationfc.github.io/dynamic-loading");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div#loading")).isDisplayed());

        Thread.sleep(5000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");

    }
    @Test
    public void TC_04_More() throws InterruptedException {

        driver.get("https://automationfc.github.io/dynamic-loading");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div#loading")).isDisplayed());

        Thread.sleep(10000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");


    }

    private String getDateTimeNow() {
        return new Date().toString();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();

    }
}

