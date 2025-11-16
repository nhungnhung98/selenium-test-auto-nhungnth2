package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic14_Actions_2 {
    WebDriver driver;
    Select select;
    JavascriptExecutor jsExecutor;
    Actions actions;
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforClass() {
        driver = new FirefoxDriver();
        //driver.manage().window().setSize(new Dimension(1366,768));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));// chờ cho việc tìm kiếm element
        driver.manage().window().maximize();

        actions = new Actions(driver);
    }

    @Test
    public void TC_01_Click_and_Hold_Block(){
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allItems =driver.findElements(By.cssSelector("ol#selectable>li"));

        //Chon tu 1-12

        actions.clickAndHold(allItems.getFirst()).pause(Duration.ofSeconds(2)).perform();
        actions.moveToElement(allItems.get(11)).release().perform();
        actions.pause(Duration.ofSeconds(2)).perform();

        //Verify da chon
        Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable>li.ui-selected")).size(), 12);

    }
    @Test
    public void TC_02_Click_and_Hold_Random(){
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allItems =driver.findElements(By.cssSelector("ol#selectable>li"));

        //Nhan phim CTRL xuong chưa nhả ra
        Keys keys = null;

        if (osName.contains("Windows")) {
            keys = Keys.CONTROL;
        } else keys = Keys.COMMAND;

        actions.keyDown(keys).perform();

        // Chon 3 5 7 9 13 17 21 25 29
        actions.click(allItems.get(2))
                .click(allItems.get(4))
                .click(allItems.get(6))
                .click(allItems.get(11))
                .click(allItems.get(12))
                .click(allItems.get(16))
                .click(allItems.get(20))
                .click(allItems.get(24))
                .click(allItems.get(28))
                .perform();

       //Nha phim CTRL ra
        actions.keyUp(keys).perform();
        actions.pause(Duration.ofSeconds(2)).perform();

        //Verify da chon
        Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable>li.ui-selected")).size(), 8);

    }

    @Test
    public void TC_03_DoubleClick(){
        //Do element can scroll xuong duoi moi nhin thay de click nen can xu ly them Js
        driver.get("https://automationfc.github.io/basic-form/");

        List<WebElement> allItems =driver.findElements(By.cssSelector("ol#selectable>li"));

        if (driver.toString().contains("Firefox")) {
            //element phải trong viewport thì mới scroll được
            //actions.scrollToElement(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();

            // scroll đến mép trên cùng của viewport
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);",
                    driver.findElement(By.xpath("//button[text()='Double click me']")));

            // scroll đến mép dưới cùng của viewport
        /*jsExecutor.executeScript("arguments[0].scrollIntoView(false);",
                driver.findElement(By.xpath("//button[text()='Double click me']")));*/
        }

        actions.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
    }
    @Test
    public void TC_04_Right_Click() throws InterruptedException {
        //Do element can scroll xuong duoi moi nhin thay de click nen can xu ly them Js
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");

        //Quit context menu: chua hien thi
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

        //Click chuột phải
        actions.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
        actions.pause(Duration.ofSeconds(2)).perform();

        //Quit context menu hien thi
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

        // Hover vào Quit menu
        actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
        actions.pause(Duration.ofSeconds(2)).perform();

        // Quit menu sẽ có thêm trạng thái hover
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());

        // Click vào Quit
        driver.findElement(By.cssSelector("li.context-menu-icon-quit")).click();

        //Accept alert
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent()).accept();
        Thread.sleep(2000);

        //Quit context menu chưa hiển thị
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
