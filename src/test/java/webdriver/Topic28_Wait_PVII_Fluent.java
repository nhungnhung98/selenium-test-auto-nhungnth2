package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Topic28_Wait_PVII_Fluent {
    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait fluentWait;
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
    public void TC_01() throws InterruptedException {
        //Mặc định thời gian tìm lại element(pollig/interval time):500ms=0.5s
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Mặc định thời gian tìm lại element(pollig/interval time):500ms=0.5s
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Custom thời gian tìm lại element(pollig/interval time)
        // 100ms tìm lại 1 lần = 1s sẽ tìm 10 lần
        //explicitWait = new WebDriverWait(driver,Duration.ofSeconds(15), Duration.ofMillis(100));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button"));

        String helloText= fluentWait.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver webDriver) {
                return webDriver.findElement(By.cssSelector("div#finish>h4")).getText();
            }
        });
        Assert.assertEquals(helloText,"Hello World");

    }
    @Test
    public void TC_02() throws InterruptedException {
        driver.get("c");

        WebElement countdownElement = getElement("div#javascript_countdown_time");

        elementFluentWait = new FluentWait<>(countdownElement);

        elementFluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);

        Assert.assertTrue(elementFluentWait.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement element) {
                String text = element.getText();
                System.out.println("Text: " + text);
                return text.endsWith("00");
            }
        }));

    }


    //Viết hàm để sửa lại các hàm findElement/click/getText/isDisplayed với pooling time mới
    private WebElement getElement(String cssLocator) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        return fluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.cssSelector(cssLocator));
            }
        });
    }

    private void clickToElement(String cssLocator) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);

        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                .until(new Function<WebDriver, WebElement>() {
                    @Override
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(By.cssSelector(cssLocator));
                    }
                }).click();
    }

    private String getElementText(String cssLocator) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);//khai báo & khởi tạo

        return fluentWait.withTimeout(Duration.ofSeconds(15))// Tổng thời gian
                .pollingEvery(Duration.ofMillis(100))// Thời gian tìm lại
                .ignoring(NoSuchElementException.class)//nếu gặp exception thì bỏ qua
                .until(new Function<WebDriver, WebElement>() { // ĐK & trả về
                    @Override
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(By.cssSelector(cssLocator));
                    }
                }).getText();
    }

    private boolean isElementDisplay(String cssLocator) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);

        return fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                .until(new Function<WebDriver, WebElement>() {
                    @Override
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(By.cssSelector(cssLocator));
                    }
                }).isDisplayed();
    }
        private String getDateTimeNow() {
        return "";
    }


}

