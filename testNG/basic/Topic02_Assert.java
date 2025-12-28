package basic;

import org.junit.Assert;
import org.junit.Test;

public class Topic02_Assert {
    @Test
    public void login(){
        // Bắt buộc phải là kiểu dữ liệu boolean
        // Selenium: tiền tố là isX: isDisplayed/ isEnable/ isSelected/ isMultiple
        // Hàm tự define trả về boolean
        // True: khi cần kiểm tra 1 dữ liệu trả về là đúng
        // False: khi kiểm tra 1 dữ liệu trả về là sai

        Assert.assertTrue(5 > 3);
        boolean status =5 < 3;
        System.out.println(status);
        Assert.assertFalse(status);

        //Equals: kiểm tra dữ liệu mong đợi với thực tế giống nhau
        // 2 vế đều cùng 1 kiểu dữ liệu
        // Kiểm tra về giá trị của dữ liệu và kiểu dữ liệu
        String studentName ="Đặng Thuỳ Trâm";

        Assert.assertEquals(studentName,"Đặng Thuỳ Trâm");
        Assert.assertEquals(studentName,"Đặng Thuỳ trâm");

        Object name="Đặng Thuỳ Trâm";

        Assert.assertEquals(studentName, name);
        int iNumber=15;
        float fNumber=15;

        Assert.assertEquals(iNumber, fNumber);




    }


}
