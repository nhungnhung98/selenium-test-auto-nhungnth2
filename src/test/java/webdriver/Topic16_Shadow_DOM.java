package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic16_Shadow_DOM {
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
    public void TC_01_Home_Shop() throws InterruptedException {
        driver.get("https://shop.polymer-project.org/");

        WebElement shopAppShadowHost =driver.findElement(By.cssSelector("shop-app"));
        SearchContext shopAppShadowRoot =shopAppShadowHost.getShadowRoot();

        WebElement shopHomeShadowHost =shopAppShadowRoot.findElement(By.cssSelector("iron-page>shop-home"));
        SearchContext shopHomeShadowRoot = shopHomeShadowHost.getShadowRoot();

        shopHomeShadowRoot.findElement(By.cssSelector("a[href*='mens_outerwear']~shop-button")).click();
    }

    @Test
    public void TC_02_Nested() throws InterruptedException {
    driver.get("https://automationfc.github.io/shadow-dom/");

        //driver đang thao tác với dom bên ngoài, chưa vào shadow
        Assert.assertEquals(driver.findElement(By.xpath("//a")).getText(), "scroll.html");
        Assert.assertEquals(driver.findElements(By.xpath("//a")).size(), 1);

        WebElement firstShadowHost =driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext firstShadowRoot =firstShadowHost.getShadowRoot();

        //Dang thao tac voi shadow dom dau tien
       Assert.assertEquals(firstShadowRoot.findElement(By.cssSelector("span#shadow_content>span")).getText(),"some text");
       Assert.assertEquals(firstShadowRoot.findElement(By.cssSelector("a")).getText(), "nested scroll.html");
       Assert.assertEquals(firstShadowRoot.findElements(By.cssSelector("a")).size(), 1);

       //Switch qua
        WebElement secondShadowHost =driver.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext secondShadowRoot =secondShadowHost.getShadowRoot();

        //Dang thao tac voi Shaow DOM thu 2
        Assert.assertEquals(secondShadowRoot.findElement(By.cssSelector("div#nested_shadow_content>div")).getText(),"nested text");

    }

    @Test
    public void TC_03() throws InterruptedException {
        driver.get("https://books-pwakit.appspot.com/");
        Thread.sleep(4000);

        WebElement bookappShadowHost = driver.findElement(By.cssSelector("book-app"));
        SearchContext bookShadowRoot = bookappShadowHost.getShadowRoot();

        bookShadowRoot.findElement(By.cssSelector("book-input-decorator>input#input")).sendKeys("Harry Porter");

        WebElement bookDecoratorHost = bookShadowRoot.findElement(By.cssSelector("book-input-decorator"));
        SearchContext bookDecoratorRoot = bookDecoratorHost.getShadowRoot();

        bookShadowRoot.findElement(By.cssSelector("div.icon")).click();

        Thread.sleep(5000);

        bookappShadowHost = driver.findElement(By.cssSelector("book-app"));
        bookShadowRoot = bookappShadowHost.getShadowRoot();

        WebElement bookExplorerShadowHost = bookShadowRoot.findElement(By.cssSelector("book-explore"));
        SearchContext bookExplorerShadowRoot = bookExplorerShadowHost.getShadowRoot();
        Thread.sleep(3000);

        List<WebElement> listBookItems = bookExplorerShadowRoot.findElements(By.cssSelector("section>ul.books>li>book-item"));

        for (WebElement bookItem : listBookItems) {
            SearchContext bookItemShadowRoot = bookItem.getShadowRoot();
            System.out.println(bookItemShadowRoot.findElement(By.cssSelector("div.title-container>h2.title")).getText());
        }


    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
