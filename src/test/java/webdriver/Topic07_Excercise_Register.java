package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic07_Excercise_Register {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Empty() throws InterruptedException {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("");
        driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("");
        driver.findElement(By.cssSelector("button.btn_pink_sm.fs16")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#txtEmail-error")).getText(),"Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");

    }
    @Test
    public void TC_02_Invalid_Email() throws InterruptedException {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("Nhung");
        driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("nhung@123");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("nhung@123");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("0352620000");
        driver.findElement(By.cssSelector("button.btn_pink_sm.fs16")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
    }
    @Test
    public void TC_03_Invalid_Phone() throws InterruptedException {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("Nhung");
        driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("nhung@gmail.com");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("nhung@gmail.com ");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("03526");
        driver.findElement(By.cssSelector("button.btn_pink_sm.fs16")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

        //Phone <10
        driver.findElement(By.cssSelector("input#txtPhone")).clear();
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("035262000000");
        driver.findElement(By.cssSelector("button.btn_pink_sm.fs16")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

        //Phone>10
        driver.findElement(By.cssSelector("input#txtPhone")).clear();
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("035262000000");
        driver.findElement(By.cssSelector("button.btn_pink_sm.fs16")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

        //Phone invalid
        driver.findElement(By.cssSelector("input#txtPhone")).clear();
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("3526200");
        driver.findElement(By.cssSelector("button.btn_pink_sm.fs16")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
