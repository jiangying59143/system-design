package com.my;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class DisposableTest {
    public static void main(String[] args) {
        Flux flux = Flux.range(1,10000)
                .delayElements(Duration.ofSeconds(1))
                .log();

        Disposable disposable = flux.subscribe(System.out::println);
        new Thread(()->{
            try {
                Thread.sleep(10000);
                disposable.dispose();// 10秒后取消
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }
}
