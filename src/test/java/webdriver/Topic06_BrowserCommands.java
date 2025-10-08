package webdriver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic06_BrowserCommands {
    WebDriver driver;

    @Test
    public void TC_01() {
        driver.get("");
        //Url của page hiện tại
        //Assert.assertEquals(driver.getPageSource().contains("Conputers"));

        //HTML theo source code cua page hiện tại
        Assert.assertTrue(driver.getPageSource().contains("Computers"));

        //Tra ve ID cua tab/window dang active
        driver.getWindowHandle();

        //Tra ve title cua pae hien tai
        driver.getTitle();

        //Mo rong cua so
        driver.manage().window().maximize();

        //Thu nho cua so
        driver.manage().window().minimize();

        //ful kich thuoc man hinh
        driver.manage().window().fullscreen();

        //Nhieu kich thuoc man hinh trinh duyet
        driver.manage().window().setSize(new Dimension(1920,1080));

        //Lay ra kich thuoc cua browser bang bao nhieu
        driver.manage().window().getSize();

        //Set man hinh o vi tri nao so voi do phan giai man hinh hien tai
        driver.manage().window().setPosition(new Point(0,50));

        //Lay ra toa do cuar Browser dang dung
        driver.manage().window().getPosition();

        //Set cho viec tim kiem Element
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(30000));

        //Ap dung cho JavascripExxcutor
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().getScriptTimeout();

        // Ap dung cho page dc load toi da la bao nhieu
        //driver.manage().timeouts().getPageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().getPageLoadTimeout();

        //Lay ra cookie theo ten/xoa cookie/xoa toan bo
        driver.manage().getCookies();

        driver.navigate().to("https://demo.nopcommerce.com/login?returnUrl=%2F");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        //Alert
        //Window/Tab
        //Frame/iFrame
        driver.switchTo().alert();
        driver.switchTo().window("");
        driver.switchTo().frame("");

        driver.quit();
    }

}
