package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic06_BrowserExcercise {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    //bai tap: https://docs.google.com/document/d/1G0QsiVDI5KfhNGKxE6a03bhn6KpvgDh69BTx2ttCO94/edit?tab=t.0
    @Test
    public void TC_01_Url() {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/create/");
    }
    @Test
    public void TC_02_Title() {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        Assert.assertEquals(driver.getTitle(),"Customer Login");
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }
    @Test
    public void TC_03_Navigate() {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/create/");
        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/login/");
        driver.navigate().forward();
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");

    }
    @Test
    public void TC_04_PageSourcel() {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
