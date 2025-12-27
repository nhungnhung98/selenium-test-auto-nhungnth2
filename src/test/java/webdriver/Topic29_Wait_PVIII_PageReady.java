package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic29_Wait_PVIII_PageReady {
    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait<WebDriver> fluentWait;
    FluentWait<WebElement> elementFluentWait;

    @BeforeClass
    public void beforClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        fluentWait = new FluentWait(driver);
        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(15),Duration.ofMillis(200));
    }

    @Test
    public void TC_01_Nopcommerce(){
        driver.get("https://admin-demo.nopcommerce.com");

        driver.findElement(By.cssSelector("input#Email")).clear();
        driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.cssSelector("input#Password")).clear();
        driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.login-button")).click();

        Assert.assertTrue(isAjaxBusyIconInvisible());

        driver.findElement(By.xpath("//li[@class='nav-item has-treeview']/a[@class='nav-link']/p[contains(string(),'Sales')]")).click();
        driver.findElement(By.xpath("//ul[@class='nav nav-treeview']/li/a[@class='nav-link']/p[contains(string(),'Orders')]")).click();

        Assert.assertTrue(isAjaxBusyIconInvisible());

        Assert.assertEquals(driver.findElement(By.cssSelector("div.content-header>h1")).getText(),"Orders");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public boolean isAjaxBusyLoadingInvisible(){
        WebDriverWait explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));
        return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
    }

    public boolean isAjaxBusyIconInvisible() {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);

        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return fluentWait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return !driver.findElement(By.cssSelector("div#ajaxBusy")).isDisplayed();
            }
        });
    }

    private boolean isPageLoadedSuccess_WebdriverWait(){
        WebDriverWait explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;

        //ĐK 1
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return (Boolean) jsExecutor.executeScript("return(window.jQuery!=null)&&(jQuery.active===0);");
            }
        };

        //ĐK 2
        ExpectedCondition<Boolean> jLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return fluentWait.until(jQueryLoad)&& fluentWait.until(jLoad);
    }

    private boolean isPageLoadedSuccess_FluentWait(){
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(JavascriptException.class);

        JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;

        //ĐK 1
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return (Boolean) jsExecutor.executeScript("return(window.jQuery!=null)&&(jQuery.active===0);");
            }
        };

        //ĐK 2
        ExpectedCondition<Boolean> jLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return fluentWait.until(jQueryLoad)&& fluentWait.until(jLoad);
    }
}
