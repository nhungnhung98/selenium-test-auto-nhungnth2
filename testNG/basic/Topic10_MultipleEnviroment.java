package basic;

import org.openqa.selenium.By;
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

public class Topic10_MultipleEnviroment {
    WebDriver driver;
    String enviromentUrl;

    @Parameters({"browser","url"})

    @BeforeClass
    public void initialBrowser(String browserName, String enviromentName) {

        System.out.println("Browser name = "+browserName);
        System.out.println("Enviroment URL= "+enviromentName);

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
