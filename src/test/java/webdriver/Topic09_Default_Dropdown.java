package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic09_Default_Dropdown {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        //Select select;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01() throws InterruptedException {
        driver.get("https://www.facebook.com/r.php?entry_point=login");
        String day = "30";
        String month = "Dec";
        String year = "1996";

        // Khởi tạo thư viện select khi dropdown xuất hiện
        select = new Select(driver.findElement(By.cssSelector("select#day")));
        select.selectByVisibleText(day);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), day);

        select = new Select(driver.findElement(By.cssSelector("select#month")));
        select.selectByVisibleText(month);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), month);

        select = new Select(driver.findElement(By.cssSelector("select#year")));
        select.selectByVisibleText(year);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), year);

        // chọn 1 tháng bất kỳ trong dropdown
        select.selectByIndex(3);// k phù hợp cho sự cập nhật item (VD: sát nhập tỉnh)
        Thread.sleep(2000);

        select.selectByValue("9");// có những site value k có ý nghĩa
        Thread.sleep(2000);

        select.selectByVisibleText("Dec");// nếu có cập nhật thì k a/h; text là 1 giá trị bắt buộc của option; text có ý nghĩa --> dùng cái này
        Thread.sleep(2000);
//        select.selectByContainsVisibleText("Au");// performance chậm --> k dùng
//        Thread.sleep(2000);

        // kt chọn thành công? --> item đc chọn sẽ nhảy lên đầu tiên (first selected)
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Dec");

        // kt dropdown có bao nhiêu item?
        Assert.assertEquals(select.getOptions().size(), 12);

        // dropdown có cho phép chọn nhiều hay k?
        Assert.assertFalse(select.isMultiple());


    }

    @Test
    public void TC_02() throws InterruptedException {
        driver.get("https://rode.com/wheretobuy");
        new Select(driver.findElement(By.cssSelector("select#country"))).selectByVisibleText("Vietnam");
        Thread.sleep(2000);

        // k phải multiple
        Assert.assertFalse(new Select(driver.findElement(By.cssSelector("select#country"))).isMultiple());

        driver.findElement(By.cssSelector("input#map_search_query")).sendKeys("Ho Chi Minh");
        driver.findElement(By.xpath("//button[text()='Search']")).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElements(By.xpath("//h3[text()='Dealers']/following-sibling::div/div")).size(), 16);
        List<WebElement> dealers = driver.findElements(By.xpath("//h3[text()='Dealers']/following-sibling::div/div//h4"));
        for (WebElement temp : dealers) {//for each
            System.out.println(temp.getText());
        }
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
