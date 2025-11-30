package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic18_Window_Tab {
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
    public void TC_01_Github() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //Lay ra ID hiện tại mà driver đang đứng ở đó
        String githubWindowID = driver.getWindowHandle();
        System.out.println(githubWindowID);

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSecond(3);

        switchToWindowbyID(githubWindowID);

        String googleWindowID = driver.getWindowHandle();

        switchToWindowbyID(googleWindowID);
        sleepInSecond(3);

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSecond(3);

        switchToWindowbyTitle("Facebook");

        switchToWindowbyTitle("Selenium Webdriver");
        sleepInSecond(3);

        closeAllWindowWithoutParent(githubWindowID);

    }

    private void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //Dung khi chi co 2 window
    private void switchToWindowbyID(String windowID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    //Chon theo title
    private void switchToWindowbyTitle(String expectedTitle) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            driver.switchTo().window(id);
            sleepInSecond(1);
            if (driver.getTitle().contains(expectedTitle)) ;
            break;
        }
    }

    private void closeAllWindowWithoutParent(String windowID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);

                //Dong Tab cua driver dang active
                driver.close();
            }
            break;
        }
    }

    @Test
    public void TC_02_Techpanda() throws InterruptedException {
        driver.get("https://live.techpanda.org");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        sleepInSecond(2);

        driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::" +
                "div[@class='actions']//a[text()='Add to Compare']")).click();
        sleepInSecond(2);

        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::" +
                "div[@class='actions']//a[text()='Add to Compare']")).click();
        sleepInSecond(2);

        driver.findElement(By.xpath("//button[@title='Compare']")).click();
        sleepInSecond(2);

        switchToWindowbyTitle("Products Comparison List");

        //Click vao close btn
        driver.findElement(By.xpath("//button[@title='Close Window']")).click();
        sleepInSecond(2);
        // nếu thao tác tiếp sẽ báo lỗi: là đang thao tác trên 1 window bị đóng rồi
        //driver.getTitle();

        // cần switch lại trang chính
        switchToWindowbyTitle("Mobile");
    }
    @Test
    public void TC_03_Cambridge() {
        driver.get("https://dictionary.cambridge.org/vi/");

        String homeWindowID = driver.getWindowHandle();
        sleepInSecond(2);

        driver.findElement(By.xpath("//header[@id='header']//span[text()='Đăng nhập']")).click();
        sleepInSecond(2);

        switchToWindowbyTitle("Login");

        driver.findElement(By.xpath("//input[@value='Log in']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("span[id*='login-form-loginID']")).getText(),"This field is required");
        Assert.assertEquals(driver.findElement(By.cssSelector("span[id*='login-form-password']")).getText(),"This field is required");
        sleepInSecond(2);

        closeAllWindowWithoutParent(homeWindowID);
        sleepInSecond(2);

        String keyword ="Selenium";

        driver.findElement(By.cssSelector("input#searchword")).sendKeys(keyword);
        driver.findElement(By.cssSelector("input#searchword")).sendKeys(Keys.ENTER);

        Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'uk')]/preceding-sibling::div[@class='di-title']/span/span")).getText(),keyword.toLowerCase());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
