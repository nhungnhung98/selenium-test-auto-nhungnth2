package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic08_TextBox_TextArea {
    WebDriver driver;
    String firstName, lastName, emailAddress, password, fullName, employeeId, passportNumber, passportComment;

    @BeforeClass
    public void beforeClass() {
        FirefoxOptions options= new FirefoxOptions();
        options.setAcceptInsecureCerts(true);
        driver = new FirefoxDriver();
        
        firstName = "Nhung";
        lastName = "Nguyen";
        fullName = firstName + " " + lastName;
        emailAddress = "nhung" + new Random().nextInt(999) + "@hotmail.com";
        password = "Tessting@123";
        passportNumber="431276152";
        passportComment="Assigned Immigration\n Records";
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
    public void TC_02_OrangeHRM() throws InterruptedException
    {
        //Login
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='passsword']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        //Wait for loading icon disappear
        Assert.assertTrue(isLoadingIconDisappear());

        //verify hien thi Dashboard
        Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='Dashboard']")).isDisplayed());

        //click PIM
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='PIM']")).isDisplayed());

        //Add employee
        driver.findElement(By.xpath("//a[text()='Add Employee]")).click();
        Assert.assertTrue(isLoadingIconDisappear());

        //Create new Employee
        driver.findElement(By.xpath("input[name='firstName']")).sendKeys(firstName);
        driver.findElement(By.xpath("input[name='lastName']")).sendKeys(lastName);

        employeeId=driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value");
        System.out.println(employeeId);

        driver.findElement(By.xpath("oxd-switch-input oxd-switch-input--active --label-right")).click();
        Thread.sleep(2000);


        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(firstName);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);

        driver.findElement(By.xpath("//button[contains(string(),'Save'")).click();

        //verify success message display
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Sucessfully Saved']")).isDisplayed());
        Assert.assertTrue(isLoadingIconDisappear());

        //Personal detail list
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getDomProperty("value"),firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getDomProperty("value"),lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value"),employeeId);
        //Immigration page
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Assert.assertTrue(isLoadingIconDisappear());

        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button[contains(string(),'Add')]")).click();

        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportNumber);
        driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(passportComment);

        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
        Thread.sleep(2000);

        // verify success message display
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Successfully Saved']")).isDisplayed());
        Assert.assertTrue(isLoadingIconDisappear());

        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Passport']/parent::div/following-sibling::div/div[text()='"+
                passportNumber+"']")).isDisplayed());

        //logout
        driver.findElement(By.cssSelector("li.oxd-userdropdown")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        //login = acc được tạo
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        // đợi cho all item biến mất
        Assert.assertTrue(isLoadingIconDisappear());
        Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='Dashboard']")).isDisplayed());


    }

    private Boolean isLoadingIconDisappear(){
        return new WebDriverWait(driver,Duration.ofSeconds(15)).until(ExpectedConditions
                .invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner"))));
    }
    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }
}
