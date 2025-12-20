package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic27_Wait_PVI_Mix {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforClass() {
        driver = new FirefoxDriver();
    }


    @Test
    public void TC_01_Element_Found() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://demo.nopcommerce.com/login");

        System.out.println("Start time: " + getDateTimeNow());
        driver.findElement(By.cssSelector("input#small-searchterms"));
        System.out.println("End time: " + getDateTimeNow());

        System.out.println("Start time: " + getDateTimeNow());
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#small-searchterms")));
        System.out.println("End time: " + getDateTimeNow());
    }

    @Test
    public void TC_02_Element_Not_Found_Only_Implicit() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://demo.nopcommerce.com/login");

        //3. Nếu như tìm element mà k thấy
        // Sẽ cố gắng tìm đi tìm lại cứ mỗi 1/2s 1 lần
        // nếu giữa chừng tìm thấy thì k cần chờ hết thời gian còn lại
        // nếu hết time còn lại mà k tìm thấy thì show ra exception: NosuchElement và đánh fail testcase tại vị trí code đó
        // k chạy tiếp các step còn lại
        System.out.println("Start time: " + getDateTimeNow());
        try {
            driver.findElement(By.cssSelector("input#small"));
        } catch (Exception e) {
            System.out.println("End time: " + getDateTimeNow());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TC_03_Element_Not_Found_Only_Explicit() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://demo.nopcommerce.com/login");

        System.out.println("Start time: " + getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#small")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("End time: " + getDateTimeNow());
        }

    }
    @Test
    public void TC_04_Element_Not_Found_Combine() throws InterruptedException {
        //Equal/Less Than/More Than đều có output giống nhau
        // implicit luôn ưu tiên chạy trước vì dù muốn áp dụng bất kỳ action/đk gì lên Element thì phải tìm được nó trước

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//B1
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));//B2
        //Sau khi B1 bắt đầu được 0.5s>> Bắt đầu chạy B2>> Nên tổng thời gian chạy nếu chạy hết tgian sẽ khoảng 10.5>> 11

        driver.get("https://demo.nopcommerce.com/login");

        System.out.println("Start time: " + getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#small")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("End time: " + getDateTimeNow());
        }
    }

    @Test
    public void TC_05_Element_Not_Found_Combine() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));//B1
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));//B2

        driver.get("https://demo.nopcommerce.com/login");

        System.out.println("Start time: " + getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#small"))));
            //TC sẽ bị lỗi ở giây thứ 3 do findElement chịu ảnh hưởng của implicit (B1)
            // còn visibilityOf chịu ảnh hưởng ExplicitWait (B2)
            // Nếu chỉ dùng duy nhất Explicit thì các hàm có tham số là Element sẽ nhận timeout=0
            // Nếu chỉ dùng duy nhất Explicit thì chỉ dùng các hàm có tham số là By

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("End time: " + getDateTimeNow());
        }
    }


    private String getDateTimeNow() {
        return "";
    }


}

