package testNG;

import org.openqa.selenium.By;
import org.testng.Assert;

public class Topic1_Assert {
    public void TC_01(){
        //Kiem tra du lieu dung
        Assert.assertTrue(3 < 5);
        //Assert.assertTrue((driver.findElement(By.cssSelector("")).isEnabled());

        //Kiem tra du lieu sai
        //Assert.assertFalse(driver.findElement(By.cssSelector("")).isEnable());

        // Kiem tra du lieu thuc te va mong doi bang nhau
        Assert.assertEquals(15,16);
    }
}
