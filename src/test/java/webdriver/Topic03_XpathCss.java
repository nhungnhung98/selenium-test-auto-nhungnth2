package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
        }
    @Test //Tim Xpath theo cac locator khac nhau
    public void TC_01() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        driver.findElement(By.xpath("//input[@value='M']"));
        driver.findElement(By.xpath("//input[@id='FirstName']"));
        driver.findElement(By.xpath("//input[@name='LastName']"));
        driver.findElement(By.xpath("//div[@class='inputs']//input[@type='email']"));
        driver.findElement(By.xpath("//input[@id='Company']"));
        driver.findElement(By.xpath("//input[@id='Newsletter']"));
        driver.findElement(By.xpath("//input[@name='Password']"));
        driver.findElement(By.xpath("//input[@name='ConfirmPassword']"));
    }
    @Test
    public void TC_02_PhuDinh()
    {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        driver.findElement(By.xpath("//a[text()='18']")).click();
        driver.findElement(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']"));
    }
    @Test
    public void TC_03_startsWith() throws InterruptedException {
        driver.get("https://www.lazada.vn/#?");
        driver.findElement(By.xpath("//a[text()='login']")).click();
//        Thread.sleep(3000);
////        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
////        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
////        element.sendKeys("admin");
        driver.findElement(By.xpath("//input[starts-with(@data-spm-anchor-id,'a2o4n.homepage')]"));
    }
    @Test
    public void TC_08_axes() {
        driver.get("https://live.techpanda.org/index.php/mobile.html");

        //từ node con lên note tổ tiên
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/ancestor::div[@class='main']"));

        // từ node con lên node cha: parent
        // node em đi lên node anh: preceding-sibling
        //node anh đi xuống node em: following-sibling
        driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']/button"));
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/button"));

        // đi lên node cha k cần dùng parent: /.. = parent::div
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/.."));
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/parent::div"));

        //----------------------------

        driver.get("https://subscription.packtpub.com/search?query=Selenium&products=Book%20%2B%20AI%20Assistant~Book&released=Available~Early%20Access");
        driver.findElement(By.xpath("//div[text()='Selenium Essentials']/ancestor::a/following-sibling::a//button[@class='cart-btn']"));
        driver.findElement(By.xpath("//div[text()='Learning Selenium Testing Tools - Third Edition']/ancestor::a/following-sibling::a//button[@class='cart-btn']"));

        //----------------------------
        driver.get("https://kajabi.com/pricing");
        driver.findElement(By.xpath("//h2[text()='Kickstarter']//parent::div/following-sibling::a[@id='pricing-kickstarter-annual']"));
        //----------------------------

        driver.get("https://demo.guru99.com/");
        driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
