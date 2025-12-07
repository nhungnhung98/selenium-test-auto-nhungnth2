package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic20_Upload {
    WebDriver driver;
    //lay duong dan tuong doi
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));// chờ cho việc tìm kiếm element
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_SingleFile() throws InterruptedException {
        //File de o dau>> Qua may kc khong tim thay huong dan
        //File co dinh tren may>> Qua may khac khong tim thay duong dan
        //=>Bat ky ma nao cung chay duoc
        // De file trong chinh bo sourcecode
        //=> Lay duong dan tuong doi cua file ra
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By uploadFileBy= By.cssSelector("input[type='file']");

        //Load file
        driver.findElement(uploadFileBy).sendKeys(mountainFilePath);
        driver.findElement(uploadFileBy).sendKeys(riverFilePath);
        driver.findElement(uploadFileBy).sendKeys(treeFilePath);

        Thread.sleep(4000);

        List<WebElement> startUploadButton = driver.findElements(By.cssSelector("table button.start"));
        for (WebElement startButton : startUploadButton) {
            startButton.click();
            Thread.sleep(1000);
        }

        // verify upload file
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + mountainFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + riverFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + treeFile + "']")).isDisplayed());
    }
    @Test
    public void TC_02_Multiple_File() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By uploadFileBy = By.cssSelector("input[type='file']");

        //load file
        driver.findElement(uploadFileBy).sendKeys(mountainFilePath + "\n" + riverFilePath + "\n" + treeFilePath);
        Thread.sleep(3000);

        //verify file được load lên thành công
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + mountainFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + riverFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + treeFile + "']")).isDisplayed());
        Thread.sleep(2000);

        //upload file
        List<WebElement> startUploadButton = driver.findElements(By.cssSelector("table button.start"));
        for (WebElement startButton : startUploadButton) {
            startButton.click();
            Thread.sleep(2000);
        }

        // verify upload file
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + mountainFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + riverFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + treeFile + "']")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();

    }
}

