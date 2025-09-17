package webdriver;

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
        int inputNumber=driver.findElements(By.tagName("input")).size();
        System.out.println(inputNumber);
    }

}
