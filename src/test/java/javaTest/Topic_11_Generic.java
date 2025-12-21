package javaTest;

import java.util.ArrayList;

public class Topic_11_Generic {
    public static void main(String[] args) {
        // Non Generic
        ArrayList student =new ArrayList<>();
        student.add("Nguyen Van A");
        student.add("25");
        student.add("9.5");
        student.add("true");

        //Generic
        ArrayList<String> std =new ArrayList<>();
        std.add("Nguyen Van A");




    }
}
