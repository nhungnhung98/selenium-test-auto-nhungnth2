package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic14_Actions_1 {
    WebDriver driver;
    Select select;
    JavascriptExecutor jsExecutor;
    Actions actions;

    @BeforeClass
    public void beforClass() {
        FirefoxOptions option = new FirefoxOptions();
        option.addPreference("dom.webnotifications.enable",false);
        driver = new FirefoxDriver(option);

        //driver.manage().window().setSize(new Dimension(1366,768));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));// chờ cho việc tìm kiếm element
        driver.manage().window().maximize();

        Actions actions = new Actions(driver);
    }

    @Test
    public void TC_01_Hover(){
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        Actions actions;
//        actions.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
//        actions.pause(Duration.ofSeconds(3)).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("div[class='ui-tooltip-content']")).getText(),
                "We ask for your age only for statistical purposes.");
    }
    @Test
    public void TC_02() throws InterruptedException {
        driver.get("https://www.fahasa.com/");
        Thread.sleep(1000);

        //hover vào icon menu
        actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        actions.pause(Duration.ofSeconds(2)).perform();

        //hover vào Sách giáo khoa 2025
        actions.moveToElement(driver.findElement(By.xpath("//ul[@class='nav navbar-nav verticalmenu']//span[text()='Sách Giáo Khoa 2025']"))).perform();

        driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Luyện Thi Môn Toán']")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Toán']")).isDisplayed());

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
