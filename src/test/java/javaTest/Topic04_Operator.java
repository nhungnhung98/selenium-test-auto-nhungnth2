package javaTest;

import org.testng.annotations.Test;

import java.util.Scanner;

public class Topic04_Operator {

    @Test
    public void TC_01() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhap ten cua ban:");
        String name = scanner.nextLine();

        System.out.print("Nhập tuoi cua ban: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        int P2 = age + 15;
        System.out.println("Sau 15 nam, tuoi của " + name + " se la " + P2);
        scanner.close();
    }


}

