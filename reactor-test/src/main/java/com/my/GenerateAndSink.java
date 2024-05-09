package com.my;

import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicLong;


public class GenerateAndSink {
    public static void main(String[] args) {
        Flux<String> flux = Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3*state);
                    if (state == 1000) sink.complete();
                    return state + 1;
                });
        flux.subscribe(System.out::println);

        Flux<String> flux2 = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long value = state.getAndIncrement();
                    sink.next("3 x " + value + " = " + 3*value);
                    if (value == 100) sink.complete();
                    return state;
                });
        flux2.subscribe(System.out::println);
    }
}
