package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Topic22_Wait_PII_FindElement {
    WebDriver driver;
    WebDriverWait explicitWait;


    @BeforeClass
    public void beforClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));// chờ cho việc tìm kiếm element
        driver.manage().window().maximize();

        explicitWait =new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Find_Element() throws InterruptedException {

        driver.get("https://demo.nopcommerce.com/login");

        //1. Nếu như tìm element thấy duy nhất 1 cái
        // k cần chờ hết tổng thời gian 10s
        // chuyển qua action tiếp theo luôn
        System.out.println("Start time: " + getDateTimeNow());
        driver.findElement(By.cssSelector("input#small-searchterms"));
        System.out.println("End time: " + getDateTimeNow());

        //2. Nếu như tìm element nhiều hơn 1 cái
        // k cần chờ hết tổng thời gian 10s
        //  Nó luôn lấy element đầu tiên để thao tác
        System.out.println("Start time: " + getDateTimeNow());
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("nhung@gmail.com");
        System.out.println("End time: " + getDateTimeNow());

        //3. Nếu như tìm element không thấy
        //Cố gắng tìm lại nửa giây 1 lần
        // Nếu giữa chừng tìm thì k cần chờ hết thời gian còn lại
        // Nếu hết time mà không tìm thấy thì show ra exception NoSuchElement và đánh fail testcase tại vị trí code đó
        // Không chạy step tiếp theo
        System.out.println("Start time: " + getDateTimeNow());
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("nhungnhung");
        System.out.println("End time: " + getDateTimeNow());
    }
    @Test
    public void TC_02_Find_Elements() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/login");

        List<WebElement> elementList =null;
        //Nếu như tìm element có duy nhất 1 cái
        System.out.println("Start time: " + getDateTimeNow());
        driver.findElements(By.cssSelector("input#small-searchterms"));
        System.out.println("Tổng số lượng element trong List:"+elementList.size());
        System.out.println("End time: " + getDateTimeNow());

        //2. Nếu như tìm element nhiều hơn 1 cái
        System.out.println("Start time: " + getDateTimeNow());
        elementList=driver.findElements(By.xpath("//a[@href]]"));
        System.out.println("End time: " + getDateTimeNow());

        System.out.println("Tổng số lượng element trong list: " + elementList.size());

        for (WebElement element : elementList) {
            System.out.println(element.getDomProperty("href"));
        }

        //3.Nếu không tìm thấy element
        //Chờ hết timeout không tìm thấy không đánh fail TC
        //Trả về 1 list rỗng =0
        System.out.println("Start time: " + getDateTimeNow());
        elementList=driver.findElements(By.xpath("//a[@FirstName]]"));
        System.out.println("End time: " + getDateTimeNow());

        System.out.println("Tổng số lượng element trong list: " + elementList.size());

    }
    @Test
    public void TC_03_Unique_Element() throws InterruptedException {
        driver.get("https://live.techpanda.org/");
        // Lấy element đầu tiên-> Đang bị ẩn >> Không thao tác lên được
        driver.findElement(By.xpath("//a[@title='My Account']")).click();

    }

    private String getDateTimeNow() {
        return new Date().toString();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();

    }
}

