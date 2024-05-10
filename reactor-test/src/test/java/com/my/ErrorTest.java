package com.my;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ErrorTest {
    /* 类似下面实现
        try {
          return doSomethingDangerous(10);
        }
        catch (Throwable error) {
          return "RECOVERED";
        }
    */
    @Test
    public void errorReturn() throws InterruptedException {
        Flux.just(1, 2, 0, 4)
                .map(i -> "100 / " + i + " = " + (100 / i))
                // NullPointerException
                .onErrorReturn(NullPointerException.class,"哈哈-6666")
                .subscribe(v-> System.out.println("v = " + v),
                        err -> System.out.println("err = " + err),
                        ()-> System.out.println("流结束")); // error handling example
        /*
            v = 100 / 1 = 100
            v = 100 / 2 = 50
            err = java.lang.ArithmeticException: / by zero
        */
    }

    @Test
    public void errorReturn2() throws InterruptedException {
        Flux.just(1, 2, 0, 4)
                .map(i -> "100 / " + i + " = " + (100 / i))
                // ArithmeticException
                .onErrorReturn(ArithmeticException.class,"哈哈-6666")
                .subscribe(v-> System.out.println("v = " + v),
                        err -> System.out.println("err = " + err),
                        ()-> System.out.println("流结束")); // error handling example
        /*
            v = 100 / 1 = 100
            v = 100 / 2 = 50
            v = 哈哈-6666
            流结束
        */
    }

    @Test
    public void errorResume(){
        Flux.just(1, 2, 0, 4)
                .map(i -> "100 / " + i + " = " + (100 / i))
                // ArithmeticException
                .onErrorResume(
                        (err)->err instanceof ArithmeticException,
                        (err)-> Mono.just("哈哈-6666")
                )
                .subscribe(v-> System.out.println("v = " + v),
                        err -> System.out.println("err = " + err),
                        ()-> System.out.println("流结束"));
    }


    @Test
    public void errorResume2(){
        Flux.just(1, 2, 0, 4)
                .map(i -> "100 / " + i + " = " + (100 / i))
                // ArithmeticException
                .onErrorResume(
                        (err)->err instanceof ArithmeticException,
                        (err)-> Flux.error(new RuntimeException("计算错误"))
                )
                .subscribe(v-> System.out.println("v = " + v),
                        err -> System.out.println("err = " + err),
                        ()-> System.out.println("流结束"));
    }

    @Test
    public void doOnError(){
        Flux.just(1, 2, 0, 4)
                .map(i -> "100 / " + i + " = " + (100 / i))
                .doOnError(err -> {
                    System.out.println("err已被记录 = " + err);
                    err.printStackTrace();
                }).subscribe(v -> System.out.println("v = " + v),
                        err -> System.out.println("err = " + err),
                        () -> System.out.println("流结束"));
    }

    @Test
    public void doOnError2() throws InterruptedException {
        Flux.just(1, 2, 0, 4)
                .map(i -> "100 / " + i + " = " + (100 / i))
                .doOnError(err -> {
                    System.out.println("err已被记录 = " + err);
                })
                .doFinally(signalType -> {
                    System.out.println("流信号："+signalType);
                })
                .subscribe(v -> System.out.println());
    }

    @Test
    public void doOnErrorContinue() {
        Flux.just(1, 2, 0, 4)
                .map(i -> "100 / " + i + " = " + (100 / i))
                .onErrorContinue((err, value) -> {
                    System.out.println(value + " 失败, err已被记录 = " + err);
                })
                .subscribe(System.out::println);
    }


}
