package com.my.java8;

import java.util.stream.Stream;

public class LamdaTest {
    public static void main(String[] args) {
//        Stream.of(new Person("王五", 1), new Person("李四", 2));
        Stream.of(1,2,3)
                .peek(System.out::println)
                .filter(ele->ele>1)
                .forEach(System.out::println);
    }

    public class Person{
        public String name;

        public String gender;
        public int age;

        public Person(String name, String gender, int age) {
            this.name = name;
            this.gender = gender;
            this.age = age;
        }
    }
}
