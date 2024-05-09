package com.my;

import org.junit.Test;
import org.reactivestreams.Publisher;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public class CommonTest {
    @Test
    public void testFlatMap(){
        Flux.just("zhang san", "li si")
                // only for flux
                .flatMap(name ->
                        Flux.fromArray(name.split(" ")))
                .subscribe(System.out::println);
    }

    @Test
    public void testFlatMapMany(){
        Mono.just("just a phrase with tabs!")
                //only for mono
                .flatMapMany(phrase -> Flux.fromArray(phrase.split("\\s+")))
                .subscribe(System.out::println);
    }

    @Test
    public void testConcat() {
        Flux.concat(Flux.just(1, 2), Flux.just("string"))
                .subscribe(System.out::println);
    }

    @Test
    public void testConcatWith() {
        // type should be the same
        Flux.just(1, 2).concatWith(Flux.just(3))
                .subscribe(System.out::println);
    }

    @Test
    public void testConcatMap() {
        Flux.just(1, 3, 5, 2, 4, 6, 11, 12, 13)
                .groupBy(i -> i % 2 == 0 ? "even" : "odd")
                .concatMap(g -> g.defaultIfEmpty(-1) //if empty groups, show them
                        .map(String::valueOf) //value map to string
                        .startWith(g.key() + ":")//start with the group's key
                )
                .subscribe(System.out::println);
    }

    @Test
    public void transformTest(){
        Flux.just("pedro", "simon", "stephane")
                .transform(flux->flux.take(2))
                .transform(flux->flux.filter(s->s.startsWith("s")))
                .subscribe(System.out::println);
    }

    @Test
    public void testDefaultIfEmpty() {
        Mono.empty()
                //值
                .defaultIfEmpty("EMPTY_STR")
                .subscribe(System.out::println);
    }

    @Test
    public void testSwitchIfEmpty() {
        Mono.just("I'm Jiang Ying")
                .flatMapMany(
                        //\s //匹配任何空白字符，包括空格、制表符、换页符等等。等价于 [ \f\n\r\t\v]
                        phrase -> Flux.fromArray(phrase.split("\\s+"))
                )
                // 流
                .switchIfEmpty(Mono.just("EMPTY_STR"))
                .subscribe(System.out::println);

        System.out.println("-------");

        Mono.empty()
                .flatMapMany(
                        //\s //匹配任何空白字符，包括空格、制表符、换页符等等。等价于 [ \f\n\r\t\v]
                        phrase -> Flux.fromArray(String.valueOf(phrase).split("\\s+"))
                )
                .switchIfEmpty(Mono.just("EMPTY_STR"))
                .subscribe(System.out::println);
    }

    @Test
    public void mergeWithTest(){
        Flux.just(1,3,5).mergeWith(Flux.just(2,4,6))
                .subscribe(System.out::println);
    }

    @Test
    public void mergeComparingWithTest(){
        Flux.just(1,3,5).mergeComparingWith(Flux.just(2,4,6), Integer::compareTo)
                .subscribe(System.out::println);
    }

    @Test
    public void zipTest(){

        Flux.just(1,3,5,7)
                .zipWith(Flux.just(2,4,6))
                .subscribe(System.out::println);

        // 取最少
        /*
        [1,2]
        [3,4]
        [5,6]
        */
    }

    @Test
    public void timeoutTest() throws InterruptedException, IOException {
        Flux.just(1, 2, 3, 4)
                .log()
                .delayElements(Duration.ofSeconds(2))
                .retry(2)
                .timeout(Duration.ofSeconds(2))
                .subscribe();
        System.in.read();
    }

    @Test
    public void sinksManyUnicast() throws IOException, InterruptedException {
        //发送flux
//        Sinks.many();

        //单播
//        Sinks.many().unicast();

        //多播
//        Sinks.many().multicast();

        // 重放 类似kafka 从头消费
//        Sinks.many().replay();

        //发送mono
//        Sinks.one();

        Sinks.Many<Object> sinkMany = Sinks.many()
                .unicast()
                .onBackpressureBuffer(new LinkedBlockingQueue<>(5));

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                sinkMany.tryEmitNext("a->" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();



        sinkMany.asFlux().subscribe(v-> System.out.println("v = " + v));


        Thread.sleep(11*1000);
    }

    @Test
    public void sinksManyMulticast() throws IOException, InterruptedException {

        //多播
//        Sinks.many().multicast();

        Sinks.Many<Object> sinkMany = Sinks.many()
                .multicast()
                .onBackpressureBuffer();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                sinkMany.tryEmitNext("a->" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();



        sinkMany.asFlux().subscribe(v-> System.out.println("v = " + v));

        new Thread(()->{
            try {
                Thread.sleep(Duration.ofSeconds(3));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sinkMany.asFlux().subscribe(v-> System.out.println("v2 = " + v));
        }).start();

        Thread.sleep(16*1000);
    }

    @Test
    public void sinksReplay() throws InterruptedException {

        //多播
//        Sinks.many().multicast();

        Sinks.Many<Object> sinkMany = Sinks.many().replay().limit(3);

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                sinkMany.tryEmitNext("a->" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();



        sinkMany.asFlux().subscribe(v-> System.out.println("v = " + v));

        new Thread(()->{
            try {
                Thread.sleep(Duration.ofSeconds(5));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 睡5s limit 3 所以从2 或3开始
            sinkMany.asFlux().subscribe(v-> System.out.println("v2 = " + v));
        }).start();

        Thread.sleep(16*1000);
    }

    @Test
    public void testCache() throws InterruptedException {

        Flux<Integer> flux = Flux.range(1,10)
                .cache(2) //test
                .delayElements(Duration.ofSeconds(1));

        flux.subscribe(v-> System.out.println("v = " + v));

        new Thread(()->{
            try {
                Thread.sleep(Duration.ofSeconds(10));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            flux.subscribe(v-> System.out.println("v2 = " + v));
        }).start();

        Thread.sleep(Duration.ofSeconds(13));
    }

//    阻塞式API
    @Test
    public void testBlock(){
        List<Integer> block = Flux.range(1, 5)
                .collectList()
                .block();

        block.forEach(v-> System.out.println("v = " + v));
    }

    @Test
    public void parallelTest() {
        Flux.range(1, 100)
                .buffer(10)
                .parallel(8)
                .runOn(Schedulers.newParallel("yy",8))
                .log()
                .subscribe();
    }

    // ThreadLocal 无法使用 线程不固定 只能使用context共享数据
    @Test
    public void contextTest(){
        Flux.just(1,2,3)
                .transformDeferredContextual((flux,context)->{
                    System.out.println("flux = " + flux);
                    System.out.println("context = " + context);
                    return flux.map(i->
                            i+"==>"+context.get("context1") +
                                    "\n\t" + context.get("context2") +
                                    "\n\t" + context.get("context3") +
                                    "\n\t" + context.get("context4") +
                                    "\n\t" + context.get("context5") +
                                    "\n\t" + context.get("context6")
                    );
                })
                .contextWrite(context->Context.of(
                        Map.of(
                                "context1", "context1 - appendix value",
                                "context2", "context2 - appendix value",
                                "context3", "context3 - appendix value",
                                "context4", "context4 - appendix value",
                                "context5", "context5 - appendix value",
                                "context6", "context6 - appendix value"
                        )))
                //ThreadLocal共享了数据，上游的所有人能看到; Context由下游传播给上游
                .subscribe(System.out::println);
    }
}
