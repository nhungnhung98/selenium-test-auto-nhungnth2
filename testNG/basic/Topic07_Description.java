package basic;

import org.testng.annotations.*;

public class Topic07_Description {

    @BeforeClass
    public void beforeClass() {
}

    @Test (description="Azure- Register new account")
    public void TC01_register()
    {
        System.out.println("Register new Account");
    }

    @Test(description="Azure- Login to system and verify")
    public void TC02_login() {
        System.out.println("Login to System");
    }

    @Test
    public void TC03_order() {
        System.out.println("Order Product");
    }

    @Test
    public void TC04_pay() {
        System.out.println("Pay Product");
    }

    @Test
    public void TC05_ship() {
        System.out.println("Ship");
    }








    @AfterClass(alwaysRun = true)
    public void afterClass() {
}

}
