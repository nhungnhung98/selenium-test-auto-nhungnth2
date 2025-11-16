package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic14_Actions_3 {
    WebDriver driver;
    Select select;
    JavascriptExecutor jsExecutor;
    Actions actions;

    @BeforeClass
    public void beforClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));// chờ cho việc tìm kiếm element
        driver.manage().window().maximize();

        actions = new Actions(driver);
    }

    @Test
    public void TC_01_Drag_and_Drop_HTML4(){
        driver.get("https://automationfc.github.io/kendo-drag-drop/");


        WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));

        actions.dragAndDrop(sourceCircle, targetCircle).perform();

        Assert.assertEquals(Color.fromString(targetCircle.getCssValue("background-color")).asHex().toUpperCase(), "#03A9F4");
        Assert.assertEquals(targetCircle.getText(), "You did great!");

    }
    @Test
    public void TC_01_Drag_and_Drop_HTML5(){
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        WebElement sourceCirle = driver.findElement(By.cssSelector("div#colum-a"));
        WebElement targetCircle =driver.findElement(By.cssSelector("div#colum-b"));

        actions.dragAndDrop(sourceCirle, targetCircle).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#colum-a>header")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#colum-b>header")).getText(),"a");


    }
    @Test
    public void TC_03(){

    }




    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
