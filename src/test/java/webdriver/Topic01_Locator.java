package webdriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;

public class Topic01_Locator {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
    }

    @Test
    public void TC_01_ID() {
        driver.findElement(By.id("email"));
        driver.findElement(By.id("pass"));
        driver.findElement(By.id("search"));
        driver.findElement(By.id("newsletter"));
    }

    @Test
    public void TC_02_className() {
        driver.findElement(By.className("validate-password"));
    }

    @Test
    public void TC_03_name() {
        driver.findElement(By.name("login[username]"));
        driver.findElement(By.name("login[password]"));
        driver.findElement(By.name("q"));
    }

    @Test
    public void TC_04_tagName() {
        //Tim ra co bao nhieu the HTMl giong nhau
        int inputNumber = driver.findElements(By.tagName("input")).size();
        System.out.println(inputNumber);
    }

    @Test
    public void TC_05_linkText() {
        driver.findElement(By.linkText("MY ACCOUNT"));
        driver.findElement(By.linkText("ORDERS AND RETURNS"));
    }

    @Test
    public void TC_06_partial_linkText() {
        driver.findElement(By.partialLinkText("ACCOUNT"));
        driver.findElement(By.partialLinkText("RETURNS"));
    }

    @Test
    public void TC7_0_Css() {
        //Css ID
        driver.findElement(By.cssSelector("input[id='email']"));
        driver.findElement(By.cssSelector("#email"));
        driver.findElement(By.cssSelector("input#email"));

        //Css Class
        driver.findElement(By.cssSelector("div[class='account-login']"));
        driver.findElement(By.cssSelector(".account-login"));
        driver.findElement(By.cssSelector("div.account-login"));

        //Css Name
        driver.findElement(By.cssSelector("input[name='login[username]']"));

        //Css tagName
        driver.findElement(By.cssSelector("input"));

        // Css Link
        driver.findElement(By.cssSelector("a[href='http://live.techpanda.org/index.php/customer/account/']"));

        // Css Partial Link
        driver.findElement(By.cssSelector("a[href*='customer/account/']"));
    }

    @Test
    public void TC_08_Xpath() {
        // xpath -ID
        driver.findElement(By.xpath("//input[@id='email']"));

        // xpath-class
        driver.findElement(By.xpath("//div[@class='account-login']"));
        driver.findElement(By.xpath("//div[@class='col-2 registered-users']"));

        // xpath-name
        driver.findElement(By.xpath("//input[@name='login[username]']"));

        // xpath-tagName
        driver.findElement(By.xpath("//input"));

        // xpath-Link
        driver.findElement(By.xpath("//a[@href='http://live.techpanda.org/index.php/customer/account/']"));
        driver.findElement(By.xpath("//a[text()='My Account']"));

        // xpath-Partial Link
        driver.findElement(By.xpath("//a[contains(@href,'customer/account/')]"));
        driver.findElement(By.xpath("//a[contains(text(),'My Account')]"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
