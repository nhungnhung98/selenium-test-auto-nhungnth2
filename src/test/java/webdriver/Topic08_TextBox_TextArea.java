package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic08_TextBox_TextArea {
    WebDriver driver;
    String firstName, lastName, emailAddress, password, fullName;

    @BeforeClass
    public void beforeClass() {
        FirefoxOptions options= new FirefoxOptions();
        options.setAcceptInsecureCerts(true);
        driver = new FirefoxDriver();
        
        firstName = "Nhung";
        lastName = "Nguyen";
        fullName = firstName + " " + lastName;
        emailAddress = "nhung" + new Random().nextInt(999) + "@hotmail.com";
        password = "123456789";
    }

    @Test
    public void TC_01_Techpanda() throws InterruptedException {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);

        driver.findElement(By.cssSelector("button[title='Register']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");

        String contactInfo = String.valueOf(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div//following-sibling::div/p")));
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        driver.findElement(By.xpath("//h3[text()='Contact Information']/following-sibling::a")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"), firstName);//Cach 1
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getDomAttribute("value"), lastName);//Cach 2
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getDomAttribute("value"), emailAddress);

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//h2/a[@title='Samsung Galaxy']")).click();
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();
        driver.findElement(By.xpath("//input[@value='5']")).click();
        driver.findElement(By.xpath("//textarea[@id='review_field']")).sendKeys("Thank you for registering\n Nhung test");
        driver.findElement(By.xpath("//input[@id='summary_field']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Your review has been accepted for moderation.");

    }
    @Test
    public void TC_02_() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        Assert.assertEquals(driver.getTitle(),"Customer Login");

    }

    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }
}
