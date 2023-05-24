package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        StringList stringList = new StringListImpl();
        System.out.println(stringList.add("wwww0"));
        System.out.println(stringList.add("wwww1"));
        System.out.println(stringList.add("wwww2"));
        System.out.println(stringList.add("wwww3"));
        System.out.println(stringList.add("wwww4"));
        System.out.println(stringList.add("wwww5"));
        System.out.println(stringList.add("wwww6"));
        System.out.println(stringList.add("wwww7"));
        System.out.println(stringList.add("wwww8"));
        System.out.println(stringList.add("wwww9"));
        System.out.println(stringList.size());
        System.out.println(stringList.getCapacity());
        System.out.println(stringList.remove("wwww5"));
        System.out.println(stringList.remove("wwww6"));
        System.out.println(stringList.remove("wwww7"));
        System.out.println(stringList.remove("wwww8"));
        System.out.println(stringList.remove("wwww9"));

        System.out.println(Arrays.toString(stringList.getStringList()));


    }
}