package com.my.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.*;

public class FunctionTest {
    public static void main(String[] args) {
        // function 有入参 有返回
        System.out.println("\n function");
        Function<String, Integer> function = (x) -> Integer.parseInt(x);
        System.out.println(function.apply("1"));

        Function<String, Long> function1 = Long::parseLong;
        System.out.println(function1.apply("2"));

        // function 两个参数 有返回
        BiFunction<String, String, String> biFunction = (x, y) -> x.concat(y);
        System.out.println(biFunction.apply("arg1", " arg2"));

        //consumer 有入参 无返回
        System.out.println("\n consumer");
        Consumer<String> consumer = System.out::println;
        consumer.accept("consumer1");

        Consumer<Long> consumer1 = System.out::println;
        consumer1.accept(function1.apply("3"));

        BiConsumer<Integer, Integer> biConsumer = (x, y) -> System.out.println(x + " + " + y + " = " +(x+y));
        biConsumer.accept(1,2);

        //supplier 无入参 有返回
        System.out.println("\n supplier");
        Supplier<List<String>> supplier = () -> Arrays.asList("1","2","3");

        supplier.get().forEach(consumer::accept);

        //prediction test
        System.out.println("\n prediction");
        Predicate<Integer> predicate = (x)->x%2 == 0;
        System.out.println(predicate.test(1));
        System.out.println(predicate.test(2));

        // custom define
        MyMultiFunction<Integer, String> myMultiFunction = (x)->Arrays.toString(x);
        System.out.println(myMultiFunction.apply(1,2,3,4));
    }

    interface MyMultiFunction<T, R>{
        R apply(T... t);
    }
}
