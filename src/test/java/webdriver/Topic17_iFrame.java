package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic17_iFrame {
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
    public void TC_01_WordPress() throws InterruptedException {
        driver.get("https://toidicodedao.com/");

        //Switch  qua Iframe/frame
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title*='Facebook Social Plugin']")));

        Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Tôi đi code dạo']/parent::div/following-sibling::div")).getText(), "396,003 followers");

        // Quay lại trang chứa iframe
        driver.switchTo().defaultContent();

        driver.findElement(By.cssSelector("div#content-sidebar input[class='search-field']")).sendKeys("puppeteer");
        driver.findElement(By.cssSelector("div#content-sidebar input[class='search-field']")).sendKeys(Keys.ENTER);


    }

    @Test
    public void TC_02_FormSize() {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        driver.findElement(By.cssSelector("div#imageTemplateContainer")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='details__form-preview-wrapper' " +
                "and contains(string(),'Interactive form loaded. Try filling out below.')]")).isDisplayed());

        //switch vào iframe
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#formTemplateContainer>iframe")));

        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("South Dorm");
        driver.findElement(By.xpath("//label[text()='Female']")).click();

        driver.switchTo().defaultContent();

        driver.findElement(By.xpath("//a[@title='Get this form']")).click();

    }
    @Test
    public void TC_03_HDFC() throws InterruptedException {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        Thread.sleep(4000);

        driver.switchTo().frame("login_page");

        driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("Jonh");
        driver.findElement(By.cssSelector("a.login-btn")).click();
        Thread.sleep(4000);

        driver.switchTo().defaultContent();

        driver.findElement(By.cssSelector("input#keyboard")).sendKeys("123456");
        driver.findElement(By.cssSelector("a#loginBtn")).click();

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
