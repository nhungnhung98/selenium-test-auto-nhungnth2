package platformWeb;

import org.testng.annotations.*;

public class Web_01_User {
    @BeforeClass
    public void beforeClass() {

    }

    @Test(groups = {"platformWeb"})
    public void TC_01_CreateNewUser() {
        System.out.println("Test 01");
    }

    @Test(groups = {"platformWeb"})
    public void TC_02_EditUser() {
        System.out.println("Test 02");
    }

    @Test(groups = {"platformWeb"})
    public void TC_03_ViewUser() {
        System.out.println("Test 03");
    }


    @AfterClass
    public static void afterClass() {

    }
}
