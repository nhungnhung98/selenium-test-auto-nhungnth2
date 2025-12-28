package basic;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class Topic05_Priority {

    @BeforeClass
    public static void beforeClass() {
}

    @Test //mặc định enable=true
    public void TC01_register()
    {
        System.out.println("Register new Account");
    }

    //@Test comment @Test lại thì TC không chạy
    public void TC02_login() {
        System.out.println("Login to System");
    }

    @Test(enable=false) //TC khong chay
    public void TC03_order() {
        System.out.println("Order Product");
    }

    @Test
    public void TC04_pay() {
        System.out.println("Pay Product");
    }

    @Test(enable=false) //TC khong chay
    public void TC05_ship() {
        System.out.println("Ship");
    }








    @AfterClass(alwaysRun = true)
    public void afterClass() {
}

}
