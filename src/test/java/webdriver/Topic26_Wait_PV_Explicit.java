package webdriver;

import com.sun.source.tree.AssertTree;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.regex.Pattern;

public class Topic26_Wait_PV_Explicit {
    WebDriver driver;
    WebDriverWait explicitWait;
    String uploadFilePath = System.getProperty("user.dir") + "\\uploadFiles\\";

    String mountainFile = "Mountain.jpg";
    String riverFile = "River.jpg";
    String treeFile = "Tree.jpg";

    String mountainFilePath = uploadFilePath + mountainFile;
    String riverFilePath = uploadFilePath + riverFile;
    String treeFilePath = uploadFilePath + treeFile;

    @BeforeClass
    public void beforClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01() throws InterruptedException {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationfc.github.io/dynamic-loading");

        driver.findElement(By.cssSelector("div#start>button")).click();

        //Invisible
        //1. Chờ cho step trước hoàn thành
        //Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading"))));

        //Visible
        //2. Sau khi step trước được bắt đầu>> Nó sẽ chờ cho 1 đói tượng của step sau xuất hiện
        //Không quan tâm cái step trước đã hoàn thành xong hay chưa
        Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4"))).getText(),
       "Hello World!");
    }

    @Test
    public void TC_02_Ajax() throws InterruptedException {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        //Chờ trong vòng 30 giây để date picker hiển thị
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1")));

        // Wait cho text được xuất hiện trong vòng 30s
        Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe
                (By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"), "No Selected Dates to display.")));

        // Wait cho element được phép click rồi click vào
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[text()='18']"))).click();

        // Wait cho Loading icon biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated
                (By.cssSelector("div:not([style='display:none;'])>div.raDiv"))));

        //Wait cho text được cập nhật lên
        Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe
                (By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"), "Thursday, December 18, 2025")));

    }

    @Test
    public void TC_03_Upload() throws InterruptedException {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://gofile.io/home");

        //Chờ cho tất cả loading icon ở trên trang hiện tại biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.animate-spin")))));

        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file']")))
                .sendKeys(mountainFilePath + "\n" + riverFilePath + "\n" + treeFilePath);

        driver.findElement(By.cssSelector("input[type='file']"));

        //Chờ cho tất cả các upload progeress bar biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.file-progressbar")))));

        String uploadURL = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.linkSuccessCard"))).getDomAttribute("href");
        driver.get(uploadURL);

        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements
                (By.cssSelector("div.animate-spin")))));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'item_open') and text()='" + mountainFile + "']")));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'item_open') and text()='" + riverFile + "']")));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'item_open') and text()='" + treeFile + "']")));

    }

}

