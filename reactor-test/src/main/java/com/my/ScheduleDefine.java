package com.my;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class ScheduleDefine {
    public static void main(String[] args) throws InterruptedException {
        flux();
    }

    public static void mono() throws InterruptedException {
        final Mono<String> mono = Mono.just("hello ");

        Thread t = new Thread(() -> mono
                .map(msg -> msg + "thread ")
                .subscribe(v ->
                        System.out.println(v + Thread.currentThread().getName())
                ),
                "mono-subscribe"
        );
        t.start();
        t.join();
    }

    public static void flux() throws InterruptedException {
        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);

        // 因为onNext 操作的单线程的，所以会从中取 一个 线程
        final Flux<String> flux = Flux
                .range(1, 2)
                .map(i -> 10 + i)
                .publishOn(s)
                .map(i -> Thread.currentThread().getName() +" value " + i);

        Thread t = new Thread(() -> flux.subscribe(System.out::println), "flux-subscribe");
        t.start();
        t.join();
        s.dispose();
//        t.join();
    }
}
