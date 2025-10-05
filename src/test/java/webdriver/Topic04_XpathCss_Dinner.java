package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic04_XpathCss_Dinner {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.get("https://automationfc.github.io/xpath-dinner/");
        }
    @Test//Get Elements by Xpath
    public void TC_01_Xpath() throws InterruptedException {
        WebElement button=driver.findElement(By.xpath("//a[@class='next']"));

        driver.findElement(By.xpath("//div[@id='gametable']/plate[1]"));//1
        button.click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[@id='gametable']/plate"));//2
        button.click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[@id='gametable']/plate[@id='fancy']"));//3
        button.click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[@id='gametable']/plate/apple[@class='strobe']"));//4
        button.click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[@id='gametable']/plate[@id='fancy']/pickle"));//5
        button.click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[@id='gametable']/apple[@class='small strobe']"));//6
        button.click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[@id='gametable']/orange[@class='small strobe']"));//7
        button.click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[@id='gametable']/bento[1]"));//8
        button.click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[@id='gametable']/plate[1]/pickle"));//9
        button.click();
        Thread.sleep(1000);
    }

    @Test//Get Elements by Css
    public void TC_01_Css() throws InterruptedException {
        WebElement button=driver.findElement(By.cssSelector("a.next"));

        driver.findElement(By.cssSelector("div[id='gametable']>plate:nth-of-type(1)"));//1
        button.click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("div[id='gametable']>plate"));//2
        button.click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("div#gametable>plate#fancy"));//3
        button.click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("div#gametable>plate>apple.strobe"));//4
        button.click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("div#gametable>plate#fancy>pickle"));//5
        button.click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("div[id='gametable']>apple[class='small strobe']"));//6
        button.click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("div[id='gametable']>orange[class='small strobe']"));//7
        button.click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("div[id='gametable']>bento:nth-of-type(1)"));//8
        button.click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("div[id='gametable']>plate:nth-of-type(1)>pickle"));//9
        button.click();
        Thread.sleep(1000);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
