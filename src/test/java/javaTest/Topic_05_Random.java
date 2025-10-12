package javaTest;

import java.util.Random;

public class Topic_05_Random {
    public static void main(String[] args) {
        Random rand=new Random();

        System.out.println(rand.nextInt());

        //0-999
        System.out.println(rand.nextInt(999));
        System.out.println(rand.nextInt(999)+"@hotmail.com");
    }
}
