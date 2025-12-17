package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.regex.Pattern;

public class Topic25_Wait_PV_Explicit_Fuction {
    WebDriver driver;
    WebDriverWait explicitWait;


    @BeforeClass
    public void beforClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01() throws InterruptedException {
        //Chờ cho đến khi thoả mãn điều kiện là alert được Present
        explicitWait.until(ExpectedConditions.alertIsPresent());

        // Element visible (Cho 1/nhiều/tham số là gì)
        // Chờ 1 element hiển thị
        WebElement emailTextbox = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
        emailTextbox.sendKeys("Auto test");

        //Chờ nhiều
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(
                driver.findElement(By.cssSelector("input#email")),
                driver.findElement(By.cssSelector("input#pass")),
                driver.findElement(By.cssSelector("input#name"))
        ));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements());

        // Element invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));

        // Element invisible
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")));

        // Element Staleness=> chi co 1 kieu duy nhat
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));


        // Elenment  clickable
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));

        //Element selected
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));

        //Element có số lượng bằng bao nhiêu (ít hơn/bằng/nhiều hơn)
        explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""), 5));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(""), 5));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(""), 5));

        // Kết hợp 2 điều kiện (AND/OR/NOT)
        //Cả 2 đều đúng
        explicitWait.until(ExpectedConditions.and(
                ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")),
                ExpectedConditions.elementToBeClickable(By.cssSelector(""))));

        // 1 trong 2 đúng
        explicitWait.until(ExpectedConditions.or(
                ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")),
                ExpectedConditions.elementToBeClickable(By.cssSelector(""))));

        // Phủ định của visible =invisible
        explicitWait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))));

        //Url/Title/Text
        explicitWait.until(ExpectedConditions.urlToBe(""));
        explicitWait.until(ExpectedConditions.urlContains(""));
        explicitWait.until(ExpectedConditions.urlMatches(""));

        explicitWait.until(ExpectedConditions.titleIs(""));
        explicitWait.until(ExpectedConditions.titleContains(""));

        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""), "Automation"));
        explicitWait.until(ExpectedConditions.textMatches(By.cssSelector(""), Pattern.compile("Automation")));

        explicitWait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("")), "Automation"));

        //Attribute/DOM property/ Frame
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector(""), "class", "email"));

        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.cssSelector("")), "textContent", "Hello"));
        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.cssSelector("")), "id", "finish"));

        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("facebook"));//text
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));//dùng index
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));//dùng index
    }


}

