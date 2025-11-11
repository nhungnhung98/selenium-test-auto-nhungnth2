package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic12_Checkbox_Radio {
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
    public void TC_01() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");
        By leatherTrimCheckbox = By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input");
        By towbarCheckbox = By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span/input");

        //click
        if (!driver.findElement(dualZoneCheckbox).isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("demo-runner")));
            driver.findElement(dualZoneCheckbox).click();
        }
        // kiểm tra
        Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());

        //disable + selected
        Assert.assertFalse(driver.findElement(leatherTrimCheckbox).isEnabled());
        Assert.assertTrue(driver.findElement(leatherTrimCheckbox).isSelected());

        //disable + de-selected
        Assert.assertFalse(driver.findElement(towbarCheckbox).isEnabled());
        Assert.assertFalse(driver.findElement(towbarCheckbox).isSelected());

        //click bỏ chọn
        if (driver.findElement(dualZoneCheckbox).isSelected()) {
            driver.findElement(dualZoneCheckbox).click();
        }

        Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());

    }

    @Test
    public void TC_02() {
        driver.get("https://automationfc.github.io/multiple-fields/");

        List<WebElement> everhadCheckboxes = driver.findElements(By.xpath("//label[contains(text(),'Have you ever had')]/following-sibling::div//input[@type='checkbox']"));

        //Select All
        for (WebElement checkbox : everhadCheckboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }

        //verify all
        for (WebElement checkbox : everhadCheckboxes) {
            Assert.assertTrue(checkbox.isSelected());
        }

        //bo chon all
        for (WebElement checkbox : everhadCheckboxes) {
            if (checkbox.isSelected()) {
                checkbox.click();
            }
        }
        for (WebElement checkbox : everhadCheckboxes) {
            Assert.assertFalse(checkbox.isSelected());
        }

        // specific
        for (WebElement checkbox:everhadCheckboxes){
            if (!checkbox.isSelected() && checkbox.getDomAttribute("value").equals("Gallstones")){
                checkbox.click();
                break;
                //nếu k break thì tiếp tục kiểm tra tiếp dù đã tìm được đk thỏa mãn
                //nếu có break thì khi tìm được đk thỏa mã rồi thì dừng lại
            }
        }

    }

    @Test
    public void TC_03() {
        driver.get("https://login.ubuntu.com/");
        By newUserRadio = By.cssSelector("input#id_new_user");
        By acceptToCheckbox = By.cssSelector("input#id_accept_tos");


    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
