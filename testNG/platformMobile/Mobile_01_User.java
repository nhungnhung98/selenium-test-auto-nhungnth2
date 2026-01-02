package platformMobile;

import org.testng.annotations.*;

public class Mobile_01_User {
    @BeforeClass
    public void beforeClass(){

    }

    @Test(groups={"platformMobile"})
    public void Payment_01_Cheque(){
        System.out.println("Test 01");
    }

    @Test(groups={"platforMobile"})
    public void Payment_02_Card(){
        System.out.println("Test 02");
    }

    @Test(groups={"platformMobile"})
    public void Payment_03_Cash(){
        System.out.println("Test 03");
    }


    @AfterClass
    public static void afterClass(){

    }
}
