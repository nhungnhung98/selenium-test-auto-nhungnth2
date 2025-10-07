package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic05_Browser {
    WebDriver driver;

    @Test
    public void TC_01() {
        //các hàm tương tác với Browser thông qua biến driver
        //driver đại diện cho selenium Webdriver
        // driver.
        //các hàm tương tác với element thông qua: findElement
        //findElement đại diện cho selenium WebElement
        //1. Tương tác trực tiếp lên element (dùng 1 lần)
//        driver.findElement(By.cssSelector("input#email")).sendKeys("abc@gmail.com");
//        2. Khai báo biến trước khi tương tác (dùng nhiều lần)
//        WebElement emailText = driver.findElement(By.cssSelector("input#email"));
//        emailText.clear();
//        emailText.sendKeys("abc@gmail.com");
//        emailText.isDisplayed();
        driver = new FirefoxDriver();
        driver.get("https://www.telerik.com/support/demos");
        driver.quit();
    }
    @Test
    public void TC_02()  {
        driver = new ChromeDriver();
        driver.get("https://www.telerik.com/support/demos");
        driver.quit();
    }

}
