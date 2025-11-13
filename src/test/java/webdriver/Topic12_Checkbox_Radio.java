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
    public void TC_03() throws InterruptedException {
        driver.get("https://login.ubuntu.com/");
        By newUserRadio = By.cssSelector("input#id_new_user");
        By acceptToCheckbox = By.cssSelector("input#id_accept_tos");

        //1. Nếu dùng thẻ input để click --> Failed
        // verify được do isSelected() chỉ dùng cho thẻ input
        /*Assert.assertFalse(driver.findElement(By.cssSelector("input#id_new_user")).isSelected());
        driver.findElement(By.cssSelector("input#id_new_user")).click();*/

        //2. Không dùng thẻ input để click - dùng thẻ khác: span, lable,...
        //thẻ nào đè lên thẻ input thì dùng để click
        //Không verify được vì thẻ label k áp dụng hàm isSelected() được
       /* driver.findElement(By.cssSelector("label[class='new-user']")).click();
        Thread.sleep(5000);
        Assert.assertTrue(driver.findElement(By.cssSelector("label[class='new-user']")).isSelected());*/

        //3. Click thì dùng thẻ label, verify dùng thẻ input
        // thực tế k làm tnay: vì dự án thực tế ít khi design việc 1 element sử dụng 2 locator để handle

        /*driver.findElement(acceptToCheckbox).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(newUserRadio).isSelected());

        driver.findElement(By.cssSelector("label[for='id_accept_tos']")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(acceptToCheckbox).isSelected());*/

        //4. sử dụng thẻ input để click và verify
        // k dùng hàm click() của webElement
        // Dùng hàm click() của javascriptExecutor: sai hành vi của End user
        jsExecutor.executeScript("arguments[0].click();",driver.findElement(newUserRadio));//lấy driver.findElement(newUserRadio) gán vào argument[0] rô click
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(newUserRadio).isSelected());

        jsExecutor.executeScript("arguments[0].click();",driver.findElement(acceptToCheckbox));
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(acceptToCheckbox).isSelected());
         //Chỉ áp dụng JavaScriptExecutor cho custom checkbox/radio thôi
        // còn Default thì k nên dùng - dùng theo WebElement click()

    }
    @Test
    public void TC_04() throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        By conthoRadio = By.cssSelector("div[aria-label='Cần Thơ']");
        By quangbinhCheckbox = By.cssSelector("div[aria-label='Quảng Bình']");
        Thread.sleep(2000);

        driver.findElement(conthoRadio).click();
        driver.findElement(quangbinhCheckbox).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(conthoRadio).getDomAttribute("aria-checked"),"true");
        Assert.assertEquals(driver.findElement(quangbinhCheckbox).getDomAttribute("aria-checked"),"true");
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
