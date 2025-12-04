package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.Set;

public class Topic19_JavaScriptExecutor {
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));// chờ cho việc tìm kiếm element
        driver.manage().window().maximize();

        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01_Techpanda() {
        navigateToUrlByJS("https://live.techpanda.org");
        sleepInSecond(2);

        Assert.assertEquals(executeForBrowser("return document.domain;"), "live.techpanda.org");
        Assert.assertEquals(executeForBrowser("return document.URL;"), "https://live.techpanda.org/");

        hightlightElement("//a[text()='Mobile']");
        clickToElementByJS("//a[text()='Mobile']");
        sleepInSecond(2);

        hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
        clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
        sleepInSecond(5);

        String samsungMessage=getInnerText();
        Assert.assertTrue(samsungMessage.contains("Samsung Galaxy was added to your shopping cart."));
        Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

        hightlightElement("//a[text()='Customer Service']");
        clickToElementByJS("//a[text()='Customer Service']");
        sleepInSecond(2);

        hightlightElement("//input[@id='newsletter']");
        scrollToElementOnTop("//input[@id='newsletter']");
        sleepInSecond(2);

        sendkeyToElementByJS("//input[@id='newsletter']", "Jone" + new Random().nextInt(999) + "@hotmail.com");
        sleepInSecond(2);

        hightlightElement("//button[@title='Subscribe']");
        scrollToElementOnTop("//button[@title='Subscribe']");
        sleepInSecond(2);

        driver.switchTo().alert().accept();
        sleepInSecond(2);

        Assert.assertTrue(isExpectedTextInInnerText("Thank you for your subscription."));
        navigateToUrlByJS("https://www.facebook.com/");
        sleepInSecond(2);

        Assert.assertEquals(executeForBrowser("return document.domain;"),"live.techpanda.org");

    }
@Test
public void TC_02_Validation_Message(){
    driver.get("https://login.ubuntu.com/");

    driver.findElement(By.cssSelector("form#login-form button[name='continue']")).click();
    sleepInSecond(2);

    Assert.assertEquals(getElementValidationMessage("//form[@id='login-form']//input[@id='id_email']"), "Please fill out this field.");

    driver.findElement(By.cssSelector("form#login-form input#id_email")).sendKeys("abd@gjk@hsdh");
    driver.findElement(By.cssSelector("form#login-form button[name='continue']")).click();
    sleepInSecond(2);

    Assert.assertEquals(getElementValidationMessage("//form[@id='login-form']//input[@id='id_email']"), "Please enter an email address.");

    driver.findElement(By.cssSelector("form#login-form input#id_email")).clear();
    driver.findElement(By.cssSelector("form#login-form input#id_email")).sendKeys("abd@gmail.com");
    driver.findElement(By.cssSelector("form#login-form button[name='continue']")).click();
    sleepInSecond(2);

    Assert.assertEquals(getElementValidationMessage("//form[@id='login-form']//input[@id='id_password']"), "Please fill out this field.");

}


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].textContent;", getElement(locator));
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }
}
