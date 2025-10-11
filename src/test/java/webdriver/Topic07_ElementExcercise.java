package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic07_ElementExcercise {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    //bai tap: https://docs.google.com/document/d/1G0QsiVDI5KfhNGKxE6a03bhn6KpvgDh69BTx2ttCO94/edit?tab=t.0
    @Test
    public void TC_01_Displayed_Enable_Selected() {
        driver.get("https://www.fahasa.com/customer/account/create/");

    //Element display: co the nhin thay, thao tac duoc, kich thuoc cu the
    Assert.assertTrue(driver.findElement(By.cssSelector("input#register_phone")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.cssSelector("input#register_phone_otp")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.cssSelector("button.fhs-btn-register")).isDisplayed());

    //Element Enable: co the thao tac dc


    // Element Selected: co the chon dc

   }
    @Test
    public void TC_02_Enable() {

    }
    @Test
    public void TC_03_Selected() {

    }
    @Test
    public void TC_04_MailChimp() {

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
