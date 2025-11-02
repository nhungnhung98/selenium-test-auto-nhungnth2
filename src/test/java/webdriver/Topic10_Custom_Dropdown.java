package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic10_Custom_Dropdown {
    WebDriver driver;
    Select select;
    JavascriptExecutor jsExecutor;


    @BeforeClass
    public void beforClass() {
        driver = new FirefoxDriver();
        //Select select;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));// chờ cho việc tìm kiếm element
        driver.manage().window().maximize();
        jsExecutor = (JavascriptExecutor) driver;//ép kiểu sang driver
    }

    @Test
    public void TC_01_ORangeHRM() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com");
        //login
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        // đợi cho all item biến mất
        Assert.assertTrue(isLoadingIconDisappear());

        //verify Dashboard page display
        Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='Dashboard']")).isDisplayed());

        // click PIM link
        driver.findElement(By.xpath("//span[text()='PIM']/parent::a")).click();
        Assert.assertTrue(isLoadingIconDisappear());

        //verify PIM page display
        Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='PIM']")).isDisplayed());

        /*Chọn item trong Employment Status*/
        selectItemInOrangeDropdown("Employment Status", "Employment Status", "Full-Time Contract");

        /*Chọn item trong include*/
        selectItemInOrangeDropdown("Include", "Include", "Current Employees Only");

        /*Chọn item trong Job Title*/
        selectItemInOrangeDropdown("Job Title", "Job Title", "Chief Executive Officer");

        /*Chọn item trong sub unit*/
        selectItemInOrangeDropdown("Sub Unit", "Sub Unit", "TechOps");

        //Kiểm tra
        //Cách 1: (khi KQ chạy fail >> nhìn rõ được KQ thực tế, MM là gì >> thấy rõ lỗi ở đâu --> ưu tiên cách 1)
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employment Status']/" +
                "parent::div/following-sibling::div//div[@class='oxd-select-text-input']")).getText(), "Full-Time Contract");

        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Include']/" +
                "parent::div/following-sibling::div//div[@class='oxd-select-text-input']")).getText(), "Current Employees Only");

        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Job Title']/" +
                "parent::div/following-sibling::div//div[@class='oxd-select-text-input']")).getText(), "Chief Executive Officer");

        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Sub Unit']/" +
                "parent::div/following-sibling::div//div[@class='oxd-select-text-input']")).getText(), "TechOps");


        //Cách 2 (khi KQ chạy fail không show rõ lỗi ở đâu; TH nên dùng isDisplayed: là khi k truyền text vào)
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Employment Status']/" +
                "parent::div/following-sibling::div//div[@class='oxd-select-text-input' and text()='Full-Time Contract']")).isDisplayed());

        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Include']/" +
                "parent::div/following-sibling::div//div[@class='oxd-select-text-input' and text()='Current Employees Only']")).isDisplayed());

        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Job Title']/" +
                "parent::div/following-sibling::div//div[@class='oxd-select-text-input' and text()='Chief Executive Officer']")).isDisplayed());

        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Sub Unit']/" +
                "parent::div/following-sibling::div//div[@class='oxd-select-text-input' and text()='TechOps']")).isDisplayed());
    }

    @Test
    public void TC_02_JQuery() throws InterruptedException {
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
        selectItemInSelectableDropdown("//span[@id='speed-button']", "//ul[@id='speed-menu']/li/div", "Slow");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']")).getText(), "Slow");

        selectItemInSelectableDropdown("//span[@id='speed-button']", "//ul[@id='speed-menu']/li/div", "Fast");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']")).getText(), "Fast");

        selectItemInSelectableDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "3");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "3");

        selectItemInSelectableDropdown("//span[@id='salutation-button']", "//ul[@id='salutation-menu']/li/div", "Dr.");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='salutation-button']/span[@class='ui-selectmenu-text']")).getText(), "Dr.");
    }

    @Test
    public void TC_03_ReactJS() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInSelectableDropdown("//div[@class='ui fluid selection dropdown']", "//div[@class='visible menu transition']/div/span", "Stevie Feliciano");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Stevie Feliciano");

        selectItemInSelectableDropdown("//div[@class='ui fluid selection dropdown']", "//div[@class='visible menu transition']/div/span", "Christian");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Christian");

        selectItemInSelectableDropdown("//div[@class='ui fluid selection dropdown']", "//div[@class='visible menu transition']/div/span", "Justen Kitsune");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Justen Kitsune");
    }

    @Test
    public void TC_04_VueJS() throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInSelectableDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li/a", "Second Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");

        selectItemInSelectableDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li/a", "Third Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Third Option");
    }

    @Test
    public void TC_05_Editable() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        selectItemInEditableDropdown("//input[@class='search']", "//div[@class='visible menu transition']//span", "Algeria");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Algeria");

        selectItemInEditableDropdown("//input[@class='search']", "//div[@class='visible menu transition']//span", "Bangladesh");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Bangladesh");
    }

    @Test
    public void TC_06_FinPeace() throws InterruptedException {
        driver.get("https://sps.finpeace.vn/tools/sktccn");

        selectItemInEditableDropdown("//input[@id='job_id']", "//div[@id='job_id_list']/following-sibling::div[@class='rc-virtual-list']//div[@class='ant-select-item-option-content']", "Ngân hàng");

        Assert.assertEquals(driver.findElement(By.xpath("//label[@title='Nghề nghiệp']/parent::div/following-sibling::div//span[@class='ant-select-selection-item']")).getText(), "Ngân hàng");

        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='ant-select-selection-item']")).getText(), "Ngân hàng");

        selectItemInEditableDropdown("//input[@id='gender']", "//div[@id='gender_list']/following-sibling::div[@class='rc-virtual-list']//div[@class='ant-select-item-option-content']", "Nữ");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@title='Giới tính']/parent::div/following-sibling::div//span[@class='ant-select-selection-item']")).getText(), "Nữ");

        selectItemInEditableDropdown("//input[@id='married_status']", "//div[@id='married_status_list']/following-sibling::div[@class='rc-virtual-list']//div[@class='ant-select-item-option-content']", "Độc thân, chưa có con");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@title='Tình trạng hôn nhân']/parent::div/following-sibling::div//span[@class='ant-select-selection-item']")).getText(), "Độc thân, chưa có con");
    }

    private void selectItemInEditableDropdown(String editableXPath, String childXPath, String expectedItem) throws InterruptedException {
        driver.findElement(By.xpath(editableXPath)).clear();
        driver.findElement(By.xpath(editableXPath)).sendKeys(expectedItem);
        Thread.sleep(2000);

        List<WebElement> allItem = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXPath)));
        for (WebElement temp : allItem) {
            String textItem = temp.getText();
            if (textItem.equals(expectedItem)) {
                temp.click();
                Thread.sleep(2000);
                break;
            }
        }
    }

    private void selectItemInSelectableDropdown(String parentXPath, String childXPath, String expectedItem) throws InterruptedException {
        driver.findElement(By.xpath(parentXPath)).click();
        List<WebElement> allItem = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXPath)));
        for (WebElement temp : allItem) {
            String textItem = temp.getText();
            if (textItem.equals(expectedItem)) {
                temp.click();
                Thread.sleep(2000);
                break;
            }
        }
    }

    private void selectItemInOrangeDropdown(String parentLocator, String childLocator, String expectedItem) throws InterruptedException {
        /*Chọn item CTO trong Job Title*/
        /*1. Click vào dropdown cho sổ ra (Parent locator)*/
        driver.findElement(By.xpath("//label[text()='" + parentLocator + "']/parent::div/following-sibling::div/div[@class='oxd-select-wrapper']")).click();

        /*2. Chờ cho các item trong dropdown đc load ra hết (Child locator)*/
        // bắt 1 locator đại diện cho tất cả các item bên trong
        // lưu tất cả item vào 1 list item
        List<WebElement> allItem = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//label[text()='" + childLocator +
                        "']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-option')]/span")));

        // dùng vòng lặp duyệt qua hết tất cả các item trên
        for (WebElement temp : allItem) {
            // get text của từng item ra
            String textItem = temp.getText();

            /*3. Kt tất cả các item xem đâu là item cần chọn */
            if (textItem.equals(expectedItem)) {

                /*4. Nếu tìm thấy thì click vào item đó*/
                temp.click();
                Thread.sleep(2000);

                /*5. Chọn xong r thì tự động đóng dropdown*/
                break;
            }
        }
    }

    private Boolean isLoadingIconDisappear() {

        /* Chờ cho element/s hiển thị hết: visible - cần nhìn thấy được & có kích thước cụ thể
            Chờ cho element/s load ra hết: presence
            Chờ cho element/s biến  mất hết: invisible - chỉ cần có ở HTML thôi, k bắt buộc phải nhìn thấy
            Chờ cho element/s có thể click vào được: clickable
            Chờ cho element/s đã được chọn thành công: selected
        */
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions
                .invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner"))));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
