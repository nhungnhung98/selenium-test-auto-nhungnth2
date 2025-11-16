package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v125.network.Network;
import org.openqa.selenium.devtools.v125.network.model.Headers;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;

public class Topic13_Alert {
    WebDriver driver;
    Select select;
    JavascriptExecutor jsExecutor;


    @BeforeClass
    public void beforClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));// chờ cho việc tìm kiếm element
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        //Cho cho alert duoc presence
        //Presence: Co xuat hien trong html (khong bat buoc hien thi tren UI)
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent());

        // Thao tac voi Alert
        Alert alert =driver.switchTo().alert();
        Assert.assertEquals(alert.getText(),"I am a JS Alert");

        //Accept Alert
        alert.accept();

        //Cancel Alert
        //alert.dismiss();

        //Lay title/text cua Alert
        //alert.getText();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked an alert successfully");
    }


    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent());

        Alert alert =driver.switchTo().alert();

        Assert.assertEquals(alert.getText(),"I am a JS Confirm");

        alert.dismiss();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");


    }

    @Test
    public void TC_03_Promp_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent());

        Alert alert =driver.switchTo().alert();

        Assert.assertEquals(alert.getText(),"I am a JS Prompt");


        String text="Alert Prompt";

        alert.sendKeys(text);

        Thread.sleep(3000);

        alert.accept();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + text);

    }
    @Test
    public void TC_04_Accept_Alert() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");

        driver.findElement(By.cssSelector("button[class*='search-box-button]")).click();

        Alert alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(),"Please enter some search keyword");

        alert.accept();

        driver.findElement(By.cssSelector("input#small-searchterms")).sendKeys("macbook");
        driver.findElement(By.cssSelector("button[class*='search-box-button']")).click();


    }
    @Test
    public void TC_05_Authenticator_Alert() throws InterruptedException {
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth"); //truyen luon username password vao link
        //http://username:password@url

        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(),"Congratulations! You must have the proper credentials.");
    }
    @Test
    public void TC_06_Authentication_Alert() {
        driver.get("http://the-internet.herokuapp.com/");

        String url = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getDomProperty("href");

        String[] urlArray = url.split("//");
        String username = "admin";
        String password = "admin";
        url = urlArray[0] + "//" + username + ":" + password + "@" + urlArray[1];
        driver.get(url);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='example']/p")).getText(), "Congratulations! You must have the proper credentials.");
    }
//    @Test
//    public void TC_07_Authentication_Alert() {
//        driver.get("https://the-internet.herokuapp.com/basic_auth");
//
//       //get dev tool object
//        DevTools devTools = ((HasDevTools) driver).getDevTools();
//
//       // start new session
//       devTools.createSession();
//
//      //Enable the network domain of devtool
//       devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(),Optional.of(true)));
//
//        //Encode user/pass
//        Map<String, Object> headers = new HashMap<String, Object>();
//        String basicAuthen = "Basic" + new String(new Base64().encode(String.format("%s:%s", "admin", "admin").getBytes()));
//        headers.put("Authorization", basicAuthen);
//
//        //send to header
//        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));
//        driver.get("https://the-internet.herokuapp.com/basic_auth");
//
//        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(), "Congratulations! You must have the proper credentials.");
//    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
