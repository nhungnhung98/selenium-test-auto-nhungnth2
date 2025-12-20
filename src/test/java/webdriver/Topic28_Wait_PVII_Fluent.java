package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic28_Wait_PVII_Fluent {
    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait fluentWait;
    @BeforeClass
    public void beforClass() {
        driver = new FirefoxDriver();
    }


    @Test
    public void TC_01() throws InterruptedException {
        //Mặc định thời gian tìm lại element(pollig/interval time):500ms=0.5s
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Mặc định thời gian tìm lại element(pollig/interval time):500ms=0.5s
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Custom thời gian tìm lại element(pollig/interval time)
        // 100ms tìm lại 1 lần = 1s sẽ tìm 10 lần
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(15), Duration.ofMillis(100));

        fluentWait= new FluentWait(driver);

        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100));

        //Quy tắd để áp dụng FluentWait

    }



    private String getDateTimeNow() {
        return "";
    }


}

