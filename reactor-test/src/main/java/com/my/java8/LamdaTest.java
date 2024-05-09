package com.my.java8;

import java.util.Arrays;
import java.util.stream.Stream;

public class LamdaTest {
    public static void main(String[] args) {
        
        Stream.of(1,2,3)
                .peek(System.out::println)
                .filter(ele->ele>1)
                .forEach(v-> System.out.println("v = " + v));


        //reduce
        Stream.of(1,2,3,4)
                .reduce((x, y)->x+y)
                .ifPresent(System.out::println);
    }
}
