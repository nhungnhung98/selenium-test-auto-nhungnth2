package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic02_RelativeLocator {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
    }
    @Test
    public void TC_01(){
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
        //Tim Remmember Me text
        WebElement rememberMeText= driver.findElement(RelativeLocator.with(By.tagName("label"))
                // Dung tren btn Login
                .above(By.cssSelector("button.login-button"))

                //Dung duoi text pass word
                .below(By.id("Password"))

                //Dung ben phai Remmember checkbox
                .toRightOf(By.id("RememberMe"))

                //Dung ben trai text Forgot password
                .toLeftOf(By.xpath("//a[text()='Forgot password?']"))
        );

        System.out.println(rememberMeText.getText());
    }

    @Test
    public void TC_02(){

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
