package platformWeb;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class Web_02_Product {
   @BeforeClass
    public static void beforeClass(){

    }

    @Test(groups={"platformWeb"})
    public void TC_01_CreateProduct(){
        System.out.println("Test 01");
    }

    @Test(groups={"platformWeb"})
    public void TC_02_EditPrduct(){
        System.out.println("Test 02");
    }

    @Test(groups={"platformWeb"})
    public void TC_03_ViewProduct(){
        System.out.println("Test 03");
    }


    @AfterClass
    public static void afterClass(){

    }
}
