package basic;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Topic03_AAA_Pattern {
    //Arrange
    //Setup/Initial Data//Browser/Driver/Variable/DB...

    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver= new FirefoxDriver();
        driver.get("");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    //Action
    @Test
    public void register(){
        //Open page

        //Fill data to form

        //Submit

        //Assert

        //Verify success message

    }

}
