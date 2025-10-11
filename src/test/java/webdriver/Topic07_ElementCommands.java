package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Logger;

public class Topic07_ElementCommands {
    private static final Logger log = (Logger) LoggerFactory.getLogger(Topic07_ElementCommands.class);
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01() {
        //Click vao element dang button/checkbox/radio/link
        driver.findElement(By.cssSelector("")).click();

        // Nhap lieu vao element cho phep edit
        driver.findElement(By.cssSelector("")).sendKeys();
        driver.findElement(By.cssSelector("")).clear();
        
        driver.findElement(By.cssSelector("form#small-search-box-form>input#small-searhterms"));
        
        //Tim element enable hay disable
        // Ap dung cho tat ca cac loai element
        Assert.assertTrue(driver.findElement((By.cssSelector(""))).isEnabled());
        Assert.assertFalse(driver.findElement(By.cssSelector("")).isEnabled());

        //Kiem tra 1 element co hien thi k
        //Co the nhin thay tren UI
        //Ap dung cho tat ca cac loai Element
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("")).isDisplayed());

        //Kiem tra xem element da duoc chon hay chua
        // ap dung cho checkbox/radio/Dropdown(selectable)
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isSelected());

        //ham get: lay du lieu, ham set: sua du lieu
        //Lay du lieu dang Text cua link,header,Error message
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getText(),"This is a required field");

        //Lay ra gia tri cua thuoc tinh HTML
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getAttribute("placeholder"),"Search entire store here");
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getAttribute("class"),"input-text required-entry");

        //Lay ra gia tri cua thuoc tinh HTML
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getDomAttribute("placeholder"),"Search entire store here");

        //Lay ra gia tri cua DOM(Document Object model)
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getDomProperty("placeholder"),"Search entire store here");
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getDomProperty("className"),"input-text required-entry");

        //Lay gia tri cua CSS (ngon ngu FE)
        Assert.assertEquals(driver.findElement(By.cssSelector("background-color")).getCssValue("background-color"),"rbg(51,153,204)");
        Assert.assertEquals(driver.findElement(By.cssSelector("background-color")).getCssValue("font-size"),"13px");

        // it dung, co the bo qua
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getAccessibleName(),"");
        Assert.assertEquals(driver.findElement(By.cssSelector("background-color")).getAriaRole(),"");

        //Lay ra chieu  rong, chieu cao cua element
//        Dimension loginButtonSize= driver.findElement((By.cssSelector("")).getSize();
//        loginButtonSize.getHeight();
//        loginButtonSize.getWidth();

        //lay ra vi tri của element so voi man hinh
        Point loginButtonLocation=driver.findElement(By.cssSelector("")).getLocation();
        loginButtonLocation.getX();
        loginButtonLocation.getY();

        // Bao gom ca size va Location
        Rectangle loginButtonRect=driver.findElement(By.cssSelector("")).getRect();
        loginButtonRect.getHeight();
        loginButtonRect.getWidth();
        loginButtonRect.getX();
        loginButtonRect.getY();
        //ep kieu
       // loginButtonSize=loginButtonRect.getDimension();
        loginButtonLocation=loginButtonRect.getPoint();

        //lay ra HTML cua element day
        String elementA=driver.findElement(By.cssSelector("")).getTagName();
        driver.findElement(By.cssSelector(elementA+""));

        //Shadow DOM -dung khi element nam trong Shadow root
        driver.findElement(By.cssSelector("")).getShadowRoot();

        //Chi ap dung cho element nam trong form
        //Tuong tu nhu gui request len server
        driver.findElement(By.cssSelector("")).submit();



    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

}
