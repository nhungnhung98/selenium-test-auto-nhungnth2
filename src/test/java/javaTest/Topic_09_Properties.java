package javaTest;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Topic_09_Properties {
    public static void main(String[] args) {
        String osName = System.getProperty("os.name");
        String projectPath = System.getProperty("user.dir");
        System.out.println(osName);
        System.out.println(projectPath);

    }
}
