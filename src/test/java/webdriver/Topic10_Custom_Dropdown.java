package webdriver;

import org.openqa.selenium.By;
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

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        //Select select;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
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

        //B1 Click vào element để dropdown xổ ra (Parent Locator)
        driver.findElement(By.xpath("//label[text()='Job Title']/parent::div/following-sibling::div/div[@class='oxd-select-wrapper']")).click();

        //B2-Cho cac item dc load ra het (Child Locator)
        //Mot locator dai dien cho tat ca locator ben trong
        //Luu tat ca element vao 1 List Element
        List<WebElement> allItems =new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfAllElementsLocatedBy
                (By.xpath("//label[text()='Job Title']/parent::sibling::div[@class='oxd-select-option']/span")));

        //B3-Kiem tra dau la item can chon
        //Vong lap de duyet qua tat ca cac item
        for(WebElement temp :allItems) {
            //Get text cua tung element ra
            String textItem=temp.getText();

            //Kiem tra text nao la cai ite can chon thi click vao
            if(textItem.equals("Chief Technical Officer")){
                //B4- Neu tim thay thi click vao item do
                temp.click();
                Thread.sleep(2000);

                //Thoat vong lap
                break;
            }


        }
        }


    private boolean isLoadingIconDisappear() {
        return new WebDriverWait(driver,Duration.ofSeconds(15)).until(ExpectedConditions
                .invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner"))));
    }



    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
