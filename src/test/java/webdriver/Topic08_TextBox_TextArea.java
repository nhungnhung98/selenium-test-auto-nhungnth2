package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic08_TextBox_TextArea {
    WebDriver driver;
    String firstName, lastName, emailAddress, password;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        firstName = "Nhung";
        lastName = "Nguyen";
        emailAddress = "nhung"+ new Random().nextInt(999)+"@hotmail.com";
        password = "123456789";
    }

    @Test
    public void TC_01_Techpanda() throws InterruptedException {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);

        driver.findElement(By.cssSelector("button[title='Register']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
