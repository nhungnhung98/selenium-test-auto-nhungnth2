package javaTest;

import org.testng.annotations.Test;

import java.util.Scanner;

public class Topic03_DataType {

    @Test
    public void TC_01() {
        int a = 6;
        int b = 2;
        int P1 = a + b;
        int P2 = a - b;
        int P3 = a * b;
        int P4 = a / b;
        System.out.println("Tong se la: " + P1);
        System.out.println("Hieu se la: " + P2);
        System.out.println("Tích se la: " + P3);
        System.out.println("Thuong se la: " + P4);
    }

    //Bai 2
    @Test
    public void TC_02() {
        float a = 7.5F;
        float b = 3.8F;
        float P = a * b;
        System.out.println("Dien tich HCN la: " + P);
    }

    //Bai 3
    @Test
    public void TC_03() {
        String name = "Automation Testing";
            System.out.println("Hello "+name);
    }

}

