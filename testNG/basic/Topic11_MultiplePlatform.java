package basic;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic11_MultiplePlatform {
    WebDriver driver;
    String enviromentUrl;
    Platform platform;
    @Parameters({"browser","url","platformName"})

    @BeforeClass
    public void initialBrowser(String browserName, String enviromentName, String platformName) {

        System.out.println("Browser name = "+browserName);
        System.out.println("Enviroment URL= "+enviromentName);
        System.out.println("Platform name"+platformName);
        switch (browserName.toUpperCase()) {
            case "FIREFOX":
                driver = new FirefoxDriver();
                break;
            case "CHROME":
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser is not support");

        }

        switch (enviromentName.toUpperCase()) {
            case "DEV":
                enviromentUrl="https://dev.fahasa.com/";
                break;
            case "TESTING":
                enviromentUrl="https://testing.fahasa.com/";
                break;
            case "STAGING":
                enviromentUrl="https://staging.fahasa.com/";
                break;
            case "PROD":
                enviromentUrl="https://www.fahasa.com/";
                break;

            default:
                throw new RuntimeException("Enviroment name is not support");

        }

        switch (platformName.toUpperCase()) {
            case "WINDOWS":
                platform = Platform.WINDOWS;
                break;
            case "MAC":
                platform = Platform.MAC;
                break;
            case "LINUX":
                platform = Platform.LINUX;
                break;
            case "ANDROID":
                platform = Platform.ANDROID;
                break;
            case "IOS":
                platform = Platform.IOS;
                break;
            default:
                throw new RuntimeException("Environment name is not support");
        }
        System.out.println("Platform name: " + platformName);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(enviromentUrl+"/customer/account/create");

    }

    @Test
    public void TC_01_Fahasa() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        By loginButton = By.cssSelector("button.fhs-btn-login");

        //verify button disable
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());
        Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(), "#000000");

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("0987654321");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("098332467");

        //verify button enable
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());
        Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(), "#C92127");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
