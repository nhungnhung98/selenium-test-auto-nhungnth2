package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic03_XpathCss {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        }
    @Test
    public void TC_01() {
        //Tim Xpath theo cac locator khac nhau
        driver.findElement(By.xpath("//input[@value='M']"));
        driver.findElement(By.xpath("//input[@id='FirstName']"));
        driver.findElement(By.xpath("//input[@name='LastName']"));
        driver.findElement(By.xpath("//div[@class='inputs']//input[@type='email']"));
        driver.findElement(By.xpath("//input[@id='Company']"));
        driver.findElement(By.xpath("//input[@id='Newsletter']"));
        driver.findElement(By.xpath("//input[@name='Password']"));
        driver.findElement(By.xpath("//input[@name='ConfirmPassword']"));
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
